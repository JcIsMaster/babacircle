package com.example.babacircle.virtual.vo;

import com.fasterxml.jackson.annotation.JsonRawValue;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author JC
 * @date 2021/11/20 10:27
 */
@Data
public class UserVirtualVo {

    /**
     *  用户ID
     */
    private int id ;

    /**
     * 用户名称
     */
    private String userName ;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 性别
     * 1男 0女
     */
    private int userSex;

    /**
     * 所在省
     */
    private String currProvince;

    /**
     * 所在省
     */
    private String city;

    /**
     * 所在省
     */
    private String county;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 介绍
     */
    private String introduce;

    /**
     * 是否是商家
     */
    private int isShops;

    /**
     * 商家平台
     */
    @ApiModelProperty("JSON拓展信息返回")
    @JsonRawValue
    private String platform;

    /**
     * 商家店铺名
     */
    @ApiModelProperty("JSON拓展信息返回")
    @JsonRawValue
    private String stor;

    /**
     * 非商家职业状态
     */
    private int isStatus;

    /**
     * 非商家职业
     */
    @ApiModelProperty("JSON拓展信息返回")
    @JsonRawValue
    private String job;

    /**
     * 非商家状态
     */
    @ApiModelProperty("JSON拓展信息返回")
    @JsonRawValue
    private String status;

    /**
     * 等级（0.铜  1.银   2.金）
     */
    private int level;

    /**
     * 圈子数量
     */
//    private int communityNum;

    /**
     * 帖子数量
     */
//    private int circleNum;

    /**
     * 资源数量
     */
//    private int resourceNum;

    /**
     * 合作数量
     */
//    private int collaborateNum;

    /**
     * 干货数量
     */
//    private int dryGoodsNum;

    /**
     * 公开课数量
     */
//    private int publicClassNum;

}
