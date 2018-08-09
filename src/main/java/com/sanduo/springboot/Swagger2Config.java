package com.sanduo.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 的配置类，必须与启动类同一级目录
 * 
 * @author sanduo
 * @date 2018/07/26
 */
@Configuration
@EnableSwagger2 // 开启swagger
public class Swagger2Config {

    @Bean
    public Docket createSwaggerApi() {

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
            .apis(RequestHandlerSelectors.basePackage("com.sanduo.springboot.controller")).paths(PathSelectors.any())
            .build();
    }

    /**
     * api详情
     * 
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("").description("").termsOfServiceUrl("服务访问地址")
            .contact(new Contact("联系人", "地址", "email")).version("v1.0.0").build();
    }

}
