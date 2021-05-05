package com.example.babacircle.talents.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.babacircle.talents.dao.CompanyMapper;
import com.example.babacircle.talents.entity.RecruitingUsers;
import com.example.babacircle.talents.service.ICompanyService;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;

/**
 * @author MQ
 * @date 2021/3/29 15:20
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, RecruitingUsers> implements ICompanyService {

    @Autowired
    private CompanyMapper companyMapper;


    @Override
    public int addRecruitingUsers(RecruitingUsers recruitingUsers) throws ParseException {


        //查询是否注册过公司信息
        int i = companyMapper.queryCount(recruitingUsers.getUserId());
        if(i>0){
            //修改公司信息
            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("user_id",recruitingUsers.getUserId());

            int update = baseMapper.update(recruitingUsers, queryWrapper);

            return update;
        }

        recruitingUsers.setCreateAt(System.currentTimeMillis()/1000+"");

        //添加
        int insert = baseMapper.insert(recruitingUsers);


        return insert;
    }

    @Override
    public int queryCount(int userId) {
        return companyMapper.queryCount(userId);
    }



    @Override
    public RecruitingUsers querySingleCompanyInformation(int userId) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        RecruitingUsers recruitingUsers = baseMapper.selectOne(queryWrapper);
        return recruitingUsers;
    }
}
