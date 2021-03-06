package com.example.babacircle.user.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author Administrator
 */
@Data
@TableName("tb_user")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class User extends Model<User> {

	/**
	 *  用户表ID
	 */
	int id ;

	/**
	 * 用户表名称
	 */
	String userName ;

	/**
	 * 性别
	 * 1男 0女
	 */
	String userSex;

	/**
	 * 生日
	 */
	String birthday;

	/**
	 * 介绍
	 */
	String introduce;

	/**
	 * 用户手机号
	 */
	String mobile;

	/**
	 * 所在省
	 */
	String currProvince;

	/**
	 * 所在市
	 */
	String city;

	/**
	 * 所在县
	 */
	String county;

	/**
	 * 背景图
	 */
	String picture;

	/**
	 * code 标识 唯一 系统生成（加好友  搜好友）
	 */
	String mCode;

	/**
	 * 邮箱
	 */
	String email;

	/**
	 * 用户微信标识
	 */
	String openId;

	/**
	 * 用户表头像
	 */
	String  avatar ;


	/**
	 * 创建时间
	 */
	String createAt;

	/**
	 *  是有效（1有效，0无效）默认1
	 */
	int  isDelete;





	  
	  
 

}
