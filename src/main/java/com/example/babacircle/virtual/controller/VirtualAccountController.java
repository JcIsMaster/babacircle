package com.example.babacircle.virtual.controller;

import com.example.babacircle.common.utils.Paging;
import com.example.babacircle.user.vo.UserHtVo;
import com.example.babacircle.util.ResultLayUi;
import com.example.babacircle.virtual.service.IVirtualAccountService;
import com.example.babacircle.virtual.vo.UserVirtualVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author JC
 * @date 2021/11/18 15:58
 */
@Api(tags = "虚拟号API")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/VirtualAccountController")
public class VirtualAccountController {

    @Resource
    private IVirtualAccountService iVirtualAccountService;

    /**
     * 根据条件查询虚拟号用户
     * @return
     */
    @ApiOperation(value = "根据条件查询虚拟号用户",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/queryVirtualAccount")
    public ResultLayUi queryVirtualAccount(int type, Paging paging) throws Exception {
        return iVirtualAccountService.queryVirtualAccount(type, paging);
    }

    /**
     * 根据id查询虚拟号用户信息
     * @return
     */
    @ApiOperation(value = "根据id查询虚拟号用户信息",notes = "成功返回数据 反则为空")
    @ResponseBody
    @PostMapping("/queryVirtualAccountById")
    public UserVirtualVo queryVirtualAccountById(int type,int userId) {
        return iVirtualAccountService.queryVirtualAccountById(type,userId);
    }

}
