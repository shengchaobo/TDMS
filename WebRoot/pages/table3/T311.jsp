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
	<table id="unverfiedData"  class="easyui-datagrid"   url="pages/PostDocSta/auditingData" >
		<thead data-options="frozen:true">
		<tr>
				<th data-options="field:'ck',checkbox:true" >选取</th>
				<th field="seqNumber" >编号</th>
				</tr>
		</thead>
			<thead>
			<tr>
				<th field="postDocStaName" >博士后流动站名称</th>
				<th field="setTime"  formatter="formattime">设置时间</th>
				<th field="researcherNum" >研究员人数</th>
				<th field="unitName" >所属单位</th>
				<th field="unitID" >单位号</th>
				<th field="note">备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="height:auto">
		<div style="float: left;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newPostDocSta()">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editPostDocSta()">编辑</a> 
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
	<table id="verfiedData" title="审核通过数据" class="easyui-datagrid" style="width:100%px;height:250px" 
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
				<th field="PostDocStaName" >博士后流动站名称</th>
				<th field="SetTime"  formatter="formattime">设置时间</th>
				<th field="ResearcherNum">研究员人数</th>
				<th field="UnitName">所属单位</th>
				<th field="UnitID">单位号</th>
				<th field="Note">备注</th>
			</tr>
		</thead>
	</table>
<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="title1">博士后流动站批量导入</h3>
		<div class="fitem" id="item1">
			<form id="batchForm" method="post" enctype="multipart/form-data">
				<select class="easyui-combobox"  id="cbYearContrast1" name="selectYear" editable=false></select>
				<input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox" size="48" style="height: 24px;"
					validType="fileType['xls']" required="true" invalidMessage="请选择Excel格式的文件" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href='pages/PostDocSta/downloadModel?saveFile=<%=URLEncoder.encode("表3-1-1博士后流动站（人事处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
			<a href="123"></a>
		</div>
		<hr></hr>	

		<h3 class="title1">博士后流动站逐条导入</h3>
		<form id="postDocStaForm" method="post">
		<table>
			<tr>
				<td>
					<div class="fitem">
						<label>博士后流动站名称：</label> 
						<input id="seqNumber" name="postDocStaBean.SeqNumber" type="hidden" > 
						<input id="PostDocStaName" type="text" name="postDocStaBean.PostDocStaName"
							class="easyui-validatebox" ><span id="PostDocStaNameSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>设置时间：</label> 
						<input id="SetTime" name="postDocStaBean.SetTime"
							class="easyui-datebox" editable="false" >
							<span id="SetTimeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
			<td>
					<div class="fitem">
						<label>研究员人数：</label> 
						<input id="ResearcherNum" type="text" name="postDocStaBean.ResearcherNum"
							class="easyui-validatebox" ><span id="ResearcherNumSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>所属单位：</label> 
						<input id="UnitName" type="text" name="postDocStaBean.UnitName"
							class="easyui-validatebox" ><span id="UnitNameSpan"></span>
					</div>
				</td>				
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>单位号：</label> 
						<input id="UnitID" type="text" name="postDocStaBean.UnitID"
							class="easyui-validatebox" ><span id="UnitIDSpan"></span>
					</div>
				</td>
			</tr>


			<tr>
				<td style="valign:left" colspan="3"><label>备&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
					<textarea id="Note" name="postDocStaBean.Note" style="resize:none" cols="50" rows="10"></textarea>
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
	    		 url: 'pages/PostDocSta/uploadFile',
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
	    
	    function newPostDocSta(){
	    	$('.title1').show();
	    	$('#item1').show();
	    	$('hr').show();
	    	url=' pages/PostDocSta/insert',
		    $('#dlg').dialog('open').dialog('setTitle','添加博士后流动站');
		    $('#postDocStaForm').form('reset');
	    }

	    function singleImport(){
		    //录入数据的表单提交
	    	 $('#postDocStaForm').form('submit',{
				    url: url,
				    data: $('#postDocStaForm').serialize(),
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
			var PostDocStaName = $('#PostDocStaName').val() ;
			var SetTime = $('#SetTime').datebox('getValue') ;
			var ResearcherNum = $('#ResearcherNum').val() ;
			var UnitName = $('#UnitName').val() ;
			var UnitID = $('#UnitID').val() ;
			var Note = $('#Note').val() ;
			//根据数据库定义的字段的长度，对其进行判断
			if(PostDocStaName == null || PostDocStaName.length==0 || PostDocStaName.length > 100){
				$.messager.alert('提示',"博士后流动站名称不能为空或长度不超过100") ;
				return false;
			}
			if(ResearcherNum == null || ResearcherNum.length == 0){
				$.messager.alert('提示',"研究员人数不能为空") ;
				return false;	
			}else if(!checkRate(ResearcherNum)){
				$.messager.alert('提示',"研究员人数必须是正整数");
				return false;
				}
			
			if(SetTime == null || SetTime.length == 0){
				$.messager.alert('提示',"设置时间不能为空") ;
				return false;
			}
			
			if(UnitName == null || UnitName.length == 0){
				$.messager.alert('提示',"所属单位不能为空") ;
				return false;
			}
			
			if(UnitID == null || UnitID.length == 0){
				$.messager.alert('提示',"单位号不能为空") ;
				return false;
			}
		
			if(Note !=null && Note.length > 1000){
				$.messager.alert('提示',"备注中文字数不超过500") ;
				return false;
			}
			return true ;
		}


		function checkRate(input)  
		{  
		     var re = /^[1-9]+[0-9]*]*$/;		  
		     if (!re.test(input))  
		    {  
		        return false;  
		     }else{
			     return true;
			     }  
		}


	    function exports() {
	    	var temp = encodeURI('表3-1-1博士后流动站（人事处）.xls');
		    $('#exportForm').form('submit', {
		    url : "pages/PostDocSta/dataExport?excelName="+temp ,
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
	    		url: "pages/PostDocSta/deleteCoursesByIds?ids=" + ids, 
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

	    function editPostDocSta(){
	    	var row = $('#unverfiedData').datagrid('getSelections');
	    	
	    	if(row.length != 1){
	    		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
	    		return ;
	    	}
	    	
	    	url = 'pages/PostDocSta/edit' ;

	    	$('.title1').hide();
	    	$('#item1').hide();
	    	$('hr').hide();
	    	$('#dlg').dialog('open').dialog('setTitle','修改博士后流动站');
	    	$('#seqNumber').val(row[0].seqNumber) ;
	        $('#PostDocStaName').val(row[0].postDocStaName);
	    	$('#SetTime').datebox('setValue',formattime(row[0].setTime)) ;
	    	$('#ResearcherNum').val(row[0].researcherNum) ;
	        $('#UnitName').val(row[0].unitName) ;
	    	$('#UnitID').val(row[0].unitID) ;
			$('#Note').val(row[0].note);
		
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

