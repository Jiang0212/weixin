<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<!-- 页面meta -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>消息管理</title>
<meta
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
	name="viewport">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/blue.css">
<link rel="stylesheet" href="css/AdminLTE.css">
<link rel="stylesheet" href="css/_all-skins.min.css">
</head>

<body class="hold-transition skin-purple sidebar-mini">

	<div class="wrapper">

		<!-- 页面头部 -->
		<header class="main-header">

			<!-- Logo -->
			<a href="index.jsp" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
				<span class="logo-mini"><b>商家</b></span> <!-- logo for regular state and mobile devices -->
				<span class="logo-lg"><b>${applicationScope.market}商家</b>小助手${applicationScope.markid}</span>
			</a>
		</header>

		<!-- 内容区域 -->
		<div class="content-wrapper">

			<!-- 内容头部 -->
			<section class="content-header">
				<ol class="breadcrumb">
						<li>
							<a href="index.jsp"><i class="fa fa-dashboard"></i> 首页</a>
						</li>
						<li class="active">消息管理</li>
					</ol>
			</section>
			<!-- 内容头部 /-->

			<!-- 正文区域 -->
			<section class="content">

			</section>
			<!-- 正文区域 /-->

		</div>
		<!-- 内容区域 /-->

		<!-- 底部导航 -->
		<footer class="main-footer" style="text-align: center;">
			<div>
				<strong><a href="index.jsp">首页</a></strong>
			</div>
			<div class="pull-right hidden-xs">
				<b>Jiang Version</b> 1.0.0
			</div>
			<strong>Copyright &copy; 2020 <a href="index.jsp">Jiang</a></strong>
		</footer>
		<!-- 底部导航 /-->

	</div>

	<script src="js/jquery-2.2.3.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/fastclick.min.js"></script>
	<script src="js/icheck.min.js"></script>
	<script src="js/app.min.js"></script>
</body>
</html>