package com.example.babacircle.match.controller;

import com.example.babacircle.common.utils.ResultUtil;
import com.example.babacircle.match.service.IMatchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author JC
 * @date 2021/11/23 14:43
 */
@Api(tags = "匹配API")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/MatchController")
public class MatchController {

    @Resource
    private IMatchService iMatchService;

    @ApiOperation(value = "新增/修改匹配信息",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/addUserMatch")
    public ResultUtil addUserMatch(int userId,String text){
        return iMatchService.addUserMatch(userId,text);
    }
}
