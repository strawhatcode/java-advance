package com.hat.javaadvance.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class NioSocketServer {
    private static Map<String,String> data = new HashMap<>();
    public static void main(String[] args) throws IOException {
        // 打开一个socket服务端通道channel
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        // 打开一个选择器selector
        Selector selector = Selector.open();
        // 绑定8888端口
        socketChannel = socketChannel.bind(new InetSocketAddress(8888));
        // 设置socket为非阻塞的
        socketChannel.configureBlocking(false);
        // 将socket服务端注册到选择器selector中，并且注册事件是OP_ACCEPT,这里注册的是ServerSocketChannel
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            // 监听是否有selectKey，每1秒返回一次
            int events = selector.select();
            if (events > 0){
                // 如果有捕获到事件selectedKeys一定存在SelectionKey，
                // 获取SelectionKey集合的迭代器
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                // 遍历迭代器
                while (iterator.hasNext()){
                    // 遍历出的SelectionKey对象
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isAcceptable()){ // 如果事件是OP_ACCEPT，捕获到客户端的连接事件
                        // 通过SelectionKey对象获取到触发OP_ACCEPT事件的channel
                        ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                        // 因为已经触发了OP_ACCEPT事件，所以socket的accept()方法一定立刻返回，所以不存在阻塞问题
                        // 获取连接到socket服务端的socket客户端的SocketChannel
                        SocketChannel accept = channel.accept();
                        accept.configureBlocking(false);
                        System.out.println("来自客户端 "+ accept.getRemoteAddress()+" 的连接~~");

                        // 再注册OP_READ事件到selector中，将OP_READ事件的处理交给其他方法处理
                        // 第3个参数是SelectionKey的一个附带属性对象，等SelectionKey的channel读事件被捕获到时可以获取这个对象来进行数据操作
                        // 这里注册的是SocketChannel
                        accept.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));

                    }else if (selectionKey.isReadable()){ // 如果事件是OP_READ，捕获到读取客户端的发送过来的数据的事件
                        // 通过SelectionKey获取到触发OP_READ的channel（这里的channel是SocketChannel而不是ServerSocketChannel）
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        // 获取SelectionKey设置的附带对象attachment，就是在注册OP_READ事件时的第3个参数（ByteBuffer）
                        ByteBuffer attachment = (ByteBuffer) selectionKey.attachment();
                        StringBuilder sb = new StringBuilder(); // 保存客户端发送过来的内容
                        // 将SocketChannel中读取到的数据写入attachment
                        int read = channel.read(attachment);
                        System.out.print("客户端 " + channel.getRemoteAddress() + " 说：");
                        // 循环读取SocketChannel中的数据
                        // read == -1时表明客户端已经写完，并且取消了对应的SelectionKey或者调用shutdownOutput()关闭写
                        // read == 0时，客户端没有新数据发送过来
                        // read > 0时，客户端又数据发送过来
                        while (read > 0){
                            attachment.flip(); // 反转attachment字节buffer
                            sb.append(new String(attachment.array(),0,read));
                            attachment.clear();  // 重置attachment字节buffer
                            read = channel.read(attachment);  // 接着读取SocketChannel中的数据
                        }
                        System.out.println(sb.toString()); //打印客户端发送过来的数据
                        NioSocketServer.data.put(channel.getRemoteAddress().toString(),sb.toString());
                        // 将该channel注册到selector中的事件改成OP_WRITE
                        // 相当于channel.register(selector,SelectionKey.OP_WRITE,attachment);
                        selectionKey.interestOps(SelectionKey.OP_WRITE);

                    }else if (selectionKey.isWritable()){ // 如果事件是OP_WRITE，触发服务端的写事件，写入内容返回给客户端
                        // 获取SocketChannel
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        String msg = "我已收到您的消息："+data.get(channel.getRemoteAddress().toString());
                        channel.write(ByteBuffer.wrap(msg.getBytes())); // 组装响应信息并写入channel
                        selectionKey.interestOps(SelectionKey.OP_READ); // 将注册的事件更改为OP_READ
                    }
                    iterator.remove(); // 操作完后把捕获到事件的SelectionKey移除掉
                }
            }
        }
    }
}
