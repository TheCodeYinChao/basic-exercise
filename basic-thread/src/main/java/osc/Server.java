package osc;

import com.illposed.osc.OSCBundle;
import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPortOut;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws Exception{
         //接收客户端发送的数据   
         DatagramSocket socket = new DatagramSocket(7000);
         // 1.创建服务器端DatagramSocket，指定端口
         // 2.创建数据报，用于接收客户端发送的数据
         byte[] data = new byte[1024];//创建字节数组，指定接收的数据包的大小
         DatagramPacket packet = new DatagramPacket(data, data.length);
         // 3.接收客户端发送的数据
         System.err.println("Server：");//输出服务器的标识
         while(true) {//通过循环不停的向客户端发送数据和接收数据
             socket.receive(packet);// 此方法在接收到数据报之前会一直阻塞
            // 4.读取数据 
             String info = new String(data, 0, packet.getLength());//创建字符串对象
            System.out.println("服务器接收到客户端的信息是：" + info);//输出提示信息
             //向客户端响应数据
             // 1.定义客户端的地址、端口号、数据
             System.out.println("回复客户端的消息：");
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
            // 3.响应客户端  
        }
    }

    public void test()throws Exception{
        /*------------------------------设置要发送的目标主机的IP地址------------------------------------*/
        InetAddress ipAddr = InetAddress.getByName("127.0.0.1");
        /*------------------------------设置要发送的目标主机的端口------------------------------------*/
        Integer port = 7000;
        /*------------------------------准备个快递员------------------------------------*/
        OSCPortOut out = new OSCPortOut(ipAddr, port);
        /*------------------------------准备要送的物件------------------------------------*/
        //来个箱子，准备装东西
        OSCMessage message = new OSCMessage();
        //给箱子上贴个快递单，告诉快递员要送到业主家的哪个位置
        message.setAddress("/test");
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
