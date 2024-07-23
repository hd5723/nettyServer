package com.qhkj.nettychatserver.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qhkj.nettychatserver.bean.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户信息表;(t_user)表数据库访问层
 * @author : http://www.chiner.pro
 * @date : 2024-7-23
 */
@Mapper
public interface UserMapper  extends BaseMapper<User>{
    /**
     * 分页查询指定行数据
     *
     * @param page 分页参数
     * @param wrapper 动态查询条件
     * @return 分页对象列表
     */
    IPage<User> selectByPage(IPage<User> page , @Param(Constants.WRAPPER) Wrapper<User> wrapper);
}