var url;
//弹出添加的界面
function newItem() {
	url = 'pages/T659/insert' ; 
	$('#title1').show();
	$('#item1').show();
	$('#dlg').dialog('open').dialog('setTitle', '学习成果—参加国际会议');
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

	var exchangeStuSum = $('#exchangeStuSum').val();
	var fromSchToOverseas = $('#fromSchToOverseas').val();
	var fromSchToDomestic = $('#fromSchToDomestic').val();
	var fromDomesticToSch = $('#fromDomesticToSch').val();
	var fromOverseasToSch = $('#fromOverseasToSch').val();	
	
	var fillUnitID = $('#fillUnitID').val();
	var time = $('#time').datetimebox('getValue');
	var note = $('#note').val();

	// 根据数据库定义的字段的长度，对其进行判断

	if (unitId == null || unitId.length == 0) {
		alert("教学单位不能为空");
		return false;
	}
	
	if (exchangeStuSum == "" || exchangeStuSum == "") {
		alert("交流学生总数部分请填写数字，若无请填写0");
		return false;
	}
	
	if (!exchangeStuSum.match(num) || !exchangeStuSum.match(num)) {
		alert("交流学生总数部分请填写数字，若无请填写0");
		return false;
	}
	
	if (fromSchToOverseas == "" || fromSchToOverseas == "") {
		alert("本校到境外部分请填写数字，若无请填写0");
		return false;
	}
	
	if (!fromSchToOverseas.match(num) || !fromSchToOverseas.match(num)) {
		alert("本校到境外部分请填写数字，若无请填写0");
		return false;
	}
	
	
	if (fromSchToDomestic == "" || fromSchToDomestic == "") {
		alert("本校到境内部分请填写数字，若无请填写0");
		return false;
	}
	
	if (!fromSchToDomestic.match(num) || !fromSchToDomestic.match(num)) {
		alert("本校到境内部分请填写数字，若无请填写0");
		return false;
	}
	
	if (fromDomesticToSch == "" || fromDomesticToSch == "") {
		alert("境内到本校部分请填写数字，若无请填写0");
		return false;
	}
	
	if (!fromDomesticToSch.match(num) || !fromDomesticToSch.match(num)) {
		alert("境内到本校部分请填写数字，若无请填写0");
		return false;
	}
	
	if (fromOverseasToSch == "" || fromOverseasToSch == "") {
		alert("境外到本校部分请填写数字，若无请填写0");
		return false;
	}
	
	if (!fromOverseasToSch.match(num) || !fromOverseasToSch.match(num)) {
		alert("境外到本校部分请填写数字，若无请填写0");
		return false;
	}
	
	
	if (parseInt(exchangeStuSum) != (parseInt(fromSchToOverseas)+parseInt(fromOverseasToSch)+parseInt(fromDomesticToSch)+parseInt(fromSchToDomestic))) {
		alert("交流学生总数应为四种交流人数之和");
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
		url : "pages/T659/deleteByIds?ids=" + ids,
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

	url = 'pages/T659/edit';
	
	$('#title1').hide();
	$('#item1').hide();
	
	$('#dlg').dialog('open').dialog('setTitle', '学习成果—参加国际会议');
	$('#seqNumber').val(row[0].seqNumber);
	$('#unitId').combobox('select', row[0].unitId);
	
	$('#exchangeStuSum').val(row[0].exchangeStuSum);
	$('#fromSchToOverseas').val(row[0].fromSchToOverseas);

	$('#fromSchToDomestic').val(row[0].fromSchToDomestic);
	$('#fromDomesticToSch').val(row[0].fromDomesticToSch);
	$('#fromOverseasToSch').val(row[0].fromOverseasToSch);
	
	$('#fillUnitID').val(row[0].fillUnitID);
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