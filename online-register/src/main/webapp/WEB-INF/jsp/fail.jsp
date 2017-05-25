<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>失败</title>
<style type="text/css">
body{font-family:""STHeitiSC-Medium","Heiti SC Medium","Heiti SC""; font-size:14px; color:#333;}
div{margin:0; padding:0;}
.fail{ min-height:300px; height:auto !important; height:300px; margin:120px auto 0; font-weight:bold; font-size:20px; line-height:24px; text-align:center;}
.fail .failTip{margin:0 0 0 20px; text-align:left; vertical-align:60px; display:inline-block; width:400px; line-height:24px;}
</style>
</head>
<body>

<div class="fail"><img src="../../resources/images/alert_icon.png" width="120" height="120"><span class="failTip">${result }</span></div>
</body>
</html>