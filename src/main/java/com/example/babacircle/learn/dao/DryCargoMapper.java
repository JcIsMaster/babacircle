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
     * @param title 标题
     * @param userName 用户名
     * @return
     */
    @Select("select a.*,b.tag_name,c.user_name from tb_dry_goods a INNER JOIN tb_tags b on a.tags_two=b.id inner join tb_user c on a.u_id=c.id where a.is_delete=1 ${title} ${userName} order by a.create_at desc")
    List<DryGoodsVo> queryAllDryCargo(@Param("title") String title,@Param("userName") String userName);

    /**
     * 统计数量
     * @param title 标题
     * @return
     */
    @Select("select COALESCE(count(*),0) from tb_dry_goods a INNER JOIN tb_tags b on a.tags_two=b.id where a.is_delete=1 ${title}")
    int countAllDryCargo(@Param("title") String title);

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
