<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>功能添加</title>
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
			var formparam = $("#funcform").serialize();
			$.ajax({
				type : 'POST',
				url : "/ajax/func/add",
				data : formparam,
				dataType : 'json',
				cache : false,
				success : function(data) {
					var parsedJson = jQuery.parseJSON(data);
					
					if(parsedJson.code==0){
						window.location.href="/func/funclist";
						
					}
				}
			});
		});

	});
</script>
</head>
<body>

	<div id="center">
		<div>
			<form action="" id="funcform" method="post">
				<lable>功能名称:</lable>        <input type="text" name="title" /><br> <br>
				<lable>功&nbsp能url:</lable>   <input type="text" name="url" value="#" /><br> <br> 
				<lable>排&nbsp;&nbsp;&nbsp;&nbsp名:</lable>   <input type="text" name="rank" /><br> <br> 
				<lable>父节点id:</lable>       <input type="text" name="parentid" value=${parentid } /><br> <br>
				<label id="submit">提交</label>
			</form>
		</div>
	</div>

</body>
</html>