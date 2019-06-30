package com.latico.example.springcloud.invoker.stream.kafka;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.ServiceActivator;

@EnableBinding(Processor.class)
public class TransFormService {


    /**
     * 指定输入输出
     * @param payload
     * @return
     */
    @ServiceActivator(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
    public Object transform(Object payload) {
        System.out.println("消息中转站：" + payload);
        payload = "消息中转站处理过的:" + payload;
        return payload;
    }

}