<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>权限管理</title>
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
		<script type="text/javascript" src="js/system/RightManager.js"></script>
	</head>
	<body>
		<table  id="tg"  class="easyui-treegrid"  style="height: auto;"  >
			<thead>
				<tr>
					<th data-options="field:'name',editor:'text'">
						权限选项
					</th>
					<th data-options="field:'ck',align:'right',checkbox:true" >
						权限选择
					</th>
				</tr>
			</thead>
		</table>
		
		<div id="toolbar" style="height:auto">
		<div style="float: left;height: 24"><!--
			<a href="javascript:void(0)" id="edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true"  onclick="edit()">编辑</a>
			--><a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-save" plain="true"  onclick="saveEdit()">保存修改</a>
			<a href="javascript:void(0)" id="cancel" class="easyui-linkbutton" iconCls="icon-cancel" plain="true"  onclick="cancel()">取消</a>		
		</div>
	 	 <form  id="roleForm"  style="float: right;"  method="post" >
			所属角色： <select class="easyui-combobox"  id="roleID"  name="roleID" panelHeight="auto" style="width:120px" ></select>
	 	</form>	
	</div>
	</body>
</html>
