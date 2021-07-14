package com.nawiew.srb.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author nawiew
 * @packageName:com.nawiew.srb.oss
 * @ClassName:ServiceOssApplication
 * @Description:
 * @date 2021/7/6 14:31
 */
@SpringBootApplication
@ComponentScan({"com.nawiew.srb","com.nawiew.common"})
public class ServiceOssApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOssApplication.class,args);
    }
}
