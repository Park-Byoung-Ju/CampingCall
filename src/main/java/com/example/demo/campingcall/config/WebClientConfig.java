package com.example.demo.campingcall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class WebClientConfig { // 파라미터를 인코딩해서 생기는 문제를 방지
	@Bean
    public DefaultUriBuilderFactory builderFactory(){
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory();
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);
        return factory;
    }

    @Bean
    public WebClient webClient(){
        return WebClient.builder()
                .uriBuilderFactory(builderFactory())
                .build();
    }
}
