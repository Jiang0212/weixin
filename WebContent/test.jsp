<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>登录首页</title>
<meta
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
	name="viewport">
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/ionicons.min.css">
<link rel="stylesheet" href="css/AdminLTE.css">
<link rel="stylesheet" href="css/blue.css">
<link rel="stylesheet" href="css/_all-skins.min.css">
<%
	String message = " ";
	String msg = (String) request.getAttribute("msg");
	if (msg != null) {
		message = msg;
	}
%>
<script type="text/javascript">
	function _change() {
		var imageEle = document.getElementById("img");
		imageEle.src = "/weixin/VerifyCodeServlet?a="
				+ new Date().getTime();
	}
	function check() {
		var remember = document.getElementById('remember');
		if (remember.checked) {
			var cookie = $.cookie('user', 'password');
			$.cookie("user", $("#user").val(), {
				expire : 15
			});
			$.cookie("password", $("#password").val(), {
				expire : 15
			});
		} else {
			$.cookie("user", "", {
				expire : -1
			});
			$.cookie("password", "", {
				expire : -1
			});
		}
	}
</script>
</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<b>商家</b>小助手
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<p class="login-box-msg" style="color: red;"><%=message%></p>
			<p class="login-box-msg">登录系统</p>
			<form id="form1" action="LoginServlet" method="post">
				<div class="form-group has-feedback">
					<input type="text" required class="form-control" name="user"
						id="user" placeholder="账号"> <span
						class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" required class="form-control"
						name="password" id="password" placeholder="密码"> <span
						class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					验证码：<input style="width: 40%;" type="text" name="verifyCode" /> <img
						id="img" src="/weixin/VerifyCodeServlet" size="3" /> <a
						href="javascript:_change()" />换一张</a><br /> </span>
				</div>
				<div class="row">
					<div class="col-xs-8">
						<div class="checkbox icheck">
							<label><input id="remember" type="checkbox">记住密码</label>
						</div>
					</div>
					<!-- /.col -->
					<div class="col-xs-4">
						<button type="submit" onclick="check()"
							class="btn btn-primary btn-block btn-flat">登录</button>
					</div>
				</div>
			</form>

		</div>
	</div>
	<!-- jQuery 2.2.3 -->
	<!-- Bootstrap 3.3.6 -->
	<!-- iCheck -->
	<script src="js/jquery-2.2.3.min.js"></script>
	<script src="js/jquery.cookie.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/icheck.min.js"></script>
	<script>
		$(function() {
			var user = $.cookie('user');
			var password = $.cookie('password');
			if (user != null) {
				$("#remember").attr("checked", "checked");
				$("#user").val(user);
				$("#password").val(password);
			}
			$('input').iCheck({
				checkboxClass : 'icheckbox_square-blue',
				radioClass : 'iradio_square-blue',
				increaseArea : '20%' // optional
			});
		});
	</script>
</body>
</html>