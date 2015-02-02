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

<title>学习成果—英语四六级、计算机等级考试</title>
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
	<script type="text/javascript" src="js/table6/T655.js"></script>
</head>


<% request.setAttribute("CHECKTYPE",Constants.CTypeThree); %>
<% request.setAttribute("WAITCHECK",Constants.WAIT_CHECK); %>
<% request.setAttribute("NOPASS",Constants.NOPASS_CHECK); %>
<% request.setAttribute("PASS",Constants.PASS_CHECK); %>

<body style="height: 100%'" onload = "myMarquee('T655','<%=request.getAttribute("CHECKTYPE") %>')">

  <div  id="floatDiv">
        <span style="font:12px; font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;审核未通过提示消息：</span>
        <marquee id="marquee"  scrollAmount="1"  width="900"  height="40" direction="up"  style="color: red;"  onmouseover="stop()" onmouseout="start()">
        </marquee>       
  </div>
  <br/>
  
	<table id="newData" style="height: auto">
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
				<th field="CET4PassRate" formatter="formatRatio">1.英语四级考试累计通过率（%）</th>
				<th field="CET6PassRate" formatter="formatRatio">2.英语六级考试累计通过率（%）</th>
				<th field="jiangxiNCREPassRate"  formatter="formatRatio">3.江西省高校计算机等级考试累计通过率（%）</th>			
				<th field="note">备注</th>
				<!--  <th field="time" formatter="formattime">时间</th>-->
			</tr>
		</thead>
	</table>
	
	
		<div id="toolbar" style="height:auto">
		<div style="float: left;">
			<a href="javascript:void(0)" id="edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">编辑</a> 
			<a href="javascript:void(0)" id="export" class="easyui-linkbutton" iconCls="icon-download" plain="true"  onclick="exports()">数据导出</a>
		</div>
	 	 <form  id="exportForm"  style="float: right;"  method="post" >
			显示： <select class="easyui-combobox" id="cbYearContrast" panelHeight="auto" style="width:80px; padding-top:5px; margin-top:10px;"  editable=false ></select>
	 	</form>	
	</div>
	
	
	<!-- 弹出框 -->
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<form id="addForm" method="post">
		<table>
		
		<tr>
			<td >
				<input type="hidden" name="T655_bean.seqNumber" id="seqNumber"/>
				<div class="fitem">
				<label>教学单位：</label> 
				<input id="teaUnit" type="text" name="T655_bean.teaUnit"
				class="easyui-validatebox" ><span id="teaUnitSpan"></span>
				</div>
				</td>
				<td class="empty"></td>
				<td>
				<div class="fitem">
				<label>单位号：</label> 
				<input id="unitID" type="text" name="T655_bean.unitId"
				class="easyui-validatebox" ><span id="unitIDSpan"></span>
				</div>
				</td>
		</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>英语四级考试累计通过率（%）：</label> 
						<input id="CET4PassRate" name="T655_bean.CET4PassRate" class='easyui-numberbox' min='0' max='100' precision='2'>
						<span id="CET4PassRateSpan"style="color:blue">%</span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>英语六级考试累计通过率（%）：</label> 
						<input id="CET6PassRate" name="T655_bean.CET6PassRate" class='easyui-numberbox' min='0'  max='100' precision='2'>
						<span id="CET6PassRateSpan" style="color:blue">%</span>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div class="fitem">
						<label>江西省高校计算机等级考试累计通过率（%）：</label> 
						<input id="jiangxiNCREPassRate" name="T655_bean.jiangxiNCREPassRate" class='easyui-numberbox' min='0' max='100' precision='2'>
						<span id="jiangxiNCREPassRateSpan" style="color:blue">%</span>
					</div>
				</td>
			</tr>
		
			<tr>
				<td style="valign:left" colspan="3"><label>备注：</label>
					<textarea id="note" name="T655_bean.note" style="resize:none" cols="50" rows="10"></textarea>
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
	
		
	 <script type="text/javascript">
		   function formatRatio(val){
		        var str = val+"";
			   var ratio=str+"%";
			   return ratio;
		   }
		</script>

</html>
