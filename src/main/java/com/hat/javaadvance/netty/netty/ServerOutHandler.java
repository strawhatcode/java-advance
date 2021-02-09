package com.hat.javaadvance.netty.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.nio.charset.StandardCharsets;

public class ServerOutHandler extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ByteBuf data = (ByteBuf) msg;
        String s = "ServerOutHandler《=====" + data.toString(StandardCharsets.UTF_8);
        System.out.println(s);
        ctx.writeAndFlush(Unpooled.copiedBuffer(s,StandardCharsets.UTF_8));
    }

//    @Override
//    public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
//        super.bind(ctx, localAddress, promise);
//        System.out.println("进入bind");
//    }
//
//    @Override
//    public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
//        super.connect(ctx, remoteAddress, localAddress, promise);
//        System.out.println("进入connect");
//    }
//
//    @Override
//    public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
//        super.disconnect(ctx, promise);
//        System.out.println("进入disconnect");
//
//    }
//
//    @Override
//    public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
//        super.close(ctx, promise);
//        System.out.println("进入close");
//
//    }
//
//    @Override
//    public void deregister(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
//        super.deregister(ctx, promise);
//        System.out.println("进入deregister");
//
//    }
//
//    @Override
//    public void read(ChannelHandlerContext ctx) throws Exception {
//        super.read(ctx);
//        System.out.println("进入read");
//
//    }
//
//    @Override
//    public void flush(ChannelHandlerContext ctx) throws Exception {
//        super.flush(ctx);
//        System.out.println("进入flush");
//
//    }
//
//    @Override
//    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
//        super.handlerAdded(ctx);
//        System.out.println("进入handlerAdded");
//    }
//
//    @Override
//    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//        super.handlerRemoved(ctx);
//        System.out.println("进入handlerRemoved");
//
//    }
}
