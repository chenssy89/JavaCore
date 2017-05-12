package com.ufclub.ljs.autoInvest.model.AutoInvestSettingcom.NIO.netty.tcpIP.stickers;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class TimeClient {

    public void connect(int port, String host) {
        // 配置客户端线程组
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();

        try {
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            socketChannel.pipeline().addLast(new StringDecoder());
                            socketChannel.pipeline().addLast(new TimeClientHandler());
                        }
                    });

            // 发起异步连接操作
            ChannelFuture future = b.connect(host, port);

            // 等待客户端链路关闭

            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {

        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new TimeClient().connect(8899,"127.0.0.1");
    }
}
