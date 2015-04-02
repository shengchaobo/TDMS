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

<title>C721</title>
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
	<script type="text/javascript">
	 //审核通过
  function passCheck(seqNumber){
    		//alert(seqNumber);
    		var checkNum = <%=Constants.PASS_CHECK %>  
    		//alert(checkNum);
		    $.ajax({
				    type:"POST", 
				    url: "pages/T721/updateCheck?seqNum=" + seqNumber +"&checkNum=" + checkNum, 
			   		async : "true",
			   		dataType : "text",
				    success:function(result){  
				         result = eval("(" + result + ")");						    	 
					  		 if (!result.state){
								  		 	$.messager.show({
								  		 			title: 'Error',
								  		 			msg: result.data
								  			 });
						  		} else {
								    		 $('#checkData').datagrid('reload'); // reload the user data
								    		  $('#checkPassData').datagrid('reload'); // reload the user data	
						  		}
				    }
				});
    }
			
    //审核不通过
  function noPassCheck(seqNumber){
   	    
     		var checkNum = <%=Constants.NOPASS_CHECK %>  
    		//alert(checkNum);
		    $.ajax({
				    type:"POST", 
				    url: "pages/T721/updateCheck?seqNum=" + seqNumber +"&checkNum=" + checkNum, 
			   		async : "true",
			   		dataType : "text",
				    success:function(result){  
				         result = eval("(" + result + ")");						    	 
					  		 if (!result.state){
								  		 	$.messager.show({
								  		 			title: 'Error',
								  		 			msg: result.data
								  			 });
						  		} else {
								    		 $('#checkData').datagrid('reload'); // reload the user data
								    		  $('#checkPassData').datagrid('reload'); // reload the user data			
						  		}
				    }
				});  	    
    }
    
   //全部审核通过
  function checkAll(){
  		  if(confirm("全部数据审核通过，该操作不可恢复，确认吗？")){ 		  	
			    $.ajax({
					    type:"POST", 
					    url: "pages/T721/checkAll", 
				   		async : "true",
				   		dataType : "text",
					    success:function(result){  
					         result = eval("(" + result + ")");						    	 
						  		 if (!result.state){
									  		 	$.messager.show({
									  		 			title: 'Error',
									  		 			msg: result.data
									  			 });
							  		} else {
									    		 $('#checkData').datagrid('reload'); // reload the user data
									    		  $('#checkPassData').datagrid('reload'); // reload the user data	
							  		}
					    }
					});
				}
    }
	</script>
</head>

<% request.setAttribute("CHECKTYPE",Constants.CTypeOne); %>
<% request.setAttribute("WAITCHECK",Constants.WAIT_CHECK); %>
<% request.setAttribute("PASSCHECK",Constants.PASS_CHECK); %>
<body style="height: 100%'">
	<table  id="checkData"  class="easyui-datagrid"  url="pages/T721/auditingData?checkNum=<%=request.getAttribute("WAITCHECK") %>"   style="height: auto"  >
		<thead data-options="frozen:true">
			<tr>			
				<th  data-options="field:'check',align:'center'"   formatter="rowformater">审核操作</th>
		  </tr>
		</thead>
		<thead>
				<tr>		
					<th  data-options="field:'seqNumber'" >编号</th>
						<th field="itemName">项目名称</th>
				<th field="teaUnit">所属教学单位</th>
					<th field="unitID">单位号</th>
				<th field="leader">负责人</th>
				<th field="teaID">教工号</th>
				<th field="otherTeaNum">其他参与教师人数</th>
				<th field="otherTea">其他教师</th>
				<th field="itemLevel">级别</th>
				<th field="itemSetUpTime" formatter="formattime">立项时间</th>
				<th field="receptTime" formatter="formattime">验收时间</th>
				<th field="applvExp">批准经费（万元）</th>
				<th field="schSupportExp">学校配套经费（万元）</th>
				<th field="appvlID">批文号</th>	
				<th field="note">备注</th>
				</tr>
			</thead>
	</table>
	
	<table  id="checkPassData"  class="easyui-datagrid"  url="pages/T721/auditingData?checkNum=<%=request.getAttribute("PASSCHECK") %>&checkFlag=0"   style="height: auto"  >
		<thead data-options="frozen:true">
			<tr>			
				<th  data-options="field:'check',align:'center'"   formatter="rowformater1">审核操作</th>
		  </tr>
		</thead>
		<thead>
				<tr>		
					<th  data-options="field:'seqNumber'" >编号</th>
						<th field="itemName">项目名称</th>
				<th field="teaUnit">所属教学单位</th>
					<th field="unitID">单位号</th>
				<th field="leader">负责人</th>
				<th field="teaID">教工号</th>
				<th field="otherTeaNum">其他参与教师人数</th>
				<th field="otherTea">其他教师</th>
				<th field="itemLevel">级别</th>
				<th field="itemSetUpTime" formatter="formattime">立项时间</th>
				<th field="receptTime" formatter="formattime">验收时间</th>
				<th field="applvExp">批准经费（万元）</th>
				<th field="schSupportExp">学校配套经费（万元）</th>
				<th field="appvlID">批文号</th>	
				<th field="note">备注</th>
				</tr>
			</thead>
	</table>
	
  <div id="toolbar"  style="float: right;">
			<a href='javascript:checkAll()'   class="easyui-linkbutton" iconCls="icon-download" plain="true"  >
					一键审核通过
			</a>
	</div>
	
	<div id="dlg" class="easyui-dialog"
		style="width:400px;height:270px;padding:10px 20px;" closed="true"
		data-options="modal:true" buttons="#dlg-buttons">
		<form id="addReasonForm" method="post">
			<table>			
				<tr>
					<td>
						<div class="fitem">
							<label>审核类型：</label> 
								<input type="text" name="checkInfo.checkType"  id="checkType"   value="<%=request.getAttribute("CHECKTYPE") %>"
								readonly="readonly"  style="width: 150px;color: grey"/>
							</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="fitem">
							<label>被审核表ID：</label> 
								<input type="text" name="checkInfo.tableID" id="tableName"   value="T721"
								readonly="readonly"  style="width: 150px;color: grey"/>
							</div>
					</td>
				</tr>
				<tr>
					<td>
							<div class="fitem">
								<label>该条数据编号：</label> 
								<input type="text" name="checkInfo.checkID" id="dataID" 
								readonly="readonly"  style="width: 150px;color: grey"/>
							</div>
					</td>
				</tr>
				<tr>
					<td>
							<div class="fitem">
								<label>审核不通过理由描述：</label> 
								<textarea id="noPassReason" name="checkInfo.checkInfo" style="resize:none" cols="30" rows="5"></textarea>
							</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="addCheckInfo()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
</body>
</html>
