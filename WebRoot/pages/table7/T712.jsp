<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'T712_TeaManagerPaperInfo_TeaTea.jsp' starting page</title>
    
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
	<table id="unverfiedData" title="待审核数据域审核未通过数据" class="easyui-datagrid" style="width:100%px;height:250px" url="pages/TeaManagerPaperInfoTeaTea/auditingData"
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="false" >
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber" width="10%">序号</th>
				<th field="teaUnit" width="10%">教学单位</th>
				<th field="unitID" width="10%">单位号</th>
				<th field="name" width="10%">姓名</th>
				<th field="teaID" width="10%">教工号</th>
				<th field="paperName" width="10%">论文名称</th>
				<th field="paperType" width="10%">归口类型</th>
				<th field="firstSubject" width="14%">所属一级学科</th>
				<th field="jonalName" width="15%">刊物/会议名称</th>
				<th field="jonalID" width="10%">刊号</th>
				<th field="jonalTime" width="10%" formatter="formattime">刊期/日期</th>
				<th field="paperWordNum" width="10%">论文字数</th>
				<th field="confirmLevel" width="10%">认定等级</th>
				<th field="joinTeaNum" width="15%">合作教师人数</th>
				<th field="otherJoinTeaInfo" width="15%">其他合作教师</th>	
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
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-download" plain="true" onclick="">数据导出</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="loadDic()">高级检索</a>
	</div>
	<table id="verfiedData" title="审核通过数据" class="easyui-datagrid" style="width:100%px;height:250px" url=""
		toolbar="#toolbar2" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="false">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber" width="10%">序号</th>
				<th field="teaUnit" width="10%">教学单位</th>
				<th field="unitID" width="10%">单位号</th>
				<th field="name" width="10%">姓名</th>
				<th field="teaID" width="10%">教工号</th>
				<th field="paperName" width="10%">论文名称</th>
				<th field="paperType" width="10%">归口类型</th>
				<th field="firstSubject" width="14%">所属一级学科</th>
				<th field="jonalName" width="15%">刊物/会议名称</th>
				<th field="jonalID" width="10%">刊号</th>
				<th field="jonalTime" width="10%" formatter="formattime">刊期/日期</th>
				<th field="paperWordNum" width="10%">论文字数</th>
				<th field="confirmLevel" width="10%">认定等级</th>
				<th field="joinTeaNum" width="15%">合作教师人数</th>
				<th field="otherJoinTeaInfo" width="15%">其他合作教师</th>	
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
		
		<form id="t712From" method="post">
		<table>
		
		<tr>
			<td>
					<div class="fitem">
						<label>教学单位：</label> 
						<input id="seqNumber" name="teaManagerPaperInfoTeaTea.SeqNumber" type="hidden" value="0">
						<input id="TeaUnit" type="hidden" name="teaManagerPaperInfoTeaTea.TeaUnit">
						<input id="UnitID" type="text" name="teaManagerPaperInfoTeaTea.UnitID" 
							 class='easyui-combobox' data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment',listHeight:'auto',editable:false,
							 onSelect:function(){
							 	document.getElementById('TeaUnit').value=$(this).combobox('getText') ;
							 }">
							<span id="TeaUnitSpan"></span>
					</div>
				<td>
					
				</tr>
			<tr>
			<td>
					<div class="fitem">
						<label>教工号：</label> 
						<input id="TeaID" type="hidden" name="teaManagerPaperInfoTeaTea.TeaID">
						<input id="Name" type="text" name="teaManagerPaperInfoTeaTea.Name" class='easyui-combobox'
						data-options="valueField:'teaName',textField:'teaId',url:'pages/T411/loadT411',listHeight:'auto',editable:true,
						 onSelect:function(){
							 	document.getElementById('TeaID').value=$(this).combobox('getText') ;
							 }">
							<span id="TeaIDSpan"></span>
					</div>
				<td>
					<div class="fitem">
						<label>论文名称：</label> 
						<input id="PaperName" type="text" name="teaManagerPaperInfoTeaTea.PaperName"
							><span id="PaperNameSpan"></span>
					</div>
				</td>
				</tr>
				<tr>
				<td>
					<div class="fitem">
						<label>归口类型：</label> 
						<select class='easyui-combobox' id="PaperType" name="teaManagerPaperInfoTeaTea.PaperType">
						<option value="教学研究">教学研究</option>
						<option value="教学管理">教学管理</option>
						</select>
						<span id="PaperTypeSpan"></span>
					</div>
				</td>
			
				<td>
					<div class="fitem">
						<label>所属一级学科：</label> 
						<select class='easyui-combobox' id="FirstSubject" name="teaManagerPaperInfoTeaTea.firstSubject" >
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
						<span id="FirstSubjectSpan"></span>
					</div>
				</td>
				</tr>
				<tr>
				<td>
					<div class="fitem">
						<label>刊物/会议名称：</label> 
						<input  id="JonalName" type="text" name="teaManagerPaperInfoTeaTea.JonalName">
						
							<span id="JonalNameSpan"></span>
					</div>
				</td>
			
				<td>
					<div class="fitem">
						<label>刊号：</label> 
						<input id="JonalID" type="text" name="teaManagerPaperInfoTeaTea.JonalID"
							><span id="JonalIDSpan"></span>
					</div>
				</td>
				</tr>
				<tr>
				<td>
					<div class="fitem">
						<label>刊期/日期：</label> 
						<input id="JonalTime" class="easyui-datebox" style="width:80px" name="teaManagerPaperInfoTeaTea.JonalTime"
							><span id="JonalTimeSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>论文字数：</label> 
						<input id="PaperWordNum" type="text" name="teaManagerPaperInfoTeaTea.PaperWordNum"
							><span id="PaperWordNumSpan"></span>
					</div>
				</td>
				</tr>
				<tr>
				<td>
					<div class="fitem">
						<label>认定等级：</label> 
						<input id="ConfirmLevel" type="text" name="teaManagerPaperInfoTeaTea.ConfirmLevel"
							><span id="ConfirmLevelSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>合作教师人数：</label> 
						<input id="JoinTeaNum" type="text" name="teaManagerPaperInfoTeaTea.JoinTeaNum"
							><span id="JoinTeaNumSpan"></span>
					</div>
				</td>
				</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>其他合作教师：</label> 
						<input id="OtherJoinTeaInfo" type="text" name="teaManagerPaperInfoTeaTea.OtherJoinTeaInfo"
							><span id="OtherJoinTeaInfoSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
			
				<td style="valign:left"><label>备&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
					<textarea id="Note" name="teaManagerPaperInfoTeaTea.Note" style="resize:none" cols="50" rows="10"></textarea>
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
	    	 url="pages/TeaManagerPaperInfoTeaTea/insert";   
		    $('#dlg').dialog('open').dialog('setTitle','添加本科教学课程库');
		    $('#t712From').form('reset');
	    }

	    function singleImport(){
		    //录入数据的表单提交
	    	 $('#t712From').form('submit',{
				    url: url,
				    data: $('#t712From').serialize(),
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
			var teaID = $('#Name').combobox('getText');
			var paperName = $('#PaperName').val();
			var paperType = $('#PaperType').combobox('getText');
			var firstSubject = $('#FirstSubject').combobox('getText');
			var jonalID = $('#JonalID').val();
			var jonalName = $('#JonalName').val();
			var jonalTime = $('#JonalTime').datebox('getText');
			var paperWordNum = $('#PaperWordNum').val();
			var confirmLevel = $('#ConfirmLevel').val();
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
			
			if(teaID == null || teaID.length == 0 || teaID.length > 200){
				$('#TeaID').focus();
				$('#TeaID').select();
				$('#TeaIDSpan').html("<font style=\"color:red\">教工号不能为空或长度不超过200</font>") ;
				return false;
			}
			if(paperName == null || paperName.length==0 || paperName.length > 200){
				$('#paperName').focus();
				$('#paperName').select();
				$('#PaperNameSpan').html("<font style=\"color:red\">奖励名称不能为空或长度不超过200</font>") ;
				return false ;
			}
			if(paperType == null || paperType.length == 0 ){
				alert(awardRank) ;
				$('#PaperTypeSpan').html("<font style=\"color:red\">等级不能为空</font>") ;
				return false ;
			}
			if(firstSubject == null || firstSubject.length == 0){
				
				$('#FirstSubjectSpan').html("<font style=\"color:red\">级别不能为空</font>") ;
				return false ;
			}
			if(jonalID == null || jonalID.length == 0){
				$('#JonalIDSpan').html("<font style=\"color:red\">获奖时间不能为空</font>") ;
				return false ;
			}
			if(jonalName == null || jonalName.length == 0){
				$('#JonalNameSpan').html("<font style=\"color:red\">授予单位不能为空</font>") ;
				return false ;
			}
			if(confirmLevel == null || confirmLevel.length == 0){
				$('#ConfirmLevelSpan').html("<font style=\"color:red\">批文号不能为空</font>") ;
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
			alert($('#TeaUnit').val()) ;
			return true ;
		}
		function editCourse(){
	    	var row = $('#unverfiedData').datagrid('getSelections');
	    	
	    	if(row.length != 1){
	    		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
	    		return ;
	    	}
	    	
	    	url = 'pages/TeaManagerPaperInfoTeaTea/edit' ;
	    	
	    	$('#dlg').dialog('open').dialog('setTitle','添加本科教学课程库');
	    	$('#seqNumber').val(row[0].seqNumber) ;
	    	//alert(row[0].seqNumber);
	    	$('#UnitID').combobox('select', row[0].unitIDD) ;
	    	$('#Name').combobox('select', row[0].name) ;
	    	$('#PaperName').val(row[0].paperName) ;
	    	$('#PaperType').val(row[0].paperType) ;
	    	$('#FirstSubject').combobox('select', row[0].firstSubject) ;
	    	$('#JonalID').val(row[0].jonalID) 
			$('#JonalName').val(row[0].jonalName) 
		
			$('#PaperWordNum').val(row[0].paperWordNum) ;
			$('#ConfirmLevel').val(row[0].confirmLevel) ;
			$('#JoinTeaNum').val(row[0].joinTeaNum) ;
			$('#OtherJoinTeaInfo').val(row[0].otherJoinTeaInfo) ;
			$('#JonalTime').datebox('setValue',formattime(row[0].jonalTime)) ;
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
	    		url: "pages/TeaManagerPaperInfoTeaTea/deleteByIds?ids=" + ids, 
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
