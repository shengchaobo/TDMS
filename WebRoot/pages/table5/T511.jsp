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
    
    <title>My JSP 'T511.jsp' starting page</title>
    
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
    <script type="text/javascript" src="js/table5/T511.js"></script>
</head>
<body style="overflow-y:scroll">
	<table id="unverfiedData" title="待审核数据域审核未通过数据" class="easyui-datagrid" url="pages/T511/auditingData"
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="false" >
		<thead data-options="frozen:true">
		<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th  data-options="field:'seqNumber'" >编号</th>
		     </tr>
		</thead>
		<thead>
				<tr>					
					<th data-options="field:'CSName'">
						课程名称
					</th>
					<th data-options="field:'CSID'">
						课程编号
					</th>
					<th data-options="field:'CSUnit'">
						开课单位
					</th>
					<th data-options="field:'unitID'">
						单位号
					</th>
					<th data-options="field:'fromTeaResOffice'">
						所属教研室
					</th>
					<th data-options="field:'teaResOfficeID'">
						教研室号
					</th>
					<th data-options="field:'CSType'">
						课程类别
					</th>
					<th data-options="field:'CSNature'">
						课程性质
					</th>
					<th data-options="field:'state'">
						状态
					</th>
					<th data-options="field:'pubCSType'" >
						公选课类别
					</th>
					<th data-options="field:'note'">
						备注
					</th>
				</tr>
			</thead>
	</table>
	<div id="toolbar" style="height:auto">
		<div style="float: left;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newCourse()">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editCourse()">编辑</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteByIds()">删除</a>
		</div>
		 <div>
		  <form method="post" id="auditing"
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
	</div>
	<div id="toolbar2" style="float: right;">
		<a href="pages/T511/dataExport?excelName=<%=URLEncoder.encode("表5-1-1本科课程库","UTF-8")%>"  class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 
		
	</div>
	<table id="verfiedData" title="审核通过数据" class="easyui-datagrid" style="width:100%px;height:250px" url=""
		toolbar="#toolbar2" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="false">
		<thead data-options="frozen:true">
		<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th  data-options="field:'seqNumber'" >编号</th>
		     </tr>
		</thead>
		<thead>
			<tr>					
					<th data-options="field:'CSName'">
						课程名称
					</th>
					<th data-options="field:'CSID'">
						课程编号
					</th>
					<th data-options="field:'CSUnit'">
						开课单位
					</th>
					<th data-options="field:'unitID'">
						单位号
					</th>
					<th data-options="field:'fromTeaResOffice'">
						所属教研室
					</th>
					<th data-options="field:'teaResOfficeID'">
						教研室号
					</th>
					<th data-options="field:'CSType'">
						课程类别
					</th>
					<th data-options="field:'CSNature'">
						课程性质
					</th>
					<th data-options="field:'state'">
						状态
					</th>
					<th data-options="field:'pubCSType'" >
						公选课类别
					</th>
					<th data-options="field:'note'">
						备注
					</th>
				</tr>
			</thead>
	</table>
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="title1">本科课程库批量导入</h3>
		<div class="fitem" id="item1">
			<form method="post" id="batchForm" enctype="multipart/form-data">
			<select class="easyui-combobox"  id="cbYearContrast" name="selectYear"></select>
				<input type="file" name="uploadFile" id="fileToUpload" class="easyui-validatebox" size="48" style="height: 24px;" required="true" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href="pages/T511/downloadModel?saveFile=<%=URLEncoder.encode("表5-1-1本科课程库.xls","UTF-8")%>"  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
			<a href="123"></a>
		</div>	
		<hr></hr>	
			
		<h3 class="title1">本科课程库逐条导入</h3>
		
		
		
		<form id="t511Form" method="post">
			<table>
			<tr>
				<td>
				<input type="hidden" name="t511_Bean.SeqNumber"  id="seqNumber"/>
					<div class="fitem">
						<label>课程名称：</label> 
						<input id="CSName" type="text" name="t511_Bean.CSName"
							><span id="CSNameSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>课程编号：</label> 
						<input id="CSID" type="text" name="t511_Bean.CSID"
							><span id="CSIDSpan"></span>
					</div>
				</td>
			</tr>	
			
			<tr>
				<td>
			   <div class="fitem">
						<label>开课单位：</label> 
						<!-- 下边的onselect方法是为了后台既要教学单位名称，有需要教学单位编号，而我们只有一个下拉框包含了这两条信息 -->
						<input type="hidden" name="t511_Bean.CSUnit" id="CSUnit"/>
						<input id="UnitID" name="t511_Bean.UnitID" 
							  class='easyui-combobox' data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDIDepartmentAca',listHeight:'auto',editable:false,
							 onSelect:function(){
							 	document.getElementById('CSUnit').value=$(this).combobox('getText') ;
							 }">
						<span id="CSUnitSpan"></span>
					</div>
			     </td>
			     <td class="empty"></td>
			     <td>
					<div class="fitem">
						<label>所属教研室：</label> 
						<input type="hidden" name="t511_Bean.FromTeaResOffice" id="FromTeaResOffice"/>
						<input id="TeaResOfficeID" name="t511_Bean.TeaResOfficeID" class='easyui-combobox'
							data-options="valueField:'unitId',textField:'researchName',url:'pages/DiResearchRoom/loadDiResearchRoom',listHeight:'auto',editable:false,
							onSelect:function(){
							 	$('#FromTeaResOffice').val($(this).combobox('getText')) ;
							 }">
					</div>
				</td>
			</tr>
			<tr>
				<td>
			    <div class="fitem">
						<label>课程类别：</label> 
						<input class='easyui-combobox' id="CSType" name="t511_Bean.CSType" 
							data-options="valueField:'indexId',textField:'courseCategories',url:'pages/DiCourseCategories/loadDiCourseCategories',listHeight:'auto',editable:false">
						<span id="CSTypeSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>课程性质：</label> 
						<input class='easyui-combobox' id="CSNature" name="t511_Bean.CSNature"
							data-options="valueField:'indexId',textField:'courseChar',url:'pages/DiCourseChar/loadDiCourseChar',listHeight:'auto',editable:false">
						<span id="CSNatureSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
			<td>
					<div class="fitem">
						<label>状&nbsp;&nbsp;&nbsp;&nbsp;态：</label> 
						<select class='easyui-combobox' id="State" name="t511_Bean.State" panelHeight="auto" editable="false">
							<option value="启用">启用</option>
							<option value="停用">停用</option>
						</select>	
							<span id="StateSpan"></span>
					</div>
				</td>
			<td class="empty"></td>
			<td>
					<div class="fitem">
						<label>公选课类别：</label> 
						<select class='easyui-combobox' id="PubCSType" name="t511_Bean.PubCSType" panelHeight="auto" editable="false">
							<option value="理工类">理工类</option>
							<option value="人文社科类">人文社科类</option>
							<option value="体育保健类">体育保健类</option>
							<option value="无">无</option>
						</select>
						<span id="PubCSTypeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td style="valign:left" colspan="3"><label>备注：</label>
					<textarea id="Note" name="t511_Bean.Note" style="resize:none" cols="50" rows="10"></textarea>
					<span id="NoteSpan"></span>
				</td>
			</tr>			
		</table>
		</form>
	</div>
	
	<div id="dicDlg" class="easyui-dialog" style="width:500px;padding:10px 20px" closed="true">
		<div class="ftitle">高级检索</div>
		<div id="dicTables"  class="fitem">
		</div>
		<div id="dices"  class="fitem"></div>
	</div>
	 
	<div id="dlg-buttons">
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
