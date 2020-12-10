package com.atguigu.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Ribbon客户端负载均衡配置
@Configuration
public class MyRule {

    // 轮询（默认）
//    @Bean
//    public IRule RoundRobbinRule(){
//        return new RoundRobinRule();
//    }

    // 随机
    @Bean
    public IRule RandomRule(){
        return new RandomRule();
    }

    // 先按照轮询策略获取服务，如果获取失败则在指定时间内进行“重试”获取可用的服务
//    @Bean
//    public IRule RetryRule(){
//        return new RetryRule();
//    }


}
