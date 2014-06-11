<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		
		<form id="schBasForm" method="post">
		<table id="showInfo" class="doc-table"
			url="pages/T11/auditingData">
			<tbody>

				<tr>
					<td rowspan=6 align="center"
						style="width: 80px;">
						联系方式
					</td>
					<td colspan=2 style="width: 200px;"
						align="left">
						1.学校地址
					</td>
					<td style="background-color: white">
					    <input id="SchAddress"  name="t11Bean.SchAddress" type="text"  style="display:none;"  size="50">
					    <input id="seqNumber" type="hidden" name="t11Bean.SeqNumber" ></input>
						<div id="di">
							<span id="SchAddressSpan" ></span>
						</div>
						<span id="SchAddressSpan1" ></span>
					</td>
				</tr>
				<tr>
					<td colspan=2 style="width: 200px; background-color: white"
						align="left">
						2.学校办公电话号码
					</td>
					<td style="background-color: white">
						<div  id="di">
							<span id="SchTelSpan"></span>
						</div>
						<input id="SchTel"  name="t11Bean.SchTel" type="text"   style="display:none;" size="50">
					     <span id="SchTelSpan1"></span>
					</td>
				</tr>
				<tr>
					<td colspan=2 style="width: 200px; background-color: white"
						align="left">
						3.学校办公传真号码
					</td>
					<td style="background-color: white">
						<div  id="di">
							<span id="SchFaxSpan"></span>
						</div>
						 <input  id="SchFax"  name="t11Bean.SchFax" type="text"  style="display:none;" size="50">
						 <span id="SchFaxSpa1"></span>
					</td>
				</tr>
				<tr>
					<td rowspan=3 style="width: 120px; background-color: white"
						align="left">
						4.学校填报负责人
					</td>
					<td style="width: 80px; background-color: white" align="left" size="50"> 
						姓名
					</td>
					<td style="background-color: white">
						<div  id="di">
							<span id="SchFillerNameSpan"></span>
						</div>
						 <input id="SchFillerName"  name="t11Bean.SchFillerName" type="text"  style="display:none;" size="50">
						 <span id="SchFillerNameSpan1"></span>
					</td>
				</tr>
				<tr>
					<td style="width: 80px; background-color: white" align="left">
						联系电话
					</td>
					<td style="background-color: white">
						<div  id="di">
							<span id="SchFillerTelSpan"></span>
						</div>
						 <input  id="SchFillerTel"  name="t11Bean.SchFillerTel" type="text"   style="display:none;" size="50">
						 <span id="SchFillerTelSpan1"></span>
					</td>
				</tr>
				<tr>
					<td style="width: 80px; background-color: white" align="left">
						联系电子邮箱
					</td>
					<td style="background-color: white">
						<div  id="di">
							<span id="SchFillerEmailSpan"></span>
						</div>
						 <input  id="SchFillerEmail"  name="t11Bean.SchFillerEmail" type="text"    style="display:none;" size="50">
						 <span id="SchFillerEmailSpan1"></span>
					</td>
				</tr>
				<tr>
					<td rowspan=11 style="width: 80px; background-color: white">
						学校概况
					</td>
					<td colspan=2 style="width: 200px; background-color: white"
						align="left">
						5.学校名称
					</td>
					<td style="background-color: white">
						<div  id="di">
							<span id="SchNameSpan"></span>
						</div>
						 <input id="SchName"  name="t11Bean.SchName" type="text"   style="display:none;" size="50">
						 <span id="SchNameSpan1"></span>
					</td>
				</tr>
				<tr>
					<td colspan=2 style="width: 200px; background-color: white"
						align="left">
						6.代码
					</td>
					<td style="background-color: white">
						<div  id="di">
							<span id="SchIDSpan"></span>
						</div>
						 <input id="SchID"  name="t11Bean.SchID" type="text"  style="display:none;" size="50">
						 <span id="SchIDSpan1"></span>
					</td>
				</tr>
				<tr>
					<td colspan=2 style="width: 200px; background-color: white"
						align="left">
						7.英文名称
					</td>
					<td style="background-color: white" align="left">
						<div  id="di">
							<span id="SchEnNameSpan"></span>
						</div>
						 <input id="SchEnName"  name="t11Bean.SchEnName"  style="display:none;" size="50">
						 <span id="SchEnNameSpan1"></span>
					</td>
				</tr>
				<tr>
					<td colspan=2 style="width: 200px; background-color: white"
						align="left">
						8.办学类型
					</td>
					<td style="background-color: white">
						<div  id="di">
							<span id="SchTypeSpan"></span>
						</div>
						 <input id="SchType"  name="t11Bean.SchType" type="text"  style="display:none;" size="50">
						 <span id="SchTypeSpan1"></span>
					</td>
				</tr>
				<tr>
					<td colspan=2 style="width: 200px; background-color: white"
						align="left">
						9.学校性质
					</td>
					<td style="background-color: white">
						<div  id="di">
							<span id="SchQualitySpan"></span>
						</div>
						 <input id="SchQuality"  name="t11Bean.SchQuality" type="text"   style="display:none;" size="50">
						 <span id="SchQualitySpan1"></span>
					</td>
				</tr>
				<tr>
					<td colspan=2 style="width: 200px; background-color: white"
						align="left">
						10.举办者
					</td>
					<td style="background-color: white">
						<div  id="di">
							<span id="SchBuilderSpan"></span>
						</div>
						 <input id="SchBuilder"  name="t11Bean.SchBuilder" type="text"  style="display:none;" size="50">
						 <span id="SchBuilderSpan1"></span>
					</td>
				</tr>
				<tr>
					<td colspan=2 style="width: 200px; background-color: white"
						align="left">
						11.主管部门
					</td>
					<td style="background-color: white">
						<div  id="di">
							<span id="MajDeptSpan"></span>
						</div>
						 <input id="MajDept"  name="t11Bean.MajDept" type="text"   style="display:none;" size="50">
						 <span id="MajDeptSpan1"></span>
					</td>
				</tr>
				<tr>
					<td colspan=2 style="width: 200px; background-color: white"
						align="left">
						12.学校网址
					</td>
					<td style="background-color: white">
						<div  id="di">
							<span id="SchUrlSpan"></span>
						</div>
						 <input id="SchUrl"  name="t11Bean.SchUrl" type="text" value="1" style="display:none;"  size="50">
						 <span id="SchUrlSpan1"></span>
					</td>
				</tr>
				<tr>
					<td colspan=2 style="width: 200px; background-color: white"
						align="left">
						13.招生批次
					</td>
					<td style="background-color: white">
						<div  id="di">
							<span id="AdmissonBatchSpan"></span>
						</div>
						 <input id="AdmissonBatch"  name="t11Bean.AdmissonBatch" type="text"   edit="true" style="display:none;" size="50">
						 <span id="AdmissonBatchSpan1"></span>
					</td>
				</tr>
				<tr>
					<td colspan=2 style="width: 200px; background-color: white"
						align="left">
						14.开办本科教育年份
					</td>
					<td style="background-color: white">
						<div  id="di">
							<span id="Sch_BeginTimeSpan"></span>
						</div>
						 <input id="Sch_BeginTime"  name="Year" type="text"  style="display:none;" size="50">
						 <span id="Sch_BeginTimeSpan1"></span>
					</td>
				</tr>
				<tr>
					<td colspan=2 style="width: 200px; background-color: white"
						align="left">
						15.多媒体反映
					</td>
					<td style="background-color: white">
						<div  id="di">
							<span id="MediaUrlSpan"></span>
						</div>
						 <input id="MediaUrl"  name="t11Bean.MediaUrl" type="text" style="display:none;" size="50">
						 <span id="MediaUrlSpan1"></span>
					</td>
				</tr>
				<tr>
					<td rowspan=2 align="center"
						style="width: 80px; background-color: white">
						校区地址
					</td>
					<td rowspan=2 align="left" style="background-color: white">
						16. 校区名称
					</td>
					<td style="width: 80px; background-color: white" align="left">
						瑶湖校区
					</td>
					<td align="left" style="background-color: white">
						<div  id="di">
							<span id="YaohuSchAddSpan"></span>
						</div>
						 <input id="YaohuSchAdd"  name="t11Bean.YaohuSchAdd" type="text"  style="display:none;" size="50" >
						 <span id="YaohuSchAddSpan1"></span>
					</td>
				</tr>
				<tr>
					<td style="width: 80px; background-color: white" align="left">
						彭桥校区
					</td>
					<td style="background-color: white">
						<div  id="di">
							<span id="PengHuSchAddSpan"></span>
						</div>
						 <input id="PengHuSchAdd"  name="t11Bean.PengHuSchAdd" type="text" style="display:none"  size="50" >
						 <span id="PengHuSchAddSpan1"></span>
					</td>
				</tr>
			</tbody>
		</table>
		</form>
		<div id="toolbar" style="height: auto">
			<div>
				<!-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newCourse()">添加</a> -->
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-edit" plain="true" onclick="editCourse()">编辑</a>
			
				<!-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyCourse()">删除</a> -->
			</div>
		</div>

		
	
	<div id="dicDlg" class="easyui-dialog" style="width:500px;padding:10px 20px" closed="true">
		<div class="ftitle">高级检索</div>
		<div id="dicTables"  class="fitem">
		</div>
		<div id="dices"  class="fitem"></div>
	</div>
	 
	<div id="dlg-buttons" style="display:none">
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

	var url;
//	var seqNumber;
	var data;

	function cancel(){
		 $('div#di').show();
		  //   
		       //$('input').attr('style').attr('display','inline-block');
		        $("input").css("display","none");   
		        $('div#dlg-buttons').css("display","none");
		        loadAuotCityList();
		}
	function loadAuotCityList() {

		$.ajax( {
			type : "POST", //post请求
			url : "pages/T11/auditingData", //请求action的URL
			dataType : "json",//返回类型
			success : function(result) { //回调函数	    		 
				// var data = eval('('+result+')');
			    data = result;
			    var data1 = result; 
				$('#seqNumber').val(data.seqNumber);
				$('#SchAddressSpan').html(data1.schAddress);
				$('#SchTelSpan').html(data1.schTel);
				$('#SchFaxSpan').html(data1.schFax);
				$('#SchFillerNameSpan').html(data1.schFillerName);
				$('#SchFillerTelSpan').html(data1.schFillerTel);
				$('#SchFillerEmailSpan').html(data1.schFillerEmail);
				$('#SchNameSpan').html(data1.schName);
				$('#SchIDSpan').html(data1.schID);
				$('#SchEnNameSpan').html(data1.schEnName);
				$('#SchTypeSpan').html(data1.schType);
				$('#SchQualitySpan').html(data1.schQuality);
				$('#SchBuilderSpan').html(data1.schBuilder);
				$('#MajDeptSpan').html(data1.majDept);
				$('#SchUrlSpan').html(data1.schUrl);
				$('#AdmissonBatchSpan').html(data1.admissonBatch);
				$('#Sch_BeginTimeSpan').html(formattime(data1.sch_BeginTime));
				$('#MediaUrlSpan').html(data1.mediaUrl);
				$('#YaohuSchAddSpan').html(data1.yaohuSchAdd);
				$('#PengHuSchAddSpan').html(data1.pengHuSchAdd);
			}
		});
	}

	function batchImport() {
		$('#fm').form('submit', {
			url : url,
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.errorMsg) {
					$.messager.show( {
						title : 'Error',
						msg : result.errorMsg
					});
				} else {
					$('#dlg').dialog('close'); // close the dialog
			$('#dg').datagrid('reload'); // reload the user data
		}
	}
		});
	}

	function newCourse() {
		$('#dlg').dialog('open').dialog('setTitle', '添加学校荣誉库');
		$('#rewardForm').form('reset');
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

//		 re = new RegExp("^[0-9]+$");//判断是否为数字
//		 alert( $('#SchTel').val()) ;
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
				|| rewardName.length > 150) {
			$('#SchAddress').focus();
			$('#SchAddress').select();
			$('#SchAddressSpan1').html(
					"<font style=\"color:red\">學校地址不能为空且不能超過150個字符</font>");
			return false;
		} else {
			return true;
			$('#SchAddressSpan1').html("");
		}

//		if (schTel == null || schTel.length == 0 || schTel.length > 50) {
//			$('#SchTel').focus();
//			$('#SchTel').select();
	//		$('#SchTelSpan1').html(
	//				"<font style=\"color:red\">學校號碼不能为空且不能超過50個字符</font>");
	//		return false;
	//	}else if (!re.test(schTel)){
	//		$('#SchTelSpan1').html(
	//		"<font style=\"color:red\">學校號碼只能為數字</font>");
	//	}	
	//	else {
	//		$('#SchTelSpan1').html("");
	//	}
	//	return true;
	}

	function editCourse(){

       $('div#di').hide();
  //   
       //$('input').attr('style').attr('display','inline-block');
        $("input").css("display","inline");   
        $('div#dlg-buttons').css("display","block");
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
    			 $.ajax({
						type : "POST",
						url : url,
						data: $('#schBasForm').serialize() ,
						async : "true",
						dataType : "text",
						success : function(result) {
	    			 //json格式转化
					    var result = eval('('+result+')');
					    $.messager.alert('温馨提示', result.data) ;
 			           cancel();
						}
					}).submit();
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
			    //alert(time) ;
			        return time;  
			    }  
			</script>

</html>
