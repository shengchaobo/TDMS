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
    
    <title>My JSP 'T722_TeachAchieveAward_Tea.jsp' starting page</title>
    
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
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script> 
	<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="js/commom.js"></script>
</head>

<% request.setAttribute("CHECKTYPE",Constants.CTypeOne); %>
<% request.setAttribute("NOCHECK",Constants.NO_CHECK); %>
<% request.setAttribute("PASS",Constants.PASS_CHECK); %>
<body style="overflow-y:scroll" onload="myMarquee('T722','<%=request.getAttribute("CHECKTYPE")%>')">
<div  id="floatDiv">
        <span style="font:12px; font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;审核不通过提示消息：</span>
        <marquee id="marquee"  scrollAmount="1"  width="900"  height="40" direction="up"  style="color: red;"  onmouseover="stop()" onmouseout="start()">
        </marquee>       
  </div>
  <br/>

	<table id="unverfiedData" title="待审核数据域审核未通过数据" class="easyui-datagrid" 
	url="pages/T722/auditingData?checkNum=<%=request.getAttribute("NOCHECK")%>"
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="false" singleSelect="false" >
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber">编号</th>
				<th  data-options="field:'checkState'"   formatter="formatCheckState">审核状态</th>
				<th field="leader" >负责人</th>
				<th field="teaID">教工号</th>
		     </tr>
		</thead>
		<thead>
			<tr>
			
				<th field="awardName">奖励名称</th>
				<th field="teaUnit">所属教学单位</th>
				<th field="unitID">单位号</th>
				<th field="otherTeaNum">其他参与教师人数</th>
				<th field="otherTea">其他教师</th>
				<th field="awardLevel">级别</th>
				<th field="awardTime" formatter="formattime">获奖时间</th>
				<th field="awardFromUnit" >授予单位</th>
				<th field="appvlID" >批文号</th>	
				<th field="note">备注</th>
				
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="height:auto">
		<div style="float: left;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newCourse()">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editCourse()">编辑</a> 
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
	
	<table id="verfiedData" title="审核通过数据" class="easyui-datagrid" 
	url="pages/T722/auditingData?checkNum=<%=request.getAttribute("PASS")%>">
		<thead data-options="frozen:true">
			<tr>			
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber">编号</th>
				<th field="leader" >负责人</th>
				<th field="teaID">教工号</th>
		     </tr>
		</thead>
		<thead>
			<tr>
			
				<th field="awardName">奖励名称</th>
				<th field="teaUnit">所属教学单位</th>
				<th field="unitID">单位号</th>
				<th field="otherTeaNum">其他参与教师人数</th>
				<th field="otherTea">其他教师</th>
				<th field="awardLevel">级别</th>
				<th field="awardTime" formatter="formattime">获奖时间</th>
				<th field="awardFromUnit" >授予单位</th>
				<th field="appvlID" >批文号</th>	
				<th field="note">备注</th>
				
			</tr>
		</thead>
	</table>
	
	<div id="toolbar2" style="float: right;">
	<form action='pages/T722/dataExport?excelName=<%=URLEncoder.encode("表7-2-2教学成果奖","UTF-8")%>'   method="post"  id="exportForm" enctype="multipart/form-data"  style="float: right;">
					  <select class="easyui-combobox"  id="cbYearContrast1" name="selectYear"  editable=false ></select>&nbsp;&nbsp;
						<a href='javascript:submitForm()'   style="font:12px;color: black;text-decoration:none;" >
								数据导出
						</a> &nbsp;&nbsp;&nbsp;&nbsp;		
			</form>
		
	</div>
	
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="title1">教学成果奖批量导入</h3>
		<div class="fitem" id="item1">
			<form method="post" id="batchForm" enctype="multipart/form-data">
			<select class="easyui-combobox"  id="cbYearContrast" name="selectYear"></select>
				<input type="file" name="uploadFile" id="fileToUpload" class="easyui-validatebox" size="48" style="height: 24px;" required="true" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href="pages/T722/downloadModel?saveFile=<%=URLEncoder.encode("表7-2-2教学成果奖.xls","UTF-8")%>"  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
			<a href="123"></a>
		</div>
    <hr></hr>	
			
		<h3 class="title1">教学成果奖逐条导入</h3>
		
		<form id="t722Form" method="post">
		<table>
		
		<tr>
			<td>
					<div class="fitem">
						<label>奖励名称：</label> 
						<input id="seqNumber" name="teachAchieveAwardTea.SeqNumber" type="hidden" value="0">
							<input id="Time" name="teachAchieveAwardTea.Time" type="hidden" value="0">
						<input id="AwardName" type="text" name="teachAchieveAwardTea.AwardName" 
							><span id="AwardNameSpan"></span>
					</div>
				</td>
				<td class="empty"></td>	
				<td>
					<div class="fitem">
					<label>所属教学单位：</label>
						<input type="hidden" name="teachAchieveAwardTea.TeaUnit" id="TeaUnit"/>
						<input id="UnitID" type="text" name="teachAchieveAwardTea.UnitID" 
							 class='easyui-combobox' data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDIDepartmentAca',listHeight:'auto',editable:false,
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
						<input id="TeaID" type="hidden" name="teachAchieveAwardTea.TeaID" >
					    <input id="Leader" type="text" name="teachAchieveAwardTea.Leader"
					     class='easyui-combobox' data-options="valueField:'teaName',textField:'teaId',url:'pages/T411/loadT411',listHeight:'auto',editable:true,
							 onSelect:function(){
							   document.getElementById('TeaID').value=$(this).combobox('getText') ;
							 }">
							
							<span id="TeaIDSpan"></span>
					</div>
					</td>
					<td class="empty"></td>	
				<td>
					<div class="fitem">
						<label>其他参与教师人数：</label> 
						<input id="OtherTeaNum" type="text" name="teachAchieveAwardTea.OtherTeaNum"
							><span id="OtherTeaNumSpan"></span>
					</div>
				</td>
				</tr>
				<tr>
				<td>
					<div class="fitem">
						<label>其他教师：</label> 
						<input id="OtherTea" type="text" name="teachAchieveAwardTea.OtherTea"
							><span id="OtherTeaSpan"></span>
					</div>
				</td>
			<td class="empty"></td>	
				<td>
					<div class="fitem">
						<label>级别：</label> 
						<input class='easyui-combobox' id="AwardLevel" name="teachAchieveAwardTea.AwardLevel"
							data-options="valueField:'indexId',textField:'awardLevel',url:'pages/DiAwardLevel/loadDiAwardLevel',listHeight:'auto',editable:false">
						<span id="AwardLevelSpan"></span>
						
					</div>
				</td>
				</tr>
				<tr>
				<td>
					<div class="fitem">
						<label>获奖时间：</label> 
						<input  id="AwardTime"  class="easyui-datebox" style="width:80px" name="teachAchieveAwardTea.AwardTime" editable="false">
						<span id="AwardTimeSpan"></span>
					</div>
				</td>
			<td class="empty"></td>	
				<td>
					<div class="fitem">
						<label>授予单位：</label> 
						<input id="AwardFromUnit"   type="text"  name="teachAchieveAwardTea.AwardFromUnit"
							><span id="AwardFromUnitSpan"></span>
					</div>
				</td>
				</tr>
				<tr>
				<td>
					<div class="fitem">
						<label>批文号：</label> 
						<input id="AppvlID" type="text" name="teachAchieveAwardTea.AppvlID"
							><span id="AppvlIDSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
			
				<td style="valign:left" colspan="3"><label>备&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
					<textarea id="Note" name="teachAchieveAwardTea.Note" style="resize:none" cols="50" rows="10"></textarea>
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
	
	var currentYear = new Date().getFullYear();
    	var select = document.getElementById("cbYearContrast");
    	for (var i = 0; i <= 10; i++) {
        var theOption = document.createElement("option");
        	theOption.innerHTML = currentYear-i + "年";
        	theOption.value = currentYear-i;
        	select.appendChild(theOption);
    	}
    	
	    var url;
	    function reloadgrid ()  { 
        //查询参数直接添加在queryParams中 
         var queryParams = $('#unverfiedData').datagrid('options').queryParams;  
         queryParams.seqNum = $('#seqNum').val(); 
         queryParams.startTime = $('#startTime').datetimebox('getValue');	         		     
    	 queryParams.endTime  = $('#endTime').datetimebox('getValue');        	 
         $("#unverfiedData").datagrid('reload'); 
    }
	      //模板导入
	 function batchImport(){
		  var fileName = $('#fileToUpload').val() ; 	
		  if(fileName == null || fileName == ""){
			  $.messager.alert('Excel批量用户导入', '请选择将要上传的文件!');      
		   		return false ;
		  }	
		 
		  var pos = fileName.lastIndexOf(".") ;
		  var suffixName = fileName.substring(pos, fileName.length) ; 	
		  if(suffixName != ".xls"){
			   $.messager.alert('Excel批量用户导入','文件类型不正确，请选择.xls文件!');   
		   		return false ;
		 }
	  	 $('#batchForm').form('submit',{
	  	 
	  		 url: 'pages/T722/uploadFile',
	  		 type: "post",
		     dataType: "json",
	  		 onSubmit: function(){
	  			 return true;
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
	    
	    function newCourse(){
	      $('.title1').show();
	    	$('#item1').show();
	    	$('hr').show();
	        url="pages/T722/insert";
		    $('#dlg').dialog('open').dialog('setTitle','添加教学成果奖');
		    $('#t722Form').form('reset');
	    }

       function singleImport(){
		    //录入数据的表单提交
	    	 $('#t722Form').form('submit',{
				    url: url,
				    data: $('#t722Form').serialize(),
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
								myMarquee('T722', CTypeOne);
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
			var awardName = $('#AwardName').val();
			var teaUnit = $('#UnitID').combobox('getText');	
			var teaId = $('#Leader').combobox('getText');
			var teaName = $('#Leader').combobox('getValue');
			var otherTeaNum = $('#OtherTeaNum').val();	
			var otherTea = $('#OtherTea').val();
			var awardLevel = $('#AwardLevel').combobox('getText');
			var awardTime = $('#AwardTime').datebox('getText');
			var awardFromUnit = $('#AwardFromUnit').val();
			var appvlID = $('#AppvlID').val();
			var note = $('#Note').val();
			//根据数据库定义的字段的长度，对其进行判断
			if(awardName == null || awardName.length==0 || awardName.length > 50){
				alert("奖励名称不能为空或长度不超过50");
				return false;
			}
			if(teaUnit == null || teaUnit.length == 0){
				alert("所属教学单位不能为空");
				return false ;
			}
			if(teaId == null || teaId.length == 0){
			    alert("负责人不能为空");
				return false ;
			}
			if (teaId == null ||  teaId == ''  || teaId.length == 0 || teaId == teaName) {
				alert("教工号不能为空或者教师库中无该教工号");
				return false;
			}
			if (!(/(^[0-9]\d*$)/.test(otherTeaNum))) {
			alert("其他参与教师人数必须为整数");
			return false;
	         }
	
			if(awardLevel == null || awardLevel.length==0 || awardLevel.length > 20){
				alert("级别不能为空或长度不超过20");
				return false ;
			}
			if(awardTime == null || awardTime.length == 0 ){
	            alert("立项时间不能为空");
				return false ;
			}
			if(awardFromUnit == null || awardFromUnit.length == 0 || awardFromUnit.length >50){
				alert("授予单位不能为空或长度不超过50");
				return false ;
			}
			
			if(appvlID == null || appvlID.length == 0|| appvlID.length >50){
			alert("批文号不能为空或长度不超过50");
				return false ;
			}
			
			if(note.length > 1000){
			   alert("备注中文字数不超过1000");
				return false ;
			}
			
			return true ;
		}
		
		function editCourse(){
	    	var row = $('#unverfiedData').datagrid('getSelections');
	    	
	    	if(row.length != 1){
	    		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
	    		return ;
	    	}
	    	
	    	url = 'pages/T722/edit' ;
	    	
	    	 $('.title1').hide();
	    	$('#item1').hide();
	    	$('hr').hide();
	    	
	    	$('#dlg').dialog('open').dialog('setTitle','修改教学成果奖');
	    	$('#seqNumber').val(row[0].seqNumber) ;
	    	$('#Time').val(formattime(row[0].time)) ;
	    	$('#AwardName').val(row[0].awardName) ;
	    	$('#UnitID').combobox('select', row[0].unitID) ;
	    	$('#Leader').combobox('select', row[0].leader) ;
	    	$('#OtherTeaNum').val(row[0].otherTeaNum) ;
	    	$('#OtherTea').val(row[0].otherTea) ;
	    	$('#AwardLevel').combobox('select', row[0].awardLevelID) ;
	        $('#AwardTime').datebox('setValue',formattime(row[0].awardTime)) ;
			$('#AwardFromUnit').val(row[0].awardFromUnit) 
			$('#AppvlID').val(row[0].appvlID) ;
			$('#Note').val(row[0].note) ;
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
	    		url: "pages/T722/deleteByIds?ids=" + ids, 
	    		async:"true",
	    		dataType: "text",
	    		success: function(result){
	    			result = eval("(" + result + ")");

					if(result.state){
						alert(result.data) ;
						myMarquee('T722', CTypeOne);
						 $('#unverfiedData').datagrid('reload') ;
					}
	    		}
	    	}).submit();
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
	    
	      //根据用户选择的年显示相应年的数据
    $(function(){ 
		 $("#cbYearContrast1").combobox({  
	        onChange:function(newValue, oldValue){ 
		     //查询参数直接添加在queryParams中 
	         var  queryYear = newValue;
	         var queryParams = $('#verfiedData').datagrid('options').queryParams;  
	         queryParams.queryYear = queryYear;  
	         $("#verfiedData").datagrid('reload'); 
	        }
	   });
	})
	    
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

