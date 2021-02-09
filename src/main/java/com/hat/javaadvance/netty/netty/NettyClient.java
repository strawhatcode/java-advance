package com.hat.javaadvance.netty.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

/**
 * Netty实现socket的客户端
 */
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();  // 创建一个EventLoopGroup
        try {
            Bootstrap bootstrap = new Bootstrap(); // 创建一个客户端启动器
            bootstrap.group(group) // 设置创建一个EventLoopGroup，boss与worker都设置为group
                    .channel(NioSocketChannel.class)  // 使用的channel
                    .option(ChannelOption.SO_KEEPALIVE,true)  // 设置group为长连接
                    .handler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) { // 设置ChannelHandler
                            // 只添加了一个入站Handler，这个入站是站在客户端角度的
                            ch.pipeline().addLast(new ClientInHandler());
                        }
                    });
            // 异步连接一个socket服务器
            ChannelFuture future = bootstrap.connect("127.0.0.1", 8888);
            // 设置一个监听器，监听channel连接的状态
            future.addListener(future1 -> {
                        if (future1.isSuccess()) {
                            System.out.println("连接服务端成功");
                            future.channel().writeAndFlush(Unpooled.copiedBuffer("我是客户端", CharsetUtil.UTF_8));
                        }
                        if (future1.isDone()) {
                            System.out.println("客户端已完成");
                        }
                        if (future1.isCancellable()) {
                            System.out.println("已取消");
                        }
                        if (future1.isCancelled()) {
                            System.out.println("未完成操作被取消");
                        }
                    });
            // 同步监听关闭channel
            future.channel().closeFuture().sync();
        }finally {
            // 优雅的关闭EventLoopGroup
            group.shutdownGracefully();
        }
    }
}
