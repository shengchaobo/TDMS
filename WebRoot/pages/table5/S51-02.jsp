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

<title>S531-01</title>
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
	<script type="text/javascript" src="js/table5/S51-02.js"></script>

	
</head>
<body style="overflow-y:scroll">
	<table id="showData"  style="height: auto">	
		
		<thead>
			<tr>
			
				<th data-options="field:'item',align:'center'" rowspan="2">
			     	项目
				</th>
				<th align="center" colspan="2">
				理论课（实践课）
				</th>
				<th align="center" colspan="2">
			   理论课（不含实践）
				</th>
				<th align="center"colspan="2">
				集中性实践环节
				</th>
				<th align="center" colspan="2">
			     	实验课
				</th>
			</tr>
			<tr>
					<th data-options="field:'theoPraNum',align:'center'">门数（门）</th>
					<th data-options="field:'theoPraRatio',align:'center'" formatter = "formatRatio">比例（%）</th>
					<th data-options="field:'inClassNum',align:'center'" >门数（门）</th>
					<th data-options="field:'inClassRatio',align:'center'" formatter = "formatRatio">比例（%）</th>
					<th data-options="field:'praNum',align:'center'">门数（门）</th>
					<th data-options="field:'praRatio',align:'center'" formatter = "formatRatio">比例（%）</th>
					<th data-options="field:'expNum',align:'center'">门数（门）</th>
					<th data-options="field:'expRatio',align:'center'" formatter = "formatRatio">比例（%）</th>
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
			   function formatRatio(val){
				   var ratio;
				   if(val!=null){
					   var str = val+"";
					   ratio=str+"%";
				   }
				   return ratio;
			   }
			</script>
		
</html>

