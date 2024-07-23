package com.qhkj.nettychatserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qhkj.nettychatserver.service.MessageService;
import com.qhkj.nettychatserver.mapper.MessageMapper;
import com.qhkj.nettychatserver.bean.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    public boolean insertOne(Message message) {
        int i = messageMapper.insert(message);
        if(i > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Message queryOne(Long id) {
        return messageMapper.selectById(id);
    }

}
