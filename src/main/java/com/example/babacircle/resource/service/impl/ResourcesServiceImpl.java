package com.example.babacircle.resource.service.impl;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.babacircle.resource.dao.ResourcesMapper;
import com.example.babacircle.resource.entity.Img;
import com.example.babacircle.resource.entity.Resources;
import com.example.babacircle.resource.service.IResourcesService;
import com.example.babacircle.resource.vo.ResourcesLabelVo;
import com.example.babacircle.util.ResultLayUi;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * @author MQ
 * @date 2021/4/20 14:23
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ResourcesServiceImpl extends ServiceImpl<ResourcesMapper, Resources> implements IResourcesService {


    @Autowired
    private ResourcesMapper resourcesMapper;


    @Override
    public ResultLayUi selectResourcesAllPosting(Resources resources, Integer page, Integer limit, String userName) throws ParseException {
        PageHelper.startPage(page,limit);
        String sql="";


        if(resources.getTitle()!=null && !resources.getTitle().equals("")){
            sql+=" and a.title like '%"+resources.getTitle()+"%'";
        }

        //如果发帖人不为空 ，根据发帖人查询帖子
        if(userName!=null && !userName.equals("")){
            sql+="and c.user_name like '%"+userName+"%'";
        }



        //将时间格式转换为时间戳
        //开始时间
      /*  if(!startTime.equals("undefined") && !endTime.equals("undefined") && !startTime.equals("null") && !endTime.equals("null")){
            if(!startTime.equals("") && !endTime.equals("")){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String startTimes=String.valueOf(sdf.parse(startTime).getTime() / 1000);

                //结束时间
                SimpleDateFormat sdftwo = new SimpleDateFormat("yyyy-MM-dd");
                String endTimes=String.valueOf(sdftwo.parse(endTime).getTime() / 1000);

                if(!"undefined".equals(startTime) && !endTime.equals("undefined") ){
                    sql+="and a.create_at>= "+startTimes+" and a.create_at<="+endTimes+"";
                }
            }
        }*/


        List<ResourcesLabelVo> resourcesLabelVos = resourcesMapper.selectResourcesAllPosting(sql);

        //根据不同条件得到不同帖子数量
        Integer integer = resourcesMapper.selectResourcesAllPostingCount(sql);

        ResultLayUi resultLayUi=new ResultLayUi();
        resultLayUi.setCode(0);
        resultLayUi.setCount(integer);
        resultLayUi.setData(resourcesLabelVos);
        resultLayUi.setMsg("成功");

        return resultLayUi;
    }

    @Override
    public Integer resourcesDeletes(int id) {
        UpdateWrapper<Resources> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("id",id).set("is_delete",0);

        int update = baseMapper.update(null, updateWrapper);
        return update;
    }

    @Override
    public int addResourcesPost(Resources resources) {
        resources.setCreateAt(System.currentTimeMillis()/1000+"");

        int i1 = resourcesMapper.addResourcesPost(resources);

        if(resources.getType()==0) {
            //添加图片组
            Img img=new Img();
            img.setType(0);
            img.setZId(resources.getId());
            img.setCreateAt(System.currentTimeMillis() / 1000 + "");
            for (int i = 0; i < resources.getImg().length; i++) {
                img.setImgUrl(resources.getImg()[i]);
                int addImg = resourcesMapper.addImg(img);
            }

        }

        return 1;
    }
}
