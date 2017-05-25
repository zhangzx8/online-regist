<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>终端公司经营分析监控系统</title>
<link href="resources/css/style.css" type="text/css" rel="stylesheet" />
<script src="resources/js/jquery-1.6.4.min.js" type="text/javascript"></script>
<script>
$(function(){
	//menu点击效果
	$('.nav').find('li:not(:last)').click(function(){				  
		$(this).addClass("on");	
		$(this).siblings().removeClass("on");
	});
	
	 /*function  getUrlArgument() {
	        var url = location.search; //获取url中"?"符后的字串
	        var theRequest = new Object();
	        if (url.indexOf("?") != -1) {
	            var str = url.split("?");
	            if (str[1].indexOf("&") != -1) {
	                strs = str[1].split("&");
	                for (var i = 0; i < strs.length; i++) {
	                    theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
	                }
	            } else {
	                theRequest[str[1].split("=")[0]] = unescape(str[1].split("=")[1]);
	            }
	        }
	        return theRequest;
	  }
	
	var getUrl = getUrlArgument();
	var guid = getUrl.guid;
	$.ajax({
		url:'http://172.16.70.36:8000/login?guid='+guid,
		type:'GET',
		dataType:'json',
		success:function(json){
			if(json.data.code==1){
				$("#wk-uname-id").text(json.data.user.name);
			}else{
				alert("用户信息加载失败！");
			}
		}
	})*/
	
})
</script>
</head>

<body>
<div class="header clear">
	<div class="logoBox"><img src="resources/images/logo.jpg" class="logo"/><span class="dividLine"></span><span class="title">终端公司经营分析监控系统</span></div>
    <div class="userMsg"><a href="#"><span class="userName"><font id="wk-uname-id">${sessionScope.userSession.username}</font>，欢迎您！</span><img src="resources/images/head.jpg" class="userHead" /></a></div>
</div>
<div class="nav">
	<ul>
    	<li class="on"><a href="http://172.16.70.35:8080/static/html_static/pc/sales.html?groupprovince=${province}" target="mainframe"><span style="border:0;">销量</span></a></li>
        <li><a href="http://172.16.70.35:8080/static/html_static/pc/stock.html?groupprovince=${province}" target="mainframe"><span>库存</span></a></li>
        <li><a href="http://172.16.70.35:8080/static/html_static/pc/purchase.html?groupprovince=${province}" target="mainframe"><span>自采</span></a></li>
        <li><span>毛利</span></li>
    </ul>
</div>
<div class="container">
    <iframe id="mainframe" name="mainframe" marginwidth="0" marginheight="0" src="http://172.16.70.35:8080/static/html_static/pc/sales.html?groupprovince=${province} " frameborder="0" width="100%" scrolling="no" height="750"></iframe>
   <script type="text/javascript">
    	$("#mainframe").load(function () {
        	 var mainheight = $(this).contents().find("body").height() + 30;
             $(this).height(mainheight);
         });
   </script>

</div>


</body>
</html>
