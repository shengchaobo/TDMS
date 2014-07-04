<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>专业认证（评估）情况统计</title>
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
	<link rel="stylesheet" type="text/css" href="jquery-easyui/themes/main.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui/demo/demo.css">
	
	<style type="text/css">
		#fm {
			margin: 0;
			padding: 10px 30px;
		}
		
		.ftitle {
			font-size: 14px;
			font-weight: bold;
			padding: 5px 0;
			margin-bottom: 10px;
			border-bottom: 1px solid #ccc;
		}
		
		.fitem {
			margin-bottom: 5px;
		}
		
		.fitem label {
			display: inline-block;
			width: 80px;
		}
	</style>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script> 
	<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body style="overflow-y:scroll">
	<table class="easyui-datagrid" toolbar="#toolbar" title="专业认证（评估）情况统计"></table>
	<hr color="blue" width="100%" />
		<div id="toolbar" style="height:auto">
		<div>
			 
			 <a href="pages/T17/dataExport" class="easyui-linkbutton" iconCls="icon-download">数据导出</a>
			
		</div> 
	</div>
	<table  class="easyui-datagrid"  style ="overflow-y: hide;" url="pages/S322/auditingData"
		fitColumns="true"> 
		<thead>
			<tr>
				<th field="seqNumber" width=10>序号</th>
				<th field="TeaUnit" width=10>教学单位</th>
				<th field="UnitID" width=10 >单位号</th>
				<th field="PassedMajor" width=10>已通过专业认证（评估）的本科专业名称</th>
				<th field="MajorID" width=10>专业代码</th>
				<th field="AssessTime" width=10 formatter="formattime">认证时间</th>
				<th field="ValidityBegin" width=10 formatter="formattime">有效期(止)</th>
				<th field="ValidityEnd" width=10 formatter="formattime">有效期(止)</th>
				<th field="AssessOrg" width=10 formatter="formattime">认证机构</th>
				<th field="time" width=10 formatter="formattime">日期</th>
				<th field="note" width=10>备注</th>
			</tr>
		</thead>
	</table>

</body>

	<script type="text/javascript">



	function loadAuotCityList() {
		
		$.ajax( {
			type : "POST", //post请求
			url : "pages/S31/auditingData", //请求action的URL
			dataType : "json",//返回类型
			success : function(result) { //回调函数	 		 
				var data = result;
				if(data == null){
					 $(function () {
				            $.messager.alert("操作提示", "还未有统计数据，请先录取数据再查看！");
				        });
				}
				$('#PostdocStationSpan').html(data.postdocStation);
				$('#DocStationSpan').html(data.docStation);
				$('#MasterStationSpan').html(data.masterStation);
				$('#SumMajorSpan').html(data.sumMajor);
				$('#NewMajorSpan').html(data.newMajor);
				$('#JuniorMajorSpan').html(data.juniorMajor);
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

</html>
