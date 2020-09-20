package com.alisls.demo.springboot.websocket.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 文本消息处理器
 *
 * @author Ke Wang
 * @date 2020/9/13
 */
@Slf4j
public class MyTextMessageHandler extends TextWebSocketHandler {

    /**
     * 客户端容器
     */
    private Map<String, WebSocketSession> clients = new ConcurrentHashMap<>();

    /**
     * 建立连接之后
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String jid = (String) session.getAttributes().get("jid");

        String msg = "客服端(" + jid + ")连接成功";
        log.info(msg);
        session.sendMessage(new TextMessage(msg));
    }

    /**
     * 处理文本消息
     *
     * @param session 当前发送消息用户的连接
     * @param message 发送的消息
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("执行处理消息方法");
    }

    /**
     * 关闭连接之后
     *
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("客户端{session}连接已经断开", session);
    }

}
