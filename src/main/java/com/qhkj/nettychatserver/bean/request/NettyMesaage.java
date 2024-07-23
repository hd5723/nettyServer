package com.qhkj.nettychatserver.bean.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * netty的消息体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NettyMesaage {

    // 消息ID
    private Long messageId;

    // 消息发送人 编号
    private Long sender;

    // 消息接收人 编号
    private Long receiver;

    // 消息业务类型  聊天:chat  大屏:dashboard
    private String businessType;

    // 消息内容
    private String messageText;

    // 消息内容类型  1:文本   2:表情包  3:语音  4:图片
    private String messageType;

    // 消息动作
    private String action;

}
