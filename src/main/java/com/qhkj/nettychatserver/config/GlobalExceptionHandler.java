package com.qhkj.nettychatserver.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qhkj.nettychatserver.config.http.HttpResult;
import com.qhkj.nettychatserver.config.http.HttpResultGenerator;
import com.qhkj.nettychatserver.config.http.HttpStatusEnum;
import org.springframework.core.MethodParameter;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.qhkj.nettychatserver.config.http.exception.HttpException;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义异常处理类
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    //处理自定义异常
    @ExceptionHandler(value = HttpException.class)
    public HttpResult baseExceptionHandler(HttpException e) {
        log.error("发生业务异常！原因是：{}" , e.getMessage());
        return HttpResultGenerator.fail(HttpStatusEnum.SERVER_BUSY);
    }

    //处理数据库异常
    @ExceptionHandler(value = DataAccessException.class)
    public HttpResult dbExceptionHandler(DataAccessException e) {
        log.error("发生数据库异常！原因是：{}" , e.getMessage());
        return HttpResultGenerator.fail(HttpStatusEnum.DB_ERROR);
    }

    //处理空指针异常
    @ExceptionHandler(value = NullPointerException.class)
    public HttpResult exceptionHandler(Exception e) {
        log.error("发生空指针异常！原因是：{}" , e);
        return HttpResultGenerator.fail(HttpStatusEnum.INTERNAM_SERVER_ERROR);
    }

}
