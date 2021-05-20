package com.example.babacircle.learn.controller;

import com.example.babacircle.learn.entity.DryGoods;
import com.example.babacircle.learn.entity.Tag;
import com.example.babacircle.learn.service.IDryCargoService;
import com.example.babacircle.learn.vo.DryGoodsVo;
import com.example.babacircle.user.vo.UserHtVo;
import com.example.babacircle.util.ResultLayUi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author MQ
 * @date 2021/4/23 13:51
 */
@Api(tags = "干货API")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/DryCargoController")
public class DryCargoController {

    @Autowired
    private IDryCargoService iDryCargoService;

    /**
     *
     * 查询所有干货信息
     * @return
     */
    @ApiOperation(value = "查询所有干货信息",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/queryAllDryCargo")
    public ResultLayUi queryAllDryCargo(DryGoodsVo dryGoodsVo, Integer page, Integer limit) {
        return iDryCargoService.queryAllDryCargo(dryGoodsVo, page, limit);
    }

    /**
     *
     * 删除
     * @return
     */
    @ApiOperation(value = "删除",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/deleteDryCargo")
    public int deleteDryCargo(int id) {
        return iDryCargoService.deleteDryCargo(id);
    }

    /**
     * 修改干货帖子信息
     * @return
     */
    @ApiOperation(value = "修改干货帖子信息",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/updateDryCargo")
    public int updateDryCargo(DryGoods dryGoods) {
        return iDryCargoService.updateDryCargo(dryGoods);
    }

    /**
     * 根据一级标签id查询二级标签
     * @return
     */
    @ApiOperation(value = "根据一级标签id查询二级标签",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/selectResourcesAllTags")
    public List<Tag> selectResourcesAllTags(int tid) {
        List<Tag> tags = iDryCargoService.selectResourcesAllTags(tid);
        return tags;
    }

    /**
     * 发布干货帖子信息
     * @return
     */
    @ApiOperation(value = "发布干货帖子信息",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/releaseDryCargo")
    public int releaseDryCargo(DryGoods dryGoods) {
        return iDryCargoService.releaseDryCargo(dryGoods);
    }



}
