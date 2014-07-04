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
    
    <title>My JSP 'T311.jsp' starting page</title>
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
	<table id="unverfiedData" title="待审核数据域审核未通过数据" class="easyui-datagrid"  style="height: auto;" url="pages/JuniorMajInfo/auditingData"
		toolbar="#toolbar" pagination="true" 
		 singleSelect="false" >
		<thead data-options="frozen:true">
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber" >序号</th>
				</tr>
				</thead>
				<thead>
				<tr>
				<th field="teaUnit" >教学单位</th>
				<th field="unitID" >单位号</th>
				<th field="majorName" >专业名称</th>
				<th field="majorID" >专业代码</th>
				<th field="majorFieldName" >专业方向名称</th>
				<th field="appvlSetTime"  formatter="formattime">批准设置时间</th>
				<th field="firstAdmisTime"  formatter="formattime">首次招生时间</th>
				<th field="majorYearLimit" >修业年限</th>
				<th field="isSepcialMajor"  formatter="booleanstr">特色专业</th>
				<th field="isKeyMajor"  formatter="booleanstr">重点专业</th>
				<th field="majorLeader" >专业带头人姓名</th>
				<th field="LIsFullTime"  formatter="booleanstr">专业带头人是否专职</th>
				<th field="majorChargeMan" >专业负责人姓名</th>
				<th field="CI sFullTime"  formatter="booleanstr">专业负责人是否专职</th>
				<th field="note" >备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="height:auto">
		<div>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newJuniorMajInfo()">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editJuniorMajInfo()">编辑</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteByIds()">删除</a>
			<a href="pages/JuniorMajInfo/dataExport?excelName=表3-3专科专业基本情况（教务处）.xls" class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a>
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
				<th data-options="field:'ck',checkbox:true" >选取</th>
				<th field="SeqNumber" >序号</th>
				</tr>
				</thead>
				<thead>
				<tr>
				<th field="TeaUnit" >教学单位</th>
				<th field="UnitID" >单位号</th>
				<th field="MajorName" >专业名称</th>
				<th field="MajorID" >专业代码</th>
				<th field="MajorFieldName" >专业方向名称</th>
				<th field="AppvlSetTime"  formatter="formattime">批准设置时间</th>
				<th field="FirstAdmisTime"  formatter="formattime">首次招生时间</th>
				<th field="MajorYearLimit" >修业年限</th>
				<th field="IsSepcialMajor" >特色专业</th>
				<th field="IsKeyMajor" >重点专业</th>
				<th field="MajorLeader" >专业带头人姓名</th>
				<th field="LIsFullTime" >专业带头人是否专职</th>
				<th field="MajorChargeMan" >专业负责人姓名</th>
				<th field="CIsFullTime" >专业负责人是否专职</th>
				<th field="Note" >备注</th>
			</tr>
		</thead>
	</table>
<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<div class="ftitle">专科专业批量导入</div>
		<div class="fitem">
			<form id="batchForm" method="post" enctype="multipart/form-data">
				<label>批量上传：</label> 
				<select class="easyui-combobox"  id="cbYearContrast" name="selectYear"></select>
				<input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox"
					validType="fileType['xls']" required="true" invalidMessage="请选择Excel格式的文件" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">导入</a>
				<a href='pages/JuniorMajInfo/downloadModel?saveFile=<%=URLEncoder.encode("表3-3专科专业基本情况（教务处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
			<a href="123"></a>
		</div>
		<div></div>
		<div class="ftitle">专科专业逐条导入</div>
		
		<form id="juniorMajInfoForm" method="post">
		<table>
			<tr>
			<td>
					<div class="fitem">
						<label>教学单位：</label> 
						<input id="seqNumber" name="t33_Bean.SeqNumber" type="hidden" > </input>
						<input type="hidden" name="t33_Bean.TeaUnit" id="TeaUnit"/>
						<input id="UnitID" type="text" name="t33_Bean.UnitID" 
							 class='easyui-combobox' data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment',listHeight:'auto',editable:false,
							 onSelect:function(){
							 	document.getElementById('TeaUnit').value=$(this).combobox('getText') ;
							 }">
						<span id="TeaUnitSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>专业名称：</label> 
						<input type="hidden" name="t33_Bean.MajorName" id="MajorName"/>
						<input id="MajorID" type="text" name="t33_Bean.MajorID" 
							 class='easyui-combobox' data-options="valueField:'majorNum',textField:'majorName',url:'pages/DiMajorOne/loadDiMajorOne',listHeight:'auto',editable:false,
							 onSelect:function(){
							 	document.getElementById('MajorName').value=$(this).combobox('getText') ;
							 }">
						<span id="MajorNameSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
			<td>
					<div class="fitem">
						<label>专业方向名称：</label> 
						<input id="MajorFieldName" type="text" name="t33_Bean.MajorFieldName"
							class="easyui-validatebox" ><span id="MajorFieldNameSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>批准设置时间：</label> 
						<input id="AppvlSetTime" name="t33_Bean.AppvlSetTime"
							class="easyui-datebox" editable="false" style="width:80px">
							<span id="AppvlSetTimeSpan"></span>
					</div>
				</td>
				
			</tr>
			
		<tr>
			<td>
					<div class="fitem">
						<label>首次招生时间：</label> 
						<input id="FirstAdmisTime" name="t33_Bean.FirstAdmisTime"
							class="easyui-datebox" editable="false" style="width:80px">
							<span id="FirstAdmisTimeSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>修业年限：</label> 
						<input id="MajorYearLimit" type="text" name="t33_Bean.MajorYearLimit"
							class="easyui-validatebox" ><span id="MajorYearLimitSpan"></span>
					</div>
				</td>
				
			</tr>
			<tr>
			<td>
					<div class="fitem">
						<label>是否特色专业：</label> 
						<select class='easyui-combobox' id="IsSepcialMajor" name="t33_Bean.IsSepcialMajor">
							<option value="false">否</option>
							<option value="true">是</option>
						</select>
						<span id="IsSepcialMajorSpan"></span>
					</div>
				</td>
			<td>
					<div class="fitem">
						<label>是否重点专业：</label> 
						<select class='easyui-combobox' id="IsKeyMajor" name="t33_Bean.IsKeyMajor">
							<option value="false">否</option>
							<option value="true">是</option>
						</select>
						<span id="IsKeyMajorSpan"></span>
					</div>
				</td>
				</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>专业带头人姓名：</label> 
						<input id="MajorLeader" type="text" name="t33_Bean.MajorLeader"
							class="easyui-validatebox" ><span id="MajorLeaderSpan"></span>
					</div>
				</td>	
				<td>
					<div class="fitem">
						<label>专业带头人是否专职：</label> 
						<select class='easyui-combobox' id="LIsFullTime" name="t33_Bean.LIsFullTime">
							<option value="true">是</option>
							<option value="false">否</option>
						</select>
						<span id="LIsFullTimeSpan"></span>
					</div>
				</td>			
				</tr>

			<tr>
				<td>
					<div class="fitem">
						<label>专业负责人姓名：</label> 
						<input id="MajorChargeMan" type="text" name="t33_Bean.MajorChargeMan"
							class="easyui-validatebox" ><span id="MajorChargeManSpan"></span>
					</div>
				</td>	
				<td>
					<div class="fitem">
						<label>专业负责人是否专职：</label> 
						<select class='easyui-combobox' id="CIsFullTime" name="t33_Bean.CIsFullTime">
							<option value="true">是</option>
							<option value="false">否</option>
						</select>
						<span id="CIsFullTimeSpan"></span>
					</div>
				</td>			
				</tr>


			<tr>
				<td style="valign:left"><label>备&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
					<textarea id="Note" name="t33_Bean.Note" style="resize:none" cols="50" rows="10"></textarea>
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

	    
	    function fillSelect(){ 
            var obj=document.getElementById( "SetTime"); 
            debugger;
             for(var i=1952;i <=2014;i++){ 
            var op=new Option(i,i); 
           obj.add(op); 
               } 
              } 
              
              
	    function batchImport(){
	    	 $('#batchForm').form('submit',{
	    		 url: 'pages/JuniorMajInfo/uploadFile',
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
	    
	    function newJuniorMajInfo(){
		    $('#dlg').dialog('open').dialog('setTitle','添加专科专业');
		    $('#juniorMajInfoForm').form('reset');
	    }

	    function singleImport(){
		    //录入数据的表单提交
	    	 $('#juniorMajInfoForm').form('submit',{
				    url: 'pages/JuniorMajInfo/insert',
				    data: $('#juniorMajInfoForm').serialize(),
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
			var TeaUnit =$('#UnitID').combobox('getText')  ;
			var MajorName = $('#MajorID').combobox('getText') ;
			var Note = $('#Note').val() ;
	
			
			if(TeaUnit == null || TeaUnit.length == 0){
				$('#TeaUnitSpan').html("<font style=\"color:red\">教学单位不能为空</font>") ;
				return false ;
			}else{
				$('#TeaUnitSpan').html("") ;
			}
			
			if(MajorName == null || MajorName.length == 0){
				$('#MajorNameSpan').html("<font style=\"color:red\">教学单位不能为空</font>") ;
				return false ;
			}else{
				$('#MajorNameSpan').html("") ;
			}
	
			

			
			if(Note !=null && Note.length > 1000){
				$('#NoteSpan').html("<font style=\"color:red\">备注中文字数不超过500</font>") ;
				return false ;
			}else{
				$('#NoteSpan').html("") ;
			}
			return true ;
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
				 	
				 	deletePostDocSta(ids) ;
				 	
				 }
			});
	    }

	    function deletePostDocSta(ids){
	    	$.ajax({ 
	    		type: "POST", 
	    		url: "pages/JuniorMajInfo/deleteCoursesByIds?ids=" + ids, 
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

	    function editJuniorMajInfo(){
	    	var row = $('#unverfiedData').datagrid('getSelections');
	    	
	    	if(row.length != 1){
	    		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
	    		return ;
	    	}
	    	
	    	url = 'pages/JuniorMajInfo/edit' ;
	    	
	    	$('#dlg').dialog('open').dialog('setTitle','添加专科专业');
	    	$('#seqNumber').val(row[0].seqNumber) ;
	    	$('#UnitID').combobox('select',row[0].unitID) ;
	    	$('#MajorID').combobox('select',row[0].majorID) ;
	    	$('#MajorFieldName').val(row[0].majorFieldName) ;	
	    	$('#AppvlSetTime').datebox('setValue',formattime(row[0].appvlSetTime)) ;
	    	$('#FirstAdmisTime').datebox('setValue',formattime(row[0].firstAdmisTime)) ;	 	    	    	
	        $('#MajorYearLimit').val(row[0].majorYearLimit);
	    	$('#IsSepcialMajor').combobox('select',row[0].isSepcialMajor) ;
	    	$('#IsKeyMajor').combobox('select',row[0].isKeyMajor) ;	   
			$('#MajorLeader').val(row[0].majorLeader);
	    	$('#LIsFullTime').combobox('select',row[0].LIsFullTime) ;					     
			$('#MajorChargeMan').val(row[0].majorChargeMan);
	    	$('#CIsFullTime').combobox('select',row[0].CIsFullTime) ;		
			$('#Note').val(row[0].note);
	
	    }

	    function exports() {
	    	var temp = encodeURI('表3-3专科专业基本情况（教务处）.xls');
		    $('#exportForm').form('submit', {
		    url : "pages/JuniorMajInfo/dataExport?excelName="+temp ,
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

