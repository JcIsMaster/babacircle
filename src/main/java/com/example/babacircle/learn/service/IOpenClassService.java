package com.example.babacircle.learn.service;

import com.example.babacircle.common.utils.ResultUtil;
import com.example.babacircle.learn.entity.PublicClass;
import com.example.babacircle.util.ResultLayUi;

/**
 * @author MQ
 * @date 2021/5/7 15:30
 */
public interface IOpenClassService {

    /**
     * 查询所有公开课新
     * @param publicClass
     * @param page
     * @param limit
     * @return
     */
    ResultLayUi queryAllOpenClass(PublicClass publicClass,String userName, Integer page, Integer limit);

    /**
     * 删除
     * @param id
     * @return
     */
    int delOpenClass(int id);

    /**
     * 添加公开课
     * @param publicClass
     * @return
     */
    int addOpenClass(PublicClass publicClass);

    /**
     * 查询用户发布的公开课信息
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    ResultUtil queryOpenClassByUserId(int userId, Integer page, Integer limit);
}
