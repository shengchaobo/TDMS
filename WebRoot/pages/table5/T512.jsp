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

<title>T512_CSInfo_TeaTea</title>
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
	<script type="text/javascript" src="js/table5/T512.js"></script>
</head>
<body style="overflow-y:scroll">
	<table id="unverfiedData" title="待审核数据域审核未通过数据" class="easyui-datagrid"  url="pages/T512/auditingData"  style="height: auto">
		
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th  data-options="field:'seqNumber'" >编号</th>
		     </tr>
		</thead>
		
		<thead>
			<tr>
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
	
	<div id="toolbar2" style="float:left;">
		<a href='pages/T512/dataExport?excelName=<%=URLEncoder.encode("表5-1-1开课、授课情况（教学单位-教务处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="">高级检索</a>
	</div>
	
	<!--添加弹出框-->
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="ftitle" id="title1">表5-1-1开课、授课情况（教学单位-教务处）模板导入</h3>
		<div class="fitem" id="item1">
		  <form method="post"  id="batchForm" enctype="multipart/form-data">
				<input type="file" name="uploadFile" id="fileToUpload" class="easyui-validatebox" size="48" style="height: 24px;" required="true" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href='pages/T412/downloadModel?saveFile=<%=URLEncoder.encode("表5-1-1开课、授课情况（教学单位-教务处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
		</div>	
		<hr style="width: 100%; height: 5px; color: blue;"></hr>	
	   <h3 class="ftitle">开课、授课逐条导入</h3>
	    <form id="addForm" method="post">
		<table>
			<tr>
				<td><div class="fitem">
				<label>学期：</label> 
				<input id="Term" type="text" name="T512bean.teaName"
				class="easyui-validatebox" ><span id="teaNameSpan"></span>
				</div>
				</td>
			<td class="empty"></td>
				<td><div class="fitem">
				<label>开课单位：</label> 
				<input type="hidden" name="T512Bean.CSUnit" id="CSUnit"/>
						<input id="UnitID" type="text" name="T512Bean.UnitID" class='easyui-combobox' 
							data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment' ,listHeight:'auto',editable:false,
							onSelect:function(){
							    $('CSUnit').val($(this).combobox('getText')) ;
							 }">
				<span id="teaIdSpan"></span>
				</div></td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
					<label>上课专业名称：</label> 
					<input type="hidden" name="T512Bean.CSMajorName" id="CSMajorName"/>
					<input id="CSMajorID" type="text" name="T512Bean.CSMajorID class='easyui-combobox' 
							data-options="valueField:'majorNum',textField:'majorName',url:'pages/DiMajorTwo/loadDiMajorTwo' ,listHeight:'auto',editable:false,
							onSelect:function(){
							    $('CSMajorName').val($(this).combobox('getText')) ;
							 }">
					<span id="teaIdSpan"></span>
					</div>
				</td>
			</tr>
			<tr><td>1.本科课程情况</td></tr>
						<tr><td><hr style="border:1 dashed blue" size=1></td></tr>
			
			<tr>
				<td>
					<div class="fitem">
					<label>课程名称：</label> 
					<input type="hidden" name="T512Bean.CSName" id="CSName"/>
						<input id="CSID" type="text" name="T512Bean.CSID" class='easyui-combobox' 
							data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment' ,listHeight:'auto',editable:false,
							onSelect:function(){
							    $('#TeaUnit').val($(this).combobox('getText')) ;
							 }">
					<span id="teaIdSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
					<td><div class="fitem">
						<label>课程类别：</label> 
						<input id="CSType" type="text" name="T512bean.CSType" class='easyui-combobox'  
						data-options="valueField:'indexId',textField:'courseCategories',url:'pages/DiCourseCategories/loadDiCourseCategories',listHeight:'auto',editable:false">
						<span id="teaNameSpan"></span>
					</div>
					</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>课程性质：</label> 
						<input id="CSNature" type="text" name="T512bean.CSNature"  panelHeight="auto"
							 class='easyui-combobox' data-options="valueField:'indexId',textField:'courseChar',url:'pages/DiCourseChar/loadDiCourseChar',listHeight:'auto',editable:false"/>
						<span id="idcodeSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>公共课类别：</label> 
						<input class="easyui-datebox"  id="PubCSType" type="text" 
						name="T512bean.PubCSType"  editable="false" />
						<span id="beginWorkTimeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>是否双语授课：</label> 
						<select class='easyui-combobox'  id="IsDoubleCS" name="T512bean.IsDoubleCS"  panelHeight="auto" editable="false" >
							<option value="true">是</option>
							<option value="false">否</option>
						</select>	
							<span id="doubleTeaSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>学分：</label> 
						<input id="Credit" name="T512bean.Credit" type="text" class="easyui-numberbox" data-options="min:0">
					</div>
				</td>
			</tr>
			<tr>
			  <td>
					<div class="fitem">
						<label>总学时：</label> 
						<input id="SumCSHour" name="T512bean.SumCSHour" type="text" class="easyui-numberbox" data-options="min:0">
					</div>
				</td>
				<td class="empty"></td>
				 <td>
					<div class="fitem">
						<label>理论学时：</label> 
						<input id="TheoryCSHour" name="T512bean.TheoryCSHour" type="text" class="easyui-numberbox" data-options="min:0">
					</div>
				</td>
			</tr>
			<tr>
				 <td>
					<div class="fitem">
						<label>实践学时：</label> 
						<input id="PraCSHour" name="T512bean.PraCSHour" type="text" class="easyui-numberbox" data-options="min:0">
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
					<label>考核方式：</label> 
					<select class='easyui-combobox'  id="ExamWay" name="T512bean.ExamWay"  panelHeight="auto" editable="false" >
							<option value="考试">考试</option>
							<option value="考查">考查</option>
					</select><span id="graSchSpan"></span>
					</div>
				</td>
		  </tr>
		  <tr>
				<td>
					<div class="fitem">
						<label>实习、设计时间：</label> 
						 <input class="easyui-datebox" id="PlanTime" name="T512bean.PlanTime" >
						 <span id="BeginYearSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
					<label>授课年级：</label> 
					<select class='easyui-combobox'  id="CSGrade" name="T512bean.CSGrade"  panelHeight="auto" editable="false" >
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
					</select><span id="graSchSpan"></span>
					</div>
				</td>
			</tr>
			<tr><td>2.本科授课情况</td></tr>
			<tr><td><hr style="border:1 dashed blue" size=1></td></tr>
			<tr>
				<td>
					<div class="fitem">
						<label>授课班级：</label> 
						<input class='easyui-combobox'  id="CSClass" name="T512bean.CSClass" 
							data-options="valueField:'indexId',textField:'titleLevel',url:'pages/DiTitleLevel/loadDiTitleLevel',listHeight:'auto',editable:false" panelHeight="auto">
						<span id="majTechTitleSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
					<label>合班情况：</label> 
					<input id="ClassInfo" type="text" name="T512bean.ClassInfo"
					class="easyui-validatebox"><span id="adminLevelSpan"></span>
					</div>
				</td>
		  </tr>
		  <tr>
				<td>
					<div class="fitem">
						<label>学生人数：</label> 
						<input class="easyui-datebox"  id="StuNum" name="T512bean.StuNum"  panelHeight="auto">
						<span id="teaTitleSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
					<label>任课老师：</label> 
					<input id="CSTea" type="text" name="T512bean.CSTea"
					class="easyui-validatebox"><span id="notTeaTitleSpan"></span>
					</div>
				</td>
		 </tr>
 		  <tr>
 				<td>
					<div class="fitem">
					<label>是否符合岗位资格：</label> 
					<select class='easyui-combobox'  id="IsAccordJob" name="T512bean.IsAccordJob"  panelHeight="auto" editable="false" >
							<option value="true">是</option>
							<option value="false">否</option>
					</select><span id="graSchSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>教师职称：</label> 
						<input id="TeaTitle" type="text" name="T512bean.TeaTitle" class="easyui-validatebox">	
							<span id="doubleTeaSpan"></span>
					</div>
				</td>
		 </tr>
		 <tr> <td>3.使用教材</td></tr>
					<tr><td><hr style="border:1 dashed blue" size=1></td></tr>
  		  <tr>
				<td>
					<div class="fitem">
					<label>使用情况：</label> 
					<select class='easyui-combobox'  id="BookUseInfo" name="T512bean.BookUseInfo"  panelHeight="auto" editable="false" >
							<option value="选用">选用</option>
							<option value="自编">自编</option>
					</select><span id="graSchSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
					<label>是否规划教材：</label> 
					<select class='easyui-combobox'  id="IsPlanbook" name="T512bean.IsPlanbook"  panelHeight="auto" editable="false" >
							<option value="true">是</option>
							<option value="false">否</option>
					</select><span id="graSchSpan"></span>
					</div>
				</td>
		 </tr>
			<tr>
				<td>
					<div class="fitem">
					<label>是否获奖教材：</label> 
					<select class='easyui-combobox'  id="IsAwardbook" name="T512bean.IsAwardbook"  panelHeight="auto" editable="false" >
							<option value="true">是</option>
							<option value="false">否</option>
					</select><span id="graSchSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td style="valign:left" colspan="3"><label>备注：</label>
					<textarea id="note" name="T512bean.note" style="resize:none" cols="50" rows="10"></textarea>
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
		
</html>
