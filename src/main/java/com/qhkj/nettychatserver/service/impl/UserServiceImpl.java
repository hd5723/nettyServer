package com.qhkj.nettychatserver.service.impl;

import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.qhkj.nettychatserver.service.UserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qhkj.nettychatserver.mapper.UserMapper;
import com.qhkj.nettychatserver.bean.domain.User;
import org.springframework.stereotype.Service;
import cn.hutool.core.util.StrUtil;
/**
 * 用户信息表;(t_user)表服务实现类
 * @author : http://www.chiner.pro
 * @date : 2024-7-23
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    public User queryById(Long userId){
        return userMapper.selectById(userId);
    }

    @Override
    public User queryByLoginNameOrPhone(String loginName, String phone) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(StringUtils.isNotEmpty(loginName), User::getLoginName, loginName);
        lambdaQueryWrapper.eq(StringUtils.isNotEmpty(phone), User::getPhonenumber, phone);
        return userMapper.selectOne(lambdaQueryWrapper);
    }

    @Override
    public User queryByLoginName(String loginName) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getLoginName, loginName);
        return userMapper.selectOne(lambdaQueryWrapper);
    }

    @Override
    public User queryByPhone(String phone) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getPhonenumber, phone);
        return userMapper.selectOne(lambdaQueryWrapper);
    }

    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    public Page<User> paginQuery(User user, long current, long size){
        //1. 构建动态查询条件
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        if(StrUtil.isNotBlank(user.getLoginName())){
            queryWrapper.eq(User::getLoginName, user.getLoginName());
        }
        if(StrUtil.isNotBlank(user.getUserName())){
            queryWrapper.eq(User::getUserName, user.getUserName());
        }
        if(StrUtil.isNotBlank(user.getEmail())){
            queryWrapper.eq(User::getEmail, user.getEmail());
        }
        if(StrUtil.isNotBlank(user.getPhonenumber())){
            queryWrapper.eq(User::getPhonenumber, user.getPhonenumber());
        }
        if(StrUtil.isNotBlank(user.getAvatar())){
            queryWrapper.eq(User::getAvatar, user.getAvatar());
        }
        if(StrUtil.isNotBlank(user.getPassword())){
            queryWrapper.eq(User::getPassword, user.getPassword());
        }
        if(StrUtil.isNotBlank(user.getSalt())){
            queryWrapper.eq(User::getSalt, user.getSalt());
        }
        if(StrUtil.isNotBlank(user.getLoginIp())){
            queryWrapper.eq(User::getLoginIp, user.getLoginIp());
        }
        if(StrUtil.isNotBlank(user.getRemark())){
            queryWrapper.eq(User::getRemark, user.getRemark());
        }
        if(StrUtil.isNotBlank(user.getOpenid())){
            queryWrapper.eq(User::getOpenid, user.getOpenid());
        }
        if(StrUtil.isNotBlank(user.getOnlineStatus())){
            queryWrapper.eq(User::getOnlineStatus, user.getOnlineStatus());
        }
        //2. 执行分页查询
        Page<User> pagin = new Page<>(current , size , true);
        IPage<User> selectResult = userMapper.selectByPage(pagin , queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    public User insert(User user){
        userMapper.insert(user);
        return user;
    }

    /**
     * 更新数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    public User update(User user){
        //1. 根据条件动态更新
        LambdaUpdateChainWrapper<User> chainWrapper = new LambdaUpdateChainWrapper<User>(userMapper);
        if(StrUtil.isNotBlank(user.getLoginName())){
            chainWrapper.eq(User::getLoginName, user.getLoginName());
        }
        if(StrUtil.isNotBlank(user.getUserName())){
            chainWrapper.eq(User::getUserName, user.getUserName());
        }
        if(StrUtil.isNotBlank(user.getEmail())){
            chainWrapper.eq(User::getEmail, user.getEmail());
        }
        if(StrUtil.isNotBlank(user.getPhonenumber())){
            chainWrapper.eq(User::getPhonenumber, user.getPhonenumber());
        }
        if(StrUtil.isNotBlank(user.getAvatar())){
            chainWrapper.eq(User::getAvatar, user.getAvatar());
        }
        if(StrUtil.isNotBlank(user.getPassword())){
            chainWrapper.eq(User::getPassword, user.getPassword());
        }
        if(StrUtil.isNotBlank(user.getSalt())){
            chainWrapper.eq(User::getSalt, user.getSalt());
        }
        if(StrUtil.isNotBlank(user.getLoginIp())){
            chainWrapper.eq(User::getLoginIp, user.getLoginIp());
        }
        if(StrUtil.isNotBlank(user.getRemark())){
            chainWrapper.eq(User::getRemark, user.getRemark());
        }
        if(StrUtil.isNotBlank(user.getOpenid())){
            chainWrapper.eq(User::getOpenid, user.getOpenid());
        }
        if(StrUtil.isNotBlank(user.getOnlineStatus())){
            chainWrapper.eq(User::getOnlineStatus, user.getOnlineStatus());
        }
        //2. 设置主键，并更新
        chainWrapper.set(User::getUserId, user.getUserId());
        boolean ret = chainWrapper.update();
        //3. 更新成功了，查询最最对象返回
        if(ret){
            return queryById(user.getUserId());
        }else{
            return user;
        }
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    public boolean deleteById(Long userId){
        int total = userMapper.deleteById(userId);
        return total > 0;
    }
}