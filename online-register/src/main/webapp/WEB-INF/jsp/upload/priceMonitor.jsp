<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<link href="/resources/js/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<link href="/resources/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
<link href="/resources/css/main-pc.css" rel="stylesheet" type="text/css" />
<script src="/resources/js/jquery-1.9.1.min.js"></script>
<script src="/resources/js/My97DatePicker/WdatePicker.js"></script>
<script src="/resources/js/pagePlug.js"></script>
<script src="/resources/js/priceMonitor.js"></script>
<script >
var contextPath = "${pageContext.request.contextPath}";
</script>
</head>
<body class="pcbody">
<div class="container">
	<div class="selectBox clearfix">
    	<div class="select_cond">时间：<input id="cycleMonth" name="cycleMonth" class="Wdate" onclick="WdatePicker({dateFmt:'yyyyMM',maxDate:'%y-{%M}',isShowToday:false})" type="text" readonly></div>
        <div class="select_cond hide_contr" id="select_area">地域：<input id="provinceId" type="text" data-id="10" value="全部" readonly/>
        	<div  class="hide_select_box hide_select_box2">
            	<ul class="select_demo">
            		<li data-tree-id="10" class="on">全部</li>
            		<li data-tree-id="11">北京</li>
            		<li data-tree-id="44">广东</li>
            		<li data-tree-id="41">河南</li>
            		<li data-tree-id="66">平均</li>
            	</ul>
               
            </div>
        </div>
        <!--<div class="select_cond hide_contr">机型：<input type="text"/>
        	<div class="hide_select_box">
            	<ul id="modelTree" class="ztree"></ul>
                <div class="closeBox"><span>关闭</span></div>
            </div>
        </div>
        -->
        <input type="button" class="refreshBtn" value="查询" style="float:left;">
        <a href=''  class="blue_btn download_pc">下载数据</a>
    </div>
    <div class="tableWrap">
    	<div class="priceMonitor">	
    	</div>
    	<div class="paginator"></div>
    </div>
</div>
</body>
</html>