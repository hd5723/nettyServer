package com.qhkj.nettychatserver.netty;

import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.util.concurrent.ImmediateEventExecutor;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.group.ChannelGroup;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.util.concurrent.Future;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import io.netty.channel.*;

@Slf4j
@Configuration
public class NettyWebsocketServer {

    /**
     * 读超时时间
     */
    @Value("${netty.read.time:300}")
    private int readTime;

    @Value("${netty.write.time:30}")
    private int writeTime;

    @Value("${netty.readAndwrite.time:0}")
    private int readAndWriteTime;

    /**
     * 创建 DefaultChannelGroup，用来保存所有已经连接的 WebSocket Channel，群发和一对一功能可以用上
     */
    private final static ChannelGroup channelGroup =
            new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);

    //bossGroup 连接线程组，主要负责接受客户端连接
    EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    private EventLoopGroup workerGroup = new NioEventLoopGroup();
    private ChannelFuture channelFuture;

    /**
     * 销毁
     */
    @PreDestroy
    public void destroy() {
        Future<?> future = bossGroup.shutdownGracefully();
        Future<?> future1 = workerGroup.shutdownGracefully();
        future.syncUninterruptibly();
        future1.syncUninterruptibly();
        log.info("关闭 ws server 成功");
    }

    private void init() throws InterruptedException {
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            // 因为使用 HTTP 协议，所以需要 HTTP编码器，解码器
                            pipeline.addLast(new HttpServerCodec());
                            // 以块方式，添加 chunkedWriter 处理器
                            pipeline.addLast(new ChunkedWriteHandler());
                            /**
                             * 1. http数据在传输中是分段的，HttpObjectAggregator 可以把多个段聚合起来
                             * 2. 这就是为什么当浏览器发送大量数据时，就会发出多次 http请求的原因
                             */
                            pipeline.addLast(new HttpObjectAggregator(8192));
                            // 保存用户ip
                            // pipeline.addLast(new HttpHeadersHandler());
                            pipeline.addLast(new WebSocketServerProtocolHandler("/chat"));

                            pipeline.addLast(new IdleStateHandler(readTime, writeTime, readAndWriteTime));
                            pipeline.addLast(new NettyHandler());
                        }
                    });

            // Bind and start to accept incoming connections.
            channelFuture = b.bind(9091).sync();
            if(channelFuture.isSuccess()) {
                log.info("netty启动成功，端口: {}", 9091);
            }
            // 对通道关闭进行监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            if (channelFuture != null && channelFuture.isSuccess()) {
                System.out.println("Netty server started on port 9091");
            } else {
                System.err.println("Netty server failed to start");
            }
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    @PostConstruct
    public void start() {
        new Thread(() -> {
            try {
                init();
            } catch (InterruptedException e) {
                log.error("netty启动失败 , msg: " + e.getMessage());
            }
        }).start();
    }

}
