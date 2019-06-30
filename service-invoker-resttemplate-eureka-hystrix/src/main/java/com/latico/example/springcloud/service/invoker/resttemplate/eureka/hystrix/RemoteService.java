package com.latico.example.springcloud.service.invoker.resttemplate.eureka.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @HystrixCommand(fallbackMethod = "serviceFailure")
    public String hello() {
        String result = restTemplate.getForObject("http://service-provider-eureka/hello", String.class);
        return "RestTemplateRibbon服务调用者结果:" + result;
    }

    /**
     * @return 如果执行失败，返回该信息
     */
    public String serviceFailure() {
        return "hello world service is not available !";
    }
}
