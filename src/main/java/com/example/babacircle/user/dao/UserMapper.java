package com.example.babacircle.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.babacircle.user.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author MQ
 * @date 2021/4/21 9:37
 */
@Component
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据条件所有用户信息
     * @param sql 拼接的参数
     * @return
     */
    @Select("select id,user_name,user_sex,avatar,create_at,m_code,is_delete,open_id,introduce,m_code from tb_user  ${sql} order by create_at desc")
    List<User> queryAllUserForSql(@Param("sql")String sql);

    /**
     * 统计用户数量
     * @return
     */
    @Select("select COALESCE(count(*),0) from tb_user where is_delete=1 ")
    Integer userCount();
}
