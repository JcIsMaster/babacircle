package com.example.babacircle.circle.controller;

import com.example.babacircle.circle.entity.Circle;
import com.example.babacircle.circle.entity.Haplont;
import com.example.babacircle.circle.service.ICircleService;
import com.example.babacircle.circle.vo.CircleClassificationVo;
import com.example.babacircle.common.constanct.CodeType;
import com.example.babacircle.common.exception.ApplicationException;
import com.example.babacircle.util.ResultLayUi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation(value = "批量删除帖子",notes = "成功返回数据 反则为空")
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


}
