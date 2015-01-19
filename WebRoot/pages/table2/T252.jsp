<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<%@ page language="java" import="cn.nit.constants.Constants"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
<base href="<%=basePath%>">

<title>T252</title>
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
	<script type="text/javascript" src="js/table2/T252.js"></script>
</head>
<% request.setAttribute("CHECKTYPE",Constants.CTypeTwo); %>
<% request.setAttribute("NOCHECK",Constants.NO_CHECK); %>
<% request.setAttribute("PASS",Constants.PASS_CHECK); %>
<body style="height: 100%'"  onload="myMarquee('T252','<%=request.getAttribute("CHECKTYPE") %>')">
  <div  id="floatDiv">
        <span style="font:12px; font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;审核未通过提示消息：</span>
        <marquee id="marquee"  scrollAmount="1"  width="900"  height="40" direction="up"  style="color: red;"  onmouseover="stop()" onmouseout="start()">
        </marquee>       
  </div>
  <br/> 
  
	<table  id="unverfiedData"  class="easyui-datagrid"  url="pages/T252/loadExpInfo?checkNum=<%=request.getAttribute("NOCHECK") %>"  style="height: auto"  >
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th  data-options="field:'seqNumber'" >编号</th>
				<th  data-options="field:'checkState'"   formatter="formatCheckState">审核状态</th>
				<th data-options="field:'expCenterName'">
					  实验中心名称
				</th>
				<th data-options="field:'teaUnit'">
					所属教学单位
				</th>
		     </tr>		     
		</thead>
		<thead>
				<tr>					
					<th data-options="field:'teaUnitID'">
						教学单位号
					</th>
					<th data-options="field:'labName'">
						下属实验室名称
					</th>
					<th data-options="field:'expClassHour'">
						每次实验教学学时数
					</th>	
					<th data-options="field:'stuNum'">
						每次可容纳的学生数（个）
					</th>
					<th data-options="field:'expHour'">
						学年度承担的实验教学人时数（人时）
					</th>		
					<th data-options="field:'expTimes'">
						学年度承担的实验教学人次数（人次）
					</th>	
					<th data-options="field:'practiseItemNum'">
						本科生实验、实习、实训项目数（个）
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
	
	<table id="verfiedData"  class="easyui-datagrid"  url="pages/T252/loadExpInfo?checkNum=<%=request.getAttribute("PASS") %>"   style="height: auto;" >
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th  data-options="field:'seqNumber'" >编号</th>
				<th data-options="field:'expCenterName'">
					  实验中心名称
				</th>
				<th data-options="field:'teaUnit'">
						所属教学单位
				</th>
		     </tr>		     
		</thead>
		<thead>
				<tr>					
					<th data-options="field:'teaUnitID'">
						教学单位号
					</th>
					<th data-options="field:'labName'">
						下属实验室名称
					</th>
					<th data-options="field:'expClassHour'">
						每次实验教学学时数
					</th>	
					<th data-options="field:'stuNum'">
						每次可容纳的学生数（个）
					</th>
					<th data-options="field:'expHour'">
						学年度承担的实验教学人时数（人时）
					</th>		
					<th data-options="field:'expTimes'">
						学年度承担的实验教学人次数（人次）
					</th>	
					<th data-options="field:'practiseItemNum'">
						本科生实验、实习、实训项目数（个）
					</th>																														
				</tr>
			</thead>
	</table>
	<div id="toolbar2" style="float: right;">	
			<form action='pages/T252/dataExport?excelName=<%=URLEncoder.encode("表2-5-2本科实验、实习、实训场所-教学情况（教学单位-教务处）","UTF-8")%>'   method="post"  id="exportForm" enctype="multipart/form-data"  style="float: right;">
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
		<h3 class="title1">本科实验、实习、实训场所信息模板导入</h3>
		<div class="fitem" id="item1">
		  <form method="post"  id="batchForm" enctype="multipart/form-data" editable=false>
		 		 <select class="easyui-combobox"  id="cbYearContrast" name="selectYear"></select>
				<input type="file" name="uploadFile" id="fileToUpload" class="easyui-validatebox" size="48" style="height: 24px;" required="true" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href='pages/T252/downloadModel?saveFile=<%=URLEncoder.encode("表2-5-2本科实验、实习、实训场所-教学情况.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
		  </form>
		</div>	
		<hr></hr>	
	   <h3 class="title1">本科实验、实习、实训场所信息逐条导入</h3>
	   <form id="addForm" method="post">
		<table>	
			<tr>
				<td>
					<input type="hidden" name="T252_bean.seqNumber" id="seqNumber"/>
					<div class="fitem">
					<label>实验中心名称：</label> 
					<input id="expCenterName" type="text" name="T252_bean.expCenterName"
					class="easyui-validatebox" ><span id="expCenterNameSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<input type="hidden"  id="teaUnit"  name="T252_bean.teaUnit"/>
						<input type="hidden" id="teaUnitID"  name="T252_bean.teaUnitID" />
						 <span id="officeIDSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>下属实验室名称：</label> 
						<input class="easyui-validatebox"  id="labName" type="text" 
						name="T252_bean.labName"  editable="false" />
						<span id="labNameSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>每次实验教学学时数：</label> 
						<input class="easyui-validatebox"  id="expClassHour" type="text" 
						name="T252_bean.expClassHour"  editable="false" />
						<span id="expClassHourSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>每次可容纳的学生数（个）：</label> 
						<input class="easyui-validatebox"  id="stuNum" type="text" 
						name="T252_bean.stuNum"  editable="false" />
						<span id="stuNumSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>学年度承担的实验教学人时数（人时）：</label> 
						<input id="expHour" type="text" name="T252_bean.expHour" class="easyui-validatebox"  >
						<span id="expHourSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>学年度承担的实验教学人次数（人次）：</label> 
						<input class="easyui-validatebox"  id="expTimes" type="text" 
						name="T252_bean.expTimes"  editable="false" />
						<span id="expTimesSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>本科生实验、实习、实训项目数（个）：</label> 
						<input id="practiseItemNum" type="text" name="T252_bean.practiseItemNum" class="easyui-validatebox" >
						<span id="practiseItemNumSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td style="valign:left" colspan="3"><label>备注：</label>
					<textarea id="note" name="T252_bean.note" style="resize:none" cols="50" rows="10"></textarea>
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
    	
	     var select1 = document.getElementById("cbYearContrast1");
    	for (var i = 0; i <= 10; i++) {
        var theOption = document.createElement("option");
        	theOption.innerHTML = currentYear-i + "年";
        	theOption.value = currentYear-i;
        	select1.appendChild(theOption);
    	}
	</script>
</html>
