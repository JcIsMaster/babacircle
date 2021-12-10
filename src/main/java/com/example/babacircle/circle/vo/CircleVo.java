package com.example.babacircle.circle.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Administrator
 * @date 2021/12/1 14:16
 */
@Data
public class CircleVo {

    /**
     * 圈子id
     */
    private int id;

    /**
     * 标签id
     */
    private int tagId;

    /**
     * 圈主id
     */
    private int userId;

    /**
     * 社区头像
     */
    private String posters;

    /**
     * 社区名称
     */
    private String communityName;

    /**
     * 社区介绍
     */
    private String introduce;

    /**
     * 帖子数量
     */
    private int cnt;

    /**
     * 圈子成员数量
     */
    private int memberCount;

    /**
     * 所属话题
     */
    private String topicName;

    /**
     *是否加入了圈子 （0不是，1是）
     */
    private int whetherJoined = 0;

}
