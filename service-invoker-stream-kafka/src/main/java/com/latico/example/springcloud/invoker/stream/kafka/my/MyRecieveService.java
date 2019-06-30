package com.latico.example.springcloud.invoker.stream.kafka.my;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

//消息接受端，stream给我们提供了Sink,Sink源码里面是绑定input的，要跟我们配置文件的imput关联的。
@EnableBinding(MySink.class)
public class MyRecieveService {

    @StreamListener(MySink.INPUT)
    public void recieve(Object payload){
        System.out.println("MySink收到kafka消息：" + payload);
    }

}