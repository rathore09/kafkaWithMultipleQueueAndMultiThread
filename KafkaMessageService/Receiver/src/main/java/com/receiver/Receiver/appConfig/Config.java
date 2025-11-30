package com.receiver.Receiver.appConfig;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.*;

import java.util.concurrent.Executor;

@Configuration
public class Config {

    private static final Logger logger = LoggerFactory.getLogger(Config.class);

    @Bean
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
        threadPoolExecutor.setCorePoolSize(10);
        threadPoolExecutor.setMaxPoolSize(20);
        threadPoolExecutor.setThreadNamePrefix("Kafka-");
        threadPoolExecutor.setQueueCapacity(100);
        return threadPoolExecutor;
    }


}
