<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"><meta name="renderer" content="webkit">
    <title>登录</title>
    <link href="static/css/bootstrap.min-3.4.0.css" rel="stylesheet">
    <link href="static/css/font-awesome-4.3.0.css" rel="stylesheet">
    <link href="static/css/animate.css" rel="stylesheet">
    <link href="static/css/style-2.2.0.css" rel="stylesheet">
    <link href="static/css/index.css" rel="stylesheet">
</head>

<body class="gray-bg">
    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div class="form-signin">
            <div id="stage">
                <div id="inner">
                    <div id="cover">
                        <div id="text">
                            <span style="color: cyan;">H</span><span style="color: white;">+</span>
                        </div>
                        <div id="detail"></div>
                        <div id="handle"></div>
                    </div>
                    <canvas class="mb-4" id="live2d" width="800" height="800"></canvas>
                </div>
                <a id="info" href="javascript:info()"><i class="fa fa-lg fa-info"></i></a>
                <a id="refresh" href="javascript:refresh()"><i class="fa fa-lg fa-refresh"></i></a>
            </div>
            <h3>欢迎使用</h3>

            <form class="m-t" role="form" method="post" action="/brand-demo/AccountServlet?method=login" id="loginForm">
                <div class="form-group">
                    <h2 id="errorMsg">${account}</h2>
                    <input name="username" type="phone" class="form-control" placeholder="用户名" value="admin" required="">
                </div>
                <div class="form-group">
                    <input name="password" type="password" class="form-control" placeholder="密码" required="">
                </div>
                
                <button type="submit" class="btn btn-primary block full-width m-b" id="btn_sub">登 录</button>
                <div class="form-group">
                    <div class="checkbox i-checks">
                        <span class="text-muted text-center"><a href="register.jsp">注册一个新账号</a>
                        </span>
                    </div>
                </div>
                <img src="static/picture/1.gif" style="width: 100%;">
            </form>
        </div>
    </div>

    <!-- Mainly scripts -->
    <script src="static/js/jquery-2.1.1.min.js"></script>
    <script src="static/js/bootstrap.min-3.4.0.js"></script>
    <script type="text/javascript">
        function stop() {
            return false;
        }
        document.oncontextmenu = stop;

        $(function (){
            // 1 给登录按钮绑定单击事件
            $("#btn_sub").click(function (){
                // 2 发送ajax请求，提交表单数据
                $.post("/brand-demo/AccountServlet?method=login",$("#loginForm").serialize(),function (data,status){
                    if (status){
                        console.log(data)
                        //登录成功
                        // location.href="index.jsp";
                    }else {
                        //登录失败
                        $("#errorMsg").html(data);
                    }
                })
            })
        })
    </script>
    <script src="static/js/live2d.min.js"></script>
    <script src="static/js/index.js"></script>

</body>

</html>
