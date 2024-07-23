package com.qhkj.nettychatserver;

import com.alibaba.fastjson.JSON;
import com.qhkj.nettychatserver.bean.domain.Message;
import com.qhkj.nettychatserver.constant.NettyCommon;
import com.qhkj.nettychatserver.service.MessageService;
import com.qhkj.nettychatserver.util.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@SpringBootTest
public class MessageTest {

    @Resource
    private MessageService messageService;

    @Test
    void addMessage() {
        Date now = new Date();
        Message message = Message.builder()
                .messageId(NettyCommon.getIdWorker().nextId())
                .businessType("chat")
                .messageType("1")
                .messageText("hello world")
                .action("send")
                .sender(111L)
                .receiver(2222L)
                .createTime(now)
                .modifyTime(now)
                .build();
        boolean flag = messageService.insertOne(message);
        if(flag) {
            log.info("保存数据成功!");
        } else {
            log.error("保存数据失败!");
        }
    }

    @Test
    void selectOne() {
        Long id = 1265320424233762816L;
        Message message = messageService.queryOne(id);
        log.info("msg: {}", JSON.toJSONString(message));
    }

}
