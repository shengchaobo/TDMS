<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="cn.nit.constants.Constants"%>

<%@ page import="java.net.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>T181</title>
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

<% request.setAttribute("CHECKTYPE",Constants.CTypeOne); %>
<% request.setAttribute("NOCHECK",Constants.NO_CHECK); %>
<% request.setAttribute("PASS",Constants.PASS_CHECK); %>
<body style="overflow-y:scroll"  onload="myMarquee('T181','<%=request.getAttribute("CHECKTYPE") %>')">
<div  id="floatDiv">
        <span style="font:12px; font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;审核未通过提示消息：</span>
        <marquee id="marquee"  scrollAmount="1"  width="900"  height="40" direction="up"  style="color: red;"  onmouseover="stop()" onmouseout="start()">
        </marquee>       
  </div>
  <br/> 

	<table id="unverfiedData" class="easyui-datagrid"  url="pages/T181/auditingData?checkNum=<%=request.getAttribute("NOCHECK") %>" style="height: auto">
		<thead>
			<tr>
				  <th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber"  >编号</th>
				<th  data-options="field:'checkState'"   formatter="formatCheckState">审核状态</th>
				<th field="cooperInsName"  >合作机构名称</th>
				<th field="cooperInsType"  >合作机构类型</th>
				<th field="cooperInsLevel"  >合作机构级别</th>
				<th field="signedTime"   fit="true" formatter="formattime">签订协议时间</th>
				<th field="unitName" >我方单位</th>
				<th field="unitID"  >单位号</th>
				<th field="unitLevel"  >我方单位级别</th>
				<th field="note"  >备注</th>
			</tr>
		</thead>	
	</table>
	<p></p>
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
	
	<table id="verfiedData" class="easyui-datagrid" url="pages/T181/auditingData?checkNum=<%=request.getAttribute("PASS") %>"  style="height: auto;">
		<thead>
			<tr>
			<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber" >编号</th>
				<th field="cooperInsName" >合作机构名称</th>
				<th field="cooperInsType" >合作机构类型</th>
				<th field="cooperInsLevel" >合作机构级别</th>
				<th field="signedTime" fit="true" formatter="formattime">签订协议时间</th>
				<th field="unitName" >我方单位</th>
				<th field="unitID" >单位号</th>
				<th field="unitLevel" >我方单位级别</th>
				<th field="note" >备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar2" >
	<form action='pages/T181/dataExport?excelName=<%=URLEncoder.encode("表1-8-1签订合作协议机构（教务处）","UTF-8")%>'   method="post"  id="exportForm" enctype="multipart/form-data"  style="float: right;">
					  <select class="easyui-combobox"  id="cbYearContrast1" name="selectYear"  editable=false ></select>&nbsp;&nbsp;
						<a href='javascript:submitForm()'   style="font:12px;color: black;text-decoration:none;" >
								数据导出
						</a> &nbsp;&nbsp;&nbsp;&nbsp;		
			</form>
	</div>
	
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="title1">签订合作协议机构批量导入</h3>
		<div class="fitem" id="item1">
			<form id="batchForm" method="post" enctype="multipart/form-data">
				<select class="easyui-combobox"  id="cbYearContrast" name="selectYear" editable="false"></select>
				<input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox" size="48" style="height: 24px;"  required="true" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href='pages/T181/downloadModel?saveFile=<%=URLEncoder.encode("表1-8-1签订合作协议机构.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
		</div>
		<hr ></hr>	
		<h3 class="title1">签订合作协议机构逐条导入</h3>
		
		<form id="t181Form" method="post">
		<table>
			<tr>
				<td>
					<div class="fitem">
						<label>合作机构名称：</label> 
						<input id="seqNumber" name="t181Bean.SeqNumber", type="hidden" value="0"></input>
						<input id="Time" name="t181Bean.Time", type="hidden"></input>
							<input id="FillDept" name="t181Bean.FillDept", type="hidden" value="0"></input>
						<!--  <input id="FillDept" name="t181Bean.FillDept", type="hidden" value="0"></input>-->
						<input id="CooperInsName" type="text" name="t181Bean.CooperInsName"
							class="easyui-validatebox" required="true"><span id="CooperInsNameSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>合作机构类型：</label> 
						<select class='easyui-combobox' id="CooperInsType" name="t181Bean.CooperInsType"  style="width:140px" editable="false">
							<option value="学术机构">学术机构</option>
							<option value="行业机构和企业">行业机构和企业</option>
							<option value="地方政府">地方政府</option>
						</select>	
						<span id="CooperInsTypeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
				   <div class="fitem">
				   		<label>合作机构级别：</label> 
				   		<input class='easyui-combobox' id="CooperInsLevel" name="t181Bean.CooperInsLevel" 
							data-options="valueField:'indexId',textField:'awardLevel',url:'pages/DiAwardLevel/loadDiAwardLevel',listHeight:'auto',editable:false">
						<span id="CooperInsLevelSpan"></span>
					</div>
				</td>
				<td class="empty"></td>	
				<td>
					<div class="fitem">
						<label>签订时间：</label> 
						<input class="easyui-datebox" id="SignedTime" name="t181Bean.SignedTime"  editable="false">
					   <span id="SignedTimeSpan"></span>
					</div>
				</td>
		  </tr>
		  <tr>
				<td>
				    <div class="fitem">
				   		<label>我方单位：</label> 
						<!-- 下边的onselect方法是为了后台既要教学单位名称，有需要教学单位编号，而我们只有一个下拉框包含了这两条信息 -->
						<input type="hidden" name="t181Bean.UnitName" id="UnitName"/>
						<input id="UnitID" type="text" name="t181Bean.UnitID" 
							 class='easyui-combobox' data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment',listHeight:'auto',editable:false,
							 onSelect:function(){
							 	document.getElementById('UnitName').value=$(this).combobox('getText') ;
							 }">
						<span id="UnitNameSpan"></span>
					</div>
			  </td>
			  <td class="empty"></td>
			  <td>
					<div class="fitem">
						<label>我方单位级别：</label> 
						<input class='easyui-combobox' id="UnitLevel" name="t181Bean.UnitLevel" 
							data-options="valueField:'indexId',textField:'awardLevel',url:'pages/DiAwardLevel/loadDIAwardLevelPartTwo',listHeight:'auto',editable:false">
							<span id="UnitLevelSpan"></span>
					</div>
			</td>
			</tr>
			<tr>
					<td style="valign:left" colspan="3">
				    <label>备&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
					<textarea id="Note" name="t181Bean.Note" style="resize:none" cols="50" rows="10"></textarea>
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
	         var queryParams = $('#unverfiedData').datagrid('options').queryParams;  
	         queryParams.seqNum = $('#seqNum').val(); 
	         queryParams.startTime = $('#startTime').datetimebox('getValue');	         		     
	    	 queryParams.endTime  = $('#endTime').datetimebox('getValue');        	 
	         $("#unverfiedData").datagrid('reload'); 
	    }
	    
	        //根据用户选择的年显示相应年的数据
    $(function(){ 
		 $("#cbYearContrast1").combobox({  
	        onChange:function(newValue, oldValue){ 
		     //查询参数直接添加在queryParams中 
	         var  queryYear = newValue;
	         var queryParams = $('#verfiedData').datagrid('options').queryParams;  
	         queryParams.queryYear = queryYear;  
	         $("#verfiedData").datagrid('reload'); 
	        }
	   });
    })
	
	function singleSearch(){
   	 $('#auditing').form('submit',{
   		 url: 'pages/T181/singleSearch',
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
		    	$('#unverfiedData').datagrid('load'); // reload the auditing data
   		 	}
   		 }
   		 });
   }
	
	    function batchImport(){
	    	 $('#batchForm').form('submit',{
	    		 url: 'pages/T181/uploadFile',
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
	    	url = 'pages/T181/insert' ;
		    $('#dlg').dialog('open').dialog('setTitle','添加签订合作协议机构的信息');
		    $('#t181Form').form('reset');
	    }

	    function singleImport(){
		    //录入数据的表单提交

	    	 $('#t181Form').form('submit',{
				    url: url ,
				    data: $('#t181Form').serialize(),
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
						    if(result.tag==2){
						    	$('#dlg').dialog('close');
								myMarquee('T181', CTypeOne);
								$('#unverfiedData').datagrid('reload'); // reload the user data

							}else{
								
						    $('#dlg').dialog('close'); 
						    $('#unverfiedData').datagrid('reload');  
					    }
				    }
				   }
			    });
		}

		function validate(){
			//获取文本框的值
			var cooperInsName = $('#CooperInsName').val() ;
			var cooperInsType = $('#CooperInsType').combobox('getText') ;
			var cooperInsLevel = $('#CooperInsLevel').combobox('getText') ;
			var signedTime=$('#SignedTime').combobox('getText');
			var unitName = $('#UnitName').val() ;
			var unitLevel = $('#UnitLevel').combobox('getText') ;
			var note = $('#Note').val() ;
			//根据数据库定义的字段的长度，对其进行判断
			if(cooperInsName == null || cooperInsName.length==0 || cooperInsName.length > 100){
				$('#CooperInsName').focus();
				$('#CooperInsName').select();
				alert("合作机构名称不能为空或长度不超过100");
				//$('#CooperInsNameSpan').html("<font style=\"color:red\">合作机构名称不能为空或长度不超过100</font>") ;
				return false ;
			}
			
			if(cooperInsType == null || cooperInsType.length == 0){
				$('#CooperInsType').focus();
				$('#CooperInsType').select();
				alert("合作机构类型不能为空");
				//$('#CooperInsTypeSpan').html("<font style=\"color:red\">合作机构类型不能为空</font>") ;
				return false ;
			}
			
			if(cooperInsLevel == null || cooperInsLevel.length == 0){
				$('#CooperInsLevel').focus();
				$('#CooperInsLevel').select();
				alert("合作机构级别不能为空");
				//$('#CooperInsLevelSpan').html("<font style=\"color:red\">合作机构级别不能为空</font>") ;
				return false ;
			}
			
			if(signedTime == null || signedTime.length == 0){
				$('#SignedTime').focus();
				$('#SignedTime').select();
				alert("签订时间不能为空");
				//$('#SignedTimeSpan').html("<font style=\"color:red\">签订时间不能为空</font>") ;
				return false ;
			}
			
			if(unitName == null || unitName.length == 0){
				$('#UnitName').focus();
				$('#UnitName').select();
				alert("我方单位不能为空");
				//$('#UnitNameSpan').html("<font style=\"color:red\">我方单位不能为空</font>") ;
				return false ;
			}
			
			if(unitLevel == null || unitLevel.length == 0){
				$('#UnitLevel').focus();
				$('#UnitLevel').select();
				alert("我方单位级别 不能为空");
				//$('#UnitLevelSpan').html("<font style=\"color:red\">我方单位级别 不能为空</font>") ;
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
	    	var row = $('#unverfiedData').datagrid('getSelections');
	    	
	    	if(row.length != 1){
	    		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
	    		return ;
	    	}
	    	
	    	url = 'pages/T181/edit' ;
	    	$('.title1').hide();
	       	$('#item1').hide();
	       	$('hr').hide();
	    	$('#dlg').dialog('open').dialog('setTitle','修改签订合作协议机构的信息');
	    	$('#seqNumber').val(row[0].seqNumber) ;
	    	$('#Time').val(formattime(row[0].time)) ;
	    	$('#FillDept').val(row[0].fillDept) ;
	    	$('#CooperInsName').val(row[0].cooperInsName);
	    	$('#CooperInsType').combobox('select',row[0].cooperInsType);
	    	//alert($('#CooperInsType').combobox('select',row[0].cooperInsType));
	    	$('#CooperInsLevel').combobox('select',row[0].cooperInsLevelID) ;
	    	$('#SignedTime').datebox('setValue',formattime(row[0].signedTime)) ;
	    	$('#UnitID').combobox('select',row[0].unitID);
	    	$('#UnitLevel').combobox('select',row[0].unitLevelID) ;
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
	    		url: "pages/T181/deleteByIds?ids=" + ids, 
	    		async:"true",
	    		dataType: "text",
	    		success: function(result){
	    			result = eval("(" + result + ")");

					if(result.state){
						alert(result.data);
						myMarquee('T181', CTypeOne);
						 $('#unverfiedData').datagrid('reload') ;
					}
	    		}
	    	}).submit();
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
