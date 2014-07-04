<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>S-5-2课程建设情况统计</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
		<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link rel="stylesheet" type="text/css"
			href="jquery-easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css"
			href="jquery-easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css"
			href="jquery-easyui/themes/main.css">
		<link rel="stylesheet" type="text/css"
			href="jquery-easyui/demo/demo.css">

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
		<script type="text/javascript"
			src="jquery-easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
		<script type="text/javascript"
			src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
				<script type="text/javascript" src="js/commom.js"></script>
	<script type="text/javascript" src="js/table5/S52.js"></script>
	</head>
	
	<body style="overflow-y: scroll">
		<table class="easyui-datagrid" toolbar="#toolbar" title="S-5-2课程建设情况统计"></table>
		
		<hr color="blue" width="100%" />
		<table id="showInfo" class="doc-table"  url="">
			<tbody>

				<tr>
					<td  style="width: 200px; background-color: white " align="center">序号</td>
					<td  align="center">类型</td>
					<td  align="center">合计</td>
					<td  align="center">国际级</td>
					<td  align="center">实验课</td>
				</tr>
				<tr>
					<td align="center">门数（们）</td>
					<td align="center">比例（%）</td>
					<td align="center">门数（们）</td>
					<td align="center">比例（%）</td>
					<td align="center">门数（们）</td>
					<td align="center">比例（%）</td>
					<td align="center">门数（们）</td>
					<td align="center">比例（%）</td>
				</tr>
		
				<tr>
					<td rowspan="13" align="center">1.按课程性质统计</td>
					<td align="center">大学外语课</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
				</tr> 
				<tr>
					<td align="center">公共任选课</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
				</tr>
				<tr>
					<td align="center">集中实践教学环节</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
				</tr>
				
				<tr>
					<td align="center">通识必修课</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
				</tr>
				<tr>
					<td align="center">通识任选课</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
				</tr>
				<tr>
					<td align="center">通识限选课</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
				</tr>
				<tr>
					<td align="center">学科基础课</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
				</tr>
				<tr>
					<td align="center">专业必修课</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
				</tr>
				<tr>
					<td align="center">专业基础课</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
				</tr>
				<tr>
					<td align="center">专业课必修</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
				</tr>
				<tr>
					<td align="center">专业任选课</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
				</tr>
				<tr>
					<td align="center">专业限选课</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
				</tr>
				<tr>
					<td align="center">专业选修课</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
				</tr>
				
					<tr>
					<td rowspan="14" align="center">2.按教学单位统计</td>
					<td align="center">水利与生态工程学院</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
				</tr> 
				<tr>
					<td align="center">土木与建筑工程学院</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
				</tr>
				<tr>
					<td align="center">机械与电气工程学院</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
				</tr>
				
				<tr>
					<td align="center">信息工程学院</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
				</tr>
				<tr>
					<td align="center">工商管理学院</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
				</tr>
				<tr>
					<td align="center">经济贸易学院</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
				</tr>
				<tr>
					<td align="center">人文与艺术学院</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
				</tr>
				<tr>
					<td align="center">理学院</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
				</tr>
				<tr>
					<td align="center">外国语学院</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
				</tr>
				<tr>
					<td align="center">国际教育学院</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
				</tr>
				<tr>
					<td align="center">继续教育学院</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
				</tr>
				<tr>
					<td align="center">马克思主义学院</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
				</tr>
				<tr>
					<td align="center">体育教学部</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
				</tr>
				<tr>
					<td align="center">军事教学部</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
					<td align="center">11</td>
				</tr>
				
			</tbody>
		</table>
		
	  
		<div id="toolbar" style="height: auto">
		<form  id="exportForm"  method="post" style="float: right;">
			<select class="easyui-combobox" id="cbYearContrast" name="selectYear" panelHeight="auto" style="width:80px; padding-top:5px; margin-top:10px;"></select>
				<a href="pages/T11/dataExport" class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a>
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
