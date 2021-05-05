package com.example.babacircle.talents.vo;

import lombok.Data;


/**
 * @author MQ
 * @create 2021/2/19
 **/
@Data
public class RecruitVo {

    private int id;

    /**
     * 用户id
     */
    private int userId;

    /**
     * 发帖人
     */
    private String userName;

    /**
     * 职位名称
     */
    private String jobTitle;

    /**
     * 职位要求
     */
    private String jobRequirements;

    /**
     * 薪资范围
     */
    private String wageRange;


    /**
     * 发布时间
     */
    private String createAt;

    /**
     * (0全部，1我的，2招全职，3招兼职)
     */
    private int type;

    /**
     * 公司id 对应tb_recruit_users表中的user_id
     */
    private int companyId;

}
