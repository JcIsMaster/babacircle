package com.example.babacircle.circle.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.babacircle.circle.dao.CircleMapper;
import com.example.babacircle.circle.entity.Circle;
import com.example.babacircle.circle.entity.Haplont;
import com.example.babacircle.circle.service.ICircleService;
import com.example.babacircle.circle.vo.CircleClassificationVo;
import com.example.babacircle.circle.vo.CircleLabelVo;
import com.example.babacircle.common.constanct.CodeType;
import com.example.babacircle.common.exception.ApplicationException;
import com.example.babacircle.common.utils.FfmpegUtil;
import com.example.babacircle.resource.entity.Img;
import com.example.babacircle.util.ResultLayUi;
import com.github.pagehelper.PageHelper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public ResultLayUi selectAllPosting(Circle circle, Integer page, Integer limit,String startTime,String endTime) throws ParseException {
        String sql="";

        if(circle.getContent()!=null && !circle.getContent().equals("")){
            sql+=" and a.content like '%"+circle.getContent()+"%'";
        }

        //如果发帖人不为空 ，根据发帖人查询帖子
        /*if(userName!=null && !userName.equals("")){
            sql+="and c.user_name like '%"+userName+"%'";
        }*/

        //将时间格式转换为时间戳
        //开始时间
        if(!startTime.equals("0") && !endTime.equals("0") ) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                long startTimes = sdf.parse(startTime).getTime() / 1000;

            //结束时间
                SimpleDateFormat sdftwo = new SimpleDateFormat("yyyy-MM-dd");
                long endTimes = sdftwo.parse(endTime).getTime() / 1000+86400;


            if(!"undefined".equals(startTime) && !endTime.equals("undefined") ){
                    sql+=" and a.create_at  between '"+startTimes+"' and '"+endTimes+"'";
                }
        }

        PageHelper.startPage(page,limit);
        List<CircleLabelVo> circleLabelVos = circleMapper.selectAllPosting(sql,circle);
        for (int i=0;i<circleLabelVos.size();i++){
            String[] strings = circleMapper.queryImgById(circleLabelVos.get(i).getId());
            circleLabelVos.get(i).setImg(strings);
        }


        //根据不同条件得到不同帖子数量

        Integer integer = circleMapper.selectAllPostingCount(sql,circle.getTagsTwo());
        ResultLayUi resultLayUi=new ResultLayUi();
        resultLayUi.setCode(200);
        resultLayUi.setCount(integer);
        resultLayUi.setData(circleLabelVos);
        resultLayUi.setMsg("成功");

        return resultLayUi;
    }

    @Override
    public int addCirclePost(Circle circle) throws IOException {

        circle.setCreateAt(System.currentTimeMillis()/1000+"");
        if(circle.getType()==0){
            if(circle.getCover()==null || circle.getCover().equals("") || "undefined".equals(circle.getCover())){
                circle.setCover(circle.getImg()[0]);
            }
        }

        if(circle.getType()==1){
            if(circle.getCover()==null || circle.getCover().equals("") || "undefined".equals(circle.getCover())){
                String videoCover = FfmpegUtil.getVideoCover(circle.getVideo());
                circle.setCover(videoCover);
            }
        }



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
        updateWrapper.eq("id",circle.getId()).set("cover",circle.getCover()).set("content",circle.getContent())
        .set("tags_two",circle.getTagsTwo()).set("haplont_type",circle.getHaplontType()).set("video",circle.getVideo());

        int update = baseMapper.update(null, updateWrapper);
        if(update<=0){
            throw new ApplicationException(CodeType.SERVICE_ERROR,"修改失败");
        }

        int i = circleMapper.deleteResourceImg(circle.getId());
        if(i<=0){
            throw new ApplicationException(CodeType.SERVICE_ERROR,"删除关系失败");
        }

        int i1 = circleMapper.addCircleImg(circle.getId(), circle.getImg(), System.currentTimeMillis() / 1000 + "", 1);
        if(i1<=0){
            throw new ApplicationException(CodeType.SERVICE_ERROR);
        }
        return i1;
    }


    @Override
    public Integer cirCleDeletes(Integer[] id) {
        Integer deletes=0;
        for (int i=0; i<id.length;i++){
            deletes= circleMapper.deletes(id[i]);
        }

        if(deletes<=0){
            throw new ApplicationException(CodeType.SERVICE_ERROR,"批量删除失败");
        }
        return deletes;
    }

    @Override
    public List<Haplont> selectHaplontByTagId(int tagId) {
        return circleMapper.selectHaplontByTagId(tagId);
    }

    @Override
    public CircleClassificationVo querySingleCircle(int id) {
        CircleClassificationVo circleClassificationVo = circleMapper.querySingleCircle(id);
        if(circleClassificationVo==null){
            throw new ApplicationException(CodeType.SERVICE_ERROR,"该圈子的帖子不存在");
        }

        //得到图片组
        String[] strings = circleMapper.selectImgByPostId(circleClassificationVo.getId());
        circleClassificationVo.setImg(strings);
        return circleClassificationVo;
    }
}
