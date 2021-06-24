package com.example.babacircle.circle.service;

import com.example.babacircle.circle.entity.Circle;
import com.example.babacircle.circle.entity.Haplont;
import com.example.babacircle.circle.vo.CircleClassificationVo;
import com.example.babacircle.util.ResultLayUi;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;


/**
 * @author MQ
 * @date 2021/5/3 16:45
 */
public interface ICircleService {
    /**
     * 查询所有圈子的数据
     * @param circle
     * @param page
     * @param limit
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     * @throws ParseException
     */
    ResultLayUi selectAllPosting(Circle circle, Integer page, Integer limit,String startTime,String endTime) throws ParseException;

    /**
     * 增加圈子
     * @param circle
     * @return
     */
    int addCirclePost(Circle circle) throws IOException;

    /**
     * 修改圈子
     * @param circle
     * @return
     */
    int updateCirclePost(Circle circle);



    /**
     * 删除
     * @param id 帖子id
     * @return
     */
    Integer cirCleDeletes( Integer[] id);

    /**
     * 根据标签id查询单元体导航栏
     * @param tagId 标签id
     * @return
     */
    List<Haplont> selectHaplontByTagId(int tagId);

    /**
     * 查询单个
     * @param id
     * @return
     */
    CircleClassificationVo querySingleCircle(int id);

}
