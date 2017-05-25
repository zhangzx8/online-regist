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

<script type="text/javascript">
function do_delete() {
	
	var del=document.getElementById('shanchu');
	
	var radio = document.getElementsByName("funcId");

	var funcId="";
	var num=0;
		for(var i = 0; i < radio.length; i++) {
			if(radio[i].checked) {
				funcId=radio[i].value;
				num++;
			}
		}
	if(num<1){
		alert("请选择功能模块！");
	}else if(confirm('确定删除？')) {
		location.href="/func/del?id=" + funcId;
	}
}


function do_add(){  
	var tj=document.getElementById('tijiao');
	
	var radio = document.getElementsByName("funcId");

	var funcId="";
	var num=0;
		for(var i = 0; i < radio.length; i++) {
			if(radio[i].checked) {
				funcId=radio[i].value;
				num++;
			}
		}
	if(num<1){
		alert("请选择一个父模块！");
	}else{
	location.href="/func/add?id=" + funcId;
	}
	}
	
	function do_update() {
	
	var xg=document.getElementById('xiugai');
	
	var radio = document.getElementsByName("funcId");

	var funcId="";
	var num=0;
		for(var i = 0; i < radio.length; i++) {
			if(radio[i].checked) {
				funcId=radio[i].value;
				num++;
			}
		}
	if(num<1){
		alert("请选择一个模块！");
	}else{
		location.href="/func/update?id=" + funcId;

	}
	}

</script>
</head>
<body>

	 <div class="operation" id="switchoperation">
    	<input id='tijiao' alt="" title="添加" onclick='do_add()' type="button" value="添加" />
		<input id='xiugai' alt="" title="修改" onclick='do_update()' type="button" value="修改" />
		<input id='shanchu' alt="" title="删除" onclick='do_delete()'  type="button" value="删除" />
	</div>
	
	</div>
		<table>
			<tr>
				<td width="10%"></td>
				<td><script type="text/javascript">
					d = new dTree('d');
					d.config.radio = true;
					d.add(0, -1, '功能列表');
					${modulTree}
				</script></td>
			</tr>
		</table>
	</div>
</body>
</html>