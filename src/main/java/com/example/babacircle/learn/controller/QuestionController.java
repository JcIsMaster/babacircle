package com.example.babacircle.learn.controller;

import com.example.babacircle.learn.entity.PublicClass;
import com.example.babacircle.learn.entity.Question;
import com.example.babacircle.learn.service.IQuestionService;
import com.example.babacircle.util.ResultLayUi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author MQ
 * @date 2021/5/12 10:45
 */
@Api(tags = "提问API")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/QuestionController")
public class QuestionController {

    @Autowired
    private IQuestionService iQuestionService;

    /**
     *
     * 查询所有提问信息
     * @return
     */
    @ApiOperation(value = "查询所有提问信息",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/queryAllQuestion")
    public ResultLayUi queryAllQuestion(Question question, String userName, Integer page, Integer limit) {
        return iQuestionService.queryAllQuestion(question,userName,page, limit);
    }

    /**
     *
     * 删除
     * @return
     */
    @ApiOperation(value = "删除",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/deleteQuestion")
    public int deleteQuestion(int id) {
        return iQuestionService.deleteQuestion(id);
    }

}
