package cn.zyc.socket.bio;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.*;

/**
 * dsc: Server
 * date: 2020/12/30 11:36
 * author: zyc
 */
public class Server implements Runnable{
    private static SocketAddress serverAddress = null;
    private static ServerSocket serverSocket = null;
    private static final ExecutorService executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
            60L, TimeUnit.SECONDS,
            new ArrayBlockingQueue(100));//线程池数量代表服务端的承载量

    private Socket socket;
    public Server(SocketAddress serverAddress) {
        this.serverAddress = serverAddress;
        connet();
    }

    public Server(Socket socket) {
        this.socket = socket;
    }

    public void connet() {
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(serverAddress);
            while (true){
                Socket socket = serverSocket.accept(); //无连接阻塞
                executorService.execute(new Server(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() { //异步 处理业务
        OutputStream inputStream = null;
        try {
            InputStream is = socket.getInputStream();
            byte[] b = new byte[1024];
            is.read(b);//发送数据
            System.out.println(new String(b));
            System.out.println("业务处理中 ....");
            TimeUnit.SECONDS.sleep(2);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("我收到啦！小子".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
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
        String host = "127.0.0.1";
        int port = 81;
        new Server(new InetSocketAddress(host,port));
    }
}
