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
	
	<style type="text/css">
		#fm {
			margin: 0;
			padding: 10px 30px;
		}
		
		.ftitle {
			font-size: 14px;
			font-weight: bold;
			padding: 5px 0;
			margin-bottom: 10px;
			border-bottom: 1px solid #ccc;
		}
		
		.fitem {
			margin-bottom: 5px;
		}
		
		.fitem label {
			display: inline-block;
			width: 80px;
		}
	</style>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script> 
	<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body style="overflow-y:scroll">
	<table id="unverfiedData" title="待审核数据域审核未通过数据" class="easyui-datagrid" style="height: auto;" url="pages/UndergraMajorInfoTea/auditingData"
		toolbar="#toolbar" pagination="true" 
	singleSelect="false" >
		<thead data-options="frozen:true">
			<tr>
				<th data-options="field:'ck',checkbox:true" >选取</th>
				<th field="seqNumber" >序号</th>
			</tr>
		</thead>
		<thead>
			<tr>	
				<th field="MajorName"  formatter="formattime">专业名称</th>
				<th field="MajorID" >专业代码</th>
				<th field="MajorVersion" >代码版本</th>
				<th field="MajorField" >专业方向名称</th>
				<th field="MajorFieldID" >专业方向号</th>
				<th field="MajorSetTime"  formatter="formattime">专业设置时间</th>
				<th field="MajorAppvlID"  >批文号</th>
				<th field="MajorDurition" >学制</th>
				<th field="MajorDegreeType" >学位授予门类</th>
				<th field="MajorAdmisTime" >开始招生时间</th>
				<th field="MajorState" >招生状态</th>
				<th field="StopAdmisTime" >停止招生时间</th>
				<th field="IsNewMajor"  formatter="formattime">是否新办专业</th>
				<th field="AppvlYear" >批准建设年度</th>
				<th field="BuildAppvlID" >建设批文号</th>
				<th field="MajorLevel" >级别</th>
				<th field="Type" >类型</th>
				<th field="Field" >领域方向</th>
				<th field="Leader"  formatter="formattime">建设负责人</th>
				<th field="TeaID" >教工号</th>
				<th field="CheckTime" >验收时间</th>
				<th field="CheckAppvlID" >验收批文号</th>
				<th field="SchExp" >学校经费（万元）</th>
				<th field="EduMinistryExp" >教育部经费（万元）</th>
				<th field="FirstAppvlTime"  formatter="formattime">首次通过认证时间</th>
				<th field="AppvlTime" >认证时间</th>
				<th field="AppvlID" >批文号</th>
				<th field="AppvlResult" >认证结果</th>
				<th field="FromTime" >有效期（起）</th>
				<th field="EndTime" >有效期（止）</th>
				<th field="AppvlAuth"  formatter="formattime">认证机构</th>
				<th field="TotalCSHour" >总学时数</th>
				<th field="RequireCShour" >必修课学时数</th>
				<th field="OptionCSHour" >选修课学时数</th>
				<th field="InClassCSHour" >课内教学学时数</th>
				<th field="ExpCSHour" >实验教学学时数</th>
				<th field="PraCSHour" >集中性实践教学环节学时数</th>
				<th field="TotalCredit" >总学分数</th>
				<th field="RequireCredit"  formatter="formattime">必修课学分数</th>
				<th field="OptionCredit" >选修课学分数</th>
				<th field="InClassCredit" >课内教学学分数</th>
				<th field="ExpCredit" >实验教学学分数</th>
				<th field="PraCredit" >集中实践教学环节学分数</th>
				<th field="OutClassCredit" >课外科技活动学分数</th>
				<th field="note" >备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="height:auto">
		<div>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newPostDocSta()">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editPostDocSta()">编辑</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteByIds()">删除</a>
			<a href="pages/PostDocSta/dataExport?excelName=表3-1-1博士后流动站（人事处）.xls" class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a>
		</div>

		
		 <div>
		 	<form id="auditing" method="post">
			 	序号: <input id="seqNum" name="seqNum" class="easyui-numberbox" style="width:80px"/>
				日期 起始: <input id="startTime" name="startTime" class="easyui-datebox" style="width:80px"/>
				结束: <input id="endTime" name="endTime" class="easyui-datebox" style="width:80px"/>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="reloadgrid()">查询</a>
			</form>
		</div>
	</div>
	<div id="toolbar2">
	 <form  id="exportForm"  method="post" style="float: right;">
			<select class="easyui-combobox" id="cbYearContrast" name="selectYear" panelHeight="auto" style="width:80px; padding-top:5px; margin-top:10px;"></select>
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
				<th field="seqNumber">序号</th>
							</tr>
		</thead>
		<thead>
			<tr>
				<th field="MajorName" formatter="formattime">专业名称</th>
				<th field="MajorID">专业代码</th>
				<th field="MajorVersion">代码版本</th>
				<th field="MajorField">专业方向名称</th>
				<th field="MajorFieldID">专业方向号</th>
				<th field="MajorSetTime" formatter="formattime">专业设置时间</th>
				<th field="MajorAppvlID" >批文号</th>
				<th field="MajorDurition">学制</th>
				<th field="MajorDegreeType">学位授予门类</th>
				<th field="MajorAdmisTime">开始招生时间</th>
				<th field="MajorState">招生状态</th>
				<th field="StopAdmisTime">停止招生时间</th>
				<th field="IsNewMajor" >是否新办专业</th>
				<th field="AppvlYear">批准建设年度</th>
				<th field="BuildAppvlID">建设批文号</th>
				<th field="MajorLevel">级别</th>
				<th field="Type">类型</th>
				<th field="Field">领域方向</th>
				<th field="Leader" formatter="formattime">建设负责人</th>
				<th field="TeaID">教工号</th>
				<th field="CheckTime">验收时间</th>
				<th field="CheckAppvlID">验收批文号</th>
				<th field="SchExp">学校经费（万元）</th>
				<th field="EduMinistryExp">教育部经费（万元）</th>
				<th field="FirstAppvlTime" formatter="formattime">首次通过认证时间</th>
				<th field="AppvlTime">认证时间</th>
				<th field="AppvlID">批文号</th>
				<th field="AppvlResult">认证结果</th>
				<th field="FromTime">有效期（起）</th>
				<th field="EndTime">有效期（止）</th>
				<th field="AppvlAuth" formatter="formattime">认证机构</th>
				<th field="TotalCSHour">总学时数</th>
				<th field="RequireCShour">必修课学时数</th>
				<th field="OptionCSHour">选修课学时数</th>
				<th field="InClassCSHour">课内教学学时数</th>
				<th field="ExpCSHour">实验教学学时数</th>
				<th field="PraCSHour">集中性实践教学环节学时数</th>
				<th field="TotalCredit">总学分数</th>
				<th field="RequireCredit" >必修课学分数</th>
				<th field="OptionCredit">选修课学分数</th>
				<th field="InClassCredit">课内教学学分数</th>
				<th field="ExpCredit">实验教学学分数</th>
				<th field="PraCredit">集中实践教学环节学分数</th>
				<th field="OutClassCredit">课外科技活动学分数</th>
				<th field="note">备注</th>
			</tr>
		</thead>
	</table>
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<div class="ftitle">专业批量导入</div>
		<div class="fitem">
			<form id="batchForm" method="post" enctype="multipart/form-data">
				<label>批量上传：</label> 
				<select class="easyui-combobox"  id="cbYearContrast" name="selectYear"></select>
				<input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox"
					validType="fileType['xls']" required="true" invalidMessage="请选择Excel格式的文件" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">导入</a>
				<a href='pages/UndergraMajorInfoTea/downloadModel?saveFile=<%=URLEncoder.encode("表3-2-2本科专业基本情况（教学单位-教务处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
			<a href="123"></a>
		</div>
		<div></div>
		<div class="ftitle">专业逐条导入</div>
		
		<form id="UndergraMajorInfoForm" method="post">
		<table>
			<tr>
					<div class="fitem">
						<label>专业名称：</label> 
						<input id="seqNumber" name="t322_Bean.SeqNumber" type="hidden" > </input>
						<input id="TotalCSHour" name="t322_Bean.TotalCSHour" type="hidden" > </input>
						<input id="RequireCShour" name="t322_Bean.RequireCShour" type="hidden" > </input>
						<input id="OptionCSHour" name="t322_Bean.OptionCSHour" type="hidden" > </input>
						<input id="InClassCSHour" name="t322_Bean.InClassCSHour" type="hidden" > </input>
						<input id="ExpCSHour" name="t322_Bean.ExpCSHour" type="hidden" > </input>
						<input id="PraCSHour" name="t322_Bean.PraCSHour" type="hidden" > </input>
						<input id="TotalCredit" name="t322_Bean.TotalCredit" type="hidden" > </input>					
						<input type="hidden" name="t322_Bean.MajorName" id="MajorName"/>
						<input id="MajorID" type="text" name="t322_Bean.MajorID" 
							 class='easyui-combobox' data-options="valueField:'majorNum',textField:'majorName',url:'pages/DiMajorTwo/loadDiMajorTwo',listHeight:'auto',editable:false,
							 onSelect:function(){
							 	document.getElementById('MajorNameInSch').value=$(this).combobox('getText') ;
							 }">
						<span id="MajorNameSpan"></span>
					</div>			


			</tr>
			<tr>
			<td>
					<div class="fitem">
						<label>研究员人数：</label> 
						<input id="MajorID" type="text" name="t322_Bean.MajorID"
							class="easyui-validatebox" ><span id="MajorIDSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>版本代码：</label> 
						<select class='easyui-combobox' id="MajorVersion" name="t322_Bean.MajorVersion">					
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
						<select class='easyui-combobox' id="MajorDurition" name="t322_Bean.MajorDurition">					
							<option value="4">4</option>
							<option value="5">5</option>
						</select>
						<span id="MajorDuritionSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>学位授予门类：</label> 
						<select class='easyui-combobox' id="MajorDegreeType" name="t322_Bean.MajorDegreeType">
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
				<td>
					<div class="fitem">
						<label>招生状态：</label> 
						<select class='easyui-combobox' id="MajorState" name="t322_Bean.MajorState">					
							<option value="true">在招</option>
							<option value="false">当年停招</option>
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
				<td>
					<div class="fitem">
						<label>是否新办专业：</label> 
						<select class='easyui-combobox' id="IsNewMajor" name="t322_Bean.IsNewMajor">					
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
						<input id="MajorLevel" type="text" name="t322_Bean.MajorLevel"
							class="easyui-validatebox" ><span id="MajorLevelSpan"></span>
					</div>
				</td>	
				<td>
					<div class="fitem">
						<label>类型：</label> 
						<select class='easyui-combobox' id="Type" name="t322_Bean.Type">
							<option value="特色专业">特色专业</option>
							<option value="品牌专业">品牌专业</option>
							<option value="名牌专业">名牌专业</option>
							<option value="示范专业">示范专业</option>
							<option value="重点建设专业">重点建设专业</option>
							<option value="地方优势专业">地方优势专业</option>
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
				<td>
					<div class="fitem">
						<label>建设负责人：</label> 
						<input id="Leader" type="text" name="t322_Bean.Leader"
							class="easyui-validatebox" ><span id="LeaderSpan"></span>
					</div>
				</td>				
			</tr>
			 <tr>
				<td>
					<div class="fitem">
						<label>教工号：</label> 
						<input id="TeaID" type="text" name="t322_Bean.TeaID"
							class="easyui-validatebox" ><span id="TeaIDSpan"></span>
					</div>
				</td>	
				<td>
					<div class="fitem">
						<label>验收时间：</label> 
						<input id="CheckTime" name="t322_Bean.CheckTime"
							class="easyui-datebox" editable="false" style="width:80px">
							<span id="CheckTimeSpan"></span>
					</div>
				</td>			
			</tr>		
	<tr>
			<td>
					<div class="fitem">
						<label>验收批文号：</label> 
						<input id="CheckAppvlID" type="text" name="t322_Bean.CheckAppvlID"
							class="easyui-validatebox" ><span id="CheckAppvlIDSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>学校经费（万元）：</label> 
						<input id="SchExp" type="text" name="t322_Bean.SchExp"
							class="easyui-validatebox" ><span id="SchExpSpan"></span>
					</div>
				</td>				
			</tr>
		 <tr>
				<td>
					<div class="fitem">
						<label>教育部经费(万元)：</label> 
						<input id="EduMinistryExp" type="text" name="t322_Bean.EduMinistryExp"
							class="easyui-validatebox" ><span id="EduMinistryExpSpan"></span>
					</div>
				</td>	
				<td>
					<div class="fitem">
						<label>首次通过认证时间：</label> 
						<input id="FirstAppvlTime" name="t322_Bean.FirstAppvlTime"
							class="easyui-datebox" editable="false" style="width:80px">
							<span id="FirstAppvlTimeSpan"></span>
					</div>
				</td>			
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>认证时间：</label> 
						<input id="AppvlTime" name="t322_Bean.AppvlTime"
							class="easyui-datebox" editable="false" style="width:80px">
							<span id="AppvlTimeSpan"></span>
					</div>
				</td>	
				<td>
					<div class="fitem">
						<label>批文号：</label> 
						<input id="AppvlID" type="text" name="t322_Bean.AppvlID"
							class="easyui-validatebox" ><span id="AppvlIDSpan"></span>
					</div>
				</td>				
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>认证结果：</label> 
						<select class='easyui-combobox' id="AppvlResult" name="t322_Bean.AppvlResult">
							<option value="通过">通过</option>
							<option value="未通过">未通过</option>
							<option value="未参加评估">未参加评估</option>
						</select>
						<span id="AppvlResultSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>有效期(起)：</label> 
						<input id="FromTime" name="t322_Bean.FromTime"
							class="easyui-datebox" editable="false" style="width:80px">
							<span id="FromTimeSpan"></span>
					</div>
				</td>			
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>有效期(止)：</label> 
						<input id="EndTime" name="t322_Bean.EndTime"
							class="easyui-datebox" editable="false" style="width:80px">
							<span id="EndTimeSpan"></span>
					</div>
				</td>	
				<td>
					<div class="fitem">
						<label>认证机构：</label> 
						<input id="AppvlAuth" type="text" name="t322_Bean.AppvlAuth"
							class="easyui-validatebox" ><span id="AppvlAuthSpan"></span>
					</div>
				</td>				
			</tr>
			<tr>

				<td>
					<div class="fitem">
						<label>必修课学分数：</label> 
						
						<input id="RequireCredit" type="text" name="t322_Bean.RequireCredit"
							class="easyui-validatebox" ><span id="RequireCreditSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>选修课学分数：</label> 
						<input id="MajorField" type="text" name="t322_Bean.MajorField"
							class="easyui-validatebox" ><span id="MajorFieldSpan"></span>
					</div>
				</td>
				
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>课内教学学分数：</label> 
						<input id="InClassCredit" type="text" name="t322_Bean.InClassCredit"
							class="easyui-validatebox" ><span id="InClassCreditSpan"></span>
					</div>
				</td>
							<td>
					<div class="fitem">
						<label>实验教学学分数：</label> 
						<input id="ExpCredit" type="text" name="t322_Bean.ExpCredit"
							class="easyui-validatebox" ><span id="ExpCreditSpan"></span>
					</div>
				</td>
			</tr>
			<tr>

				<td>
					<div class="fitem">
						<label>集中实践教学环节学分数：</label> 
						<input id="PraCredit" type="text" name="t322_Bean.PraCredit"
							class="easyui-validatebox" ><span id="PraCreditSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>课外科技活动学分数：</label> 
						<input id="OutClassCredit" type="text" name="t322_Bean.OutClassCredit"
							class="easyui-validatebox" ><span id="OutClassCreditSpan"></span>
					</div>
				</td>
			</tr>


			<tr>
				<td style="valign:left"><label>备&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
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
	    		 url: 'pages/MainTrainBasicInfoTea/uploadFile',
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
	    
	    function newPostDocSta(){
		    $('#dlg').dialog('open').dialog('setTitle','添加专业');
		    $('#UndergraMajorInfoForm').form('reset');
	    }

	    function singleImport(){
		    //录入数据的表单提交
	    	 $('#UndergraMajorInfoForm').form('submit',{
				    url: 'pages/UndergraMajorInfoTea/insert',
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
						    $('#unverifiedData').datagrid('reload'); 
					    }
				    }
			    });
		}

		function validate(){
			//获取文本框的值
            var MajorName = $('#MajorID').combobox('getText');
			var Note = $('#Note').val() ;
			//根据数据库定义的字段的长度，对其进行判断
			if(MajorName == null || MajorName.length == 0){
				$('#MajorNameSpan').html("<font style=\"color:red\">校内专业名称不能为空</font>") ;
				return false ;
			}else{
				$('#MajorNameSpan').html("") ;
			}
			

			
			if(Note !=null && Note.length > 1000){
				$('#NoteSpan').html("<font style=\"color:red\">备注中文字数不超过500</font>") ;
				return false ;
			}else{
				$('#NoteSpan').html("") ;
			}
			return true ;
		}


	    function exports() {
	    	var temp = encodeURI('表3-2-2本科专业基本情况（教学单位-教务处）.xls');
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

	    function editUser(){
	    	var row = $('#dg').datagrid('getSelections');
	    	if(row.length != 1){
	    		 $.messager.alert("信息提示","没选取或者选取了多行","info");  ;
	    		return ;
	    	}
	    	alert(row[0].birthday) ;
	    	var date = formattime(row[0].birthday) ;
	    	//为文本框赋值
	    	$('#id').val(row[0].id) ;
	    	$('#username').val(row[0].username) ;
	    	$('#password').val(row[0].password) ;
	    	$('#email').val(row[0].email) ;
	    	$('#sex').val(row[0].sex) ;
	    	$('#birthday').val(date) ;
	    	
	    	
		    if (row){
			    $('#dlg').dialog('open').dialog('setTitle','本科课程库');
			    $('#fm').form('load',row);
			    url = 'updateUser';
		    }
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

	    function editPostDocSta(){
	    	var row = $('#unverfiedData').datagrid('getSelections');
	    	
	    	if(row.length != 1){
	    		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
	    		return ;
	    	}
	    	
	    	url = 'pages/UndergraMajorInfoTea/edit' ;
	    	
	    	$('#dlg').dialog('open').dialog('setTitle','添加博士后流动站');
	    	$('#seqNumber').val(row[0].seqNumber) ;
	        $('#MajorName').val(row[0].MajorName);
	     
	    	$('#SetTime').datebox('setValue',formattime(row[0].setTime)) ;
	  
	    	$('#MajorID').val(row[0].MajorID) ;
	 
	    	$('#UnitID').combobox('select',row[0].unitID) ;
	
			$('#Note').val(row[0].note);
		
	    }
	    
	    
	    function loadDic(){
		    $('#dicDlg').dialog('open').dialog('setTitle','高级查询');
		    loadDictionary() ;
		    
	    }
	    
	    function loadDictionary(){
	    	
	    	$.ajax({ 
	    		type: "POST", 
	    		url: "table5/loadDic", 
	    		async:"false",
	    		dataType: "text",
	    		success: function(data){
	    			data = eval("(" + data + ")");
	    			alert(data[0].id) ;
	    			var str = "<table width=\"100%\" border=\"1\"><tr>" ;
	    			$(data).each(function(index) {
	    				var val = data[index];
	    				if(index%4 == 0 && index != 0){
	    					str += "</tr><tr>" ;
	    				}
	    				str += "<td><input type=\"checkbox\" id=\"" + val.id + "\"name=" + "\"checkboxex\"" +  "value=\"" + val.data + "\">" + val.data + "</input></td>" ; 
	    			}); 
	    			//alert(str);
	    			str += "</tr><tr><td colSpan=\"4\" style=\"text-align:center\"><a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" iconCls=\"icon-add\" onclick=\"loadData()\">添加</a></td></tr></table>" ;
	    			document.getElementById("dicTables").innerHTML = str;
	    			$.parser.parse('#dicTables');
	    		}
	    	}).submit();
	    }
	    	
	    function loadData(){
	    	
	    	//flag判断
	    	var flag = false ;
	    	var checkboxes = document.getElementsByName("checkboxex");
	    	var tables = "<div class=\"ftitle\">自定义查询条件</div><form method=\"post\" action=\"table5/dictorySearch\" id=\"dicsDataForm\"><table width=\"100%\" border=\"1\">" ;
	    	tables += "<tr><td>查询名称</td><td>运算符</td><td>查询内容</td><td>逻辑关系</td></tr>" ;
	    	for(i=0; i<checkboxes.length; i++){
	    		if(checkboxes[i].checked){
	    			flag = true ;
	    			tables += ("<tr><td style=\"width:50%px\">" + hideId(checkboxes[i].id,i)  + checkboxes[i].value + "</td><td>" + selectOperateData(i) + "</td><td>" + selectDataHtml(checkboxes[i].id,i) +"</td><td>" + selectLogicData(i) + "</td></tr>") ;
	    		}
	    	}
	    	if(flag){
	    		tables += "<tr><td colSpan=\"4\" style=\"text-align:center\"><a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" iconCls=\"icon-search\" onclick=\"submitDicForm()\">查询</a></td></tr>" ;
	    	}
	    	tables += "</table></form>" ;
	    	alert(tables) ;
	    	document.getElementById("dices").innerHTML = tables ;
	    	$.parser.parse('#dices');
	    	
	    }
	    
	    function hideId(val,index){
	    	var hiddenId = "<input type='hidden' name='dictorySearch[" + index + "].id' value='" + val + "'/>" ;
	    	
	    	return hiddenId ;
	    }
	    
	    //自动加载要查询的数据
	    function selectDataHtml(val,index){
	    	
	    	var selectsHtml = "<select class=\"easyui-combogrid\" style=\"width:50%px\" name=\"dictorySearch[" + index + "].dicData\" data-options=\"panelWidth: 500, multiple: true,required:true,"
	    	 + " idField: 'dicData',textField: 'dicData',"
	    	 + "url: 'table5/loadDictionary?dicId=" + val + "',"
	    	 + "method: 'post',"
	    	 + "columns: [[{field:'ck',checkbox:true},{field:'itemid',title:'数据',width:80},{field:'dicData',title:'数据',width:80}]],"
	    	 + "fitColumns: true \"> </select>" ;
	    	 
	    	 return selectsHtml ;
	    }
	    
	    //生成运算关系combo
	    function selectOperateData(index){
	    	
	    	var operateHtml = "<select style=\"width:15%px\" name=\"dictorySearch[" + index + "].operator\"> <option value=\"equals\">等于</option><option value=\"between\">之间</option><option value=\"side\">两边</option></select>" ;
	    	
	    	return operateHtml ;
	    }
	    
	  //生成逻辑关系combo
	    function selectLogicData(index){
	    	
	    	var logicHtml = "<select style=\"width:15%px\" name=\"dictorySearch[" + index + "].logic\"> <option value=\"and\">并且</option><option value=\"or\">或者</option></select>" ;
	    	
	    	return logicHtml ;
	    }
	  
	  function submitDicForm(){
		  $.ajax({ 
	    		type: "POST", 
	    		url: "table3/dictorySearch",
	    		data: $('#dicsDataForm').serialize(), 
	    		async:"false",
	    		dataType: "text",
	    		success: function(data){
	    			alert(123) ;
	    		}
	    	}).submit();
	  }
	    
	    </script>

	<script type="text/javascript"> 
			//日期格式转换 
			function formattime(val) {  
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

