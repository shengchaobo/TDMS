	//全局变量，用来暂存当前的url值
   var url;
   
   function reloadgrid ()  { 
       //查询参数直接添加在queryParams中 
       var queryParams = $('#unverfiedData').datagrid('options').queryParams;  
       queryParams.seqNum = $('#seqNum').val(); 
       queryParams.startTime = $('#startTime').datetimebox('getValue');	         		     
       queryParams.endTime  = $('#endTime').datetimebox('getValue');        	 
       $("#unverfiedData").datagrid('reload'); 
  }
   
   /**模板导入*/
   function batchImport(){
  	 $('#batchForm').form('submit',{
  		 url: 'pages/T551/uploadFile',
  		 type: "post",
	         dataType: "json",
  		 onSubmit: function(){
  			 return check() ;
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
   
   function check(){
   	var fileName = $('#uploadFile').val() ;
   	
   	if(fileName == null || fileName == ""){
   		 $.messager.alert("操作提示", "请先选择要导入的文件！");
   		return false ;
   	}
   	
   	var pos = fileName.lastIndexOf(".") ;
   	var suffixName = fileName.substring(pos, fileName.length) ;
   	
   	if(suffixName == ".xls"){
   		return true ;
   	}else{
   		 $.messager.alert("操作提示", "请选择正确的Excel文件（后缀为.xls）");
   		return false ;
   	}
   } 
   
   //添加
   function newObject(){

   	$('.title1').show();
   	$('#item1').show();
   	$('hr').show();
   	url = 'pages/T551/insert' ;
	    $('#dlg').dialog('open').dialog('setTitle','添加学风概况的信息');
	    $('#resInsForm').form('reset');
   }

	
   function singleImport(){
	    //录入数据的表单提交
	    
   	 $('#resInsForm').form('submit',{
			    url: url ,
			    data: $('#resInsForm').serialize(),
	            type: "post",
	            dataType: "json",
			    onSubmit: function(){
			    	return validate();
			    //	$('#dlg').dialog('reload');
			    },
			    //结果返回
			    success: function(result){
				    //json格式转化
				    var result = eval('('+result+')');
				    $.messager.alert('温馨提示', result.data) ;
				    if (result.state){ 
					    if(result.tag==2){
					    	$('#dlg').dialog('close');
							myMarquee('T551', CTypeOne);
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
		
		var  num = /^\d+$/;  //用于判断字符串是否全是数字	
		// 获取文本框的值
		var unitID = $('#UnitID').combobox('getText');
		var majorID = $('#MajorID').combobox('getText');
		var admisSchYear = $('#AdmisSchYear').numberbox('getValue');
		var partyMemNum = $('#PartyMemNum').numberbox('getValue');
		var cheatNum = $('#CheatNum').numberbox('getValue');
		var goodClassRatio = $('#GoodClassRatio').val();
		var note = $('#Note').val();
		
		if(unitID == null || unitID.length == 0){
			alert("教学单位不能为空");
			return false;
		}
		if(majorID == null || majorID.length == 0){
			alert("专业名称不能为空");
			return false;
		}
		if(admisSchYear == null || admisSchYear.length == 0){
			alert("入校年份不能为空");
			return false;
		}
		if(!num.test(admisSchYear)){
			alert("入校年份只能填数字");
			return false;
		}
		if(partyMemNum == null || partyMemNum.length == 0){
			alert("本科生党员数不能为空");
			return false;
		}
		if(!num.test(partyMemNum)){
			alert("本科生党员数只能填数字");
			return false;
		}
		if(cheatNum == null || cheatNum.length == 0){
			alert("考试违纪、作弊及受处分不能为空");
			return false;
		}
		if(!num.test(cheatNum)){
			alert("考试违纪、作弊及受处分只能填数字");
			return false;
		}
		if(goodClassRatio == null || goodClassRatio.length == 0){
			alert("优良学风班的比例不能为空");
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
   	
   	url = 'pages/T551/edit' ;
   
   	$('.title1').hide();
   	$('#item1').hide();
   	$('hr').hide();
   	
   	$('#dlg').dialog('open').dialog('setTitle','修改学风概况的信息');
   	$('#seqNumber').val(row[0].seqNumber) ;
   	$('#Time').val(formattime(row[0].time)) ;
  	$('#UnitID').combobox('select', row[0].unitID) ;
	$('#MajorID').combobox('select', row[0].majorID) ;
	$('#AdmisSchYear').numberbox('setValue',row[0].admisSchYear) ;
	$('#PartyMemNum').numberbox('setValue',row[0].partyMemNum) ;
   	$('#CheatNum').numberbox('setValue',row[0].cheatNum) ;
   	$('#GoodClassRatio').numberbox("setValue",row[0].goodClassRatio) ;
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
	    		url: "pages/T551/deleteByIds?ids=" + ids, 
	    		async:"true",
	    		dataType: "text",
	    		success: function(result){
	    			result = eval("(" + result + ")");

					if(result.state){
						alert(result.data) ;
						myMarquee('T151', CTypeOne);
						 $('#unverfiedData').datagrid('reload') ;
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
   

	    //提交导出表单
	    function submitForm(){
	    	  document.getElementById('exportForm').submit();
	    }
