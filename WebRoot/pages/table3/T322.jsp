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
    
    <title>My JSP 'T311.jsp' starting page</title>
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
</head>
<body style="overflow-y:scroll">
	<table id="unverfiedData" class="easyui-datagrid"  url="pages/UndergraMajorInfoTea/auditingData" >
		<thead data-options="frozen:true">
			<tr>
				<th data-options="field:'ck',checkbox:true" >选取</th>
				<th field="seqNumber" >编号</th>
				<th field="majorName" >专业名称</th>
				<th field="majorID" >专业代码</th>
			</tr>
		</thead>
		<thead>
			<tr>	
				<th field="majorVersion" >代码版本</th>
				<th field="majorField" >专业方向名称</th>
				<th field="majorFieldID" >专业方向号</th>
				<th field="majorSetTime"  formatter="formattime">专业设置时间</th>
				<th field="majorAppvlID"  >批文号</th>
				<th field="majorDurition" >学制</th>
				<th field="majorDegreeType" >学位授予门类</th>
				<th field="majorAdmisTime"  formatter="formattime">开始招生时间</th>
				<th field="majorState" >招生状态</th>
				<th field="stopAdmisTime"  formatter="formattime">停止招生时间</th>
				<th field="isNewMajor" formatter="booleanstr" >是否新办专业</th>
				<th field="appvlYear"  formatter="formattime">批准建设年度</th>
				<th field="buildAppvlID" >建设批文号</th>
				<th field="majorLevel" >级别</th>
				<th field="type" >类型</th>
				<th field="field" >领域方向</th>
				<th field="leader"  >建设负责人</th>
				<th field="teaID" >教工号</th>
				<th field="checkTime"  formatter="formattime">验收时间</th>
				<th field="checkAppvlID" >验收批文号</th>
				<th field="schExp" >学校经费（万元）</th>
				<th field="eduMinistryExp" >教育部经费（万元）</th>
				<th field="firstAppvlTime"  formatter="formattime">首次通过认证时间</th>
				<th field="appvlTime"  formatter="formattime">认证时间</th>
				<th field="appvlID" >批文号</th>
				<th field="appvlResult" >认证结果</th>
				<th field="fromTime"  formatter="formattime">有效期（起）</th>
				<th field="endTime"  formatter="formattime">有效期（止）</th>
				<th field="appvlAuth"  >认证机构</th>
				<th field="totalCSHour" >总学时数</th>
				<th field="requireCShour" >必修课学时数</th>
				<th field="optionCSHour" >选修课学时数</th>
				<th field="inClassCSHour" >课内教学学时数</th>
				<th field="expCSHour" >实验教学学时数</th>
				<th field="praCSHour" >集中性实践教学环节学时数</th>
				<th field="totalCredit" >总学分数</th>
				<th field="requireCredit" >必修课学分数</th>
				<th field="optionCredit" >选修课学分数</th>
				<th field="inClassCredit" >课内教学学分数</th>
				<th field="expCredit" >实验教学学分数</th>
				<th field="praCredit" >集中实践教学环节学分数</th>
				<th field="outClassCredit" >课外科技活动学分数</th>
				<th field="note" >备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="height:auto">
		<div style="float: left;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUndergraMajorInfo()">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUndergraMajorInfo()">编辑</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteByIds()">删除</a>
		</div>

		
		 <div>
		 	<form id="auditing" method="post" style="float: right;height: 26px;">
			 	编号: <input id="seqNum" name="seqNum" class="easyui-numberbox" style="width:80px"/>
				日期 起始: <input id="startTime" name="startTime" class="easyui-datebox" style="width:80px"/>
				结束: <input id="endTime" name="endTime" class="easyui-datebox" style="width:80px"/>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="reloadgrid()">查询</a>
			</form>
		</div>
	</div>
	<div id="toolbar2">
	 <form  id="exportForm"  method="post" style="float: right;">

			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-download" plain="true"  onclick="exports()">数据导出</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="loadDic()">高级检索</a>
		</form>
	</div>
	<table id="verfiedData" title="审核通过数据" class="easyui-datagrid" style=style="height: auto;" url="table5/verifiedData"
		toolbar="#toolbar2" pagination="true"
	 singleSelect="false">
		<thead data-options="frozen:true">
			<tr>
				<th data-options="field:'ck',checkbox:true" >选取</th>
				<th field="SeqNumber" >编号</th>
				<th field="MajorName" >专业名称</th>
				<th field="MajorID" >专业代码</th>
			</tr>
		</thead>
		<thead>
			<tr>	
				<th field="MajorVersion" >代码版本</th>
				<th field="MajorField" >专业方向名称</th>
				<th field="MajorFieldID" >专业方向号</th>
				<th field="MajorSetTime"  formatter="formattime">专业设置时间</th>
				<th field="MajorAppvlID"  >批文号</th>
				<th field="MajorDurition" >学制</th>
				<th field="MajorDegreeType" >学位授予门类</th>
				<th field="MajorAdmisTime"  formatter="formattime">开始招生时间</th>
				<th field="MajorState" >招生状态</th>
				<th field="StopAdmisTime"  formatter="formattime">停止招生时间</th>
				<th field="IsNewMajor" formatter="booleanstr" >是否新办专业</th>
				<th field="AppvlYear"  formatter="formattime">批准建设年度</th>
				<th field="BuildAppvlID" >建设批文号</th>
				<th field="MajorLevel" >级别</th>
				<th field="Type" >类型</th>
				<th field="Field" >领域方向</th>
				<th field="Leader"  >建设负责人</th>
				<th field="TeaID" >教工号</th>
				<th field="CheckTime"  formatter="formattime">验收时间</th>
				<th field="CheckAppvlID" >验收批文号</th>
				<th field="SchExp" >学校经费（万元）</th>
				<th field="EduMinistryExp" >教育部经费（万元）</th>
				<th field="FirstAppvlTime"  formatter="formattime">首次通过认证时间</th>
				<th field="AppvlTime"  formatter="formattime">认证时间</th>
				<th field="AppvlID" >批文号</th>
				<th field="AppvlResult" >认证结果</th>
				<th field="FromTime"  formatter="formattime">有效期（起）</th>
				<th field="EndTime"  formatter="formattime">有效期（止）</th>
				<th field="AppvlAuth"  >认证机构</th>
				<th field="TotalCSHour" >总学时数</th>
				<th field="RequireCShour" >必修课学时数</th>
				<th field="OptionCSHour" >选修课学时数</th>
				<th field="InClassCSHour" >课内教学学时数</th>
				<th field="ExpCSHour" >实验教学学时数</th>
				<th field="PraCSHour" >集中性实践教学环节学时数</th>
				<th field="TotalCredit" >总学分数</th>
				<th field="RequireCredit" >必修课学分数</th>
				<th field="OptionCredit" >选修课学分数</th>
				<th field="InClassCredit" >课内教学学分数</th>
				<th field="ExpCredit" >实验教学学分数</th>
				<th field="PraCredit" >集中实践教学环节学分数</th>
				<th field="OutClassCredit" >课外科技活动学分数</th>
				<th field="Note" >备注</th>
			</tr>
		</thead>
	</table>
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="title1">本科专业批量导入</h3>
		<div class="fitem" id="item1">
			<form id="batchForm" method="post" enctype="multipart/form-data">
				<select class="easyui-combobox"  id="cbYearContrast1" name="selectYear" editable=false></select>
				<input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox" size="48" style="height: 24px;"
					validType="fileType['xls']" required="true" invalidMessage="请选择Excel格式的文件" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href='pages/UndergraMajorInfoTea/downloadModel?saveFile=<%=URLEncoder.encode("表3-2-2本科专业基本情况（教学单位-教务处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
			<a href="123"></a>
		</div>
		<hr></hr>	
			<h3 class="title1">本科专业逐条导入</h3>
		
		<form id="UndergraMajorInfoForm" method="post">
		<table>
			<tr>
			<td>
					<div class="fitem">
						<label>专业名称：</label> 
						<input id="SeqNumber" name="t322_Bean.SeqNumber" type="hidden" value="0"></input>
						<input type="hidden" name="t322_Bean.MajorName" id="MajorName"/>
						<input id="MajorID" type="text" name="t322_Bean.MajorID" 
							 class='easyui-combobox' data-options="valueField:'majorNum',textField:'majorName',url:'pages/DiMajorTwo/loadDiMajorTwo',listHeight:'auto',editable:false,
							 onSelect:function(){
							 	document.getElementById('MajorName').value=$(this).combobox('getText') ;
							 }">
						<span id="MajorNameSpan"></span>
					</div>	
					</td>	
						<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>版本代码：</label> 
						<select class='easyui-combobox' id="MajorVersion" name="t322_Bean.MajorVersion" editable=false panelHeight="auto">					
							<option value="2012">2012</option>
							<option value="1998">1998</option>
							<option value="99">99</option>
						</select>
							<span id="MajorVersionSpan"></span>
					</div>
				</td>	
			</tr>

		<tr>
			<td>
					<div class="fitem">
						<label>专业方向名称：</label> 
						<input id="MajorField" type="text" name="t322_Bean.MajorField"
							class="easyui-validatebox" ><span id="MajorFieldSpan"></span>
					</div>
				</td>
					<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>专业方向号：</label> 
						<input id="MajorFieldID" type="text" name="t322_Bean.MajorFieldID"
							class="easyui-validatebox" ><span id="MajorFieldIDSpan"></span>
					</div>
				</td>
				
			</tr>
	   <tr>
			<td>
					<div class="fitem">
						<label>专业设置时间：</label> 
						<input id="MajorSetTime" name="t322_Bean.MajorSetTime"
							class="easyui-datebox" editable="false" style="width:80px">
							<span id="MajorSetTimeSpan"></span>
					</div>
				</td>
					<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>批文号：</label> 
						<input id="MajorAppvlID" type="text" name="t322_Bean.MajorAppvlID"
							class="easyui-validatebox" ><span id="MajorAppvlIDSpan"></span>
					</div>
				</td>
				
			</tr>
		<tr>
				<td>
					<div class="fitem">
						<label>学制：</label> 
						<select class='easyui-combobox' id="MajorDurition" name="t322_Bean.MajorDurition" editable=false>					
							<option value="4">4</option>
							<option value="5">5</option>
						</select>
						<span id="MajorDuritionSpan"></span>
					</div>
				</td>
					<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>学位授予门类：</label> 
						<select class='easyui-combobox' id="MajorDegreeType" name="t322_Bean.MajorDegreeType" editable=false panelHeight="auto">
							<option value="01哲学">01哲学</option>
							<option value="02经济学">02经济学</option>
							<option value="03法学">03法学</option>
							<option value="04教育学">04教育学</option>
							<option value="05文学">05文学</option>
							<option value="06历史学">06历史学</option>
							<option value="07理学">07理学</option>
							<option value="08工学">08工学</option>
							<option value="09农学">09农学</option>
							<option value="10医学">10医学</option>
							<option value="11军事学">11军事学</option>
							<option value="12管理学">12管理学</option>
							<option value="13艺术学">13艺术学</option>
						</select>
						<span id="MajorDegreeTypeSpan"></span>
					</div>
				</td>
				
			</tr>
		<tr>
			<td>
					<div class="fitem">
						<label>开始招生时间：</label> 
						<input id="MajorAdmisTime" name="t322_Bean.MajorAdmisTime"
							class="easyui-datebox" editable="false" style="width:80px">
							<span id="MajorAdmisTimeSpan"></span>
					</div>
				</td>
					<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>招生状态：</label> 
						<select class='easyui-combobox' id="MajorState" name="t322_Bean.MajorState" editable=false panelHeight="auto">					
							<option value="在招">在招</option>
							<option value="当年停招">当年停招</option>
						</select>
						<span id="MajorStateSpan"></span>
					</div>
				</td>
				
			</tr>
		<tr>
			<td>
					<div class="fitem">
						<label>停止招生时间：</label> 
						<input id="StopAdmisTime" name="t322_Bean.StopAdmisTime"
							class="easyui-datebox" editable="false" style="width:80px">
							<span id="StopAdmisTimeSpan"></span>
					</div>
				</td>
					<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>是否新办专业：</label> 
						<select class='easyui-combobox' id="IsNewMajor" name="t322_Bean.IsNewMajor" editable=false panelHeight="auto">					
							<option value="true">是</option>
							<option value="false">否</option>
						</select>
						<span id="IsNewMajorSpan"></span>
					</div>
				</td>				
			</tr>
		 <tr>
			<td>
					<div class="fitem">
						<label>批准建设年度：</label> 
						<input id="AppvlYear" name="t322_Bean.AppvlYear"
							class="easyui-datebox" editable="false" style="width:80px">
							<span id="AppvlYearSpan"></span>
					</div>
				</td>
					<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>建设批文号：</label> 
						<input id="BuildAppvlID" type="text" name="t322_Bean.BuildAppvlID"
							class="easyui-validatebox" ><span id="BuildAppvlIDSpan"></span>
					</div>
				</td>				
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>级别：</label> 
						<input id="MajorLevel" name="t322_Bean.MajorLevel" 
							 class='easyui-combobox' data-options="valueField:'indexId',textField:'awardLevel',url:'pages/DiAwardLevel/loadDiAwardLevel',listHeight:'auto',editable:false">
						<span id="MajorLevelSpan"></span>
					</div>
				</td>
					<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>类型：</label> 
						<select class='easyui-combobox' id="Type" name="t322_Bean.Type" editable=false panelHeight="auto">
							<option value="特色专业">特色专业</option>
							<option value="品牌专业">品牌专业</option>
							<option value="名牌专业">名牌专业</option>
							<option value="示范专业">示范专业</option>
							<option value="重点建设专业">重点建设专业</option>
							<option value="地方优势专业">地方优势专业</option>
							<option value="地方优势专业">其他</option>
						</select>
						<span id="TypeSpan"></span>
					</div>
				</td>				
			</tr>
			<tr>
			<td>
					<div class="fitem">
						<label>领域方向：</label> 
						<input id="Field" type="text" name="t322_Bean.Field"
							class="easyui-validatebox" ><span id="FieldSpan"></span>
					</div>
				</td>
					<td class="empty"></td>
				<td>
				<div class="fitem">
				<label>教工号：</label> 
				<input type="hidden" name="t322_Bean.TeaID" id="TeaID"/>
				<input id="Leader" type="text" name="t322_Bean.Leader" class='easyui-combobox' 
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
						<label>验收时间：</label> 
						<input id="CheckTime" name="t322_Bean.CheckTime"
							class="easyui-datebox" editable="false" style="width:80px">
							<span id="CheckTimeSpan"></span>
					</div>
				</td>
					<td class="empty"></td>
					<td>
					<div class="fitem">
						<label>验收批文号：</label> 
						<input id="CheckAppvlID" type="text" name="t322_Bean.CheckAppvlID"
							class="easyui-validatebox" ><span id="CheckAppvlIDSpan"></span>
					</div>
				</td>			
			</tr>		
	<tr>
				<td>
					<div class="fitem">
						<label>学校经费（万元）：</label> 
						<input id="SchExp" type="text" name="t322_Bean.SchExp"
							class="easyui-validatebox" ><span id="SchExpSpan"></span>
					</div>
				</td>
					<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>教育部经费(万元)：</label> 
						<input id="EduMinistryExp" type="text" name="t322_Bean.EduMinistryExp"
							class="easyui-validatebox" ><span id="EduMinistryExpSpan"></span>
					</div>
				</td>					
			</tr>
		 <tr>

				<td>
					<div class="fitem">
						<label>首次通过认证时间：</label> 
						<input id="FirstAppvlTime" name="t322_Bean.FirstAppvlTime"
							class="easyui-datebox" editable="false" style="width:80px">
							<span id="FirstAppvlTimeSpan"></span>
					</div>
				</td>
					<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>认证时间：</label> 
						<input id="AppvlTime" name="t322_Bean.AppvlTime"
							class="easyui-datebox" editable="false" style="width:80px">
							<span id="AppvlTimeSpan"></span>
					</div>
				</td>				
			</tr>
			<tr>

				<td>
					<div class="fitem">
						<label>批文号：</label> 
						<input id="AppvlID" type="text" name="t322_Bean.AppvlID"
							class="easyui-validatebox" ><span id="AppvlIDSpan"></span>
					</div>
				</td>
					<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>认证结果：</label> 
						<select class='easyui-combobox' id="AppvlResult" name="t322_Bean.AppvlResult" editable=false panelHeight="auto">
							<option value="通过">通过</option>
							<option value="未通过">未通过</option>
							<option value="未参加评估">未参加评估</option>
						</select>
						<span id="AppvlResultSpan"></span>
					</div>
				</td>				
			</tr>
			<tr>

				<td>
					<div class="fitem">
						<label>有效期(起)：</label> 
						<input id="FromTime" name="t322_Bean.FromTime"
							class="easyui-datebox" editable="false" style="width:80px">
							<span id="FromTimeSpan"></span>
					</div>
				</td>	
					<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>有效期(止)：</label> 
						<input id="EndTime" name="t322_Bean.EndTime"
							class="easyui-datebox" editable="false" style="width:80px">
							<span id="EndTimeSpan"></span>
					</div>
				</td>			
			</tr>
			<tr>

				<td>
					<div class="fitem">
						<label>认证机构：</label> 
						<input id="AppvlAuth" type="text" name="t322_Bean.AppvlAuth"
							class="easyui-validatebox" ><span id="AppvlAuthSpan"></span>
					</div>
				</td>	
					<td class="empty"></td>
					<td>
					<div class="fitem">
						<label>必修课学时数：</label> 
						
						<input id="RequireCShour" type="text" name="t322_Bean.RequireCShour"
							class="easyui-validatebox" ><span id="RequireCShourSpan"></span>
					</div>
				</td>
					
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>选修课学时数：</label> 
						<input id="OptionCSHour" type="text" name="t322_Bean.OptionCSHour"
							class="easyui-validatebox" ><span id="OptionCSHourSpan"></span>
					</div>
				</td>
					<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>课内教学学时数：</label> 
						<input id="InClassCSHour" type="text" name="t322_Bean.InClassCSHour"
							class="easyui-validatebox" ><span id="InClassCSHourSpan"></span>
					</div>
				</td>
				
			</tr>
					<tr>
					<td>
					<div class="fitem">
						<label>实验教学学时数：</label> 
						<input id="ExpCSHour" type="text" name="t322_Bean.ExpCSHour"
							class="easyui-validatebox" ><span id="ExpCSHourSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
					<td>
					<div class="fitem">
						<label>必修课学分数：</label> 
						
						<input id="RequireCredit" type="text" name="t322_Bean.RequireCredit"
							class="easyui-validatebox" ><span id="RequireCreditSpan"></span>
					</div>
				</td>			
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>选修课学分数：</label> 
						<input id="OptionCredit" type="text" name="t322_Bean.OptionCredit"
							class="easyui-validatebox" ><span id="OptionCreditSpan"></span>
					</div>
				</td>
					<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>课内教学学分数：</label> 
						<input id="InClassCredit" type="text" name="t322_Bean.InClassCredit"
							class="easyui-validatebox" ><span id="InClassCreditSpan"></span>
					</div>
				</td>
				
			</tr>
			<tr>

				<td>
					<div class="fitem">
						<label>实验教学学分数：</label> 
						<input id="ExpCredit" type="text" name="t322_Bean.ExpCredit"
							class="easyui-validatebox" ><span id="ExpCreditSpan"></span>
					</div>
				</td>
					<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>集中实践教学环节学分数：</label> 
						<input id="PraCredit" type="text" name="t322_Bean.PraCredit"
							class="easyui-validatebox" ><span id="PraCreditSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>课外科技活动学分数：</label> 
						<input id="OutClassCredit" type="text" name="t322_Bean.OutClassCredit"
							class="easyui-validatebox" ><span id="OutClassCreditSpan"></span>
					</div>
				</td>
			</tr>


			<tr>
				<td style="valign:left" colspan="3"><label>备&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
					<textarea id="Note" name="t322_Bean.Note" style="resize:none" cols="50" rows="10"></textarea>
					<span id="NoteSpan"></span>
				</td>
			</tr>
		</table>
		</form>
	</div>
	
	<div id="dicDlg" class="easyui-dialog" style="width:500px;padding:10px 20px" closed="true">
		<div class="ftitle">高级检索</div>
		<div id="dicTables"  class="fitem">
		</div>
		<div id="dices"  class="fitem"></div>
	</div>
	 
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="singleImport()">保存</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
</body>




	<script type="text/javascript">
	
	    var url;

		function reloadgrid ()  { 
	        //查询参数直接添加在queryParams中 
	         var queryParams = $('#unverfiedData').datagrid('options').queryParams;  
	         queryParams.seqNum = $('#seqNum').val(); 
	         queryParams.startTime = $('#startTime').datetimebox('getValue');	         		     
        	 queryParams.endTime  = $('#endTime').datetimebox('getValue');        	 
	         $("#unverfiedData").datagrid('reload'); 
	    }

	    
	    function fillSelect(){ 
            var obj=document.getElementById( "SetTime"); 
            debugger;
             for(var i=1952;i <=2014;i++){ 
            var op=new Option(i,i); 
           obj.add(op); 
               } 
              } 
              
              
	    function batchImport(){
	    	 $('#batchForm').form('submit',{
	    		 url: 'pages/UndergraMajorInfoTea/uploadFile',
	    		 type: "post",
		         dataType: "json",
	    		 onSubmit: function(){
	    			 return check() ;
	    		 },
	    		 success: function(result){
	    		 	var result = eval('('+result+')');
	    		 	if (!result.success){
	    		 		$.messager.show({
	    		 			title: 'Error',
	    		 			msg: result.errorMsg
	    			 });
	    		 	} else {
			    		 $('#dlg').dialog('close'); // close the dialog
			    		 $('#unverfiedData').datagrid('reload'); // reload the user data
	    		 	}
	    		 }
	    		 });
	    }
	    
	    function check(){
	    	var fileName = $('#uploadFile').val() ;
	    	
	    	if(fileName == null || fileName == ""){
	    		 $.messager.alert("操作提示", "请先选择要导入的文件！");
	    		return false ;
	    	}
	    	
	    	var pos = fileName.lastIndexOf(".") ;
	    	var suffixName = fileName.substring(pos, fileName.length) ;
	    	
	    	if(suffixName == ".xls"){
	    		return true ;
	    	}else{
	    		 $.messager.alert("操作提示", "请选择正确的Excel文件（后缀为.xls）");
	    		return false ;
	    	}
	    } 
	    
	    function newUndergraMajorInfo(){
	    	$('.title1').show();
	    	$('#item1').show();
	    	$('hr').show();
		    
	    	url=' pages/UndergraMajorInfoTea/insert',
		    $('#dlg').dialog('open').dialog('setTitle','添加本科专业');
		    $('#UndergraMajorInfoForm').form('reset');
	    }

	    function singleImport(){
		    //录入数据的表单提交
	    	 $('#UndergraMajorInfoForm').form('submit',{
				    url: url,
				    data: $('#UndergraMajorInfoForm').serialize(),
		            type: "post",
		            dataType: "json",
				    onSubmit: function(){
				    	return validate();
				    },
				    //结果返回
				    success: function(result){
					    //json格式转化
					    var result = eval('('+result+')');
					    $.messager.alert('温馨提示', result.data) ;
					    if (result.state){ 
						    $('#dlg').dialog('close'); 
						    $('#unverfiedData').datagrid('reload'); 
						
					    }
				    }
			    });
		}

		function validate(){
			var MajorField = $('#MajorField').val() ;
			var MajorFieldID = $('#MajorFieldID').val() ;
			var MajorSetTime = $('#MajorSetTime').datebox('getValue') ;
			var MajorAppvlID = $('#MajorAppvlID').val() ;
			var MajorAdmisTime = $('#MajorAdmisTime').datebox('getValue') ;
			var MajorState = $('#MajorState').combobox('getText') ;
			var StopAdmisTime = $('#StopAdmisTime').datebox('getValue') ;
			var AppvlYear = $('#AppvlYear').datebox('getValue') ;
			var BuildAppvlID = $('#BuildAppvlID').val() ;
			var MajorLevel = $('#MajorLevel').combobox('getText');
			var Field = $('#Field').val() ;
			var TeaID = $('#Leader').combobox('getText') ;
			var CheckTime = $('#CheckTime').datebox('getValue') ;
			var CheckAppvlID = $('#CheckAppvlID').val() ;
			var SchExp = $('#SchExp').val() ;
			var EduMinistryExp = $('#EduMinistryExp').val() ;
			var AppvlResult = $('#AppvlResult').combobox('getText');
			var FirstAppvlTime = $('#FirstAppvlTime').datebox('getValue') ;
			var AppvlTime = $('#AppvlTime').datebox('getValue') ;
			var AppvlID = $('#AppvlID').val() ;
			var FromTime = $('#FromTime').datebox('getValue') ;
			var EndTime = $('#EndTime').datebox('getValue') ;
			var AppvlAuth = $('#AppvlAuth').val() ;
			var RequireCShour = $('#RequireCShour').val() ;
			var InClassCSHour = $('#InClassCSHour').val() ;
			var OptionCredit = $('#OptionCredit').val() ;
			var ExpCSHour = $('#ExpCSHour').val() ;
			var RequireCredit = $('#RequireCredit').val() ;
			var OptionCredit = $('#OptionCredit').val() ;
			var InClassCredit = $('#InClassCredit').val() ;
			var ExpCredit = $('#ExpCredit').val() ;
			var PraCredit = $('#PraCredit').val() ;
			var OutClassCredit = $('#OutClassCredit').val() ;
	

		
			
			
			
			

			

			if(MajorName == null || MajorName.length==0 || MajorName.length > 100){
				$.messager.alert('提示',"专业名称不能为空或长度不超过100") ;
				return false;
			}

			if(MajorField == null ||MajorField.length == 0 || MajorField.length > 50){
				$.messager.alert('提示',"专业方向名称不能为空（没有请填无）或长度不超过50");
				return false;
			}
			if(MajorFieldID == null ||MajorFieldID.length == 0 || MajorFieldID.length > 50){
				$.messager.alert('提示',"专业方向号不能为空（没有请填无）或长度不超过50");
				return false;
			}
			if(MajorSetTime == null || MajorSetTime.length == 0){
				$.messager.alert('提示',"专业设置时间不能为空") ;
				return false;
			}

			if(MajorAppvlID == null || MajorAppvlID.length==0 || MajorAppvlID.length > 100){
				$.messager.alert('提示',"批文号不能为空或长度不超过100") ;
				return false;
			}

			if(MajorAdmisTime == null || MajorAdmisTime.length == 0){
				$.messager.alert('提示',"开始招生时间不能为空") ;
				return false;
			}

			if(MajorState == "在招" && !StopAdmisTime== null){
				$.messager.alert('提示',"招生状态为'在招'时，停止招生时间不填") ;
				return false;
			}


			if(MajorState == "当年停招" && StopAdmisTime== null){
				$.messager.alert('提示',"招生状态为'当年停招'时，停止招生时间不能为空 ") ;
				return false;
			}

			if(AppvlYear == null || AppvlYear.length == 0){
				$.messager.alert('提示',"批准建设年度不能为空") ;
				return false;
			}

			if(BuildAppvlID == null || BuildAppvlID.length==0 || BuildAppvlID.length > 100){
				$.messager.alert('提示',"建设批文号不能为空或长度不超过100") ;
				return false;
			}

			if(MajorLevel == null || MajorLevel.length == 0){
				$.messager.alert('提示',"级别不能为空") ;
				return false;
			}

			if(Field == null || Field.length == 0){
				$.messager.alert('提示',"领域方向不能为空") ;
				return false;
			}
			if(TeaID == null || TeaID.length == 0){
				$.messager.alert('提示',"建设负责人教工号不能为空") ;
				return false;
			}
			if(CheckTime == null || CheckTime.length == 0){
				$.messager.alert('提示',"验收时间不能为空") ;
				return false;
			}
			if(CheckAppvlID == null || CheckAppvlID.length == 0){
				$.messager.alert('提示',"验收批文号不能为空") ;
				return false;
			}
			if(SchExp == null || SchExp.length == 0){
				$.messager.alert('提示',"学校经费(万元)不能为空") ;
				return false;
			}else if(isNaN(SchExp)){
				$.messager.alert('提示',"学校经费(万元)必须是数字") ;
				return false;
			}

			if(EduMinistryExp == null || EduMinistryExp.length == 0){
				$.messager.alert('提示',"教育部经费(万元)不能为空") ;
				return false;
			}else if(isNaN(EduMinistryExp)){
				$.messager.alert('提示',"教育部经费(万元)必须是数字") ;
				return false;
			}
			if(AppvlResult == "通过"){
				if(FirstAppvlTime == null || FirstAppvlTime.length == 0){
					$.messager.alert('提示',"首次通过认证时间不能为空") ;
					return false;
				}
				if(AppvlTime == null || AppvlTime.length == 0){
					$.messager.alert('提示',"认证时间不能为空") ;
					return false;
				}
				if(AppvlID == null || AppvlID.length == 0){
					$.messager.alert('提示',"认证批文号不能为空") ;
					return false;
				}
				if(FromTime == null || FromTime.length == 0){
					$.messager.alert('提示',"有限期起不能为空") ;
					return false;
				}
				if(EndTime == null || EndTime.length == 0){
					$.messager.alert('提示',"有限期止不能为空") ;
					return false;
				}
				if(AppvlAuth == null || AppvlAuth.length == 0){
					$.messager.alert('提示',"认证机构不能为空") ;
					return false;
				}
				
				
			}
			if(AppvlResult == "未通过"){
				if(FirstAppvlTime == null || FirstAppvlTime.length == 0){
					$.messager.alert('提示',"首次通过认证时间不能为空") ;
					return false;
				}
				if(AppvlTime == null || AppvlTime.length == 0){
					$.messager.alert('提示',"认证时间不能为空") ;
					return false;
				}
				if(AppvlID == null || AppvlID.length == 0){
					$.messager.alert('提示',"认证批文号不能为空") ;
					return false;
				}
				if(!(FromTime == null || FromTime.length == 0)){
					$.messager.alert('提示',"有限期起必须为空") ;
					return false;
				}
				if(!(EndTime == null || EndTime.length == 0)){
					$.messager.alert('提示',"有限期止必须为空") ;
					return false;
				}
				if(AppvlAuth == null || AppvlAuth.length == 0){
					$.messager.alert('提示',"认证机构不能为空") ;
					return false;
				}
				
				
			}
			if(AppvlResult == "未参加评估"){
				if(FirstAppvlTime == null || FirstAppvlTime.length == 0){
					$.messager.alert('提示',"首次通过认证时间不能为空") ;
					return false;
				}
				if(!(AppvlTime == null || AppvlTime.length == 0)){
					$.messager.alert('提示',"认证时间必须为空") ;
					return false;
				}
				if(!(AppvlID == null || AppvlID.length == 0)){
					$.messager.alert('提示',"认证批文号必须为空") ;
					return false;
				}
				if(!(FromTime == null || FromTime.length == 0)){
					$.messager.alert('提示',"有限期起必须为空") ;
					return false;
				}
				if(!(EndTime == null || EndTime.length == 0)){
					$.messager.alert('提示',"有限期止必须为空") ;
					return false;
				}
				if(!(AppvlAuth == null || AppvlAuth.length == 0)){
					$.messager.alert('提示',"认证机构必须为空") ;
					return false;
				}
				

			}
			if(RequireCShour == null || RequireCShour.length == 0){
				$.messager.alert('提示',"必修课学时数不能为空") ;
				return false;
			}
			if(OptionCSHour == null || OptionCSHour.length == 0){
				$.messager.alert('提示',"选修课学时数不能为空") ;
				return false;
			}
			if(InClassCSHour == null || InClassCSHour.length == 0){
				$.messager.alert('提示',"课内教学学时数不能为空") ;
				return false;
			}
			if(ExpCSHour == null || ExpCSHour.length == 0){
				$.messager.alert('提示',"实验教学学时数不能为空") ;
				return false;
			}
			if(RequireCredit == null || RequireCredit.length == 0){
				$.messager.alert('提示',"必修课学分数不能为空") ;
				return false;
			}
			if(OptionCredit == null || OptionCredit.length == 0){
				$.messager.alert('提示',"选修课学分数不能为空") ;
				return false;
			}
			if(InClassCredit == null || InClassCredit.length == 0){
				$.messager.alert('提示',"课内教学学分数不能为空") ;
				return false;
			}
			if(ExpCredit == null || ExpCredit.length == 0){
				$.messager.alert('提示',"实验教学学分数不能为空") ;
				return false;
			}
			if(PraCredit == null || PraCredit.length == 0){
				$.messager.alert('提示',"集中实践教学环节学分数不能为空") ;
				return false;
			}
			if(OutClassCredit == null || OutClassCredit.length == 0){
				$.messager.alert('提示',"课外科技活动学分数不能为空") ;
				return false;
			}

			
			return true ;
		}
	    function exports() {
	    	var temp = encodeURI('表3-2-2本科专业信息表.xls');
		    $('#exportForm').form('submit', {
		    url : "pages/UndergraMajorInfoTea/dataExport?excelName="+temp ,
		    onSubmit : function() {
		    return $(this).form('validate');//对数据进行格式化
		    },
		    success : function(data) {
		    $.messager.show({
		    	title : '提示',
		    	msg : data
		    });
		    }
		    }); 
	    }

	    function deleteByIds(){
	    	//获取选中项
			var row = $('#unverfiedData').datagrid('getSelections');
	    	
			if(row.length == 0){
	    		$.messager.alert('温馨提示', "请选择需要删除的数据！！！") ;
	    		return ;
	    	}
	    	
			 $.messager.confirm('数据删除', '您确定删除选中项?', function(sure){
				 if (sure){
				 	var ids = "";
				 	ids += "(" ;
				 	
				 	for(var i=0; i<row.length; i++){
				 		if(i < (row.length - 1)){
				 			ids += (row[i].seqNumber + ",") ;
				 		}else{
				 			ids += (row[i].seqNumber + ")") ;
				 		}
				 	}
				 	
				 	deletePostDocSta(ids) ;
				 	
				 }
			});
	    }

	    function deletePostDocSta(ids){
	    	$.ajax({ 
	    		type: "POST", 
	    		url: "pages/UndergraMajorInfoTea/deleteCoursesByIds?ids=" + ids, 
	    		async:"true",
	    		dataType: "text",
	    		success: function(result){
	    			result = eval("(" + result + ")");

					if(result.state){
						alert(result.data) ;
						 $('#unverfiedData').datagrid('reload') ;
					}
	    		}
	    	}).submit();
	    }

	    function editUndergraMajorInfo(){
	    	var row = $('#unverfiedData').datagrid('getSelections');
	    	
	    	if(row.length != 1){
	    		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
	    		return ;
	    	}
	    	
	    	url = 'pages/UndergraMajorInfoTea/edit' ;

	    	$('.title1').hide();
	    	$('#item1').hide();
	    	$('hr').hide();
	    	
	    	$('#dlg').dialog('open').dialog('setTitle','修改本科专业');
	    	$('#SeqNumber').val(row[0].seqNumber) ;

	    	$('#MajorID').combobox('select',row[0].majorID) ;
	    	$('#MajorVersion').combobox('select',row[0].majorVersion) ;
	        $('#MajorField').val(row[0].majorField);
	        $('#MajorFieldID').val(row[0].majorFieldID);
	    	$('#MajorSetTime').datebox('setValue',formattime(row[0].majorSetTime)) ;
	        $('#MajorAppvlID').val(row[0].majorAppvlID);
	    	$('#MajorDurition').combobox('select',row[0].majorDurition) ;
	    	$('#MajorDegreeType').combobox('select',row[0].majorDegreeType) ;
	    	$('#MajorAdmisTime').datebox('setValue',formattime(row[0].majorAdmisTime)) ;
	    	$('#MajorState').combobox('select',row[0].majorState) ;
	    	$('#StopAdmisTime').datebox('setValue',formattime(row[0].stopAdmisTime)) ;
	    	$('#IsNewMajor').combobox('select',row[0].isNewMajor) ;
	    	$('#AppvlYear').datebox('setValue',formattime(row[0].appvlYear)) ;
	        $('#BuildAppvlID').val(row[0].buildAppvlID);
	    	$('#MajorLevel').combobox('select',row[0].majorLevelID) ;

	      	$('#Type').combobox('select',row[0].type) ;
	        $('#Field').val(row[0].field);
	    	$('#Leader').combobox('select',row[0].leader) ;
	    	$('#CheckTime').datebox('setValue',formattime(row[0].checkTime)) ;
	        $('#CheckAppvlID').val(row[0].checkAppvlID);
	        $('#SchExp').val(row[0].schExp);
	        $('#EduMinistryExp').val(row[0].eduMinistryExp);
	        $('#FirstAppvlTime').datebox('setValue',formattime(row[0].firstAppvlTime)) ;
	        $('#AppvlTime').datebox('setValue',formattime(row[0].appvlTime)) ;
	        $('#AppvlID').val(row[0].appvlID);
	        $('#AppvlResult').combobox('select',row[0].appvlResult) ;
	        $('#FromTime').datebox('setValue',formattime(row[0].fromTime)) ;
	        $('#EndTime').datebox('setValue',formattime(row[0].endTime)) ;
	        $('#AppvlAuth').val(row[0].appvlAuth);
	        
	        $('#RequireCShour').val(row[0].requireCShour);
	        $('#InClassCSHour').val(row[0].inClassCSHour);
	        $('#OptionCSHour').val(row[0].optionCSHour);
	        $('#ExpCSHour').val(row[0].expCSHour);


	        
	        $('#RequireCredit').val(row[0].requireCredit);
	        $('#OptionCredit').val(row[0].optionCredit);
	        $('#InClassCredit').val(row[0].inClassCredit);
	        $('#ExpCredit').val(row[0].expCredit);
	        $('#PraCredit').val(row[0].praCredit);
	        $('#OutClassCredit').val(row[0].outClassCredit);
			$('#Note').val(row[0].note);


		
	    }
	    
	    
	    
	    </script>

	<script type="text/javascript"> 
			//日期格式转换 
			function formattime(val) {  
			    if(val == null){
				    return null ;
			    }
			    var year=parseInt(val.year)+1900;  
			    var month=(parseInt(val.month)+1);  
			    month=month>9?month:('0'+month);  
			    var date=parseInt(val.date);  
			    date=date>9?date:('0'+date);  
			    var hours=parseInt(val.hours);  
			    hours=hours>9?hours:('0'+hours);  
			    var minutes=parseInt(val.minutes);  
			    minutes=minutes>9?minutes:('0'+minutes);  
			    var seconds=parseInt(val.seconds);  
			    seconds=seconds>9?seconds:('0'+seconds);  
			    var time=year+'-'+month+'-'+date ;  
			    //alert(time) ;
			     return time;  
			    }  
			</script>
			
			
			<script type="text/javascript"> 
		    function booleanstr(val) { 	 
		    	if(val == null){
					return null ;
				}
				var bo1=""+val;//吧boolean型转换成str类型再判断
				var boo;
				if( bo1 == "false") {
					boo="否" ;
				}else if (bo1 == "true"){

					boo="是" ;
				}
				return boo;
	        }  
			</script>
			
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

