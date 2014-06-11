<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'T721_TeachResItem_Tea.jsp' starting page</title>
    
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
	<table id="unverfiedData" title="待审核数据域审核未通过数据" class="easyui-datagrid" style="width:100%px;height:250px" url="table5/verifingData"
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="false" >
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="id" width="5%">序号</th>
				<th field="ItemName" width="10%">项目名称</th>
				<th field="TeaUnit" width="13%">所属教学单位</th>
				<th field="UnitID" width="8%">单位号</th>
				<th field="Leader" width="7%">负责人</th>
				<th field="TeaID" width="7%">教工号</th>
				<th field="OtherTeaNum" width="20%">其他参与教师人数</th>
				<th field="OtherTea" width="10%">其他教师</th>
				<th field="ItemLevel" width="5%">级别</th>
				<th field="ItemSetUpTime" width="10%">立项时间</th>
				<th field="ReceptTime" width="10%">验收时间</th>
				<th field="ApplvExp" width="17%">批准经费（万元）</th>
				<th field="SchSupportExp" width="22%">学校配套经费（万元）</th>
				<th field="AppvlID" width="8%">批文号</th>	
				<th field="note" width="20%">备注</th>
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
				<th field="id" width="5%">序号</th>
				<th field="ItemName" width="10%">项目名称</th>
				<th field="TeaUnit" width="13%">所属教学单位</th>
				<th field="UnitID" width="8%">单位号</th>
				<th field="Leader" width="7%">负责人</th>
				<th field="TeaID" width="7%">教工号</th>
				<th field="OtherTeaNum" width="20%">其他参与教师人数</th>
				<th field="OtherTea" width="10%">其他教师</th>
				<th field="ItemLevel" width="5%">级别</th>
				<th field="ItemSetUpTime" width="10%">立项时间</th>
				<th field="ReceptTime" width="10%">验收时间</th>
				<th field="ApplvExp" width="17%">批准经费（万元）</th>
				<th field="SchSupportExp" width="22%">学校配套经费（万元）</th>
				<th field="AppvlID" width="8%">批文号</th>	
				<th field="note" width="20%">备注</th>
			</tr>
		</thead>
	</table>
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<div class="ftitle">本科课程库批量导入</div>
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
		<div class="ftitle">本科课程库逐条导入</div>
		
		<form id="courseForm" method="post">
		<table>
		
		<tr>
			<td>
					<div class="fitem">
						<label>项目名称：</label> 
						<input id="ItemName" type="text" name="teachResItemTea.ItemName" 
							><span id="ItemNameSpan"></span>
					</div>
				<td>
					<div class="fitem">
					<label>所属教学单位：</label>
						<input type="hidden" name="teachResItemTea.TeaUnit" id="TeaUnit"/>
						<input id="UnitID" type="text" name="teachResItemTea.UnitID" 
							 class='easyui-combobox' data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment',listHeight:'auto',editable:false,
							 onSelect:function(){
							 	document.getElementById('TeaUnit').value=$(this).combobox('getText') ;
							 }">
						<span id="TeaUnitSpan"></span>
					</div>
				</td>
				</tr>
			<tr>
			<td>
					<div class="fitem">
						<label>教工号：</label> 
						<input id="TeaID" type="hidden" name="teachResItemTea.TeaID" >
					    <input id="Leader" type="text" name="teachResItemTea.Leader"
					     class='easyui-combobox' data-options="valueField:'teaName',textField:'teaId',url:'pages/T411/loadT411',listHeight:'auto',editable:true,
							 onSelect:function(){
							   document.getElementById('TeaID').value=$(this).combobox('getText') ;
							 }">
							
							<span id="TeaIDSpan"></span>
					</div>
					
				<td>
					<div class="fitem">
						<label>其他参与教师人数：</label> 
						<input id="OtherTeaNum" type="text" name="teachResItemTea.OtherJoinTeaNum"
							><span id="OtherTeaNumSpan"></span>
					</div>
				</td>
				</tr>
				<tr>
				<td>
					<div class="fitem">
						<label>其他教师：</label> 
						<input id="OtherTea" type="text" name="teachResItemTea.OtherTea"
							><span id="OtherTeaSpan"></span>
					</div>
				</td>
			
				<td>
					<div class="fitem">
						<label>级别：</label> 
						<input class='easyui-combobox' id="ItemLevel" name="teachResItemTea.ItemLevel"
							data-options="valueField:'indexId',textField:'awardLevel',url:'pages/DiAwardLevel/loadDiAwardLevel',listHeight:'auto',editable:false">
						<span id="ItemLevelSpan"></span>
						
					</div>
				</td>
				</tr>
				<tr>
				<td>
					<div class="fitem">
						<label>立项时间：</label> 
						<input  id="ItemSetUpTime"  class="easyui-datebox" style="width:80px" name="teachResItemTea.ItemSetUpTime">
						
							<span id="ItemSetUpTimeSpan"></span>
					</div>
				</td>
			
				<td>
					<div class="fitem">
						<label>验收时间：</label> 
						<input id="ReceptTime"  class="easyui-datebox" style="width:80px"  name="teachResItemTea.ReceptTime"
							><span id="ReceptTimeSpan"></span>
					</div>
				</td>
				</tr>
				<tr>
				<td>
					<div class="fitem">
						<label>批准经费（万元）：</label> 
						<input id="ApplvExp" type="text" name="teachResItemTea.ApplvExp"
							><span id="ApplvExpSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>学校配套经费（万元）：</label> 
						<input id="SchSupportExp" type="text" name="teachResItemTea.SchSupportExp"
							><span id="SchSupportExpSpan"></span>
					</div>
				</td>
				</tr>
				<tr>
				<td>
					<div class="fitem">
						<label>批文号：</label> 
						<input id="AppvlID" type="text" name="teachResItemTea.AppvlID"
							><span id="AppvlIDSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
			<input name="teachResItemTea.time" class="easyui-datebox" style="width:80px"/>
				<td style="valign:left"><label>备&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
					<textarea id="Note" name="teachResItemTea.Note" style="resize:none" cols="50" rows="10"></textarea>
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
		    $('#dlg').dialog('open').dialog('setTitle','添加本科教学课程库');
		    $('#courseForm').form('reset');
	    }

	    function singleImport(){
		    //录入数据的表单提交
	    	 $('#courseForm').form('submit',{
				    url: 'pages/TeachResItemTea/insert',
				    data: $('#courseForm').serialize(),
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
			var itemName = $('#ItemName').val();
			var teaUnit = $('#UnitID').combobox('getText');
			var teaID = $('#Leader').combobox('getText');
			var otherTeaNum = $('#OtherTeaNum').val();
			var otherTea = $('#OtherTea').val();
			var itemLevel = $('#ItemLevel').combobox('getText');
			var itemSetUpTime = $('#ItemSetUpTime').datebox('getText');
			var receptTime = $('#ReceptTime').datebox('getText');
			var applvExp = $('#ApplvExp').val();
			var schSupportExp = $('#SchSupportExp').val();
			var appvlID = $('#AppvlID').val();
			var note = $('#Note').val();
			//根据数据库定义的字段的长度，对其进行判断
			if(itemName == null || itemName.length==0 || itemName.length > 100){
				$('#ItemName').focus();
				$('#ItemName').select();
				$('#ItemNameSpan').html("<font style=\"color:red\">教学单位不能为空或长度不超过100</font>") ;
				return false;
			}
			if(teaID == null || teaID.length == 0){
				$('#TeaIDSpan').html("<font style=\"color:red\">负责人不能为空</font>") ;
				return false ;
			}
			if(otherTeaNum == null || otherTeaNum.length==0){
				
				$('#OtherTeaNumSpan').html("<font style=\"color:red\">其他参与教师人数不能为空或长度不超过10</font>") ;
				return false;
			}
			if(otherTea == null || otherTea.length == 0 || otherTea.length > 50){
				$('#OtherTea').focus();
				$('#OtherTea').select();
				$('#OtherTeaSpan').html("<font style=\"color:red\">其他教师不能为空或长度不超过50</font>") ;
				return false;
			}
			if(itemLevel == null || itemLevel.length==0 || itemLevel.length > 200){
				$('#ItemLevel').focus();
				$('#ItemLevel').select();
				$('#ItemLevelSpan').html("<font style=\"color:red\">级别不能为空或长度不超过200</font>") ;
				return false ;
			}
			if(itemSetUpTime == null || itemSetUpTime.length == 0 ){
				alert(awardRank) ;
				$('#ItemSetUpTimeSpan').html("<font style=\"color:red\">立项时间不能为空</font>") ;
				return false ;
			}
			if(receptTime == null || receptTime.length == 0){
				
				$('#ReceptTimeSpan').html("<font style=\"color:red\">验收时间不能为空</font>") ;
				return false ;
			}
			if(applvExp == null || applvExp.length == 0){
				$('#ApplvExpSpan').html("<font style=\"color:red\">批准经费不能为空</font>") ;
				return false ;
			}
			if(schSupportExp == null || schSupportExp.length == 0){
				$('#SchSupportExpSpan').html("<font style=\"color:red\">学校配套经费不能为空</font>") ;
				return false ;
			}
			if(appvlID == null || appvlID.length == 0){
				$('#AppvlIDSpan').html("<font style=\"color:red\">批文号不能为空</font>") ;
				return false ;
			}
			
			if(note !=null && note.length > 1000){
				$('#NoteSpan').html("<font style=\"color:red\">备注中文字数不超过500</font>") ;
				return false ;
			}
			alert($('#TeaUnit').val()) ;
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
	    		url: "table5/dictorySearch",
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
