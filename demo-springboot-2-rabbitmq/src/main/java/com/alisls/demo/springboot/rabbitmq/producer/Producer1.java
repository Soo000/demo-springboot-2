package com.alisls.demo.springboot.rabbitmq.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

public class Producer1 {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 单条消息发送给单个队列，该队列只有一个消费者
     *
     * @return
     */
    @GetMapping(value = "send")
    public String send() {
        String content = "Date:" + System.currentTimeMillis();
        //发送默认交换机对应的的队列kinson
        amqpTemplate.convertAndSend("kinson", content);
        return content;
    }

}
