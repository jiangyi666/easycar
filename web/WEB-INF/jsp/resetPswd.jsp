<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/8/12
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
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
    <meta name="description" content="快拼网重置密码">
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
    <script type="text/javascript">
        $(document).ready(function () {
            $("#loginBox").fadeToggle(1000);

        })
    </script>
    <style type="text/css">
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
            <form class="form-horizontal" id="commentForm" role="form" action="<%=basePath%>login/resetPswd.action?email=${emailOfResetPswd}" method="post">
                <div style="text-align: center" class="col-sm-10">
                    <h3><i><b>重置密码</b></i></h3>
                </div>
                <div class="form-group">
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="inputPassword" name="password" autofocus required placeholder="新的密码"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" autofocus required
                               placeholder="请再次确认密码"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-10">
                        <button type="submit" class="btn btn-success btn-block">确认</button>
                    </div>
                </div>
                <div class="col-sm-10" align="center">
                  <%--预留--%>
                </div>
            </form>
        </div>
    </div>

</div>
<script>
    $.validator.setDefaults({
        submitHandler: function(form) {
            form.submit();
        }
    });
    //名称为name属性!
    $().ready(function() {
        $("#commentForm").validate({
            rules:{
                password:{
                    required:true,
                    minlength:6
                },
                confirmPassword:{
                    required:true,
                    minlength:6,
                    equalTo:"#inputPassword"
                }
            },
            messages:{
                password:{
                    required:"请输入密码",
                    minlength:"密码长度不小于6位"
                },
                confirmPassword:{
                    required:"请再次输入密码",
                    minlength:"密码长度不小于6位",
                    equalTo:"两次密码输入不一致"
                }
            }
        });
    });
</script>
</body>
</html>
