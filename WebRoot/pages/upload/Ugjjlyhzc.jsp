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

		<title>国际交流与合作处</title>
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
		<script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
		<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	</head>
	
	<SCRIPT type="text/javascript">
	
			
			function upload(param){
			
				$('#udlg').dialog('open').dialog('setTitle','文件上传');
				$('#batchForm').form('reset');				
			}
			
			 function batchImport(){
			 
				  var fileName = $('#upload').val() ; 	
				  if(fileName == null || fileName == ""){
					  $.messager.alert('文件导入', '请选择将要上传的文件!');      
				   		return false ;
				  }	
				  
			  	 $('#batchForm').form('submit',{
			  		 url: 'fileupload',
			  		 type: "post",
				     dataType: "json",
			  		 onSubmit: function(){
			  			 return true;
			  		 },
			  		 success: function(result){
				  		 	var result = eval('('+result+')');
				  		 	if (result.state){
				  		 		$('#udlg').dialog('close'); // close the dialog	
								alert(result.data);
								html = "<a href='downloadFile?fileName='"+$('#upload').val() +"'>" + $('#upload').val() + "</a><a href='deleteFile?fileName='"+$('#upload').val() +"'>x</a>"					
								$('#downFile'+'1').html(html);												 
				  		 	} else {
								alert(result.data);
				  		 	}
				  	 }
			  	});
			  }
	
	</SCRIPT>
	
	<body style="overflow-y: scroll">
				
		<hr color="blue" width="100%" />
			
		<table id="showInfo" class="doc-table">
			<tbody>											
				<tr>
					<td rowspan=3 style="width: 160px; background-color: white" align="center">
						本科生参加国际交流情况
					</td>
					<td colspan=2 style="width: 200px; background-color: white"
						align="left">
						1.教师交流情况一览表
					</td>
					<td style="background-color: white">
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload('1')">上传文件</a>
						</div>						
					</td>		      							
					<td style="background-color: white">
						<div id="downFile1"></div>						
					</td>
				<tr>
					<td colspan=2 style="width: 200px; background-color: white"
						align="left">
						2.本科生交流情况一览表
					</td>
					<td style="background-color: white">
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload('2')">上传文件</a>
						</div>						
					</td>		      							
					<td style="background-color: white">
					    <div id="downFile2"></div>						
					</td>
				</tr>
				
				<tr>
					<td colspan=2 style="width: 200px; background-color: white"
						align="left">
						3.本科生参加国际交流情况一览表
					</td>
					<td style="background-color: white">
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload('3')">上传文件</a>
						</div>						
					</td>		      							
					<td style="background-color: white">
							<div id="downFile3"></div>
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
