package com.dy.client.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/index")
    public Object index(@AuthenticationPrincipal Authentication authentication,HttpServletRequest request){
    	String header = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(header, "bearer ");
        return token;
    }
    
    @GetMapping("/index1")
    public Object index1(){
        return "成功1111";
    }
}