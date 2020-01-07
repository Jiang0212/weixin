<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<title>处理结果</title>
<script type="text/javascript" src="jquery-2.1.4.min.js"></script>
<!-- 引入 WeUI -->
<link rel="stylesheet"
	href="https://res.wx.qq.com/open/libs/weui/2.1.3/weui.min.css">
</head>
<!-- 引入js文件 -->
<script src="http://res.wx.qq.com/open/js/jweixin-1.6.0.js"></script>
<body ontouchstart>
	<div class="weui-msg">
		<div class="weui-msg__icon-area">
			<i class="weui-icon-success weui-icon_msg"></i>
		</div>
		<div class="weui-msg__text-area">
			<h2 class="weui-msg__title">修改成功</h2>
			<p class="weui-msg__desc">
				请选择要进行的操作
			</p>
		</div>
		<div class="weui-msg__opr-area">
			<p class="weui-btn-area">
				<a href="index.jsp" class="weui-btn weui-btn_primary">返回首页</a>
				<a href="scan.jsp" class="weui-btn weui-btn_default">继续扫描商品</a>
			</p>
		</div>
		<div class="weui-footer weui-footer_fixed-bottom">
			<p class="weui-footer__links">
				<a href="choose.jsp" class="weui-footer__link">首页</a>
			</p>
			<p class="weui-footer__text">Copyright © 2019 Jiang</p>
		</div>
	</div>
</body>
</html>