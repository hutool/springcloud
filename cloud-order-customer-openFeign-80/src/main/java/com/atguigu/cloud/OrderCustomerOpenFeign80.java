package com.atguigu.cloud;

import com.atguigu.rule.MyRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient  // 注册到eureka
@EnableFeignClients  // 启用OpenFeign服务调用
@RibbonClient(name = "cloud-payment-service",configuration = MyRule.class)   // 访问cloud-payment-service服务时，使用自定义的负载均衡算法
public class OrderCustomerOpenFeign80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderCustomerOpenFeign80.class, args);
    }
}
