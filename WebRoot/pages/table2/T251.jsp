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

<title>T251</title>
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
	<script type="text/javascript" src="js/table2/T251.js"></script>
</head>

<body style="height: 100%'" >
	<table  id="unverfiedData"  class="easyui-datagrid"  url="pages/T251/loadPlaceInfo"  style="height: auto"  >
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th  data-options="field:'seqNumber'" >编号</th>
				<th data-options="field:'expCenterName'">
						实验中心名称
				</th>
		     </tr>		     
		</thead>
		<thead>
				<tr>					
					<th data-options="field:'teaUnit'">
						所属教学单位
					</th>
					<th data-options="field:'teaUnitID'">
						教学单位号
					</th>
					<th data-options="field:'labName'" >
						下属实验室名称
					</th>
					<th data-options="field:'buildTime'" formatter="formattime">
						创建时间
					</th>
					<th data-options="field:'place'">
						地点
					</th>	
					<th data-options="field:'machNum'">
						台件数量
					</th>
					<th data-options="field:'money'">
						金额（元）
					</th>		
					<th data-options="field:'area'">
						面积（平方米）
					</th>	
					<th data-options="field:'newAddArea'">
						其中当年新增面积（平方米）
					</th>
					<th data-options="field:'nature'">
						性质
					</th>	
					<th data-options="field:'forMajor'">
						面向专业
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
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th  data-options="field:'seqNumber'" >编号</th>
				<th data-options="field:'expCenterName'">
						实验中心名称
				</th>
		     </tr>		     
		</thead>
		<thead>
				<tr>					
					<th data-options="field:'teaUnit'">
						所属教学单位
					</th>
					<th data-options="field:'teaUnitID'">
						教学单位号
					</th>
					<th data-options="field:'labName'" >
						下属实验室名称
					</th>
					<th data-options="field:'buildTime'" formatter="formattime">
						创建时间
					</th>
					<th data-options="field:'place'">
						地点
					</th>	
					<th data-options="field:'machNum'">
						台件数量
					</th>
					<th data-options="field:'money'">
						金额（元）
					</th>		
					<th data-options="field:'area'">
						面积（平方米）
					</th>	
					<th data-options="field:'newAddArea'">
						其中当年新增面积（平方米）
					</th>
					<th data-options="field:'nature'">
						性质
					</th>	
					<th data-options="field:'forMajor'">
						面向专业
					</th>																															
					<th data-options="field:'note'">
						备注
					</th>
				</tr>
			</thead>
	</table>
	<div id="toolbar2" style="float: right;">
		<a href='pages/T251/dataExport?excelName=<%=URLEncoder.encode("表2-5-1本科实验、实习、实训场所-基本情况","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 		
		
	</div>
	
	<!--添加弹出框-->
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="title1">本科实验、实习、实训场所信息模板导入</h3>
		<div class="fitem" id="item1">
		  <form method="post"  id="batchForm" enctype="multipart/form-data">
		 		<select class="easyui-combobox"  id="cbYearContrast" name="selectYear"  editable=false></select>
				<input type="file" name="uploadFile" id="fileToUpload" class="easyui-validatebox" size="48" style="height: 24px;" required="true" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href='pages/T251/downloadModel?saveFile=<%=URLEncoder.encode("表2-5-1本科实验、实习、实训场所-基本情况.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
		  </form>
		</div>	
		<hr></hr>	
	   <h3 class="title1">本科实验、实习、实训场所信息逐条导入</h3>
	   <form id="addForm" method="post">
		<table>	
			<tr>
				<td>
				<input type="hidden" name="T251_bean.seqNumber" id="seqNumber"/>
				<div class="fitem">
				<label>实验中心名称：</label> 
				<input id="expCenterName" type="text" name="T251_bean.expCenterName"
				class="easyui-validatebox" ><span id="expCenterNameSpan"></span>
				</div>
				</td>
				<td class="empty"></td>
				<td>
				   <div class="fitem">
						<label>所属教学单位：</label> 
						<input type="hidden" name="T251_bean.teaUnit" id="teaUnit"/>
						<input id="teaUnitID" type="text" name="T251_bean.teaUnitID" class='easyui-combobox' 
							data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDIDepartmentAca',listHeight:'auto',editable:false,
							onSelect:function(){
							    document.getElementById('teaUnit').value=$(this).combobox('getText') ;
							 }">
						 <span id="teaUnitIDSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
				<div class="fitem">
					<label>下属实验室名称：</label> 
					<input id="labName" type="text" name="T251_bean.labName"
						class="easyui-validatebox" ><span id="labNameSpan"></span>
				</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>创建时间：</label> 
						<input class="easyui-datebox"  id="buildTime" type="text" 
						name="T251_bean.buildTime"  editable="false" />
						<span id="buildTimeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
				<div class="fitem">
					<label>地点：</label> 
					<input id="place" type="text" name="T251_bean.place"
						class="easyui-validatebox" ><span id="placeSpan"></span>
				</div>
				</td>
				<td class="empty"></td>
				<td>
				<div class="fitem">
					<label>台件数量：</label> 
					<input id="machNum" type="text" name="T251_bean.machNum"
						class="easyui-validatebox" ><span id="machNumSpan"></span>
				</div>
				</td>
			</tr>
			<tr>
				<td>
				<div class="fitem">
					<label>金额（元）：</label> 
					<input id="money" type="text" name="T251_bean.machNum"
						class="easyui-validatebox" ><span id="machNumSpan"></span>
				</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>面积（平方米）：</label> 
						<input id="area" type="text" name="T251_bean.area" class="easyui-validatebox" >
						<span id="areaSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
				<div class="fitem">
					<label>其中当年新增面积（平方米）：</label> 
					<input id="newAddArea" type="text" name="T251_bean.newAddArea"
						class="easyui-validatebox" ><span id="newAddAreaSpan"></span>
				</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>性质：</label> 
						<select class='easyui-combobox'  id="nature" name="T251_bean.nature"  panelHeight="auto" editable="false" >
							<option value="专业实验室">专业实验室</option>
							<option value="基础实验室">基础实验室</option>
						</select>	
							<span id="natureSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td style="valign:left" colspan="3">
				<label>面向专业：</label> 
				<textarea id="forMajor" name="T251_bean.forMajor" style="resize:none" cols="50" rows="5"></textarea>
					<span id="forMajorSpan"></span>				
				</td>
			</tr>								
			<tr>
				<td style="valign:left" colspan="3"><label>备注：</label>
					<textarea id="note" name="T251_bean.note" style="resize:none" cols="50" rows="10"></textarea>
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
