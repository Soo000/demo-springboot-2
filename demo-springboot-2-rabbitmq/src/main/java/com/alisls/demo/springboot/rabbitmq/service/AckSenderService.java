package com.alisls.demo.springboot.rabbitmq.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class AckSenderService implements RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println("AckSender returnedMessage " + message.toString() + " === " + i + " === " + s1 + " === " + s2);
    }

    /**
     * 消息发送
     */
    public void send() {
        final String content = "现在时间是" + LocalDateTime.now(ZoneId.systemDefault());

        //设置返回回调
        rabbitTemplate.setReturnCallback(this);
        //设置确认回调
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                System.out.println("消息发送成功！");
            }
            else {
                System.out.println("消息发送失败，" + cause + correlationData.toString());
            }
        });
        rabbitTemplate.convertAndSend("ackQueue", content);
    }

}
