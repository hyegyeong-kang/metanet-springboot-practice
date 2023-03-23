package com.example.userservice.config;

import com.example.userservice.filter.MyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class FilterConfig {
    @Autowired
    private Environment env;

    @Bean
    public FilterRegistrationBean<MyFilter> filter1() {
        FilterRegistrationBean<MyFilter> bean = new FilterRegistrationBean<>(new MyFilter(env));

        bean.addUrlPatterns("/user2/*");  // 토큰 가져왔는지 체크하겠다.
        bean.setOrder(0); // 우선순위 첫번째로

        return bean;
    }
}
