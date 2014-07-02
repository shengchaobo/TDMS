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

<title>专科招生信息补充表（招就处）</title>
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
	<script type="text/javascript" src="js/table6/T624.js"></script>
</head>


<body>
	<table id="commomData" title="专科招生信息补充表" class="easyui-datagrid" url="pages/T624/loadData" style="height: auto;">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber">序号</th>
				<th field="teaUnit">教学单位</th>
				<th field="unitId">单位号</th>
				<th field="majorName">专业名称</th>
				<th field="majorId">专业代码</th>
				<th field="majorFieldName">专业方向名称</th>
				<th data-options="field:'isCurrentYearAdmis'" formatter="formatBoolean">当年是否招生（含方向）</th>
				<th field="planAdmisNum">当年计划招生数</th>
				<th field="actualAdmisNum">实际录取数</th>
				<th field="actualRegisterNum">实际报到数</th>
				<th field="genHignSchNum">普通高中起点</th>
				<th field="secondVocationNum">中职起点</th>
				<th field="otherNum">其他</th>
				<th field="time" formatter="formattime">时间</th>
				<th field="note">备注</th>
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
		 	专业名称: <input id="searchItem" class="easyui-box" style="width:80px"/>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="reloadgrid()">查询</a>
		</div>
	</div>
	
	<!--审核通过数据-->
	<table id="verfiedData"  class="easyui-datagrid"  url=""  style="height: auto;" >
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber">序号</th>
				<th field="teaUnit">教学单位</th>
				<th field="unitId">单位号</th>
				<th field="majorName">专业名称</th>
				<th field="majorId">专业代码</th>
				<th field="majorFieldName">专业方向名称</th>
				<th data-options="field:'isCurrentYearAdmis'" formatter="formatBoolean">当年是否招生（含方向）</th>
				<th field="planAdmisNum">当年计划招生数</th>
				<th field="actualAdmisNum">实际录取数</th>
				<th field="actualRegisterNum">实际报到数</th>
				<th field="genHignSchNum">普通高中起点</th>
				<th field="secondVocationNum">中职起点</th>
				<th field="otherNum">其他</th>
				<th field="time" formatter="formattime">时间</th>
				<th field="note">备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar2" style="float: right;">
		<a href='pages/T624/dataExport?excelName=<%=URLEncoder.encode("表6-2-4专科招生信息补充表（招就处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="">高级检索</a>
	</div>
	
	
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<div class="ftitle" id="title1">专科招生信息批量导入</div>
		<div class="fitem" id="item1">
			<form id="batchForm" method="post" enctype="multipart/form-data">
				<label>批量上传：</label> 
				<input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox" size="48" style="height: 24px;"
					validType="fileType['xls']" required="true" invalidMessage="请选择Excel格式的文件" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">导入</a>
				<a href='pages/T624/downloadModel?saveFile=<%=URLEncoder.encode("表6-2-4专科招生信息补充表（招就处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
		</div>
	
		<div></div>
		<div class="ftitle">专科招生信息逐条导入</div>
		<form id="addItemForm" method="post">
		<table>
			<tr>
				<td>
					<div class="fitem">
						<label>教学单位：</label> 
						<input id="seqNumber" type="hidden" name="T624_bean.seqNumber">	
						<input id="TeaUnit" type="hidden" name="T624_bean.teaUnit">										
						<input id="unitId" type="text" name="T624_bean.unitId" class='easyui-combobox'
							data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment',listHeight:'auto',editable:false,
							onSelect:function(){
							 	 document.getElementById('TeaUnit').value=$(this).combobox('getText') ;
							 }">
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>专业名称：</label> 
						<input id="majorName" type="hidden" name="T624_bean.majorName">
						<input id="majorId" type="text" name="T624_bean.majorId" class='easyui-combobox'
							data-options="valueField:'majorNum',textField:'majorName',url:'pages/DiMajorTwo/loadDiMajorTwo',listHeight:'auto',editable:false,
							onSelect:function(){
							 	 document.getElementById('majorName').value=$(this).combobox('getText') ;
							 }"></input>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div class="fitem">
						<label>专业方向名称：</label> 
						<input id="majorFieldName" name="T624_bean.majorFieldName" 
							 class='easyui-validatebox'><span id="majorFieldNameSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>当年是否招生（含方向）：</label> 
						<select class='easyui-combobox'  id="isCurrentYearAdmis" name="T624_bean.isCurrentYearAdmis"  panelHeight="auto" editable="false" >
							<option value="true">是</option>
							<option value="false">否</option>
						</select>	<span id="isCurrentYearAdmisSpan"></span>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div class="fitem">
						<label>当年计划招生数：</label> 
						<input id="planAdmisNum" name="T624_bean.planAdmisNum" 
							 class='easyui-validatebox'><span id="planAdmisNumSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>实际录取数：</label> 
						<input id="actualAdmisNum" name="T624_bean.actualAdmisNum" 
							 class='easyui-validatebox'><span id="actualAdmisNumSpan"></span>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div class="fitem">
						<label>实际报到数：</label> 
						<input id="actualRegisterNum" name="T624_bean.actualRegisterNum" 
							 class='easyui-validatebox'><span id="actualRegisterNumSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>普通高中起点：</label> 
						<input id="genHignSchNum" name="T624_bean.genHignSchNum" 
							 class='easyui-validatebox'><span id="genHignSchNumSpan"></span>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div class="fitem">
						<label>中职起点：</label> 
						<input id="secondVocationNum" name="T624_bean.secondVocationNum" 
							 class='easyui-validatebox'><span id="secondVocationNumSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>其他：</label> 
						<input id="otherNum" name="T624_bean.otherNum" 
							 class='easyui-validatebox'><span id="otherNumSpan"></span>
					</div>
				</td>

			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>时间：</label> 
						<input class="easyui-datebox"  id="time" type="text" 
						name="T624_bean.time"  editable="false" />
						<span id="timeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td style="valign:left"><label>备&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
					<textarea id="note" name="T624_bean.note" style="resize:none" cols="50" rows="10"></textarea>
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
