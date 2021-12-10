package com.example.babacircle.virtual.dao;

import com.example.babacircle.virtual.vo.UserVirtualVo;
import com.example.babacircle.virtual.vo.VirtualAccountVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author JC
 * @date 2021/11/18 15:59
 */
@Component
public interface VirtualAccountMapper {

    /**
     * 根据条件查询虚拟号用户
     * @param type
     * @param sql
     * @return
     */
    @Select("<script>" +
            "select a.id,a.user_name,a.user_sex,a.introduce from tb_user a left join tb_parameter b on a.id = b.user_id " +
            "where a.open_id = '0'" +
            "<if test='type==0'>" +
            " and b.json_class-> '$.is_shops' = 0" +
            "</if>" +
            "<if test='type==1'>" +
            " and b.json_class-> '$.is_shops' = 1" +
            "</if>" +
            "<if test='type==2'>" +
            " and a.id not in (select user_id from tb_parameter)" +
            "</if>" +
            " ${sql}" +
            "</script>")
    List<VirtualAccountVo> queryVirtualAccount(@Param("type") int type,@Param("sql") String sql);

    /**
     * 根据条件查询虚拟号用户数量
     * @param type
     * @return
     */
    @Select("<script>" +
            "select count(a.id) from tb_user a left join tb_parameter b on a.id = b.user_id " +
            "where a.open_id = '0'" +
            "<if test='type==0'>" +
            " and b.json_class-> '$.is_shops' = 0" +
            "</if>" +
            "<if test='type==1'>" +
            " and b.json_class-> '$.is_shops' = 1" +
            "</if>" +
            "</script>")
    int queryVirtualAccountCount(@Param("type") int type);

    /**
     * 根据id查询虚拟号用户信息
     * @param type
     * @param userId
     * @return
     */
    @Select("<script>" +
            "select a.id,a.user_name,a.avatar,a.user_sex,a.birthday,a.introduce,a.curr_province,a.city,a.county,c.level" +
            "<if test='type==0'>" +
            ",b.json_class-> '$.is_shops' as isShop,b.json_class-> '$.platform' as platform,b.json_class-> '$.stor' as stor " +
            "</if>" +
            "<if test='type==1'>" +
            ",b.json_class-> '$.is_shops' as isShop,b.json_class-> '$.is_status' as isStatus,b.json_class-> '$.job' as job,b.json_class-> '$.status' as status " +
            "</if>" +
            "from tb_user a left join tb_parameter b on a.id = b.user_id inner join tb_honored c on a.id = c.user_id where a.id = ${userId}" +
            "</script>")
    UserVirtualVo queryVirtualAccountById(@Param("type") int type, @Param("userId") int userId);

}
