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
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newCourse()">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editCourse()">编辑</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteByIds()">删除</a>
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
	<div id="toolbar2">
		<a href="pages/UndergraCSBaseTea/dataExport" class="easyui-linkbutton" iconCls="icon-download">数据导出</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="loadDic()">高级检索</a>
	</div>
	<table id="verfiedData" title="审核通过数据" class="easyui-datagrid" style="width:100%px;height:250px" url="pages/table5/verifiedData"
		toolbar="#toolbar2" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="false">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="id" width="5%">序号</th>
				<th field="csName" width="10%">课程名称</th>
				<th field="csId" width="10%">课程编号</th>
				<th field="csUnit" width="5%">开课单位</th>
				<th field="unitId" width="5%">单位号</th>
				<th field="fromTeaResOffice" width="5%">所属教研室</th>
				<th field="teaResOfficeId" width="5%">教研室号</th>
				<th field="csType" width="10%">课程类别</th>
				<th field="csNature" width="10%">课程性质</th>
				<th field="state" width="5%">状态</th>
				<th field="pubCsType" width="10%">公选课类别</th>
				<th field="note" width="20%">备注</th>
			</tr>
		</thead>
	</table>
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<div class="ftitle">本科课程库批量导入</div>
		<div class="fitem">
			<form id="batchForm" method="post" enctype="multipart/form-data">
				<label>批量上传：</label> 
				<input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox"
					validType="fileType['xls']" required="true" invalidMessage="请选择Excel格式的文件" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">导入</a>
				<a href="pages/UndergraCSBaseTea/downloadModel" class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
		</div>
		<div></div>
		<div class="ftitle">本科课程库逐条导入</div>
		<form id="courseForm" method="post">
		<table>
		
			<tr>
				<td>
					<div class="fitem">
						<label>课程名称：</label> 
						<input id="seqNumber" type="hidden"name="undergraCSBaseTea.SeqNumber" value="0"></input>
						<input id="CSName" type="text" name="undergraCSBaseTea.CSName"
							class="easyui-validatebox" required="true"><span id="CSNameSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>课程编号：</label> 
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
				<td style="valign:left"><label>备&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
					<textarea id="Note" name="undergraCSBaseTea.Note" style="resize:none" cols="50" rows="10"></textarea>
					<span id="NoteSpan"></span>
				</td>
			</tr>
		</table>
		</form>
	</div>
	
	<div id="dicDlg" class="easyui-dialog" style="width:500px;padding:10px 20px" closed="true">
		<div class="ftitle">高级检索</div>
		<div id="dicTables"  class="fitem">
		</div>
		<div id="dices"  class="fitem"></div>
	</div>
	 
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="singleImport()">保存</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
</body>
	<script type="text/javascript">
	
	var url ;
	
	function singleSearch(){
   	 $('#auditing').form('submit',{
   		 url: 'pages/UndergraCSBaseTea/singleSearch',
   		 type: "post",
	     dataType: "json",
   		 success: function(result){
   		 	var result = eval('('+result+')');
   		 	if (!result.state){
   		 		$.messager.show({
   		 			title: 'Error',
   		 			msg: result.errorMsg
   			 });
   		 	} else {
   		 		alert(13113) ;
		    	$('#unverfiedData').datagrid('load'); // reload the auditing data
   		 	}
   		 }
   		 });
   }
	
	    function batchImport(){
	    	 $('#batchForm').form('submit',{
	    		 url: 'pages/UndergraCSBaseTea/uploadFile',
	    		 type: "post",
		         dataType: "json",
	    		 onSubmit: function(){
	    			 return check() ;
	    		 },
	    		 success: function(result){
	    		 	var result = eval('('+result+')');
	    		 	if (!result.success){
	    		 		$.messager.show({
	    		 			title: 'Error',
	    		 			msg: result.errorMsg
	    			 });
	    		 	} else {
			    		 $('#dlg').dialog('close'); // close the dialog
			    		 $('#dg').datagrid('reload'); // reload the user data
	    		 	}
	    		 }
	    		 });
	    }
	    
	    function check(){
	    	var fileName = $('#uploadFile').val() ;
	    	
	    	if(fileName == null || fileName == ""){
	    		return false ;
	    	}
	    	
	    	var pos = fileName.lastIndexOf(".") ;
	    	var suffixName = fileName.substring(pos, fileName.length) ;
	    	
	    	if(suffixName == ".xls"){
	    		return true ;
	    	}else{
	    		return false ;
	    	}
	    } 
	    
	    function newCourse(){
	    	url = 'pages/UndergraCSBaseTea/insert' ;
		    $('#dlg').dialog('open').dialog('setTitle','添加本科教学课程库');
		    $('#courseForm').form('reset');
	    }

	    function singleImport(){
		    //录入数据的表单提交
	    	 $('#courseForm').form('submit',{
				    url: url,
				    data: $('#courseForm').serialize(),
		            type: "post",
		            dataType: "json",
				    onSubmit: function(){
				    	return validate();
				    },
				    //结果返回
				    success: function(result){
					    //json格式转化
					    var result = eval('('+result+')');
					    $.messager.alert('温馨提示', result.data) ;
					    if (result.state){ 
						    $('#dlg').dialog('close'); 
						    $('#unverfiedData').datagrid('reload'); 
					    }
				    }
			    });
		}

		function validate(){
			//获取文本框的值
			var csName = $('#CSName').val() ;
			var csId = $('#CSID').val() ;
			var TeaResOffice = $('#TeaResOfficeID').combobox('getText') ;
			var csUnit = $('#UnitID').combobox('getText') ;
			var csType = $('#CSType').combobox('getText') ;
			var csNature = $('#CSNature').combobox('getText') ;
			var state = $('#State').combobox('getText') ;
			var pubCSType = $('#PubCSType').combobox('getText') ;
			var note = $('#Note').val() ;
			//根据数据库定义的字段的长度，对其进行判断
			if(csName == null || csName.length==0 || csName.length > 100){
				$('#CSName').focus();
				$('#CSName').select();
				$('#CSNameSpan').html("<font style=\"color:red\">课程名称不能为空或长度不超过100</font>") ;
				return false ;
			}else{
				$('#CSNameSpan').html("") ;
			}
			
			if(csId == null || csId.length == 0 || csId.length > 50){
				$('#CSID').focus();
				$('#CSID').select();
				$('#CSIDSpan').html("<font style=\"color:red\">课程编号不能为空或长度不超过50</font>") ;
				return false ;
			}else{
				$('#CSIDSpan').html("") ;
			}
			
			if(csUnit == null || csUnit.length == 0){
				$('#CSUnitSpan').html("<font style=\"color:red\">开课单位不能为空</font>") ;
				return false ;
			}else{
				$('#CSUnitSpan').html("") ;
			}
			
			if(TeaResOffice == null || TeaResOffice.length ==0 || TeaResOffice.length > 50){
				$('#TeaResOfficeID').html("<font style=\"color:red\">开课单位不能为空</font>") ;
				return false ;
			}else{
				$('#TeaResOfficeID').html("") ;
			}
			
			if(csType == null || csType.length == 0){
				$('#CSTypeSpan').html("<font style=\"color:red\">课程类别不能为空</font>") ;
				return false ;
			}else{
				$('#CSTypeSpan').html("") ;
			}
			
			if(csNature == null || csNature.length == 0){
				$('#CSNatureSpan').html("<font style=\"color:red\">课程性质不能为空</font>") ;
				return false ;
			}else{
				$('#CSNatureSpan').html("") ;
			}
			if(state == null || state.length == 0){
				$('#StateSpan').html("<font style=\"color:red\">课程状态不能为空</font>") ;
				return false ;
			}else{
				$('#StateSpan').html("") ;
			}
			
			if(pubCSType == null || pubCSType.length == 0){
				$('#PubCSTypeSpan').html("<font style=\"color:red\">公选课类别不能为空</font>") ;
				return false ;
			}else{
				$('#PubCSTypeSpan').html("") ;
			}
			
			if(note !=null && note.length > 1000){
				$('#NoteSpan').html("<font style=\"color:red\">备注中文字数不超过500</font>") ;
				return false ;
			}else{
				$('#NoteSpan').html("") ;
			}
			return true ;
		}

	    function editCourse(){
	    	var row = $('#unverfiedData').datagrid('getSelections');
	    	
	    	if(row.length != 1){
	    		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
	    		return ;
	    	}
	    	
	    	url = 'pages/UndergraCSBaseTea/edit' ;
	    	
	    	$('#dlg').dialog('open').dialog('setTitle','添加本科教学课程库');
	    	$('#seqNumber').val(row[0].seqNumber) ;
	    	$('#CSID').val(row[0].CSID) ;
	    	$('#CSName').val(row[0].CSName) ;
	    	$('#UnitID').combobox('select',row[0].unitID) ;
	    	$('#TeaResOfficeID').combobox('select', row[0].teaResOfficeID) ;
	    	$('#CSType').combobox('select', row[0].CSTypeID) ;
			$('#CSNature').combobox('select', row[0].CSNatureID) ;
			$('#State').combobox('select', row[0].state) ;
			$('#PubCSType').combobox('select', row[0].pubCSType) ;
			$('#Note').val(row[0].note) ;
	    }
	    
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
	    
	    function loadDic(){
		    $('#dicDlg').dialog('open').dialog('setTitle','高级查询');
		    loadDictionary() ;
		    
	    }
	    
	    function loadDictionary(){
	    	
	    	$.ajax({ 
	    		type: "POST", 
	    		url: "table5/loadDic", 
	    		async:"false",
	    		dataType: "text",
	    		success: function(data){
	    			data = eval("(" + data + ")");
	    			alert(data[0].id) ;
	    			var str = "<table width=\"100%\" border=\"1\"><tr>" ;
	    			$(data).each(function(index) {
	    				var val = data[index];
	    				if(index%4 == 0 && index != 0){
	    					str += "</tr><tr>" ;
	    				}
	    				str += "<td><input type=\"checkbox\" id=\"" + val.id + "\"name=" + "\"checkboxex\"" +  "value=\"" + val.data + "\">" + val.data + "</input></td>" ; 
	    			}); 
	    			//alert(str);
	    			str += "</tr><tr><td colSpan=\"4\" style=\"text-align:center\"><a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" iconCls=\"icon-add\" onclick=\"loadData()\">添加</a></td></tr></table>" ;
	    			document.getElementById("dicTables").innerHTML = str;
	    			$.parser.parse('#dicTables');
	    		}
	    	}).submit();
	    }
	    	
	    function loadData(){
	    	
	    	//flag判断
	    	var flag = false ;
	    	var checkboxes = document.getElementsByName("checkboxex");
	    	var tables = "<div class=\"ftitle\">自定义查询条件</div><form method=\"post\" action=\"table5/dictorySearch\" id=\"dicsDataForm\"><table width=\"100%\" border=\"1\">" ;
	    	tables += "<tr><td>查询名称</td><td>运算符</td><td>查询内容</td><td>逻辑关系</td></tr>" ;
	    	for(i=0; i<checkboxes.length; i++){
	    		if(checkboxes[i].checked){
	    			flag = true ;
	    			tables += ("<tr><td style=\"width:50%px\">" + hideId(checkboxes[i].id,i)  + checkboxes[i].value + "</td><td>" + selectOperateData(i) + "</td><td>" + selectDataHtml(checkboxes[i].id,i) +"</td><td>" + selectLogicData(i) + "</td></tr>") ;
	    		}
	    	}
	    	if(flag){
	    		tables += "<tr><td colSpan=\"4\" style=\"text-align:center\"><a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" iconCls=\"icon-search\" onclick=\"submitDicForm()\">查询</a></td></tr>" ;
	    	}
	    	tables += "</table></form>" ;
	    	alert(tables) ;
	    	document.getElementById("dices").innerHTML = tables ;
	    	$.parser.parse('#dices');
	    	
	    }
	    
	    function hideId(val,index){
	    	var hiddenId = "<input type='hidden' name='dictorySearch[" + index + "].id' value='" + val + "'/>" ;
	    	
	    	return hiddenId ;
	    }
	    
	    //自动加载要查询的数据
	    function selectDataHtml(val,index){
	    	
	    	var selectsHtml = "<select class=\"easyui-combogrid\" style=\"width:50%px\" name=\"dictorySearch[" + index + "].dicData\" data-options=\"panelWidth: 500, multiple: true,required:true,"
	    	 + " idField: 'dicData',textField: 'dicData',"
	    	 + "url: 'table5/loadDictionary?dicId=" + val + "',"
	    	 + "method: 'post',"
	    	 + "columns: [[{field:'ck',checkbox:true},{field:'itemid',title:'数据',width:80},{field:'dicData',title:'数据',width:80}]],"
	    	 + "fitColumns: true \"> </select>" ;
	    	 
	    	 return selectsHtml ;
	    }
	    
	    //生成运算关系combo
	    function selectOperateData(index){
	    	
	    	var operateHtml = "<select style=\"width:15%px\" name=\"dictorySearch[" + index + "].operator\"> <option value=\"equals\">等于</option><option value=\"between\">之间</option><option value=\"side\">两边</option></select>" ;
	    	
	    	return operateHtml ;
	    }
	    
	  //生成逻辑关系combo
	    function selectLogicData(index){
	    	
	    	var logicHtml = "<select style=\"width:15%px\" name=\"dictorySearch[" + index + "].logic\"> <option value=\"and\">并且</option><option value=\"or\">或者</option></select>" ;
	    	
	    	return logicHtml ;
	    }
	  
	  function submitDicForm(){
		  $.ajax({ 
	    		type: "POST", 
	    		url: "table5/dictorySearch",
	    		data: $('#dicsDataForm').serialize(), 
	    		async:"false",
	    		dataType: "text",
	    		success: function(data){
	    			alert(123) ;
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
