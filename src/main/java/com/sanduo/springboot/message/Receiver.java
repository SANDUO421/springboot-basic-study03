package com.sanduo.springboot.message;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 消息监听者
 * 
 * @author sanduo
 * @date 2018/08/06
 */
@Component
public class Receiver {

    @JmsListener(destination = "my-destination")
    public void receiverMsg(String msg) {
        System.err.println("接收到的消息：<" + msg + ">");
    }

}
