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
	<script type="text/javascript" src="js/table3/A322.js"></script>

	
</head>
<body style="overflow-y:scroll">
	<table id="showData"  style="height: auto">		
	<thead>
	<tr>
		<th data-options="field:'teaUnit',align:'center'" rowspan="2">
	          教学单位
		</th>
		<th data-options="field:'unitID',align:'center'" rowspan="2">
	          单位号
		</th>
		<th data-options="field:'fieldNum',align:'center'" rowspan="2">
	          本科专业数
		</th>
		<th colspan="6">
		优势专业占专业总数比例（%）
		</th>
	</tr>
	<tr>
		<th data-options="field:'sum'" formatter="toPercent">
		合计
		</th>
		<th data-options="field:'internationRatio'" formatter="toPercent">
	          国际级
		</th>
		<th  data-options="field:'nationRatio'" formatter="toPercent">
		国家级
		</th>
		<th data-options="field:'proviRatio'" formatter="toPercent">
		省部级
		</th>
		<th data-options="field:'cityRatio'" formatter="toPercent">
		市级
		</th>
		<th  data-options="field:'schoolRatio'" formatter="toPercent">
		校级
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
