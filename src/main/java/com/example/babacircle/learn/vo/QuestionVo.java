package com.example.babacircle.learn.vo;

import lombok.Data;

/**
 * @author MQ
 * @date 2021/5/12 14:02
 */
@Data
public class QuestionVo {

    private int id;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 用户名称
     */
    private String userName;

    /**
     *点赞
     */
    private int favour;

    /**
     *收藏
     */
    private int collect;

    /**
     *评论
     */
    private int comment;

    /**
     * 创建时间
     */
    private String createAt;
}
