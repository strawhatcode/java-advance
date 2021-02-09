package com.hat.javaadvance.netty.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyHttpServer {
    public static void main(String[] args) {
        // 监听连接的线程池
        EventLoopGroup boss = new NioEventLoopGroup(1);
        // 监听读写的线程池
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            // 服务端启动器
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 设置启动器的参数
            bootstrap.group(boss,worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new HttpServerChannelHandler());
            // 同步启动服务端
            ChannelFuture future = bootstrap.bind(8888).sync();
            // 同步关闭服务端
            future.channel().closeFuture().sync();
        }catch (Exception e){
            System.out.println("发生异常");
        }finally {
            // 关闭两个EventLoopGroup
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
