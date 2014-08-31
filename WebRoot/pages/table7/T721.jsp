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
    
    <title>My JSP 'T721_TeachResItem_Tea.jsp' starting page</title>
    
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
	<table id="unverfiedData" title="待审核数据域审核未通过数据" class="easyui-datagrid" url="pages/T721/auditingData"
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="false" singleSelect="false" >
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber">编号</th>
				<th field="itemName">项目名称</th>
				<th field="teaUnit">所属教学单位</th>
		     </tr>
		</thead>
		<thead>
			<tr>
				<th field="unitID">单位号</th>
				<th field="leader">负责人</th>
				<th field="teaID">教工号</th>
				<th field="otherTeaNum">其他参与教师人数</th>
				<th field="otherTea">其他教师</th>
				<th field="itemLevel">级别</th>
				<th field="itemSetUpTime" formatter="formattime">立项时间</th>
				<th field="receptTime" formatter="formattime">验收时间</th>
				<th field="applvExp">批准经费（万元）</th>
				<th field="schSupportExp">学校配套经费（万元）</th>
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
		<a href="pages/T721/dataExport?excelName=<%=URLEncoder.encode("表7-2-1教育教学研究与改革项目","UTF-8")%>"  class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="loadDic()">高级检索</a>
	</div>
	<table id="verfiedData" title="审核通过数据" class="easyui-datagrid" url=""
		toolbar="#toolbar2" pagination="true" rownumbers="true"
		fitColumns="false" singleSelect="false">
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber">编号</th>
				<th field="itemName">项目名称</th>
				<th field="teaUnit">所属教学单位</th>
		     </tr>
		</thead>
		<thead>
			<tr>
				<th field="unitID">单位号</th>
				<th field="leader">负责人</th>
				<th field="teaID">教工号</th>
				<th field="otherTeaNum">其他参与教师人数</th>
				<th field="otherTea">其他教师</th>
				<th field="itemLevel">级别</th>
				<th field="itemSetUpTime" formatter="formattime">立项时间</th>
				<th field="receptTime" formatter="formattime">验收时间</th>
				<th field="applvExp">批准经费（万元）</th>
				<th field="schSupportExp">学校配套经费（万元）</th>
				<th field="appvlID">批文号</th>	
				<th field="note">备注</th>
				
			</tr>
		</thead>
	</table>
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="title1">教育教学研究与改革项目批量导入</h3>
		<div class="fitem" id="item1">
			<form method="post" id="batchForm" enctype="multipart/form-data">
			<select class="easyui-combobox"  id="cbYearContrast" name="selectYear"></select>
				<input type="file" name="uploadFile" id="fileToUpload" class="easyui-validatebox" size="48" style="height: 24px;" required="true" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href="pages/T721/downloadModel?saveFile=<%=URLEncoder.encode("表7-2-1教育教学研究与改革项目.xls","UTF-8")%>"  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
			<a href="123"></a>
		</div>
     <hr></hr>	
			
		<h3 class="title1">教育教学研究与改革项目逐条导入</h3>
		<form id="t721Form" method="post">
		<table>
		
		<tr>
			<td>
					<div class="fitem">
						<label>项目名称：</label> 
						<input id="seqNumber" name="teachResItemTea.SeqNumber" type="hidden" value="0">
						<input id="ItemName" type="text" name="teachResItemTea.ItemName" 
							><span id="ItemNameSpan"></span>
					</div>
				</td>
				<td class="empty"></td>	
				<td>
					<div class="fitem">
					<label>所属教学单位：</label>
						<input type="hidden" name="teachResItemTea.TeaUnit" id="TeaUnit"/>
						<input id="UnitID" type="text" name="teachResItemTea.UnitID" 
							 class='easyui-combobox' data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDIDepartmentAca',listHeight:'auto',editable:false,
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
						<label>教工号：</label> 
						<input id="TeaID" type="hidden" name="teachResItemTea.TeaID" >
					    <input id="Leader" type="text" name="teachResItemTea.Leader"
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
						<label>其他参与教师人数：</label> 
						<input id="OtherTeaNum" type="text" name="teachResItemTea.OtherTeaNum"
							><span id="OtherTeaNumSpan"></span>
					</div>
				</td>
				</tr>
				<tr>
				<td>
					<div class="fitem">
						<label>其他教师：</label> 
						<input id="OtherTea" type="text" name="teachResItemTea.OtherTea"
							><span id="OtherTeaSpan"></span>
					</div>
				</td>
			<td class="empty"></td>	
				<td>
					<div class="fitem">
						<label>级别：</label> 
						<input class='easyui-combobox' id="ItemLevel" name="teachResItemTea.ItemLevel"
							data-options="valueField:'indexId',textField:'awardLevel',url:'pages/DiAwardLevel/loadDiAwardLevel',listHeight:'auto',editable:false">
						<span id="ItemLevelSpan"></span>
						
					</div>
				</td>
				</tr>
				<tr>
				<td>
					<div class="fitem">
						<label>立项时间：</label> 
						<input  id="ItemSetUpTime"  class="easyui-datebox" style="width:80px" name="teachResItemTea.ItemSetUpTime" editable="false">
						
							<span id="ItemSetUpTimeSpan"></span>
					</div>
				</td>
			<td class="empty"></td>	
				<td>
					<div class="fitem">
						<label>验收时间：</label> 
						<input id="ReceptTime"  class="easyui-datebox" style="width:80px"  name="teachResItemTea.ReceptTime"
							editable="false"><span id="ReceptTimeSpan"></span>
					</div>
				</td>
				</tr>
				
				<tr>
				<td>
					<div class="fitem">
						<label>批准经费（万元）：</label> 
						<input id="ApplvExp" type="text" name="teachResItemTea.ApplvExp"
							><span id="ApplvExpSpan"></span>
					</div>
				</td>
				<td class="empty"></td>	
				<td>
					<div class="fitem">
						<label>学校配套经费（万元）：</label> 
						<input id="SchSupportExp" type="text" name="teachResItemTea.SchSupportExp"
							><span id="SchSupportExpSpan"></span>
					</div>
				</td>
				</tr>
				<tr>
				<td>
					<div class="fitem">
						<label>批文号：</label> 
						<input id="AppvlID" type="text" name="teachResItemTea.AppvlID"
							><span id="AppvlIDSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
			
				<td style="valign:left" colspan="3"><label>备&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
					<textarea id="Note" name="teachResItemTea.Note" style="resize:none" cols="50" rows="10"></textarea>
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
	  	 
	  		 url: 'pages/T721/uploadFile',
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
	    	$('.item1').show();
	    	$('hr').show();
	        url="pages/T721/insert";
		    $('#dlg').dialog('open').dialog('setTitle','添加教育教学研究与改革项目');
		    $('#t721Form').form('reset');
	    }

	    function singleImport(){
		    //录入数据的表单提交
	    	 $('#t721Form').form('submit',{
				    url: url,
				    data: $('#t721Form').serialize(),
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
			var itemName = $('#ItemName').val();
			var teaUnit = $('#UnitID').combobox('getText');
			var teaId = $('#Leader').combobox('getText');
			var teaName = $('#Leader').combobox('getValue');
			var otherTeaNum = $('#OtherTeaNum').val();
			var otherTea = $('#OtherTea').val();
			var itemLevel = $('#ItemLevel').combobox('getText');
			var itemSetUpTime = $('#ItemSetUpTime').datebox('getText');
			var receptTime = $('#ReceptTime').datebox('getText');
			var applvExp = $('#ApplvExp').val();
			var schSupportExp = $('#SchSupportExp').val();
			var appvlID = $('#AppvlID').val();
			var note = $('#Note').val();
			//根据数据库定义的字段的长度，对其进行判断
			if(itemName == null || itemName.length==0 || itemName.length > 100){
			alert("教学单位不能为空或长度不超过100");
				return false;
			}
			if(teaId == null || teaId.length == 0){
			alert("负责人不能为空");
				return false ;
			}
			if (teaId == null ||  teaId == ''  || teaId.length == 0 || teaId == teaName) {
				alert("教工号不能为空或者教师库中无该教工号");
				return false;
			}
			if(itemLevel == null || itemLevel.length==0 || itemLevel.length > 20){
				alert("级别不能为空或长度不超过20");
				return false ;
			}
			if(itemSetUpTime == null || itemSetUpTime.length == 0 ){
				alert("立项时间不能为空") ;
				return false ;
			}
			if(receptTime == null || receptTime.length == 0){
			    alert("验收时间不能为空") ;
				return false ;
			}
			if(applvExp == null || applvExp.length == 0){
			    alert("批准经费不能为空") ;
				return false ;
			}
			if(schSupportExp == null || schSupportExp.length == 0){
			    alert("学校配套经费不能为空") ;
				return false ;
			}
			if(appvlID == null || appvlID.length == 0){
			   alert("批文号不能为空") ;
				return false ;
			}
			
			if(note !=null && note.length > 500){
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
	    	
	    	url = 'pages/T721/edit' ;
	      	$('.title1').hide();
	    	$('#item1').hide();
	    	$('hr').hide();
	    	
	    	$('#dlg').dialog('open').dialog('setTitle','修改教育教学研究与改革项目');
	    	$('#seqNumber').val(row[0].seqNumber) ;
	    	$('#ItemName').val(row[0].itemName) ;
	    	$('#UnitID').combobox('select', row[0].unitID) ;
	    	$('#Leader').combobox('select', row[0].leader) ;
	    	$('#OtherTeaNum').val(row[0].otherTeaNum) ;
	    	$('#OtherTea').val(row[0].otherTea) ;
	    	$('#ItemLevel').combobox('select', row[0].itemLevelID) ;
	        $('#ItemSetUpTime').datebox('setValue',formattime(row[0].itemSetUpTime)) ;
	        $('#ReceptTime').datebox('setValue',formattime(row[0].receptTime)) ;
	    	$('#ApplvExp').val(row[0].applvExp) ;
			$('#SchSupportExp').val(row[0].schSupportExp) ;
			$('#AppvlID').val(row[0].appvlID) ;
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
	    		url: "pages/T721/deleteByIds?ids=" + ids, 
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
