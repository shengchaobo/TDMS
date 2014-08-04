<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>办学指导思想</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
		<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link rel="stylesheet" type="text/css"
			href="jquery-easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css"
			href="jquery-easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css"
			href="jquery-easyui/themes/main.css">
		<link rel="stylesheet" type="text/css"
			href="jquery-easyui/demo/demo.css">

	<style type="text/css">
	     label {
	    width: 10em;
	    float: left;
	}  
	.empty{
		width: 4em;
	}
	</style>
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

</style>
		<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
		<script type="text/javascript" src="jquery-easyui/jquery-1.7.2.min.js"></script>
		<script type="text/javascript"
			src="jquery-easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
		<script type="text/javascript"
			src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
			<script type="text/javascript" src="js/commom.js"></script>
	</head>
	<body style="overflow-y: scroll">
		<table class="easyui-datagrid" toolbar="#toolbar" title="办学指导思想"></table>
		
		<hr color="blue" width="100%" />
		<table id="showInfo" class="doc-table"
			url="pages/T16/loadInfo?selectYear="+selectYear>
			<tbody>
<tr>
		    <td style="background-color: white" align="center">项目</td>
		     <td style="background-color: white" align="center">内容</td>
		      <td style="background-color: white" align="center">备注</td>
		       <td style="background-color: white"></td>
		</tr>
		
			<form id="schGuiForm1" method="post">
		<tr >
		   <td style="background-color: white" width="10%" >
		      <div id="di1" style="display:inline;">
		         <span id="ItemSpan1"></span>
		         </div>
		         <div id="input1"  style="display:none;" >
		         <input id="seqNumber1" name="t16Bean.SeqNumber" type="hidden"/>
		          <input id="Item1"  name="t16Bean.Item" type="text">
		          </div>
		   </td>
		     <td style="background-color: white" width="50%" field="contents">
                  <div  id="di1" style="display:inline;">
					<span id="ContentsSpan1"></span>
			 </div>
			 <div id="input1"  style="display:none;" >
			  <!--  <input id="Contents1"  name="t16Bean.Contents" type="text"  size="60" >-->
			  <textarea  id="Contents1"  name="t16Bean.Contents" type="text"  style="width: 400px;height: 50px;max-width: 400px;max-height: 100px;"></textarea>
			  </div>
            </td>
		     <td style="background-color: white" width="30%"  field="note">
                  <div  id="di1"  style="display:inline;">
					<span id="NoteSpan1"> </span>
			 </div>
			 <div id="input1"  style="display:none;" >
			  <textarea id="Note1" name ="t16Bean.Note"  style="width: 200px;height: 50px;max-width: 200px;max-height: 100px;"> </textarea>
			  </div>
            </td>
            <td style="background-color: white" width="10%" align="center">
		       <div  id="di1" style="display:inline;">
		       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="modify1()">修改</a> 
		       </div>
		       <div id="input1"   style="display:none;" > 
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="save1()">保存</a> 
		         
		       </div>
		       <div id="input1"   style="display:none;" >
		       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancel1()">取消</a>
		       </div>
		       
		       </td>
		</tr>
			</form>
	
	<form id="schGuiForm2" method="post">
		<tr>
		
		     <td style="background-color: white" width="10%" >
		       <div id="di2" style="display:inline;">
		         <span id="ItemSpan2"></span>
		        </div>
		        <div id="input2"  style="display:none;" >
		          <input id="seqNumber2" name="t16Bean.SeqNumber" type="hidden"/>
		         <input id="Item2"  name="t16Bean.Item" type="text">
		         </div>
		   </td>
		     <td style="background-color: white" width="50%">
                  <div  id="di2" style="display:inline;">
					<span id="ContentsSpan2"></span>
			 </div>
			   <div id="input2"  style="display:none;" >
			  <textarea  id="Contents2"  name="t16Bean.Contents" type="text"  style="width: 400px;height: 50px;max-width: 400px;max-height: 100px;"></textarea>
			  </div>
            </td>
		     <td style="background-color: white" width="30%">
                  <div  id="di2" style="display:inline;">
					<span id="NoteSpan2"></span>
			 </div>
			   <div id="input2"  style="display:none;" >
			  <textarea id="Note2" name ="t16Bean.Note"  style="width: 200px;height: 50px;max-width: 200px;max-height: 100px;"> </textarea>
			  </div>
            </td>
		       <td style="background-color: white" width="10%" align="center">
		       <div id="di2" style="display:inline;">
		       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="modify2()">修改</a> 
		       </div>
		       <div id="input2"   style="display:none;" >
		      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="save2()">保存</a> 
		       </div>
		       <div id="input2"   style="display:none;" >
		         <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancel2()">取消</a>
		       </div>
		       
		       </td>
		</tr>
		</form>
	
			</tbody>
		</table>
		<div id="toolbar" style="height:auto">
	    <div style="float: left;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newObject()">添加</a>
				</div>
			 <form  id="exportForm"  style="float: right;"  method="post" >
			显示： <select class="easyui-combobox" id="cbYearContrast" panelHeight="auto" style="width:80px; padding-top:5px; margin-top:10px;"  editable=false ></select>
	 	</form>

	
	</div>
		
	
		<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<div></div>
		<div class="ftitle">添加学校办学指导思想</div>
		
		<form id="t16Form" method="post">
		<table>
			<tr>
			    <td style="valign:left">
						<label>项目名：</label> 
						<select class='easyui-combobox' id='Item' name='t16Bean.Item'  style="width:140px" editable="false" >
						   <option value="1.校训">1.校训</option>
						   <option value="2.定位与发展目标">2.定位与发展目标</option> 
						</select>
						 <span id="ItemSpan"></span>
			</td>
			<!-- 
			<td class="empty"></td>
			<td>
			<label>选择年份：</label> 	
			<select class="easyui-combobox" id="cbYearContrast" name="t16Bean.Time" editable="false" panelHeight="auto" style="width:80px; padding-top:5px; margin-top:10px;">
			</select>
			</td>
			 -->
			</tr>
			<tr>
				<td style="valign:left" colspan="3"><label>内容：</label>
					<textarea id="Contents" name="t16Bean.Contents" style="resize:none" cols="50" rows="10"></textarea>
					<span id="ContentsSpan"></span>
				</td>
				</tr>
				<tr>
				<td style="valign:left" colspan="3"><label>备注：</label>
					<textarea id="Note" name="t16Bean.Note" style="resize:none" cols="50" rows="10"></textarea>
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
	var data;
	var selectYear = $("#cbYearContrast").combobox('getValue'); 

	$(document).ready(function() {

		loadAuotCityList();
	});

	function exports() {
    	var temp = encodeURI('表1-6办学指导思想（党院办）.xls');
	    $('#exportForm').form('submit', {
	    url : "pages/T16/dataExport?excelName="+temp ,
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

	function cancel1(){
		$('div#di1').css("display","inline");
		$('div#input1').css("display","none");
		loadAuotCityList();
	}

	function cancel2(){
		$('div#di2').css("display","inline");
		$('div#input2').css("display","none");
		loadAuotCityList();
	}

	function loadAuotCityList() {
		$.ajax( {
			type : "POST", //post请求
			url : "pages/T16/auditingData", //请求action的URL
			dataType : "json",//返回类型
			success : function(result) { //回调函数	    		 
				// var data = eval('('+result+')');
			    data = result;
			    var data1=result;
				if (data==null)
					{
					alert("还未录入数据！请添加！") ;
					}
				else{
					$('#seqNumber1').val(data.seqNumber1);
					$('#ItemSpan1').html(data1.item1);
				$('#ContentsSpan1').html(data1.contents1);
				$('#NoteSpan1').html(data1.note1);
				$('#seqNumber2').val(data.seqNumber2);
				$('#ItemSpan2').html(data1.item2);
				$('#ContentsSpan2').html(data1.contents2);
				$('#NoteSpan2').html(data1.note2);

				}
			}
		});
	}
	
	var url ;

	function modify1(){
		$('div#di1').css("display","none");
		$('div#input1').css("display","inline");
		 document.getElementById("Item1").value=data.item1 ;
		 document.getElementById("Contents1").value=data.contents1 ;
		 document.getElementById("Note1").value=data.note1 ;
		
		}

	function modify2(){
		//alert(345);
		$('div#di2').css("display","none");
		$('div#input2').css("display","inline");
		 document.getElementById("Item2").value=data.item2 ;
		 document.getElementById("Contents2").value=data.contents2 ;
		 document.getElementById("Note2").value=data.note2 ;
		}

	
	 function save1()
     {
    		 url = 'pages/T16/edit' ;
    			 $.ajax({
						type : "POST",
						url : url,
						data: $('#schGuiForm1').serialize() ,
						async : "true",
						dataType : "text",
						success : function(result) {
	    			 //json格式转化
					    var result = eval('('+result+')');
					    $.messager.alert('提示', result.data) ;
					    if(result.state){
					    	cancel11();
						   	loadAuotCityList();
					    }
					}
					}).submit();
    	
      }

	    function cancel11(){
	    	$('div#di1').css("display","inline");
			$('div#input1').css("display","none");
	    }
	 
	 function save2()
     {
		 url = 'pages/T16/edit' ;
		 $.ajax({
				type : "POST",
				url : url,
				data: $('#schGuiForm2').serialize() ,
				async : "true",
				dataType : "text",
				success : function(result) {
			 //json格式转化
			    var result = eval('('+result+')');
			    $.messager.alert('提示', result.data) ;
			    if(result.state){
			    	cancel22();
				   	loadAuotCityList();
			    }
			}
			}).submit();
      }

	 function cancel22(){
	    	$('div#di2').css("display","inline");
			$('div#input2').css("display","none");
	    } 
	function singleSearch(){
   	 $('#auditing').form('submit',{
   		 url: 'pages/T16/singleSearch',
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
	    		 url: 'pages/T181/uploadFile',
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
			    		 $('#dg').datagrid('reload'); // reload the user data
	    		 	}
	    		 }
	    		 });
	    }
	    
	    function check(){
	    	var fileName = $('#uploadFile').val() ;
	    	
	    	if(fileName == null || fileName == ""){
	    		return false ;
	    	}
	    	
	    	var pos = fileName.lastIndexOf(".") ;
	    	var suffixName = fileName.substring(pos, fileName.length) ;
	    	
	    	if(suffixName == ".xls"){
	    		return true ;
	    	}else{
	    		return false ;
	    	}
	    } 
	    
	    function newObject(){
	    	url = 'pages/T16/insert' ;
		    $('#dlg').dialog('open').dialog('setTitle','添加校训及目标');
		    $('#t16Form').form('reset');
	    }

	    function singleImport(){
		    //录入数据的表单提交

	    	 $('#t16Form').form('submit',{
				    url: 'pages/T16/insert' ,
				    data: $('#t16Form').serialize(),
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
			var item = $('#Item').val() ;
			var contents = $('#Contents').val() ;
			var note = $('#Note').val() ;
			
			//根据数据库定义的字段的长度，对其进行判断
			if(Item == null || Item.length==0 || Item.length > 100){
				$('#ItemSpan').focus();
				$('#ItemSpan').select();
				$('#ItemSpan').html("<font style=\"color:red\">校训不能为空或长度不超过100</font>") ;
				return false ;
			}else{
				$('#ItemSpan').html("") ;
			}

			if(contents == null || contents.length==0 || contents.length > 1000){
				$('#ContentsSpan').focus();
				$('#ContentsSpan').select();
				$('#ContentsSpan').html("<font style=\"color:red\">校训不能为空或长度不超过500</font>") ;
				return false ;
			}else{
				$('#ContentsSpan').html("") ;
			}
			
			if(note !=null && note.length > 1000){
				$('#NoteSpan').html("<font style=\"color:red\">备注中文字数不超过500</font>") ;
				return false ;
			}else{
				$('#NoteSpan').html("") ;
			}
			return true ;
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
				 	
				 	deleteCourses(ids) ;
				 	
				 }
			});
	    }
	    
	    function deleteCourses(ids){
	    	$.ajax({ 
	    		type: "POST", 
	    		url: "pages/T181/deleteByIds?ids=" + ids, 
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
	    
	    function loadDic(){
		    $('#dicDlg').dialog('open').dialog('setTitle','高级查询');
		    loadDictionary() ;
		    
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


</html>
