package com.example.babacircle.talents.service;

import com.example.babacircle.talents.entity.Recruit;
import com.example.babacircle.talents.entity.RecruitLabel;
import com.example.babacircle.talents.vo.RecruitVo;
import com.example.babacircle.talents.vo.RequirementsR;
import com.example.babacircle.util.ResultLayUi;

import java.util.List;

/**
 * @author MQ
 * @date 2021/4/28 14:50
 */
public interface ITalentsService {

    /**
     * 根据条件查询人才里面的招牌信息
     * @param page
     * @param limit
     * @return
     */
    ResultLayUi querySignboardInformation(Recruit recruit, Integer page, Integer limit);

    /**
     *  根据发布帖子信息id查询该招聘的要求信息
     * @param id 帖子id
     * @return
     */
    List<RecruitLabel> queryInquireJobRequirementsAccordingPostById(int id);

    /**
     * 删除
     * @param id 帖子id
     * @return
     */
    int deleteTalents(int id);

    /**
     *
     * @return
     */
    List<RecruitLabel> queryCheckJobRequirementsTab ();

    /**
     * 添加求职期望
     * @param recruit
     * @param label
     * @return
     */
    ResultLayUi addJobExpectations (Recruit recruit,Integer[] label);

    /**
     * 修改招聘信息
     * @param recruit
     * @param label
     * @return
     */
    int updateJbExpectations (Recruit recruit,Integer[] label);


}
