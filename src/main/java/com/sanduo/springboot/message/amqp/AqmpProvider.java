package com.sanduo.springboot.message.amqp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * rabbit Mq --产生消息
 * 
 * @author sanduo
 * @date 2018/08/06
 */
@Component
public class AqmpProvider implements CommandLineRunner {

    @Autowired
    RabbitTemplate rabbitTemplate;

    /* 产生消息
     * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
     */
    @Override
    public void run(String... args) throws Exception {
        // 向队列my-queue发送消息
        rabbitTemplate.convertAndSend("my-queue", "来自RabbitMQ的消息");
    }

}
