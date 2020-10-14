package com.alisls.demo.springboot.rabbitmq.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发送消息到Fanout类型的交换器
 *
 * @author Ke Wang
 * @date 2020/10/14
 */
@RestController
@RequestMapping("/fanout")
public class FanoutController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RequestMapping("/sendMessage")
    public String sendMessage(@RequestParam String message) {
        amqpTemplate.convertAndSend("fanoutExchange", null, message);
        return "ok";
    }

}
