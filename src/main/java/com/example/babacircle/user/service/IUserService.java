package com.example.babacircle.user.service;

import com.example.babacircle.user.entity.User;
import com.example.babacircle.user.entity.UserAdmin;
import com.example.babacircle.user.vo.UserHtVo;
import com.example.babacircle.util.ResultLayUi;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author MQ
 * @date 2021/4/21 9:37
 */
public interface IUserService {

    /**
     * 根据条件查询用户
     * @param userHtVo
     * @param page
     * @param limit
     * @return
     */
    ResultLayUi queryAllUserForSql(UserHtVo userHtVo, Integer page, Integer limit);

    /**
     * 查询用户名密码
     * @param userName
     * @param password
     * @return
     */
    ResultLayUi selectUserNamePassword(String userName, String password);

    /**
     * 查询单个用户
     * @param userId
     * @return
     */
    User selectUserById(int userId);

    /**
     * 批量删除用户
     * @param id
     * @return
     */
    int batchDeleteUsers(@RequestParam("id") Integer[] id);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 添加管理员（开放）用户
     * @param userAdmin
     * @return
     */
    int addAdminUser(UserAdmin userAdmin);

    /**
     * 修改管理员用户权限、关联
     * @param userAdmin
     * @return
     */
    int updateAdminUserAuthority(UserAdmin userAdmin);

    /**
     * 查询管理员用户列表
     * @param account
     * @param role
     * @param page
     * @param limit
     * @return
     */
    ResultLayUi queryUserAdminList(String account, Integer role, Integer page, Integer limit);
}
