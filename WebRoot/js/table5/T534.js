	//全局变量，用来暂存当前的url值
   var url;

   //弹出添加的界面
	function newObject() {
		
		//update隐藏的量在提交之后要恢复
		$('.title1').show();
    	$('#item1').show();
    	$('hr').show();
    	
		url = 'pages/T534/insert' ;
		$('#dlg').dialog('open').dialog('setTitle', '添加新的分专业毕业综合训练情况');
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
	  		 url: 'pages/T534/uploadFile',
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
		alert(123);
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
		
		var  num = /^\d+$/;  //用于判断字符串是否全是数字		
		// 获取文本框的值
		var unitID = $('#TeaUnit').val();
		var majorID = $('#MajorName').val();
		var teaName = $('#TeaName').val();
		var education = $('#Education').combobox('getText');
		var degree = $('#Degree').combobox('getText');
		var title = $('#Title').combobox('getText');
		var trainIssueNum = $('#TrainIssueNum').val();
		var sociaPraFinishNum = $('#SocialNum').val();
		var guideStuNum = $('#GuideStuNum').val();
		var gainTime = $('#GainTime').datebox('getValue');
		var note = $('#Note').val();
		
		if(unitID == null || unitID.length == 0){
			alert("教学单位不能为空");
			return false;
		}
		if(majorID == null || majorID.length == 0){
			alert("专业名称不能为空");
			return false;
		}
		if(teaName == null || teaName.length == 0){
			alert("教师姓名不能为空");
			return false;
		}
		if(education == null || education.length == 0){
			alert("学历不能为空");
			return false;
		}
		if(degree == null || degree.length == 0){
			alert("学位不能为空");
			return false;
		}
		if(title == null || title.length == 0){
			alert("职称不能为空");
			return false;
		}
		if(trainIssueNum == null || trainIssueNum.length == 0){
			alert("指导毕业综合训练课题数量不能为空");
			return false;
		}
		if(!num.test(trainIssueNum)){
			alert("指导毕业综合训练课题数量只能填数字");
			return false;
		}
		if(sociaPraFinishNum == null || sociaPraFinishNum.length == 0){
			alert("实践完成数不能为空");
			return false;
		}
		if(!num.test(sociaPraFinishNum)){
			alert("实践完成数只能填数字");
			return false;
		}
		if(guideStuNum == null || guideStuNum.length == 0){
			alert("指导学生人数不能为空");
			return false;
		}
		if(!num.test(guideStuNum)){
			alert("指导学生人数只能填数字");
			return false;
		}
		if(gainTime == null || gainTime.length == 0){
			alert("获评时间不能为空");
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
    	
    	url = 'pages/T534/edit' ;
    
    	$('.title1').hide();
       	$('#item1').hide();
       	$('hr').hide();
    	
    	$('#dlg').dialog('open').dialog('setTitle','修改分专业毕业综合情况');
    	$('#seqNumber').val(row[0].seqNumber) ;
    	$('#UnitID').combobox('select', row[0].unitID) ;
    	$('#MajorID').combobox('select', row[0].majorID) ;
    	$('#TeaID').combobox('select', row[0].teaID) ;
    	$('#IsOutEmploy').combobox('select', row[0].isOutEmploy) ;
    	$('#Education').combobox('select', row[0].educationID) ;
    	$('#Degree').combobox('select', row[0].degreeID) ;
    	$('#Title').combobox('select', row[0].titleID) ;
    	$('#IsExcellent').combobox('select', row[0].isExcellent) ;
    	$('#TrainIssueNum').val(row[0].trainIssueNum);
    	$('#SocialNum').val(row[0].socialNum);
    	$('#GuideStuNum').val(row[0].guideStuNum);
    	$('#GainBestNum').combobox('select', row[0].gainBestNum) ;
    	$('#GainTime').datebox('setValue',formattime(row[0].gainTime));
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
    		url : "pages/T534/deleteByIds?ids=" + ids,
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

    function formatBoolean(val) {  
        if(val == true){
    	    return '是' ;
        }else if(val == false){
        	return '否' ;
        }else{
        	return null;
        }
    }  


	
