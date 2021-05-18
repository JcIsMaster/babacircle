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
     * 收藏数量
     */
    private int collect;

    /**
     * 公开课购买人数
     */
    private int buyerNum;

    /**
     * 发布时间
     */
    private String createAt;
}
