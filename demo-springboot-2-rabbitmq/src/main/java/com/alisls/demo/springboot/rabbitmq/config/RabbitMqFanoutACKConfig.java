package com.alisls.demo.springboot.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqFanoutACKConfig {

    @Bean
    public Queue ackQueue() {
        return new Queue("ackQueue");
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingAckQueue2Exchange(Queue ackQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(ackQueue).to(fanoutExchange);
    }

}
