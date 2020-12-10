package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.Payment;
import com.atguigu.cloud.entities.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Controller
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

    private static final String PAYMENT_URL = "http://cloud-payment-service";

    @GetMapping("/customer/payment/save")
    @ResponseBody
    public Result<Payment> save(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/service/payment/", payment, Result.class);
    }

    @GetMapping("/customer/payment/get/{id}")
    @ResponseBody
    public Result<Payment> getPayment(@PathVariable Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/service/payment/"+id, Result.class);
    }
}
