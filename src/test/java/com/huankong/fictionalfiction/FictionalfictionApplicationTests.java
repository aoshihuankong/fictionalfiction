package com.huankong.fictionalfiction;

import com.huankong.fictionalfiction.bean.User;
import com.huankong.fictionalfiction.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FictionalfictionApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    public void contextLoads() {
        User user = userMapper.getUserById(1);
        System.out.println(user);
    }

    @Test
    public void contextLoads1() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        // 邮件设置
        helper.setSubject("通知，今晚开会");
        helper.setText("<a>今晚7：30开会</a>", true);

        helper.setTo("1946945401@qq.com");
        helper.setFrom("2669868310@qq.com");

        mailSender.send(mimeMessage);
    }

}
