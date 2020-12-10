package com.atguigu.cloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// openFeign的日志配置
@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel(){

//        return Logger.Level.NONE;       // 默认，不显示任何日志

//        return Logger.Level.BASIC;      // 仅记录请求方法、URL、响应状态码、执行时间

//        return Logger.Level.HEADERS;    // 除了BASIC中定义的信息之外，还要请求头和响应头的信息

        return Logger.Level.FULL;       // 除了HEADERS中定义的信息之外，还有请求和响应的正文及元数据

    }
}
