<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/3/24
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
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
    <title>登录</title>
    <!-- jQuery -->
    <script src="<%=basePath%>js/jquery.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
    <!-- Bootstrap Core CSS -->
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <%--Jquery验证--%>
    <script src="https://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
    <script src="https://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <script src="https://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
    <script>
        $.validator.setDefaults({
            submitHandler: function(form) {
               form.submit();
            }
        });
        $().ready(function() {
            $("#commentForm").validate();
        });
    </script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#loginBox").fadeToggle(1000);

        })
    </script>
    <script type="text/javascript">
        var msg="${loginMsg}";
       if(typeof msg!=null&&msg!="")
       {
           alert(msg)
       }
    </script>
    <style type="text/css">
        .toRegister:link{
           color: #4cae4c;
        }
        .toRegister:hover{
           color: red;
        }
        label.error{
            color:indianred;
        }
    </style>
</head>
<body style="background-color: #f3f3f4;">
<div class="container" id="loginBox" hidden>
    <div class="row clearfix">
        <div class="col-md-4 column">
        </div>
        <div class="col-md-4 column">
            <form class="form-horizontal" id="commentForm" role="form" action="<%=basePath%>login/login.action" method="post">
                <div style="text-align: center" class="col-sm-10">
                    <h3><i><b>快拼网登录</b></i></h3>
                </div>
                <div class="form-group">
                    <div class="col-sm-10">
                        <input type="email" class="form-control" id="inputEmail3" name="email" autofocus required placeholder="邮箱"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="inputPassword3" name="password" autofocus required
                               placeholder="密码"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-10">
                        <button type="submit" class="btn btn-success btn-block">登录</button>
                    </div>
                </div>
                <div class="col-sm-10" align="center">
                    <%--去注册界面--%>
                    <a href="<%=basePath%>login/userRegister.action" class="toRegister">
                        <i>还没有账号，去注册</i>
                    </a>
                        <span class="glyphicon glyphicon-option-vertical"></span>
                    <%--去找回密码--%>
                    <a href="<%=basePath%>/login/toFindPswd.action" class="toRegister" >
                        <i>忘记密码?</i>
                    </a>
                </div>
            </form>
        </div>
    </div>

</div>
</body>
</html>
