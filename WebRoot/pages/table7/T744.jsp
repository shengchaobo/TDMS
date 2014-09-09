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
    
    <title>My JSP 'T744_MajBuildAssess_AC.jsp' starting page</title>
    
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
	<table id="unverfiedData" title="待审核数据域审核未通过数据" class="easyui-datagrid" url="pages/T744/auditingData"
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="false" singleSelect="false" >
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber">编号</th>
				<th field="teaUnit">教学单位</th>
				<th field="unitID">单位号</th>
		     </tr>
		</thead>
		<thead>
			<tr>


				<th field="majorName">专业名称</th>
				<th field="majorID">专业代码</th>
				<th field="degreeType">学位授予门类</th>
				<th field="leaderName">负责人姓名</th>
				<th field="teaID">教工号</th>
				<th field="setYear">设置年份</th>
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
		</div>
	</div>
	<div id="toolbar2" style="float: right;">
	<a href="pages/T744/dataExport?excelName=<%=URLEncoder.encode("表7-4-4专业建设评估","UTF-8")%>"  class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 

	</div>
	<table id="verfiedData" title="审核通过数据" class="easyui-datagrid" url=""
		toolbar="#toolbar2" pagination="true" rownumbers="true"
		fitColumns="false" singleSelect="false">
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber">编号</th>
				<th field="teaUnit">教学单位</th>
				<th field="unitID">单位号</th>
		     </tr>
		</thead>
		<thead>
			<tr>


				<th field="majorName">专业名称</th>
				<th field="majorID">专业代码</th>
				<th field="degreeType">学位授予门类</th>
				<th field="leaderName">负责人姓名</th>
				<th field="teaID">教工号</th>
				<th field="setYear">设置年份</th>
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
		<h3 class="title1">专业建设评估批量导入</h3>
		<div class="fitem" id="item1">
			<form method="post" id="batchForm" enctype="multipart/form-data">
			<select class="easyui-combobox"  id="cbYearContrast" name="selectYear"></select>
				<input type="file" name="uploadFile" id="fileToUpload" class="easyui-validatebox" size="48" style="height: 24px;" required="true" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href="pages/T744/downloadModel?saveFile=<%=URLEncoder.encode("表7-4-4专业建设评估.xls","UTF-8")%>"  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
			<a href="123"></a>
		</div>
	
	   <hr></hr>	
			
		<h3 class="title1">专业建设评估逐条导入</h3>
		<form id="courseForm" method="post">
		<table>
		<tr>
				<td>
					<div class="fitem">
						<label>教学单位：</label> 
						<input id="seqNumber" name="majBuildAssessAC.SeqNumber" type="hidden" value="0">
						<input id="TeaUnit" type="hidden" name="majBuildAssessAC.TeaUnit" >
					    <input id="UnitID" type="text" name="majBuildAssessAC.UnitID"
					     class='easyui-combobox' data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDIDepartmentAca',listHeight:'auto',editable:false,
							 onSelect:function(){
							   document.getElementById('TeaUnit').value=$(this).combobox('getText') ;
							 }">
							
							<span id="TeaUnitSpan"></span>
					</div>
				</td>
				<td class="empty"></td>	
				<td>
					<div class="fitem">
						<label>专业名称：</label> 
						<input id="MajorName" type="hidden" name="majBuildAssessAC.MajorName" >
					    <input id="MajorID" type="text" name="majBuildAssessAC.MajorID"
					     class='easyui-combobox' data-options="valueField:'majorNum',textField:'majorName',url:'pages/DiMajorTwo/loadDiMajorTwo',listHeight:'auto',editable:false,
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
						<label>学位授予门类：</label> 
					<select class='easyui-combobox' id="DegreeType" name="majBuildAssessAC.DegreeType" panelHeight="auto" editable="false">
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
						<span id="DegreeTypeSpan"></span>
					</div>
				</td>
				<td class="empty"></td>	
				<td>
					<div class="fitem">
						<label>教工号：</label> 
						<input id="TeaID" type="hidden" name="majBuildAssessAC.TeaID" >
					    <input id="LeaderName" type="text" name="majBuildAssessAC.LeaderName"
					     class='easyui-combobox' data-options="valueField:'teaName',textField:'teaId',url:'pages/T411/loadT411',listHeight:'auto',editable:true,
							 onSelect:function(){
							   document.getElementById('TeaID').value=$(this).combobox('getText') ;
							 }">
							
							<span id="TeaIDSpan"></span>
					</div>
				</td>
			
			</tr>
		
			
			<tr>
				
				<td>
					<div class="fitem">
						<label>设置年份：</label> 
						<input id="SetYear" type="text" name="majBuildAssessAC.SetYear">
						<span id="SetYearSpan"></span>
					</div>
				</td>
				<td class="empty"></td>	
				<td>
					<div class="fitem">
						<label>评估年份：</label> 
							<input id="AssessYear" type="text" name="majBuildAssessAC.AssessYear"></select>
							<span id="AssessYearSpan"></span>
					</div>
				</td>
				
				
			</tr>
			<tr>
			<td>
					<div class="fitem">
						<label>评估结果：</label> 
						<select class='easyui-combobox' id="AssessResult" name="majBuildAssessAC.AssessResult" panelHeight="auto" editable="false">
							<option value="校级优秀">校级优秀</option>
							<option value="校级良好">校级良好</option>
							<option value="校级合格">校级合格</option>
							<option value="校级不合格">校级不合格</option>
				
						</select>	
						<span id="AssessResultSpan"></span>
					</div>
				</td>
				<td class="empty"></td>	
				<td>
					<div class="fitem">
						<label>批文号：</label> 
						<input id="AppvlID" type="text" name="majBuildAssessAC.AppvlID"
							><span id="AppvlIDSpan"></span>
					</div>
				</td>
			</tr>
			
			<tr>
				<td style="valign:left" colspan="3"><label>备&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
					<textarea id="Note" name="majBuildAssessAC.Note" style="resize:none" cols="50" rows="10"></textarea>
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
	  	 
	  		 url: 'pages/T744/uploadFile',
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
			    		 $('#unverfiedData').datagrid('reload'); // reload the user data
	  		 	}
	  		 }
	  		 });
	   }
	    
	    function newCourse(){
	    
	    $('.title1').show();
	    	$('#item1').show();
	    	$('hr').show();
	        url="pages/T744/insert";
		    $('#dlg').dialog('open').dialog('setTitle','添加专业建设评估情况');
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
			var teaUnit = $('#UnitID').combobox('getText');

			var majorName = $('#MajorID').combobox('getText');

			var degreeType = $('#DegreeType').combobox('getText');
			
			var teaId = $('#LeaderName').combobox('getText');
			var teaName = $('#LeaderName').combobox('getValue');
			
			var setYear = $('#SetYear').val();
			var assessYear = $('#AssessYear').val();
		
			var assessResult = $('#AssessResult').combobox('getText') ;
			
			var appvlID = $('#AppvlID').val() ;
			var note = $('#Note').val() ;
			//根据数据库定义的字段的长度，对其进行判断
			if(teaUnit == null || teaUnit.length==0){
				alert("教学单位不能为空");
				return false ;
			}
			if(majorName == null || majorName.length == 0){
				alert("专业名称不能为空");
				return false ;
			}
			if(degreeType == null || degreeType.length == 0){
				alert("学位授予门类不能为空");
				return false ;
			}
			if (teaId == null ||  teaId == ''  || teaId.length == 0 || teaId == teaName) {
				alert("教工号不能为空或者教师库中无该教工号");
				return false;
			}
            if (!(/(^[0-9]\d*$)/.test(setYear)) || setYear == null || setYear.length == 0) {
			alert("设置年份不能为空且必须为整数");
			return false;
	       }
			if (!(/(^[0-9]\d*$)/.test(assessYear)) || assessYear == null || assessYear.length == 0) {
			alert("评估年份不能为空且必须为整数");
			return false;
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
	    	
	    	url = 'pages/T744/edit' ;
	        $('.title1').hide();
	    	$('#item1').hide();
	    	$('hr').hide();
	    	$('#dlg').dialog('open').dialog('setTitle','修改专业建设评估情况');
	    	$('#seqNumber').val(row[0].seqNumber) ;
	    	$('#UnitID').combobox('select', row[0].unitID) ;
	    	$('#MajorID').combobox('select', row[0].majorIDD) ;
	    	$('#DegreeType').combobox('select', row[0].degreeType) ;    	
			$('#LeaderName').combobox('select', row[0].leaderName) ;
			$('#SetYear').val(row[0].setYear) ;
			$('#AssessYear').val(row[0].assessYear) ;
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
	    		url: "pages/T744/deleteByIds?ids=" + ids, 
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



