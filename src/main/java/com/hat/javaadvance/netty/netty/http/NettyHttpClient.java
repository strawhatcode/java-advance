package com.hat.javaadvance.netty.netty.http;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyHttpClient {
    public static void main(String[] args) {
        EventLoopGroup worker = new NioEventLoopGroup(); // 创建一个线程池
        try {
            Bootstrap bootstrap = new Bootstrap(); // 客户端启动器
            // 设置启动器
            bootstrap.group(worker)
                    .channel(NioSocketChannel.class)
                    .handler(new MyClientChannelHandler());
            // 同步连接服务器
            ChannelFuture future = bootstrap.connect("127.0.0.1", 8888).sync();
            // 同步监听关闭连接
            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            System.out.println("发生异常");
        } finally {
            worker.shutdownGracefully();
        }
    }
}
