package com.qhkj.nettychatserver.service;

import com.qhkj.nettychatserver.bean.domain.Message;

/**
 * 消息服务
 */
public interface MessageService {

    boolean insertOne(Message message) ;

    Message queryOne(Long id) ;
}
