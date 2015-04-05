	//全局变量，用来暂存当前的url值
   var url;
   
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
  		 url: 'pages/T413/uploadFile',
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
		    		 $('#commomData').datagrid('reload'); // reload the user data
  		 	}
  		 }
  		 });
   }
  
	//弹出添加的界面
	function newTeacher() {
		
		//update隐藏的量在提交之后要恢复
		$("input#teaId").attr("readonly",false);
		$("input#teaId").css({"color":"black"});
		$('.title1').show();
    	$('#item1').show();
    	$('hr').show();
    	
		url = 'pages/T413/insert' ;
		$('#dlg').dialog('open').dialog('setTitle', '添加新的外聘教师');
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
					$('#commomData').datagrid('reload');
				}
			}
			});
	}

	//对输入字符串进行验证
	function validate() {
		// 获取文本框的值
		var teaId = $('#teaId').val();
		var teaName = $('#name').val();
		var hireLen = $('#hireTimeLen').val();
		var note = $('#note').val();
		var  num = /^\d+$/;  //用于判断字符串是否全是数字
		var unitName = $('#unitId').combobox('getText');
		var topDegree = $('#topDegree').combobox('getText');
		var education = $('#education').combobox('getText');
		var techTitle = $('#techTitle').combobox('getText');
		var tutorType = $('#tutorType').combobox('getText');
		var region = $('#region').combobox('getText');
		
		// 根据数据库定义的字段的长度，对其进行判断
		 if (teaName == null || teaName.length == 0 || teaName.length > 100) {
			$('#name').focus();
			$('#name').select();
			alert("教师名字不能为空或长度不超过100");
			return false;
/*			$('#teaNameSpan').html(
					"<font style=\"color:red\">教师名字不能为空或长度不超过100</font>");
					return false;
			}*/
		 }
		 
		if (teaId == null || teaId.length == 0 ) {
				$('#teaId').focus();
				$('#teaId').select();
				alert("教师ID不能为空");
/*				$('#teaIdSpan').html(
					"<font style=\"color:red\">教师ID不能为空或长度不超过50或不全是数字</font>");*/
				return false;
		}else if( !teaId.match(num)){
			 	alert("教师ID不全是数字");
			 	return false;
		}else if(teaId.length > 50){
			 	alert("教师ID长度不超过50");
			 	return false;
		}
		
		if(unitName == null || unitName==""){
			alert("所属教学单位不能为空");
			return false;
	    }
		
		
		if(topDegree == null || topDegree==""){
			alert("最高学位不能为空");
			return false;
	    }
		
		if(education == null || education==""){
			alert("学历不能为空");
			return false;
	    }
		
		if(techTitle == null || techTitle==""){
			alert("专业技术职称不能为空");
			return false;
	    }
		
		if(tutorType == null || tutorType==""){
			alert("导师类型不能为空");
			return false;
	    }
		
		if (!(/(^[1-9]\d*$)/.test(hireLen))) {
			$('#hireTimeLen').focus();
			$('#hireTimeLen').select();
			alert("聘期长短必须为整数");
/*				$('#teaIdSpan').html(
				"<font style=\"color:red\">教师ID不能为空或长度不超过50或不全是数字</font>");*/
			return false;
	}
		
		if (note != null && note.length > 1000) {
			alert("备注中文字数不超过500");
/*			$('#noteSpan').html(
				"<font style=\"color:red\">备注中文字数不超过500</font>");*/
				return false;
		}
		if(region == null || region==""){
			alert("地区不能为空");
			return false;
	    }
		return true;
	 }

	function edit() {
		
    	var row = $('#commomData').datagrid('getSelections');
    	
    	if(row.length != 1){
    		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
    		return ;
    	}
    	
    	url = 'pages/T413/edit' ;
    
    	$('.title1').hide();
    	$('#item1').hide();
    	$('hr').hide();
    	$('#dlg').dialog('open').dialog('setTitle','添加新的外聘教师信息');
    	$('#teaId').val(row[0].teaId) ;
    	$("input#teaId").attr("readonly",true);
    	$("input#teaId").css({"color":"#888"});
    	$('#name').val(row[0].name) ;
    	$('#gender').combobox('select', row[0].gender) ;
    	$('#birthday').datebox("setValue", formattime(row[0].birthday)) ;
    	$('#hireBeginTime').datebox("setValue", formattime(row[0].hireBeginTime)) ;
    	$('#teaState').combobox('select', row[0].teaState) ;
    	$('#hireTimeLen').val(row[0].hireTimeLen); 
    	$('#unitId').combobox('select', row[0].unitId) ;
		$('#education').combobox('setText', row[0].education) ;
		$('#topDegree').combobox('setText', row[0].topDegree) ;
		$('#techTitle').combobox('setText', row[0].techTitle) ;	
		$('#subjectClass').combobox('select', row[0].subjectClass) ;
		$('#workUnitType').val(row[0].workUnitType) ;
		$('#tutorType').combobox('setText', row[0].tutorType) ;
		$('#region').val(row[0].region) ;
		$('#note').val(row[0].note) ;
	}
	
	function reloadgrid ()  { 
        //查询参数直接添加在queryParams中 
         var  queryValue = $('#searchID').val();
         var queryParams = $('#commomData').datagrid('options').queryParams;  
         queryParams.searchID = queryValue;  
         $("#commomData").datagrid('reload'); 
    }	