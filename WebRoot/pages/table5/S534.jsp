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

<title>S512</title>
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
			width: 120px;
		}
	</style>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="js/commom.js"></script>
	<script type="text/javascript" src="js/table5/S534.js"></script>

	
</head>
<body style="overflow-y:scroll">
	<table id="showData"  style="height: auto">	
		
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'teaUnit',align:'center'" rowspan="2" >
				          教学单位
					</th>
		     </tr>
		</thead>
		<thead>
			<tr>
			   <tr>
					
					<th  colspan="8" >
				        1.指导教师数（人）
					</th>
					<th  colspan="2" >
				        2.课题数（个）
					</th>
					<th  colspan="2">
				       3.学生数（人）
					</th>
				</tr>
				
				<tr>
						<th data-options="field:'sumTeaNum',align:'center'">
						总人数
						</th>
						<th data-options="field:'outHireTeaNum',align:'center'" >
						其中外聘教师
						</th>
						<th data-options="field:'highTitle',align:'center'">
						其中正高职称
						</th>
						<th data-options="field:'viceHighTitle',align:'center'" >
						其中副高职称
						</th>
						<th data-options="field:'midTitle',align:'center'">
						其中中级职称
						</th>
						<th data-options="field:'graDegree',align:'center'" >
						其中研究生学历
						</th>
						<th data-options="field:'aboveGraDegree',align:'center'">
						其中硕士以上学位
						</th>
						<th data-options="field:'schGoodTea',align:'center'" >
						其中获评校级优秀指导教师
						</th>
						<th data-options="field:'issueNum',align:'center'">
						数量
						</th>
						<th data-options="field:'praIssueNum',align:'center'" >
						其中在社会实践中完成
						</th>
						<th data-options="field:'stuNum',align:'center'">
						总人数
						</th>
						<th data-options="field:'goodStuNum',align:'center'" >
						获评优秀毕业设计学生人数
						</th>
						
				</tr>
				
			</thead>
	</table>
			 
	<div id="toolbar" style="height:auto">
		<div style="float: left;">	
			<a href="javascript:void(0)" id="export" class="easyui-linkbutton" iconCls="icon-download" plain="true"  onclick="exports()">数据导出</a>
		</div>
	 	 <form  id="exportForm"  style="float: right;"  method="post" >
			显示： <select class="easyui-combobox" id="cbYearContrast" panelHeight="auto" style="width:80px; padding-top:5px; margin-top:10px;"  editable=false ></select>
	 	</form>	
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

