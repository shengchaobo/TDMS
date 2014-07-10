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

<title>T532</title>
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
	<script type="text/javascript" src="js/table5/T532.js"></script>
</head>

<body style="height: 100%'" >
	<table  id="unverfiedData"  class="easyui-datagrid"  url="pages/T532/auditingData"  style="height: auto"  >
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th  data-options="field:'seqNumber'" >编号</th>
					<th data-options="field:'centerName'">
						中心名称
					</th>
		     </tr>
		</thead>
		<thead>
				<tr>					
				
					<th data-options="field:'fromSubject'">
						所属学科
					</th>
					<th data-options="field:'fromTeaUnit'">
						所属教学单位
					</th>
					<th data-options="field:'unitID'">
						单位号
					</th>
					<th data-options="field:'centerLeader'">
						中心主任
					</th>
					<th data-options="field:'teaID'">
						教工号
					</th>
					<th data-options="field:'teaTitle'">
						职称
					</th>
					<th data-options="field:'buildTime'" formatter="formattime">
						设立时间
					</th>
					<th data-options="field:'buildAppvlID'">
						建设批文号
					</th>
					<th data-options="field:'receptTime'" formatter="formattime">
						验收时间
					</th>
					<th data-options="field:'receptAppvlID'">
						验收批文号
					</th>
					<th data-options="field:'validTime'">
						有效期（年）
					</th>
					<th data-options="field:'fund'">
						经费(万元)
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
				<th data-options="field:'centerName'">
						中心名称
					</th>
				
		     </tr>
		</thead>
		<thead>
				<tr>					
					
					<th data-options="field:'fromSubject'">
						所属学科
					</th>
					<th data-options="field:'fromTeaUnit'">
						所属教学单位
					</th>
					<th data-options="field:'unitID'">
						单位号
					</th>
					<th data-options="field:'centerLeader'">
						中心主任
					</th>
					<th data-options="field:'teaID'">
						教工号
					</th>
					<th data-options="field:'teaTitle'">
						职称
					</th>
					<th data-options="field:'buildTime'">
						设立时间
					</th>
					<th data-options="field:'buildAppvlID'">
						建设批文号
					</th>
					<th data-options="field:'receptTime'">
						验收时间
					</th>
					<th data-options="field:'receptAppvlID'">
						验收批文号
					</th>
					<th data-options="field:'validTime'">
						有效期（年）
					</th>
					<th data-options="field:'fund'">
						经费(万元)
					</th>
					<th data-options="field:'note'">
						备注
					</th>
				</tr>
			</thead>
	</table>
	<div id="toolbar2" style="float: right;">
		<a href='pages/T532/dataExport?excelName=<%=URLEncoder.encode("表5-3-2实验教学示范中心（设备处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="">高级检索</a>
	</div>
	
	<!--添加弹出框-->
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3  class="title1">实验教学示范中心批量导入</h3>
		<div class="fitem" id="item1">
		  <form method="post"  id="batchForm" enctype="multipart/form-data">
		  		<select class="easyui-combobox"  id="cbYearContrast" name="selectYear" editable="false"></select>
				<input type="file" name="uploadFile" id="fileToUpload" class="easyui-validatebox" size="48" style="height: 24px;" required="true" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href='pages/T532/downloadModel?saveFile=<%=URLEncoder.encode("表5-3-2实验教学示范中心（设备处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
		</div>	
		<hr></hr>	
	   <h3  class="title1">实验教学示范中心逐条导入</h3>
	   <form id="addForm" method="post">
		<table>
		   <tr>
		   		<td>
		   		   <div class="fitem">
		   		   <label>中心名称:</label>
		   		   <input id="seqNumber" name="t532Bean.SeqNumber" type="hidden" value="0">
		   		   <input id="CenterName" name="t532Bean.CenterName" type="text" class="easyui-validatebox">
		   		   <span id="CenterNameSpan"></span>
		   		   </div>
		   		</td>
		   		<td class="empty"></td>
		   		<td>
		   			<div class="fitem">
		   			<label>所属学科:</label>
		   			 <input id="FromSubject" name="t532Bean.FromSubject" type="text" class="easyui-validatebox">
		   		   <span id="FromSubjectSpan"></span>
		   			</div>
		   		</td>
		   </tr>
		   <tr>
		   		<td>
		   		<div class="fitem">
		   		<label>级别：</label> 
			    <input class='easyui-combobox' id="CenterLevel" name="t532Bean.CenterLevel"
							data-options="valueField:'indexId',textField:'awardLevel',url:'pages/DiAwardLevel/loadDiAwardLevel',listHeight:'auto',editable:false">
				<span id="CenterLevelSpan"></span>
		   		</div>
		   		</td>
		   		<td class="empty"></td>
		   		<td>
		   			<div class="fitem">
					<label>所属教学单位：</label> 
					<input type="hidden" name="t532Bean.FromTeaUnit" id="FromTeaUnit"/>
						<input id="UnitID" type="text" name="t532Bean.UnitID" class='easyui-combobox' 
							data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment' ,listHeight:'auto',editable:false,
							onSelect:function(){
							    $('#FromTeaUnit').val($(this).combobox('getText')) ;
							 }">
					<span id="UnitIDSpan"></span>
					</div>
		   		</td>
		   </tr>
		   <tr>
		   		<td>
		   			<div class="fitem">
					<label>负责人：</label> 
					<input type="hidden" name="t532Bean.CenterLeader" id="Leader"/>
						<input id="TeaID" type="text" name="t532Bean.TeaID" class='easyui-combobox' 
							data-options="valueField:'teaId',textField:'teaName',url:'pages/T411/loadT411' ,listHeight:'auto',editable:'false',
							onSelect:function(){
							    $('#Leader').val($(this).combobox('getText')) ;
							 }">
					<span id="TeaIDSpan"></span>
					</div>
		   		</td>
		   		<td class="empty"></td>
		   		<td>
			   		<div class="fitem">
			   		<label>职称：</label> 
				    <input class='easyui-combobox' id="TeaTitle" name="t532Bean.TeaTitle"
								data-options="valueField:'indexId',textField:'titleName',url:'pages/DiTitleName/loadDiTitleName',listHeight:'auto',editable:'false'">
					<span id="TeaTitleSpan"></span>
			   		</div>
		   		</td>
		   	</tr>
		   	<tr>
		   		<td>
		   			<div class="fitem">
						<label>设立时间：</label> 
						 <input class="easyui-datebox" id="BuildTime" name="t532Bean.BuildTime"  type="text">
						 <span id="BuildTimeSpan"></span>
					</div>
		   		</td>
		   		<td class="empty"></td>
		   		<td>
		   			<div class="fitem">
		   			<label>建设批文号:</label>
		   			<input id="BuildAppvlID" name="t532Bean.BuildAppvlID" type="text" class="easyui-validatebox">
		   			<span id="BuildAppvlIDSpan"></span>
		   			</div>
		   		</td>
		   	</tr>
		   	<tr>
		   		<td>
		   		<div class="fitem">
		   		<label>验收时间：</label> 
				<input class="easyui-datebox" id="ReceptTime" name="t532Bean.ReceptTime" type="text">
				<span id="ReceptTimeSpan"></span>
		   		</div>
		   		</td>
		   		<td class="empty"></td>
		   		<td>
		   			<div class="fitem">
		   			<label>验收批文号:</label>
		   			<input id="ReceptAppvlID" name="t532Bean.ReceptAppvlID" type="text" class="easyui-validatebox">
		   			<span id="ReceptAppvlIDSpan"></span>
		   			</div>
		   		</td>
		   	</tr>
		   	<tr>
		   		<td>
		   			<div class="fitem">
		   			<label>有效期（年）:</label>
		   			<input id="ValidTime" name="t532Bean.ValidTime" type="text" class="easyui-validatebox">
		   			<span id="ValidTimeSpan"></span>
		   			</div>
		   		</td>
		   		<td class="empty"></td>
		   		<td>
		   			<div class="fitem">
		   			<label>经费(万元):</label>
		   			<input id="Fund" name="t532Bean.Fund" type="text" class="easyui-validatebox">
		   			<span id="FundSpan"></span>
		   			</div>
		   		</td>
		   	</tr>
			<tr>
				<td style="valign:left" colspan="3"><label>备注：</label>
					<textarea id="Note" name="t532Bean.Note" style="resize:none" cols="50" rows="10"></textarea>
					<span id="NoteSpan"></span>
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
