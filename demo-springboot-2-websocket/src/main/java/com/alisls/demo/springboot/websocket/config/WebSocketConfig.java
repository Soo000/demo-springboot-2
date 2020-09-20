package com.alisls.demo.springboot.websocket.config;

import com.alisls.demo.springboot.websocket.handler.MyTextMessageHandler;
import com.alisls.demo.springboot.websocket.interceptor.MyHandshakeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;

/**
 * WebSocket配置类
 * 注解@EnableWebSocket表示启动WebSocket
 *
 * @author Ke Wang
 * @date 2020/9/13
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Resource
    private MyHandshakeInterceptor myHandshakeInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry
                // 添加处理器
                .addHandler(myTextMessageHandler(), "/mywebsocket")
                // 添加拦截器
                .addInterceptors(myHandshakeInterceptor)
                .setAllowedOrigins("*");
    }

    @Bean
    public MyTextMessageHandler myTextMessageHandler() {
        return new MyTextMessageHandler();
    }

}
