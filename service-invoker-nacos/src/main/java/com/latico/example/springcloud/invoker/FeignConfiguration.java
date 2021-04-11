package com.latico.example.springcloud.invoker;

import com.latico.example.springcloud.invoker.EchoServiceFallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

	@Bean
	public EchoServiceFallback echoServiceFallback() {
		return new EchoServiceFallback();
	}

}