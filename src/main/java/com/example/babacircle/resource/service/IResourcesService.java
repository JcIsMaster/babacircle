package com.example.babacircle.resource.service;

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
     * @return
     */
    ResultLayUi selectResourcesAllPosting(Resources resources, Integer page, Integer limit, String userName) throws ParseException;

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
}
