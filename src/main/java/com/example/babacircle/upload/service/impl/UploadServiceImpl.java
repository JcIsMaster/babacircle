package com.example.babacircle.upload.service.impl;

import com.example.babacircle.upload.service.IUploadService;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
    public static int getRandomInt(int Min , int Max){
        Random rand = new Random();
        return rand.nextInt(Max - Min + 1) + Min;
    }

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
            String newfilename=System.currentTimeMillis()/1000+""+getRandomInt(10000, 99999);
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1);

            String visbit="img";
            if(suffixName.equals("AVI")||suffixName.equals("mov")||suffixName.equals("rmvb")||suffixName.equals("FLV")||suffixName.equals("mp4")||suffixName.equals("3GP")){
                System.out.println("jin");
                visbit="video";
            }
            System.out.println(newfilename);
            //图片保存的地址
            Path path = Paths.get("e:/file/"+visbit+"/"+newfilename+"."+suffixName);


            //文件不存在就创建
            if(!Files.isWritable(path)) {
                Files.createDirectories(Paths.get("e:/file/img"));
            }
            //上传图片
            file.transferTo(path);

            //得到上传图片之后的图片
            //图片访问地址
            urlpat="https://www.gofatoo.com/"+visbit+"/"+newfilename+"."+suffixName;
            System.out.println(urlpat);
            List<String> list=new ArrayList<String>();
            list.add(urlpat);
            mv.put("data", list);
            mv.put("errno", 0);
            /*mv.put("success", 0);*/
            return mv;
        } catch (Exception e) {
            e.printStackTrace();
            mv.put("success", 1);
            return mv;
        }
    }

    @Override
    public int deleteFile(int type, String imgUrl) {
        //得到最后一个斜杆后面的值
        String substring = imgUrl.substring(imgUrl.lastIndexOf("/"));

        String documentType="";
        //0代表是图片
        if(type==0){
            documentType="img";
        }

        //1代表是视屏
        if(type==1){
            documentType="video";
        }
        File file = new File("e://file/"+documentType+""+substring+"");
        //判断文件是否存在
        if (file.exists()){
            boolean delete = file.delete();
        }
        return 1;
    }
}
