package main.java.com.NIO.nio._1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * 服务器端
 *
 * @author chenssy
 * @date 2017-04-20
 * @since v1.0.0
 */
public class MultiplexerTimerServer implements Runnable{

    // 多路复用器
    private Selector selector;

    // 通道
    private ServerSocketChannel serverSocketChannel;

    private volatile boolean stop;

    MultiplexerTimerServer(int port){
        try {
            // 打开通道channel，监听客户端连接
            selector = Selector.open();

            serverSocketChannel = ServerSocketChannel.open();
            //监听端口
            serverSocketChannel.socket().bind(new InetSocketAddress(port),1024);
            // 设置为非阻塞
            serverSocketChannel.configureBlocking(false);

            //将通道channel注册到多路复用器selector上面
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("The time server is start in port:" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop){
            try {
                selector.select(1000);

                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();

                SelectionKey key = null;

                while (it.hasNext()){
                    key = it.next();

                    it.remove();

                    try {
                        handleInput(key);
                    } catch (IOException e) {
                        if(key != null){
                            key.cancel();
                            if(key.channel() != null){
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 多路复用器关闭后，所有注册在上面的Channel等资源都会自动去注册并关闭，所以不需要重复释放资源
        if(selector != null){
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理链接请求
     *
     * @author chenssy
     * @date 2017-04-20
     * @since v1.0.0
     */
    private void handleInput(SelectionKey key) throws IOException {
        if(key.isValid()){
            // 处理新接入的请求
            if(key.isAcceptable()){
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();

                SocketChannel channel = ssc.accept();
                ssc.configureBlocking(false);
                ssc.register(selector,SelectionKey.OP_READ);        // 准备读
            }

            // 读取数据
            if(key.isReadable()){
                SocketChannel sc = (SocketChannel) key.channel();

                ByteBuffer readBuffer = ByteBuffer.allocate(1024);

                int readBytes = sc.read(readBuffer);

                if(readBytes > 0){
                    readBuffer.flip();

                    byte[] bytes = new byte[readBuffer.remaining()];

                    readBuffer.get(bytes);
                    String body = new String(bytes,"UTF-8");

                    System.out.println("The time server receive order:" + body);
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";

                    doWrite(sc,currentTime);
                }else if(readBytes < 0){
                    // 关闭终端
                    key.cancel();
                    sc.close();
                }
            }
        }
    }

    /**
     * 向客户端写入数据
     *
     * @author chenssy
     * @date 2017-04-20
     * @since v1.0.0
     */
    private void doWrite(SocketChannel channel, String response) throws IOException {
        if(response != null && response.trim().length() > 0){
            byte[] bytes = response.getBytes();

            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);

            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        }
    }
}
