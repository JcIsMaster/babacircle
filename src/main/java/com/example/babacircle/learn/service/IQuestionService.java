package com.example.babacircle.learn.service;

import com.example.babacircle.learn.entity.Question;
import com.example.babacircle.util.ResultLayUi;

/**
 * @author MQ
 * @date 2021/5/12 10:47
 */
public interface IQuestionService {

    /**
     * 查询所有提问信息
     * @param question
     * @param page
     * @param limit
     * @return
     */
    ResultLayUi queryAllQuestion(Question question, Integer page, Integer limit);

    /**
     * 删除
     * @param id 提问id
     * @return
     */
    int deleteQuestion(int id);
}
