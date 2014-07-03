	//全局变量，用来暂存当前的url值
   var url;

   //弹出添加的界面
	function newObject() {
		
		//update隐藏的量在提交之后要恢复
    	$('#title1').show();
    	$('#item1').show();
    	$('hr').show();
    	
		url = 'pages/UndergraCSBaseTea/insert' ;
		$('#dlg').dialog('open').dialog('setTitle', '添加新的开课、授课情况');
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
	  		 url: 'pages/T511/uploadFile',
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
	
	    
	    
	    function check(){
	    	var fileName = $('#uploadFile').val() ;
	    	
	    	if(fileName == null || fileName == ""){
	    		return false ;
	    	}
	    	
	    	var pos = fileName.lastIndexOf(".") ;
	    	var suffixName = fileName.substring(pos, fileName.length) ;
	    	
	    	if(suffixName == ".xls"){
	    		return true ;
	    	}else{
	    		return false ;
	    	}
	    } 
	    

		function validate(){
			//获取文本框的值
			var csName = $('#CSName').val() ;
			var csId = $('#CSID').val() ;
			var TeaResOffice = $('#TeaResOfficeID').combobox('getText') ;
			var csUnit = $('#UnitID').combobox('getText') ;
			var csType = $('#CSType').combobox('getText') ;
			var csNature = $('#CSNature').combobox('getText') ;
			var state = $('#State').combobox('getText') ;
			var pubCSType = $('#PubCSType').combobox('getText') ;
			var note = $('#Note').val() ;
			//根据数据库定义的字段的长度，对其进行判断
			if(csName == null || csName.length==0 || csName.length > 100){
				$('#CSName').focus();
				$('#CSName').select();
				alert("课程名称不能为空或长度不超过100");
				return false ;
			}
			
			if(csId == null || csId.length == 0 || csId.length > 50){
				$('#CSID').focus();
				$('#CSID').select();
				alert("课程编号不能为空或长度不超过50");
				return false ;
			}
			
			if(csUnit == null || csUnit.length == 0){
				alert("开课单位不能为空");
				return false ;
			}
			
			if(TeaResOffice == null || TeaResOffice.length ==0 || TeaResOffice.length > 50){
				alert("开课单位不能为空");
				return false ;
			}
			
			if(csType == null || csType.length == 0){
				alert("课程类别不能为空");
				return false ;
			}
			
			if(csNature == null || csNature.length == 0){
				alert("课程性质不能为空");
				return false ;
			}
			if(state == null || state.length == 0){
				alert("课程状态不能为空");
				return false ;
			}
			
			if(pubCSType == null || pubCSType.length == 0){
				alert("公选课类别不能为空");
				return false ;
			}
			
			if(note !=null && note.length > 1000){
				alert("备注中文字数不超过500");
				return false ;
			}
			return true ;
		}

	    function edit(){
	    	var row = $('#unverfiedData').datagrid('getSelections');
	    	
	    	if(row.length != 1){
	    		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
	    		return ;
	    	}
	    	
	    	url = 'pages/UndergraCSBaseTea/edit' ;
	    	
	    	$('#dlg').dialog('open').dialog('setTitle','添加本科教学课程库');
	    	$('#seqNumber').val(row[0].seqNumber) ;
	    	$('#CSID').val(row[0].CSID) ;
	    	$('#CSName').val(row[0].CSName) ;
	    	$('#UnitID').combobox('select',row[0].unitID) ;
	    	$('#TeaResOfficeID').combobox('select', row[0].teaResOfficeID) ;
	    	$('#CSType').combobox('select', row[0].CSTypeID) ;
			$('#CSNature').combobox('select', row[0].CSNatureID) ;
			$('#State').combobox('select', row[0].state) ;
			$('#PubCSType').combobox('select', row[0].pubCSType) ;
			$('#Note').val(row[0].note) ;
	    }
	    
	    function deleteByIds(){
	    	//获取选中项
			var row = $('#unverfiedData').datagrid('getSelections');
	    	
			if(row.length == 0){
	    		$.messager.alert('温馨提示', "请选择需要删除的数据！！！") ;
	    		return ;
	    	}
	    	
			 $.messager.confirm('数据删除', '您确定删除选中项?', function(sure){
				 if (sure){
				 	var ids = "";
				 	ids += "(" ;
				 	
				 	for(var i=0; i<row.length; i++){
				 		if(i < (row.length - 1)){
				 			ids += (row[i].seqNumber + ",") ;
				 		}else{
				 			ids += (row[i].seqNumber + ")") ;
				 		}
				 	}
				 	
				 	deletes(ids);
				 	
				 }
			});
	    }
	    
	    function deletes(ids){
	    	$.ajax({ 
	    		type: "POST", 
	    		url: "pages/UndergraCSBaseTea/deleteByIds?ids=" + ids, 
	    		async:"true",
	    		dataType: "text",
	    		success: function(result){
	    			result = eval("(" + result + ")");

					if(result.state){
						alert(result.data) ;
						 $('#unverfiedData').datagrid('reload') ;
					}
	    		}
	    	}).submit();
	    }
	    