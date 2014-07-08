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

<title>T534</title>
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
	<script type="text/javascript" src="js/table5/T534.js"></script>
</head>

<body style="height: 100%'" >
	<table  id="unverfiedData"  class="easyui-datagrid"  url="pages/T534/auditingData"  style="height: auto"  >
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th  data-options="field:'seqNumber'" >编号</th>
		     </tr>
		</thead>
		<thead>
				<tr>					
					<th data-options="field:'teaUnit'">
						教学单位
					</th>
					<th data-options="field:'unitID'">
						单位号
					</th>
					<th data-options="field:'majorName'">
						专业名称
					</th>
					<th data-options="field:'majorID'">
						专业代码
					</th>
					<th data-options="field:'teaName'">
						教师姓名
					</th>
					<th data-options="field:'teaID'">
						教工号
					</th>
					<th data-options="field:'isOutEmploy'" formatter="formatBoolean">
						是否外聘
					</th>
					<th data-options="field:'education'">
						学历
					</th>
					<th data-options="field:'degree'">
						学位
					</th>
					<th data-options="field:'title'">
						职称
					</th>
					<th data-options="field:'isExcellent'" formatter="formatBoolean">
						是否获评校级优秀指导教师
					</th>
					<th data-options="field:'trainIssueNum'">
						指导毕业综合训练课题数量
					</th>
					<th data-options="field:'socialNum'">
						其中在实验、实习、工程实践和社会调查等社会实践中完成数
					</th>
					<th data-options="field:'guideStuNum'">
						指导学生人数
					</th>
					<th data-options="field:'gainBestNum'">
						其中：学生获优秀毕业设计人数
					</th>
					<th data-options="field:'gainTime'" formatter="formattime">
						获评时间
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
		<form method="post"  id="searchForm"   style="float: right;height: 24px;"  >
		 	编号: <input  id="seqNum"   name="seqNum"  class="easyui-box" style="width:80px"/>
			起始日期: <input id ="startTime"  name ="startTime"   class="easyui-datebox" style="width:80px"/>
			结束日期: <input id="endTime"  name="endTime" class="easyui-datebox" style="width:80px"/>
			<a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-search"  plain="true" onclick="reloadgrid()">查询</a>
		</form>
	</div>
	
	<table id="verfiedData"  class="easyui-datagrid"  url=""  style="height: auto;" >
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th  data-options="field:'seqNumber'" >编号</th>
		     </tr>
		</thead>
		<thead>
				<tr>					
					<th data-options="field:'TeaUnit'">
						教学单位
					</th>
					<th data-options="field:'UnitID'">
						单位号
					</th>
					<th data-options="field:'MajorName'">
						专业名称
					</th>
					<th data-options="field:'majorID'">
						专业代码
					</th>
					<th data-options="field:'TeaName'">
						教师姓名
					</th>
					<th data-options="field:'TeaID'">
						教工号
					</th>
					<th data-options="field:'IsOutEmploy'">
						是否外聘
					</th>
					<th data-options="field:'Education'">
						学历
					</th>
					<th data-options="field:'Degree'">
						学位
					</th>
					<th data-options="field:'Title'">
						职称
					</th>
					<th data-options="field:'IsExcellent'">
						是否获评校级优秀指导教师
					</th>
					<th data-options="field:'TrainIssueNum'">
						指导毕业综合训练课题数量
					</th>
					<th data-options="field:'SociaPraFinishNum'">
						其中在实验、实习、工程实践和社会调查等社会实践中完成数
					</th>
					<th data-options="field:'GuideStuNum'">
						指导学生人数
					</th>
					<th data-options="field:'GainBestGraDesinNum'">
						其中：学生获优秀毕业设计人数
					</th>
					<th data-options="field:'GainTime'">
						获评时间
					</th>
					
					<th data-options="field:'note'">
						备注
					</th>
				</tr>
			</thead>
	</table>
	<div id="toolbar2" style="float: right;">
		<a href='pages/T534/dataExport?excelName=<%=URLEncoder.encode("表5-3-4分专业毕业综合训练情况（教学单位-教务处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="">高级检索</a>
	</div>
	
	<!--添加弹出框-->
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3  class="title1">分专业毕业综合训练情况模板导入</h3>
		<div class="fitem" id="item1">
		  <form method="post"  id="batchForm" enctype="multipart/form-data">
		  		<select class="easyui-combobox"  id="cbYearContrast" name="selectYear" editable="false"></select>
				<input type="file" name="uploadFile" id="fileToUpload" class="easyui-validatebox" size="48" style="height: 24px;" required="true" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href='pages/T534/downloadModel?saveFile=<%=URLEncoder.encode("表5-3-4分专业毕业综合训练情况（教学单位-教务处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
		</div>	
		<hr></hr>	
	   <h3  class="title1">分专业毕业综合训练情况逐条导入</h3>
	   <form id="addForm" method="post">
		<table>
			<tr>
				<td>
					<input type="hidden" name="t534Bean.SeqNumber"  id="seqNumber"/>
					<div class="fitem">
						<label>教学单位：</label> 
						<input type="hidden" name="t534Bean.TeaUnit" id="TeaUnit"/>
						<input id="UnitID" type="text" name="t534Bean.UnitID" class='easyui-combobox' 
							data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment',listHeight:'auto',editable:true,
							onSelect:function(){
							    document.getElementById('TeaUnit').value=$(this).combobox('getText') ;
							 }">
						 <span id="teaUnitIDSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>专业名称：</label> 
						<input type="hidden" name="t534Bean.MajorName" id="MajorName"/>
						<input id="MajorID" type="text" name="t534Bean.MajorID" class='easyui-combobox' 
							data-options="valueField:'majorNum',textField:'majorName',url:'pages/DiMajorTwo/loadDiMajorTwo',listHeight:'auto',editable:true,
							onSelect:function(){
							 	 document.getElementById('MajorName').value=$(this).combobox('getText') ;
							 }">
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
				<div class="fitem">
				<label>教师姓名：</label> 
				<input type="hidden" name="t534Bean.TeaName" id="TeaName"/>
				<input id="TeaID" type="text" name="t534Bean.TeaID" class='easyui-combobox' 
							data-options="valueField:'teaId',textField:'teaName',url:'pages/T411/loadT411',listHeight:'auto',editable:true,
							onSelect:function(){
							 	 document.getElementById('TeaName').value=$(this).combobox('getText') ;
							 }">
				<span id="teaIdSpan"></span>
				</div></td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>是否外聘：</label> 
						<select class='easyui-combobox' style="width:50px"  id="IsOutEmploy" name="t534bean.IsOutEmploy"  panelHeight="auto" editable="false" >
							<option value="true" selected = "selected">是</option>
							<option value="false">否</option>
						</select>	
							<span id="IsOutEmploySpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>学历：</label> 
						<input class='easyui-combobox'  id="Education" name="t534Bean.Education" 
							data-options="valueField:'indexId',textField:'education',url:'pages/DiEducation/loadDiEducation',listHeight:'auto',editable:false" panelHeight="auto">
						<span id="EducationSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>学位：</label> 
						<input class='easyui-combobox'  id="Degree" name="t534Bean.Degree" 
							data-options="valueField:'indexId',textField:'degree',url:'pages/DiDegree/loadDiDegree',listHeight:'auto',editable:false" panelHeight="auto">
						<span id="DegreeSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>职称：</label> 
						<input class='easyui-combobox'  id="Title" name="t534Bean.Title" 
							data-options="valueField:'indexId',textField:'titleName',url:'pages/DiTitleName/loadDiTitleName',listHeight:'auto',editable:false" panelHeight="auto">
						<span id="TitleSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>校级优秀指导教师：</label> 
						<select class='easyui-combobox' style="width:50px"  id="IsExcellent" name="t534Bean.IsExcellent"  panelHeight="auto" editable="false" >
							<option value="true" selected = "selected">是</option>
							<option value="false">否</option>
						</select>	
							<span id="IsExcellentSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
					<label>综合训练课题数量:</label>
					<input id="TrainIssueNum" name="t534Bean.TrainIssueNum" type="text" class="easyui-validatebox">
					<span id="TrainIssueNumSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
					<label>社会实践中完成数:</label>
					<input id="SocialNum" name="t534Bean.SocialNum" type="text" class="easyui-validatebox">
					<span id="SocialNumSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
					<label>指导学生人数:</label>
					<input id="GuideStuNum" name="t534Bean.GuideStuNum" type="text" class="easyui-validatebox">
					<span id="GuideStuNumSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>优秀毕业设计人数：</label> 
						<select class='easyui-combobox' style="width:50px"  id="GainBestNum" name="t534Bean.GainBestNum"  panelHeight="auto" editable="false" >
							<option value="6" selected = "selected">6</option>
							<option value="5">5</option>
							<option value="4">4</option>
							<option value="3">3</option>
							<option value="2">2</option>
							<option value="1">1</option>
							<option value="0">0</option>
						</select>	
							<span id="GainBestGraDesinNumSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
					<label>获评时间:</label>
					<input id="GainTime" name="t534Bean.GainTime" type="text" class="easyui-datebox">
					<span id="GainTimeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td style="valign:left" colspan="3"><label>备注：</label>
					<textarea id="Note" name="t534Bean.Note" style="resize:none" cols="50" rows="10"></textarea>
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
</html>
