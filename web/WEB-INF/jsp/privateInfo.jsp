<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/8/17
  Time: 14:33
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
    <title>个人信息</title>
    <!-- jQuery -->
    <script src="<%=basePath%>js/jquery.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
    <!-- Bootstrap Core CSS -->
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
</head>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h1><i><b>个人信息</b></i></h1>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-2 column">
        </div>
        <div class="col-md-6 column">
            <form class="form-horizontal" role="form" id="create_info_form">
                <div class="form-group">
                    <label for="customerName" class="col-sm-2 control-label">用户名</label>
                    <div class="col-sm-10">
                        <input type="text"  class="form-control" readonly  id="customerName" name="customerName" placeholder="用户名" value="<%=request.getSession().getAttribute("customerName")%>"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" class="col-sm-2 control-label">邮箱号</label>
                    <div class="col-sm-10">
                        <input type="text"  class="form-control" id="email" name="Email" placeholder="邮箱号" readonly value="${privateInfo.email}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="phone" class="col-sm-2 control-label">手机号</label>
                    <div class="col-sm-10">
                        <input type="text"  class="form-control" id="phone" name="Phone" placeholder="手机号" readonly value="${privateInfo.phone}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="wechat" class="col-sm-2 control-label">微信号</label>
                    <div class="col-sm-10">
                        <input type="text"  class="form-control" id="wechat" name="WeChat" placeholder="微信号" readonly value="${privateInfo.wechat}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="qq" class="col-sm-2 control-label">QQ号</label>
                    <div class="col-sm-10">
                        <input type="text"  class="form-control" id="qq" name="Qq" placeholder="QQ号" readonly value="${privateInfo.qq}"/>
                    </div>
                </div>
            </form>
            <div style="text-align: center">
                <a href="#" class="btn btn-info btn-default" data-toggle="modal"
                   data-target="#privateInfoEditDialog"
                   onclick="editInfo('')">编辑我的资料</a>
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
<!-- 客户编辑对话框 -->
<div class="modal fade" id="privateInfoEditDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">编辑个人资料</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="edit_customer_form">
                    <input type="hidden" id="edit_customerno" name="customerno" value="${customerNo}"/>
                    <div class="form-group">
                        <label for="customerName" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-10">
                            <input type="text"  class="form-control" readonly id="editCustomerName" name="customername" placeholder="用户名" value="<%=request.getSession().getAttribute("customerName")%>"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-2 control-label">邮箱号</label>
                        <div class="col-sm-10">
                            <input type="text"  class="form-control" id="editEmail" name="email" placeholder="邮箱号" readonly value="${privateInfo.email}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="phone" class="col-sm-2 control-label">手机号</label>
                        <div class="col-sm-10">
                            <input type="text"  class="form-control" id="editPhone" name="phone" placeholder="手机号"  value="${privateInfo.phone}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="wechat" class="col-sm-2 control-label">微信号</label>
                        <div class="col-sm-10">
                            <input type="text"  class="form-control" id="editWeChat" name="wechat" placeholder="微信号"  value="${privateInfo.wechat}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="qq" class="col-sm-2 control-label">QQ号</label>
                        <div class="col-sm-10">
                            <input type="text"  class="form-control" id="editQq" name="qq" placeholder="QQ号"  value="${privateInfo.qq}"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" onclick="updatePrivateInfo()">修改</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    function updatePrivateInfo() {
        $.post("<%=basePath%>customer/updatePrivateInfo.action",$("#edit_customer_form").serialize(),function (data) {
            alert("修改个人信息成功！");
            window.location.reload();
        })
        
    }
</script>
</html>
