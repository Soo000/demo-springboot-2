package com.alisls.demo.springboot.rabbitmq.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发送消息到Topic类型的交换器
 *
 * @author Ke Wang
 * @date 2020/10/14
 */
@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RequestMapping("/sendMessage")
    public String sendMessage(@RequestParam String routingKey, @RequestParam String message) {
        amqpTemplate.convertAndSend("topicExchange", routingKey, message);
        return "ok";
    }

}
