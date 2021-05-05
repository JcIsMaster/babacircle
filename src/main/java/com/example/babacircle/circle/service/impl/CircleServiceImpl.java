package com.example.babacircle.circle.service.impl;

import com.example.babacircle.circle.dao.CircleMapper;
import com.example.babacircle.circle.entity.Circle;
import com.example.babacircle.circle.service.ICircleService;
import com.example.babacircle.circle.vo.CircleLabelVo;
import com.example.babacircle.util.ResultLayUi;
import com.github.pagehelper.PageHelper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author MQ
 * @date 2021/5/3 16:45
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class CircleServiceImpl implements ICircleService {

    @Autowired
    private CircleMapper circleMapper;

    @Override
    public ResultLayUi selectAllPosting(Circle circle, Integer page, Integer limit, String userName) {
        String sql="";

        if(circle.getTitle()!=null && !circle.getTitle().equals("")){
            sql+=" and a.title like '%"+circle.getTitle()+"%'";
        }

        //如果发帖人不为空 ，根据发帖人查询帖子
        if(userName!=null && !userName.equals("")){
            sql+="and c.user_name like '%"+userName+"%'";
        }

        PageHelper.startPage(page,limit);

        List<CircleLabelVo> circleLabelVos = circleMapper.selectAllPosting(sql);
        //根据不同条件得到不同帖子数量
        Integer integer = circleMapper.selectAllPostingCount(sql);
        ResultLayUi resultLayUi=new ResultLayUi();
        resultLayUi.setCode(0);
        resultLayUi.setCount(integer);
        resultLayUi.setData(circleLabelVos);
        resultLayUi.setMsg("成功");

        return resultLayUi;
    }
}
