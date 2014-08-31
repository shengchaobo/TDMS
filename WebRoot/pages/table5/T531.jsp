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

<title>T531</title>
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
	<script type="text/javascript" src="js/table5/T531.js"></script>
</head>

<body style="height: 100%'" >
	<table  id="unverfiedData"  class="easyui-datagrid"  url="pages/T531/auditingData"  style="height: auto"  >
	
		<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true">选取</th>
					<th  data-options="field:'seqNumber'" >编号</th>					
					<th data-options="field:'name'">
						名称
					</th>
					<th data-options="field:'type'">
						类型
					</th>
					<th data-options="field:'itemLevel'">
						级别
					</th>
					<th data-options="field:'buildTime'" formatter="formattime">
						设立时间
					</th>
					<th data-options="field:'teaUnit'">
						所属教学单位
					</th>
					<th data-options="field:'joinStuNum'">
						参与学生数（人）
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
		 <form method="post" id="searchForm"
				style="float: right; height: 24px;">
				<table id="test" width="520">
					<tr>
						<td>
							编号:
						</td>
						<td>
							<input id="seqNum" name="seqNum" class="easyui-box"
								style="width: 40px" />
						</td>
						<td>
							起始日期:
						</td>
						<td>
							<input id="startTime" name="startTime" class="easyui-datebox"
								style="width: 100px" />
						</td>
						<td>
							结束日期:
						</td>
						<td>
							<input id="endTime" name="endTime" class="easyui-datebox"
								style="width: 100px" />
						</td>
						<td>
							<a href="javascript:void(0)" class="easyui-linkbutton"
								iconCls="icon-search" plain="true" onclick=	reloadgrid();>查询</a>
						</td>
					</tr>
				</table>
			</form>
	</div>
	
	<table id="verfiedData"  class="easyui-datagrid"  url=""  style="height: auto;" >
		<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true">选取</th>
					<th  data-options="field:'seqNumber'" >编号</th>					
					<th data-options="field:'name'">
						名称
					</th>
					<th data-options="field:'type'">
						类型
					</th>
					<th data-options="field:'itemLevel'">
						级别
					</th>
					<th data-options="field:'buildTime'" formatter="formattime">
						设立时间
					</th>
					<th data-options="field:'teaUnit'">
						所属教学单位
					</th>
					<th data-options="field:'joinStuNum'">
						参与学生数（人）
					</th>
					<th data-options="field:'note'">
						备注
					</th>
				</tr>
			</thead>
	</table>
	<div id="toolbar2" style="float: right;">
		<a href='pages/T531/dataExport?excelName=<%=URLEncoder.encode("表5-3-1人才培养模式创新实验项目（教务处）","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="">高级检索</a>
	</div>
	
	<!--添加弹出框-->
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3  class="title1">创新实验项目批量导入</h3>
		<div class="fitem" id="item1">
		  <form method="post"  id="batchForm" enctype="multipart/form-data">
		  		<select class="easyui-combobox"  id="cbYearContrast" name="selectYear" editable="false"></select>
				<input type="file" name="uploadFile" id="fileToUpload" class="easyui-validatebox" size="48" style="height: 24px;" required="true" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href='pages/T531/downloadModel?saveFile=<%=URLEncoder.encode("表5-3-1人才培养模式创新实验项目（教务处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
		</div>	
		<hr></hr>	
	   <h3  class="title1">创新实验项目逐条导入</h3>
	   <form id="addForm" method="post">
		<table>
			<tr>
				<td colspan="3">
					<div class="fitem">
						<label>名称：</label> 
						<input type="hidden" name="t531Bean.SeqNumber"  id="seqNumber" value="0"/>
						<input id="Name" type="text" name="t531Bean.Name" class="easyui-validatebox"   style="width:400px">
						 <span id="NameSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div class="fitem">
						<label>类型：</label> 
						<select class='easyui-combobox'  id="Type" name="t531Bean.Type" style="width:200px" panelHeight="auto" editable="false" >
							<option value="人才培养模式创新实验区">人才培养模式创新实验区</option>
							<option value="国家级教学基地">国家级教学基地</option>
							<option value="拔尖人才培养计划">拔尖人才培养计划</option>
							<option value="卓越人才培养计划">卓越人才培养计划</option>
						</select>
						 <span id="TypeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
			    <td>
			   	    <div class="fitem">
					<label>级别：</label> 
					<select class='easyui-combobox'  id="ItemLevel" name="t531Bean.ItemLevel" style="width:200px" panelHeight="auto" editable="false" >
							<option value="国际级">国际级</option>
							<option value="国家级">国家级</option>
							<option value="省级">省级</option>
							<option value="市级">市级</option>
							<option value="校级">校级</option>
						</select>
					<span id="ItemLevelSpan"></span>
					</div>
			    </td>
			   <td class="empty"></td>
			    <td>
			    	<div class="fitem">
						<label>获准时间：</label> 
						 <input class="easyui-datebox" id="buildTime" name="t531Bean.buildTime" editable = "false">
						 <span id="buildTimeSpan"></span>
					</div>
			    </td>
			</tr>
			<tr>
			   <td>
					<div class="fitem">
					<label>所属教学单位：</label>
					<input id="TeaUnit"  name="t531Bean.TeaUnit" class='easyui-combobox'
						data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDIDepartmentAca',listHeight:'auto',editable:false">
					<span id="TeaUnitSpan"></span>
					</div>
			   </td>
			   <td class="empty"></td>
			   <td>
			   	<div class="fitem">
			   	<label>参与学生数（个）：</label>
			   	<input id="JoinStuNum" name="t531Bean.JoinStuNum" type="text" class="easyui-validatebox">
			   	<span id="JoinStuNumSpan"></span>
			   	</div>
			   </td>
			</tr>
			<tr>
				<td style="valign:left" colspan="3"><label>备注：</label>
					<textarea id="Note" name="t531Bean.Note" style="resize:none" cols="50" rows="10"></textarea>
					<span id="NoteSpan"></span>
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
