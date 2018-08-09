package com.sanduo.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.sanduo.springboot.service.impl.CustomerService;

/**
 * 配置Security
 * 
 * @author sanduo
 * @date 2018/07/31
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {// 扩展spring Security
                                                                     // 需要继承WebSecurityConfigurerAdapter

    @Bean
    UserDetailsService customerService() {
        return new CustomerService();
    }

    // 认证
    /* 认证
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerService());// 添加自定义的认证
    }

    // 授权
    /* 授权
    * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
    */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.authorizeRequests().antMatchers("/", "/login").permitAll().anyRequest().authenticated()// 所有的请求需认证完之后才可以访问
            .and().formLogin().loginPage("/login").failureUrl("/login?error").permitAll()// 定制登录行为
            .and().logout().permitAll();// 定制退出行为
        */
        http.authorizeRequests().anyRequest().authenticated()// 所有的请求需认证完之后才可以访问
            .and().formLogin().loginPage("/login").failureUrl("/login?error").permitAll()// 定制登录行为
            .and().logout().permitAll();// 定制退出行为
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/static/**").anyRequest();// 不拦截静态的资源
    }

}
