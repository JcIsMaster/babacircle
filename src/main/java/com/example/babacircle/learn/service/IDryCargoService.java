package com.example.babacircle.learn.service;

import com.example.babacircle.learn.entity.DryGoods;
import com.example.babacircle.learn.entity.Tag;
import com.example.babacircle.learn.vo.DryGoodsVo;
import com.example.babacircle.util.ResultLayUi;

import java.util.List;

/**
 * @author MQ
 * @date 2021/4/23 13:53
 */
public interface IDryCargoService {

    /**
     * 查询所有干货信息
     * @param dryGoodsVo 标题
     * @param page 起始页
     * @param limit 页条数
     * @return
     */
    ResultLayUi queryAllDryCargo(DryGoodsVo dryGoodsVo, Integer page, Integer limit);

    /**
     * 删除
     * @param id 帖子id
     * @return
     */
    int deleteDryCargo(int id);

    /**
     * 修改干货帖子信息
     * @param dryGoods
     * @return
     */
    int updateDryCargo(DryGoods dryGoods);

    /**
     * 发布干货帖子信息
     * @param dryGoods
     * @return
     */
    int releaseDryCargo(DryGoods dryGoods);
}
