package com.hat.javaadvance.netty.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SocketServer {
    private static final int port = 8888;
    public static void main(String[] args) {
//        socketServer1();
        socketServer2();
    }

    public static void socketServer1(){
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(port); // 创建一个socket服务器，并绑定8888端口
            System.out.println("启动socket服务端成功，绑定的端口号："+port);
            System.out.println("正在等待客户端的链接。。。。。");
            Socket accept = socket.accept();  // 等待socket客户端连接（阻塞等待）
            System.out.println("接收到客户端 "+accept.getInetAddress() +" 的连接");
            // 创建缓冲流来读取socket客户端发送过来的数据
            BufferedReader br = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            String data = br.readLine();
            while (data != null){  // 无限循环读取socket客户端发送过来消息
                System.out.println("接收到客户端的内容："+data);
                data = br.readLine();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 多线程实现允许多个客户端连接服务端
    public static void socketServer2(){
        // 创建一个线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(8,16,60,
                TimeUnit.SECONDS,new ArrayBlockingQueue<>(8), new ThreadPoolExecutor.AbortPolicy());

        try {
            ServerSocket socket = new ServerSocket(port); // 创建一个socket服务器，并绑定8888端口
            System.out.println("启动socket服务端成功，绑定的端口号："+port);
            System.out.println("正在等待客户端的链接。。。。。");
            while (true){  // 无限循环等待socket客户端发送过来消息
                Socket accept = socket.accept();  // 等待socket客户端连接（阻塞等待）
                System.out.println("接收到客户端 "+accept.getRemoteSocketAddress() +" 的连接");
                // 从线程池中获取一个线程资源
                executor.execute(() -> {
                    DataInputStream dis = null; // 创建数据流来读取socket客户端发送过来的数据
                    DataOutputStream dos = null; // 创建数据流来发送响应到客户端
                    try {
                        dis = new DataInputStream(accept.getInputStream());
                        dos = new DataOutputStream(accept.getOutputStream());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    // 无限循环等待客户端发送过来的数据报
                    while (true){
                        try {
                            int len = dis.readInt(); // 读取客户端中数据报的长度
                            byte[] data = new byte[len];
                            dis.read(data);     // 将数据报内容读取到data中
                            System.out.println("数据长度："+len);
                            System.out.println("数据内容："+new String(data));
                            byte[] res = "已收到".getBytes();

                            // 响应给客户端的数据报
                            dos.writeInt(res.length+5); // 响应数据的长度
                            dos.write(res); // 响应数据写到输出流
                            dos.flush();   // 响应数据到客户端
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                            try {
                                accept.close();  // 关闭socket连接
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                    }

                });
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
