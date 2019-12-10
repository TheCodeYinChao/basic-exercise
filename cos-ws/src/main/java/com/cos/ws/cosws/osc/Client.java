package com.cos.ws.cosws.osc;

import com.illposed.osc.OSCBundle;
import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPortOut;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

    public static void main(String[] args) throws Exception{
        /*------------------------------设置要发送的目标主机的IP地址------------------------------------*/
        InetAddress ipAddr = InetAddress.getByName("224.0.0.88");
    /*    byte[] data = new byte[1024];
        DatagramSocket socket = new DatagramSocket(10000);
        DatagramPacket datagramPacket = new DatagramPacket(data, data.length, InetAddress.getByName("255.255.255.255"), 10000);

        socket.send(datagramPacket);*/

        /*------------------------------设置要发送的目标主机的端口------------------------------------*/
        Integer port = 7000;
        /*------------------------------准备个快递员------------------------------------*/
        OSCPortOut out = new OSCPortOut(ipAddr, port);
        /*------------------------------准备要送的物件------------------------------------*/
        //来个箱子，准备装东西
        OSCMessage message = new OSCMessage();
        //给箱子上贴个快递单，告诉快递员要送到业主家的哪个位置
        message.setAddress("/test2");
        //给箱子里装东西，先放十块钱进去,再放一个蛋糕进去
        message.addArgument(10);
        message.addArgument("蛋糕");
        //给箱子套上一个保护袋
        OSCBundle pack = new OSCBundle();
        pack.addPacket(message);
        /*--------------------------------快递员送件----------------------------------*/
        //把打包好的东西送走
        out.send(pack);
    }
}
