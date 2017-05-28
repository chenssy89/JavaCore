package main.java.com.NIO.netty.base;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeClientHandler extends ChannelHandlerAdapter{
    
    private final ByteBuf firstMessage;
    
    public TimeClientHandler(){
        byte[] req = "QUERY TIME ORDER".getBytes();
        firstMessage = Unpooled.buffer(req.length);
        
        firstMessage.writeBytes(req);
    }
    
    

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        
        String body = new String(req,"UTF-8");
        
        System.out.println("Now is :" + body);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        
        System.out.println("cause by :" + cause.getMessage());
        
        ctx.close();
    }
}
