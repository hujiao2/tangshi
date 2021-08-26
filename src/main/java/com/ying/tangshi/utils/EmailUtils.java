package com.ying.tangshi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Properties;

@Service
public class EmailUtils {
    private static Logger logger = LoggerFactory.getLogger(EmailUtils.class);

    public static void main(String[] args) {
//        sendSimpleMail("hujiao3669@163.com", "这是标题", "正式内容");

    }

    private static synchronized JavaMailSenderImpl getJavaMailSender() {
        // 获取邮箱发送实例
        JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
        javaMailSenderImpl.setHost("smtp.qq.com");
        javaMailSenderImpl.setUsername("1657139917@qq.com");
        javaMailSenderImpl.setPassword("pptlnajdcdrxdbdb");
        javaMailSenderImpl.setDefaultEncoding("UTF-8");
        return javaMailSenderImpl;
    }

    public  boolean sendVerifyCode(String toEmail, String verifyCode) {
        return sendSimpleMail(toEmail, "党员在线教育验证码", "【信息与机电工程学院党支部】您的验证码是" + verifyCode + "，请于3分钟内正确输入");
    }

    private static boolean sendSimpleMail(String toEmail, String subject, String content) {
        logger.info("简单邮件开始发送");
        try {
            JavaMailSenderImpl javaMailSender = getJavaMailSender();
            String username = javaMailSender.getUsername();
            logger.info("当前发送邮箱: " + username);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(username);
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(content);
            javaMailSender.send(message);
            logger.info("简单邮件发送成功");
            return true;
        } catch (Exception e) {
            logger.error("简单邮件发送异常", e);
            e.printStackTrace();
        }
        return false;
    }

    private static boolean sendHtmlMail(String toEmail, String subject, String content) {
        logger.info("HTML邮件开始发送");
        try {
            JavaMailSenderImpl javaMailSender = getJavaMailSender();
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            String username = javaMailSender.getUsername();
            logger.info("当前发送邮箱: " + username);
            messageHelper.setFrom(username);
            messageHelper.setTo(toEmail);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);
            javaMailSender.send(message);
            logger.info("HTML邮件发送成功");
            return true;
        } catch (Exception e) {
            logger.error("HTML邮件发送异常", e);
            e.printStackTrace();
        }
        return false;
    }

}
