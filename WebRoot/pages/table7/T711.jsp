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
    
    <title>My JSP 'T711_TeaManagerAwardInfo_TeaTea.jsp' starting page</title>
    
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
	  label {
	    width: 10em;
	    float: left;
	}
	.empty{
		width: 4em;
	}
	</style>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script> 
	<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body style="overflow-y:scroll">
	<table id="unverfiedData" title="待审核数据域审核未通过数据" class="easyui-datagrid" style="width:100%px;height:250px" url="pages/T711/auditingData"
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="false" >
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber" width="10%">编号</th>
				<th field="teaUnit" width="10%">教学单位</th>
				<th field="unitID" width="10%">单位号</th>
				<th field="name" width="10%">姓名</th>
				<th field="teaID" width="10%">教工号</th>
				<th field="awardName" width="10%">奖励名称</th>
				<th field="awardLevel" width="10%">级别</th>
				<th field="awardRank" width="10%">等级</th>
				<th field="awardTime" width="10%" formatter="formattime">获奖时间</th>
				<th field="awardFromUnit" width="10%">授予单位</th>
				<th field="appvlID" width="10%">批文号</th>
				<th field="joinTeaNum" width="20%">合作教师人数</th>
				<th field="otherJoinTeaInfo" width="20%">其他合作教师</th>	
				<th field="note" width="20%">备注</th>
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
		                       序号: <input id="seqNum" name="seqNum" class="easyui-numberbox" style="width:70px"/>
				日期 起始: <input id="startTime" name="startTime" class="easyui-datebox" style="width:80px"/>
				结束: <input id="endTime" name="endTime" class="easyui-datebox" style="width:80px"/>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="reloadgrid()">查询</a>
			</form>
		</div>
	</div>
	<div id="toolbar2" style="float: right;">
	
		<a href="pages/T711/dataExport?excelName=<%=URLEncoder.encode("表7-1-1教学管理人员获得教学成果奖情况.xls","UTF-8")%>"  class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="">高级检索</a>
	</div>
	<table id="verfiedData" title="审核通过数据" class="easyui-datagrid" style="width:100%px;height:250px" url=""
		toolbar="#toolbar2" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="false">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber" width="10%">编号</th>
				<th field="teaUnit" width="10%">教学单位</th>
				<th field="unitID" width="10%">单位号</th>
				<th field="name" width="10%">姓名</th>
				<th field="teaID" width="10%">教工号</th>
				<th field="awardName" width="10%">奖励名称</th>
				<th field="awardLevel" width="10%">级别</th>
				<th field="awardRank" width="10%">等级</th>
				<th field="awardTime" width="10%" formatter="formattime">获奖时间</th>
				<th field="awardFromUnit" width="10%">授予单位</th>
				<th field="appvlID" width="10%">批文号</th>
				<th field="joinTeaNum" width="20%">合作教师人数</th>
				<th field="otherJoinTeaInfo" width="20%">其他合作教师</th>	
				<th field="note" width="20%">备注</th>		
			</tr>
		</thead>
	</table>
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="title1">教学管理人员获得教学成果奖情况批量导入</h3>
		<div class="fitem"  id="item1">
			<form method="post" id="batchForm" enctype="multipart/form-data">
			<select class="easyui-combobox"  id="cbYearContrast" name="selectYear"></select>
				<input type="file" name="uploadFile" id="fileToUpload" class="easyui-validatebox" size="48" style="height: 24px;" required="true" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href="pages/T711/downloadModel?saveFile=<%=URLEncoder.encode("表7-1-1教学管理人员获得教学成果奖情况.xls","UTF-8")%>"  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
			<a href="123"></a>
		</div>
		<hr style="width: 100%; height: 5px; color: blue;"></hr>	
			
		<h3 class="title1">教学管理人员获得教学成果奖情况逐条导入</h3>
		
		<form id="t711Form" method="post">
		<table>
		
		<tr>
			<td>
			<input id="seqNumber" name="t711_Bean.SeqNumber" type="hidden" value="0">
					<div class="fitem">
						<label>教学单位：</label> 
						
						<input id="TeaUnit" type="hidden" name="t711_Bean.TeaUnit">
						<input id="UnitID" name="t711_Bean.UnitID" 
							 class='easyui-combobox' data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment',listHeight:'auto',editable:false,
							 onSelect:function(){
							 	document.getElementById('TeaUnit').value=$(this).combobox('getText') ;
							 }">
							<span id="TeaUnitSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>姓名：</label> 
						<input id="Name" type="text" name="t711_Bean.Name"
							><span id="NameSpan"></span>
					</div>
				</td>
				</tr>
			<tr>
			<td>
					<div class="fitem">
						<label>教工号：</label> 
						<input id="TeaID" type="text" name="t711_Bean.TeaID"
							><span id="TeaIDSpan"></span>
					</div>
					</td>
					<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>奖励名称：</label> 
						<input id="AwardName" type="text" name="t711_Bean.AwardName"
							><span id="AwardNameSpan"></span>
					</div>
				</td>
				</tr>
				<tr>
				<td>
					<div class="fitem">
						<label>级别：</label> 
						<input class='easyui-combobox' id="AwardLevel" name="t711_Bean.AwardLevel"
							data-options="valueField:'indexId',textField:'awardLevel',url:'pages/DiAwardLevel/loadDiAwardLevel',listHeight:'auto',editable:false">
						<span id="AwardLevelSpan"></span>
					</div>
				</td>
			   <td class="empty"></td>
				<td>
					<div class="fitem">
						<label>等级：</label> 
						<select class='easyui-combobox' id="AwardRank" name="t711_Bean.AwardRank" panelHeight="auto" >
							<option value="一等奖">一等奖</option>
							<option value="二等奖">二等奖</option>
							<option value="三等奖">三等奖</option>
							<option value="优秀奖">优秀奖</option>
						</select>	
						<span id="AwardRankSpan"></span>
					</div>
				</td>
				</tr>
				<tr>
				<td>
					<div class="fitem">
						<label>获奖时间：</label> 
						<input  id="AwardTime"  class="easyui-datebox" style="width:80px"  name="t711_Bean.AwardTime">
						
							<span id="AwardTimeSpan"></span>
					</div>
				</td>
			<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>授予单位：</label> 
						<input id="AwardFromUnit" type="text" name="t711_Bean.AwardFromUnit"
							><span id="AwardFromUnitSpan"></span>
					</div>
				</td>
				</tr>
				<tr>
				<td>
					<div class="fitem">
						<label>批文号：</label> 
						<input id="AppvlID" type="text" name="t711_Bean.AppvlID"
							><span id="AppvlIDSpan"></span>
					</div>
				</td>
			<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>合作教师人数：</label> 
						<input id="JoinTeaNum" type="text" name="t711_Bean.JoinTeaNum"
							><span id="JoinTeaNumSpan"></span>
					</div>
				</td>
				</tr>
			<tr>
				<td>
				
					<div class="fitem">
						<label>其他合作教师：</label> 
						<input id="OtherJoinTeaInfo" type="text" name="t711_Bean.OtherJoinTeaInfo"
							><span id="OtherJoinTeaInfoSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
			
				<td style="valign:left" colspan="3"><label>备&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
					<textarea id="Note" name="t711_Bean.Note" style="resize:none" cols="50" rows="10"></textarea>
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
	  	 
	  		 url: 'pages/T711/uploadFile',
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
	    	
	     	url = "pages/T711/insert";
		    $('#dlg').dialog('open').dialog('setTitle','添加教学管理人员获得教学成果奖情况');
		    $('#t711Form').form('reset');
		   
	    }

	    function singleImport(){
		    //录入数据的表单提交
	    	 $('#t711Form').form('submit',{
				    url: url ,
				    data: $('#t711Form').serialize(),
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
			var name = $('#Name').val();
			var teaID = $('#TeaID').val();
			var awardName = $('#AwardName').val();
			var awardLevel = $('#AwardLevel').combobox('getText');
			var awardRank = $('#AwardRank').combobox('getText');
			var awardTime = $('#AwardTime').datebox('getText');
			var awardFromUnit = $('#AwardFromUnit').val();
			var appvlID = $('#AppvlID').val();
			var joinTeaNum = $('#JoinTeaNum').val();
			var otherjoinTeaInfo = $('#OtherJoinTeaInfo').val();
			var note = $('#Note').val();
			//根据数据库定义的字段的长度，对其进行判断
			if(teaUnit == null || teaUnit.length==0 || teaUnit.length > 100){
				$('#TeaUnit').focus();
				$('#TeaUnit').select();
				$('#TeaUnitSpan').html("<font style=\"color:red\">教学单位不能为空或长度不超过100</font>") ;
				return false;
			}
			if(name == null || name.length==0 || name.length > 10){
				$('#Name').focus();
				$('#Name').select();
				$('#NameSpan').html("<font style=\"color:red\">名字不能为空或长度不超过10</font>") ;
				return false;
			}
			if(teaID == null || teaID.length == 0 || teaID.length > 50){
				$('#TeaID').focus();
				$('#TeaID').select();
				$('#TeaIDSpan').html("<font style=\"color:red\">教工号不能为空或长度不超过50</font>") ;
				return false;
			}
			if(awardName == null || awardName.length==0 || awardName.length > 200){
				$('#awardName').focus();
				$('#awardName').select();
				$('#AwardNameSpan').html("<font style=\"color:red\">奖励名称不能为空或长度不超过200</font>") ;
				return false ;
			}
			if(awardRank == null || awardRank.length == 0 ){
				alert(awardRank) ;
				$('#AwardRankSpan').html("<font style=\"color:red\">等级不能为空</font>") ;
				return false ;
			}
			if(awardLevel == null || awardLevel.length == 0){
				
				$('#AwardLevelSpan').html("<font style=\"color:red\">级别不能为空</font>") ;
				return false ;
			}
			if(awardTime == null || awardTime.length == 0){
				$('#AwardTimeSpan').html("<font style=\"color:red\">获奖时间不能为空</font>") ;
				return false ;
			}
			if(awardFromUnit == null || awardFromUnit.length == 0){
				$('#AwardFromUnitSpan').html("<font style=\"color:red\">授予单位不能为空</font>") ;
				return false ;
			}
			if(appvlID == null || appvlID.length == 0){
				$('#AppvlIDSpan').html("<font style=\"color:red\">批文号不能为空</font>") ;
				return false ;
			}
			if(joinTeaNum == null){
				$('#JoinTeaNumSpan').html("<font style=\"color:red\">合作教师人数不能为空</font>") ;
				return false ;
			}
			if(otherjoinTeaInfo == null){
				$('#OtherJoinTeaInfoSpan').html("<font style=\"color:red\">其他合作教师不能为空</font>") ;
				return false ;
			}
			if(note !=null && note.length > 1000){
				$('#NoteSpan').html("<font style=\"color:red\">备注中文字数不超过500</font>") ;
				return false ;
			}
			alert($('#AwardFromUnit').val()) ;
			return true ;
		}
		function editCourse(){
	    	var row = $('#unverfiedData').datagrid('getSelections');
	    	
	    	if(row.length != 1){
	    		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
	    		return ;
	    	}
	    	
	    	url = 'pages/T711/edit' ;
	    	
	    	  	
	    	$('.title1').hide();
	    	$('#item1').hide();
	    	$('hr').hide();
	    	
	    	
	    	$('#dlg').dialog('open').dialog('setTitle','编辑教学管理人员获得教学成果奖情况');
	    	$('#seqNumber').val(row[0].seqNumber) ;
	    	//alert(row[0].seqNumber);
	    	$('#Name').val(row[0].name) ;
	    	$('#TeaID').val(row[0].teaID) ;
	    	$('#UnitID').combobox('select',row[0].unitID) ;
	    	$('#AwardName').val(row[0].awardName) ;
	    	$('#AwardLevel').combobox('select', row[0].awardLevelID) ;
			$('#AwardRank').combobox('select', row[0].awardRank) ;
		
			$('#AwardFromUnit').val(row[0].awardFromUnit) ;
			$('#AppvlID').val(row[0].appvlID) ;
			$('#JoinTeaNum').val(row[0].joinTeaNum) ;
			$('#OtherJoinTeaInfo').val(row[0].otherJoinTeaInfo) ;
			$('#AwardTime').datebox('setValue',formattime(row[0].awardTime)) ;
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
	    		url: "pages/T711/deleteByIds?ids=" + ids, 
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
