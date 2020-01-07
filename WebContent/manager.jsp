<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<title>商品管理</title>
<style type="text/css">

</style>
</head>
<!-- 引入js文件 -->
<!-- 引入 WeUI -->
<link rel="stylesheet"
	href="https://res.wx.qq.com/open/libs/weui/2.1.3/weui.min.css">
<link rel="stylesheet" href="css/jquery-weui.css">
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="js/jquery-weui.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.6.0.js"></script>

<body ontouchstart>
	<!-- 容器 -->
	<div class="weui-tab">
		<div class="weui-navbar">
			<a class="weui-navbar__item  weui-bar__item--on" href="#tab1">按时间排序
			</a> <a class="weui-navbar__item" href="#tab2"> 按编号排序 </a> <a
				class="weui-navbar__item" href="#tab3"> 按名称排序</a>
		</div>
		<div class="weui-tab__bd">
			<div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active">
				<h1>时间排序</h1>
			</div>
			<div id="tab2" class="weui-tab__bd-item">
				<h1>编号排序</h1>
			</div>
			<div id="tab3" class="weui-tab__bd-item">
				<h1>名称排序</h1>
			</div>
		</div>
	</div>
</body>
</html>