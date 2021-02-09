package com.hat.javaadvance.netty.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpServerChannelHandler extends ChannelInitializer<NioSocketChannel> {

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ch.pipeline().addLast("httpServer",new HttpServerCodec());
        ch.pipeline().addLast("httpAggregator", new HttpObjectAggregator(65535));
        ch.pipeline().addLast("myHttpServerHandler",new MyHttpServerHandler());
    }
}
