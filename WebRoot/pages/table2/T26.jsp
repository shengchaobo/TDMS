<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<%@ page language="java" import="cn.nit.constants.Constants"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>T26</title>
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
	<script type="text/javascript" src="js/table2/T26.js"></script>
</head>

<% request.setAttribute("CHECKTYPE",Constants.CTypeTwo); %>
<% request.setAttribute("NOCHECK",Constants.NO_CHECK); %>
<% request.setAttribute("PASS",Constants.PASS_CHECK); %>
<body style="height: 100%'"  onload="myMarquee('T26','<%=request.getAttribute("CHECKTYPE") %>')">
  <div  id="floatDiv">
        <span style="font:12px; font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;审核未通过提示消息：</span>
        <marquee id="marquee"  scrollAmount="1"  width="900"  height="40" direction="up"  style="color: red;"  onmouseover="stop()" onmouseout="start()">
        </marquee>       
  </div>
  <br/> 
  
	<table  id="unverfiedData"  class="easyui-datagrid"  url="pages/T26/loadPlaceInfo?checkNum=<%=request.getAttribute("NOCHECK") %>"  style="height: auto"  >
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th  data-options="field:'seqNumber'" >编号</th>
				<th  data-options="field:'checkState'"   formatter="formatCheckState">审核状态</th>
				<th data-options="field:'practiseBase'">
						校外实习、实训基地名称
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
					<th data-options="field:'address'" >
						地址
					</th>
					<th data-options="field:'forMajor'">
						面向专业
					</th>	
					<th data-options="field:'stuNumEachTime'">
						每次可接纳学生数（个）
					</th>
					<th data-options="field:'stuNumEachYear'">
						当年接纳学生总数（人）
					</th>	
					<th data-options="field:'signLevel'">
						签约级别
					</th>
					<th data-options="field:'baseLevel'">
						基地单位级别
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
	
	<table id="verfiedData"  class="easyui-datagrid"  url="pages/T26/loadPlaceInfo?checkNum=<%=request.getAttribute("PASS") %>"  style="height: auto;" >
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th  data-options="field:'seqNumber'" >编号</th>
				<th data-options="field:'practiseBase'">
						校外实习、实训基地名称
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
					<th data-options="field:'address'" >
						地址
					</th>
					<th data-options="field:'forMajor'">
						面向专业
					</th>	
					<th data-options="field:'stuNumEachTime'">
						每次可接纳学生数（个）
					</th>
					<th data-options="field:'stuNumEachYear'">
						当年接纳学生总数（人）
					</th>	
					<th data-options="field:'signLevel'">
						签约级别
					</th>
					<th data-options="field:'baseLevel'">
						基地单位级别
					</th>																						
					<th data-options="field:'note'">
						备注
					</th>
				</tr>
			</thead>
	</table>
	<div id="toolbar2" style="float: right;">
		<form action='pages/T26/dataExport?excelName=<%=URLEncoder.encode("表2-6校外实习、实训基地","UTF-8")%>'   method="post"  id="exportForm" enctype="multipart/form-data"  style="float: right;">
					  <select class="easyui-combobox"  id="cbYearContrast1" name="selectYear"  editable=false ></select>&nbsp;&nbsp;
						<a href='javascript:submitForm()'   style="font:12px;color: black;text-decoration:none;" >
								数据导出
						</a> &nbsp;&nbsp;&nbsp;&nbsp;		
		</form>		
	</div>
	
	<!--添加弹出框-->
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="title1">校外实习、实训基地信息模板导入</h3>
		<div class="fitem" id="item1">
		  <form method="post"  id="batchForm" enctype="multipart/form-data">
		 		 <select class="easyui-combobox"  id="cbYearContrast" name="selectYear"  editable=false></select>
				<input type="file" name="uploadFile" id="fileToUpload" class="easyui-validatebox" size="48" style="height: 24px;" required="true" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href='pages/T26/downloadModel?saveFile=<%=URLEncoder.encode("表2-6校外实习、实训基地.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
		  </form>
		</div>	
		<hr></hr>	
	   <h3 class="title1">校外实习、实训基地所信息逐条导入</h3>
	   <form id="addForm" method="post">
		<table>	
			<tr>
				<td>
				<input type="hidden" name="T26_bean.seqNumber" id="seqNumber"/>
				<input type="hidden"  name="T26_bean.teaUnit" id="teaUnit"/>
				<input type="hidden"  name="T26_bean.teaUnitID"  id="teaUnitID" >
				<div class="fitem">
				<label>校外实习、实训基地名称：</label> 
				<input id="practiseBase" type="text" name="T26_bean.practiseBase"
				class="easyui-validatebox" ><span id="practiseBaseSpan"></span>
				</div>
				</td>
			</tr>
			<tr>
				<td>
				<div class="fitem">
					<label>地址：</label> 
					<input id="address" type="text" name="T26_bean.address"
						class="easyui-validatebox" ><span id="addressSpan"></span>
				</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>每次可接纳学生数（个）：</label> 
						<input class="easyui-validatebox"   id="stuNumEachTime" type="text" 
						name="T26_bean.stuNumEachTime"  editable="false" />
						<span id="stuNumEachTimeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>当年接纳学生总数（人）：</label> 
						<input class="easyui-validatebox"   id="stuNumEachYear" type="text" 
						name="T26_bean.stuNumEachYear"  editable="false" />
						<span id="stuNumEachYearSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>签约级别：</label> 
						<input class='easyui-combobox' id="signLevel" name="T26_bean.signLevel" 
							data-options="valueField:'indexId',textField:'awardLevel',url:'pages/DiAwardLevel/loadDIAwardLevelPartTwo',listHeight:'auto',editable:false" panelHeight="auto">
						<span id="signLevelSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td style="valign:left" colspan="3">
					<div class="fitem">
						<label>基地单位级别：</label> 
						<select class='easyui-combobox'  id="baseLevel" name="T26_bean.baseLevel"  panelHeight="auto" editable="false" >
							<option value="国家级">国家级</option>
							<option value="省部级">省部级</option>
							<option value="市厅级">市厅级</option>
							<option value="企业">企业</option>				
						</select>	
							<span id="baseLevelSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td style="valign:left" colspan="3">
				<label>面向专业：</label> 
				<textarea id="forMajor" name="T26_bean.forMajor" style="resize:none" cols="50" rows="5"></textarea>
					<span id="forMajorSpan"></span>				
				</td>
			</tr>								
			<tr>
				<td style="valign:left" colspan="3"><label>备注：</label>
					<textarea id="note" name="T26_bean.note" style="resize:none" cols="50" rows="10"></textarea>
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
    	
    	var select1 = document.getElementById("cbYearContrast1");
    	for (var i = 0; i <= 10; i++) {
        var theOption = document.createElement("option");
        	theOption.innerHTML = currentYear-i + "年";
        	theOption.value = currentYear-i;
        	select1.appendChild(theOption);
    	}
	</script>
</html>
