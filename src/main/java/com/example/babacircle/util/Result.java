package com.example.babacircle.util;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回的结果封装
 * @Author MQ
 * @Date 2019/8/20
 * @Version 1.0
 */
@Data
public class Result<T> implements Serializable {

    /**
     * 状态码
     */
    private int code;

    /**
     * 结果描述
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    public Result(){
    }

    public Result(int code, String msg) {
        this(code,msg,null);
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }








}
