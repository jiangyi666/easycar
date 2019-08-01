package com.itcast.crm.utils;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class JavaMailUtil {
    //发件人的邮箱和密码
    public static String emailAccount="jiangyi_best@qq.com";
    //发件人的邮箱得授权码
    public static String emailPassword="kawiqbucxxnbbeie";
    //发件人邮箱得服务地址
    public static String emailSMTPHost="smtp.qq.com";
    //收件人邮箱
    public static String receiveMailAccount="";

    /**
     * 创建一封邮件对象
     * @param session
     * @param sendMail
     * @param receiveMail
     * @param html
     * @return
     * cc:抄送
     * Bcc：密送
     * To:发送
     */
    public static MimeMessage createMimeMessage(Session session,String sendMail,String receiveMail,String html) throws UnsupportedEncodingException, MessagingException {
        //创建一封邮件对象
        MimeMessage message = new MimeMessage(session);
        //From:发件人
        message.setFrom(new InternetAddress(sendMail,"快拼网","utf-8"));
        //To:收件人（也可以抄送或者密送)
        message.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(receiveMail,"尊敬的快拼网用户","utf-8"));
        //Subject:邮件的主题
        message.setSubject("邮箱验证","utf-8");
        //content：邮件正文(可以使用html标签)
        message.setContent(html,"text/html;charset=UTF-8");
        //设置发送时间
        message.setSentDate(new Date());
        //保存设置
        message.saveChanges();
        return message;
    }
}
