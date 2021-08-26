package com.ying.tangshi.controller;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.ying.tangshi.utils.ImageBase64Converter;
import com.ying.tangshi.utils.OssUtils;
import com.ying.tangshi.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 2021/1/1 - 7:44 下午
 * Lian-Ying
 **/
@RestController
@RequestMapping("/upload")
public class upLoad {

    @Autowired
    ImageBase64Converter imageBase64Converter;

    @Autowired
    OssUtils ossUtils;


    @Autowired
    ResultUtils resultUtils;

    @PostMapping("/up")
    public Object upload(@RequestParam("file") MultipartFile fileUpload, @RequestParam("userId") int userId,
                         @RequestParam("userName") String userName, @RequestParam("tangName") String tangName,
                         @RequestParam("tangId") int tangId, @RequestParam("tangVoiceDate") String tangVoiceDate) throws IOException {

        Map<String, Object> result = new HashMap<>();

//        QueryWrapper<SysTangVoice> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("tang_voice_date", tangVoiceDate).eq("user_id", userId).eq("tang_id", tangId);
//        SysTangVoice one = sysTangVoiceMapper.selectOne(queryWrapper);

        if (1 == 1) {
            String convertFileToBase64 = imageBase64Converter.convertFileToBase64(fileUpload.getInputStream());
            if (fileUpload.isEmpty()) {
                return "文件为空";
            }
            // 获取文件名
            String fileName = fileUpload.getOriginalFilename();
            System.out.println("上传的文件名为：" + fileName);

            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));

            System.out.println("上传的后缀名为：" + suffixName);
            // 文件上传后的路径
            String filePath = "/Users/lianying/Desktop/毕业设计/古诗背诵小程序/voice//";//////////修改文件的路径

            fileName = "voice-" + IdUtil.simpleUUID() + suffixName;

            File dest = new File(filePath + fileName);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                fileUpload.transferTo(dest);//////////////
                ossUtils.upLoadOss("userVoice/" + fileName, filePath + fileName);
                String voiceUrl = "https://20200719jianli.oss-cn-beijing.aliyuncs.com/userVoice/" + fileName;
                //////开始存数据库
//                SysTangVoice sysTangVoice = new SysTangVoice();
//                sysTangVoice.setTangVoiceUrl(voiceUrl);
//                sysTangVoice.setUserId(userId);
//                sysTangVoice.setUserName(userName);
//                sysTangVoice.setTangId(Convert.toStr(tangId));
//                sysTangVoice.setTangName(tangName);
//                sysTangVoice.setTangVoiceDate(tangVoiceDate);
//                sysTangVoiceMapper.insert(sysTangVoice);



            } catch (IOException e) {
                e.printStackTrace();
            }
            result.put("msg","上传成功");
            result.put("code",200);

        }else {
            result.put("msg","今天已背诵，每天一次");
            result.put("code",300);
        }
        return result;

    }
}
