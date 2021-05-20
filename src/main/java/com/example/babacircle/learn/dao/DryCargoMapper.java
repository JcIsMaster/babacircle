package com.example.babacircle.learn.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.babacircle.learn.entity.DryGoods;
import com.example.babacircle.learn.entity.Tag;
import com.example.babacircle.learn.vo.DryGoodsVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author MQ
 * @date 2021/4/23 13:52
 */
@Component
public interface DryCargoMapper extends BaseMapper<DryGoods> {

    /**
     * 查询所有干货信息
     * @return
     */
    @Select("select a.id,a.title,a.favour,a.collect,a.description,a.cover_img,a.content,a.create_at,b.tag_name,c.user_name from tb_dry_goods a INNER JOIN tb_tags b on a.tags_two=b.id inner join tb_user c on a.u_id=c.id where a.is_delete=1 ${sql} order by a.create_at desc ")
    List<DryGoodsVo> queryAllDryCargo(@Param("sql") String sql);

    /**
     * 统计数量
     * @param sql 拼接字段
     * @return
     */
    @Select("select COALESCE(count(*),0) from tb_dry_goods a INNER JOIN tb_tags b on a.tags_two=b.id inner join tb_user c on a.u_id=c.id where a.is_delete=1 ${sql}")
    int countAllDryCargo(@Param("sql") String sql);

    /**
     * 根据帖子id查询该帖子点赞数量
     * @param id 帖子id
     * @return
     */
    @Select("select COALESCE(count(*),0) from tb_learn_give where learn_type=1 and zq_id=${id} and give_cancel=1")
    int countPostGiveNumber(@Param("id") int id);

    /**
     * 根据帖子id查询该帖子收藏数量
     * @param id 帖子id
     * @return
     */
    @Select("select COALESCE(count(*),0) from tb_learn_collect where learn_type=1 and zq_id=${id} and give_cancel=1")
    int countPostCollectNumber(@Param("id") int id);

    /**
     * 根据帖子id查询该帖子评论数量
     * @param id 帖子id
     * @return
     */
    @Select("select COALESCE(count(*),0) from tb_learn_comment where t_type=1 and t_id=${id}")
    int countPostCommentNumber(@Param("id") int id);

    /**
     * 根据一级标签id查询二级标签
     * @param tid 一级标签id
     * @return
     */
    @Select("select * from tb_tags where t_id=${tid} and is_delete=1")
    List<Tag> selectResourcesAllTags(@Param("tid") int tid);

}
