package com.transfer.ws;

import com.transfer.model.Body;
import com.transfer.model.MsgCommand;
import com.transfer.model.OscEntity;
import com.transfer.model.WsEntity;
import com.transfer.osc.MsgRetry;
import com.transfer.resp.Code;
import com.transfer.resp.MsgType;
import com.transfer.resp.RespVo;
import com.transfer.utils.JacksonUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;


@Slf4j
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

    private WebSocketServerHandshaker handshaker;
    private ConcurrentHashMap<String, WsEntity> channels;
    private ConcurrentHashMap<String, OscEntity> oscs;
    private MsgRetry msgRetry;

    public WebSocketServerHandler(ConcurrentHashMap<String, WsEntity> channels, ConcurrentHashMap<String, OscEntity> oscs, MsgRetry msgRetry) {
        this.channels = channels;
        this.oscs = oscs;
        this.msgRetry = msgRetry;
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        // 传统的HTTP接入
        if (msg instanceof FullHttpRequest) {
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        }
        // WebSocket接入
        else if (msg instanceof WebSocketFrame) {
            handleWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
        // 如果HTTP解码失败，返回HHTP异常
        if (!req.getDecoderResult().isSuccess()
                || (!"websocket".equals(req.headers().get("Upgrade")))) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1, BAD_REQUEST));
            return;
        }

        // 构造握手响应返回，本机测试
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws://localhost:8080/websocket", null, false);
        handshaker = wsFactory.newHandshaker(req);


        joinAndAddTime(ctx.channel());

        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
        } else {
            ChannelFuture handshake = handshaker.handshake(ctx.channel(), req);

        }
    }

    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {

        // 判断是否是关闭链路的指令
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());

            /**退出时将会话remove*/
            InetSocketAddress ipSocket = (InetSocketAddress) ctx.channel().remoteAddress();
            String clientIp = ipSocket.getAddress().getHostAddress();
            channels.remove(clientIp);
            return;
        }
        // 判断是否是Ping消息
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        // 本例程仅支持文本消息，不支持二进制消息
        if (!(frame instanceof TextWebSocketFrame)) {
            throw new UnsupportedOperationException(String.format("%s frame types not supported", frame.getClass().getName()));
        }

        // 收到的json 消息
        String reqJson = ((TextWebSocketFrame) frame).text();
        log.info("WS Server Resive Data {}", reqJson);
        try {
            MsgCommand msgCommand = JacksonUtil.toObj(reqJson, MsgCommand.class);
            long msgSerial = msgRetry.snowflake.nextId();
            msgCommand.setMsgSerial(msgSerial);
            msgCommand.setSendTimeStemp(System.currentTimeMillis());

            if (MsgType.ONE_TO_MORE.getType() == msgCommand.getType()) { //群发  //ws2osc
                msgRetry.putRetryMsg(msgCommand);

                List<String> ipL = new ArrayList<String>();
                for (Map.Entry<String, OscEntity> entry : oscs.entrySet()) {
                    OscEntity oscEntity = entry.getValue();
                    if (!Objects.isNull(msgCommand.getDataCommand())) {
                        /**这里需要修改，让osc端能拿到消息序列号 用于消息确认*/
                        Body body = new Body();
                        body.setMsgSerial(msgSerial);
                        body.setDataCommand(msgCommand.getDataCommand());
                        msgRetry.respOscMsg(JacksonUtil.toJson(RespVo.success(body)), oscEntity);
                        ipL.add(oscEntity.getAddress().toString());
                    }
                }
                msgRetry.putIps(msgSerial, ipL);
            } else if (MsgType.ONE_TO_ONE.getType() == msgCommand.getType()) {  //ws2osc
                String targetIp = msgCommand.getTargetIp();
                OscEntity oscEntity = oscs.get(targetIp);
                if (Objects.isNull(oscEntity)) {
                    ctx.channel().write(
                            new TextWebSocketFrame(JacksonUtil.toJson(RespVo.error(Code.ERROR_TARGET, null))));
                }

                Object dataCommand = msgCommand.getDataCommand();
                if (!Objects.isNull(dataCommand)) {
                    /**这里需要修改，让osc端能拿到消息序列号 用于消息确认*/
                    Body body = new Body();
                    body.setMsgSerial(msgSerial);
                    body.setDataCommand(dataCommand);
                    String ack = JacksonUtil.toJson(RespVo.success(body));
                    msgRetry.respOscMsg(ack, oscEntity);
                    msgRetry.putRetryMsg(msgCommand);
                }

            }
            //ackws
            ctx.channel().write(
                    new TextWebSocketFrame(JacksonUtil.toJson(RespVo.success("发送成功", null))));
        } catch (Exception e) {
            ctx.channel().write(
                    new TextWebSocketFrame(JacksonUtil.toJson(RespVo.error(Code.ERROR_FORMATTER, null))));
        }
    }

    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse res) {
        // 返回应答给客户端
        if (res.getStatus().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
//		    HttpHeaderUtil.setContentLength(res, res.content().readableBytes());
        }

        ChannelFuture f = ctx.channel().writeAndFlush(res);

		/*if (!HttpHeaderUtil.isKeepAlive(req) || res.getStatus().code() != 200) {
		    f.addListener(ChannelFutureListener.CLOSE);
		}*/
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


    /**
     * 加入容器 或者 续期
     *
     * @param channel
     */
    private void joinAndAddTime(Channel channel) {
        InetSocketAddress ipSocket = (InetSocketAddress) channel.remoteAddress();
        String clientIp = ipSocket.getAddress().getHostAddress();
        if (Objects.isNull(channels.get(clientIp))) {
            WsEntity wsEntity = new WsEntity();
            wsEntity.setChannel(channel);
            wsEntity.setAddr(clientIp);
            wsEntity.setLastTime(System.currentTimeMillis());
            channels.put(clientIp, wsEntity);
            log.info("客户端ip地址：{}", clientIp);
        } else {
            WsEntity wsEntity = channels.get(clientIp);
            wsEntity.setLastTime(System.currentTimeMillis());
            channels.put(clientIp, wsEntity);
        }


    }
}
