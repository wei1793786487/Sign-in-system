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
                &nbsp;
                日志管理

            </h1>
            <ol class="breadcrumb">
                <li><a href="manager.jsp"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">用户登录日志管理</li>
            </ol>


            <!-- 正文区域 -->
            <section class="content">

                <!-- .box-body -->
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">会议详情列表</h3>
                    </div>

                    <div class="box-body">

                        <!-- 数据表格 -->
                        <div class="table-box">

                            <!--工具栏-->
                            <div class="pull-left">
                                <div class="form-group form-inline">
                                    <div class="btn-group">

                                        <button type="button" id="delete" class="btn btn-default" title="删除"><i
                                                class="fa fa-trash-o"></i> 删除
                                        </button>

                                        <button type="button"  id="onload" class="btn btn-default" title="刷新"><i
                                                class="fa fa-refresh"></i> 刷新
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <!--工具栏/-->

                            <!--数据列表-->
                            <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                                <thead>
                                <tr>
                                    <th class="" style="padding-right:0px;">
<%--                                        <input id="selall" type="checkbox" class="icheckbox_square-blue">--%>
                                    </th>
                                    <th class="">ID</th>
                                    <th class="">操作ip</th>
                                    <th class="">登录时间</th>
                                    <th class="">执行操作</th>

                                </tr>
                                </thead>
                                <tbody id="list">

<%--                                <tr>--%>
<%--                                    <td><input name="ids" type="checkbox"></td>--%>
<%--                                    <td>1</td>--%>
<%--                                    <td>加拿大--%>
<%--                                    </td>--%>
<%--                                    <td>2000-01-24</td>--%>
<%--                                    <td>删库跑路</td>--%>
<%--                                    <td>大哥</td>--%>

<%--                                </tr>--%>


                                </tbody>

                            </table>
                            <!--数据列表/-->


                        </div>
                        <!-- 数据表格 /-->


                    </div>
                    <!-- /.box-body -->

                    <!-- .box-footer-->
                    <div class="box-footer">
                        <div class="pull-left">
                            <div class="form-group form-inline">
                                <br/>
                                <br/>
                                总共<span id="total"></span> 页，共<span id="totalcont"></span> 条数据。
                            </div>
                        </div>

                        <div class="box-tools pull-right">
                            <div class="box-tools pull-right">
                                <ul id="pages" class="pagination">

                                </ul>
                            </div>
                        </div>

                    </div>
                    <!-- /.box-footer-->


                </div>
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
    function load(currenpage) {
        // alert("ahahsha");
        $.get("/log/manager_log", {currentPage: currenpage}, function (pb) {

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
                    '                                    <td><input name="ids" type="checkbox"  class="icheckbox_square-blue"></td>\n' +
                    '                                    <td>'+(i+1)+'</td>\n' +
                    '                                    <td>'+route.ip+'\n' +
                    '                                    </td>\n' +
                    '                                    <td>'+route.time+'</td>\n' +
                    '                                    <td>'+route.action+'</td>\n' +
                    '\n' +
                    '                                </tr>'

                router_id += li;
            }
            $("#list").html(router_id)
            window.scrollTo(0,0);
        })
    }

    $(document).ready(function () {
        $("#new").click(function () {
            window.location.href = "../managers/meeting_add.jsp";
        })
        $("#onload").click(function () {
            location.reload();
            //刷新此页面
        })

        $("#sousuo").click(function () {

            $.get("/Meeting/meetinglist", $("#sousuoform").serialize(), function (data) {


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
        load(1);
        // 激活导航位置
        setSidebarActive("log");


    });
</script>
</body>
</html>
