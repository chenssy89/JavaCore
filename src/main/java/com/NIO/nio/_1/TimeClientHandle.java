package com.NIO.nio._1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class TimeClientHandle implements Runnable{
    private String host;
    
    private int port;
    
    private Selector selector;
    
    private SocketChannel socketChannel;
    
    private volatile boolean stop;
    
    public TimeClientHandle(String host,int port){
        this.host = host == null ? "127.0.0.1" : host;
        this.port = port;

        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    @Override
    public void run() {
        try {
            doConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 链接服务端
     * 如果链接成功，则注册到多路复用器上，发送请求消息，读应答
     * @author chenming
     * @date 2017-04-20
     * @since v1.0.0
     */
    private void doConnect() throws IOException {
       if(socketChannel.connect(new InetSocketAddress(host,port))){
           socketChannel.register(selector, SelectionKey.OP_READ);
           dowWrite(socketChannel);
       }else{
           socketChannel.register(selector,SelectionKey.OP_CONNECT);
       }
    }

    /**
     * 发送消息
     *
     * @author chenming
     * @date 2017-04-20
     * @since v1.0.0
     */
    private void dowWrite(SocketChannel socketChannel) throws IOException {
        byte[] req = "QUERY TIME ORDER".getBytes();

        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        
        writeBuffer.put(req);
        writeBuffer.flip();
        
        socketChannel.write(writeBuffer);
        
        if(!writeBuffer.hasRemaining()){
            System.out.println("Send order 2 Server succeed.");
        }
    }
}
