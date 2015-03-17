<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>综合查询</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

	<link href="css/CN/BLUE/mainWin.css"  type=text/css media=screen rel=stylesheet>		
	<link href="css/CN/BLUE/query.css"     type=text/css    media=screen rel=stylesheet>		
	<link rel="stylesheet" type="text/css" href="jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui/demo/demo.css">

	<script type="text/javascript" src="jquery-easyui/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script> 
	<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/search.js"></script>

	
</head>

<body>
  <!--
  		查询头部
	-->
	<div id="Title_bar">
		<div id="Title_bar_Head">
		<div id="Title_Head"></div>
					<div id="Title">
						<img border="0" width="11" height="11"  src="images/title_arrow.gif" />&nbsp;&nbsp;自定义查询					
					</div>
			<div id="Title_End"></div>
		</div>
  </div>
	
	<div id="MainArea">
	  <form method="post" id="QueryForm" >
  	<div class="ItemBlock_Title1">		 
			 <table>
					<tr>
						<td>
							选择所要查询的表：
						</td>
						<td>
						      <input id="Tname" type="hidden" name="diTableMessageBean.Tname">
			    				<input id="Tid" name="diTableMessageBean.Tid"   size="60"
										 class='easyui-combobox' data-options="valueField:'tid',textField:'tname',url:'pages/DiTableMessage/loadTableMessage',listHeight:'auto',editable:true,
										 onSelect:function(){
										 	document.getElementById('Tname').value=$(this).combobox('getText') ;
										 }">
						</td>
						<td>
							<a href="javascript:void(0)" class="easyui-linkbutton"
								iconCls="icon-search" plain="true" onclick=	"Query()">执行查询</a>
						</td>
					</tr>
				</table>
     </div> 
     
     <div  id="searchDiv">
      	<table width="80%"  >
      			<tr align="center"  valign="middle"   id="TableTitle">
      					<td  width="10%">左括号</td>
				        <td  width="20%">查询条件列</td>
				        <td  width="10%">逻辑运算符</td>
				        <td  width="10%">左括号</td>
				        <td  width="20%">查询条件值</td>
				        <td  width="10%">右括号</td>
				        <td  width="10%">逻辑连接符</td>
      			</tr> 	
      			<tr class="serach_list" id="d0">
      					<td>
      							<select class='easyui-combobox' id="leftJoin" name="QueryConditionsBean.leftJoin"  style="width: 100px;">
												<option value="">无</option>
												<option value="(">(</option>
									  </select>
      					</td>
      					<td>
      						 <select  id="fieldName" name="QueryConditionsBean.FieldName" class="easyui-combobox"  style="width: 200px;"></select>  					
      					</td> 
      					<td>
									 <select id="logicRelation" name="QueryConditionsBean.LogicRelation" class='easyui-combobox'  style="width: 100px;">
													<option value="">选择</option>
													<option value="=">等于</option>
													<option value="!=">不等于</option>
													<option value="&gt;">大于</option>
													<option value="&lt;">小于</option>												
													<option value="&gt;=">大于等于</option>
													<option value="&lt;=">小于等于</option>
													<option value="like">包含</option>
													<option value="not like">不包含</option>
													<option value="is null">为空</option>
													<option value="is not null">不为空</option>
									</select>
      					</td>
      					<td>
      							<select class='easyui-combobox' id="leftJoin01" name="QueryConditionsBean.leftJoin"  style="width: 100px;">
												<option value="">无</option>
												<option value="'">'</option>
												<option value="(">(</option>
												<option value="('">('</option>
									  </select>
      					</td>      					 
      					<td>
      						 <input maxlength="100"  id="paramValue" name="QueryConditionsBean.ParamValue"  type="text" onfocus="formatM(this);"  style="width: 200px;"/>
      					</td>
      					<td>
										<select id="rightJoin" name="QueryConditionsBean.RightJoin" class='easyui-combobox'  style="width: 100px;">
											    <option value="">无</option>
											    <option value="'">'</option>
											    <option value=")">)</option>
											    <option value="')">')</option>
											    <option value="))">))</option>
											    <option value="'))">'))</option>
										</select>
      					</td> 
      					<td>
										 <select id="joinRelation" name="QueryConditionsBean.JoinRelation" class='easyui-combobox'  style="width: 100px;">
													<option value="">选择</option>
													<option value="and">并且</option>
													<option value="or">或者</option>
										</select>     					
      					</td>     					      					     					   			
      			</tr>
      			<tr  class="serach_list" id="d1">
      					<td>
      							<select class='easyui-combobox' id="leftJoin1" name="QueryConditionsBean.leftJoin1"  style="width: 100px;">
													<option value="">无</option>
													<option value="(">(</option>
									  </select>
      					</td>
      					<td>
      						 <select  id="fieldName1" name="QueryConditionsBean.FieldName1" class="easyui-combobox"  style="width: 200px;"></select>  					
      					</td> 
      					<td>
									 <select id="logicRelation1" name="QueryConditionsBean.LogicRelation1" class='easyui-combobox'  style="width: 100px;">
													<option value="">选择</option>
													<option value="=">等于</option>
													<option value="!=">不等于</option>
													<option value="&gt;">大于</option>
													<option value="&lt;">小于</option>												
													<option value="&gt;=">大于等于</option>
													<option value="&lt;=">小于等于</option>
													<option value="like">包含</option>
													<option value="not like">不包含</option>
													<option value="is null">为空</option>
													<option value="is not null">不为空</option>
									</select>
      					</td>
      					<td>
      							<select class='easyui-combobox' id="leftJoin11" name="QueryConditionsBean.leftJoin"  style="width: 100px;">
												<option value="">无</option>
												<option value="'">'</option>
												<option value="(">(</option>
												<option value="('">('</option>
									  </select>
      					</td>      					 
      					<td>
      						 <input maxlength="100"  id="paramValue1" name="QueryConditionsBean.ParamValue1"  type="text" onfocus="formatM(this);"   style="width: 200px;"/>
      					</td>
      					<td>
										<select id="rightJoin1" name="QueryConditionsBean.RightJoin1" class='easyui-combobox'  style="width: 100px;">
											    <option value="">无</option>
											    <option value="'">'</option>
											    <option value=")">)</option>
											    <option value="')">')</option>
											    <option value="))">))</option>
											    <option value="'))">'))</option>
										</select>
      					</td>       			
      			</tr>
      	</table>
      </div>
   </form>
  </div>
  <div  id="divDatagrid"></div>
</body>
</html>