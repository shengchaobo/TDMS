<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>教学基本数据库管理平台</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <title>Basic Layout - jQuery EasyUI Demo</title>
    <link rel="stylesheet" type="text/css" href="jquery-easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui/demo/demo.css">
    <script type="text/javascript" src="jquery-easyui/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript">
    
   
    /**
    	$(document).ready(function() { 

		    $("#tab-tools").load("http://localhost:8080/gui/tree.jsp"); 
		
		}); 
		*/
		
		function collapseAll(){
				$('#trees').tree('collapseAll');
			}        
			
			function expandAll(){
				 $('#trees').tree('expandAll');
			}
			
			 function expandTo(){
			  var node = $('#trees').tree('find',113);
			  $('#trees').tree('expandTo', node.target).tree('select', node.target);
			  }
			  
			  function getSelected(){
			  	 var node = $('#trees').tree('getSelected');
			  	 var tabs = $('#tabs') ;
			  	 
			  	 if (node){
			  	 	var s = node.text;
			  	 	if (node.attributes){
			  	 		s += ","+node.attributes.p1+","+node.attributes.p2;
			  	 	}
			  	 	alert(s);
			  	 }
			  	 
			  	// addPanel() ;
			  }
			//向右边添加标签页
		    function addPanel(node){
			   // alert(123) ;
			    var flag = $('#tabs').tabs('exists',node.text) ;
			    
			    alert(node.id) ;
			 
			    if(flag){
			    	$('#tabs').tabs('select',node.text);
			    	return ;
			    }
			    
			    $('#tabs').tabs('add',{
			    title: node.text,
			   // href: node.attributes.url + "?refId=" + node.id,
			   content: '<iframe frameborder=0 width=\'100%\' height=\'100%\' src=\"' + node.attributes.url + '\"?refId=\" + node.id + \" ></iframe>',
			    closable: true
			    });
		    }
		   	
		   	/**
		   	$('#trees').tree({
				onclick: function(node){
					if($('.easyui-tabs').tabs('exists',title)){
                        $('.easyui-tabs').tabs('select',title) ;
                        return ;
                    }
				alert(node.text);  // alert node text property when clicked
				}
		});
		   */
		   //自动从后台加载树控件
		   $(function(){
			    $('#trees').tree({   
			         url: 'pages/trees/loadTree?refId=0',
			     	//在树菜单展开之前，java后台查询
			     	onBeforeExpand:function(node,param){
	                     $('#trees').tree('options').url = "pages/trees/loadTree?refId=" + node.id;   
			     	},
			     	//添加单击树的叶子节点事件
			     	onClick: function(node){
						if($('#trees').tree('isLeaf',node.target)){
							//alert(node.text) ;
							addPanel(node) ;  // alert node text property when clicked
						}
					}
			    });
			});
		   
		   function addTreeMenu(){
			   	var node = $('#trees').tree('getSelected');
			    $('#treeMenuDlg').dialog('open').dialog('setTitle','新增菜单');
			    $('#treeMenuForm').form('clear');
			    if(node){
			    	$('#parentId').val(node.id);
			    	$('#parentName').val(node.text) ;
			    }else{
			    	$('#parentId').val(0) ;
			    	$('#parentName').val("无") ;
			    }
		    }
		   
		   function saveUser(){
			   
			    $('#treeMenuForm').form('submit',{
				    url: 'pages/trees/addTree',
				    onSubmit: function(){
				    	return $(this).form('validate');
				    },
				    success: function(result){
					    var result = eval('('+result+')');
					    
					    if (result.errorMsg){
						    $.messager.alert('温馨提示', result.errorMsg) ;
					    } else {
						    $('#treeMenuDlg').dialog('close'); // close the dialog
						    $('#trees').datagrid('load'); // reload the user data
					    }
				    },
				    onLoaddError: function(result){
				    	$.message.show({
				    		title: '温馨提示',
				    		msg:result.errorMsg
				    	});
				    }
			    });
		    }
		   
		   function reladTree(){
			   var node = $('#trees').tree("getSelected") ;
			   if(node){
				   $('#trees').tree("reload", node.attributes.url + "?refId=" + node.id) ;
			   }else{
				   
			   }
		   }
		   
    </script>
    </head>
    <body style="margin-left:auto;margin-right:auto;align-text:center">
    	<div class="easyui-layout" data-options="fit:true">
	    	<div style="height:46px;background:url(images/main19.jpg);width:1364px;" data-options="region:'north',scroll:false">
	    	<div class="left">
	    	<img src="images/main14.jpg" width="230" height="40px" border="0" />
	    	<font face="楷体" style="font-size:20pt;color:#0033CC">教 学 基 本 状 态 数 据 管 理</font>
	    	</div>
	    	<div class="right">
				<h5 style="align:'right';valign:'bottom'"><a >退出</a> | <a >帮助</a>&nbsp;&nbsp;&nbsp;　  </h5>
			</div>
	    	</div>
	    	<div style="height:30px;background:url(images/main01.jpg);width:1364px;" data-options="region:'south',split:false">
	    	<div align="center" valign="bottom">
				<span style="font-family:宋体;text-align:center">Copyright &copy;南昌工程学院版权 所有</span><br>
			    <span style="font-family:宋体;align:center;valign:bottom">北京交通大学技术支持</span>
		    </div>
	    	</div>
	    	<!-- 
			<div style="width:100px;" title="East" data-options="region:'east',split:true">534</div>
			 -->
		    <div id="tree-tools">
		    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" title="收起" onclick="collapseAll()"></a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" title="展开" onclick="expandAll()"></a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-new'" title="获取选中的节点" onclick="addTreeMenu()"></a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" title="添加" onclick="addPanel()"></a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'" title="刷新" onclick="addPanel()"></a>
		    </div>
	    	<div title="菜单栏" data-options="region:'west',split:true,tools:'#tree-tools',lines:true,border:true"  style="width:200px;">
	    		<ul id="trees" class="easyui-tree" data-options="tools:'#tree-tools',lines:true,border:true">
	    		</ul>
	    	</div>
		    <div id="tabs" class="easyui-tabs" data-options="region:'center',split:false,fit:false" >
		    	<div title="首页" >
		    	<div>
				    <div id="system" class="easyui-panel" title="系统公告" style="width:'auto';height:'auto';padding:10px;">
						<table>
							<tr>
								<td>
									<li>关于该系统的使用方法介绍</li>
								</td>
								<td>
									<li>校领导</li>
								</td>
								<td>
									<li>关于2014年数据采集截止时间</li>
								</td>
							</tr>
						</table>
					</div>
					
					<div id="dataHelp" class="easyui-panel" title="校园新闻" style="width:'auto';height:'auto';padding:10px;">
						<p style="font-size:14px">jQuery EasyUI framework helps you build your web pages easily.</p>
						<ul>
						<table>
							<tr>
							<td>
							<li>easyui is a collection of user-interface plugin based on jQuery.</li>
							</td>
							<td>
							<li>easyui provides essential functionality for building modem, interactive, javascript applications.</li>
							</td>
							<td>
							<li>using easyui you don't need to write many javascript code, you usually defines user-interface by writing some HTML markup.</li>
							</td>
							</tr>
							</table>
							<li>complete framework for HTML5 web page.</li>
							<li>easyui save your time and scales while developing your products.</li>
							<li>easyui is very easy but powerful.</li>
						</ul>
					</div>
					</div>
				</div>
		    </div>
    	</div>
     <div id="treeMenuDlg" class="easyui-dialog" style="width:400px;height:250px;padding:10px 20px" closed="true" buttons="#dlg-buttons">
			<div class="ftitle">菜单管理</div>
				<form id="treeMenuForm" method="post" novalidate>
					<div class="fitem">
					<label>父节点名称:</label> <input id="parentName" name="parentName"
						class="easyui-validatebox" required="true" editable="false">
					<input id="parentId" type="hidden" name="tree.parentId"/>
				</div>
				<div class="fitem">
					<label>菜单名:</label> <input id="treeName" name="tree.treeName"
						class="easyui-validatebox" required="true">
				</div>
				<div class="fitem">
					<label>URL地址:</label> <input id="url" name="tree.url"  placeholder="非叶子节点不需填写">
				</div>
				</form>
			</div>
			<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保存</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#treeMenuDlg').dialog('close')">取消</a>
		</div>
    </body>
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
			
			 .left{

		 float: left;
		 
		 }
		 
		.right{
		
		  float:right;
		}
		</style>
</html>
