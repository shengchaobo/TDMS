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
    
    <title>My JSP 'T731_SchLeadInClassInfo_Tea.jsp' starting page</title>
    
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
	<table id="commomData" title="校领导听课情况" class="easyui-datagrid" url="pages/T731/auditingData"
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="false" singleSelect="false" >
		<thead data-options="frozen:true">
			<tr>			
		        <th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber" >编号</th>
				<th field="attendClassTerm" >听课学期</th>
				<th field="leaderName" >校领导姓名</th>
		     </tr>
		</thead>
		<thead>
			<tr>
				<th field="leaderID">校领导教工号</th>
				<th field="attendClassTime" formatter="formattime">听课日期</th>
				<th field="lectureTea">授课教师</th>
				<th field="lectureTeaID" >授课教教工号</th>
				<th field="lectureCS" >听课课程</th>
				<th field="CSID">课程编号</th>
				<th field="setCSUnit">开课单位</th>
				<th field="unitID">单位号</th>
				<th field="lectureClass">上课班级</th>
				<th field="evaluate">综合评价</th>	
				<th field="note">备注</th>
			
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="height:auto">
		<div style="float: left;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newCourse()">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editCourse()">编辑</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteByIds()">删除</a>
			<a href='javascript:dataExport()'  class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 		
		</div>
			<form method="post" id="searchForm"
				style="float: right; height: 24px;">
				<table id="test" width="550">
					<tr>
						<td>
							教工号:
						</td>
						<td>
							<input id="TeaID" name="TeaID" class="easyui-box"
								style="width: 100px" />
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
		
	</div>


	
	
	<div id="dlg1" class="easyui-dialog" style="width:300px;height:150px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg1-buttons">
		<form action='pages/T731/dataExport?excelName=<%=URLEncoder.encode("表7-3-1校领导听课情况","UTF-8")%>'   method="post"  id="exportForm" enctype="multipart/form-data">
			  <select class="easyui-combobox"  id="cbYearContrast" name="selectYear"  editable=false ></select>&nbsp;&nbsp;	
	   </form>	
    </div>
	
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="title1">校领导听课情况批量导入</h3>
		<div class="fitem" id="item1">
			<form method="post" id="batchForm" enctype="multipart/form-data">
			<select class="easyui-combobox"  id="cbYearContrast" name="selectYear"></select>
				<input type="file" name="uploadFile" id="fileToUpload" class="easyui-validatebox" size="48" style="height: 24px;" required="true" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href="pages/T731/downloadModel?saveFile=<%=URLEncoder.encode("表7-3-1校领导听课情况.xls","UTF-8")%>"  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
			<a href="123"></a>
		</div>
    <hr></hr>	
			
		<h3 class="title1">校领导听课情况逐条导入</h3>
		<form id="t731Form" method="post">
		<table>
		
		<tr>
			<td>
					<div class="fitem">
						<label>听课学期：</label> 
						<input id="seqNumber" name="schleadInClass.SeqNumber" type="hidden" value="0">
						<input id="Time" type="hidden" name="schleadInClass.Time"></input>
						<input id="AttendClassTerm" type="text" name="schleadInClass.AttendClassTerm"
							><span id="AttendClassTermSpan"></span>
					</div>
				</td>
					<td class="empty"></td>	
					<td>
					<div class="fitem">
						<label>听课日期：</label> 
						<input id="AttendClassTime" class="easyui-datebox" style="width:80px" name="schleadInClass.AttendClassTime"
							editable="false"><span id="AttendClassTimeSpan"></span>
					</div>
				</td>
					
					
				
				
				</tr>
				
				<tr>
				<td>
					<div class="fitem">
						<label>校领导教工号：</label> 
						<input id="LeaderID" type="hidden" name="schleadInClass.LeaderID" >
					    <input id="LeaderName" type="text" name="schleadInClass.LeaderName"
					     class='easyui-combobox' data-options="valueField:'teaName',textField:'teaId',url:'pages/T411/loadT411',listHeight:'auto',editable:true,
							 onSelect:function(){
							   document.getElementById('LeaderID').value=$(this).combobox('getText') ;
							   document.getElementById('name').value=$(this).combobox('getValue') ;
							 }">
							
							<span id="LeaderIDSpan"></span>
					</div>
				</td>
				<td class="empty"></td>	
				<td>
					<div class="fitem">
					<label>校领导姓名：</label> 
					<input id="name"  name="techName"  readonly="true" style="color:grey">
					</div>
				</td>
				</tr>
			<tr>
			<td>
					<div class="fitem">
						<label>授课教师教工号：</label> 
						<input id="LectureTeaID" type="hidden" name="schleadInClass.LectureTeaID" >
					    <input id="LectureTea" type="text" name="schleadInClass.LectureTea"
					     class='easyui-combobox' data-options="valueField:'teaName',textField:'teaId',url:'pages/T411/loadT411',listHeight:'auto',editable:true,
							 onSelect:function(){
							   document.getElementById('LectureTeaID').value=$(this).combobox('getText') ;
							     document.getElementById('lecName').value=$(this).combobox('getValue') ;
							 }">
							
							<span id="LeaderIDSpan"></span>
					</div>
				</td>
				<td class="empty"></td>	
				<td>
					<div class="fitem">
					<label>授课教师姓名：</label> 
					<input id="lecName"  name="lecName"  readonly="true" style="color:grey">
					</div>
				</td>
				</tr>
				<tr>
				<td>
					<div class="fitem">
						<label>听课课程：</label> 
						<input id="LectureCS" type="text" name="schleadInClass.LectureCS"
							><span id="LectureCSSpan"></span>
					</div>
				</td>
			<td class="empty"></td>	
				<td>
					<div class="fitem">
						<label>课程编号：</label> 
						<input id="CSID" type="text" name="schleadInClass.CSID"
							><span id="CSIDSpan"></span>
					</div>
				</td>
				</tr>
				<tr>
				<td>
					<div class="fitem">
						<label>开课单位：</label> 
						<input id="SetCSUnit" type="hidden" name="schleadInClass.SetCSUnit">
						<input id="UnitID" type="text" name="schleadInClass.UnitID" 
							 class='easyui-combobox' data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDIDepartmentAca',listHeight:'auto',editable:false,
							 onSelect:function(){
							 	document.getElementById('SetCSUnit').value=$(this).combobox('getText') ;
							 }">
							<span id="TeaUnitSpan"></span>
					</div>
				</td>
			<td class="empty"></td>	
				<td>
					<div class="fitem">
						<label>上课班级：</label> 
						<input id="LectureClass" type="text" name="schleadInClass.LectureClass"
							><span id="LectureClassSpan"></span>
					</div>
				</td>
				</tr>
				<tr>
				<td>
					<div class="fitem">
						<label>综合评价：</label> 
						<select class='easyui-combobox' id="Evaluate" name="schleadInClass.Evaluate" panelHeight="auto" editable="false">
						<option value="优">优</option>
						<option value="良">良</option>
						<option value="中">中</option>
						<option value="合格">合格</option>
						<option value="不合格">不合格</option>
						</select>
						<span id="EvaluateSpan"></span>
					</div>
				</td>		
			</tr>
			<tr>
				<td style="valign:left" colspan="3"><label>备&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
					<textarea id="Note" name="schleadInClass.Note" style="resize:none" cols="50" rows="10"></textarea>
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
	
	<div id="dlg1-buttons"  >
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="submitForm()">确定</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg1').dialog('close')">取消</a>
	</div>
</body>
	<script type="text/javascript">
	
	
		//var currentYear = new Date().getFullYear();
    	//var select = document.getElementById("cbYearContrast");
    	//for (var i = 0; i <= 10; i++) {
       // var theOption = document.createElement("option");
        //	theOption.innerHTML = currentYear-i + "年";
        //	theOption.value = currentYear-i;
        //	select.appendChild(theOption);
    	//}
	    var url;
	    function reloadgrid ()  { 
        //查询参数直接添加在queryParams中 
         var queryParams = $('#commomData').datagrid('options').queryParams;  
         queryParams.TeaID = $('#TeaID').val(); 
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
	  	 
	  		 url: 'pages/T731/uploadFile',
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
	        url="pages/T731/insert";
		    $('#dlg').dialog('open').dialog('setTitle','添加校领导听课情况');
		    $('#t731Form').form('reset');
	    }

	    function singleImport(){
		    //录入数据的表单提交
	    	 $('#t731Form').form('submit',{
				    url: url,
				    data: $('#t731Form').serialize(),
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
			var attendClassTerm = $('#AttendClassTerm').val();
			var leaderId = $('#LeaderName').combobox('getText');
			var leaderName = $('#LeaderName').combobox('getValue');
				
			var attendClassTime = $('#AttendClassTime').datebox('getText');
			var lectureTeaId = $('#LectureTea').combobox('getText');
			var lectureTeaName = $('#LectureTea').combobox('getValue');
			var lectureCS = $('#LectureCS').val();
			var cSID = $('#CSID').val();
			var setCSUnit = $('#UnitID').combobox('getText');
			var lectureClass = $('#LectureClass').val();
			var evaluate = $('#Evaluate').combobox('getText');
			var note = $('#Note').val();
			//根据数据库定义的字段的长度，对其进行判断
			if(attendClassTerm == null || attendClassTerm.length==0){
			    alert("听课学期不能为空");
				return false;
			}
			
			if(attendClassTime == null || attendClassTime.length == 0){
				alert("听课日期不能为空");
				return false;
			}
			if (leaderId == null ||  leaderId == ''  || leaderId.length == 0 || leaderId == leaderName) {
				alert("校领导不能为空或者教师库中无该教工号");
				return false;
			}
			
			
			
			if (lectureTeaId == null ||  lectureTeaId == ''  || lectureTeaId.length == 0 || lectureTeaId == lectureTeaName) {
				alert("教工号不能为空或者教师库中无该教工号");
				return false;
			}
			
			if(lectureCS == null || lectureCS.length == 0 ){
			  alert("听课课程不能为空");	
				return false ;
			}
			if(cSID == null || cSID.length == 0){
			 alert("课程编号不能为空");		
				return false ;
			}
			if(setCSUnit == null || setCSUnit.length == 0){
			 alert("开课单位不能为空");	
				return false ;
			}
			if(lectureClass == null || lectureClass.length == 0){
			 alert("上课班级不能为空");	
				return false ;
			}
			if(note !=null && note.length > 1000){
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
	    	
	    	url = 'pages/T731/edit' ;
	    		
	    	 $('.title1').hide();
	    	$('#item1').hide();
	    	$('hr').hide();
	    	
	    	$('#dlg').dialog('open').dialog('setTitle','修改校领导听课情况');
	    
	    	$('#seqNumber').val(row[0].seqNumber) ;
	    	$('#Time').val(formattime(row[0].time)) ;
	    	$('#AttendClassTerm').val(row[0].attendClassTerm) ;
	    	$('#LeaderName').combobox('select', row[0].leaderIDD) ;
	    	$('#AttendClassTime').datebox('setValue',formattime(row[0].attendClassTime)) ;
	    	$('#LectureTea').combobox('select', row[0].lectureTea) ;
	    	$('#LectureCS').val(row[0].lectureCS) ;
	    	$('#CSID').val(row[0].CSID) ;
	    	
	    	$('#UnitID').combobox('select', row[0].unitID) ;
			$('#LectureClass').val(row[0].lectureClass);
			$('#Evaluate').combobox('select', row[0].evaluate) ;
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
	    		url: "pages/T731/deleteByIds?ids=" + ids, 
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

    
	//弹出添加的界面
	function dataExport() {
		$('#dlg1').dialog('open').dialog('setTitle', '请选择要导出的时间年');
	}
	
    //提交导出表单
    function submitForm(){
    	  document.getElementById('exportForm').submit();
    	  $('#dlg1').dialog('close'); // close the dialog
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

