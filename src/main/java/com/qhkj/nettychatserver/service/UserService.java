package com.qhkj.nettychatserver.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qhkj.nettychatserver.bean.domain.User;

/**
 * 用户信息表;(t_user)表服务接口
 * @author : http://www.chiner.pro
 * @date : 2024-7-23
 */
public interface UserService{

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    User queryById(Long userId);

    User queryByLoginNameOrPhone(String loginName, String phone) ;

    /**
     * 通过 loginName 查询单条数据
     *
     * @param loginName
     * @return 实例对象
     */
    User queryByLoginName(String loginName);


    /**
     * 通过 phone 查询单条数据
     *
     * @param phone
     * @return 实例对象
     */
    User queryByPhone(String phone);

    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<User> paginQuery(User user, long current, long size);
    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);
    /**
     * 更新数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);
    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(Long userId);
}