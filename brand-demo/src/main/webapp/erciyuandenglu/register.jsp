<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"><meta name="renderer" content="webkit">
    <title>注册</title>
    <link href="static/css/bootstrap.min-3.4.0.css" rel="stylesheet">
    <link href="static/css/font-awesome-4.3.0.css" rel="stylesheet">
    <link href="static/css/custom.css" rel="stylesheet">
    <link href="static/css/animate.css" rel="stylesheet">
    <link href="static/css/style-2.2.0.css" rel="stylesheet">
    <link href="static/css/index.css" rel="stylesheet">
</head>

<body class="gray-bg">
    <div class="middle-box text-center loginscreen animated fadeInDown">
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
            <h3>欢迎注册 H+</h3>
            <p>创建一个H+新账户</p>
            <form class="m-t" id="zc" role="form" method="post" action="/brand-demo/AccountServlet?method=register">
                <h1>${result}</h1>
                <div class="form-group">
                    <input name="username" type="text" class="form-control" placeholder="请自定义输入账号" required="">
                </div>
                <div class="form-group">
                    <input id="pwd" name="password" type="password" class="form-control" placeholder="请输入密码" required="">
                </div>
                <div class="form-group">
                    <input id="confirm" name="confirm" type="password" class="form-control" placeholder="请再次输入密码" required="">
                </div>
                <div class="form-group">
                    <input id="name" name="name" type="text" class="form-control" placeholder="请输入名字" required="">
                </div>
                
                <button type="submit" class="btn btn-primary block full-width m-b" onclick="return mm();">注 册</button>

                <p class="text-muted text-center"><small>已经有账户了？</small><a href="index.jsp">点此登录</a>
                </p>
                <img src="static/picture/1.gif" style="width: 100%;">
            </form>
        </div>
    </div>

    <!-- Mainly scripts -->
    <script src="static/js/jquery-2.1.1.min.js"></script>
    <script src="static/js/bootstrap.min-3.4.0.js"></script>
    <!-- iCheck -->
    <script src="static/js/icheck.min.js"></script>
    <!-- Jquery Validate -->
    <script src="static/js/jquery.validate.min.js"></script>

    <script>
        $(document).ready(function () {
            $('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-green',
                radioClass: 'iradio_square-green',
            });
            $("#zc").validate({
                rules: {
                    confirm: {
                        equalTo: "#pwd"
                    }
                },
                messages: {
                    username: {
                        required: "请输入您的用户名"
                    },
                    password: {
                        required: "请输入您的密码"
                    },
                    confirm: {
                        required: "请再次输入您的密码",
                        equalTo: "两次输入的密码不一致"
                    },
                }
            });
        });
    </script>
    <script type="text/javascript">
        function stop() {
            return false;
        }
        document.oncontextmenu = stop;
    </script>
    <script src="static/js/live2d.min.js"></script>
    <script src="static/js/index.js"></script>
</body>

</html>
