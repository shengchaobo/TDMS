	//全局变量，用来暂存当前的url值
   var url;

   //弹出添加的界面
	function newObject() {  	
		url = 'pages/T4_11/insert' ;
		$('#dlg').dialog('open').dialog('setTitle', '添加新的教学单位社会服务情况');
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
	  		 url: 'pages/T4_11/uploadFile',
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
						myMarquee('T4_11', CTypeTwo)
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
		//var time = $('#time').datetimebox('getValue');
		var patentNum =  parseInt($('#patentNum').val());
		var achieNum =  parseInt($('#achieNum').val());
		var consNum=  parseInt($('#consNum').val());
		var partJobNum=  parseInt($('#partJobNum').val());
		var judgeNum=  parseInt($('#judgeNum').val());

		var note = $('#note').val();
		var  num = /^\d+$/;  //用于判断字符串是否全是数字		
/*		if (time == null || time.length == 0) {
			alert("导入时间不能为空");
			return false;
		}	*/
		
/*		if(unitName == null || unitName==""){
			alert("教学单位不能为空");
			return false;
	    }*/

		if($('#patentNum').val() == null || $('#patentNum').val()==""){
			$('#patentNum').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(patentNum))) {
			alert("专利转让数量必须为整数");
			return false;
	    }
		
		if($('#achieNum').val() == null || $('#achieNum').val()==""){
			$('#achieNum').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(achieNum))) {
			alert("科技成果转化数量必须为整数");
			return false;
	    }
		
		if($('#consNum').val() == null || $('#consNum').val()==""){
			$('#consNum').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(consNum))) {
			alert("技术咨询采用次数必须为整数");
			return false;
	    }
		
		if($('#partJobNum').val() == null || $('#partJobNum').val()==""){
			$('#partJobNum').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(partJobNum))) {
			alert("兼任协（学）会职务人次数必须为整数");
			return false;
	    }
		
		if($('#judgeNum').val() == null || $('#judgeNum').val()==""){
			$('#judgeNum').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(judgeNum))) {
			alert("受聘学科竞赛评委/裁判人次数必须为整数");
			return false;
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
    	
    	url = 'pages/T4_11/edit' ;
    	
    	$('#dlg').dialog('open').dialog('setTitle','修改教学单位社会服务情况');
    	$('#seqNumber').val(row[0].seqNumber) ;
    	$('#unitName').val(row[0].unitName) ;
    	$('#unitId').val(row[0].unitId) ;
		$('#patentNum').val(row[0].patentNum);
		$('#achieNum').val(row[0].achieNum);
		$('#consNum').val(row[0].consNum);
		$('#partJobNum').val(row[0].partJobNum);
		$('#judgeNum').val(row[0].judgeNum);	
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
    		url : "pages/T4_11/deleteByIds?ids=" + ids,
    		async : "true",
    		dataType : "text",
    		success : function(result) {
			result = eval("(" + result + ")");

			if (result.state) {
				alert(result.data);
				myMarquee('T4_11', CTypeTwo);
				$('#unverfiedData').datagrid('reload'); // reload the user data
			}
		}
    	}).submit();
    }
    
    //提交导出表单
    function submitForm(){
    	  document.getElementById('exportForm').submit();
    }