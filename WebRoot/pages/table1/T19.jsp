<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
	<!--
		<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui/themes/icon.css">
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
	<table id="unverfiedData" title="待审核数据域审核未通过数据" class="easyui-datagrid" style="width:100%px;height:350px" url="pages/T19/auditingData"
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="false" >
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber" width="10">序号</th>
				<th field="rewardName" width="10">奖励名称</th>
				<th field="rewardLevel" width="10">级别</th>
				<th field="rewardFromUnit" width="10">授予单位</th>
				<th field="unitName" width="10">获奖单位</th>
				<th field="unitID" width="10">单位号</th>
				<th field="rewardTime" width="10" formatter="formattime">获奖时间</th>
				<th field="note" width="10">备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="height:auto">
		<div>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newCourse()">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editCourse()">编辑</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyCourse()">删除</a>
		</div>
		 <div>
		 	序号: <input class="easyui-box" style="width:80px"/>
			日期 起始: <input class="easyui-datebox" style="width:80px"/>
			结束: <input class="easyui-datebox" style="width:80px"/>
			<a href="#" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		</div>
	</div>
	<div id="toolbar2">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-download" plain="true" onclick="newCourse()">数据导出</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="loadDic()">高级检索</a>
	</div>
	<table id="verfiedData" title="审核通过数据" class="easyui-datagrid" style="width:100%px;height:250px" url="table5/verifiedData"
		toolbar="#toolbar2" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="false">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber" width="5%">序号</th>
				<th field="rewardName" width="25%">奖励名称</th>
				<th field="rewardLevel" width="10%">级别</th>
				<th field="rewardFromUnit" width="20%">授予单位</th>
				<th field="unitName" width="15%">获奖单位</th>
				<th field="unitID" width="5%">单位号</th>
				<th field="rewardTime" width="10%">获奖时间</th>
				<th field="note" width="10%">备注</th>
			</tr>
		</thead>
	</table>
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<div class="ftitle">学校荣誉记录批量导入</div>
		<div class="fitem">
			<form method="post">
				<label>批量上传：</label> 
				<input type="file" name="fileToUpload" id="fileToUpload" class="easyui-validatebox"
					validType="fileType['xls']" required="true" invalidMessage="请选择Excel格式的文件" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">导入</a>
				<a href="table5/downloadCSBaseLibraries" class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
			<a href="123"></a>
		</div>
		<div></div>
		<div class="ftitle">学校荣誉记录逐条导入</div>
		
		<form id="rewardForm" method="post">
		<table>
			<tr>
				<td>
					<div class="fitem">
						<label>荣誉名称：</label> 
						<input id="seqNumber" type="hidden" name="t19Bean.SeqNumber" value="0"></input>
						<input id="RewardName" type="text" name="t19Bean.RewardName"
							class="easyui-validatebox" required="true"><span id="RewardNameSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>授予单位：</label> 
						<input id="RewardFromUnit" type="text" name="t19Bean.RewardFromUnit"
							class="easyui-validatebox" required="true"><span id="RewardFromUnitSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>级别：</label> 
						<input class='easyui-combobox' id="RewardLevel" name="t19Bean.RewardLevel" 
							data-options="valueField:'indexId',textField:'awardLevel',url:'pages/DiAwardLevel/loadDiAwardLevel',listHeight:'auto',editable:false">
							
						<span id="RewardLevelSpan"></span>
					</div>
				</td>
				<td>
				      <div class="fitem">
				        <label>获奖时间：</label>
				        <input class="easyui-datebox" id="RewardTime" name="t19Bean.RewardTime" >
						<span id="RewardTimeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
					<label>获奖单位：</label> 
						<!-- 下边的onselect方法是为了后台既要教学单位名称，有需要教学单位编号，而我们只有一个下拉框包含了这两条信息 -->
						<input type="hidden" name="t19Bean.UnitName" id="UnitName"/>
						<input id="UnitID" type="text" name="t19Bean.UnitID" 
							 class='easyui-combobox' data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment',listHeight:'auto',editable:false,
							 onSelect:function(){
							 	document.getElementById('UnitName').value=$(this).combobox('getText') ;
							 }">
						<span id="UnitNameSpan"></span>
					</div>
				</td>
				
			</tr>	
					<tr>
				<td style="valign:left"><label>备&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
					<textarea id="Note" name="t19Bean.Note" style="resize:none" cols="50" rows="10"></textarea>
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
	    function batchImport(){
	    	 $('#fm').form('submit',{
	    		 url: pages/T19/uploadFile,
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
				    url: url,
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

		 function editCourse(){
		    	var row = $('#unverfiedData').datagrid('getSelections');
		    	
		    	if(row.length != 1){
		    		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
		    		return ;
		    	}
		    	url = 'pages/T19/edit' ;
		    	
		    	$('#dlg').dialog('open').dialog('setTitle','添加本科教学课程库');
		    	$('#seqNumber').val(row[0].seqNumber) ;
		    	$('#RewardName').val(row[0].rewardName);
		    	$('#RewardFromUnit').val(row[0].rewardFromUnit);
		    	$('#RewardLevel').combobox('select', row[0].rewardLevelID) ;
		    	$('#RewardTime').datebox('setValue',formattime(row[0].rewardTime)) ;
		    	$('#UnitID').combobox('select',row[0].unitID) ;
				$('#Note').val(row[0].note) ;
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
	<script>
  var jsonStr = "{\"a\":\"内容1\", \"b\":\"内容2\",\"c\":\"内容3\",\"d\":\"内容4\"}";
  var jsonObj = eval("(" + jsonStr + ")");
  for(var property in jsonObj){
   var nodeObj = document.getElementById(property);
   if(nodeObj)
    nodeObj.childNodes[0].nodeValue = jsonObj[property];
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
