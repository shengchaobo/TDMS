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

<title>学习成果—本科生竞赛获奖情况</title>
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
	<script type="text/javascript" src="js/table6/T651.js"></script>
</head>


<body>
	<table id="commomData" title="学习成果—本科生竞赛获奖情况" class="easyui-datagrid" url="pages/T651/loadData" style="height: auto;">

		<thead data-options="frozen:true">
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber">序号</th>
				<th field="teaUnit">教学单位</th>
				<th field="unitId">单位号</th>

			</tr>
			</thead>	
			<thead>
				<tr>
				<th field="competiType">竞赛类型</th>
				<th field="competiName">赛事名称</th>
				<th field="awardItem">获奖项目</th>
				<th field="awardLevel">级别</th>				
				<th field="awardGrade">等级</th>
				<th field="awardFromUnit">授予单位</th>
				<th field="awardTime" formatter="formattime">获奖时间</th>
				<th field="awardStuName">获奖学生姓名</th>
				<th field="awardStuNum">获奖学生数</th>
				<th field="guideTeaName">指导教师</th>
				<th field="guideTeaNum">指导教师数</th>
				<th field="note">备注</th>
				<th field="fillUnitID">填写单位</th>	
				<th field="time" formatter="formattime">时间</th>
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
		 	教学单位: <input id="searchItem" class="easyui-box" style="width:80px"/>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="reloadgrid()">查询</a>
		</div>
	</div>
	
		<!--审核通过数据-->
	<table id="verfiedData"  class="easyui-datagrid"  url=""  style="height: auto;" >
		<thead data-options="frozen:true">
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber">序号</th>
				<th field="teaUnit">教学单位</th>
				<th field="unitId">单位号</th>

			</tr>
			</thead>	
			<thead>
				<tr>
				<th field="competiType">竞赛类型</th>
				<th field="competiName">赛事名称</th>
				<th field="awardItem">获奖项目</th>
				<th field="awardLevel">级别</th>				
				<th field="awardGrade">等级</th>
				<th field="awardFromUnit">授予单位</th>
				<th field="awardTime" formatter="formattime">获奖时间</th>
				<th field="awardStuName">获奖学生姓名</th>
				<th field="awardStuNum">获奖学生数</th>
				<th field="guideTeaName">指导教师</th>
				<th field="guideTeaNum">指导教师数</th>
				<th field="note">备注</th>
				<th field="fillUnitID">填写单位</th>	
				<th field="time" formatter="formattime">时间</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar2" style="float: right;">
		<a href='pages/T651/dataExport?excelName=<%=URLEncoder.encode("表6-5-1学习成果—本科生竞赛获奖情况（教学单位-团委）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="">高级检索</a>
	</div>
	
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="title1">本科生竞赛获奖情况批量导入</h3>
		<div class="fitem" id="item1">
			<form id="batchForm" method="post" enctype="multipart/form-data">
				<select class="easyui-combobox"  id="cbYearContrast" name="selectYear" editable="false"></select>
				<input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox" size="48" style="height: 24px;"
					validType="fileType['xls']" required="true" invalidMessage="请选择Excel格式的文件" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">导入</a>
				<a href='pages/T651/downloadModel?saveFile=<%=URLEncoder.encode("表6-5-1学习成果—本科生竞赛获奖情况（教学单位-团委）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
		</div>
		<hr></hr>	
		<div></div>
		<h3 class="title1">本科生竞赛获奖情况逐条导入</h3>
		<form id="addItemForm" method="post">
		<table>
			<tr>
				<td>
					<div class="fitem">
						<label>教学单位：</label> 
						<input id="seqNumber" type="hidden" name="T651_bean.seqNumber">	
						<input id="teaUnit" type="hidden" name="T651_bean.teaUnit">										
						<input id="unitId" type="text" name="T651_bean.unitId" class='easyui-combobox'
							data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment',listHeight:'auto',editable:false,
							onSelect:function(){
							 	 document.getElementById('teaUnit').value=$(this).combobox('getText') ;
							 }">
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>竞赛类型：</label> 
										
						<select id="competiType" type="text" name="T651_bean.competiType" class='easyui-combobox' panelHeight="auto" editable="false">
							<option value="55000">本科生学科竞赛</option>
							<option value="55001">本科生创新活动</option>
							<option value="55002">本科生文艺、体育竞赛</option>
							<option value="55003">其他</option>
						</select>
						<span id="competiTypeSpan"></span>
					</div>
				</td>
			</tr>

			
			<tr>
				<td>
					<div class="fitem">
						<label>赛事名称：</label> 
						<input id="competiName" name="T651_bean.competiName" class='easyui-validatebox'><span id="competiNameSpan" ></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>获奖项目：</label> 
						<input id="awardItem" name="T651_bean.awardItem" class='easyui-validatebox'><span id="awardItemSpan"></span>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div class="fitem">
						<label>级别：</label> 
						<select id="awardLevel" type="text" name="T651_bean.awardLevel" class='easyui-combobox'  panelHeight="auto" editable="false">
							<option value="50000">国际级</option>
							<option value="50001">国家级</option>
							<option value="50002">省部级</option>
							<option value="50003">市级</option>
							<option value="50004">校级</option>
							<option value="50005">系级</option>
							<option value="50006">其他</option>
						</select>
						<span id="awardLevelSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>			
					<div class="fitem">
						<label>等级：</label> 
						<input id="awardGrade" name="T651_bean.awardGrade" 
							 class='easyui-validatebox'><span id="awardGradeSpan"></span>
					</div>
				</td>

			</tr>

			
			<tr>
				<td>			
					<div class="fitem">
						<label>授予单位：</label> 
						<input id="awardFromUnit" name="T651_bean.awardFromUnit" 
							 class='easyui-validatebox'><span id="awardFromUnitSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>			
					<div class="fitem">
						<label>获奖时间：</label> 
						<input id="awardTime" name="T651_bean.awardTime" class='easyui-datebox'><span id="awardTimeSpan"></span>
						
					</div>
				</td>
			</tr>
			<tr>
				<td>			
					<div class="fitem">
						<label>获奖学生姓名：</label> 
						<input id="awardStuName" name="T651_bean.awardStuName" class='easyui-validatebox'>
						<span id="awardStuNameSpan">
						
					</div>
				</td>
				<td class="empty"></td>
				<td>			
					<div class="fitem">
						<label>获奖学生数：</label> 
						<input id="awardStuNum" name="T651_bean.awardStuNum" 
							 class='easyui-validatebox'><span id="awardStuNumSpan"></span>
					</div>
				</td>
			</tr>	
			<tr>
				<td>			
					<div class="fitem">
						<label>指导教师：</label> 
						<input id="guideTeaName" name="T651_bean.guideTeaName" 
							 class='easyui-validatebox'><span id="guideTeaNameSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>			
					<div class="fitem">
						<label>指导教师数：</label> 
						<input id="guideTeaNum" name="T651_bean.guideTeaNum" 
							 class='easyui-validatebox'><span id="guideTeaNumSpan"></span>
					</div>
				</td>
			</tr>

			<tr>
			
				<td>			
					<div class="fitem">
						<label>填写单位：</label> 
						<input id="fillUnitID" name="T651_bean.fillUnitID" 
							 class='easyui-validatebox'><span id="fillUnitIDSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>填写时间：</label> 
						<input class="easyui-datebox"  id="time" type="text" 
						name="T651_bean.time"  editable="false" />
						<span id="timeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td style="valign:left" colspan="3"><label>备注：</label>
					<textarea id="note" name="T651_bean.note" style="resize:none" cols="50" rows="10"></textarea>
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

</html>
