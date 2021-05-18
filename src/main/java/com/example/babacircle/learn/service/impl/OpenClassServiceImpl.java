package com.example.babacircle.learn.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.babacircle.learn.dao.DryCargoMapper;
import com.example.babacircle.learn.dao.OpenClassMapper;
import com.example.babacircle.learn.entity.DryGoods;
import com.example.babacircle.learn.entity.PublicClass;
import com.example.babacircle.learn.service.IOpenClassService;
import com.example.babacircle.learn.vo.OpenClassVo;
import com.example.babacircle.util.ResultLayUi;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author MQ
 * @date 2021/5/7 15:30
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class OpenClassServiceImpl extends ServiceImpl<OpenClassMapper, PublicClass> implements IOpenClassService {


    @Autowired
    private OpenClassMapper openClassMapper;

    @Override
    public ResultLayUi queryAllOpenClass(PublicClass publicClass,String userName, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);

        String sql="";

        if(publicClass.getTitle()!=null && !publicClass.getTitle().equals("")){
            sql=" and a.title like'%"+publicClass.getTitle()+"%'";
        }

        if(userName!=null && !userName.equals("")){
            sql=" and c.user_name ='"+userName+"'";
        }

        List<OpenClassVo> openClassVos = openClassMapper.queryAllOpenClass(sql);

        int i = openClassMapper.countAllOpenClass(sql);
        ResultLayUi resultLayUi=new ResultLayUi();
        resultLayUi.setCode(0);
        resultLayUi.setCount(i);
        resultLayUi.setData(openClassVos);
        resultLayUi.setMsg("成功");

        return resultLayUi;
    }

    @Override
    public int delOpenClass(int id) {
        UpdateWrapper<PublicClass> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("id",id).set("is_delete",0);

        int update = baseMapper.update(null, updateWrapper);


        return update;
    }

    @Override
    public int addOpenClass(PublicClass publicClass) {
        publicClass.setCreateAt(System.currentTimeMillis()/1000+"");
        publicClass.setTagsOne(15);
        publicClass.setIsDelete(1);
        int insert = baseMapper.insert(publicClass);

        return insert;
    }


}
