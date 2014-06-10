<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<style type="text/css">
	     label {
	    width: 10em;
	    float: left;
	}
	.empty{
		width: 4em;
	}
	</style>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="js/commom.js"></script>
	<script type="text/javascript" src="js/table4/T49.js"></script>
	<script type="text/javascript"></script>
</head>

<body style="height: 100%'" >
	<table  id="unverfiedData"  class="easyui-datagrid"  url="pages/T49/loadTextInfo"  style="height: auto"  >
		<thead data-options="frozen:true">
			<tr>			
				    <th data-options="field:'ck',checkbox:true" colspan="2">选取</th>
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
	<div id="toolbar" style="height:auto">
		<div style="float: left;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newMajorTea()">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editCourse()">编辑</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyCourse()">删除</a>
		</div>
		 <div style="float: right;">
		 	序号: <input class="easyui-box" style="width:80px"/>
			日期 起始: <input class="easyui-datebox" style="width:80px"/>
			结束: <input class="easyui-datebox" style="width:80px"/>
			<a href="#" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		</div>
	</div>
	
	<table id="verfiedData"  class="easyui-datagrid"  url=""  style="height: auto;" >
		<thead data-options="frozen:true">
			<tr>			
				    <th data-options="field:'ck',checkbox:true" colspan="2">选取</th>
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
	<div id="toolbar2" style="float: right;">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-download" plain="true" onclick="">数据导出</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="">高级检索</a>
	</div>
	
	<!--添加弹出框-->
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
	   <h3 class="ftitle">教师出版教材信息逐条导入</h3>
	   <form id="addForm" method="post">
		<table>
			<tr>
				<td style="valign:left" colspan="3">
					<div class="fitem">
						<label>请选择导入时间：</label> 
						<input class="easyui-datebox"  id="time" type="text" 
						name="T49_bean.time"  required="true"  editable="false" />
						<span id="timeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div class="fitem">
						<label>教学单位：</label> 
						<input type="hidden" name="T49_bean.teaUnit" id="teaUnit"/>
						<input id="unitId" type="text" name="T49_bean.unitId" class='easyui-combobox' 
							data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment',listHeight:'auto',editable:true,
							onSelect:function(){
							    document.getElementById('teaUnit').value=$(this).combobox('getText') ;
							 }">
						 <span id="unitIdSpan"></span>
					</div>
				</td>
			</tr>			
			<tr>
				<td>
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
</html>
