package com.itcast.crm.utils.MailContents;

public class htmlText {
    public static String sendHtml(String code)
    {
        String html="Email地址验证<br/>"+
                "这封邮件是快拼网发送的。<br/>"+
                "你收到的这封邮件是快拼网进行新用户注册的。<br/>"+
                "账号激活声明<br/>"+
                "请将下面的验证码输入提示框里即可！"+
                "<h3 style='color:red;'>"+code+"</h3><br/>";
        return html;
    }

}
