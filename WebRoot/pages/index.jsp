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

  </head>
  
  <body>
    	<div>
		    <div id="system" class="easyui-panel" title="系统公告" style="width:'auto';height:'auto';padding:10px;">
		    	<ul>
						<li>关于该系统的使用方法介绍</li>
						<li>校领导</li>
						<li>关于2014年数据采集截止时间</li>
				</ul>
			</div>
					
			<div id="dataHelp" class="easyui-panel" title="校园新闻" style="width:'auto';height:'auto';padding:10px;">
						<p style="font-size:14px">jQuery EasyUI framework helps you build your web pages easily.</p>
						<ul>
							<li>easyui is a collection of user-interface plugin based on jQuery.</li>
							<li>easyui provides essential functionality for building modem, interactive, javascript applications.</li>
							<li>complete framework for HTML5 web page.</li>
							<li>easyui save your time and scales while developing your products.</li>
							<li>easyui is very easy but powerful.</li>
						</ul>
			</div>
		</div>
  </body>
</html>
