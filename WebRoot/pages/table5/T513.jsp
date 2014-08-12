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

<title>T513</title>
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
	<script type="text/javascript" src="js/table5/T513.js"></script>

</head>
<body style="overflow-y:scroll">
	<table  id="showData"   style="height: auto"  >	
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true" rowspan=2>选取</th>
				<th  data-options="field:'seqNumber'"  hidden="true">编号</th>
				<th data-options="field:'item'" rowspan=2>
				项目
				</th>
				<th data-options="field:'category'" rowspan=2>
	       		分类
				</th>
				<th  data-options="field:'shouldASCSNum'" rowspan=2>
				应评课程总门次数
				</th>
				<th  data-options="field:'havedASCSNum'" rowspan=2>
				已评课程门次数
				</th>
				<th data-options="field:'coverRatio'" rowspan=2>
				覆盖比例（%）
				</th>
				<th colspan=2>
	       		优（90分及以上
				</th>
				<th  colspan=2>
				良好（89-75分）
				</th>
				<th   colspan=2>
				中（74-60分）
				</th>
				<th   colspan=2>
				差（60分以下）
				</th>
			</tr>	
			<tr>
			    <th  data-options="field:'excellentNum'">
				门次数
				</th>
				<th  data-options="field:'excellentRatio'">
				比例
				</th>
				  <th  data-options="field:'goodNum'">
				门次数
				</th>
				<th  data-options="field:'goodRatio'">
				比例
				</th>
				  <th  data-options="field:'avgNum'">
				门次数
				</th>
				<th  data-options="field:'avgRatio'">
				比例
				</th>
				  <th  data-options="field:'poorNum'">
				门次数
				</th>
				<th  data-options="field:'poorRatio'">
				比例
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
		<!-- 
		<h3  class="title1">课堂教学质量评估统计表导入</h3>
		<div class="fitem" id="item1">
		  <form method="post"  id="batchForm" enctype="multipart/form-data">
		  		<select class="easyui-combobox"  id="cbYearContrast" name="selectYear" editable="false"></select>
				<input type="file" name="uploadFile" id="fileToUpload" class="easyui-validatebox" size="48" style="height: 24px;" required="true" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href='pages/T513/downloadModel?saveFile=<%=URLEncoder.encode("表表5-1-3课堂教学质量评估统计表（评估中心）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
		</div>
		<hr></hr>
	   <h3  class="title1">课堂教学质量评估统计逐条插</h3>	
	    -->	
	   <form id="addForm" method="post">
		<table>	
			<tr>
			   <td>
			      <input type="hidden" name="t513Bean.SeqNumber"  id="seqNumber"/>
					<div class="fitem">
						<label>项目：</label> 
						<input class='easyui-combobox' id="Item" name="t513Bean.Item" 
							data-options="valueField:'indexId',textField:'evaluType',url:'pages/DiEvaluType/loadDiEvaluType',listHeight:'auto',editable:false">
						<span id="ItemSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>分类：</label> 
						<select class='easyui-combobox'  id="Category" name="t513Bean.Category"  panelHeight="auto" editable="false" >
							<option value="理论课">理论课</option>
							<option value="实践教学">实践教学</option>				
						</select>	
							<span id="CategorySpan"></span>
					</div>
				</td>	
			</tr>
			<tr>
				<td >
					<div class="fitem">
						<label>应评课程总门次数：</label> 
						<input class="easyui-numberbox"   id="ShouldASCSNum"  type="text"  min=0  
						name="t513Bean.ShouldASCSNum"  editable="false" />
						<span id="ShouldASCSNumSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td >
					<div class="fitem">
						<label>已评课程门次数：</label> 
						<input class="easyui-numberbox"   id="HavedASCSNum"  type="text"  min=0  
						name="t513Bean.HavedASCSNum"  editable="false" />
						<span id="HavedASCSNumSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan = "3">
					<div class="fitem">
						<label>覆盖比例（%）：</label> 
						<input  id="CoverRatio"  type="text"  min=0  precision=2
						name="t513Bean.CoverRatio"  editable="false" />
						<span id="CoverRatioSpan"></span>
					</div>
				</td>
			</tr>
			
			
			<tr >
				<td colspan = "3" style="color: blue">	优（90分及以上）
				</td>
			</tr>
	
			<tr>
				<td >
					<div class="fitem">
						<label>门次数：</label> 
						<input class="easyui-numberbox" id="ExcellentNum"  type="text"  min=0  
						name="t513Bean.ExcellentNum"  editable="false" />
						<span id="ExcellentNumSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td >
					<div class="fitem">
						<label>比例：</label> 
						<input  id="ExcellentRatio"  type="text"  min=0  precision=2
						name="t513Bean.ExcellentRatio"  editable="false" />
						<span id="ExcellentRatioSpan"></span>
					</div>
				</td>
			</tr>
			
			<tr >
				<td colspan = "3"  style="color: blue">	良好（89-75分）
				</td>
			</tr>
		
			<tr>
				<td >
					<div class="fitem">
						<label>门次数：</label> 
						<input class="easyui-numberbox" id="GoodNum"  type="text"  min=0  
						name="t513Bean.GoodNum"  editable="false" />
						<span id="GoodNumSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td >
					<div class="fitem">
						<label>比例：</label> 
						<input  id="GoodRatio"  type="text"  min=0 
						name="t513Bean.GoodRatio"  editable="false" />
						<span id="GoodRatioSpan"></span>
					</div>
				</td>
			</tr>
			
			<tr >
				<td colspan = "3"  style="color: blue">	中（74-60分））
				</td>
			</tr>
	
			<tr>
				<td >
					<div class="fitem">
						<label>门次数：</label> 
						<input class="easyui-numberbox" id="AvgNum"  type="text"  min=0  
						name="t513Bean.AvgNum"  editable="false" />
						<span id="AvgNumSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td >
					<div class="fitem">
						<label>比例：</label> 
						<input  id="AvgRatio"  type="text"  min=0 
						name="t513Bean.AvgRatio"  editable="false" />
						<span id="AvgRatioSpan"></span>
					</div>
				</td>
			</tr>
			
			<tr >
				<td colspan = "3"  style="color: blue">	差（60分以下）
				</td>
			</tr>
			<tr>
				<td >
					<div class="fitem">
						<label>门次数：</label> 
						<input class="easyui-numberbox" id="PoorNum"  type="text"  min=0  
						name="t513Bean.PoorNum"  editable="false" />
						<span id="PoorNumSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td >
					<div class="fitem">
						<label>比例：</label> 
						<input  id="PoorRatio"  type="text"  min=0 
						name="t513Bean.PoorRatio"  editable="false" />
						<span id="PoorRatioSpan"></span>
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
