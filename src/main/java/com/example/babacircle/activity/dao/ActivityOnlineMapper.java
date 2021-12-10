package com.example.babacircle.activity.dao;

import com.example.babacircle.activity.entity.ActivityOnline;
import com.example.babacircle.activity.vo.ActivityOnlineListVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author JC
 * @date 2021/11/22 14:52
 */
@Component
public interface ActivityOnlineMapper {

    /**
     * 查询线上活动列表
     * @param status
     * @param title
     * @param sql
     * @return
     */
    @Select("<script>" +
            "select a.id,a.cover,a.title,a.initiator_user_id,b.user_name,a.finish_time,a.stock," +
            "(select count(1) from tb_activity_online_participate where activity_online_id = a.id) as participateNum,a.activity_level,a.is_status " +
            "from tb_activity_online a left join tb_user b on a.initiator_user_id = b.id " +
            "<if test='status!=null and title!=null'>" +
            "where a.is_status = ${status} and a.title LIKE CONCAT('%',#{title},'%') " +
            "</if>" +
            "<if test='status!=null and title==null'>" +
            "where a.is_status = ${status} " +
            "</if>" +
            "<if test='status==null and title!=null'>" +
            "where a.title LIKE CONCAT('%',#{title},'%') " +
            "</if>" +
            "order by a.create_at desc ${sql}" +
            "</script>")
    List<ActivityOnlineListVo> queryActivityOnlineList(@Param("status") Integer status,@Param("title") String title,@Param("sql") String sql);


    /**
     * 创建线上活动
     * @param activityOnline
     * @return
     */
    @Insert("insert into tb_activity_online(cover,title,initiator_user_id,original_price,discount_price,stock,finish_time,shop_name,shop_url,shop_phone,we_chat_number," +
            "description,create_at,single_discount_rate,activity_level) values(#{activityOnline.cover},#{activityOnline.title},${activityOnline.initiatorUserId},${activityOnline.originalPrice}," +
            "${activityOnline.discountPrice},${activityOnline.stock},#{activityOnline.finishTime},#{activityOnline.shopName},#{activityOnline.shopUrl},#{activityOnline.shopPhone}," +
            "#{activityOnline.weChatNumber},#{activityOnline.description},#{activityOnline.createAt},${activityOnline.singleDiscountRate},${activityOnline.activityLevel})")
    @Options(useGeneratedKeys=true, keyProperty="activityOnline.id",keyColumn="id")
    int createActivityOnline(@Param("activityOnline") ActivityOnline activityOnline);

}
