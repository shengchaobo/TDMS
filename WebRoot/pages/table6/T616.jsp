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

<title>T616</title>
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
	<script type="text/javascript" src="js/table6/T616.js"></script>

</head>
<body style="overflow-y:scroll">
	<table id="showData"  style="height: auto">		
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"  rowspan="2">选取</th>
				<th  data-options="field:'seqNumber'" rowspan="2" hidden="true">编号</th>
				<th data-options="field:'stuType'" rowspan="2">
				学生类别
				</th>
				
				<th  colspan="5">1.毕（结）业生数（人）</th>
				<th  colspan="5">2.授予学位数（人）</th>
				<th  colspan="5">3.招生数（人）</th>
				<th  colspan="5">4.在校生数（人）</th>
				
			</tr>
			<tr>
				<th data-options="field:'sumGraNum'">
				小计
				</th>
				<th data-options="field:'graOutNum'">
	       		国外
				</th>
				<th data-options="field:'graHongNum'">
				香港
				</th>
				<th data-options="field:'graAoNum'">
				澳门
				</th>
				<th data-options="field:'graTaiNum'">
	       		台湾
				</th>
				<th data-options="field:'sumDegreeNum'">
				小计
				</th>
				<th data-options="field:'degreeOutNum'">
	       		国外
				</th>
				<th data-options="field:'degreeHongNum'">
				香港
				</th>
				<th data-options="field:'degreeAoNum'">
				澳门
				</th>
				<th data-options="field:'degreeTaiNum'">
	       		台湾
				</th>
				<th data-options="field:'sumAdmisNum'">
				小计
				</th>
				<th data-options="field:'admisOutNum'">
	       		国外
				</th>
				<th data-options="field:'admisHongNum'">
				香港
				</th>
				<th data-options="field:'admisAoNum'">
				澳门
				</th>
				<th data-options="field:'admisTaiNum'">
	       		台湾
				</th>
				<th data-options="field:'sumInSchNum'">
				小计
				</th>
				<th data-options="field:'inSchOutNum'">
	       		国外
				</th>
				<th data-options="field:'inSchHongNum'">
				香港
				</th>
				<th data-options="field:'inSchAoNum'">
				澳门
				</th>
				<th data-options="field:'inSchTaiNum'">
	       		台湾
				</th>
			</tr>			
	</thead>
	</table>
								 
	<div id="toolbar" style="height:auto">
		<div style="float: left;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">编辑</a> 
			<a href="javascript:void(0)" id="export" class="easyui-linkbutton" iconCls="icon-download" plain="true"  onclick="exports()">数据导出</a>
			<a href="javascript:void(0)" id="add" class="easyui-linkbutton" iconCls="icon-add" plain="true" >数据导入</a>
		</div>
	 	 <form  id="exportForm"  style="float: right;"  method="post" >
			显示： <select class="easyui-combobox" id="cbYearContrast" panelHeight="auto" style="width:80px; padding-top:5px; margin-top:10px;"  editable=false ></select>
	 	</form>	
	</div>
	
	<div id="dlg-import" class="easyui-dialog"
		style="width:600px;height:auto;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="title1">课外活动、讲座导入</h3>
		<div class="fitem" id="item1">
			<form id="batchForm" method="post" enctype="multipart/form-data">
			<select class="easyui-combobox"  id="cbYearContrast1" name="selectYear" editable="false"></select>
				<input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox" size="48" style="height: 24px;"  required="true" />
				<a href="javascript:void(0)" id = "import" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				
			</form>
		</div>
		</div>
	
	<!--添加弹出框-->
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
	   <form id="addForm" method="post">
		<table>	
			<tr>
				<td >
				<input type="hidden" name="T616_bean.seqNumber" id="seqNumber"/>
				<div class="fitem">
				<label>学位类别：</label> 
				<input id="stuType" type="text" name="T616_bean.stuType"
				class="easyui-validatebox" ><span id="stuTypeSpan"></span>
				</div>
				</td>
				<td class="empty"></td>
				
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>国外毕业生：</label> 
						<input class="easyui-numberbox"   id="graOutNum"  type="text" 
						name="T616_bean.graOutNum"  editable="false" />
						<span id="graOutNumSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>香港毕业生：</label> 
						<input class="easyui-numberbox"   id="graHongNum"  type="text" 
						name="T616_bean.graHongNum"  editable="false" />
						<span id="graHongNumSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>澳门毕业生：</label> 
						<input class="easyui-numberbox"   id="graAoNum"  type="text" 
						name="T616_bean.graAoNum"  editable="false" />
						<span id="graAoNumSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>台湾毕业生：</label> 
						<input class="easyui-numberbox"   id="graTaiNum"  type="text" 
						name="T616_bean.graTaiNum"  editable="false" />
						<span id="graTaiNumSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>毕业生总数：</label> 
						<input class="easyui-numberbox"   id="sumGraNum"  type="text" 
						name="T616_bean.sumGraNum"  editable="false" />
						<span id="sumGraNumSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>国外授予学位数：</label> 
						<input class="easyui-numberbox"   id="degreeOutNum"  type="text" 
						name="T616_bean.degreeOutNum"  editable="false" />
						<span id="degreeOutNumSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>香港授予学位数：</label> 
						<input class="easyui-numberbox"   id="degreeHongNum"  type="text" 
						name="T616_bean.degreeHongNum"  editable="false" />
						<span id="degreeHongNumSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>澳门授予学位数：</label> 
						<input class="easyui-numberbox"   id="degreeAoNum"  type="text" 
						name="T616_bean.degreeAoNum"  editable="false" />
						<span id="degreeAoNumSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>台湾授予学位数：</label> 
						<input class="easyui-numberbox"   id="degreeTaiNum"  type="text" 
						name="T616_bean.degreeTaiNum"  editable="false" />
						<span id="degreeTaiNumSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>授予学位学生总数：</label> 
						<input class="easyui-numberbox"   id="sumDegreeNum"  type="text" 
						name="T616_bean.sumDegreeNum"  editable="false" />
						<span id="sumDegreeNumSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>国外招生数：</label> 
						<input class="easyui-numberbox"   id="admisOutNum"  type="text" 
						name="T616_bean.admisOutNum"  editable="false" />
						<span id="admisOutNumSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>香港招生数：</label> 
						<input class="easyui-numberbox"   id="admisHongNum"  type="text" 
						name="T616_bean.admisHongNum"  editable="false" />
						<span id="admisHongNumSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>澳门招生数：</label> 
						<input class="easyui-numberbox"   id="admisAoNum"  type="text" 
						name="T616_bean.admisAoNum"  editable="false" />
						<span id="admisAoNumSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>台湾招生数：</label> 
						<input class="easyui-numberbox"   id="admisTaiNum"  type="text" 
						name="T616_bean.admisTaiNum"  editable="false" />
						<span id="admisTaiNumSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>招生总数：</label> 
						<input class="easyui-numberbox"   id="sumAdmisNum"  type="text" 
						name="T616_bean.sumAdmisNum"  editable="false" />
						<span id="sumAdmisNumSpan"></span>
					</div>
				</td>
			</tr>
			
			<tr>
				<td>
					<div class="fitem">
						<label>在校国外学生：</label> 
						<input class="easyui-numberbox"   id="inSchOutNum"  type="text" 
						name="T616_bean.inSchOutNum"  editable="false" />
						<span id="inSchOutNumSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>在校香港学生：</label> 
						<input class="easyui-numberbox"   id="inSchHongNum"  type="text" 
						name="T616_bean.inSchHongNum"  editable="false" />
						<span id="inSchHongNumSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>在校澳门学生：</label> 
						<input class="easyui-numberbox"   id="inSchAoNum"  type="text" 
						name="T616_bean.inSchAoNum"  editable="false" />
						<span id="inSchAoNumSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>在校台湾学生：</label> 
						<input class="easyui-numberbox"   id="inSchTaiNum"  type="text" 
						name="T616_bean.inSchTaiNum"  editable="false" />
						<span id="inSchTaiNumSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>在校生总数：</label> 
						<input class="easyui-numberbox"   id="sumInSchNum"  type="text" 
						name="T616_bean.sumInSchNum"  editable="false" />
						<span id="sumInSchNumSpan"></span>
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
<script type="text/javascript">
    	var currentYear = new Date().getFullYear();
    	var select = document.getElementById("cbYearContrast1");
    	for (var i = 0; i <= 10; i++) {
        var theOption = document.createElement("option");
        	theOption.innerHTML = currentYear-i + "年";
        	theOption.value = currentYear-i;
        	select.appendChild(theOption);
    	}
	</script>
		
</html>
