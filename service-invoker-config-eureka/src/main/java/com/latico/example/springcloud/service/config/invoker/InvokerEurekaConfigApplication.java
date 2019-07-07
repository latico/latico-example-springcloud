package com.latico.example.springcloud.service.config.invoker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * <PRE>
 *  程序入口
 * </PRE>
 * @Author: latico
 * @Date: 2019-03-13 22:03:56
 * @Version: 1.0
 */
@SpringBootApplication(scanBasePackages={"com.latico.example.springcloud"})
//@MapperScan(basePackages = {"com.latico.archetype.springboot.dao.mapper"})
@ServletComponentScan
public class InvokerEurekaConfigApplication {

    /**
     * 服务器启动
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(InvokerEurekaConfigApplication.class, args);
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
