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

<title>T441</title>
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
	<style type="text/css">
	     label {
	    width: 10em;
	    float: left;
	}
	.empty{
		width: 4em;
	}
	</style>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="js/commom.js"></script>
	<script type="text/javascript" src="js/table4/T441.js"></script>
</head>

<body style="height: 100%'" >
	<table  id="unverfiedData"  class="easyui-datagrid"  url="pages/T441/loadLeaderInfo"  style="height: auto"  >
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th  data-options="field:'seqNumber'" >编号</th>
		     </tr>
		</thead>
		<thead>
				<tr>					
					<th data-options="field:'fromTeaUnit'">
						所属教学单位
					</th>
					<th data-options="field:'teaUnitID'">
						教学单位号
					</th>
					<th data-options="field:'majorName'">
						专业名称
					</th>
					<th data-options="field:'majorId'">
						专业代码
					</th>
					<th data-options="field:'majorLeaderName'">
						专业带头人姓名
					</th>
					 <th data-options="field:'teaId'">
						教工号
					</th>
					<th data-options="field:'note'">
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
		<form method="post"  id="searchForm"   style="float: right;height: 24px;"  >
		 	编号: <input  id="seqNum"   name="seqNum"  class="easyui-box" style="width:80px"/>
			起始日期: <input id ="startTime"  name ="startTime"   class="easyui-datebox" style="width:80px"/>
			结束日期: <input id="endTime"  name="endTime" class="easyui-datebox" style="width:80px"/>
			<a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-search"  plain="true" onclick="reloadgrid()">查询</a>
		</form>
	</div>
	
	<table id="verfiedData"  class="easyui-datagrid"  url=""  style="height: auto;" >
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th  data-options="field:'seqNumber'" >编号</th>
		     </tr>
		</thead>
		<thead>
				<tr>					
					<th data-options="field:'fromTeaUnit'">
						所属教学单位
					</th>
					<th data-options="field:'teaUnitID'">
						教学单位号
					</th>
					<th data-options="field:'majorName'">
						专业名称
					</th>
					<th data-options="field:'majorId'">
						专业代码
					</th>
					<th data-options="field:'majorLeaderName'">
						专业带头人姓名
					</th>
					 <th data-options="field:'teaId'">
						教工号
					</th>
					<th data-options="field:'note'">
						备注
					</th>
				</tr>
			</thead>
	</table>
	<div id="toolbar2" style="float: right;">
		<a href='pages/T441/dataExport?excelName=<%=URLEncoder.encode("表4-4-1专业带头人.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="">高级检索</a>
	</div>
	
	<!--添加弹出框-->
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="ftitle" id="title1">专业带头人模板导入</h3>
		<div class="fitem" id="item1">
		  <form method="post"  id="batchForm" enctype="multipart/form-data">
		  		<select class="easyui-combobox"  id="cbYearContrast" name="selectYear"></select>
				<input type="file" name="uploadFile" id="fileToUpload" class="easyui-validatebox" size="48" style="height: 24px;" required="true" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href='pages/T441/downloadModel?saveFile=<%=URLEncoder.encode("表4-4-1专业带头人.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
		</div>		
		<hr style="width: 100%; height: 5px; color: blue;"></hr>	
	   <h3 class="ftitle">专业带头人逐条导入</h3>
	   <form id="addForm" method="post">
		<table><!--
			<tr>
				<td style="valign:left" colspan="3">
					<div class="fitem">
						<label>请选择导入时间：</label> 
						<input class="easyui-datebox"  id="time" type="text" 
						name="T441_bean.time"  required="true"  editable="false" />
						<span id="timeSpan"></span>
					</div>
				</td>
			</tr>	
			--><tr>
				<td>
					<div class="fitem">
						<input type="hidden" name="T441_bean.SeqNumber"  id="seqNumber"/>
						<label>所属教学单位：</label> 
						<input type="hidden" name="T441_bean.fromTeaUnit" id="fromTeaUnit"/>
						<input id="teaUnitID" type="text" name="T441_bean.teaUnitID" class='easyui-combobox' 
							data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment',listHeight:'auto',editable:true,
							onSelect:function(){
							    document.getElementById('fromTeaUnit').value=$(this).combobox('getText') ;
							 }">
						 <span id="teaUnitIDSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>所属专业：</label> 
						<input type="hidden" name="T441_bean.majorName" id="majorName"/>
						<input id="majorId" type="text" name="T441_bean.majorId" class='easyui-combobox' 
							data-options="valueField:'majorNum',textField:'majorName',url:'pages/DiMajorTwo/loadDiMajorTwo',listHeight:'auto',editable:true,
							onSelect:function(){
							 	 document.getElementById('MajorName').value=$(this).combobox('getText') ;
							 }">
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
				<div class="fitem">
				<label>教工号：</label> 
				<input type="hidden" name="T441_bean.teaId" id="teaId"/>
				<input id="majorLeaderName" type="text" name="T441_bean.majorLeaderName" class='easyui-combobox' 
							data-options="valueField:'teaName',textField:'teaId',url:'pages/T411/loadT411',listHeight:'auto',editable:true,
							onSelect:function(){
							 	 document.getElementById('teaId').value=$(this).combobox('getText') ;
							 }">
				<span id="teaIdSpan"></span>
				</div></td>
			</tr>
			<tr>
				<td style="valign:left" colspan="3"><label>备注：</label>
					<textarea id="note" name="T441_bean.note" style="resize:none" cols="50" rows="10"></textarea>
					<span id="noteSpan"></span>
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
</html>
