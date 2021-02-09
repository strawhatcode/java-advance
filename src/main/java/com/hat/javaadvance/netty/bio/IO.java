package com.hat.javaadvance.netty.bio;

import java.io.*;
import java.net.SocketTimeoutException;

public class IO {
    public static void main(String[] args) throws IOException {
//        useByteArrayStream1();
//        useByteArrayStream2();
//        useBufferedReader();
//        useFileInputStream();
        useRandomAccessFile();
    }

    // 简单使用ByteArrayInputStream和ByteArrayOutputStream
    public static void useByteArrayStream1() throws IOException {
        byte[] bytes = "Stream".getBytes();  // 字节数组
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes); // 创建输入流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  // 创建输出流
        System.out.println("字节数组的长度："+bytes.length);

        // 1.------
//        byte[] bytes1 = new byte[bytes.length];
//        int read1 = bais.read(bytes1); // 把输入流（bais）中的字节读取到bytes1数组中
//        int read = bais.read(bytes1,0,4); // 读取输入流（bais）中前4个字节到bytes1中，其中bytes1数组的索引从0开始

        long skip = bais.skip(2); // 跳过前两个字节

        // 2.------
//        bais.mark(3);  // 记录输入流的第3个字节
//        int flag = 0;

        int d;  // 记录读取输入流中的字节，当d为-1时则表明输入流已经读完了
        while ((d = bais.read()) != -1){

            int i = bais.available();  // 获取输入流中还有几个字节

            System.out.println("剩余长度："+i);
            System.out.println("读取到的字节："+d);
            // 2.------
//            flag++;
//            if (flag == 3){
//                bais.reset(); // 当flag等于3时把输入流的指针指向返回到bais.mark(3)记录的位置，即第3个字节
//            }
            baos.write(d);  // 将字节写入输出流
        }
        System.out.println("把输入流的字节写如输出流："+baos.toString());

        baos.flush(); // 关闭输出流
        bais.close(); // 关闭输入流
    }
    public static void useByteArrayStream2() throws IOException {
        byte[] bytes = "Stream".getBytes();  // 字节数组
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes); // 创建输入流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  // 创建输出流
        byte[] bytes1 = new byte[bytes.length];
        bais.read(bytes1);  // 将输入流中的字节读取到bytes1中
        baos.write(bytes1);  // 把bytes1中的字节写入到输出流中
//        baos.write(bytes1,0,3); // 把bytes1从第0位开始取3个字节写入到输出流中
        System.out.println(baos.toString());
        baos.close();  // 关闭输出流
        bais.close();  // 关闭输入流
    }

    public static void useBufferedReader() throws IOException {
        String msg = "字符输入流\n 第二行";
        BufferedReader br = new BufferedReader(new StringReader(msg)); // 将字符输入流写入到输入缓冲区中
        char[] chars = new char[msg.length()];
        br.read(chars);  // 将输入缓冲区中的字符读取到chars中
        StringWriter sw = new StringWriter();  // 创建一个字符输出流
        BufferedWriter bw = new BufferedWriter(sw); // 创建一个输出缓冲区
        bw.write(chars);  // 将chars写入到缓冲输出流中（注意:还没有输出到字符流中）
        bw.flush();   // 刷新缓冲区，将缓冲区的数据写入到StringWriter字符输出流中
        bw.close();
        br.close();
        System.out.println(sw.toString());
    }

    public static void useFileInputStream() throws IOException {
        File file = new File("D:\\aa.txt");  // 创建一个文件对象
        FileInputStream fis = new FileInputStream(file); // 将文件读取到输入流中,文件不能会报异常
        byte[] bytes = new byte[(int) file.length()];
        fis.read(bytes);  // 将文件内容读取到bytes中
        System.out.println(new String(bytes));
        FileOutputStream fos = new FileOutputStream(file);  // 创建一个文件输出流
        fos.write("更改文件内容".getBytes());  // 给输出流写入内容，并把内容写入到文件中
        fos.close();  // 关闭输出流
        fis.close();  // 关闭输入流
    }

    public static void useRandomAccessFile() throws IOException {
        File file = new File("D:\\aa.txt");
        RandomAccessFile raf = new RandomAccessFile(file,"rw"); // 创建一个可读可写的随机访问文件对象
        raf.write("给aa文件添加内容".getBytes());  // 写入内容到文件
        raf.close();  // 关闭文件
        RandomAccessFile raf2 = new RandomAccessFile(file,"r");  //创建一个只读的文件独享

        byte[] bytes = new byte[(int) file.length()];
        raf2.read(bytes); // 将文件内容读取到bytes中, 指针指向字节的位置是尾部
        System.out.println(new String(bytes));
        raf2.seek(4);  // 将指针移到第4个字节处(注意:一个中文3个字节)
        byte[] bytes1 = new byte[bytes.length];
        int read = raf2.read(bytes1);  // 将从第4个字节到最后的字节读取到bytes1中
        System.out.println(new String(bytes1));
        raf2.close();  // 关闭文件
    }
}
