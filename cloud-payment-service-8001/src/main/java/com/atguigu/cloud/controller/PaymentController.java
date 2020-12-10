package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.Payment;
import com.atguigu.cloud.entities.Result;
import com.atguigu.cloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @GetMapping("/service/payment/{id}")
    @ResponseBody
    public Result<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        if(payment != null){
            return new Result<>(200, "查询成功"+port, payment);
        }else {
            return new Result<>(400, "查询失败"+port);
        }
    }

    @PostMapping("/service/payment")
    @ResponseBody
    public Result<Integer> save(@RequestBody Payment payment){
        int count = paymentService.save(payment);
        if(count > 0){
            return new Result<>(200, "保存成功"+port, count);
        }else{
            return new Result<>(400, "保存失败"+port, count);
        }
    }
}
