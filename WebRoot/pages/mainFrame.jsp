<%@ page language="java" import="java.util.*"   import ="cn.nit.bean.UserinfoBean" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false"%>
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
	
    <link rel="stylesheet" type="text/css" href="jquery-easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui/demo/demo.css">
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <script type="text/javascript" src="jquery-easyui/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
     <style type="text/css">  
	    .blue{background: #bcd4ec;}  
	</style> 
    <script type="text/javascript">
    		
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
			  }
			  
			//向右边添加标签页
		    function addPanel(node){
			    var flag = $('#tabs').tabs('exists',node.text) ;
			    if(flag){
			    	$('#tabs').tabs('select',node.text);
			    	return ;
			    }			    
			    $('#tabs').tabs('add',{
			    title: node.text,
			   	content: '<iframe frameborder=0 width=\'100%\' height=\'100%\' src=\"' + node.attributes.url + '\"?refId=\" + node.id + \" ></iframe>',
			    closable: true
			    //fit: true
			    });
		    }
		    
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
						    reloadTree();// reload the user data
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
		   
		   function reloadTree(){
   			    $('#trees').tree({   
			         url: 'pages/trees/loadTree?refId=0'
			    });
		   }
		   
		   //刷新功能
		   function reFresh(){
                var node = $('#trees').tree('getSelected');
                if (node) {
                    $('#trees').tree('reload', node.target);
                }
                else {
                    $('#trees').tree('reload');
                }
		   }
		   
           function remvoeTreeMenu(){
                var node = $('#trees').tree('getSelected');
                if(node == null){
                 	alert("请先选中一个节点！");
                 	return ;
                }
                $.messager.confirm('数据删除', '您确定删除选中节点?', function(sure) {
				if (sure) {              			
               	       deletes(node);
				}
   				});
            }
            
   		function deletes(node) {
   				$.ajax( {
   				type : "POST",
   				url : "pages/trees/removeTree?refId=" + node.id,      
   				async : "true",
   				dataType : "text",
   				success : function(result) {
					reloadTree();
				}
   			}).submit();
   		}
   		
   		 //绑定tabs的右键菜单
	  $(document).ready(function () {
	    	$("#tabs").tabs({
	       	 	onContextMenu : function (e, title) {
	            	e.preventDefault();
	            	$('#tabsMenu').menu('show', {
	             	   left : e.pageX,
	              	   top : e.pageY
	          	  }).data("tabTitle", title);
	      	  }
    	});
    	
   		 //实例化menu的onClick事件
   		 $("#tabsMenu").menu({
       	 onClick : function (item) {
           	 CloseTab(this, item.name);
        	}
    	});
  	 });
  	 

    
       //几个关闭事件的实现
    function CloseTab(menu, type) {
        var curTabTitle = $(menu).data("tabTitle");
        var tabs = $("#tabs");
        
        if (type === "close") {
            tabs.tabs("close", curTabTitle);
            return;
        }
        
        var allTabs = tabs.tabs("tabs");
        var closeTabsTitle = [];
        
        $.each(allTabs, function () {
            var opt = $(this).panel("options");
            if (opt.closable && opt.title != curTabTitle && type === "Other") {
                closeTabsTitle.push(opt.title);
            } else if (opt.closable && type === "All") {
                closeTabsTitle.push(opt.title);
            }
        });
        
        for (var i = 0; i < closeTabsTitle.length; i++) {
            tabs.tabs("close", closeTabsTitle[i]);
        }
    }
    
    //用户show效果
    $(document).ready(function () {
		    $("#username").mouseover(function(){
				document.getElementById("showList").style.display="block";
			});
						
		    $("#showList").mouseleave(function(){
				document.getElementById("showList").style.display="none";
			});
			
	        $("#showList li").hover(function () {  
            		$(this).addClass("blue");  
	        }, function () {  
	            	$(this).removeClass("blue");  
	        }); 
	});
	
	//修改密码
		function alertPsd() {
			$('#dlg').dialog('open').dialog('setTitle', '修改个人密码');
		}
		
	function singleImport() {
		//录入数据的表单提交
		$('#userManagerForm').form('submit', {
			url : 'pages/UserManager/alertPassword',
			data : $('#userManagerForm').serialize(),
			type : "post",
			dataType : "json",
			onSubmit : function() {
				return validate();
			},
			//结果返回
			success : function(result) {
				//json格式转化
				var result = eval('(' + result + ')');
				$.messager.alert('温馨提示', result.data);
				if (result.state) {
					$('#dlg').dialog('close');					
				}
			}
		});
	}
	
		function validate() {
		//获取文本框的值
		var newPsd = $('#newPsd').val();
		var comfirmPsd = $('#comfirmPsd').val();
		//根据数据库定义的字段的长度，对其进行判断
		if (newPsd != comfirmPsd ) {
			alert("前后密码不一致!!!");
			return false;
		}
		
		if( (newPsd == comfirmPsd) && ( newPsd.length == 0)){
			alert("密码不能为空!!!");
			return false;
		}		
		return true;
	}
	
    
    </script>
    
 
    </head>
    <body style="margin-left:auto;margin-right:auto;align-text:center">
    <div class="easyui-layout" data-options="fit:true">
	    <div style="height:75px;background:url(images/main02.jpg);width:1364px; overflow: hidden;" data-options="region:'north',split:true">
	    	<div class="left">
	    	<table>
	    	<tr>
	    	<td><img src="images/index.jpg" border="0"></td>
	    	<td><font face="宋体" style="font-size:21pt;color:#0033CC">教 学 基 本 状 态 数 据 管 理</font></td> 
	    	</tr></table>
	    	</div>
	    	<div class="right">
	    		<table>
	    		<tr>
	    		<td><b>欢迎用户：</b></td>
	    		<td><a href="javascript:void(0);"  id="username" onclick="return false">${userinfo.teaID}</a>			</td>
	    		</tr>
	    		<tr>
	    			<td></td>
	    			<td width="90px;">
	    				<div  id="showList"  style="display: none; border:1px solid grey; background-color: white;">
							<ul>
								<li><a href="javascript:void(0)"  onclick="return alertPsd()" >&nbsp;&nbsp;修改个人密码</a></li>
								<li><a href="exit" >&nbsp;&nbsp;注销登录</a></li>	
							</ul>
						</div>
	    			</td>
	    		</tr>
				</table>
			</div>	
	   </div>
	   <div style="height:30px;background:url(images/main01.jpg);width:1364px;" data-options="region:'south',split:true">
	    	<div align="center" valign="bottom">
				<span style="font-family:宋体;text-align:center">Copyright &copy;南昌工程学院版权 所有</span><br>
			    <span style="font-family:宋体;align:center;valign:bottom">北京交通大学技术支持</span>
		    </div>
	    </div>
	    <%  
	    		UserinfoBean user = (UserinfoBean) session.getAttribute("userinfo") ;
	    		if("000".equals(user.getRoleID())){
	     %>		
		    <div id="tree-tools">
		    	<a href="javascript:void(0)"   class="easyui-linkbutton"  data-options="plain:true,iconCls:'icon-back'" title="全部收起" onclick="collapseAll()" style="position: relative;top: -5px;"></a><!--
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-sum'" title="展开" onclick="expandAll()" style="position: relative;top: -5px;"></a>
				--><a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" title="添加一个节点" onclick="addTreeMenu()" style="position: relative;top: -5px;"></a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" title="删除一个节点" onclick="remvoeTreeMenu()" style="position: relative;top: -5px;"></a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'" title="刷新" onclick="reFresh()" style="position: relative;top: -5px;"></a>
		    </div>
		<%    
	    		}	    
	    %>

	    	<div id="menu" title="菜单栏"  data-options="region:'west',tools:'#tree-tools',split:true"   style="width:240px;"  >
	    		<ul id="trees" class="easyui-tree"  data-options="lines:true,border:true" ></ul>
	    	</div>
	    	<div region="center" border="false">
				<div id="tabs" class="easyui-tabs" data-options="split:true"  fit="true">
			    	<div title="首页" >
			    		    <%  
	    		user = (UserinfoBean) session.getAttribute("userinfo") ;
	    		if("111".equals(user.getRoleID())){
	     %>	
			    	   <iframe frameborder=0 width='100%' height='100%' src="pages/index1.jsp"></iframe>
		<%    
	    		}else{	    
	    %>
	     <iframe frameborder=0 width='100%' height='100%' src="pages/index.jsp"></iframe>
	     		<%    
	    		}	    
	    %>
	     
					</div>
			    </div>
		    </div>

			<div id="tabsMenu" class="easyui-menu" style="width: 120px;">
				<div name="close">
					关闭
				</div>
				<div name="Other">
					关闭其他
				</div>
				<div name="All">
					关闭所有
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
		
	<div id="dlg" class="easyui-dialog"
		style="width:400px;height:200px;padding:10px 20px;" closed="true"
		data-options="modal:true" buttons="#dlg-buttons">
		<form id="userManagerForm" method="post">
			<table>
				<tr>
					<td>
						<div class="fitem">
							<label>教工号：</label> 
								<input type="text" name="teaID" id="teaID"   value="${userinfo.teaID}"
								readonly="readonly"  style="width: 150px;color: grey"/>
							</div>
					</td>
				</tr>
				<tr>
					<td>
							<div class="fitem">
								<label>新密码：</label> 
								<input type="password" name="newPsd"  
								style="width: 150px;"   class="easyui-validatebox" id="newPsd" required='true'/>
								<span id="newPsdSpan"></span>
							</div>
					</td>
				</tr>
				<tr>
					<td>
							<div class="fitem">
								<label>确认密码：</label> 
								<input type="password" name="comfirmPsd"  
								style="width: 150px;"  class="easyui-validatebox" id="comfirmPsd" required='true'/>
								<span id="comfirmPsdSpan"></span>
							</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="singleImport()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
	
    </body>
    
    	<style type="text/css">			
		 .left{

		 float: left;
		 
		 }
		 
		.right{
		
		  float:right;
		  margin-right: 50px;
		}
		
		ul,ol{list-style:none; margin:0px; padding:0px;} 
		</style>
</html>
