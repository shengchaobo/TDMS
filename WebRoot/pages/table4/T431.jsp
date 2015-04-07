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

<title>T431</title>
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
	<script type="text/javascript">
	function reloadgrid ()  { 
        //查询参数直接添加在queryParams中 
         var  queryValue = $('#searchID').val();
         var queryParams = $('#showData').datagrid('options').queryParams;  
         queryParams.queryword = queryValue;  
         $("#showData").datagrid('reload'); 
    }		
	</script>
</head>

<body>
	<table id="showData"  title="实验技术人员信息" class="easyui-datagrid"  url ="pages/T411/loadT431"  style="height: auto;" >
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th  data-options="field:'name'" >姓名</th>
				<th  data-options="field:'teaId'" >教工号</th>
		     </tr>
		</thead>
		<thead>
				<tr>	
					<th data-options="field:'fromDept'">
					  所属部门
					</th>
					<th data-options="field:'unitId'">
						所属部门单位号
					</th>
					<th data-options="field:'staffType'">
						人员类型
					</th>
				</tr>
			</thead>
		</table>
	<div id="toolbar" style="height:auto">
	  <div style="float: left;">
			<a href='pages/T411/dataExport1?excelName=<%=URLEncoder.encode("表4-3-1实验技术人员（设备处）","UTF-8")%>&param=1'  class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 
		</div>
		<form method="post"  id="searchForm"   style="float: right;height: 24px;"  >
				<table id="test" width="280">
					<tr>
						<td>
							教工号:
						</td>
						<td>
							<input id="searchID"  name=" searchID"  class="easyui-box" style="height:24px" />
						</td>
						<td>
							<a href="javascript:void(0)" class="easyui-linkbutton"
								iconCls="icon-search" plain="true" onclick=	reloadgrid();>查询</a>
						</td>
					</tr>
				</table>
		</form>
	</div>
</body>
</html>
