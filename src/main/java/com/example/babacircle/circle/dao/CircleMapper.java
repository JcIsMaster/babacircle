package com.example.babacircle.circle.dao;

import com.example.babacircle.circle.vo.CircleLabelVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author MQ
 * @date 2021/5/3 16:50
 */
@Component
public interface CircleMapper {

    /**
     * 根据条件查询所有圈子
     * @param sql 条件拼接
     * @return
     */
    @Select("select a.id,a.content,b.tag_name,a.type,a.video,a.favour,a.collect,a.browse,a.title,a.create_at,c.avatar,c.id as uId,c.user_name " +
            "from tb_circles a INNER JOIN tb_user c on a.u_id=c.id INNER JOIN tb_tag b on a.tags_one=b.id " +
            "where a.is_delete=1 ${sql} ORDER BY a.create_at desc")
    List<CircleLabelVo> selectAllPosting(@Param("sql") String sql);

    /**
     * 根据条件统计数量
     * @param sql
     * @return
     */
    @Select("select COALESCE(count(*),0) from tb_circles a INNER JOIN tb_tags b on a.tags_two=b.id INNER JOIN tb_user c on a.u_id=c.id where a.is_delete=1 ${sql}")
    Integer selectAllPostingCount(@Param("sql") String sql);
}
