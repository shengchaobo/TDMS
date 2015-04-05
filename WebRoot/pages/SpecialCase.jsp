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

<title>SpecialCase</title>
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
	<script type="text/javascript" src="js/commom.js"></script>
	<script type="text/javascript" src="js/SpecialCase.js"></script>
</head>

<body style="height: 100%'" >
	<table title="特殊情况说明" id="commomData"  class="easyui-datagrid"  url="loadCaseInfo"  style="height: auto"  >
		<thead>
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th data-options="field:'tableName',align:'center'">
					　　表名（例如1-1形式）
				</th>
				<th data-options="field:'instruction',align:'center'">
						说明
				</th>				
				<th data-options="field:'note',align:'center'">
					   　备注
				</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="height:auto">
		<div style="float: left;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newObject()">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">编辑</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteByIds()">删除</a>
		</div>
	    <div style="float: right;">
			<a href='dataExport?excelName=<%=URLEncoder.encode("特殊情况说明表","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 		
		</div>
	</div>


	<!--添加弹出框-->
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">	
	   <h3 class="title1">特殊说明导入</h3>
	   <form id="addForm" method="post">
		<table>	
			<tr>
				<td>
					<div class="fitem">
					<label>表名（例如1-1形式）：</label> 
					<input id="TableName" type="text" name="specialCaseBean.TableName"
					class="easyui-validatebox" >
					</div>
				</td>
			</tr>
			<tr>
				<td style="valign:left" colspan="3"><label>说明：</label>
					<textarea id="Instruction" name="specialCaseBean.Instruction" style="resize:none" cols="30" rows="10"></textarea>
				</td>
			</tr>						
			<tr>
				<td style="valign:left" colspan="3"><label>备注：</label>
					<textarea id="Note" name="specialCaseBean.Note" style="resize:none" cols="50" rows="10"></textarea>
				</td>
			</tr>			
		</table>
		</form>
	</div>
	<!-- 跟dlg组合-->
	<div id="dlg-buttons"  >
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="singleImport()">保存</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
</body>
</html>
