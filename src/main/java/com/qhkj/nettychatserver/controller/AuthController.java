package com.qhkj.nettychatserver.controller;

import com.qhkj.nettychatserver.config.http.HttpResultGenerator;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qhkj.nettychatserver.config.http.HttpStatusEnum;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import com.qhkj.nettychatserver.bean.request.UserRequest;
import com.qhkj.nettychatserver.config.http.HttpResult;
import com.qhkj.nettychatserver.constant.NettyCommon;
import com.qhkj.nettychatserver.service.UserService;
import com.qhkj.nettychatserver.bean.domain.User;
import com.qhkj.nettychatserver.constant.Common;
import org.apache.commons.lang3.StringUtils;
import cn.hutool.core.bean.BeanUtil;
import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public HttpResult register(@RequestBody UserRequest request) {

        if(StringUtils.isEmpty(request.getLoginName())) {
            return HttpResultGenerator.fail(HttpStatusEnum.DB_ERROR.getCode(), "登录账号不能为空");
        }

        if(StringUtils.isEmpty(request.getPhonenumber())) {
            return HttpResultGenerator.fail(HttpStatusEnum.DB_ERROR.getCode(), "手机号不能为空");
        }

        if(null != userService.queryByLoginName(request.getLoginName())) {
            return HttpResultGenerator.fail(HttpStatusEnum.DB_ERROR.getCode(), "登录账号已存在，请更换");
        }

        if(null != userService.queryByPhone(request.getPhonenumber())) {
            return HttpResultGenerator.fail(HttpStatusEnum.DB_ERROR.getCode(), "手机号已存在，请更换");
        }

        Date now = new Date();
        User user = User.builder()
                .createTime(now)
                .modifyTime(now)
                .userId(NettyCommon.getIdWorker().nextId())
                .delFlag(0)
                .status(0)
                .onlineStatus("onlineStatus")
                .salt(Common.USER_SALT)
                .build();
        BeanUtil.copyProperties(request, user, Common.options);
        User newUser = userService.insert(user);
        if (null != newUser) {
            newUser.setPassword(null);
            return HttpResultGenerator.success(newUser);
        } else {
            return HttpResultGenerator.fail(HttpStatusEnum.DB_ERROR);
        }
    }

    @PostMapping("/login")
    public HttpResult login(@RequestBody UserRequest request) {

        if(StringUtils.isEmpty(request.getLoginName())) {
            return HttpResultGenerator.fail(HttpStatusEnum.DB_ERROR.getCode(), "登录账号不能为空");
        }

        if(StringUtils.isEmpty(request.getPhonenumber())) {
            return HttpResultGenerator.fail(HttpStatusEnum.DB_ERROR.getCode(), "手机号不能为空");
        }

        if(StringUtils.isEmpty(request.getPassword())) {
            return HttpResultGenerator.fail(HttpStatusEnum.DB_ERROR.getCode(), "密码不能为空");
        }

        User newUser = userService.queryByLoginNameOrPhone(request.getLoginName(), request.getPhonenumber());
        if(null == newUser) {
            return HttpResultGenerator.fail(HttpStatusEnum.USER_NOT_EXISTS);
        }

        if(!request.getPassword().equals(newUser.getPassword())) {
            return HttpResultGenerator.fail(HttpStatusEnum.PASSWORD_ERROR);
        }

        return HttpResultGenerator.success("登录成功");
    }
}
