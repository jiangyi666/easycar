package com.itcast.crm.controller;

import com.itcast.crm.pojo.Customer;
import com.itcast.crm.service.CustomerService;
import com.itcast.crm.utils.ImageValidatedCodeUtil;
import com.itcast.crm.utils.JavaMailUtil;
import com.itcast.crm.utils.MailContents.htmlText;
import com.itcast.crm.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Properties;

@Controller
@RequestMapping("login")
public class LoginController {
    @Autowired
    CustomerService customerService;

    /**
     * 用户登录
     * @param customer
     * @param request
     * @return
     */
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

    /**
     * 发送邮件验证码
     * @param email
     * @param httpSession
     * @return
     */
    @RequestMapping("sendEmail")
    @ResponseBody
    public String sendEmail(String email, HttpSession httpSession,String validatedCode){
//        如果前端验证码不匹配
        String vCode = (String) httpSession.getAttribute("validatedCode");
        if(!validatedCode.toLowerCase().equals(vCode.toLowerCase())){
            //就提示前端验证码错误！
            System.out.println("前端验证码错误");
            httpSession.removeAttribute("validatedCode");//移除验证码
            return "2";
        }
        //检验邮箱是否已经存在（即已经注册过），如果存在就会返回客户编号
        //如果不存在就会返回null
        String status = customerService.checkEmailIsExisted(email);
        if(status==null){//该邮箱不存在，即允许注册
            //平时测试编码时关闭下面代码，要不然一直发送邮件不好。。。。
        JavaMailUtil.receiveMailAccount=email;//给用户输入的邮箱发送邮件
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
            return "false";//发送失败，即发送问题或者提供的邮箱 有问题；
        }
            System.out.println("这是要发送的邮箱"+email);
            //String code = RandomUtil.getRandom();/*测试的时候使用，非测试请注释掉*/
            System.out.println("邮箱验证码:" + code);
            httpSession.setAttribute("code", code);
            System.out.println("发送成功！");
            return "1";//返回1表示邮箱不存在且发送验证码成功
        }else {
            return "0";//该邮箱存在，放回0，提示（不允许注册)
        }
    }

    /**
     * 用户注册
     * @param customer
     * @param httpSession
     * @return
     */
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

    /**
     * 获得前端图片验证码
     */
    @RequestMapping("getValidatedCode")
    public void getValidatedCode(HttpServletResponse response,HttpSession session)throws Exception{
        //利用图片工具生成图片
        //第一个参数是生成的验证码，第二个参数是生成的图片
        Object[] objs = ImageValidatedCodeUtil.createImage();
        //将验证码存入Session
        session.setAttribute("validatedCode",objs[0]);
        //禁止缓存
        response.setHeader("Prama","no-cache");
        response.setHeader("Coche-Control","no-cache");
        response.setDateHeader("Expires",0);
        response.setContentType("image/png");
        //将图片输出给浏览器
        BufferedImage image = (BufferedImage) objs[1];
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
        os.close();
    }
}
