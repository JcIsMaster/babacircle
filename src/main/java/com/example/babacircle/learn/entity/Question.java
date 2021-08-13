package com.example.babacircle.learn.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author JC
 * @date 2021/4/28 13:55
 */
@Data
@TableName("tb_question")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Question extends Model<Question> {

    private int id;
    /**
     * 发问人id
     */
    private int userId;
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
    private int favourNum;
    /**
     * 评论数量
     */
    private int commentNum;
    /**
     * 描述
     */
    private String description;
    /**
     * 是否匿名 0:否  1:是
     */
    private int anonymous;
    /**
     * 0:图片  1:视频
     */
    private int contentType;
    /**
     * 封面图
     */
    private String coverImg;
    /**
     * 视频
     */
    private String video;
    /**
     * 悬赏奖励  0.未悬赏
     */
    private int award;
    /**
     * 删除状态0:有效；1:无效； 默认0
     */
    private int isDelete;
    /**
     * 发布时间
     */
    private String createAt;
}
