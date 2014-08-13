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

<title>T285</title>
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
	<script type="text/javascript" src="js/table2/T285.js"></script>

</head>
<body style="overflow-y:scroll">
	<table id="showData"  style="height: auto">		
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"  rowspan="2">选取</th>
				<th  data-options="field:'seqNumber'" rowspan="2" hidden="true">编号</th>
				<th data-options="field:'teaUnit'" rowspan="2">
				教学单位
				</th>
				<th data-options="field:'unitID'" rowspan="2">
	       		单位号
				</th>
				<th  colspan="2">1.教学及科研仪器设备数(台数）</th>
				<th  colspan="3">2.教学及科研仪器设备值(万元)</th>
			</tr>
			<tr>
				<th data-options="field:'sumEquNum'">
				设备总量
				</th>
				<th data-options="field:'aboveTenEquNum'">
	       		单价10万元以上设备台数
				</th>
				<th data-options="field:'sumEquAsset'">
				设备值总量
				</th>
				<th data-options="field:'newAddAsset'">
				当年新增设备值
				</th>
				<th data-options="field:'aboveTenEquAsset'">
	       		单价10万元以上设备值
				</th>
			</tr>			
	</thead>
	</table>
								 
	<div id="toolbar" style="height:auto">
		<div style="float: left;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">编辑</a> 
			<a href="javascript:void(0)" id="export" class="easyui-linkbutton" iconCls="icon-download" plain="true"  onclick="exports()">数据导出</a>
		</div>
	 	 <form  id="exportForm"  style="float: right;"  method="post" >
			显示： <select class="easyui-combobox" id="cbYearContrast" panelHeight="auto" style="width:80px; padding-top:5px; margin-top:10px;"  editable=false ></select>
	 	</form>	
	</div>
	
	<!--添加弹出框-->
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
	   <form id="addForm" method="post">
		<table>	
			<tr>
				<td >
				<input type="hidden" name="T285_bean.seqNumber" id="seqNumber"/>
				<div class="fitem">
				<label>教学单位：</label> 
				<input id="teaUnit" type="text" name="T285_bean.teaUnit"
				class="easyui-validatebox" ><span id="teaUnitSpan"></span>
				</div>
				</td>
				<td class="empty"></td>
				<td>
				<div class="fitem">
				<label>单位号：</label> 
				<input id="unitID" type="text" name="T285_bean.unitID"
				class="easyui-validatebox" ><span id="unitIDSpan"></span>
				</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>设备总量：</label> 
						<input class="easyui-numberbox"   id="sumEquNum"  type="text" 
						name="T285_bean.sumEquNum"  editable="false" />
						<span id="sumEquNumSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>单价10万元以上设备台数：</label> 
						<input class="easyui-numberbox"   id="aboveTenEquNum"  type="text"
						name="T285_bean.aboveTenEquNum"  editable="false" />
						<span id="aboveTenEquNumSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>设备值总量：</label> 
						<input class="easyui-numberbox"   id="sumEquAsset"  type="text"  min=0  precision=2
						name="T285_bean.sumEquAsset"  editable="false" />
						<span id="sumEquAssetSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>当年新增设备值：</label> 
						<input class="easyui-numberbox"   id="newAddAsset"  type="text"  min=0  precision=2
						name="T285_bean.newAddAsset"  editable="false" />
						<span id="newAddAssetSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div class="fitem">
						<label>单价10万元以上设备值：</label> 
						<input class="easyui-numberbox"   id="aboveTenEquAsset"  type="text"  min=0  precision=2
						name="T285_bean.aboveTenEquAsset"  editable="false" />
						<span id="aboveTenEquAssetSpan"></span>
					</div>
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
