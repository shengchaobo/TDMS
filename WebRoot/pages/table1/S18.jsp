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

<title>S17</title>
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
	<script type="text/javascript" src="js/table1/S18.js"></script>
	<script type="text/javascript"></script>
</head>

<body style="height: 100%'" >
 <table id="edit" class="easyui-propertygrid"  ></table>
 <div id="toolbar" style="height:30px;">
		<div style="float: left;">
		<!-- 
			<a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-save" plain="true"   ><font color=Silver>保存</font></a>
			<a href="javascript:void(0)" id="cancel" class="easyui-linkbutton" iconCls="icon-cancel" plain="true"  ><font color=Silver>取消</font></a>
			 -->	
			<a href="javascript:void(0)" id="export" class="easyui-linkbutton" iconCls="icon-download" plain="true"  onclick="exports()">数据导出</a>
		</div>
	 	 <form  id="exportForm"  style="float: right;"  method="post" >
			显示： <select class="easyui-combobox" id="cbYearContrast" panelHeight="auto" style="width:80px; padding-top:5px; margin-top:10px;"  editable=false ></select>
	 	</form>	
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
	</script>
</html>




<!-- 

<body style="overflow-y:scroll">
	<table class="easyui-datagrid" toolbar="#toolbar" title="协议个数"></table>
	<hr color="blue" width="100%" />
	<table class="doc-table"  id="showInfo" url="pages/S18/auditingData">
	<tbody >
	 
	        <tr>
	          <th  colspan = 2  align="center" style="width:100px; height:50px ;background-color:white ">项目</th>
	          <th align="center" style="width:80px; height:50px ;background-color:white ">内容</th>
	        </tr>
	        <tr>
	          <th  rowspan = 4  align="left" style="width:40px;background-color:white ">签订合作协议机构的协议个数</th>
	          <th  align="left" style="width:60px;background-color:white ">协议总数</th>
	          <th style="background-color:white">
              		  <div>
		          	    <span id="SumAgreeNumSpan"></span>
		             </div>
              </th>
	        </tr>
	        <tr>
	          <th align="left" style="width:60px;background-color:white ">其中：学术机构</th>
	          <th style="background-color:white">
                  <div>
		          	    <span id="AcademicNumSpan"></span>
		             </div>
              </th>
	        </tr>
	        <tr>
	          <th align="left" style="width:60px;background-color:white ">&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;行业机构和企业</th>
	          <th style="background-color:white">
	             <div>
		          	    <span id="IndustryNumSpan"></span>
		             </div>
	          </th>
	        </tr>
	        <tr>
	          <th align="left" style="width:60px;background-color:white ">&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地方政府</th>
	          <th style="background-color:white">
	              <div>
		          	    <span id="LocalGoverNumSpan"></span>
		             </div>
	          </th>
	        </tr>
		</tbody>
	
	</table>
	<div id="toolbar" style="height:auto">
		<div>
		
		<form  id="exportForm"  method="post" style="float: right;">
				<select class="easyui-combobox" id="cbYearContrast" name="selectYear" editable="false" panelHeight="auto" style="width:80px; padding-top:5px; margin-top:10px;"></select>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-download" plain="true"  onclick="exports()">数据导出</a>
	  	</form>	
		</div> 
	</div>
</body>

	<script type="text/javascript">

	$(document).ready(function() {
       //alert(2224);
		loadAuotCityList();
	});

	function exports() {
    	var temp = encodeURI('S-1-8签订合作协议机构的协议个数.xls');
	    $('#exportForm').form('submit', {
	    url : "pages/S18/dataExport" ,
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

	function loadAuotCityList() {
    //  alert(3334);
		$.ajax( {
			type : "POST", //post请求
			url : "pages/S18/auditingData", //请求action的URL
			dataType : "json",//返回类型
			success : function(result) { //回调函数	    		 
				// var data = eval('('+result+')');
			    var data = result;
			    if(data == null){
			    	$(function () {
			            $.messager.alert("操作提示", "还未有统计数据，请先录取数据再查看！");
			        });
			    }else{
			    	$('#SumAgreeNumSpan').html(data.sumAgreeNum);
		            $('#AcademicNumSpan').html(data.academicNum);
		            $('#IndustryNumSpan').html(data.industryNum);
		            $('#LocalGoverNumSpan').html(data.localGoverNum);
			    }
			
			}
		});
	}
	
	
	

	    var url;
	    function batchImport(){
	    	 $('#fm').form('submit',{
	    		 url: url,
	    		 onSubmit: function(){
	    		 	return $(this).form('validate');
	    		 },
	    		 success: function(result){
	    		 	var result = eval('('+result+')');
	    		 	if (result.errorMsg){
	    		 		$.messager.show({
	    		 			title: 'Error',
	    		 			msg: result.errorMsg
	    			 });
	    		 	} else {
			    		 $('#dlg').dialog('close'); // close the dialog
			    		 $('#dg').datagrid('reload'); // reload the user data
	    		 	}
	    		 }
	    		 });
	    }
	    
	    function newCourse(){
		    $('#dlg').dialog('open').dialog('setTitle','添加学校荣誉库');
		    $('#rewardForm').form('reset');
	    }

	    function singleImport(){
		    //录入数据的表单提交
	    	 $('#rewardForm').form('submit',{
				    url: 'pages/SchHonor/insert',
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
				$('#RewardNameSpan').html("<font style=\"color:red\">荣誉名称不能为空或长度不超过100</font>") ;
				return false ;
			}else{
				$('#RewardNameSpan').html("") ;
			}
			
			if(rewardFromUnit == null || rewardFromUnit.length == 0 || rewardFromUnit.length > 50){
				$('#RewardFromUnit').focus();
				$('#RewardFromUnit').select();
				$('#RewardFromUnitSpan').html("<font style=\"color:red\">授予单位不能为空或长度不超过50</font>") ;
				return false ;
			}else{
				$('#RewardFromUnitSpan').html("") ;
			}
			
			if(rewardLevel == null || rewardLevel.length == 0){
				$('#RewardLevelSpan').html("<font style=\"color:red\">级别不能为空</font>") ;
				return false ;
			}else{
				$('#RewardLevelSpan').html("") ;
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
				$('#UnitIDSpan').html("<font style=\"color:red\">获奖单位不能为空</font>") ;
				return false ;
			}else{
				$('#UnitIDSpan').html("") ;
			}
			
			if(note !=null && note.length > 1000){
				$('#NoteSpan').html("<font style=\"color:red\">备注中文字数不超过500</font>") ;
				return false ;
			}else{
				$('#NoteSpan').html("") ;
			}
			return true ;
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
 -->
