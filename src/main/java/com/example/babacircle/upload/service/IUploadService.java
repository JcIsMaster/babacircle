package com.example.babacircle.upload.service;

import com.example.babacircle.common.utils.ResultUtil;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author MQ
 * @date 2021/4/23 18:06
 */
public interface IUploadService {

    /**
     * 文件上传
     * @param file 文件
     * @return
     */
    Map<String,Object> uploadConimagea(MultipartFile file);

    /**
     * 文件上传
     * @param file
     * @return
     */
    ResultUtil uploadEditImage(MultipartFile file);

    /**
     * 删除服务器文件
     * @param type
     * @param imgUrl
     */
    int deleteFile(int type,String imgUrl);
}
