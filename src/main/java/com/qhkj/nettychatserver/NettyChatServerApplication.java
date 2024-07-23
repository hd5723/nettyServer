package com.qhkj.nettychatserver;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.qhkj.nettychatserver.util.SpringContextUtils;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
public class NettyChatServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(NettyChatServerApplication.class, args);
        System.out.println("--------started--------");
        SpringContextUtils.setApplicationContext(context);
    }

}
