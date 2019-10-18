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
                <li class="active">签到管理</li>
            </ol>
        </section>
        <!-- 内容头部 /-->
        <section class="content">

            <div class="box-body">

                <!--tab页-->
                <div class="nav-tabs-custom">

                    <!--tab头-->
                    <ul class="nav nav-tabs">


                        <li class="active">
                            <a href="#tab-treetable" data-toggle="tab">签到人数</a>
                        </li>

                    </ul>
                    <!--tab头/-->

                    <!--tab内容-->
                    <div class="active">
                        <!--树表格-->
                        <div class="tab-pane" id="tab-treetable">
                            <table id="collapse-table" class="table table-bordered table-hover dataTable">
                                <thead>
                                <tr>
                                    <th>人员名册</th>

                                </tr>
                                </thead>
                                <tr>
                                    <td id="people_number"></td>

                                </tr>
                                <tr data-tt-id="0" id="checkafter">
                                    <td>已签到</td>
                                </tr>

                                <%--                                <p id="check">--%>


<%--                                <tr data-tt-id="1" data-tt-parent-id="0">--%>
<%--                                    <td>大花</td>--%>
<%--                               </tr>--%>
                                    <%--&lt;%&ndash;                                    </tr>&ndash;%&gt;--%>
                                    <%--                                </p>--%>

                                <tr data-tt-id="2" id="uncheckafter">
                                    <td>未签到</td>

                                </tr>

                                <%--                                <div id="uncheck">--%>


                                <%--&lt;%&ndash;                                                                        <tr data-tt-id="20" data-tt-parent-id="2">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;                                                                            <td>小花花</td>&ndash;%&gt;--%>
                                <%--                                    &lt;%&ndash;                                    </tr>&ndash;%&gt;--%>
                                <%--                                </div>--%>

                            </table>
                        </div>
                        <!--树表格/-->


                    </div>
                    <!--tab内容/-->

                </div>
                <!--tab页/-->

                <!-- .box-footer
    <div class="box-footer"></div>
    -->
                <!-- /.box-footer-->

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
    $(document).ready(function () {

        var parameter = getParameter("id");
        $.ajaxSettings.async = false;
        $.get("/Meeting/findCheck", {id: parameter}, function (data) {
            // alert(parameter);
            var checklist = "";
            var unchecklist = "";
            var number = 0;
            var Proportion = 0;
       // alert(data.uncheckByMid.length);

            if (data.checkByMid.length>0){
                for (var i = 0; i < data.checkByMid.length; i++) {

                    checklist += "\n" +
                        "                                    <tr data-tt-id=" + (i + 1) + "\" data-tt-parent-id=0>\n" +
                        "                                        <td>" + data.checkByMid[i].person_name + "</td>";
                    number++;
                    // alert(number);
                }
            }

              if (data.uncheckByMid.length>0){
                  for (var z = 0; z < data.uncheckByMid.length; z++) {
                      unchecklist += "   <tr data-tt-id=" + (z + 1) + "\" data-tt-parent-id=2>\n" +
                          "                    <td>" + data.uncheckByMid[z].person_name + "</td>\n" +
                          "\n" +
                          "\n" +
                          "                    </tr>"
                      number++;
                      // alert(number);
                  }
              }




            // alert(data.checkByMid.length)
            // alert(data.uncheckByMid.length)
            Proportion = ((data.checkByMid.length) / number) * 100;
             // alert(Proportion);
             // alert(number);
            $("#checkafter").after(checklist);
            $("#uncheckafter").after(unchecklist)
            // $("#peole_bumber").html(number);
            // $("#Proportion").html(Proportion);
            $("#people_number").html("总人数" + number + "（签到完成比例：" + Proportion + "%）");

        })

        /*table tree*/
        $("#collapse-table").treetable({
            expandable: true
        });

    });
</script>
</body>
</html>
