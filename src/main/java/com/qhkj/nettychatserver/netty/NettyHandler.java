package com.qhkj.nettychatserver.netty;

import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import com.qhkj.nettychatserver.bean.request.NettyMesaage;
import com.qhkj.nettychatserver.util.SpringContextUtils;
import com.qhkj.nettychatserver.service.NettyService;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import org.springframework.util.StringUtils;
import io.netty.channel.ChannelHandler;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ChannelHandler.Sharable
public class NettyHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private NettyService nettyService;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String messgae = msg.text();
        log.info("netty接收到消息->{}", messgae);
        if(!StringUtils.isEmpty(messgae)) {
            NettyMesaage nettyMesaage = JSON.parseObject(messgae, NettyMesaage.class);
            try {
                nettyService = (NettyService) SpringContextUtils.applicationContext.getBean(nettyMesaage.getBusinessType());
                nettyService.nettyHandler(nettyMesaage, ctx.channel());
            } catch (Exception e) {
                log.info("netty业务异常->{}", e.getMessage());
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("code", 500);
                dataMap.put("msg", e.getMessage());
                ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(dataMap)));
            }
        }
    }

    /**
     * 读超时、写超时、读写超时 事件
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            String eventType = null;
            switch (event.state()) {
                case READER_IDLE:
                    // 读空闲
                    eventType = "读空闲";
                    // 打印触发超时警告
                    log.info("{} 超时事件：{}",ctx.channel().remoteAddress(), eventType);
                    log.info(" [server]读空闲超时，关闭连接，释放更多资源");
                    ctx.channel().writeAndFlush("idle close");
                    ctx.channel().close(); // 手动断开连接
                    break;
                case WRITER_IDLE:
                    // 写空闲
                    eventType = "写空闲";
                    log.info("写空闲 channel: {}", ctx.channel().id());
                    ctx.channel().writeAndFlush("cricly time write");
                    break;
                case ALL_IDLE:
                    // 读写空闲
                    eventType = "读写空闲";
                    log.info("读写空闲 channel: {}", ctx.channel().id());
                    ctx.channel().close();
                    break;
            }

        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
