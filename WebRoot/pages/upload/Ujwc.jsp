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

		<title>教务处</title>
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
	<body style="overflow-y: scroll"  onload="loadAllURL(31,43)">
		
		
		<hr color="blue" width="100%" />
		
		
		<table id="showInfo" class="doc-table">
			<tbody>

				<tr>
					<td rowspan=1 align="center"
						style="width: 160px;">
						校级教学管理文件目录
					</td>
					<td colspan=2
						align="left">
						1.校级教学管理文件目录	
					</td>									
      				<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(31)">上传文件</a>
						</div>						
					</td>		      							
					<td>
							<div id="downFile31"></div>											
					</td>
				</tr>
				<tr>
					<td rowspan=2 align="center"
						style="width: 160px;">
						教学实施
					</td>
					<td colspan=2
						align="left">
						2.教学任务书	
					</td>									
      				<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(32)">上传文件</a>
						</div>						
					</td>		      							
					<td>
							<div id="downFile32"></div>				
					</td>
				</tr>
				<tr>
					<td colspan=2
						align="left">
						3.教学安排表
					</td>
					<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(33)">上传文件</a>
						</div>						
					</td>
					<td>
							<div id="downFile33"></div>				
					</td>
				</tr>
				<tr>
					<td rowspan=2 align="center">
						教学管理人员培训
					</td>
					<td colspan=2
						align="left">
						4.培训计划 	
					</td>									
      				<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(34)">上传文件</a>
						</div>						
					</td>		      							
					<td>
							<div id="downFile34"></div>					
					</td>
				</tr>
				<tr>
					<td colspan=2
						align="left">
						5.培训实施情况 
					</td>
					<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(35)">上传文件</a>
						</div>						
					</td>
					<td>
							<div id="downFile35"></div>					
					</td>
				</tr>
				<tr>
					<td rowspan=2 align="center">
						编写教材概况
					</td>
					<td colspan=2
						align="left">
						6.近三年学校主编出版教材一览表  	
					</td>									
      				<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(36)">上传文件</a>
						</div>						
					</td>		      							
					<td>
							<div id="downFile36"></div>					
					</td>
				</tr>
				<tr>
					<td colspan=2
						align="left">
						7.近三年学校参编出版教材一览表 
					</td>
					<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(37)">上传文件</a>
						</div>						
					</td>
					<td>
							<div id="downFile37"></div>					
					</td>
				</tr>
				<tr>
					<td rowspan=3 align="center">
						教学改革概况
					</td>
					<td colspan=2
						align="left">
						8.教师发表教学研究论文、论著一览表	
					</td>									
      				<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(38)">上传文件</a>
						</div>						
					</td>		      							
					<td>
							<div id="downFile38"></div>				
					</td>
				</tr>
				<tr>
					<td colspan=2
						align="left">
						9.其他教学改革成果一览表
					</td>
					<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(39)">上传文件</a>
						</div>						
					</td>
					<td>
							<div id="downFile39"></div>						
					</td>
				</tr>
				<tr>
					<td colspan=2
						align="left">
						10.教学改革成果的推广应用情况 
					</td>
					<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(40)">上传文件</a>
						</div>						
					</td>
					<td>
							<div id="downFile40"></div>				
					</td>
				</tr>
								
				<tr>
					<td rowspan=3 align="center">
						本科生等级考试
					</td>
					<td colspan=2
						align="left">
						11.本科生英语四级、六级考试通过情况一览表	
					</td>									
      				<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(41)">上传文件</a>
						</div>						
					</td>		      							
					<td>
							<div id="downFile41"></div>				
					</td>
				</tr>
				<tr>
					<td colspan=2
						align="left">
						12.本科生江西省高校计算机等级考试通过情况一览表
					</td>
					<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(42)">上传文件</a>
						</div>						
					</td>
					<td>
							<div id="downFile42"></div>					
					</td>
				</tr>
				<tr>
					<td colspan=2
						align="left">
						13.本科生全国高校计算机等级考试通过情况一览表
					</td>
					<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(43)">上传文件</a>
						</div>						
					</td>
					<td>
							<div id="downFile43"></div>						
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
