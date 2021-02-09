package com.hat.javaadvance.netty.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;

public class MyClientChannelHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast("httpClient",new HttpClientCodec()); // http客户端编解码器
        ch.pipeline().addLast("aggregator",new HttpObjectAggregator(65535)); // http客户端编解码器
        ch.pipeline().addLast("myHttpClientHandler",new MyHttpClientHandler()); // 自定义一个客户端处理
    }
}
