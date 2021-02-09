package com.hat.javaadvance.netty.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;

public class ByteBufT {
    public static void main(String[] args) {
        b();
        a();
        c();
    }

    private static void a() {
        final byte[] CONTENT = new byte[1024];
        int loop = 1800000;
        long startTime = System.currentTimeMillis();
        ByteBuf poolBuffer = null;
        for (int i = 0; i < loop; i++) {
            poolBuffer = PooledByteBufAllocator.DEFAULT.directBuffer(1024);
            poolBuffer.writeBytes(CONTENT);
            poolBuffer.release();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("内存池分配缓冲区耗时" + (endTime - startTime) + "ms.");
    }

    private static void b() {
        final byte[] CONTENT = new byte[1024];
        int loop = 1800000;
        long startTime2 = System.currentTimeMillis();
        ByteBuf buffer = null;
        for (int i = 0; i < loop; i++) {
            buffer = Unpooled.directBuffer(1024);
            buffer.writeBytes(CONTENT);
            buffer.release();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("非内存池分配缓冲区耗时" + (endTime - startTime2) + "ms.");
    }

    private static void c(){
        final byte[] CONTENT = new byte[1024];
        int loop = 1800000;
        long startTime2 = System.currentTimeMillis();
        ByteBuf buffer = null;
        for (int i = 0; i < loop; i++) {
            buffer = Unpooled.copiedBuffer(CONTENT);
            buffer.release();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("非池化" + (endTime - startTime2) + "ms.");
    }

}
