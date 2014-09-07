var url;
//弹出添加的界面
function newItem() {
	$('.title1').show();
	$('#item1').show();
	$('hr').show();
	
	url = 'pages/T622/insert' ;  // 配置action?
	$('#dlg').dialog('open').dialog('setTitle', '近一届文、理科本科生录取标准及人数');
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

	var province = $('#province').combobox('getText');
	var batch = $('#batch').combobox('getText');
	var libEnrollNum = $('#libEnrollNum').val();
	var sciEnrollNum = $('#sciEnrollNum').val();
	var libLowestScore = $('#libLowestScore').val();
	var sciLowestScore = $('#sciLowestScore').val();
	var libAvgScore = $('#libAvgScore').val();
	var sciAvgScore = $('#sciAvgScore').val();
	var time = $('#time').datetimebox('getValue');
	var note = $('#note').val();

	// 根据数据库定义的字段的长度，对其进行判断


	if (libEnrollNum == "" || sciEnrollNum == "") {
		alert("录取人数部分请填写数字，若无请填写0");
		return false;
	}
	
	if (!libEnrollNum.match(num) || !sciEnrollNum.match(num)) {
		alert("录取人数部分请填写数字，若无请填写0");
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
		url : "pages/T622/deleteByIds?ids=" + ids,
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

	url = 'pages/T622/edit';
	
	$('.title1').hide();
	$('#item1').hide();
	$('hr').hide();

	
	$('#dlg').dialog('open').dialog('setTitle', '近一届文、理科本科生录取标准及人数');

	$('#seqNumber').val(row[0].seqNumber);
	$('#province').combobox('select', row[0].province);
	$('#batch').combobox('select', row[0].batch);
	
	$('#libEnrollNum').val(row[0].libEnrollNum);
	$('#sciEnrollNum').val(row[0].sciEnrollNum);
	$('#libLowestScore').val(row[0].libLowestScore);
	$('#sciLowestScore').val(row[0].sciLowestScore);
	$('#libAvgScore').val(row[0].libAvgScore);
	$('#sciAvgScore').val(row[0].sciAvgScore);

	$('#time').datebox("setValue", formattime(row[0].time)) ;
	$('#note').val(row[0].note);
	

}
/**用于数据查询功能*/
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
		 url: 'pages/T622/uploadFile',
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
		    		 $('#commomData').datagrid('reload'); // reload the user data
		 	}
		 }
		 });
 }
