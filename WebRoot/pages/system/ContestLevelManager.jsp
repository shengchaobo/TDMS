<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>竞赛类型管理</title>
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
		<script type="text/javascript" src="js/system/ContestLevelManager.js"></script>
		
	</head>
<body style="overflow-y:scroll">
	<table id="levelManager" class="easyui-datagrid"  style="height: auto;"  >
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="indexId">竞赛类型代码</th>
				<th field="contestLevel">竞赛类型名称</th>

			</tr>
		</thead>
	</table>
	<div id="toolbar" style="height:auto">
		<div style="float: left;height:auto">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="newLevel()">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="editLevel()">编辑</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="deleteByIds()">删除</a>
		</div>
	</div>
	<div id="dlg" class="easyui-dialog"
		style="width:600px;height:150px;padding:10px 20px;" closed="true"
		data-options="modal:true" buttons="#dlg-buttons">
		<form id="levelManagerForm" method="post">
			<table>
					<tr>
						<td>
							<div class="fitem">
								<label>
									竞赛类型代码：
								</label>
								<input id="IndexID" type="text" name="level_bean.indexId"
									class="easyui-validatebox">
								<span id="IndexIDSpan"></span>
							</div>
						</td>
						<td class="empty"></td>
						<td>
							<div class="fitem">
								<label>
									竞赛类型名称：
								</label>
								<input id="ContestLevel" type="text" name="level_bean.contestLevel"
									class="easyui-validatebox">
								<span id="ContestLevelSpan"></span>
							</div>
						</td>
					</tr>
						
			</table>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="singleImport()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
</body>
</html>
