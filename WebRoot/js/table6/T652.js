var url;
//弹出添加的界面
function newItem() {
	url = 'pages/T652/insert' ; 
	$('#title1').show();
	$('#item1').show();
	$('#dlg').dialog('open').dialog('setTitle', '学习成果—学生发表论文');
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

	var paperTitle = $('#paperTitle').val();;
	var jonalName = $('#jonalName').val();
	var jonalId = $('#jonalId').val();
	var jonalDate = $('#jonalDate').datetimebox('getValue');
	var awardStuName = $('#awardStuName').val();
	var awardStuNum = $('#awardStuNum').val();
	var guideTeaName = $('#guideTeaName').val();
	var guideTeaNum = $('#guideTeaNum').val();
	var isAward = $('#isAward').combobox('getText');
	var awardLevel = $('#awardLevel').combobox('getText');
	var awardName = $('#awardName').val();
	var awardFromUnit = $('#awardFromUnit').val();

	
	var fillUnitID = $('#fillUnitID').val();
	var time = $('#time').datetimebox('getValue');
	var note = $('#note').val();



	// 根据数据库定义的字段的长度，对其进行判断

	if (unitId == null || unitId.length == 0) {
		alert("教学单位不能为空");
		return false;
	}

	if (paperTitle == null || paperTitle.length == 0) {
		alert("学术论文题目不能为空");
		return false;
	}
	
	if (jonalName == null || jonalName.length == 0) {
		alert("刊物名称不能为空");
		return false;
	}
	
	if (jonalId == null || jonalId.length == 0) {
		alert("刊号不能为空");
		return false;
	}
	
	if (jonalDate == null || jonalDate.length == 0) {
		alert("刊期不能为空");
		return false;
	}
	
	if (awardLevel == null || awardLevel.length == 0) {
		alert("获奖级别不能为空");
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
		url : "pages/T652/deleteByIds?ids=" + ids,
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

	url = 'pages/T652/edit';
	
	$('#title1').hide();
	$('#item1').hide();
	
	$('#dlg').dialog('open').dialog('setTitle', '学习成果—学生发表论文情况');
	$('#seqNumber').val(row[0].seqNumber);
	$('#unitId').combobox('select', row[0].unitId);
	$('#paperTitle').val(row[0].paperTitle);
	$('#jonalName').val(row[0].jonalName);
	$('#jonalId').val(row[0].jonalId);
	$('#jonalDate').datebox("setValue", formattime(row[0].jonalDate)) ;
	$('#awardStuName').val(row[0].awardStuName);
	$('#awardStuNum').val(row[0].awardStuNum);
	$('#guideTeaName').val(row[0].guideTeaName);
	$('#guideTeaNum').val(row[0].guideTeaNum);
	
	$('#isAward').combobox('select', row[0].isAward);
	$('#awardLevel').combobox('select', row[0].awardLevel);
	$('#awardName').val(row[0].awardName);
	$('#awardFromUnit').val(row[0].awardFromUnit);	
	$('#awardTime').datebox("setValue", formattime(row[0].awardTime));
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
		 url: 'pages/T652/uploadFile',
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

