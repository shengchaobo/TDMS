	//全局变量，用来暂存当前的url值
   var url;
   

   //弹出添加的界面
	function newObject() {
		
//		alert(22242224);
		
		//update隐藏的量在提交之后要恢复
		$('.title1').show();
    	$('#item1').show();
    	$('hr').show();
    	
		url = 'pages/T521/insert' ;
		$('#dlg').dialog('open').dialog('setTitle', '添加新的课程建设情况');
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
	  		 url: 'pages/T521/uploadFile',
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
//		alert(22);
		// 录入数据的表单提交
		$('#addForm').form('submit', {
				url : url,
				data : $('#addForm').serialize(),
				type : "post",
				dataType : "json",
				onSubmit : function() {
					return validate();
				},
				 //结果返回
			    success: function(result){
				    //json格式转化
				    var result = eval('('+result+')');
				    $.messager.alert('温馨提示', result.data) ;
				    if (result.state){ 
					    if(result.tag==2){
					    	$('#dlg').dialog('close');
							myMarquee('T521', CTypeOne);
							$('#unverfiedData').datagrid('reload'); // reload the user data

						}else{
							
					    $('#dlg').dialog('close'); 
					    $('#unverfiedData').datagrid('reload');  
				    }
			    }
			   }
			});
	}

	//对输入字符串进行验证
	function validate() {
		
//		alert(123);
		
		var  num = /^\d+$/;  //用于判断字符串是否全是数字	
		// 获取文本框的值
		var csType = $('#CStype').combobox('getText') ;
		var csName = $('#CSName').val(); 
		var csID = $('#CSID').val();
		var csLevel = $('#CSLevel').combobox('getText');
		var teaID= $('#Leader').combobox('getText');
		var joinTeaNum = $('#JoinTeaNum').combobox('getText');
		var appvlTime = $('#AppvlTime').datebox('getValue');
		var unitID = $('#TeaUnit').val();
		var note = $('#Note').val();
		
		if(csType ==null || csType.length == 0){
			alert("类型不能为空");
			return false;
		}
		if(csName ==null || csName.length == 0){
			alert("课程名称不能为空");
			return false;
		}
		if(csID ==null || csID.length == 0){
			alert("课程代码不能为空");
			return false;
		}
		if(csLevel ==null || csLevel.length == 0){
			alert("级别不能为空");
			return false;
		}
		if(teaID ==null || teaID.length == 0){
			alert("负责人教工号不能为空");
			return false;
		}
		if(joinTeaNum ==null || joinTeaNum.length == 0){
			alert("参与教师总人数不能为空");
			return false;
		}
		if(appvlTime ==null || appvlTime.length == 0){
			alert("获准时间不能为空");
			return false;
		}
		
		if(unitID ==null || unitID.length == 0){
			alert("所属教学单位不能为空");
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
    	
    	url = 'pages/T521/edit' ;
    
    	$('.title1').hide();
       	$('#item1').hide();
       	$('hr').hide();
    	
    	$('#dlg').dialog('open').dialog('setTitle','修改课程信息的情况');
    	$('#seqNumber').val(row[0].seqNumber);
    	$('#Time').val(formattime(row[0].time)); 
    	$('#CStype').combobox('select', row[0].CSType);
    	$('#CSLevel').combobox('select', row[0].CSLevelID);
    	$('#CSName').val(row[0].CSName);
    	$('#CSID').val(row[0].CSID);
    	$('#Leader').combobox('select', row[0].leader) ;
    	$('#JoinTeaNum').combobox('select', row[0].joinTeaNum) ;
    	$('#CSUrl').val(row[0].CSUrl) ;
    	$('#UnitID').combobox('select', row[0].unitID) ;
    	$('#AppvlTime').datebox('setValue', formattime(row[0].appvlTime));
    	$('#ReceptTime').datebox('setValue', formattime(row[0].receptTime));
    	$('#AppvlID').val(row[0].appvlID) ;
    	$('#OtherTea').val(row[0].otherTea) ;
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
    		url : "pages/T521/deleteByIds?ids=" + ids,
    		async : "true",
    		dataType : "text",
    		success : function(result) {
			result = eval("(" + result + ")");

			if (result.state) {
				alert(result.data);
				myMarquee('T521', CTypeOne);
				$('#unverfiedData').datagrid('reload');
			}
		}
    	}).submit();
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

    
    function formattime(val) {  
        if(val == null){
    	    return null ;
        }
        var year=parseInt(val.year)+1900;  
        var month=(parseInt(val.month)+1);  
        month=month>9?month:('0'+month);  
        var date=parseInt(val.date);  
        date=date>9?date:('0'+date);  
        var hours=parseInt(val.hours);  
        hours=hours>9?hours:('0'+hours);  
        var minutes=parseInt(val.minutes);  
        minutes=minutes>9?minutes:('0'+minutes);  
        var seconds=parseInt(val.seconds);  
        seconds=seconds>9?seconds:('0'+seconds);  
        var time=year+'-'+month+'-'+date ;  
        //alert(time) ;
         return time;  
    }  
    
    //提交导出表单
    function submitForm(){
    	  document.getElementById('exportForm').submit();
    }


	
