var url;
//弹出添加的界面
function newItem() {
	url = 'pages/T651/insert' ; 
	$('.title1').show();
	$('#item1').show();
	$('hr').show();
	$('#dlg').dialog('open').dialog('setTitle', '学习成果—本科生竞赛获奖情况');
	$('#addItemForm').form('reset');
}
// 单条录入时的表单提交
function singleImport() {

	$('#addItemForm').form('submit', {
		url : url,
		data : $('#addItemForm').serialize(),
		type : "post",
		dataType : "json",
		onSubmit : function() {
			return validate();
		},
		// 结果返回
		success : function(result) {
		// json格式转化
		var result = eval('(' + result + ')');
		$.messager.alert('温馨提示', result.data);
		if (result.state) {
			if(result.tag==2){
				$('#dlg').dialog('close');
				myMarquee('T651', CTypeTwo)
				$('#unverfiedData').datagrid('reload'); // reload the user data
			}else{
				$('#dlg').dialog('close');
				$('#unverfiedData').datagrid('reload'); // reload the user data
			}
		}
	}
	});
}

//数据验证
function validate() {
	// 获取文本框的值
	
	var  num = /^\d+$/;  //用于判断字符串是否全是数字
	//var unitId = $('#unitId').combobox('getText');
//	var teaUnit = $('#teaUnit').combobox('getText');
	
	var competiType = $('#competiType').combobox('getText');
	var competiName = $('#competiName').val();
	var awardItem = $('#awardItem').val();
	var awardLevel = $('#awardLevel').combobox('getText');
	var awardGrade = $('#awardGrade').val();
	var awardTime = $('#awardTime').datetimebox('getValue');
	var awardFromUnit = $('#awardFromUnit').val();
	var awardStuName = $('#awardStuName').val();
	var awardStuNum =  $('#awardStuNum').numberbox('getValue');
	var guideTeaName = $('#guideTeaName').val();
	var guideTeaNum =  $('#guideTeaNum').numberbox('getValue');
	
	var fillUnitID = $('#fillUnitID').val();
	var time = $('#time').datetimebox('getValue');
	var note = $('#note').val();



	// 根据数据库定义的字段的长度，对其进行判断

	//if (unitId == null || unitId.length == 0) {
	//	alert("教学单位不能为空");
	//	return false;
	//}

	if (competiType == null || competiType.length == 0) {
		alert("竞赛类型不能为空");
		return false;
	}
	
	if (competiName == null || competiName.length == 0) {
		alert("赛事名称不能为空");
		return false;
	}
	
	if (awardLevel == null || awardLevel.length == 0 || awardGrade == null || awardGrade.length == 0) {
		alert("获奖等级或级别不能为空");
		return false;
	}
	
	if (awardFromUnit == null || awardFromUnit.length == 0) {
		alert("授予单位不能为空");
		return false;
	}
	
	if (awardStuName == null || awardStuName.length == 0) {
		alert("获奖学生姓名不能为空");
		return false;
	}
	
	if (guideTeaName == null || guideTeaName.length == 0) {
		alert("指导教师姓名不能为空");
		return false;
	}
	
	if (awardStuNum == "" || guideTeaNum == "") {
		alert("获奖学生数或指导教师部分请填写数字，若无请填写0");
		return false;
	}
	
	if (!awardStuNum.match(num) || !guideTeaNum.match(num)) {
	alert("获奖学生数或指导教师部分请填写数字，若无请填写0");
	return false;
}
	

	if (time == null || time.length == 0 || awardTime == null || awardTime.length == 0) {
	
		alert("请选择时间");
		return false;
	}

	if (note != null && note.length > 1000) {
		alert("备注中文字数不超过500");
		return false;
	}
	return true;
}

//删除选中的行
function deleteByIds() {
	// 获取选中项
	var row = $('#unverfiedData').datagrid('getSelections');
	if (row.length == 0) {
		$.messager.alert('温馨提示', "请选择需要删除的数据！！！");
		return;
	}
	$.messager.confirm('数据删除', '您确定删除选中项?', function(sure) {
		if (sure) {
			var ids = "";
			ids += "(";

			for ( var i = 0; i < row.length; i++) {
				if (i < (row.length - 1)) {
					ids += ("'" + row[i].seqNumber + "'" + ",");
				} else {
					ids += ("'" + row[i].seqNumber + "'" + ")");
				}
			}

			deletes(ids);

		}
	});
}

function deletes(ids) {
	$.ajax( {
		type : "POST",
		url : "pages/T651/deleteByIds?ids=" + ids,
		async : "true",
		dataType : "text",
		success : function(result) {
			result = eval("(" + result + ")");

			if (result.state) {
				alert(result.data);
				myMarquee('T651', CTypeTwo);
				$('#unverfiedData').datagrid('reload');
			}
		}
	}).submit();
}

function editItem() {
	var row = $('#unverfiedData').datagrid('getSelections');

	if (row.length != 1) {
		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！");
		return;
	}

	url = 'pages/T651/edit';
	
	$('.title1').hide();
	$('#item1').hide();
	$('hr').hide();

	
	$('#dlg').dialog('open').dialog('setTitle', '学习成果—本科生竞赛获奖情况');
	$('#seqNumber').val(row[0].seqNumber);
	$('#teaUnit').val(row[0].teaUnit);
	$('#unitId').val(row[0].unitId);
	$('#fillUnitID').val(row[0].fillUnitID);
	$('#competiType').combobox('setText', row[0].competiType);
	$('#competiName').val(row[0].competiName);
	$('#awardItem').val(row[0].awardItem);
	$('#awardLevel').combobox('setText', row[0].awardLevel);
	$('#awardGrade').val(row[0].awardGrade);
	$('#awardTime').datebox("setValue", formattime(row[0].awardTime)) ;
	$('#awardFromUnit').val(row[0].awardFromUnit);
	$('#awardStuName').val(row[0].awardStuName);
	$('#awardStuNum').numberbox('setValue',row[0].awardStuNum) ;
	$('#guideTeaName').val(row[0].guideTeaName);
	$('#guideTeaNum').numberbox('setValue',row[0].guideTeaNum) ;
	$('#time').datebox("setValue", formattime(row[0].time)) ;
	$('#note').val(row[0].note);
}

function reloadgrid ()  { 
    //查询参数直接添加在queryParams中 
	 var seqNum = $('#seqNum').val();
     var startTime = $('#startTime').datetimebox('getValue');
     var endTime = $('#endTime').datetimebox('getValue');
     var queryParams = $('#unverfiedData').datagrid('options').queryParams;  
     queryParams.seqNum = seqNum;  
     queryParams.startTime = startTime;  
     queryParams.endTime = endTime;  
     $("#unverfiedData").datagrid('reload'); 
}	

//模板导入
function batchImport(){

	  var fileName = $('#uploadFile').val() ; 	
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
		 url: 'pages/T651/uploadFile',
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

//提交导出表单
function submitForm(){
	  document.getElementById('exportForm').submit();
}

