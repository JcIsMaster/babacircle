package com.example.babacircle.tags.controller;


import com.example.babacircle.tags.entity.Tag;
import com.example.babacircle.tags.service.ITagService;
import com.example.babacircle.tags.vo.TagCircleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author MQ
 * @date 2021/1/21 16:07
 */
@Api(tags = "标签API")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/TagController")
public class TagController {


    @Autowired
    private ITagService iTagService;


    /**
     * 查询所有圈子
     * @return
     */
    @ApiOperation(value = "查询所有圈子",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/selectResourcesTag")
    public List<TagCircleVo> selectResourcesTag() {
        List<TagCircleVo> tags = iTagService.selectResourcesAllTag();
        return tags;
    }


    /**
     * 根据一级标签id查询二级标签
     * @return
     */
    @ApiOperation(value = "根据一级标签id查询二级标签",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/selectResourcesAllTags")
    public List<Tag> selectResourcesAllTags(int tid) {
        List<Tag> tags = iTagService.selectResourcesAllTags(tid);
        return tags;
    }

    /**
     * 查询我可以发帖的圈子
     * @return
     */
    @ApiOperation(value = "查询我可以发帖的圈子",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/selectTagsCircleMyCan")
    public List<TagCircleVo> selectTagsCircleMyCan(int userId){
        return iTagService.selectTagsCircleMyCan(userId);
    }


}
