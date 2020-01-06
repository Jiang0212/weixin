<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<title>商品扫描</title>
<script type="text/javascript" src="jquery-3.4.0.min.js"></script>
<!-- 引入 WeUI -->
<link rel="stylesheet"
	href="https://res.wx.qq.com/open/libs/weui/2.1.3/weui.min.css">
</head>
<!-- 引入js文件 -->
<script src="http://res.wx.qq.com/open/js/jweixin-1.6.0.js"></script>
<body ontouchstart>
	<div class="weui-cells weui-cells_form">
		<div class="weui-cell">
			<div class="weui-cell__bd">
				<h1>XXX商品条形码${product.code}</h1>
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd">
				<label class="weui-label">产品ID</label>
			</div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="number" pattern="[0-9]*"
					placeholder="${product.id}">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd">
				<label class="weui-label">产品名称</label>
			</div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="number"	placeholder="${product.name}">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd">
				<label class="weui-label">产品单价</label>
			</div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="number"	placeholder="${product.price}">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd">
				<label for="" class="weui-label">生产日期</label>
			</div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="datetime" value="${product.begintime}">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd">
				<label for="" class="weui-label">过期时间</label>
			</div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="datetime" value="${product.overtime}">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__bd">
				<a href="validate.jsp" class="weui-btn weui-btn_primary">修改信息</a>
			</div>
		</div>
	</div>
</body>
</html>