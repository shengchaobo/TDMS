	//全局变量，用来暂存当前的url值
   var url;

	//弹出添加的界面
	function newObject() {   
		$("input#TableName").attr("readonly",false);
		$("input#TableName").css({"color":"black"});
		
		url = 'insert' ;
		$('#dlg').dialog('open').dialog('setTitle', '添加新信息');
		$('#addForm').form('reset');
	}
   
	//单条导入
	function singleImport() {
		alert
		// 录入数据的表单提交
		$('#addForm').form('submit', {
				url : url,
				data : $('#addForm').serialize(),
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

	//对输入字符串进行验证
	function validate() {
		// 获取文本框的值
		var tableName = $('#TableName').val();
		
		var note = $('#Note').val();

		//根据数据库定义的字段的长度，对其进行判断
		if (tableName == null ||  tableName == ''  || tableName.length == 0) {
			alert("表名不能为空");
			return false;
		}
		
		if (note != null && note.length > 1000) {
			alert("备注中文字数不超过500");
			return false;
		}
		return true;
	 }

	function edit() {
		
   	var row = $('#commomData').datagrid('getSelections');
   	
   	if(row.length != 1){
   		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
   		return ;
   	}
   	
   	url = 'edit' ;
   
   	$('#dlg').dialog('open').dialog('setTitle','修改信息');
  	$('#TableName').val(row[0].tableName) ;
	$("input#TableName").attr("readonly",true);
	$("input#TableName").css({"color":"#888"});
	$('#Instruction').val(row[0].instruction) ;	
	$('#Note').val(row[0].note) ;
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
					ids += ("'" + row[i].tableName + "'" + ",");
				} else {
					ids += ("'" + row[i].tableName + "'" + ")");
				}
			}
			deletes(ids);
			}
   	});
   }
   
   function deletes(ids) {
   	$.ajax( {
   		type : "POST",
   		url : "deleteByIds?ids=" + ids,
   		async : "true",
   		dataType : "text",
   		success : function(result) {
			result = eval("(" + result + ")");
			if (result.state) {
				$('#commomData').datagrid('reload');
			}
		}
   	}).submit();
   }

	
