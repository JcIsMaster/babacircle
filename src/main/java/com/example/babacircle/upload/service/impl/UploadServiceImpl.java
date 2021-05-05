package com.example.babacircle.upload.service.impl;

import com.example.babacircle.upload.service.IUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author MQ
 * @date 2021/4/23 18:06
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UploadServiceImpl implements IUploadService {
    @Override
    public Map<String, Object> uploadConimagea(MultipartFile file) {
        Map<String,Object> mv= new HashMap<String, Object>();

        String urlpat=null;

        try {
            ////Calendar.getInstance()是获取一个Calendar对象并可以进行时间的计算，时区的指定
            Calendar date = Calendar.getInstance();
            //获得文件最初的路径
            String originalFile = file.getOriginalFilename();
            //UUID转化为String对象
            String uuid = UUID.randomUUID().toString();
            String newfilename=date.get(Calendar.YEAR)+""+(date.get(Calendar.MONTH)+1)+""+date.get(Calendar.DATE)+uuid.replace("-", "")+originalFile;
            //图片保存的地址
            Path path = Paths.get("e:/file/img/"+ newfilename);

            //文件不存在就创建
            if(!Files.isWritable(path)) {
                Files.createDirectories(Paths.get("e:/file/img"));
            }
            //上传图片
            file.transferTo(path);

            //得到上传图片之后的图片
            //图片访问地址
            urlpat="https://www.gofatoo.com/img/"+newfilename;

            List<String> list=new ArrayList<String>();
            list.add(urlpat);
            mv.put("data", list);
            mv.put("errno", 0);
            return mv;
        } catch (Exception e) {
            e.printStackTrace();
            mv.put("success", 1);
            return mv;
        }
    }
}
