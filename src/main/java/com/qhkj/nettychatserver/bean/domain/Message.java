package com.qhkj.nettychatserver.bean.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName
public class Message {

    // 消息ID
    @TableId
    private Long messageId;

    // 消息发送人 编号
    private Long sender;

    // 消息接收人 编号
    private Long receiver;

    @TableField(exist = false)
    // 消息业务类型  聊天:chat  大屏:dashboard
    private String businessType;

    // 消息内容
    private String messageText;

    // 消息内容类型  1:文本   2:表情包  3:语音  4:图片
    private String messageType;

    // 消息动作
    private String action;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;


}
