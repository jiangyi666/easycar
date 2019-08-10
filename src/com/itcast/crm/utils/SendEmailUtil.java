package com.itcast.crm.utils;

import com.itcast.crm.utils.MailContents.htmlText;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.util.Properties;

/**
 * 该类是发送邮件验证码的关键工具类
 */
public class SendEmailUtil {
    /**
     * 发送邮件
     * @param email 待发送对象的email地址
     * @param html 需要发送的HTML内容
     * @return
     */
    public static String sendEmail(String email,String html) {
        //平时测试编码时关闭下面代码，要不然一直发送邮件不好。。。。
        JavaMailUtil.receiveMailAccount = email;//给用户输入的邮箱发送邮件
        //1.创建参数配置，用来连接邮箱服务器的参数配置
        Properties props = new Properties();
        //开启Debug模式
        props.setProperty("mail.debug", "true");
        //发送服务器需要身份验证
        props.setProperty("'mail.smtp.auth", "true");
        //设置邮件服务器的主机名
        props.setProperty("mail.host", JavaMailUtil.emailSMTPHost);
        //发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");
        //2.根据配置创建会话对象用来和邮件服务器交互
        Session session = Session.getInstance(props);
        //设置debug，可以查看详细的发送log
        session.setDebug(true);
        //3.创建一封邮件
        MimeMessage message = null;
        try {
            message = JavaMailUtil.createMimeMessage(session, JavaMailUtil.emailAccount, JavaMailUtil.receiveMailAccount, html);
            //4.根据session获取邮件传输对象
            Transport transport = session.getTransport();
            //5.使用邮箱账号和密码连接邮件服务器emailAccount必须和message中的发件人一样，否则报错
            transport.connect(JavaMailUtil.emailAccount, JavaMailUtil.emailPassword);
            //6.发送邮件，发送所有收件人的地址
            transport.sendMessage(message, message.getAllRecipients());
            //7.关闭连接
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发送失败!");
            return "false";//发送失败，即发送问题或者提供的邮箱 有问题；
        }
        System.out.println("发送成功!");
        return "1";//返回1表示发送验证码成功
    }
}
