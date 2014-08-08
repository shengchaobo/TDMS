<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'table.jsp' starting page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
	<!--
		<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui/demo/demo.css">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="js/table7/S71.js"></script>

	
</head>
<body style="overflow-y:scroll">
	<table>
	</table>
	<table id="showData"  style="height: auto">		
		<thead>
			<tr>
				<th data-options="field:'teaUnit',align:'center'" rowspan="2">
			          教学单位
				</th>
				<th data-options="field:'unitID',align:'center'" rowspan="2">
				单位号
				</th>
				<th colspan="6">
				1.教学成果奖（项）
				</th>
				<th colspan="3">
				2.教学论文（篇）
				</th>
			</tr>
			<tr>
				<th data-options="field:'sumTeaAward'">
				总数
				</th>
				<th data-options="field:'interAward'">
		          国际级
				</th>
				<th  data-options="field:'nationAward'">
				国家级
				</th>
				<th data-options="field:'proviAward'">
				省部级
				</th>
				<th data-options="field:'cityAward'">
				市级
				</th>
				<th  data-options="field:'schAward'">
				校级
				</th>
				<th data-options="field:'sumTeaPaper'">
				总数
				</th>
				<th data-options="field:'teaResPaper'">
	           教学研究
				</th>
				<th  data-options="field:'teaManagePaper'">
				教学管理
				</th>
			</tr>	
			</thead>
			
	</table>
					
			 
	<div id="toolbar" style="height:auto">
		<div style="float: left;">	
			<a href="javascript:void(0)" id="export" class="easyui-linkbutton" iconCls="icon-download" plain="true"  onclick="exports()">数据导出</a>
		</div>
	 	 <form  id="exportForm"  style="float: right;"  method="post" >
			显示： <select class="easyui-combobox" id="cbYearContrast" panelHeight="auto" style="width:80px; padding-top:5px; margin-top:10px;"  editable=false ></select>
	 	</form>	
	</div>	
</body>

			 <script type="text/javascript">
		    	var currentYear = new Date().getFullYear();
		    	var select = document.getElementById("cbYearContrast");
		    	for (var i = 0; i <= 10; i++) {
		        var theOption = document.createElement("option");
		        	theOption.innerHTML = currentYear-i + "年";
		        	theOption.value = currentYear-i;
		        	select.appendChild(theOption);
		    	}
			</script>
		
</html>
