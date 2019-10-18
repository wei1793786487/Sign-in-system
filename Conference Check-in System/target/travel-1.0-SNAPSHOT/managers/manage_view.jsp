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
                    <li><a href="manager.jsp"><i class="fa fa-dashboard"></i> 首页</a></li>
                    <li class="active">会议管理</li>
                </ol>
            </section>
            <!-- 内容头部 /-->
            <section class="content">

                <div class="box-body">

                    <!--tab页-->
                    <div class="nav-tabs-custom">

                        <!--tab头-->
                        <ul class="nav nav-tabs">

                            <li  class="active">
                                <a data-toggle="tab">会议查看</a>
                            </li>

                        </ul>
                        <!--tab头/-->

                        <!--tab内容-->
                        <div class="ab-common">
                            <!--基础控件-->
                            <div class="tab-pane active" id="tab-common">
                                <div class="row data-type">

                                    <div class="col-md-2 title">会议名称</div>
                                    <div  id="meeting_name" class="col-md-4 data">

                                    </div>

                                    <div class="col-md-2 title">会议地点</div>
                                    <div id="meeting_address" class="col-md-4 data">

                                    </div>




                                    <div class="col-md-2 title">发起人联系电话</div>
                                    <div  id="meeting_phone" class="col-md-4 data">

                                    </div>



                                    <div class="col-md-2 title">会议时间</div>
                                    <div  id="meeting_time" class="col-md-4 data">

                                    </div>

                                    <div class="col-md-2 title">人数</div>
                                    <div  id="meeting_number" class="col-md-4 data">

                                    </div>






                                    <br />




                                </div>
                            </div>
                            <!--基础控件/-->

                        </div>
                        <!--tab内容/-->

                    </div>
                    <!--tab页/-->

                </div>

            </section>

        </div>
    <!-- 内容区域 /-->

    <!-- 底部导航 -->
    <%@include file="../layout/foot.jsp" %>
    <!-- 底部导航 /-->

</div>
<%@include file="../config/js.jsp" %>
<script>
    $(document).ready(function() {
    var parameter = getParameter("id");
        $.get("/Meeting/findmeetingbyid?id="+parameter+"","",function (data) {
         if (data.information!=null){
             alert(data.information);
             window.history.back(-1);
         }else{
             $("#meeting_name").html(data.meeting_name);
             $("#meeting_address").html(data.metting_address);
             $("#meeting_time").html(data.times);
             $("#meeting_phone").html(data.meeting_phone);
             $("#meeting_number").html(data.meeting_number);
         }


        })


    });
</script>

</body>
</html>
