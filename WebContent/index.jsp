<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<!-- 页面meta -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>首页</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
	name="viewport">
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/AdminLTE.css">
<link rel="stylesheet" href="css/_all-skins.min.css">
<%
	if(application.getAttribute("markid")==null){
		request.setAttribute("msg", "账号身份已过期，请重新登陆！");
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
%>
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
		<!-- 页面头部 /-->

		<!-- 内容区域 -->
		<div class="content-wrapper">

			<!-- 内容头部 -->
			<section class="content-header">
				<ol class="breadcrumb">
						<li>
							<a href="index.jsp"><i class="fa fa-dashboard"></i> 首页</a>
						</li>
					</ol>
			</section>
			<!-- 内容头部 /-->

			<!-- 菜单区域 -->
			<section class="content">

				<!-- 菜单列表 -->
				<div class="row">
					<!-- ./col -->
					<div class="col-lg-3 col-xs-6">
						<!-- small box -->
						<div class="small-box bg-aqua">
							<div class="inner"></div>
							<div class="inner">
								<p style="font-size: 30px; font-weight: bold;"><a href="manager.jsp" style="color: white;">商品信息</a></p>
							</div>
							<a href="manager.jsp" class="small-box-footer">详细 <i
								class="fa fa-arrow-circle-right"></i>
							</a>
						</div>
					</div>
					<!-- ./col -->
					<div class="col-lg-3 col-xs-6">
						<!-- small box -->
						<div class="small-box bg-orange">
							<div class="inner"></div>
							<div class="inner">
								<p style="font-size: 30px; font-weight: bold;"><a href="scan.jsp" style="color: white;">商品入库</a></p>
							</div>
							<a href="scan.jsp" class="small-box-footer">详细 <i
								class="fa fa-arrow-circle-right"></i>
							</a>
						</div>
					</div>
					<!-- ./col -->
					<div class="col-lg-3 col-xs-6">
						<!-- small box -->
						<div class="small-box bg-red">
							<div class="inner">
								<span
									style="float: right; margin-right: -10px; margin-top: -10px;"
									class="label label-warning"><i class="fa fa-bell"></i>10</span>
							</div>
							<div class="inner">
								<p style="font-size: 30px; font-weight: bold;"><a href="news.jsp" style="color: white;">消息中心</a></p>
							</div>
							<a href="news.jsp" class="small-box-footer">详细 <i
								class="fa fa-arrow-circle-right"></i>
							</a>
						</div>
					</div>
					<!-- ./col -->
				</div>
				<!-- /.row -->
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
	<script src="js/fastclick.min.js"></script>
	<script src="js/app.min.js"></script>
</body>
</html>
<!---->