package com.example.babacircle.talents.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.babacircle.talents.entity.Recruit;
import com.example.babacircle.talents.entity.RecruitLabel;
import com.example.babacircle.talents.vo.RecruitVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author MQ
 * @date 2021/4/28 14:44
 */
@Component
public interface TalentsMapper extends BaseMapper<Recruit> {

    /**
     * 根据条件查询人才里面的招牌信息
     * @param typeId 类型id
     * @return
     */
    @Select("select a.id,a.job_title,a.wage_range,a.job_requirements,a.type,a.create_at,b.user_name,b.id as userId from tb_recruit a inner join tb_user b on a.company_id=b.id  where type=${typeId} and a.is_delete=1 order by a.create_at desc")
    List<RecruitVo> querySignboardInformation(@Param("typeId") int typeId);

    /**
     * 根据条件统计人才里面的招牌信息
     * @param typeId 类型id
     * @return
     */
    @Select("select count(*) from tb_recruit  where type=${typeId} and is_delete=1")
    int countSignboardInformation(@Param("typeId") int typeId);

    /**
     * 根据条件查询人才里面的招牌信息
     * @return
     */
    @Select("select a.id,a.job_title,a.wage_range,a.job_requirements,a.type,a.create_at,b.user_name,b.id as userId from tb_recruit a inner join tb_user b on a.company_id=b.id where  a.is_delete=1 order by a.create_at desc")
    List<RecruitVo> querySignboardInformationAll();

    /**
     * 统计所有人才发帖信息
     * @return
     */
    @Select("select count(*) from tb_recruit where  is_delete=1 ")
    int countySignboardInformationAll();

    /**
     * 查询自己发布的招聘信息
     * @param userId 用户id
     * @return
     */
    @Select("select a.id,a.job_title,a.wage_range,a.job_requirements,a.type,a.create_at,b.user_name,b.id as userId from tb_recruit a inner join tb_user b on a.company_id=b.id  where a.company_id=${userId} and a.is_delete=1 order by a.create_at desc")
    List<RecruitVo> queryCheckYourOwnJobPostings(@Param("userId") int userId);

    /**
     * 查询自己发布的招聘信息
     * @param userId 用户id
     * @return
     */
    @Select("select count(*) from tb_recruit  where company_id=${userId} and is_delete=1")
    int countCheckYourOwnJobPostings(@Param("userId") int userId);


    /**
     * 根据发布职位帖子id查询岗位要求标签
     * @param recruitId
     * @return
     */
    @Select("select b.recruit_label_name,b.type,b.id from tb_recruit_label_middle a inner join tb_recruit_label b on a.recruit_label_id=b.id where a.recruit_id=${recruitId}")
    List<RecruitLabel> queryRequire(@Param("recruitId") int recruitId);

    /**
     * 查询职位要求标签
     * @return
     */
    @Select("select * from tb_recruit_label")
    List<RecruitLabel> queryCheckJobRequirementsTab();


    /**
     * 增加职位和职位标签中间表
     * @param recruitId 职位id
     * @param label 标签id组
     * @return
     */
    @Insert("<script>" +
            "insert into tb_recruit_label_middle(recruit_id,recruit_label_id) VALUES  " +
            "<foreach collection='label' item='item' index='index' separator=','>" +
            "(${recruitId},${item})" +
            "</foreach>" +
            "</script>")
    int addRecruitLabelMiddle(@Param("recruitId") int recruitId,@Param("label") Integer[] label);

    /**
     * 删除人才和标签的对应表数据
     * @param recruitId 人才id
     * @return
     */
    @Delete("delete  from tb_recruit_label_middle where recruit_id=${recruitId} ")
    int deleteRecruitLabelMiddle(@Param("recruitId") int recruitId);
}
