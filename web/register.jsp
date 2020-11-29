<%@ page import="java.net.URLEncoder" %><%--
  Created by IntelliJ IDEA.
  User: lintao
  Date: 2020/7/28
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="head.jsp"%>
<head>
    <title>Title</title>
    <style type="text/css" rel="stylesheet">
        .wrapper{
            width: 500px;
            font-size: 14px;
            border: 1px black solid ;
        }
        .wrapper .title{
            font-weight: bold;
            font-size: 18px;
            background-color: aliceblue;
            padding: 10px;
        }
        .wrapper div{
            padding: 5px;
            margin: 5px;
        }
        .red{
            color: red;
        }
    </style>
    <script type="text/javascript" src="js/jquery-1.7.2.js"></script>
    <script>

        $(function () {


            $("#id_avatar").change(function () {
                // 1. 创建一个读取文件的对象
                var fileReader = new FileReader();
                //读取所选文件,需要一定时间
                fileReader.readAsDataURL(this.files[0]);
                fileReader.onload = function () {
                    // 2. 读完文件后再讲图片加载到img标签中
                    $("#avatar-img").attr("src", fileReader.result);
                };
            });
            
        })


    </script>

</head>
<body>
<div class="wrapper">
    <div class="title">欢迎注册</div>
    <form action="<%=basePath%>user.do?action=register" method="post" enctype="multipart/form-data">

        <div>
            用户名<span class="red">*</span>
            <span><input type="text" name="username" id="username"></span>
        </div>
        <div>
            密码<span class="red">*</span>
            <span><input type="password" name="password" id="password"></span>
        </div>
        <div>
            确认密码<span class="red">*</span>
            <span><input type="password" name="repassword" id="repassword"></span>
        </div>
        <div>
            邮箱<span class="red">*</span>
            <span><input type="text" name="email" id="email"></span>
        </div>


        <div class="form-group">
            <label class="col-sm-2 control-label">头像</label>
            <div class="col-sm-8">
                <label for="id_avatar"><img id="avatar-img" src="" alt=""></label>
                <input  type="file" name="file" id="id_avatar" >
                <span class="help-block"></span>
            </div>
        </div>

        <div>
            <span><button type="submit">注册</button></span>
        </div>
    </form>
</div>
</body>
</html>
