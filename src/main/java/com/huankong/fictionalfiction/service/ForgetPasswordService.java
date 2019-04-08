package com.huankong.fictionalfiction.service;

import com.huankong.fictionalfiction.bean.BookRequestBody;
import com.huankong.fictionalfiction.bean.User;
import com.huankong.fictionalfiction.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class ForgetPasswordService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JavaMailSenderImpl mailSender;

    public void forgetPassword(BookRequestBody bookRequestBody) {
        String username = bookRequestBody.getUsername();
        String email = bookRequestBody.getEmail();

        User user = userMapper.getUserByUserName(username);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        if (user.getEmail().equals(email)) {
            try {
                // 邮件设置
                helper.setSubject("网络小说系统——用户密码找回");
                String text = "<p>听说你忘记了小说用户的密码？对此我们深感抱歉！</p>" +
                        "<p>但别担心！我们可以帮你找回，以下是你的密码：</p>" +
                        "<a>"+ user.getPassword() +"</a>" +
                        "<p>感谢你的支持！</p>";
                helper.setText(text, true);

                helper.setTo(email);
                helper.setFrom("2669868310@qq.com");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        } else {
            try {
                // 邮件设置
                helper.setSubject("网络小说系统——用户密码找回");
                String text = "<p>听说你忘记了小说用户的密码？对此我们深感抱歉！</p>" +
                        "<p>我们尝试帮你找回密码，但是发现该用户并不是使用该邮箱，请认真确认！</p>" +
                        "<p>感谢你的支持！</p>";
                helper.setText(text, true);

                helper.setTo(email);
                helper.setFrom("2669868310@qq.com");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        mailSender.send(mimeMessage);
    }
}
