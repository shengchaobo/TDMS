<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

		<title>用户管理</title>
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
		<script type="text/javascript" src="js/system/UserManager.js"></script>
		
</head>
<body style="overflow-y:scroll">
	<table id="userManager" class="easyui-datagrid"  style="height: auto;"  >
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="teaID">教工号</th>
				<th field="teaName">姓名</th>
				<th field="unitID">单位号</th>
				<th field="fromOffice">单位名称</th>
				<th field="teaEmail">电子邮箱</th>
				<th field="roleID"  hidden="true">用户ID</th>
				<th field="roleName">用户角色</th>
				<th field="userNote">备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="height:auto">
		<div style="float: left;height: 24">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="newUser()">添加用户</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="editUser()">编辑用户</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="deleteByIds()">删除用户</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="resetPassword()">重置密码</a>
		</div>
		<form method="post"  id="searchForm"   style="float: right;height: 24px;"  >
			<!-- 两个文体输入框，可以避免enter键自动刷新事件 -->
			<input id="hiddenText" type="text"  style="display:none" />
		 	教工号 :&nbsp;<input id="searchID"  name=" searchID"  class="easyui-box" style="height:24px" />
			<a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-search"  plain="true" onclick="reloadgrid ()">查询</a>
		</form>
	</div>
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true"
		data-options="modal:true" buttons="#dlg-buttons">
		<form id="userManagerForm" method="post">
			<table>
				<tr>
					<td>
					<input type="hidden" name="userinfo.SeqNumber" id="seqNumber"/> 
					<div class="fitem">
					<label>教工号：</label> 
					<input type="hidden" name="userinfo.TeaID" id="TeaID"/>
					<input id="TeaName" type="text" name="userinfo.TeaName" class='easyui-combobox'  
								data-options="valueField:'teaName',textField:'teaId',url:'pages/T411/loadT411',listHeight:'auto',editable:true,
								onSelect:function(){
								 	 document.getElementById('TeaID').value=$(this).combobox('getText') ;
								 }">
					<span id="TeaIDSpan"></span>
					</div>
					</td>
					<td class="empty"></td>
					<td>
						<div class="fitem">
							<label>职工单位：</label>
							<!-- 下边的onselect方法是为了后台既要教学单位名称，有需要教学单位编号，而我们只有一个下拉框包含了这两条信息 -->
							<input type="hidden" name="userinfo.FromOffice" id="FromOffice" />
							<input id="UnitID" name="userinfo.UnitID"
								class='easyui-combobox'
								data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment',listHeight:'auto',editable:false,
							 	onSelect:function(){
							 		document.getElementById('FromOffice').value=$(this).combobox('getText') ;
							 	}">
							<span id="UnitIDSpan"></span>
						  </div>
						</td>
				</tr>
				<tr>
						<td>
							<div class="fitem">
								<label>电子邮箱：</label> 
								<input type="text" name="userinfo.TeaEmail" 
									class="easyui-validatebox" id="TeaEmail"/>
								<span id="TeaEmailSpan"></span>
							</div>
						</td>
						<td class="empty"></td>
						<td>
							<div class="fitem">
							<label>用户角色：</label>
							<input type="hidden" name="userinfo.RoleName" id="RoleName" />
							<input id="RoleID" name="userinfo.RoleID"
								class='easyui-combobox'
								data-options="valueField:'roleID',textField:'roleName',url:'pages/diRole/loadDIRoles',listHeight:'auto',editable:false,
							 	onSelect:function(){
							 		document.getElementById('RoleName').value=$(this).combobox('getText') ;
							 	}">
							<span id="RoleIDSpan"></span>
						  </div>
					  </td>
					</tr>
					<tr>
						<td style="valign:left" colspan="3"><label>备注：</label>
							<textarea id="UserNote" name="userinfo.UserNote" style="resize:none" cols="50" rows="10"></textarea>
							<span id="UserNoteSpan"></span>
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
