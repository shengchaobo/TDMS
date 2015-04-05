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

<title>T172</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
		<script type="text/javascript" src="js/commom.js"></script>
	
</head>
<body style="overflow-y:scroll">
	<table id="commomData" title="校友返校交流情况（党院办）" class="easyui-datagrid"  url="pages/T172/auditingData" >
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber" >编号</th>
				<th field="friName">校友姓名</th>
				<th field="actName">交流活动名称</th>
				<th field="actType">交流活动类型</th>
				<th field="actTime"  formatter="formattime">交流时间</th>
				<th field="actPlace">交流地点</th>
				<th field="unitName">协办单位</th>
				<th field="unitID">单位号</th>
				<th field="note">备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="height:auto">
		<div style="float: left;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newObject()">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">编辑</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteByIds()">删除</a>
		</div>
		<div style="float: right;">
			<form action='pages/T172/dataExport?excelName=<%=URLEncoder.encode("表1-7-2校友返校交流情况（党院办）","UTF-8")%>'   method="post"  id="exportForm" enctype="multipart/form-data"  style="float: right;">
					  <select class="easyui-combobox"  id="cbYearContrast1" name="selectYear"  editable=false ></select>&nbsp;&nbsp;
						<a href='javascript:submitForm()'   style="font:12px;color: black;text-decoration:none;" >
								数据导出
						</a> &nbsp;&nbsp;&nbsp;&nbsp;		
			</form>
			
			</div>
  </div>
			
		<!-- 	<a href="pages/T172/dataExport?excelName=表1-7-2校友返校交流情况（党院办）" class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a>  
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

	-->
	<!--  
	<table id="verfiedData"  class="easyui-datagrid"  url="pages/T17/auditingData" >
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber" >编号</th>
				<th field="clubName">校友会名称</th>
				<th field="buildYear"  formatter="formattime">建设时间</th>
				<th field="place" >地点</th>
				<th field="note">备注</th>
			</tr>
		</thead>
	</table>
	
	<div id="toolbar2" style="float: right;">
	
		<a href='pages/T17/dataExport?excelName=<%=URLEncoder.encode("表1-7学校校友会","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="">高级检索</a>
	</div>
	-->
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="title1">校友返校交流情况批量导入</h3>
		<div class="fitem" id="item1">
			<form id="batchForm" method="post" enctype="multipart/form-data">
				<select class="easyui-combobox"  id="cbYearContrast" name="selectYear" editable="false"></select>
				<input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox" size="48" style="height: 24px;" required="true" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href='pages/T172/downloadModel?saveFile=<%=URLEncoder.encode("表1-7-2校友返校交流情况（党院办）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
		</div>
		<hr></hr>
		<h3 class="title1">校友返校交流情况逐条导入</h3>
		
		<form id="t172form" method="post">
		<table>
			<tr>
			   <td>
					<div class="fitem">
						<label>校友姓名：</label> 
						<input id="seqNumber" type="hidden" name="t172Bean.SeqNumber" value="0"></input>
						<input id="Time" type="hidden" name="t172Bean.Time" ></input>
						<input id="FriName" name="t172Bean.FriName" class="easyui-validatebox" required="true">
						<span id="FriNameSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				 <td>
					<div class="fitem">
						<label>交流活动名称：</label> 
						<input id="ActName" name="t172Bean.ActName" class="easyui-validatebox" required="true">
						<span id="ActNameSpan"></span>
					</div>
				</td>
			</tr>

			<tr>
			   <td>
					<div class="fitem">
						<label>交流活动类型：</label> 
						<input id="ActType" name="t172Bean.ActType" class="easyui-validatebox" required="true">
						<span id="ActTypeSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				  <td>
					<div class="fitem">
						<label>交流时间：</label> 
						 <input id="ActTime" name="t172Bean.ActTime"  class="easyui-datebox" editable="false">
						 <span id="ActTimeSpan"></span>
					</div>
				</td>
			</tr>
			
			<tr>
			   <td>
					<div class="fitem">
						<label>交流地点：</label> 
						<input id="ActPlace" name="t172Bean.ActPlace" class="easyui-validatebox" required="true">
						<span id="ActPlaceSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				  <td>
					<div class="fitem">
						<label>协办单位：</label> 
						 <input id="UnitName" name="t172Bean.UnitName"  class="easyui-validatebox" editable="false">
						 <span id="UnitNameSpan"></span>
					</div>
				</td>
			</tr>					
			
			<tr>
				<td>
					<div class="fitem">
						<label>单位号：</label> 
						<input id="UnitID" name="t172Bean.UnitID"  class="easyui-validatebox" editable="false">
						 <span id="UnitIDSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td style="valign:left" colspan="3">
				<label>备注：</label>
					<textarea id="Note" name="t172Bean.Note" style="resize:none" cols="50" rows="10"></textarea>
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
	
	var url ;

	 function reloadgrid ()  { 
	        //查询参数直接添加在queryParams中 
	         var queryParams = $('#commomData').datagrid('options').queryParams;  
	         queryParams.seqNum = $('#seqNum').val(); 
	         queryParams.startTime = $('#startTime').datetimebox('getValue');	         		     
	    	 queryParams.endTime  = $('#endTime').datetimebox('getValue');        	 
	         $("#commomData").datagrid('reload'); 
	    }
	
	function singleSearch(){
   	 $('#auditing').form('submit',{
   		 url: 'pages/T172/singleSearch',
   		 type: "post",
	     dataType: "json",
   		 success: function(result){
   		 	var result = eval('('+result+')');
   		 	if (!result.state){
   		 		$.messager.show({
   		 			title: 'Error',
   		 			msg: result.errorMsg
   			 });
   		 	} else {
		    	$('#commomData').datagrid('load'); // reload the auditing data
   		 	}
   		 }
   		 });
   }
	
	    function batchImport(){
	    	 $('#batchForm').form('submit',{
	    		 url: 'pages/T172/uploadFile',
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
			    		 $('#commomData').datagrid('reload'); // reload the user data
	    		 	}
	    		 }
	    		 });
	    	 loadDictionary();
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
	    	url = 'pages/T172/insert' ;
		    $('#dlg').dialog('open').dialog('setTitle','添加校友返校交流情况的信息');
		    $('#t172form').form('reset');
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

	    function singleImport(){
		    //录入数据的表单提交
	    	 $('#t172form').form('submit',{
				    url: url,
				    data: $('#t172form').serialize(),
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
			var FriName = $('#FriName').val() ;
			var ActName = $('#ActName').val() ;
			var ActType = $('#ActType').val() ;
			var ActTime = $('#ActTime').datebox('getValue');
			var ActPlace = $('#ActPlace').val() ;
			var UnitName = $('#UnitName').val() ;
			var UnitID = $('#UnitID').val() ;
			var note = $('#Note').val() ;
			//根据数据库定义的字段的长度，对其进行判断
			if(FriName == null || FriName.length==0 || FriName.length> 200){
				$('#FriName').focus();
				$('#FriName').select();
				alert("校友姓名不能为空或字数不能超过100");
				//$('#ClubNameSpan').html("<font style=\"color:red\">校友会名称不能为空或长度不超过100</font>") ;
				return false ;
			}
			
			if(ActName == null ||ActName.length == 0 || ActName.length> 200){
				$('#ActName').focus();
				$('#ActName').select();
				alert("交流活动名称不能为空或字数不能超过100");
				//$('#PlaceSpan').html("<font style=\"color:red\">地点不能为空</font>") ;
				return false ;
			}
			if(ActType == null ||ActType.length == 0 || ActType.length> 100){
				$('#ActType').focus();
				$('#ActType').select();
				alert("交流活动类型不能为空或字数不能超过50");
				//$('#PlaceSpan').html("<font style=\"color:red\">地点不能为空</font>") ;
				return false ;
			}
			if(ActTime == null ||ActTime.length == 0){
				$('#ActTime').focus();
				$('#ActTime').select();
				alert("交流时间不能为空");
				//$('#PlaceSpan').html("<font style=\"color:red\">地点不能为空</font>") ;
				return false ;
			}
			if(ActPlace == null ||ActPlace.length == 0 || ActPlace.length> 200){
				$('#ActPlace').focus();
				$('#ActPlace').select();
				alert("交流地点不能为空或字数不能超过100");
				//$('#PlaceSpan').html("<font style=\"color:red\">地点不能为空</font>") ;
				return false ;
			}
			if(UnitName == null ||UnitName.length == 0 || UnitName.length> 200){
				$('#UnitName').focus();
				$('#UnitName').select();
				alert("协办单位不能为空或字数不能超过100");
				//$('#PlaceSpan').html("<font style=\"color:red\">地点不能为空</font>") ;
				return false ;
			}
			if(UnitID == null ||UnitID.length == 0 || ActType.length> 50){
				$('#UnitID').focus();
				$('#UnitID').select();
				alert("协办单位编号不能为空或字数不能超过50");
				//$('#PlaceSpan').html("<font style=\"color:red\">地点不能为空</font>") ;
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

	    function edit(){
	    	var row = $('#commomData').datagrid('getSelections');
	     //alert(row.length);
	    	
	    	if(row.length != 1){
	    		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
	    		return ;
	    	}
	    	
	    	url = 'pages/T172/edit' ;
	    	$('.title1').hide();
	       	$('#item1').hide();
	       	$('hr').hide();
	    	
	    	$('#dlg').dialog('open').dialog('setTitle','修改校友返校交流情况的信息');
	    	$('#seqNumber').val(row[0].seqNumber) ;
	    	$('#Time').val(formattime(row[0].time)) ;
	    	$('#FriName').val(row[0].friName) ;
	    	$('#ActName').val(row[0].actName);
	    	$('#ActType').val(row[0].actType);
	    	$('#ActTime').datebox('setValue',formattime(row[0].actTime)) ;
	    	$('#ActPlace').val(row[0].actPlace) ;
	    	$('#UnitName').val(row[0].unitName);
	    	$('#UnitID').val(row[0].unitID);
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
	    		url: "pages/T172/deleteByIds?ids=" + ids, 
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
	    
	    function loadDic(){
		    $('#dicDlg').dialog('open').dialog('setTitle','高级查询');
		    loadDictionary() ;
		    
	    }
	    
	  //提交导出表单
	    function submitForm(){
	    	  document.getElementById('exportForm').submit();
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
		    	var select = document.getElementById("cbYearContrast");
		    	for (var i = 0; i <= 10; i++) {
		        var theOption = document.createElement("option");
		        	theOption.innerHTML = currentYear-i + "年";
		        	theOption.value = currentYear-i;
		        	select.appendChild(theOption);
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
