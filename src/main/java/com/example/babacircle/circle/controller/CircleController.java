package com.example.babacircle.circle.controller;

import com.example.babacircle.circle.entity.Circle;
import com.example.babacircle.circle.service.ICircleService;
import com.example.babacircle.util.ResultLayUi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private ICircleService iCircleService;

    /**
     * 后台
     * 查询所有圈子的数据
     * @return
     */
    @ApiOperation(value = "查询所有圈子的数据",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/selectAllPosting")
    public ResultLayUi selectAllPosting(Circle circle, Integer page, Integer limit, String userName) throws Exception {

        ResultLayUi resultLayUi = iCircleService.selectAllPosting(circle, page, limit, userName);

        return resultLayUi;
    }
}
