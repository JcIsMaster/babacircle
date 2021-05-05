package com.example.babacircle.talents.controller;

import com.example.babacircle.learn.vo.DryGoodsVo;
import com.example.babacircle.talents.entity.Recruit;
import com.example.babacircle.talents.entity.RecruitLabel;
import com.example.babacircle.talents.service.ITalentsService;
import com.example.babacircle.talents.vo.RecruitVo;
import com.example.babacircle.talents.vo.RequirementsR;
import com.example.babacircle.util.ResultLayUi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * @author MQ
 * @date 2021/4/28 14:44
 */
@Api(tags = "人才API")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/TalentsController")
public class TalentsController {

    @Autowired
    private ITalentsService iTalentsService;

    /**
     *
     * 根据条件查询人才里面的招牌信息
     * @return
     */
    @ApiOperation(value = "根据条件查询人才里面的招牌信息",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/querySignboardInformation")
    public ResultLayUi querySignboardInformation(Recruit recruit, Integer page, Integer limit)  {
        return iTalentsService.querySignboardInformation(recruit,page,limit);
    }

    /**
     *
     * 根据发布帖子信息id查询该招聘的要求信息
     * @return
     */
    @ApiOperation(value = "根据发布帖子信息id查询该招聘的要求信息",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/queryInquireJobRequirementsAccordingPostById")
    public List<RecruitLabel> queryInquireJobRequirementsAccordingPostById(int id)  {
        return iTalentsService.queryInquireJobRequirementsAccordingPostById(id);
    }

    /**
     * 删除
     * @return
     */
    @ApiOperation(value = "删除",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/deleteTalents")
    public int deleteTalents(int id)  {
        return iTalentsService.deleteTalents(id);
    }


    /**
     * 查询职位要求标签
     * @return
     */
    @ApiOperation(value = "查询职位要求标签",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/queryCheckJobRequirementsTab")
    public List<RecruitLabel> queryCheckJobRequirementsTab ()  {
        return iTalentsService.queryCheckJobRequirementsTab();
    }

    /**
     * 添加求职期望
     * @return
     */
    @ApiOperation(value = "添加求职期望",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/addJobExpectations")
    public ResultLayUi addJobExpectations (Recruit recruit, @RequestParam(value = "label[]") Integer[] label) throws ParseException {
        return iTalentsService.addJobExpectations(recruit,label);
    }

    /**
     * 修改招聘信息
     * @return
     */
    @ApiOperation(value = "添加求职期望",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/updateJbExpectations")
    public int updateJbExpectations (Recruit recruit, @RequestParam(value = "label[]") Integer[] label) throws ParseException {
        return iTalentsService.updateJbExpectations(recruit,label);
    }


}
