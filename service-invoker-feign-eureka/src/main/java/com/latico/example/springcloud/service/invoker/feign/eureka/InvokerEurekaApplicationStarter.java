package com.latico.example.springcloud.service.invoker.feign.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <PRE>
 *  程序启动入口类
 * </PRE>
 * @Author: LanDingDong
 * @Date: 2019-03-18 20:40:19
 * @Version: 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.latico.example.springcloud"})
//@MapperScan(basePackages = {"com.latico.archetype.springboot.dao.mapper"})
@ServletComponentScan
@EnableEurekaClient
@EnableFeignClients  //自带负载均衡
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

}
