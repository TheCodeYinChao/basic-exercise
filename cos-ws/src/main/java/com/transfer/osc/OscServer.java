package com.transfer.osc;

import com.illposed.osc.*;
import com.illposed.osc.utility.OSCByteArrayToJavaConverter;
import com.illposed.osc.utility.OSCPacketDispatcher;
import com.illposed.osc.utility.OSCPatternAddressSelector;
import com.transfer.model.MsgCommand;
import com.transfer.model.OscEntity;
import com.transfer.model.WsEntity;
import com.transfer.resp.Code;
import com.transfer.resp.MsgType;
import com.transfer.resp.RespVo;
import com.transfer.utils.JacksonUtil;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class OscServer  implements OSCListener{
    private ConcurrentHashMap<String,WsEntity> target; //ws clients
    private DatagramSocket socket;
    private DatagramPacket packet;
    private MsgRetry msgRetry;

    public OscServer(ConcurrentHashMap<String,OscEntity> oscEntitys, ConcurrentHashMap<String,WsEntity> target,MsgRetry msgRetry) {
        this.target = target;
        this.msgRetry = msgRetry;
        msgRetry.oscEntitys = oscEntitys;
    }

    public  void run(int port) throws Exception{

        socket = new DatagramSocket(port);
        while(true) {
            byte[] data = new byte[65507];//创建字节数组，指定接收的数据包的大小
            packet = new DatagramPacket(data, data.length);
            socket.receive(packet);
            log.info("新数据接收 ing！！！");

            /**转换获取到的数据 */
            OSCByteArrayToJavaConverter oscByteArrayToJavaConverter = new OSCByteArrayToJavaConverter();
            OSCPacket oscPacket = oscByteArrayToJavaConverter.convert(data, packet.getLength());
            OSCPacketDispatcher  dispatcher = new OSCPacketDispatcher();
            dispatcher.addListener((AddressSelector)(new OSCPatternAddressSelector("/lzx")),this);
            dispatcher.dispatchPacket(oscPacket);

        }
    }

    @Override
    public void acceptMessage(Date date, OSCMessage oscMessage) {
        try { /**异常不能抛出去，否则异常接收服务会中断*/

            List<Object> arguments = oscMessage.getArguments();
            log.info("接收参数: {}",JacksonUtil.toJson(arguments));
            Object o = oscMessage.getArguments().get(0);
            if(Objects.isNull(o)){
                OscEntity oscEntity = joinAndAddTime(packet,packet.getPort());
                String s = JacksonUtil.toJson(RespVo.error(Code.ERROR_MSGISNULL, null));
                msgRetry.respOscMsg(s, oscEntity);
                return;
            }
            String reqJson = o.toString();
            log.info("OSC Server Recevice Data {}",reqJson);

            /**test ios
            reqJson=UUID.randomUUID().toString();
            int port = packet.getPort();
            try {
                packet.setAddress(InetAddress.getByName("192.168.1.159"));
                packet.setPort(8090);
            } catch (Exception e) {
                e.printStackTrace();
            }
            OscEntity oscEntity1 = joinAndAddTime(packet,packet.getPort());
            msgRetry.respOscMsg(reqJson, oscEntity1);
            if(true)
            return;
             test*/

            MsgCommand msgCommand = JacksonUtil.toObj(reqJson, MsgCommand.class);
            if(closeConnect(msgCommand)){ //是否下线
                String s = JacksonUtil.toJson(RespVo.success("下线成功", null));
                OscEntity oscEntity = joinAndAddTime(packet,msgCommand.getOscListenerPort());
                msgRetry.respOscMsg(s, oscEntity);
                return;
            }
            if(connect(msgCommand)){ //是否是建立连接
                String s = JacksonUtil.toJson(RespVo.error(Code.SUCCESS, null));
                OscEntity oscEntity = joinAndAddTime(packet,msgCommand.getOscListenerPort());
                msgRetry.respOscMsg(s, oscEntity);
                return;
            }

            if(msgConfirm(msgCommand,packet)){ //消息确认
                return;
            }

            if(MsgType.ONE_TO_ONE.getType()== msgCommand.getType()){ //osc2ws
                String targetIp = msgCommand.getTargetIp();
                WsEntity wsEntity = target.get(targetIp);
                if(Objects.isNull(wsEntity)){
                    RespVo resp = RespVo.error(Code.ERROR_TARGET, null);
                    OscEntity oscEntity = msgRetry.oscEntitys.get(msgCommand.getReqIp());
                    msgRetry.respOscMsg(JacksonUtil.toJson(resp),oscEntity);
                }
                wsEntity.getChannel().writeAndFlush(
                        new TextWebSocketFrame(JacksonUtil.toJson(RespVo.success(
                                Objects.isNull(msgCommand.getDataCommand()) ? "" : msgCommand.getDataCommand())))
                );
            }else if(MsgType.ONE_TO_MORE.getType()== msgCommand.getType()){ //osc2ws
                /**
                 * 群发
                 */
                for(Map.Entry <String,WsEntity>entry: target.entrySet()){
                    entry.getValue().getChannel().writeAndFlush(
                            new TextWebSocketFrame(JacksonUtil.toJson(RespVo.success(
                                    Objects.isNull(msgCommand.getDataCommand()) ? "" : msgCommand.getDataCommand())
                            ))
                    );
                }
            }
            // osc ack
            osc2wsAck(msgCommand);
        } catch (Exception e) {
            String s = JacksonUtil.toJson(RespVo.error(Code.ERROR_SENDMSG, null));
            OscEntity oscEntity = joinAndAddTime(packet,packet.getPort());
            msgRetry.respOscMsg(s, oscEntity);
        }
    }

    /**
     * osc 发送 ws 消息确认
     * @param msgCommand
     */
    private void osc2wsAck(MsgCommand msgCommand) {
        OscEntity oscEntity = joinAndAddTime(packet,msgCommand.getOscListenerPort());
        msgRetry.respOscMsg(JacksonUtil.toJson(RespVo.success("发送成功", null)),oscEntity);
    }


    /**
     * 消息确认
     * @param msgCommand
     * @return
     */
    private boolean msgConfirm(MsgCommand msgCommand,DatagramPacket packet) {
        boolean isMsgConfirm = false;
        if(MsgType.MSG_CONFIRM.getType() == msgCommand.getType()){
            MsgCommand msgOs = msgRetry.retryMsg.get(msgCommand.getMsgSerial());
            if(MsgType.ONE_TO_MORE.getType() == msgOs.getType()){ //群发确认
                String oscIp = packet.getAddress().toString();
                msgRetry.removeIps(msgCommand.getMsgSerial(),oscIp);
            }else {
                msgRetry.deleteConfirmed(msgCommand.getMsgSerial()); //单发确认成功 移除备份消息
            }
            isMsgConfirm = true;
        }
        return isMsgConfirm;
    }

    /**
     * 创建连接
     * @param msgCommand
     * @return
     */
    private boolean connect(MsgCommand msgCommand) {
        if(MsgType.CONNECT.getType() == msgCommand.getType()){
            return true;
        }
        return  false;
    }

    /**
     * osc主动下线
     * @param msgCommand
     * @return
     */
    private boolean closeConnect(MsgCommand msgCommand){
        boolean isSuccess= false;
        if(msgCommand.getType()==MsgType.CLOSE.getType()){
            msgRetry.oscEntitys.remove(msgCommand.getReqIp());
            isSuccess = true;

        }
        return isSuccess;
    }


    /**
     * 加入容器或者续期
     * @param packet
     */
    private OscEntity joinAndAddTime(DatagramPacket packet,int port){
        String address = packet.getAddress().toString();

//        if(Objects.isNull(local.get(address))){
            /**获取发送者信息 每次 都更新*/
            OscEntity oscEntity = new OscEntity();
            oscEntity.setAddress(packet.getAddress());
            oscEntity.setPort(port);
            oscEntity.setLastTime(System.currentTimeMillis());
            log.info("OSC Sender Info {}",JacksonUtil.toJson(oscEntity));
            msgRetry.oscEntitys.put(address,oscEntity);
            return oscEntity;
//        }else{
//            OscEntity oscEntity = local.get(address);
//            oscEntity.setLastTime(System.currentTimeMillis());
//            local.put(address,oscEntity);
//            return  oscEntity;
//        }

    }


    /**
     * 响应信息
     * @param respMsg
     * @param oscEntity
     */
    public void send(String respMsg,OscEntity oscEntity){
//        DatagramPacket packet2 = new DatagramPacket(respMsg.getBytes(), respMsg.getBytes().length,oscEntity.getAddress(),oscEntity.getPort());
        try {
        /*   OSCMessage message = new OSCMessage();
            message.setAddress("/lzx");
            message.addArgument(respMsg);
            OSCBundle pack = new OSCBundle();
            pack.addPacket(message);
            byte[] byteArray = pack.getByteArray();
            DatagramPacket packet = new DatagramPacket(byteArray, byteArray.length,oscEntity.getAddress(),oscEntity.getPort());
            this.socket.send(packet);*/
//          socket.send(packet2);

            MsgCommand msgCommand = new MsgCommand();
            msgCommand.setReqIp("/10.10.10.167");
            msgCommand.setTargetIp("10.10.10.167");
            msgCommand.setType(2);
            msgCommand.setDataCommand("我是osc 发送端");
            sendDemo(msgCommand, oscEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendDemo(MsgCommand msgCommand,OscEntity oscEntity)throws Exception{
        OSCPortOut out = new OSCPortOut(oscEntity.getAddress(), oscEntity.getPort());
        OSCMessage message = new OSCMessage();
        message.setAddress("/zlx");
        msgCommand.setReqIp("/10.10.10.167");
        msgCommand.setTargetIp("10.10.10.167");
        msgCommand.setType(2);
        msgCommand.setDataCommand("我是osc 发送端");
        message.addArgument(JacksonUtil.toJson(msgCommand));
        OSCBundle pack = new OSCBundle();
        pack.addPacket(message);
        out.send(pack);
    }

}
