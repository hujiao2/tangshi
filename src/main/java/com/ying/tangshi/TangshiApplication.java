package com.ying.tangshi;

import com.ying.tangshi.config.PropertiesListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.spring.web.WebMvcRequestHandler;

@SpringBootApplication

@MapperScan("com.ying.tangshi.mapper")
public class TangshiApplication extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //这是配置模板页面的路径  配置文件里面已经配置了  所以这里暂时不需要
        //这是是配置静态资源文件的路径
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
        super.addResourceHandlers(registry);
    }
    public static void main(String[] args) {
//        SpringApplication.run(TangshiApplication.class, args);
        SpringApplication application = new SpringApplication(TangshiApplication.class);
        application.addListeners(new PropertiesListener("file.properties"));
        application.run(args);
        System.out.println("开始了");
    }

}
