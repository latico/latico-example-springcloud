package com.latico.example.springcloud.provider.stream.kafka;

import com.latico.example.springcloud.provider.stream.kafka.my.MySendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    @Autowired
    private SendService sendService;

    @Autowired
    private MySendService mySendService;


    @RequestMapping("/send/{msg}")
    public void send(@PathVariable("msg") String msg) {
        System.out.println("SendService发送消息:" + msg);
        sendService.sendMsg(msg);
    }


    @RequestMapping("/mysend/{msg}")
    public void mysend(@PathVariable("msg") String msg) {
        System.out.println("MySendService发送消息:" + msg);
        mySendService.sendMsg(msg);
    }
}