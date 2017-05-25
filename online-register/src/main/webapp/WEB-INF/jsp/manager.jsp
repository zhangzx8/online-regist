<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理</title>

<link href="/resources/css/style.css" type="text/css" rel="stylesheet" />
<script src="/resources/js/jquery-1.6.4.min.js" type="text/javascript"></script>

<script type="text/javascript">
	$(function() {
		var h = $('body').height();
		//var topH = $(".header").height()+2;
		$(".box").height(h-62);
		
		$(".addFun").click(function() {
			$("#func").show();
		});

		$(".level1 > a").click(
				function() {
					$(this).next().show().parent().siblings().children("a")
							.next().hide();
					return false;
				});

		$("#submit").click(function(e) {
			var formparam = $("#funcform").serialize();
			alert(formparam);
			$.ajax({
				type : 'POST',
				url : "/func/add",
				data : formparam,
				dataType : 'json',
				cache : false,
				success : function(data) {
					var parsedJson = jQuery.parseJSON(data);
					alert(parsedJson.code);
				}
			});
		});

	});
</script>
</head>
<body>
	<div class="header">
		<div class="logoBox">
			<img src="/resources/images/logo.jpg" class="logo" /><span class="dividLine"></span><span class="title">终端有限公司经营分析监控系统</span>
		</div>
	</div>
	<div class="box">
		<div class="menu">
			<%@include file="menu.jsp"%>
		</div>
		<div class="center">
			<iframe id="main" name="main" marginwidth="0" src="/main" marginheight="0"  frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
		</div>
	</div>
</body>
</html>