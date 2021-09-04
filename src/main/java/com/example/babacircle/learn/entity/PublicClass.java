package com.example.babacircle.learn.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author MQ
 * @date 2021/5/7 15:24
 */
@Data
@TableName("tb_public_class")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class PublicClass extends Model<PublicClass> {

    @TableId
    private int id;

    /**
     * 公开课发布人id
     */
    private int uId;

    /**
     * 标题
     */
    private String title;

    /**
     * 一级标签id
     */
    private int tagsOne;

    /**
     * 二级标签id
     */
    private int tagsTwo;

    /**
     * 单元体类型id
     */
    private int haplontId;

    /**
     * 封面图
     */
    private String coverImg;

    /**
     * 课程列表
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

    /**
     * 删除状态1:有效；0:无效； 默认1
     */
    private int isDelete;
    
}
