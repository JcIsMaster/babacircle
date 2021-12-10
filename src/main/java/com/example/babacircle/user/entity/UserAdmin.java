package com.example.babacircle.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author JC
 * @date 2021/9/27 11:54
 */
@Data
@TableName("tb_user")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class UserAdmin {

    int id;

    /**
     * 账号
     */
    String account;

    /**
     * 密码
     */
    String password;

    /**
     * 角色id (1超级管理员,2管理员,3开放作者)
     */
    int roleId;

    /**
     * 头像
     */
    String avatar;

    /**
     * 创建时间
     */
    String createAt;

    /**
     * 修改时间
     */
    String updateAt;

    /**
     * 备注
     */
    String remarks;

    /**
     * 关联前端账号
     */
    int associatedId;

    /**
     * 是否启用 默认启用（1有效，0无效）
     */
    int isStatus;
}
