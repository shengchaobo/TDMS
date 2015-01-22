var url;
//弹出添加的界面
function newItem() {
	url = 'pages/T615/insert' ; 
	$('.title1').show();
	$('#item1').show();
	$('hr').show();
	$('#dlg').dialog('open').dialog('setTitle', '普通本科分专业（大类）学生数');
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
				myMarquee('T615', CTypeOne);
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
	var unitId = $('#unitId').combobox('getText');
	var majorId = $('#majorId').combobox('getText');

	var schLen = $('#schLen').val();
	var schStuSumNum = $('#schStuSumNum').val();
	var freshmanNum = $('#freshmanNum').val();
	var sophomoreNum = $('#sophomoreNum').val();
	var juniorNum = $('#juniorNum').val();
	
	var seniorNum = $('#seniorNum').val();
	var otherGradeNum = $('#otherGradeNum').val();
	var minorNum = $('#minorNum').val();
	var dualDegreeNum = $('#dualDegreeNum').val();
	
	var changeInNum = $('#changeInNum').val();
	var changeOutNum = $('#changeOutNum').val();

	var time = $('#time').datetimebox('getValue');
	var note = $('#note').val();

	// 根据数据库定义的字段的长度，对其进行判断

	if (unitId == null || unitId.length == 0) {
		alert("教学单位不能为空");
		return false;
	}

	if (majorId == null || majorId.length == 0) {
		alert("专业名称不能为空");
		return false;
	}
	
	if (schLen == null || schLen.length == 0) {
		alert("学制不能为空");
		return false;
	}
	
	if (!schLen.match(num)) {
		alert("学制应为数字");
		return false;
	}

	if (schStuSumNum == "" || freshmanNum == "" || sophomoreNum == ""
			|| juniorNum == "" || seniorNum == "" || otherGradeNum == "" || minorNum == ""
				|| dualDegreeNum == "" || changeInNum == ""
					|| changeOutNum == "" ) {
		alert("人数部分请填写数字，若无请填写0");
		return false;
	}
	
	if (!schStuSumNum.match(num) || !freshmanNum.match(num) || !sophomoreNum.match(num)
		|| !juniorNum.match(num)|| !seniorNum.match(num) || !otherGradeNum.match(num) || !minorNum.match(num)
		|| !dualDegreeNum.match(num) || !changeInNum.match(num) || !changeOutNum.match(num)) {
		alert("人数部分请填写数字，若无请填写0");
		return false;
		}

	if (time == null || time.length == 0) {
		$('#time').focus();
		$('#time').select();
		alert("请选择填写时间");
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
		url : "pages/T615/deleteByIds?ids=" + ids,
		async : "true",
		dataType : "text",
		success : function(result) {
			result = eval("(" + result + ")");

			if (result.state) {
				alert(result.data);
				myMarquee('T615', CTypeOne);
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

	url = 'pages/T615/edit';
	
	$('.title1').hide();
	$('#item1').hide();
	$('hr').hide();
	
	$('#dlg').dialog('open').dialog('setTitle', '普通本科分专业（大类）学生数');
	$('#seqNumber').val(row[0].seqNumber);
	$('#unitId').combobox('select', row[0].unitId);
	$('#majorId').combobox('select', row[0].majorId);

	$('#schLen').val(row[0].schLen);

	$('#schStuSumNum').val(row[0].schStuSumNum);
	$('#freshmanNum').val(row[0].freshmanNum);
	$('#sophomoreNum').val(row[0].sophomoreNum);
	$('#juniorNum').val(row[0].juniorNum);
	
	$('#seniorNum').val(row[0].seniorNum);
	$('#otherGradeNum').val(row[0].otherGradeNum);
	$('#minorNum').val(row[0].minorNum);
	$('#dualDegreeNum').val(row[0].dualDegreeNum);
	
	$('#changeInNum').val(row[0].changeInNum);
	$('#changeOutNum').val(row[0].changeOutNum);

	$('#time').datebox("setValue", formattime(row[0].time)) ;
	$('#note').val(row[0].note);
}

function reloadgrid ()  { 
    //查询参数直接添加在queryParams中 
	 var  seqNum = $('#seqNum').val();
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
		 url: 'pages/T615/uploadFile',
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