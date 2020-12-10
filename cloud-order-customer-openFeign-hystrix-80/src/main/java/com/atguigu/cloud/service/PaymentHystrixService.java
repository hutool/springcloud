package com.atguigu.cloud.service;

import com.atguigu.cloud.entities.Payment;
import com.atguigu.cloud.entities.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

// 服务调用接口
@Component
@FeignClient(value = "cloud-payment-service", fallback = PaymentFallBackService.class)
public interface PaymentHystrixService {

    @GetMapping("/service/payment/{id}")
    Result<Payment> getPaymentById(@PathVariable("id") Long id);

    @PostMapping("/service/payment")
    Result<Integer> save(@RequestBody Payment payment);
}
