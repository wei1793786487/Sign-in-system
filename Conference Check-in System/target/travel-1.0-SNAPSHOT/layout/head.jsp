    <%--
      Created by IntelliJ IDEA.
      User: Administrator
      Date: 2019/10/2
      Time: 0:22
      To change this template use File | Settings | File Templates.
    --%>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
        <html>
        <head>

        </head>
        <body>
        <!-- 页面头部 -->
        <header class="main-header">
        <!-- Logo -->
        <a href="all-admin-index.html" class="logo">
        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"><b>数据</b></span>
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg"><b>数据</b>后台管理</span>
        </a>

        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
        </a>

        <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">


        <!-- User Account: style can be found in dropdown.less -->



        <li class="dropdown user user-menu">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
        <img src="../img/user2-160x160.jpg" class="user-image" alt="User Image">
        <span class="hidden-xs">${manager.name}</span>
        </a>
        <ul class="dropdown-menu">
        <!-- User image -->
        <li class="user-header">
        <img src="../img/user2-160x160.jpg" class="img-circle" alt="User Image">

        <p>
        ${manager.name}—普通管理员
        <small>最后登录 ${manager.lasttime}</small>
        </p>
        </li>

        <!-- Menu Footer-->
        <li class="user-footer">
        <div class="pull-left">
        <a href="../managers/passwordchangce.jsp" class="btn btn-default btn-flat">修改密码</a>
        </div>
        <div class="pull-right">
        <a href="/user/exits" class="btn btn-default btn-flat">注销</a>
        </div>
        </li>
        </ul>
        </li>

        </ul>
        </div>
        </nav>
        </header>
        <!-- 页面头部 /-->
        </body>
        </html>
