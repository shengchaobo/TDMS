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
	<script type="text/javascript" src="js/table3/A323.js"></script>

	
</head>
<body style="overflow-y:scroll">
	<table id="showData"  style="height: auto">		
	<thead data-options="frozen:true">
	<tr>
		<th data-options="field:'teaUnit',align:'center'" rowspan="2">
	         专业名称
		</th>
		<th data-options="field:'unitID',align:'center'" rowspan="2">
	          专业代码
		</th>
		</tr>
	</thead>
	<thead>
	<tr>
		<th colspan="5">
		1.各类课程学时结构（%）
		</th>
		<th colspan="6">
        2.各类课程学分结构（%）
		</th>
	</tr>
	<tr>
		<th data-options="field:'requireHour'" formatter="toPercent">
		必修课学时
		</th>
		<th data-options="field:'optionHour'" formatter="toPercent">
	          选修课学时
		</th>
		<th  data-options="field:'inClassHour'" formatter="toPercent">
		理论教学学时
		</th>
		<th data-options="field:'expHour'" formatter="toPercent">
		实验教学学时
		</th>
		<th data-options="field:'praHour'" formatter="toPercent">
		集中实践教学环节学时
		</th>
		<th  data-options="field:'requireCredit'" formatter="toPercent">
		必修课学分
		</th>
		<th data-options="field:'optionCredit'" formatter="toPercent">
	         选修课学分
		</th>
		<th  data-options="field:'inClassCredit'" formatter="toPercent">
		理论教学学分
		</th>
		<th data-options="field:'expCredit'" formatter="toPercent">
		实验教学学分
		</th>
		<th data-options="field:'praCredit'" formatter="toPercent">
		集中实践教学环节学
		</th>
		<th  data-options="field:'outClassCredit'" formatter="toPercent">
		课外科技活动学分
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
			
						<script type="text/javascript">
function toPercent(data){
	if(typeof(data) != "undefined"){
    var strData = parseFloat(data)*100;
    strData = Math.round(strData);
    strData/=100.00;
    return strData;
	}
}

</script>
		
</html>
