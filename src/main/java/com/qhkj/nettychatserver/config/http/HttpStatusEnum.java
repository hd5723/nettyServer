package com.qhkj.nettychatserver.config.http;

/**
 * Http状态码
 */
public enum HttpStatusEnum implements HttpStatusInfoInterface{

    //定义状态枚举值
    SUCCESS(200 , "成功!"),
    BODY_NOT_MATCH(400 , "数据格式不匹配!"),
    NOT_FOUND(404 , "访问资源不存在!"),
    INTERNAM_SERVER_ERROR(500 , "服务器内部错误!"),
    SERVER_BUSY(503 , "服务器正忙，请稍后再试!"),
    DB_ERROR(600 , "数据库异常，请检查入参!"),
    REQUEST_METHOD_SUPPORT_ERROR(10001 , "当前请求方法不支持!"),
    REQUEST_DATA_NULL(10002 , "当前请求参数为空!"),
    USER_NOT_EXISTS(10003 , "该用户不存在!"),
    USER_INVALID(10004 , "当前登录信息已生效，请重新登录!"),
    PASSWORD_ERROR(10005 , "密码错误!"),
    USER_NAME_LOCK(10006 , "该账号已被锁定!");

    //状态码
    private int code;

    //提示信息
    private String message;

    //构造方法
    HttpStatusEnum(int code , String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
