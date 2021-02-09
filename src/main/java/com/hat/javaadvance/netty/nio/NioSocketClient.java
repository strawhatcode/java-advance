package com.hat.javaadvance.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public class NioSocketClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();  // 创建一个SocketChannel
        Selector selector = Selector.open();  // 创建一个Selector
        socketChannel.configureBlocking(false);  // 设置SocketChannel为非阻塞的
        socketChannel.register(selector, SelectionKey.OP_CONNECT);  // SocketChannel注册OP_CONNECT事件（连接服务端触发）
        // 创建一个线程监听控制台输入
        new Thread(() -> {
            try {
                Scanner scanner = new Scanner(System.in);
                // 按行读取控制台输入的数据
                while (scanner.hasNextLine()) {
                    String next = scanner.nextLine();
                    // 没有数据时继续，不发送给服务端
                    if ("".equals(next)){
                        continue;
                    }
                    // 包装控制台输入的数据成ByteBuffer
                    ByteBuffer buffer = ByteBuffer.wrap(next.getBytes());
                    // 唤醒selector.select();中的阻塞的线程，如果使用无参的select()时必须得使用wakeup()唤醒线程再注册事件，
                    // 否则注册的事件不会触发selector的监听，因此还会一直阻塞在select()
                    selector.wakeup();
                    // socketChannel注册OP_WRITE事件，发送数据给客户端
                    socketChannel.register(selector, SelectionKey.OP_WRITE, buffer);
                }
            } catch (ClosedChannelException e) {
                e.printStackTrace();
            }
        }).start();

        // 连接服务端
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8888));

        // 循环执行Selector
        while (true) {
            int events = selector.select();  // 阻塞监听事件的触发，只有触发了事件后才有返回
            if (events > 0) {  // 触发的事件
                // 获取触发的事件的SelectionKey集合的迭代器
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) { // 遍历
                    SelectionKey selectionKey = iterator.next();  // 获取SelectionKey对象
                    if (selectionKey.isReadable()) {  // 如果是OP_READ事件
                        SocketChannel channel = (SocketChannel) selectionKey.channel(); // 获取通道，其实跟上面的socketChannel是同一个对象
                        StringBuilder sb = new StringBuilder(); // 保存服务端发送过来的内容
                        ByteBuffer buffer = ByteBuffer.allocate(1024); // 初始化一个ByteBuffer
                        // 将SocketChannel中读取到的数据写入buffer
                        int read = channel.read(buffer);
                        System.out.print("服务端 " + channel.getRemoteAddress() + " 说：");
                        // 循环读取SocketChannel中的数据
                        // read == -1时表明服务端已经写完，并且取消了对应的SelectionKey或者调用shutdownOutput()关闭写
                        // read == 0时，服务端没有新数据发送过来
                        // read > 0时，服务端又数据发送过来
                        while (read > 0) {
                            buffer.flip(); // 反转attachment字节buffer
                            sb.append(new String(buffer.array(), 0,read));
                            buffer.clear();  // 重置attachment字节buffer
                            read = channel.read(buffer);  // 接着读取SocketChannel中的数据
                        }
                        System.out.print(sb.toString()); //打印客户端发送过来的数据
                        selectionKey.interestOps(0); // 取消当前的事件（OP_READ）

                    } else if (selectionKey.isConnectable()) {  // 触发OP_CONNECT事件，连接服务端时触发
                        while (!socketChannel.finishConnect()) ; // 无限循环判断连接是否成功
                        // 连接成功后注册OP_READ事件
                        socketChannel.register(selector, SelectionKey.OP_READ);

                    } else if (selectionKey.isWritable()) {  // 触发OP_WRITE事件，写数据发送给服务端
                        ByteBuffer attachment = (ByteBuffer) selectionKey.attachment();  // 读取刚才注册写事件时传入的附加对象
                        int write = socketChannel.write(attachment);// 写数据发送给服务端
                        selectionKey.interestOps(SelectionKey.OP_READ); // 注册事件改为OP_READ
                    }
                    iterator.remove();  // 把当前的selectionKey从迭代器删除
                }
            }
        }
    }
}