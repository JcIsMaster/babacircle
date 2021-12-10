package com.example.babacircle.resource.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author Administrator
 */
@Data
@TableName("tb_resources")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Resources extends Model<Resources> {

    /**
     * ID
     */
    private int id;

    /**
     * 资源内容
     */
    private String content;

    /**
     * 一级标签ID
     */
    private Integer tagsOne;

    /**
     * 二级标签ID
     */
    private Integer tagsTwo;


    /**
     * 图片地址
     */
    private String[] img;

    /**
     *类型（0 图文  1视频）
     */
    private int type;

    /**
     * 用户id
     */
    private int uId;

    /**
     * 标题
     */
    private String title;

    /**
     * 视频地址
     */
    private String video;

    /**
     * 封面
     */
    private String cover;

    /**
     * 单元体类型
     */
    private int haplontType;

    /**
     * 点赞数量
     */
    private int favour;

    /**
     * 收藏数量
     */
    private int collect;

    /**
     * 浏览记录
     */
    private int browse;

    /**
     * 0.供货or1.找货
     */
    private int supplyOrDemand;

    /**
     * 创建时间
     */
    private String createAt;

    /**
     * 是否删除(1有效，0无效)
     */
    private int isDelete;
}
