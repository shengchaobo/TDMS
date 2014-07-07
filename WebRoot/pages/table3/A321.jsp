<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>专业门类统计</title>
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
	<table class="easyui-datagrid" toolbar="#toolbar" title="专业门类统计"></table>
	<hr color="blue" width="100%" />
	<table class="doc-table" id="showInfo" > 
	<tbody >
	 
	        <tr>
	          <th align="center" style="width:100px; height:50px ;background-color:white ">序号</th>
	          <th align="center" style="width:80px; height:50px ;background-color:white ">学位授予门类</th>
	          <th align="center" style="width:100px; height:50px ;background-color:white ">专业数（个）</th>
	          <th align="center" style="width:80px; height:50px ;background-color:white ">所占比例（%）</th>	          
	        </tr>
	        <tr>
	          <th  colspan = 2  align="center" style="width:40px;background-color:white ">全校合计</th>

	          <th style="background-color:white">
	             <div>
	          		<span id="TotalNumSpan"></span>
	          	</div>
	          </th>
	          <th align="center" style="width:60px;background-color:white ">/</th>	          	          
	        </tr>
	        <tr>
	          <th  align="center" style="width:40px;background-color:white ">1</th>
	          <th  align="center" style="width:40px;background-color:white ">02经济学</th>
	          <th style="background-color:white">
	             <div>
	          		<span id="EconomicNumSpan"></span>
	          	</div>
	          </th>
	          <th style="background-color:white">
	             <div>
	          		<span id="EconomicRatioSpan"></span>
	          	</div>
	          </th>
	        </tr>
	        
	         <tr>
	          <th  align="center" style="width:40px;background-color:white ">2</th>
	          <th  align="center" style="width:40px;background-color:white ">05文学</th>
	          <th style="background-color:white">
	             <div>
	          		<span id="LiteratureNumSpan"></span>
	          	</div>
	          </th>
	          <th style="background-color:white">
	             <div>
	          		<span id="LiteratureRatioSpan"></span>
	          	</div>
	          </th>
	        </tr>
	        
	        	        <tr>
	          <th  align="center" style="width:40px;background-color:white ">3</th>
	          <th  align="center" style="width:40px;background-color:white ">07理学</th>
	          <th style="background-color:white">
	             <div>
	          		<span id="ScienceNumSpan"></span>
	          	</div>
	          </th>
	          <th style="background-color:white">
	             <div>
	          		<span id="ScienceRatioSpan"></span>
	          	</div>
	          </th>
	        </tr>
	        
	        	        <tr>
	          <th  align="center" style="width:40px;background-color:white ">4</th>
	          <th  align="center" style="width:40px;background-color:white ">08工学</th>
	          <th style="background-color:white">
	             <div>
	          		<span id="EngineerNumSpan"></span>
	          	</div>
	          </th>
	          <th style="background-color:white">
	             <div>
	          		<span id="EngineerRatioSpan"></span>
	          	</div>
	          </th>
	        </tr>
	        
	        	        <tr>
	          <th  align="center" style="width:40px;background-color:white ">5</th>
	          <th  align="center" style="width:40px;background-color:white ">09农学</th>
	          <th style="background-color:white">
	             <div>
	          		<span id="AgronomyNumSpan"></span>
	          	</div>
	          </th>
	          <th style="background-color:white">
	             <div>
	          		<span id="AgronomyRatioSpan"></span>
	          	</div>
	          </th>
	        </tr>
	        
	        	        <tr>
	          <th  align="center" style="width:40px;background-color:white ">6</th>
	          <th  align="center" style="width:40px;background-color:white ">12管理学</th>
	          <th style="background-color:white">
	             <div>
	          		<span id="ManageNumSpan"></span>
	          	</div>
	          </th>
	          <th style="background-color:white">
	             <div>
	          		<span id="ManageRatioSpan"></span>
	          	</div>
	          </th>
	        </tr>
	        
	        	        <tr>
	          <th  align="center" style="width:40px;background-color:white ">7</th>
	          <th  align="center" style="width:40px;background-color:white ">13艺术学</th>
	          <th style="background-color:white">
	             <div>
	          		<span id="ArtNumSpan"></span>
	          	</div>
	          </th>
	          <th style="background-color:white">
	             <div>
	          		<span id="ArtRatioSpan"></span>
	          	</div>
	          </th>
	        </tr>

	        
		</tbody>
	</table>
	<div id="toolbar" style="height:auto">
		<div>
			
			 <a href="pages/A321/dataExport" class="easyui-linkbutton" iconCls="icon-download" plain="true">数据导出</a>
			<!--<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editCourse()">编辑</a> 
			 <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyCourse()">删除</a> -->
		</div> 
	</div>
	<!--  
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
	-->
</body>
	<script type="text/javascript">

	$(document).ready(function() {
  
		loadAuotCityList();
	});

	function loadAuotCityList() {
		
		$.ajax( {
			type : "POST", //post请求
			url : "pages/A321/auditingData", //请求action的URL
			dataType : "json",//返回类型
			success : function(result) { //回调函数	 		 
				var data = result;
				if(data == null){
					 $(function () {
				            $.messager.alert("操作提示", "还未有统计数据，请先录取数据再查看！");
				        });
				}
				$('#TotalNumSpan').html(data.economicNum+data.literatureNum+data.scienceNum+data.engineerNum+data.agronomyNum+data.manageNum+data.artNum);					
				$('#EconomicNumSpan').html(data.economicNum);
				$('#EconomicRatioSpan').html(toPercent(data.economicRatio));
				$('#LiteratureNumSpan').html(data.literatureNum);
				$('#LiteratureRatioSpan').html(toPercent(data.literatureRatio));
				$('#ScienceNumSpan').html(data.scienceNum);
				$('#ScienceRatioSpan').html(toPercent(data.scienceRatio));
				$('#EngineerNumSpan').html(data.engineerNum);
				$('#EngineerRatioSpan').html(toPercent(data.engineerRatio));
				$('#AgronomyNumSpan').html(data.agronomyNum);
				$('#AgronomyRatioSpan').html(toPercent(data.agronomyRatio));
				$('#ManageNumSpan').html(data.manageNum);
				$('#ManageRatioSpan').html(toPercent(data.manageRatio));	
				$('#ArtNumSpan').html(data.artNum);
				$('#ArtRatioSpan').html(toPercent(data.artRatio));					
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
			
<script type="text/javascript">
function toPercent(data){
    var strData = parseFloat(data)*10000;
    strData = Math.round(strData);
    strData/=100.00;
    var ret = strData.toString()+"%";
    return ret;
}
var data = document.getElementById("retData").value;
alert(toPercent(data));
</script>

</html>