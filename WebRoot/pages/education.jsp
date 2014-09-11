<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'education.jsp' starting page</title>
    
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
  
  <body>
  	<div  style="position: relative;top: 30px;left: 50px;">
       	<a  href="MultidownloadFile"   title="点击下载">教育部表一键导出</a>
     </div>
  </body>
</html>
