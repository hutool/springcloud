package com.atguigu.cloud.service;

import com.atguigu.cloud.entities.Payment;
import com.atguigu.cloud.entities.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * OpenFeign服务调用
 * 在客户端提供一个接口，用于绑定服务端提供的接口。这样就可以在客户端直接调用该接口，从而达到调用服务端接口的作用
 */
@Component
@FeignClient("cloud-payment-service")      // 需要绑定哪个服务
public interface OrderFeignService {

    // 绑定该服务中的哪个接口
    @GetMapping("/service/payment/{id}")
    Result<Payment> getPaymentById(@PathVariable("id") Long id);

    @PostMapping("/service/payment")
    Result<Integer> save(@RequestBody Payment payment);
}
