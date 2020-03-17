package com.cos.ws.cosws.server2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * description: Server1 <br>
 * date: 2020/1/14 16:25 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class Server2 {
    public static void main(String[] args)throws Exception {

        DatagramSocket socket = new DatagramSocket(8889);
        String str = "我是服务器消息";
//        socket.setSoTimeout(3000);
        while(true) {//通过循环不停的向客户端发送数据和接收数据
            DatagramPacket re = new DatagramPacket(new byte[1024], 1024);
            socket.receive(re);
            System.out.println("Received: " + new String(re.getData()));
            DatagramPacket packet =
                    new DatagramPacket(str.getBytes(), str.getBytes().length, re.getAddress(),re.getPort());
            socket.send(packet);

        }
    }
}
