package com.example.desafio_valcann.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
public class ControllerInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(ControllerInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestId = UUID.randomUUID().toString();
        MDC.put("requestId", requestId);
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);

        log.info("[{}] Request started: {} {}", requestId, request.getMethod(), request.getRequestURI());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            String requestId = MDC.get("requestId");
            Object startTimeAttr = request.getAttribute("startTime");

            if (startTimeAttr instanceof Long startTime) {
                long executionTime = System.currentTimeMillis() - startTime;

                log.info("[{}] Request completed: {} {} - Status: {} - Time: {}ms",
                        requestId, request.getMethod(), request.getRequestURI(), response.getStatus(), executionTime);
            }
        } finally {
            MDC.clear();
        }
    }
}
