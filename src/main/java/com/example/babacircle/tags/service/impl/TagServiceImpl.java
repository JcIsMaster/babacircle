package com.example.babacircle.tags.service.impl;


import com.example.babacircle.tags.dao.TagMapper;
import com.example.babacircle.tags.entity.Tag;
import com.example.babacircle.tags.service.ITagService;
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
    public List<Tag> selectResourcesAllTag(int type) {
        List<Tag> tags = tagMapper.selectResourcesAllTag(type);
        for (int i=0;i<tags.size();i++){
            if("人才".equals(tags.get(i).getTagName())){
                tags.remove(i);
            }
        }
        return tags;
    }

    @Override
    public List<Tag> selectResourcesAllTags(int tid) {
        //根据一级标签查询二级标签数据
        List<Tag> tags = tagMapper.selectResourcesAllTags(tid);
        return tags;
    }




}
