package com.sanduo.springboot.message;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;

/**
 * 生产消息-消息定义
 * 
 * @author sanduo
 * @date 2018/08/06
 */
public class JmsMsg implements MessageCreator {

    /* 生产消息
     * @see org.springframework.jms.core.MessageCreator#createMessage(javax.jms.Session)
     */
    @Override
    public Message createMessage(Session session) throws JMSException {

        return session.createTextMessage("测试消息！");
    }

}
