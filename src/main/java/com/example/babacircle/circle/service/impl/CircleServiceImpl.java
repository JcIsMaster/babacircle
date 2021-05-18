package com.example.babacircle.circle.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.babacircle.circle.dao.CircleMapper;
import com.example.babacircle.circle.entity.Circle;
import com.example.babacircle.circle.service.ICircleService;
import com.example.babacircle.circle.vo.CircleLabelVo;
import com.example.babacircle.resource.dao.ResourcesMapper;
import com.example.babacircle.resource.entity.Img;
import com.example.babacircle.resource.entity.Resources;
import com.example.babacircle.util.ResultLayUi;
import com.github.pagehelper.PageHelper;

import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.portable.ApplicationException;
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
public class CircleServiceImpl extends ServiceImpl<CircleMapper, Circle> implements ICircleService {

    @Autowired
    private CircleMapper circleMapper;

    @Override
    public ResultLayUi selectAllPosting(Circle circle, Integer page, Integer limit, String userName) {
        String sql="";

        if(circle.getContent()!=null && !circle.getContent().equals("")){
            sql+=" and a.content like '%"+circle.getContent()+"%'";
        }

        //如果发帖人不为空 ，根据发帖人查询帖子
        if(userName!=null && !userName.equals("")){
            sql+="and c.user_name like '%"+userName+"%'";
        }

        PageHelper.startPage(page,limit);

        List<CircleLabelVo> circleLabelVos = circleMapper.selectAllPosting(sql);
        for (int i=0;i<circleLabelVos.size();i++){
            String[] strings = circleMapper.queryImgById(circleLabelVos.get(i).getId());
            circleLabelVos.get(i).setImg(strings);
        }


        //根据不同条件得到不同帖子数量
        Integer integer = circleMapper.selectAllPostingCount(sql);
        ResultLayUi resultLayUi=new ResultLayUi();
        resultLayUi.setCode(0);
        resultLayUi.setCount(integer);
        resultLayUi.setData(circleLabelVos);
        resultLayUi.setMsg("成功");

        return resultLayUi;
    }

    @Override
    public int addCirclePost(Circle circle) {
        circle.setCreateAt(System.currentTimeMillis()/1000+"");

        int i1 = circleMapper.addCirclePost(circle);


        if(circle.getType()==0) {
            //添加图片组
            Img img = new Img();
            img.setType(1);
            img.setZId(circle.getId());
            img.setCreateAt(System.currentTimeMillis() / 1000 + "");
            for (int i = 0; i < circle.getImg().length; i++) {
                img.setImgUrl(circle.getImg()[i]);
                int addImg = circleMapper.addImg(img);

            }
        }

        return 1;
    }

    @Override
    public int updateCirclePost(Circle circle) {
        UpdateWrapper<Circle> updateWrapper=new UpdateWrapper();
        updateWrapper.eq("id",circle.getId()).set("cover",circle.getCover());

        int update = baseMapper.update(null, updateWrapper);

        int i = circleMapper.deleteResourceImg(circle.getId());


        int i1 = circleMapper.addCircleImg(circle.getId(), circle.getImg(), System.currentTimeMillis() / 1000 + "", 1);


        return i1;
    }

    @Override
    public Integer cirCleDeletes(int id) {
        UpdateWrapper<Circle> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("id",id).set("is_delete",0);

        int update = baseMapper.update(null, updateWrapper);

        return update;
    }
}
