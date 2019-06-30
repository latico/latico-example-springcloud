package com.latico.example.springcloud.service.invoker.feign;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * <PRE>
 * 程序启动入口类
 * 在Spring Boot启动类上用@EnableHystrixDashboard注解和@EnableCircuitBreaker注解。
 * 需要特别注意的是我们之前的Feign服务由于内置断路器支持， 所以没有@EnableCircuitBreaker注解，
 * 但要使用Dashboard则必须加，如果不加，Dashboard无法接收到来自Feign内部断路器的监控数据，
 * 会报“Unable to connect to Command Metric Stream”错误
 * </PRE>
 * @Author: LanDingDong
 * @Date: 2019-03-18 17:24:40
 * @Version: 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.latico.example.springcloud"})
//@MapperScan(basePackages = {"com.latico.archetype.springboot.dao.mapper"})
@ServletComponentScan
@EnableEurekaClient
@EnableDiscoveryClient
//自带负载均衡
@EnableFeignClients

//在Spring Boot启动类上用@EnableHystrixDashboard注解和@EnableCircuitBreaker注解。需要特别注意的是我们之前的Feign服务由于内置断路器支持， 所以没有@EnableCircuitBreaker注解，但要使用Dashboard则必须加，如果不加，Dashboard无法接收到来自Feign内部断路器的监控数据，会报“Unable to connect to Command Metric Stream”错误
//@EnableCircuitBreaker
//@EnableHystrix
public class InvokerEurekaApplicationStarter {

    /**
     * 服务器启动
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(InvokerEurekaApplicationStarter.class, args);
        //开始处理业务流程
        startProcessService();
    }

    private static void startProcessService() {
        System.out.println("开始处理业务");
        int i = 0;

        while (i++ <= 5) {
            System.out.println("业务正在处理");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("处理业务完成");
    }

    /**
     * springboot2.0版本以上，需要添加此Servlet，
     * http://localhost:9100/actuator/hystrix.stream
     * @return
     */
    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
