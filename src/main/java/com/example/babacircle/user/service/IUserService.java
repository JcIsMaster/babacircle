package com.example.babacircle.user.service;

import com.example.babacircle.user.entity.User;
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
     *
     * @param userName
     * @param password
     * @return
     */
    ResultLayUi selectUserNamePassword(String userName,  String password);
}
