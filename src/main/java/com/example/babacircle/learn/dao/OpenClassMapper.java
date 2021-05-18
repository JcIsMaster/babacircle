package com.example.babacircle.learn.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.babacircle.learn.entity.PublicClass;
import com.example.babacircle.learn.vo.OpenClassVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author MQ
 * @date 2021/5/7 15:31
 */
@Component
public interface OpenClassMapper extends BaseMapper<PublicClass> {


    /**
     * 查询所有公开课新
     * @param sql
     * @return
     */
    @Select("select a.id,a.title,a.cover_img,a.class_list,a.price,a.collect,a.buyer_num,a.create_at,b.tag_name,c.user_name,c.id as userId " +
            "from tb_public_class a INNER JOIN tb_tags b on a.tags_two=b.id INNER JOIN tb_user c on a.u_id=c.id " +
            "where a.is_delete=1 ${sql} ORDER BY a.create_at desc ")
    List<OpenClassVo> queryAllOpenClass(@Param("sql") String sql);

    /**
     * 统计所有公开课新
     * @param sql
     * @return
     */
    @Select("select COALESCE(count(*),0) from tb_public_class a INNER JOIN tb_tags b on a.tags_two=b.id INNER JOIN tb_user c on a.u_id=c.id " +
            "where a.is_delete=1 ${sql} ")
    int countAllOpenClass(@Param("sql") String sql);

}
