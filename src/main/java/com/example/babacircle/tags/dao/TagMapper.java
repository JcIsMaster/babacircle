package com.example.babacircle.tags.dao;


import com.example.babacircle.tags.entity.Tag;
import com.example.babacircle.tags.vo.TagCircleVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author MQ
 * @date 2021/1/21 16:00
 */
@Component
public interface TagMapper {

    /**
     * 查询所有圈子
     * @return
     */
    @Select("select tag_id as id,community_name as tagName from tb_community where is_delete = 1")
    List<TagCircleVo> selectResourcesAllTag();

    /**
     * 查询我可以发帖的圈子
     * @param userId
     * @return
     */
    @Select("select a.tag_id as id,a.community_name as tagName from tb_community a inner join tb_community_user b " +
            "on b.community_id = a.id where a.is_delete = 1 and " +
            "(a.whether_official = 1 or b.user_id = ${userId}) GROUP BY a.id order by a.id")
    List<TagCircleVo> selectTagsCircleMyCan(@Param("userId") int userId);

    /**
     * 根据一级标签id查询二级标签
     * @param tid 一级标签id
     * @return
     */
    @Select("select * from tb_tags where t_id=${tid} and is_delete=1")
    List<Tag> selectResourcesAllTags(@Param("tid") int tid);



    /**
     * 根据二级标签id查询一级标签
     * @param id 二级标签id
     * @return
     */
    @Select("select t_id from tb_tags where id=${id} and is_delete=1")
    int queryLabelAccordingSecondaryId(@Param("id") int id);

    /**
     * 添加二级标签
     * @param tag
     * @return
     */
    @Insert("insert into tb_tags (tag_name,t_id,type,create_at)values(#{tag.tagName},${tag.tId},${tag.type},#{tag.createAt})")
    @Options(useGeneratedKeys=true, keyProperty="tag.id", keyColumn="id")
    int addTag(@Param("tag") Tag tag);

    /**
     * 添加标签和导航栏的中间表
     * @param tagId 标签id 对应tb_tags表中的id
     * @param haplontId 单元体id 对应 tb_haplont表中的id
     * @return
     */
    @Insert("insert into tb_tag_haplont(tag_id,haplont_id)values(${tagId},${haplontId})")
    int addTagHaplont(@Param("tagId") int tagId,@Param("haplontId") int haplontId);

    /**
     * 根据圈子改动修改对应的标签名
     * @param tagName
     * @param tagId
     * @return
     */
    @Update("update tb_tags set tag_name = #{tagName} where id = ${tagId}")
    int updateTagNameForCommunity(@Param("tagName") String tagName,@Param("tagId") int tagId);

}
