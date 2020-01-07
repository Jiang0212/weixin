<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<title>商品简介</title>
<style type="text/css">

</style>
</head>
<!-- 引入 WeUI -->
<link rel="stylesheet"
	href="https://res.wx.qq.com/open/libs/weui/2.1.3/weui.css">
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="js/jquery-weui.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.6.0.js"></script>
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
				jsApiList : [ 'scanQRCode' ]// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
			});
		}
	});
	wx.error(function(res) {
		alert("出错了：" + res.errMsg);//这个地方的好处就是wx.config配置错误，会弹出窗口哪里错误，然后根据微信文档查询即可。
	});

	wx.ready(function() {
				wx.checkJsApi({
					jsApiList : [ 'checkJsApi', 'scanQRCode' ],
					success : function(res) {
					}
				});
				//点击按钮扫描二维码
				document.querySelector('#scanQRCode').onclick = function() {
					wx.scanQRCode({
								needResult : 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
								scanType : [ "qrCode", "barCode" ], // 可以指定扫二维码还是一维码，默认二者都有
								success : function(res) {
									// 扫码成功，跳转到二维码指定页面（res.resultStr为扫码返回的结果）            
									var scan = res.resultStr;
									if (scan.indexOf(",") != -1) {
										$.ajax({
													type : "get",
													url : "ActionScan",
													data : {
														"qrcode" : scan
													},
													success : function(result) {
														if(result.msg==1){
														document.getElementById("id").value = result.id;
														document.getElementById("name").value = result.name;
														document.getElementById("price").value = result.price;
														document.getElementById("begintime").value = result.begintime;
														document.getElementById("overtime").value = result.overtime;
														document.getElementById("mark").value = result.mark;
														}else{
															alert("该商品未入库！");
															window.location.href='scan.jsp';
														}
													}
												});
									}else {
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
<body ontouchstart>
	<form action="ActionScan" method="post" id="submitForm">
		<input type="hidden" name="qrCode" id="qrCode" />
	</form>
	<div class="weui-cells weui-cells_form">
		<div class="weui-cell weui-cell_vcode">
			<div class="weui-cell__bd">
				<h1>商品检查</h1>
			</div>
			<div class="weui-cell__ft">
				<button class="weui-vcode-btn weui-btn_warn" id="scanQRCode">商品扫码</button>
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd">
				<label class="weui-label">产品ID</label>
			</div>
			<div class="weui-cell__bd">
				<input class="weui-input" id="id" type="text" placeholder="请输入信息"
					value="">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd">
				<label class="weui-label">产品名称</label>
			</div>
			<div class="weui-cell__bd">
				<input class="weui-input" id="name" type="text" placeholder="请输入信息"
					value="">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd">
				<label class="weui-label">产品单价</label>
			</div>
			<div class="weui-cell__bd">
				<input class="weui-input" id="price" type="text" placeholder="请输入信息"
					value="">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd">
				<label for="" class="weui-label">生产日期</label>
			</div>
			<div class="weui-cell__bd">
				<input class="weui-input" id="begintime" type="datetime-local" value="">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd">
				<label for="" class="weui-label">过期时间</label>
			</div>
			<div class="weui-cell__bd">
				<input class="weui-input" id="overtime" type="datetime-local" value="">
			</div>
		</div>
		<div class="weui-cells weui-cells_form">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label for="" class="weui-label">备注</label>
				</div>
				<div class="weui-cell__bd">
					<textarea class="weui-textarea" id="mark" rows="3"></textarea>
				</div>
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__bd">
				<button id='show-confirm' class="weui-btn weui-btn_primary ">修改信息</button>
			</div>
		</div>
		<div class="weui-footer weui-footer_fixed-bottom">
			<p class="weui-footer__links">
				<a href="index.jsp" class="weui-footer__link">首页</a>
			</p>
			<p class="weui-footer__text">Copyright © 2019 Jiang</p>
		</div>
	</div>
	<script type="text/javascript">
	$(document).on("click", "#show-confirm", function() {
        $.confirm("您确定修改信息无误吗？", "修改确认", function() {
          window.location.href='validateSuccess.jsp';
        }, function() {
        	$(".weui-mask").remove();  
        	$(".weui-dialog").remove(); 	
        });
      });
	</script>
</body>
</html>