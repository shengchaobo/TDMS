	//全局变量，用来暂存当前的url值
   var url;

	//弹出添加的界面
	function newObject() {
		
		//update隐藏的量在提交之后要恢复
		$('.title1').show();
    	$('#item1').show();
    	$('hr').show();
    	
		url = 'pages/T461/insert' 
		$('#dlg').dialog('open').dialog('setTitle', '添加新的教师获奖荣誉');
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
	  		 url: 'pages/T461/uploadFile',
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
		// 获取文本框的值
		//var time = $('#time').datetimebox('getValue');
		var teaId = $('#name').combobox('getText');
		var teaName = $('#name').combobox('getValue');
		var teaNum = $('#otherTeaNum').val();
		var tea = $('#otherTeaInfo').val();
		var note = $('#note').val();
		var  num = /^\d+$/;  //用于判断字符串是否全是数字		
		var unitName = $('#unitId').combobox('getText');
		var awardLevel = $('#awardLevel').combobox('getText');
		var awardType = $('#awardType').combobox('getText');
		
		if(unitName == null || unitName==""){
			alert("所属教学单位不能为空");
			return false;
	    }
		
		//根据数据库定义的字段的长度，对其进行判断
		if (teaId == null ||  teaId == ''  || teaId.length == 0 || teaId == teaName) {
			alert("教工号不能为空或者教师库中无该教工号");
			return false;
		}
		
		if(awardType == null || awardType==""){
			alert("获奖类别不能为空");
			return false;
	    }
		
		if(awardLevel == null || awardLevel==""){
			alert("获奖级别不能为空");
			return false;
	    }
		if(teaNum == null || teaNum == 0){
		     if(tea!=null && tea!=""){
		    	 alert("当参与教师为0和空时，其他成员应为空");
		    	 return false;
		     }
		}else if (!(/(^[1-9]\d*$)/.test(teaNum))) {
			alert("参与教师必须为整数");
			return false;
	    }else{
		     if(tea==null || tea==""){
		    	 alert("当参与教师为非零整数时，其他成员应不能为空");
		    	 return false;
		     }
	    }
		
		if (note != null && note.length > 1000) {
			alert("备注中文字数不超过500");
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
	   	
	   	url = 'pages/T461/edit' ;
	   
	   	$('.title1').hide();
	   	$('#item1').hide();
	   	$('hr').hide();
	   	
	   	$('#dlg').dialog('open').dialog('setTitle','修改教师获奖荣誉的信息');
	   	$('#seqNumber').val(row[0].seqNumber) ;
	  	$('#name').combobox('select', row[0].name) ;
	  	$('#unitId').combobox('select', row[0].unitId) ;
		$('#awardType').combobox('setText', row[0].awardType) ;
		$('#awardLevel').combobox('setText', row[0].awardLevel) ;		
	  	$('#awardFromUnit').val(row[0].awardFromUnit) ;
	  	$('#gainAwardTime').datebox("setValue", formattime(row[0].gainAwardTime)) ;
		$('#appvlId').val(row[0].appvlId) ;	
		$('#otherTeaNum').val(row[0].otherTeaNum) ;
		$('#otherTeaInfo').val(row[0].otherTeaInfo) ;
		$('#note').val(row[0].note) ;
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
	   		url : "pages/T461/deleteByIds?ids=" + ids,
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

		
