package com.ying.tangshi;

import com.ying.tangshi.config.PropertiesListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

@MapperScan("com.ying.tangshi.mapper")
public class TangshiApplication {

    public static void main(String[] args) {
//        SpringApplication.run(TangshiApplication.class, args);
        SpringApplication application = new SpringApplication(TangshiApplication.class);
        application.addListeners(new PropertiesListener("file.properties"));
        application.run(args);
        System.out.println("开始了");
    }

}
