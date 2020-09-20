package com.alisls.demo.springboot.websocket.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * 握手拦截器
 *
 * @author Ke Wang
 * @date 2020/9/20
 */
@Component
@Slf4j
public class MyHandshakeInterceptor implements HandshakeInterceptor {

    /**
     * 握手之前，返回true将建立连接，false将不会建立连接
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @param webSocketHandler
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse,
                                   WebSocketHandler webSocketHandler, Map<String, Object> attributes) throws Exception {
        log.info("开始握手");

        // 将用户id放入socket处理器的会话(WebSocketSession)中
        attributes.put("jid", "1001");

        return true;
    }

    /**
     * 握手之后
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @param webSocketHandler
     * @param e
     */
    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse,
                               WebSocketHandler webSocketHandler, Exception e) {
        log.info("握手完成");
    }

}
