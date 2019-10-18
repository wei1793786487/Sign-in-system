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
    <style>
        .box-body{
            height: 150px;
        }
    </style>
</head>
<body class="hold-transition skin-purple sidebar-mini">

<div class="wrapper">
    <%--    头--%>
    <%@include file="../layout/head.jsp" %>
    <%--    侧--%>
    <%@include file="../layout/left.jsp" %>
    <%
        String id = request.getParameter("id");
        request.setAttribute("meetid", id);
        System.out.println(request.getSession().getAttribute("meetid"));
    %>

    <!-- 内容区域 -->
    <div class="content-wrapper">


        <!-- 内容头部 -->
        <!--正文区域-->
        <section class="content">

            <section class="content-header">
                <h1>
                    &nbsp;
                    人员管理

                </h1>
                <ol class="breadcrumb">
                    <li><a href="manager.jsp"><i class="fa fa-dashboard"></i> 首页</a></li>
                    <li class="active">人员管理</li>
                </ol>
                <br/>
                <br/>

                <form method="post" action="/file" id="people_add" enctype="multipart/form-data">
                    <input type="text"  id="manager_id" name="manager_id" style="display:none" value="${meetid}">
                    <div class="col-md-2 data">
                        <div class="a-upload">
                            <input type="file" name="person_list" id="">选择你要新增的人员图片
                        </div>
                    </div>
<%--                    <div class="col-md-2 data">--%>
<%--                        <div class="a-upload">--%>
<%--                            <input type="button"id="submit_button">--%>
<%--                        </div>--%>
<%--                    </div>--%>
                    <input type="submit" id="submit" class="btn bg-maroon col-md-1 "
                           style="font-size: larger;">
                    <div class="col-md-6"></div>
                </form>
                <br>
                <div class="pull-right ">

                    <div id="peole_bumber" style="color: #3CB371; font-size: large;"></div>
                </div>

                <br/>
                <br>
                <div id="listpeople">
<%--                <div class="col-md-2">--%>
<%--                    <div class="box box-primary">--%>
<%--                        <div class="box-body box-profile">--%>
<%--                            <img class="profile-user-img img-responsive img-circle" src="../img/user4-128x128.jpg"--%>
<%--                                 alt="User profile picture">--%>
<%--                            <h4 class="profile-username text-center">胡国华</h4>--%>
<%--                        </div>--%>
<%--                    </div>--%>

<%--                </div>--%>
<%--                <div class="col-md-2">--%>
<%--                    <div class="box box-primary">--%>
<%--                        <div class="box-body box-profile">--%>
<%--                            <img class="profile-user-img img-responsive img-circle" src="../img/user4-128x128.jpg"--%>
<%--                                 alt="User profile picture">--%>
<%--                            <h4 class="profile-username text-center">胡国华</h4>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>



                </div>

                <%--        &lt;%&ndash;%>
                <%--            String meessage = (String) session.getAttribute("meessage");--%>
                <%--            System.out.println(meessage);--%>

                <%--        %>--%>


            </section>
            <!-- 正文区域 /-->
        </section>
    </div>
    <!-- 内容区域 /-->

    <!-- 底部导航 -->
    <%@include file="../layout/foot.jsp" %>
    <!-- 底部导航 /-->

</div>
<%@include file="../config/js.jsp" %>
<script>

    $(document).ready(function () {
 //动态获取id改变表单的value参数
 //        var parameter = getParameter("id");
 //         $("#manager_id").val(parameter);
         // 提交请求
        $.get("/Meeting/personlist?mid=${meetid}", "", function (data) {
            if(data.information!=null){
                alert(data.information);
                window.history.back(-1);
            }else {
               $("#peole_bumber").html("该会议有"+data.length+"人")

                var listdata = "";
                for (var i = 0; i < data.length; i++) {
                    var list = data[i];
                    var li = ' <div   class="col-md-2">\n' +
                        '                    <div class="box box-primary">\n' +
                        '                        <div class="box-body box-profile">\n' +
                        '                            <img  " class="profile-user-img img-responsive img-circle" src="../upload/'+list.photo_name+'"\n' +
                        '                                 alt="User profile picture">\n' +
                        '                            <h4 class="profile-username text-center">'+list.person_name+'</h4>\n' +
                        '                        </div>\n' +
                        '                    </div>\n' +
                        '\n' +
                        '                </div>'
                    listdata += li;
                }
                // alert(listdata);
                $("#listpeople").html(listdata);
            }

        })


    });
</script>

</body>
</html>
