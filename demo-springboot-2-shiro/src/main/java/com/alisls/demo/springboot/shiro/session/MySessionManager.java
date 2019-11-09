package com.alisls.demo.springboot.shiro.session;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 自定义 SessionManager
 */
public class MySessionManager extends DefaultWebSessionManager {

    private static final Logger logger = LoggerFactory.getLogger(MySessionManager.class);

    private static final String AUTHORIZATION = "Authorization";

    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    /**
     * 获取 SessionId
     * @param request
     * @param response
     * @return
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        // request 请求 header 中获取 sessionId
        String sessionId = WebUtils.toHttp(request).getHeader(AUTHORIZATION);

        if (sessionId != null) { // 如果从 request 请求 header 中获取 sessionId 不为空
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);

            logger.info("Session 管理器检测到 AUTHORIZATION token 使用此token作为 Session");
            return sessionId;
        } else { // 否则按照默认规则从 cookie 中获取 ssionId
            return super.getSessionId(request, response);
        }
    }
}
