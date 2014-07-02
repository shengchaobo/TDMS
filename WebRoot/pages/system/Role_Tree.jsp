<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>角色管理</title>
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
		width: 80px;
	}
</style>
<script type="text/javascript" src="jquery-easyui/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">

$(function(){
	$('#roleTrees').tree({
		onClick:function(node){
			addTabs(node);
		}
	});
	
	$('#roleAuthId').treegrid({
		/**
		onClick:function(node){
			alert(node.text);
		},
		*/
		onBeforeLoad:function(row,param){    
			if(row == null){
				$('#roleAuthId').treegrid('options').url = "pages/system/loadAuthByUserRole?refId=0&rolesId=" + ${rolesId};
			}else{
				$('#roleAuthId').treegrid('options').url = "pages/system/loadAuthByUserRole?refId=" + row.treeId + "&rolesId=" + row.rolesId;
			}
		}
	});
});
	//日期格式转换 
	function formattime(val) {
		var year = parseInt(val.year) + 1900;
		var month = (parseInt(val.month) + 1);
		month = month > 9 ? month : ('0' + month);
		var date = parseInt(val.date);
		date = date > 9 ? date : ('0' + date);
		var hours = parseInt(val.hours);
		hours = hours > 9 ? hours : ('0' + hours);
		var minutes = parseInt(val.minutes);
		minutes = minutes > 9 ? minutes : ('0' + minutes);
		var seconds = parseInt(val.seconds);
		seconds = seconds > 9 ? seconds : ('0' + seconds);
		var time = year + '-' + month + '-' + date;
		//alert(time) ;
		return time;
	}
	
	function addRoleAuth(){
		alert(123) ;
		//var node = $('#roleAuthId').treegrid('select',1) ;
		var rolesId = document.getElementById("hide").value ;
		alert(rolesId) ;
		$('#roleAuthDlg').dialog('open').dialog('setTitle','为角色添加功能');
	}
	
	function deleteRoleAuth(){
		var node = $('#roleAuthId').treegrid('getSelections') ;
		if(node == null || node.length == 0){
			 $.messager.alert('删除权限', '请选择要删除的权限！！！', 'info');
			 return ;
		}
		var ids = "";
		for(var i=0; i<node.length; i++){
			ids += node[i].treeName + "," ;
		}
		 $.messager.confirm('删除权限', '您确定要删除' + ids + '?', function(r){
			 if (r){
				 deleteByIds(ids) ;
			 }
			 });
	}
	
	function deleteByIds(ids){
		$.ajax({
            url: "pages/system/deleteRoleAuthbyIds?ids=" + ids,
            dataType: "text",
            success: function (data) {
                data = eval("(" + data + ")");
                if (!data.success) {
                	$.messager.show({
   					 title:'删除成功',
   					 msg:data.msg,
   					 timeout:5000,
   					 showType:'slide'
   					 });
                }else{
                	$.messager.show({
   					 title:'删除失败',
   					 msg:data.msg,
   					 timeout:5000,
   					 showType:'slide'
   					 });
                }
            }
        /**
            before: function () {
                startFileUpload();
                var errMsg = "";
            }
            */
        }).submit();
	}
	
</script>
</head>
<body>
	<input type="hidden" id="hide" value="${rolesId}" >
			
			 <table id="roleAuthId" title="权限" class="easyui-treegrid" style="width:auto;height:250px"
				data-options="rownumbers: true, idField: 'treeName', treeField: 'treeName',checkbox:true," singleSelect=false  toolbar="#roleAuthToolbar">
				<thead>
				<tr>
					<th data-options="field:'ck', checkbox:true" width="220">选择</th>
					<th data-options="field:'treeName'" width="220">角色权限栏</th>
				</tr>
				</thead>
			</table>
			<div id="roleAuthToolbar">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="addRoleAuth()">添加</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="deleteRoleAuth()">删除</a>
			</div>
	<div id="roleAuthDlg" class="easyui-dialog" style="width:400px;height:200px;padding:10px 20px" closed="true"
			buttons="#dlg-roleAuth">
			<div class="ftitle">功能列表</div>
			<form id="fm" method="post" >
				<div class="fitem">
					<label>选择:</label>
					<select id="cc" class="easyui-combotree" data-options="url:'pages/system/loadRoleAuthTree?rolesId=${rolesId}',method:'post'" multiple style="width:200px;" ></select>
				</div>
			</form>
	</div>
	
	 <div id="dlg-roleAuth">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveRoleAuth()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#roleAuthDlg').dialog('close')">取消</a>
	</div>
</body>
</html>
