package com.qhkj.nettychatserver.constant;

import cn.hutool.core.bean.copier.CopyOptions;

/**
 * 常常量
 */
public interface Common {

    String USER_SALT = "123456";

    CopyOptions options = CopyOptions.create()
            .setIgnoreNullValue(true)  // 忽略源对象属性为空的情况
            .setIgnoreError(true);     // 忽略复制过程中出现的错误

}
