package com.latico.example.springcloud.server.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;

@RestController
@SessionAttributes("authorizationRequest")
public class LoginController {
 
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("loginSucc")
    public String loginSucc(){
        return "登陆成功";
    }
}