
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

<title>T4_11</title>
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
	<script type="text/javascript" src="js/table4/T4_11.js"></script>
	<script type="text/javascript"></script>
</head>

<body style="height: 100%'" >
	<table  id="unverfiedData"  class="easyui-datagrid"  url="pages/T4_11/loadSerInfo"  style="height: auto"  >
		<thead data-options="frozen:true">
			<tr>			
				    <th data-options="field:'ck',checkbox:true" rowspan="2">选取</th>
				    <th  data-options="field:'seqNumber'"  rowspan="2">编号</th>
					<th data-options="field:'unitName'" rowspan="2">
					 	教学单位
					</th>
					<th data-options="field:'unitId'" rowspan="2"> 
						单位号
					</th>
		     </tr>
		</thead>
		<thead>
				<tr>	
					<th colspan="2">
						1.成果转化
					</th>
					<th colspan="3">
						2.社会工作
					</th>
				</tr>
				<tr>
					<th  data-options="field:'patentNum'" >
						专利转让数量
					</th>
					<th data-options="field:'achieNum'" >
						科技成果转化数量
					</th>
					<th  data-options="field:'consNum'" >
						技术咨询采用次数
					</th>
					<th data-options="field:'partJobNum'" >
						兼任协（学）会职务人次数
					</th>					
					<th data-options="field:'judgeNum'" >
						受聘学科竞赛评委/裁判人次数
					</th>
					<th data-options="field:'note'" rowspan="2">
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
		<form method="post" id="searchForm"
				style="float: right; height: 24px;">
				<table id="test" width="520">
					<tr>
						<td>
							编号:
						</td>
						<td>
							<input id="seqNum" name="seqNum" class="easyui-box"
								style="width: 40px" />
						</td>
						<td>
							起始日期:
						</td>
						<td>
							<input id="startTime" name="startTime" class="easyui-datebox"
								style="width: 100px" />
						</td>
						<td>
							结束日期:
						</td>
						<td>
							<input id="endTime" name="endTime" class="easyui-datebox"
								style="width: 100px" />
						</td>
						<td>
							<a href="javascript:void(0)" class="easyui-linkbutton"
								iconCls="icon-search" plain="true" onclick=	reloadgrid();>查询</a>
						</td>
					</tr>
				</table>
			</form>
	</div>
	
	<table id="verfiedData"  class="easyui-datagrid"  url=""  style="height: auto;" >
		<thead data-options="frozen:true">
			<tr>			
				    <th data-options="field:'ck',checkbox:true" rowspan="2">选取</th>
				    <th  data-options="field:'seqNumber'"  rowspan="2">编号</th>
					<th data-options="field:'unitName'" rowspan="2">
					 	教学单位
					</th>
					<th data-options="field:'unitId'" rowspan="2"> 
						单位号
					</th>
		     </tr>
		</thead>
		<thead>
				<tr>	
					<th colspan="2">
						1.成果转化
					</th>
					<th colspan="3">
						2.社会工作
					</th>
				</tr>
				<tr>
					<th  data-options="field:'patentNum'" >
						专利转让数量
					</th>
					<th data-options="field:'achieNum'" >
						科技成果转化数量
					</th>
					<th  data-options="field:'consNum'" >
						技术咨询采用次数
					</th>
					<th data-options="field:'partJobNum'" >
						兼任协（学）会职务人次数
					</th>					
					<th data-options="field:'judgeNum'" >
						受聘学科竞赛评委/裁判人次数
					</th>
					<th data-options="field:'note'" rowspan="2">
						备注
					</th>
				</tr>
			</thead>
	</table>
	<div id="toolbar2" style="float: right;">
		<a href='pages/T4_11/dataExport?excelName=<%=URLEncoder.encode("表4-11教学单位社会服务情况（教学单位）","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 		
	</div>
	
	<!--添加弹出框-->
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="title1">教学单位社会服务情况模板导入</h3>
		<div class="fitem" id="item1">
		  <form method="post"  id="batchForm" enctype="multipart/form-data">
		 		 <select class="easyui-combobox"  id="cbYearContrast" name="selectYear"  editable=false></select>
				<input type="file" name="uploadFile" id="fileToUpload" class="easyui-validatebox" size="48" style="height: 24px;" required="true" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href='pages/T4_11/downloadModel?saveFile=<%=URLEncoder.encode("表4-11教学单位社会服务情况（教学单位）","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
		  </form>
		</div>	
		<hr></hr>	
		 <h3 class="title1">教学单位社会服务情况逐条导入</h3>
	   <form id="addForm" method="post">
		<table>
			<tr>
				<td colspan="3">
					<input type="hidden" name="T4_11_bean.seqNumber" id="seqNumber"/>
					<div class="fitem">
						<label>教学单位：</label> 
						<input type="hidden" name="T4_11_bean.unitName" id="unitName"/>
						<input id="unitId" type="text" name="T4_11_bean.unitId" class='easyui-combobox' 
							data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDIDepartmentAca',listHeight:'auto',editable:false,
							onSelect:function(){
							    document.getElementById('unitName').value=$(this).combobox('getText') ;
							 }">
						 <span id="unitIdSpan"></span>
					</div>
				</td>
			</tr>			
			<tr>
				<td>
 					<div class="fitem">
					<label>专利转让数量：</label> 
					<input id="patentNum" type="text" name="T4_11_bean.patentNum"
							class="easyui-validatebox" ><span id="patentNumSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
 					<div class="fitem">
					<label>科技成果转化数量：</label> 
					<input id="achieNum" type="text" name="T4_11_bean.achieNum"
							class="easyui-validatebox" ><span id="achieNumSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
 					<div class="fitem">
					<label>技术咨询采用次数：</label> 
					<input id="consNum" type="text" name="T4_11_bean.consNum"
							class="easyui-validatebox" ><span id="consNumSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
 					<div class="fitem">
					<label>兼任协（学）会职务人次数：</label> 
					<input id="partJobNum" type="text" name="T4_11_bean.partJobNum"
							class="easyui-validatebox" ><span id="partJobNumSpan"></span>
					</div>
				</td>
			</tr>			
			<tr>
				<td colspan="3">
 					<div class="fitem">
					<label>受聘学科竞赛评委/裁判人次数：</label> 
					<input id="judgeNum" type="text" name="T4_11_bean.judgeNum"
							class="easyui-validatebox" ><span id="judgeNumSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td style="valign:left" colspan="3"><label>备注：</label>
					<textarea id="note" name="T4_11_bean.note" style="resize:none" cols="50" rows="10"></textarea>
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
