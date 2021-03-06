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

<title>T413</title>
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
	<script type="text/javascript" src="js/table4/T413.js"></script>
</head>

<body>
	<table id="commomData"  title="外聘教职工个人信息" class="easyui-datagrid"  url ="pages/T413/loadTeaInfo"  style="height: auto;">
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th  data-options="field:'teaId'" >教工号</th>
				<th  data-options="field:'name'" >姓名</th>		
		     </tr>
		</thead>
		<thead>
				<tr>
					<th data-options="field:'gender'">
						性别
					</th>
					<th data-options="field:'birthday'" formatter="formattime">
						出生年月
					</th>
					<th data-options="field:'hireBeginTime'" formatter="formattime">
						聘任时间
					</th>
					<th data-options="field:'teaState'">
						任职状态
					</th>
					<th data-options="field:'hireTimeLen'">
						聘期（个月）
					</th>
					<th data-options="field:'unitName'">
					      教学单位
					</th>
					<th data-options="field:'unitId'">
						教学单位号
					</th>
					<th data-options="field:'education'">
						学历
					</th>
					<th data-options="field:'topDegree'">
						最高学位
					</th>
					<th data-options="field:'techTitle'">
						专业技术职称
					</th>
					<th data-options="field:'subjectClass'">
						学科类别
					</th>
					<th data-options="field:'workUnitType'">
						工作单位类型
					</th>
					<th data-options="field:'tutorType'">
						导师类别
					</th>
					<th data-options="field:'region'">
						地区
					</th>
					<th data-options="field:'note'">
						备注
					</th>
				</tr>
			</thead>
		</table>
	<div id="toolbar" style="height:auto">
		<div style="float: left;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newTeacher()">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">编辑</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteByIds()">删除</a>
			<a href='pages/T413/dataExport?excelName=<%=URLEncoder.encode("表4-1-3外聘教师基本信息（教学单位-人事处）","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 
		</div>
		<form method="post"  id="searchForm"   style="float: right;height: 24px;"  >
				<table id="test" width="280">
					<tr>
						<td>
							教工号:
						</td>
						<td>
							<input id="searchID"  name=" searchID"  class="easyui-box" style="height:24px" />
						</td>
						<td>
							<a href="javascript:void(0)" class="easyui-linkbutton"
								iconCls="icon-search" plain="true" onclick=	reloadgrid();>查询</a>
						</td>
					</tr>
				</table>
		</form>
	</div>
	
   <div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="title1">外聘教师基本信息模板导入</h3>
		<div class="fitem"  id="item1">
		  <form method="post"  id="batchForm" enctype="multipart/form-data">
				<input type="file" name="uploadFile" id="fileToUpload" class="easyui-validatebox" size="48" style="height: 24px;" required="true" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href='pages/T413/downloadModel?saveFile=<%=URLEncoder.encode("表4-1-3外聘教师基本信息","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
		</div>	
		<hr ></hr>	
	   <h3 class="title1">外聘教师基本信息逐条导入</h3>
	   <form id="addForm" method="post">
		<table>
			<tr>
			<td>
			<div class="fitem">
				<label>姓名：</label> 
				<input id="name" type="text" name="T413_bean.name"
				class="easyui-validatebox" ><span id="nameSpan"></span>
				</div>
			</td>
			<td class="empty"></td>
				<td><div class="fitem">
				<label>教工号：</label> 
				<input id="teaId" type="text" name="T413_bean.teaId"
				class="easyui-validatebox" ><span id="teaIdSpan"></span>
				</div>
			</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>性别：</label> 
						<select class='easyui-combobox'  id="gender" name="T413_bean.gender"  panelHeight="auto" editable="false" >
							<option value="男">男</option>
							<option value="女">女</option>
						</select>	
							<span id="genderSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>出生日期：</label> 
						<input class="easyui-datebox"  id="birthday" type="text" 
						name="T413_bean.birthday"  editable="false" />
						<span id="birthdaySpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>任职状态：</label> 
						<select class='easyui-combobox'  id="teaState" name="T413_bean.teaState"  panelHeight="auto" editable="false" >
							<option value="在职">在职</option>
							<option value="当年离职">当年离职</option>
						</select>	
							<span id="teaStateSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>聘任时间：</label> 
						<input class="easyui-datebox"  id="hireBeginTime" type="text" 
						name="T413_bean.hireBeginTime"  editable="false" />
						<span id="hireBeginTimeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td><div class="fitem">
				<label>聘期（个月）：</label> 
				<input id="hireTimeLen" type="text" name="T413_bean.hireTimeLen"
				class="easyui-validatebox" ><span id="hireTimeLenSpan"></span>
				</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>教学单位：</label> 
						<input type="hidden" name="T413_bean.unitName" id="unitName"/>
						<input id="unitId" type="text" name="T413_bean.unitId" class='easyui-combobox'
							data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDIDepartmentAca',listHeight:'auto',editable:false,
							onSelect:function(){
							    document.getElementById('unitName').value=$(this).combobox('getText') ;
							 }">
						 <span id="officeIDSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>最高学位：</label> 
						<input class='easyui-combobox' id="topDegree" name="T413_bean.topDegree" 
							data-options="valueField:'indexId',textField:'degree',url:'pages/DiDegree/loadDiDegree',listHeight:'auto',editable:false" panelHeight="auto">
						<span id="topDegreeSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>学历：</label> 
						<input class='easyui-combobox' id="education" name="T413_bean.education" 
							data-options="valueField:'indexId',textField:'education',url:'pages/DiEducation/loadDiEducation',listHeight:'auto',editable:false" panelHeight="auto">
						<span id="educationSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>专业技术职称：</label> 
						<input class='easyui-combobox'  id="techTitle" name="T413_bean.techTitle" 
							data-options="valueField:'indexId',textField:'titleLevel',url:'pages/DiTitleLevel/loadDiTitleLevel',listHeight:'auto',editable:false" panelHeight="auto">
						<span id="TechTitleSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
					<label>学科类别：</label> 
					<select class='easyui-combobox'  id="subjectClass" name="T413_bean.subjectClass"  panelHeight="auto" editable="false" >
							<option value="01哲学">01哲学</option>
							<option value="02经济学">02经济学</option>
							<option value="03法学">03法学</option>
							<option value="04教育学">04教育学</option>
							<option value="05文学">05文学</option>
							<option value="06历史学">06历史学</option>
							<option value="07理学">07理学</option>
							<option value="08工学">08工学</option>
							<option value="09农学">09农学</option>
							<option value="10医学">10医学</option>
							<option value="11军事学">11军事学</option>
							<option value="12管理学">12管理学</option>
							<option value="13艺术学">13艺术学</option>
						</select>	<span id="subjectClassSpan"></span>
					</div>
				</td>
		  </tr>
 			<tr>
				<td><div class="fitem">
				<label>工作单位类型：</label> 
				<input id="workUnitType" type="text" name="T413_bean.workUnitType"
				class="easyui-validatebox" ><span id="workUnitTypeSpan"></span>
				</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>导师类型：</label> 
						<input class='easyui-combobox'  id="tutorType" name="T413_bean.tutorType" 
							data-options="valueField:'indexId',textField:'tutorType',url:'pages/DiTutorType/loadDiTutorType',listHeight:'auto',editable:false" panelHeight="auto">
						<span id="TutorTypeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
	
				<td>
					<div class="fitem">
					<label>地区：</label> 
					<select class='easyui-combobox'  id="region" name="T413_bean.region"  panelHeight="auto" editable="false" >
							<option value="境内">境内</option>
							<option value="境外（国外及港澳台）">境外（国外及港澳台）</option>
						</select>	<span id="regionSpan"></span>
					</div>
				
				</td>
			</tr>
			<tr>
				<td style="valign:left" colspan="3"><label>备注：</label>
					<textarea id="note" name="T413_bean.note" style="resize:none" cols="50" rows="10"></textarea>
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
