<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<title>选择功能</title>
<script type="text/javascript" src="jquery-2.1.4.min.js"></script>
<!-- 引入 WeUI -->
<link rel="stylesheet"
	href="https://res.wx.qq.com/open/libs/weui/2.1.3/weui.min.css">
</head>
<!-- 引入js文件 -->
<script src="http://res.wx.qq.com/open/js/jweixin-1.6.0.js"></script>
<script type="text/javascript">
	$.ajax({
		type : "post",
		url : "Redirect",//自己填写请求地址
		data : {
			"url" : encodeURIComponent(location.href.split('#')[0])
		},
		success : function(result) {
			var jsonobj = eval('(' + result + ')'); // 把JSON字符串解析为javascript对象
			wx.config({
				// 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				debug : false,
				// 必填，公众号的唯一标识
				appId : jsonobj.appId,
				// 必填，生成签名的时间戳
				timestamp : jsonobj.timestamp,
				// 必填，生成签名的随机串
				nonceStr : jsonobj.nonceStr,
				// 必填，签名，见附录1
				signature : jsonobj.signature,
				// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
				jsApiList : [ 'getLocation', 'openLocation' ]
			});
		}
	});
	wx.error(function(res) {
		alert("出错了：" + res.errMsg);//这个地方的好处就是wx.config配置错误，会弹出窗口哪里错误，然后根据微信文档查询即可。
	});

	wx.ready(function() {
		wx.checkJsApi({
			jsApiList : [ 'checkJsApi', 'getLocation', 'openLocation' ],
			success : function(res) {

			}
		});
		//点击按钮获取当前地址坐标
		document.querySelector('#location').onclick = function() {
			wx.getLocation({
				type : 'gcj02', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
				success : function(res) {
					//使用微信内置地图查看位置接口
					wx.openLocation({
						latitude : res.latitude, // 纬度，浮点数，范围为90 ~ -90
						longitude : res.longitude, // 经度，浮点数，范围为180 ~ -180。
						name : '我的位置', // 位置名
						address : 'xxxx', // 地址详情说明
						scale : 28, // 地图缩放级别,整形值,范围从1~28。默认为最大
					});
				},
				error : function(res) {
					if (res.errMsg.indexOf('function_not_exist') > 0) {
						alert('当前版本过低，请进行升级');
					}
				}
			});
		};
	});
</script>
<body ontouchstart>
	<div class="weui-tab__bd">
		<div class="div1">
			<button id="location">
				<img src="images/product.png" alt="商品信息"></a>
			</button>
		</div>
	</div>
	<div class="weui-footer weui-footer_fixed-bottom">
		<p class="weui-footer__links">
			<a href="choose.jsp" class="weui-footer__link">首页</a>
		</p>
		<p class="weui-footer__text">Copyright © 2019 Jiang</p>
	</div>
</body>
</html>