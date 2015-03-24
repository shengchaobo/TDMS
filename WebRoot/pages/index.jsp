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
a:link {color: #FF0000}
a:hover {color: #FF0000}
a:active {color: #0000FF}
 </style>
  </head>
  
  <body>
    	<div>
	    	<div >
	    		<table >
	    		<tr>
	    		<td><a href="javascript:void(0);"  id="name1" onclick="return false"  >重要通知</a>			</td>
	    		</tr>
	    		<tr>
	    		
	    			<td width="90px;">
	    				<div  id="showList"  style="border:1px solid grey; background-color: white;width:300px;">
							<ul id="show1">	
							</ul>
						</div>
	    			</td>
	    		</tr>
				</table>
			</div>	

		</div>
  </body>
  <script type="text/javascript">
  
  window.onload = function doAjax(){}
  
  
  $.ajax({  
    type: 'post',  
    url:"findFiles_1",  
	dataType :"json",  
    success: function(result){ 
    
    for(var i = 0;i<2;i++){
    if(result[i]!="none"){
    add(result[i],i);
    }
    }
    }
});


  
  function add(txt,num)
 {

        var ul=document.getElementById("show1");             
        var obj=document.createElement("li");         
        obj.innerHTML="<a href='downloadFileofMain?FileNum="+num+"'&FileName="+txt+">"+txt+"</a>";           
        ul.appendChild(obj); 
 }
  </script>
</html>
