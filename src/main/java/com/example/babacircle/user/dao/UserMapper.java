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
    @Select("select id,user_name,user_sex,avatar,mobile,email,create_at,m_code,is_delete,open_id,introduce,m_code from tb_user  ${sql} order by create_at desc")
    List<User> queryAllUserForSql(@Param("sql")String sql);

    /**
     * 统计用户数量
     * @return
     */
    @Select("select COALESCE(count(*),0) from tb_user where is_delete=1 ")
    Integer userCount();

    /**
     * 根据id查询用户
     * @param Id
     * @return
     */
    @Select("select a.id,a.user_name,a.user_sex,a.birthday,a.mobile,a.email,a.avatar,a.introduce,a.open_id,a.create_at,b.can_withdraw_gold_coins,b.may_not_withdraw_gold_coins from tb_user a inner join tb_user_gold_coins b on a.id=b.user_id where a.id=${Id}")
    User selectUserById(@Param("Id") int Id);
}
