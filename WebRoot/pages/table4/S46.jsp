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

<title>S46</title>
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
	<script type="text/javascript" src="js/table4/S46.js"></script>

</head>
<body style="overflow-y:scroll">
	<table id="showData"  style="height: auto">		
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"  rowspan="2">选取</th>
				<th  data-options="field:'seqNumber'"  hidden="true">编号</th>
				<th data-options="field:'item'" colspan="2">
				项目
				</th>
				<th data-options="field:'fameTeaAward'" >
	       		教学名师
				</th>
				<th data-options="field:'advanceTeaAward'">
				师德先进个人
				</th>
				<th data-options="field:'workAward'">
	       		研究与创作奖
				</th>
				<th data-options="field:'stuWordAward'">
				学生思政队伍工作成果奖
				</th>
				<th data-options="field:'outstdTeaAward'">
				优秀教师
				</th>
				<th data-options="field:'outWorkAward'">
	       		优秀工作者
				</th>
				<th data-options="field:'teathAward'">
	       		教学活动月获奖
				</th><th data-options="field:'otherAward'">
	       		其他
				</th>
			</tr>			
	</thead>
	</table>
								 
	<div id="toolbar" style="height:auto">
		<div style="float: left;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">编辑</a> 
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
