package com.stackroute.zuulapi.jwt.config;

import com.stackroute.zuulapi.jwt.filter.JWTFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean jwtFilter(){
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(new JWTFilter());
        filter.addUrlPatterns("/authentication/api/v1/sensitive");
        return filter;
    }
}
