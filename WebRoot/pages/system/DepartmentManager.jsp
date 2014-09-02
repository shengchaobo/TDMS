<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>部门管理</title>
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
		<script type="text/javascript" src="js/system/DepartmentManager.js"></script>
		
	</head>
<body style="overflow-y:scroll">
	<table id="departmentManager" class="easyui-datagrid"  style="height: auto;"  >
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="unitId">单位号</th>
				<th field="unitName">部门名称</th>
				<th field="class1">一级分类</th>
				<th field="class2">二级分类</th>
				<th field="functions">单位职能</th>
				<th field="leader">负责人</th>
				<th field="teaId">教工号</th>
				<th field="note">备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="height:auto">
		<div style="float: left;height: 24">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="newDepartment()">添加部门</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="editDepartment()">编辑部门</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="deleteByIds()">删除部门</a>
		</div>
		<form method="post"  id="searchForm"   style="float: right;height: 24px;"  >
			<!-- 两个文体输入框，可以避免enter键自动刷新事件 -->
			<input id="hiddenText" type="text"  style="display:none" />
		 	单位号 :&nbsp;<input id="searchID"  name=" searchID"  class="easyui-box" style="height:24px" />
			<a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-search"  plain="true" onclick="reloadgrid ()">查询</a>
		</form>
	</div>
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true"
		data-options="modal:true" buttons="#dlg-buttons">
		<form id="departmentManagerForm" method="post">
			<table>
					<tr>
						<td>
							<div class="fitem">
								<label>
									单位号：
								</label>
								<input id="UnitID" type="text" name="de_bean.unitId"
									class="easyui-validatebox">
								<span id="UnitIDSpan"></span>
							</div>
						</td>
						<td class="empty"></td>
						<td>
							<div class="fitem">
								<label>
									部门名称：
								</label>
								<input id="UnitName" type="text" name="de_bean.unitName"
									class="easyui-validatebox">
								<span id="UnitNameSpan"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="fitem">
								<label>
									一级分类：
								</label>
								<input id="Class1" type="text" name="de_bean.class1"
									class="easyui-validatebox">
								<span id="Class1Span"></span>
							</div>
						</td>
						<td class="empty"></td>
						<td>
							<div class="fitem">
								<label>
									二级分类：
								</label>
								<input id="Class2" type="text" name="de_bean.class2"
									class="easyui-validatebox">
								<span id="Class2Span"></span>
							</div>
						</td>
					</tr>
					
					<tr>
						<td>
							<div class="fitem">
								<label>
									单位职能：
								</label>
								<input id="Functions" type="text" name="de_bean.functions"
									class="easyui-validatebox">
								<span id="FunctionsSpan"></span>
							</div>
						</td>
						<td class="empty"></td>
						<td>
							<div class="fitem">
								<label>
									负责人：
								</label>
								<input id="Leader" type="text" name="de_bean.leader"
									class="easyui-validatebox">
								<span id="LeaderSpan"></span>
							</div>
						</td>
					</tr>
					
					<tr>
						<td style="valign:left" colspan="3">
							<div class="fitem">
								<label>
									教工号：
								</label>
								<input id="TeaID" type="text" name="de_bean.teaId"
									class="easyui-validatebox">
								<span id="TeaIDSpan"></span>
							</div>
						</td>
						
					</tr>
					
				<tr>
					<td style="valign:left" colspan="3"><label>备注：</label>
							<textarea id="Note" name="de_bean.note" style="resize:none" cols="50" rows="10"></textarea>
							<span id="NoteSpan"></span>
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
