package com.qhkj.nettychatserver.bean.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表;
 * @author : http://www.chiner.pro
 * @date : 2024-7-23
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName
public class User implements Serializable,Cloneable {
    /**
     * 用户ID
     */
    @TableId
    private Long userId;
    /**
     * 登录账号
     */
    private String loginName;
    /**
     * 用户昵称
     */
    private String userName;
    /**
     * 账号类型 1：管理员账号
     */
    private Integer type;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 手机号码
     */
    private String phonenumber;
    /**
     * 用户性别（0男 1女 2未知）
     */
    private Integer sex;
    /**
     * 头像路径
     */
    private String avatar;
    /**
     * 密码
     */
    @JsonFormat()
    private String password;
    /**
     * 盐加密
     */
    private String salt;
    /**
     * 帐号状态（0正常 1停用）
     */
    private Integer status;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private Integer delFlag;
    /**
     * 最后登陆IP
     */
    private String loginIp;
    /**
     * 最后登陆时间
     */
    private Date loginDate;
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
    /**
     * 备注
     */
    private String remark;
    /**
     *
     */
    private String openid;
    /**
     * 在线状态(online;offline)
     */
    private String onlineStatus;
}