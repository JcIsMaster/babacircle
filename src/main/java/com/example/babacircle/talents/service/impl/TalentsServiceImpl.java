package com.example.babacircle.talents.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.babacircle.learn.entity.DryGoods;
import com.example.babacircle.talents.dao.TalentsMapper;
import com.example.babacircle.talents.entity.Recruit;
import com.example.babacircle.talents.entity.RecruitLabel;
import com.example.babacircle.talents.service.ITalentsService;
import com.example.babacircle.talents.vo.RecruitVo;
import com.example.babacircle.talents.vo.RequirementsR;
import com.example.babacircle.util.ResultLayUi;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author MQ
 * @date 2021/4/28 14:51
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class TalentsServiceImpl extends ServiceImpl<TalentsMapper,Recruit> implements ITalentsService {


    @Autowired
    private TalentsMapper talentsMapper;

    @Override
    public ResultLayUi querySignboardInformation(Recruit recruit, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        //查询全部
        if(recruit.getType()==0){
            List<RecruitVo> recruitVos = talentsMapper.querySignboardInformationAll();

            int i = talentsMapper.countySignboardInformationAll();
            ResultLayUi resultLayUi=new ResultLayUi();
            resultLayUi.setCode(0);
            resultLayUi.setCount(i);
            resultLayUi.setData(recruitVos);
            resultLayUi.setMsg("成功");

            return resultLayUi;
        }



        if(recruit.getType()==1){
            if(recruit.getCompanyId()!=0){
                List<RecruitVo> recruitVos = talentsMapper.queryCheckYourOwnJobPostings(recruit.getCompanyId());

                int i = talentsMapper.countCheckYourOwnJobPostings(recruit.getCompanyId());

                ResultLayUi resultLayUi=new ResultLayUi();
                resultLayUi.setCode(0);
                resultLayUi.setCount(i);
                resultLayUi.setData(recruitVos);
                resultLayUi.setMsg("成功");
                return resultLayUi;
            }
        }

        if(recruit.getType()>1){
            List<RecruitVo> recruitVos = talentsMapper.querySignboardInformation(recruit.getType());

            int i = talentsMapper.countSignboardInformation(recruit.getType());

            ResultLayUi resultLayUi=new ResultLayUi();
            resultLayUi.setCode(0);
            resultLayUi.setCount(i);
            resultLayUi.setData(recruitVos);
            resultLayUi.setMsg("成功");
            return resultLayUi;
        }

        return null;
    }

    @Override
    public List<RecruitLabel> queryInquireJobRequirementsAccordingPostById(int id) {
        return talentsMapper.queryRequire(id);
    }

    @Override
    public int deleteTalents(int id) {
        UpdateWrapper<Recruit> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id).set("is_delete", 0);

        int update = baseMapper.update(null, wrapper);
        return update;
    }

    @Override
    public List<RecruitLabel> queryCheckJobRequirementsTab() {
        List<RecruitLabel> recruitLabels = talentsMapper.queryCheckJobRequirementsTab();
        return recruitLabels;
    }

    @Override
    public ResultLayUi addJobExpectations(Recruit recruit, Integer[] label) {
        ResultLayUi resultLayUi=new ResultLayUi();
        recruit.setCreateAt(System.currentTimeMillis()/1000+"");
        recruit.setIsDelete(1);
        //添加发布的职位
        int insert = baseMapper.insert(recruit);


        /**
         * 添加职位和标签的中间表数据
         */
        int i = talentsMapper.addRecruitLabelMiddle(recruit.getId(), label);
        if(i<=0){
            resultLayUi.setCode(0);
            resultLayUi.setCount(i);
            resultLayUi.setData(0);
            resultLayUi.setMsg("失败");
            return resultLayUi;
        }
        resultLayUi.setCode(0);
        resultLayUi.setCount(i);
        resultLayUi.setData(1);
        resultLayUi.setMsg("成功");

        return resultLayUi;
    }

    @Override
    public int updateJbExpectations(Recruit recruit, Integer[] label) {
        UpdateWrapper<Recruit>  queryWrapper=new UpdateWrapper<>();
        queryWrapper.eq("id",recruit.getId()).set("job_title",recruit.getJobTitle()).set("wage_range",recruit.getWageRange())
        .set("job_requirements",recruit.getJobRequirements()).set("type",recruit.getType());

        int update = baseMapper.update(null, queryWrapper);

        //删除中间表
        int i = talentsMapper.deleteRecruitLabelMiddle(recruit.getId());

        //添加职位和标签的中间表数据
        int i1 = talentsMapper.addRecruitLabelMiddle(recruit.getId(), label);
        return 1;
    }
}
