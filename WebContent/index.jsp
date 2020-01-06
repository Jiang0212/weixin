<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<title>功能界面</title>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

.header {
	height: 50px;
	width: 100%;
	background: #e0ffff;
	text-align: center;
}

.menu {
	width: 50%;
	height: 150px;
}
</style>
<script type="text/javascript" src="jquery-3.4.0.min.js"></script>
<!-- 引入 WeUI -->
<link rel="stylesheet"
	href="https://res.wx.qq.com/open/libs/weui/2.1.3/weui.min.css">
</head>
<!-- 引入js文件 -->
<script src="http://res.wx.qq.com/open/js/jweixin-1.6.0.js"></script>
</head>
<body ontouchstart>
	<div class="header">广告区</div>
	<div class="main">
		<div class="menu">
			<a href="manager.jsp"><img src="images/icon_nav_special.png"><br>
				<span>商品管理</span></a>
		</div>
		<div class="menu">
			<a href="scan.jsp"><img src="images/icon_nav_special.png"><br>
				<span>商品扫描</span></a>
		</div>
		<div class="menu">
			<a href="news.jsp"><img src="images/icon_nav_special.png"><br>
				<span>消息中心</span></a>
		</div>
	</div>
	</div>
	<div class="weui-footer weui-footer_fixed-bottom">
		<p class="weui-footer__links">
			<a href="index.jsp" class="weui-footer__link">首页</a>
		</p>
		<p class="weui-footer__text">Copyright © 2019 Jiang</p>
	</div>
</body>
</html>