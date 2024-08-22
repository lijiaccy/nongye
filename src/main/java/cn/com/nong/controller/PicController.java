package cn.com.nong.controller;

import cn.com.nong.dao.impl.UserService;
import cn.com.nong.util.MinIoUtil;
import io.minio.PutObjectArgs;
import io.minio.UploadObjectArgs;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.util.Random;

@RestController
@RequestMapping("/pic")
public class PicController {
 
    @Autowired
    private MinIoUtil minIoUtil;

    @Autowired
    private UserService userService;
 
    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
    @ApiOperation(value = "上传图片")
    public boolean upload(@RequestParam("file") MultipartFile[] files,String goodid) throws Exception {
        for (MultipartFile file :files) {
            int random = 10000 + (int) (Math.random() * 90000);
            String filename = goodid+"_"+random+"_"+file.getOriginalFilename();
            Boolean aPublic = minIoUtil.upload(file, "public", filename);
            if (aPublic) {
//                userService.addPics(goodid, filename);
            }
        }
        return true;
    }
 
    /**
     * minio下载文件
     */
    @GetMapping("/download")
    public void download(HttpServletResponse res){
        minIoUtil.download("my-bucketname","logo.png", res);
    }
 
}