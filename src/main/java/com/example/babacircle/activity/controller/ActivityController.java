package com.example.babacircle.activity.controller;

import com.example.babacircle.activity.entity.Activity;
import com.example.babacircle.activity.service.IActivityService;
import com.example.babacircle.common.utils.Paging;
import com.example.babacircle.common.utils.ResultUtil;
import com.example.babacircle.util.ResultLayUi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * @author JC
 * @date 2021/9/24 16:51
 */
@Api(tags = "活动API")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/ActivityController")
public class ActivityController {

    @Autowired
    private IActivityService iActivityService;

    @ApiOperation(value = "查询活动列表",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/queryActivityList")
    public ResultLayUi queryActivityList(int type, String title, String area, Paging paging){
        return iActivityService.queryActivityList(type,title,area,paging);
    }

    @ApiOperation(value = "查询活动详情",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/queryActivityDetailsById")
    public Activity queryActivityDetailsById(int id){
        return iActivityService.queryActivityDetailsById(id);
    }

    @ApiOperation(value = "创建活动",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/createActivity")
    public ResultUtil createActivity(Activity activity) throws ParseException {
        return iActivityService.createActivity(activity);
    }

    @ApiOperation(value = "删除(截止)活动",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/delActivity")
    public ResultUtil delActivity(int id) {
        return iActivityService.delActivity(id);
    }

    @ApiOperation(value = "根据id查询该活动的参加者",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/queryActivityParticipates")
    public ResultLayUi queryActivityParticipates(int id, Paging paging) {
        return iActivityService.queryActivityParticipates(id,paging);
    }

    @ApiOperation(value = "根据用户查询活动列表",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/queryActivityListByUserId")
    public ResultUtil queryActivityListByUserId(int userId, Paging paging){
        return iActivityService.queryActivityListByUserId(userId,paging);
    }
}
