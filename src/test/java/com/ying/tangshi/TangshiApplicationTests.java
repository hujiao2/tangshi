package com.ying.tangshi;
import java.util.Date;

import com.ying.tangshi.entity.User;
import com.ying.tangshi.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

@SpringBootTest
class TangshiApplicationTests {

    @Autowired
    UserMapper userMapper;


    @Test
    void contextLoads() {
        ///获取当前系统桌面
        File desktopDir = FileSystemView.getFileSystemView() .getHomeDirectory();

        String desktopPath = desktopDir.getAbsolutePath();
        System.out.println(desktopPath);
    }

    @Test
    void test1(){
        User user = new User();
        user.setUserNumber("123");
        user.setUserName("123");
        user.setUserPhone("123");
        user.setUserEmail("123");
        user.setUserClass("123");
        user.setUserState("123");
        user.setUserStateClass("123");
        user.setUserPassword("123");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setVersion(0);
        user.setLogicDel(0);

        userMapper.insert(user);

    }
    @Test
    void test2(){


        userMapper.selectById(1);

    }

}
