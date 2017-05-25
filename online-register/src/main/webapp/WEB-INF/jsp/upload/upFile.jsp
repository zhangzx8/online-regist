<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传</title>
<link href="/resources/css/style.css" type="text/css" rel="stylesheet" />
<script src="/resources/js/jquery-1.6.4.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/resources/js/ajaxfileupload.js"></script>

<script type="text/javascript">
$(function () {
    $("#submit").click(function () {
        ajaxFileUpload();
    })
})
function ajaxFileUpload() {
    $.ajaxFileUpload
    (
        {
            url: '/uploadexcel', //用于文件上传的服务器端请求地址
            secureuri: false, //是否需要安全协议，一般设置为false
            fileElementId: 'fileupload', //文件上传域的ID
            dataType: 'json', //返回值类型 一般设置为json
            type:"post",
            success: function (data, status)  
            {
            	var obj = eval('(' + data + ')');
            	if(obj.code==0){
            		alert("上传成功");
            	}else if(obj.code==1){
            		alert("文件不能为空");
            	}else{
            		alert("上传失败");
            	}
            	
            },
            error: function (data, status, e)//服务器响应失败处理函数
            {
                alert(e);
            }
        }
    )
    return false;
}
</script>
</head>
<body>
	<div class="content">
		<div class="formList">
			<form>
				<ul>
					<%--  <li><input type="hidden" name="token" value="${guid }"/></li> --%>
					<li><input type="hidden" name="token" value="hehe" /></li>
					<li><span class="labelName">文件上传:</span><input type="file" id="fileupload" name="uploadexcel" /><span id="submit" class="btn_style" style="height:24px; line-height:24px; font-weight:normal;">点击上传</span></li>
					<li class="downModel"><span class="labelName">下载模板：</span><a href="/resources/template/28.xls">月份为28天数据模板</a><a href="/resources/template/29.xls">月份为29天数据模板</a><a href="/resources/template/30.xls">月份为30天数据模板</a><a href="/resources/template/31.xls">月份为31天数据模板</a></li>
				</ul>
			</form>
			
			
		</div>
	</div>

</body>
</html>