package com.latico.example.springcloud.server.config.springcloud.bus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * <PRE>
 * 程序启动入口类
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
@ServletComponentScan
public class ConfigRefreshClientApplication {

    /**
     * 服务器启动
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ConfigRefreshClientApplication.class, args);
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
