package com.sanduo.springboot.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * 消息提供者
 * 
 * @author sanduo
 * @date 2018/08/06
 */
@Component
public class JmsProvider implements CommandLineRunner {// springboot 提供的CommandLineRunner接口，用户程序启动后执行的代码

    @Autowired
    JmsTemplate jmsTemplate;// springboot 已经配置好了，直接注入

    /* 消息发送目的地定义
     * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
     */
    @Override
    public void run(String... args) throws Exception {
        jmsTemplate.send("my-destination", new JmsMsg());// 发送目的地

    }

}
