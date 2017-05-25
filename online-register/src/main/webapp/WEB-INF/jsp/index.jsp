<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<title>终端公司经营分析监控系统</title>
<link href="resources/css/style.css" type="text/css" rel="stylesheet" />
<script src="resources/js/jquery-1.6.4.min.js" type="text/javascript"></script>
<script>
	$(function() {
		//menu点击效果
		$('.nav').find('li').click(function() {
			$(this).addClass("on");
			$(this).siblings().removeClass("on");
		})

	})
</script>
</head>

<body>
	<div class="header clear">
		<div class="logoBox">
			<img src="resources/images/logo.jpg" class="logo" /><span
				class="dividLine"></span><span class="title">终端公司经营分析监控系统</span>
		</div>
		<!--  <div class="userMsg">
			<a href="#"><span class="userName"><font id="wk-uname-id">${sessionScope.userSession.name}</font>，欢迎您！</span><img
				src="resources/images/head.jpg" class="userHead" /></a>
		</div>-->
	</div>
	<div class="nav">
		<ul>
			<c:forEach items="${list}" var="row" varStatus="rowStatus">
				<c:choose>
					<c:when test="${rowStatus.index==0}">
						<li class="on"><a
							href="${row.url }?groupprovince=${province}"
							target="mainframe"><span style="border: 0;">${row.title }</span></a></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="${row.url }?groupprovince=${province} "
							target="mainframe"><span>${row.title }</span></a></li>
					</c:otherwise>
				</c:choose>

			</c:forEach>
			<li><a href="http://192.168.1.105:8080/demo/demotest/demo.jsp?a=10&b=22" target="mainframe"><span>测试</span></a></li>

		</ul>
	</div>

	<div class="container">
		<iframe id="mainframe" name="mainframe" marginwidth="0"
			marginheight="0"
			src="${target.url }?groupprovince=${province}"
			frameborder="0" width="100%" scrolling="no" height="750"></iframe>
		<script type="text/javascript">
			$("#mainframe").load(function() {
				var mainheight = $(this).contents().find("body").height() + 30;
				$(this).height(mainheight);
			});
		</script>

	</div>
</body>
</html>