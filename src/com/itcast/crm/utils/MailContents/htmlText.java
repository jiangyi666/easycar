package com.itcast.crm.utils.MailContents;

public class htmlText {
    /**
     * 发送注册邮件
     * @param code
     * @return
     */
    public static String sendRegisterHtml(String code)
    {
        String html="Email注册验证<br/>"+
                "这封邮件是快拼网发送的。<br/>"+
                "你收到的这封邮件是快拼网进行新用户注册的。<br/>"+
                "账号激活声明<br/>"+
                "请将下面的验证码输入提示框里即可！"+
                "<h3 style='color:red;'>"+code+"</h3><br/>"+
                "如果您没有进行该操作，请忽略，如有打扰，敬请谅解！";
        return html;
    }

    /**
     * 发送找回密码邮件
     * @param code
     * @return
     */
    public static String sendReturnPswdHtml(String code){
        String html="Email找回密码验证<br/>"+
                "这封邮件是快拼网发送的。<br/>"+
                "您正在进行找回密码操作。<br/>"+
                "请将下面的验证码输入提示框里即可！"+
                "<h3 style='color:red;'>"+code+"</h3><br/>"+
                "如果您没有进行该操作，请忽略，如有打扰，敬请谅解！";
        return html;
    }

}
