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

<title>T54</title>
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
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="js/commom.js"></script>
	<script type="text/javascript" src="js/table5/T54.js"></script>
	<script type="text/javascript"></script>
</head>

<body style="height: 100%'" >
 <table id="edit" class="easyui-propertygrid"  ></table>
 <div id="toolbar" style="height:30px;">
		<div style="float: left;">	
			<a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-save" plain="true"  onclick="getSave()">保存</a>
			<a href="javascript:void(0)" id="cancel" class="easyui-linkbutton" iconCls="icon-cancel" plain="true"  >取消</a>
			<a href="javascript:void(0)" id="export" class="easyui-linkbutton" iconCls="icon-download" plain="true"  ">数据导出</a>
		</div>
	 	 <form  id="exportForm"  style="float: right;"  method="post" >
			显示： <select class="easyui-combobox" id="cbYearContrast" panelHeight="auto" style="width:80px; padding-top:5px; margin-top:10px;"  editable=false ></select>
	 	</form>	
	</div>
	
	<div id="dlg" class="easyui-dialog"
		style="width:600px;height:auto;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="title1">课外活动、讲座导入</h3>
		<div class="fitem" id="item1">
			<form id="batchForm" method="post" enctype="multipart/form-data">
			<select class="easyui-combobox"  id="cbYearContrast1" name="selectYear" editable="false"></select>
				<input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox" size="48" style="height: 24px;"  required="true" />
				<a href="javascript:void(0)" id = "import" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<!--  <a href='pages/T181/downloadModel?saveFile=<%=URLEncoder.encode("表1-8-1签订合作协议机构.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>-->
			</form>
		</div>
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
    	var currentYear = new Date().getFullYear();
    	var select = document.getElementById("cbYearContrast1");
    	for (var i = 0; i <= 10; i++) {
        var theOption = document.createElement("option");
        	theOption.innerHTML = currentYear-i + "年";
        	theOption.value = currentYear-i;
        	select.appendChild(theOption);
    	}
	</script>

	
	
</html>
