package com.example.babacircle.activity.service.impl;

import com.example.babacircle.activity.dao.ActivityOnlineMapper;
import com.example.babacircle.activity.entity.ActivityOnline;
import com.example.babacircle.activity.service.IActivityOnlineService;
import com.example.babacircle.activity.vo.ActivityOnlineListVo;
import com.example.babacircle.circle.dao.CircleMapper;
import com.example.babacircle.common.constanct.CodeType;
import com.example.babacircle.common.exception.ApplicationException;
import com.example.babacircle.common.utils.Paging;
import com.example.babacircle.common.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author JC
 * @date 2021/11/22 14:55
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ActivityOnlineServiceImpl implements IActivityOnlineService {

    @Resource
    private ActivityOnlineMapper activityOnlineMapper;

    @Resource
    private CircleMapper circleMapper;


    @Override
    public ResultUtil queryActivityOnlineList(Integer status, String title, Paging paging) {
        List<ActivityOnlineListVo> activityOnlineListVos = activityOnlineMapper.queryActivityOnlineList(status, title, getPaging(paging));
        return ResultUtil.success(activityOnlineListVos);
    }

    @Override
    public ResultUtil createActivityOnline(ActivityOnline activityOnline) {
        if (activityOnline.getInitiatorUserId() == 0) {
            throw new ApplicationException(CodeType.PARAMETER_ERROR,"参数异常");
        }
        //查询是否为圈主，否则不允许创建
        int circleCount = circleMapper.queryCircleCountByUserId(activityOnline.getInitiatorUserId());
        if (circleCount == 0){
            ResultUtil.error("该用户还不是圈主");
        }

        activityOnline.setCreateAt(String.valueOf(System.currentTimeMillis() / 1000));
        int i = activityOnlineMapper.createActivityOnline(activityOnline);
        if (i <= 0) {
            throw new ApplicationException(CodeType.SERVICE_ERROR,"创建线上活动失败");
        }
        return ResultUtil.success(i);
    }

    /**
     * 分页获取
     * @param paging
     * @return
     */
    public String getPaging(Paging paging) {
        int page = (paging.getPage() - 1) * paging.getLimit();
        return "limit " + page + "," + paging.getLimit();
    }
}
