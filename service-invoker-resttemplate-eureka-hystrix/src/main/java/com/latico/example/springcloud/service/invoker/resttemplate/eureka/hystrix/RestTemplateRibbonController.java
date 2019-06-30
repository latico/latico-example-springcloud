package com.latico.example.springcloud.service.invoker.resttemplate.eureka.hystrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTemplateRibbonController {

    @Autowired
    RemoteService remoteService;

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello() {
        return this.remoteService.hello();
    }
}