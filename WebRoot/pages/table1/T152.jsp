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

<title>教学单位科研机构</title>
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

<% request.setAttribute("CHECKTYPE",Constants.CTypeTwo); %>
<% request.setAttribute("NOCHECK",Constants.NO_CHECK); %>
<% request.setAttribute("PASS",Constants.PASS_CHECK); %>

<body style="height: 100%"   onload="myMarquee('T152','<%=request.getAttribute("CHECKTYPE")%>')">

<div  id="floatDiv">
        <span style="font:12px; font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;审核不通过提示消息：</span>
        <marquee id="marquee"  scrollAmount="1"  width="900"  height="40" direction="up"  style="color: red;"  onmouseover="stop()" onmouseout="start()">
        </marquee>       
  </div>
  </br>
  
	<table id="unverfiedData" class="easyui-datagrid"  
	url="pages/T152/auditingData?checkNum=<%=request.getAttribute("NOCHECK")%>" style="height: auto"  > 
		<thead data-options="frozen:true">
					<tr>			
					<th data-options="field:'ck',checkbox:true">选取</th>
					<th field="seqNumber">编号</th>
					<th  data-options="field:'checkState'"   formatter="formatCheckState">审核状态</th>
					<th field="resInsName" >科研机构名称</th>
					<th field="resInsID" >单位号</th>
		   	  		</tr>
		</thead>
		<thead>
			<tr>
				<th field="type" >类别</th>
				<th field="buildCondition"  formatter="booleanstr" >共建情况</th>
				<th field="biOpen"  formatter="booleanstr" >是否对本科生开放</th>
				<th field="openCondition" >对本科生开放情况（500字以内）</th>
				<th field="teaUnit" >所属教学单位</th>
				<th field="unitID" >教学单位号</th>
				<th field="beginYear" fit="true" formatter="formattime">开设年份</th>
				<th field="houseArea" >专业科研用房面积（平方米）</th>
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
	
	
	<table id="verfiedData" class="easyui-datagrid"  url="pages/T152/auditingData?checkNum=<%=request.getAttribute("PASS")%>"  style="height: auto;" >
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber">编号</th>
				<th field="resInsName" >科研机构名称</th>
				<th field="resInsID" >单位号</th>
		     </tr>
		</thead>
		<thead>
			<tr>
				
				<th field="type" >类别</th>
				<th field="buildCondition" formatter="booleanstr">共建情况</th>
				<th field="biOpen"  formatter="booleanstr">是否对本科生开放</th>
				<th field="openCondition"  >对本科生开放情况（500字以内）</th>
				<th field="teaUnit" >所属教学单位</th>
				<th field="unitID" >教学单位号</th>
				<th field="beginYear"  fit="true" formatter="formattime">开设年份</th>
				<th field="houseArea" >专业科研用房面积（平方米）</th>
				<th field="note" >备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar2" style="float: right;">
	<form action='pages/T152/dataExport?excelName=<%=URLEncoder.encode("表1-5-2教学单位科研机构","UTF-8")%>'   
	method="post"  id="exportForm" enctype="multipart/form-data"  style="float: right;">
					  <select class="easyui-combobox"  id="cbYearContrast1" name="selectYear"  editable=false ></select>&nbsp;&nbsp;
						<a href='javascript:submitForm()'   style="font:12px;color: black;text-decoration:none;" >
								数据导出
						</a> &nbsp;&nbsp;&nbsp;&nbsp;		
			</form>
	
		<!--  <a href='pages/T152/dataExport?excelName=<%=URLEncoder.encode("表1-5-2教学单位科研机构","UTF-8")%>' 
		 class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 
		 -->
	</div>
	
	
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="title1">教学单位科研机构批量导入</h3>
		<div class="fitem" id="item1">
			<form id="batchForm" method="post" enctype="multipart/form-data">
				<select class="easyui-combobox"  id="cbYearContrast" name="selectYear" editable="false"></select>
				<input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox" size="48" style="height: 24px;" required="true" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href='pages/T152/downloadModel?saveFile=<%=URLEncoder.encode("表1-5-2教学单位科研机构.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
		</div>
		<hr></hr>	
		<h3 class="title1">教学单位科研机构逐条导入</h3>
		
		<form id="addForm" method="post">
		<table>
			<tr>
				<td>
					<div class="fitem">
						<label>科研机构名称：</label> 
						<input id="seqNumber" type="hidden"name="t152Bean.SeqNumber" value="0"></input>
						<input id="Time" type="hidden"name="t152Bean.Time" ></input>
						<input id="TeaUnit" type="hidden"name="t152Bean.TeaUnit" value="0"></input>
						<input id="UnitID" type="hidden"name="t152Bean.UnitID" value="0"></input>
						<input id="FillUnitID" type="hidden"name="t152Bean.FillUnitID" value="0"></input>
					<!--	<input id="fillUnitID" type="hidden"name="t152Bean.fillUnitID" value="0"></input> -->
						<input type="hidden" name="t152Bean.ResInsName" id="ResInsName"/>
						<input id="ResInsID" type="text" name="t152Bean.ResInsID" class='easyui-combobox' 
							data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDIDepartmentSci' ,listHeight:'auto',editable:false,
							onSelect:function(){
							 	$('#ResInsName').val($(this).combobox('getText')) ;
							 }">
					    <span id="ResInsNameSpan"></span>	
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>类别：</label> 
						<input id="Type"  name="t152Bean.Type" class='easyui-combobox'
						data-options="valueField:'indexId',textField:'researchType',url:'pages/DiResearchType/loadDiResearchType',listHeight:'auto',editable:false">
						<span id="TypeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>开设年份：</label> 
						 <input class="easyui-datebox" id="BeginYear" name="t152Bean.BeginYear" editable="false">
						 <span id="BeginYearSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td style="valign:left" colspan="3">
					<div class="fitem">
						<label>专业科研用房面积（平方米）：</label> 
						<input id="HouseArea" type="text" name="t152Bean.HouseArea" 
						class="easyui-numberbox"  data-options="min:1,precision:2" required="true">
						   <span id="HouseAreaSpan"></span>
					</div>
				</td>
				<!--  
				<td>
					<div class="fitem">
						<label>所属教学单位：</label> 
						<input type="hidden" name="t152Bean.TeaUnit" id="TeaUnit"/>
						<input id="UnitID" type="text" name="t152Bean.UnitID" class='easyui-combobox' 
							data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDIDepartmentAca' ,listHeight:'auto',editable:false,
							onSelect:function(){
							    $('#TeaUnit').val($(this).combobox('getText')) ;
							 }">
							 
					    <span id="TeaUnitSpan"></span>
					</div>
				</td>
				-->
			</tr>
			<tr>
			    <td>
					<div class="fitem">
						<label>共建情况：</label> 
						<select class='easyui-combobox' id='BuildCondition' name='t152Bean.BuildCondition' style="width:50px" editable="false">
						   <option value="true">是</option>
						   <option value="false">否</option> 
						</select>
						<span id="BuildConditionSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>是否对本科生开放：</label> 
						<select class='easyui-combobox' id='BiOpen' name='t152Bean.BiOpen' style="width:50px" editable="false">
						   <option value="true">是</option>
						   <option value="false">否</option> 
						</select>
						 <span id="BiOpenSpan"></span>
					</div>
				</td>
			</tr>
		    <tr>
				<td style="valign:left" colspan="3">
					<div class="fitem">
						<label>对本科生开放情况（500字以内）：</label> 
						<textarea id="OpenCondition" name="t152Bean.OpenCondition" style="resize:none" cols="50" rows="10"></textarea>
						<span id="OpenConditionSpan"></span>
						</div>
				</td>
			</tr>
			<tr>
			
			   <td style="valign:left" colspan="3">
			        <div class="fitem">
				    <label>备注：</label>
					<textarea id="Note" name="t152Bean.Note" style="resize:none" cols="50" rows="10"></textarea>
					<span id="NoteSpan"></span>
					</div>
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
	         //alert(queryParams.queryYear);  
	         $("#verfiedData").datagrid('reload'); 
	        }
	   });
    })
	
	function singleSearch(){
		//alert(123);
   	 $('#auditing').form('submit',{
   		 url: 'pages/UndergraCSBaseTea/singleSearch',
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
	    		 url: 'pages/T152/uploadFile',
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
	    		$.messager.alert("操作提示","文件格式不正确，请选择后缀为“.xls”的文件！");
	    		return false ;
	    	}
	    } 
	    
	    function newObject(){

	    	$('.title1').show();
	    	$('#item1').show();
	    	$('hr').show();
	    	url = 'pages/T152/insert' ;
		    $('#dlg').dialog('open').dialog('setTitle','添加教学单位科研机构的信息');
		    $('#addForm').form('reset');
	    }

	    function singleImport(){
		    //录入数据的表单提交
	    	 $('#addForm').form('submit',{
				    url: url ,
				    data: $('#resInsForm').serialize(),
		            type: "post",
		            dataType: "json",
				    onSubmit: function(){
				    	return validate();
				    //	$('#dlg').dialog('reload');
				    },
				    //结果返回
				    success: function(result){
					    //json格式转化
					    var result = eval('('+result+')');
					    $.messager.alert('温馨提示', result.data) ;
					    if (result.state){ 
						    if(result.tag==2){
						    	$('#dlg').dialog('close');
								myMarquee('T152', CTypeTwo);
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
			//var reg=/^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$/ ;
			var resInsName = $('#ResInsName').val();
			var type = $('#Type').combobox('getText') ;
			var beginYear= $('#BeginYear').datebox('getValue') ;
			//var teaUnit = $('#TeaUnit').val();
			var buildCondition = $('#BuildCondition').combobox('getText');
			var biOpen = $('#BiOpen').combobox('getText');
			var houseArea=$('#HouseArea').val();
			var openCondition = $('#OpenCondition').val() ;
			var note = $('#Note').val() ;
			//根据数据库定义的字段的长度，对其进行判断
			if(resInsName == null || resInsName.length == 0){
				$('#ResInsName').focus();
				$('#ResInsName').select();
				//$('#ResInsNameSpan').html("<font style=\"color:red\">科研机构名称不能为空</font>") ;
				alert("科研机构名称不能为空");
				return false ;
			}

			if(type == null || type.length == 0){
				$('#Type').focus();
				$('#Type').select();
				//$('#TypeSpan').html("<font style=\"color:red\">类别不能为空</font>") ;
				alert("类别不能为空");
				return false ;
			}

			if(beginYear == null || beginYear.length == 0){
				$('#BeginYear').focus();
				$('#BeginYear').select();
				alert("开设年份不能为空");
               // $('#BeginYearSpan').html("<font style=\"color:red\">开设年份不能为空</font>") ;
                return false;
			}
         
			//if(teaUnit == null || teaUnit.length == 0){
			//	$('#TeaUnit').focus();
			//	$('#TeaUnit').select();
			//	alert("教学单位不能为空");
               // $('#TeaUnitSpan').html("<font style=\"color:red\">教学单位不能为空</font>") ;
             //   return false;
			//}
         
			if(buildCondition == null || buildCondition.length == 0){
				$('#BuildCondition').focus();
				$('#BuildCondition').select();
				alert("共建情况不能为空");
                //$('#BuildConditionSpan').html("<font style=\"color:red\">共建情况不能为空</font>") ;
                return false;
			}

			if(biOpen == null || biOpen.length == 0){
				$('#BiOpen').focus();
				$('#BiOpen').select();
				alert("开放情况不能为空");
               // $('#BiOpenSpan').html("<font style=\"color:red\">开放情况不能为空</font>") ;
                return false;
			}

		if(houseArea == null || houseArea.length == 0){
			$('#HouseArea').focus();
			$('#HouseArea').select();
			alert("面积不能为空且只能保留两位小数");
				//$('#HouseAreaSpan').html("<font style=\"color:red\">面积不能为空</font>");
			    return false;
			}
			
			if(openCondition == null || openCondition.length==0 || openCondition.length > 500){
				$('#OpenCondition').focus();
				$('#OpenCondition').select();
				alert("对本科生开放情况不能为空或字数不超过500");
				//$('#OpenConditionSpan').html("<font style=\"color:red\">对本科生开放情况不能为空或长度不超过100</font>") ;
				return false ;
			}
			
			if(note.length > 1000){
				$('#Note').focus();
				$('#Note').select();
				alert("备注中文字数不超过500");
				//$('#NoteSpan').html("<font style=\"color:red\">备注中文字数不超过500</font>") ;
				return false ;
			}
			return true ;
		}

		//编辑数据
	    function edit(){
	    	var row = $('#unverfiedData').datagrid('getSelections');
	    	if(row.length != 1){
	    		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
	    		return ;
	    	}
	    	
	    	url = 'pages/T152/edit' ;

	    	$('.title1').hide();
	       	$('#item1').hide();
	       	$('hr').hide();
	    	
	    	$('#dlg').dialog('open').dialog('setTitle','修改教学单位科研机构的信息');
	    	$('#seqNumber').val(row[0].seqNumber) ;
	    	$('#Time').val(formattime(row[0].time)) ;
	    	$('#FillUnitID').val((row[0].fillUnitID)) ;
	    
	    	$('#ResInsID').combobox('select',row[0].resInsID);
	    	$('#Type').combobox('select',row[0].typeID);
	    	$('#BeginYear').datebox('setValue',formattime(row[0].beginYear)) ;
	    	//alert(formattime(row[0].beginYear));
	    	$('#UnitID').val(row[0].unitID) ;
	    	$('#TeaUnit').val(row[0].teaUnit) ;
	    	var flag1 = "" + row[0].biOpen ;
	    	var flag2 = "" + row[0].buildCondition ;
	    	$('#BuildCondition').combobox('select', flag2) ;
	    	$('#BiOpen').combobox('select',flag1) ;
	    	$('#HouseArea').val(row[0].houseArea) ;
	    	$('#OpenCondition').val(row[0].openCondition) ;
			$('#Note').val(row[0].note) ;
	    }

	    //删除数据
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
	    		url: "pages/T152/deleteByIds?ids=" + ids, 
	    		async:"true",
	    		dataType: "text",
	    		success: function(result){
	    			result = eval("(" + result + ")");

					if(result.state){
						alert(result.data) ;
						myMarquee('T152', CTypeTwo);
						 $('#unverfiedData').datagrid('reload') ;
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
			
				<!--导入数据年份选择 -->
			<script type="text/javascript"> 
		    function booleanstr(val) { 	 
		    	if(val == null){
					return null ;
				}
				var bo1=""+val;//吧boolean型转换成str类型再判断
				var boo;
				if( bo1 == "false") {
					boo="否" ;
				}else if (bo1 == "true"){

					boo="是" ;
				}
				return boo;
	        }  
			</script>
			
			<!--导出表年份选择 -->
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
