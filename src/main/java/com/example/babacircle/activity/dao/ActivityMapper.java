package com.example.babacircle.activity.dao;

import com.example.babacircle.activity.entity.Activity;
import com.example.babacircle.activity.entity.ActivityParticipate;
import com.example.babacircle.activity.vo.ActivityListVo;
import com.example.babacircle.common.utils.Paging;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author JC
 * @date 2021/9/24 16:51
 */
@Component
public interface ActivityMapper {

    /**
     * 查询活动列表
     * @param type
     * @param title
     * @param area
     * @param sql
     * @return
     */
    @Select("<script>"+
            "select a.id,a.activity_title,a.activity_cover,a.activity_area,a.sponsor_user_id,a.activity_end_time,a.is_delete,b.user_name as sponsorUserName," +
            "count(c.id) as numberOfParticipants from tb_activity a left join tb_user b on a.sponsor_user_id = b.id " +
            "left join tb_activity_participate c on a.id = c.activity_id and c.is_delete = 0 " +
            "<if test='type != 2'>where a.is_delete = ${type}</if>" +
            "<if test='title != null and type != 2'>and a.activity_title LIKE CONCAT('%',#{title},'%')</if>" +
            "<if test='title != null and type == 2'>where a.activity_title LIKE CONCAT('%',#{title},'%')</if>" +
            "<if test='title != null or type != 2'> and </if>" +
            "<if test='title == null and type == 2 and area != null'>where </if>" +
            "<if test='area != null'>a.activity_area LIKE CONCAT('%',#{area},'%')</if>" +
            "GROUP BY a.id ORDER BY a.activity_start_time desc ${sql}" +
            "</script>")
    List<ActivityListVo> queryActivityList(@Param("type") int type, @Param("title") String title, @Param("area") String area, @Param("sql") String sql);

    /**
     * 条件查询活动数量
     * @param type
     * @param title
     * @param area
     * @return
     */
    @Select("<script>"+
            "select count(*) from tb_activity " +
            "<if test='type != 2'>where is_delete = ${type}</if>" +
            "<if test='title != null and type != 2'>and activity_title LIKE CONCAT('%',#{title},'%')</if>" +
            "<if test='title != null and type == 2'>where activity_title LIKE CONCAT('%',#{title},'%')</if>" +
            "<if test='title != null or type != 2'> and </if>" +
            "<if test='title == null and type == 2 and area != null'>where </if>" +
            "<if test='area != null'>activity_area LIKE CONCAT('%',#{area},'%')</if>" +
            "</script>")
    int queryActivityCount(@Param("type") int type, @Param("title") String title, @Param("area") String area);

    /**
     * 查询活动详情
     * @param id
     * @return
     */
    @Select("select * from tb_activity where id = ${id}")
    Activity queryActivityDetailsById(@Param("id") int id);

    /**
     * 创建活动
     * @param activity
     * @return
     */
    @Insert("insert into tb_activity(activity_title,activity_cover,activity_content,activity_sponsor,activity_time,activity_area,activity_location," +
            "sponsor_user_id,activity_fee,activity_fee_desc,activity_rule,activity_start_time,activity_end_time) values(#{activity.activityTitle}," +
            "#{activity.activityCover},#{activity.activityContent},#{activity.activitySponsor},#{activity.activityTime},#{activity.activityArea},#{activity.activityLocation}," +
            "${activity.sponsorUserId},${activity.activityFee},#{activity.activityFeeDesc},#{activity.activityRule},#{activity.activityStartTime}," +
            "#{activity.activityEndTime})")
    @Options(useGeneratedKeys=true, keyProperty="activity.id",keyColumn="id")
    int createActivity(@Param("activity") Activity activity);

    /**
     * 删除活动
     * @param id
     * @return
     */
    @Update("update tb_activity set is_delete = 1 where id = ${id}")
    int delActivity(@Param("id") int id);

    /**
     * 查询活动参加者列表
     * @param id
     * @param sql
     * @return
     */
    @Select("select * from tb_activity_participate where activity_id = ${id} and is_delete = 0 order by create_at desc ${sql}")
    List<ActivityParticipate> queryActivityParticipates(@Param("id") int id, @Param("sql") String sql);

    /**
     * 查询活动参加者数量
     * @param id
     * @return
     */
    @Select("select count(*) from tb_activity_participate where activity_id = ${id} and is_delete = 0")
    int queryActivityParticipatesCount(@Param("id") int id);

    /**
     * 根据用户查询活动列表
     * @param userId
     * @param sql
     * @return
     */
    @Select("select a.id,a.activity_title,a.activity_cover,a.activity_area,a.sponsor_user_id,a.activity_end_time,a.is_delete,b.user_name as sponsorUserName," +
            "count(c.id) as numberOfParticipants from tb_activity a left join tb_user b on a.sponsor_user_id = b.id " +
            "left join tb_activity_participate c on a.id = c.activity_id and c.is_delete = 0 " +
            "where a.sponsor_user_id = ${userId} GROUP BY a.id ORDER BY a.activity_start_time desc ${sql}")
    List<ActivityListVo> queryActivityListByUserId(@Param("userId") int userId, @Param("sql") String sql);

    /**
     * 查询用户发布的活动数量
     * @param userId
     * @return
     */
    @Select("select count(*) from tb_activity where sponsor_user_id = ${userId}")
    int queryActivityCountByUserId(@Param("userId") int userId);
}
