package com.latico.example.springcloud.service.zookeeper.invoker.resttemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestTemplateRibbonController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello() {
        String result = this.restTemplate.getForObject("http://service-provider-zookeeper/hello", String.class);
        return "RestTemplateRibbon服务调用者结果:" + result;
    }
}