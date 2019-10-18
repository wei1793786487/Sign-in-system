<%--
  Created by IntelliJ IDEA.
  User: 郑文海
  Date: 2018/11/16
  Time: 9:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>在线人脸检测</title>

    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/demo.css">
    <script src="../plugins/jQuery/jquery-3.2.1.min.js"></script>
    <script src="../js/getParameter.js"></script>
</head>
<body>
<div class="uploadimg-group">
    <img src="" id="uploadimg">
    <button class="loadbtn">打开摄像头</button>
    <button class="uploadbtn">签到确认</button>
</div>
<div class="video_port" id="video_port">
    <video id="video" width="400" height="300"></video>
    <canvas id="vi_canver" width="400" height="300" hidden></canvas>
    <p class="picturebtn">
        <button id="screenshots" class="screenshots">截取签到图像</button>
        <span class="myshu"></span>
        <button id="close" class="closecamera">关闭摄像头</button>
    </p>
</div>
<script>
    $(document).ready(function(){
        var mid = getParameter("mid");
        // 这里设置一个全局变量， 作为图像base64
        var imgdata = null;
        $('.loadbtn').click(function(){
            jQuery('.video_port').show();
            function $(elem) {
                return document.querySelector(elem);
            };

            var canvas = document.getElementById("vi_canver");
            var context = canvas.getContext("2d");
            var video = $('#video'),
                screen = $('#screenshots'),
                close = $('#close'),
                mediaStream;

            //打开摄像头主要用到下面的getUserMedia方法，用来将获取到的媒体流传入video标签中
            navigator.mediaDevices.getUserMedia({
                video: true
            }).then(function(stream) {
                // 这里面写成功的回调函数
                console.log(stream);
                mediaStream = typeof stream.stop === 'function' ? stream : stream.getTracks()[1];
                video.src = (window.URL || window.webkitURL).createObjectURL(stream);
                video.play();
            }).catch(function(error) {
                // 这里是失败的回调
                console.log(error);
            })
            // 这里截取图片的原理为截取video画面中的当前帧，然后使用canvas中drawImage方法将内容绘至canvas中
            screen.addEventListener('click', function() {
                context.drawImage(video, 0, 0, 400, 300);
                var type = 'jpeg';
                //在这里我直接将canvas中的内容转化为base64格式，传入到需要显示的img中
                imgdata = canvas.toDataURL(type)
                jQuery("#uploadimg").attr("src",imgdata);
            }, false);
            // 关闭摄像头
            close.addEventListener('click', function() {
                mediaStream && mediaStream.stop();
                jQuery('.video_port').hide();
            }, false);
        });

        $('.uploadbtn').click(function(){
            imgdata = imgdata.split(',')[1];
            $.post("/video",{image:imgdata,mid:mid},function (data) {
               var information = data.information;
               alert(information);

            })

        })

    })
</script>
</body>
</html>
