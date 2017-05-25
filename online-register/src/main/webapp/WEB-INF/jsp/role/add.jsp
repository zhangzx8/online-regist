<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色添加</title>
<link href="/resources/css/style.css" type="text/css" rel="stylesheet" />
<script src="/resources/js/jquery-1.6.4.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {

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
			var formparam = $("#roleform").serialize();
			$.ajax({
				type : 'POST',
				url : "/ajax/role/add",
				data : formparam,
				dataType : 'json',
				cache : false,
				success : function(data) {
					var parsedJson = jQuery.parseJSON(data);
					if(parsedJson.code == 0){
						document.aForm.action = "/role/rolelist";
					    document.aForm.submit();
					}
				}
			});
		});

	});
</script>
</head>
<body>
<div  class="content">
	<div class="formList">
		<form name="aForm" id="roleform" method="post">
			<ul>
				<li><span class="labelName">角色名称:</span><input type="text" name="title" class="textStyle"/></li>
				<li><span class="labelName">角色描述:</span><input type="text" name="descp" class="textStyle"/></li>
				<li class="formBtnB"><span id="submit" class="btn_style submitBtn">提交</span></li>
			</ul>
		
		</form>
	</div>
</div>
</body>
</html>