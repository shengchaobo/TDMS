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
	<table id="unverfiedData" title="待审核数据域审核未通过数据" class="easyui-datagrid" url="pages/T712/auditingData"
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="false" singleSelect="false" >
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber">编号</th>
				<th field="name">姓名</th>
				<th field="teaID">教工号</th>
		     </tr>
		</thead>
		<thead>
			<tr>
			
				<th field="teaUnit">教学单位</th>
				<th field="unitID" >单位号</th>
				<th field="paperName" >论文名称</th>
				<th field="paperType">归口类型</th>
				<th field="firstSubject">所属一级学科</th>
				<th field="jonalName">刊物/会议名称</th>
				<th field="jonalID">刊号</th>
				<th field="jonalTime" formatter="formattime">刊期/日期</th>
				<th field="paperWordNum">论文字数</th>
				<th field="confirmLevel" >认定等级</th>
				<th field="joinTeaNum">合作教师人数</th>
				<th field="otherJoinTeaInfo">其他合作教师</th>	
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
		<a href="pages/T712/dataExport?excelName=<%=URLEncoder.encode("表7-1-2教学管理人员发表教学论文情况","UTF-8")%>"  class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="loadDic()">高级检索</a>
	</div>
	<table id="verfiedData" title="审核通过数据" class="easyui-datagrid" url=""
		toolbar="#toolbar2" pagination="true" rownumbers="true"
		fitColumns="false" singleSelect="false">
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber">编号</th>
				<th field="name">姓名</th>
				<th field="teaID">教工号</th>
		     </tr>
		</thead>
		<thead>
			<tr>
			
				<th field="teaUnit">教学单位</th>
				<th field="unitID" >单位号</th>
				<th field="paperName" >论文名称</th>
				<th field="paperType">归口类型</th>
				<th field="firstSubject">所属一级学科</th>
				<th field="jonalName">刊物/会议名称</th>
				<th field="jonalID">刊号</th>
				<th field="jonalTime" formatter="formattime">刊期/日期</th>
				<th field="paperWordNum">论文字数</th>
				<th field="confirmLevel" >认定等级</th>
				<th field="joinTeaNum">合作教师人数</th>
				<th field="otherJoinTeaInfo">其他合作教师</th>	
				<th field="note">备注</th>
				
			</tr>
		</thead>
	</table>
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="title1">教学管理人员发表教学论文情况批量导入</h3>
		<div class="fitem"  id="item1">
			<form method="post" id="batchForm" enctype="multipart/form-data">
			<select class="easyui-combobox"  id="cbYearContrast" name="selectYear"></select>
				<input type="file" name="uploadFile" id="fileToUpload" class="easyui-validatebox" size="48" style="height: 24px;" required="true" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href="pages/T712/downloadModel?saveFile=<%=URLEncoder.encode("表7-1-2教学管理人员发表教学论文情况.xls","UTF-8")%>"  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
			<a href="123"></a>
		</div>
	     <hr></hr>	
			
		<h3 class="title1">教学管理人员发表教学论文情况逐条导入</h3>
		<form id="t712From" method="post">
		<table>
		
		<tr>
			<td>
			<input id="seqNumber" name="teaManagerPaperInfoTeaTea.SeqNumber" type="hidden" value="0">
					<div class="fitem">
						<label>教学单位：</label> 
						
						<input id="TeaUnit" type="hidden" name="teaManagerPaperInfoTeaTea.TeaUnit">
						<input id="UnitID" type="text" name="teaManagerPaperInfoTeaTea.UnitID" 
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
						<label>教工号：</label> 
						<input id="TeaID" type="hidden" name="teaManagerPaperInfoTeaTea.TeaID">
						<input id="Name" type="text" name="teaManagerPaperInfoTeaTea.Name" class='easyui-combobox'
						data-options="valueField:'teaName',textField:'teaId',url:'pages/T411/loadT411',listHeight:'auto',editable:true,
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
						<label>论文名称：</label> 
						<input id="PaperName" type="text" name="teaManagerPaperInfoTeaTea.PaperName"
							><span id="PaperNameSpan"></span>
					</div>
				</td>
			<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>归口类型：</label> 
						<select class='easyui-combobox' id="PaperType" name="teaManagerPaperInfoTeaTea.PaperType" panelHeight="auto" >
						<option value="教学研究">教学研究</option>
						<option value="教学管理">教学管理</option>
						</select>
						<span id="PaperTypeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>所属一级学科：</label> 
						<select class='easyui-combobox' id="FirstSubject" name="teaManagerPaperInfoTeaTea.firstSubject" panelHeight="auto"  >
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
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>刊物/会议名称：</label> 
						<input  id="JonalName" type="text" name="teaManagerPaperInfoTeaTea.JonalName">
						
							<span id="JonalNameSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>刊号：</label> 
						<input id="JonalID" type="text" name="teaManagerPaperInfoTeaTea.JonalID"
							><span id="JonalIDSpan"></span>
					</div>
				</td>
			<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>刊期/日期：</label> 
						<input id="JonalTime" class="easyui-datebox" style="width:80px" name="teaManagerPaperInfoTeaTea.JonalTime"
							><span id="JonalTimeSpan"></span>
					</div>
				</td>
				</tr>
				<tr>
				<td>
					<div class="fitem">
						<label>论文字数：</label> 
						<input id="PaperWordNum" type="text" name="teaManagerPaperInfoTeaTea.PaperWordNum"
							><span id="PaperWordNumSpan"></span>
					</div>
				</td>
			<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>认定等级：</label> 
						<input id="ConfirmLevel" type="text" name="teaManagerPaperInfoTeaTea.ConfirmLevel"
							><span id="ConfirmLevelSpan"></span>
					</div>
				</td>
				</tr>
				<tr>
				<td>
					<div class="fitem">
						<label>合作教师人数：</label> 
						<input id="JoinTeaNum" type="text" name="teaManagerPaperInfoTeaTea.JoinTeaNum"
							><span id="JoinTeaNumSpan"></span>
					</div>
				</td>
			<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>其他合作教师：</label> 
						<input id="OtherJoinTeaInfo" type="text" name="teaManagerPaperInfoTeaTea.OtherJoinTeaInfo"
							><span id="OtherJoinTeaInfoSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
			
				<td style="valign:left" colspan="3"><label>备&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
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
	  	 
	  		 url: 'pages/T712/uploadFile',
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
	    	
	    	 url="pages/T712/insert";   
		    $('#dlg').dialog('open').dialog('setTitle','添加教学管理人员发表教学论文情况');
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
			   alert("教学单位不能为空或长度不超过100");	
				return false;
			}
			
			if(teaID == null || teaID.length == 0 || teaID.length > 200){
			   alert("教工号不能为空或长度不超过200");
				return false;
			}
			if(paperName == null || paperName.length==0 || paperName.length > 200){
			  alert("奖励名称不能为空或长度不超过200");
				return false ;
			}
			if(paperType == null || paperType.length == 0 ){
				alert("等级不能为空") ;
				return false ;
			}
			if(firstSubject == null || firstSubject.length == 0){
				alert("级别不能为空") ;
				return false ;
			}
			if(jonalID == null || jonalID.length == 0){
			alert("获奖时间不能为空") ;
				return false ;
			}
			if(jonalName == null || jonalName.length == 0){
				alert("授予单位不能为空") ;
				return false ;
			}
			if(confirmLevel == null || confirmLevel.length == 0){
			alert("批文号不能为空") ;
				return false ;
			}
	
			if(note.length > 500){
			alert("备注中文字数不超过500") ;
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
	    	
	    	url = 'pages/T712/edit' ;
	    	$('.title1').hide();
	    	$('#item1').hide();
	    	$('hr').hide();
	    	
	    	$('#dlg').dialog('open').dialog('setTitle','修改教学管理人员发表教学论文情况');
	    	$('#seqNumber').val(row[0].seqNumber) ;
	    	//alert(row[0].seqNumber);
	    	$('#UnitID').combobox('select', row[0].unitIDD) ;
	    	$('#Name').combobox('select', row[0].name) ;
	    	$('#PaperName').val(row[0].paperName) ;
	    	$('#PaperType').val(row[0].paperType) ;
	    	$('#FirstSubject').combobox('select', row[0].firstSubject) ;
	    	$('#JonalID').val(row[0].jonalID);
			$('#JonalName').val(row[0].jonalName); 
		
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
	    		url: "pages/T712/deleteByIds?ids=" + ids, 
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
