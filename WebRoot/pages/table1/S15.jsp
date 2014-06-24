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

		<title>学校基本情况</title>
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
		<script type="text/javascript"
			src="jquery-easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
		<script type="text/javascript"
			src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	</head>
	<body style="overflow-y: scroll">
		<table class="easyui-datagrid" toolbar="#toolbar" title="学校基本情况"></table>
		
		<hr color="blue" width="100%" />
		
		<table id="showInfo" class="doc-table"
			url="pages/S15/auditingData">
			<tbody>

				<tr>
					<th colspan=2 align="center"
						style="width: 100px; height: 50px; background-color: white">
						项目
					</th>
					<th align="center"
						style="width: 80px; height: 50px; background-color: white">
						数量（个）
					</th>
					<th align="center"
						style="width: 80px; height: 50px; background-color: white">
						专业科研用房面积（平方米）
					</th>
				</tr>
				<tr>
					<th colspan=2 align="center"
						style="width: 100px; background-color: white">
						总计
					</th>
					<th style="background-color: white">
						<span id="SumResNumSpan"></span>
					</th>
					<th style="background-color: white">
						<span id="SumResAreaSpan"> </span>
					</th>
				</tr>
				<tr>
					<th colspan=2 align="left"
						style="width: 100px; background-color: white">
						1. 国家实验室
					</th>
					<th style="background-color: white">
						<span id="NationResNumSpan"></span>
					</th>
					<th style="background-color: white">
						<span id="NationResAreaSpan"></span>
					</th>
				</tr>
				<tr>
					<th colspan=2 align="left"
						style="width: 100px; background-color: white">
						2. 国家重点验室
					</th>
					<th style="background-color: white">
						<span id="NationKeyResNumSpan"></span>
					</th>
					<th style="background-color: white">
						<span id="NationKeyResAreaSpan"></span>
					</th>
				</tr>
				<tr>
					<th colspan=2 align="left"
						style="width: 100px; background-color: white">
						3. 国家工程（技术）研究中心
					</th>
					<th style="background-color: white">
						<span id="NationEnginResNumSpan"></span>
					</th>
					<th style="background-color: white">
						<span id="NationEnginResAreaSpan"></span>
					</th>
				</tr>
				<tr>
					<th colspan=2 align="left"
						style="width: 100px; background-color: white">
						4. 其他国家级科研机构
					</th>
					<th style="background-color: white">
						<span id="OtherNationResNumSpan"></span>
					</th>
					<th style="background-color: white">
						<span id="OtherNationResAreaSpan"></span>
					</th>
				</tr>
				<tr>
					<th colspan=2 align="left"
						style="width: 100px; background-color: white">
						5. 省、部级设置的研究所（院、中心）
					</th>
					<th style="background-color: white">
						<span id="ProviResNumSpan"></span>
					</th>
					<th style="background-color: white">
						<span id="ProviResAreaSpan"></span>
					</th>
				</tr>
				<tr>
					<th colspan=2 align="left"
						style="width: 100px; background-color: white">
						6. 省、部级设置的实验室
					</th>
					<th style="background-color: white">
						<span id="ProviLabNumSpan"></span>
					</th>
					<th style="background-color: white">
						<span id="ProviLabAreaSpan"></span>
					</th>
				</tr>
				<tr>
					<th colspan=2 align="left"
						style="width: 100px; background-color: white">
						7. 其他省级科研机构
					</th>
					<th style="background-color: white">
						<span id="OtherProviResNumSpan"></span>
					</th>
					<th style="background-color: white">
						<span id="OtherProviResAreaSpan"></span>
					</th>
				</tr>
				<tr>
					<th rowspan=3 align="left"
						style="width: 60px; background-color: white">
						8. 人文科学重点研究基地
					</th >
					<th align="left" style="width: 40px; background-color: white">总计</th>
					<th  style="background-color: white">
						<span id="HumanResSumNumSpan"></span>
					</th>
					<th style="background-color: white">
						<span id="HumanResSumAreaSpan"></span>
					</th>
				</tr>
				<tr>
					<th align="left" style="width: 40px; background-color: white">
						其中：国家级
					</th>
					<th style="background-color: white">
						<span id="HumanNationResNumSpan"></span>
					</th>
					<th style="background-color: white">
						<span id="HumanNationResAreaSpan"></span>
					</th>
				</tr>
				<tr>
					<th align="left" style="width: 40px; background-color: white">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;省部级
					</th>
					<th style="background-color: white">
						<span id="HumanProviResNumSpan"></span>
					</th>
					<th style="background-color: white">
						<span id="HumanProviResAreaSpan"></span>
					</th>
				</tr>
				<tr>
					<th colspan=2 align="left"
						style="width: 100px; background-color: white">
						9. 市级科研机构
					</th>
					<th style="background-color: white">
						<span id="CityResNumSpan"></span>
					</th>
					<th style="background-color: white">
						<span id="CityResAreaSpan"></span>
					</th>
				</tr>
				<tr>
					<th colspan=2 align="left"
						style="width: 100px; background-color: white">
						10. 教学单位科研实验室
					</th>
					<th style="background-color: white">
						<span id="TeaUnitResNumSpan"></span>
					</th>
					<th style="background-color: white">
						<span id="TeaUnitResAreaSpan"></span>
					</th>
				</tr>
				<tr>
					<th colspan=2 align="left"
						style="width: 100px; background-color: white">
						11. 其他校级科研机构
					</th>
					<th style="background-color: white">
						<span id="OtherSchResNumSpan"></span>
					</th>
					<th style="background-color: white">
						<span id="OtherSchResAreaSpan"></span>
					</th>
				</tr>
			</tbody>
		</table>
		<div id="toolbar" style="height: auto">
			<div>
				<!-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newCourse()">添加</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-edit" plain="true" onclick="editCourse()">编辑</a>-->
				<a href="pages/S15/dataExport?excelName=表1-5-1校级以上科研机构（科研处）.xls" class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a>
				<!-- 
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newCourse()">数据导入</a>
			<a href='pages/T11/downloadModel?saveFile=<%=URLEncoder.encode("表1-1学校基本信息（党院办）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a> -->
			</div>
		</div>
		
	
		<div id="dlg" class="easyui-dialog"
		style="width:500px;height:180px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<div class="ftitle">学校基本信息导入</div>
		<div class="fitem">
			<form id="batchForm" method="post" enctype="multipart/form-data">
				<label>批量上传：</label> 
				<input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox"
					validType="fileType['xls']" required="true" invalidMessage="请选择Excel格式的文件" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">导入</a>
			</form>
		</div>
	</div>

		
	<!-- 
	<div id="dicDlg" class="easyui-dialog" style="width:500px;padding:10px 20px" closed="true">
		<div class="ftitle">高级检索</div>
		<div id="dicTables"  class="fitem">
		</div>
		<div id="dices"  class="fitem"></div>
	</div>
	  -->
	<div id="dlg-buttons1" style="display:none">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="save()">保存</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="cancel()">取消</a>
	</div>
	
	</body>

	<script type="text/javascript">
	$(document).ready(function() {

		loadAuotCityList();
	});

	function loadAuotCityList() {
		//alert(123);
		$.ajax( {
			type : "POST", //post请求
			url : "pages/S15/autidingdata", //请求action的URL
			dataType : "json",//返回类型
			success : function(result) { //回调函数	 		 
				var data = result;
				$('#SumResNumSpan').html(data.sumResNum);
				$('#SumResAreaSpan').html(data.sumResArea);
				$('#NationResNumSpan').html(data.nationResNum);
				$('#NationResAreaSpan').html(data.nationResArea);
				$('#NationKeyResNumSpan').html(data.nationKeyResNum);
				$('#NationKeyResAreaSpan').html(data.nationKeyResArea);
				$('#NationEnginResNumSpan').html(data.nationEnginResNum);
				$('#NationEnginResAreaSpan').html(data.nationEnginResArea);
				$('#OtherNationResNumSpan').html(data.otherNationResNum);
				$('#OtherNationResAreaSpan').html(data.otherNationResArea);
				$('#ProviResNumSpan').html(data.proviResNum);
				$('#ProviResAreaSpan').html(data.proviResArea);
				$('#ProviLabNumSpan').html(data.proviLabNum);
				$('#ProviLabAreaSpan').html(data.proviLabArea);
				$('#OtherProviResNumSpan').html(data.otherProviResNum);
				$('#OtherProviResAreaSpan').html(data.otherProviResArea);
				$('#HumanResSumNumSpan').html(data.humanResSumNum);
				$('#HumanResSumAreaSpan').html(data.humanResSumArea);
				$('#HumanNationResNumSpan').html(data.humanNationResNum);
				$('#HumanNationResAreaSpan').html(data.humanNationResArea);
				$('#HumanProviResNumSpan').html(data.humanProviResNum);
				$('#HumanProviResAreaSpan').html(data.humanProviResArea);
				$('#CityResNumSpan').html(data.cityResNum);
				$('#CityResAreaSpan').html(data.cityResArea);
				$('#TeaUnitResNumSpan').html(data.teaUnitResNum);
				$('#TeaUnitResAreaSpan').html(data.teaUnitResArea);
				$('#OtherSchResNumSpan').html(data.otherSchResNum);
				$('#OtherSchResAreaSpan').html(data.otherSchResArea);
				
			}
		});
	} 

	var url;
//	var seqNumber;
	var data;

	function cancel(){
		 $('div#di').show();
		
		// $("input#input").css("display","block");
		 $("div#input").css("display","none");   
		 $('div#dlg-buttons1').css("display","none");
		 loadAuotCityList();
		}
	
	  function batchImport(){
	    	 $('#batchForm').form('submit',{
	    		 url: 'pages/T11/uploadFile',
	    		 type: "post",
		         dataType: "json",
	    		 onSubmit: function(){
	    		   alert(123);
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
	    	 loadAuotCityList();
	    }
	    
	    function check(){
	    	var fileName = $("#uploadFile").val() ;
	    	alert(fileName);
	    	
	    	if(fileName == null || fileName == ""){
	    		return false ;
	    	}
	    	
	    	var pos = fileName.lastIndexOf(".") ;
	    	var suffixName = fileName.substring(pos, fileName.length) ;
	    	
	    	if(suffixName == ".xls"){
	    		return true ;
	    	}else{
	    		 $.messager.alert("操作提示", "导入Excel格式不正确！");
	    		return false ;
	    	}
	    } 

	 function newCourse(){
	    	//url = 'pages/T11/uploadFile' ;
		    $('#dlg').dialog('open').dialog('setTitle','导入学校基本信息');
		   // $('#resInsForm').form('reset');
	    }

	function singleImport() {
		//录入数据的表单提交
		$('#schBasForm').form('submit', {
			url : 'pages/T11/edit',
			data : $('#schBasForm').serialize(),
			type : "post",
			dataType : "json",
			onSubmit : function() {
				return validate();
			},
			//结果返回
			success : function(result) {
				//json格式转化
			var result = eval('(' + result + ')');
			$.messager.alert('温馨提示', result.data);
			if (result.state) {
				$('#dlg').dialog('close');
				$('#unverifiedData').datagrid('reload');
			}
		}
		});
	}

	function validate() {
		
        var reg1=/\d{4}-\d{8}/ ;
        var reg2=/\d{4}/;
		//获取文本框的值
		var schAddress = $('#SchAddress').val();
		var schTel = $('#SchTel').val();
		var schFax = $('#SchFax').val();
		var schFillerName = $('#SchFillerName').val();
		var schFillerTel = $('#SchFillerTel').val();
		var schFillerEmail = $('#SchFillerEmail').val();
		var schName = $('#SchName').val();
		var schID = $('#SchID').val();
		var schEnName = $('#SchEnName').val();
		var schType = $('#SchType').val();
		var schQuality = $('#SchQuality').val();
		var schBuilder = $('#SchBuilder').val();
		var majDept = $('#MajDept').val();
		var schUrl = $('#SchUrl').val();
		var admissionBatch = $('#AdmissonBatch').val();
		var sch_BeginTime = $('#Sch_BeginTime').val();
		var mediaUrl = $('#MediaUrl').val();
		var yaohuSchAdd = $('#YaohuSchAdd').val();
		var pengHuSchAdd = $('#PengHuSchAdd').val();
		//根据数据库定义的字段的长度，对其进行判断
		if (schAddress == null || schAddress.length == 0
				||schAddress.length > 150) {
			$('#SchAddress').focus();
			$('#SchAddress').select();
			$('#SchAddressSpan1').html(
					"<font style=\"color:red\">學校地址不能为空且不能超過150個字符</font>");
			return false;
		} else {
			$('#SchAddressSpan1').html("");
		}
		
		if (schTel == null || schTel.length == 0 || schTel.length<12) {
			$('#SchTel').focus();
			$('#SchTel').select();
		$('#SchTelSpan1').html(
					"<font style=\"color:red\">學校號碼不能为空且不能少于12個字符</font>");
			return false;
		}
		else {
			$('#SchTelSpan1').html("");
		//	alert(222);
		}

	    if (!reg1.test(schTel)){
		    $('#SchTel').focus();
			$('#SchTel').select();
			$('#SchTelSpan1').html(
			"<font style=\"color:red\">學校號碼格式只能为：0791-88126683</font>");
			return false;
		}	
		else {
			$('#SchTelSpan1').html("");
		}

	    if (schFax == null || schFax.length == 0 || schFax.length<12) {
			$('#SchFax').focus();
			$('#SchFax').select();
		    $('#SchFaxSpan1').html(
					"<font style=\"color:red\">學校號碼不能为空且不能少于12個字符</font>");
			return false;
		}
		else {
			$('#SchFaxSpan1').html("");
		//	alert(222);
		}

	    if (!reg1.test(schFax)){
		    $('#SchFax').focus();
			$('#SchFax').select();
			$('#SchFaxSpan1').html(
			         "<font style=\"color:red\">學校號碼格式只能为：0791-88126683</font>");
			return false;
		}	
		else {
			$('#SchFaxSpan1').html("");
		}

	    if (schFillerName  == null || schFillerName .length == 0
				||schFillerName .length > 50) {
			$('#SchFillerName ').focus();
			$('#SchFillerName').select();
			$('#SchFillerNameSpan1').html(
					"<font style=\"color:red\">填报人姓名不能为空且不能超过25个字符！</font>");
			return false;
		} else {
			$('#SchFillerNameSpan1').html("");
		}

	    if (schFillerTel  == null || schFillerTel .length == 0 || schFillerTel .length<12) {
			$('#SchFillerTel ').focus();
			$('#SchFillerTel').select();
		$('#SchFillerTelSpan1').html(
					"<font style=\"color:red\">填报人联系电话不能为空且不能少于12個字符</font>");
			return false;
		}
		else {
			$('#SchFillerTelSpan1').html("");
		//	alert(222);
		}

	    if (!reg1.test(schFillerTel)){
		    $('#SchFillerTel').focus();
			$('#SchFillerTel').select();
			$('#SchFillerTelSpan1').html(
			"<font style=\"color:red\">电话號碼格式只能为：0791-88126683</font>");
			return false;
		}	
		else {
			$('#SchFillerTelSpan1').html("");
		}

	    if (schFillerEmail   == null || schFillerEmail.length == 0
				||schFillerEmail.length > 100) {
			$('#SchFillerEmail').focus();
			$('#SchFillerEmail').select();
			$('#SchFillerEmailSpan1').html(
					"<font style=\"color:red\">电子邮箱不能为空且不能超过100个字符！</font>");
			return false;
		} else {
			$('#SchFillerEmailSpan1').html("");
		}

	    if (schName== null || schName.length == 0
				||schName.length > 100) {
			$('#SchName').focus();
			$('#SchName').select();
			$('#SchNameSpan1').html(
					"<font style=\"color:red\">学校不能为空且不能超过100个字符！</font>");
			return false;
		} else {
			$('#SchNameSpan1').html("");
		}

	    if (schID == null || schID.length == 0
				||schID .length > 100) {
			$('#SchID').focus();
			$('#SchID').select();
			$('#SchIDSpan1').html(
					"<font style=\"color:red\">学校不能为空且不能超过100个字符！</font>");
			return false;
		} else {
			$('#SchIDSpan1').html("");
		}

	    if (schEnName  == null || schEnName.length == 0
				||schEnName.length > 200) {
			$('#SchEnName ').focus();
			$('#SchEnName').select();
			$('#SchEnNameSpan1').html(
					"<font style=\"color:red\">学校英文名称不能为空且不能超过200个字符！</font>");
			return false;
		} else {
			$('#SchEnNameSpan1').html("");
		}

	    if (schType   == null || schType.length == 0
				||schType.length > 50) {
			$('#SchType  ').focus();
			$('#SchType').select();
			$('#SchTypeSpan1').html(
					"<font style=\"color:red\">办学不能为空且不能超过50个字符！</font>");
			return false;
		} else {
			$('#SchTypeSpan1').html("");
		}

	    if (schQuality  == null || schQuality.length == 0
				||schQuality.length > 50) {
			$('#SchQuality').focus();
			$('#SchQuality').select();
			$('#SchQualitySpan1').html(
					"<font style=\"color:red\">办学性质不能为空且不能超过50个字符！</font>");
			return false;
		} else {
			$('#SchQualitySpan1').html("");
		}

	    if (schBuilder   == null || schBuilder.length == 0
				||schBuilder.length > 100) {
			$('#SchBuilder ').focus();
			$('#SchBuilder').select();
			$('#SchBuilderSpan1').html(
					"<font style=\"color:red\">举办者不能为空且不能超过100个字符！</font>");
			return false;
		} else {
			$('#SchBuilderSpan1').html("");
		}

	    if (majDept == null ||majDept.length == 0
				||majDept.length > 100) {
			$('#MajDept').focus();
			$('#MajDept').select();
			$('#MajDeptSpan1').html(
					"<font style=\"color:red\">主管部门不能为空且不能超过100个字符！</font>");
			return false;
		} else {
			$('#MajDeptSpan1').html("");
		}

	    if (schUrl  == null ||schUrl.length == 0
				||schUrl.length > 100) {
			$('#SchUrl ').focus();
			$('#SchUrl').select();
			$('#SchUrlSpan1').html(
					"<font style=\"color:red\">学校网址不能为空且不能超过100个字符！</font>");
			return false;
		} else {
			$('#SchUrlSpan1').html("");
		}

	    if (admissionBatch == null ||admissionBatch.length == 0
				||admissionBatch.length > 300) {
			$('#AdmissionBatch').focus();
			$('#AdmissionBatch').select();
			$('#AdmissionBatchSpan1').html(
					"<font style=\"color:red\">招生批次不能为空且不能超过150个字符！</font>");
			return false;
		} else {
			$('#AdmissionBatchSpan1').html("");
		}

	    if (sch_BeginTime  == null ||sch_BeginTime.length == 0) {
			$('#Sch_BeginTime').focus();
			$('#Sch_BeginTime').select();
			$('#Sch_BeginTimeSpan1').html(
					"<font style=\"color:red\">年份不能为空！</font>");
			return false;
		} else {
			$('#Sch_BeginTimeSpan1').html("");
		}

        if(!reg2.test(sch_BeginTime)){
        	$('#Sch_BeginTime').focus();
			$('#Sch_BeginTime').select();
			$('#Sch_BeginTimeSpan1').html(
					"<font style=\"color:red\">年份格式为：2014</font>");
			return false;
		} else {
			$('#Sch_BeginTimeSpan1').html("");
        }
		

	    if (mediaUrl == null ||mediaUrl.length == 0
				||mediaUrl.length > 100) {
			$('#MediaUrl').focus();
			$('#MediaUrl').select();
			$('#MediaUrlSpan1').html(
					"<font style=\"color:red\">多媒体反映不能为空且不能超过100个字符</font>");
			return false;
		} else {
			$('#MediaUrlSpan1').html("");
		}

	    if (yaohuSchAdd == null ||yaohuSchAdd.length == 0
				||yaohuSchAdd.length > 300) {
			$('#YaohuSchAdd').focus();
			$('#YaohuSchAdd').select();
			$('#YaohuSchAddSpan1').html(
					"<font style=\"color:red\">校区名称不能为空且不能超过150个字符</font>");
			return false;
		} else {
			$('#YaohuSchAddSpan1').html("");
		}

	    if (pengHuSchAdd == null ||pengHuSchAdd.length == 0
				||pengHuSchAdd.length > 300) {
			$('#PengHuSchAdd').focus();
			$('#PengHuSchAdd').select();
			$('#PengHuSchAddSpan1').html(
					"<font style=\"color:red\">校区名称不能为空且不能超过150个字符</font>");
			return false;
		} else {
			$('#PengHuSchAddSpan1').html("");
		}
		return true;
	}

	function editCourse(){

       $('div#di').hide();
  //   
       //$('input').attr('style').attr('display','inline-block');
       // $("input#input").css("display","block");
       // $("input").css("display","inline");   
        $('div#input').css("display","inline");
        $('div#dlg-buttons1').css("display","block");
        document.getElementById("SchAddress").value=data.schAddress ;
        document.getElementById("SchTel").value=data.schTel ;
        document.getElementById("SchFax").value=data.schFax ;
        document.getElementById("SchFillerName").value=data.schFillerName ;
        document.getElementById("SchFillerTel").value=data.schFillerTel ;
        document.getElementById("SchFillerEmail").value=data.schFillerEmail;
        document.getElementById("SchName").value=data.schName ;
        document.getElementById("SchID").value=data.schID ;
        document.getElementById("SchEnName").value=data.schEnName ;
        document.getElementById("SchType").value=data.schType ;
        document.getElementById("SchQuality").value=data.schQuality ;
        document.getElementById("SchBuilder").value=data.schBuilder ;
        document.getElementById("MajDept").value=data.majDept ;
        document.getElementById("SchUrl").value=data.schUrl ;
        document.getElementById("AdmissonBatch").value=data.admissonBatch ;
        document.getElementById("Sch_BeginTime").value=formattime(data.sch_BeginTime) ;
        document.getElementById("MediaUrl").value=data.mediaUrl ;
        document.getElementById("YaohuSchAdd").value=data.yaohuSchAdd ;
        document.getElementById("PengHuSchAdd").value=data.pengHuSchAdd ;

    }
	
    
     function save()
     {
            // validate();       
    		 url = 'pages/T11/edit' ;
    		 if(validate()){
    			 $.ajax({
						type : "POST",
						url : url,
						data: $('#schBasForm').serialize() ,
						async : "true",
						dataType : "text",
						success : function(result) {
	    			 //json格式转化
					    var result = eval('('+result+')');
					    $.messager.alert('提示', result.data) ;
			           cancel();
						}
					}).submit();
    		 }
    		 //else{  $.messager.alert('提示', "请核对好数据！") ;}
    			
      }

	function loadDic() {
		$('#dicDlg').dialog('open').dialog('setTitle', '高级查询');
		loadDictionary();

	}

	function loadDictionary() {

		$
				.ajax(
						{
							type : "POST",
							url : "table5/loadDic",
							async : "false",
							dataType : "text",
							success : function(data) {
								data = eval("(" + data + ")");
								alert(data[0].id);
								var str = "<table width=\"100%\" border=\"1\">__tag_509$54_";
								$(data)
										.each(
												function(index) {
													var val = data[index];
													if (index % 4 == 0
															&& index != 0) {
														str += "__tag_513$18_<tr>";
													}
													str += "__tag_515$17_<input type=\"checkbox\" id=\""
															+ val.id
															+ "\"name="
															+ "\"checkboxex\""
															+ "value=\""
															+ val.data
															+ "\">"
															+ val.data
															+ "__tag_515$140_</td>";
												});
								//alert(str);
								str += "__tag_518$16_<tr>__tag_518$25_<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" iconCls=\"icon-add\" onclick=\"loadData()\">添加__tag_518$176_</td>__tag_518$185_</table>";
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
			    var time1=year;
			    //alert(time) ;
			        return time1;  
			    }  
			</script>

</html>
