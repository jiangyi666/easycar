<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/8/18
  Time: 16:35
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
    <meta name="description" content="">
    <meta name="author" content="">
    <title>反馈</title>
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

</head>
<body style="background-color: #f3f3f4;">
<div class="container">
    <div class="row clearfix" id="titleHeader" hidden>
        <div class="col-md-12 column">
            <h1><i><b>快拼网反馈</b></i></h1>
        </div>
    </div>
    <div class="row clearfix" id="feedBackDiv" hidden>
        <div class="col-md-2 column">
        </div>
        <div class="col-md-6 column">
            <p style="color: forestgreen;">
                <em><strong>感谢您使用快拼网，如果您在使用途中发现什么问题，
                    您可以在下方填写后反馈给我们，我们会努力改进，您的支持是对我们最大的鼓励！
                    如果快拼网的问题对您造成不便，我们对此深表歉意！您也可以添加QQ反馈！</strong></em>
            </p>
            <p>
                <a href="mqqwpa://im/chat?chat_type=wpa&uin=2926250136&version=1&src_type=web&web_src=lvlingseeds.com" target="_blank">QQ反馈(手机端)</a>
                <a href="tencent://message/?uin=2926250136&Site=&menu=yes" target="_blank">QQ反馈(pc端)</a>

            </p>
            <form class="form-horizontal" role="form" id="create_feedBack_form" action="">
                <input type="hidden" id="create_customerno" name="customerno" value="<%=request.getSession().getAttribute("customerNo")%>"/>
                <%--<input type="hidden" id="create_orderno" name="orderno" value="${orderno}"/>--%>
                <div class="form-group">
                    <label for="create_note" class="col-sm-2 control-label">反馈内容</label>
                    <div class="col-sm-10">
                            <textarea type="" style="width: 100%;height: 50%;resize: none;overflow: hidden" class="form-control"
                                      id="create_note" placeholder="请详细描述您在使用过程中发现的问题，谢谢您的反馈！"
                                      name="details"></textarea>
                    </div>
                </div>
            </form>
            <div style="text-align: center">
               <button class="btn btn-success" onclick="createFeedBack()" > <span class="glyphicon glyphicon-send"></span> 反馈</button>
            </div>
        </div>
        <div class="col-md-4 column">
        </div>
    </div>
    <%--页脚--%>
    <div class="row clearfix">
        <div class="col-md-12 column">
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $("#titleHeader").toggle(1000);
        $("#feedBackDiv").fadeToggle(1000);
    })
    function createFeedBack() {
        if($("#create_note").val()){
            $.post("<%=basePath%>feedBack/createFeedBack.action",$("#create_feedBack_form").serialize(),function (data) {
                alert("感谢您的反馈，祝您生活愉快！")
                window.location.href="<%=basePath%>customer/list.action";
            })
        }else {
            alert("请填写反馈内容再提交!")
        }
    }
</script>
</body>
</html>
