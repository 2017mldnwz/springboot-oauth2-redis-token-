package com.dy.client.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private TokenStore redisTokenStore;
    @GetMapping("/index")
    public Object index(@AuthenticationPrincipal Authentication authentication,HttpServletRequest request){
    	String header = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(header, "bearer ");
        // 通过token在redis里获取用户信息，从而验证用户。
        OAuth2Authentication authen=null;
        authen=redisTokenStore.readAuthentication(token);
        Map<String, Object> map = new HashMap<>();
        map.put("user", authen.getPrincipal());
        map.put("authorities", authen.getAuthorities());
        /**
         * map格式
         * {
			    "user": {
			        "password": null,
			        "username": "122",
			        "authorities": [
			            {
			                "authority": "admin"
			            }
			        ],
			        "accountNonExpired": true,
			        "accountNonLocked": true,
			        "credentialsNonExpired": true,
			        "enabled": true
			    },
			    "authorities": [
			        {
			            "authority": "admin"
			        }
			    ]
			}
         */
        return map;
    }
    
    @GetMapping("/index1")
    public Object index1(){
        return "成功1111";
    }
}