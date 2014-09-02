var url;
//弹出添加的界面
function newItem() {
	url = 'pages/T671/insert' ; 
	$('.title1').show();
	$('#item1').show();
	$('hr').show();
	$('#dlg').dialog('open').dialog('setTitle', '辅修情况汇总');
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
			$('#dlg').dialog('close');
			$('#commomData').datagrid('reload');
		}
	}
	});
}

//数据验证
function validate() {
	// 获取文本框的值
	
	var  num = /^\d+$/;  //用于判断字符串是否全是数字

	var stuName = $('#stuName').val();
	var stuId = $('#stuId').val();
	
	var unitId = $('#unitId').combobox('getText');
	var majId = $('#majId').combobox('getText');

	var fromClass = $('#fromClass').val();
	var minorUnitId = $('#minorUnitId').combobox('getText');
	var minorId = $('#minorId').combobox('getText');
	
	var beginTime = $('#beginTime').datetimebox('getValue');
	var graduateTime = $('#graduateTime').datetimebox('getValue');

	var time = $('#time').datetimebox('getValue');
	var note = $('#note').val();

	// 根据数据库定义的字段的长度，对其进行判断
	
	if (stuName == null || stuName.length == 0) {
		alert("学生姓名不能为空");
		return false;
	}
	
	if (stuId == null || stuId.length == 0) {
		alert("学号不能为空");
		return false;
	}

	if (unitId == null || unitId.length == 0 || minorUnitId == null || minorUnitId.length == 0) {
		alert("所在教学单位不能为空");
		return false;
	}
	
	if (majId == null || majId.length == 0 || minorId == null || minorId.length == 0) {
		alert("所在专业或辅修专业不能为空");
		return false;
	}
	
	if (time == null || time.length == 0) {
		
		alert("请选择时间");
		return false;
	}
	
	if (beginTime == null || beginTime.length == 0) {
		
		alert("请选择起始时间");
		return false;
	}
	
	if (graduateTime == null || graduateTime.length == 0) {
	
		alert("请选择预计毕业时间");
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
	var row = $('#commomData').datagrid('getSelections');
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
		url : "pages/T671/deleteByIds?ids=" + ids,
		async : "true",
		dataType : "text",
		success : function(result) {
			result = eval("(" + result + ")");

			if (result.state) {
				alert(result.data);
				$('#commomData').datagrid('reload');
			}
		}
	}).submit();
}

function editItem() {
	var row = $('#commomData').datagrid('getSelections');

	if (row.length != 1) {
		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！");
		return;
	}

	url = 'pages/T671/edit';
	
	$('.title1').hide();
	$('#item1').hide();
	$('hr').hide();
	
	$('#dlg').dialog('open').dialog('setTitle', '辅修情况汇总');
	$('#seqNumber').val(row[0].seqNumber);
	$('#stuName').val(row[0].stuName);
	$('#stuId').val(row[0].stuId);
	$('#unitId').combobox('select', row[0].unitId);
	$('#majId').combobox('select', row[0].majId);
	$('#minorUnitId').combobox('select', row[0].minorUnitId);
	$('#minorId').combobox('select', row[0].minorId);
	$('#fromClass').val(row[0].fromClass);
	$('#beginTime').datebox("setValue", formattime(row[0].beginTime)) ;
	$('#graduateTime').datebox("setValue", formattime(row[0].graduateTime)) ;
	$('#time').datebox("setValue", formattime(row[0].time)) ;
	$('#note').val(row[0].note);
}

function reloadgrid ()  { 
    //查询参数直接添加在queryParams中 
	 var  seqNum = $('#seqNum').val();
     var startTime = $('#startTime').datetimebox('getValue');
     var endTime = $('#endTime').datetimebox('getValue');
     var queryParams = $('#commomData').datagrid('options').queryParams;  
     queryParams.seqNum = seqNum;  
     queryParams.startTime = startTime;  
     queryParams.endTime = endTime;  
     $("#commomData").datagrid('reload'); 
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
		 url: 'pages/T671/uploadFile',
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
		    		 $('#dlg').dialog('close'); // close the dialog
		    		 $('#commomData').datagrid('reload'); // reload the user data
		 	}
		 }
		 });
 }
