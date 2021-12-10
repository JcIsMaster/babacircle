package com.example.babacircle.virtual.vo;

import lombok.Data;

/**
 * @author JC
 * @date 2021/11/18 16:05
 */
@Data
public class VirtualAccountVo {

    /**
     *  用户ID
     */
    private int id ;

    /**
     * 用户名称
     */
    private String userName ;

    /**
     * 性别
     * 1男 0女
     */
    private int userSex;

    /**
     * 介绍
     */
    private String introduce;
}
