package com.qhkj.nettychatserver.config.http.exception;

import com.qhkj.nettychatserver.config.http.HttpStatusEnum;
import lombok.Data;

@Data
public class HttpException extends RuntimeException{

    //错误码
    private int code;

    //错误信息
    private String message;

    // 默认构造函数
    public HttpException() {
        super();
    }

    public HttpException(HttpStatusEnum httpStatusEnum) {
        super(String.valueOf(httpStatusEnum.getCode()));
        this.code = httpStatusEnum.getCode();
        this.message = httpStatusEnum.getMessage();
    }

}
