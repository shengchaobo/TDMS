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

<title>My JSP 'table.jsp' starting page</title>
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
			width: 120px;
		}
	</style>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="js/commom.js"></script>
	<script type="text/javascript" src="js/table5/T512.js"></script>
</head>
<body style="overflow-y:scroll">
	<table id="unverfiedData" title="待审核数据域审核未通过数据" class="easyui-datagrid"  url="pages/SchResIns/auditingData"
		toolbar="#toolbar" >
		
		<thead>
			<tr>
				<th data-options="field:'SeqNumber',align:'center'" rowspan="2">
				序号
				</th>
				<th data-options="field:'Term',align:'center'" rowspan="2">
			          学期
				</th>
				<th data-options="field:'CSUnit',align:'center'" rowspan="2">
				开课单位
				</th>
				<th data-options="field:'UnitID',align:'center'" rowspan="2">
				单位号
				</th>
				<th data-options="field:'CSMajor',align:'center'" rowspan="2">
				上课专业名称
				</th>
				<th data-options="field:'CSMajorID',align:'center'" rowspan="2">
				上课专业代码
				</th>
				<th colspan="12">
			      1.本科课程情况   
				</th>
				<th colspan="8">
			      2.本科授课情况   
				</th>
				<th colspan="3">
			      3.使用教材   
				</th>
			</tr>
			<tr>
				<th data-options="field:'CSName',align:'center'">
				课程名称
				</th>
				<th data-options="field:'CSID',align:'center'">
				课程编号
				</th>
				<th data-options="field:'CSType',align:'center'">
				课程类别
				</th>
				<th data-options="field:'CSNature',align:'center' ">
				课程性质
				</th>
				<th data-options="field:'PubCSType',align:'center'">
				公选课类别
				</th>
				<th data-options="field:'IsDoubleCS',align:'center'">
			          是否双语授课
				</th>
				<th data-options="field:'Credit',align:'center'">
				学分
				</th>
				<th data-options="field:'SumCSHour',align:'center'">
				总学时
				</th>
				<th data-options="field:'TheoryCSHour',align:'center' ">
				理论学时
				</th>
				<th data-options="field:'PraCSHour',align:'center'">
				实践学时
				</th>
				<th data-options="field:'ExamWay',align:'center' ">
				考核方式
				</th>
				<th data-options="field:'PlanTime',align:'center'">
				实习、设计时间
				</th>
				<th data-options="field:'CSGrade',align:'center'">
				授课年级
				</th>
				<th data-options="field:'CSClass',align:'center'">
				授课班级
				</th>
				<th data-options="field:'ClassID',align:'center'">
				开课班号
				</th>
				<th data-options="field:'ClassInfo',align:'center' ">
				合班情况
				</th>
				<th data-options="field:'StuNum',align:'center'">
				学生人数
				</th>
				<th data-options="field:'CSTea',align:'center'">
			        任课教师
				</th>
				<th data-options="field:'IsAccordJob',align:'center'">
			         是否符合岗位资格
				</th>
				<th data-options="field:'TeaTitle',align:'center'">
			        教师职称
				</th>
				<th data-options="field:'BookUseInfo',align:'center'">
				使用情况
				</th>
				<th data-options="field:'IsPlanbook',align:'center'">
			         是否规划教材
				</th>
				<th data-options="field:'IsAwardbook',align:'center'">
				是否获奖教材
				</th>
			</tr>
			
			</thead>
					
		
	  
	</table>
	<div id="toolbar" style="height:auto">
		<div>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newObject()">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">编辑</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteByIds()">删除</a>
			<a href="pages/SchResIns/dataExport?excelName=表1-5-1校级以上科研机构（科研处）.xls" class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 
		 	<form id="auditing" method="post" style="float: right;height: 24px;">
			 	序号: <input id="seqNum" name="seqNum" class="easyui-numberbox" style="width:80px"/>
				日期 起始: <input id="startTime" name="startTime" class="easyui-datebox" style="width:80px"/>
				结束: <input id="endTime" name="endTime" class="easyui-datebox" style="width:80px"/>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="reloadgrid()">查询</a>
			</form>
		</div>
		 
	</div>
	<div id="toolbar2">
		<a href="pages/UndergraCSBaseTea/dataExport" class="easyui-linkbutton" iconCls="icon-download">数据导出</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="loadDic()">高级检索</a>
	</div>
	<table id="verfiedData" title="审核通过数据" class="easyui-datagrid" style="width:100%px;height:250px" url=""
		toolbar="#toolbar2" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="false">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="SeqNumber" width=10>序号</th>
				<th field="ResInsName" width=10>科研机构名称</th>
				<th field="ResInsID" width=10>单位号</th>
				<th field="Type" width=10>类别</th>
				<th field="BuildCondition" width=10>共建情况</th>
				<th field="BiOpen" width=10 >是否对本科生开放</th>
				<th field="OpenCondition" width=10 >对本科生开放情况（500字以内）</th>
				<th field="TeaUnit" width=10>所属教学单位</th>
				<th field="UnitID" width=10>教学单位号</th>
				<th field="BeginYear" width=10 fit="true">开设年份</th>
				<th field="HouseArea" width=10>专业科研用房面积（平方米）</th>
				<th field="Note" width=10>备注</th>
			</tr>
		</thead>
	</table>
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<div class="ftitle">校级以上科研机构批量导入</div>
		<div class="fitem">
			<form id="batchForm" method="post" enctype="multipart/form-data">
				<label>批量上传：</label> 
				<input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox"
					validType="fileType['xls']" required="true" invalidMessage="请选择Excel格式的文件" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">导入</a>
				<a href='pages/SchResIns/downloadModel?saveFile=<%=URLEncoder.encode("表1-5-1校级以上科研机构（科研处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
		</div>
		<div></div>
		<div class="ftitle">校级以上科研机构逐条导入</div>
		
		<form id="resInsForm" method="post">
		<table id="formTable">
			<tr>
				<td>
					<div class="fitem">
						<label>科研机构名称：</label> 
						<input id="seqNumber" type="hidden" name="t151Bean.SeqNumber" value="0"></input>
						<input type="hidden" name="t151Bean.ResInsName" id="ResInsName"/>
						<input id="ResInsID" type="text" name="t151Bean.ResInsID" class='easyui-combobox' 
							data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDIDepartmentSci' ,listHeight:'auto',editable:false,
							onSelect:function(){
							 	$('#ResInsName').val($(this).combobox('getText')) ;
							 }">
					    <span id="ResInsNameSpan"></span>
							
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>类别：</label> 
						<input id="Type"  name="t151Bean.Type" class='easyui-combobox'
						data-options="valueField:'indexId',textField:'researchType',url:'pages/DiResearchType/loadDiResearchType',listHeight:'auto',editable:false">
						<span id="TypeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>开设年份：</label> 
						 <input class="easyui-datebox" id="BeginYear" name="t151Bean.BeginYear" >
						 <span id="BeginYearSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>所属教学单位：</label> 
						<input type="hidden" name="t151Bean.TeaUnit" id="TeaUnit"/>
						<input id="UnitID" type="text" name="t151Bean.UnitID" class='easyui-combobox' 
							data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment' ,listHeight:'auto',editable:false,
							onSelect:function(){
							    $('#TeaUnit').val($(this).combobox('getText')) ;
							 }">
							 
					    <span id="TeaUnitSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
			    <td>
					<div class="fitem">
						<label>共建情况：</label> 
						<select class='easyui-combobox' id='BuildCondition' name='t151Bean.BuildCondition'>
						   <option value="true">是</option>
						   <option value="false">否</option> 
						</select>
						<span id="BuildConditionSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>是否对本科生开放：</label> 
						<select class='easyui-combobox' id='BiOpen' name='t151Bean.BiOpen'>
						   <option value="true">是</option>
						   <option value="false">否</option> 
						</select>
						 <span id="BiOpenSpan"></span>
					</div>
				</td>
			</tr>
			
			<tr>
				<td>
					<div class="fitem">
						<label>专业科研用房面积（平方米）：</label> 
						<input id="HouseArea" type="text" name="t151Bean.HouseArea" 
						class="easyui-numberbox"  data-options="min:0,precision:2" required="true">
						   <span id="HouseAreaSpan"></span>
					</div>
				</td>
			</tr>
			
		    <tr>
				<td >
				    <div class="fitem">
						<label>对本科生开放情况（500字以内）：</label> 
						<br/>
						<textarea id="OpenCondition" name="t151Bean.OpenCondition" style="resize:none" cols="50" rows="10"></textarea>
						<span id="OpenConditionSpan"></span>
						</div>
				</td>
			</tr>
			<tr>
			
				<td >
				   <div class="fitem">
					    <label>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
			            <br/>
						<textarea id="Note" name="t151Bean.Note" style="resize:none" cols="50" rows="3"></textarea>
						<span id="NoteSpan"></span>
						</div>
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
		
</html>
