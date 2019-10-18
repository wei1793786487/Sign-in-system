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
<div class="dropdown custom1">
    <button type="button" class="btn dropdown-toggle" data-toggle="dropdown">
        <em>请选择</em>
        <i class="caret"></i>
    </button>
    <ul class="dropdown-menu">
        <li>
            <input class="form-control seach_name" placeholder="请输入搜索内容" />
            <i class="glyphicon glyphicon-search"></i>
        </li>
        <li data="red"><a>red</a></li>
        <li data="blue"><a>blue</a></li>
        <li data="gray"><a>gray</a></li>
        <li data="green"><a>green</a></li>
    </ul>
</div>
<script src="../plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="../plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="../plugins/iCheck/icheck.min.js"></script>
  <script>
      $(function(){
          //new一个实例
          new downSeach('.custom1');
          //new downSeach('.custom2')
      })
  </script>

</body>
</html>
