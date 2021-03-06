	//全局变量，用来暂存当前的url值
   var url;

	//弹出添加的界面
	function newObject() {
		
		//update隐藏的量在提交之后要恢复
    	$('.title1').show();
    	$('#item1').show();
    	$('hr').show();
    	
		url = 'pages/T252/insert' 
		$('#dlg').dialog('open').dialog('setTitle', '添加新的本科实验、实习、实训场所信息');
		$('#addForm').form('reset');
	}
	
    //根据用户选择的年显示相应年的数据
    $(function(){ 
		 $("#cbYearContrast1").combobox({  
	        onChange:function(newValue, oldValue){ 
		     //查询参数直接添加在queryParams中 
	         var  queryYear = newValue;
	         var queryParams = $('#verfiedData').datagrid('options').queryParams;  
	         queryParams.queryYear = queryYear;  
	         $("#verfiedData").datagrid('reload'); 
	        }
	   });
    })
	
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
	  		 url: 'pages/T252/uploadFile',
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
					if(result.tag==2){
						$('#dlg').dialog('close');
						myMarquee('T252', CTypeTwo)
						$('#unverfiedData').datagrid('reload'); // reload the user data
					}else{
						$('#dlg').dialog('close');
						$('#unverfiedData').datagrid('reload'); // reload the user data
					}
				}
			}
		});
	}

	//对输入字符串进行验证
	function validate() {
		
		// 获取文本框的值
		var note = $('#note').val();
		var  num = /^\d+$/;  //用于判断字符串是否全是数字
		var expClassHour = $('#expClassHour').val();
		var stuNum = $('#stuNum').val();
		var expHour = $('#expHour').val();
		var expTimes = $('#expTimes').val();
		var practiseItemNum = $('#practiseItemNum').val();
		var expCenterName = $('#expCenterName').val();		
		
		if($('#expCenterName').val() == null || $('#expCenterName').val()==""){
			alert("实验中心名称不能为空");
			return false;
	    }
		
		if($('#expClassHour').val() == null || $('#expClassHour').val()==""){
			$('#expClassHour').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(expClassHour))) {
			alert("实验教学学时数 必须为整数");
			return false;
	    }
				
		if($('#stuNum').val() == null || $('#stuNum').val()==""){
			$('#stuNum').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(stuNum))) {
			alert("可容纳的学生数必须为整数");
			return false;
	    }
		
		if($('#expHour').val() == null || $('#expHour').val()==""){
			$('#expHour').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(expHour))) {
			alert("实验教学人时数必须为整数");
			return false;
	    }
		
		if($('#expTimes').val() == null || $('#expTimes').val()==""){
			$('#expTimes').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(expTimes))) {
			alert("实验教学人次数必须为整数");
			return false;
	    }
		
		if($('#practiseItemNum').val() == null || $('#practiseItemNum').val()==""){
			$('#practiseItemNum').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(practiseItemNum))) {
			alert("科生实验、实习、实训项目数必须为整数");
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
   	
   	url = 'pages/T252/edit' ;
   
   	$('.title1').hide();
   	$('#item1').hide();
   	$('hr').hide();
     	
   	$('#dlg').dialog('open').dialog('setTitle','修改实验、实习、实训场所的信息');
   	$('#seqNumber').val(row[0].seqNumber) ;
   	$('#expCenterName').val(row[0].expCenterName) ;
   	$('#teaUnitID').val(row[0].teaUnitID) ;
	$('#teaUnit').val(row[0].teaUnit) ;
  	$('#labName').val(row[0].labName) ;
	$('#expClassHour').val(row[0].expClassHour) ;
	$('#stuNum').val(row[0].stuNum) ;	
   	$('#expHour').val(row[0].expHour) ;
   	$('#expTimes').val(row[0].expTimes) ;
   	$('#practiseItemNum').val(row[0].practiseItemNum) ;
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
   		url : "pages/T252/deleteByIds?ids=" + ids,
   		async : "true",
   		dataType : "text",
   		success : function(result) {
			result = eval("(" + result + ")");
			if (result.state) {
				alert(result.data);
				myMarquee('T252', CTypeTwo);
				$('#unverfiedData').datagrid('reload'); // reload the user data
			}
		}
   	}).submit();
   }

   //提交导出表单
   function submitForm(){
   	  document.getElementById('exportForm').submit();
   }
	
