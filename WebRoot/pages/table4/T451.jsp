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

<title>T451</title>
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
	<script type="text/javascript" src="js/table4/T451.js"></script>
</head>

<body style="height: 100%'" >
	<table  id="unverfiedData"  class="easyui-datagrid"  url="pages/T451/loadOrgInfo"  style="height: auto"  >
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th  data-options="field:'seqNumber'" >编号</th>
				<th data-options="field:'orgName'">
						机构名称
				</th>
				<th data-options="field:'unitId'">
						机构单位号
				</th>
		     </tr>
		</thead>
		<thead>
				<tr>					
					<th data-options="field:'orgType'">
						机构类型
					</th>
					<th data-options="field:'trainTimes'">
						培训次数
					</th>
					<th data-options="field:'trainPerTimes'">
						培训人数
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
				<th data-options="field:'orgName'">
						机构名称
				</th>
				<th data-options="field:'unitId'">
						机构单位号
				</th>
		     </tr>
		</thead>
		<thead>
				<tr>					
					<th data-options="field:'orgType'">
						机构类型
					</th>
					<th data-options="field:'trainTimes'">
						培训次数
					</th>
					<th data-options="field:'trainPerTimes'">
						培训人数
					</th>
					<th data-options="field:'note'">
						备注
					</th>
				</tr>
			</thead>
	</table>
	<div id="toolbar2" style="float: right;">
		<a href='pages/T451/dataExport?excelName=<%=URLEncoder.encode("表4-5-1教师教学发展机构.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 		
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="">高级检索</a>
	</div>
	
	<!--添加弹出框-->
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="ftitle" id="title1">教师教学发展机构模板导入</h3>
		<div class="fitem" id="item1">
		  <form method="post"  id="batchForm" enctype="multipart/form-data">
				<input type="file" name="uploadFile" id="fileToUpload" class="easyui-validatebox" size="48" style="height: 24px;" required="true" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href='pages/T451/downloadModel?saveFile=<%=URLEncoder.encode("表4-5-1教师教学发展机构.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
		</div>	
		<hr style="width: 100%; height: 5px; color: blue;"></hr>	
	   <h3 class="ftitle">教师教学发展机构逐条导入</h3>
	   <form id="addForm" method="post">
		<table><!--
			<tr>
				<td style="valign:left" colspan="3">
					<div class="fitem">
						<label>请选择导入时间：</label> 
						<input class="easyui-datebox"  id="time" type="text" 
						name="T451_bean.time"  required="true"  editable="false" />
						<span id="timeSpan"></span>
					</div>
				</td>
			</tr>
 			--><tr>
				<td>
					<input type="hidden" name="T451_bean.seqNumber" id="seqNumber"/>
 					<div class="fitem">
					<label>机构名称：</label> 
					<input id="orgName" type="text" name="T451_bean.orgName"
							class="easyui-validatebox" ><span id="orgNameSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
 					<div class="fitem">
					<label>机构单位号：</label> 
					<input id="unitId" type="text" name="T451_bean.unitId"
							class="easyui-validatebox" ><span id="unitIdSpan"></span>
					</div>
				</td>
			</tr>
 			<tr>
				<td>
 					<div class="fitem">
					<label>培训次数：</label> 
					<input id="trainTimes" type="text" name="T451_bean.trainTimes"
							class="easyui-validatebox" ><span id="trainTimesSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
 					<div class="fitem">
					<label>培训人次：</label> 
					<input id="trainPerTimes" type="text" name="T451_bean.trainPerTimes"
							class="easyui-validatebox" ><span id="trainPerTimesSpan"></span>
					</div>
				</td>
			</tr>
 			<tr>
				<td colspan="3">
					<div class="fitem">
						<label>机构类型：</label> 
						<select class='easyui-combobox'  id="orgType" name="T451_bean.orgType"  panelHeight="auto" editable="false" >
							<option value="教师培训">教师培训</option>
							<option value="教学咨询">教学咨询</option>
							<option value="教学改革研究">教学改革研究</option>
							<option value="教学质量评估">教学质量评估</option>
							<option value="提供优质教学资源">提供优质教学资源</option>
							<option value="其他">其他</option>
						</select>	
							<span id="orgTypeSpan"></span>
					</div>
				</td>
			</tr>	
			<tr>
				<td style="valign:left" colspan="3"><label>备注：</label>
					<textarea id="note" name="T451_bean.note" style="resize:none" cols="50" rows="10"></textarea>
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
