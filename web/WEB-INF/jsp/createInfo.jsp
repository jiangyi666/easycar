<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/3/19
  Time: 19:38
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
    <title>寻找拼友</title>
    <!-- jQuery -->
    <script src="<%=basePath%>js/jquery.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
    <!-- Bootstrap Core CSS -->
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript">
        function createInfo() {
            <%--由于jquery的validated验证无法在ajax提价中使用 --%>
            <%-- 因为在ajax中required无法使用--%>
            if($("#create_startAddress").val()&&$("#create_endAddress").val()&&$("#create_orderDate").val()){
                $.post("<%=basePath%>customer/createInfo.action", $("#create_info_form").serialize(), function (data) {
                    alert("发布拼车信息成功！");
                    window.location.href = "<%=basePath%>customer/list.action";
                });
            }else {
                alert("请填写相关信息")
            }
        }
    </script>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h1><i><b>快拼网发布</b></i></h1>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-2 column">
        </div>
        <div class="col-md-6 column">
            <form class="form-horizontal" role="form" id="create_info_form">
                <%--<input type="hidden" id="create_customerno" name="customerno" value="${customerno}"/>--%>
                <%--<input type="hidden" id="create_orderno" name="orderno" value="${orderno}"/>--%>
                <div class="form-group">
                    <label for="create_startAddress" class="col-sm-2 control-label">起点</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="create_startAddress" placeholder="起点"
                             required  name="start_address"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="create_endAddress" class="col-sm-2 control-label">终点</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="create_endAddress" placeholder="终点"
                               required  name="end_address"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="create_orderDate" class="col-sm-2 control-label">时间</label>
                    <div class="col-sm-10">
                        <input type="date" class="form-control" id="create_orderDate" placeholder="时间"
                               required   name="orderdate"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="create_note" class="col-sm-2 control-label">备注</label>
                    <div class="col-sm-10">
                            <textarea type="" style="resize: none;overflow: hidden" class="form-control"
                                      id="create_note" placeholder="备注"
                                       name="note"></textarea>
                    </div>
                </div>


            </form>
            <div style="text-align: center">
                <button class="btn btn-success" onclick="createInfo()">发布</button>
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
</body>
</html>
