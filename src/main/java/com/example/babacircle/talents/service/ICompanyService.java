package com.example.babacircle.talents.service;


import com.example.babacircle.talents.entity.RecruitingUsers;

import java.text.ParseException;

/**
 * @author MQ
 * @date 2021/3/29 15:19
 */
public interface ICompanyService {

    /**
     * 添加公司信息
     * @param recruitingUsers
     * @return
     */
    int addRecruitingUsers(RecruitingUsers recruitingUsers) throws ParseException;

    /**
     * 查询是否填写过公司信息
     * @param userId 用户id
     * @return
     */
    int queryCount(int userId);



    /**
     * 查询单个公司信息
     * @param userId 用户id
     * @return
     */
    RecruitingUsers querySingleCompanyInformation(int userId);
}
