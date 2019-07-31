<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/3/24
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>注册</title>
    <!-- jQuery -->
    <script src="<%=basePath%>js/jquery.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
    <!-- Bootstrap Core CSS -->
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript">
        $(document).ready(function () {
            $("#loginBox").fadeToggle(1000);

        })
        function sendEmail() {
            if ($("#inputEmail").val())
            {
                $.ajax({
                    type:"POST",
                    url:"<%=basePath%>login/sendEmail.action?random"+Math.random(),
                    data:{
                        email:$("#inputEmail").val(),
                    },
                    success:function (data) {
                        alert("已成功发送验证码到指定邮箱！");
                        //点击发送验证码按钮后，按钮变成灰色不可以点击60秒
                        $("#getCode").attr("disabled",true);
                        //60秒后恢复点击，可以从新发送验证码
                        setTimeout("$(\"#getCode\").removeAttr(\"disabled\")",1000*60);
                        //显示60秒倒计时
                        $("#tipReSend").show();
                        timeCount();
                    },
                })
            }else {
                alert("失败！请填写邮箱！");
            }

        }
        var c=60;
        var t;
        function timeCount() {
            document.getElementById("time").innerHTML=c;
            c-=1;
            t=setTimeout("timeCount()",1000);
            if(c==0){
                c=60;
                clearTimeout(t);
            }
        }
    </script>
</head>
<body style="background-color: #f3f3f4;">
<div class="container" id="loginBox" hidden>
    <div class="row clearfix">
        <div class="col-md-4 column">
        </div>
        <div class="col-md-4 column">
            <form class="form-horizontal" role="form" action="<%=basePath%>login/register.action" method="post">
                <div style="text-align: center" class="col-sm-10">
                    <h3><i><b>快拼网注册</b></i></h3>
                </div>
                <div class="form-group">
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputUsername" autofocus required name="customername"
                               placeholder="用户名">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="inputPassword" autofocus required name="password"
                               placeholder="密码">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="inputPhone" autofocus required name="phone"
                               placeholder="手机号">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="inputQq" autofocus  name="qq"
                               placeholder="(选填)QQ号码">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="inputWechat" autofocus  name="wechat"
                               placeholder="(选填)微信号">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-10">
                        <input type="email" name="email" class="form-control"  id="inputEmail" autofocus required placeholder="请填写邮箱">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-6">
                        <input type="text" name="code" class="form-control" id="code" autofocus  placeholder="验证码">
                    </div>
                    <div class="col-sm-4">
                        <%--这里一定要指定button的type，如果没有指定，点击会提交表单！--%>
                        <button class="btn btn-success btn-block" id="getCode" type="button" value="获取验证码" onclick="sendEmail()">获取验证码</button>
                    </div>
                </div>
                <div class="form-group" id="tipReSend" hidden>
                    <div class="col-sm-10" id="tipReSend-child">
                       在<span id="time"></span> 秒后可选择重新发送
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-10">
                        <%--在这里点击会提交表单--%>
                        <button type="submit" class="btn btn-success btn-block" >注册</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-4 column">
        </div>
    </div>
</div>
</body>
</html>
