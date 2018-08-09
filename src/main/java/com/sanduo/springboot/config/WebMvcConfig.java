package com.sanduo.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置springmvc
 * 
 * @author sanduo
 * @date 2018/07/31
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /* 简单的驶入处理器
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addViewControllers(org.springframework.web.servlet.config.annotation.ViewControllerRegistry)
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("/login");// 注册访问/login登录界面,跳转
    }

}
