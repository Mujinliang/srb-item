package com.nawiew.srb.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author nawiew
 * @packageName:com.nawiew.srb.gateway
 * @ClassName:ServiceGatewayApplication
 * @Description:
 * @date 2021/7/10 14:42
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceGatewayApplication.class,args);
    }
}
