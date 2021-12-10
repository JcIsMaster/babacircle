package com.example.babacircle.virtual.service;

import com.example.babacircle.common.utils.Paging;
import com.example.babacircle.util.ResultLayUi;
import com.example.babacircle.virtual.vo.UserVirtualVo;
import org.springframework.stereotype.Component;

/**
 * @author JC
 * @date 2021/11/18 16:00
 */
public interface IVirtualAccountService {

    /**
     * 根据条件查询虚拟号用户
     * @param type
     * @param paging
     * @return
     * @throws Exception
     */
    ResultLayUi queryVirtualAccount(int type, Paging paging) throws Exception;

    /**
     * 根据id查询虚拟号用户信息
     * @param type
     * @param userId
     * @return
     */
    UserVirtualVo queryVirtualAccountById(int type,int userId);
}
