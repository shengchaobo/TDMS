<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>S-5-1-1本科课程库信息统计</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
		<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link rel="stylesheet" type="text/css"
			href="jquery-easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css"
			href="jquery-easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css"
			href="jquery-easyui/themes/main.css">
		<link rel="stylesheet" type="text/css"
			href="jquery-easyui/demo/demo.css">

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
	width: 80px;
}
</style>
		<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
		<script type="text/javascript" src="jquery-easyui/jquery-1.7.2.min.js"></script>
		<script type="text/javascript"
			src="jquery-easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
		<script type="text/javascript"
			src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
				<script type="text/javascript" src="js/commom.js"></script>
	<script type="text/javascript" src="js/table5/S511.js"></script>
	</head>
	
	<body style="overflow-y: scroll">
		<table class="easyui-datagrid" toolbar="#toolbar" title="S-5-1-1本科课程库信息统计"></table>
		
		<hr color="blue" width="100%" />
		<table id="showInfo" class="doc-table"  url="">
			<tbody>

				<tr>
					<td rowspan="2" style="width: 200px; background-color: white " align="center">项目</td>
					<td colspan="2" align="center">理论课（实践课）</td>
					<td colspan="2" align="center">理论课（不含实践）</td>
					<td colspan="2" align="center">集中性实践环节</td>
					<td colspan="2" align="center">实验课</td>
				</tr>
				<tr>
					<td align="center">门数（们）</td>
					<td align="center">比例（%）</td>
					<td align="center">门数（们）</td>
					<td align="center">比例（%）</td>
					<td align="center">门数（们）</td>
					<td align="center">比例（%）</td>
					<td align="center">门数（们）</td>
					<td align="center">比例（%）</td>
				</tr>
			</tbody>
		</table>
		
	  
		<div id="toolbar" style="height: auto">
		<form  id="exportForm"  method="post" style="float: right;">
			<select class="easyui-combobox" id="cbYearContrast" name="selectYear" panelHeight="auto" style="width:80px; padding-top:5px; margin-top:10px;"></select>
				<a href="pages/T11/dataExport" class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a>
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
