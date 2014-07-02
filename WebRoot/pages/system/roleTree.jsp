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
				$('#roleAuthId').treegrid('options').url = "pages/system/loadAuthByUserRole?refId=0&rolesId=1";
			}else{
				$('#roleAuthId').treegrid('options').url = "pages/system/loadAuthByUserRole?refId=" + row.treeId + "&rolesId=1";
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
		var node = $('#roleAuthId').treegrid('select',1) ;
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
            url: "pages/system/deleteRoleAutbyIds?ids=" + ids,
            dataType: "text",
            success: function (data) {
                data = eval("(" + data + ")");
                if (!data.success) {
                	$.messager.show({
   					 title:'删除成功',
   					 msg:'删除成功',
   					 timeout:5000,
   					 showType:'slide'
   					 });
                }else{
                	$.messager.show({
   					 title:'删除失败',
   					 msg:'删除失败',
   					 timeout:5000,
   					 showType:'slide'
   					 });
                }
            },
            error: function(data){
            	data = eval("(" + data + ")");
                if (!data.success) {
                    alert(data.errorMsg) ;
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
	
	//向右边添加标签页
    function addTabs(node){
	   var flag = $('#roleTabs').tabs('exists',node.text) ;
	    
	    alert(node.id) ;
	 
	    if(flag){
	    	$('#roleTabs').tabs('select',node.text);
	    	return ;
	    }
	    
	    $('#roleTabs').tabs('add',{
	    title: node.text,
	   // href: node.attributes.url + "?refId=" + node.id,
	   content: '<iframe frameborder=0 width=\'100%\' height=\'100%\' src=\"pages/system/loadRoleAuth?refId=0&rolesId=' + node.id + '\"></iframe>',
	    closable: true
	    });
	    
    }
	
</script>
</head>
<body>
	<input type="hidden" id="hide" value="${rolesId}" >
	<div class="easyui-layout" data-options="fit:true">
		
		<div id="roleTrees" class="easyui-tree" title="角色栏"
			data-options="region:'west',split:true,tools:'#tree-tools',lines:true,border:true,url:'pages/role/loadRoleTree'"
			style="width:200px;">
		</div>
		<div id="roleTabs" title='角色权限栏' class="easyui-tabs" data-options="region:'center',split:true,fit:true">
		</div>
	</div>
</body>
</html>
