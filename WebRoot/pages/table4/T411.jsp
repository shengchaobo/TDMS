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

<title>T411_TeaBasicInfo_Per</title>
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
	<script type="text/javascript" src="js/table4/T411.js"></script>
</head>

<body>
	<table id="commomData"  title="全校教职工个人信息" class="easyui-datagrid"  url ="pages/T411/loadTeaInfo"  style="height: auto;">
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th  data-options="field:'teaId'" >教工号</th>
				<th  data-options="field:'teaName'" >姓名</th>				
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
					<th data-options="field:'admisTime'" formatter="formattime">
						入校时间
					</th>
					<th data-options="field:'teaState'">
						任职状态
					</th>
					<th data-options="field:'beginWorkTime'" formatter="formattime">
						参加本科教学工作时间
					</th>
					<th data-options="field:'idcode'">
						身份代码
					</th>
					<th data-options="field:'fromOffice'">
						所属部门
					</th>
					<th data-options="field:'officeID'">
						所属部门单位号
					</th>
					<th data-options="field:'fromUnit'">
						所属教学单位
					</th>
					<th data-options="field:'unitId'">
						所属教学单位号
					</th>
					<th data-options="field:'fromTeaResOffice'">
						所属教研室
					</th>
					<th data-options="field:'teaResOfficeID'">
						所属教研室单位号
					</th>
					<th data-options="field:'education'">
						学历
					</th>
					<th data-options="field:'topDegree'">
						最高学位
					</th>
					<th  data-options="field:'graSch'">
						毕业学校
					</th>
					<th data-options="field:'major'">
						专业
					</th>
					<th data-options="field:'source'">
						学缘
					</th>
					<th data-options="field:'adminLevel'">
						行政职务
					</th>
					<th data-options="field:'majTechTitle'">
						专业技术职称
					</th>
					<th data-options="field:'teaTitle'">
						教学系统职称
					</th>
					<th data-options="field:'notTeaTitle'">
						非教学系统职称
					</th>
					<th data-options="field:'subjectClass'">
						学科类别
					</th>
					<th data-options="field:'doubleTea'" formatter="formatBoolean">
						是否双师型教师
					</th>
					<th data-options="field:'industry'" formatter="formatBoolean">
						是否具有行业背景
					</th>
					<th data-options="field:'engineer'" formatter="formatBoolean">
						是否具有工程背景
					</th>
					<th data-options="field:'teaBase'" formatter="formatBoolean">
						是否列入师资库
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
			<a href='pages/T411/dataExport?excelName=<%=URLEncoder.encode("表4-1-1教师基本信息（人事处）","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 
		</div>
		<form method="post"  id="searchForm"   style="float: right;height: 24px;"  >
		 	教工号 :&nbsp;<input id="searchID"  name=" searchID"  class="easyui-box" style="height:24px" />
			<a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-search"  plain="true" onclick="reloadgrid ()">查询</a>
		</form>
	</div>
	
   <div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="title1"  >教师基本信息模板导入</h3>
		<div class="fitem"  id="item1">
		  <form method="post"  id="batchForm" enctype="multipart/form-data">
				<input type="file" name="uploadFile" id="fileToUpload" class="easyui-validatebox" size="48" style="height: 24px;" required="true" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href='pages/T411/downloadModel?saveFile=<%=URLEncoder.encode("表4-1-1教师基本信息（人事处）","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
		</div>	
		<hr></hr>	
	   <h3 class="title1" >教师基本信息逐条导入</h3>
	   <form id="addForm" method="post">
		<table>
			<tr><td><div class="fitem">
				<label>姓名：</label> 
				<input id="teaName" type="text" name="T411_bean.teaName"
				class="easyui-validatebox" ><span id="teaNameSpan"></span>
				</div>
			</td>
			<td class="empty"></td>
				<td><div class="fitem">
				<label>教工号：</label> 
				<input id="teaId" type="text" name="T411_bean.teaId"
				class="easyui-validatebox" ><span id="teaIdSpan"></span>
				</div></td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>性别：</label> 
						<select class='easyui-combobox'  id="gender" name="T411_bean.gender"  panelHeight="auto" editable="false" >
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
						name="T411_bean.birthday"  editable="false" />
						<span id="birthdaySpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>任职状态：</label> 
						<select class='easyui-combobox'  id="teaState" name="T411_bean.teaState"  panelHeight="auto" editable="false" >
							<option value="在职">在职</option>
							<option value="当年离职">当年离职</option>
						</select>	
							<span id="teaStateSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>入校时间：</label> 
						<input class="easyui-datebox"  id="admisTime" type="text" 
						name="T411_bean.admisTime"  editable="false" />
						<span id="admisTimeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>身份代码：</label> 
						<input id="idcode" type="text" name="T411_bean.idcode"  panelHeight="auto"
							 class='easyui-combobox' data-options="valueField:'indexId',textField:'identiType',url:'pages/DiIdentiType/loadDiIdentiType',listHeight:'auto',editable:false"/>
						<span id="idcodeSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>参加本科教学工作时间：</label> 
						<input class="easyui-datebox"  id="beginWorkTime" type="text" 
						name="T411_bean.beginWorkTime"  editable="false" />
						<span id="beginWorkTimeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>所属部门：</label> 
						<input type="hidden" name="T411_bean.fromOffice" id="fromOffice"/>
						<input id="officeID" type="text" name="T411_bean.officeID" class='easyui-combobox'
							data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment',listHeight:'auto',editable:false,
							onSelect:function(){
							    document.getElementById('fromOffice').value=$(this).combobox('getText') ;
							 }">
						 <span id="officeIDSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>所属教学单位：</label> 
						<input type="hidden" name="T411_bean.fromUnit" id="fromUnit"/>
						<input id="unitId" type="text" name="T411_bean.unitId" class='easyui-combobox'
							data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment',listHeight:'auto',editable:false,
							onSelect:function(){
							 	 document.getElementById('fromUnit').value=$(this).combobox('getText') ;
							 }">
					</div>
				</td>
			</tr>
			<tr>
			  <td>
				<div class="fitem">
					<label>所属教研室：</label> 
						<input type="hidden" name="T411_bean.fromTeaResOffice" id="fromTeaResOffice"/>
						<input id="teaResOfficeID" type="text" name="T411_bean.teaResOfficeID" class='easyui-combobox'
							data-options="valueField:'unitId',textField:'researchName',url:'pages/DiResearchRoom/loadDiResearchRoom',listHeight:'auto',editable:false,
							onSelect:function(){
							 	$('#fromTeaResOffice').val($(this).combobox('getText')) ;
							 }">
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>学历：</label> 
						<input class='easyui-combobox' id="education" name="T411_bean.education" 
							data-options="valueField:'indexId',textField:'education',url:'pages/DiEducation/loadDiEducation',listHeight:'auto',editable:false" panelHeight="auto">
						<span id="educationSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>最高学位：</label> 
						<input class='easyui-combobox' id="topDegree" name="T411_bean.topDegree" 
							data-options="valueField:'indexId',textField:'degree',url:'pages/DiDegree/loadDiDegree',listHeight:'auto',editable:false" panelHeight="auto">
						<span id="topDegreeSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
					<label>毕业学校：</label> 
					<input id="graSch" type="text" name="T411_bean.graSch"
					class="easyui-validatebox"><span id="graSchSpan"></span>
					</div>
				</td>
		  </tr>
		  <tr>
				<td>
					<div class="fitem">
						<label>专业：</label> 
						<input class="easyui-validatebox"  id="major" name="T411_bean.major" 
						/><span id="majorSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>学缘：</label> 
						<input class='easyui-combobox' id="source" name="T411_bean.source" 
							data-options="valueField:'indexId',textField:'source',url:'pages/DiSource/loadDiSource',listHeight:'auto',editable:false" panelHeight="auto">
						<span id="sourceSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>专业技术职称：</label> 
						<input class='easyui-combobox'  id="majTechTitle" name="T411_bean.majTechTitle" 
							data-options="valueField:'indexId',textField:'titleLevel',url:'pages/DiTitleLevel/loadDiTitleLevel',listHeight:'auto',editable:false" panelHeight="auto">
						<span id="majTechTitleSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
					<label>行政职务：</label> 
					<input id="adminLevel" type="text" name="T411_bean.adminLevel"
					class="easyui-validatebox"><span id="adminLevelSpan"></span>
					</div>
				</td>
		  </tr>
		  <tr>
				<td>
					<div class="fitem">
						<label>教学系列职称：</label> 
						<input class='easyui-combobox'  id="teaTitle" name="T411_bean.teaTitle" 
							data-options="valueField:'indexId',textField:'titleName',url:'pages/DiTitleName/loadDiTitleName',listHeight:'auto',editable:false" panelHeight="auto">
						<span id="teaTitleSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
					<label>非教学系列职称：</label> 
					<input id="notTeaTitle" type="text" name="T411_bean.notTeaTitle"
					class="easyui-validatebox"><span id="notTeaTitleSpan"></span>
					</div>
				</td>
		 </tr>
 		  <tr>
 				<td>
					<div class="fitem">
					<label>学科类别：</label> 
					<select class='easyui-combobox'  id="subjectClass" name="T411_bean.subjectClass"  panelHeight="auto" editable="false" >
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
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>是否双师型教师：</label> 
						<select class='easyui-combobox'  id="gender" name="T411_bean.doubleTea"  panelHeight="auto" editable="false" >
							<option value="true">是</option>
							<option value="false">否</option>
						</select>	
							<span id="doubleTeaSpan"></span>
					</div>
				</td>
		 </tr>
  		  <tr>
				<td>
					<div class="fitem">
						<label>是否具有行业背景：</label> 
						<select class='easyui-combobox'  id="gender" name="T411_bean.industry" panelHeight="auto" editable="false" >
							<option value="true">是</option>
							<option value="false">否</option>
						</select>	
							<span id="industryTeaSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>是否具有工程背景：</label> 
						<select class='easyui-combobox'  id="gender" name="T411_bean.engineer"  panelHeight="auto" editable="false" >
							<option value="true">是</option>
							<option value="false">否</option>
						</select>	
							<span id="engineerSpan"></span>
					</div>
				</td>
		 </tr>
			<tr>
				<td colspan="3">
					<div class="fitem">
						<label>是否具列入师资库：</label> 
						<select class='easyui-combobox'  id="gender" name="T411_bean.teaBase"  panelHeight="auto" editable="false" >
							<option value="true">是</option>
							<option value="false">否</option>
						</select>	
							<span id="teaBaseSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td style="valign:left" colspan="3"><label>备注：</label>
					<textarea id="note" name="T411_bean.note" style="resize:none" cols="50" rows="10"></textarea>
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
