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
	<table id="unverfiedData" title="待审核数据域审核未通过数据" class="easyui-datagrid" style="height: auto;" url="pages/MainTrainBasicInfoTea/auditingData"
		toolbar="#toolbar" pagination="true" 
		 singleSelect="false" >
		<thead data-options="frozen:true">
			<tr>
				<th data-options="field:'ck',checkbox:true" >选取</th>
				<th field="seqNumber" >序号</th>
				<th field="mainClassName" >大类名称</th>
				</tr>
				</thead>
				<thead>
				<tr>
				<th field="mainClassID" >大类代码</th>
				<th field="byPassTime" >分流时间</th>
				<th field="majorNameInSch" ">包含校内专业名称</th>
				<th field="majorID" >校内专业代码</th>
				<th field="unitName" >所属单位</th>
				<th field="unitID" >单位号</th>
				<th field="note" >备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="height:auto">
		<div>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newCourse()">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editMainTrainBasicInfo()">编辑</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteByIds()">删除</a>
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
			<select class="easyui-combobox" id="cbYearContrast" name="selectYear" panelHeight="auto" style="width:80px; padding-top:5px; margin-top:10px;" editable=false></select>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-download" plain="true"  onclick="exports()">数据导出</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="loadDic()">高级检索</a>
		</form>
	</div>
	<table id="verfiedData" title="审核通过数据" class="easyui-datagrid" style="height: auto;" url="table3/verifiedData"
		toolbar="#toolbar2" pagination="true" 
		 singleSelect="false">
		<thead data-options="frozen:true">
			<tr>
				<th data-options="field:'ck',checkbox:true" >选取</th>
				<th field="SeqNumber" >序号</th>
				<th field="MainClassName" >大类名称</th>
				</tr>
				</thead>
				<thead>
				<tr>
				<th field="MainClassID" >大类代码</th>
				<th field="ByPassTime" >分流时间</th>
				<th field="MajorNameInSch" ">包含校内专业名称</th>
				<th field="MajorID" >校内专业代码</th>
				<th field="UnitName" >所属单位</th>
				<th field="UnitID" >单位号</th>
				<th field="Note" >备注</th>
			</tr>
		</thead>
	</table>
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<div class="ftitle">大类培养基本情况批量导入</div>
		<div class="fitem">
			<form id="batchForm" method="post" enctype="multipart/form-data">
				<label>批量上传：</label> 
				<select class="easyui-combobox"  id="cbYearContrast1" name="selectYear" editable=false></select>
				<input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox"
					validType="fileType['xls']" required="true" invalidMessage="请选择Excel格式的文件" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">导入</a>
				<a href='pages/MainTrainBasicInfoTea/downloadModel?saveFile=<%=URLEncoder.encode("表3-2-1大类培养基本情况表（教务处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
			<a href="123"></a>
		</div>
		<div></div>
		<div class="ftitle">大类培养基本情况逐条导入</div>
		
		<form id="mainTrainBasicInfoForm" method="post">
		<table>
			<tr>
				<td>
					<div class="fitem">
						<label>大类名称：</label> 
						<input id="MainClassName" type="text" name="t321_Bean.MainClassName"
							class="easyui-validatebox" ><span id="MainClassNameSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>大类代码：</label> 
						<input id="MainClassID" type="text" name="t321_Bean.MainClassID"
							class="easyui-validatebox" ><span id="MainClassIDSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>分流时间：</label> 
						<select class='easyui-combobox' id="ByPassTime" name="t321_Bean.ByPassTime" editable=false>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
							<option value="10">10</option>
						</select>
						<span id="ByPassTimeSpan"></span>
					</div>
				</td>
		
				<td>
					<div class="fitem">
						<label>包含校内专业名称：</label> 
					<input id="SeqNumber" name="t321_Bean.SeqNumber" type="hidden" > </input>
						<input type="hidden" name="t321_Bean.MajorNameInSch" id="MajorNameInSch"/>
						<input id="MajorID" type="text" name="t321_Bean.MajorID" 
							 class='easyui-combobox' data-options="valueField:'majorNum',textField:'majorName',url:'pages/DiMajorTwo/loadDiMajorTwo',listHeight:'auto',editable:false,
							 onSelect:function(){
							 	document.getElementById('MajorNameInSch').value=$(this).combobox('getText') ;
							 }">
						<span id="MajorNameInSchSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>所属单位：</label> 
						<!-- 下边的onselect方法是为了后台既要教学单位名称，有需要教学单位编号，而我们只有一个下拉框包含了这两条信息 -->
						<input type="hidden" name="t321_Bean.UnitName" id="UnitName"/>
						<input id="UnitID" type="text" name="t321_Bean.UnitID" 
							 class='easyui-combobox' data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment',listHeight:'auto',editable:false,
							 onSelect:function(){
							 	document.getElementById('UnitName').value=$(this).combobox('getText') ;
							 }">
						<span id="UnitNameSpan"></span>
					</div>
				</td>


			</tr>
			
			<tr>
				<td style="valign:left"><label>备&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
					<textarea id="Note" name="t321_Bean.Note" style="resize:none" cols="50" rows="10"></textarea>
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
	    		 url: 'pages/MainTrainBasicInfoTea/uploadFile',
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
	    	url='pages/MainTrainBasicInfoTea/insert',
		    $('#dlg').dialog('open').dialog('setTitle','添加大类基本情况');
		    $('#mainTrainBasicInfoForm').form('reset');
	    }

	    function singleImport(){
	    
		    //录入数据的表单提交
	    	 $('#mainTrainBasicInfoForm').form('submit',{
				    url: url,
				    data: $('#mainTrainBasicInfoForm').serialize(),
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
			var MainClassName = $('#MainClassName').val() ;
			var MainClassID = $('#MainClassID').val() ;
			var UnitName = $('#UnitID').combobox('getText') ;
			var MajorNameInSch = $('#MajorID').combobox('getText');
		
			var Note = $('#Note').val() ;
			//根据数据库定义的字段的长度，对其进行判断
			if(MainClassName == null || MainClassName.length==0 || MainClassName.length > 100){
				$.messager.alert('提示',"大类名称不能为空或长度不超过100");
				return false;
			}

			if(MainClassID == null || MainClassID.length == 0 || MainClassID.length > 50){
				$.messager.alert('提示',"大类代码不能为空或长度不超过50");
				return false;
			}

			
			if(MajorNameInSch == null || MajorNameInSch.length == 0){
				$.messager.alert('提示',"包含校内专业名称不能为空");
				return false;

			}


			

			
			if(UnitName == null || UnitName.length == 0){
				$.messager.alert('提示',"所属单位不能为空");
				return false;
			}

			
			if(Note !=null && Note.length > 1000){
				$.messager.alert('提示',"备注中文字数不超过500");
				return false;
			}
			return true ;
		}


	    function editMainTrainBasicInfo(){
	    	var row = $('#unverfiedData').datagrid('getSelections');
	    	
	    	if(row.length != 1){
	    		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
	    		return ;
	    	}
	    	
	    	url = 'pages/MainTrainBasicInfoTea/edit' ;
	    	
	    	$('#dlg').dialog('open').dialog('setTitle','添加重点学科');
	    	$('#SeqNumber').val(row[0].seqNumber) ;
	        $('#MainClassName').val(row[0].mainClassName);
	        $('#MainClassID').val(row[0].mainClassID);
	        $('#ByPassTime').combobox('select',row[0].byPassTime) ;
	        $('#MajorID').combobox('select',row[0].majorID);
	        $('#UnitID').combobox('select',row[0].unitID);

			$('#Note').val(row[0].note);
		
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
				 	
				 	deleteDiscip(ids) ;
				 	
				 }
			});
	    }

	    function deleteDiscip(ids){
	    	$.ajax({ 
	    		type: "POST", 
	    		url: "pages/MainTrainBasicInfoTea/deleteCoursesByIds?ids=" + ids, 
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

	    function exports() {
	    	var temp = encodeURI('表3-2-1大类培养基本情况表（教务处）.xls');
		    $('#exportForm').form('submit', {
		    url : "pages/MainTrainBasicInfoTea/dataExport?excelName="+temp ,
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
	
<script type="text/javascript">
    	var currentYear = new Date().getFullYear();
    	var select = document.getElementById("cbYearContrast1");
    	for (var i = 0; i <= 10; i++) {
        var theOption = document.createElement("option");
        	theOption.innerHTML = currentYear-i + "年";
        	theOption.value = currentYear-i;
        	select.appendChild(theOption);
    	}
	</script>

</html>
