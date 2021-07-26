package com.example.babacircle.user.controller;

import com.example.babacircle.user.entity.User;
import com.example.babacircle.user.service.IUserService;
import com.example.babacircle.user.vo.UserHtVo;
import com.example.babacircle.util.ResultLayUi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author MQ
 * @date 2021/4/21 9:36
 */
@Api(tags = "用户API")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/UserController")
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     *
     * 根据条件查询用户
     * @return
     */
    @ApiOperation(value = "根据条件查询用户",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/queryAllUserForSql")
    public ResultLayUi queryAllUserForSql(UserHtVo userHtVo, Integer page, Integer limit) throws Exception {
        return iUserService.queryAllUserForSql(userHtVo, page, limit);
    }

    @ApiOperation(value = "查询用户名密码", notes = "查询用户名密码")
    @ResponseBody
    @GetMapping("/selectUserNamePassword")
    public ResultLayUi selectUserNamePassword(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        ResultLayUi resultLayUi = iUserService.selectUserNamePassword(userName, password);
        return resultLayUi;
    }

    @ApiOperation(value = "查询单个用户", notes = "查询用户名密码")
    @ResponseBody
    @PostMapping("/selectUserById")
    public User selectUserById(int userId) {
        return iUserService.selectUserById(userId);
    }

    @ApiOperation(value = "批量删除用户", notes = "查询用户名密码")
    @ResponseBody
    @PostMapping("/batchDeleteUsers")
    public int batchDeleteUsers(@RequestParam("id") Integer[] id) {
        return iUserService.batchDeleteUsers(id);
    }

    @ApiOperation(value = "修改用户信息", notes = "查询用户名密码")
    @ResponseBody
    @PostMapping("/updateUser")
    public int updateUser(User user) {
        System.out.println(user.toString());
        System.out.println(iUserService.updateUser(user));
        return iUserService.updateUser(user);
    }





}
