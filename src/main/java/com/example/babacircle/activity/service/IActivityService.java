package com.example.babacircle.activity.service;

import com.example.babacircle.activity.entity.Activity;
import com.example.babacircle.activity.entity.ActivityParticipate;
import com.example.babacircle.activity.vo.ActivityListVo;
import com.example.babacircle.common.utils.Paging;
import com.example.babacircle.common.utils.ResultUtil;
import com.example.babacircle.util.ResultLayUi;

import java.text.ParseException;
import java.util.List;

/**
 * @author JC
 * @date 2021/9/24 16:49
 */
public interface IActivityService {

    /**
     * 查询活动列表
     * @param type
     * @param title
     * @param area
     * @param paging
     * @return
     */
    ResultLayUi queryActivityList(int type, String title, String area, Paging paging);

    /**
     * 查询活动详情
     * @param id
     * @return
     */
    Activity queryActivityDetailsById(int id);

    /**
     * 创建活动
     * @param activity
     * @throws ParseException
     * @return
     */
    ResultUtil createActivity(Activity activity) throws ParseException;

    /**
     * 删除(截止)活动
     * @param id
     * @return
     */
    ResultUtil delActivity(int id);

    /**
     * 查询活动参加者列表
     * @param id
     * @param paging
     * @return
     */
    ResultLayUi queryActivityParticipates(int id, Paging paging);

    /**
     * 根据用户查询活动列表
     * @param userId
     * @param paging
     * @return
     */
    ResultUtil queryActivityListByUserId(int userId, Paging paging);
}
