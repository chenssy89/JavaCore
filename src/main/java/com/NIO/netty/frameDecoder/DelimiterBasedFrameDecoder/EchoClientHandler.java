package com.NIO.netty.frameDecoder.DelimiterBasedFrameDecoder;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class EchoClientHandler extends ChannelHandlerAdapter{
    private int counter;

    // 末尾增加$_
    static final String ECHO_REQ = "Hi,chenssy,welcome to netty.$_";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i = 0 ; i < 10 ; i++){
            ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("This is " + (++counter) + " times receive server:[" + msg + "]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
