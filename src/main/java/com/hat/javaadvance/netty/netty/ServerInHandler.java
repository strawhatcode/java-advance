package com.hat.javaadvance.netty.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;

public class ServerInHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println("ServerInHandler进入channelRead");
        ByteBuf data = (ByteBuf) msg;
        String m = "ServerInHandler《====="+data.toString(CharsetUtil.UTF_8);
        System.out.println(m);
        System.out.println("ctx.attr: "+ctx.attr(AttributeKey.valueOf("cccName")));
        System.out.println("channel.attr:"+ctx.channel().attr(AttributeKey.valueOf("cccName")));
        ctx.fireChannelRead(Unpooled.copiedBuffer(m,CharsetUtil.UTF_8)); // 转发给下一个inbound继续执行
//        ctx.writeAndFlush(Unpooled.copiedBuffer("我是ServerInHandler",CharsetUtil.UTF_8));
    }

//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("ServerInHandler读取完成");
//    }

//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        cause.printStackTrace();
//        System.out.println("ServerInHandler发生异常");
//    }
//
//    @Override
//    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("ServerInHandler进入channelRegistered");
//    }
//
//    @Override
//    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("ServerInHandler进入channelUnregistered");
//
//    }
//
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("ServerInHandler进入channelActive");
//
//    }
//
//    @Override
//    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("ServerInHandler进入channelInactive");
//
//    }
//
//    @Override
//    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//        System.out.println("ServerInHandler进入userEventTriggered");
//    }
//
//    @Override
//    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("ServerInHandler进入channelWritabilityChanged");
//    }
//
//    @Override
//    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("ServerInHandler进入handlerAdded");
//    }
//
//    @Override
//    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("ServerInHandler进入handlerRemoved");
//
//    }
}
