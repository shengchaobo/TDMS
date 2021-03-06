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

<title>T632</title>
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
	<script type="text/javascript" src="js/table6/T632.js"></script>
</head>

<% request.setAttribute("CHECKTYPE",Constants.CTypeThree); %>
<% request.setAttribute("WAITCHECK",Constants.WAIT_CHECK); %>
<% request.setAttribute("NOPASS",Constants.NOPASS_CHECK); %>
<% request.setAttribute("PASS",Constants.PASS_CHECK); %>
<body style="height: 100%'"   onload = "myMarquee('T632','<%=request.getAttribute("CHECKTYPE") %>')">
  <div  id="floatDiv">
        <span style="font:12px; font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;审核未通过提示消息：</span>
        <marquee id="marquee"  scrollAmount="1"  width="900"  height="40" direction="up"  style="color: red;"  onmouseover="stop()" onmouseout="start()">
        </marquee>       
  </div>
  <br/>
	<table id="newData"  style="height: auto">		
		<thead data-options="frozen:true">
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber">编号</th>
				<th field="teaUnit">教学单位</th>
				<th field="unitId">单位号</th>
				<th field="majorName">专业名称</th>
				<th field="majorId">专业代码</th>
			</tr>
	    </thead>	
		<thead>
			<tr>
				<th field="sumEmployNum">应届就业总人数</th>
				<th field="govermentNum">政府机构就业人数</th>				
				<th field="pubInstiNum">事业单位就业人数</th>
				<th field="enterpriseNum">企业就业人数</th>
				<th field="forceNum">部队人数</th>
				<th field="flexibleEmploy">灵活就业人数</th>
				<th field="goOnHighStudy">升学人数</th>
				<th field="nationItemEmploy">参加国家地方项目就业人数</th>
				<th field="otherEmploy">其他人数</th>
				
				<th field="sumGoOnHighStudyNum">应届升学总人数</th>
				<th field="recommendGraNum">免试推荐研究生人数</th>
				<th field="examGraApplyNum">考研报名人数</th>
				<th field="examGraEnrollNum">考研录取总人数</th>
				<th field="examGraInSch">考取本校人数</th>
				<th field="examGraOutSch">考取外校人数</th>
				<th field="abroadNum">出国（境）留学人数</th>
								
				<th field="note">备注</th>
			</tr>
		</thead>
	</table>
								 
	<div id="toolbar" style="height:auto">
		<div style="float: left;">
			<a href="javascript:void(0)"  id="newObject"  class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newObject()">添加</a>
			<a href="javascript:void(0)"  id="edit"  class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">编辑</a> 
			<a href="javascript:void(0)"  id="delete"  class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteByIds()">删除</a>	
			<a href="javascript:void(0)"  id="export" class="easyui-linkbutton" iconCls="icon-download" plain="true"  onclick="exports()">数据导出</a>
		</div>
	 	 <form  id="exportForm"  style="float: right;"  method="post" >
			显示： <select class="easyui-combobox" id="cbYearContrast" panelHeight="auto" style="width:80px; padding-top:5px; margin-top:10px;"  editable=false ></select>
	 	</form>	
	</div>
	
	<!--添加弹出框-->
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
	   <form id="addForm" method="post">
		<table>
			<tr>
				<td>
					<div class="fitem">
						<label>教学单位：</label> 
						<input id="seqNumber" type="hidden" name="T632_bean.seqNumber">	
						<input id="TeaUnit" type="hidden" name="T632_bean.teaUnit">										
						<input id="unitId" type="text" name="T632_bean.unitId" class='easyui-combobox'
							data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDIDepartmentAca',listHeight:'auto',editable:false,
							onSelect:function(){
							 	 document.getElementById('TeaUnit').value=$(this).combobox('getText') ;
							 }">
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>专业名称：</label> 
						<input id="majorName" type="hidden" name="T632_bean.majorName">
						<input id="majorId" type="text" name="T632_bean.majorId" class='easyui-combobox'
							data-options="valueField:'majorNum',textField:'majorName',url:'pages/DiMajorTwo/loadDiMajorTwo',listHeight:'auto',editable:false,
							onSelect:function(){
							 	 document.getElementById('majorName').value=$(this).combobox('getText') ;
							 }"></input>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>政府机构就业人数：</label> 
						<input id="govermentNum" name="T632_bean.govermentNum" class='easyui-validatebox'><span id="govermentNumSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>事业单位就业人数：</label> 
						<input id="pubInstiNum" name="T632_bean.pubInstiNum" 
							 class='easyui-validatebox'><span id="pubInstiNumSpan"></span>
					</div>
				</td>
			</tr>
	
			<tr>
				<td>			
					<div class="fitem">
						<label>企业就业人数：</label> 
						<input id="enterpriseNum" name="T632_bean.enterpriseNum" 
							 class='easyui-validatebox'><span id="enterpriseNumSpan"></span>
					</div>
				</td>

				<td class="empty"></td>
				<td>			
					<div class="fitem">
						<label>部队人数：</label> 
						<input id="forceNum" name="T632_bean.forceNum" 
							 class='easyui-validatebox'><span id="forceNumSpan"></span>
					</div>
				</td>
			</tr>
			
			<tr>
				<td>			
					<div class="fitem">
						<label>灵活就业人数：</label> 
						<input id="flexibleEmploy" name="T632_bean.flexibleEmploy" class='easyui-validatebox'><span id="flexibleEmploySpan"></span>
						
					</div>
				</td>
				<td class="empty"></td>
			<td>			
					<div class="fitem">
						<label>参加国家地方项目就业人数：</label> 
						<input id="nationItemEmploy" name="T632_bean.nationItemEmploy" 
							 class='easyui-validatebox'><span id="nationItemEmploySpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>			
					<div class="fitem">
						<label>其他人数：</label> 
						<input id="otherEmploy" name="T632_bean.otherEmploy" 
							 class='easyui-validatebox'><span id="otherEmploySpan"></span>
					</div>
				</td>
					<td class="empty"></td>
					<td>			
					<div class="fitem">
						<label>免试推荐研究生人数：</label> 
						<input id="recommendGraNum" name="T632_bean.recommendGraNum" 
							 class='easyui-validatebox'><span id="sumGoOnHighStudyNumSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>			
					<div class="fitem">
						<label>考研报名人数：</label> 
						<input id="examGraApplyNum" name="T632_bean.examGraApplyNum" 
							 class='easyui-validatebox'><span id="examGraApplyNumSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>			
					<div class="fitem">
						<label>考取本校人数：</label> 
						<input id="examGraInSch" name="T632_bean.examGraInSch" 
							 class='easyui-validatebox'><span id="examGraInSchSpan"></span>
					</div>
				</td>
				</tr>

			<tr>
				<td>			
					<div class="fitem">
						<label>考取外校人数：</label> 
						<input id="examGraOutSch" name="T632_bean.examGraOutSch" 
							 class='easyui-validatebox'><span id="examGraOutSchSpan"></span>
					</div>
				</td>
					<td class="empty"></td>
					<td>			
					<div class="fitem">
						<label>出国（境）留学人数：</label> 
						<input id="abroadNum" name="T632_bean.abroadNum" 
							 class='easyui-validatebox'><span id="abroadNumSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td style="valign:left" colspan="3"><label>备注：</label>
					<textarea id="note" name="T632_bean.note" style="resize:none" cols="50" rows="10"></textarea>
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
</script>
		
</html>
