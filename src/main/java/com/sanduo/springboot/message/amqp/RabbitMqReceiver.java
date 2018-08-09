package com.sanduo.springboot.message.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * RabbitMq的接收者
 * 
 * @author sanduo
 * @date 2018/08/06
 */
@Component
public class RabbitMqReceiver {

    @RabbitListener(queues = "my-queue")
    public void receiverMsg(String msg) {
        System.err.println("Receive RabbitMQ <" + msg + ">");
    }
}
