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

<link href="/resources/css/style.css" type="text/css" rel="stylesheet" />
<script src="/resources/js/jquery-1.6.4.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		//表格隔行色值
		$("table tr:even").css("background", "#f6f6f6")

		$(".addFun").click(function() {
			$("#func").show();
		});

		$(".level1 > a").click(
				function() {
					$(this).next().show().parent().siblings().children("a")
							.next().hide();
					return false;
				});

		$(".rolelistset").change(function() {
			var roleuserid = $(this).val();
			var url = "/ajax/user/addRole?roleuserid=" + roleuserid;
			$.ajax({
				type : 'POST',
				url : url,
				data : "",
				dataType : 'json',
				cache : false,
				success : function(data) {
					var parsedJson = jQuery.parseJSON(data);
					if (parsedJson.code == 0) {
						window.location.href = "/user/userlist?pageNo=${pageNo}&pageSize=${pageSize}";
						//					document.aForm.action = "/role/rolelist";
						//				    document.aForm.submit();
					}
				}
			});
		});

		/* 		$("#rolelistset").change(function()
		 {
		 //alert($("#selecttest").attr("name"));
		 //$("a").attr("href","xx.html");
		 //window.location.href="xx.html";
		 alert($("#rolelistset").val());
		
		 //alert($("#rolelistset option[@selected]").text());
		 //$("#selecttest").attr("value", "2");

		 }); */

	});
</script>


</head>
<body>
	<div class="content">
	<form action="/user/userlist?pageNo=1&pageSize=15" method="post">
      <lable>模糊查询：</lable> <input type="text" name="userName" />
	<input type="submit" value="查询"/>
	</form>
		<div class="tableData">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<th width="25%">用户ID</th>
					<th width="15%">账号</th>
					<th width="15%">姓名</th>
					<th width="5%">省份</th>
					<th width="18%">公司</th>
					<th width="10%">手机</th>
					<th width="5%">修改</th>
					<th width="5*">功能设置</th>
				</tr>
				<c:forEach items="${list}" var="row">


					<tr>

						<%-- <td><input type="radio" name="userid" value="${row.guid}</td> --%>
						<td>${row.guid}</td>
						<td>${row.username}</td>
						<td>${row.name}</td>
						<td>${row.groupprovince}</td>
						<td>${row.department}</td>
						<td>${row.mobile}</td>
                        <td><a href="/user/update?guid=${row.guid}">修改</a></td>
						<td class="operate"><select id="rolelistset+${row.roleid}"
							class="rolelistset">
								<c:forEach items="${roleList}" var="roww">

									<c:choose>
										<c:when test="${roww.id==row.roleid}">
											<option selected="selected" value="${row.guid},${roww.id} ">${roww.title}</option>
										</c:when>
										<%-- 									<c:when test="${row.v_money>=10000&&row.v_money<20000}">
                                                                                                                                         身手小试
                                      </c:when> --%>
										<c:otherwise>
											<option value="${row.guid},${roww.id} ">${roww.title}</option>
										</c:otherwise>
									</c:choose>



								</c:forEach>
						</select></td>

						<%-- <td><a href="/user/roleList?uid=${row.guid}">设置角色</a></td> --%>
					</tr>

					<%-- <li><input type="checkbox" name="funcparam" value="${row.id}"
				<c:if test="${row.parentId>0}">checked</c:if>>${row.funcName}</li> --%>
				</c:forEach>

			</table>
			<br>



		</div>

		<div class="pageData">
			<c:choose>
				<c:when test="${totalPages != pageNo}">
					<a href="/user/userlist?pageNo=1&pageSize=15">首页</a>
			                            当前第${pageNo}页
			        <a href="/user/userlist?pageNo=${pageNo-1 }&pageSize=15">上一页</a>
					<a href="/user/userlist?pageNo=${pageNo+1 }&pageSize=15">下一页</a>
				</c:when>
				<c:otherwise>

				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>