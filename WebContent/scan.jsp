<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<!-- 指定字符集 -->
<meta charset="utf-8">
<!-- 使用Edge最新的浏览器的渲染方式 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">

<title>商品入库</title>
<!--[if lt IE 9]>
  			<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  		<![endif]-->
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/AdminLTE.css">
<link rel="stylesheet" href="css/_all-skins.min.css">
<link rel="stylesheet" href="css/style.css">
<%
	if(application.getAttribute("markid")==null){
		request.setAttribute("msg", "账号身份已过期，请重新登陆！");
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
%>
</head>

<script src="js/jquery-2.2.3.min.js"></script>
<script src="js/fastclick.min.js"></script>
<script src="js/app.min.js"></script>
<script src="js/myconfirm.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.4.0.js"></script>
<script type="text/javascript">
	$.ajax({
		type : "post",
		url : "Redirect",//自己填写请求地址
		data : {
			"url" : encodeURIComponent(location.href.split('#')[0])
		},
		success : function(result) {
			wx.config({
				debug : false,// 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				appId : result.appId,// 必填，公众号的唯一标识				
				timestamp : result.timestamp,// 必填，生成签名的时间戳				
				nonceStr : result.nonceStr,// 必填，生成签名的随机串				
				signature : result.signature,// 必填，签名，见附录1				
				jsApiList : [ 'scanQRCode' ]
			// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
			});
		}
	});
	wx.error(function(res) {
		alert("出错了：" + res.errMsg);//这个地方的好处就是wx.config配置错误，会弹出窗口哪里错误，然后根据微信文档查询即可。
	});
	wx
			.ready(function() {
				wx.checkJsApi({
					jsApiList : [ 'checkJsApi', 'scanQRCode' ],
					success : function(res) {
					}
				});
				var markid = $("#markid").val();
				//点击按钮扫描二维码
				document.querySelector('#scanQRCode').onclick = function() {
					wx
							.scanQRCode({
								needResult : 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
								scanType : [ "qrCode", "barCode" ], // 可以指定扫二维码还是一维码，默认二者都有
								success : function(res) {
									// 扫码成功  res.resultStr为扫码返回的结果            
									var scan = res.resultStr;
									if (scan.indexOf(",") != -1) {
										$
												.ajax({
													type : "get",
													url : "findoneServlet",
													data : {
														"qrcode" : scan,
														"markid" : markid
													},
													success : function(result) {
														if (result.msg == 1) {
															document
																	.getElementById("id").value = result.id;
															document
																	.getElementById("name").value = result.name;
															document
																	.getElementById("size").value = result.size;
															document
																	.getElementById("unit").value = result.unit;
															document
																	.getElementById("day").value = result.day;
															document
																	.getElementById("validTime").value = result.validTime;
														} else {
															alert("该商品未入库！");
															window.location.href = 'scan.jsp';
														}
													}
												});
									} else {
										alert(scan);
									}
								},
								error : function(res) {
									if (res.errMsg
											.indexOf('function_not_exist') > 0) {
										alert('当前版本过低，请进行升级');
									}
								}
							});
				};
			});
</script>
<script type="text/javascript">
	function sub() {
		$.ajax({
			//几个参数需要注意一下
			type : "POST",//方法类型，或者用GET
			dataType : "json",//预期服务器返回的数据类型，可以改成json
			url : "addGoodsServlet",//ip:端口/项目名称/Servlet名称
			data : $('#form1').serialize(),//获取表单里的数据到这里
			success : function(res) {
				if (res.msg == 0) {
					window.location.href = 'fail.jsp';
				} else {
					window.location.href = 'success.jsp';
				}
			},
		});
	}
</script>
<body class="hold-transition skin-purple sidebar-mini" ontouchstart>
	<div class="wrapper">
		<!-- 页面头部 -->
		<header class="main-header">
			<!-- Logo -->
			<a href="index.jsp" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
				<span class="logo-mini"><b>商家</b></span> <!-- logo for regular state and mobile devices -->
				<span class="logo-lg"><b>${applicationScope.market}商家</b>小助手</span>
			</a>
		</header>
		<!-- 页面头部 /-->
		<!-- 内容区域 -->
		<div class="content-wrapper">
			<input type="hidden" id="markid" class="markid" value="${applicationScope.markid}">
			<!-- 内容头部 -->
			<section class="content-header">
				</h1>
				<ol class="breadcrumb">
					<li><a href="index.jsp"><i class="fa fa-dashboard"></i> 首页</a>
					</li>
					<li class="active">商品入库</li>
					<button id="scanQRCode"
						style="float: right; margin: -8px -4px 0 0; background: #f39c12;"
						class="btn btn-default btn-flat">入库扫描</button>
					</li>
				</ol>
			</section>
			<!-- 内容头部 /-->

			<!-- 正文区域 -->
			<section class="content">

				<!--基础控件-->
				<div id="tab-common">
					<div class="row data-type">
						<form id='form1'>
							<div class="col-md-2 title">商品编号</div>
							<div class="col-md-4 data">
								<input type="text" id="id" name="id" class="form-control"
									placeholder="商品编号" value="">
							</div>

							<div class="col-md-2 title">商品名称</div>
							<div class="col-md-4 data">
								<input type="text" id="name" name="name" class="form-control"
									placeholder="商品名称" value="">
							</div>

							<div class="col-md-2 title">规格</div>
							<div class="col-md-4 data">
								<input type="text" id="size" name="size" class="form-control"
									placeholder="规格" value="">
							</div>

							<div class="col-md-2 title">单位</div>
							<div class="col-md-4 data">
								<input type="text" id="unit" name="unit" class="form-control"
									placeholder="单位" value="">
							</div>

							<div class="col-md-2 title">有效天数</div>
							<div class="col-md-4 data">
								<input type="text" id="day" name="day" class="form-control"
									placeholder="有效天数" value="">
							</div>

							<div class="col-md-2 title">保质期</div>
							<div class="col-md-4 data">
								<input type="date" id="validTime" name="validTime"
									class="form-control" value="">
							</div>

							<div class="col-md-2 title"></div>
							<div class="col-md-4 data text-center">
								<button onclick="sub()" type="button" class="btn bg-maroon">提交</button>
								<button type="button" class="btn bg-default"
									onclick="history.back(-1);">返回</button>
							</div>
						</form>
					</div>
				</div>
				<!--基础控件/-->
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

</body>
</html>