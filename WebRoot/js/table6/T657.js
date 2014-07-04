var url;
//弹出添加的界面
function newItem() {
	url = 'pages/T657/insert' ; 
	$('#title1').show();
	$('#item1').show();
	$('#dlg').dialog('open').dialog('setTitle', '学习成果—体质合格、达标率（体育教学部）');
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
	
	
	var unitId = $('#unitId').combobox('getText');
	var habitusQualifiedRate = $('#habitusQualifiedRate').val();
	var habitusTestReachRate = $('#habitusTestReachRate').val();

	var time = $('#time').datetimebox('getValue');
	var note = $('#note').val();

	// 根据数据库定义的字段的长度，对其进行判断

	if (unitId == null || unitId.length == 0) {
		alert("教学单位不能为空");
		return false;
	}
	
	if (habitusTestReachRate == null || habitusTestReachRate.length == 0) {
		alert("体质测试达标率不能为空");
		return false;
	}
	
	if (habitusQualifiedRate == null || habitusQualifiedRate.length == 0) {
		alert("体质合格率不能为空");
		return false;
	}
	
	if (time == null || time.length == 0) {
	
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
		url : "pages/T657/deleteByIds?ids=" + ids,
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

	url = 'pages/T657/edit';
	
	$('#title1').hide();
	$('#item1').hide();
	
	$('#dlg').dialog('open').dialog('setTitle', '学习成果—体质合格、达标率（体育教学部）');
	$('#seqNumber').val(row[0].seqNumber);
	$('#unitId').combobox('select', row[0].unitId);

	$('#habitusQualifiedRate').val(row[0].habitusQualifiedRate);
	$('#habitusTestReachRate').val(row[0].habitusTestReachRate);
	$('#time').datebox("setValue", formattime(row[0].time)) ;
	$('#note').val(row[0].note);
}

function reloadgrid ()  { 
    //查询参数直接添加在queryParams中 
     var  queryValue = $('#searchItem').val();
     var queryParams = $('#commomData').datagrid('options').queryParams;  
     queryParams.searchItem = queryValue;  
     $("#commomData").datagrid('reload'); 
}	