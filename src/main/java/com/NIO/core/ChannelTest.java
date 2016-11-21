package com.NIO.core;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelTest {
    public static void main(String[] args) throws Exception {
        RandomAccessFile file = new RandomAccessFile("E:/workspace/channelTest.txt","rw");

        FileChannel fileChannel = file.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(8);

        int bytesRead = fileChannel.read(buf);
        while (bytesRead != -1){
            System.out.println("Read " + bytesRead);
            buf.flip();
            while (buf.hasRemaining()){
                System.out.println((char)buf.get());
            }

            buf.clear();
            bytesRead = fileChannel.read(buf);
        }

        file.close();
    }
}
