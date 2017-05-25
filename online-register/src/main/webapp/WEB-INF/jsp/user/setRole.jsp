<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
</head>
<body>


	<table class="table_border" width="500px">
		<tbody>
			<tr>
				<th align="center">序号</th>
				<th align="center">名称</th>
				<!-- <th align="center">时间</th> -->
				<th align="center">操作</th>
			</tr>
			<%-- <c:if test="${empty listRole}">
				<tr>
					<td colspan=4>记录为空！</td>
				</tr>
			</c:if>
			 --%><c:forEach items="${allRole}" var="row">
				<tr>
					<td align="center">${row.id}</td>
					<td align="center">${row.title}</td>
<%-- 					<td align="center"><fmt:formatDate value="${row.uptime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td> --%>
					<td align="center"><a
						href="/user/addRole?uid=${uid }&rid=${row.id}">授权</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>