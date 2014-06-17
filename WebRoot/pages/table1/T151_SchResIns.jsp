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
			width: 120px;
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
	<table id="unverfiedData" title="待审核数据域审核未通过数据" class="easyui-datagrid" style="width:100%px;height:300px" url="pages/SchResIns/auditingData"
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="false" >
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber" width=10>序号</th>
				<th field="resInsName" width=10>科研机构名称</th>
				<th field="resInsID" width=10>单位号</th>
				<th field="type" width=10>类别</th>
				<th field="buildCondition" width=10>共建情况</th>
				<th field="biOpen" width=10 >是否对本科生开放</th>
				<th field="openCondition" width=10>对本科生开放情况（500字以内）</th>
				<th field="teaUnit" width=10>所属教学单位</th>
				<th field="unitID" width=10>教学单位号</th>
				<th field="beginYear" width=10 fit="true" formatter="formattime">开设年份</th>
				<th field="houseArea" width=10>专业科研用房面积（平方米）</th>
				<th field="note" width=10>备注</th>
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
	<table id="verfiedData" title="审核通过数据" class="easyui-datagrid" style="width:100%px;height:250px" url=""
		toolbar="#toolbar2" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="false">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="SeqNumber" width=10>序号</th>
				<th field="ResInsName" width=10>科研机构名称</th>
				<th field="ResInsID" width=10>单位号</th>
				<th field="Type" width=10>类别</th>
				<th field="BuildCondition" width=10>共建情况</th>
				<th field="BiOpen" width=10 >是否对本科生开放</th>
				<th field="OpenCondition" width=10 >对本科生开放情况（500字以内）</th>
				<th field="TeaUnit" width=10>所属教学单位</th>
				<th field="UnitID" width=10>教学单位号</th>
				<th field="BeginYear" width=10 fit="true">开设年份</th>
				<th field="HouseArea" width=10>专业科研用房面积（平方米）</th>
				<th field="Note" width=10>备注</th>
			</tr>
		</thead>
	</table>
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<div class="ftitle">校级以上科研机构批量导入</div>
		<div class="fitem">
			<form id="batchForm" method="post" enctype="multipart/form-data">
				<label>批量上传：</label> 
				<input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox"
					validType="fileType['xls']" required="true" invalidMessage="请选择Excel格式的文件" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">导入</a>
				<a href="pages/SchResIns/downloadModel" class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
		</div>
		<div></div>
		<div class="ftitle">校级以上科研机构逐条导入</div>
		
		<form id="resInsForm" method="post">
		<table>
			<tr>
				<td>
					<div class="fitem">
						<label>科研机构名称：</label> 
						<input id="seqNumber" type="hidden" name="t151Bean.SeqNumber" value="0"></input>
						<input type="hidden" name="t151Bean.ResInsName" id="ResInsName"/>
						<input id="ResInsID" type="text" name="t151Bean.ResInsID" class='easyui-combobox' 
							data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDIDepartmentSci' ,listHeight:'auto',editable:false,
							onSelect:function(){
							 	$('#ResInsName').val($(this).combobox('getText')) ;
							 }">
					    <span id="ResInsNameSpan"></span>
							
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>类别：</label> 
						<input id="Type"  name="t151Bean.Type" class='easyui-combobox'
						data-options="valueField:'indexId',textField:'researchType',url:'pages/DiResearchType/loadDiResearchType',listHeight:'auto',editable:false,
						onSelect:function(){
							 	alert($(this).combobox('getValue')) ;
							 }">
						<span id="TypeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>开设年份：</label> 
						 <input class="easyui-datebox" id="BeginYear" name="t151Bean.BeginYear" >
						 <span id="BeginYearSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>所属教学单位：</label> 
						<input type="hidden" name="t151Bean.TeaUnit" id="TeaUnit"/>
						<input id="UnitID" type="text" name="t151Bean.UnitID" class='easyui-combobox' 
							data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment' ,listHeight:'auto',editable:false,
							onSelect:function(){
							    $('#TeaUnit').val($(this).combobox('getText')) ;
							 }">
							 
					    <span id="TeaUnitSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
			    <td>
					<div class="fitem">
						<label>共建情况：</label> 
						<select class='easyui-combobox' id='BuildCondition' name='t151Bean.BuildCondition'>
						   <option value="true">是</option>
						   <option value="false">否</option> 
						</select>
						<span id="BuildConditionSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>是否对本科生开放：</label> 
						<select class='easyui-combobox' id='BiOpen' name='t151Bean.BiOpen'>
						   <option value="true">是</option>
						   <option value="false">否</option> 
						</select>
						 <span id="BiOpenSpan"></span>
					</div>
				</td>
			</tr>
			
			<tr>
				<td>
					<div class="fitem">
						<label>专业科研用房面积（平方米）：</label> 
						<input id="HouseArea" type="text" name="t151Bean.HouseArea" 
						class="easyui-numberbox"  data-options="min:0,precision:2" required="true">
						   <span id="HouseAreaSpan"></span>
					</div>
				</td>
			</tr>
			
		    <tr>
				<td >
				    <div class="fitem">
						<label>对本科生开放情况（500字以内）：</label> 
						<br/>
						<textarea id="OpenCondition" name="t151Bean.OpenCondition" style="resize:none" cols="50" rows="10"></textarea>
						<span id="OpenConditionSpan"></span>
						</div>
				</td>
			</tr>
			<tr>
			
				<td >
				   <div class="fitem">
					    <label>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
			            <br/>
						<textarea id="Note" name="t151Bean.Note" style="resize:none" cols="50" rows="3"></textarea>
						<span id="NoteSpan"></span>
						</div>
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
   		 url: 'pages/SchResIns/singleSearch',
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
	    	url = 'pages/SchResIns/insert' ;
		    $('#dlg').dialog('open').dialog('setTitle','添加校级科研机构库（科研处）');
		    $('#resInsForm').form('reset');
	    }

	    function singleImport(){
		    //录入数据的表单提交

	    	 $('#resInsForm').form('submit',{
				    url: url ,
				    data: $('#resInsForm').serialize(),
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
		
			var resInsName = $('#ResInsName').val();
			var type = $('#Type').combobox('getText') ;
			var beginYear= $('#BeginYear').datebox('getValue') ;
			var teaUnit = $('#TeaUnit').val();
			var buildCondition = $('#BuildCondition').combobox('getText');
			var biOpen = $('#BiOpen').combobox('getText');
			var houseArea=$('#HouseArea').val();
			var openCondition = $('#OpenCondition').val() ;
			var note = $('#Note').val() ;
			//根据数据库定义的字段的长度，对其进行判断
			if(resInsName == null || resInsName.length == 0){
				$('#ResInsNameSpan').html("<font style=\"color:red\">科研机构名称不能为空</font>") ;
				return false ;
			}else{
				$('#ResInsNameSpan').html("") ;
			}

			if(type == null || type.length == 0){
				$('#TypeSpan').html("<font style=\"color:red\">类别不能为空</font>") ;
				return false ;
			}else{
				$('#TypeSpan').html("") ;
			}

			if(beginYear == null || beginYear.length == 0){
                $('#BeginYearSpan').html("<font style=\"color:red\">开设年份不能为空</font>") ;
                return false;
     		}else {
			    $('BeginYearSpan').html("") ;
			}

			if(teaUnit == null || teaUnit.length == 0){
                $('#TeaUnitSpan').html("<font style=\"color:red\">教学单位不能为空</font>") ;
                return false;
			}else {
			    $('TeaUnitSpan').html("") ;
			}

			if(buildCondition == null || buildCondition.length == 0){
                $('#BuildConditionSpan').html("<font style=\"color:red\">共建情况不能为空</font>") ;
                return false;
			}else {
			    $('BuildConditionSpan').html("") ;
			}

			if(biOpen == null || biOpen.length == 0){
                $('#BiOpenSpan').html("<font style=\"color:red\">开放情况不能为空</font>") ;
                return false;
			}else {
			    $('BiOpenSpan').html("") ;
			}

		if(houseArea == null || houseArea.length == 0){
				$('#HouseAreaSpan').html("<font style=\"color:red\">面积不能为空</font>");
			    return false;
			}else{
				$('#HouseAreaSpan').html("");
			}
			
			if(openCondition == null || openCondition.length==0 || openCondition.length > 500){
				$('#OpenConditionSpan').focus();
				$('#OpenConditionSpan').select();
				$('#OpenConditionSpan').html("<font style=\"color:red\">对本科生开放情况不能为空或长度不超过100</font>") ;
				return false ;
			}else{
				$('#OpenConditionSpan').html("") ;
			}
			
			if(note.length > 1000){
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
	    	url = 'pages/SchResIns/edit' ;
	    	
	    	$('#dlg').dialog('open').dialog('setTitle','添加本科教学课程库');
	    	$('#seqNumber').val(row[0].seqNumber) ;
	    	$('#ResInsID').combobox('select',row[0].resInsID);
	    	$('#Type').combobox('select',row[0].typeID);
	    	$('#BeginYear').datebox('setValue',formattime(row[0].beginYear)) ;
	    	$('#UnitID').combobox('select',row[0].unitID) ;
	    	var flag = "" + row[0].biOpen ;
	    	$('#BuildCondition').combobox('select', row[0].buildCondition) ;
	    	$('#BiOpen').combobox('select',flag) ;
	    	$('#HouseArea').val(row[0].houseArea) ;
	    	$('#OpenCondition').val(row[0].openCondition) ;
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
	    		url: "pages/SchResIns/deleteByIds?ids=" + ids, 
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
