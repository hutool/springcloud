package com.atguigu.cloud.service;

import com.atguigu.cloud.entities.Payment;

public interface PaymentService {

    Payment getPaymentById(Long id);

    int save(Payment payment);
}
