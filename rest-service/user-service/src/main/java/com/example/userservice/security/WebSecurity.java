package com.example.userservice.security;

import com.example.userservice.service.AuthenticationFilter;
import com.example.userservice.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private Environment env;
    public WebSecurity(Environment env, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.env = env;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception { // 권한과 관련된 메소드
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/users").permitAll(); // users 로 들어오는 애는 예외적으로 허락해줘 , 얘 말고는 로그인하도록 유도해
        http.authorizeRequests().antMatchers("/h2-console/**").permitAll();


        http.authorizeRequests().antMatchers("/**")
                        .hasIpAddress("localhost")
                        .and()
                        .addFilter(getApplicationFilter()); // 특정 ip 로 들어오는건 이 필터를 거치게 해라

        http.headers().frameOptions().disable();

    }

    private AuthenticationFilter getApplicationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager());

        return authenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // 정말 인증을 위한 메소드
        // 준비한 것을 정말 인증 처리하는 메소드
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }
}
