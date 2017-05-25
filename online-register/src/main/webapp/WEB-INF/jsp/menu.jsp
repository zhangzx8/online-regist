<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理—菜单</title>
<link rel="StyleSheet" href="/resources/dtree/dtree.css" type="text/css" />
<script type="text/javascript" src="/resources/dtree/dtree.js"></script>
</head>

<body >

		<table>
			<tr>
				<td width="10%"></td>
				<td><script type="text/javascript">
					d = new dTree('d');
					d.add(0, -1, '功能列表');
					${modulTreeMenu}
				</script></td>
			</tr>
		</table>
		
		
<!-- <iframe id="mainframe1" name="left" marginwidth="0" marginheight="0" src="/user/funclist" frameborder="0" width="100%" scrolling="no" height="750"></iframe> -->
</body>
</html>
