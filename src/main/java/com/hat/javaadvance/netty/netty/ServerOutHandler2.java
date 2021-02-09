package com.hat.javaadvance.netty.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.nio.charset.StandardCharsets;

public class ServerOutHandler2 extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ByteBuf data = (ByteBuf) msg;
        String s = "ServerOutHandler2《=====" + data.toString(StandardCharsets.UTF_8);
        System.out.println(s);
        ctx.writeAndFlush(Unpooled.copiedBuffer(s,StandardCharsets.UTF_8));
    }

}
