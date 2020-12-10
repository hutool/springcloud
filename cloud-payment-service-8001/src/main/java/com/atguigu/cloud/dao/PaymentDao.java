package com.atguigu.cloud.dao;

import com.atguigu.cloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentDao {

    Payment getPaymentById(Long id);

    int save(Payment payment);
}
