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

<title>双学位情况汇总表（教务处）</title>
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
	<script type="text/javascript" src="js/table6/T672.js"></script>
</head>


<body>
	<table id="commomData" title="双学位情况汇总表（教务处）" class="easyui-datagrid" url="pages/T672/loadData" style="height: auto;">

		<thead data-options="frozen:true">
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber">序号</th>
				<th field="stuName">学生姓名</th>
				<th field="stuId">学号</th>
			</tr>
		</thead>	
		<thead>
			<tr>
				<th field="fromTeaUnit">所在教学单位</th>
				<th field="unitId">单位号</th>			
				<th field="fromMaj">所在专业</th>		
				<th field="majId">所在专业代码</th>		
				<th field="fromClass">所在班级</th>

				<th field="dualDegreeFromTeaUnit">双学位所在教学单位</th>
				<th field="dualDegreeUnitId">单位号</th>			
				<th field="dualDegreeMaj">双学位专业</th>		
				<th field="dualDegreeId">双学位专业代码</th>		
				<th field="beginTime" formatter="formattime">起始时间</th>
				<th field="graduateTime" formatter="formattime">预计毕业时间</th>
				
				<th field="note">备注</th>
				<th field="time" formatter="formattime">填写时间</th>
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
		 	学生姓名: <input id="searchItem" class="easyui-box" style="width:80px"/>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="reloadgrid()">查询</a>
		</div>
	</div>
	
		<!--审核通过数据-->
	<table id="verfiedData"  class="easyui-datagrid"  url=""  style="height: auto;" >
		<thead data-options="frozen:true">
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber">序号</th>
				<th field="stuName">学生姓名</th>
				<th field="stuId">学号</th>
			</tr>
		</thead>	
		<thead>
			<tr>
				<th field="fromTeaUnit">所在教学单位</th>
				<th field="unitId">单位号</th>			
				<th field="fromMaj">所在专业</th>		
				<th field="majId">所在专业代码</th>		
				<th field="fromClass">所在班级</th>

				<th field="dualDegreeFromTeaUnit">双学位所在教学单位</th>
				<th field="dualDegreeUnitId">单位号</th>			
				<th field="dualDegreeMaj">双学位专业</th>		
				<th field="dualDegreeId">双学位专业代码</th>			
				<th field="beginTime" formatter="formattime">起始时间</th>
				<th field="graduateTime" formatter="formattime">预计毕业时间</th>
				
				<th field="note">备注</th>
				<th field="time" formatter="formattime">填写时间</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar2" style="float: right;">
		<a href='pages/T672/dataExport?excelName=<%=URLEncoder.encode("表6-7-1双学位情况汇总表（教务处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="">高级检索</a>
	</div>
	
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<div class="ftitle" id="title1">双学位情况批量导入</div>
		<div class="fitem" id="item1">
			<form id="batchForm" method="post" enctype="multipart/form-data">
				<label>批量上传：</label> 
				<input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox" size="48" style="height: 24px;"
					validType="fileType['xls']" required="true" invalidMessage="请选择Excel格式的文件" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">导入</a>
				<a href='pages/T672/downloadModel?saveFile=<%=URLEncoder.encode("表6-7-1双学位情况汇总表（教务处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
		</div>
	
		<div></div>
		<div class="ftitle">双学位情况逐条导入</div>
		<form id="addItemForm" method="post">
		<table>
			<tr>
				<td>
					<div class="fitem">
						<label>学生姓名：</label> 
						<input id="seqNumber" type="hidden" name="T672_bean.seqNumber">	
						<input id="stuName" name="T672_bean.stuName" class='easyui-validatebox'>
						<span id="stuNameSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>学号：</label> 
						<input id="stuId" name="T672_bean.stuId" class='easyui-validatebox'>
						<span id="stuIdSpan"></span>
					</div>
				</td>
			</tr>
			 
			<tr>
				<td>
					<div class="fitem">
						<label>所在教学单位：</label> 
						<input id="fromTeaUnit" type="hidden" name="T672_bean.fromTeaUnit">	
						<input id="unitId" type="text" name="T672_bean.unitId" class='easyui-combobox'
							data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment',listHeight:'auto',editable:false,
							onSelect:function(){
							 	 document.getElementById('fromTeaUnit').value=$(this).combobox('getText') ;
							 }">
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>所在专业：</label> 
						<input id="fromMaj" type="hidden" name="T672_bean.fromMaj">	
						<input id="majId" type="text" name="T672_bean.majId" class='easyui-combobox'
							data-options="valueField:'majorNum',textField:'majorName',url:'pages/DiMajorTwo/loadDiMajorTwo',listHeight:'auto',editable:false,
							onSelect:function(){
							 	 document.getElementById('fromMaj').value=$(this).combobox('getText') ;
							 }">
					</div>
				</td>
			</tr>		
			
			<tr>
				<td>			
					<div class="fitem">
						<label>所在班级：</label> 
						<input id="fromClass" name="T672_bean.fromClass" 
							 class='easyui-validatebox' ><span id="fromClassSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>辅修专业所在教学单位：</label> 
						<input id="dualDegreeFromTeaUnit" type="hidden" name="T672_bean.dualDegreeFromTeaUnit">	
						<input id="dualDegreeUnitId" type="text" name="T672_bean.dualDegreeUnitId" class='easyui-combobox'
							data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment',listHeight:'auto',editable:false,
							onSelect:function(){
							 	 document.getElementById('dualDegreeFromTeaUnit').value=$(this).combobox('getText') ;
							 }">
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div class="fitem">
						<label>辅修专业：</label> 
						<input id="dualDegreeMaj" type="hidden" name="T672_bean.dualDegreeMaj">	
						<input id="dualDegreeId" type="text" name="T672_bean.dualDegreeId" class='easyui-combobox'
							data-options="valueField:'majorNum',textField:'majorName',url:'pages/DiMajorTwo/loadDiMajorTwo',listHeight:'auto',editable:false,
							onSelect:function(){
							 	 document.getElementById('dualDegreeMaj').value=$(this).combobox('getText') ;
							 }">
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>起始时间：</label> 
						<input class="easyui-datebox"  id="beginTime" type="text" 
						name="T672_bean.beginTime"  editable="false" />
						<span id="beginTimeSpan"></span>
					</div>
				</td>
			</tr>
			
			<tr>
				<td>
					<div class="fitem">
						<label>预计毕业时间：</label> 
						<input class="easyui-datebox"  id="graduateTime" type="text" 
						name="T672_bean.graduateTime"  editable="false" />
						<span id="graduateTimeSpan"></span>
					</div>
				</td>
					<td>
					<div class="fitem">
						<label>填写时间：</label> 
						<input class="easyui-datebox"  id="time" type="text" 
						name="T672_bean.time"  editable="false" />
						<span id="timeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td style="valign:left"><label>备注：</label>
					<textarea id="note" name="T672_bean.note" style="resize:none" cols="50" rows="10"></textarea>
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