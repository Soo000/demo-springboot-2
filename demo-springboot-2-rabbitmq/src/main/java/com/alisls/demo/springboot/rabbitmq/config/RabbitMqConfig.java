package com.alisls.demo.springboot.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    /**
     * 会在 rabbitmq 中创建队列 kinson
     * @return
     */
    @Bean
    public Queue kinsonQueue() {
        return new Queue("kinson");
    }

    /**
     * 会在 rabbitmq 中创建队列 kinson
     * @return
     */
    @Bean
    public Queue kinsonQueue2() {
        return new Queue("kinson2");
    }

    /**
     * 创建队列
     * @return
     */
    @Bean
    public Queue ackQueue() {
        return new Queue("ackQueue");
    }

    @Bean
    public Queue topicQueue() {
        return new Queue("topicQueue");
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

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    /**
     * 将队列绑定到交换器
     * @return
     */
    @Bean
    Binding topicBinding() {
        Binding binding = BindingBuilder.bind(topicQueue()).to(topicExchange()).with("topicQueue.#");
        return binding;

    }

    /**
     * 将队列绑定到交换器
     * @param ackQueue
     * @param fanoutExchange
     * @return
     */
    @Bean
    Binding fanoutBinding(Queue ackQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(ackQueue).to(fanoutExchange);
    }

}
