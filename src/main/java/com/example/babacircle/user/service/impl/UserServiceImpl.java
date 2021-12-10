package com.example.babacircle.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.babacircle.common.constanct.CodeType;
import com.example.babacircle.common.exception.ApplicationException;
import com.example.babacircle.common.utils.SHA1Util;
import com.example.babacircle.user.dao.UserMapper;
import com.example.babacircle.user.entity.User;
import com.example.babacircle.user.entity.UserAdmin;
import com.example.babacircle.user.service.IUserService;
import com.example.babacircle.user.vo.UserHtVo;
import com.example.babacircle.util.ResultLayUi;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author MQ
 * @date 2021/4/21 9:38
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultLayUi queryAllUserForSql(UserHtVo userHtVo, Integer page, Integer limit) {

        String sql="";

        PageHelper.startPage(page,limit);

        System.out.println(userHtVo.getUserName());

        if(!"undefined".equals(userHtVo.getUserName()) && userHtVo.getUserName()!=null){
            sql+="where user_name like '%"+userHtVo.getUserName()+"%'";
        }
        List<User> userHtVos = userMapper.queryAllUserForSql(sql);
        Integer integer = userMapper.userCount();

        ResultLayUi resultLayUi=new ResultLayUi();
        resultLayUi.setCode(0);
        resultLayUi.setCount(integer);
        resultLayUi.setData(userHtVos);
        resultLayUi.setMsg("成功");

        return resultLayUi;
    }

    @Override
    public ResultLayUi selectUserNamePassword(String userName, String password) {
        ResultLayUi resultLayUi=new ResultLayUi();
        UserAdmin user = userMapper.selectUserAdminOne(userName,SHA1Util.encode(password));

        if(user==null){
            resultLayUi.setData(0);
            resultLayUi.setCode(0);
            resultLayUi.setMsg("账号或密码不存在");
        }else{
            resultLayUi.setData(user);
            resultLayUi.setCode(1);
            resultLayUi.setMsg("登录成功");
        }

        return resultLayUi;
    }

    @Override
    public User selectUserById(int userId) {
        return userMapper.selectUserById(userId);
    }

    @Override
    public int batchDeleteUsers(Integer[] id) {
        int i1=0;
        for (int i=0;i<id.length;i++){
            i1= baseMapper.deleteById(id[i]);
            if(i1<=0){
                throw new ApplicationException(CodeType.SERVICE_ERROR,"删除失败！");
            }

        }

        return i1;
    }

    @Override
    public int updateUser(User user) {
        UpdateWrapper<User> userUpdateWrapper=new UpdateWrapper<>();
        userUpdateWrapper.eq("id",user.getId()).set("user_name",user.getUserName()).set("user_sex",user.getUserSex())
                .set("avatar",user.getAvatar()).set("birthday",user.getBirthday()).set("email",user.getEmail())
                .set("mobile",user.getMobile()).set("introduce",user.getIntroduce()).set("curr_province",user.getCurrProvince())
        .set("city",user.getCity()).set("county",user.getCounty());
        int update = baseMapper.update(null, userUpdateWrapper);
        if(update<=0){
            throw new ApplicationException(CodeType.SERVICE_ERROR,"修改失败");
        }
        return update;
    }

    @Override
    public int addAdminUser(UserAdmin userAdmin) {
        String pwd = userAdmin.getPassword();
        userAdmin.setPassword(SHA1Util.encode(pwd));
        userAdmin.setCreateAt(System.currentTimeMillis() / 1000 + "");
        return userMapper.addAdminUser(userAdmin);
    }

    @Override
    public int updateAdminUserAuthority(UserAdmin userAdmin) {
        return userMapper.updateAdminUserAuthority(userAdmin);
    }

    @Override
    public ResultLayUi queryUserAdminList(String account, Integer role, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);

        List<UserAdmin> userAdmins = userMapper.queryUserAdminList(account,role);
        int i = 0;
        if (userAdmins != null){
            i = userMapper.queryUserAdminCount(account,role);
        }

        ResultLayUi resultLayUi=new ResultLayUi();
        resultLayUi.setCode(0);
        resultLayUi.setCount(i);
        resultLayUi.setData(userAdmins);
        resultLayUi.setMsg("成功");

        return resultLayUi;
    }
}
