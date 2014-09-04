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
  		 url: 'pages/T411/uploadFile',
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
    	
		url = 'pages/T411/insert' ;
		$('#dlg').dialog('open').dialog('setTitle', '添加新的教职工');
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
		var teaName = $('#teaName').val();
		var note = $('#note').val();
		var  num = /^\d+$/;  //用于判断字符串是否全是数字
		
		var idcode = $('#idcode').combobox('getText');
		var officeName = $('#officeID').combobox('getText');
		var unitName = $('#unitId').combobox('getText');
		var TeaResOffice = $('#teaResOfficeID').combobox('getText');
		var topDegree = $('#topDegree').combobox('getText');
		var education = $('#education').combobox('getText');
		var source = $('#source').combobox('getText');
		var techTitle = $('#majTechTitle').combobox('getText');
		var teaTitle = $('#teaTitle').combobox('getText');
		
		// 根据数据库定义的字段的长度，对其进行判断
		 if (teaName == null || teaName.length == 0 || teaName.length > 100) {
			$('#teaName').focus();
			$('#teaName').select();
			alert("教师名字不能为空或长度不超过100");
			return false;
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

		
		if(idcode == null || idcode==""){
			alert("身份代码不能为空");
			return false;
	    }
		
		if(officeName == null || officeName==""){
			alert("所属部门不能为空");
			return false;
	    }
		
		if(unitName == null || unitName==""){
			alert("所属教学单位不能为空");
			return false;
	    }
		
		if(TeaResOffice == null || TeaResOffice==""){
			alert("所属教研室不能为空");
			return false;
	    }
		
		if(education == null || education==""){
			alert("学历不能为空");
			return false;
	    }
		
		if(topDegree == null || topDegree==""){
			alert("最高学位不能为空");
			return false;
	    }
		
		if(source == null || source==""){
			alert("学缘不能为空");
			return false;
	    }

		
		if(techTitle == null || techTitle==""){
			alert("专业技术职称不能为空");
			return false;
	    }
		
		if(teaTitle == null || teaTitle==""){
			alert("教学系列职称不能为空");
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
		
    	var row = $('#commomData').datagrid('getSelections');
    	
    	if(row.length != 1){
    		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
    		return ;
    	}
    	
    	url = 'pages/T411/edit' ;
    
    	$('.title1').hide();
    	$('#item1').hide();
    	$('hr').hide();
    	$('#dlg').dialog('open').dialog('setTitle','修改该名教职工的信息');
    	$('#teaId').val(row[0].teaId) ;
    	$("input#teaId").attr("readonly",true);
    	$("input#teaId").css({"color":"#888"});
    	$('#teaName').val(row[0].teaName) ;
    	$('#gender').combobox('select', row[0].gender) ;
    	$('#birthday').datebox("setValue", formattime(row[0].birthday)) ;
    	$('#admisTime').datebox("setValue", formattime(row[0].admisTime)) ;
    	$('#teaState').combobox('select', row[0].teaState) ;
    	$('#beginWorkTime').datebox("setValue", formattime(row[0].beginWorkTime)); 
    	$('#idcode').combobox('setText', row[0].idcode) ;
    	$('#officeID').combobox('select', row[0].officeID) ;
    	$('#teaResOfficeID').combobox('select', row[0].teaResOfficeID) ;
    	$('#unitId').combobox('select', row[0].unitId) ;
		$('#education').combobox('setText', row[0].education) ;
		$('#topDegree').combobox('setText', row[0].topDegree) ;
		$('#graSch').val(row[0].graSch) ;
		$('#major').val(row[0].major) ;
		$('#source').combobox('setText', row[0].source) ;
		$('#adminLevel').val(row[0].adminLevel) ;
		$('#majTechTitle').combobox('setText', row[0].majTechTitle) ;
		$('#teaTitle').combobox('setText', row[0].teaTitle) ;
		$('#notTeaTitle').val(row[0].notTeaTitle) ;		
		$('#subjectClass').combobox('select', row[0].subjectClass) ;
		$('#doubleTea').combobox('select', row[0].doubleTea) ;
		$('#industry').combobox('select', row[0].industry) ;
		$('#engineer').combobox('select', row[0].engineer) ;
		$('#teaBase').combobox('select', row[0].teaBase) ;
		$('#note').val(row[0].note) ;
	}
	
	function reloadgrid ()  { 
        //查询参数直接添加在queryParams中 
         var  queryValue = $('#searchID').val();
         var queryParams = $('#commomData').datagrid('options').queryParams;  
         queryParams.searchID = queryValue;  
         $("#commomData").datagrid('reload'); 
    }	