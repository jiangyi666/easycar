package com.itcast.crm.controller;

import com.itcast.crm.pojo.Customer;
import com.itcast.crm.service.CustomerService;
import com.itcast.crm.utils.JavaMailUtil;
import com.itcast.crm.utils.MailContents.htmlText;
import com.itcast.crm.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Properties;

@Controller
@RequestMapping("login")
public class LoginController {
    @Autowired
    CustomerService customerService;

    @RequestMapping("login")
    public String checkLogin(Customer customer, HttpServletRequest request){
        Customer ul = customerService.checkLogin(customer);
        if (ul==null||ul.equals(""))
        {
            //登录失败,重新跳转到登录界面
            return "login";
        }else {
            //登录成功，重定向到主界面
            //把登录成功的用户信息保存到session中
            //System.out.println(userLogin.getCustomername());
            request.getSession().setAttribute("customerName",ul.getCustomername());
            //把用户的编号放在session域中方便后面创建订单的时候使用
            request.getSession().setAttribute("customerNo",ul.getCustomerno());
            return "redirect:/customer/list.action";
        }

    }
    /**
     * 跳转到登录界面
     * @return 登录界面的地址
     */
    @RequestMapping("userLogin")
    public String toLogin(){
        return "login";
    }

    /**
     * 跳转到注册界面
     * @return
     */
    @RequestMapping("userRegister")
    public String toRegister()
    {
        return "register";
    }
    @RequestMapping("sendEmail")
    @ResponseBody
    public String sendEmail(String email, HttpSession httpSession){
        //平时测试编码时关闭下面代码，要不然一直发送邮件不好。。。。
      /*  JavaMailUtil.receiveMailAccount=email;//给用户输入的邮箱发送邮件
        //1.创建参数配置，用来连接邮箱服务器的参数配置
        Properties props = new Properties();
        //开启Debug模式
        props.setProperty("mail.debug","true");
        //发送服务器需要身份验证
        props.setProperty("'mail.smtp.auth","true");
        //设置邮件服务器的主机名
        props.setProperty("mail.host",JavaMailUtil.emailSMTPHost);
        //发送邮件协议名称
        props.setProperty("mail.transport.protocol","smtp");
        //2.根据配置创建会话对象用来和邮件服务器交互
        Session session = Session.getInstance(props);
        //设置debug，可以查看详细的发送log
        session.setDebug(true);
        //3.创建一封邮件
        String code = RandomUtil.getRandom();
        System.out.println("邮箱验证码:"+code);
        String html = htmlText.sendHtml(code);
        MimeMessage message=null;
        try {
            message= JavaMailUtil.createMimeMessage(session, JavaMailUtil.emailAccount, JavaMailUtil.receiveMailAccount, html);
            //4.根据session获取邮件传输对象
            Transport transport = session.getTransport();
            //5.使用邮箱账号和密码连接邮件服务器emailAccount必须和message中的发件人一样，否则报错
            transport.connect(JavaMailUtil.emailAccount,JavaMailUtil.emailPassword);
            //6.发送邮件，发送所有收件人的地址
            transport.sendMessage(message,message.getAllRecipients());
            //7.关闭连接
            transport.close();
            //写入session
            httpSession.setAttribute("code",code);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发送失败!");
            return "false";
        }
        System.out.println("这是要发送的邮箱"+email);*/
        String code = RandomUtil.getRandom();/*测试的时候使用，非测试请注释掉*/
        System.out.println("邮箱验证码:"+code);
        httpSession.setAttribute("code",code);
        System.out.println("发送成功！");
        return "1";
    }
    @RequestMapping("register")
    public String register(Customer customer, HttpSession httpSession){
        System.out.println("用户注册的名字是"+customer.getCustomername());
        String code =(String) httpSession.getAttribute("code");
        System.out.println(code);
        if(code!=null)
        {
            //获取页面提交的验证码
            String inputCode = customer.getCode();
            System.out.println("页面提交的验证码"+inputCode);
            if(code.toLowerCase().equals(inputCode.toLowerCase()))//验证码填写正确，注册ok！
            {
                //向数据库中添加用户的注册信息
                customerService.createNewUser(customer);
                //验证成功，跳转到登录界面
                return "login";
            }

        }
        //移除验证码
        httpSession.removeAttribute("code");
        //验证失败，再次跳转到登录界面
        return "register";
    }
}
