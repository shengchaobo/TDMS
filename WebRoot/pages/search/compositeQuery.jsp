<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'compositeQuery.jsp' starting page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
	<!--
		<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<LINK href="<%=request.getContextPath() %>/css/CN/BLUE/mainWin.css" type=text/css media=screen rel=stylesheet>
		<LINK href="<%=request.getContextPath() %>/css/CN/BLUE/query.css" type=text/css media=screen rel=stylesheet>
			
	<link rel="stylesheet" type="text/css" href="jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui/demo/demo.css">
	
	<style type="text/css">
		#fm {
			margin: 0;
			padding: 10px 30px;
		}
		
		.ftitle {
			font-size: 14px;
			font-weight: bold;
			padding: 5px 0;
			margin-bottom: 10px;
			border-bottom: 1px solid #ccc;
		}
		
		.fitem {
			margin-bottom: 5px;
		}
		
		.fitem label {
			display: inline-block;
			width: 80pRx;
		}
			#TableData .serach_list td{ border-top: 0px solid #D2D2D2; empty-cells: show; height: 24px; padding-left: 8px; vertical-align: middle;}
			#explain_div{display:none; border: 1px solid #91C0E3; margin:10px 0; padding: 10px; width:800px; vertical-align: middle;}
			#explain_div .titleLeft{text-align: left;float: left; height: 22px;width:150px; padding: 4px 0 0px 3px; border-bottom: 1px solid #DDDDDD;}
			#explain_div .titleRight{text-align: right;}
			#explain_div .content{clear: both; padding: 10px; }
		
		</style>

	<script type="text/javascript" src="jquery-easyui/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script> 
	<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/system/FieldName.js"></script>
	
</head>

<body marginwidth="0" topmargin="0" leftmargin="0" marginheight="0">

  <input id="functionName" type="hidden" name="functionName" />
			<DIV ID=Title_bar>
				<DIV ID=Title_bar_Head>
					<DIV ID=Title_Head></DIV>
					<DIV ID=Title>
						<img border="0" width="11" height="11"
					src="<%=request.getContextPath() %>/images/title_arrow.gif" />
						自定义查询
					</DIV>
					<DIV ID=Title_End></DIV>
				</DIV>
				</DIV>
	
	<DIV ID=MainArea>
	<center>
  	<DIV CLASS=ItemBlock_Title1>		 
    <label>选择所要查询的表：</label>
    <input id="Tname" type="hidden" name="diTableMessageBean.Tname">
    <input id="Tid" name="diTableMessageBean.Tid" 
							 class='easyui-combobox' data-options="valueField:'tid',textField:'tname',url:'pages/DiTableMessage/loadTableMessage',listHeight:'auto',editable:true,
							 onSelect:function(){
							 	document.getElementById('Tname').value=$(this).combobox('getText') ;
							 }">
							 &nbsp&nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp&nbsp
<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="Query()">执行查询</a> 
   </DIV> 
    <DIV CLASS=ItemBlockBorder>
    <DIV CLASS=ItemBlock>
    
    <form method="post" id="QueryForm" >
   <TABLE WIDTH="70%" BORDER=0 CELLSPACING=0 CELLPADDING=0 >
	    <TR ALIGN=center VALIGN=middle ID=TableTitle>
        <td  width="10%">左括号</td>
        <td  width="20%">查询条件列</td>
        <td  width="10%">逻辑运算符</td>
        <td  width="20%">查询条件值</td>
        <td  width="10%">右括号</td>
        <td  width="10%">逻辑连接符</td>
  </tr>

  </table>
  <tbody ID="TableData">
  <tr height="18" class="serach_list" id="d0">
  <TD>
		      <select class='easyui-combobox' id="leftJoin" name="QueryConditionsBean.leftJoin" style="width: 87px;">
								<option value="">无</option>
								<option value="(">(</option>
								<option value="((">((</option>
								<option value="(((">(((</option>
			  </select>
  </TD>
  <TD>
 <select  id="fieldName" name="QueryConditionsBean.FieldName" class='easyui-combobox' style="width: 166px;">						
 </select>
 </TD>
 <TD>
 <select id="logicRelation" name="QueryConditionsBean.LogicRelation" class='easyui-combobox' style="width: 84px;">
								<option value="">选择</option>
								<option value="=">=</option>
								<option value="&gt;=">&gt;=</option>
								<option value="&lt;=">&lt;=</option>
								<option value="!=">!=</option>
								<option value="&gt;">&gt;</option>
								<option value="&lt;">&lt;</option>
								<option value="like">like</option>
								<option value="1">包含</option>
								<option value="2">不包含</option>
								<option value="3">为空</option>
								<option value="4">不为空</option>
</select>
</TD>

<TD>
<input maxlength="100"  id="paramValue" name="QueryConditionsBean.ParamValue" style="width: 170px;" type="text" onfocus="formatM(this);"/>
</TD>
<TD>
<select id="rightJoin" name="QueryConditionsBean.RightJoin" class='easyui-combobox' style="width: 84px;">
							    <option value="">无</option>
							    <option value=")">)</option>
							    <option value="))">))</option>
							    <option value=")))">)))</option>
</select>
</TD>
<TD>
<select id="joinRelation" name="QueryConditionsBean.JoinRelation" class='easyui-combobox' style="width: 80px;" >
								<option value="">选择</option>
								<option value="and">并且</option>
								<option value="or">或者</option>
						</select>
</TD>
</tr>
<br>
<tr height="18" class="serach_list" id="d0">
  <TD>
		      <select id="leftJoin1" name="QueryConditionsBean.LeftJoin1" class='easyui-combobox' style="width: 87px;">
								<option value="">无</option>
								<option value="(">(</option>
								<option value="((">((</option>
								<option value="(((">(((</option>
			  </select>
  </TD>
  <TD>
  <select id="fieldName1" name="QueryConditionsBean.FieldName1" class='easyui-combobox' style="width: 166px;">						
 </select>
 </TD>
 <TD>
 <select id="logicRelation1" name="QueryConditionsBean.LogicRelation1" class='easyui-combobox' style="width: 84px;">
								<option value="">选择</option>
								<option value="=">=</option>
								<option value="&gt;=">&gt;=</option>
								<option value="&lt;=">&lt;=</option>
								<option value="!=">!=</option>
								<option value="&gt;">&gt;</option>
								<option value="&lt;">&lt;</option>
								<option value="like">like</option>
								<option value="1">包含</option>
								<option value="2">不包含</option>
								<option value="3">为空</option>
								<option value="4">不为空</option>
</select>
</TD>

<TD>
	<input maxlength="100"  id="paramValue1" name="QueryConditionsBean.ParamValue1" style="width: 170px;" type="text" onfocus="formatM(this);"/>
</TD>
<TD>
<select id="rightJoin1" name="QueryConditionsBean.RightJoin1" class='easyui-combobox' style="width: 84px;">
							    <option value="">无</option>
							    <option value=")">)</option>
							    <option value="))">))</option>
							    <option value=")))">)))</option>
</select>
</TD>

</tr>
</tbody>
</form>
 </div>
 </DIV>
<div id="divDatagrid"></div>
</center>
</DIV>



</body>
</html>