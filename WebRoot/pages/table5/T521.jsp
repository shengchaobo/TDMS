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

<title>T521</title>
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
	<script type="text/javascript" src="js/table5/T521.js"></script>
</head>
<% request.setAttribute("CHECKTYPE",Constants.CTypeOne); %>
<% request.setAttribute("NOCHECK",Constants.NO_CHECK); %>
<% request.setAttribute("PASS",Constants.PASS_CHECK); %>
<body style="height: 100%'"   onload="myMarquee('T521','<%=request.getAttribute("CHECKTYPE") %>')">
<div  id="floatDiv">
        <span style="font:12px; font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;审核未通过提示消息：</span>
        <marquee id="marquee"  scrollAmount="1"  width="900"  height="40" direction="up"  style="color: red;"  onmouseover="stop()" onmouseout="start()">
        </marquee>       
  </div>
  <br/> 

	<table  id="unverfiedData"  class="easyui-datagrid"  url="pages/T521/auditingData?checkNum=<%=request.getAttribute("NOCHECK") %>"   style="height: auto"  >
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th  data-options="field:'seqNumber'" >编号</th>
				<th  data-options="field:'checkState'"   formatter="formatCheckState">审核状态</th>
		     </tr>
		</thead>
		<thead>
				<tr>					
					<th data-options="field:'CSType'">
						类型
					</th>
					<th data-options="field:'CSName'">
						课程名称
					</th>
					<th data-options="field:'CSID'">
						课程编号
					</th>
					<th data-options="field:'CSLevel'">
						级别
					</th>
					<th data-options="field:'leader'">
					           负责人
					</th>
					<th data-options="field:'teaID'">
						教工号
					</th>
					<th data-options="field:'joinTeaNum'">
						参与教师总人数
					</th>
					<th data-options="field:'otherTea'">
						其他参与教师
					</th>
					<th data-options="field:'CSUrl'">
						课程访问链接
					</th>
					<th data-options="field:'appvlTime'" formatter="formattime">
						获准时间
					</th>
					<th data-options="field:'receptTime'" formatter="formattime">
					          验收时间
					</th>
					<th data-options="field:'teaUnit'">
						所属教学单位
					</th>
					<th data-options="field:'unitID'">
					          单位号
					</th>
					<th data-options="field:'appvlID'">
						批文号
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
	
	<table id="verfiedData"  class="easyui-datagrid"  url="pages/T521/auditingData?checkNum=<%=request.getAttribute("PASS") %>"  style="height: auto;" >
		<thead data-options="frozen:true">
			<tr>	
			<th data-options="field:'ck',checkbox:true">选取</th>		
				<th  data-options="field:'seqNumber'" >编号</th>
		     </tr>
		</thead>
		<thead>
				<tr>					
					<th data-options="field:'CSType'">
						类型
					</th>
					<th data-options="field:'CSName'">
						课程名称
					</th>
					<th data-options="field:'CSID'">
						课程编号
					</th>
					<th data-options="field:'CSLevel'">
						级别
					</th>
					<th data-options="field:'leader'">
					           负责人
					</th>
					<th data-options="field:'teaID'">
						教工号
					</th>
					<th data-options="field:'joinTeaNum'">
						参与教师总人数
					</th>
					<th data-options="field:'otherTea'">
						其他参与教师
					</th>
					<th data-options="field:'CSUrl'">
						课程访问链接
					</th>
					<th data-options="field:'appvlTime'" formatter="formattime">
						获准时间
					</th>
					<th data-options="field:'receptTime'" formatter="formattime">
					          验收时间
					</th>
					<th data-options="field:'teaUnit'">
						所属教学单位
					</th>
					<th data-options="field:'unitID'">
					          单位号
					</th>
					<th data-options="field:'appvlID'">
						批文号
					</th>
					<th data-options="field:'note'">
						备注
					</th>
				</tr>
			</thead>
	</table>
	<div id="toolbar2" style="float: right;">
	
	<form action='pages/T521/dataExport?excelName=<%=URLEncoder.encode("表5-2-1课程建设情况（教务处）","UTF-8")%>'   method="post"  id="exportForm" enctype="multipart/form-data"  style="float: right;">
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
		<h3  class="title1">课程建设情况批量导入</h3>
		<div class="fitem" id="item1">
		  <form method="post"  id="batchForm" enctype="multipart/form-data">
		  		<select class="easyui-combobox"  id="cbYearContrast" name="selectYear"></select>
				<input type="file" name="uploadFile" id="fileToUpload" class="easyui-validatebox" size="48" style="height: 24px;" required="true" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href='pages/T521/downloadModel?saveFile=<%=URLEncoder.encode("表5-2-1课程建设情况（教务处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
		</div>	
		<hr></hr>	
	   <h3  class="title1">课程建设逐条导入</h3>
	   <form id="addForm" method="post">
		<table>
			<tr>
				<td>
					<input type="hidden" name="t521Bean.SeqNumber"  id="seqNumber" value="0"/>
					<input type="hidden" name="t521Bean.Time"  id="Time"/>
					<div class="fitem">
						<label>类型：</label> 
						<select class='easyui-combobox'  id="CStype" name="t521Bean.CSType"  panelHeight="auto" editable="false" >
							<option value="精品（优秀）课程">精品（优秀）课程</option>
							<option value="精品视频公开课">精品视频公开课</option>
							<option value="微课">微课</option>
							<option value="双语教学示范课程">双语教学示范课程</option>
							<option value="重点建设课程">重点建设课程</option>
							<option value="重点建设通识任选课程">重点建设通识任选课程</option>
							<option value="其他">其他</option>
						</select>
						 <span id="CStypeSpan"></span>
					</div>
				</td>
					
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>级别：</label> 
						<input id="CSLevel" type="text" name="t521Bean.CSLevel" class='easyui-combobox' 
							data-options="valueField:'indexId',textField:'awardLevel',url:'pages/DiAwardLevel/loadDiAwardLevel',listHeight:'auto',editable:false">
						<span id="CSLevelSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td >
					<div class="fitem">
					<label>课程名称：</label> 
					<input id="CSName" type="text" name="t521Bean.CSName" class="easyui-validatebox" >
					<span id="CSNameSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td >
					<div class="fitem">
					<label>课程编号：</label> 
					<input id="CSID" type="text" name="t521Bean.CSID" class="easyui-validatebox" >
					<span id="CSIDSpan"></span>
					</div>
				</td>
			</tr>
			
			<tr>
				<td>
					<div class="fitem">
					<label>负责人教工号：</label> 
					<input type="hidden" name="t521Bean.TeaID" id="TeaID"/>
						<input id="Leader" type="text" name="t521Bean.Leader" class='easyui-combobox' 
							data-options="valueField:'teaName',textField:'teaId',url:'pages/T411/loadT411' ,listHeight:'auto',editable:false,
							onSelect:function(){
							   document.getElementById('TeaID').value=$(this).combobox('getText')  ;
							 }">
					<span id="TeaIDSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				 <td>
					<div class="fitem">
					<label>课程访问链接：</label> 
					<input id="CSUrl" type="text" name="t521Bean.CSUrl" class="easyui-validatebox"  >
					<span id="CSUrlSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>获准时间：</label> 
						 <input class="easyui-datebox" id="AppvlTime" name="t521Bean.AppvlTime" editable="false"> 
						 <span id="AppvlTimeSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>验收时间：</label> 
						 <input class="easyui-datebox" id="ReceptTime" name="t521Bean.ReceptTime" editable="false">
						 <span id="ReceptTimeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
					<label>所属教学单位：</label> 
						<input type="hidden" name="t521Bean.TeaUnit" id="TeaUnit"/>
						<input id="UnitID" type="text" name="t521Bean.UnitID" class='easyui-combobox' 
							data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDIDepartmentAca' ,listHeight:'auto',editable:false,
							onSelect:function(){
							    $('#TeaUnit').val($(this).combobox('getText')) ;
							 }">
					<span id="CSNameSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
					<label>批文号：</label> 
					<input id="AppvlID" type="text" name="t521Bean.AppvlID" class="easyui-validatebox"  >
					<span id="AppvlIDSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				 <td>
					<div class="fitem">
						<label>参与教师总人数：</label> 
						<select class='easyui-combobox'  id="JoinTeaNum" name="t521Bean.JoinTeaNum" style="width:50px" panelHeight="auto" editable="false" >
							<option value="4">4</option>
							<option value="3">3</option>
							<option value="2">2</option>
							<option value="1">1</option>
							<option value="0">0</option>
						</select>
						 <span id="JoinTeaNumSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td style="valign:left" colspan="3">
					<div class="fitem">
					<label>其他参与教师：</label> 
					<textarea id="OtherTea" name="t521Bean.OtherTea" style="resize:none" cols="50" rows="10"></textarea>
					<span id="OtherTeaSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td style="valign:left" colspan="3">
				<label>备注：</label>
					<textarea id="Note" name="t521Bean.Note" style="resize:none" cols="50" rows="10"></textarea>
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
