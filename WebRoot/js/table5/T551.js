	//全局变量，用来暂存当前的url值
   var url;

	//弹出添加的界面
	function newObject() {
		
		//update隐藏的量在提交之后要恢复
		$('.title1').show();
    	$('#item1').show();
    	$('hr').show();
    	
		url = 'pages/T551/insert' 
		$('#dlg').dialog('open').dialog('setTitle', '添加新的学风概况');
		$('#addForm').form('reset');
	}
	
	  //模板导入
	 function batchImport(){
		  var fileName = $('#fileToUpload').val() ; 	
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
	  		 url: 'pages/T551/uploadFile',
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
			    		 $('#unverfiedData').datagrid('reload'); // reload the user data
	  		 	}
	  		 }
	  		 });
	   }
   
	//单条导入
	function singleImport() {
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
					$('#unverfiedData').datagrid('reload');
				}
			}
			});
	}

	//对输入字符串进行验证
	function validate() {
		
		var  num = /^\d+$/;  //用于判断字符串是否全是数字	
		// 获取文本框的值
		var unitID = $('#UnitID').combobox('getText');
		var majorID = $('#MajorID').combobox('getText');
		var admisSchYear = $('#AdmisSchYear').val();
		var partyMemNum = $('#PartyMemNum').val();
		var cheatNum = $('#CheatNum').val();
		var goodClassRatio = $('#GoodClassRatio').val();
		var note = $('#Note').val();
		
		if(unitID == null || unitID.length == 0){
			alert("教学单位不能为空");
			return false;
		}
		if(majorID == null || majorID.length == 0){
			alert("专业名称不能为空");
			return false;
		}
		if(admisSchYear == null || admisSchYear.length == 0){
			alert("入校年份不能为空");
			return false;
		}
		if(!num.test(admisSchYear)){
			alert("入校年份只能填数字");
			return false;
		}
		if(partyMemNum == null || partyMemNum.length == 0){
			alert("本科生党员数不能为空");
			return false;
		}
		if(!num.test(partyMemNum)){
			alert("本科生党员数只能填数字");
			return false;
		}
		if(cheatNum == null || cheatNum.length == 0){
			alert("考试违纪、作弊及受处分不能为空");
			return false;
		}
		if(!num.test(cheatNum)){
			alert("考试违纪、作弊及受处分只能填数字");
			return false;
		}
		if(goodClassRatio == null || goodClassRatio.length == 0){
			alert("优良学风班的比例不能为空");
			return false;
		}
		if (note != null && note.length > 1000) {
			alert("备注中文字数不超过500");
/*			$('#noteSpan').html(
				"<font style=\"color:red\">备注中文字数不超过500</font>");*/
				return false;
		}
		return true;
	 }

	function edit() {
		
   	var row = $('#unverfiedData').datagrid('getSelections');
   	
   	if(row.length != 1){
   		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
   		return ;
   	}
   	
   	url = 'pages/T551/edit' ;
   
	$('.title1').hide();
   	$('#item1').hide();
   	$('hr').hide();
   	
   	$('#dlg').dialog('open').dialog('setTitle','修改学风概况的信息');
   	$('#seqNumber').val(row[0].seqNumber) ;
  	$('#UnitID').combobox('select', row[0].unitID) ;
	$('#MajorID').combobox('select', row[0].majorID) ;
	$('#AdmisSchYear').val(row[0].admisSchYear) ;
	$('#PartyMemNum').val(row[0].partyMemNum) ;
   	$('#CheatNum').val(row[0].cheatNum) ;
   	$('#GoodClassRatio').val(row[0].goodClassRatio) ;
	$('#Note').val(row[0].note) ;
	}

	//查询
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
					ids += (row[i].seqNumber + ",");
				} else {
					ids += (row[i].seqNumber + ")");
				}
			}
			deletes(ids);
			}
   	});
   }
   
   function deletes(ids) {
   	$.ajax( {
   		type : "POST",
   		url : "pages/T551/deleteByIds?ids=" + ids,
   		async : "true",
   		dataType : "text",
   		success : function(result) {
			result = eval("(" + result + ")");
			if (result.state) {
				$('#unverfiedData').datagrid('reload');
			}
		}
   	}).submit();
   }

	
