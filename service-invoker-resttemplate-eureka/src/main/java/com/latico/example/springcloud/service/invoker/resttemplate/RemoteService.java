package com.latico.example.springcloud.service.invoker.resttemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @Author: LanDingDong
 * @Date: 2019-03-16 23:18
 * @Version: 1.0
 */
@Service
public class RemoteService {

    /**
     * 定义为私有可能会报错
     */
    @Autowired
    RestTemplate restTemplate;

    public String hello() {
        String result = restTemplate.getForObject("http://service-provider-eureka/hello", String.class);
        return "RestTemplateRibbon服务调用者结果:" + result;
    }
}
