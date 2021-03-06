package com.cos.ws.cosws.osc;

import com.illposed.osc.AddressSelector;
import com.illposed.osc.OSCListener;
import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPacket;
import com.illposed.osc.utility.OSCByteArrayToJavaConverter;
import com.illposed.osc.utility.OSCPacketDispatcher;
import com.illposed.osc.utility.OSCPatternAddressSelector;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Recevice {
    public static void main(String[] args) throws Exception {
        //接收客户端发送的数据   
        DatagramSocket socket = new DatagramSocket(57367);
        // 1.创建服务器端DatagramSocket，指定端口
        // 2.创建数据报，用于接收客户端发送的数据
        byte[] data = new byte[1024];//创建字节数组，指定接收的数据包的大小
        DatagramPacket packet = new DatagramPacket(data, data.length);
        // 3.接收客户端发送的数据
        System.err.println("Server：");//输出服务器的标识
        while (true) {//通过循环不停的向客户端发送数据和接收数据
            socket.receive(packet);// 此方法在接收到数据报之前会一直阻塞

            /**转换获取到的数据 */
            OSCByteArrayToJavaConverter oscByteArrayToJavaConverter = new OSCByteArrayToJavaConverter();
            OSCPacket oscPacket = oscByteArrayToJavaConverter.convert(data, packet.getLength());
            OSCPacketDispatcher dispatcher = new OSCPacketDispatcher();
            dispatcher.addListener((AddressSelector) (new OSCPatternAddressSelector("/lzx")), new OSCListener() {
                @Override
                public void acceptMessage(Date date, OSCMessage oscMessage) {
                    List<Object> arguments = oscMessage.getArguments();
                    System.out.println(arguments);
                }
            });
            dispatcher.dispatchPacket(oscPacket);

            // 4.读取数据 
            String info = new String(data, 0, packet.getLength());//创建字符串对象
            System.out.println("服务器接收到客户端的信息是：" + info);//输出提示信息
            //向客户端响应数据
            // 1.定义客户端的地址、端口号、数据
            /* System.out.println("回复客户端的消息：");
             InetAddress address = packet.getAddress();
            //获取发送端的地址
             int port = packet.getPort();
            //获取 发送端进程所绑定的端口  
            Scanner scanner = new Scanner(System.in);
             //从键盘接受数据  
             String send = scanner.nextLine();
            //nextLine方式接受字符串  
            byte[] data2 = send.getBytes();
            //将接收到的数据转换为字节数组
             DatagramPacket packet2 = new DatagramPacket(data2, data2.length,address,port);
            // 2.创建数据报，包含响应的数据信息      
            socket.send(packet2);
            // 3.响应客户端  */
        }
    }
}
