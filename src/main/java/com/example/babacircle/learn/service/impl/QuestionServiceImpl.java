package com.example.babacircle.learn.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.babacircle.learn.dao.QuestionMapper;
import com.example.babacircle.learn.entity.Question;
import com.example.babacircle.learn.service.IQuestionService;
import com.example.babacircle.learn.vo.QuestionVo;
import com.example.babacircle.util.ResultLayUi;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author MQ
 * @date 2021/5/12 10:48
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class QuestionServiceImpl  extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public ResultLayUi queryAllQuestion(Question question, String userName, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);

        List<QuestionVo> questionVo = questionMapper.queryAllQuestion();

        int i = questionMapper.countAllQuestion();

        ResultLayUi resultLayUi=new ResultLayUi();
        resultLayUi.setCode(0);
        resultLayUi.setCount(i);
        resultLayUi.setData(questionVo);
        resultLayUi.setMsg("成功");

        return resultLayUi;
    }

    @Override
    public int deleteQuestion(int id) {
        UpdateWrapper<Question> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("id",id).set("is_delete",0);

        int update = baseMapper.update(null, updateWrapper);
        return update;
    }
}
