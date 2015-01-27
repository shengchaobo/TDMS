<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>数据库备份与恢复</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
		<link rel="stylesheet" type="text/css"  href="jquery-easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="jquery-easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="jquery-easyui/demo/demo.css">
		<link rel="stylesheet" type="text/css" href="css/common.css">
		<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
		<script type="text/javascript" src="jquery-easyui/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="jquery-easyui/jquery-migrate-1.2.1.min.js"></script>
		<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
		
		
	</head>
<body style="overflow-y:scroll">
	
	<div id="toolbar" style="height:auto">
		<div style="float: left;height: 24">
			<a href='pages/DatabaseManager/backup' class="easyui-linkbutton"
				iconCls="icon-download" plain="true">数据备份</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="recover('11')">数据恢复</a> 

		</div>
		<form method="post"  id="searchForm"   style="float: right;height: 24px;"  >
			<!-- 两个文体输入框，可以避免enter键自动刷新事件 -->
			<input id="hiddenText" type="text"  style="display:none" />
		</form>
		
	</div>
	<div id="udlg" class="easyui-dialog" style="width:500px;height:180px;padding:10px 20px;" closed="true" data-options="modal:true">
		<div class="ftitle">数据恢复</div>
		<div class="fitem">
			<Form id="batchForm" enctype="multipart/form-data" method="post">
				<label>请选备份文件：</label> 
				<input  type="file"  id="upload" name="uploadFile" />
				<input type="button" value=" 提交 "  onclick="batchImport()"/>
			</Form>
		</div>
	</div>
	
	
	
</body>
</html>
