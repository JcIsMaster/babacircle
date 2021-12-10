package com.example.babacircle.circle.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.babacircle.circle.entity.Circle;
import com.example.babacircle.circle.entity.Haplont;
import com.example.babacircle.circle.vo.CircleClassificationVo;
import com.example.babacircle.circle.vo.CircleLabelVo;
import com.example.babacircle.resource.entity.Img;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author MQ
 * @date 2021/5/3 16:50
 */
@Component
public interface CircleMapper extends BaseMapper<Circle> {

    /**
     * 根据条件查询所有圈子
     * @param sql 条件拼接
     * @param circle
     * @return
     */
    @Select({"<script>"+
            "select a.cover,a.id,a.content,b.tag_name,a.type,a.video,a.favour,a.collect,a.browse,a.title,a.create_at,c.avatar,c.id as uId,c.user_name " +
            "from tb_circles a INNER JOIN tb_user c on a.user_id=c.id INNER JOIN tb_tags b on a.tags_two=b.id " +
            "where a.is_delete=1" +
            "<if test='circle.tagsTwo!=0'>" +
            " and tags_two=${circle.tagsTwo} " +
            "</if>" +
            "${sql} ORDER BY a.create_at desc" +
            "</script>"})
    List<CircleLabelVo> selectAllPosting(@Param("sql") String sql,@Param("circle") Circle circle);

    /**
     * 根据条件统计数量
     * @param sql
     * @param tagsTwo
     * @return
     */
    @Select({"<script>"+
            "select COALESCE(count(*),0) from tb_circles a INNER JOIN tb_tags b on a.tags_two=b.id INNER JOIN tb_user c on a.user_id=c.id where a.is_delete=1" +
            "<if test='tagsTwo!=0'>" +
            " and tags_two=${tagsTwo}" +
            "</if>" +
            " ${sql}" +
            "</script>"})
    Integer selectAllPostingCount(@Param("sql") String sql,@Param("tagsTwo") int tagsTwo);

    /**
     * 增加圈子帖子
     * @param circle
     * @return
     */
    @Insert("insert into tb_circles(content,tags_one,tags_two,type,video,cover,create_at,user_id,title,haplont_type)values(#{circle.content},${circle.tagsOne},${circle.tagsTwo},${circle.type},#{circle.video},#{circle.cover},#{circle.createAt},${circle.userId},#{circle.title},${circle.haplontType})")
    @Options(useGeneratedKeys=true, keyProperty="circle.id",keyColumn="id")
    int addCirclePost(@Param("circle") Circle circle);

    /**
     * 添加图片
     * @param img
     * @return
     */
    @Insert("insert into tb_img(z_id,img_url,type,create_at)values(${img.zId},#{img.imgUrl},${img.type},#{img.createAt})")
    int addImg(@Param("img") Img img);

    /**
     * 根据帖子id查询图片
     * @param id 帖子id
     * @return
     */
    @Select("select img_url from tb_img where z_id=${id} ")
    String[] queryImgById(@Param("id") int id);

    /**
     * 根据帖子id删除对应的图片信息
     * @param id
     * @return
     */
    @Delete("delete from tb_img where z_id=${id} and type=1")
    int deleteResourceImg(@Param("id") int id);

    /**
     * 批量删除
     * @param id
     * @return
     */
    @Update("update tb_circles set is_delete=0 where id = ${id}")
    Integer deletes(@Param("id") int id);

    /**
     * 批量增加图片
     * @param zId 帖子id
     * @param imgUrl 图片地址
     * @param createAt 创建时间
     * @param postType 帖子类型
     * @return
     */
    @Insert({"<script>" +
            "insert into tb_img(z_id,img_url,type,create_at) VALUES  " +
            "<foreach collection='imgUrl' item='item' index='index' separator=','>" +
            "(${zId},#{item},${postType},#{createAt})" +
            "</foreach>" +
            "</script>"})
    int addCircleImg(@Param("zId") int zId, @Param("imgUrl") String[] imgUrl,@Param("createAt") String createAt,@Param("postType") int postType);

    /**
     * 根据标签id查询单元体导航栏
     * @param tagId 标签id
     * @return
     */

    @Select("SELECT a.id,a.h_name FROM `tb_haplont` a INNER JOIN tb_tag_haplont b on  a.id=b.haplont_id where b.tag_id=${tagId} and a.id>2")
    List<Haplont> selectHaplontByTagId(@Param("tagId") int tagId);

    /**
     * 查询单个圈子的帖子
     * @param id 帖子id
     * @return
     */
    @Select("select a.type,a.forwarding_number,a.id,a.content,a.browse,a.video,a.cover,a.create_at,b.tag_name,b.id as tagId,a.haplont_type" +
            ",c.avatar,c.id as uId,c.user_name,  ifnull(d.giveNumber,0) as giveNumber ,ifnull(e.uu,0) as numberPosts " +
            " from tb_circles a LEFT JOIN (select count(*) as giveNumber,zq_id from tb_circles_give where give_cancel=1 GROUP BY zq_id) d on a.id=d.zq_id " +
            "LEFT JOIN (select COALESCE(count(*),0) as uu,t_id from tb_comment GROUP BY t_id) e on a.id=e.t_id " +
            "INNER JOIN tb_user c on a.user_id=c.id INNER JOIN tb_tags b on a.tags_two=b.id  " +
            " where a.id=${id} and a.is_delete=1")
    CircleClassificationVo querySingleCircle(@Param("id") int id);

    /**
     * 根据帖子id查询当前帖子图片
     * @param id
     * @return
     */
    @Select("select img_url from tb_img where z_id=${id} and type=1")
    String[] selectImgByPostId(@Param("id") int id);

    /**
     * 删除资源 圈子
     * @param id 标签id
     * @return
     */
    @Update("update tb_circles set is_delete = 0 where tags_two=${id}")
    int deletePosts(@Param("id") int id);

    /**
     * 查询用户创建的圈子的数量
     * @param userId
     * @return
     */
    @Select("select count(id) from tb_community where user_id = ${userId} and is_delete = 1")
    int queryCircleCountByUserId(@Param("userId") int userId);

    /**
     * 根据用户id查询圈子文章数量
     * @param userId 用户id
     * @return
     */
    @Select("select count(a.id) from tb_circles a INNER JOIN tb_user c on a.user_id=c.id INNER JOIN tb_tags b on a.tags_two=b.id where a.user_id=${userId} and a.is_delete=1")
    int queryHavePostedCircleNum(@Param("userId") int userId);

    /**
     * 查询用户发的帖子
     * @param userId
     * @return
     */
    @Select("select a.cover,a.id,a.content,b.tag_name,a.type,a.video,a.favour,a.collect,a.browse,a.title,a.create_at,c.avatar,c.id as uId,c.user_name " +
            "from tb_circles a INNER JOIN tb_user c on a.user_id=c.id INNER JOIN tb_tags b on a.tags_two=b.id " +
            "where a.is_delete=1 and c.id = ${userId} order by a.create_at desc")
    List<CircleLabelVo> selectPostsByUserId(@Param("userId") int userId);

    /**
     * 查询用户发的帖子数量
     * @param userId
     * @return
     */
    @Select("select count(1) from tb_circles a INNER JOIN tb_user c on a.user_id = c.id " +
            "where a.is_delete = 1 and c.id = ${userId}")
    int selectPostsNumByUserId(@Param("userId") int userId);
}
