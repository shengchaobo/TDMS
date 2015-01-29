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

<title>T11</title>
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
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="js/commom.js"></script>
	<script type="text/javascript" src="js/table1/T11.js"></script>
	<script type="text/javascript"></script>
</head>

<body style="height: 100%'" >
 <table id="edit" class="easyui-propertygrid"  ></table>
 <div id="toolbar" style="height:30px;">
		<div style="float: left;">	
			<a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-save" plain="true"  onclick="getSave()">保存</a>
			<a href="javascript:void(0)" id="cancel" class="easyui-linkbutton" iconCls="icon-cancel" plain="true"  >取消</a>
			<a href="javascript:void(0)" id="export" class="easyui-linkbutton" iconCls="icon-download" plain="true"  onclick="exports()">数据导出</a>
			<!-- <a href="javascript:void(0)" id="copy" class="easyui-linkbutton" iconCls="icon-edit" plain="true"  >取消</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="newCopy()">复制</a> -->
		</div>
	 	 <form  id="exportForm"  style="float: right;"  method="post" >
			显示： <select class="easyui-combobox" id="cbYearContrast" panelHeight="auto" style="width:80px; padding-top:5px; margin-top:10px;"  editable=false ></select>
	 	</form>	
	</div>
</body>


	<div id="dlg" class="easyui-dialog"
		style="width:300px;height:150px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="title1">选择复制年份</h3>
			<hr></hr>
		<div class="fitem" id="item1">
			<form id="batchForm" method="post" enctype="multipart/form-data">
				<select class="easyui-combobox"  id="cbYearContrast1" name="selectYear" editable="false"></select>
			</form>
		</div>
	
	</div>
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
	
	<script type="text/javascript">

    var copyYear = $("#cbYearContrast1").combobox('getValue'); 

 	//出现复制年份
    function newCopy(){

    	$('.title1').show();
    	$('#item1').show();
    	$('hr').show();
    	url = 'pages/T11/copy?copyYear='+copyYear;
	    $('#dlg').dialog('open').dialog('setTitle','选择年份');
	    //$('#addForm').form('reset');
    }

	function reloadgrid (year,flag)  { 
			  $.ajax( {
	    		type : "POST",
	    		contentType: "application/json;utf-8",
				url: 'pages/T11/loadInfo?selectYear='+year,
	    		async : false,
	    		dataType : "json",
	    		success : function(json) {
                  var i = 0;
                  while(i<rows.length){
                  	rows[i].value = eval('json.'+rows[i].field);	
                  	i=i+1;
                  }								
				},
              error: function(XMLResponse) {
                 // alert(XMLResponse.responseText
                  var i = 0;
                  while(i<rows.length){
                  	rows[i].value = "";	
                  	i=i+1;
                  }
                  if(flag == true){
                  	alert("该年数据为空!!!");
                  }			                    
              }
		})
 }
 	

	
	 //复制
  function copy(seqNumber){
    		//alert(checkNum);
		    $.ajax({
				    type:"POST", 
				    url: "pages/T412/updateCheck?seqNum=" + seqNumber +"&checkNum=" + checkNum, 
			   		async : "true",
			   		dataType : "text",
				    success:function(result){  
				         result = eval("(" + result + ")");						    	 
					  		 if (!result.state){
								  		 	$.messager.show({
								  		 			title: 'Error',
								  		 			msg: result.data
								  			 });
						  		} else {
								    		 $('#checkData').datagrid('reload'); // reload the user data		
						  		}
				    }
				});  	    
    }
	</script>
</html>

