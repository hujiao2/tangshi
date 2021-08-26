package com.ying.tangshi.utils;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 2021/5/9 - 2:34 上午
 * Lian-Ying
 **/
@Component
public class OssUtils {
    private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";

    // accessKeyId和accessKeySecret是OSS的访问密钥，您可以在控制台上创建和查看，
    // 创建和查看访问密钥的链接地址是：https://ak-console.aliyun.com/#/。
    // 注意：accessKeyId和accessKeySecret前后都没有空格，从控制台复制时请检查并去除多余的空格。
    private static String accessKeyId = "LTAI4FzYaHxXyHDefZbrAqsP";
    private static String accessKeySecret = "9VoZsb7zXYHjAEGArzMTxmngavpdky";

    // Bucket用来管理所存储Object的存储空间，详细描述请参看“开发人员指南 > 基本概念 > OSS基本概念介绍”。
    // Bucket命名规范如下：只能包括小写字母，数字和短横线（-），必须以小写字母或者数字开头，长度必须在3-63字节之间。
    private static String bucketName = "20200719jianli";

    public void upLoadOss(String fileOssPath,String filePath) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileOssPath, new File(filePath));
        ossClient.putObject(putObjectRequest);
        ossClient.shutdown();
    }

}
