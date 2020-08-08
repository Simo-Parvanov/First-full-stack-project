package com.svc.myproject.config;

import com.svc.myproject.stat.Statistic;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebConfig implements WebMvcConfigurer {
    private final Statistic statistic;

    public WebConfig(Statistic statistic) {
        this.statistic = statistic;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(statistic);
    }
}
