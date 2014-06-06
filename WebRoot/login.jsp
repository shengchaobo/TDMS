<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>江西教学基本状态数据库系统</title>
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
a {
	color: #008EE3
}

a:link {
	text-decoration: none;
	color: #008EE3
}

A:visited {
	text-decoration: none;
	color: #666666
}

A:active {
	text-decoration: underline
}

A:hover {
	text-decoration: underline;
	color: #0066CC
}

A.b:link {
	text-decoration: none;
	font-size: 12px;
	font-family: "Helvetica,微软雅黑,宋体";
	color: #FFFFFF;
}

A.b:visited {
	text-decoration: none;
	font-size: 12px;
	font-family: "Helvetica,微软雅黑,宋体";
	color: #FFFFFF;
}

A.b:active {
	text-decoration: underline;
	color: #FF0000;
}

A.b:hover {
	text-decoration: underline;
	color: #ffffff
}

.table1 {
	border: 1px solid #CCCCCC;
}

.font {
	font-size: 12px;
	text-decoration: none;
	color: #999999;
	line-height: 20px;
}

.input {
	font-size: 12px;
	color: #999999;
	text-decoration: none;
	border: 0px none #999999;
}

td {
	font-size: 12px;
	color: #007AB5;
}

form {
	margin: 1px;
	padding: 1px;
}

input {
	border: 0px;
	height: 26px;
	color: #007AB5;
	.
	unnamed1
	{
	border
	:
	thin
	none
	#FFFFFF;
}

.unnamed1 {
	border: thin none #FFFFFF;
}

select {
	border: 1px solid #cccccc;
	height: 18px;
	color: #666666;
	.
	unnamed1
	{
	border
	:
	thin
	none
	#FFFFFF;
}

body {
	background-repeat: no-repeat;
	background-color: #9CDCF9;
	background-position: 0px 0px;
}

.tablelinenotop {
	border-top: 0px solid #CCCCCC;
	border-right: 1px solid #CCCCCC;
	border-bottom: 0px solid #CCCCCC;
	border-left: 1px solid #CCCCCC;
}

.tablelinenotopdown {
	border-top: 1px solid #eeeeee;
	border-right: 1px solid #eeeeee;
	border-bottom: 1px solid #eeeeee;
	border-left: 1px solid #eeeeee;
}

.style6 {
	FONT-SIZE: 9pt;
	color: #7b8ac3;
}
-->
</style>

<script type="text/javascript" src="jquery-easyui/jquery-1.7.2.min.js"></script>

<script type="text/javascript">

			function validate() {
			    if(!check()){
			    	return ;
			    }
			        $.ajax({
			            url: "login",
			            data: $("#loginForm").serialize(),
			            type: "post",
			            dataType: "text",
			            success: function (data) {
			                data = eval("(" + data + ")");
			                
			                if(!data.success){
								alert(data.msg) ;
				            }else{
								window.location = data.url ;
					        }
			            }
			        }).submit();
			    }
				
			function check() {
				
				var userid = $("#userid").val();
				var password = $("#Password").val() ;
				if (userid == null || userid == "") {
					alert("用户名不能为空！！");
					return false;
				}
				if (password == null || password == "") {
					alert('密码不能为空！！');
					return false;
				}
				return true ;
			}
		</script>
</head>
<body>
	<table width="681" border="0" align="center" cellpadding="0"
		cellspacing="0" style="margin-top: 120px">
		<!--<tr>
      <td height="115"  background="images/index_11.gif" width="353"  colspan="3">
	  </td>
  </tr>-->
		<tr>
			<td width="353" height="259" align="center" valign="bottom"
				background="images/login_1-1.gif">
				<table width="90%" border="0" cellspacing="3" cellpadding="0">
				</table></td>
			<td width="195" background="images/login_2.gif">
				<table width="190" height="106" border="0" align="center"
					cellpadding="2" cellspacing="0">
					<form id="loginForm" method="post">
						<tr>
							<td height="50" colspan="2" align="left">&nbsp;</td>
						</tr>
						<tr>
							<td width="60" height="30" align="left">用户名称</td>
							<td><input name="user.userId" type="TEXT"
								style="background: url(images/login_6.gif) repeat-x; border: solid 1px #27B3FE; height: 20px; background-color: #FFFFFF"
								id="userid" size="16"
								onkeyup="this.value=this.value.replace(/\D/g,'')"
								onafterpaste="this.value=this.value.replace(/\D/g,'')" /></td>
						</tr>
						<tr>
							<td height="30" align="left">登陆密码</td>
							<td><input name="user.password" TYPE="PASSWORD"
								style="background: url(images/login_6.gif) repeat-x; border: solid 1px #27B3FE; height: 20px; background-color: #FFFFFF"
								id="Password" size="16" /></td>
						</tr>
						<tr>
							<td height="40" colspan="2" align="center"><img
								src="images/tip.gif" width="16" height="16"> 请勿非法登陆！</td>
						</tr>
						<tr>
							<td colspan="3" align="center">
								<input type="button"
									id="loginBtn"
									style="background: url(images/login_5.gif) no-repeat"
									value="&nbsp;登&nbsp;&nbsp;陆&nbsp;" onclick="validate()"/>
								 <input type="reset"
								style="background: url(images/login_5.gif) no-repeat"
								value="重置&nbsp;" /></td>
						</tr>
						<td height="5" colspan="2"></td>
					</form>
				</table></td>
			<td width="133" background="images/login_3.gif">&nbsp;</td>
		<tr>
			<td height="161" colspan="3" background="images/login_4.gif"></td>
		</tr>
		</tr>
	</table>
</body>
</html>