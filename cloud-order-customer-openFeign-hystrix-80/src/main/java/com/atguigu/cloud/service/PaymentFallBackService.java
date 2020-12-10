package com.atguigu.cloud.service;

import com.atguigu.cloud.entities.Payment;
import com.atguigu.cloud.entities.Result;
import org.springframework.stereotype.Component;

// 如果服务提供者端发生了故障，对它进行服务降级处理
@Component
public class PaymentFallBackService implements PaymentHystrixService {

    @Override
    public Result<Payment> getPaymentById(Long id) {
        return new Result<>(400, "全局服务降级：当前系统繁忙，请稍后再试");
    }

    @Override
    public Result<Integer> save(Payment payment) {
        return new Result<>(400, "全局服务降级：当前系统繁忙，请稍后再试");
    }
}
