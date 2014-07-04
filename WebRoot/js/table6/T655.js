var url;
//弹出添加的界面
function newItem() {
	url = 'pages/T655/insert' ; 
	$('#title1').show();
	$('#item1').show();
	$('#dlg').dialog('open').dialog('setTitle', '学习成果—英语四六级、计算机等级考试通过率');
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
	var CET4PassRate = $('#CET4PassRate').val();
	var CET6PassRate = $('#CET6PassRate').val();
	var jiangxiNCREPassRate = $('#jiangxiNCREPassRate').val();
	var nationNCREPassRate = $('#nationNCREPassRate').val();
	var time = $('#time').datetimebox('getValue');
	var note = $('#note').val();



	// 根据数据库定义的字段的长度，对其进行判断

	if (unitId == null || unitId.length == 0) {
		alert("教学单位不能为空");
		return false;
	}
	
	if (CET4PassRate == null || CET4PassRate.length == 0) {
		alert("英语四级考试累计通过率不能为空");
		return false;
	}
	
	if (CET6PassRate == null || CET6PassRate.length == 0) {
		alert("英语六级考试累计通过率不能为空");
		return false;
	}
	
	if (jiangxiNCREPassRate == null || jiangxiNCREPassRate.length == 0) {
		alert("江西省高校计算机等级考试累计通过率不能为空");
		return false;
	}
	
	if (nationNCREPassRate == null || nationNCREPassRate.length == 0) {
		alert("全国高校计算机等级考试累计通过率不能为空");
		return false;
	}
	
//
//	
//	if (awardStuNum == "" || guideTeaNum == "") {
//		alert("获奖学生数或指导教师部分请填写数字，若无请填写0");
//		return false;
//	}
//	
//	if (!awardStuNum.match(num) || !guideTeaNum.match(num)) {
//	alert("获奖学生数或指导教师部分请填写数字，若无请填写0");
//	return false;
//	}
	
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
		url : "pages/T655/deleteByIds?ids=" + ids,
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

	url = 'pages/T655/edit';
	
	$('#title1').hide();
	$('#item1').hide();
	
	$('#dlg').dialog('open').dialog('setTitle', '学习成果—英语四六级、计算机等级考试通过率');
	$('#seqNumber').val(row[0].seqNumber);
	$('#unitId').combobox('select', row[0].unitId);
	
	$('#CET4PassRate').val(row[0].CET4PassRate);
	$('#CET6PassRate').val(row[0].CET6PassRate);
	$('#jiangxiNCREPassRate').val(row[0].jiangxiNCREPassRate);
	$('#nationNCREPassRate').val(row[0].nationNCREPassRate);
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