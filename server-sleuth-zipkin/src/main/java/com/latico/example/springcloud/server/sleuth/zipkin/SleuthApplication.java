package com.latico.example.springcloud.server.sleuth.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class SleuthApplication {

    /**
     * 服务器启动
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SleuthApplication.class, args);
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