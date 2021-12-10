package com.example.babacircle.match.service;

import com.example.babacircle.common.utils.ResultUtil;

/**
 * @author JC
 * @date 2021/11/23 14:50
 */
public interface IMatchService {

    /**
     * 新增/修改匹配信息
     * @param userId
     * @param text
     * @return
     */
    ResultUtil addUserMatch(int userId, String text);

}
