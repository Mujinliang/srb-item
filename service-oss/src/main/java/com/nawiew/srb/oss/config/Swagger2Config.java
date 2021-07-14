package com.nawiew.srb.oss.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author nawiew
 * @packageName:com.nawiew.srb.base.config
 * @ClassName:Swagger2Config
 * @Description:
 * @date 2021/7/2 20:22
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket apiConfig(){

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api")
                .apiInfo(apiInfo())
                .select()
                //只显示admin路径下的页面
                .paths(Predicates.and(PathSelectors.regex("/api/.*")))
                .build();

    }

    private ApiInfo apiInfo(){

        return new ApiInfoBuilder()
                .title("尚融宝-API文档")
                .description("本文档描述了尚融宝OSS接口")
                .version("1.0")
                .contact(new Contact("Nawiew", "http://nawiew.com", "238629@qq.com"))
                .build();
    }

}
















