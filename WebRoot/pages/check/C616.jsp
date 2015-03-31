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

<title>C616</title>
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
	<script type="text/javascript" src="js/check/C616.js"></script>
</head>
<% request.setAttribute("CHECKTYPE",Constants.CTypeThree); %>
<body style="height: 100%"> 
		<table id="newData"  style="height: auto" >		
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"  rowspan="2">选取</th>
				<th  data-options="field:'seqNumber'" rowspan="2" hidden="true">编号</th>
				<th data-options="field:'stuType'" rowspan="2">
				学生类别
				</th>
				
				<th  colspan="5">1.毕（结）业生数（人）</th>
				<th  colspan="5">2.授予学位数（人）</th>
				<th  colspan="5">3.招生数（人）</th>
				<th  colspan="5">4.在校生数（人）</th>
				
			</tr>
			<tr>
				<th data-options="field:'sumGraNum'">
				小计
				</th>
				<th data-options="field:'graOutNum'">
	       		国外
				</th>
				<th data-options="field:'graHongNum'">
				香港
				</th>
				<th data-options="field:'graAoNum'">
				澳门
				</th>
				<th data-options="field:'graTaiNum'">
	       		台湾
				</th>
				<th data-options="field:'sumDegreeNum'">
				小计
				</th>
				<th data-options="field:'degreeOutNum'">
	       		国外
				</th>
				<th data-options="field:'degreeHongNum'">
				香港
				</th>
				<th data-options="field:'degreeAoNum'">
				澳门
				</th>
				<th data-options="field:'degreeTaiNum'">
	       		台湾
				</th>
				<th data-options="field:'sumAdmisNum'">
				小计
				</th>
				<th data-options="field:'admisOutNum'">
	       		国外
				</th>
				<th data-options="field:'admisHongNum'">
				香港
				</th>
				<th data-options="field:'admisAoNum'">
				澳门
				</th>
				<th data-options="field:'admisTaiNum'">
	       		台湾
				</th>
				<th data-options="field:'sumInSchNum'">
				小计
				</th>
				<th data-options="field:'inSchOutNum'">
	       		国外
				</th>
				<th data-options="field:'inSchHongNum'">
				香港
				</th>
				<th data-options="field:'inSchAoNum'">
				澳门
				</th>
				<th data-options="field:'inSchTaiNum'">
	       		台湾
				</th>
			</tr>			
	</thead>
	</table>
								 
	<div id="toolbar" style="height:30px">
		<div style="float: left;">	
			<a href="javascript:void(0)" id="pass"  class="easyui-linkbutton" iconCls="icon-save" plain="true" >审核通过</a>
			<a href="javascript:openDig()" id="nopass"  class="easyui-linkbutton" iconCls="icon-save" plain="true">审核未通过</a>
			<a href="javascript:openDig()" id="renopass"  class="easyui-linkbutton" iconCls="icon-save" plain="true">重新审核未通过</a>	
		</div>
	 	 <form  id="exportForm"  style="float: right;"  method="post" >
			显示： <select class="easyui-combobox" id="cbYearContrast" panelHeight="auto" style="width:80px; padding-top:5px; margin-top:10px;"  editable=false ></select>
	  </form>	
	</div>
	
	<div id="dlg" class="easyui-dialog"
		style="width:400px;height:270px;padding:10px 20px;" closed="true"
		data-options="modal:true" buttons="#dlg-buttons">
		<form id="addReasonForm" method="post">
			<table>			
				<tr>
					<td>
						<div class="fitem">
							<label>审核类型：</label> 
								<input type="text" name="checkInfo.checkType"  id="checkType"   value="<%=request.getAttribute("CHECKTYPE") %>"
								readonly="readonly"  style="width: 150px;color: grey"/>
							</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="fitem">
							<label>被审核表ID：</label> 
								<input type="text" name="checkInfo.tableID" id="tableName"   value="T616"
								readonly="readonly"  style="width: 150px;color: grey"/>
							</div>
					</td>
				</tr>
				<tr>
					<td>
							<div class="fitem">
								<label>该条数据年限：</label> 
								<input type="text" name="checkInfo.checkID" id="dataID" 
								readonly="readonly"  style="width: 150px;color: grey"/>
							</div>
					</td>
				</tr>
				<tr>
					<td>
							<div class="fitem">
								<label>审核不通过理由描述：</label> 
								<textarea id="noPassReason" name="checkInfo.checkInfo" style="resize:none" cols="30" rows="5"></textarea>
							</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="addCheckInfo()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
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
