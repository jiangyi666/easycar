<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="itcast" uri="http://itcast.cn/common/" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">


    <title>快拼网</title>
    <!-- jQuery -->
    <script src="<%=basePath%>js/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<%=basePath%>js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="<%=basePath%>js/metisMenu.min.js"></script>

    <!-- DataTables JavaScript -->
    <script src="<%=basePath%>js/jquery.dataTables.min.js"></script>
    <script src="<%=basePath%>js/dataTables.bootstrap.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="<%=basePath%>js/sb-admin-2.js"></script>

    <!-- Bootstrap Core CSS -->
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="<%=basePath%>css/metisMenu.min.css" rel="stylesheet">

    <!-- DataTables CSS -->
    <link href="<%=basePath%>css/dataTables.bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%=basePath%>css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<%=basePath%>css/font-awesome.min.css" rel="stylesheet"
          type="text/css">
    <link href="<%=basePath%>css/boot-crm.css" rel="stylesheet"
          type="text/css">
    <%--引入页脚--%>
    <link href="<%=basePath%>css/footer.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
        function editCustomer(id) {
            $.ajax({
                type: "get",
                url: "<%=basePath%>customer/edit.action",
                data: {"id": id},
                success: function (data) {
                    $("#detail_customernoo").val(data.customerno);
                    $("#detail_customerName").val(data.customername);
                    $("#detail_startAddress").val(data.start_address);
                    $("#detail_endAddress").val(data.end_address)
                    $("#detail_orderDate").val(data.orderdate)
                    $("#detail_phone").val(data.phone)
                    $("#detail_qq").val(data.qq);
                    $("#detail_wechat").val(data.wechat);
                    $("#detail_note").val(data.note);
                }
            });
        }

    </script>
</head>

<body>

<div id="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation"
         style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">快拼网</a>
        </div>
        <!-- /.navbar-header -->

        <ul class="nav navbar-top-links navbar-right">

            <i>欢迎您，<%=request.getSession().getAttribute("customerName")%></i>
            <!-- /.dropdown -->
            <li class="dropdown"><a class="dropdown-toggle"
                                    data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
                <i class="fa fa-caret-down"></i>
            </a>
                <ul class="dropdown-menu dropdown-user">
                    <li><a href="#"><i class="fa fa-user fa-fw"></i> 用户设置</a></li>
                    <%--<li><a href="#"><i class="fa fa-gear fa-fw"></i> 系统设置</a></li>--%>
                    <li class="divider"></li>
                    <li><a href="<%=basePath%>/login/userLogin.action"><i class="fa fa-sign-out fa-fw"></i>
                        退出登录</a></li>
                </ul>
                <!-- /.dropdown-user -->
            </li>
            <!-- /.dropdown -->
        </ul>
        <!-- /.navbar-top-links -->

        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">

                    <li><a href="<%=basePath%>/customer/list.action" class="active"><i
                            class="fa fa-edit fa-fw"></i>查询拼车</a></li>
                    <li><a href="<%=basePath%>/customer/toPersonInfo.action"><i
                            class="fa fa-dashboard fa-fw"></i> 我的发布</a></li>
                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side --> </nav>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-8">
                <h1 class="page-header">开启出行</h1>
            </div>
            <div class="col-lg-4">
                <%--<button type="button" class="btn btn-success page-header" style="float: right" >--%>
                <%--免费发布--%>
                <%--</button>--%>
                <a href="${pageContext.request.contextPath }/customer/toCreate.action"
                   class="btn btn-success btn-xs page-header" style="float:right;">免费发布</a>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="panel panel-default">
            <div class="panel-body">
                <form class="form-inline" action="${pageContext.request.contextPath }/customer/list.action"
                      method="get">
                    <div class="form-group">
                        <label for="startAddress">起点</label>
                        <input type="text" class="form-control" id="startAddress" value="${startAddress }"
                               name="startAddress">
                    </div>
                    <div class="form-group">
                        <label for="endAddress">终点</label>
                        <input type="text" class="form-control" id="endAddress" value="${endAddress }"
                               name="endAddress">
                    </div>
                    <div class="form-group">
                        <label for="orderDate">时间</label>
                        <input type="date" id="orderDate" value="${orderDate}" name="orderDate">
                    </div>
                    <button type="submit" class="btn btn-primary">查询</button>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">以下为查询结果</div>
                    <!-- /.panel-heading -->
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>用户名</th>
                            <th>起点</th>
                            <th>终点</th>
                            <th>出发时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${page.rows}" var="row">
                            <tr>
                                <td>${row.customername}</td>
                                <td>${row.start_address}</td>
                                <td>${row.end_address}</td>
                                <td>${row.orderdate}</td>
                                <td>
                                    <a href="#" class="btn btn-primary btn-xs" data-toggle="modal"
                                       data-target="#customerEditDialog"
                                       onclick="editCustomer('${row.orderno}')">查看详情</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="col-md-12 text-right">
                        <itcast:page url="${pageContext.request.contextPath }/customer/list.action"/>
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-12 -->
        </div>
    </div>
    <!-- /#page-wrapper -->

</div>
<%--页脚可以写在这里--%>
<%--页脚--%>
<div id="footer">
    <div style="text-align: center">
        <br>
        <font color="#f5f5f5" style="font-family: -apple-system" size="2">All Rights Reserved.</font>
        <font color="#f5f5f5"><span class="glyphicon glyphicon-copyright-mark"></span></font>
        <font color="#f5f5f5" style="font-family: -apple-system" size="2">Easy Car.2019</font>
    </div>
</div>
<%--页脚结束--%>
<!-- 客户编辑对话框 -->
<div class="modal fade" id="customerEditDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">查看详情</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="edit_customer_form">
                    <input type="hidden" id="detail_customerno" name="customerno"/>
                    <div class="form-group">
                        <label for="detail_customerName" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="detail_customerName" placeholder="用户名"
                                   readonly name="customername">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="detail_startAddress" class="col-sm-2 control-label">起点</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="detail_startAddress" placeholder="起点"
                                   readonly name="start_address">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="detail_endAddress" class="col-sm-2 control-label">终点</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="detail_endAddress" placeholder="终点"
                                   readonly name="end_address">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="detail_orderDate" class="col-sm-2 control-label">时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="detail_orderDate" placeholder="时间"
                                   readonly name="orderdate">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="detail_phone" class="col-sm-2 control-label">电话</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="detail_phone" placeholder="电话"
                                   readonly name="phone">
                        </div>
                    </div>
                    <div class="form-group">

                    </div>
                    <div class="form-group">
                        <label for="detail_qq" class="col-sm-2 control-label">QQ号码</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="detail_qq" placeholder="QQ号码"
                                   readonly name="qq">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="detail_wechat" class="col-sm-2 control-label">微信</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="detail_wechat" placeholder="微信"
                                   readonly name="wechat">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="detail_note" class="col-sm-2 control-label">备注</label>
                        <div class="col-sm-10">
                            <textarea type="" style="resize: none;overflow: hidden" class="form-control"
                                      id="detail_note" placeholder="备注"
                                      readonly name="note"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>





</body>

</html>
