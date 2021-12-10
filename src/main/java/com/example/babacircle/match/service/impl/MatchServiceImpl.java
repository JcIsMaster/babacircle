package com.example.babacircle.match.service.impl;

import com.example.babacircle.common.constanct.CodeType;
import com.example.babacircle.common.exception.ApplicationException;
import com.example.babacircle.common.utils.ResultUtil;
import com.example.babacircle.match.dao.MatchMapper;
import com.example.babacircle.match.entity.ParameterJson;
import com.example.babacircle.match.service.IMatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author JC
 * @date 2021/11/23 14:53
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class MatchServiceImpl implements IMatchService {

    @Resource
    private MatchMapper matchMapper;

    @Override
    public ResultUtil addUserMatch(int userId, String text) {
        if (userId == 0) {
            throw new ApplicationException(CodeType.PARAMETER_ERROR);
        }
        int parameterJson = matchMapper.queryParameterById(userId);
        int i = 0;
        if (parameterJson != 0){
            i = matchMapper.updateParameter(userId, text);
        }else {
            i = matchMapper.insertParameter(userId, text);
        }
        if (i <= 0) {
            throw new ApplicationException(CodeType.SERVICE_ERROR);
        }
        return ResultUtil.success("ok");
    }
}
