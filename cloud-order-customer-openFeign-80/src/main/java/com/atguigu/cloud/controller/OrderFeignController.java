package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.Payment;
import com.atguigu.cloud.entities.Result;
import com.atguigu.cloud.service.OrderFeignService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class OrderFeignController {

    @Resource
    private OrderFeignService orderFeignService;

    @GetMapping("/customer/payment/get/{id}")
    @ResponseBody
    public Result<Payment> getPaymentById(@PathVariable("id") Long id){
        return orderFeignService.getPaymentById(id);
    }

    @GetMapping("/customer/payment/save")
    @ResponseBody
    public Result<Integer> save(Payment payment){
        return orderFeignService.save(payment);
    }
}
