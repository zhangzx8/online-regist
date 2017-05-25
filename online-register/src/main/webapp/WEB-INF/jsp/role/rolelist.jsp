<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设置功能</title>
<link href="/resources/css/style.css" type="text/css" rel="stylesheet" />
<script src="/resources/js/jquery-1.6.4.min.js" type="text/javascript"></script>
<script>
$(function(){
	//表格隔行色值
	$("table tr:even").css("background","#f6f6f6")
})
</script>
</head>
<body>
<div class="content">
	<div class="btnbox"><a href="/role/add" class="btn_style">角色添加</a></div>
	<div class="tableData">
		<table cellpadding="0" cellspacing="0">
			<tr>
                <th  width="15%">角色ID</th>
                <th  width="15%">角色名称</th>
                <th  width="15%">角色描述</th>
                <th  width="20%">更新时间</th>
                <th  width="10%">状态</th>
                <th  width="*">功能设置</th>
            </tr>
			<c:forEach items="${list}" var="row">
			<tr>
				<td>${row.id}</td>
				<td>${row.title}</td>
				<td>${row.descp}</td>
				<td>${row.updateTime}</td>
				<td>${row.state}</td>
				<td class="operate"><a href="/role/delete?id=${row.id}" class="deleteBtn">删除</a><a href="/role/update?id=${row.id}" class="editBtn">修改</a><a href="/role/funcList?rid=${row.id}" class="setBtn">设置功能</a></td>
			</tr>

<%-- 			<li><input type="checkbox" name="funcparam" value="${row.id}"
				<c:if test="${row.parentId>0}">checked</c:if>>${row.funcName}</li> --%>
		</c:forEach>
		
		</table>
	</div>
</div>

</body>
</html>