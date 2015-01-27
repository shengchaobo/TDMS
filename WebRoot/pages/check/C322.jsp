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

<title>C412</title>
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
				    url: "pages/UndergraMajorInfoTea/updateCheck?seqNum=" + seqNumber +"&checkNum=" + checkNum, 
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
				    url: "pages/UndergraMajorInfoTea/updateCheck?seqNum=" + seqNumber +"&checkNum=" + checkNum, 
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
						    url: "pages/UndergraMajorInfoTea/checkAll", 
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
	<table  id="checkData"  class="easyui-datagrid"  url="pages/UndergraMajorInfoTea/loadMajorTea?checkNum=<%=request.getAttribute("WAITCHECK") %>"    style="height: auto"  >
		<thead data-options="frozen:true">
			<tr>			
				<th  data-options="field:'check',align:'center'"   formatter="rowformater">审核操作</th>
		 </tr>
		</thead>
		<thead>
				<tr>		
					<th  data-options="field:'seqNumber'" >编号</th>			
				<th field="majorName" >专业名称</th>
				<th field="majorID" >专业代码</th>
					<th field="majorVersion" >代码版本</th>
				<th field="majorField" >专业方向名称</th>
				<th field="majorFieldID" >专业方向号</th>
				<th field="majorSetTime"  formatter="formattime">专业设置时间</th>
				<th field="majorAppvlID"  >批文号</th>
				<th field="majorDurition" >学制</th>
				<th field="majorDegreeType" >学位授予门类</th>
				<th field="majorAdmisTime"  formatter="formattime">开始招生时间</th>
				<th field="majorState" >招生状态</th>
				<th field="stopAdmisTime"  formatter="formattime">停止招生时间</th>
				<th field="isNewMajor" formatter="booleanstr" >是否新办专业</th>
				<th field="appvlYear"  formatter="formattime">批准建设年度</th>
				<th field="buildAppvlID" >建设批文号</th>
				<th field="majorLevel" >级别</th>
				<th field="type" >类型</th>
				<th field="field" >领域方向</th>
				<th field="leader"  >建设负责人</th>
				<th field="teaID" >教工号</th>
				<th field="checkTime"  formatter="formattime">验收时间</th>
				<th field="checkAppvlID" >验收批文号</th>
				<th field="schExp" >学校经费（万元）</th>
				<th field="eduMinistryExp" >教育部经费（万元）</th>
				<th field="firstAppvlTime"  formatter="formattime">首次通过认证时间</th>
				<th field="appvlTime"  formatter="formattime">认证时间</th>
				<th field="appvlID" >批文号</th>
				<th field="appvlResult" >认证结果</th>
				<th field="fromTime"  formatter="formattime">有效期（起）</th>
				<th field="endTime"  formatter="formattime">有效期（止）</th>
				<th field="appvlAuth"  >认证机构</th>
				<th field="totalCSHour" >总学时数</th>
				<th field="requireCShour" >必修课学时数</th>
				<th field="optionCSHour" >选修课学时数</th>
				<th field="inClassCSHour" >课内教学学时数</th>
				<th field="expCSHour" >实验教学学时数</th>
				<th field="praCSHour" >集中性实践教学环节学时数</th>
				<th field="totalCredit" >总学分数</th>
				<th field="requireCredit" >必修课学分数</th>
				<th field="optionCredit" >选修课学分数</th>
				<th field="inClassCredit" >课内教学学分数</th>
				<th field="expCredit" >实验教学学分数</th>
				<th field="praCredit" >集中实践教学环节学分数</th>
				<th field="outClassCredit" >课外科技活动学分数</th>
				<th field="note" >备注</th>
					 <th data-options="field:'fillUnitID',hidden:true">
						填报教学单位
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
								<input type="text" name="checkInfo.tableID" id="tableName"   value="T322"
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
</body>
</html>
