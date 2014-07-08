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
	  
	    	
	  
	     	url = "pages/T512/insert";
		    $('#dlg').dialog('open').dialog('setTitle','添加本科教学课程库');
		    $('#t512Form').form('reset');
		   
	    }

	  
	    
	    
	    function singleImport(){
		    //录入数据的表单提交
	    	 $('#t512Form').form('submit',{
				    url: url ,
				    data: $('#t512Form').serialize(),
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
			// 获取文本框的值
			var term = $('#Term').val();		
			
			var cSUnit = $('#UnitID').combobox('getText');
			
			var cSMajorName = $('#CSMajorID').combobox('getText');
			
			var cSName = $('#CSName').val();
			
			var cSID = $('#CSID').val();
			
			var cSType = $('#CSType').combobox('getText');
			
			var cSNature = $('#CSNature').combobox('getText');
		
			var pubCSType = $('#PubCSType').combobox('getText');
		
			var credit = $('#Credit').val();
			
			var sumCSHour = $('#SumCSHour').val();
			
			var theoryCSHour = $('#TheoryCSHour').val();
			
			var praCSHour = $('#PraCSHour').val();
			
			var planTime = $('#PlanTime').val();
			
			var cSClass = $('#CSClass').val();
			
			var classID = $('#ClassID').val();
		
			var stuNum = $('#StuNum').val();
			
			var cSTea = $('#CSTea').val();
			
			var note = $('#Note').val();
			
			var  num = /^\d+$/;  //用于判断字符串是否全是数字		
			
			if(term ==null || term.length==0){
				alert("学期不能为空");
			}
			if(cSUnit == null || cSUnit.length==0){
				alert("开课单位不能为空");
			}
			if(cSMajorName == null || cSMajorName.length==0){
				alert("上课专业名称不能为空");
				return false;
			}
			if(cSID == null || cSID.length == 0){
				alert("课程名称不能为空");
				return false;
			}
			if(cSType == null || cSType.length == 0){
				alert("课程类别不能为空");
				return false;
			}
			if(cSNature == null || cSNature.length == 0){
				alert("课程性质不能为空");
				return false;
			}
			if(pubCSType == null || pubCSType.length ==0){
			    alert("公共课类别不能为空");
			    return false;
			}
			if(credit == null || credit.length ==0){
				alert("学分不能为空");
				return false;
			}
			if(!num.test(credit)){
				alert("学分只能为数字");
				return false;
			}
			if(sumCSHour == null || sumCSHour.length ==0){
				alert("总学时不能为空");
				return false;
			}
			if(!num.test(sumCSHour)){
				alert("总学时只能为数字");
				return false;
			}
			if(theoryCSHour == null || theoryCSHour.length ==0){
				alert("理论学时不能为空");
				return false;
			}
			if(!num.test(theoryCSHour)){
				alert("理论学时只能为数字");
				return false;
			}
			if(praCSHour == null || praCSHour.length ==0){
				alert("实践学时不能为空");
				return false;
			}
			if(!num.test(praCSHour)){
				alert("实践学时只能为数字");
				return false;
			}
			if(planTime == null || planTime.length ==0){
				alert("实习、设计时间不能为空");
				return false;
			}
			if(!num.test(planTime)){
				alert("实习、设计时间只能为数字");
				return false;
			}
			if(cSClass == null || cSClass.length ==0){
				alert("授课班级不能为空");
				return false;
			}
			if(stuNum == null || stuNum.length ==0){
				alert("学生人数不能为空");
				return false;
			}
			if(!num.test(stuNum)){
				alert("学生人数只能为数字");
				return false;
			}
			if(cSTea == null || cSTea.length ==0){
				alert("任课老师不能为空");
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
		  	 
		  		 url: 'pages/T512/uploadFile',
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
		function editCourse(){
	    	var row = $('#unverfiedData').datagrid('getSelections');
	    	
	    	if(row.length != 1){
	    		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
	    		return ;
	    	}
	    	
	    	url = 'pages/T512/edit' ;
	    	
	    	$('#dlg').dialog('open').dialog('setTitle','修改开课、授课情况');
	       	$('#seqNumber').val(row[0].seqNumber) ;
	      	$('#Term').val(row[0].term) ;
	      	$('#UnitID').combobox('select',row[0].unitID) ;
	    	$('#CSMajorID').combobox('select', row[0].CSMajorID) ;
	    	$('#CSName').val(row[0].CSName) ;
	    	$('#CSID').val(row[0].CSID) ;
	    	$('#CSType').combobox('select', row[0].CSTypeID) ;
	    	$('#CSNature').combobox('select', row[0].CSNatureID) ;	
	    	$('#PubCSType').combobox('setText', row[0].pubCSType) ;
	    	$('#IsDoubleCS').combobox('setText', row[0].isDoubleCS) ;
	       	$('#Credit').val(row[0].credit) ;
	       	$('#SumCSHour').val(row[0].sumCSHour) ;
	    	$('#TheoryCSHour').val(row[0].theoryCSHour) ;
	    	$('#PraCSHour').val(row[0].praCSHour) ;
	    	$('#ExamWay').val(row[0].examWay) ;
	    	$('#PlanTime').val(row[0].planTime) ;
	    	$('#CSGrade').combobox('setText', row[0].CSGrade) ;
	    	$('#CSClass').val(row[0].CSClass) ;
	    	$('#ClassID').val(row[0].classID) ;
	    	$('#ClassInfo').val(row[0].classInfo);
	    	$('#StuNum').val(row[0].stuNum);
	    	$('#CSTea').val(row[0].CSTea);
	    	$('#IsAccordJob').combobox('setText', row[0].isAccordJob) ;
	    	$('#TeaTitle').combobox('setText', row[0].teaTitle) ;
	    	$('#BookUseInfo').combobox('setText', row[0].bookUseInfo) ;
	    	$('#IsPlanbook').combobox('setText', row[0].isPlanbook) ;
	    	$('#IsAwardbook').combobox('setText', row[0].isAwardbook) ;
	    	$('#Note').val(row[0].note);
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
	    		url: "pages/T512/deleteByIds?ids=" + ids, 
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
	
	    	    