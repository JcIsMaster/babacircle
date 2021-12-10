package com.example.babacircle.tags.service;



import com.example.babacircle.tags.entity.Tag;
import com.example.babacircle.tags.vo.TagCircleVo;

import java.util.List;

/**
 * @author MQ
 * @date 2021/1/21 16:08
 */
public interface ITagService {


    /**
     * 查询所有圈子
     * @return
     */
    List<TagCircleVo> selectResourcesAllTag();

    /**
     * 根据一级标签id查询二级标签
     * @param tid 一级标签id
     * @return
     */
    List<Tag> selectResourcesAllTags(int tid);

    /**
     * 查询我可以发帖的圈子
     * @param userId
     * @return
     */
    List<TagCircleVo> selectTagsCircleMyCan(int userId);

}
