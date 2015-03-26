<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>education.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type ="text/css">     
		a {font-size:16px}   
		a:link {color: blue; text-decoration:none;} 
		a:active:{color: red; } 
		a:visited {color:purple;text-decoration:none;} 
		a:hover {color: red; text-decoration:underline;} 
	</style> 
  </head>
  <script type="text/javascript">
    //提交导出表单
    function submitForm(){
    	  document.getElementById('exportForm').submit();
    }
    
    //提交导出表单
    function submitForm1(){
    	  document.getElementById('exportForm1').submit();
    }
  
  </script>
  
  <body>
     <div id="edus" style="position: relative;top: 20px;left: 10px;ss">
		 <div id="eduction">
		    <form action='MultidownloadFile'   method="post"  id="exportForm" enctype="multipart/form-data">
			    <select class="easyui-combobox"  id="cbYearContrast1" name="selectYear"  editable=false ></select>&nbsp;&nbsp;
				<a href='javascript:submitForm()'   style="font:18px;" >
						教育部表一键导出
				</a> 	
			</form>	
		 </div>
		 <div id="allFile">
			<form action='AllDownFile'   method="post"  id="exportForm1" enctype="multipart/form-data">
			    <select class="easyui-combobox"  id="cbYearContrast" name="selectYear"  editable=false ></select>&nbsp;&nbsp;
				<a href='javascript:submitForm1()'   style="font:18px;" >
						全校所有表一键导出
				</a> 	
			</form>	
		 </div>
	</div>
  </body>


<script type="text/javascript">
    	var currentYear = new Date().getFullYear();
    	var select = document.getElementById("cbYearContrast");
    	for (var i = 0; i <= 10; i++) {
        var theOption = document.createElement("option");
        	theOption.innerHTML = currentYear-i + "年";
        	theOption.value = currentYear-i;
        	select.appendChild(theOption);
    	}
    	
      var select1 = document.getElementById("cbYearContrast1");
    	for (var i = 0; i <= 10; i++) {
        var theOption = document.createElement("option");
        	theOption.innerHTML = currentYear-i + "年";
        	theOption.value = currentYear-i;
        	select1.appendChild(theOption);
    	}
</script>
</html>
