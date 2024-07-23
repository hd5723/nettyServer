package com.qhkj.nettychatserver.config.http;

import lombok.Data;

/**
 * http 统一返回类
 * @param <T>
 */
@Data
public class HttpResult<T> {

    private Integer code;

    private String message;

    private T data;

    //构造方法
    public HttpResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    //默认构造函数
    public HttpResult() {
        super();
    }
}


