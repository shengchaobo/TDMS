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
li {list-style-type: none;height: 24px;}
ul,li {margin:0;padding:0;}
 </style>
  </head>
  
  <body >
    	<div>
	    	<div style="height:auto;width:300px;float:left">
	    		<table >
	    		<tr>
	    		<td><h1>重要通知</h1>			</td>
	    		</tr>
	    		<tr>
	    		
	    			<td width="90px;">
	    				<div  id="showList1"  style="border:2px solid #EFFBFB; background-color:#EFFBFB;width:300px;">
							<ul id="show1">	
							</ul>
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
				</table>
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
        obj.innerHTML="<a title='下载' href='downloadFileofMain?FileNum="+num+"&fileName="+txt+"&type_1="+i+"'>"+txt+"</a>";           
        ul.appendChild(obj); 
 }
   
   
   
   
  })

  </script>
</html>
