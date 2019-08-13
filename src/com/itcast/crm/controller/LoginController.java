package com.itcast.crm.controller;

import com.itcast.crm.pojo.Customer;
import com.itcast.crm.service.CustomerService;
import com.itcast.crm.utils.ImageValidatedCodeUtil;
import com.itcast.crm.utils.JavaMailUtil;
import com.itcast.crm.utils.MailContents.htmlText;
import com.itcast.crm.utils.RandomUtil;
import com.itcast.crm.utils.SendEmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
     *
     * @param customer
     * @param request
     * @return
     */
    @RequestMapping("login")
    public String checkLogin(Customer customer, HttpServletRequest request) {
        Customer ul = customerService.checkLogin(customer);
        if (ul == null || ul.equals("")) {
            //登录失败,重新跳转到登录界面
            return "login";
        } else {
            //登录成功，重定向到主界面
            //把登录成功的用户信息保存到session中
            //System.out.println(userLogin.getCustomername());
            request.getSession().setAttribute("customerName", ul.getCustomername());
            //把用户的编号放在session域中方便后面创建订单的时候使用
            request.getSession().setAttribute("customerNo", ul.getCustomerno());
            return "redirect:/customer/list.action";
        }

    }

    /**
     * 跳转到登录界面
     *
     * @return 登录界面的地址
     */
    @RequestMapping("userLogin")
    public String toLogin() {
        return "login";
    }

    /**
     * 跳转到注册界面
     *
     * @return
     */
    @RequestMapping("userRegister")
    public String toRegister() {
        return "register";
    }

    /**
     * 注册的时候发送邮件验证码
     *需要进行前端的图片验证码匹配，如果错误就不发送邮件
     * @param email
     * @param httpSession
     * @return
     */
    @RequestMapping("sendEmail")
    @ResponseBody
    public String sendEmail(String email, HttpSession httpSession, String validatedCode) {
//        如果前端验证码不匹配
        String vCode = (String) httpSession.getAttribute("validatedCode");
        if(vCode!=null){
            if (!validatedCode.toLowerCase().equals(vCode.toLowerCase())) {
                //就提示前端验证码错误！
                httpSession.removeAttribute("validatedCode");//移除验证码
                return "2";
            }
            //检验邮箱是否已经存在（即已经注册过），如果存在就会返回客户编号
            //如果不存在就会返回null
            String status = customerService.checkEmailIsExisted(email);
            if (status == null) {//该邮箱不存在，即允许注册
                String code = RandomUtil.getRandom();//获得随机生成的验证码
                //String html = htmlText.sendRegisterHtml(code);//生成注册的时候的html文件
                httpSession.setAttribute("code", code);//将验证码存放在httpSession域中
                //return SendEmailUtil.sendEmail(email,html);
                return "1";
            } else {
                return "0";//该邮箱存在，返回0，提示该邮箱已近存在（不允许注册)
            }
        }
        return "2";

    }



    /**
     * 用户注册提交注册信息
     *
     * @param customer
     * @param httpSession
     * @return
     */
    @RequestMapping("register")
    public String register(Customer customer, HttpSession httpSession) {
        System.out.println("用户注册的名字是" + customer.getCustomername());
        String code = (String) httpSession.getAttribute("code");//从httpSession中获得code的值
        System.out.println("用户注册的时候提交的邮箱验证码"+code);
        if (code != null) {
            //获取页面提交的验证码
            String inputCode = customer.getCode();
            System.out.println("页面提交的验证码" + inputCode);
            if (code.toLowerCase().equals(inputCode.toLowerCase()))//验证码填写正确，注册ok！
            {
                //向数据库中添加用户的注册信息
                customerService.createNewUser(customer);
                //验证成功，跳转到登录界面
                //验证成功后就可以移除验证码
                httpSession.removeAttribute("validatedCode");
                httpSession.removeAttribute("code");
                return "login";
            }

        }
        //验证失败，再次跳转到注册界面
        return "register";
    }

    /**
     * 跳转到找回密码界面（主要为验证)
     * @return
     */
    @RequestMapping("toFindPswd")
    public String toFindPswd(){
        return "FindPswd";
    }

    /**
     * 发送找回密码的邮件
     * @param email 待发送验证码的邮件地址
     * @param httpSession
     * @param validatedCode 前端的图片验证码
     * @return 2就提示前端验证码错误！，0表示该邮箱不存在不允许发送邮箱验证码
     * false发送失败，即发送问题或者提供的邮箱 有问题，1表示发送成功
     */
    @RequestMapping("sendReturnPswdEmail")
    @ResponseBody
    public String sendReturnPswdEmail(String email,HttpSession httpSession,String validatedCode){
        // 如果前端验证码不匹配
        String vCode = (String) httpSession.getAttribute("validatedCode");
        if(vCode!=null){
            if (!validatedCode.toLowerCase().equals(vCode.toLowerCase())) {
                //就提示前端验证码错误！
                httpSession.removeAttribute("validatedCode");//移除验证码
                //这个验证码不能移除，如果移除了，如果用户还是输入之前那个验证码，上面拿vCode就会报空指针异常
                return "2";//2就提示前端验证码错误！
            }
            //检验邮箱是否已经存在（即已经注册过），如果存在就会返回客户编号
            //如果不存在就会返回null
            String status = customerService.checkEmailIsExisted(email);
            if (status == null) {//该邮箱不存在，不允许发送邮箱验证码
                return "0";//该邮箱不存在不允许发送邮箱验证码
            } else {
                //否则该邮箱存在，发送邮箱验证
                String code = RandomUtil.getRandom();//获得随机生成的邮箱验证码
                System.out.println("邮箱验证码:"+code);
                //String html = htmlText.sendReturnPswdHtml(code);//生成找回密码的时候的html文件
                httpSession.setAttribute("code", code);//将验证码存放在httpSession域中
                //return SendEmailUtil.sendEmail(email,html);
                return "1";//测试代码，发布的时候去掉
            }
        }
        return "2";

    }

    /**
     * 跳转到重置密码界面
     * @param model 用来给前端返回数据
     * @param httpSession 用来拿到保存的系统生成的邮箱验证码
     * @return
     */
    @RequestMapping("toResetPswd")
    public String toResetPswd(Customer customer, Model model, HttpSession httpSession){
        String emailCode = (String) httpSession.getAttribute("code");//从httpSession中获得code的值，邮箱验证码
        if(emailCode!=null){
            if(customer.getCode().toLowerCase().equals(emailCode.toLowerCase()))
            {
                //前端提交的邮箱验证码正确，就跳转到密码重置
                //先把需要重置密码的用户的邮箱存放起来
                System.out.println("用户提交的邮箱:"+customer.getEmail());
                model.addAttribute("emailOfResetPswd",customer.getEmail());
                httpSession.removeAttribute("code");//成功就移除
                httpSession.removeAttribute("validatedCode");
                return "resetPswd";
            }else {
                //如果提交的邮箱验证码错误，就再次跳转到邮箱验证界面
                model.addAttribute("codeStatus","邮箱验证码错误！");
                return "FindPswd";
            }
        }
        return "FindPswd";


    }

    /**
     * 重置密码
     * @return
     */
    @RequestMapping("resetPswd")
    public String resetPswd(Customer customer ){
        customerService.updatePassword(customer);
        return "login";
    }

    /**
     * 获得前端图片验证码
     */
    @RequestMapping("getValidatedCode")
    public void getValidatedCode(HttpServletResponse response, HttpSession session) throws Exception {
        //利用图片工具生成图片
        //第一个参数是生成的验证码，第二个参数是生成的图片
        Object[] objs = ImageValidatedCodeUtil.createImage();
        //将验证码存入Session
        session.setAttribute("validatedCode", objs[0]);
        //禁止缓存
        response.setHeader("Prama", "no-cache");
        response.setHeader("Coche-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/png");
        //将图片输出给浏览器
        BufferedImage image = (BufferedImage) objs[1];
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
        os.close();
    }
}
