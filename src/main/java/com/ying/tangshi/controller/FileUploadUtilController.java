package com.ying.tangshi.controller;

import com.ying.tangshi.utils.FileUploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.UUID;

/**
 * 图片， 文件， 视频， 音频上传共用接口
 * @author Zyred
 */
@Controller
@RequestMapping("/file")
public class FileUploadUtilController {

    /**
     * 文件上传
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/fileUpload/ds", method = RequestMethod.POST)
    public String fileUpload(@RequestParam("file") MultipartFile file){
        FileUploadUtil upload = new FileUploadUtil(file, "/Users/lianying/Desktop/毕业设计/古诗背诵小程序/voice//");
        String result = upload.uploadFile();
        return result;
    }

}