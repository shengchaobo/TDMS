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
    
    <title>My JSP 'T742_TeachLevelAssess_AC.jsp' starting page</title>
    
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
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script> 
	<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>

</head>
<body style="overflow-y:scroll">
	<table id="unverfiedData" title="待审核数据域审核未通过数据" class="easyui-datagrid" url="pages/T742/auditingData"
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="false" singleSelect="false" >
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber">编号</th>
				<th field="teaName">教师姓名</th>
				<th field="teaID">教工号</th>
		     </tr>
		</thead>
		<thead>
			<tr>
				<th field="teaUnit">所属教学单位</th>
				<th field="unitID">单位号</th>
				<th field="assessCS">参评课程</th>
				<th field="CSID">课程编号</th>
				<th field="CSType">课程类别</th>
				<th field="assessYear">评估年份</th>
				<th field="assessResult">评估结果</th>
				<th field="appvlID">批文号</th>
				<th field="note">备注</th>
				
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="height:auto">
		<div style="float: left;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newCourse()">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editCourse()">编辑</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteByIds()">删除</a>
		</div>
		 <div>
		  <form id="auditing" method="post" style="float: right;height: 26px;">
		                       序号: <input id="seqNum" name="seqNum" class="easyui-numberbox" style="width:80px"/>
				日期 起始: <input id="startTime" name="startTime" class="easyui-datebox" style="width:80px"/>
				结束: <input id="endTime" name="endTime" class="easyui-datebox" style="width:80px"/>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="reloadgrid()">查询</a>
			</form>
		</div>
	</div>
	<div id="toolbar2" style="float: right;">
	<a href="pages/T742/dataExport?excelName=<%=URLEncoder.encode("表7-4-2教师教学水平评估.xls","UTF-8")%>"  class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="loadDic()">高级检索</a>
	</div>
	<table id="verfiedData" title="审核通过数据" class="easyui-datagrid" url=""
		toolbar="#toolbar2" pagination="true" rownumbers="true"
		fitColumns="false" singleSelect="false">
	<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber">编号</th>
				<th field="teaName">教师姓名</th>
				<th field="teaID">教工号</th>
		     </tr>
		</thead>
		<thead>
			<tr>
				<th field="teaUnit">所属教学单位</th>
				<th field="unitID">单位号</th>
				<th field="assessCS">参评课程</th>
				<th field="CSID">课程编号</th>
				<th field="CSType">课程类别</th>
				<th field="assessYear">评估年份</th>
				<th field="assessResult">评估结果</th>
				<th field="appvlID">批文号</th>
				<th field="note">备注</th>
				
			</tr>
		</thead>
	</table>
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="title1">教师教学水平评估情况批量导入</h3>
		<div class="fitem" id="item1">
			<form method="post" id="batchForm" enctype="multipart/form-data">
			<select class="easyui-combobox"  id="cbYearContrast" name="selectYear"></select>
				<input type="file" name="uploadFile" id="fileToUpload" class="easyui-validatebox" size="48" style="height: 24px;" required="true" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href="pages/T742/downloadModel?saveFile=<%=URLEncoder.encode("表7-4-2教师教学水平评估.xls","UTF-8")%>"  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
			<a href="123"></a>
		</div>
	    <hr></hr>	
			
		<h3 class="title1">教师教学水平评估情况逐条导入</h3>
		<form id="courseForm" method="post">
		<table>
		
			<tr>
				<td>
					<div class="fitem">
						<label>教工号：</label> 
						<input id="seqNumber" name="teachLevelAssessAC.SeqNumber" type="hidden" value="0">
						<input id="TeaID" type="hidden" name="teachLevelAssessAC.TeaID" >
					    <input id="TeaName" type="text" name="teachLevelAssessAC.TeaName"
					     class='easyui-combobox' data-options="valueField:'teaName',textField:'teaId',url:'pages/T411/loadT411',listHeight:'auto',editable:true,
							 onSelect:function(){
							   document.getElementById('TeaID').value=$(this).combobox('getText') ;
							 }">
							
							<span id="TeaIDSpan"></span>
					</div>
				</td>
				<td class="empty"></td>	
				<td>
					<div class="fitem">
						<label>所属教学单位：</label> 
						<input id="TeaUnit" type="hidden" name="teachLevelAssessAC.TeaUnit" >
					    <input id="UnitID" type="text" name="teachLevelAssessAC.UnitID"
					     class='easyui-combobox' data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment',listHeight:'auto',editable:false,
							 onSelect:function(){
							   document.getElementById('TeaUnit').value=$(this).combobox('getText') ;
							 }">
							
							<span id="TeaUnitSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>参评课程：</label> 
						<input id="AssessCS" type="text" name="teachLevelAssessAC.AssessCS"
							><span id="AssessCSSpan"></span>
					</div>
					
				</td>
				<td class="empty"></td>	
				<td>
					<div class="fitem">
						<label>课程编号：</label> 
						<input id="CSID" type="text" name="teachLevelAssessAC.CSID"
							><span id="CSIDSpan"></span>
					</div>
					
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>课程类别：</label> 
						<input class='easyui-combobox' id="CSType" name="teachLevelAssessAC.CSType" 
							data-options="valueField:'indexId',textField:'courseCategories',url:'pages/DiCourseCategories/loadDiCourseCategories',listHeight:'auto',editable:false">
						<span id="CSTypeSpan"></span>
					</div>
				</td>
				<td class="empty"></td>	
				<td>
					<div class="fitem">
						<label>评估年份：</label> 
					<select class="easyui-combobox"  id="AssessYear" name="teachLevelAssessAC.AssessYear"></select>
							<span id="AssessYearSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
			<td>
					<div class="fitem">
						<label>评估结果：</label> 
						<select class='easyui-combobox' id="AssessResult" name="teachLevelAssessAC.AssessResult" panelHeight="auto">
							<option value="校级优秀">校级优秀</option>
							<option value="校级良好">校级良好</option>
							<option value="系级优秀">系级优秀</option>
							<option value="系级良好">系级良好</option>
							<option value="合格">合格</option>
							<option value="不合格">不合格</option>
				
						</select>	
						<span id="AssessResultSpan"></span>
					</div>
				</td>
			<td class="empty"></td>	
				<td>
					<div class="fitem">
						<label>批文号：</label> 
						<input id="AppvlID" type="text" name="teachLevelAssessAC.AppvlID"
							><span id="AppvlIDSpan"></span>
					</div>
				</td>
			</tr>
			
			<tr>
				<td style="valign:left" colspan="3"><label>备&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
					<textarea id="Note" name="teachLevelAssessAC.Note" style="resize:none" cols="50" rows="10"></textarea>
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
	var select = document.getElementById("AssessYear");
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
	  	 
	  		 url: 'pages/T742/uploadFile',
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
	       $('.title1').show();
	    	$('#item1').show();
	    	$('hr').show();
	    
	        url="pages/T742/insert";
		    $('#dlg').dialog('open').dialog('setTitle','添加教师教学水平评估情况');
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
			var teaID = $('#TeaName').combobox('getText');
			
			var teaUnit = $('#UnitID').combobox('getText');
			
			var assessCS = $('#AssessCS').val() ;
			
			var cSID = $('#CSID').val() ;
		
			var cSType = $('#CSType').combobox('getText') ;
			
			var assessYear = $('#AssessYear').combobox('getText') ;
		
			var assessResult = $('#AssessResult').combobox('getText') ;
			
			var appvlID = $('#AppvlID').val() ;
			var note = $('#Note').val() ;
			//根据数据库定义的字段的长度，对其进行判断
			if(teaID == null || teaID.length==0){
				
				alert("教工号不能为空");
				return false ;
			}
		
			if(assessCS == null || assessCS.length == 0){

			 alert("参评课程不能为空");
				return false ;
			}
			if(cSID == null || cSID.length == 0){
				 alert("课程编号不能为空");
				return false ;
			}
			if(cSType == null || cSType.length == 0){
			alert("课程类别不能为空");
				return false ;
			}
			if(assessYear == null || assessYear.length == 0){
			 alert("评估年份不能为空");
				return false ;
			}
			if(assessResult == null || assessResult.length == 0){
				 alert("评估结果不能为空");
				return false ;
			}
			if(appvlID == null || appvlID.length == 0){
			 alert("批文号不能为空");
			
				return false ;
			}
			if(note !=null && note.length > 1000){
			   alert("备注中文字数不超过500");
				return false ;
			}
			
			return true ; 
		}
		
		function editCourse(){
	    	var row = $('#unverfiedData').datagrid('getSelections');
	    	
	    	if(row.length != 1){
	    		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
	    		return ;
	    	}
	    	
	    	url = 'pages/T742/edit' ;
	        $('.title1').hide();
	    	$('#item1').hide();
	    	$('hr').hide();
	    	
	    	$('#dlg').dialog('open').dialog('setTitle','修改教师教学水平评估情况');
	    	$('#seqNumber').val(row[0].seqNumber) ;
	    	$('#TeaName').combobox('select', row[0].teaName) ;
	    	$('#UnitID').combobox('select', row[0].unitID) ;
	    	$('#AssessCS').val(row[0].assessCS) ;
	    	$('#CSID').val(row[0].CSID) ;
	    	$('#CSType').combobox('select', row[0].CSTypeID) ;
			$('#AssessYear').combobox('select', row[0].assessYear) ;
			$('#AppvlID').val(row[0].appvlID) ;
			$('#AssessResult').combobox('select', row[0].assessResult) ;
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
	    		url: "pages/T742/deleteByIds?ids=" + ids, 
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


