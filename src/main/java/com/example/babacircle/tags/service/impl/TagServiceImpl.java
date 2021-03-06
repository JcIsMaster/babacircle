package com.example.babacircle.tags.service.impl;


import com.example.babacircle.tags.dao.TagMapper;
import com.example.babacircle.tags.entity.Tag;
import com.example.babacircle.tags.service.ITagService;
import com.example.babacircle.tags.vo.TagCircleVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author MQ
 * @date 2021/1/21 16:08
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class TagServiceImpl implements ITagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<TagCircleVo> selectResourcesAllTag() {
        List<TagCircleVo> tags = tagMapper.selectResourcesAllTag();
        return tags;
    }

    @Override
    public List<Tag> selectResourcesAllTags(int tid) {
        //根据一级标签查询二级标签数据
        List<Tag> tags = tagMapper.selectResourcesAllTags(tid);
        return tags;
    }

    @Override
    public List<TagCircleVo> selectTagsCircleMyCan(int userId) {
        //查询我可以发帖的圈子
        List<TagCircleVo> vos = tagMapper.selectTagsCircleMyCan(userId);
        return vos;
    }

}
