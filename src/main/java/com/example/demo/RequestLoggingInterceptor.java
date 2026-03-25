package com.example.demo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component 
public class RequestLoggingInterceptor implements HandlerInterceptor //CREATED INTERCEPTOR CLASS
{
    // Before request hits controller
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        System.out.println("Incoming Request → " + request.getMethod() + " " + request.getRequestURI());

        return true;
    }

    // After response is sent
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        System.out.println("Completed Request → Status: " + response.getStatus());
    }
}