package com.svc.myproject.stat;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class Statistic implements HandlerInterceptor {
    private final StatService statService;

    public Statistic(StatService statService) {
        this.statService = statService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        statService.incRequestCount();
        return true;
    }
}
