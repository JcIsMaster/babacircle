package com.example.babacircle.resource.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.babacircle.resource.entity.Img;
import com.example.babacircle.resource.entity.Resources;
import com.example.babacircle.resource.vo.ResourcesLabelVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author MQ
 * @date 2021/4/20 14:23
 */
@Component
public interface ResourcesMapper extends BaseMapper<Resources> {

    /**
     * 后台
     * 查询所有资源数据
     * @param sql 条件
     * @return
     */
    @Select("select a.id,a.cover,a.content,b.tag_name,a.type,a.video,a.favour,a.collect,a.browse,a.title,a.create_at,c.avatar,c.id as uId,c.user_name " +
            "from tb_resources a INNER JOIN tb_user c on a.u_id=c.id INNER JOIN tb_tags b on a.tags_two=b.id where a.is_delete=1 ${sql} order by a.create_at desc")
    List<ResourcesLabelVo> selectResourcesAllPosting(@Param("sql") String sql);



    /**
     * 根据条件统计数量
     * @param sql
     * @return
     */
    @Select("select COALESCE(count(*),0) from tb_resources a INNER JOIN tb_user c on a.u_id=c.id INNER JOIN tb_tags b on a.tags_two=b.id where a.is_delete=1 ${sql}")
    Integer selectResourcesAllPostingCount(@Param("sql") String sql);

    /**
     * 增加资源帖子
     * @param resources
     * @return
     */
    @Insert("insert into tb_resources(content,tags_one,tags_two,type,video,cover,create_at,u_id,title)values(#{resources.content},${resources.tagsOne},${resources.tagsTwo},${resources.type},#{resources.video},#{resources.cover},#{resources.createAt},${resources.uId},#{resources.title})")
    @Options(useGeneratedKeys=true, keyProperty="resources.id",keyColumn="id")
    int addResourcesPost(@Param("resources") Resources resources);

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
    int addResourceImg(@Param("zId") int zId, @Param("imgUrl") String[] imgUrl,@Param("createAt") String createAt,@Param("postType") int postType);

    /**
     * 根据帖子id删除对应的图片信息
     * @param id
     * @return
     */
    @Delete("delete  from tb_img where z_id=${id} and type=0")
    int deleteResourceImg(@Param("id") int id);
}
