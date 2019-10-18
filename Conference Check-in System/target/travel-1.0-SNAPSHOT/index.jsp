
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<!DOCTYPE html>--%>
<%--<html>--%>

<head>
    <link rel="stylesheet" href="./plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="./plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="./plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="./plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet" href="./plugins/iCheck/square/blue.css">
</head>

<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">


        <a href="index.jsp">多用户会议签到系统</a>
        <a>（登录界面）</a>


    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <div class="login-box-msg">管理员登录系统</div>

        <form id="loginForm" action="#" method="">
            <div class="form-group has-feedback">
                <input type="text" name="username" class="form-control" placeholder="请填写您的账号信息">
                <span class="fa  fa-user form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" name="password" class="form-control" placeholder="请填写您的密码">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input name="check" type="text" class="form-control" placeholder="请输入验证码" autocomplete="off" style="float:left;width:200px;">
                <span style="float:right;"><img id="checkimg" src="${pageContext.request.contextPath}/user/Verification"onclick="changeCheckCode(this)"></span>
                <script type="text/javascript">
                    //图片点击事件
                    function changeCheckCode(img) {
                        img.src="${pageContext.request.contextPath}/user/Verification?"+new Date().getTime();
                    }

                </script>
            </div>

            <p></p><br />
            <p></p><br />

            <div class="row" >
                <div class="col-xs-8">
                    <label>
                        <input type="checkbox"> 记住密码，下次自动登录
                    </label>
                </div>
                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="button" id="login" class="btn btn-primary btn-block btn-flat">登录</button>
                </div>
                <!-- /.col -->
            </div>
        </form>

<%--        <div class="social-auth-links text-center">--%>
<%--            <p>- 或者 -</p>--%>
<%--            <a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-qq"></i> 腾讯QQ用户登录</a>--%>
<%--            <a href="#" class="btn btn-block btn-social btn-google btn-flat"><i class="fa fa-weixin"></i> 微信用户登录</a>--%>
<%--        </div>--%>
        <!-- /.social-auth-links -->

<%--        <a href="#">忘记密码</a>--%>

        <p id="errorMsg"class="h3" style="text-align: center;"></p>

    </div>
    <!-- /.login-box-body -->
</div>


            <script src="./plugins/jQuery/jquery-2.2.3.min.js"></script>
            <script src="./plugins/bootstrap/js/bootstrap.min.js"></script>
            <script src="./plugins/iCheck/icheck.min.js"></script>

<!-- /.login-box -->

<!-- jQuery 2.2.3 -->
<!-- Bootstrap 3.3.6 -->
<!-- iCheck -->
<%--&lt;%&ndash;//这个tmd不是报错 这是转发之后放在js文件的丢失 别删 &ndash;%&gt;--%>
<%--<%@include file="../js/js.jsp" %>--%>
<%--<%@include file="js/js.jsp" %>--%>
<script>

    $(function () {
        $("p").hide();
        $("#login").click(function () {
            $.post("/user/login",$("#loginForm").serialize(),function (data) {

                if (data.flag){
                    if (data.user==0){
                        window.location.href="surper/surper.jsp";
                    } else if (data.user==1){
                        window.location.href="managers/manager.jsp";
                    }else {
                        $("#errorMsg").html("用户异常")
                    }
                }else{//如果登录失败的话再换验证码 就不会出现登录成功还换验证码的不舒服了
                    var s1 = "/user/Verification?" + new Date().getTime();
                    var s = $("#checkimg").prop("src", s1);
                    $("p").show();
                    $("#errorMsg").html(data.errorMsg)
                }
            })
        })
    })

    $(function() {
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });
    });


</script>
</body>

</html>