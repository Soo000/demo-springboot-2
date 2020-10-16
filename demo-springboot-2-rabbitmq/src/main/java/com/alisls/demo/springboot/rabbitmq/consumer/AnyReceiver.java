package com.alisls.demo.springboot.rabbitmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 描述信息
 *
 * @author Ke Wang
 * @date 2020/10/14
 */
@Component
@Slf4j
public class AnyReceiver {

    @RabbitListener(
            bindings = @QueueBinding(value = @Queue(), // 注意这里不要定义队列名称，系统会随机产生
                exchange = @Exchange(value = "fanoutExchange", type = ExchangeTypes.FANOUT))
    )
    public void process(String payload) {
        log.info("接收到消息：{}", payload);
    }

}
