package com.hat.javaadvance.netty.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.*;
import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MyHttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        // chrome浏览器会先请求/favicon.ico获取网页图标，直接return
        if ("/favicon.ico".equals(msg.uri())) {
            return;
        }
        String name = msg.method().name();
        System.out.println("请求的方法：" + name);

        // 预组装一个httpResponse响应对象
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.NOT_FOUND);
        response.headers().add(HttpHeaderNames.CONTENT_TYPE, "application/json;charset=utf-8");

        // GET请求
        if ("GET".equals(name)) {
            // 使用QueryStringDecoder解码器来解析get请求的uri和参数
            QueryStringDecoder decoder = new QueryStringDecoder(msg.uri());
            String uri = decoder.uri().split("\\?")[0];  // 获取uri
            Map<String, List<String>> parameters = decoder.parameters();  // 获取参数
            parameters.forEach((s, strings) -> System.out.println("参数：" + s + "=" + strings.get(0))); // 遍历输出所有参数
            System.out.println("请求的uri：" + uri);
            if ("/getSth".equals(uri)) {  // uri是getSth的get请求
                String res = "getSth接口已收到，这是响应~~~";
                System.out.println("响应内容长度：" + res.getBytes().length);
                ByteBuf byteBuf = Unpooled.copiedBuffer(res, CharsetUtil.UTF_8);  // 组装响应内容
                response.setStatus(HttpResponseStatus.OK);   // 设置响应状态码
                response = response.replace(byteBuf);  // 设置响应内容
            }
            // POST请求
        } else if ("POST".equals(name)) {
            HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(msg); // 使用HttpPostRequestDecoder解析参数
            List<InterfaceHttpData> bodyHttpDatas = decoder.getBodyHttpDatas();  // 获取所有参数
            bodyHttpDatas.forEach(interfaceHttpData -> {  // 遍历所有参数
                // 判断是哪种参数类型，Attribute是普通key=value类型表单参数
                if (interfaceHttpData.getHttpDataType() == InterfaceHttpData.HttpDataType.Attribute) {
                    Attribute data = (Attribute) interfaceHttpData; // 将参数转换成Attribute类型
                    try {
                        System.out.println("参数：" + data.getName() + "=" + data.getValue());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                // 参数是文件
                else if (interfaceHttpData.getHttpDataType() == InterfaceHttpData.HttpDataType.FileUpload) {
                    System.out.println("参数是文件~~~~");
                    FileUpload data = (FileUpload) interfaceHttpData; // 将参数转换成FileUpload类型
                }

            });
            decoder.destroy();  // 使用完要销毁
            // 一般在content-type在postman中的raw参数才会使用，其他情况可以用以上方法获取参数
            String content = msg.content().toString(CharsetUtil.UTF_8);
            System.out.println("请求的参数体：" + content);

            String uri = msg.uri();
            System.out.println("请求的uri：" + uri);
            if ("/postSth".equals(uri)) {
                response.setStatus(HttpResponseStatus.OK); // 响应状态码
                String res = "/postSth接口收到请求";
                ByteBuf byteBuf = Unpooled.copiedBuffer(res, CharsetUtil.UTF_8);
                response = response.replace(byteBuf);  // 设置响应参数
            }
        }
        // 把响应数据响应给客户端，发送完毕后需关闭通道
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
