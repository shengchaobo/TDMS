	//全局变量，用来暂存当前的url值
   var url;

	//弹出添加的界面
	function newObject() {
		
		//update隐藏的量在提交之后要恢复
    	$('.title1').show();
    	$('#item1').show();
    	$('hr').show();
    	
		url = 'pages/T251/insert' 
		$('#dlg').dialog('open').dialog('setTitle', '添加新的实验、实习、实训场所信息');
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
	  		 url: 'pages/T251/uploadFile',
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
		
		// 获取文本框的值
		var note = $('#note').val();
		var  num = /^\d+$/;  //用于判断字符串是否全是数字
		var money = $('#money').val();
		var area = $('#area').val();
		var newAddArea = $('#newAddArea').val();
		var machNum = $('#machNum').val();
		var expCenterName = $('#expCenterName').val();
		var unitName = $('#teaUnitID').combobox('getText');
		
		
		if($('#expCenterName').val() == null || $('#expCenterName').val()==""){
			alert("实验中心名称不能为空");
			return false;
	    }
		
		if(unitName == null || unitName==""){
			alert("所属教学单位不能为空");
			return false;
	    }
				
		if($('#machNum').val() == null || $('#machNum').val()==""){
			$('#machNum').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(machNum))) {
			alert("台件数必须为整数");
			return false;
	    }
		
		if(isNaN(money)){
			if($('#money').val()==""){
				$('#money').val(0);
			}else{
				alert("金额必须为数字");
				return false;
			}
		}
		
		if(isNaN(area)){
			if($('#area').val()==""){
				$('#area').val(0);
			}else{
				alert("面积必须为数字");
				return false;
			}
		}
		
		if(isNaN(newAddArea)){
			if($('#newAddArea').val()==""){
				$('#newAddArea').val(0);
			}else{
				alert("新增面积必须为数字");
				return false;
			}
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
   	
   	url = 'pages/T251/edit' ;
   
   	$('.title1').hide();
   	$('#item1').hide();
   	$('hr').hide();
     	
   	$('#dlg').dialog('open').dialog('setTitle','修改实验、实习、实训场所的信息');
   	$('#seqNumber').val(row[0].seqNumber) ;
   	$('#expCenterName').val(row[0].expCenterName) ;
  	$('#teaUnitID').combobox('select', row[0].teaUnitID) ;
  	$('#labName').val(row[0].labName) ;
	$('#buildTime').datebox("setValue", formattime(row[0].buildTime)) ;
	$('#place').val(row[0].place) ;
	$('#machNum').val(row[0].machNum) ;	
   	$('#money').val(row[0].money) ;
   	$('#area').val(row[0].area) ;
   	$('#newAddArea').val(row[0].newAddArea) ;
   	$('#nature').val(row[0].nature) ;
   	$('#forMajor').val(row[0].forMajor) ;
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
   		url : "pages/T251/deleteByIds?ids=" + ids,
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

	
