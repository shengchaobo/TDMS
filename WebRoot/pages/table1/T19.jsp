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

<title>T19</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
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
		<script type="text/javascript" src="js/commom.js"></script>
		
</head>
<body style="height: 100%'" >
	<table id="unverfiedData" class="easyui-datagrid" url="pages/T19/auditingData">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber" >编号</th>
				<th field="rewardName" >奖励名称</th>
				<th field="rewardLevel" >级别</th>
				<th field="rewardFromUnit" >授予单位</th>
				<th field="unitName" >获奖单位</th>
				<th field="unitID" >单位号</th>
				<th field="rewardTime"  formatter="formattime">获奖时间</th>
				<th field="note" >备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="height:auto">
		<div style="float: left;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newObject()">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">编辑</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteByIds()">删除</a>
		</div>
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
	
	<div id="toolbar2" style="float: right">
	
		<a href='pages/T19/dataExport?excelName=<%=URLEncoder.encode("表1-9学校获得荣誉（党院办）","UTF-8")%>' class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 
	</div>
<div></div>
	<table id="verfiedData"class="easyui-datagrid"  url="">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber" >序号</th>
				<th field="rewardName" >奖励名称</th>
				<th field="rewardLevel" >级别</th>
				<th field="rewardFromUnit" >授予单位</th>
				<th field="unitName" >获奖单位</th>
				<th field="unitID" >单位号</th>
				<th field="rewardTime" >获奖时间</th>
				<th field="note" >备注</th>
			</tr>
		</thead>
	</table>
	
	
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="title1">学校荣誉记录批量导入</h3>
		<div class="fitem" id="item1"> 
			<form id="batchForm" method="post" enctype="multipart/form-data">
				<select class="easyui-combobox"  id="cbYearContrast" name="selectYear" editable="false"></select> 
				<input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox" size="48" style="height: 24px;" required="true" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href='pages/T19/downloadModel?saveFile=<%=URLEncoder.encode("表1-9学校获得荣誉（党院办）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
			<a href="123"></a>
		</div>
		<hr></hr>	
		<h3 class="title1">学校荣誉记录逐条导入</h3>
		
		<form id="rewardForm" method="post">
		<table>
			<tr>
				<td>
					<div class="fitem">
						<label>荣誉名称：</label> 
						<input id="seqNumber" type="hidden" name="t19Bean.SeqNumber" value="0"></input>
						<input id="RewardName" type="text" name="t19Bean.RewardName"
							class="easyui-validatebox" required="true"><span id="RewardNameSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>授予单位：</label> 
						<input id="RewardFromUnit" type="text" name="t19Bean.RewardFromUnit"
							class="easyui-validatebox" required="true"><span id="RewardFromUnitSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>级别：</label> 
						<input class='easyui-combobox' id="RewardLevel" name="t19Bean.RewardLevel" 
							data-options="valueField:'indexId',textField:'awardLevel',url:'pages/DiAwardLevel/loadDiAwardLevel',listHeight:'auto',editable:false">
							
						<span id="RewardLevelSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
				      <div class="fitem">
				        <label>获奖时间：</label>
				        <input class="easyui-datebox" id="RewardTime" name="t19Bean.RewardTime" editable="false">
						<span id="RewardTimeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td >
					<div class="fitem">
					<label>获奖单位：</label> 
						<!-- 下边的onselect方法是为了后台既要教学单位名称，有需要教学单位编号，而我们只有一个下拉框包含了这两条信息 -->
						<input type="hidden" name="t19Bean.UnitName" id="UnitName"/>
						<input id="UnitID" type="text" name="t19Bean.UnitID" 
							 class='easyui-combobox' data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment',listHeight:'auto',editable:false,
							 onSelect:function(){
							 	document.getElementById('UnitName').value=$(this).combobox('getText') ;
							 }">
						<span id="UnitNameSpan"></span>
					</div>
				</td>
				
			</tr>	
				<td style="valign:left" colspan="3">
				<label>备注：</label>
					<textarea id="Note" name="t19Bean.Note" style="resize:none" cols="50" rows="10"></textarea>
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
	    	 $('#batchForm').form('submit',{
	    		 url: 'pages/T19/uploadFile',
	    		 type: "post",
		         dataType: "json",
	    		 onSubmit: function(){
	    			 return check() ;
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
	    
	    function check(){
	    	var fileName = $('#uploadFile').val() ;
	    	
	    	if(fileName == null || fileName == ""){
		    	$.messager.alert("操作提示","请选择一个Excel文件！");
	    		return false ;
	    	}
	    	
	    	var pos = fileName.lastIndexOf(".") ;
	    	var suffixName = fileName.substring(pos, fileName.length) ;
	    	
	    	if(suffixName == ".xls"){
	    		return true ;
	    	}else{
	    		$.messager.alert("操作提示","文件格式错误，请选择后缀为“.xls”的文件！");
	    		return false ;
	    	}
	    } 

	   
	    function newObject(){
	     	$('.title1').show();
	    	$('#item1').show();
	    	$('hr').show();
	    	url = 'pages/T19/insert' ;
		    $('#dlg').dialog('open').dialog('setTitle','添加新的学校荣誉信息');
		    $('#rewardForm').form('reset');
	    }

	    function singleImport(){
		    //录入数据的表单提交
	    	 $('#rewardForm').form('submit',{
				    url: url,
				    data: $('#rewardForm').serialize(),
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
						    $('#unverifiedData').datagrid('reload'); 
					    }
				    }
			    });
		}

		function validate(){
			//获取文本框的值
			var rewardName = $('#RewardName').val() ;
			var rewardFromUnit = $('#RewardFromUnit').val() ;
			var rewardLevel = $('#RewardLevel').combobox('getText') ;
			//var rewardTime = $('#RewardTime').combobox('getText') ;
			var unitID = $('#UnitID').combobox('getText') ;
			var note = $('#Note').val() ;
			//根据数据库定义的字段的长度，对其进行判断
			if(rewardName == null || rewardName.length==0 || rewardName.length> 100){
				$('#RewardName').focus();
				$('#RewardName').select();
				alert("荣誉名称不能为空或长度不超过100");
				//$('#RewardNameSpan').html("<font style=\"color:red\">荣誉名称不能为空或长度不超过100</font>") ;
				return false ;
			}
			
			if(rewardFromUnit == null || rewardFromUnit.length == 0 || rewardFromUnit.length > 50){
				$('#RewardFromUnit').focus();
				$('#RewardFromUnit').select();
				alert("授予单位不能为空或长度不超过50");
				//$('#RewardFromUnitSpan').html("<font style=\"color:red\">授予单位不能为空或长度不超过50</font>") ;
				return false ;
			}
			
			if(rewardLevel == null || rewardLevel.length == 0){
				$('#RewardLevel').focus();
				$('#RewardLevel').select();
				alert("级别不能为空");
				//$('#RewardLevelSpan').html("<font style=\"color:red\">级别不能为空</font>") ;
				return false ;
			}
			/**
			if(rewardTime == null || rewardTime.length == 0){
				$('#RewardTimeSpan').html("<font style=\"color:red\">获奖时间不能为空</font>") ;
				return false ;
			}else{
				$('#RewardTimeSpan').html("") ;
			}
			*/
			
			if(unitID == null || unitID.length == 0){
				$('#UnitID').focus();
				$('#UnitID').select();
				alert("获奖单位不能为空");
				$('#UnitIDSpan').html("<font style=\"color:red\">获奖单位不能为空</font>") ;
				return false ;
			}
			
			if(note !=null && note.length > 1000){
				$('#Note').focus();
				$('#Note').select();
				alert("备注中文字数不超过500");
				//$('#NoteSpan').html("<font style=\"color:red\">备注中文字数不超过500</font>") ;
				return false ;
			}
			return true ;
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
		    		url: "pages/T19/deleteByIds?ids=" + ids, 
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

		
		
		 function edit(){
		    	var row = $('#unverfiedData').datagrid('getSelections');
		    	
		    	if(row.length != 1){
		    		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
		    		return ;
		    	}
		    	url = 'pages/T19/edit' ;
		    	$('.title1').hide();
		       	$('#item1').hide();
		       	$('hr').hide();
		    	
		    	$('#dlg').dialog('open').dialog('setTitle','修改学校荣誉的信息');
		    	$('#seqNumber').val(row[0].seqNumber) ;
		    	$('#RewardName').val(row[0].rewardName);
		    	$('#RewardFromUnit').val(row[0].rewardFromUnit);
		    	$('#RewardLevel').combobox('select', row[0].rewardLevelID) ;
		    	$('#RewardTime').datebox('setValue',formattime(row[0].rewardTime)) ;
		    	$('#UnitID').combobox('select',row[0].unitID) ;
				$('#Note').val(row[0].note) ;
		    }
	    
	    
	    function loadDic(){
		    $('#dicDlg').dialog('open').dialog('setTitle','高级查询');
		    loadDictionary() ;
		    
	    }
	   
	</script>
	<script>
  var jsonStr = "{\"a\":\"内容1\", \"b\":\"内容2\",\"c\":\"内容3\",\"d\":\"内容4\"}";
  var jsonObj = eval("(" + jsonStr + ")");
  for(var property in jsonObj){
   var nodeObj = document.getElementById(property);
   if(nodeObj)
    nodeObj.childNodes[0].nodeValue = jsonObj[property];
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
