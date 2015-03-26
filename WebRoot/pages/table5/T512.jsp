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

<title>T512_CSInfo_TeaTea</title>
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
	<script type="text/javascript" src="js/table5/T512.js"></script>
</head>

<% request.setAttribute("CHECKTYPE",Constants.CTypeTwo); %>
<% request.setAttribute("NOCHECK",Constants.NO_CHECK); %>
<% request.setAttribute("PASS",Constants.PASS_CHECK); %>
<body style="overflow-y:scroll"  onload="myMarquee('T512','<%=request.getAttribute("CHECKTYPE") %>')">

<div  id="floatDiv">
        <span style="font:12px; font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;审核未通过提示消息：</span>
        <marquee id="marquee"  scrollAmount="1"  width="900"  height="40" direction="up"  style="color: red;"  onmouseover="stop()" onmouseout="start()">
        </marquee>       
  </div>
  <br/> 

	<table id="unverfiedData" title="待审核数据域审核未通过数据" class="easyui-datagrid"  
	url="pages/T512/auditingData?checkNum=<%=request.getAttribute("NOCHECK") %>" 
	 style="height: auto" toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="false">
		
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th  data-options="field:'seqNumber'" >编号</th>
				<th  data-options="field:'checkState'"   formatter="formatCheckState">审核状态</th>
				<th data-options="field:'term',align:'center'" rowspan="2">
			          学期
				</th>
				<th data-options="field:'CSUnit',align:'center'" rowspan="2">
				开课单位
				</th>
				<th data-options="field:'unitID',align:'center'" rowspan="2">
				单位号
				</th>
		     </tr>
		</thead>
		
		<thead>
			<tr>
				<th data-options="field:'CSMajorName',align:'center'" rowspan="2">
				上课专业名称
				</th>
				<th data-options="field:'CSMajorID',align:'center'" rowspan="2">
				上课专业代码
				</th>
				<th colspan="12">
			      1.本科课程情况   
				</th>
				<th colspan="8">
			      2.本科授课情况   
				</th>
				<th colspan="3">
			      3.使用教材   
				</th>
			</tr>
			<tr>
				<th data-options="field:'CSName',align:'center'">
				课程名称
				</th>
				<th data-options="field:'CSID',align:'center'">
				课程编号
				</th>
				<th data-options="field:'CSType',align:'center'">
				课程类别
				</th>
				<th data-options="field:'CSNature',align:'center' ">
				课程性质
				</th>
				<th data-options="field:'pubCSType',align:'center'">
				公选课类别
				</th>
				<th data-options="field:'isDoubleCS',align:'center'" formatter="formatBoolean">
			          是否双语授课
				</th>
				<th data-options="field:'credit',align:'center'">
				学分
				</th>
				<th data-options="field:'sumCSHour',align:'center'">
				总学时
				</th>
				<th data-options="field:'theoryCSHour',align:'center' ">
				理论学时
				</th>
				<th data-options="field:'praCSHour',align:'center'">
				实践学时
				</th>
				<th data-options="field:'examWay',align:'center' ">
				考核方式
				</th>
				<th data-options="field:'planTime',align:'center'">
				实习、设计时间
				</th>
				<th data-options="field:'CSGrade',align:'center'">
				授课年级
				</th>
				<th data-options="field:'CSClass',align:'center'">
				授课班级
				</th>
				<th data-options="field:'classID',align:'center'">
				开课班号
				</th>
				<th data-options="field:'classInfo',align:'center' ">
				合班情况
				</th>
				<th data-options="field:'stuNum',align:'center'">
				学生人数
				</th>
				<th data-options="field:'CSTea',align:'center'">
			        任课教师
				</th>
				<th data-options="field:'teaID',align:'center'">
			       教工号
				</th>
				<th data-options="field:'isAccordJob',align:'center'" formatter="formatBoolean">
			         是否符合岗位资格
				</th>
				<th data-options="field:'teaTitle',align:'center'">
			        教师职称
				</th>
				<th data-options="field:'bookUseInfo',align:'center'">
				使用情况
				</th>
				<th data-options="field:'isPlanbook',align:'center'" formatter="formatBoolean">
			         是否规划教材
				</th>
				<th data-options="field:'isAwardbook',align:'center'" formatter="formatBoolean">
				是否获奖教材
				</th>
			</tr>			
			</thead>
						  
	</table>
	
	<div id="toolbar" style="height:auto">
		<div style="float: left;">
		    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newCourse()">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editCourse()">编辑</a> 
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
	
	<table id="verfiedData"  class="easyui-datagrid"  	url="pages/T512/auditingData?checkNum=<%=request.getAttribute("PASS") %>"   style="height: auto;" >
			<thead data-options="frozen:true">
			<tr>	
			<th data-options="field:'ck',checkbox:true">选取</th>		
				<th  data-options="field:'seqNumber'" >编号</th>
				<th data-options="field:'term',align:'center'" rowspan="2">
			          学期
				</th>
				<th data-options="field:'CSUnit',align:'center'" rowspan="2">
				开课单位
				</th>
				<th data-options="field:'unitID',align:'center'" rowspan="2">
				单位号
				</th>
		     </tr>
		</thead>
		
	<thead>
			<tr>
				<th data-options="field:'CSMajorName',align:'center'" rowspan="2">
				上课专业名称
				</th>
				<th data-options="field:'CSMajorID',align:'center'" rowspan="2">
				上课专业代码
				</th>
				<th colspan="12">
			      1.本科课程情况   
				</th>
				<th colspan="8">
			      2.本科授课情况   
				</th>
				<th colspan="3">
			      3.使用教材   
				</th>
			</tr>
			<tr>
				<th data-options="field:'CSName',align:'center'">
				课程名称
				</th>
				<th data-options="field:'CSID',align:'center'">
				课程编号
				</th>
				<th data-options="field:'CSType',align:'center'">
				课程类别
				</th>
				<th data-options="field:'CSNature',align:'center' ">
				课程性质
				</th>
				<th data-options="field:'pubCSType',align:'center'">
				公选课类别
				</th>
				<th data-options="field:'isDoubleCS',align:'center'" formatter="formatBoolean">
			          是否双语授课
				</th>
				<th data-options="field:'credit',align:'center'">
				学分
				</th>
				<th data-options="field:'sumCSHour',align:'center'">
				总学时
				</th>
				<th data-options="field:'theoryCSHour',align:'center' ">
				理论学时
				</th>
				<th data-options="field:'praCSHour',align:'center'">
				实践学时
				</th>
				<th data-options="field:'examWay',align:'center' ">
				考核方式
				</th>
				<th data-options="field:'planTime',align:'center'">
				实习、设计时间
				</th>
				<th data-options="field:'CSGrade',align:'center'">
				授课年级
				</th>
				<th data-options="field:'CSClass',align:'center'">
				授课班级
				</th>
				<th data-options="field:'classID',align:'center'">
				开课班号
				</th>
				<th data-options="field:'classInfo',align:'center' ">
				合班情况
				</th>
				<th data-options="field:'stuNum',align:'center'">
				学生人数
				</th>
				<th data-options="field:'CSTea',align:'center'">
			        任课教师
				</th>
				<th data-options="field:'TeaID',align:'center'">
			       教工号
				</th>
				<th data-options="field:'isAccordJob',align:'center'" align:'center'" formatter="formatBoolean">
			         是否符合岗位资格
				</th>
				<th data-options="field:'teaTitle',align:'center'">
			        教师职称
				</th>
				<th data-options="field:'bookUseInfo',align:'center'">
				使用情况
				</th>
				<th data-options="field:'isPlanbook',align:'center'"  align:'center'" formatter="formatBoolean">
			         是否规划教材
				</th>
				<th data-options="field:'isAwardbook',align:'center'" align:'center'" formatter="formatBoolean">
				是否获奖教材
				</th>
			</tr>			
			</thead>
						  
	</table>
	
	<div id="toolbar2" style="float: right;">
	
	<form action='pages/T512/dataExport?excelName=<%=URLEncoder.encode("表5-1-2开课、授课情况（教学单位-教务处）","UTF-8")%>'   method="post"  id="exportForm" enctype="multipart/form-data"  style="float: right;">
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
		<h3 class="title1">开课、授课情况（教学单位-教务处）模板导入</h3>
		<div class="fitem" id="item1">
		  <form method="post"  id="batchForm" enctype="multipart/form-data">
		  <select class="easyui-combobox" id="cbYearContrast" name="selectYear" panelHeight="auto" style="width:80px; padding-top:5px; margin-top:10px;"></select>
				<input type="file" name="uploadFile" id="fileToUpload" class="easyui-validatebox" size="48" style="height: 24px;" required="true" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href='pages/T512/downloadModel?saveFile=<%=URLEncoder.encode("表5-1-2开课、授课情况（教学单位-教务处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
		</div>	
		<hr></hr>	
	   <h3 class="ftitle">开课、授课逐条导入</h3>
	    <form id="t512Form" method="post">
		<table>
			<tr>
				<td><div class="fitem">
				<label>学期：</label> 
				<input id="seqNumber" name="t512_Bean.SeqNumber" type="hidden"/>
				<input id="Time" name="t512_Bean.Time" type="hidden" value="0"/>
				<input id="FillUnitID" name="t512_Bean.FillUnitID" type="hidden" value="0"/>
				<input id="FillTeaID" name="t512_Bean.FillTeaID" type="hidden" value="0"/>
				<input id="CSUnit" name="t512_Bean.CSUnit" type="hidden" value="0"/>
				<input id="UnitID" name="t512_Bean.UnitID" type="hidden" value="0"/>
				   <input id="Term" type="text" name="t512_Bean.Term"
				   ><span id="TermSpan"></span>
				</div>
				</td>
				<td class="empty"></td>
				<td>
				<div class="fitem">
				<div class="fitem">
					<label>上课专业名称：</label> 
					<input type="hidden" name="t512_Bean.CSMajorName" id="CSMajorName"/>
					<input id="CSMajorID" type="text" name="t512_Bean.CSMajorID"
			            class='easyui-combobox' data-options="valueField:'majorNum',textField:'majorName',url:'pages/DiMajorTwo/loadDiMajorTwo',listHeight:'auto',editable:false,
							 onSelect:function(){
							   document.getElementById('CSMajorName').value=$(this).combobox('getText') ;
							 }">
					<span id="teaIdSpan"></span>
					</div>
					<!-- <label>开课单位：</label> 
					<input type="hidden" name="t512_Bean.CSUnit" id="CSUnit"/>
							<input id="UnitID" name="t512_Bean.UnitID" class='easyui-combobox' 
								data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDIDepartmentAca' ,listHeight:'auto',editable:false,
								onSelect:function(){
								     document.getElementById('CSUnit').value=$(this).combobox('getText') ;
								 }">
					<span id="CSUnitSpan"></span>
					</div>
					 --> 
				</td>
			</tr>
			<tr>
				<td colspan="3" align="left" height="30px"><h4>1.本科课程情况</h4></td>
			</tr>	
			<tr>
				<td>
					<div class="fitem">
					<label>课程名称：</label> 
						<input id="CSName" type="text" name="t512_Bean.CSName"
							><span id="CSNameSpan"></span>
					<span id="CSNameSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
					<td>
					<div class="fitem">
					<label>课程编号：</label> 
						<input id="CSID" type="text" name="t512_Bean.CSID"
							><span id="CSIDSpan"></span>
					<span id="CSIDSpan"></span>
					</div>
				</td>
				</tr>	
			<tr>
				<td>
				<div class="fitem">
					<label>课程类别：</label> 
						<input id="CSType" type="text" name="t512_Bean.CSType" class='easyui-combobox'  
						data-options="valueField:'indexId',textField:'courseCategories',url:'pages/DiCourseCategories/loadDiCourseCategories',listHeight:'auto',editable:false">
						<span id="CSTypeSpan"></span>
					</div>
					</td>
			<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>课程性质：</label> 
						<input id="CSNature" type="text" name="t512_Bean.CSNature"  
							 class='easyui-combobox' data-options="valueField:'indexId',textField:'courseChar',url:'pages/DiCourseChar/loadDiCourseChar',listHeight:'auto',editable:false"/>
						<span id="CSNatureSpan"></span>
					</div>
				</td>
					</tr>	
			<tr>
				<td>
					<div class="fitem">
						<label>公共课类别：</label> 
						<select class='easyui-combobox'  id="PubCSType" name="t512_Bean.PubCSType"  panelHeight="auto" editable="false" >
							<option value="无">无</option>
							<option value="理工科">理工科</option>
							<option value="人文社科类">人文社科类</option>
							<option value="体育保健类">体育保健类</option>
						</select>
						<span id="PubCSTypeSpan"></span>
					</div>
				</td>
			<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>是否双语授课：</label> 
						<select class='easyui-combobox' style="width:50px"  id="IsDoubleCS" name="t512_Bean.IsDoubleCS"  panelHeight="auto" editable="false" >
							<option value="true" selected = "selected">是</option>
							<option value="false">否</option>
						</select>	
							<span id="IsDoubleCSSpan"></span>
					</div>
				</td>
					</tr>	
			<tr>
				<td>
					<div class="fitem">
						<label>学分：</label> 
						<input id="Credit" name="t512_Bean.Credit" type="text" class="easyui-numberbox" data-options="min:0">
						<span id="CreditSpan"></span>
					</div>
				</td>
			<td class="empty"></td>
			  <td>
					<div class="fitem">
						<label>总学时：</label> 
						<input id="SumCSHour" name="t512_Bean.SumCSHour" type="text" class="easyui-numberbox" data-options="min:0">
						<span  id="SumCSHourSpan"></span>
					</div>
				</td>
					</tr>	
			<tr>
				 <td>
					<div class="fitem">
						<label>理论学时：</label> 
						<input id="TheoryCSHour" name="t512_Bean.TheoryCSHour" type="text" class="easyui-numberbox" data-options="min:0">
						<span  id="TheoryCSHourSpan"></span>
					</div>
				</td>
			<td class="empty"></td>
				 <td>
					<div class="fitem">
						<label>实践学时：</label> 
						<input id="PraCSHour" name="t512_Bean.PraCSHour" type="text" class="easyui-numberbox" data-options="min:0">
						<span  id="PraCSHourSpan"></span>
					</div>
				</td>
				</tr>	
			<tr>
				<td>
					<div class="fitem">
					<label>考核方式：</label> 
					<select class='easyui-combobox' style="width:60px" id="ExamWay" name="t512_Bean.ExamWay"  panelHeight="auto" editable="false" >
							<option value="考试" selected="selected">考试</option>
							<option value="考查">考查</option>
					</select><span id="ExamWaySpan"></span>
					</div>
				</td>
		<td class="empty"></td>
		        <td>
					<div class="fitem">
						<label>实习、设计时间：</label> 
						<input id="PlanTime" type="text" name="t512_Bean.PlanTime" >
						 <span id="PlanTimeSpan"></span>
					</div>
				</td>
		  </tr>
		  <tr>
				<td colspan="3" align="left" height="30px"><h4>2.本科授课情况</h4></td>
			</tr>
		  <tr>
				<td>
					<div class="fitem">
					<label>授课年级：</label> 
					<select class='easyui-combobox' style="width:50px" id="CSGrade" name="t512_Bean.CSGrade"  panelHeight="auto" editable="false" >
							<option value="1" selected="selected">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
					</select><span id="CSGradeSpan"></span>
					</div>
				</td>
					<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>授课班级：</label> 
						  <input id="CSClass" type="text" name="t512_Bean.CSClass"
				   ><span id="CSClassSpan"></span>
					</div>
				</td>	
			</tr>
			<tr>
			<td>
					<div class="fitem">
						<label>开课班号：</label> 
						  <input id="ClassID" type="text" name="t512_Bean.ClassID"
				   ><span id="ClassIDSpan"></span>
					</div>
				</td>	
				<td class="empty"></td>
				<td>
					<div class="fitem">
					<label>合班情况：</label> 
						<input id="ClassInfo" type="text" name="t512_Bean.ClassInfo"
							>
							<span id="ClassInfoSpan"></span>
					</div>
				</td>
				</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>学生人数：</label> 
						<input class="easyui-numbox"  id="StuNum" name="t512_Bean.StuNum"  panelHeight="auto">
						<span id="StuNumSpan"></span>
					</div>
				</td>
			<td class="empty"></td>
							<td>
				<div class="fitem">
				<label>教工号：</label> 
				<input type="hidden" name="t512_Bean.TeaID" id="TeaID"/>
				<input id="CSTea" type="text" name="t512_Bean.CSTea" class='easyui-combobox' 
							data-options="valueField:'teaName',textField:'teaId',url:'pages/T411/loadT411',listHeight:'auto',editable:true,
							onSelect:function(){
							 	 document.getElementById('TeaID').value=$(this).combobox('getText') ;
							 }">
				<span id="TeaIDSpan"></span>
				</div>
				</td>
			</tr>
			<tr>
           
				<td>
					<div class="fitem">
					<label>是否符合岗位资格：</label> 
					<select class='easyui-combobox' style="width:50px" id="IsAccordJob" name="t512_Bean.IsAccordJob"  panelHeight="auto" editable="false" >
							<option value="true" selected = "selected">是</option>
							<option value="false">否</option>
					</select><span id="IsAccordJobSpan"></span>
					</div>
				</td>
				 <td class="empty"></td>
				<td>
					<div class="fitem">
					<label>教师职称：</label> 
					<select class='easyui-combobox' style="width:70px" id="TeaTitle" name="t512_Bean.TeaTitle"  panelHeight="auto" editable="false" >
							<option value="正高级" selected = "seletced">正高级</option>
							<option value="副高级">副高级</option>
							<option value="中级">中级</option>
							<option value="初级">初级</option>
							<option value="未定级">未定级</option>
					</select>	
							<span id="TeaTitleSpan"></span>
					</div>
				</td>
			</tr>

		 
		 <tr>
				<td colspan="3" align="left" height="30px"><h4>3.使用教材</h4></td>
			</tr>
  		  <tr>
				<td>
					<div class="fitem">
					<label>使用情况：</label> 
					<select class='easyui-combobox' style="width:50px" id="BookUseInfo" name="t512_Bean.BookUseInfo"  panelHeight="auto" editable="false" >
							<option value="选用" selected="selected">选用</option>
							<option value="自编">自编</option>
					</select><span id="BookUseInfoSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
					<label>是否规划教材：</label> 
					<select class='easyui-combobox' style="width:50px"  id="IsPlanbook" name="t512_Bean.IsPlanbook"  panelHeight="auto" editable="false" >
							<option value="true" selected = "selected">是</option>
							<option value="false">否</option>
					</select><span id="IsPlanbookSpan"></span>
					</div>
				</td>
		 </tr>
			<tr>
				<td>
					<div class="fitem">
					<label>是否获奖教材：</label> 
					<select class='easyui-combobox' style="width:50px" id="IsAwardbook" name="t512_Bean.IsAwardbook"  panelHeight="auto" editable="false" >
							<option value="true" selected = "selected">是</option>
							<option value="false">否</option>
					</select><span id="IsAwardbookSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td style="valign:left" colspan="3"><label>备注：</label>
					<textarea id="Note" name="t512_Bean.Note" style="resize:none" cols="50" rows="10"></textarea>
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
