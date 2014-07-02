<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
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
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script> 
	<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body style="overflow-y:scroll">
	<table id="unverfiedData" title="待审核数据域审核未通过数据" class="easyui-datagrid" style="height: auto;" url="pages/DiscipRes/auditingData"
		toolbar="#toolbar" pagination="true" 
		 singleSelect="false" >
		<thead data-options="frozen:true">
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber" >序号</th>
				<th field="discipName" >重点学科名称</th>
				</tr>
				</thead>
				<thead>
				<tr>
				<th field="discipID" >学科代码</th>
				<th field="unitName" >所属教学单位</th>
				<th field="unitID" >单位号</th>
				<th field="discipType" >学科门类</th>
				<th field="nationLevelOne"  formatter="booleanstr">国家一级</th>
				<th field="nationLevelTwo"  formatter="booleanstr">国家二级</th>
				<th field="nationLevelKey" formatter="booleanstr">国家重点（培育）</th>
				<th field="provinceLevelOne" " formatter="booleanstr">省部一级</th>
				<th field="provinceLevelTwo"  formatter="booleanstr">省部二级</th>
				<th field="cityLevel"  formatter="booleanstr">市级</th>
				<th field="schLevel"  formatter="booleanstr">校级</th>
				<th field="note" >备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="height:auto">
		<div>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newCourse()">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editDiscip()">编辑</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteByIds()">删除</a>
			<a href="pages/DiscipRes/dataExport?excelName=表3-1-3重点学科（科研处）.xls" class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 
		</div>
		 <div>
		 	<form id="auditing" method="post">
			 	序号: <input id="seqNum" name="seqNum" class="easyui-numberbox" style="width:80px"/>
				日期 起始: <input id="startTime" name="startTime" class="easyui-datebox" style="width:80px"/>
				结束: <input id="endTime" name="endTime" class="easyui-datebox" style="width:80px"/>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="reloadgrid()">查询</a>
			</form>
		</div>
	</div>
	<div id="toolbar2">
	 <form  id="exportForm"  method="post" style="float: right;">
			<select class="easyui-combobox" id="cbYearContrast" name="selectYear" panelHeight="auto" style="width:80px; padding-top:5px; margin-top:10px;"></select>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-download" plain="true"  onclick="exports()">数据导出</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="loadDic()">高级检索</a>
		</form>
	</div>
	<table id="verfiedData" title="审核通过数据" class="easyui-datagrid" style="height: auto;" url="table5/verifiedData"
		toolbar="#toolbar2" pagination="true"
		 singleSelect="false">
		<thead data-options="frozen:true">
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber" >序号</th>
				<th field="discipName" >重点学科名称</th>
			</tr>
				</thead>
				<thead>
				<tr>
				<th field="discipID" >学科代码</th>
				<th field="unitName" >所属教学单位</th>
				<th field="unitId" >单位号</th>
				<th field="discipType" >学科门类</th>
				<th field="nationLevelOne" >国家一级</th>
				<th field="nationLevelTwo" >国家二级</th>
				<th field="nationLevelKey" >国家重点（培育）</th>
				<th field="provinceLevelOne" ">省部一级</th>
				<th field="provinceLevelTwo" >省部二级</th>
				<th field="cityLevel" >市级</th>
				<th field="schLevel" >校级</th>
				<th field="note" >备注</th>
			</tr>
		</thead>
	</table>
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<div class="ftitle">重点学科库批量导入</div>
		<div class="fitem">
			<form id="batchForm" method="post" enctype="multipart/form-data">
				<label>批量上传：</label> 
				<select class="easyui-combobox"  id="cbYearContrast" name="selectYear"></select>
				<input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox"
					validType="fileType['xls']" required="true" invalidMessage="请选择Excel格式的文件" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">导入</a>
				<a href='pages/DiscipRes/downloadModel?saveFile=<%=URLEncoder.encode("表3-1-3重点学科（科研处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
			<a href="123"></a>
		</div>
		<div></div>
		<div class="ftitle">重点学科库逐条导入</div>
		
		<form id="discipForm" method="post">
		<table>
			<tr>
				<td>
					<div class="fitem">
						<label>重点学科名称：</label> 
						<input id="SeqNumber" name="discipBean.SeqNumber" type="hidden" > </input>
						<input id="DiscipName" type="text" name="discipBean.DiscipName"
							class="easyui-validatebox" required="true"><span id="DiscipNameSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>学科代码：</label> 
						<input id="DiscipID" type="text" name="discipBean.DiscipID"
							class="easyui-validatebox" required="true"><span id="DiscipIDSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>所属单位：</label> 
						<!-- 下边的onselect方法是为了后台既要教学单位名称，有需要教学单位编号，而我们只有一个下拉框包含了这两条信息 -->
						<input type="hidden" name="discipBean.UnitName" id="UnitName"/>
						<input id="UnitID" type="text" name="discipBean.UnitID" 
							 class='easyui-combobox' data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment',listHeight:'auto',editable:false,
							 onSelect:function(){
							 	document.getElementById('UnitName').value=$(this).combobox('getText') ;
							 }">
						<span id="UnitNameSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>学科门类：</label> 
						<select class='easyui-combobox' id="DiscipType" name="discipBean.DiscipType">
							<option value="01哲学">01哲学</option>
							<option value="02经济学">02经济学</option>
							<option value="03法学">03法学</option>
							<option value="04教育学">04教育学</option>
							<option value="05文学">05文学</option>
							<option value="06历史学">06历史学</option>
							<option value="07理学">07理学</option>
							<option value="08工学">08工学</option>
							<option value="09农学">09农学</option>
							<option value="10医学">10医学</option>
							<option value="11军事学">11军事学</option>
							<option value="12管理学">12管理学</option>
							<option value="13艺术学">13艺术学</option>
						</select>
						<span id="DiscipTypeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
			<td>
					<div class="fitem">
						<label>国家一级：</label> 
						<select class='easyui-combobox' id="NationLevelOne" name="discipBean.NationLevelOne">
							<option value="false">否</option>
							<option value="true">是</option>
						</select>
						<span id="NationLevelOneSpan"></span>
					</div>
				</td>
			<td>
					<div class="fitem">
						<label>国家二级：</label> 
						<select class='easyui-combobox' id="NationLevelTwo" name="discipBean.NationLevelTwo">
							<option value="false">否</option>
							<option value="true">是</option>
						</select>
						<span id="NationLevelTwoSpan"></span>
					</div>
				</td>
				</tr>
				<tr>
							<td>
					<div class="fitem">
						<label>国家重点：</label> 
						<select class='easyui-combobox' id="NationLevelKey" name="discipBean.NationLevelKey">
							<option value="false">否</option>
							<option value="true">是</option>
						</select>
						<span id="NationLevelKeySpan"></span>
					</div>
				</td>
			</tr>
			<tr>

			<td>
					<div class="fitem">
						<label>省部一级：</label> 
						<select class='easyui-combobox' id="ProvinceLevelOne" name="discipBean.ProvinceLevelOne">
							<option value="false">否</option>
							<option value="true">是</option>
						</select>
						<span id="ProvinceLevelOneSpan"></span>
					</div>
				</td>
							<td>
					<div class="fitem">
						<label>省部二级：</label> 
						<select class='easyui-combobox' id="ProvinceLevelTwo" name="discipBean.ProvinceLevelTwo">
							<option value="false">否</option>
							<option value="true">是</option>
						</select>
						<span id="ProvinceLevelTwoSpan"></span>
					</div>
				</td>
			</tr>
			<tr>

			<td>
					<div class="fitem">
						<label>市级：</label> 
						<select class='easyui-combobox' id="CityLevel" name="discipBean.CityLevel">
							<option value="false">否</option>
							<option value="true">是</option>
						</select>
						<span id="CityLevelSpan"></span>
					</div>
				</td>
								<td>
					<div class="fitem">
						<label>校级：</label> 
						<select class='easyui-combobox' id="SchLevel" name="discipBean.SchLevel">
							<option value="false">否</option>
							<option value="true">是</option>
						</select>
						<span id="SchLevelSpan"></span>
					</div>
				</td>
			</tr>

			
			<tr>

				<td style="valign:left"><label>备&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
					<textarea id="Note" name="discipBean.Note" style="resize:none" cols="50" rows="10"></textarea>
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
	
	    var url;

		function reloadgrid ()  { 
	        //查询参数直接添加在queryParams中 
	         var queryParams = $('#unverfiedData').datagrid('options').queryParams;  
	         queryParams.seqNum = $('#seqNum').val(); 
	         queryParams.startTime = $('#startTime').datetimebox('getValue');	         		     
        	 queryParams.endTime  = $('#endTime').datetimebox('getValue');        	 
	         $("#unverfiedData").datagrid('reload'); 
	    }

	    
	    function batchImport(){
	    	 $('#batchForm').form('submit',{
	    		 url: 'pages/DiscipRes/uploadFile',
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
			    		 $('#unverfiedData').datagrid('reload'); // reload the user data
	    		 	}
	    		 }
	    		 });
	    }
	    
	    function check(){
	    	var fileName = $('#uploadFile').val() ;
	    	
	    	if(fileName == null || fileName == ""){
	    		 $.messager.alert("操作提示", "请先选择要导入的文件！");
	    		return false ;
	    	}
	    	
	    	var pos = fileName.lastIndexOf(".") ;
	    	var suffixName = fileName.substring(pos, fileName.length) ;
	    	
	    	if(suffixName == ".xls"){
	    		return true ;
	    	}else{
	    		 $.messager.alert("操作提示", "请选择正确的Excel文件（后缀为.xls）");
	    		return false ;
	    	}
	    } 
	    
	    function newCourse(){
		    $('#dlg').dialog('open').dialog('setTitle','添加重点学科库');
		    $('#discipForm').form('reset');
	    }


	    function singleImport(){
	
		    //录入数据的表单提交
	    	 $('#discipForm').form('submit',{
				    url: 'pages/DiscipRes/insert',
				    data: $('#discipForm').serialize(),
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
						    $('#unverifiedData').datagrid('reload'); 
					    }
				    }
			    });
		
		}
		

		function validate(){
			//获取文本框的值
			var DiscipName = $('#DiscipName').val() ;
			var DiscipID = $('#DiscipID').val() ;
			var UnitName = $('#UnitID').combobox('getText') ;
			var DiscipType = $('#DiscipType').combobox('getText') ;
			var NationLevelOne = $('#NationLevelOne').combobox('getText') ;
			var NationLevelTwo = $('#NationLevelTwo').combobox('getText') ;
			var NationLevelKey = $('#NationLevelKey').combobox('getText') ;
			var ProvinceLevelOne = $('#ProvinceLevelOne').combobox('getText') ;
			var ProvinceLevelTwo = $('#ProvinceLevelTwo').combobox('getText') ;
			var CityLevel = $('#CityLevel').combobox('getText') ;
			var SchLevel = $('#SchLevel').combobox('getText') ;
			var Note = $('#Note').val() ;
			//根据数据库定义的字段的长度，对其进行判断
			if(DiscipName == null || DiscipName.length==0 || DiscipName.length > 100){
				$('#DiscipName').focus();
				$('#DiscipName').select();
				$('#DiscipNameSpan').html("<font style=\"color:red\">学科名称不能为空或长度不超过100</font>") ;
				return false ;
			}else{
				$('#DiscipNameSpan').html("") ;
			}
			
			if(DiscipID == null || DiscipID.length == 0 || DiscipID.length > 50){
				$('#DiscipID').focus();
				$('#DiscipID').select();
				$('#DiscipIDSpan').html("<font style=\"color:red\">学科编号不能为空或长度不超过50</font>") ;
				return false ;
			}else{
				$('#DiscipIDSpan').html("") ;
			}
			
			if(UnitName == null || UnitName.length == 0){
				$('#UnitNameSpan').html("<font style=\"color:red\">开课单位不能为空</font>") ;
				return false ;
			}else{
				$('#UnitNameSpan').html("") ;
			}
			
			if(DiscipType == null || DiscipType.length == 0){
				$('#DiscipTypeSpan').html("<font style=\"color:red\">学科门类不能为空</font>") ;
				return false ;
			}else{
				$('#DiscipTypeSpan').html("") ;
			}
			
			if(Note !=null && Note.length > 1000){
				$('#NoteSpan').html("<font style=\"color:red\">备注中文字数不超过500</font>") ;
				return false ;
			}else{
				$('#NoteSpan').html("") ;
			}
			return true ;
		}

		function singleSearch(){
		   	 $('#auditing').form('submit',{
		   		 url: 'pages/DiscipRes/singleSearch',
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

	    function editDiscip(){
	    	var row = $('#unverfiedData').datagrid('getSelections');
	    	
	    	if(row.length != 1){
	    		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
	    		return ;
	    	}
	    	
	    	url = 'pages/DiscipRes/edit' ;
	    	
	    	$('#dlg').dialog('open').dialog('setTitle','添加重点学科');
	    	$('#SeqNumber').val(row[0].seqNumber) ;
	        $('#DiscipName').val(row[0].discipName);
	        $('#DiscipID').val(row[0].discipID);
	        $('#UnitID').combobox('select',row[0].unitID) ;
	        $('#DiscipType').combobox('select',row[0].discipType);
	        $('#NationLevelOne').combobox('select',row[0].nationLevelOne);
	        $('#NationLevelTwo').combobox('select',row[0].nationLevelTwo);
	        $('#NationLevelKey').combobox('select',row[0].nationLevelKey);
	        $('#ProvinceLevelOne').combobox('select',row[0].provinceLevelOne);
	        $('#ProvinceLevelTwo').combobox('select',row[0].provinceLevelTwo);
	        $('#CityLevel').combobox('select',row[0].cityLevel);
	        $('#SchLevel').combobox('select',row[0].schLevel);

			$('#Note').val(row[0].note);
		
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
				 	
				 	deleteDiscip(ids) ;
				 	
				 }
			});
	    }


	    function exports() {
	    	var temp = encodeURI('表3-1-3重点学科（科研处）.xls');
		    $('#exportForm').form('submit', {
		    url : "pages/DiscipRes/dataExport?excelName="+temp ,
		    onSubmit : function() {
		    return $(this).form('validate');//对数据进行格式化
		    },
		    success : function(data) {
		    $.messager.show({
		    	title : '提示',
		    	msg : data
		    });
		    }
		    }); 
	    }

	    function deleteDiscip(ids){
	    	$.ajax({ 
	    		type: "POST", 
	    		url: "pages/DiscipRes/deleteCoursesByIds?ids=" + ids, 
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
	    		url: "table3/dictorySearch",
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
			</script>
			
		<script type="text/javascript">
    	var currentYear = new Date().getFullYear();
    	var select = document.getElementById("cbYearContrast");
    	for (var i = 0; i <= 10; i++) {
        var theOption = document.createElement("option");
        	theOption.innerHTML = currentYear-i + "年";
        	theOption.value = currentYear-i;
        	select.appendChild(theOption);
    	}
	</script>

</html>
