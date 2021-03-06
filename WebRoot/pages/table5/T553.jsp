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
    
    <title>My JSP 'T553.jsp' starting page</title>
    
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
	<script type="text/javascript" src="js/table5/T553.js"></script>
</head>
<% request.setAttribute("CHECKTYPE",Constants.CTypeOne); %>
<% request.setAttribute("NOCHECK",Constants.NO_CHECK); %>
<% request.setAttribute("PASS",Constants.PASS_CHECK); %>
<body style="overflow-y:scroll" onload="myMarquee('T553','<%=request.getAttribute("CHECKTYPE") %>')">
<div  id="floatDiv">
        <span style="font:12px; font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;审核未通过提示消息：</span>
        <marquee id="marquee"  scrollAmount="1"  width="900"  height="40" direction="up"  style="color: red;"  onmouseover="stop()" onmouseout="start()">
        </marquee>       
  </div>
  <br/> 

	<table id="unverfiedData" title="待审核数据域审核未通过数据" class="easyui-datagrid" 
	url="pages/T553/auditingData?checkNum=<%=request.getAttribute("NOCHECK") %>"
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="false" >
		<thead data-options="frozen:true">
		<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th  data-options="field:'seqNumber'" >编号</th>
				<th  data-options="field:'checkState'"   formatter="formatCheckState">审核状态</th>
		     </tr>
		</thead>
		<thead>
				<tr>					
					<th data-options="field:'awardName'">
						获奖名称
					</th>
					<th data-options="field:'awardStuName'">
						获奖学生姓名
					</th>
					<th data-options="field:'stuID'">
						学号
					</th>
					<th data-options="field:'teaUnit'">
						所在教学单位
					</th>
					<th data-options="field:'fromClass'">
						所在班级
					</th>
					<th data-options="field:'awardLevel'">
						级别
					</th>
					<th data-options="field:'awardTime'" formatter="formattime">
						获奖时间
					</th>
					<th data-options="field:'note'">
						备注
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
		 <div>
		  <form method="post" id="auditing"
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
	</div>
	
	<table id="verfiedData" title="审核通过数据" class="easyui-datagrid"  
	   url="pages/T553/auditingData?checkNum=<%=request.getAttribute("PASS") %>"
		toolbar="#toolbar2" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="false">
		<thead data-options="frozen:true">
		<tr>	
		<th data-options="field:'ck',checkbox:true">选取</th>		
				<th  data-options="field:'seqNumber'" >编号</th>
		     </tr>
		</thead>
		<thead>
				<tr>					
					<th data-options="field:'awardName'">
						获奖名称
					</th>
					<th data-options="field:'awardStuName'">
						获奖学生姓名
					</th>
					<th data-options="field:'stuID'">
						学号
					</th>
					<th data-options="field:'teaUnit'">
						所在教学单位
					</th>
					<th data-options="field:'fromClass'">
						所在班级
					</th>
					<th data-options="field:'awardLevel'">
						级别
					</th>
					<th data-options="field:'awardTime'" formatter="formattime">
						获奖时间
					</th>
					<th data-options="field:'note'">
						备注
					</th>
				</tr>
			</thead>
	</table>
	
	<div id="toolbar2" style="float: right;">
	<form action='pages/T553/dataExport?excelName=<%=URLEncoder.encode("表5-5-3优秀本科生","UTF-8")%>'   method="post"  id="exportForm" enctype="multipart/form-data"  style="float: right;">
					  <select class="easyui-combobox"  id="cbYearContrast1" name="selectYear"  editable=false ></select>&nbsp;&nbsp;
						<a href='javascript:submitForm()'   style="font:12px;color: black;text-decoration:none;" >
								数据导出
						</a> &nbsp;&nbsp;&nbsp;&nbsp;		
			</form>
		
	</div>
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="title1">优秀本科生批量导入</h3>
		<div class="fitem" id="item1">
			<form method="post" id="batchForm" enctype="multipart/form-data">
			<select class="easyui-combobox"  id="cbYearContrast" name="selectYear"></select>
				<input type="file" name="uploadFile" id="fileToUpload" class="easyui-validatebox" size="48" style="height: 24px;" required="true" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href="pages/T553/downloadModel?saveFile=<%=URLEncoder.encode("表5-5-3优秀本科生.xls","UTF-8")%>"  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
			<a href="123"></a>
		</div>	
		<hr></hr>	
			
		<h3 class="title1">优秀本科生逐条导入</h3>
		
		
		<form id="t553Form" method="post">
			<table>
			<tr>
				<td>
					<input type="hidden" name="t553_Bean.SeqNumber"  id="seqNumber"/>
					<input type="hidden" name="t553_Bean.Time"  id="Time"/>
					<div class="fitem">
						<label>获奖名称：</label> 
						<input id="AwardName" type="text" name="t553_Bean.AwardName"
							><span id="AwardNameSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>获奖学生姓名：</label> 
						<input id="AwardStuName" type="text" name="t553_Bean.AwardStuName"
							><span id="AwardStuNameSpan"></span>
					</div>
				</td>
			</tr>	
			
			<tr>
				<td>
					<div class="fitem">
						<label>学号：</label> 
						<input id="StuID" type="text" name="t553_Bean.StuID"
							><span id="StuIDSpan"></span>
					</div>
				</td>
			<td class="empty"></td>
				<td>
			   <div class="fitem">
				<label>所在教学单位：</label> 
				<input id="TeaUnit" type="text" name="t553_Bean.TeaUnit"><span id="TeaUnitSpan"></span>	
				   </div>				   
			    </td>
			</tr>
			<tr>
			     <td>
					<div class="fitem">
						<label>级别：</label> 
						<input class='easyui-combobox' id="AwardLevel" name="t553_Bean.AwardLevel"
							data-options="valueField:'indexId',textField:'awardLevel',url:'pages/DiAwardLevel/loadDiAwardLevel',listHeight:'auto',editable:false">
						<span id="AwardLevelSpan"></span>					
					</div>
				</td>
				<td class="empty"></td>
			    <td>
					<div class="fitem">
						<label>所在班级：</label> 
						<input id="FromClass" type="text" name="t553_Bean.FromClass"
							><span id="FromClassSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
				<div class="fitem">
				<label>获奖时间：</label> 
					<input  id="AwardTime"  class="easyui-datebox" style="width:80px" name="t553_Bean.AwardTime" editable = "false">
						<span id="AwardTimeSpan"></span>
				</div></td>
			</tr>
			<tr>
				<td style="valign:left" colspan="3"><label>备注：</label>
					<textarea id="Note" name="t553_Bean.Note" style="resize:none" cols="50" rows="10"></textarea>
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
