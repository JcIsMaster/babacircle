package com.example.babacircle.resource.service;

import com.example.babacircle.common.utils.ResultUtil;
import com.example.babacircle.resource.entity.Resources;
import com.example.babacircle.util.ResultLayUi;

import java.text.ParseException;

/**
 * @author MQ
 * @date 2021/4/20 14:22
 */
public interface IResourcesService {

    /**
     * 查询所有资源帖子
     * @param resources
     * @param page
     * @param limit
     * @param userName
     * @throws ParseException
     * @return
     */
    ResultLayUi selectResourcesAllPosting(Resources resources, Integer page, Integer limit, String userName) throws ParseException;

    /**
     * 根据id查询资源帖子
     * @param id
     * @return
     */
    Resources selectResourcesById(int id);

    /**
     * 删除
     * @param id
     * @return
     */
    Integer resourcesDeletes(int id);

    /**
     * 增加资源帖子
     * @param resources
     * @return
     */
    int addResourcesPost(Resources resources);

    /**
     * 修改资源帖子
     * @param resources
     * @return
     */
    int updateResourcesPost(Resources resources);

    /**
     * 查询用户发布的资源or合作帖子
     * @param type
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    ResultUtil queryResourcesPostingByUserId(int type, int userId, Integer page, Integer limit);
}
