package com.ying.tangshi.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 2020/7/20 - 11:54 上午
 * Lian-Ying
 **/
@Component
public class EmailUtil {
    @Resource
    JavaMailSenderImpl mailSender;

    @Async
    public void sendEmail(String subject, String text, boolean html, boolean file, String qq) throws MessagingException {
        MimeMessage mimeMailMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMailMessage, file);//附加文件
        helper.setSubject(subject);//主题
        helper.setText(text, html);//网页

//        helper.addAttachment("1.png", new File("/Users/lianying/Desktop/1.png"));
        //String qq[]={"2524829435@qq.com","1416127947@qq.com"};

        helper.setTo(qq);
        helper.setFrom("2524829435@qq.com");

        mailSender.send(mimeMailMessage);
        System.out.println("邮箱已发送！！！");

    }

    @Async
    public void sendCode(String qq) throws MessagingException {

        String subject = "党员在线教育验证码";


        int code = (int) ((Math.random() * 9 + 1) * 100000);
        String text = "<h1 style=\"color: black;font-weight: 800;\">【信息与机电工程学院党支部】您的验证码是" + code + "</h1>";

        MimeMessage mimeMailMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMailMessage, false);//附加文件
        helper.setSubject(subject);//主题
        helper.setText(text, true);//网页
        helper.setTo(qq);
        helper.setFrom("2524829435@qq.com");
        mailSender.send(mimeMailMessage);
        System.out.println("邮箱已发送！！！");

    }
}
