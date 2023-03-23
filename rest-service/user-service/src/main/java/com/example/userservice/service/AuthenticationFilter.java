package com.example.userservice.service;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.vo.RequestLogin;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.User;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UserService userService;
    private Environment env;
    public AuthenticationFilter(AuthenticationManager authenticationManager, UserService userService, Environment env) {
        super.setAuthenticationManager(authenticationManager);
        this.userService = userService;
        this.env = env;
    }

    @Override   // 인증 처리하기 전에 호출됨
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        // 인증되기 전까지의 사전에 해야하는 작업
        // 로그인에서 들어오는 데이터값 RequestLogin 객체로 변환해주는 역할
        try{
            RequestLogin creds = new ObjectMapper().readValue(request.getInputStream(),
                    RequestLogin.class);

            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                    creds.getEmail(), creds.getPassword(), new ArrayList<>()
            )); // 리턴형식에 맞게 변환

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // 인증이 성공되면 호출되는 메소드 / 그래서 여기서 JWT 처리해주는 것
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String userName = ((User)authResult.getPrincipal()).getUsername();
        //System.out.println("username:" + userName);

        UserDTO userDetail = userService.getUserDetailByEmail(userName); // 이거 다른 페이지 이동할 때 사용하면 됨

        String token = Jwts.builder()
                .setSubject(userDetail.getUserId())
                .setExpiration(new Date(System.currentTimeMillis() +
                        Long.parseLong(env.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
                .compact();

        response.addHeader("token", token);
        response.addHeader("userId", userDetail.getUserId());
    }
}
