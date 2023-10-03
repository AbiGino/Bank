package com.example.bankApplication.CustomConfiguration;

import com.example.bankApplication.Utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {


    @Autowired
    RequestMeta requestMeta;

    @Autowired
    JwtUtils jwtUtils;

    public JwtInterceptor(RequestMeta requestMeta) {

        this.requestMeta = requestMeta;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String token=request.getHeader("Authorization");
        String JwtToken = null;
        if(token!=null && token.startsWith("Bearer")){
            JwtToken=token.substring(7,token.length());
        }
        if(!((request.getRequestURI().contains("/api/customer/login")) || (request.getRequestURI().contains("/api/customer/getcustomer/{account_number}")))){
            Claims claims=jwtUtils.verify(JwtToken);
            System.out.println("claims : "+claims);
            if(claims.get("name") != null){
                requestMeta.setName(claims.get("name").toString());
            }
            if (claims.get("phone_number") != null) {
                requestMeta.setPhone_number(claims.get("phone_number").toString());
            }
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
