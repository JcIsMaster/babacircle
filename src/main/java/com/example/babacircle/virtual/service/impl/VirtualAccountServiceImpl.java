package com.example.babacircle.virtual.service.impl;

import com.example.babacircle.circle.dao.CircleMapper;
import com.example.babacircle.common.utils.Paging;
import com.example.babacircle.resource.dao.ResourcesMapper;
import com.example.babacircle.util.ResultLayUi;
import com.example.babacircle.virtual.dao.VirtualAccountMapper;
import com.example.babacircle.virtual.service.IVirtualAccountService;
import com.example.babacircle.virtual.vo.UserVirtualVo;
import com.example.babacircle.virtual.vo.VirtualAccountVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author JC
 * @date 2021/11/18 16:01
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class VirtualAccountServiceImpl implements IVirtualAccountService {

    @Resource
    private VirtualAccountMapper virtualAccountMapper;

    @Resource
    private CircleMapper circleMapper;

    @Resource
    private ResourcesMapper resourcesMapper;

    @Override
    public ResultLayUi queryVirtualAccount(int type, Paging paging) throws Exception {
        List<VirtualAccountVo> accountVos = virtualAccountMapper.queryVirtualAccount(type,getPaging(paging));
        ResultLayUi resultLayUi = new ResultLayUi();
        resultLayUi.setCode(0);
        resultLayUi.setCount(virtualAccountMapper.queryVirtualAccountCount(type));
        resultLayUi.setData(accountVos);
        resultLayUi.setMsg("成功");
        return resultLayUi;
    }

    @Override
    public UserVirtualVo queryVirtualAccountById(int type,int userId) {
        UserVirtualVo userVirtualVo = virtualAccountMapper.queryVirtualAccountById(type, userId);
        //圈子数量
//        int circleCount = circleMapper.queryCircleCountByUserId(userId);
//        userVirtualVo.setCommunityNum(circleCount);
        //帖子数量
//        int postedNum = circleMapper.queryHavePostedCircleNum(userId);
//        userVirtualVo.setCircleNum(postedNum);
        //资源数量
//        int postsCount1 = resourcesMapper.queryMyPostedPostsCount(userId, 12);
//        userVirtualVo.setResourceNum(postsCount1);
        //合作数量
//        int postsCount2 = resourcesMapper.queryMyPostedPostsCount(userId, 13);
//        userVirtualVo.setCollaborateNum(postsCount2);
        //干货数量

        //公开课数量

        return userVirtualVo;
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
