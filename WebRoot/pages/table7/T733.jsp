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
    <link rel="stylesheet" type="text/css" href="css/common.css">
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script> 
	<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
			<script type="text/javascript" src="js/commom.js"></script>
</head>
<body style="overflow-y:scroll">
	<table id="commomData" title="单位开展教学研究活动情况" class="easyui-datagrid" url="pages/T733/auditingData"
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="false" singleSelect="false" >
		<thead data-options="frozen:true">
			<tr>			
		    	<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber">编号</th>
				<th field="unitName">单位名称</th>
				<th field="unitID">单位号</th>
		     </tr>
		</thead>
		<thead>
			<tr>
				<th field="meetingDate" formatter="formattime">会议日期</th>
				<th field="meetingMemberInfo">参会人员情况</th>
				<th field="meetingNum">参会人数</th>
				<th field="meetingTheme">会议主要议题或内容</th>
				<th field="meetingResult">会议形成的主要决议或共识</th>	
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
		<div style="float: right;">
		<form action='pages/T733/dataExport?excelName=<%=URLEncoder.encode("表7-3-3各单位开展教学研究活动情况","UTF-8")%>'   method="post"  id="exportForm" enctype="multipart/form-data"  style="float: right;">
					  <select class="easyui-combobox"  id="cbYearContrast1" name="selectYear"  editable=false ></select>&nbsp;&nbsp;
						<a href='javascript:submitForm()'   style="font:12px;color: black;text-decoration:none;" >
								数据导出
						</a> &nbsp;&nbsp;&nbsp;&nbsp;		
			</form>
		
		</div>
	</div>		
	<!-- 
		 <div>
		  <form method="post" id="auditing"
				style="float: right; height: 24px;">
				<table id="test" width="520">
					<tr>
						<td>
							编号:
						</td>
						<td>
							<input id="seqNum" name="seqNum" class="easyui-box"
								style="width: 40px" />
						</td>
						<td>
							起始日期:
						</td>
						<td>
							<input id="startTime" name="startTime" class="easyui-datebox"
								style="width: 100px" />
						</td>
						<td>
							结束日期:
						</td>
						<td>
							<input id="endTime" name="endTime" class="easyui-datebox"
								style="width: 100px" />
						</td>
						<td>
							<a href="javascript:void(0)" class="easyui-linkbutton"
								iconCls="icon-search" plain="true" onclick=	reloadgrid();>查询</a>
						</td>
					</tr>
				</table>
			</form>
		</div> -->

	<!--  
	<div id="toolbar2" style="float: right;">
	<a href="pages/T733/dataExport?excelName=<%=URLEncoder.encode("表7-3-3各单位开展教学研究活动情况","UTF-8")%>"  class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 

	</div>
	
	<table id="verfiedData" title="审核通过数据" class="easyui-datagrid" url=""
		toolbar="#toolbar2" pagination="true" rownumbers="true"
		fitColumns="false" singleSelect="false">
		<thead data-options="frozen:true">
			<tr>			
		    	<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber">编号</th>
				<th field="unitName">单位名称</th>
				<th field="unitID">单位号</th>
		     </tr>
		</thead>
		<thead>
			<tr>
				<th field="meetingDate" formatter="formattime">会议日期</th>
				<th field="meetingMemberInfo">参会人员情况</th>
				<th field="meetingNum">参会人数</th>
				<th field="meetingTheme">会议主要议题或内容</th>
				<th field="meetingResult">会议形成的主要决议或共识</th>	
				<th field="note">备注</th>
			
			</tr>
		</thead>
	</table>
	-->
	<div id="dlg" class="easyui-dialog"
	style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="title1">各单位开展教学研究活动情况批量导入</h3>
		<div class="fitem" id="item1">
			<form method="post" id="batchForm" enctype="multipart/form-data">
			<select class="easyui-combobox"  id="cbYearContrast" name="selectYear"></select>
				<input type="file" name="uploadFile" id="fileToUpload" class="easyui-validatebox" size="48" style="height: 24px;" required="true" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href="pages/T733/downloadModel?saveFile=<%=URLEncoder.encode("表7-3-3各单位开展教学研究活动情况.xls","UTF-8")%>"  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
			<a href="123"></a>
		</div>
	<hr></hr>	
			
		<h3 class="title1">各单位开展教学研究活动情况逐条导入</h3>
		<form id="courseForm" method="post">
		<table>
		
		<tr>
			<td>
					<div class="fitem">
					<label>会议日期：</label> 
						<input id="seqNumber" name="eachUnitTeachResActInfo.SeqNumber" type="hidden" value="0">
						<input id="Time" type="hidden" name="eachUnitTeachResActInfo.Time" ></input>
							<input id="FillUnitID" name="eachUnitTeachResActInfo.FillUnitID" type="hidden" value="0">
							<input id="TeaUnit" name="eachUnitTeachResActInfo.UnitName" type="hidden" value="0">
							<input id="UnitID" name="eachUnitTeachResActInfo.UnitID" type="hidden" value="0">
						<input id="MeetingDate" class="easyui-datebox" style="width:80px" name="eachUnitTeachResActInfo.MeetingDate"
							editable="false"><span id="MeetingDateSpan"></span>
						
					
					</div>
				</td>
				<td class="empty"></td>		
				<td>
					<div class="fitem">
						<label>参会人员情况：</label> 
						<input id="MeetingMemberInfo" type="text" name="eachUnitTeachResActInfo.MeetingMemberInfo"
							><span id="MeetingMemberInfoSpan"></span>
					</div>
				</td>
				</tr>
			<tr>
			<td>
					<div class="fitem">
						<label>参会人数：</label> 
						<input id="MeetingNum" type="text" name="eachUnitTeachResActInfo.MeetingNum"
							><span id="MeetingNumSpan"></span>
					</div>
				</td>
				<td class="empty"></td>	
				<td>
					<div class="fitem">
						<label>会议主要议题或内容：</label> 
						<input id="MeetingTheme" type="text" name="eachUnitTeachResActInfo.MeetingTheme"
							><span id="MeetingThemeSpan"></span>
					</div>
				</td>
				</tr>
				<tr>
				<td>
					<div class="fitem">
						<label>会议形成的主要决议或共识：</label> 
						<input id="MeetingResult" type="text" name="eachUnitTeachResActInfo.MeetingResult"
							><span id="MeetingResultSpan"></span>
					</div>
				</td>
				
				</tr>
				
			<tr>
			
				<td style="valign:left" colspan="3"><label>备&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
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
         var queryParams = $('#commomData').datagrid('options').queryParams;  
         queryParams.seqNum = $('#seqNum').val(); 
         queryParams.startTime = $('#startTime').datetimebox('getValue');	         		     
    	 queryParams.endTime  = $('#endTime').datetimebox('getValue');        	 
         $("#commomData").datagrid('reload'); 
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
			 		$.messager.show({
			 			title: 'Success',
			 			msg: result.errorMsg
			 		});
			    		 $('#dlg').dialog('close'); // close the dialog
			    		 $('#commomData').datagrid('reload'); // reload the user data
	  		 	}
	  		 }
	  		 });
	   }
	    
	    function newCourse(){
	    
	       $('.title1').show();
	    	$('#item1').show();
	    	$('hr').show();
	        url="pages/T733/insert";
		    $('#dlg').dialog('open').dialog('setTitle','添加各单位开展教学研究活动情况');
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
						    $('#commomData').datagrid('reload'); 
					    }
				    }
			    });
		}

		function validate(){
			//获取文本框的值
			var meetingDate = $('#MeetingDate').datebox('getText');
			var meetingMemberInfo = $('#MeetingMemberInfo').val();
			var meetingNum = $('#MeetingNum').val();
			var meetingTheme = $('#MeetingTheme').val();
			var meetingResult = $('#MeetingResult').val();
			var note = $('#Note').val();
			//根据数据库定义的字段的长度，对其进行判断
			
			if(meetingDate == null || meetingDate.length==0){
				alert("会议日期不能为空");
				return false;
			}
			if(meetingMemberInfo == null || meetingMemberInfo.length == 0 || meetingMemberInfo.length >200){
				alert("参会人员情况不能为空或长度不超过200");
				return false;
			}
			if (!(/(^[0-9]\d*$)/.test(meetingNum)) || meetingNum == null || meetingNum.length == 0) {
			alert("参会人数不能为空且必须为整数");
			return false;
	       }
			if(meetingTheme == null || meetingTheme.length == 0 ){
		           	alert("会议主题不能为空");
			
				return false ;
			}
			if(meetingResult == null || meetingResult.length == 0){
					alert("会议结果不能为空");
				return false ;
			}
			
			if(note.length > 1000){
				alert("备注中文字数不超过500");
			
				return false ;
			}
			
			return true ;
		}
		
		function editCourse(){
	    	var row = $('#commomData').datagrid('getSelections');
	    	
	    	if(row.length != 1){
	    		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
	    		return ;
	    	}
	    	
	    	url = 'pages/T733/edit' ;
	    	
	        $('.title1').hide();
	    	$('#item1').hide();
	    	$('hr').hide();
	    	$('#dlg').dialog('open').dialog('setTitle','修改各单位开展教学研究活动情况');
	    	$('#seqNumber').val(row[0].seqNumber) ;
	    	$('#Time').val(formattime(row[0].time)) ;
	    	$('#UnitID').val(row[0].unitID) ;
	    	$('#FillUnitID').val(row[0].fillUnitID) ;
	    	$('#TeaUnit').val(row[0].unitName) ;
	    	
	    	//$('#UnitID').combobox('select', row[0].unitIDD) ;
	    	$('#MeetingMemberInfo').val(row[0].meetingMemberInfo);
	    	
	    	$('#MeetingDate').datebox('setValue',formattime(row[0].meetingDate)) ;
	    	$('#MeetingNum').val(row[0].meetingNum) ;
	    	$('#MeetingTheme').val(row[0].meetingTheme) ;
			$('#MeetingResult').val(row[0].meetingResult);
			$('#Note').val(row[0].note) ;
	    }


         function deleteByIds(){
	    	//获取选中项
			var row = $('#commomData').datagrid('getSelections');
	    	
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
						 $('#commomData').datagrid('reload') ;
					}
	    		}
	    	}).submit();
	    }
	    
	    
	        //根据用户选择的年显示相应年的数据
    $(function(){ 
		 $("#cbYearContrast1").combobox({  
	        onChange:function(newValue, oldValue){ 
		     //查询参数直接添加在queryParams中 
	         var  queryYear = newValue;
	         var queryParams = $('#commomData').datagrid('options').queryParams;  
	         queryParams.queryYear = queryYear;  
	         
	         $("#commomData").datagrid('reload'); 
	        }
	   });
    })
		
		  //提交导出表单
	    function submitForm(){
	    	  document.getElementById('exportForm').submit();
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
