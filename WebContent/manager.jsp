<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<!-- 页面meta -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>商品管理</title>
<meta
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
	name="viewport">
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/blue.css">
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

		<!-- 内容区域 -->
		<div class="content-wrapper">

			<!-- 内容头部 -->
			<section class="content-header">
				<ol class="breadcrumb">
					<li><a href="index.jsp"><i class="fa fa-dashboard"></i> 首页</a>
					</li>
					<li class="active">商品管理</li>
				</ol>
			</section>
			<!-- 内容头部 /-->

			<!-- 正文区域 -->
			<section class="content">
				<input type="hidden" class="hidde" id="hidde" value="${applicationScope.markid}">

				<div class="box-header with-border">
					<!--工具栏 -->
					<div class="pull-left">
						<div class="form-group form-inline">
							<div class="btn-group">
								<button type="button" id="sort" class="btn btn-default"
									title="排序">
									<i class="fa fa-refresh"></i>有效期排序
								</button>
								<button type="button" id="selall" class="btn btn-default"
									title="全选">
									<i class="fa fa-check"></i> 全选
								</button>
								<button type="button" id="unsel" class="btn btn-default"
									title="取消">
									<i class="fa fa-close"></i> 取消
								</button>
								<button type="button" id="deleteAcc" class="btn btn-default"
									title="删除">
									<i class="fa fa-trash-o"></i> 删除
								</button>
								<input type="text" id="searchcontent" name="searchcontent"
									placeholder="输入商品名称搜索" value="">
								<button type="button" id="search" class="btn-default" title="搜索">
									<i class="fa fa-search nav-search-icon"></i> 搜索
								</button>
							</div>
						</div>
					</div>
				</div>
				<div class="box-body">
					<!-- 数据表格 -->
					<div class="table-box">
						<!--数据列表-->
						<table id="dataList"
							class="tablesorter table table-bordered table-striped table-hover dataTable">
							<thead>
								<tr>
									<th></th>
									<th class="text-center">商品名称</th>
									<th class="text-center">截止有效期</th>
									<th></th>
								</tr>
							</thead>
							<tbody id="datatable">
								<!-- <tr">
									<td class='text-center'><input value='1001' name="ids"
										class="icheckbox_square-blue" type="checkbox"></td>
									<td class='text-center' style='color:red;'>商品1号</td>
									<td class='text-center' style='color:red;'>2017-03-13</td>
									<td class="text-center">
										<button type="button" id="del" class="btn bg-olive btn-xs">删除</button>
									</td>
								</tr> -->
							</tbody>

						</table>
						<!--数据列表/-->
						<!--分页-->
						<div class="page" style="text-align: center;">

							<a id="down" href="#" onClick="change1(--pageno)">上一页</a> <span
								id="a3"></span><a id="up" href="#" onClick="change1(++pageno)">下一页</a>
							<span class="ho">第<span id="a2"></span>/<span id="a1"></span>页
							</span>
						</div>
						<!--用来存放总页数，放置在body中-->
						<div style="display: none" id="p"></div>
					</div>
					<!-- 数据表格 /-->
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
	<script src="js/myconfirm.js"></script>
	<script src="js/jquery.tablesorter.js"></script>
	<script>
		//获取后台商品信息
		$(function() {
			var markid = $("#hidde").val();
			$
					.ajax({
						type : 'get',
						url : 'findAllServlet',
						dataType : 'json',
						data:{
							"markid":markid
						},
						success : function(data) {
							//方法中传入的参数data为后台获取的数据
							for (i = 0; i < data.length; i++) //data.data指的是数组，数组里是8个对象，i为数组的索引
							{
								var tr;
								tr = "<td class='text-center'><input value='" + data[i].id + "' name='ids'	class='icheckbox_square-blue' type='checkbox'></td>";
								if (parseInt(data[i].day) <= 1) {
									tr += "<td class='text-center' style='color:red;'>"
											+ data[i].name + "</td>"
											+ "<td class='text-center' style='color:red;'>"
											+ data[i].validTime + "</td>";
								} else {
									tr += "<td class='text-center'>" + data[i].name + "</td>"
											+ "<td class='text-center'>" + data[i].validTime
											+ "</td>";
								}
								tr = tr
										+ "<td class='text-center'><button type='button' id='del' class='btn bg-olive btn-xs'>删除</button></td>";
								$("#datatable").append("<tr>" + tr + "</tr>");
								$("#datatable").trigger("update");
							}
							var a = $("#datatable").children("tr");
							//alert(a.length);
							var zz = new Array(a.length);
							//alert(zz.length);
							for (var i = 0; i < a.length; i++) {
								zz[i] = a[i].innerHTML;
							} //div的字符串数组付给zz
							var pageno=1;               //当前页
				            var pagesize=10;            //每页多少条信息
				            if((zz.length)%pagesize==0)
				             {var  pageall =(zz.length)/pagesize;}
				             else
				             {var  pageall =parseInt((zz.length)/pagesize)+1;}//一共多少页
				             $("#p").text(pageall);      //将pageall的值存放到div中，便于下次使用
				             //alert($("#p").text());
				             change(1,pageall,zz);
						}
					})

		});

		var k;

		function change(e, all, zu) {
			zz = zu;
			//alert(zz.length);
			var pagesize = 10; //每页多少条信息
			pageall = all; //总页数
			pageno = e; //当前页
			if (e < 1) //如果输入页<1页
		{
			e = 1;
			pageno = 1
		} //就等于第1页 ， 当前页为1
		if(e > pageall) //如果输入页大于最大页
			{
				e = pageall;
				pageno = pageall
			} //输入页和当前页都=最大页
			document.getElementById("datatable").innerHTML = ""; //全部清空
			for (var i = 0; i < pagesize; i++) {
				var div = document.createElement("tr"); //建立div对象
				div.innerHTML = zz[(e - 1) * pagesize + i]; //建立显示元素
				document.getElementById("datatable").appendChild(div); //加入tbody中
				if (zz[(e - 1) * pagesize + i + 1] == null) //超出范围跳出
					break;
			}
			var ye = "";
			for (var j = 1; j <= pageall; j++) {
				if (e == j) {
					ye = ye + "<span><a href='#' onClick='change1(" + j
							+ ")' style='color:#FF0000'>" + j + "</a></span> "
				} else {
					ye = ye + "<a href='#' onClick='change1(" + j + ")'>" + j
							+ "</a> "
				}
			}
			document.getElementById("a1").innerHTML = pageall;
			document.getElementById("a2").innerHTML = pageno;
			document.getElementById("a3").innerHTML = ye;

			/*如果当前是第一页则：*/

			if (pageno == 1) {
				$('#down').hide(); //隐藏
			} else {
				$('#down').show(); //显示
			}

			/*如果是最后一页则：*/

			if (pageno == pageall) {
				$('#up').hide(); //隐藏
			} else {
				$('#up').show(); //显示
			}
			k = zu;

		}

		function change1(e){
		    
		    zz=k;//每页多少条信息
		    
		    var pagesize=10; 
		    pageall=$("#p").text();
		    pageno=e;
		    if(e <1)//如果输入页<1页
		    { e=1;pageno=1}//就等于第1页 ， 当前页为1
		    if(e>pageall)//如果输入页大于最大页
		    {e=pageall;pageno=pageall}//输入页和当前页都=最大页
		    document.getElementById("datatable").innerHTML="";//全部清空
		    for(var i=0;i<pagesize;i++)
		    {
		        var div =document.createElement("tr");//建立div对象
		        div.innerHTML=zz[(e-1)*pagesize+i];//建立显示元素
		        document.getElementById("datatable").appendChild(div);//加入tbody中
		        if(zz[(e-1)*pagesize+i+1]==null)//超出范围跳出
		            break;
		    }
		    var ye="";
		    for(var j=1;j<=pageall;j++)
		    {
		        if(e==j)
		        {ye=ye+"<span><a href='#' onClick='change1("+j+")' style='color:#FF0000'>"+j+"</a></span> "}
		        else
		        {ye=ye+"<a href='#' onClick='change1("+j+")'>"+j+"</a> "}
		    }
		    document.getElementById("a1").innerHTML=pageall;
		    document.getElementById("a2").innerHTML=pageno;
		    document.getElementById("a3").innerHTML=ye;
		 
		    /*如果当前是第一页则：*/
		 
		    if (pageno == 1)
		    {
		        $('#down').hide();//隐藏
		    }else {
		        $('#down').show();//显示
		    }
		 
		    /*如果是最后一页则：*/
		 
		    if (pageno == pageall)
		    {
		        $('#up').hide();//隐藏
		    }else {
		        $('#up').show();//显示
		    }
		}


		//删除多个数据
		$("#deleteAcc").click(function() {
			var list = $(':checkbox:checked');
			var ids = "";
			list.each(function() {
				ids += $(this).val() + ',';
			});
			if (ids.length == 0) {
				$.myAlert({
					content : "请选择要删除的商品！",
				});
			} else {
				//截取最后一个
				ids = ids.substr(0, ids.length - 1);
				$.myConfirm({
					content : '您一共选择了' + (list.length-1) + '件商品，确定删除吗？',
					confirm : function() {
						$.ajax({
							type : 'post',
							url : 'delSelectedServlet',
							data : {
								"ids" : ids,
								"markid":markid
							},
							dataType : 'json',
							success : function(data) {
								//如果在后台返回来200数字表示后台在数据库已经删除成功
								//后台删除成功之后前端用jQuery也随之删除对应的节点,为了不用重新再查询一遍数据
								if (data == 200) {
									loction.reload(true);
								} else {
									$.myAlert({
										content : "删除失败！",
									});
								}

							}
						})
					}
				})
			}
		});

		//2.绑定动态生成元素的事件
		//#datatable是已经存在的标签 .#del是动态添加元素的标签类名
		$('#datatable').on('click', '#del', function() {
			var n = $(this).parent().parent();
			var ids = n.children('td').eq(0).children('input').val();
			$.myConfirm({
				content : '确定要删除该商品吗?',
				confirm : function() {
					$.ajax({
						type : 'post',
						url : 'deleteOneServlet',
						data : {
							"ids" : ids
						},
						dataType : 'json',
						success : function(data) {
							//方法中传入的参数data为后台获取的数据
							if (data.msg == 0) {
								alert("删除失败！");
							} else {
								alert("删除成功！");
								location.reload(true);
							}

						}
					})
				}
			})
		});

		//排序操作
		$("#sort").click(function() {
			document.getElementById("datatable").innerHTML = ""; //全部清空
			var t = $("thead tr").children(); //取得thead下的tr的所有子元素
			$(t[2]).trigger("click");//模拟时间点击事件
		});
		$.tablesorter.addParser({
			id : "num", //指定一个唯一的ID  
			is : function(s) {
				return false;
			},
			format : function(s) {
				//对 xxxx-xx-xx 数据的处理
				var hourNum = parseInt(s.substring(0, 4));//xxxx年
				var minuteNum = parseInt(s.substring(5, 7));//xx月
				var secondsNum = parseInt(s.substring(8, 10));//xx日
				//将时间换算为数字
				var seconds = hourNum * 10000 + minuteNum * 100 + secondsNum;
				return seconds;
			},
			type : "numeric" //按数值排序  
		});
		$(".tablesorter").tablesorter({
			headers : {
				0 : {
					sorter : false
				},
				1 : {
					sorter : false
				},
				2 : {
					sorter : "num"
				},
				3 : {
					sorter : false
				}
			}
		});//表格第3列 

		// 全选操作 
		$("#selall").click(function() {
			$("input[type='checkbox']").iCheck("check");
		});
		//取消选择
		$("#unsel").click(function() {
			$("#dataList td input[type='checkbox']").iCheck("uncheck");
		});

		//简易搜索
		$('#search').click(
				function() {
					var sstxt = $('#searchcontent').val();
					if(sstxt){
						$("#selall").hide();
					}else{
						$("#selall").show();
					}
					$("table tbody tr").hide().filter(
							":contains('" + sstxt + "')").show();
		})
	</script>
</body>
</html>