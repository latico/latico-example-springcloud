package com.latico.example.springcloud.service.invoker.resttemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * <PRE>
 *  spring自带的请求客户端工具
 * </PRE>
 * @Author: latico
 * @Date: 2019-03-10 17:15:03
 * @Version: 1.0
 */
@Service
public class RestTemplateService {
    @Autowired
    private RestTemplateBuilder builder;

    /**
     * @return 返回一个负载均衡的客户端工具
     */
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return builder.build();
    }
}