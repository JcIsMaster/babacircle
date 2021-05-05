package com.example.babacircle.resource.controller;

import com.example.babacircle.resource.entity.Resources;
import com.example.babacircle.resource.service.IResourcesService;
import com.example.babacircle.util.ResultLayUi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * @author MQ
 * @date 2021/4/20 14:21
 */
@Api(tags = "资源API")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/ResourcesController")
public class ResourcesController {

    @Autowired
    private IResourcesService iResourcesService;

    /**
     * 查询所有资源帖子
     * @return
     */
    @ApiOperation(value = "查询所有资源帖子",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/selectResourcesAllPosting")
    public ResultLayUi selectResourcesAllPosting(Resources resources, Integer page, Integer limit,String userName) throws ParseException {
        return iResourcesService.selectResourcesAllPosting(resources,page,limit,userName);
    }

    /**
     * 后台
     * 批量删除帖子
     * @return
     */
    @ApiOperation(value = "批量删除帖子",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/resourcesDeletes")
    public Integer resourcesDeletes(int id) throws ParseException {
        return  iResourcesService.resourcesDeletes(id);
    }

    /**
     *
     * 增加资源帖子  后台
     * @return
     */
    @ApiOperation(value = "增加资源帖子",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/addResourcesPost")
    public int addResourcesPost(Resources resources) throws Exception {
        return iResourcesService.addResourcesPost(resources);
    }

}
