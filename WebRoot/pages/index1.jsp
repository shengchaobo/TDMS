<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
    <link rel="stylesheet" type="text/css" href="jquery-easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui/demo/demo.css">
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <script type="text/javascript" src="jquery-easyui/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
     <style type="text/css">  
	    .blue{background: #bcd4ec;}  
	</style> 
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
 <!-- 
a { text-decoration: none} 
 -->
a:link {color: #000000}
a:hover {color: #FF0000}
a:active {color: #0000FF}
 </style>
  </head>
  
  <body >
    	<div>
	    	<div style="height:auto;width:300px;float:left">
	    		<table >
	    		<tr>
	    		<td><h1>重要通知1</h1>			</td>
	    		</tr>
	    		<tr>
	    		
	    			<td width="90px;">
	    				<div  id="showList1"  style="border:2px solid #EFFBFB; background-color:#EFFBFB;width:300px;">
							<ul id="show1">	
							</ul>
						</div>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(1)">上传文件</a>
						</div>						
					</td>	
	    		</tr>
				</table>
			</div>
			  <div style="height:auto;width:30px;float:left">  <tr>	<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>	</tr></div>		
	    	<div style="height:auto;width:300px;float:left">
	    		<table >
	    		<tr>
	    		<td><h1>常见问题</h1>			</td>
	    		</tr>
	    		<tr>
	    		
	    			<td width="90px;">
	    				<div  id="showList2"  style="border:2px solid #EFFBFB; background-color:#EFFBFB;width:300px;">
							<ul id="show2">	
							</ul>
						</div>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(2)">上传文件</a>
						</div>						
					</td>	
	    		</tr>
				</table>
			</div>	

		
		 <div style="height:auto;width:30px;float:left">  <tr>	<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>	</tr></div>		    
	    	<div style="height:auto;width:300px;float:left">
	    		<table >
	    		<tr>
	    		<td><h1>填报资料下载</h1>			</td>
	    		</tr>
	    		<tr>
	    		
	    			<td width="90px;">
	    				<div  id="showList3"  style="border:2px solid #EFFBFB; background-color:#EFFBFB;width:300px;">
							<ul id="show3" >	
							</ul>
						</div>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>
					    <div>
					    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upload(3)">上传文件</a>
						</div>						
					</td>	
	    		</tr>
				</table>
			</div>	
		</div>
		
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
  <script type="text/javascript">
  

  $(function(){

  $.ajax({  
    type: 'post',  
    url:"findFiles?type=1",  
	dataType :"json",  
    success: function(result){ 
    var length = getJsonLength(result);
    for(var i = 0;i<length;i++){
    if(result[i]!="none"){
    add(result[i],i,1);
    }
    }
    }
});


  $.ajax({  
    type: 'post',  
    url:"findFiles?type=2",  
	dataType :"json",  
    success: function(result){ 
    var length = getJsonLength(result);
    for(var i = 0;i<length;i++){
    if(result[i]!="none"){
    add(result[i],i,2);
    }
    }
    }
});


  $.ajax({  
    type: 'post',  
    url:"findFiles?type=3",  
	dataType :"json",  
    success: function(result){ 
    var length = getJsonLength(result);
    for(var i = 0;i<length;i++){
    if(result[i]!="none"){
    add(result[i],i,3);
    }
    }
    }
});


function getJsonLength(jsonData){

var jsonLength = 0;

for(var item in jsonData){

jsonLength++;

}

return jsonLength;

}

  
  function add(txt,num,i)
 {

        var ul=document.getElementById("show"+i);             
        var obj=document.createElement("li");         
        obj.innerHTML="<a href='downloadFileofMain?FileNum="+num+"&fileName="+txt+"&type_1="+i+"'>"+txt+"</a>&nbsp;&nbsp;&nbsp;&nbsp;<a title='删除' herf='javascript:void(0)' onclick='deleteFile("+i+","+num+")'><img src='images/delete.jpg' border='0'/></a>";           
        ul.appendChild(obj); 
 }
   
   
   
   
  })
  
  			var url;
			function upload(type_1){
			
				$('#udlg').dialog('open').dialog('setTitle','文件上传');
				$('#batchForm').form('reset');	
				url = "fileupload?type_1=" + type_1;			
			}
			
			function batchImport(){
			 
				  var fileName = $('#upload').val() ; 	
				  if(fileName == null || fileName == ""){
					  $.messager.alert('文件导入', '请选择将要上传的文件!');      
				   		return false ;
				  }	
				  
			  	 $('#batchForm').form('submit',{
			  		 url: url,
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
								window.location.reload();											 
				  		 	} else {
								alert(result.data);
				  		 	}
				  	 }
			  	});
			  }
  
  			  function deleteFile(type,fileNum){	
					$.messager.confirm('文件删除', '您确定删除?', function(sure) {
						if (sure) {
			    		   	$.ajax({
						   		type : "post",
						   		url : "deleteFile?FileNum=" +fileNum+"&type_1="+type,
						   		async : true,
						   		dataType :"json",
						   		success : function(result) {
							  		 	if (result.state){				  		 	
											alert(result.data);								
											window.location.reload();											 
							  		 	} else {
											alert(result.data);
							  		 	}		
								}
						   	});
						}
				   	});				  
			  }

  </script>
</html>
