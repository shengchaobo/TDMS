<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'table.jsp' starting page</title>
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
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	
</head>
<body style="overflow-y:scroll">
	<table id="unverfiedData" title="待审核数据域审核未通过数据" class="easyui-datagrid" style="width:100%px;height:250px" url="pages/UndergraCSBaseTea/auditingData"
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="false" >
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber" width="10">序号</th>
				<th field="CSName" width="10">课程名称</th>
				<th field="CSID" width="10">课程编号</th>
				<th field="CSUnit" width="10">开课单位</th>
				<th field="unitID" width="10">单位号</th>
				<th field="fromTeaResOffice" width="10">所属教研室</th>
				<th field="teaResOfficeID" width="10">教研室号</th>
				<th field="CSType" width="10">课程类别</th>
				<th field="CSNature" width="10">课程性质</th>
				<th field="state" width="10" fit="true">状态</th>
				<th field="pubCSType" width="10">公选课类别</th>
				<th field="note" width="20">备注</th>
				<th field="time" width="10" formatter="formattime">时间</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="height:auto">
		<div>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="throughAudit()">通过</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="rejectAudit()">驳回</a> 
		</div>
		 <div>
		 	<form id="auditing" method="post">
			 	序号: <input id="seqNum" name="seqNum" class="easyui-numberbox" style="width:80px"/>
				日期 起始: <input id="startTime" name="startTime" class="easyui-datebox" style="width:80px"/>
				结束: <input id="endTime" name="endTime" class="easyui-datebox" style="width:80px"/>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="singleSearch()">查询</a>
			</form>
		</div>
	</div>
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<div class="ftitle">数据驳回</div>
		<form id="courseForm" method="post">
		<table>
		
			<tr>
				<td>
					<div class="fitem">
						<label>邮箱密码：</label> 
						<input id="seqNumber" type="hidden"name="undergraCSBaseTea.SeqNumber" value="0"></input>
						<input id="CSName" type="text" name="undergraCSBaseTea.CSName"
							class="easyui-validatebox" required="true"><span id="CSNameSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>驳回原因：</label> 
						<input id="CSID" type="text" name="undergraCSBaseTea.CSID"
							class="easyui-validatebox" required="true"><span id="CSIDSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>开课单位：</label> 
						<!-- 下边的onselect方法是为了后台既要教学单位名称，有需要教学单位编号，而我们只有一个下拉框包含了这两条信息 -->
						<input type="hidden" name="undergraCSBaseTea.CSUnit" id="CSUnit"/>
						<input id="UnitID" name="undergraCSBaseTea.UnitID" 
							 class='easyui-combobox' data-options="valueField:'unitID',textField:'unitName',url:'pages/diDepartment/loadDIDepartment',listHeight:'auto',editable:false,
							 onSelect:function(){
							 	document.getElementById('CSUnit').value=$(this).combobox('getText') ;
							 }">
						<span id="CSUnitSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>所属教研室：</label> 
						<input type="hidden" name="undergraCSBaseTea.FromTeaResOffice" id="FromTeaResOffice"/>
						<input id="TeaResOfficeID" name="undergraCSBaseTea.TeaResOfficeID" class='easyui-combobox'
							data-options="valueField:'unitID',textField:'researchName',url:'pages/diResearchRoom/loadDIResearchRoom',listHeight:'auto',editable:false,
							onSelect:function(){
							 	$('#FromTeaResOffice').val($(this).combobox('getText')) ;
							 }">
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>课程类别：</label> 
						<input class='easyui-combobox' id="CSType" name="undergraCSBaseTea.CSType" 
							data-options="valueField:'indexID',textField:'courseCategories',url:'pages/diCourseCategories/loadDICourseCategories',listHeight:'auto',editable:false">
						<span id="CSTypeSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>课程性质：</label> 
						<input class='easyui-combobox' id="CSNature" name="undergraCSBaseTea.CSNature"
							data-options="valueField:'indexID',textField:'courseChar',url:'pages/diCourseChar/loadDICourseChar',listHeight:'auto',editable:false">
						<span id="CSNatureSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>状&nbsp;&nbsp;&nbsp;&nbsp;态：</label> 
						<select class='easyui-combobox' id="State" name="undergraCSBaseTea.State" >
							<option value="启用">启用</option>
							<option value="停用">停用</option>
						</select>	
							<span id="StateSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>公选课类别：</label> 
						<select class='easyui-combobox' id="PubCSType" name="undergraCSBaseTea.PubCSType">
							<option value="理工类">理工类</option>
							<option value="人文社科类">人文社科类</option>
							<option value="体育保健类">体育保健类</option>
							<option value="无">无</option>
						</select>
						<span id="PubCSTypeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
			<input class="easyui-datebox" name="undergraCSBaseTea.time" style="width:80px"/>
				<td style="valign:left"><label>驳回原因：：</label>
					<textarea id="Note" name="undergraCSBaseTea.Note" style="resize:none" cols="50" rows="10"></textarea>
					<span id="NoteSpan"></span>
				</td>
			</tr>
		</table>
		</form>
	</div>
	 
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="singleImport()">保存</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
</body>
	<script type="text/javascript">
	
	    
	    function deleteByIds(){
	    	//获取选中项
			var row = $('#unverfiedData').datagrid('getSelections');
	    	
			if(row.length == 0){
	    		$.messager.alert('温馨提示', "请选择需要删除的数据！！！") ;
	    		return ;
	    	}
	    	
			 $.messager.confirm('数据删除', '您确定删除选中项?', function(sure){
				 if (sure){
				 	var ids = "";
				 	ids += "(" ;
				 	
				 	for(var i=0; i<row.length; i++){
				 		if(i < (row.length - 1)){
				 			ids += (row[i].seqNumber + ",") ;
				 		}else{
				 			ids += (row[i].seqNumber + ")") ;
				 		}
				 	}
				 	
				 	deleteCourses(ids) ;
				 	
				 }
			});
	    }
	    
	    function deleteCourses(ids){
	    	$.ajax({ 
	    		type: "POST", 
	    		url: "pages/UndergraCSBaseTea/deleteByIds?ids=" + ids, 
	    		async:"true",
	    		dataType: "text",
	    		success: function(result){
	    			result = eval("(" + result + ")");

					if(result.state){
						alert(result.data) ;
						 $('#unverfiedData').datagrid('reload') ;
					}
	    		}
	    	}).submit();
	    }
	    
	</script>
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

</html>
