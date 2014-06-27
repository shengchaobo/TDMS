	//全局变量，用来暂存当前的url值
   var url;

   //弹出添加的界面
	function newObject() {  	
		url = 'pages/T49/insert' ;
		$('#dlg').dialog('open').dialog('setTitle', '添加新的教学单位教材出版信息');
		$('#addForm').form('reset');
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
		var complileBookNum =  parseInt($('#complileBookNum').val());
		var writeBookNum =  parseInt($('#writeBookNum').val());
		var interPlanBook=  parseInt($('#interPlanBook').val());
		var nationPlanBook=  parseInt($('#nationPlanBook').val());
		var proviPlanBook=  parseInt($('#proviPlanBook').val());
		var cityPlanBook=  parseInt($('#cityPlanBook').val());
		var schPlanBook=  parseInt($('#schPlanBook').val());
		var interAwardBook=  parseInt($('#interAwardBook').val());
		var nationAwardBook= parseInt( $('#nationAwardBook').val());
		var proviAwardBook=  parseInt($('#proviAwardBook').val());
		var cityAwardBook =  parseInt($('#cityAwardBook').val());
		var schAwardBook=  parseInt($('#schAwardBook').val());
		var note = $('#note').val();
		var  num = /^\d+$/;  //用于判断字符串是否全是数字		
/*		if (time == null || time.length == 0) {
			alert("导入时间不能为空");
			return false;
		}	*/

		if($('#complileBookNum').val() == null || $('#complileBookNum').val()==""){
			$('#complileBookNum').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(complileBookNum))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#writeBookNum').val() == null || $('#writeBookNum').val()==""){
			$('#writeBookNum').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(writeBookNum))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#interPlanBook').val() == null || $('#interPlanBook').val()==""){
			$('#interPlanBook').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(interPlanBook))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#nationPlanBook').val() == null || $('#nationPlanBook').val()==""){
			$('#nationPlanBook').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(nationPlanBook))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#proviPlanBook').val() == null || $('#proviPlanBook').val()==""){
			$('#proviPlanBook').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(proviPlanBook))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#cityPlanBook').val() == null || $('#cityPlanBook').val()==""){
			$('#cityPlanBook').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(cityPlanBook))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#schPlanBook').val() == null || $('#schPlanBook').val()==""){
			$('#schPlanBook').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(schPlanBook))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#interAwardBook').val() == null || $('#interAwardBook').val()==""){
			$('#interAwardBook').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(interAwardBook))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#nationAwardBook').val() == null || $('#nationAwardBook').val()==""){
			$('#nationAwardBook').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(nationAwardBook))) {
			alert("必须为整数");
			return false;
	    }
		if($('#proviAwardBook').val() == null || $('#proviAwardBook').val()==""){
			$('#proviAwardBook').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(proviAwardBook))) {
			alert("必须为整数");
			return false;
	    }
		if($('#cityAwardBook').val() == null || $('#cityAwardBook').val()==""){
			$('#cityAwardBook').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(cityAwardBook))) {
			alert("必须为整数");
			return false;
	    }
		if($('#schAwardBook').val() == null || $('#schAwardBook').val()==""){
			$('#schAwardBook').val(0) ;
		}else  if (!(/(^[0-9]\d*$)/.test(schAwardBook))) {
			alert("必须为整数");
			return false;
	    }
		
		if((complileBookNum+writeBookNum) < (	interPlanBook+nationPlanBook+proviPlanBook
		+cityPlanBook+ schPlanBook+ interAwardBook+nationAwardBook+ proviAwardBook
		+ cityAwardBook + schAwardBook)){
			alert("编著教材数与编写教材数之和应大于规划教材和获奖教材之和");
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
    	
    	url = 'pages/T49/edit' ;
    	
    	$('#dlg').dialog('open').dialog('setTitle','修改教学单位教材出版信息');
    	$('#seqNumber').val(row[0].seqNumber) ;
    	$('#unitId').combobox('select', row[0].unitId) ;
		$('#complileBookNum').val(row[0].complileBookNum);
		$('#writeBookNum').val(row[0].writeBookNum);
		$('#interPlanBook').val(row[0].interPlanBook);
		$('#nationPlanBook').val(row[0].nationPlanBook);
		$('#proviPlanBook').val(row[0].proviPlanBook);
		$('#cityPlanBook').val(row[0].cityPlanBook);
		$('#schPlanBook').val(row[0].schPlanBook);
		$('#interAwardBook').val(row[0].interAwardBook);
		$('#nationAwardBook').val(row[0].nationAwardBook);
		$('#proviAwardBook').val(row[0].proviAwardBook);
		$('#cityAwardBook').val(row[0].cityAwardBook);
		$('#schAwardBook').val(row[0].schAwardBook);   	
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
    		url : "pages/T49/deleteByIds?ids=" + ids,
    		async : "true",
    		dataType : "text",
    		success : function(result) {
			result = eval("(" + result + ")");

			if (result.state) {
				alert(result.data);
				$('#unverfiedData').datagrid('reload');
			}
		}
    	}).submit();
    }

	
