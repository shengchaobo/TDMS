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

		<title>招生就业处</title>
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
	</head>
	<body style="overflow-y: scroll">
		
		
		<hr color="blue" width="100%" />
		
		
		<table id="showInfo" class="doc-table">
			<tbody>

											
				<tr>
					<td rowspan=3 style="width: 160px; background-color: white" align="center">
						学校就业工作情况
					</td>
					<td colspan=2 style="width: 200px; background-color: white"
						align="left">
						1.学校促进就业工作的制度与措施
					</td>
					<td style="background-color: white">
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload()">上传文件</a>
						</div>						
					</td>		      							
					<td style="background-color: white">
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-download" plain="true" onclick="downloads()">文件下载</a>
					    
						</div>						
					</td>
				<tr>
					<td colspan=2 style="width: 200px; background-color: white"
						align="left">
						2.年度就业计划与工作总结
					</td>
					<td style="background-color: white">
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload()">上传文件</a>
						</div>						
					</td>		      							
					<td style="background-color: white">
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-download" plain="true" onclick="downloads()">文件下载</a>
					    
						</div>						
					</td>
				</tr>
				<tr>
					<td colspan=2 style="width: 200px; background-color: white"
						align="left">
						3.职业生涯规划及创业教育指导课程开设情况
					</td>
					<td style="background-color: white">
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload()">上传文件</a>
						</div>						
					</td>		      							
					<td style="background-color: white">
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-download" plain="true" onclick="downloads()">文件下载</a>
					    
						</div>						
					</td>
				</tr>
					
				
			</tbody>
		</table>
		
		
		<div id="ddlg" class="easyui-dialog" style="width:500px;height:180px;padding:10px 20px;" closed="true" data-options="modal:true" buttons="#dlg-buttons">
		<div class="ftitle">请选择文件</div>
		<div class="fitem">

			<% 
    //取得服务器"/download/file"目录的物理路径 
    String fpath = request.getRealPath("/WEB-INF/uploadList");
    //取得"/download/file"目录的file对象 
    File file = new File(fpath); 
    //取得file目录下所有文件 
    File[] files = file.listFiles(); 
    if(files == null){
    out.print("还没有上传文件，请先上传文件！");
    }else{
  
   for (int i = 0; i < files.length; i++) { 
  
    String fname = files[i].getName();
  
    //对文件名进行url编码(UTF-8指明fname原来的编码，UTF-8一般由本地编码GBK代替) 
     fname = java.net.URLEncoder.encode(fname, "UTF-8"); 
  
    out.println("<a href=download2.action?name=" + fname + "&len=" + files[i].length() + ">" 
     + files[i].getName() + "</a><br>"); 
    }
    }
   %>
		</div>
	</div>
	
	<div id="udlg" class="easyui-dialog" style="width:500px;height:180px;padding:10px 20px;" closed="true" data-options="modal:true" buttons="#dlg-buttons">
		<div class="ftitle">文件上传</div>
		<div class="fitem">

			<s:form action="fileupload.action" id="batchForm" method="post" enctype="multipart/form-data">
				<label>请选择文件：</label> 
				<input class="easyui-validatebox" type="file" name="upload" />
				<input type="submit" value=" 提交 " />
			</s:form>
		</div>
	</div>
		

		
		 
	</body>

	<script type="text/javascript">
	
function upload(){
	    	
	$('#udlg').dialog('open').dialog('setTitle','文件上传');
		   
}


function downloads(){
	    	
	 
	$('#ddlg').dialog('open').dialog('setTitle','文件下载');
		   
}

</script>
</html>
