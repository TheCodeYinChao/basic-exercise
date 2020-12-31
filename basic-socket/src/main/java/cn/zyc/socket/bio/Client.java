package cn.zyc.socket.bio;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * dsc: Clint
 * date: 2020/12/30 11:35
 * author: zyc
 */
public class Client {
    private SocketAddress serverAddress;
    private Socket socket;

    public Client(SocketAddress serverAddress) {
        this.serverAddress = serverAddress;
        connet();
    }

    public void connet() {
        OutputStream os = null;
        try {
            socket = new Socket();
            socket.connect(serverAddress);
            os = socket.getOutputStream();
            byte[] b = new byte[1024];
//            System.in.read(b);
            ThreadLocalRandom current = ThreadLocalRandom.current();
            Long v = current.nextLong();
            os.write(("颤抖吧"+v.toString()).getBytes());//发送数据

            InputStream is = socket.getInputStream(); //在接收 服务端返回的响应信息会阻塞
            byte[] r = new byte[1024];
            is.read(r);
            System.out.println(new String(r));

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket != null){
                if (!socket.isClosed()) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        //模拟一个多client的压测
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                String host = "127.0.0.1";
                int port = 81;
                new Client(new InetSocketAddress(host,port));
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        countDownLatch.countDown();
    }
}
