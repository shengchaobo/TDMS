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
    
    <title>My JSP 'T311.jsp' starting page</title>
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
	<table id="unverfiedData" class="easyui-datagrid"  url="pages/JuniorMajInfo/auditingData" >
		<thead data-options="frozen:true">
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber" >编号</th>
				<th field="teaUnit" >教学单位</th>
				<th field="unitID" >单位号</th>
				<th field="majorName" >专业名称</th>
				<th field="majorID" >专业代码</th>
				</tr>
				</thead>
				<thead>
				<tr>
				<th field="majorFieldName" >专业方向名称</th>
				<th field="appvlSetTime"  formatter="formattime">批准设置时间</th>
				<th field="firstAdmisTime"  formatter="formattime">首次招生时间</th>
				<th field="majorYearLimit" >修业年限</th>
				<th field="isSepcialMajor"  formatter="booleanstr">特色专业</th>
				<th field="isKeyMajor"  formatter="booleanstr">重点专业</th>
				<th field="majorLeader" >专业带头人姓名</th>
				<th field="LIsFullTime"  formatter="booleanstr">专业带头人是否专职</th>
				<th field="majorChargeMan" >专业负责人姓名</th>
				<th field="CIsFullTime"  formatter="booleanstr">专业负责人是否专职</th>
				<th field="note" >备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="height:auto">
		<div style="float: left;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newJuniorMajInfo()">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editJuniorMajInfo()">编辑</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteByIds()">删除</a>
		</div>

		
		 <div>
		 	<form id="auditing" method="post" style="float: right;height: 26px;">
			 	编号: <input id="seqNum" name="seqNum" class="easyui-numberbox" style="width:80px"/>
				日期 起始: <input id="startTime" name="startTime" class="easyui-datebox" style="width:80px"/>
				结束: <input id="endTime" name="endTime" class="easyui-datebox" style="width:80px"/>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="reloadgrid()">查询</a>
			</form>
		</div>
	</div>
	<div id="toolbar2">
	 <form  id="exportForm"  method="post" style="float: right;">

			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-download" plain="true"  onclick="exports()">数据导出</a>
		</form>
	</div>
	<table id="verfiedData" title="审核通过数据" class="easyui-datagrid" style="height: auto;" 
		toolbar="#toolbar2" pagination="true" 
		 singleSelect="false">
		<thead data-options="frozen:true">
			<tr>
				<th data-options="field:'ck',checkbox:true" >选取</th>
				<th field="SeqNumber" >编号</th>
				</tr>
				</thead>
				<thead>
				<tr>
				<th field="TeaUnit" >教学单位</th>
				<th field="UnitID" >单位号</th>
				<th field="MajorName" >专业名称</th>
				<th field="MajorID" >专业代码</th>
				<th field="MajorFieldName" >专业方向名称</th>
				<th field="AppvlSetTime"  formatter="formattime">批准设置时间</th>
				<th field="FirstAdmisTime"  formatter="formattime">首次招生时间</th>
				<th field="MajorYearLimit" >修业年限</th>
				<th field="IsSepcialMajor" >特色专业</th>
				<th field="IsKeyMajor" >重点专业</th>
				<th field="MajorLeader" >专业带头人姓名</th>
				<th field="LIsFullTime" >专业带头人是否专职</th>
				<th field="MajorChargeMan" >专业负责人姓名</th>
				<th field="CIsFullTime" >专业负责人是否专职</th>
				<th field="Note" >备注</th>
			</tr>
		</thead>
	</table>
<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="title1">专科专业批量导入</h3>
		<div class="fitem"  id="item1">
			<form id="batchForm" method="post" enctype="multipart/form-data">
				<select class="easyui-combobox"  id="cbYearContrast1" name="selectYear" editable=false></select>
				<input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox" size="48" style="height: 24px;"
					validType="fileType['xls']" required="true" invalidMessage="请选择Excel格式的文件" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href='pages/JuniorMajInfo/downloadModel?saveFile=<%=URLEncoder.encode("表3-3专科专业基本情况（教务处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
			<a href="123"></a>
		</div>
			<hr></hr>
		<h3 class="title1">专科专业逐条导入</h3>
		
		<form id="juniorMajInfoForm" method="post">
		<table>
			<tr>
			<td>
					<div class="fitem">
						<label>教学单位：</label> 
						<input id="seqNumber" name="t33_Bean.SeqNumber" type="hidden" > </input>
						<input type="hidden" name="t33_Bean.TeaUnit" id="TeaUnit"/>
						<input id="UnitID" type="text" name="t33_Bean.UnitID" 
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
						<label>专业名称：</label> 
						<input type="hidden" name="t33_Bean.MajorName" id="MajorName"/>
						<input id="MajorID" type="text" name="t33_Bean.MajorID" 
							 class='easyui-combobox' data-options="valueField:'majorNum',textField:'majorName',url:'pages/DiMajorOne/loadDiMajorOne',listHeight:'auto',editable:false,
							 onSelect:function(){
							 	document.getElementById('MajorName').value=$(this).combobox('getText') ;
							 }">
						<span id="MajorNameSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
			<td>
					<div class="fitem">
						<label>专业方向名称：</label> 
						<input id="MajorFieldName" type="text" name="t33_Bean.MajorFieldName"
							class="easyui-validatebox" ><span id="MajorFieldNameSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>批准设置时间：</label> 
						<input id="AppvlSetTime" name="t33_Bean.AppvlSetTime"
							class="easyui-datebox" editable="false" style="width:80px">
							<span id="AppvlSetTimeSpan"></span>
					</div>
				</td>
				
			</tr>
			
		<tr>
			<td>
					<div class="fitem">
						<label>首次招生时间：</label> 
						<input id="FirstAdmisTime" name="t33_Bean.FirstAdmisTime"
							class="easyui-datebox" editable="false" style="width:80px">
							<span id="FirstAdmisTimeSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>修业年限：</label> 
						<input id="MajorYearLimit" type="text" name="t33_Bean.MajorYearLimit"
							class="easyui-validatebox" ><span id="MajorYearLimitSpan"></span>
					</div>
				</td>
				
			</tr>
			<tr>
			<td>
					<div class="fitem">
						<label>是否特色专业：</label> 
						<select class='easyui-combobox' id="IsSepcialMajor" name="t33_Bean.IsSepcialMajor" editable=false panelHeight="auto">
							<option value="false">否</option>
							<option value="true">是</option>
						</select>
						<span id="IsSepcialMajorSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
			<td>
					<div class="fitem">
						<label>是否重点专业：</label> 
						<select class='easyui-combobox' id="IsKeyMajor" name="t33_Bean.IsKeyMajor" editable=false panelHeight="auto">
							<option value="false">否</option>
							<option value="true">是</option>
						</select>
						<span id="IsKeyMajorSpan"></span>
					</div>
				</td>
				</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>专业带头人姓名：</label> 
						<input id="MajorLeader" type="text" name="t33_Bean.MajorLeader"
							class="easyui-validatebox" ><span id="MajorLeaderSpan"></span>
					</div>
				</td>	
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>专业带头人是否专职：</label> 
						<select class='easyui-combobox' id="LIsFullTime" name="t33_Bean.LIsFullTime" editable=false panelHeight="auto">
							<option value="true">是</option>
							<option value="false">否</option>
						</select>
						<span id="LIsFullTimeSpan"></span>
					</div>
				</td>			
				</tr>

			<tr>
				<td>
					<div class="fitem">
						<label>专业负责人姓名：</label> 
						<input id="MajorChargeMan" type="text" name="t33_Bean.MajorChargeMan"
							class="easyui-validatebox" ><span id="MajorChargeManSpan"></span>
					</div>
				</td>	
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>专业负责人是否专职：</label> 
						<select class='easyui-combobox' id="CIsFullTime" name="t33_Bean.CIsFullTime" editable=false panelHeight="auto">
							<option value="true">是</option>
							<option value="false">否</option>
						</select>
						<span id="CIsFullTimeSpan"></span>
					</div>
				</td>				
				</tr>


			<tr>
				<td style="valign:left" colspan="3"><label>备&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
					<textarea id="Note" name="t33_Bean.Note" style="resize:none" cols="50" rows="10"></textarea>
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

	    
	    function fillSelect(){ 
            var obj=document.getElementById( "SetTime"); 
            debugger;
             for(var i=1952;i <=2014;i++){ 
            var op=new Option(i,i); 
           obj.add(op); 
               } 
              } 
              
              
	    function batchImport(){
	    	 $('#batchForm').form('submit',{
	    		 url: 'pages/JuniorMajInfo/uploadFile',
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
	    
	    function newJuniorMajInfo(){
	    	$('.title1').show();
	    	$('#item1').show();
	    	$('hr').show();
	    	url=' pages/JuniorMajInfo/insert',
		    $('#dlg').dialog('open').dialog('setTitle','添加专科专业');
		    $('#juniorMajInfoForm').form('reset');
	    }

	    function singleImport(){
		    //录入数据的表单提交
	    	 $('#juniorMajInfoForm').form('submit',{
				    url: url,
				    data: $('#juniorMajInfoForm').serialize(),
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
			var TeaUnit =$('#UnitID').combobox('getText')  ;
			var MajorName = $('#MajorID').combobox('getText') ;
			var MajorFieldName=$('#MajorFieldName').val()  ;
			var AppvlSetTime = $('#AppvlSetTime').datebox('getValue') ;
			var FirstAdmisTime = $('#FirstAdmisTime').datebox('getValue') ;
			var MajorYearLimit = $('#MajorYearLimit').val() ;
			var MajorLeader = $('#MajorLeader').val() ;
			var MajorChargeMan = $('#MajorChargeMan').val() ;
			var Note = $('#Note').val() ;
	
			
			if(TeaUnit == null || TeaUnit.length == 0){
				$.messager.alert('提示',"教学单位不能为空") ;
				return false;
			}
			
			if(MajorName == null || MajorName.length == 0){
				$.messager.alert('提示',"专业名称不能为空") ;
				return false;
			}

			if(MajorFieldName == null || MajorFieldName.length==0 || MajorFieldName.length > 100){
				$.messager.alert('提示',"专业方向名称不能为空或长度不超过100") ;
				return false;
			}

			if(AppvlSetTime == null || AppvlSetTime.length == 0){
				$.messager.alert('提示',"批准设置时间不能为空") ;
				return false;
			}

			if(FirstAdmisTime == null || FirstAdmisTime.length == 0){
				$.messager.alert('提示',"首次招生时间不能为空") ;
				return false;
			}

			if(MajorYearLimit == null || MajorYearLimit.length == 0){
				$.messager.alert('提示',"修业年限不能为空") ;
				return false;	
			}else if(!checkData(MajorYearLimit)){
				$.messager.alert('提示',"修业年限必须是正整数");
				return false;
				}
			if(MajorLeader == null || MajorLeader.length==0 || MajorLeader.length > 100){
				$.messager.alert('提示',"专业带头人姓名不能为空或长度不超过100");
				return false;
			}

			if(MajorChargeMan == null || MajorChargeMan.length==0 || MajorChargeMan.length > 100){
				$.messager.alert('提示',"专业负责人姓名不能为空或长度不超过100");
				return false;
			}


			
			if(Note !=null && Note.length > 1000){
				$.messager.alert('提示',"备注中文字数不超过500") ;
				return false;
			}
			return true ;
		}

		function checkData(input)  
		{  
		     var re = /^[1-9]+[0-9]*]*$/;		  
		     if (!re.test(input))  
		    {  
		        return false;  
		     }else{
			     return true;
			     }  
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
				 	
				 	deletePostDocSta(ids) ;
				 	
				 }
			});
	    }

	    function deletePostDocSta(ids){
	    	$.ajax({ 
	    		type: "POST", 
	    		url: "pages/JuniorMajInfo/deleteCoursesByIds?ids=" + ids, 
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

	    function editJuniorMajInfo(){
	    	var row = $('#unverfiedData').datagrid('getSelections');
	    	
	    	if(row.length != 1){
	    		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
	    		return ;
	    	}
	    	
	    	url = 'pages/JuniorMajInfo/edit' ;

    	  	
	    	$('.title1').hide();
	    	$('#item1').hide();
	    	$('hr').hide();
	    	
	    	$('#dlg').dialog('open').dialog('setTitle','修改专科专业');
	    	$('#seqNumber').val(row[0].seqNumber) ;
	    	$('#UnitID').combobox('select',row[0].unitID) ;
	    	$('#MajorID').combobox('select',row[0].majorID) ;
	    	$('#MajorFieldName').val(row[0].majorFieldName) ;	
	    	$('#AppvlSetTime').datebox('setValue',formattime(row[0].appvlSetTime)) ;
	    	$('#FirstAdmisTime').datebox('setValue',formattime(row[0].firstAdmisTime)) ;	 	    	    	
	        $('#MajorYearLimit').val(row[0].majorYearLimit);
	    	$('#IsSepcialMajor').combobox('select',row[0].isSepcialMajor) ;
	    	$('#IsKeyMajor').combobox('select',row[0].isKeyMajor) ;	   
			$('#MajorLeader').val(row[0].majorLeader);
	    	$('#LIsFullTime').combobox('select',row[0].LIsFullTime) ;					     
			$('#MajorChargeMan').val(row[0].majorChargeMan);
	    	$('#CIsFullTime').combobox('select',row[0].CIsFullTime) ;		
			$('#Note').val(row[0].note);
	
	    }

	    function exports() {
	    	var temp = encodeURI('表3-3专科专业基本情况（教务处）.xls');
		    $('#exportForm').form('submit', {
		    url : "pages/JuniorMajInfo/dataExport?excelName="+temp ,
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
	    		url: "table3/dictorySearch",
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

