    var url;
	    
	function reloadgrid ()  { 
        //查询参数直接添加在queryParams中 
         var queryParams = $('#unverfiedData').datagrid('options').queryParams;  
         queryParams.seqNum = $('#seqNum').val(); 
         queryParams.startTime = $('#startTime').datetimebox('getValue');	         		     
    	 queryParams.endTime  = $('#endTime').datetimebox('getValue');        	 
         $("#unverfiedData").datagrid('reload'); 
    }
	 
	    function newCourse(){ 
	    
	    	$('#title1').show();
	    	$('#item1').show();
	    	$('hr').show();
	    	
	    	
	    
	     	url = "pages/T553/insert";
		    $('#dlg').dialog('open').dialog('setTitle','添加本科教学课程库');
		    $('#t553Form').form('reset');
		   
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
		  	 
		  		 url: 'pages/T553/uploadFile',
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
	    
	    
	    function singleImport(){
		    //录入数据的表单提交
	    	 $('#t553Form').form('submit',{
				    url: url ,
				    data: $('#t553Form').serialize(),
		            type: "post",
		            dataType: "json",
				    onSubmit: function(){
				    	return validate();
				    },
				    //结果返回
				    success: function(result){
					    //json格式转化
					    var result = eval('('+result+')');
					    $.messager.alert('温馨提示', result.data) ;
					    if (result.state){ 
						    $('#dlg').dialog('close'); 
						    $('#unverfiedData').datagrid('reload'); 
					    }
				    }
			    });
		}

		function validate(){
			//获取文本框的值
			var awardName = $('#AwardName').val();
			var awardStuName = $('#AwardStuName').val();
			var stuID = $('#StuID').val();
			var teaUnit = $('#TeaUnit').val();
			var fromClass = $('#FromClass').val();
			var awardLevel = $('#AwardLevel').combobox('getText');
			var awardTime = $('#AwardTime').datebox('getText');
			var note = $('#Note').val();
			//根据数据库定义的字段的长度，对其进行判断
		
			if(awardName == null || awardName.length==0 || awardName.length > 200){
				$('#awardName').focus();
				$('#awardName').select();
				$('#AwardNameSpan').html("<font style=\"color:red\">奖励名称不能为空或长度不超过200</font>") ;
				return false ;
			}
			if(awardStuName == null || awardStuName.length == 0 ){
			
				$('#AwardStuNameSpan').html("<font style=\"color:red\">获奖学生姓名不能为空</font>") ;
				return false ;
			}
			if(stuID == null || stuID.length == 0 ){
				
				$('#StuIDSpan').html("<font style=\"color:red\">学号不能为空</font>") ;
				return false ;
			}
			if(awardLevel == null || awardLevel.length == 0){
				
				$('#AwardLevelSpan').html("<font style=\"color:red\">级别不能为空</font>") ;
				return false ;
			}
		
			if(teaUnit == null || teaUnit.length == 0){
				$('#TeaUnitSpan').html("<font style=\"color:red\">所在教学单位不能为空</font>") ;
				return false ;
			}
		
			return true ;
		}
		function editCourse(){
	    	var row = $('#unverfiedData').datagrid('getSelections');
	    	
	    	if(row.length != 1){
	    		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
	    		return ;
	    	}
	    	
	    	url = 'pages/T553/edit' ;
	    	
	    	$('#title1').hide();
	    	$('#item1').hide();
	    	$('hr').hide();
	    	
	    	
	    	$('#dlg').dialog('open').dialog('setTitle','添加本科教学课程库');
	    	$('#seqNumber').val(row[0].seqNumber) ;
	    	
	    	$('#AwardName').val(row[0].awardName) ;
	    	$('#awardStuName').val(row[0].awardStuName) ;
	    	$('#stuID').val(row[0].stuID) ;
	    	$('#TeaUnit').val(row[0].teaUnit) ;
	    	$('#fromClass').val(row[0].fromClass) ;
	    	$('#AwardLevel').combobox('select', row[0].awardLevelID) ;
			$('#AwardTime').datebox('setValue',formattime(row[0].awardTime)) ;
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
				 	
				 	deleteCourses(ids) ;
				 	
				 }
			});
	    }
	        function deleteCourses(ids){
	    	$.ajax({ 
	    		type: "POST", 
	    		url: "pages/T553/deleteByIds?ids=" + ids, 
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
	
	    	    