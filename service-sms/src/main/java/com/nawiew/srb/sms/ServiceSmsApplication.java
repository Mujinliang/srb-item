package com.nawiew.srb.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author nawiew
 * @packageName:com.nawiew.srb.sms
 * @ClassName:ServiceSmsApplication
 * @Description:
 * @date 2021/7/5 20:22
 */
@EnableFeignClients
@SpringBootApplication
@ComponentScan({"com.nawiew.srb", "com.nawiew.common"})
public class ServiceSmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceSmsApplication.class, args);
    }
}