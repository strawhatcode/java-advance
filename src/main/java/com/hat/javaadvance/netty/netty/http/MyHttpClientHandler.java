package com.hat.javaadvance.netty.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class MyHttpClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // GET请求
        FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1,
                HttpMethod.GET, "/getSth?p1=aaa&p2=333");  // 构建http GET请求
        // 设置请求头
        request.headers().add(HttpHeaderNames.ACCEPT,"*/*");
        request.headers().add(HttpHeaderNames.CONTENT_LENGTH,request.content().readableBytes());
        request.headers().add(HttpHeaderNames.CONNECTION,HttpHeaderValues.KEEP_ALIVE);
        request.headers().add(HttpHeaderNames.ACCEPT_ENCODING,HttpHeaderValues.GZIP_DEFLATE);
        request.headers().add(HttpHeaderNames.HOST,ctx.channel().localAddress());


//        // POST请求 json参数
//        String param = "{\"json1\":\"数据\",\"jsonNum\":55555}";
//        ByteBuf byteBuf = Unpooled.copiedBuffer(param, CharsetUtil.UTF_8);
//        // 构建HTTP POST请求
//        FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1,
//                HttpMethod.POST, "/postSth", byteBuf);
//        // 设置请求头
//        request.headers().add(HttpHeaderNames.ACCEPT,"*/*");
//        request.headers().add(HttpHeaderNames.CONTENT_LENGTH,request.content().readableBytes());
//        request.headers().add(HttpHeaderNames.CONNECTION,HttpHeaderValues.KEEP_ALIVE);
//        request.headers().add(HttpHeaderNames.ACCEPT_ENCODING,HttpHeaderValues.GZIP_DEFLATE);
//        request.headers().add(HttpHeaderNames.HOST,ctx.channel().localAddress());
//        request.headers().add(HttpHeaderNames.CONTENT_TYPE,HttpHeaderValues.APPLICATION_JSON);

        ctx.writeAndFlush(request);  // 发送数据

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpResponse response = (FullHttpResponse) msg;  // 接受服务端的响应内容
        ByteBuf content = response.content();   // 响应内容
        HttpHeaders headers = response.headers();   // 响应头
        System.out.println("服务端响应内容："+content.toString(CharsetUtil.UTF_8));
        System.out.println("服务端响应头：");
        headers.forEach(o -> {
            System.out.println(o.getKey()+"="+o.getValue());
        });

    }
}
