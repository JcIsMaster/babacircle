package com.example.babacircle.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.babacircle.user.entity.User;
import com.example.babacircle.user.entity.UserAdmin;
import org.apache.ibatis.annotations.*;
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

    /**
     * 根据账号密码查询管理员用户
     * @param account
     * @param password
     * @return
     */
    @Select("select * from tb_user_admin where account = #{account} and password = #{password} and is_status = 1")
    UserAdmin selectUserAdminOne(@Param("account") String account, @Param("password") String password);

    /**
     * 添加管理员（开放）用户
     * @param userAdmin
     * @return
     */
    @Insert("insert into tb_user_admin(account,password,role_id,avatar,create_at,update_at,remarks,associated_id) value(#{userAdmin.account}," +
            "#{userAdmin.password},${userAdmin.roleId},#{userAdmin.avatar},#{userAdmin.createAt},#{userAdmin.createAt},#{userAdmin.remarks}," +
            "${userAdmin.associatedId})")
    @Options(useGeneratedKeys=true, keyProperty="userAdmin.id",keyColumn="id")
    int addAdminUser(@Param("userAdmin") UserAdmin userAdmin);

    /**
     * 修改管理员用户权限、关联
     * @param userAdmin
     * @return
     */
    @Update("update tb_user_admin set role_id = ${userAdmin.roleId},associated_id = ${userAdmin.associatedId} where id = ${userAdmin.id}")
    int updateAdminUserAuthority(@Param("userAdmin") UserAdmin userAdmin);

    /**
     * 查询管理员用户列表
     * @param account
     * @param role
     * @return
     */
    @Select("<script>" +
            "select id,account,role_id,avatar,create_at,update_at,remarks,associated_id,is_status from tb_user_admin where is_status = 1" +
            "<if test = 'account != null'> and account LIKE CONCAT('%',#{account},'%')</if>" +
            "<if test = 'role != null'> and role_id = ${role}</if>" +
            "</script>")
    List<UserAdmin> queryUserAdminList(@Param("account") String account, @Param("role") Integer role);

    /**
     * 查询管理员用户数
     * @param account
     * @param role
     * @return
     */
    @Select("<script>" +
            "select count(*) from tb_user_admin where is_status = 1" +
            "<if test = 'account != null'> and account LIKE CONCAT('%',#{account},'%')</if>" +
            "<if test = 'role != null'> and role_id = ${role}</if>" +
            "</script>")
    int queryUserAdminCount(@Param("account") String account, @Param("role") Integer role);
}
