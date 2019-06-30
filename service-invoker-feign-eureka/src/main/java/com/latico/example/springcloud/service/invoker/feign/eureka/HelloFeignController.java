package com.latico.example.springcloud.service.invoker.feign.eureka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Configuration
public class HelloFeignController {
    @Autowired
    private HelloFeignClient helloFeignClient;

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello() {
        return "Feign服务调用者结果:" + helloFeignClient.hello();
    }
}