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

		<title>教育教学评估中心</title>
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
	<body style="overflow-y: scroll"  onload="loadAllURL(71,85)">
		
		
		<hr color="blue" width="100%" />
		
		
		<table id="showInfo" class="doc-table">
			<tbody>										
				<tr>
					<td rowspan=15 align="center">
						教学质量监控体系
					</td>
					<td colspan=2
						align="left">
						1.教学质量保障体系建设基本情况 
					</td>
					<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(71)">上传文件</a>
						</div>						
					</td>		      							
					<td>
							<div id="downFile71"></div>								
					</td>
				</tr>
				<tr>
					<td colspan=2
						align="left">
						2.教学工作定期检查制度
					</td>
					<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(72)">上传文件</a>
						</div>						
					</td>		      							
					<td>
							<div id="downFile72"></div>								
					</td>
				</tr>
				<tr>
					<td colspan=2
						align="left">
						3.教学督导机构和制度
					</td>
					<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(73)">上传文件</a>
						</div>						
					</td>		      							
					<td>
							<div id="downFile73"></div>								
					</td>
				</tr>
				<tr>
					<td colspan=2
						align="left">
						4.课程教学评价体系
					</td>
					<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(74)">上传文件</a>
						</div>						
					</td>		      							
					<td>
							<div id="downFile74"></div>							
					</td>
				</tr>
				<tr>
					<td colspan=2
						align="left">
						5.实验教学评价体系
					</td>
					<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(75)">上传文件</a>
						</div>						
					</td>		      							
					<td>
							<div id="downFile75"></div>						
					</td>
				</tr>
				<tr>
					<td colspan=2
						align="left">
						6.实习教学评价体系
					</td>
					<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(76)">上传文件</a>
						</div>						
					</td>		      							
					<td>
							<div id="downFile76"></div>								
					</td>
				</tr>
				<tr>
					<td colspan=2
						align="left">
						7.毕业综合训练环节评价体系
					</td>
					<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(77)">上传文件</a>
						</div>						
					</td>		      							
					<td>
							<div id="downFile77"></div>							
					</td>
				</tr>
				<tr>
					<td colspan=2
						align="left">
						8.教学单位或专业本科教学工作评价制度
					</td>
					<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(78)">上传文件</a>
						</div>						
					</td>		      							
					<td>
							<div id="downFile78"></div>							
					</td>
				</tr>
				<tr>
					<td colspan=2
						align="left">
						9.本科教学质量年度报告
					</td>
					<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(79)">上传文件</a>
						</div>						
					</td>		      							
					<td>
							<div id="downFile79"></div>					
					</td>
				</tr>
				<tr>
					<td colspan=2
						align="left">
						10.学校教学自我评价及质量改进机制
					</td>
					<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(80)">上传文件</a>
						</div>						
					</td>		      							
					<td>
							<div id="downFile80"></div>							
					</td>
				</tr>
				<tr>
					<td colspan=2
						align="left">
						11.学生评教制度
					</td>
					<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(81)">上传文件</a>
						</div>						
					</td>		      							
					<td>
							<div id="downFile81"></div>					
					</td>
				</tr>
				<tr>
					<td colspan=2
						align="left">
						12.评教结果分析
					</td>
					<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(82)">上传文件</a>
						</div>						
					</td>		      							
					<td >
							<div id="downFile82"></div>					
					</td>
				</tr>
				<tr>
					<td colspan=2
						align="left">
						13.评教结果数据库
					</td>
					<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(83)">上传文件</a>
						</div>						
					</td>		      							
					<td>
							<div id="downFile83"></div>					
					</td>
				</tr>
				<tr>
					<td colspan=2
						align="left">
						14.教学质量评估实施情况
					</td>
					<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(84)">上传文件</a>
						</div>						
					</td>		      							
					<td>
							<div id="downFile84"></div>					
					</td>
				</tr>
				<tr>
					<td colspan=2
						align="left">
						15.教学质量管理制度目录
					</td>
					<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(85)">上传文件</a>
						</div>						
					</td>		      							
					<td>
							<div id="downFile85"></div>					
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
