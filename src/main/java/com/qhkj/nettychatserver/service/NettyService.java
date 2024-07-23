package com.qhkj.nettychatserver.service;

import com.qhkj.nettychatserver.bean.request.NettyMesaage;
import io.netty.channel.Channel;

public interface NettyService {

    /**
     * netty回调
     * @param message
     * @param channel
     */
    void nettyHandler(NettyMesaage message, Channel channel);

}
