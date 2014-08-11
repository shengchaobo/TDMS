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

<title>S25</title>
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
	<script type="text/javascript" src="js/table2/S25.js"></script>

</head>
<body style="overflow-y:scroll">
	<table id="showData"  style="height: auto">		
		<thead data-options="frozen:true">
			<tr>
				<th data-options="field:'expCenterName',align:'center'" rowspan="2">
			          实验中心名称
				</th>			
				<th data-options="field:'teaUnit',align:'center'" rowspan="2">
			          所属教学单位
				</th>
				<th data-options="field:'unitID',align:'center'" rowspan="2">
					教学单位号
				</th>
			</tr>
		</thead>
		<thead>
			<tr>
				<th data-options="field:'machNum'">
				   台件数量
				</th>
				<th data-options="field:'money'">
		          金额（元）
				</th>
				<th  data-options="field:'area'">
				面积（平方米）
				</th>
				<th data-options="field:'newArea'">
				其中当年新增面积（平方米）
				</th>
				<th data-options="field:'labHour'">
				每次实验教学学时数
				</th>
				<th  data-options="field:'labStuNum'">
				每次可容纳的学生数（个）
				</th>
				<th data-options="field:'yearHour'">
				学年度承担的实验教学人时数（人时）
				</th>
				<th data-options="field:'yearTimes'">
	           学年度承担的实验教学人次数（人次）
				</th>
				<th  data-options="field:'itemNum'">
				本科生实验、实习、实训项目数（个）
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
