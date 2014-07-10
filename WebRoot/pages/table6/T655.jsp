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

<title>学习成果—英语四六级、计算机等级考试</title>
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
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="js/commom.js"></script>
	<script type="text/javascript" src="js/table6/T655.js"></script>
</head>


<body>
	<table id="commomData" title="待审核数据域审核未通过数据" class="easyui-datagrid" url="pages/T655/loadData" style="height: auto;">
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
				<th field="CET4PassRate">1.英语四级考试累计通过率（%）</th>
				<th field="CET6PassRate">2.英语六级考试累计通过率（%）</th>
				<th field="jiangxiNCREPassRate">3.江西省高校计算机等级考试累计通过率（%）</th>			
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
				<th field="CET4PassRate">1.英语四级考试累计通过率（%）</th>
				<th field="CET6PassRate">2.英语六级考试累计通过率（%）</th>
				<th field="jiangxiNCREPassRate">3.江西省高校计算机等级考试累计通过率（%）</th>			
				<th field="note">备注</th>
				<th field="time" formatter="formattime">时间</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar2" style="float: right;">
		<a href='pages/T655/dataExport?excelName=<%=URLEncoder.encode("表6-5-5学习成果—英语四六级、计算机等级考试.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="">高级检索</a>
	</div>
	
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="title1">英语四六级、计算机等级考试通过率批量导入</h3>
		<div class="fitem" id="item1">
			<form id="batchForm" method="post" enctype="multipart/form-data">
				<select class="easyui-combobox"  id="cbYearContrast" name="selectYear" editable="false"></select>
				<input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox" size="48" style="height: 24px;"
					validType="fileType['xls']" required="true" invalidMessage="请选择Excel格式的文件" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">导入</a>
				<a href='pages/T655/downloadModel?saveFile=<%=URLEncoder.encode("表6-5-5学习成果—英语四六级、计算机等级考试.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
		</div>
	<hr></hr>
		<div></div>
		<h3 class="title1">英语四六级、计算机等级考试通过率逐条导入</h3>
		<form id="addItemForm" method="post">
		<table>
			<tr>
				<td>
					<div class="fitem">
						<label>教学单位：</label> 
						<input id="seqNumber" type="hidden" name="T655_bean.seqNumber">	
						<input id="teaUnit" type="hidden" name="T655_bean.teaUnit">										
						<input id="unitId" type="text" name="T655_bean.unitId" class='easyui-combobox'
							data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment',listHeight:'auto',editable:false,
							onSelect:function(){
							 	 document.getElementById('teaUnit').value=$(this).combobox('getText') ;
							 }">
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>英语四级考试累计通过率（%）：</label> 
						<input id="CET4PassRate" name="T655_bean.CET4PassRate" class='easyui-validatebox'>
						<span id="CET4PassRateSpan"></span>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div class="fitem">
						<label>英语六级考试累计通过率（%）：</label> 
						<input id="CET6PassRate" name="T655_bean.CET6PassRate" class='easyui-validatebox'><span id="CET6PassRateSpan" ></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>江西省高校计算机等级考试累计通过率（%）：</label> 
						<input id="jiangxiNCREPassRate" name="T655_bean.jiangxiNCREPassRate" class='easyui-validatebox'><span id="jiangxiNCREPassRateSpan"></span>
					</div>
				</td>
			</tr>
			
			<tr>
				<td>
					<div class="fitem">
						<label>时间：</label> 
						<input class="easyui-datebox"  id="time" type="text" 
						name="T655_bean.time"  editable="false" />
						<span id="timeSpan"></span>
					</div>
				</td>
				
			</tr>
			<tr>
				<td style="valign:left" colspan="3"><label>备注：</label>
					<textarea id="note" name="T655_bean.note" style="resize:none" cols="50" rows="10"></textarea>
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

	<script type="text/javascript">
    	var currentYear = new Date().getFullYear();
    	var select = document.getElementById("cbYearContrast");
    	for (var i = 0; i <= 10; i++) {
        var theOption = document.createElement("option");
        	theOption.innerHTML = currentYear-i + "年";
        	theOption.value = currentYear-i;
        	select.appendChild(theOption);
    	}
	</script>

</html>
