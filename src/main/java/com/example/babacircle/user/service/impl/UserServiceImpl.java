package com.example.babacircle.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.babacircle.resource.dao.ResourcesMapper;
import com.example.babacircle.resource.entity.Resources;
import com.example.babacircle.resource.service.IResourcesService;
import com.example.babacircle.user.dao.UserMapper;
import com.example.babacircle.user.entity.User;
import com.example.babacircle.user.service.IUserService;
import com.example.babacircle.user.vo.UserHtVo;
import com.example.babacircle.util.ResultLayUi;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author MQ
 * @date 2021/4/21 9:38
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultLayUi queryAllUserForSql(UserHtVo userHtVo, Integer page, Integer limit) {

        String sql="";

        PageHelper.startPage(page,limit);

        System.out.println(userHtVo.getUserName());

        if(!"undefined".equals(userHtVo.getUserName()) && userHtVo.getUserName()!=null){
            sql+="where user_name like '%"+userHtVo.getUserName()+"%'";
        }
        List<User> userHtVos = userMapper.queryAllUserForSql(sql);
        Integer integer = userMapper.userCount();

        ResultLayUi resultLayUi=new ResultLayUi();
        resultLayUi.setCode(0);
        resultLayUi.setCount(integer);
        resultLayUi.setData(userHtVos);
        resultLayUi.setMsg("成功");

        return resultLayUi;
    }

    @Override
    public ResultLayUi selectUserNamePassword(String userName, String password) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
        queryWrapper.eq("user_name",userName);
        queryWrapper.eq("m_code",password);

        ResultLayUi resultLayUi=new ResultLayUi();
        User user = baseMapper.selectOne(queryWrapper);

        resultLayUi.setCode(0);
        resultLayUi.setCount(0);

        if(user==null){
            resultLayUi.setData(0);
        }else{
            resultLayUi.setData(user);
        }


        resultLayUi.setMsg("成功");

        return resultLayUi;
    }
}
