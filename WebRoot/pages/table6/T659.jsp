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

<title>学习成果—本科生交流情况</title>
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
	<script type="text/javascript" src="js/table6/T659.js"></script>
</head>

<% request.setAttribute("CHECKTYPE",Constants.CTypeThree); %>
<% request.setAttribute("WAITCHECK",Constants.WAIT_CHECK); %>
<% request.setAttribute("NOPASS",Constants.NOPASS_CHECK); %>
<% request.setAttribute("PASS",Constants.PASS_CHECK); %>
<body style="height: 100%'"   onload = "myMarquee('T659','<%=request.getAttribute("CHECKTYPE") %>')">

<div  id="floatDiv">
        <span style="font:12px; font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;审核未通过提示消息：</span>
        <marquee id="marquee"  scrollAmount="1"  width="900"  height="40" direction="up"  style="color: red;"  onmouseover="stop()" onmouseout="start()">
        </marquee>       
  </div>
    <br/>

	<table id="newData"  style="height: auto" >		
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"  rowspan="2">选取</th>
				<th  data-options="field:'seqNumber'" rowspan="2" hidden="true">编号</th>
				<th data-options="field:'teaUnit'" rowspan="2">
			   	教学单位
				</th>
				<th data-options="field:'unitId'" rowspan="2">
	       		单位号
				</th>
				<th  colspan="5">交流学生数（个）</th>
			</tr>
			<tr>
				<th data-options="field:'exchangeStuSum'">
				总数
				</th>
				<th data-options="field:'fromSchToOverseas'">
	       		本校到境外
				</th>
				<th data-options="field:'fromSchToDomestic'">
				本校到境内
				</th>
				<th data-options="field:'fromDomesticToSch'">
				境内到本校
				</th>
				<th data-options="field:'fromOverseasToSch'">
	       		境外到本校
				</th>
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
	
		<!--添加弹出框-->
	<div id="dlg" class="easyui-dialog"
	style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
	   <form id="addForm" method="post">
		<table>
			<tr>
			<td >
				<input type="hidden" name="T659_bean.seqNumber" id="seqNumber"/>
				<div class="fitem">
				<label>教学单位：</label> 
				<input id="teaUnit" type="text" name="T659_bean.teaUnit"
				class="easyui-validatebox" ><span id="teaUnitSpan"></span>
				</div>
				</td>
				<td class="empty"></td>
				<td>
				<div class="fitem">
				<label>单位号：</label> 
				<input id="unitId" type="text" name="T659_bean.unitId"
				class="easyui-validatebox" ><span id="unitIDSpan"></span>
				</div>
				</td>
		
		</tr>
		<tr>
		
		<td>
					<div class="fitem">
						<label>交流学生总数：</label> 
						<input class="easyui-numberbox"   id="exchangeStuSum"  type="text"  
						name="T659_bean.exchangeStuSum"  editable="false" />
						<span id="exchangeStuSumSpan"></span>
					</div>
				</td>
				
		</tr>

			<tr>
				<td>
					<div class="fitem">
						<label>本校到境外：</label> 
						<input class="easyui-numberbox"   id="fromSchToOverseas"  type="text" 
						name="T659_bean.fromSchToOverseas"  editable="false" />
						<span id="fromSchToOverseasSpan" ></span>
					</div>
				</td>
				<td class="empty"></td>
				
				<td>
					<div class="fitem">
						<label>本校到境内：</label> 
						<input class="easyui-numberbox"   id="fromSchToDomestic"  type="text" 
						name="T659_bean.fromSchToDomestic"  editable="false" />
						<span id="fromSchToDomesticSpan"></span>
					</div>
				</td>
			</tr>		
			<tr>
				<td>			
					<div class="fitem">
						<label>境内到本校：</label>
						<input class="easyui-numberbox"   id="fromDomesticToSch"  type="text" 
						name="T659_bean.fromDomesticToSch"  editable="false" /> 
						<span id="fromDomesticToSchSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				
				<td>			
					<div class="fitem">
						<label>境外到本校：</label> 
						<input class="easyui-numberbox"   id="fromOverseasToSch"  type="text"  
						name="T659_bean.fromOverseasToSch"  editable="false" /> 
						<span id="fromOverseasToSchSpan"></span>
					</div>
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
