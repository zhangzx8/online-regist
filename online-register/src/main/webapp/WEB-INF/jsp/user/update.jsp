<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息修改</title>
<link href="/resources/css/style.css" type="text/css" rel="stylesheet" />
<script src="/resources/js/jquery-1.6.4.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {


		$("#submit").click(function(e) {
			var formparam = $("#roleform").serialize();
			$.ajax({
				type : 'POST',
				url : "/ajax/user/update",
				data : formparam,
				dataType : 'json',
				cache : false,
				success : function(data) {
					var parsedJson = jQuery.parseJSON(data);
					if(parsedJson.code == 0){
						alert("修改成功");
						//document.aForm.action = "/role/rolelist";
					  //  document.aForm.submit();
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
			<input type="hidden" name="guid" value=${user.guid } />
			
			<ul>
				<li><span class="labelName">用户账号:</span><input type="text" name="username" value=${user.username }  class="textStyle" readonly/></li>
				<li><span class="labelName">用户名称:</span><input type="text" name="name" value=${user.name }  class="textStyle" readonly/></li>
				<li><span class="labelName">所属省份:</span><input type="text" name="groupprovince" value=${user.groupprovince }  class="textStyle"/></li>
				<li class="formBtnB"><span id="submit" class="btn_style submitBtn">提交</span></li>
			</ul>
		
		</form>
	</div>
</div>
</body>
</html>