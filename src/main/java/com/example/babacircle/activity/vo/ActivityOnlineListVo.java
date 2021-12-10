package com.example.babacircle.activity.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author JC
 * @date 2021/10/6 11:43
 */
@Data
public class ActivityOnlineListVo {

    /**
     * 线上活动id
     */
    private int id;

    /**
     * 封面
     */
    private String cover;

    /**
     * 标题
     */
    private String title;

    /**
     * 活动发起人id
     */
    private int initiatorUserId;

    /**
     * 活动发起人姓名
     */
    private String userName;

    /**
     * 库存
     */
    private int stock;

    /**
     * 结束时间
     */
    private String finishTime;

    /**
     * 活动参与人数
     */
    private int participateNum;

    /**
     * 活动等级
     */
    private int activityLevel;

    /**
     * 状态 0.进行中  1.已结束
     */
    private int isStatus;
}
