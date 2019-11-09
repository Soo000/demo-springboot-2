package com.alisls.demo.springboot.redis.jpa.listener.event;

import org.springframework.context.ApplicationEvent;

public class InitRedisDataEvent extends ApplicationEvent {

    public InitRedisDataEvent(Object source) {
        super(source);
    }

}
