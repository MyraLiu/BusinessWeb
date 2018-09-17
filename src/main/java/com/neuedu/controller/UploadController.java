package com.neuedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class UploadController {
    @RequestMapping(value = "/upload",method = RequestMethod.GET)
    public String upload(){
        return "upload";
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
//    public String upload(@RequestParam MultipartFile upload)
//    public String upload(@RequestParam("upload") MultipartFile file)
    public String upload(@RequestParam("upload") MultipartFile upload){

      //重新生成一个文件名，生成一个唯一的字符串UUID+扩展名
        if(upload!=null) {
            //1. 获取原文件的扩展名
            String originalFilename = upload.getOriginalFilename();
            if (originalFilename != null && !originalFilename.equals("")) {
                int doti = originalFilename.lastIndexOf(".");/*获取最后一个点的位置索引*/
                String extendname = originalFilename.substring(doti);/*包含点的索引[ 闭区间,开区间），获取到原文件的扩展名*/
                //2. 生成新的文件名
                String uuid = UUID.randomUUID().toString();
                String newFileName = uuid+extendname;
                // 3. 构造新文件
                String filePath = "d:\\ftpfile";/*文件存储的目录*/
                File file = new File(filePath,newFileName);/*构建目录下的新文件*/
                try {
                    /*写入新文件*/
                    upload.transferTo(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return "upload";
    }

}
