package com.example.babacircle.activity.service;

import com.example.babacircle.activity.entity.ActivityOnline;
import com.example.babacircle.common.utils.Paging;
import com.example.babacircle.common.utils.ResultUtil;

/**
 * @author JC
 * @date 2021/11/22 14:54
 */
public interface IActivityOnlineService {

    /**
     * 查询线上活动列表
     * @param status
     * @param title
     * @param paging
     * @return
     */
    ResultUtil queryActivityOnlineList(Integer status, String title, Paging paging);

    /**
     * 创建线上活动
     * @param activityOnline
     * @return
     */
    ResultUtil createActivityOnline(ActivityOnline activityOnline);
}
