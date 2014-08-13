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

<title>S512</title>
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
	
	<style type="text/css">
		#fm {
			margin: 0;
			padding: 10px 30px;
		}
		
		.ftitle {
			font-size: 14px;
			font-weight: bold;
			padding: 5px 0;
			margin-bottom: 10px;
			border-bottom: 1px solid #ccc;
		}
		
		.fitem {
			margin-bottom: 5px;
		}
		
		.fitem label {
			display: inline-block;
			width: 120px;
		}
	</style>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="js/commom.js"></script>
	<script type="text/javascript" src="js/table5/S512.js"></script>

	
</head>
<body style="overflow-y:scroll">
	<table id="showData"  style="height: auto">	
	<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'teaUnit',align:'center'" rowspan="3" >
			          开课单位
				</th>
				<th data-options="field:'unitID',align:'center'" rowspan="3">
				单位号
				</th>
		     </tr>
		</thead>
		
		<thead>
			<tr>
				<th colspan="2">
				1.本科课程门次数
				</th>
				<th colspan="6">
				2.主讲本科课程的教师
				</th>
				<th colspan="2">
				3.授课情况
				</th>
			</tr>
			<tr>
				<th  data-options="field:'sumCS',align:'center'" rowspan="2" >
				总数
				</th>
				<th data-options="field:'smallCSNum',align:'center'" rowspan="2">
			         其中：小班授课
				</th>
				<th data-options="field:'sumTeaNum',align:'center'" rowspan="2">
				总人数(人)
				</th>
				<th colspan="5">
				其中
				</th>
				<th  data-options="field:'CSProfNum',align:'center'" rowspan="2">
				由教授授课的课程门次（门次）
				</th>
				<th data-options="field:'CSViceProfNum',align:'center'" rowspan="2">
				由副教授授课的课程门次（门次）
				</th>
			</tr>
			<tr>
				<th data-options="field:'quqlifyTea',align:'center'">
				符合岗位资格（人）
				</th>
				<th data-options="field:'professor',align:'center'">
				教授（人）
				</th>
				<th data-options="field:'viceProfessor',align:'center'">
				副教授（人）
				</th>
				<th data-options="field:'juniorTea',align:'center' ">
				为低年级授课的教授（人）
				</th>
				<th data-options="field:'juniorViceProf',align:'center'">
				为低年级授课的副教授（人）
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

