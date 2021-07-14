package com.nawiew.srb.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author nawiew
 * @packageName:com.nawiew.srb.core
 * @ClassName:ServiceCoreApplication
 * @Description:
 * @date 2021/7/2 19:22
 */
@EnableFeignClients
@SpringBootApplication
@ComponentScan({"com.nawiew.srb","com.nawiew.common"})
public class ServiceCoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCoreApplication.class,args);
    }
}
