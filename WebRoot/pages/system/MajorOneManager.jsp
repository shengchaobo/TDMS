<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>专科专业管理</title>
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
		<script type="text/javascript" src="js/system/MajorOneManager.js"></script>
		
	</head>
<body style="overflow-y:scroll">
	<table id="majorManager" class="easyui-datagrid"  style="height: auto;"  >
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="majorNum">专业代码</th>
				<th field="majorName">专业名称</th>
				<th field="direction">方向名称</th>					
				<th field="duration">年限</th>			
				<th field="unitId">所属学院代号</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="height:auto">
		<div style="float: left;height: 24">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="newMajor()">添加专科专业</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="editMajor()">编辑专科专业</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="deleteByIds()">删除专科专业</a>
		</div>
		<form method="post"  id="searchForm"   style="float: right;height: 24px;"  >
		 	专业代码 :&nbsp;<input id="searchID"  name=" searchID"  class="easyui-box" style="height:24px" />
			<a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-search"  plain="true" onclick="reloadgrid ()">查询</a>
		</form>
	</div>
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true"
		data-options="modal:true" buttons="#dlg-buttons">
		<form id="majorManagerForm" method="post">
			<table>
					<tr>
						<td>
							<div class="fitem">
								<label>
									专业代码：
								</label>
								<input id="MajorNum" type="text" name="major_bean.majorNum"
									class="easyui-validatebox">
								<span id="MajorNumSpan"></span>
							</div>
						</td>
						<td class="empty"></td>
						<td>
							<div class="fitem">
								<label>
									专业名称：
								</label>
								<input id="MajorName" type="text" name="major_bean.majorName"
									class="easyui-validatebox">
								<span id="MajorNameSpan"></span>
							</div>
						</td>
						
					</tr>
					<tr>
						<td>
							<div class="fitem">
								<label>
									方向名称：
								</label>
								<input id="Direction" type="text" name="major_bean.direction"
									class="easyui-validatebox">
								<span id="DirectionSpan"></span>
							</div>
						</td>
						<td class="empty"></td>	
						<td>
							<div class="fitem">
								<label>
									年限：
								</label>
								<select class='easyui-combobox' id="Duration" name="major_bean.duration" style="width:80px">
									<option value="一年">一年</option>
									<option value="二年">二年</option>
									<option value="三年">三年</option>
									<option value="四年">四年</option>
									<option value="五年">五年</option>
									<option value="六年">六年</option>
							 <span id="DurationIdSpan"></span>
							</div>
						</td>								
					</tr>
					
					<tr>
						
						<td>
							<div class="fitem">
								<label>
									所属学院：
								</label>
								<input id="UnitId" type="text" name="major_bean.unitId" class="easyui-combobox" 
								data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDIDepartmentAca' ,listHeight:'auto',editable:false,
							"/>
								<span id="UnitIDSpan"></span>
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