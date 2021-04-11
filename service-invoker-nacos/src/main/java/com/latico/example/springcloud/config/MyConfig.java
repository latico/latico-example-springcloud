package com.latico.example.springcloud.config;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.UrlCleaner;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @author latico
 * @version 1.0.0
 * @date 2021-04-09 15:38
 */
@Configuration
public class MyConfig {
    @LoadBalanced
    @Bean
    @SentinelRestTemplate(urlCleanerClass = UrlCleanerImpl.class, urlCleaner = "clean")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @LoadBalanced
    @Bean
    @SentinelRestTemplate
    public RestTemplate restTemplate1() {
        return new RestTemplate();
    }

}
