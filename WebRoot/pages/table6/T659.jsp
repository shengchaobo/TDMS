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

<title>学习成果—本科生交流情况</title>
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
	<script type="text/javascript" src="js/table6/T659.js"></script>
</head>


<body>
	<table id="commomData" title="学习成果—本科生交流情况" class="easyui-datagrid" url="pages/T659/loadData" style="height: auto;">
		<thead data-options="frozen:true">
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber">序号</th>
				<th field="teaUnit">教学单位</th>
				<th field="unitId">单位号</th>
			</tr>
		</thead>	
		<thead>
			<tr>
				<th field="exchangeStuSum">交流学生总数</th>
				<th field="fromSchToOverseas">本校到境外</th>			
				<th field="fromSchToDomestic">本校到境内</th>		
				<th field="fromDomesticToSch">境内到本校</th>		
				<th field="fromOverseasToSch">境外到本校</th>

				<th field="fillUnitID">填写单位</th>	
				<th field="note">备注</th>
				<th field="time" formatter="formattime">时间</th>
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
		 	教学单位: <input id="searchItem" class="easyui-box" style="width:80px"/>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="reloadgrid()">查询</a>
		</div>
	</div>
	
		<!--审核通过数据-->
	<table id="verfiedData"  class="easyui-datagrid"  url=""  style="height: auto;" >
		<thead data-options="frozen:true">
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber">序号</th>
				<th field="teaUnit">教学单位</th>
				<th field="unitId">单位号</th>
			</tr>
		</thead>	
		<thead>
			<tr>
				<th field="exchangeStuSum">交流学生总数</th>
				<th field="fromSchToOverseas">本校到境外</th>			
				<th field="fromSchToDomestic">本校到境内</th>		
				<th field="fromDomesticToSch">境内到本校</th>		
				<th field="fromOverseasToSch">境外到本校</th>

				<th field="fillUnitID">填写单位</th>	
				<th field="note">备注</th>
				<th field="time" formatter="formattime">时间</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar2" style="float: right;">
		<a href='pages/T659/dataExport?excelName=<%=URLEncoder.encode("表6-5-9学习成果—本科生交流情况（教学单位-国际交流与合作处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="">高级检索</a>
	</div>
	
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<div class="ftitle" id="title1">本科生交流情况批量导入</div>
		<div class="fitem" id="item1">
			<form id="batchForm" method="post" enctype="multipart/form-data">
				<label>批量上传：</label> 
				<input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox" size="48" style="height: 24px;"
					validType="fileType['xls']" required="true" invalidMessage="请选择Excel格式的文件" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">导入</a>
				<a href='pages/T659/downloadModel?saveFile=<%=URLEncoder.encode("表6-5-9学习成果—本科生交流情况（教学单位-国际交流与合作处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
		</div>
	
		<div></div>
		<div class="ftitle">本科生交流情况逐条导入</div>
		<form id="addItemForm" method="post">
		<table>
			<tr>
				<td>
					<div class="fitem">
						<label>教学单位：</label> 
						<input id="seqNumber" type="hidden" name="T659_bean.seqNumber">	
						<input id="teaUnit" type="hidden" name="T659_bean.teaUnit">										
						<input id="unitId" type="text" name="T659_bean.unitId" class='easyui-combobox'
							data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment',listHeight:'auto',editable:false,
							onSelect:function(){
							 	 document.getElementById('teaUnit').value=$(this).combobox('getText') ;
							 }">
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>交流学生总数：</label> 
						<input id="exchangeStuSum" name="T659_bean.exchangeStuSum" class='easyui-validatebox'>
						<span id="exchangeStuSumSpan"></span>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div class="fitem">
						<label>本校到境外：</label> 
						<input id="fromSchToOverseas" name="T659_bean.fromSchToOverseas" class='easyui-validatebox'><span id="fromSchToOverseasSpan" ></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>本校到境内：</label> 
						<input id="fromSchToDomestic" name="T659_bean.fromSchToDomestic" class='easyui-validatebox' type="text" editable="false"><span id="fromSchToDomesticSpan"></span>
					</div>
				</td>
			</tr>		
			<tr>
				<td>			
					<div class="fitem">
						<label>境内到本校：</label> 
						<input id="fromDomesticToSch" name="T659_bean.fromDomesticToSch" 
							 class='easyui-validatebox' ><span id="fromDomesticToSchSpan"></span>
					</div>
				</td>
				
				<td>			
					<div class="fitem">
						<label>境外到本校：</label> 
						<input id="fromOverseasToSch" name="T659_bean.fromOverseasToSch" 
							 class='easyui-validatebox' ><span id="fromOverseasToSchSpan"></span>
					</div>
				</td>
			</tr>
			
			<tr>
				<td>			
					<div class="fitem">
						<label>填写单位：</label> 
						<input id="fillUnitID" name="T659_bean.fillUnitID" 
							 class='easyui-validatebox'><span id="fillUnitIDSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>填写时间：</label> 
						<input class="easyui-datebox"  id="time" type="text" 
						name="T659_bean.time"  editable="false" />
						<span id="timeSpan"></span>
					</div>
				</td>
			</tr>

			<tr>
				<td style="valign:left"><label>备注：</label>
					<textarea id="note" name="T659_bean.note" style="resize:none" cols="50" rows="10"></textarea>
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