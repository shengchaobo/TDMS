<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<%@ page import="java.io.File" %>
<%@ taglib uri="/struts-tags" prefix="s"%> 
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

		<title>科学研究处</title>
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
		<script type="text/javascript" src="js/upload/upload.js"></script>
	</head>
	<body style="overflow-y: scroll" onload="loadAllURL(91,95)">
				
		<hr color="blue" width="100%" />
				
		<table id="showInfo" class="doc-table" >
			<tbody>											
				<tr>
					<td rowspan=5 style="width: 160px; background-color: white" align="center">
						教师科研情况
					</td>
					<td colspan=2 style="width: 200px; background-color: white"
						align="left">
						1.教师科研项目一览表
					</td>
					<td style="background-color: white">
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(91)">上传文件</a>
						</div>						
					</td>		      							
					<td style="background-color: white">
						<div id="downFile91"></div>						
					</td>
				</tr>
				<tr>
					<td colspan=2 style="width: 200px; background-color: white"
						align="left">
						2.近一届科研成果奖项一览表
					</td>
					<td style="background-color: white">
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(92)">上传文件</a>
						</div>						
					</td>		      							
					<td style="background-color: white">
						<div id="downFile92"></div>						
					</td>
				</tr>
				<tr>
					<td colspan=2 style="width: 200px; background-color: white"
						align="left">
						3.教师发表论文情况一览表
					</td>
					<td style="background-color: white">
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(93)">上传文件</a>
						</div>						
					</td>		      							
					<td style="background-color: white">
						<div id="downFile93"></div>							
					</td>
				</tr>
				<tr>
					<td colspan=2 style="width: 200px; background-color: white"
						align="left">
						4.教师出版专、译著情况一览表
					</td>
					<td style="background-color: white">
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(94)">上传文件</a>
						</div>						
					</td>		      							
					<td style="background-color: white">
						<div id="downFile94"></div>						
					</td>
				</tr>
				<tr>
					<td colspan=2 style="width: 200px; background-color: white"
						align="left">
						5.教师获准专利情况一览表
					</td>
					<td style="background-color: white">
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(95)">上传文件</a>
						</div>						
					</td>		      							
					<td style="background-color: white">
						<div id="downFile95"></div>						
					</td>
				</tr>																							
			</tbody>
		</table>
	
	<div id="udlg" class="easyui-dialog" style="width:500px;height:180px;padding:10px 20px;" closed="true" data-options="modal:true">
		<div class="ftitle">文件上传</div>
		<div class="fitem">
			<Form id="batchForm" enctype="multipart/form-data" method="post">
				<label>请选择文件：</label> 
				<input  type="file"  id="upload" name="uploadFile" />
				<input type="button" value=" 提交 "  onclick="batchImport()"/>
			</Form>
		</div>
	</div>	
	</body>
</html>
