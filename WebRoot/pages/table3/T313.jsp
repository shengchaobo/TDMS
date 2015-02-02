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

<title>My JSP 'table.jsp' starting page</title>
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
<body style="overflow-y:scroll"  onload="myMarquee('T313','<%=request.getAttribute("CHECKTYPE")%>')">
  <div  id="floatDiv">
        <span style="font:12px; font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;审核不通过提示消息：</span>
        <marquee id="marquee"  scrollAmount="1"  width="900"  height="40" direction="up"  style="color: red;"  onmouseover="stop()" onmouseout="start()">
        </marquee>       
  </div>
  <br/>

	<table id="unverfiedData"  class="easyui-datagrid"  
	url="pages/DiscipRes/auditingData?checkNum=<%=request.getAttribute("NOCHECK")%>" >
		<thead data-options="frozen:true">
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber" >编号</th>
				<th  data-options="field:'checkState'"   formatter="formatCheckState">审核状态</th>
				<th field="discipName" >重点学科名称</th>
				</tr>
				</thead>
				<thead>
				<tr>
				<th field="discipID" >学科代码</th>
				<th field="unitName" >所属教学单位</th>
				<th field="unitID" >单位号</th>
				<th field="discipType" >学科门类</th>
				<th field="nationLevelOne"  formatter="booleanstr">国家一级</th>
				<th field="nationLevelTwo"  formatter="booleanstr">国家二级</th>
				<th field="nationLevelKey" formatter="booleanstr">国家重点（培育）</th>
				<th field="provinceLevelOne" " formatter="booleanstr">省部一级</th>
				<th field="provinceLevelTwo"  formatter="booleanstr">省部二级</th>
				<th field="cityLevel"  formatter="booleanstr">市级</th>
				<th field="schLevel"  formatter="booleanstr">校级</th>
				<th field="note" >备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="height:auto">
		<div style="float: left;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newCourse()">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editDiscip()">编辑</a> 
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
	<div id="toolbar2">
	
	<form action='pages/DiscipRes/dataExport?excelName=<%=URLEncoder.encode("表3-1-3重点学科（科研处）","UTF-8")%>'   method="post"  id="exportForm" enctype="multipart/form-data"  style="float: right;">
					  <select class="easyui-combobox"  id="cbYearContrast1" name="selectYear"  editable=false ></select>&nbsp;&nbsp;
						<a href='javascript:submitForm()'   style="font:12px;color: black;text-decoration:none;" >
								数据导出
						</a> &nbsp;&nbsp;&nbsp;&nbsp;		
			</form>
	</div>
	<table id="verfiedData" title="审核通过数据" class="easyui-datagrid" style="height: auto" 
	url="pages/DiscipRes/auditingData?checkNum=<%=request.getAttribute("PASS")%>"
		toolbar="#toolbar2" pagination="true"
		 singleSelect="false">
		<thead data-options="frozen:true">
			<tr>
				<!--  <th data-options="field:'ck',checkbox:true">选取</th>-->
				<th field="seqNumber" >编号</th>
				<th field="discipName" >重点学科名称</th>
			</tr>
				</thead>
				<thead>
				<tr>
				<th field="discipID" >学科代码</th>
				<th field="unitName" >所属教学单位</th>
				<th field="unitId" >单位号</th>
				<th field="discipType" >学科门类</th>
				<th field="nationLevelOne" >国家一级</th>
				<th field="nationLevelTwo" >国家二级</th>
				<th field="nationLevelKey" >国家重点（培育）</th>
				<th field="provinceLevelOne" ">省部一级</th>
				<th field="provinceLevelTwo" >省部二级</th>
				<th field="cityLevel" >市级</th>
				<th field="schLevel" >校级</th>
				<th field="note" >备注</th>
			</tr>
		</thead>
	</table>
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="title1">重点学科库批量导入</h3>
		<div class="fitem" id="item1">
			<form id="batchForm" method="post" enctype="multipart/form-data">
				<select class="easyui-combobox"  id="cbYearContrast" name="selectYear" editable=false></select>
				<input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox" size="48" style="height: 24px;"
					validType="fileType['xls']" required="true" invalidMessage="请选择Excel格式的文件" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href='pages/DiscipRes/downloadModel?saveFile=<%=URLEncoder.encode("表3-1-3重点学科（科研处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
			<a href="123"></a>
		</div>
		<hr></hr>	
		<h3 class="title1">重点学科库逐条导入</h3>
		
		<form id="discipForm" method="post">
		<table>
			<tr>
				<td>
					<div class="fitem">
						<label>重点学科名称：</label> 
						<input id="SeqNumber" name="discipBean.SeqNumber" type="hidden" > </input>
						<input id="Time" name="discipBean.Time" type="hidden"  value ="0"> </input>
						<input id="DiscipName" type="text" name="discipBean.DiscipName"
							class="easyui-validatebox" ><span id="DiscipNameSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>学科代码：</label> 
						<input id="DiscipID" type="text" name="discipBean.DiscipID"
							class="easyui-validatebox" ><span id="DiscipIDSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>所属单位：</label> 
						<!-- 下边的onselect方法是为了后台既要教学单位名称，有需要教学单位编号，而我们只有一个下拉框包含了这两条信息 -->
						<input type="hidden" name="discipBean.UnitName" id="UnitName"/>
						<input id="UnitID" type="text" name="discipBean.UnitID" 
							 class='easyui-combobox' data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDIDepartmentAca',listHeight:'auto',editable:false,
							 onSelect:function(){
							 	document.getElementById('UnitName').value=$(this).combobox('getText') ;
							 }">
						<span id="UnitNameSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>学科门类：</label> 
						<select class='easyui-combobox' id="DiscipType" name="discipBean.DiscipType" editable=false panelHeight="auto">
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
						<span id="DiscipTypeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
			<td>
					<div class="fitem">
						<label>国家一级：</label> 
						<select class='easyui-combobox' id="NationLevelOne" name="discipBean.NationLevelOne" editable=false panelHeight="auto">
							<option value="false">否</option>
							<option value="true">是</option>
						</select>
						<span id="NationLevelOneSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
			<td>
					<div class="fitem">
						<label>国家二级：</label> 
						<select class='easyui-combobox' id="NationLevelTwo" name="discipBean.NationLevelTwo" editable=false panelHeight="auto">
							<option value="false">否</option>
							<option value="true">是</option>
						</select>
						<span id="NationLevelTwoSpan"></span>
					</div>
				</td>
				</tr>
				<tr>
							<td>
					<div class="fitem">
						<label>国家重点：</label> 
						<select class='easyui-combobox' id="NationLevelKey" name="discipBean.NationLevelKey" editable=false panelHeight="auto">
							<option value="false">否</option>
							<option value="true">是</option>
						</select>
						<span id="NationLevelKeySpan"></span>
					</div>
				</td>
			</tr>
			<tr>

			<td>
					<div class="fitem">
						<label>省部一级：</label> 
						<select class='easyui-combobox' id="ProvinceLevelOne" name="discipBean.ProvinceLevelOne" editable=false panelHeight="auto">
							<option value="false">否</option>
							<option value="true">是</option>
						</select>
						<span id="ProvinceLevelOneSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>省部二级：</label> 
						<select class='easyui-combobox' id="ProvinceLevelTwo" name="discipBean.ProvinceLevelTwo" editable=false panelHeight="auto">
							<option value="false">否</option>
							<option value="true">是</option>
						</select>
						<span id="ProvinceLevelTwoSpan"></span>
					</div>
				</td>
			</tr>
			<tr>

			<td>
					<div class="fitem">
						<label>市级：</label> 
						<select class='easyui-combobox' id="CityLevel" name="discipBean.CityLevel" editable=false panelHeight="auto">
							<option value="false">否</option>
							<option value="true">是</option>
						</select>
						<span id="CityLevelSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>校级：</label> 
						<select class='easyui-combobox' id="SchLevel" name="discipBean.SchLevel" editable=false panelHeight="auto">
							<option value="false">否</option>
							<option value="true">是</option>
						</select>
						<span id="SchLevelSpan"></span>
					</div>
				</td>
			</tr>

			
			<tr>

				<td style="valign:left" colspan="3"><label>备&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
					<textarea id="Note" name="discipBean.Note" style="resize:none" cols="50" rows="10"></textarea>
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
	    		 url: 'pages/DiscipRes/uploadFile',
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
	    		 $.messager.alert("操作提示", "请先选择要导入的文件！");
	    		return false ;
	    	}
	    	
	    	var pos = fileName.lastIndexOf(".") ;
	    	var suffixName = fileName.substring(pos, fileName.length) ;
	    	
	    	if(suffixName == ".xls"){
	    		return true ;
	    	}else{
	    		 $.messager.alert("操作提示", "请选择正确的Excel文件（后缀为.xls）");
	    		return false ;
	    	}
	    } 
	    
	    function newCourse(){
	    	$('.title1').show();
	    	$('#item1').show();
	    	$('hr').show();
	    	url='pages/DiscipRes/insert',
		    $('#dlg').dialog('open').dialog('setTitle','添加重点学科库');
		    $('#discipForm').form('reset');
	    }


	    function singleImport(){
	
		    //录入数据的表单提交
	    	 $('#discipForm').form('submit',{
				    url: url,
				    data: $('#discipForm').serialize(),
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
								myMarquee('T313', CTypeOne);
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
			var DiscipName = $('#DiscipName').val() ;
			var DiscipID = $('#DiscipID').val() ;
			var UnitName = $('#UnitID').combobox('getText') ;
			var Note = $('#Note').val() ;
			//根据数据库定义的字段的长度，对其进行判断
			if(DiscipName == null || DiscipName.length==0 ){
				$.messager.alert('提示',"学科名称不能为空");
				return false;
			}
			
			if(DiscipID == null || DiscipID.length == 0 ){
				$.messager.alert('提示',"学科代码不能为空");
				return false;
			}
			
			if(UnitName == null || UnitName.length == 0){
				$.messager.alert('提示',"所属单位不能为空");
				return false;
			}
			

			
			if(Note !=null && Note.length > 1000){
				$.messager.alert('提示',"备注中文字数不超过500");
				return false;
			}
			return true ;
		}

		function singleSearch(){
		   	 $('#auditing').form('submit',{
		   		 url: 'pages/DiscipRes/singleSearch',
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

	    function editDiscip(){
	    	var row = $('#unverfiedData').datagrid('getSelections');
	    	
	    	if(row.length != 1){
	    		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
	    		return ;
	    	}
	    	
	    	url = 'pages/DiscipRes/edit' ;

	    	$('.title1').hide();
	    	$('#item1').hide();
	    	$('hr').hide();
	    	
	    	
	    	$('#dlg').dialog('open').dialog('setTitle','修改重点学科');
	    	$('#SeqNumber').val(row[0].seqNumber) ;
	    	$('#Time').val(formattime(row[0].time)) ;
	        $('#DiscipName').val(row[0].discipName);
	        $('#DiscipID').val(row[0].discipID);
	        $('#UnitID').combobox('select',row[0].unitID) ;
	        $('#DiscipType').combobox('select',""+row[0].discipType);
	        $('#NationLevelOne').combobox('select',""+row[0].nationLevelOne);
	        $('#NationLevelTwo').combobox('select',""+row[0].nationLevelTwo);
	        $('#NationLevelKey').combobox('select',""+row[0].nationLevelKey);
	        $('#ProvinceLevelOne').combobox('select',""+row[0].provinceLevelOne);
	        $('#ProvinceLevelTwo').combobox('select',""+row[0].provinceLevelTwo);
	        $('#CityLevel').combobox('select',""+row[0].cityLevel);
	        $('#SchLevel').combobox('select',""+row[0].schLevel);
			$('#Note').val(row[0].note);
		
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
				 	
				 	deleteDiscip(ids) ;
				 	
				 }
			});
	    }


	    function exports() {
	    	var temp = encodeURI('表3-1-3重点学科（科研处）');
		    $('#exportForm').form('submit', {
		    url : "pages/DiscipRes/dataExport?excelName="+temp ,
		    onSubmit : function() {
		    return $(this).form('validate');//对数据进行格式化
		    },
		    success : function(data) {
		    $.messager.show({
		    	title : '提示',
		    	msg : data
		    });
		    }
		    }); 
	    }

	    function deleteDiscip(ids){
	    	$.ajax({ 
	    		type: "POST", 
	    		url: "pages/DiscipRes/deleteCoursesByIds?ids=" + ids, 
	    		async:"true",
	    		dataType: "text",
	    		success: function(result){
	    			result = eval("(" + result + ")");

					if(result.state){
						alert(result.data) ;
						myMarquee('T313', CTypeOne);
						 $('#unverfiedData').datagrid('reload') ;
					}
	    		}
	    	}).submit();
	    }
	    
	    
	    function loadDic(){
		    $('#dicDlg').dialog('open').dialog('setTitle','高级查询');
		    loadDictionary() ;
		    
	    }
	    


	    
	   
	  
	  function submitDicForm(){
		  $.ajax({ 
	    		type: "POST", 
	    		url: "table3/dictorySearch",
	    		data: $('#dicsDataForm').serialize(), 
	    		async:"false",
	    		dataType: "text",
	    		success: function(data){
	    			alert(123) ;
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
