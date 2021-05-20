package com.example.babacircle.learn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.babacircle.learn.dao.DryCargoMapper;
import com.example.babacircle.learn.entity.DryGoods;
import com.example.babacircle.learn.entity.Tag;
import com.example.babacircle.learn.service.IDryCargoService;
import com.example.babacircle.learn.vo.DryGoodsVo;
import com.example.babacircle.user.dao.UserMapper;
import com.example.babacircle.user.entity.User;
import com.example.babacircle.user.service.IUserService;
import com.example.babacircle.util.ResultLayUi;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.DriverManager;
import java.util.List;

/**
 * @author MQ
 * @date 2021/4/23 13:53
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class DryCargoServiceImpl  extends ServiceImpl<DryCargoMapper, DryGoods> implements IDryCargoService {

    @Autowired
    private DryCargoMapper dryCargoMapper;

    @Override
    public ResultLayUi queryAllDryCargo(DryGoodsVo dryGoodsVo, Integer page, Integer limit) {


        String sql="";
        if(!"undefined".equals(dryGoodsVo.getTitle()) && dryGoodsVo.getTitle()!=null){
            sql=" and a.title like '%"+dryGoodsVo.getTitle()+"%'";
        }


        if(!"undefined".equals(dryGoodsVo.getUserName()) && dryGoodsVo.getUserName()!=null){
            sql=" and c.user_name like '%"+dryGoodsVo.getUserName()+"%'";
        }

        //查询所有
        PageHelper.startPage(page,limit);
        List<DryGoodsVo> dryGoodsVos = dryCargoMapper.queryAllDryCargo(sql);
        for (int i=0;i<dryGoodsVos.size();i++){
            //得到点赞数量
            int i1 = dryCargoMapper.countPostGiveNumber(dryGoodsVos.get(i).getId());
            dryGoodsVos.get(i).setFavour(i1);

            //得到收藏数量
            int i2 = dryCargoMapper.countPostCollectNumber(dryGoodsVos.get(i).getId());
            dryGoodsVos.get(i).setCollect(i2);

            //得到评论数量
            int i3 = dryCargoMapper.countPostCommentNumber(dryGoodsVos.get(i).getId());
            dryGoodsVos.get(i).setCommentNumber(i3);
        }


        //统计
        int i = dryCargoMapper.countAllDryCargo(sql);

        ResultLayUi resultLayUi=new ResultLayUi();
        resultLayUi.setCode(0);
        resultLayUi.setCount(i);
        resultLayUi.setData(dryGoodsVos);
        resultLayUi.setMsg("成功");

        return resultLayUi;
    }

    @Override
    public int deleteDryCargo(int id) {
       /* DryGoods dryGoods=new DryGoods();
        dryGoods.setIsDelete(0);

        //更新的条件
        QueryWrapper<DryGoods> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);*/
        //更新的条件以及字段
        UpdateWrapper<DryGoods> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id).set("is_delete", 0);


        int i = baseMapper.update(null,wrapper);

        return i;
    }

    @Override
    public int updateDryCargo(DryGoods dryGoods) {
        System.out.println(dryGoods.getTagsTwo());
        UpdateWrapper<DryGoods> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", dryGoods.getId()).set("title", dryGoods.getTitle())
                .set("cover_img",dryGoods.getCoverImg()).set("content",dryGoods.getContent())
                .set("description",dryGoods.getDescription());

        int update = baseMapper.update(null, wrapper);

        return update;
    }

    @Override
    public List<Tag> selectResourcesAllTags(int tid) {
        return dryCargoMapper.selectResourcesAllTags(tid);
    }

    @Override
    public int releaseDryCargo(DryGoods dryGoods) {
        if(dryGoods.getUId()==0 || dryGoods.getTagsTwo()==0){
            return 0;
        }

        dryGoods.setIsDelete(1);
        dryGoods.setCreateAt(System.currentTimeMillis()/1000+"");
        int insert = baseMapper.insert(dryGoods);
        return insert;
    }
}
