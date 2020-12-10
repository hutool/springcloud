package com.atguigu.cloud.controller;

import cn.hutool.core.util.IdUtil;
import com.atguigu.cloud.entities.Payment;
import com.atguigu.cloud.entities.Result;
import com.atguigu.cloud.service.PaymentHystrixService;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@DefaultProperties(defaultFallback = "fallbackMethod") //如果消费者端接口发生故障，则使用指定的全局服务降级方法
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

/********************* 服务降级 *********************************/

    @GetMapping("/1/customer/get/{id}")
    @HystrixCommand(fallbackMethod = "paymentFallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500") //峰值
    })
    public Result<Payment> getPaymentById1(@PathVariable("id") Long id){
        //int i = 10/0;
        return paymentHystrixService.getPaymentById(id);
    }
    //服务降级方法
    public Result<Payment> paymentFallbackMethod(@PathVariable("id") Long id){
        return new Result<>(400, "服务降级，我是消费者，支付服务系统繁忙，请稍后再试o(╥﹏╥)o");
    }

    @GetMapping("/2/customer/get/{id}")
    @HystrixCommand  //没有指定服务降级方法，会执行全局降级方法
    public Result<Payment> getPaymentById2(@PathVariable("id") Long id){
        //int i = 10/0;
        return paymentHystrixService.getPaymentById(id);
    }


/********************* 服务熔断 *********************************/

    @GetMapping("/3/customer/get/{id}")
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //开启断路器
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //时间窗口期:10s
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //最大请求次数
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), //请求失败率
            // 10秒内，请求次数超过10次，并且请求失败率达到60%，会开启断路器
    })
    public String getPaymentById3(@PathVariable("id") Long id){
        if (id < 0){
            throw new RuntimeException("***id 不能为负数");
        }
        // huTool工具类，随机获取一串唯一识别码，如82efe45f49b1445e98c4518a36
        String serialNum = IdUtil.simpleUUID();
        return "success " + serialNum;
    }
    // 服务降级方法
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Long id){
        return "id不能为负数，请稍后再试o(╥﹏╥)o";
    }



    // 全局降级方法
    public Result<Payment> fallbackMethod(){
        return new Result<>(400, "全局服务降级，我是消费者，支付服务系统繁忙，请稍后再试o(╥﹏╥)o");
    }
}
