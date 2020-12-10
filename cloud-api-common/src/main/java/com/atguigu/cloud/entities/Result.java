package com.atguigu.cloud.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

    private int code;
    private String massage;
    private T data;

    public Result(){}

    public Result(int code, String massage){
        this.code = code;
        this.massage = massage;
        this.data = null;
    }

    public Result (int code, String massage, T data){
        this.code = code;
        this.massage = massage;
        this.data = data;
    }
}
