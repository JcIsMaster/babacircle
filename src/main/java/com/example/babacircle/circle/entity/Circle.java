package com.example.babacircle.circle.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author MQ
 * @date 2021/1/19 15:48
 * 圈子实体类
 */
@Data
@TableName("tb_circles")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Circle extends Model<Circle> {

    /**
     * ID
     */
    @TableId
    private int id;

    /**
     * 圈子内容
     */
    private String content;

    /**
     * 一级标签ID
     */
    private int tagsOne;

    /**
     * 二级标签ID
     */
    private int tagsTwo;

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
     * 创建时间
     */
    private String createAt;

    /**
     * 是否删除(1有效，0无效)
     */
    private int isDelete;

}
