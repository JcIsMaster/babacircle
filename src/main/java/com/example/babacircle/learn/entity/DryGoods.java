package com.example.babacircle.learn.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author JC
 */
@Data
@TableName("tb_dry_goods")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class DryGoods extends Model<DryGoods> {

    @TableId
    private int id;
    /**
     * 发帖人id
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
     * 点赞数量
     */
    private int favour;
    /**
     * 收藏数量
     */
    private int collect;
    /**
     * 描述
     */
    private String description;
    /**
     * 封面图
     */
    private String coverImg;
    /**
     * 内容
     */
    private String content;
    /**
     * 删除状态1:有效；0:无效； 默认1
     */
    private int isDelete;
    /**
     * 发布时间
     */
    private String createAt;
    
}
