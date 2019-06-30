package com.latico.example.springcloud.server.config.springcloud.bus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * <PRE>
 *  刷新消息总线
 * </PRE>
 * @Author: LanDingDong
 * @Date: 2019-04-01 15:22:35
 * @Version: 1.0
 */
@RestController
@Configuration
public class ConfigRefreshFeignController {

    @Value("${config.bus.server.host}")
    String configServerHost;

    @Value("${config.bus.server.port}")
    String configServerPort;

    /**
     * 刷新接口
     * @return
     */
    @RequestMapping(value = "refreshConfig", method = {RequestMethod.GET, RequestMethod.POST})
    public String refreshConfig() {
        RestTemplate restTemplate = new RestTemplate();
        String result = null;
        try {
            String url = "http://" + configServerHost + ":" + configServerPort + "/actuator/bus-refresh";
            System.out.println("POST请求URL：" + url);
            Map<String, Object> map = new HashMap<>();
            result = restTemplate.postForObject(url, map, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return "RestTemplateRibbon服务调用者结果异常:" + e.getMessage();
        }

        return "RestTemplateRibbon服务调用成功，返回结果:" + result;
    }
}