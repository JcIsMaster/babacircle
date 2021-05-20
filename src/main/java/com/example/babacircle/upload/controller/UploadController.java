package com.example.babacircle.upload.controller;

import com.example.babacircle.upload.service.IUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author MQ
 * @date 2021/4/23 16:43
 */
@Api(tags = "上传API")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/UploadController")
public class UploadController {

    @Autowired
    private IUploadService iUploadService;

    @PostMapping("uploadConimagea")
    @ResponseBody
    public Map<String,Object> uploadConimagea(MultipartFile file) {
        Map<String, Object> map = iUploadService.uploadConimagea(file);
        return map;
    }

    @ApiOperation(value = "删除服务器图片", notes = "删除服务器图片")
    @ResponseBody
    @PostMapping("/deleteFile")
    public int deleteFile(int type,String imgUrl) {

       return iUploadService.deleteFile(type,imgUrl);
    }



}
