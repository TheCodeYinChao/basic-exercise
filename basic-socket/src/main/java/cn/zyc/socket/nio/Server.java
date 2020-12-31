package cn.zyc.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.AbstractSelector;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;

/**
 * dsc: Sever
 * date: 2020/12/30 14:37
 * author: zyc
 */
public class Server {

    private Selector selector;

    private ByteBuffer readBuf = ByteBuffer.allocate(1024);

    private ServerSocketChannel serverSocketChannel;


    public Server(InetSocketAddress socketAddress) throws Exception {
        assert socketAddress != null;
/*

        SelectorProvider provider = SelectorProvider.provider();
        provider.openSelector()
*/

        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(socketAddress);
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
        connet();
    }

    public void connet()throws Exception{
        while (true) {
            this.selector.select(); //开启多路复用器轮训

            Iterator<SelectionKey> s = selector.selectedKeys().iterator();//有事件产生的selection
            while (s.hasNext()) {
                SelectionKey next = s.next();
                /**
                 *  //这里有个弊端使用过的不需要在留在这里面啦 应该用迭代器删除
                 * 这里用完必须清楚否则还会被轮训出来
                 */
                s.remove();
                if (next.isValid()) {
                    if (next.isAcceptable()) {//接收事件
                        accept(next);
                    }
                    if(next.isReadable()){//读取时间
                        read(next);
                    }
                }
            }
        }
    }

    private void read(SelectionKey key) {

        try {
            //1 清空缓冲区旧的数据
            this.readBuf.clear();
            //2 获取之前注册的socket通道对象
            SocketChannel sc = (SocketChannel) key.channel();
            //3 读取数据
            int count = sc.read(this.readBuf);
            //4 如果没有数据
            if(count == -1){
                key.channel().close();
                key.cancel();
                return;
            }
            //5 有数据则进行读取 读取之前需要进行复位方法(把position 和limit进行复位)
            this.readBuf.flip();
            //6 根据缓冲区的数据长度创建相应大小的byte数组，接收缓冲区的数据
            byte[] bytes = new byte[this.readBuf.remaining()];
            //7 接收缓冲区数据
            this.readBuf.get(bytes);
            //8 打印结果
            String body = new String(bytes).trim();
            System.out.println("Server : " + body);

            // 9..可以写回给客户端数据
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void accept(SelectionKey key) throws Exception{
        ServerSocketChannel channel = (ServerSocketChannel)key.channel();
        SocketChannel accept = channel.accept();
        accept.configureBlocking(false);
        accept.register(selector,SelectionKey.OP_READ);

    }

    public static void main(String[] args)throws Exception {
        String host = "127.0.0.1";
        int port = 81;
        new Server(new InetSocketAddress(host,port));
    }

}
