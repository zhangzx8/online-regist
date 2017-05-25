<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>功能设置:</title>
<link rel="StyleSheet" href="/resources/dtree/dtree.css" type="text/css" />
<link href="/resources/css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="/resources/dtree/dtree.js"></script>
<script src="/resources/js/jquery-1.6.4.min.js" type="text/javascript"></script>

<script type="text/javascript">

function do_set() {
	var checkbox = document.getElementsByName("funcId");
	var num = 0;
	var funcId = "";
	for ( var i = 0; i < checkbox.length; i++) {
		if (checkbox[i].checked) {
			funcId += checkbox[i].value + ",";
			num++;
		}
	}
	if (num == 0) {
		alert("您至少要选择一个功能！");
	} else {
		
		document.aForm.action = "/role/addFunc?fids="
				+ funcId;
		document.aForm.submit();
	}
}

</script>
</head>
<body>
<div class="content">
	<h2 class="pageTitle">功能列表</h2>
	<form name="aForm" method="post">
		<input type="hidden" name="rid" value="${roleId }" />
		<div class="funSet">
			<script type="text/javascript">
					d = new dTree('d');
					d.config.check = true;
					d.add(0, -1, '功能设置');
					${modulTree}
			</script>
		</div>	
		<div class="formBtnInput" ><input type="button" name="button" value="确定"  class="btn_style"  onClick="do_set()"><input type="button" name="button" value="返回"  class="btn_style" onClick="history.back()"></div>
	</form>
</div>

	
</body>
</html>