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
     * @return
     */
    @Select("select a.id,a.title,a.description,a.create_at,b.tag_name,c.user_name,a.favour,a.collect,a.`comment` from tb_question a INNER JOIN tb_tags b on a.tags_two=b.id INNER JOIN tb_user c on a.u_id=c.id where a.is_delete=1 ORDER BY a.create_at desc")
    List<QuestionVo> queryAllQuestion();

    /**
     * 统计所有
     * @return
     */
    @Select("select COALESCE(count(*),0) from tb_question a INNER JOIN tb_tags b on a.tags_two=b.id INNER JOIN tb_user c on a.u_id=c.id where a.is_delete=1 ")
    int countAllQuestion();
}
