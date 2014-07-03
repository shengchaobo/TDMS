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

<title>近一届文、理科本科生录取标准及人数</title>
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
			width: 80px;
		}
	</style>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="js/commom.js"></script>
	<script type="text/javascript" src="js/table6/T622.js"></script>
</head>

<body>
	<table id="commomData" title="近一届文、理科本科生录取标准及人数" class="easyui-datagrid" url="pages/T622/loadData" style="height: auto;">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber">序号</th>
				<th field="fromTeaUnit">所属教学单位</th>
				<th field="unitId">单位号</th>
				<th field="majorName">专业名称</th>
				<th field="majorId">专业代码</th>
				<th field="amisPlanNum">招生计划数</th>
				<th field="actulEnrollNum">实际录取数</th>
				<th field="actulRegisterNum">实际报到数</th>
				<th field="autoEnrollNum">自主招生数</th>
				<th field="specialtyEnrollNum">招收特长生数</th>
				<th field="inProviEnrollNum">招收本省学生数</th>
				<th field="newMajEnrollNum">新办专业招生数</th>
				<th field="time" formatter="formattime">时间</th>
				<th field="note">备注</th>
			</tr>
		</thead>
	</table>
	
	<div id="toolbar" style="height:auto">
		<div style="float: left;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newItem()">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editItem()">编辑</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteByIds()">删除</a>
			
		</div>
		 <div style="float: right;">
		 	专业名称: <input id="searchItem" class="easyui-box" style="width:80px"/>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="reloadgrid()">查询</a>
		</div>
	</div>
	
	<!--审核通过数据-->
	<table id="verfiedData"  class="easyui-datagrid"  url=""  style="height: auto;" >
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
		     </tr>
		</thead>
			<thead>
			<tr>
				<th field="seqNumber">序号</th>
				<th field="fromTeaUnit">所属教学单位</th>
				<th field="unitId">单位号</th>
				<th field="majorName">专业名称</th>
				<th field="majorId">专业代码</th>
				<th field="amisPlanNum">招生计划数</th>
				<th field="actulEnrollNum">实际录取数</th>
				<th field="actulRegisterNum">实际报到数</th>
				<th field="autoEnrollNum">自主招生数</th>
				<th field="specialtyEnrollNum">招收特长生数</th>
				<th field="inProviEnrollNum">招收本省学生数</th>
				<th field="newMajEnrollNum">新办专业招生数</th>
				<th field="time" formatter="formattime">时间</th>
				<th field="note">备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar2" style="float: right;">
		<a href='pages/T622/dataExport?excelName=<%=URLEncoder.encode("表6-2-2近一届文、理科本科生录取标准及人数（招就处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="">高级检索</a>
	</div>
	
	
	<!--添加弹出框-->
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<div class="ftitle" id="title1">近一届文、理科本科生录取标准及人数批量导入</div>
		<div class="fitem" id="item1">
			<form id="batchForm" method="post" enctype="multipart/form-data">
				<label>批量上传：</label> 
				<input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox" size="48" style="height: 24px;"
					validType="fileType['xls']" required="true" invalidMessage="请选择Excel格式的文件" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">导入</a>
				<a href='pages/T622/downloadModel?saveFile=<%=URLEncoder.encode("表6-2-2近一届文、理科本科生录取标准及人数（招就处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
		</div>
	
		<div></div>
		<div class="ftitle">近一届文、理科本科生录取标准及人数逐条导入</div>
		<form id="addItemForm" method="post">
		<table>
			<tr>
			<td>
					<div class="fitem">
						<label>所属教学单位：</label> 
						<input id="seqNumber" type="hidden" name="UndergraAdmiInfo.seqNumber">		
						<input id="fromTeaUnit" type="hidden" name="UndergraAdmiInfo.fromTeaUnit">										
						<input id="unitId" type="text" name="UndergraAdmiInfo.unitId" class='easyui-combobox'
							data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment',listHeight:'auto',editable:false,
							onSelect:function(){
							 	 document.getElementById('fromTeaUnit').value=$(this).combobox('getText') ;
							 }">
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>专业名称：</label> 
						<input id="majorName" type="hidden" name="UndergraAdmiInfo.majorName">
						<input id="majorId" type="text" name="UndergraAdmiInfo.majorId" class='easyui-combobox'
							data-options="valueField:'majorNum',textField:'majorName',url:'pages/DiMajorTwo/loadDiMajorTwo',listHeight:'auto',editable:false,
							onSelect:function(){
							 	 document.getElementById('majorName').value=$(this).combobox('getText') ;
							 }"></input>
					</div>
				</td>
			</tr>
		
			<tr>
				<td>
					<div class="fitem">
						<label>招生计划数：</label> 
						<input id="amisPlanNum" name="UndergraAdmiInfo.amisPlanNum" 
							 class='easyui-validatebox'><span id="amisPlanNumSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>实际录取数：</label> 
						<input id="actulEnrollNum" name="UndergraAdmiInfo.actulEnrollNum" 
							 class='easyui-validatebox'><span id="actulEnrollNumSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>实际报到数：</label> 
						<input id="actulRegisterNum" name="UndergraAdmiInfo.actulRegisterNum" 
							 class='easyui-validatebox'><span id="actulRegisterNumSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>自主招生数：</label> 
						<input id="autoEnrollNum" name="UndergraAdmiInfo.autoEnrollNum" 
							 class='easyui-validatebox'><span id="autoEnrollNumSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>招收特长生数：</label> 
						<input id="specialtyEnrollNum" name="UndergraAdmiInfo.specialtyEnrollNum" 
							 class='easyui-validatebox'><span id="specialtyEnrollNumSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>招收本省学生数：</label> 
						<input id="inProviEnrollNum" name="UndergraAdmiInfo.inProviEnrollNum" 
							 class='easyui-validatebox'><span id="inProviEnrollNumSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>新办专业招生数：</label> 
						<input id="newMajEnrollNum" name="UndergraAdmiInfo.newMajEnrollNum" 
							 class='easyui-validatebox'><span id="newMajEnrollNumSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>填写时间：</label> 
						<input class="easyui-datebox"  id="time" type="text" 
						name="UndergraAdmiInfo.time"  editable="false" />
						<span id="timeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td style="valign:left" colspan="3"><label>备注：</label>
					<textarea id="note" name="UndergraAdmiInfo.note" style="resize:none" cols="50" rows="10"></textarea>
					<span id="noteSpan"></span>
				</td>
			</tr>
		</table>
		</form>
	</div>
	

	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="singleImport()">保存</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
</body>

</html>
