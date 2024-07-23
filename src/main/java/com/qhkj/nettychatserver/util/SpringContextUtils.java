package com.qhkj.nettychatserver.util;

import org.springframework.context.ApplicationContext;

public class SpringContextUtils {

    public static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return SpringContextUtils.applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtils.applicationContext = applicationContext;
    }

    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    public static Object getBean(Class<?> requiredType) {
        return getApplicationContext().getBean(requiredType);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
}

