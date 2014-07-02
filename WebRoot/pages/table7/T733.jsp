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
    
    <title>My JSP 'T733_EachUnitTeachResActInfo.jsp' starting page</title>
    
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
	<table id="unverfiedData" title="待审核数据域审核未通过数据" class="easyui-datagrid" style="width:100%px;height:250px" url="pages/T733/auditingData"
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="false" >
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber" width="10%">序号</th>
				<th field="unitName" width="10%">单位名称</th>
				<th field="unitID" width="10%">单位号</th>
				<th field="meetingDate" width="10%" formatter="formattime">会议日期</th>
				<th field="meetingMemberInfo" width="15%">参会人员情况</th>
				<th field="meetingNum" width="10%">参会人数</th>
				<th field="meetingTheme" width="22%">会议主要议题或内容</th>
				<th field="meetingResult" width="25%">会议形成的主要决议或共识</th>	
				<th field="note" width="20%">备注</th>
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
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="reloadgrid()">查询</a>
			</form>
		</div>
	</div>
	<div id="toolbar2">
	<a href="pages/T733/dataExport?excelName=<%=URLEncoder.encode("表7-3-3各单位开展教学研究活动情况.xls","UTF-8")%>"  class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="loadDic()">高级检索</a>
	</div>
	<table id="verfiedData" title="审核通过数据" class="easyui-datagrid" style="width:100%px;height:250px" url=""
		toolbar="#toolbar2" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="false">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber" width="10%">序号</th>
				<th field="unitName" width="10%">单位名称</th>
				<th field="unitID" width="10%">单位号</th>
				<th field="meetingDate" width="10%" formatter="formattime">会议日期</th>
				<th field="meetingMemberInfo" width="15%">参会人员情况</th>
				<th field="meetingNum" width="10%">参会人数</th>
				<th field="meetingTheme" width="22%">会议主要议题或内容</th>
				<th field="meetingResult" width="25%">会议形成的主要决议或共识</th>	
				<th field="note" width="20%">备注</th>
				<th field="time" width="10" formatter="formattime">时间</th>
			</tr>
		</thead>
	</table>
	<div id="dlg" class="easyui-dialog"
	style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<div class="ftitle">校领导听课情况批量导入</div>
		<div class="fitem">
			<form method="post" id="batchForm" enctype="multipart/form-data">
			<select class="easyui-combobox"  id="cbYearContrast" name="selectYear"></select>
				<input type="file" name="uploadFile" id="fileToUpload" class="easyui-validatebox" size="48" style="height: 24px;" required="true" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href="pages/T733/downloadModel?saveFile=<%=URLEncoder.encode("表7-3-3各单位开展教学研究活动情况.xls","UTF-8")%>"  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
			<a href="123"></a>
		</div>
		<div></div>
		<div class="ftitle">本科课程库逐条导入</div>
		
		<form id="courseForm" method="post">
		<table>
		
		<tr>
			<td>
					<div class="fitem">
						<label>单位名称：</label> 
						<input id="seqNumber" name="eachUnitTeachResActInfo.SeqNumber" type="hidden" value="0">
						<input id="UnitName" type="hidden" name="eachUnitTeachResActInfo.UnitName">
						<input id="UnitID" type="text" name="eachUnitTeachResActInfo.UnitID" 
							 class='easyui-combobox' data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment',listHeight:'auto',editable:false,
							 onSelect:function(){
							 	document.getElementById('UnitName').value=$(this).combobox('getText') ;
							 }">
							<span id="UnitNameSpan"></span>
					</div>
				<td>
					<div class="fitem">
						<label>会议日期：</label> 
						<input id="MeetingDate" class="easyui-datebox" style="width:80px" name="eachUnitTeachResActInfo.MeetingDate"
							><span id="MeetingDateSpan"></span>
					</div>
				</td>
				</tr>
			<tr>
			<td>
					<div class="fitem">
						<label>参会人员情况：</label> 
						<input id="MeetingMemberInfo" type="text" name="eachUnitTeachResActInfo.MeetingMemberInfo"
							><span id="MeetingMemberInfoSpan"></span>
					</div>
				<td>
					<div class="fitem">
						<label>参会人数：</label> 
						<input id="MeetingNum" type="text" name="eachUnitTeachResActInfo.MeetingNum"
							><span id="MeetingNumSpan"></span>
					</div>
				</td>
				</tr>
				<tr>
				<td>
					<div class="fitem">
						<label>会议主要议题或内容：</label> 
						<input id="MeetingTheme" type="text" name="eachUnitTeachResActInfo.MeetingTheme"
							><span id="MeetingThemeSpan"></span>
					</div>
				</td>
			
				<td>
					<div class="fitem">
						<label>会议形成的主要决议或共识：</label> 
						<input id="MeetingResult" type="text" name="eachUnitTeachResActInfo.MeetingResult"
							><span id="MeetingResultSpan"></span>
					</div>
				</td>
				</tr>
				
			<tr>
			
				<td style="valign:left"><label>备&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
					<textarea id="Note" name="eachUnitTeachResActInfo.Note" style="resize:none" cols="50" rows="10"></textarea>
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
	
	var currentYear = new Date().getFullYear();
    	var select = document.getElementById("cbYearContrast");
    	for (var i = 0; i <= 10; i++) {
        var theOption = document.createElement("option");
        	theOption.innerHTML = currentYear-i + "年";
        	theOption.value = currentYear-i;
        	select.appendChild(theOption);
    	}
	
	    var url;	    
	   function reloadgrid ()  { 
        //查询参数直接添加在queryParams中 
         var queryParams = $('#unverfiedData').datagrid('options').queryParams;  
         queryParams.seqNum = $('#seqNum').val(); 
         queryParams.startTime = $('#startTime').datetimebox('getValue');	         		     
    	 queryParams.endTime  = $('#endTime').datetimebox('getValue');        	 
         $("#unverfiedData").datagrid('reload'); 
    }
	   //模板导入
	 function batchImport(){
		  var fileName = $('#fileToUpload').val() ; 	
		  if(fileName == null || fileName == ""){
			  $.messager.alert('Excel批量用户导入', '请选择将要上传的文件!');      
		   		return false ;
		  }	
		 
		  var pos = fileName.lastIndexOf(".") ;
		  var suffixName = fileName.substring(pos, fileName.length) ; 	
		  if(suffixName != ".xls"){
			   $.messager.alert('Excel批量用户导入','文件类型不正确，请选择.xls文件!');   
		   		return false ;
		 }
	  	 $('#batchForm').form('submit',{
	  	 
	  		 url: 'pages/T733/uploadFile',
	  		 type: "post",
		     dataType: "json",
	  		 onSubmit: function(){
	  			 return true;
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
	    
	    function newCourse(){
	        url="pages/T733/insert";
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
			var unitName = $('#UnitID').combobox('getText');
			var meetingDate = $('#MeetingDate').datebox('getText');
			var meetingMemberInfo = $('#MeetingMemberInfo').val();
			var meetingNum = $('#MeetingNum').val();
			var meetingTheme = $('#MeetingTheme').val();
			var meetingResult = $('#MeetingResult').val();
			var note = $('#Note').val();
			//根据数据库定义的字段的长度，对其进行判断
			if(unitName == null || unitName.length==0 || unitName.length > 100){
				$('#UnitName').focus();
				$('#UnitName').select();
				$('#UnitNameSpan').html("<font style=\"color:red\">单位名称不能为空或长度不超过100</font>") ;
				return false;
			}
			if(meetingDate == null || meetingDate.length==0){
				$('#MeetingDate').focus();
				$('#MeetingDate').select();
				$('#MeetingDateSpan').html("<font style=\"color:red\">会议日期不能为空</font>") ;
				return false;
			}
			if(meetingMemberInfo == null || meetingMemberInfo.length == 0 || meetingMemberInfo.length >200){
				$('#MeetingMemberInfo').focus();
				$('#MeetingMemberInfo').select();
				$('#MeetingMemberInfoSpan').html("<font style=\"color:red\">参会人员情况不能为空或长度不超过200</font>") ;
				return false;
			}
			if(meetingNum == null || meetingNum.length==0){
				$('#MeetingNum').focus();
				$('#MeetingNum').select();
				$('#MeetingNumSpan').html("<font style=\"color:red\">不能为空</font>") ;
				return false ;
			}
			if(meetingTheme == null || meetingTheme.length == 0 ){
		
				$('#MeetingThemeSpan').html("<font style=\"color:red\">不能为空</font>") ;
				return false ;
			}
			if(meetingResult == null || meetingResult.length == 0){
				
				$('#MeetingResultSpan').html("<font style=\"color:red\">不能为空</font>") ;
				return false ;
			}
			
			if(note !=null && note.length > 1000){
				$('#NoteSpan').html("<font style=\"color:red\">备注中文字数不超过500</font>") ;
				return false ;
			}
			alert($('#UnitName').val()) ;
			return true ;
		}
		
		function editCourse(){
	    	var row = $('#unverfiedData').datagrid('getSelections');
	    	
	    	if(row.length != 1){
	    		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
	    		return ;
	    	}
	    	
	    	url = 'pages/T733/edit' ;
	    	$('#dlg').dialog('open').dialog('setTitle','添加本科教学课程库');
	    	$('#seqNumber').val(row[0].seqNumber) ;
	    	$('#UnitID').combobox('select', row[0].unitIDD) ;
	    	$('#MeetingMemberInfo').val(row[0].meetingMemberInfo);
	    	
	    	$('#MeetingDate').datebox('setValue',formattime(row[0].meetingDate)) ;
	    	$('#MeetingNum').val(row[0].meetingNum) ;
	    	$('#MeetingTheme').val(row[0].meetingTheme) ;
			$('#MeetingResult').val(row[0].meetingResult);
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
	    		url: "pages/T733/deleteByIds?ids=" + ids, 
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
