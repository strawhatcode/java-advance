package com.hat.javaadvance.netty.nio;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class NIO {
    public static void main(String[] args) {
        useBuffer();
    }

    public static void useBuffer(){
        byte[] msg = "abcde".getBytes(); // 5个字节的数据
        System.out.println(Arrays.toString(msg));
        ByteBuffer buffer = ByteBuffer.allocate(5);  // 创建一个容量为5的ByteBuffer
        buffer.put(msg); // 将5个字节写入buffer
        buffer.flip();   // 反转buffer
        //buffer.clear();  // 重置buffer

        while (buffer.hasRemaining()){ // 判断buffer中是否还有数据
            System.out.println(buffer.get()); // 读取buffer中的一个字节
        }
    }

}
