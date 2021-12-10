package com.example.babacircle.activity.service.impl;

import com.example.babacircle.activity.dao.ActivityMapper;
import com.example.babacircle.activity.entity.Activity;
import com.example.babacircle.activity.entity.ActivityParticipate;
import com.example.babacircle.activity.service.IActivityService;
import com.example.babacircle.activity.vo.ActivityListVo;
import com.example.babacircle.common.constanct.CodeType;
import com.example.babacircle.common.exception.ApplicationException;
import com.example.babacircle.common.utils.Paging;
import com.example.babacircle.common.utils.ResultUtil;
import com.example.babacircle.common.utils.TimeUtil;
import com.example.babacircle.util.ResultLayUi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author JC
 * @date 2021/9/24 16:50
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ActivityServiceImpl implements IActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public ResultLayUi queryActivityList(int type, String title, String area, Paging paging) {
        List<ActivityListVo> activityListVos = activityMapper.queryActivityList(type, title, area, getPaging(paging));
        ResultLayUi resultLayUi = new ResultLayUi();
        resultLayUi.setCode(0);
        resultLayUi.setCount(activityMapper.queryActivityCount(type, title, area));
        resultLayUi.setData(activityListVos);
        resultLayUi.setMsg("成功");
        return resultLayUi;
    }

    @Override
    public Activity queryActivityDetailsById(int id) {
        return activityMapper.queryActivityDetailsById(id);
    }

    @Override
    public ResultUtil createActivity(Activity activity) throws ParseException {
        activity.setActivityStartTime(System.currentTimeMillis() / 1000 + "");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        activity.setActivityEndTime(String.valueOf(TimeUtil.getEndOfDay(simpleDateFormat.parse(activity.getActivityEndTime()))));
        int i = activityMapper.createActivity(activity);
        if (i <= 0){
            throw new ApplicationException(CodeType.SERVICE_ERROR,"创建活动失败");
        }
        return ResultUtil.success(i);
    }

    @Override
    public ResultUtil delActivity(int id) {
        int i = activityMapper.delActivity(id);
        if (i <= 0) {
            throw new ApplicationException(CodeType.SERVICE_ERROR,"删除活动失败");
        }
        return ResultUtil.success(i);
    }

    @Override
    public ResultLayUi queryActivityParticipates(int id, Paging paging) {
        List<ActivityParticipate> activityParticipates = activityMapper.queryActivityParticipates(id, getPaging(paging));
        ResultLayUi resultLayUi = new ResultLayUi();
        resultLayUi.setCode(0);
        resultLayUi.setCount(activityMapper.queryActivityParticipatesCount(id));
        resultLayUi.setData(activityParticipates);
        resultLayUi.setMsg("成功");
        return resultLayUi;
    }

    @Override
    public ResultUtil queryActivityListByUserId(int userId, Paging paging) {
        List<ActivityListVo> activityListVos = activityMapper.queryActivityListByUserId(userId, getPaging(paging));
        int i = activityMapper.queryActivityCountByUserId(userId);
        return ResultUtil.success(activityListVos,i);
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
