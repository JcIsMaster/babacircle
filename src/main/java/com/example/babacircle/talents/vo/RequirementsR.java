package com.example.babacircle.talents.vo;

import lombok.Data;

/**
 * @author MQ
 * @date 2021/4/29 14:20
 */
@Data
public class RequirementsR {

    /**
     * 经验要求
     */
    private String experienceRequirements;

    /**
     * 学历要求
     */
    private String educationalRequirements;

    /**
     * 年龄要求
     */
    private String ageRequirement;
}
