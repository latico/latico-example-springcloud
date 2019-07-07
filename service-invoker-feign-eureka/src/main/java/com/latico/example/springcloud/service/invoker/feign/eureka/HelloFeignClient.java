package com.latico.example.springcloud.service.invoker.feign.eureka;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-03-10 17:20
 * @Version: 1.0
 */
@FeignClient("service-provider-eureka") //声明调用的服务名称，自带负载均衡
public interface HelloFeignClient {

    @RequestMapping(value = "hello")
    public String hello();
}
