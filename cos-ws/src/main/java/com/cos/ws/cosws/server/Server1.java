package com.cos.ws.cosws.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * description: Server1 <br>
 * date: 2020/1/14 16:25 <br>
 * author: zyc <br>
 * version: 1.0 <br>
 */
public class Server1 {
    public static void main(String[] args)throws Exception {
            DatagramSocket socket = new DatagramSocket(8888);
            String str = "露平  我是clint端";
            DatagramPacket packet =
                    new DatagramPacket(str.getBytes(), str.getBytes().length, InetAddress.getByName("114.242.236.157"),8889);
            socket.send(packet);
            socket.setSoTimeout(3000);
            DatagramPacket re = new DatagramPacket(new byte[1024], 1024);
            socket.receive(re);
            System.out.println("Received: " + new String(re.getData()));

    }
}
