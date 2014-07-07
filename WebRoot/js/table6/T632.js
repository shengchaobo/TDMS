var url;
//弹出添加的界面
function newItem() {
	url = 'pages/T632/insert' ; 
	$('#title1').show();
	$('#item1').show();
	$('#dlg').dialog('open').dialog('setTitle', '分专业应届本科毕业生就业情况（招就处）');
	$('#addItemForm').form('reset');
}
// 单条录入时的表单提交
function singleImport() {
    $('#examGraEnrollNum').val(parseInt($('#examGraInSch').val())+ parseInt($('#examGraOutSch').val())); //考研录取总人数
    $('#sumGoOnHighStudyNum').val(parseInt($('#recommendGraNum').val())+parseInt($('#examGraInSch').val())
    		+parseInt($('#examGraOutSch').val())+parseInt($('#abroadNum').val()));//应届升学总人数
    $('#goOnHighStudy').val(parseInt($('#sumGoOnHighStudyNum').val()));//升学总人数
    $('#sumEmployNum').val(parseInt($('#govermentNum').val())+ parseInt($('#pubInstiNum').val())+
    		parseInt($('#enterpriseNum').val())+ parseInt($('#forceNum').val())+ parseInt($('#flexibleEmploy').val())+ parseInt($('#goOnHighStudy').val())
    		+parseInt($('#nationItemEmploy').val())+ parseInt($('#otherEmploy').val()))//应届就业总人数
    		

	
//		var sumEmployNum = $('#sumEmployNum').val();
//		var goOnHighStudy = $('#goOnHighStudy').val();
//		var sumGoOnHighStudyNum = $('#sumGoOnHighStudyNum').val();
//		var examGraEnrollNum = $('#examGraEnrollNum').val();
//		
//		alert(sumEmployNum+" "+ goOnHighStudy + " "+sumGoOnHighStudyNum+" "+examGraEnrollNum);
	
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
	var majorId = $('#majorId').combobox('getText');
//	var sumEmployNum = $('#sumEmployNum').val();
	var govermentNum = $('#govermentNum').val();	
	var pubInstiNum = $('#pubInstiNum').val();
	var enterpriseNum = $('#enterpriseNum').val();
	var forceNum = $('#forceNum').val();
	var flexibleEmploy = $('#flexibleEmploy').val();
//	var goOnHighStudy = $('#goOnHighStudy').val();
	var nationItemEmploy = $('#nationItemEmploy').val();
	var otherEmploy = $('#otherEmploy').val();
	
//	var sumGoOnHighStudyNum = $('#sumGoOnHighStudyNum').val();
	var recommendGraNum = $('#recommendGraNum').val();
	var examGraApplyNum = $('#examGraApplyNum').val();
//	var examGraEnrollNum = $('#examGraEnrollNum').val();
	var examGraInSch = $('#examGraInSch').val();
	var examGraOutSch = $('#examGraOutSch').val();
	var abroadNum = $('#abroadNum').val();
	
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
	
	if (govermentNum == "" || pubInstiNum == "" || enterpriseNum == "" ||
			forceNum == "" || flexibleEmploy == "" || nationItemEmploy == "" || otherEmploy == "" || 
			recommendGraNum == "" || examGraApplyNum == "" || examGraInSch == "" || examGraOutSch == "" || abroadNum == "") {
		alert("请填写数字，若无请填写0");
		return false;
	}
	
	if (!govermentNum.match(num) || !pubInstiNum.match(num) || !enterpriseNum.match(num)
			|| !forceNum.match(num) || !flexibleEmploy.match(num)|| !nationItemEmploy.match(num) || !otherEmploy.match(num)
			|| !recommendGraNum.match(num) || !examGraApplyNum.match(num)|| !examGraInSch.match(num) || !examGraOutSch.match(num)|| !abroadNum.match(num)) {
	alert("请填写数字，若无请填写0");
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
		url : "pages/T632/deleteByIds?ids=" + ids,
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

	url = 'pages/T632/edit';
	
	$('#title1').hide();
	$('#item1').hide();

	
	$('#dlg').dialog('open').dialog('setTitle', '分专业应届本科毕业生就业情况（招就处）');
	$('#seqNumber').val(row[0].seqNumber);
	$('#unitId').combobox('select', row[0].unitId);
	$('#majorId').combobox('select', row[0].majorId);

	$('#sumEmployNum').val(row[0].sumEmployNum);
	$('#govermentNum').val(row[0].govermentNum);
	$('#pubInstiNum').val(row[0].pubInstiNum);
	$('#enterpriseNum').val(row[0].enterpriseNum);
	$('#forceNum').val(row[0].forceNum);
	$('#flexibleEmploy').val(row[0].flexibleEmploy);
	$('#goOnHighStudy').val(row[0].goOnHighStudy);
	$('#nationItemEmploy').val(row[0].nationItemEmploy);
	$('#otherEmploy').val(row[0].otherEmploy);
	$('#sumGoOnHighStudyNum').val(row[0].sumGoOnHighStudyNum);
	$('#recommendGraNum').val(row[0].recommendGraNum);
	$('#examGraApplyNum').val(row[0].examGraApplyNum);
	$('#examGraEnrollNum').val(row[0].examGraEnrollNum);
	$('#examGraInSch').val(row[0].examGraInSch);
	$('#examGraOutSch').val(row[0].examGraOutSch);
	$('#abroadNum').val(row[0].abroadNum);
	
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
		 url: 'pages/T632/uploadFile',
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
