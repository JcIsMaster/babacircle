package com.example.babacircle.activity.controller;

import com.example.babacircle.activity.entity.ActivityOnline;
import com.example.babacircle.activity.service.IActivityOnlineService;
import com.example.babacircle.common.utils.Paging;
import com.example.babacircle.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;

/**
 * @author JC
 * @date 2021/11/22 14:51
 */
@Api(tags = "活动(线上)API")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/ActivityOnlineController")
public class ActivityOnlineController {

    @Resource
    private IActivityOnlineService iActivityOnlineService;

    @ApiOperation(value = "查询线上活动列表",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/queryActivityOnlineList")
    public ResultUtil queryActivityOnlineList(Integer status, String title, Paging paging) {
        return iActivityOnlineService.queryActivityOnlineList(status,title,paging);
    }

    @ApiOperation(value = "创建线上活动",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/createActivityOnline")
    public ResultUtil createActivityOnline(ActivityOnline activityOnline) {
        return iActivityOnlineService.createActivityOnline(activityOnline);
    }

}
