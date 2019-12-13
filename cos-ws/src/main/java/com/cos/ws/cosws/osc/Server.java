package com.cos.ws.cosws.osc;

import com.cos.ws.cosws.utils.JacksonUtil;
import com.illposed.osc.OSCBundle;
import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPortOut;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws Exception{
        test();
//        test1();
    }


    public static void test1()throws Exception{
        /*------------------------------设置要发送的目标主机的IP地址------------------------------------*/
        InetAddress ipAddr = InetAddress.getByName("127.0.0.1");
        /*------------------------------设置要发送的目标主机的端口------------------------------------*/
        Integer port = 8764;
        /*------------------------------准备个快递员------------------------------------*/
        OSCPortOut out = new OSCPortOut(ipAddr, port);
        /*------------------------------准备要送的物件------------------------------------*/
        //来个箱子，准备装东西
        OSCMessage message = new OSCMessage();
        //给箱子上贴个快递单，告诉快递员要送到业主家的哪个位置
        message.setAddress("/");
        //给箱子里装东西，先放十块钱进去,再放一个蛋糕进去
//        message.addArgument(10);


        MsgCommand msgCommand = new MsgCommand();
        msgCommand.setReqIp("/127.0.0.1");
        msgCommand.setTargetIp("127.0.0.1");
        msgCommand.setType(0);
        msgCommand.setDataCommand("我是osc 发送端");
        JacksonUtil.toJson(msgCommand);
        byte[] bytes = JacksonUtil.toJson(msgCommand).getBytes();
        message.addArgument(JacksonUtil.toJson(msgCommand));
        //给箱子套上一个保护袋
        OSCBundle pack = new OSCBundle();
        pack.addPacket(message);
        /*--------------------------------快递员送件----------------------------------*/
        //把打包好的东西送走
        out.send(pack);
    }




    public static void test()throws Exception{
        /*------------------------------设置要发送的目标主机的IP地址------------------------------------*/
        InetAddress ipAddr = InetAddress.getByName("10.10.10.118");
        /*------------------------------设置要发送的目标主机的端口------------------------------------*/
        Integer port = 8764;
        /*------------------------------准备个快递员------------------------------------*/
        OSCPortOut out = new OSCPortOut(ipAddr, port);
        /*------------------------------准备要送的物件------------------------------------*/
        //来个箱子，准备装东西
        OSCMessage message = new OSCMessage();
        //给箱子上贴个快递单，告诉快递员要送到业主家的哪个位置
        message.setAddress("/");
        //给箱子里装东西，先放十块钱进去,再放一个蛋糕进去
//        message.addArgument(10);


        MsgCommand msgCommand = new MsgCommand();
        msgCommand.setReqIp("/10.10.10.167");
        msgCommand.setTargetIp("10.10.10.167");
        msgCommand.setType(2);
        msgCommand.setDataCommand("我是osc 发送端");
        JacksonUtil.toJson(msgCommand);
        byte[] bytes = JacksonUtil.toJson(msgCommand).getBytes();
        message.addArgument(JacksonUtil.toJson(msgCommand));
        //给箱子套上一个保护袋
        OSCBundle pack = new OSCBundle();
        pack.addPacket(message);
        /*--------------------------------快递员送件----------------------------------*/
        //把打包好的东西送走
        out.send(pack);
    }
}
