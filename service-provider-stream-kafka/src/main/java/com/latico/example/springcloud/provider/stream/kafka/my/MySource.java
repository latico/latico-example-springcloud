package com.latico.example.springcloud.provider.stream.kafka.my;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MySource {

    @Output("myOutput")
    MessageChannel myOutput();

}