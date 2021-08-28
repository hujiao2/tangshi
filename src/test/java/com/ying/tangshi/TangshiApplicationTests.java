package com.ying.tangshi;
import java.util.Date;

import com.ying.tangshi.entity.SysRole;
import com.ying.tangshi.entity.User;
import com.ying.tangshi.mapper.SysRoleMapper;
import com.ying.tangshi.mapper.UserMapper;
import com.ying.tangshi.utils.EmailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

@SpringBootTest
class TangshiApplicationTests {

    @Autowired
    UserMapper userMapper;


    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    EmailUtil emailUtil;

    @Test
    void adduser() {
        String subject = "简历之家";


        String qq = "1416127947@qq.com";
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        String text = "<h1 style=\"color: black;font-weight: 800;\">" + code + "</h1>";

        try {

            emailUtil.sendEmail(subject, text, true, false, qq);//发送验证码
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

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

        sysRoleMapper.selectList(null);


    }

}
