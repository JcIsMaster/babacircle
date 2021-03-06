package com.example.babacircle.resource.service.impl;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.babacircle.common.constanct.CodeType;
import com.example.babacircle.common.exception.ApplicationException;
import com.example.babacircle.common.utils.ResultUtil;
import com.example.babacircle.resource.dao.ResourcesMapper;
import com.example.babacircle.resource.entity.Img;
import com.example.babacircle.resource.entity.Resources;
import com.example.babacircle.resource.service.IResourcesService;
import com.example.babacircle.resource.vo.ResourcesLabelVo;
import com.example.babacircle.util.ResultLayUi;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
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


        List<ResourcesLabelVo> resourcesLabelVos = resourcesMapper.selectResourcesAllPosting(resources.getTagsOne(),sql);
        for (int i=0;i<resourcesLabelVos.size();i++){
            String[] strings = resourcesMapper.queryImgById(resourcesLabelVos.get(i).getId());
            resourcesLabelVos.get(i).setImg(strings);
        }

        //根据不同条件得到不同帖子数量
        Integer integer = resourcesMapper.selectResourcesAllPostingCount(resources.getTagsOne(),sql);

        ResultLayUi resultLayUi=new ResultLayUi();
        resultLayUi.setCode(0);
        resultLayUi.setCount(integer);
        resultLayUi.setData(resourcesLabelVos);
        resultLayUi.setMsg("成功");

        return resultLayUi;
    }

    @Override
    public Resources selectResourcesById(int id) {
        Resources resources = baseMapper.selectById(id);
        if (resources != null){
            resources.setImg(resourcesMapper.queryImgById(resources.getId()));
        }
        return resources;
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
        if(i1 <= 0){
            throw new ApplicationException(CodeType.SERVICE_ERROR,"添加资源失败");
        }


        if(resources.getType()==0) {
            //添加图片组
            Img img=new Img();
            img.setType(0);
            img.setZId(resources.getId());
            img.setCreateAt(System.currentTimeMillis() / 1000 + "");
            for (int i = 0; i < resources.getImg().length; i++) {
                System.out.println(resources.getImg()[i]);
                img.setImgUrl(resources.getImg()[i]);
                int addImg = resourcesMapper.addImg(img);
            }

        }
        return 1;
    }

    @Override
    public int updateResourcesPost(Resources resources) {
        UpdateWrapper<Resources> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("id",resources.getId()).set("cover",resources.getCover()).set("title",resources.getTitle()).set("content",resources.getContent())
        .set("tags_two",resources.getTagsTwo());
        int update = baseMapper.update(null, updateWrapper);
        if(update <= 0){
            throw new ApplicationException(CodeType.SERVICE_ERROR,"修改失败");
        }

        int i1 = resourcesMapper.deleteResourceImg(resources.getId());
        if(i1 <= 0){
            throw new ApplicationException(CodeType.SERVICE_ERROR,"删除资源图片失败");
        }

        int i = resourcesMapper.addResourceImg(resources.getId(), resources.getImg(), System.currentTimeMillis() / 1000 + "", 0);
        if(i <= 0){
            throw new ApplicationException(CodeType.SERVICE_ERROR,"添加资源图片失败");
        }

        return 1;
    }

    @Override
    public ResultUtil queryResourcesPostingByUserId(int type, int userId, Integer page, Integer limit) {
        if (userId == 0) {
            return ResultUtil.error("参数异常!");
        }
        PageHelper.startPage(page,limit);
        List<ResourcesLabelVo> resourcesLabelVos = resourcesMapper.selectResourcesPostingByUserId(type, userId);
        for (int i=0;i<resourcesLabelVos.size();i++){
            String[] strings = resourcesMapper.queryImgById(resourcesLabelVos.get(i).getId());
            resourcesLabelVos.get(i).setImg(strings);
        }
        int count = resourcesMapper.queryMyPostedPostsCount(userId, type);
        return ResultUtil.success(resourcesLabelVos,count);
    }
}
