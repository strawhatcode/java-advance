package com.hat.javaadvance.netty.bio;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SocketClient {
    public static void main(String[] args) {
        socketClient2();
    }

    public static void socketClient1() {
        try {
            Socket socket = new Socket("127.0.0.1", 8888); // 创建一个socket客户端并连接8888端口
            System.out.println("连接socket服务器成功。。。。");
            // 创建一个数据流来发送数据报
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            // 读取控制台输入的数据
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
            while (true) {
                String data = br.readLine(); // 读取一行数据
                bw.write(data);  // 发送数据报内容
                bw.write("\n");   // 发送数据报结束符，告诉服务器已经发送完了
                bw.flush();   // 刷新缓冲区，将数据发送给服务器
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void socketClient2() {
        try {
            Socket socket = new Socket("127.0.0.1", 8888); // 创建一个socket客户端并连接8888端口
            System.out.println("连接socket服务器成功。。。。");
            // 创建一个数据流来发送数据报
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            // 开启一个线程来接受服务端返回来的数据报
            new Thread(() -> {
                while (true){
                    try {
                        int len = dis.readInt();  // 读取服务端响应的长度
                        byte[] b = new byte[len];
                        dis.read(b);   // 读取服务端响应的数据
                        System.out.println("服务端响应："+new String(b));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }).start();

            // 读取控制台输入的数据
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
            // 无限循环等待获取控制台输入的数据(一行发送一次)
            while (true) {
                String data = br.readLine();   // 读取控制台输入的一行数据
                byte[] bytes = data.getBytes();
                dos.writeInt(bytes.length+5);  // 发送数据报长度
                dos.write(bytes);   // 发送数据报内容
                dos.flush();   // 刷新缓冲区，将数据发送给服务器
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
