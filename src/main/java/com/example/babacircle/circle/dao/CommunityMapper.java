package com.example.babacircle.circle.dao;

import com.example.babacircle.circle.entity.Community;
import com.example.babacircle.circle.entity.CommunityTopic;
import com.example.babacircle.circle.entity.CommunityUser;
import com.example.babacircle.circle.vo.CircleVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author JC
 * @date 2021/11/23 15:35
 */
@Component
public interface CommunityMapper {

    /**
     * 查询是否有同名圈子
     * @param communityName 圈子名
     * @return
     */
    @Select("select count(id) from tb_community where is_delete = 1 and community_name = #{communityName}")
    int querySameNameExist(@Param("communityName") String communityName);

    /**
     * 添加圈子
     * @param community
     * @return
     */
    @Insert("insert into tb_community(community_name,posters,user_id,introduce,announcement,create_at,tag_id,whether_public,community_type)" +
            "values(#{community.communityName},#{community.posters},${community.userId},#{community.introduce},#{community.announcement}" +
            ",#{community.createAt},#{community.tagId},${community.whetherPublic},${community.communityType})")
    @Options(useGeneratedKeys=true, keyProperty="community.id",keyColumn="id")
    int addCommunity(@Param("community") Community community);

    /**
     * 初始化圈子人数
     * @param communityUser
     * @return
     */
    @Insert("insert into tb_community_user(community_id,user_id,create_at)" +
            "values(${communityUser.communityId},${communityUser.userId},#{communityUser.createAt})")
    int addCommunityUser(@Param("communityUser") CommunityUser communityUser);

    /**
     * 查询所有官方圈子
     * @return
     */
    @Select("select * from tb_community where whether_official = 1 and is_delete = 1")
    List<Community> queryOfficialCircleList();

    /**
     * 查询所有话题
     * @return
     */
    @Select("select * from tb_community_topic")
    List<CommunityTopic> queryAllTopic();

    /**
     * 查询所有圈子
     * @param paging 分页
     * @return
     */
    @Select("select a.id,a.user_id,b.user_name,a.tag_id,a.community_name,a.posters,a.introduce,c.topic_name," +
            "(select count(tags_two) from tb_circles where is_delete = 1 and tags_two = a.tag_id) AS cnt from tb_community a " +
            "LEFT JOIN tb_user b on a.user_id = b.id LEFT JOIN tb_community_topic c on a.community_type = c.id " +
            "where a.is_delete = 1 ${paging}")
    List<CircleVo> allCircle( @Param("paging") String paging);

    /**
     * 查询所有圈子的数量
     * @return
     */
    @Select("select count(id) from tb_community where is_delete = 1")
    Integer allCircleCount();

    /**
     * 根据id查询创建的圈子
     * @param userId 用户id
     * @param paging 分页
     * @return
     */
    @Select("select a.id,a.user_id,b.user_name,a.tag_id,a.community_name,a.posters,a.introduce,c.topic_name," +
            "(select count(tags_two) from tb_circles where is_delete = 1 and tags_two = a.tag_id) AS cnt from tb_community a " +
            "LEFT JOIN tb_user b on a.user_id = b.id LEFT JOIN tb_community_topic c on a.community_type = c.id " +
            "where a.is_delete = 1 and a.user_id=${userId} ${paging}")
    List<CircleVo> myCreateCircle(@Param("userId") int userId, @Param("paging") String paging);

    /**
     * 查询创建的圈子的数量
     * @param userId
     * @return
     */
    @Select("select count(id) from tb_community where user_id = ${userId} and is_delete = 1")
    Integer myCircleCount(@Param("userId") int userId);

    /**
     * 查询我加入的圈子
     * @param userId 用户id
     * @param paging 分页
     * @return
     */
    @Select("select b.id,b.user_id,c.user_name,b.tag_id,b.community_name,b.posters,b.introduce,d.topic_name," +
            "(select count(tags_two) from tb_circles where is_delete = 1 and tags_two = b.tag_id) as cnt from tb_community_user a " +
            "INNER JOIN tb_community b on a.community_id=b.id LEFT JOIN tb_user c on b.user_id = c.id " +
            "LEFT JOIN tb_community_topic d on b.community_type = d.id " +
            "where a.user_id=${userId} and b.user_id <> ${userId} " +
            "and b.is_delete = 1 ${paging}")
    List<CircleVo> circleJoined(@Param("userId") int userId,@Param("paging") String paging);

    /**
     * 查询我加入的圈子数量
     * @param userId
     * @return
     */
    @Select("select count(b.id) from tb_community_user a " +
            "INNER JOIN tb_community b on a.community_id = b.id where a.user_id = ${userId} and b.user_id <> ${userId}")
    Integer circleJoinedCount(@Param("userId") int userId);

    /**
     * 统计每个圈子的人数
     * @param id 圈子id
     * @return
     */
    @Select("select IFNULL(count(*),0) from tb_community_user where community_id=${id}")
    int countCircleJoined(@Param("id") int id);

    /**
     * 查询用户是否加入了某圈子
     * @param id
     * @param userId
     * @return
     */
    @Select("select count(*) from tb_community_user where community_id = ${id} and user_id = ${userId}")
    int queryWhetherJoinedCircle(@Param("id") int id,@Param("userId") int userId);

    /**
     * 退出圈子
     * @param id 圈子id
     * @param userId 用户id
     * @return
     */
    @Delete("delete from tb_community_user where community_id=${id} and user_id=${userId}")
    int exitGroupChat(@Param("id") int id,@Param("userId") int userId);

    /**
     * 加入圈子
     * @param communityUser
     * @return
     */
    @Insert("insert into tb_community_user(community_id,user_id,create_at)values(${communityUser.communityId},${communityUser.userId},#{communityUser.createAt})")
    int joinCircle(@Param("communityUser") CommunityUser communityUser);

    /**
     * 根据id查看单个圈子信息
     * @param id
     * @return
     */
    @Select("select * from tb_community where id = ${id}")
    Community queryCommunityById(@Param("id") int id);

    /**
     * 修改圈子
     * @param community
     * @return
     */
    @Update("update tb_community set community_name=#{community.communityName},posters=#{community.posters},introduce=#{community.introduce}," +
            "announcement=#{community.announcement},whether_public=${community.whetherPublic} where id=${community.id}")
    int updateCommunity(@Param("community") Community community);

    /**
     * 删除圈子
     * @param id 圈子id
     * @return
     */
    @Update("update tb_community set is_delete=0 where id=${id}")
    int deleteCommunity(@Param("id") int id);
}
