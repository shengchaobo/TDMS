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
	    
	    	$('.title1').show();
	    	$('#item1').show();
	    	$('hr').show();
	    	
	    	
	    
	     	url = "pages/T552/insert";
		    $('#dlg').dialog('open').dialog('setTitle','添加本科教学课程库');
		    $('#t552Form').form('reset');
		   
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
		  	 
		  		 url: 'pages/T552/uploadFile',
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
	    
	    
	    function singleImport(){
		    //录入数据的表单提交
	    	 $('#t552Form').form('submit',{
				    url: url ,
				    data: $('#t552Form').serialize(),
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
						    if(result.tag==2){
						    	$('#dlg').dialog('close');
								myMarquee('T552', CTypeOne);
								$('#unverfiedData').datagrid('reload'); // reload the user data

							}else{
								
						    $('#dlg').dialog('close'); 
						    $('#unverfiedData').datagrid('reload');  
					    }
				    }
				   }
			    });
		}

		function validate(){
			//获取文本框的值
			var awardName = $('#AwardName').val();
			var awardClass = $('#AwardClass').val();
			var teaUnit = $('#TeaUnit').val();
			var awardLevel = $('#AwardLevel').combobox('getText');
			var awardTime = $('#AwardTime').datebox('getText');
			var note = $('#Note').val();
			//根据数据库定义的字段的长度，对其进行判断
		
			if(awardName == null || awardName.length==0 || awardName.length > 200){
			alert("奖励名称不能为空或长度不超过200");
	
				return false ;
			}
			if(awardClass == null || awardClass.length == 0 ){
				alert("班级不能为空");
				
				return false ;
			}
			if(awardLevel == null || awardLevel.length == 0){
				alert("级别不能为空");
				
				return false ;
			}
		
			if(teaUnit == null || teaUnit.length == 0){
				alert("所在教学单位不能为空");
				
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
	    	
	    	url = 'pages/T552/edit' ;
	    	
	    	$('.title1').hide();
	    	$('#item1').hide();
	    	$('hr').hide();
	    	
	    	
	    	$('#dlg').dialog('open').dialog('setTitle','修改本科教学课程库');
	    	$('#seqNumber').val(row[0].seqNumber) ;
	    	$('#Time').val(formattime(row[0].time)) ;
	    	$('#AwardName').val(row[0].awardName) ;
	    	$('#AwardClass').val(row[0].awardClass) ;
	    	$('#TeaUnit').val(row[0].teaUnit) ;
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
	    		url: "pages/T552/deleteByIds?ids=" + ids, 
	    		async:"true",
	    		dataType: "text",
	    		success: function(result){
	    			result = eval("(" + result + ")");

					if(result.state){
						alert(result.data) ;
						myMarquee('T552', CTypeOne);
						 $('#unverfiedData').datagrid('reload') ;
					}
	    		}
	    	}).submit();
	    }

		    //提交导出表单
		    function submitForm(){
		    	  document.getElementById('exportForm').submit();
		    }
	
	    	    