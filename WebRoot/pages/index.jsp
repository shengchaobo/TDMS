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
		    <div id="system" class="easyui-panel" style="width:'auto';height:'auto';padding:10px;">
		    	<ul>
						<li><a text-decoration="none" href ="data/manual.doc" >关于该系统的使用方法介绍</a></li>
						
						<li ><a text-decoration="none" href = "pages/leader.jsp">校领导</a></li>
						<li>关于2014年数据采集截止时间</li>
				</ul>
			</div>

		</div>
  </body>
</html>
