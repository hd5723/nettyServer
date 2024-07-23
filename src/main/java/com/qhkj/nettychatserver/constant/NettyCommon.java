package com.qhkj.nettychatserver.constant;

import cn.hutool.core.bean.copier.CopyOptions;
import com.qhkj.nettychatserver.util.SnowflakeIdWorker;

public class NettyCommon {
    public static volatile SnowflakeIdWorker idWorker = null;

    public static CopyOptions options = CopyOptions.create()
            .setIgnoreNullValue(true)  // 忽略源对象属性为空的情况
            .setIgnoreError(true);     // 忽略复制过程中出现的错误

    public static synchronized SnowflakeIdWorker getIdWorker() {
        if (null == idWorker) {
            idWorker = new SnowflakeIdWorker(0, 0);
        }
        return idWorker;
    }
}
