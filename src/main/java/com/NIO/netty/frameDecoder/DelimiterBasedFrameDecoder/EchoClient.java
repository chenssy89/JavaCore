package com.NIO.netty.frameDecoder.DelimiterBasedFrameDecoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class EchoClient {
    public void connect(int port,String host) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap sb = new Bootstrap();
            sb.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());

                            socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));

                            socketChannel.pipeline().addLast(new StringDecoder());

                            socketChannel.pipeline().addLast(new EchoClientHandler());
                        }
                    });

            ChannelFuture f = sb.connect(host,port).sync();

            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
    
    public static void main(String[] args) throws Exception {
            new EchoClient().connect(8999,"127.0.0.1");
    }
    
    
}
