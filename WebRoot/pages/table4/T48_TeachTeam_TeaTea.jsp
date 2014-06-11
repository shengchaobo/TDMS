<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>T48</title>
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
	<script type="text/javascript" src="js/table4/T48.js"></script>
</head>

<body style="height: 100%'" >
	<table  id="unverfiedData"  class="easyui-datagrid"  url="pages/T48/loadTeamInfo"  style="height: auto"  >
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				 <th data-options="field:'teamName'">
						团队名称
				</th>
		     </tr>
		</thead>
		<thead>
				<tr>	
					<th data-options="field:'teamLevel'">
						团队级别
					</th>	
					<th data-options="field:'teaUnit'">
					 	 教学单位
					</th>
					<th data-options="field:'unitId'">
						教学单位号
					</th>
					<th data-options="field:'leader'">
						负责人
					</th>
					<th data-options="field:'teaId'">
						教工号
					</th>
					<th data-options="field:'groupNum'">
						其他团队成员人数
					</th>
					<th data-options="field:'groupInfo'">
						其他团队成员
					</th>
					<th data-options="field:'gainTime'"  formatter="formattime">
						获得时间
					</th>
					<th data-options="field:'appvlId'">
						批文号
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
				 <th data-options="field:'teamName'">
						团队名称
				</th>
				<th  data-options="field:'name'" >姓名</th>
				<th  data-options="field:'teaId'" >教工号</th>
		     </tr>
		</thead>
		<thead>
				<tr>	
					<th data-options="field:'teamLevel'">
						团队级别
					</th>	
					<th data-options="field:'teaUnit'">
					 	 教学单位
					</th>
					<th data-options="field:'unitId'">
						教学单位号
					</th>
					<th data-options="field:'leader'">
						负责人
					</th>
					<th data-options="field:'teaId'">
						教工号
					</th>
					<th data-options="field:'groupNum'">
						其他团队成员人数
					</th>
					<th data-options="field:'groupInfo'">
						其他团队成员
					</th>
					<th data-options="field:'gainTime'"  formatter="formattime">
						获得时间
					</th>
					<th data-options="field:'appvlId'">
						批文号
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
		<h3 class="ftitle">教学团队模板导入</h3>
		<div class="fitem">
		  <form method="post">
				<input type="file" name="fileToUpload" id="fileToUpload" class="easyui-validatebox" size="48" style="height: 24px;"
					validType="fileType['xls']" required="true" invalidMessage="请选择Excel格式的文件" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href="table5/downloadCSBaseLibraries" class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
		</div>	
		<hr style="width: 100%; height: 5px; color: blue;"></hr>	
	   <h3 class="ftitle">教学团队逐条导入</h3>
	   <form id="addForm" method="post">
		<table>
			<tr>
				<td style="valign:left" colspan="3">
					<div class="fitem">
						<label>请选择导入时间：</label> 
						<input class="easyui-datebox"  id="time" type="text" 
						name="T48_bean.time"  required="true"  editable="false" />
						<span id="timeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
 					<div class="fitem">
					<label>团队名称：</label> 
					<input id="teamName" type="text" name="T48_bean.teamName"
							class="easyui-validatebox" ><span id="teamNameSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>团队级别：</label> 
						<input class='easyui-combobox' id="teamLevel" name="T48_bean.teamLevel" 
							data-options="valueField:'indexId',textField:'awardLevel',url:'pages/DiAwardLevel/loadDiAwardLevel',listHeight:'auto',editable:false" panelHeight="auto">
						<span id="teamSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>所属教学单位：</label> 
						<input type="hidden" name="T48_bean.teaUnit" id="teaUnit"/>
						<input id="unitId" type="text" name="T48_bean.unitId" class='easyui-combobox' 
							data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment',listHeight:'auto',editable:true,
							onSelect:function(){
							    document.getElementById('teaUnit').value=$(this).combobox('getText') ;
							 }">
						 <span id="unitIdSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
				<div class="fitem">
				<label>负责人教工号：</label> 
				<input type="hidden" name="T48_bean.teaId" id="teaId"/>
				<input id="leader" type="text" name="T48_bean.leader" class='easyui-combobox' 
							data-options="valueField:'teaName',textField:'teaId',url:'pages/T411/loadT411',listHeight:'auto',editable:true,
							onSelect:function(){
							 	 document.getElementById('teaId').value=$(this).combobox('getText') ;
							 }">
				<span id="teaIdSpan"></span>
				</div></td>
			</tr>			
			<tr>
				<td >
					<div class="fitem">
						<label>获得时间：</label> 
						<input class="easyui-datebox"  id="gainTime" type="text" 
						name="T48_bean.gainTime"  editable="false" />
						<span id="gainTimeSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
 					<div class="fitem">
					<label>批文号：</label> 
					<input id="appvlId" type="text" name="T48_bean.appvlId"
							class="easyui-validatebox" ><span id="appvlIdSpan"></span>
					</div>
				</td>
			</tr>
 			<tr>
				<td colspan="3">
 					<div class="fitem">
					<label>其他团队成员人数：</label> 
					<input id="groupNum" type="text" name="T48_bean.groupNum"
							class="easyui-validatebox" ><span id="groupSpan"></span>
					</div>
				</td>
				</tr>
			<tr>
				<td colspan="3">
 					<div class="fitem">
					<label>其他团队成员：</label> 
					<textarea id="groupInfo" name="T48_bean.groupInfo" style="resize:none" cols="50" rows="5"></textarea>
					<span id="groupInfoSpan"></span>
					</div>
				</td>
			</tr>		
			<tr>
				<td style="valign:left" colspan="3"><label>备注：</label>
					<textarea id="note" name="T48_bean.note" style="resize:none" cols="50" rows="10"></textarea>
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
