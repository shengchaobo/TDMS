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

<title>C533</title>
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
				    url: "pages/T533/updateCheck?seqNum=" + seqNumber +"&checkNum=" + checkNum, 
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
				    url: "pages/T533/updateCheck?seqNum=" + seqNumber +"&checkNum=" + checkNum, 
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
						  		}
				    }
				});  	    
    }
    
   //全部审核通过
  function checkAll(){
  			
  			if(confirm("全部数据审核通过，该操作不可恢复，确认吗？")){ 					
				    $.ajax({
						    type:"POST", 
						    url: "pages/T533/checkAll", 
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
								  		}
						    }
						});
				}
    }
	</script>
</head>

<% request.setAttribute("CHECKTYPE",Constants.CTypeTwo); %>
<% request.setAttribute("WAITCHECK",Constants.WAIT_CHECK); %>
<body style="height: 100%" >  
	<table  id="checkData"  class="easyui-datagrid"  url="pages/T533/auditingData?checkNum=<%=request.getAttribute("WAITCHECK") %>"    style="height: auto"  >
		<thead data-options="frozen:true">
			<tr>			
				<th  data-options="field:'check',align:'center'"   formatter="rowformater">审核操作</th>
		 </tr>
		</thead>
		<thead>
				<tr>		
					<th  data-options="field:'seqNumber'" >编号</th>				
					<th data-options="field:'teaUnit'">
						教学单位
					</th>
					<th data-options="field:'unitID'">
						单位号
					</th>
					<th data-options="field:'majorName'">
						专业名称
					</th>
					<th data-options="field:'majorID'">
						专业代码
					</th>
					<th data-options="field:'expCSNum'">
						有实验的课程（门）
					</th>
					<th data-options="field:'indepentExpCSNum'">
						独立设置的实验课程（门）
					</th>
					<th data-options="field:'designExpCSNum'">
						综合性、设计性实验教学（门）
					</th>
					<th data-options="field:'expRatio'" >
						实验开出率（%）
					</th>
					<th data-options="field:'note'">
						备注
					</th>
				</tr>
			</thead>
	</table>
		
  <div id="toolbar"  style="float: right;">
			<a href='javascript:checkAll()'   class="easyui-linkbutton" iconCls="icon-download" plain="true"  >
					一键全审核通过
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
								<input type="text" name="checkInfo.tableID" id="tableName"   value="T533"
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
								<label>填写者的教学单位号：</label> 
								<input type="text" name="checkInfo.fillUnitID"  id="unitID" 
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
	
	<script type="text/javascript"> 
			//日期格式转换 
			function formattime(val) {  
				
				if(val == null){
					return null ;
				}
				
			    var year=parseInt(val.year)+1900;  
			    var month=(parseInt(val.month)+1);  
			    month=month>9?month:('0'+month);  
			    var date=parseInt(val.date);  
			    date=date>9?date:('0'+date);  
			    var hours=parseInt(val.hours);  
			    hours=hours>9?hours:('0'+hours);  
			    var minutes=parseInt(val.minutes);  
			    minutes=minutes>9?minutes:('0'+minutes);  
			    var seconds=parseInt(val.seconds);  
			    seconds=seconds>9?seconds:('0'+seconds);  
			    var time=year+'-'+month+'-'+date ;  
			    //alert(time) ;
			        return time;  
			    }  
			</script>
			<script type="text/javascript"> 
		    function booleanstr(val) { 	 
		    	if(val == null){
					return null ;
				}
				var bo1=""+val;//吧boolean型转换成str类型再判断
				var boo;
				if( bo1 == "false") {
					boo="否" ;
				}else if (bo1 == "true"){

					boo="是" ;
				}
				return boo;
	        }  

		    function rowformater(value,row,index)
		    {
		    	return "<a href='javascript:passCheck(" + row.seqNumber+")'>审核通过</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href ='javascript:openDig("+ row.seqNumber+ "," + row.fillUnitID +")'>审核不通过</a>";
		    }
			</script>
			
</body>
</html>
