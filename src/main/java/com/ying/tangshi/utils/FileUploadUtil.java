package com.ying.tangshi.utils;

import com.ying.tangshi.config.PropertiesListenerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.UUID;

/**
 * 文件上传方法， 该类可以传 视频， 文件， 音频， 图片
 * @author Zyred
 */
public class FileUploadUtil {

    Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);

    private String type;

    private MultipartFile file;

    private String savePath;

    /**
     * 文件访问地址
     */
    private String resultPath;

    /**
     * 根据类型读取配置文件构造器
     * @param type  properties文件key
     * @param file  文件
     */
    public FileUploadUtil(String type, MultipartFile file){
        this.file = file;
        this.type = type;
    }

    /**
     * 根据保存路径构造器
     * @param file   文件
     * @param savePath  保存路径如： C:\\upload
     */
    public FileUploadUtil(MultipartFile file, String savePath){
        this.file = file;
        this.savePath = savePath;
    }

    /**
     * 上传文件方法
     * @return
     */
    public String uploadFile(){

        logger.info("上传文件类型：" + type);

        String save_path = null;
        InputStream is = null;
        FileOutputStream os = null;

        //判断文件不为空
        if (file == null && file.isEmpty()) {
            return "上传的文件为空";
        }

        if(type != null && !type.isEmpty()){
            //通过key获取到存储的路径
            save_path = PropertiesListenerConfig.getProperty(type);
        }

        if(save_path == null){
            save_path = savePath;
        }

        //获取到文件的名字
        String oldFileName = file.getOriginalFilename();
        logger.info("上传文件名称：" + oldFileName);
        String oldFileNameSufix = oldFileName.substring(oldFileName.lastIndexOf("."), oldFileName.length());

        //得到文件的新名字
        String newFileName = UUID.randomUUID().toString() + oldFileNameSufix;
        logger.info("上传文件新名称：" + newFileName);

        //保存后的文件路径
        String affterPath = save_path + newFileName;
        logger.info("文件保存位置：" + affterPath);

        //创建文件对象
        File dest = new File(affterPath);
        //文件或目录是否存在
        if (dest.exists()) {
            dest.mkdir();
        }

        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        //保存文件
        try {
            //获取输入流
            is = file.getInputStream();
            //创建输出流
            os = new FileOutputStream(dest);
            int len = 0;
            byte[] buffer = new byte[2048*512];
            //往输出流中写入字节流
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }

            InetAddress localHost = InetAddress.getLocalHost();
            //请求前缀  http://  https://
            String prefix = PropertiesListenerConfig.getProperty("file.download.path.prefix");
            //java项目请求路径 如：/image/download/file
            String middle = PropertiesListenerConfig.getProperty("file.download.path.middle");
            resultPath = prefix + localHost.getHostAddress() + middle + newFileName;
            logger.info("文件下载地址：" + resultPath);
        } catch (IOException e) {
            e.printStackTrace();
            return "服务器异常，请稍后重试";
        }finally {
            try{
                if(os != null || os != null){
                    os.close();
                    os.flush();
                    is.close();
                }
            }catch (IOException e){
                e.printStackTrace();
                return "流关闭异常";
            }
        }
        return resultPath;
    }
}