package com.sanduo.springboot.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 异步消息
 * 
 * @author sanduo
 * @date 2018/08/06
 */
@Configuration
public class MqConfig {

    // qmqp 队列
    @Bean
    public Queue queue() {
        return new Queue("my-queue");
    }
}
