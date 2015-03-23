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

		<title>教学单位</title>
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
	<body style="overflow-y: scroll"  onload="loadAllURL(51,61)">
				
		<hr color="blue" width="100%" />
				
		<table id="showInfo" class="doc-table">
			<tbody>
				
				<tr>
					<td rowspan=3 align="center">
						培养方案
					</td>
					<td colspan=2
						align="left">
						1.人才培养方案	
					</td>									
      				<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(51)">上传文件</a>
						</div>						
					</td>		      							
					<td>
							<div id="downFile51"></div>				
					</td>
				</tr>
				<tr>
					<td colspan=2
						align="left">
						2.专业培养目标
					</td>
					<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(52)">上传文件</a>
						</div>						
					</td>
					<td>
							<div id="downFile52"></div>							
					</td>
				</tr>
				<tr>
					<td colspan=2
						align="left">
						3.专业特色
					</td>
					<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(53)">上传文件</a>
						</div>						
					</td>
					<td>
							<div id="downFile53"></div>							
					</td>
				</tr>
				<tr>
					<td rowspan=4 align="center">
						建设规划
					</td>
					<td colspan=2
						align="left">
						4.专业建设规划	
					</td>									
      				<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(54)">上传文件</a>
						</div>						
					</td>		      							
					<td>
							<div id="downFile54"></div>							
					</td>
				</tr>
				<tr>
					<td colspan=2
						align="left">
						5.师资队伍建设规划
					</td>
					<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(55)">上传文件</a>
						</div>						
					</td>
					<td>
							<div id="downFile55"></div>							
					</td>
				</tr>
				<tr>
					<td colspan=2
						align="left">
						6.课程建设规划 
					</td>
					<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(56)">上传文件</a>
						</div>						
					</td>
					<td>
							<div id="downFile56"></div>							
					</td>
				</tr>
				<tr>
					<td colspan=2
						align="left">
						7.教材建设规划 
					</td>
					<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(57)">上传文件</a>
						</div>						
					</td>
					<td>
							<div id="downFile57"></div>								
					</td>
				</tr>
				<tr>
					<td rowspan=1 align="center">
						教学管理
					</td>
					<td colspan=2
						align="left">
						8.教学单位教学管理文件目录 	
					</td>									
      				<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(58)">上传文件</a>
						</div>						
					</td>		      							
					<td>
							<div id="downFile58"></div>							
					</td>
				</tr>
				
				<tr>
					<td rowspan=2 align="center">
						所获荣誉
					</td>
					<td colspan=2
						align="left">
						9.教学单位所获荣誉证书照片/扫描图像	
					</td>									
      				<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(59)">上传文件</a>
						</div>						
					</td>		      							
					<td>
							<div id="downFile59"></div>							
					</td>
				</tr>
				<tr>
					<td colspan=2
						align="left">
						10.教师个人所获荣誉证书照片/扫描图像
					</td>
					<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(60)">上传文件</a>
						</div>						
					</td>
					<td>
							<div id="downFile60"></div>						
					</td>
				</tr>
				<tr>
				   <td  align="center">
						社会服务
					</td>
					<td  colspan=2
						align="left">
						11.教学单位社会服务情况一览表
					</td>
					<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(61)">上传文件</a>
						</div>						
					</td>
					<td>
							<div id="downFile61"></div>						
					</td>
				</tr>				
			</tbody>
		</table>
			
	<div id="udlg" class="easyui-dialog" style="width:500px;height:180px;padding:10px 20px;" closed="true" data-options="modal:true">
		<div class="fitem" style="position: relative;top:25px;">
			<Form id="batchForm" enctype="multipart/form-data" method="post">
				<label　style="width: 100px">请选择文件：</label> 
				<input  type="file"  id="upload" name="uploadFile" size=25 style="height: 24px;"/>
				<input type="button" value=" 提交 "  onclick="batchImport()" style="height: 24px;"s/>
			</Form>
		</div>
	</div>	
	</body>
</html>
