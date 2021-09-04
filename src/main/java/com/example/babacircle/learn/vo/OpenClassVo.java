package com.example.babacircle.learn.vo;

import lombok.Data;

/**
 * @author MQ
 * @date 2021/5/7 15:33
 */
@Data
public class OpenClassVo {

    private int id;

    /**
     * 发布人用户名
     */
    private String userName;

    /**
     * 发布人id
     */
    private int userId;

    /**
     * 标题
     */
    private String title;

    /**
     * 封面图
     */
    private String coverImg;

    /**
     * 视频
     */
    private String classList;

    /**
     * 公开课价格 0为免费内容
     */
    private int price;

    /**
     * 课程详情介绍
     */
    private String description;

    /**
     * 免费观看时长（默认为0）
     */
    private int freeTime;

    /**
     * 公开课购买人数
     */
    private int buyerNum;

    /**
     * 发布时间
     */
    private String createAt;
}
