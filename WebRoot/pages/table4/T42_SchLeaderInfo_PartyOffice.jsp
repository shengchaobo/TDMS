<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>T42</title>
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
	<script type="text/javascript" src="js/table4/T42.js"></script>
</head>

<body style="height: 100%'" >
	<table  id="unverfiedData"  class="easyui-datagrid"  url="pages/T42/loadLeaderInfo"  style="height: auto"  >
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th data-options="field:'teaId'">
						教工号
				</th>
				<th data-options="field:'name'">
						姓名
				</th>
		     </tr>		     
		</thead>
		<thead>
				<tr>					
					<th data-options="field:'duty'">
						职务
					</th>
					<th data-options="field:'gender'">
						性别
					</th>
					<th data-options="field:'birthday'"  formatter="formattime">
						出生日期
					</th>
					<th data-options="field:'joinSchTime'" formatter="formattime">
						入校时间
					</th>
					<th data-options="field:'education'">
						学历
					</th>	
					<th data-options="field:'topDegree'">
						最高学位
					</th>
					<th data-options="field:'majTechTitle'">
						专业技术职称
					</th>		
					<th data-options="field:'forCharge'">
						校内分管工作
					</th>	
					<th data-options="field:'resume'">
						学习和工作简历
					</th>																														
					<th data-options="field:'note'">
						备注
					</th>
				</tr>
			</thead>
	</table>
	<div id="toolbar" style="height:auto">
		<div style="float: left;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newMajorTea()">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editCourse()">编辑</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyCourse()">删除</a>
		</div>
		 <div style="float: right;">
		 	序号: <input class="easyui-box" style="width:80px"/>
			日期 起始: <input class="easyui-datebox" style="width:80px"/>
			结束: <input class="easyui-datebox" style="width:80px"/>
			<a href="#" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		</div>
	</div>
	
	<table id="verfiedData"  class="easyui-datagrid"  url=""  style="height: auto;" >
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th data-options="field:'teaId'">
						教工号
				</th>
				<th data-options="field:'name'">
						姓名
				</th>
		     </tr>		     
		</thead>
		<thead>
				<tr>					
					<th data-options="field:'duty'">
						职务
					</th>
					<th data-options="field:'gender'">
						性别
					</th>
					<th data-options="field:'birthday'"  formatter="formattime">
						出生日期
					</th>
					<th data-options="field:'joinSchTime'"  formatter="formattime">
						入校时间
					</th>
					<th data-options="field:'education'">
						学历
					</th>	
					<th data-options="field:'topDegree'">
						最高学位
					</th>
					<th data-options="field:'majTechTitle'">
						专业技术职称
					</th>		
					<th data-options="field:'forCharge'">
						校内分管工作
					</th>	
					<th data-options="field:'resume'">
						学习和工作简历
					</th>																														
					<th data-options="field:'note'">
						备注
					</th>
				</tr>
			</thead>
	</table>
	<div id="toolbar2" style="float: right;">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-download" plain="true" onclick="">数据导出</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="">高级检索</a>
	</div>
	
	<!--添加弹出框-->
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="ftitle">校领导信息模板导入</h3>
		<div class="fitem">
		  <form method="post">
				<input type="file" name="fileToUpload" id="fileToUpload" class="easyui-validatebox" size="48" style="height: 24px;"
					validType="fileType['xls']" required="true" invalidMessage="请选择Excel格式的文件" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href="table5/downloadCSBaseLibraries" class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
		</div>	
		<hr style="width: 100%; height: 5px; color: blue;"></hr>	
	   <h3 class="ftitle">校领导信息逐条导入</h3>
	   <form id="addForm" method="post">
		<table>
			<tr>
				<td style="valign:left" colspan="3">
					<div class="fitem">
						<label>请选择导入时间：</label> 
						<input class="easyui-datebox"  id="time" type="text" 
						name="T412_bean.time"  required="true"  editable="false" />
						<span id="timeSpan"></span>
					</div>
				</td>
			</tr>	
			<tr>
				<td>
				<div class="fitem">
				<label>教工号：</label> 
				<input type="hidden" name="T42_bean.teaId" id="teaId"/>
				<input id="name" type="text" name="T42_bean.name" class='easyui-combobox' 
							data-options="valueField:'teaName',textField:'teaId',url:'pages/T411/loadT411',listHeight:'auto',editable:true,
							onSelect:function(){
							 	 document.getElementById('teaId').value=$(this).combobox('getText') ;
							 }">
				<span id="teaIdSpan"></span>
				</div>
				</td>
				<td class="empty"></td>
				<td><div class="fitem">
				<label>职务：</label> 
				<input id="duty" type="text" name="T42_bean.duty"
				class="easyui-validatebox" ><span id="dutySpan"></span>
				</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>性别：</label> 
						<select class='easyui-combobox'  id="gender" name="T42_bean.gender"  panelHeight="auto" editable="false" >
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
						name="T42_bean.birthday"  editable="false" />
						<span id="birthdaySpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>最高学位：</label> 
						<input class='easyui-combobox' id="topDegree" name="T42_bean.topDegree" 
							data-options="valueField:'indexId',textField:'degree',url:'pages/DiDegree/loadDiDegree',listHeight:'auto',editable:false" panelHeight="auto">
						<span id="topDegreeSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>学历：</label> 
						<input class='easyui-combobox' id="education" name="T42_bean.education" 
							data-options="valueField:'indexId',textField:'education',url:'pages/DiEducation/loadDiEducation',listHeight:'auto',editable:false" panelHeight="auto">
						<span id="educationSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>入校时间：</label> 
						<input class="easyui-datebox"  id="joinSchTime" type="text" 
						name="T42_bean.joinSchTime"  editable="false" />
						<span id="joinSchTimeSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>专业技术职称：</label> 
						<input class='easyui-combobox'  id="majorTechTitle" name="T42_bean.majTechTitle" 
							data-options="valueField:'indexId',textField:'titleLevel',url:'pages/DiTitleLevel/loadDiTitleLevel',listHeight:'auto',editable:false" panelHeight="auto">
						<span id="MajorTechTitleSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td style="valign:left" colspan="3">
				<label>校内分管工作：</label> 
				<textarea id="forCharge" name="T42_bean.forCharge" style="resize:none" cols="50" rows="5"></textarea>
					<span id="forChargeSpan"></span>				
				</td>
				</tr>
			<tr>
				<td style="valign:left" colspan="3">
				<label>学习和工作简历：</label> 
					<textarea id="resume" name="T42_bean.resume" style="resize:none" cols="50" rows="10">
				    </textarea>
				    <span id="resumeSpan"></span>
				</td>	
			</tr>								
			<tr>
				<td style="valign:left" colspan="3"><label>备注：</label>
					<textarea id="note" name="T42_bean.note" style="resize:none" cols="50" rows="10"></textarea>
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
