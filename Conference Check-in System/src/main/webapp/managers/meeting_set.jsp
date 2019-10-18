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
                <section class="content-header">
                    <h1>
                        会议列表

                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="manager.jsp"><i class="fa fa-dashboard"></i> 首页</a></li>
                        <li class="active">会议列表</li>
                    </ol>
                </section>
                <!-- 内容头部 /-->

                <!-- 正文区域 -->
                <section class="content">

                    <!-- .box-body -->
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title">列表</h3>
                        </div>

                        <div class="box-body">

                            <!-- 数据表格 -->
                            <div class="table-box">

                                <!--工具栏-->
                                <div class="pull-left">
                                    <div class="form-group form-inline">
                                        <div class="btn-group">
                                            <button type="button" id="new" class="btn btn-default" title="新建"><i class="fa fa-file-o"></i> 新建</button>
                                            <button type="button" id="delete" class="btn btn-default" title="删除"><i class="fa fa-trash-o"></i> 删除</button>

                                            <button type="button" id="onload" class="btn btn-default" title="刷新"><i class="fa fa-refresh"></i> 刷新</button>
                                        </div>
                                    </div>
                                </div>
                                <div class="box-tools pull-right">
                                    <div class="has-feedback">
                                        <input type="text" class="form-control input-sm" placeholder="搜索">
                                        <span id="sousuo" class="glyphicon glyphicon-search form-control-feedback"></span>
                                    </div>
                                </div>
                                <!--工具栏/-->

                                <!--数据列表-->
                                <form id="sumitdelect" action="/Meeting/delectMeeting" method="post">
                                <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                                    <thead>
                                    <tr>
                                        <th >
<%--                                                class="" style="padding-right:0px;">--%>
<%--                                            <input id="selall" type="checkbox" class="icheckbox_square-blue">--%>
                                        </th>
                                        <th class="">ID</th>
                                        <th class="">会议名称</th>
                                        <th class="">会议详情</th>
                                    </tr>
                                    </thead>
                                    <tbody id="list" >

<%--                                    <tr>--%>
<%--                                        <td><input name="ids" type="checkbox"></td>--%>
<%--                                        <td>1</td>--%>
<%--                                        <td>demo会议--%>
<%--                                        </td>--%>
<%--                                        <td>--%>
<%--                                            <button type="button" class="btn bg-olive btn-sm"onclick="window.location='manage_view.jsp'">查看会议</button>--%>
<%--                                        </td>--%>

<%--                                    </tr>--%>


                                    </tbody>
                                    <!--
                                <tfoot>
                                <tr>
                                <th>Rendering engine</th>
                                <th>Browser</th>
                                <th>Platform(s)</th>
                                <th>Engine version</th>
                                <th>CSS grade</th>
                                </tr>
                                </tfoot>-->
                                </table>
                                    </form>
                                <!--数据列表/-->


                            </div>
                            <!-- 数据表格 /-->


                        </div>
                        <!-- /.box-body -->

                        <!-- .box-footer-->
                        <div class="box-footer">
                            <div class="pull-left">
                                <div class="form-group form-inline">
                                    <br />
                                    <br />
                                    总共<span id="total"></span> 页，共<span id="totalcont"></span> 条数据。
                                </div>
                            </div>

                            <div class="box-tools pull-right">
                                <ul id="pages" class="pagination">
<%--                                    <li>--%>
<%--                                        <a href="#" aria-label="Previous">首页</a>--%>
<%--                                    </li>--%>
<%--                                    <li><a href="#">上一页</a></li>--%>
<%--                                    <li><a href="#">1</a></li>--%>
<%--                                    <li><a href="#">2</a></li>--%>
<%--                                    <li><a href="#">3</a></li>--%>
<%--                                    <li><a href="#">4</a></li>--%>
<%--                                    <li><a href="#">5</a></li>--%>
<%--                                    <li><a href="#">下一页</a></li>--%>
<%--                                    <li>--%>
<%--                                        <a href="#" aria-label="Next">尾页</a>--%>
<%--                                    </li>--%>
                                </ul>
                            </div>

                        </div>
                        <!-- /.box-footer-->



                    </div>


                </section>
                </section>

        </div>
    <!-- 内容区域 /-->

    <!-- 底部导航 -->
    <%@include file="../layout/foot.jsp" %>
    <!-- 底部导航 /-->

</div>
<%@include file="../config/js.jsp" %>
<%--<script>--%>

<%--</script>--%>
<script>
    //分页展示的方法
    function load(currenpage) {
        // alert("ahahsha");
        $.get("/Meeting/meetinglist", {currentPage: currenpage}, function (pb) {
            $("#total").html(pb.total);
            $("#totalcont").html(pb.totalcont)

            //分页
            //算出上/下一页页码
            var beforepage = currenpage - 1;
            var alterpage = currenpage+1;
            var page = "";
            var fristpage='  <li>\n' +
                '                                        <a href="javascript:load('+1+')" aria-label="Previous">首页</a>\n' +
                '                                    </li>\n'

                    if(beforepage<1){
                        fristpage+=     '                                    <li class="disabled"><a href="javascript:javascript:void(0)">上一页</a></li>'
                    }else {
                        fristpage+=     '                                    <li><a href="javascript:load('+beforepage+')">上一页</a></li>'
                    }

            page = fristpage + page;
            //单页前五后四显示逻辑
            var begin;
            var end;
            if (pb.total < 10) {
                begin = 1;
                end = pb.total;
            } else {
                begin = currenpage - 5;
                end = currenpage + 4;
                if (begin < 1) {
                    begin = 1;
                    end = begin + 9;
                }
                if (end > pb.total) {
                    end = pb.total;
                    begin = end - 9;
                }
            }

            //显示
            for (var i = begin; i <= end; i++) {
                var li;

                if (pb.currentpage == i) {
                    li=' <li class="active" onclick="load('+i+')"><a href="javascript:void(0)">'+i+'</a></li>'
                } else {
                    li = '<li onclick="javascript:load('+i+')"><a href="javascript:void(0)">' + i + '</a></li>'
                }
                page += li;
            }

            var lastpage='';
            if (alterpage>pb.total){
                lastpage=' <li class="disabled"><a href="javascript:void(0)">下一页</a></li>\n'
            }else {
                lastpage=' <li><a href="javascript:load('+alterpage+')">下一页</a></li>\n'
            }

          lastpage+=
                '                                    <li>\n' +
                '                                        <a href="javascript:load('+pb.total+')" aria-label="Next">尾页</a>\n' +
                '                                    </li>'
            page += lastpage
            $("#pages").html(page)


            // //具体的数值
            var router_id = "";
            for (var i = 0; i < pb.list.length; i++) {
                var route = pb.list[i];
                var li = '  <tr>\n' +
                    '                                        <td><input name="ids" type="checkbox" value="'+ route.id +'" class="icheckbox_square-blue"></td>\n' +
                    '                                        <td>'+(i+1)+'</td>\n' +
                    '                                        <td>'+route.meeting_name+'\n' +
                    '                                        </td>\n' +
                    '                                        <td>\n' +
                    '                                            <a type="button" href="manage_view.jsp?id='+route.id+'" class="btn bg-olive btn-sm"">查看会议</button>\n' +
                '                                        </td>\n' +
                '\n' +
                '                                    </tr>'

                router_id += li;
            }
            $("#list").html(router_id)
            window.scrollTo(0,0);刷新页面到顶部
        })
    }





    jQuery(document).ready(function() {

        $("#new").click(function () {
            window.location.href="../managers/meeting_add.jsp";
        })
        $("#onload").click(function () {
            location.reload();
            //刷新此页面
        })

        $("#sousuo").click(function () {

            $.get("/Meeting/meetinglist",$("#sousuoform").serialize(),function (data) {


            })

        })
        document.getElementById("delete").onclick = function () {
            if (confirm("你确定要删除吗？删除之后不可以在撤销")) {
                //防止空指针异常
                var flag = false;
                var ckx = document.getElementsByName("ids")
                for (var i = 0; i < ckx.length; i++) {
                    if (ckx[i].checked) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    document.getElementById("sumitdelect").submit();
                    <%--                        ${deforms}.submit();--%>
                }

            }

        }
        // 激活导航位置
        setSidebarActive("admin-register");
        var currentPage = getParameter("currentPage")
        // $.get("/Meeting/meetinglist","",function (pb) {
        //
        // // $("#total").html(pb.total);
        // // $("#totalcont").html(pb.totalcont)
        // })
        // alert("hahaahh");
      load(1);


    });


</script>
</body>
</html>
