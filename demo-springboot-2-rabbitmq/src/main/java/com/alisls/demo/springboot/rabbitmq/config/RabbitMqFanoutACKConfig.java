package com.alisls.demo.springboot.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqFanoutACKConfig {

    /**
     * 创建队列
     * @return
     */
    @Bean
    public Queue ackQueue() {
        return new Queue("ackQueue");
    }

    /**
     * 创建交换器
     * 也可以 new DirectExchange(), new TopicExchange()
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    /**
     * 将队列绑定到交换器
     * @param ackQueue
     * @param fanoutExchange
     * @return
     */
    @Bean
    Binding bindingAckQueue2Exchange(Queue ackQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(ackQueue).to(fanoutExchange);
    }

}
