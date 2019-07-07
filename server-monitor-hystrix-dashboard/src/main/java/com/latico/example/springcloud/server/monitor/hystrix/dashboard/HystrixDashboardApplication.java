package com.latico.example.springcloud.server.monitor.hystrix.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * <PRE>
 * 程序启动入口类
 * 在Spring Boot启动类上用@EnableHystrixDashboard注解和@EnableCircuitBreaker注解。
 * 需要特别注意的是我们之前的Feign服务由于内置断路器支持， 所以没有@EnableCircuitBreaker注解，
 * 但要使用Dashboard则必须加，如果不加，Dashboard无法接收到来自Feign内部断路器的监控数据，
 * 会报“Unable to connect to Command Metric Stream”错误
 * </PRE>
 * <B>项	       目：</B>
 * <B>技术支持：</B>latico (c)
 *
 * @author <B><a href="mailto:latico@qq.com"> latico </a></B>
 * @version <B>V1.0 2017年3月18日</B>
 * @since <B>JDK1.6</B>
 */
@SpringBootApplication(scanBasePackages = {"com.latico.example.springcloud"})
//@MapperScan(basePackages = {"com.latico.archetype.springboot.dao.mapper"})
//在Spring Boot启动类上用@EnableHystrixDashboard注解和@EnableCircuitBreaker注解。需要特别注意的是我们之前的Feign服务由于内置断路器支持， 所以没有@EnableCircuitBreaker注解，但要使用Dashboard则必须加，如果不加，Dashboard无法接收到来自Feign内部断路器的监控数据，会报“Unable to connect to Command Metric Stream”错误
@EnableHystrixDashboard
public class HystrixDashboardApplication {

    /**
     * 服务器启动
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardApplication.class, args);
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

}
