package com.example.babacircle.circle.controller;

import com.example.babacircle.circle.entity.*;
import com.example.babacircle.circle.service.ICircleService;
import com.example.babacircle.circle.service.ICommunityService;
import com.example.babacircle.circle.vo.CircleClassificationVo;
import com.example.babacircle.circle.vo.CircleVo;
import com.example.babacircle.common.constanct.CodeType;
import com.example.babacircle.common.exception.ApplicationException;
import com.example.babacircle.common.utils.Paging;
import com.example.babacircle.common.utils.ResultUtil;
import com.example.babacircle.util.ResultLayUi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * @author MQ
 * @date 2021/5/3 16:44
 */
@Api(tags = "圈子API")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/CircleController")
public class CircleController {

    @Resource
    private ICircleService iCircleService;

    @Resource
    private ICommunityService iCommunityService;

    /**
     * 后台
     * 查询所有圈子帖子的数据
     * @return
     */
    @ApiOperation(value = "查询所有圈子的数据",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/selectAllPosting")
    public ResultLayUi selectAllPosting(Circle circle, Integer page, Integer limit,String startTime,String endTime) throws Exception {
        ResultLayUi resultLayUi = iCircleService.selectAllPosting(circle, page, limit,startTime,endTime);

        return resultLayUi;
    }

    /**
     *
     *  增加圈子帖子
     * @return
     */
    @ApiOperation(value = "增加圈子帖子",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/addCirclePost")
    public int addCirclePost(Circle circle) throws IOException {
        if(circle.getContent()==null || circle.getUserId()==0){
            throw new ApplicationException(CodeType.PARAMETER_ERROR);
        }
        return iCircleService.addCirclePost(circle);
    }

    /**
     *
     *  修改圈子帖子
     * @return
     */
    @ApiOperation(value = "修改圈子帖子",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/updateCirclePost")
    public int updateCirclePost(Circle circle)  {
        if(circle.getUserId()==0){
            throw new ApplicationException(CodeType.PARAMETER_ERROR);
        }
        return iCircleService.updateCirclePost(circle);
    }


    /**
     * 后台
     * 批量删除帖子
     * @return
     */
    @ApiOperation(value = "批量删除帖子",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/cirCleDeletes")
    public Integer cirCleDeletes(@RequestParam("id") Integer[] id) throws ParseException {
        return  iCircleService.cirCleDeletes(id);
    }

    /**
     * 后台
     * 批量删除帖子
     * @return
     */
    @ApiOperation(value = "根据标签id查询单元体导航栏",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/selectHaplontByTagId")
    public List<Haplont> selectHaplontByTagId(int tagId){
        return  iCircleService.selectHaplontByTagId(tagId);
    }

    @ApiOperation(value = "查询单个帖子",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/querySingleCircle")
    public CircleClassificationVo querySingleCircle(int id){
        return  iCircleService.querySingleCircle(id);
    }

    @ApiOperation(value = "查询用户发的所有帖子",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/queryPostsByUserId")
    public ResultLayUi queryPostsByUserId(int userId,Integer page, Integer limit) {
        return iCircleService.queryPostsByUserId(userId,page,limit);
    }

    @ApiOperation(value = "创建圈子",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/addCircle")
    public void addCircle(Community community) {
        iCommunityService.addCircle(community);
    }

    @ApiOperation(value = "官方圈子列表",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/queryOfficialCircleList")
    public List<Community> queryOfficialCircleList(){
        return iCommunityService.queryOfficialCircleList();
    }

    @ApiOperation(value = "查询所有话题",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/queryAllTopic")
    public List<CommunityTopic> queryAllTopic() {
        return iCommunityService.queryAllTopic();
    }

    @ApiOperation(value = "根据条件筛选查询圈子列表",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/queryCommunityBySort")
    public ResultUtil queryCommunityBySort(int sort, int userId, Paging paging) {
        return iCommunityService.queryCommunityBySort(sort,userId,paging);
    }

    @ApiOperation(value = "加入圈子",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/joinCircle")
    public int joinCircle(CommunityUser communityUser) {
        if(communityUser.getUserId()==0){
            throw new ApplicationException(CodeType.PARAMETER_ERROR);
        }
        return iCommunityService.joinCircle(communityUser);
    }

    @ApiOperation(value = "根据id查看单个圈子信息",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/queryCommunityById")
    public Community queryCommunityById(int id) {
        return iCommunityService.queryCommunityById(id);
    }

    @ApiOperation(value = "修改圈子信息",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/updateCommunity")
    public ResultUtil updateCommunity(Community community) {
        return iCommunityService.updateCommunity(community);
    }

    @ApiOperation(value = "删除圈子", notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/deleteCommunity")
    public ResultUtil deleteCommunity(int id,int tagId) {
        return iCommunityService.deleteCommunity(id,tagId);
    }
}
