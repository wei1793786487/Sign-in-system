<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/2
  Time: 1:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../config/css.jsp" %>

</head>
<body class="hold-transition skin-purple sidebar-mini">


<div class="wrapper">
    <%--    头--%>
    <%@include file="../layout/head.jsp" %>
    <%--    侧--%>
    <%@include file="../layout/left.jsp" %>

    <!-- 内容区域 -->
        <div class="content-wrapper">
            <!-- 内容头部 -->
            <section class="content-header">
                <h1>
                    &nbsp 会议管理系统

                </h1>
                <ol class="breadcrumb">
                    <li><a href="index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
                    <li class="active">密码修改</li>
                </ol>
            </section>
            <!-- 内容头部 /-->

            <section>
                <div class="box-body">

                    <!--tab页-->

                    <!--tab头/-->

                    <!--tab内容-->
                    <div class="ab-common">
                        <!--基础控件-->



                        <div class="tab-pane active" id="tab-common">
                            <div class="row data-type">
                                <div class="col-md-2"></div>
                                <div class="col-md-2"></div>
                                <div class="col-md-3 title">密码修改</div>
                                <div class="col-md-5"></div>
                                <br />
                                <br />
                                <br />

                                <form id="pswdc" action="#" method="">

                                    <div class="col-md-3 title">请输入你原来的密码</div>
                                    <div class="col-md-7 data">
                                        <input type="password" name="oldpassword" class="form-control" >

                                    </div>

                                    <div class="col-md-3 title">请输入你的新密码</div>
                                    <div class="col-md-7 data">
                                        <input type="password" name="newpassword" class="form-control" >
                                    </div>
                                    <div class="col-md-3 title">重复输入你的新密码</div>
                                    <div class="col-md-7 data">
                                        <input type="password" name="newpassword2" class="form-control" >
                                    </div>

                                    <div class="col-md-10 data text-center">
                                        <button type="button" id="pc" class="btn bg-maroon"style="font-size: larger;" >提交</button>
                                        <button type="button" class="btn bg-default" onclick="location.reload()">重置</button>

                                    </div>

                                </form>

                            </div>
                        </div>
                        <!--基础控件/-->


                    </div>


                </div>
            </section>

        </div>
        <!--内容区域 /-->

    <!-- 底部导航 -->
    <%@include file="../layout/foot.jsp" %>
    <!-- 底部导航 /-->

</div>
<%@include file="../config/js.jsp" %>
   <script>
       $("#pc").click(function () {

           $.post("/user/chancepswd",$("#pswdc").serialize(),function (data) {
               alert(data.information);
           })
       })
   </script>
</body>
</html>
