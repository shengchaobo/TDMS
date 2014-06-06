<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>系统主页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="pages/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="pages/js/left_nav.js"></script>
	
	<link rel="stylesheet" type="text/css" href="pages/css/master.css">
	
	<style type="text/css">
	   .td_current{background-color: #00BFFF}
	   .td1{background-color: #F0F8FF}
	</style>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
		<div class="header">
			<!--header start-->
			<div class="header_UserInfo">
				<div style="margin-left: 1096px; margin-top: 10px;">
					<span style="font-size: 14px;">Welcome</span>&nbsp;&nbsp;
					<span style="font-size: 14px; color: #cc3366; font-weight: bold">Admin</span>&nbsp;&nbsp;
					<span style="font-size: 14px;"><a href="./login.jsp">退出</a>
					</span>
				</div>
			</div><!--
			<div class="top_menu">
			<table width="100%"  id="table1">
				<tbody>
					<tr>
						<td title="首页'"  width="20%"   class="td_current">
						     <div class="t_menu_current"  onclick="showContent('./test.jsp');">
									<b></b>
									<strong id="tmn_menu_1" style="width: 164px;">首页</strong>
									<a href="javascript:void(0);" onclick="" ></a>
								</div>
						</td>
						<td width="20%"></td>
						<td width="20%"></td>
						<td width="20%"></td>
						<td width="20%"></td>
					</tr>
				</tbody>
			</table>
			</div>	
		   --></div>
		<!--header end-->

		<div class="wrapper" style="width: 1260px; height: auto">
			<div class="left_nav">
				<!--Left Nav start-->
				<div class="menu_fold">
					<span style="display: block; padding-top: 5px; line-height: 15px">导航</span>
					<span class="menu_fold_icon"></span>
				</div>
				<br />
				<div class="root_menu_module">
					<!--root_menu_module start-->
					<div class="root_menu_top"></div>
					<div class="root_menu_middle">
						<div class="root_menu_title">
							<a href="#">数据查询</a>
						</div>
						<ul class="sub_menu">
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('./test.jsp');">数据查询1</a>
							</li>
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('login.action');">数据查询2</a>
							</li>
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">数据查询3</a>
							</li>
						</ul>
					</div>
					<div class="root_menu_bottom"></div>
				</div>
				<!--root_menu_module end-->

				<div class="root_menu_module">
					<!--root_menu_module start-->
					<div class="root_menu_top"></div>
					<div class="root_menu_middle">
						<div class="root_menu_title">
							<a href="#">数据导入</a>
						</div>
						<ul class="sub_menu">
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">DDI数据导入</a>
							</li>
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">电子版数据导入</a>
							</li>
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">批量调整导入</a>
							</li>
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">机构导入</a>
							</li>
						</ul>
					</div>
					<div class="root_menu_bottom"></div>
				</div>
				<!--root_menu_module end-->

				<div class="root_menu_module">
					<!--root_menu_module start-->
					<div class="root_menu_top"></div>
					<div class="root_menu_middle">
						<div class="root_menu_title">
							<a href="#">数据导出</a>
						</div>
						<ul class="sub_menu">
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">DDI数据导出</a>
							</li>
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">电子版数据导出</a>
							</li>
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">批量调整导出</a>
							</li>
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">机构导出</a>
							</li>
						</ul>
					</div>
					<div class="root_menu_bottom"></div>
				</div>
				<!--root_menu_module end-->
				
				<div class="root_menu_module">
					<!--root_menu_module start-->
					<div class="root_menu_top"></div>
					<div class="root_menu_middle">
						<div class="root_menu_title">
							<a href="#">一 学校基本信息</a>
						</div>
						<ul class="sub_menu">
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('pages/1_SchBasicInfo/T12_SchAdminUnit_PartyOffice.jsp');">学校行政单位</a>
							</li>
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">电子版数据导出</a>
							</li>
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">批量调整导出</a>
							</li>
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">机构导出</a>
							</li>
						</ul>
					</div>
					<div class="root_menu_bottom"></div>
				</div>
				<!--root_menu_module end-->
				
				<div class="root_menu_module">
					<!--root_menu_module start-->
					<div class="root_menu_top"></div>
					<div class="root_menu_middle">
						<div class="root_menu_title">
							<a href="#">二 学校基本条件</a>
						</div>
						<ul class="sub_menu">
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">DDI数据导出</a>
							</li>
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">电子版数据导出</a>
							</li>
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">批量调整导出</a>
							</li>
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">机构导出</a>
							</li>
						</ul>
					</div>
					<div class="root_menu_bottom"></div>
				</div>
				<!--root_menu_module end-->
				
				<div class="root_menu_module">
					<!--root_menu_module start-->
					<div class="root_menu_top"></div>
					<div class="root_menu_middle">
						<div class="root_menu_title">
							<a href="#">三 学科专业</a>
						</div>
						<ul class="sub_menu">
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">DDI数据导出</a>
							</li>
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">电子版数据导出</a>
							</li>
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">批量调整导出</a>
							</li>
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">机构导出</a>
							</li>
						</ul>
					</div>
					<div class="root_menu_bottom"></div>
				</div>
				<!--root_menu_module end-->
				
				<div class="root_menu_module">
					<!--root_menu_module start-->
					<div class="root_menu_top"></div>
					<div class="root_menu_middle">
						<div class="root_menu_title">
							<a href="#">四 教师信息</a>
						</div>
						<ul class="sub_menu">
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">DDI数据导出</a>
							</li>
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">电子版数据导出</a>
							</li>
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">批量调整导出</a>
							</li>
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">机构导出</a>
							</li>
						</ul>
					</div>
					<div class="root_menu_bottom"></div>
				</div>
				<!--root_menu_module end-->
				
				<div class="root_menu_module">
					<!--root_menu_module start-->
					<div class="root_menu_top"></div>
					<div class="root_menu_middle">
						<div class="root_menu_title">
							<a href="#">五 人才培养</a>
						</div>
						<ul class="sub_menu">
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">DDI数据导出</a>
							</li>
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">电子版数据导出</a>
							</li>
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">批量调整导出</a>
							</li>
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">机构导出</a>
							</li>
						</ul>
					</div>
					<div class="root_menu_bottom"></div>
				</div>
				<!--root_menu_module end-->
				
				<div class="root_menu_module">
					<!--root_menu_module start-->
					<div class="root_menu_top"></div>
					<div class="root_menu_middle">
						<div class="root_menu_title">
							<a href="#">六 学生信息</a>
						</div>
						<ul class="sub_menu">
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">DDI数据导出</a>
							</li>
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">电子版数据导出</a>
							</li>
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">批量调整导出</a>
							</li>
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">机构导出</a>
							</li>
						</ul>
					</div>
					<div class="root_menu_bottom"></div>
				</div>
				<!--root_menu_module end-->
				
				<div class="root_menu_module">
					<!--root_menu_module start-->
					<div class="root_menu_top"></div>
					<div class="root_menu_middle">
						<div class="root_menu_title">
							<a href="#">七 教学管理与质量监控</a>
						</div>
						<ul class="sub_menu">
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">DDI数据导出</a>
							</li>
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">电子版数据导出</a>
							</li>
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">批量调整导出</a>
							</li>
							<li class="sub_menu_li">
								<a href="javascript:void(0);" onmousedown="showContent('url');">机构导出</a>
							</li>
						</ul>
					</div>
					<div class="root_menu_bottom"></div>
				</div>
				<!--root_menu_module end-->
			</div>
			<!--Left Nav end-->

			<div class="page_content" style="float: left; width: 1040px; min-height: 800px; background-color: #eee;">
			        <iframe src="pages/test.jsp"  id="right_content" scrolling="auto"></iframe>
			</div>
			<div class="clear"></div>
		</div>
	</body>
</html>
