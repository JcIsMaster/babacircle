package com.example.babacircle.match.dao;

import com.example.babacircle.match.entity.ParameterJson;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * @author JC
 * @date 2021/11/23 14:36
 */
@Component
public interface MatchMapper {

    /**
     * 新增
     * @param userId
     * @param text
     * @return
     */
    @Insert("INSERT into tb_parameter(user_id,json_class,create_at) VALUES(${userId},#{text},unix_timestamp())")
    int insertParameter(@Param("userId") int userId, @Param("text") String text);

    /**
     * 根据用户id修改
     * @param userId
     * @param text
     * @return
     */
    @Update("UPDATE tb_parameter set json_class=#{text} WHERE user_id=${userId}")
    int updateParameter(@Param("userId") int userId,@Param("text") String text);

    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    @Select("SELECT count(*) from tb_parameter WHERE user_id = ${userId}")
    int queryParameterById(@Param("userId") int userId);
}
