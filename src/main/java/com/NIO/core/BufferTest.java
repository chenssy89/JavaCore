package com.NIO.core;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Buffer测试<br>
 * buffer使用步骤：
 * 1、写入数据到Buffer
 * 2、调用flip()方法
 * 3、从Buffer中读取数据
 * 4、调用clear()方法或者compact()方法
 *
 * @author chenming
 * @date 2016-11-21
 * @since v1.0.0
 */
public class BufferTest {
    public static void main(String[] args) throws Exception {
        RandomAccessFile file = new RandomAccessFile("E:/workspace/channelTest.txt","rw");

        FileChannel fileChannel = file.getChannel();

        //对buffer对象进行分配
        ByteBuffer buf = ByteBuffer.allocate(24);

        int bytesRead = fileChannel.read(buf);
        while (bytesRead != -1){
            buf.flip();     //切换到读模式
            while (buf.hasRemaining()){
                System.out.println((char)buf.get());
            }

            buf.clear();   //清空缓存
            bytesRead = fileChannel.read(buf);
        }

        file.close();
    }
}
