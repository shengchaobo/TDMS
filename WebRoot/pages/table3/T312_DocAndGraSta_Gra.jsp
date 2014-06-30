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
	<table id="unverfiedData" title="待审核数据域审核未通过数据" class="easyui-datagrid" style="width:100%px;height:250px" url="pages/DocAndGraSta/auditingData"
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="false" >
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber" width=10>序号</th>
				<th field="staName" width=10>名称</th>
				<th field="staID" width=10">代码</th>
				<th field="unitName" width=10>所属单位</th>
				<th field="unitID" width=10>单位号</th>
				<th field="staType" width=10>类型</th>
				<th field="note" width=10>备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="height:auto">
		<div>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newCourse()">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editDocAndGra()">编辑</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteByIds()">删除</a>
			<a href="pages/DocAndGraSta/dataExport?excelName=表3-1-2博士点 、硕士点（研究生院）.xls" class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 
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
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-download" plain="true" onclick="newCourse()">数据导出</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="loadDic()">高级检索</a>
	</div>
	<table id="verfiedData" title="审核通过数据" class="easyui-datagrid" style="width:100%px;height:250px" url="table5/verifiedData"
		toolbar="#toolbar2" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="false">
		<thead>
			<tr>
		<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="SeqNumber" width="5%">序号</th>
				<th field="StaName" width="10%">名称</th>
				<th field="StaID" width="10%">代码</th>
				<th field="UnitName" width="5%">所属单位</th>
				<th field="UnitID" width="5%">单位号</th>
				<th field="StaType" width="5%">类型</th>
				<th field="Note" width="20%">备注</th>
			</tr>
		</thead>
	</table>
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<div class="ftitle">博士点硕士点批量导入</div>
		<div class="fitem">
			<form id="batchForm" method="post" enctype="multipart/form-data">
				<label>批量上传：</label> 
				<input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox"
					validType="fileType['xls']" required="true" invalidMessage="请选择Excel格式的文件" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">导入</a>
				<a href='pages/DocAndGraSta/downloadModel?saveFile=<%=URLEncoder.encode("表3-1-2博士点 、硕士点（研究生院）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
			<a href="123"></a>
		</div>
		<div></div>
		<div class="ftitle">博士点硕士点逐条导入</div>
		
		<form id="DocAndGraStaForm" method="post">
		<table>
			<tr>
				<td>
					<div class="fitem">
						<label>名称：</label> 
						<input id="seqNumber" name="postDocStaBean.SeqNumber" type="hidden" > </input>
						<input id="StaName" type="text" name="docAndGraStaBean.StaName"
							class="easyui-validatebox" required="true"><span id="StaNameSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>代码：</label> 
						<input id="StaID" type="text" name="docAndGraStaBean.StaID"
							class="easyui-validatebox" required="true"><span id="StaIDSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>所属单位：</label> 
						<!-- 下边的onselect方法是为了后台既要教学单位名称，有需要教学单位编号，而我们只有一个下拉框包含了这两条信息 -->
						<input type="hidden" name="docAndGraStaBean.UnitName" id="UnitName"/>
						<input id="UnitID" type="text" name="docAndGraStaBean.UnitID" 
							 class='easyui-combobox' data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment',listHeight:'auto',editable:false,
							 onSelect:function(){
							 	document.getElementById('UnitName').value=$(this).combobox('getText') ;
							 }">
						<span id="UnitNameSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>类型：</label> 
						<select class='easyui-combobox' id="StaType" name="docAndGraStaBean.StaType">
							<option value="硕士点">硕士点</option>
							<option value="博士点">博士点</option>
						</select>
						<span id="StaTypeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td style="valign:left"><label>备&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
					<textarea id="Note" name="docAndGraStaBean.Note" style="resize:none" cols="50" rows="10"></textarea>
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
	    		 url: 'pages/DocAndGraSta/uploadFile',
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
		    $('#dlg').dialog('open').dialog('setTitle','添加博士点硕士点库');
		    $('#DocAndGraStaForm').form('reset');
	    }

	    function singleImport(){
		    //录入数据的表单提交
	    	 $('#DocAndGraStaForm').form('submit',{
				    url: 'pages/DocAndGraSta/insert',
				    data: $('#DocAndGraStaForm').serialize(),
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
			var StaName = $('#StaName').val() ;
			var StaID = $('#StaID').val() ;
			var UnitName = $('#UnitID').combobox('getText') ;
			var StaType = $('#StaType').combobox('getText') ;
			var Note = $('#Note').val() ;
			//根据数据库定义的字段的长度，对其进行判断
			if(StaName == null || StaName.length==0 || StaName.length > 100){
				$('#StaName').focus();
				$('#StaName').select();
				$('#StaNameSpan').html("<font style=\"color:red\">名称不能为空或长度不超过100</font>") ;
				return false ;
			}else{
				$('#StaNameSpan').html("") ;
			}
			
			if(StaID == null || StaID.length == 0 || StaID.length > 50){
				$('#StaID').focus();
				$('#StaID').select();
				$('#StaIDSpan').html("<font style=\"color:red\">代码不能为空或长度不超过50</font>") ;
				return false ;
			}else{
				$('#StaIDSpan').html("") ;
			}
			
			if(UnitName == null || UnitName.length == 0){
				$('#UnitNameSpan').html("<font style=\"color:red\">所属单位不能为空</font>") ;
				return false ;
			}else{
				$('#UnitNameSpan').html("") ;
			}
			
			if(StaType == null || StaType.length == 0){
				$('#StaTypeSpan').html("<font style=\"color:red\">类别不能为空</font>") ;
				return false ;
			}else{
				$('#StaTypeSpan').html("") ;
			}
				
	
			if(Note !=null && Note.length > 1000){
				$('#NoteSpan').html("<font style=\"color:red\">备注中文字数不超过500</font>") ;
				return false ;
			}else{
				$('#NoteSpan').html("") ;
			}
			return true ;
		}



	    function editDocAndGra(){
	    	var row = $('#unverfiedData').datagrid('getSelections');
	    	
	    	if(row.length != 1){
	    		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
	    		return ;
	    	}
	    	
	    	url = 'pages/DocAndGraSta/edit' ;
	    	
	    	$('#dlg').dialog('open').dialog('setTitle','添加博士点硕士点库');
	    	$('#seqNumber').val(row[0].seqNumber) ;
	        $('#StaName').val(row[0].staName);
	        $('#StaID').val(row[0].staID);
	        $('#StaType').combobox('select',row[0].staType);
	    	$('#UnitID').combobox('select',row[0].unitID) ;
	    	alert(row[0].unitID);
			$('#Note').val(row[0].note);
			alert(row[0].note);
	    }

	    function editUser(){
	    	var row = $('#dg').datagrid('getSelections');
	    	if(row.length != 1){
	    		 $.messager.alert("信息提示","没选取或者选取了多行","info");  ;
	    		return ;
	    	}
	    	alert(row[0].birthday) ;
	    	var date = formattime(row[0].birthday) ;
	    	//为文本框赋值
	    	$('#id').val(row[0].id) ;
	    	$('#username').val(row[0].username) ;
	    	$('#password').val(row[0].password) ;
	    	$('#email').val(row[0].email) ;
	    	$('#sex').val(row[0].sex) ;
	    	$('#birthday').val(date) ;
	    	
	    	
		    if (row){
			    $('#dlg').dialog('open').dialog('setTitle','本科课程库');
			    $('#fm').form('load',row);
			    url = 'updateUser';
		    }
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
				 	
				 	deleteDocAndGra(ids) ;
				 	
				 }
			});
	    }

	    function deleteDocAndGra(ids){
	    	$.ajax({ 
	    		type: "POST", 
	    		url: "pages/DocAndGraSta/deleteCoursesByIds?ids=" + ids, 
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
	    		url: "table3/loadDic", 
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
	    	var tables = "<div class=\"ftitle\">自定义查询条件</div><form method=\"post\" action=\"table3/dictorySearch\" id=\"dicsDataForm\"><table width=\"100%\" border=\"1\">" ;
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
	    	 + "url: 'table3/loadDictionary?dicId=" + val + "',"
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

</html>
