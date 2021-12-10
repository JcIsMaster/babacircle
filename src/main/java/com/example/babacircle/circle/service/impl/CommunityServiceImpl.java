package com.example.babacircle.circle.service.impl;

import com.example.babacircle.circle.dao.CircleMapper;
import com.example.babacircle.circle.dao.CommunityMapper;
import com.example.babacircle.circle.entity.Community;
import com.example.babacircle.circle.entity.CommunityTopic;
import com.example.babacircle.circle.entity.CommunityUser;
import com.example.babacircle.circle.service.ICommunityService;
import com.example.babacircle.circle.vo.CircleClassificationVo;
import com.example.babacircle.circle.vo.CircleVo;
import com.example.babacircle.common.constanct.CodeType;
import com.example.babacircle.common.exception.ApplicationException;
import com.example.babacircle.common.utils.Paging;
import com.example.babacircle.common.utils.ResultUtil;
import com.example.babacircle.tags.dao.TagMapper;
import com.example.babacircle.tags.entity.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author JC
 * @date 2021/11/23 16:43
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class CommunityServiceImpl implements ICommunityService {

    @Resource
    private CommunityMapper communityMapper;

    @Resource
    private TagMapper tagMapper;

    @Resource
    private CircleMapper circleMapper;

    @Override
    public void addCircle(Community community) {

        //查找是否有同名圈子
        int nameExist = communityMapper.querySameNameExist(community.getCommunityName());
        if (nameExist > 0){
            throw new ApplicationException(CodeType.RESOURCES_EXISTING, "该圈子名称已存在");
        }

        Tag tag = new Tag();
        tag.setTId(0);
        tag.setTagName(community.getCommunityName());
        tag.setCreateAt(System.currentTimeMillis() / 1000 + "");
        tag.setType(1);

        community.setCreateAt(System.currentTimeMillis() / 1000 + "");
        int i = tagMapper.addTag(tag);
        if (i <= 0) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "添加失败");
        }

        community.setTagId(tag.getId());
        int i1 = communityMapper.addCommunity(community);
        if (i1 <= 0) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "添加圈子信息失败");
        }

        //添加圈子人数 （创建人）
        CommunityUser communityUser = new CommunityUser();
        communityUser.setCreateAt(System.currentTimeMillis() / 1000 + "");
        communityUser.setCommunityId(community.getId());
        communityUser.setUserId(community.getUserId());
        int i4 = communityMapper.addCommunityUser(communityUser);
        if (i4 <= 0) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "添加圈子人错误");
        }

        int i2 = tagMapper.addTagHaplont(tag.getId(), 1);
        if (i2 <= 0) {
            throw new ApplicationException(CodeType.SERVICE_ERROR);
        }

        int i3 = tagMapper.addTagHaplont(tag.getId(), 2);
        if (i3 <= 0) {
            throw new ApplicationException(CodeType.SERVICE_ERROR);
        }
    }

    @Override
    public List<Community> queryOfficialCircleList() {
        return communityMapper.queryOfficialCircleList();
    }

    @Override
    public List<CommunityTopic> queryAllTopic() {
        return communityMapper.queryAllTopic();
    }

    @Override
    public ResultUtil queryCommunityBySort(int sort, int userId, Paging paging) {
        List<CircleVo> circleVos = null;
        int vosCount = 0;
        switch (sort){
            //全部圈子
            case 0:
                circleVos = communityMapper.allCircle(getPaging(paging));
                vosCount = communityMapper.allCircleCount();
                break;
            //创建的圈子
            case 1:
                circleVos = communityMapper.myCreateCircle(userId,getPaging(paging));
                vosCount = communityMapper.myCircleCount(userId);
                break;
            //加入的圈子
            case 2:
                circleVos = communityMapper.circleJoined(userId,getPaging(paging));
                vosCount = communityMapper.circleJoinedCount(userId);
                break;
            default:
                throw new ApplicationException(CodeType.PARAMETER_ERROR);
        }
        for (CircleVo vo : circleVos) {
            //圈子成员人数
            vo.setMemberCount(communityMapper.countCircleJoined(vo.getId()));
            //是否加入了该圈子
            if (sort != 0) {
                vo.setWhetherJoined(1);
            }
            else {
                int whetherJoinedCircle = communityMapper.queryWhetherJoinedCircle(vo.getId(), userId);
                if (whetherJoinedCircle != 0) {
                    vo.setWhetherJoined(1);
                }
            }
        }
        return ResultUtil.success(circleVos,vosCount);
    }

    @Override
    public int joinCircle(CommunityUser communityUser) {
        communityUser.setCreateAt(System.currentTimeMillis() / 1000 + "");

        //查询是否存在圈子里面 如果存在在调用接口就是退出圈子
        int i1 = communityMapper.queryWhetherJoinedCircle(communityUser.getCommunityId(), communityUser.getUserId());
        if (i1 > 0) {
            //退出圈子
            int i = communityMapper.exitGroupChat(communityUser.getCommunityId(), communityUser.getUserId());
            if (i <= 0) {
                throw new ApplicationException(CodeType.SERVICE_ERROR, "退出圈子失败");
            }
            return i;
        }

        int i = communityMapper.joinCircle(communityUser);
        if (i <= 0) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "加入圈子失败!");
        }
        return i;
    }

    @Override
    public Community queryCommunityById(int id) {
        Community community = communityMapper.queryCommunityById(id);
        return community;
    }

    @Override
    public ResultUtil updateCommunity(Community community) {
        int i = communityMapper.updateCommunity(community);
        if (i <= 0) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "修改失败");
        }
        //修改圈子对应的标签名
        int j = tagMapper.updateTagNameForCommunity(community.getCommunityName(), community.getTagId());
        if (j <= 0) {
            throw new ApplicationException(CodeType.SERVICE_ERROR, "修改失败");
        }
        return ResultUtil.success("ok");
    }

    @Override
    public ResultUtil deleteCommunity(int id, int tagId) {
        int i = communityMapper.deleteCommunity(id);
        if(i <= 0){
            throw new ApplicationException(CodeType.SERVICE_ERROR,"删除圈子失败");
        }
        int j = circleMapper.deletePosts(tagId);
        if(j < 0){
            throw new ApplicationException(CodeType.SERVICE_ERROR,"删除圈子内帖子失败");
        }
        return ResultUtil.success("ok");
    }

    public String getPaging(Paging paging) {
        int page = (paging.getPage() - 1) * paging.getLimit();
        return "limit " + page + "," + paging.getLimit();
    }
}
