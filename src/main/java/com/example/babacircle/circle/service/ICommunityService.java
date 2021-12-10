package com.example.babacircle.circle.service;

import com.example.babacircle.circle.entity.Community;
import com.example.babacircle.circle.entity.CommunityTopic;
import com.example.babacircle.circle.entity.CommunityUser;
import com.example.babacircle.circle.vo.CircleVo;
import com.example.babacircle.common.utils.Paging;
import com.example.babacircle.common.utils.ResultUtil;

import java.util.List;

/**
 * @author JC
 * @date 2021/11/23 16:43
 */
public interface ICommunityService {

    /**
     * 创建圈子
     * @param community
     */
    void addCircle(Community community);

    /**
     * 官方圈子列表
     * @return
     */
    List<Community> queryOfficialCircleList();

    /**
     * 查询所有话题
     * @return
     */
    List<CommunityTopic> queryAllTopic();

    /**
     * 根据条件筛选查询圈子列表
     * @param sort
     * @param userId
     * @param paging
     * @return
     */
    ResultUtil queryCommunityBySort(int sort, int userId, Paging paging);

    /**
     * 加入圈子
     * @param communityUser
     * @return
     */
    int joinCircle(CommunityUser communityUser);

    /**
     * 根据id查看单个圈子信息
     * @param id
     * @return
     */
    Community queryCommunityById(int id);

    /**
     * 修改圈子信息
     * @param community
     * @return
     */
    ResultUtil updateCommunity(Community community);

    /**
     * 删除圈子
     * @param id
     * @param tagId
     * @return
     */
    ResultUtil deleteCommunity(int id,int tagId);
}
