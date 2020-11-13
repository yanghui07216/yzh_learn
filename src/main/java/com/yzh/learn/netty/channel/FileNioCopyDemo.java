package com.yzh.learn.netty.channel;


import sun.nio.ch.IOUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileNioCopyDemo {

    private static String FILE_RESOURCE_SRC_PATH = "/Users/yzh/Downloads/work/workPath.docx";
    private static String FILE_RESOURCE_DEST_PATH = "/Users/yzh/Downloads/test/workPath.docx";

    public static void main(String[] args) {
        // 演示赋值资源文件
        nioCopyResourceFile();
    }

    // 复制两个资源目录下的文件
    public static void nioCopyResourceFile() {
        String srcPath = FILE_RESOURCE_SRC_PATH;
        String destPath = FILE_RESOURCE_DEST_PATH;
        nioCopyFile(srcPath, destPath);

    }

    private static void nioCopyFile(String srcPath, String destPath) {
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);
        try {
            // 如果目标文件不存在，则新建
            if (!destFile.exists()) {
                destFile.createNewFile();
            }
            long startTime = System.currentTimeMillis();
            FileInputStream fis = null;
            FileOutputStream fos = null;
            FileChannel inChannel = null;
            FileChannel outChannel = null;

            try {
                fis = new FileInputStream(srcFile);
                fos = new FileOutputStream(destFile);
                inChannel = fis.getChannel();
                outChannel = fos.getChannel();
                int length = -1;
                ByteBuffer buf = ByteBuffer.allocate(1024);
                // 从输入通道读取到buf
                while((length = inChannel.read(buf)) != -1) {
                    // 第一次切换：翻转buf，变成读取模式
                    buf.flip();
                    int outLength = 0;
                    // 将buf写入到输出的通道
                    while((outLength = outChannel.write(buf)) != 0) {
                        System.out.println("写入的字节数：" + outLength);
                    }
                    // 第二次切换：清除buf，变成写入模式
                    buf.clear();
                }
                // 强制刷新到磁盘
                outChannel.force(true);
            } finally {
                outChannel.close();
                fos.close();
                inChannel.close();
                fis.close();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("base复制毫秒数：" + (endTime - startTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
