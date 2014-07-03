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

<title>T511_UndergraCSBase_Tea</title>
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
	<script type="text/javascript" src="js/table5/T511.js"></script>
	<script type="text/javascript" src="js/commom.js"></script>
	
</head>
<body style="overflow-y:scroll">
<table  id="unverfiedData"  class="easyui-datagrid"  url="pages/UndergraCSBaseTea/auditingData"  style="height: auto"  >
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber">序号</th>
				<th field="CSName" >课程名称</th>
				<th field="CSID" >课程编号</th>
				<th field="CSUnit" >开课单位</th>
				<th field="unitID" >单位号</th>
				<th field="fromTeaResOffice" >所属教研室</th>
				<th field="teaResOfficeID" >教研室号</th>
				<th field="CSType" >课程类别</th>
				<th field="CSNature" >课程性质</th>
				<th field="state"  fit="true">状态</th>
				<th field="pubCSType" >公选课类别</th>
				<th field="note" w>备注</th>
				<th field="time"  formatter="formattime">时间</th>
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
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="id" >序号</th>
				<th field="csName" >课程名称</th>
				<th field="csId" >课程编号</th>
				<th field="csUnit" >开课单位</th>
				<th field="unitId" >单位号</th>
				<th field="fromTeaResOffice" >所属教研室</th>
				<th field="teaResOfficeId" >教研室号</th>
				<th field="csType" >课程类别</th>
				<th field="csNature" >课程性质</th>
				<th field="state" >状态</th>
				<th field="pubCsType" >公选课类别</th>
				<th field="note" >备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar2" style="float: right;">
		<a href='pages/T511/dataExport?excelName=<%=URLEncoder.encode("表5-1-1本科课程库.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="">高级检索</a>
	</div>
	
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<div class="ftitle">本科课程库批量导入</div>
		<div class="fitem">
			<form id="batchForm" method="post" enctype="multipart/form-data">
				<label>批量上传：</label> 
				<select class="easyui-combobox" id="cbYearContrast" name="selectYear" panelHeight="auto" style="width:80px; padding-top:5px; margin-top:10px;"></select>
				<input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox"
					validType="fileType['xls']" required="true" invalidMessage="请选择Excel格式的文件" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">导入</a>
				<a href="pages/UndergraCSBaseTea/downloadModel" class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
		</div>
		<div></div>
		<div class="ftitle">本科课程库逐条导入</div>
		<form id="addForm" method="post">
		<table>
		
			<tr>
				<td>
					<div class="fitem">
						<label>课程名称：</label> 
						<input id="seqNumber" type="hidden"name="undergraCSBaseTea.SeqNumber" value="0"></input>
						<input id="CSName" type="text" name="undergraCSBaseTea.CSName"
							class="easyui-validatebox" required="true"><span id="CSNameSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>课程编号：</label> 
						<input id="CSID" type="text" name="undergraCSBaseTea.CSID"
							class="easyui-validatebox" required="true"><span id="CSIDSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>开课单位：</label> 
						<!-- 下边的onselect方法是为了后台既要教学单位名称，有需要教学单位编号，而我们只有一个下拉框包含了这两条信息 -->
						<input type="hidden" name="undergraCSBaseTea.CSUnit" id="CSUnit"/>
						<input id="UnitID" name="undergraCSBaseTea.UnitID" 
							 class='easyui-combobox' data-options="valueField:'unitID',textField:'unitName',url:'pages/diDepartment/loadDIDepartment',listHeight:'auto',editable:false,
							 onSelect:function(){
							 	document.getElementById('CSUnit').value=$(this).combobox('getText') ;
							 }">
						<span id="CSUnitSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>所属教研室：</label> 
						<input type="hidden" name="undergraCSBaseTea.FromTeaResOffice" id="FromTeaResOffice"/>
						<input id="TeaResOfficeID" name="undergraCSBaseTea.TeaResOfficeID" class='easyui-combobox'
							data-options="valueField:'unitID',textField:'researchName',url:'pages/diResearchRoom/loadDIResearchRoom',listHeight:'auto',editable:false,
							onSelect:function(){
							 	$('#FromTeaResOffice').val($(this).combobox('getText')) ;
							 }">
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>课程类别：</label> 
						<input class='easyui-combobox' id="CSType" name="undergraCSBaseTea.CSType" 
							data-options="valueField:'indexId',textField:'courseCategories',url:'pages/DiCourseCategories/loadDiCourseCategories',listHeight:'auto',editable:false">
						<span id="CSTypeSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>课程性质：</label> 
						<input class='easyui-combobox' id="CSNature" name="undergraCSBaseTea.CSNature"
							data-options="valueField:'indexId',textField:'courseChar',url:'pages/DiCourseChar/loadDiCourseChar',listHeight:'auto',editable:false">
						<span id="CSNatureSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>状&nbsp;&nbsp;&nbsp;&nbsp;态：</label> 
						<select class='easyui-combobox' id="State" name="undergraCSBaseTea.State" >
							<option value="启用">启用</option>
							<option value="停用">停用</option>
						</select>	
							<span id="StateSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>公选课类别：</label> 
						<select class='easyui-combobox' id="PubCSType" name="undergraCSBaseTea.PubCSType">
							<option value="理工类">理工类</option>
							<option value="人文社科类">人文社科类</option>
							<option value="体育保健类">体育保健类</option>
							<option value="无">无</option>
						</select>
						<span id="PubCSTypeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
			<!-- <input class="easyui-datebox" name="undergraCSBaseTea.time" style="width:80px"/> -->
				<td style="valign:left"><label>备&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
					<textarea id="Note" name="undergraCSBaseTea.Note" style="resize:none" cols="50" rows="10"></textarea>
					<span id="NoteSpan"></span>
				</td>
			</tr>
		</table>
		</form>
	</div>
	
	<div id="dicDlg" class="easyui-dialog" style="width:500px;padding:10px 20px" closed="true">
		<div class="ftitle">高级检索</div>
		<div id="dicTables"  class="fitem">
		</div>
		<div id="dices"  class="fitem"></div>
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
	<script type="text/javascript"> 
			//日期格式转换 
			function formattime(val) {  
				
				if(val == null){
					return null ;
				}
				
			    var year=parseInt(val.year)+1900;  
			    var month=(parseInt(val.month)+1);  
			    month=month>9?month:('0'+month);  
			    var date=parseInt(val.date);  
			    date=date>9?date:('0'+date);  
			    var hours=parseInt(val.hours);  
			    hours=hours>9?hours:('0'+hours);  
			    var minutes=parseInt(val.minutes);  
			    minutes=minutes>9?minutes:('0'+minutes);  
			    var seconds=parseInt(val.seconds);  
			    seconds=seconds>9?seconds:('0'+seconds);  
			    var time=year+'-'+month+'-'+date ;  
			    //alert(time) ;
			        return time;  
			    }  
			</script>

</html>
