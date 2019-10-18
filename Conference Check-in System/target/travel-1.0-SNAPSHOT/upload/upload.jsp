<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/6
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/file/upload" method="post" enctype="multipart/form-data">
    <input type="text" name="usename">
    文件上传<input type="file" name="upload"><br>
    <input type="submit"value="上传">
</form>
</body>
</html>
