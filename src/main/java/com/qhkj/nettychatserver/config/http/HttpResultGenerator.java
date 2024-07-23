package com.qhkj.nettychatserver.config.http;

public class HttpResultGenerator {

    //正常返回时调用方法
    public static HttpResult success(Object data) {
        return new HttpResult(HttpStatusEnum.SUCCESS.getCode() , "接口调用成功!" , data);
    }


    //失败时调用方法（入参是异常枚举）
    public static HttpResult fail(HttpStatusEnum httpStatusEnum) {
        return new HttpResult(httpStatusEnum.getCode() , httpStatusEnum.getMessage() , null);
    }

    //失败时调用方法（提供给GlobalExceptionHandler类使用）
    public static HttpResult fail(int code ,  String message) {
        return new HttpResult(code , message , null);
    }

}
