<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="cn.nit.constants.Constants"%>
<%@ page import="java.net.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>T465</title>
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
	<script type="text/javascript" src="js/table4/T461.js"></script>
</head>

<% request.setAttribute("CHECKTYPE",Constants.CTypeOne); %>
<% request.setAttribute("NOCHECK",Constants.NO_CHECK); %>
<% request.setAttribute("PASS",Constants.PASS_CHECK); %>
<body style="height: 100%'"  onload="myMarquee('T465','<%=request.getAttribute("CHECKTYPE") %>')">
  <div  id="floatDiv">
        <span style="font:12px; font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;审核未通过提示消息：</span>
        <marquee id="marquee"  scrollAmount="1"  width="900"  height="40" direction="up"  style="color: red;"  onmouseover="stop()" onmouseout="start()">
        </marquee>       
  </div>
  <br/> 
	<table  id="unverfiedData"  class="easyui-datagrid"  url="pages/T461/loadHonorInfo?checkNum=<%=request.getAttribute("NOCHECK") %>&param=5"  style="height: auto"  >
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th  data-options="field:'seqNumber'" >编号</th>
				<th  data-options="field:'checkState'"   formatter="formatCheckState">审核状态</th>				
				<th  data-options="field:'name'" >姓名</th>
				<th  data-options="field:'teaId'" >教工号</th>
		     </tr>
		</thead>
		<thead>
				<tr>	
					<th data-options="field:'fromTeaUnit'">
					 	 教学单位
					</th>
					<th data-options="field:'unitId'">
						教学单位号
					</th>
					<th data-options="field:'awardType'">
						获奖类别
					</th>				
					<th data-options="field:'awardLevel'">
						获奖级别
					</th>
					<th data-options="field:'awardFromUnit'">
						授予单位
					</th>
					<th data-options="field:'gainAwardTime'"  formatter="formattime">
						获奖时间
					</th>
					<th data-options="field:'appvlId'">
						批文号
					</th>
					<th data-options="field:'otherTeaNum'">
						其他参与教师人数
					</th>
					<th data-options="field:'otherTeaInfo'">
						其他成员
					</th>
					<th data-options="field:'note'">
						备注
					</th>
				</tr>
			</thead>
	</table>
	<div id="toolbar" style="height:auto">
		<div style="float: left;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newObject('5')">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit('5')">编辑</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteByIds('5')">删除</a>
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
	
	<table id="verfiedData"  class="easyui-datagrid"  url="pages/T461/loadHonorInfo?checkNum=<%=request.getAttribute("PASS") %>&param=5" style="height: auto;" >
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th  data-options="field:'seqNumber'" >编号</th>
				<th  data-options="field:'name'" >姓名</th>
				<th  data-options="field:'teaId'" >教工号</th>
		     </tr>
		</thead>
		<thead>
				<tr>	
					<th data-options="field:'fromTeaUnit'">
					 	 教学单位
					</th>
					<th data-options="field:'unitId'">
						教学单位号
					</th>
					<th data-options="field:'awardType'">
						获奖类别
					</th>				
					<th data-options="field:'awardLevel'">
						获奖级别
					</th>
					<th data-options="field:'awardFromUnit'">
						授予单位
					</th>
					<th data-options="field:'gainAwardTime'"  formatter="formattime">
						获奖时间
					</th>
					<th data-options="field:'appvlId'">
						批文号
					</th>
					<th data-options="field:'otherTeaNum'">
						其他参与教师人数
					</th>
					<th data-options="field:'otherTeaInfo'">
						其他成员
					</th>
					<th data-options="field:'note'">
						备注
					</th>
				</tr>
			</thead>
	</table>
	<div id="toolbar2" style="float: right;">
			<form action='pages/T461/dataExport?param=5&excelName=<%=URLEncoder.encode("表4-6-5教师所获荣誉-教学活动月获奖（教务处）","UTF-8")%>'   method="post"  id="exportForm" enctype="multipart/form-data"  style="float: right;">
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
		<h3 class="title1">教师荣誉情况模板导入</h3>
		<div class="fitem" id="item1">
		  <form method="post"  id="batchForm" enctype="multipart/form-data">
		  		<select class="easyui-combobox"  id="cbYearContrast" name="selectYear"  editable=false></select>
				<input type="file" name="uploadFile" id="fileToUpload" class="easyui-validatebox" size="48" style="height: 24px;" required="true" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href='pages/T461/downloadModel?saveFile=<%=URLEncoder.encode("表4-6-5教师所获荣誉-教学活动月获奖（教务处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
		</div>	
		<hr></hr>	
	   <h3 class="title1">教师荣誉情况逐条导入</h3>
	   <form id="addForm" method="post">
		<table><!--
			<tr>
				<td style="valign:left" colspan="3">
					<div class="fitem">
						<label>请选择导入时间：</label> 
						<input class="easyui-datebox"  id="time" type="text" 
						name="T461_bean.time"  required="true"  editable="false" />
						<span id="timeSpan"></span>
					</div>
				</td>
			</tr>
			-->
			<tr>
				<td>
					<input type="hidden" name="T461_bean.seqNumber" id="seqNumber"/>
					<div class="fitem">
						<label>所属教学单位：</label> 
						<input type="hidden" name="T461_bean.fromTeaUnit" id="fromTeaUnit"/>
						<input id="unitId" type="text" name="T461_bean.unitId" class='easyui-combobox' 
							data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDIDepartmentAca',listHeight:'auto',editable:false,
							onSelect:function(){
							    document.getElementById('fromTeaUnit').value=$(this).combobox('getText') ;
							 }">
						 <span id="unitIdSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
				<div class="fitem">
				<label>获奖教师教工号：</label> 
				<input type="hidden" name="T461_bean.teaId" id="teaId"/>
				<input id="name" type="text" name="T461_bean.name" class='easyui-combobox' 
							data-options="valueField:'teaName',textField:'teaId',url:'pages/T411/loadT411',listHeight:'auto',editable:true,
							onSelect:function(){
							 	 document.getElementById('teaId').value=$(this).combobox('getText') ;
							 }">
				<span id="teaIdSpan"></span>
				</div></td>
			</tr>			
			<tr>
				<td>
					<div class="fitem">
						<label>获奖类别：</label> 
						<input class='easyui-combobox' id="awardType" name="T461_bean.awardType" 
							data-options="valueField:'indexId',textField:'awardType',url:'pages/DiAwardType/loadDiAwardType?type=5',listHeight:'auto',editable:false" panelHeight="auto">
						<span id="awardTypeSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>获奖级别：</label> 
						<input class='easyui-combobox' id="awardLevel" name="T461_bean.awardLevel" 
							data-options="valueField:'indexId',textField:'awardLevel',url:'pages/DiAwardLevel/loadDiAwardLevel',listHeight:'auto',editable:false" panelHeight="auto">
						<span id="awardLevelSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
 					<div class="fitem">
					<label>授予单位：</label> 
					<input id="awardFromUnit" type="text" name="T461_bean.awardFromUnit"
							class="easyui-validatebox" ><span id="awardFromUnitSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td >
					<div class="fitem">
						<label>获奖时间：</label> 
						<input class="easyui-datebox"  id="gainAwardTime" type="text" 
						name="T461_bean.gainAwardTime"  editable="false" />
						<span id="gainAwardTimeSpan"></span>
					</div>
				</td>
			</tr>
 			<tr>
				<td>
 					<div class="fitem">
					<label>批文号：</label> 
					<input id="appvlId" type="text" name="T461_bean.appvlId"
							class="easyui-validatebox" ><span id="appvlIdSpan"></span>
					</div>
				</td>
 			    <td class="empty"></td>
				<td>
 					<div class="fitem">
					<label>其他参与教师人数：</label> 
					<input id="otherTeaNum" type="text" name="T461_bean.otherTeaNum"
							class="easyui-validatebox" ><span id="otherTeamNumSpan"></span>
					</div>
				</td>
				</tr>
			<tr>
				<td colspan="3">
 					<div class="fitem">
					<label>其他成员：</label> 
					<textarea id="otherTeaInfo" name="T461_bean.otherTeaInfo" style="resize:none" cols="50" rows="5"></textarea>
					<span id="otherTeaInfoSpan"></span>
					</div>
				</td>
			</tr>		
			<tr>
				<td style="valign:left" colspan="3"><label>备注：</label>
					<textarea id="note" name="T461_bean.note" style="resize:none" cols="50" rows="10"></textarea>
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
