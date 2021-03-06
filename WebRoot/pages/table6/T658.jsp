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

<title>学习成果—参加国际会议</title>
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
	<script type="text/javascript" src="js/table6/T658.js"></script>
</head>
<% request.setAttribute("CHECKTYPE",Constants.CTypeTwo); %>
<% request.setAttribute("NOCHECK",Constants.NO_CHECK); %>
<% request.setAttribute("PASS",Constants.PASS_CHECK); %>
<body style="height: 100%'"  onload="myMarquee('T658','<%=request.getAttribute("CHECKTYPE") %>')">
  <div  id="floatDiv">
        <span style="font:12px; font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;审核未通过提示消息：</span>
        <marquee id="marquee"  scrollAmount="1"  width="900"  height="40" direction="up"  style="color: red;"  onmouseover="stop()" onmouseout="start()">
        </marquee>       
  </div>
  <br/> 
	<table id="unverfiedData" title="待审核数据域审核未通过数据" class="easyui-datagrid" 
	url="pages/T658/loadData?checkNum=<%=request.getAttribute("NOCHECK") %>"  style="height: auto;">
	
		<thead data-options="frozen:true">
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber">编号</th>
					<th  data-options="field:'checkState'"   formatter="formatCheckState">审核状态</th>
				<th field="teaUnit">教学单位</th>
				<th field="unitId">单位号</th>
			</tr>
			</thead>	
			<thead>
				<tr>
				<th field="conferenceName">会议名称</th>
				<th field="paperTitle">发表论文题目</th>			
				<th field="holdTime" formatter="formattime">举办时间</th>		
				<th field="holdPlace">举办地点</th>		
				<th field="holdUnit">举办单位</th>
				<th field="conferenceLevel">会议级别</th>
				<th field="awardStuName">学生姓名学号</th>
				<th field="awardStuNum">参与学生人数</th>
				<th field="guideTeaName">指导教师</th>
				<th field="guideTeaNum">指导教师人数</th>
				<th field="note">备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="height:auto">
		<div style="float: left;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newItem()">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editItem()">编辑</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteByIds()">删除</a>
		</div>
		 <div style="float: right;">
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
	</div>
	
		<!--审核通过数据-->
	<table id="verfiedData"  class="easyui-datagrid"  
	url="pages/T658/loadData?checkNum=<%=request.getAttribute("PASS") %>"   style="height: auto;" >
			<thead data-options="frozen:true">
			<tr>
			<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber">编号</th>
				<th field="teaUnit">教学单位</th>
				<th field="unitId">单位号</th>
			</tr>
			</thead>	
			<thead>
				<tr>
				<th field="conferenceName">会议名称</th>
				<th field="paperTitle">发表论文题目</th>			
				<th field="holdTime" formatter="formattime">举办时间</th>		
				<th field="holdPlace">举办地点</th>		
				<th field="holdUnit">举办单位</th>
				<th field="conferenceLevel">会议级别</th>
				<th field="awardStuName">学生姓名学号</th>
				<th field="awardStuNum">参与学生人数</th>
				<th field="guideTeaName">指导教师</th>
				<th field="guideTeaNum">指导教师人数</th>
				<th field="note">备注</th>
				<th field="time" formatter="formattime">时间</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar2" style="float: right;">
	<form action='pages/T658/dataExport?excelName=<%=URLEncoder.encode("表6-5-8学习成果—参加国际会议（教学单位-国际交流与合作处）","UTF-8")%>'   method="post"  id="exportForm" enctype="multipart/form-data"  style="float: right;">
					  <select class="easyui-combobox"  id="cbYearContrast1" name="selectYear"  editable=false ></select>&nbsp;&nbsp;
						<a href='javascript:submitForm()'   style="font:12px;color: black;text-decoration:none;" >
								数据导出
						</a> &nbsp;&nbsp;&nbsp;&nbsp;		
			</form>
	</div>
	
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="title1">参加国际会议情况批量导入</h3>
		<div class="fitem" id="item1">
			<form id="batchForm" method="post" enctype="multipart/form-data">
				<select class="easyui-combobox"  id="cbYearContrast" name="selectYear" editable="false"></select>
				<input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox" size="48" style="height: 24px;"
					validType="fileType['xls']" required="true" invalidMessage="请选择Excel格式的文件" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href='pages/T658/downloadModel?saveFile=<%=URLEncoder.encode("表6-5-8学习成果—参加国际会议（教学单位-国际交流与合作处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
		</div>
	<hr></hr>
		<div></div>
		<h3 class="title1">参加国际会议情况逐条导入</h3>
		<form id="addItemForm" method="post">
		<table>
			<tr>
				<td>
					<div class="fitem">
						<label>会议名称：</label> 
						<input id="seqNumber" type="hidden" name="T658_bean.seqNumber" value = "0">	
						<input id="fillUnitID" type="hidden" name="T658_bean.fillUnitID" value = "0">	
						<input id="unitId" type="hidden" name="T658_bean.unitId" value = "0">	
						<input id="teaUnit" type="hidden" name="T658_bean.teaUnit" value = "0">	
						<input id="conferenceName" name="T658_bean.conferenceName" class='easyui-validatebox'>
						<span id="conferenceNameSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>发表论文题目：</label> 
						<input id="paperTitle" name="T658_bean.paperTitle" class='easyui-validatebox'><span id="paperTitleSpan" ></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>举办时间：</label> 
						<input id="holdTime" name="T658_bean.holdTime" class='easyui-datebox' type="text" editable="false"><span id="holdTimeSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>			
					<div class="fitem">
						<label>举办地点：</label> 
						<input id="holdPlace" name="T658_bean.holdPlace" 
							 class='easyui-validatebox' ><span id="holdPlaceSpan"></span>
					</div>
				</td>
			</tr>
			
			<tr>
				<td>			
					<div class="fitem">
						<label>举办单位：</label> 
						<input id="holdUnit" name="T658_bean.holdUnit" 
							 class='easyui-validatebox' ><span id="holdUnitSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>会议级别：</label> 
						<input class='easyui-combobox' id="conferenceLevel" name="T658_bean.conferenceLevel" 
							data-options="valueField:'indexId',textField:'awardLevel',url:'pages/DiAwardLevel/loadDiAwardLevel',listHeight:'auto',editable:false">
						<span id="conferenceLevelSpan"></span>
					</div>
				</td>
			</tr>
			
			<tr>
				<td>			
					<div class="fitem">
						<label>学生姓名学号：</label> 
						<input id="awardStuName" name="T658_bean.awardStuName" class='easyui-validatebox'><span id="awardStuNameSpan"></span>
						
					</div>
				</td>
				<td class="empty"></td>
				<td>			
					<div class="fitem">
						<label>参与学生人数：</label> 
						<input id="awardStuNum" name="T658_bean.awardStuNum" class="easyui-numberbox" type="text" data-options="min:0"> 
						<span id="awardStuNumSpan"></span>
					</div>
				</td>
			</tr>
			
			<tr>
				<td>			
					<div class="fitem">
						<label>指导教师：</label> 
						<input id="guideTeaName" name="T658_bean.guideTeaName" class='easyui-validatebox'>
						<span id="guideTeaNameSpan"></span>
						
					</div>
				</td>
				
				<td class="empty"></td>
				<td>			
					<div class="fitem">
						<label>指导教师人数：</label> 
						<input id="guideTeaNum" name="T658_bean.guideTeaNum" class="easyui-numberbox" type="text" data-options="min:0"> 
						<span id="guideTeaNumSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>填写时间：</label> 
						<input class="easyui-datebox"  id="time" type="text" 
						name="T658_bean.time"  editable="false" />
						<span id="timeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td style="valign:left" colspan="3">
				<label>备注：</label>
					<textarea id="note" name="T658_bean.note" style="resize:none" cols="50" rows="10"></textarea>
					<span id="noteSpan"></span>
				</td>
			</tr>
		</table>
		</form>
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
