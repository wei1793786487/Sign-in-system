<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/2
  Time: 0:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>

<!-- 导航侧栏 -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

        <ul class="sidebar-menu">
            <li class="header">菜单</li>

            <li id="admin-index"><a href="../managers/manager.jsp"><i class="fa fa-dashboard"></i> <span>首页</span></a></li>

            <!-- 菜单 -->


            <li class="treeview">
                <a href="#">
                    <i class="fa fa-folder"></i> <span>会议管理</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">

                    <li id="meeting_add">
                        <a href="../managers/meeting_add.jsp">
                            <i class="fa fa-circle-o"></i> 会议添加
                        </a>
                    </li>

                    <li id="admin-register">
                        <a href="../managers/meeting_set.jsp">
                            <i class="fa fa-circle-o"></i> 会议设置
                        </a>
                    </li>

                    <li id="meeting_Administration">
                        <a href="../managers/meeting_Administration.jsp">
                            <i class="fa fa-circle-o"></i> 报名管理
                        </a>
                    </li>
                    <li id="meeting_sign_in">
                        <a href="../managers/meeting_sign_in.jsp">
                            <i class="fa fa-circle-o"></i> 签到管理
                        </a>
                    </li>

                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-cog"></i> <span>安全设置</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">

                    <li id="log">
                        <a href="../managers/log.jsp">
                            <i class="fa fa-circle-o"></i> 日志管理
                        </a>
                    </li>

                </ul>
            </li>
            </li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
<!-- 导航侧栏 /-->
</body>
</html>
