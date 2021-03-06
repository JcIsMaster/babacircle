package com.example.babacircle.resource.controller;

import com.example.babacircle.common.utils.ResultUtil;
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
@Api(tags = "资源or合作API")
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
    @ApiOperation(value = "查询所有资源or合作帖子",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/selectResourcesAllPosting")
    public ResultLayUi selectResourcesAllPosting(Resources resources, Integer page, Integer limit,String userName) throws ParseException {
        return iResourcesService.selectResourcesAllPosting(resources,page,limit,userName);
    }

    @ApiOperation(value = "根据id查询资源or合作帖子",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/selectResourcesById")
    public Resources selectResourcesById(int id){
        return iResourcesService.selectResourcesById(id);
    }

    /**
     * 后台
     * 删除帖子
     * @return
     */
    @ApiOperation(value = "删除帖子",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/resourcesDeletes")
    public Integer resourcesDeletes(int id) {
        return  iResourcesService.resourcesDeletes(id);
    }

    /**
     *
     * 增加资源帖子  后台
     * @return
     */
    @ApiOperation(value = "增加资源or合作帖子",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/addResourcesPost")
    public int addResourcesPost(Resources resources) {
        return iResourcesService.addResourcesPost(resources);
    }

    /**
     *
     * 修改资源or合作帖子  后台
     * @return
     */
    @ApiOperation(value = "修改资源帖子",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/updateResourcesPost")
    public int updateResourcesPost(Resources resources){
        return iResourcesService.updateResourcesPost(resources);
    }

    /**
     * 查询用户发布的资源or合作帖子
     * @return
     */
    @ApiOperation(value = "查询用户发布的资源or合作帖子",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/queryResourcesPostingByUserId")
    public ResultUtil queryResourcesPostingByUserId(int type, int userId, Integer page, Integer limit) {
        return iResourcesService.queryResourcesPostingByUserId(type,userId,page,limit);
    }

}
