package com.example.babacircle.learn.controller;

import com.example.babacircle.learn.entity.PublicClass;
import com.example.babacircle.learn.service.IOpenClassService;
import com.example.babacircle.learn.vo.DryGoodsVo;
import com.example.babacircle.util.ResultLayUi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author MQ
 * @date 2021/5/7 15:22
 */
@Api(tags = "公开课API")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/OpenClassController")
public class OpenClassController {

     @Autowired
     private IOpenClassService iOpenClassService;

    /**
     *
     * 查询所有公开课信息
     * @return
     */
    @ApiOperation(value = "查询所有公开课信息",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/queryAllOpenClass")
    public ResultLayUi queryAllOpenClass(PublicClass publicClass,String userName, Integer page, Integer limit) {
        return iOpenClassService.queryAllOpenClass(publicClass,userName,page, limit);
    }
    /**
     *
     * 删除公开课信息
     * @return
     */
    @ApiOperation(value = "删除公开课信息",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/delOpenClass")
    public int delOpenClass(int id)  {
        return iOpenClassService.delOpenClass(id);
    }

    /**
     *
     * 添加公开课
     * @return
     */
    @ApiOperation(value = "添加公开课",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/addOpenClass")
    public int addOpenClass(PublicClass publicClass)  {
        return iOpenClassService.addOpenClass(publicClass);
    }

}
