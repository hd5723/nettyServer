package com.qhkj.nettychatserver.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.qhkj.nettychatserver.bean.domain.Message;
import com.qhkj.nettychatserver.config.http.HttpResultGenerator;
import com.qhkj.nettychatserver.config.http.HttpStatusEnum;
import com.qhkj.nettychatserver.constant.Common;
import com.qhkj.nettychatserver.constant.NettyCommon;
import com.qhkj.nettychatserver.service.MessageService;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import com.qhkj.nettychatserver.bean.request.NettyMesaage;
import com.qhkj.nettychatserver.service.NettyService;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;

import javax.annotation.Resource;
import java.util.Date;

@Service("chat")
public class ChatNettyServiceImpl implements NettyService {

    @Resource
    private MessageService messageService;

    @Override
    public void nettyHandler(NettyMesaage message, Channel channel) {
        Date now = new Date();
        Message dbmsg = Message.builder()
                .messageId(NettyCommon.getIdWorker().nextId())
                .createTime(now)
                .modifyTime(now)
                .build();

        BeanUtil.copyProperties(message, dbmsg, Common.options);
        boolean flag = messageService.insertOne(dbmsg);
        if (flag) {
            channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(HttpResultGenerator.success(message.getMessageId()))));
        } else {
            channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(HttpResultGenerator.fail(HttpStatusEnum.INTERNAM_SERVER_ERROR.getCode(), message.getMessageId() + ""))));
        }
    }
}
