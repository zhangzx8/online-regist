<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>功能列表:</title>
<link rel="StyleSheet" href="/resources/dtree/dtree.css" type="text/css" />
<script type="text/javascript" src="/resources/dtree/dtree.js"></script>
<script src="/resources/js/jquery-1.6.4.min.js" type="text/javascript"></script>

</head>
<body>
	<table>
		<tr>
			<td width="10%"></td>
			<td><script type="text/javascript">
					d = new dTree('d');
					d.add(0, -1, '功能列表');
					${modulTree}
			</script>
			</td>
		</tr>
	</table>
</body>
</html>