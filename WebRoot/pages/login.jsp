<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

		<title>江西教学基本状态数据库系统登录界面</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/login.css">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>
	<script type="text/javascript">
	function check() {
		if (document.loginForm.username.value == "") {
			alert('用户名不能为空！！');
			return false;
		}
		if (document.loginForm.password.value == "") {
			alert('密码不能为空！！');
			return false;
		}
		document.loginForm.submit();
	}
   </script>
	<body>
		<table class="table1"  align="center" cellpadding="0" cellspacing="0" >
			<tr>
				<td width="353" height="259" align="center" valign="bottom"
					background="images/login_1-1.gif">
				</td>
				<td width="195" background="images/login_2.gif">
					<form method="post" action="login" name="loginForm">
						<table width="190" height="106" border="0" align="center"
							cellpadding="2" cellspacing="0">
							<tr>
								<td height="50" colspan="2" align="left">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td width="60" height="30" align="left">
									用户名称
								</td>
								<td>
									<input name="username" type="TEXT"
										style="background: url(images/login_6.gif) repeat-x; border: solid 1px #27B3FE; height: 20px; background-color: #FFFFFF"
										id="username" size="16">
								</td>
							</tr>
							<tr>
								<td width="60" height="30" align="left">
									登陆密码
								</td>
								<td>
									<input name="password" TYPE="PASSWORD"
										style="background: url(images/login_6.gif) repeat-x; border: solid 1px #27B3FE; height: 20px; background-color: #FFFFFF"
										id="password" size="16">
								</td>
							</tr>
							<tr>
								<td height="40" colspan="2" align="center"></td>
							</tr>
							<tr>
								<td colspan="3" align="center">
										<input type="submit" name="submit"
											style="background: url(images/login_5.gif) no-repeat"
											value=" 登    陆  " onclick="check();">
										<input type="reset" name="Submit"
											style="background: url(images/login_5.gif) no-repeat"
											value=" 忘记密码  ">
									</td>
								</tr>
							<tr>
								<td height="5" colspan="2"></td>
							</tr>
						</table>
					</form>
				</td>
				<td width="133" background="images/login_3.gif">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td height="161" colspan="3" background="images/login_4.gif"></td>
			</tr>
		</table>
	</body>
</html>
