package com.example.babacircle.learn.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.babacircle.learn.entity.Question;
import com.example.babacircle.learn.vo.QuestionVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author MQ
 * @date 2021/5/12 10:50
 */
@Component
public interface QuestionMapper extends BaseMapper<Question> {


    /**
     * 查询所有
     * @param title
     * @return
     */
    @Select("<script>" +
            "select a.id,a.title,a.description,a.create_at,b.tag_name,c.user_name,a.favour_num,a.comment_num from tb_question a INNER JOIN tb_tags b on a.tags_two=b.id " +
            "INNER JOIN tb_user c on a.user_id=c.id where a.is_delete=0 " +
            "<if test='title != null'>and a.title LIKE CONCAT('%',#{title},'%') </if>" +
            "ORDER BY a.create_at desc" +
            "</script>")
    List<QuestionVo> queryAllQuestion(@Param("title")String title);

    /**
     * 统计所有
     * @param title
     * @return
     */
    @Select("<script>" +
            "select COALESCE(count(*),0) from tb_question a INNER JOIN tb_tags b on a.tags_two=b.id INNER JOIN tb_user c on a.user_id=c.id " +
            "where a.is_delete = 0" +
            "<if test='title != null'>and a.title LIKE CONCAT('%',#{title},'%') </if>" +
            "</script>")
    int countAllQuestion(@Param("title")String title);
}
