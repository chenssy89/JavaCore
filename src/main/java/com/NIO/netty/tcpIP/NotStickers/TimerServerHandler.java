package com.NIO.netty.tcpIP.NotStickers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

/**
 * 未考虑粘包拆包
 *
 * @author chenssy
 * @date 2017-05-05
 * @since v1.0.0
 */
public class TimerServerHandler extends ChannelHandlerAdapter{

    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;

        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);

        String body = new String(req,"UTF-8").substring(0,req.length - System.getProperty("line.separator").length());

        System.out.println("The time server receive order:" + body + "; the counter is " + (++counter));

        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";

        currentTime = currentTime + System.getProperty("line.separator");

        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());

        ctx.writeAndFlush(resp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
