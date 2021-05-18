package com.example.babacircle.circle.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.babacircle.circle.entity.Circle;
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
     * @return
     */
    @Select("select a.cover,a.id,a.content,b.tag_name,a.type,a.video,a.favour,a.collect,a.browse,a.title,a.create_at,c.avatar,c.id as uId,c.user_name " +
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

    /**
     * 增加圈子帖子
     * @param circle
     * @return
     */
    @Insert("insert into tb_circles(content,tags_one,tags_two,type,video,cover,create_at,u_id,title,haplont_type)values(#{circle.content},${circle.tagsOne},${circle.tagsTwo},${circle.type},#{circle.video},#{circle.cover},#{circle.createAt},${circle.uId},#{circle.title},${circle.haplontType})")
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
     * 批量增加图片
     * @param zId 帖子id
     * @param imgUrl 图片地址
     * @param createAt 创建时间
     * @param postType 帖子类型
     * @return
     */
    @Insert("<script>" +
            "insert into tb_img(z_id,img_url,type,create_at) VALUES  " +
            "<foreach collection='imgUrl' item='item' index='index' separator=','>" +
            "(${zId},#{item},${postType},#{createAt})" +
            "</foreach>" +
            "</script>")
    int addCircleImg(@Param("zId") int zId, @Param("imgUrl") String[] imgUrl,@Param("createAt") String createAt,@Param("postType") int postType);
}
