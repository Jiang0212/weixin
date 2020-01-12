<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ "/";
%>
<!doctype html>
<html lang="zh-CN" class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>商家小助手</title>
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="stylesheet" href="css/amazeui.min.css">
<link rel="stylesheet" href="css/app.css">

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
		imageEle.src = "/weixin/VerifyCodeServlet?a=" + new Date().getTime();
	}
	function check() {
		var remember = document.getElementById('remember');
		if (remember.checked) {
			var cookie = $.cookie('user', 'password');
			$.cookie("user", $(".user").val(), {
				expire : 15
			});
			$.cookie("password", $(".password").val(), {
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
<body>
	<div class="am-g myapp-login">
		<div class="myapp-login-bg">
			<div class="myapp-login-logo">
				<i class="am-icon-stumbleupon"></i>
			</div>
			<div class="am-u-sm-10 myapp-login-form">
				<form class="am-form" id="form1" action="LoginServlet" method="post">
					<fieldset>
						<div class="am-form-group">
							<input type="text" class="user" name="user" id="doc-ipt-text-1" value=""
								placeholder="请输入账号">
						</div>
						<div class="am-form-group">
							<input type="password" class="password" name="password" id="doc-ipt-pwd-1" value=""
								placeholder="请输入密码">
						</div>
						<p class="login-box-msg" style="color: red;"><%=message%></p>
						<div class="am-form-group">
							<font color="black">验证码：</font><input name="verifyCode"
								style="width: 25%;"><img id="img"
								src="/weixin/VerifyCodeServlet" size="4" /> <a
								href="javascript:_change()" />&nbsp;换一张
						</div>
			    		<p>
							<input id="remember" type="checkbox" /><font>记住密码</font><button type="submit"
								onclick="check()" class="am-btn am-btn-default">登录</button>
						</p>
			  </fieldset>
			</form>
		 </div>
	</div>
</div>
<!--[if (gte IE 9)|!(IE)]><!-->
<script src="js/jquery.min.js"></script>
<script src="js/jquery.cookie.js"></script>
<!--<![endif]-->
<!--[if lte IE 8 ]>
<script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->
<script src="js/amazeui.min.js"></script>
<script src="js/app.js"></script>
<script>
	$(function() {
		var user = $.cookie('user');
		var password = $.cookie('password');
		if (user != null) {
			$("#remember").attr("checked", "checked");
			$(".user").val(user);
			$(".password").val(password);
		}
	});
</script>
</body>
</html>