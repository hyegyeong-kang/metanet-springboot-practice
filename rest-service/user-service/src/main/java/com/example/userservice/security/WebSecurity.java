package com.example.userservice.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception { // 권한과 관련된 메소드
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/users").permitAll(); // users 로 들어오는 애는 예외적으로 허락해줘 , 얘 말고는 로그인하도록 유도해
        http.authorizeRequests().antMatchers("/h2-console/**").permitAll();

        http.headers().frameOptions().disable();

    }
}
