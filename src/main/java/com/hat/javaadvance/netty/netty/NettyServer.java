package com.hat.javaadvance.netty.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.AttributeKey;

import java.time.LocalTime;

/**
 * Netty实现Socket服务端
 */
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        // bossGroup，一个线程池监听channel的accpet事件,这里指定了线程的数量，默认是cpu逻辑处理器的个数*2
        EventLoopGroup boss = new NioEventLoopGroup(2);
        // workerGroup，这个线程池完成channel的各种交互事件
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap(); // 创建一个服务端启动器
            serverBootstrap.group(boss,worker)   // 设置两个EventLoopGroup
                    .channel(NioServerSocketChannel.class) // 使用的channel，根据具体协议而定，这里是socket协议
                    .option(ChannelOption.SO_BACKLOG,1000) // 设置bossGroup的最大连接数，windows默认200，linux默认128
                    .childOption(ChannelOption.SO_KEEPALIVE,true)  // 设置workerGroup为保持连接
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) {  // 设置ChannelHandler
                            // 添加4个Handler
                            ch.pipeline().addLast(new ServerInHandler());
                            ch.pipeline().addLast(new ServerInHandler2());
                            ch.pipeline().addLast(new ServerOutHandler());
                            ch.pipeline().addLast(new ServerOutHandler2());
                        }
                    }).childAttr(AttributeKey.valueOf("cccName"), LocalTime.now().toString());
            // 同步监听绑定8888端口，阻塞到绑定成功
            ChannelFuture future = serverBootstrap.bind(8888).sync();
            System.out.println("服务器启动成功~~");
            // 同步监听关闭channel
            future.channel().closeFuture().sync();
        }finally {
            // 优雅关闭两个EventLoopGroup
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }

    }
}
