package com.example.babacircle.circle.service;

import com.example.babacircle.circle.entity.Circle;
import com.example.babacircle.util.ResultLayUi;


/**
 * @author MQ
 * @date 2021/5/3 16:45
 */
public interface ICircleService {
    /**
     * 查询所有圈子的数据
     * @param circle
     * @param page
     * @param limit
     * @param userName
     * @return
     */
    ResultLayUi selectAllPosting(Circle circle, Integer page, Integer limit, String userName);

    /**
     * 增加圈子
     * @param circle
     * @return
     */
    int addCirclePost(Circle circle);

    /**
     * 修改圈子
     * @param circle
     * @return
     */
    int updateCirclePost(Circle circle);

    /**
     * 删除
     * @param id
     * @return
     */
    Integer cirCleDeletes(int id);

}
