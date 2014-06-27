<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<table id="unverfiedData" title="待审核数据域审核未通过数据" class="easyui-datagrid" style="width:100%px;height:250px" url="pages/SchLeadInClassInfoTea/auditingData"
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="false" >
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber" width="10%">序号</th>
				<th field="attendClassTerm" width="10%">听课学期</th>
				<th field="leaderName" width="15%">校领导姓名</th>
				<th field="leaderID" width="15%">校领导教工号</th>
				<th field="attendClassTime" width="10%" formatter="formattime">听课日期</th>
				<th field="lectureTea" width="10%">授课教师</th>
				<th field="lectureTeaID" width="15%">授课教教工号</th>
				<th field="lectureCS" width="10%">听课课程</th>
				<th field="CSID" width="10%">课程编号</th>
				<th field="setCSUnit" width="10%">开课单位</th>
				<th field="unitID" width="10%">单位号</th>
				<th field="lectureClass" width="10%">上课班级</th>
				<th field="evaluate" width="10%">综合评价</th>	
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
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-download" plain="true" onclick="newCourse()">数据导出</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="loadDic()">高级检索</a>
	</div>
	<table id="verfiedData" title="审核通过数据" class="easyui-datagrid" style="width:100%px;height:250px" url=""
		toolbar="#toolbar2" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="false">
		<thead>
			<tr>
					<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber" width="10%">序号</th>
				<th field="attendClassTerm" width="10%">听课学期</th>
				<th field="leaderName" width="15%">校领导姓名</th>
				<th field="leaderID" width="15%">校领导教工号</th>
				<th field="attendClassTime" width="10%" formatter="formattime">听课日期</th>
				<th field="lectureTea" width="10%">授课教师</th>
				<th field="lectureTeaID" width="15%">授课教教工号</th>
				<th field="lectureCS" width="10%">听课课程</th>
				<th field="CSID" width="10%">课程编号</th>
				<th field="setCSUnit" width="10%">开课单位</th>
				<th field="unitID" width="10%">单位号</th>
				<th field="lectureClass" width="10%">上课班级</th>
				<th field="evaluate" width="10%">综合评价</th>	
				<th field="note" width="20%">备注</th>
				<th field="time" width="10" formatter="formattime">时间</th>
			</tr>
		</thead>
	</table>
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<div class="ftitle">本科课程库批量导入</div>
		<div class="fitem">
			<form method="post">
				<label>批量上传：</label> 
				<input type="file" name="fileToUpload" id="fileToUpload" class="easyui-validatebox"
					validType="fileType['xls']" required="true" invalidMessage="请选择Excel格式的文件" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">导入</a>
				<a href="table5/downloadCSBaseLibraries" class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
			<a href="123"></a>
		</div>
		<div></div>
		<div class="ftitle">本科课程库逐条导入</div>
		
		<form id="t731Form" method="post">
		<table>
		
		<tr>
			<td>
					<div class="fitem">
						<label>听课学期：</label> 
						<input id="seqNumber" name="schleadInClass.SeqNumber" type="hidden" value="0">
						<input id="AttendClassTerm" type="text" name="schleadInClass.AttendClassTerm"
							><span id="AttendClassTermSpan"></span>
					</div>
					</td>
				<td>
					<div class="fitem">
						<label>校领导教工号：</label> 
						<input id="LeaderID" type="hidden" name="schleadInClass.LeaderID" >
					    <input id="LeaderName" type="text" name="schleadInClass.LeaderName"
					     class='easyui-combobox' data-options="valueField:'teaName',textField:'teaId',url:'pages/T411/loadT411',listHeight:'auto',editable:true,
							 onSelect:function(){
							   document.getElementById('LeaderID').value=$(this).combobox('getText') ;
							 }">
							
							<span id="LeaderIDSpan"></span>
					</div>
				</td>
				</tr>
			<tr>
			<td>
					<div class="fitem">
						<label>听课日期：</label> 
						<input id="AttendClassTime" class="easyui-datebox" style="width:80px" name="schleadInClass.AttendClassTime"
							><span id="AttendClassTimeSpan"></span>
					</div>
				<td>
					<div class="fitem">
						<label>授课教师教工号：</label> 
						<input id="LectureTeaID" type="hidden" name="schleadInClass.LectureTeaID" >
					    <input id="LectureTea" type="text" name="schleadInClass.LectureTea"
					     class='easyui-combobox' data-options="valueField:'teaName',textField:'teaId',url:'pages/T411/loadT411',listHeight:'auto',editable:true,
							 onSelect:function(){
							   document.getElementById('LectureTeaID').value=$(this).combobox('getText') ;
							 }">
							
							<span id="LeaderIDSpan"></span>
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
							 class='easyui-combobox' data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment',listHeight:'auto',editable:false,
							 onSelect:function(){
							 	document.getElementById('SetCSUnit').value=$(this).combobox('getText') ;
							 }">
							<span id="TeaUnitSpan"></span>
					</div>
				</td>
			
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
						<select class='easyui-combobox' id="Evaluate" name="schleadInClass.Evaluate">
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
				<td style="valign:left"><label>备&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
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
	    	 $('#fm').form('submit',{
	    		 url: url,
	    		 onSubmit: function(){
	    		 	return $(this).form('validate');
	    		 },
	    		 success: function(result){
	    		 	var result = eval('('+result+')');
	    		 	if (result.errorMsg){
	    		 		$.messager.show({
	    		 			title: 'Error',
	    		 			msg: result.errorMsg
	    			 });
	    		 	} else {
			    		 $('#dlg').dialog('close'); // close the dialog
			    		 $('#dg').datagrid('reload'); // reload the user data
	    		 	}
	    		 }
	    		 });
	    }
	    
	    function newCourse(){
	        url="pages/SchLeadInClassInfoTea/insert";
		    $('#dlg').dialog('open').dialog('setTitle','添加本科教学课程库');
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
						    $('#unverfiedData').datagrid('reload'); 
					    }
				    }
			    });
		}

		function validate(){
			//获取文本框的值
			var attendClassTerm = $('#AttendClassTerm').val();
			var leaderID = $('#LeaderName').combobox('getText');
			var attendClassTime = $('#AttendClassTime').datebox('getText');
			var lectureTeaID = $('#LectureTea').combobox('getText');
			var lectureCS = $('#LectureCS').val();
			var cSID = $('#CSID').val();
			var setCSUnit = $('#UnitID').combobox('getText');
			var lectureClass = $('#LectureClass').val();
			var evaluate = $('#Evaluate').combobox('getText');
			var note = $('#Note').val();
			//根据数据库定义的字段的长度，对其进行判断
			if(attendClassTerm == null || attendClassTerm.length==0){
				$('#AttendClassTermSpan').html("<font style=\"color:red\">听课学期不能为空</font>") ;
				return false;
			}
			if(leaderID == null || leaderID.length==0 || leaderID.length > 50){
				
				$('#LeaderIDSpan').html("<font style=\"color:red\">校领导姓名不能为空或长度不超过50</font>") ;
				return false;
			}
			if(attendClassTime == null || attendClassTime.length == 0){
				$('#AttendClassTimeSpan').html("<font style=\"color:red\">听课日期不能为空</font>") ;
				return false;
			}
			if(lectureTeaID == null || lectureTeaID.length==0){
				$('#lectureTeaID').focus();
				$('#lectureTeaID').select();
				$('#LectureTeaIDSpan').html("<font style=\"color:red\">授课教师不能为空</font>") ;
				return false ;
			}
			if(lectureCS == null || lectureCS.length == 0 ){
				
				$('#LectureCSSpan').html("<font style=\"color:red\">听课课程不能为空</font>") ;
				return false ;
			}
			if(cSID == null || cSID.length == 0){
				
				$('#CSIDSpan').html("<font style=\"color:red\">课程编号不能为空</font>") ;
				return false ;
			}
			if(setCSUnit == null || setCSUnit.length == 0){
				$('#SetCSUnitSpan').html("<font style=\"color:red\">开课单位不能为空</font>") ;
				return false ;
			}
			if(lectureClass == null || lectureClass.length == 0){
				$('#LectureClassSpan').html("<font style=\"color:red\">上课班级不能为空</font>") ;
				return false ;
			}
			if(note !=null && note.length > 1000){
				$('#NoteSpan').html("<font style=\"color:red\">备注中文字数不超过500</font>") ;
				return false ;
			}
			alert($('#LectureCS').val()) ;
			return true ;
		}
		
			function editCourse(){
	    	var row = $('#unverfiedData').datagrid('getSelections');
	    	
	    	if(row.length != 1){
	    		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
	    		return ;
	    	}
	    	
	    	url = 'pages/SchLeadInClassInfoTea/edit' ;
	    	$('#dlg').dialog('open').dialog('setTitle','添加本科教学课程库');
	    	alert(row[0].lectureCS);
	    	$('#seqNumber').val(row[0].seqNumber) ;
	    	$('#AttendClassTerm').val(row[0].attendClassTerm) ;
	    	$('#LeaderName').combobox('select', row[0].leaderIDD) ;
	    	$('#AttendClassTime').datebox('setValue',formattime(row[0].attendClassTime)) ;
	    	$('#LectureTea').combobox('select', row[0].lectureTea) ;
	    	$('#LectureCS').val(row[0].lectureCS) ;
	    	$('#CSID').val(row[0].cSID) ;
	    	
	    	$('#UnitID').combobox('select', row[0].unitID) ;
			$('#LectureClass').val(row[0].lectureClass);
			$('#Evaluate').combobox('select', row[0].evaluate) ;
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
	    		url: "pages/SchLeadInClassInfoTea/deleteByIds?ids=" + ids, 
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
	    		url: "table5/dictorySearch",
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
