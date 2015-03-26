<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>角色管理</title>
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
		<script type="text/javascript" src="js/system/RoleManager.js"></script>
		
	</head>
<body style="overflow-y:scroll">
	<table id="roleManager" class="easyui-datagrid"  style="height: auto;"  >
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="roleID">角色编号</th>
				<th field="roleName">角色名称</th>
				<th field="unitName">单位名称</th>
				<th field="roleDest">角色描述</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="height:auto">
		<div style="float: left;height: 24">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="newRole()">添加角色</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="editRole()">编辑角色</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="deleteByIds()">删除角色</a>
		</div>
		<form method="post"  id="searchForm"   style="float: right;height: 24px;"  >
				<table id="test" width="280">
					<tr>
						<td>
							角色编号 :
						</td>
						<td>
							<input id="searchID"  name=" searchID"  class="easyui-box" style="height:24px" />
						</td>
						<td>
							<a href="javascript:void(0)" class="easyui-linkbutton"
								iconCls="icon-search" plain="true" onclick=	reloadgrid();>查询</a>
						</td>
					</tr>
				</table>
		</form>
	</div>
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true"
		data-options="modal:true" buttons="#dlg-buttons">
		<form id="roleManagerForm" method="post">
			<table>
					<tr>
						<td>
							<div class="fitem">
								<label>
									角色编号：
								</label>
								<input id="RoleID" type="text" name="role_bean.RoleID"
									class="easyui-validatebox">
								<span id="RoleIDSpan"></span>
							</div>
						</td>
						<td class="empty"></td>
						<td>
							<div class="fitem">
								<label>
									角色名称：
								</label>
								<input id="RoleName" type="text" name="role_bean.RoleName"
									class="easyui-validatebox">
								<span id="RoleNameSpan"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div class="fitem">
								<label>
									角色单位：
								</label>
								<input id="UnitName" type="text" name="role_bean.UnitName"
									class="easyui-validatebox">
								<span id="UnitNameSpan"></span>
							</div>
						</td>
				</tr>
				<tr>
					<td style="valign:left" colspan="3"><label>角色描述：</label>
							<textarea id="RoleDest" name="role_bean.RoleDest" style="resize:none" cols="50" rows="10"></textarea>
							<span id="RoleDestSpan"></span>
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
