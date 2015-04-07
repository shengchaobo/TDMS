<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="cn.nit.constants.Constants"%>
<%@ page import="java.net.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>T49</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
	<!--
		<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui/demo/demo.css">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="js/commom.js"></script>
	<script type="text/javascript" src="js/table4/T49.js"></script>
</head>

<% request.setAttribute("CHECKTYPE",Constants.CTypeTwo); %>
<% request.setAttribute("NOCHECK",Constants.NO_CHECK); %>
<% request.setAttribute("PASS",Constants.PASS_CHECK); %>
<body style="height: 100%'"  onload="myMarquee('T49','<%=request.getAttribute("CHECKTYPE") %>')">
  <div  id="floatDiv">
        <span style="font:12px; font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;审核未通过提示消息：</span>
        <marquee id="marquee"  scrollAmount="1"  width="900"  height="40" direction="up"  style="color: red;"  onmouseover="stop()" onmouseout="start()">
        </marquee>       
  </div>
  <br/> 
	<table  id="unverfiedData"  class="easyui-datagrid"  url="pages/T49/loadTextInfo?checkNum=<%=request.getAttribute("NOCHECK") %>" style="height: auto"  >
		<thead data-options="frozen:true">
			<tr>			
				    <th data-options="field:'ck',checkbox:true" rowspan="2">选取</th>
				    <th  data-options="field:'seqNumber'" rowspan="2">编号</th>
				    <th  data-options="field:'checkState'"   formatter="formatCheckState" rowspan="2">审核状态</th>				    
					  <th data-options="field:'teaUnit'" rowspan="2">
					 			教学单位
						</th>
						<th data-options="field:'unitId'" rowspan="2"> 
								单位号
						</th>
		     </tr>
		</thead>
		<thead>
				<tr>	
					<th data-options="field:'complileBookNum'" rowspan="2">
						教师编著教材数
					</th>				
					<th data-options="field:'writeBookNum'"  rowspan="2">
						教师编写教材数
					</th>
					<th colspan="6">
						其中规划教材
					</th>
					<th colspan="6">
						其中获奖教材
					</th>
				</tr>
				<tr>
					<th  data-options="field:'sumPlanBook'" >
						小计
					</th>
					<th data-options="field:'interPlanBook'" >
						国际级
					</th>
					<th  data-options="field:'nationPlanBook'" >
						国家级
					</th>
					<th data-options="field:'proviPlanBook'" >
						省部级
					</th>					
					<th data-options="field:'cityPlanBook'" >
						市级
					</th>
					<th data-options="field:'schPlanBook'" >
						校级
					</th>
					<th data-options="field:'sumAwardBook'" >
						小计
					</th>
					<th data-options="field:'interAwardBook'" >
						国际级
					</th>
					<th  data-options="field:'nationAwardBook'" >
						国家级
					</th>
					<th data-options="field:'proviAwardBook'" >
						省部级
					</th>					
					<th data-options="field:'cityAwardBook'" >
						市级
					</th>
					<th data-options="field:'schAwardBook'" >
						校级
					</th>
					<th data-options="field:'note'" rowspan="2">
						备注
					</th>
				</tr>
			</thead>
	</table>
	<div id="toolbar" style="height:auto">
		<div style="float: left;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newObject()">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">编辑</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteByIds()">删除</a>
		</div>
		<form method="post" id="searchForm"
				style="float: right; height: 24px;">
				<table id="test" width="520">
					<tr>
						<td>
							编号:
						</td>
						<td>
							<input id="seqNum" name="seqNum" class="easyui-box"
								style="width: 40px" />
						</td>
						<td>
							起始日期:
						</td>
						<td>
							<input id="startTime" name="startTime" class="easyui-datebox"
								style="width: 100px" />
						</td>
						<td>
							结束日期:
						</td>
						<td>
							<input id="endTime" name="endTime" class="easyui-datebox"
								style="width: 100px" />
						</td>
						<td>
							<a href="javascript:void(0)" class="easyui-linkbutton"
								iconCls="icon-search" plain="true" onclick=	reloadgrid();>查询</a>
						</td>
					</tr>
				</table>
			</form>
	</div>
	
	<table id="verfiedData"  class="easyui-datagrid"  url="pages/T49/loadTextInfo?checkNum=<%=request.getAttribute("PASS") %>"  style="height: auto;" >
		<thead data-options="frozen:true">
			<tr>			
				  <th data-options="field:'ck',checkbox:true" colspan="2">选取</th>
				  <th  data-options="field:'seqNumber'" >编号</th>
					<th data-options="field:'teaUnit'" colspan="2">
					 	教学单位
					</th>
					<th data-options="field:'unitId'" colspan="2"> 
						单位号
					</th>
		     </tr>
		</thead>
		<thead>
				<tr>	
					<th data-options="field:'complileBookNum'" rowspan="2">
						教师编著教材数
					</th>				
					<th data-options="field:'writeBookNum'"  rowspan="2">
						教师编写教材数
					</th>
					<th colspan="6">
						其中规划教材
					</th>
					<th colspan="6">
						其中获奖教材
					</th>
				</tr>
				<tr>
					<th  data-options="field:'sumPlanBook'" >
						小计
					</th>
					<th data-options="field:'interPlanBook'" >
						国际级
					</th>
					<th  data-options="field:'nationPlanBook'" >
						国家级
					</th>
					<th data-options="field:'proviPlanBook'" >
						省部级
					</th>					
					<th data-options="field:'cityPlanBook'" >
						市级
					</th>
					<th data-options="field:'schPlanBook'" >
						校级
					</th>
					<th data-options="field:'sumAwardBook'" >
						小计
					</th>
					<th data-options="field:'interAwardBook'" >
						国际级
					</th>
					<th  data-options="field:'nationAwardBook'" >
						国家级
					</th>
					<th data-options="field:'proviAwardBook'" >
						省部级
					</th>					
					<th data-options="field:'cityAwardBook'" >
						市级
					</th>
					<th data-options="field:'schAwardBook'" >
						校级
					</th>
					<th data-options="field:'note'" rowspan="2">
						备注
					</th>
				</tr>
			</thead>
	</table>
	<div id="toolbar2">
			<form action='pages/T49/dataExport?excelName=<%=URLEncoder.encode("表4-9教师出版教材（教学单位-教务处）","UTF-8")%>'   method="post"  id="exportForm" enctype="multipart/form-data"  style="float: right;">
					  <select class="easyui-combobox"  id="cbYearContrast1" name="selectYear"  editable=false ></select>&nbsp;&nbsp;
						<a href='javascript:submitForm()'   style="font:12px;color: black;text-decoration:none;" >
								数据导出
						</a> &nbsp;&nbsp;&nbsp;&nbsp;		
			</form>	
	
	</div>
	
	<!--添加弹出框-->
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
	   <!--<h3 class="ftitle">教师出版教材信息逐条导入</h3>
	   --><form id="addForm" method="post">
		<table>	
			<tr>
				<td>
				<input type="hidden" name="T49_bean.SeqNumber"  id="seqNumber"/>
				<input type="hidden"  name="T49_bean.teaUnit" id="teaUnit"/>
				<input type="hidden"  name="T49_bean.unitId"  id="unitId" >						
 					<div class="fitem">
					<label>教师编著教材数：</label> 
					<input id="complileBookNum" type="text" name="T49_bean.complileBookNum"
							class="easyui-validatebox" ><span id="complileBookNumSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
 					<div class="fitem">
					<label>教师编写教材数：</label> 
					<input id="writeBookNum" type="text" name="T49_bean.writeBookNum"
							class="easyui-validatebox" ><span id="writeBookNumNumSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
 					<div class="fitem">
					<label>国际级规划教材：</label> 
					<input id="interPlanBook" type="text" name="T49_bean.interPlanBook"
							class="easyui-validatebox" ><span id="interPlanBookSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
 					<div class="fitem">
					<label>国家级规划教材：</label> 
					<input id="nationPlanBook" type="text" name="T49_bean.nationPlanBook"
							class="easyui-validatebox" ><span id="nationPlanBookSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
 					<div class="fitem">
					<label>省部级规划教材：</label> 
					<input id="proviPlanBook" type="text" name="T49_bean.proviPlanBook"
							class="easyui-validatebox" ><span id="proviPlanBookSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
 					<div class="fitem">
					<label>市级规划教材：</label> 
					<input id="cityPlanBook" type="text" name="T49_bean.cityPlanBook"
							class="easyui-validatebox" ><span id="cityPlanBookSpan"></span>
					</div>
				</td>
			</tr>			
			<tr>
				<td colspan="3">
 					<div class="fitem">
					<label>校级规划教材：</label> 
					<input id="schPlanBook" type="text" name="T49_bean.schPlanBook"
							class="easyui-validatebox" ><span id="schPlanBookSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
 					<div class="fitem">
					<label>国际级获奖教材：</label> 
					<input id="interAwardBook" type="text" name="T49_bean.interAwardBook"
							class="easyui-validatebox" ><span id="interAwardBookSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
 					<div class="fitem">
					<label>国家级获奖教材：</label> 
					<input id="nationAwardBook" type="text" name="T49_bean.nationAwardBook"
							class="easyui-validatebox" ><span id="nationAwardBookSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
 					<div class="fitem">
					<label>省部级获奖教材：</label> 
					<input id="proviAwardBook" type="text" name="T49_bean.proviAwardBook"
							class="easyui-validatebox" ><span id="proviAwardBookSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
 					<div class="fitem">
					<label>市级获奖教材：</label> 
					<input id="cityAwardBook" type="text" name="T49_bean.cityAwardBook"
							class="easyui-validatebox" ><span id="cityPlanBookSpan"></span>
					</div>
				</td>
			</tr>			
			<tr>
				<td colspan="3">
 					<div class="fitem">
					<label>校级获奖教材：</label> 
					<input id="schAwardBook" type="text" name="T49_bean.schAwardBook"
							class="easyui-validatebox" ><span id="schPlanBookSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td style="valign:left" colspan="3"><label>备注：</label>
					<textarea id="note" name="T49_bean.note" style="resize:none" cols="50" rows="10"></textarea>
					<span id="noteSpan"></span>
				</td>
			</tr>			
		</table>
		</form>
	</div>
	<!-- 跟dlg组合-->
	<div id="dlg-buttons"  >
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="singleImport()">保存</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
</body>
	<script type="text/javascript">
     var currentYear = new Date().getFullYear();   	
     var select1 = document.getElementById("cbYearContrast1");
    	for (var i = 0; i <= 10; i++) {
        var theOption = document.createElement("option");
        	theOption.innerHTML = currentYear-i + "年";
        	theOption.value = currentYear-i;
        	select1.appendChild(theOption);
    	}
	</script>
</html>
