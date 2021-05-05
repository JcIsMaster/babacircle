package com.example.babacircle.talents.vo;

import lombok.Data;

/**
 * @author MQ
 * @date 2021/5/3 17:40
 */
@Data
public class RecruitUpdateVo {

    /**
     * (0全部，1我的，2招全职，3招兼职)
     */
    private int type;

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
}
