	//全局变量，用来暂存当前的url值
   var url;
    
   //弹出添加的界面
	function newObject() {
		
		//update隐藏的量在提交之后要恢复
    	$('#title1').show();
    	$('#item1').show();
    	$('hr').show();
    	
		url = 'pages/T532/insert' ;
		$('#dlg').dialog('open').dialog('setTitle', '添加新的实验教学示范中心');
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
	  		 url: 'pages/T532/uploadFile',
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
		var centerName = $('#CenterName').val();
		var fromSubject = $('#FromSubject').val();
		var centerLevel = $('#CenterLevel').combobox('getText');
		var unitID = $('#FromTeaUnit').val();
		var teaID = $('#Leader').val();
		var teaTitle = $('#TeaTitle').combobox('getText');
		var buildTime = $('#BuildTime').datebox('getValue');
		var buildAppvlID = $('#BuildAppvlID').val();
		var receptTime = $('#ReceptTime').datebox('getValue');
		var receptAppvlID = $('#ReceptAppvlID').val();
		var validTime = $('#ValidTime').val();
		var fund = $('#Fund').val();
		var note = $('#Note').val();
	
		if(centerName == null|| centerName.length == 0){
			alert("中心名称不能为空");
			return false;
		}
		if(fromSubject == null|| fromSubject.length == 0){
			alert("所属学科不能为空");
			return false;
		}
		if(centerLevel == null|| centerLevel.length == 0){
			alert("级别不能为空");
			return false;
		}
		if(unitID == null|| unitID.length == 0){
			alert("所属教学单位不能为空");
			return false;
		}
		if(teaID == null|| teaID.length == 0){
			alert("中心主任不能为空");
			return false;
		}
		if(teaTitle == null||teaTitle.length == 0){
			alert("职称不能为空");
			return false;
		}
		if(buildTime == null||buildTime.length == 0){
			alert("设立时间不能为空");
			return false;
		}
		if(buildAppvlID == null||buildAppvlID.length == 0){
			alert("批文号不能为空");
			return false;
		}
		if(receptTime == null||receptTime.length == 0){
			alert("验收时间不能为空");
			return false;
		}
		if(receptAppvlID == null||receptAppvlID.length == 0){
			alert("验收批文号不能为空");
			return false;
		}
		if(validTime == null||fund.length == 0){
			alert("有效期不能为空");
			return false;
		}
		if(!num.test(validTime)){
			alert("有效期只能为数字");
			return false;
		}
		if(fund == null||receptAppvlID.length == 0){
			alert("经费不能为空");
			return false;
		}
		if(!num.test(fund)){
			alert("经费只能为数字");
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
    	
    	url = 'pages/T532/edit' ;
    	
    	$('#title1').hide();
    	$('#item1').hide();
    	$('hr').hide();
    	
    	$('#dlg').dialog('open').dialog('setTitle','修改实验教学示范中心的信息');
    	$('#seqNumber').val(row[0].seqNumber) ;
    	$('#CenterName').val(row[0].centerName) ;
    	$('#FromSubject').val(row[0].fromSubject) ;
    	$('#CenterLevel').combobox('select', row[0].centerLevelID) ;
    	$('#UnitID').combobox('select', row[0].unitID) ;
    	$('#TeaID').combobox('select', row[0].teaID) ;
    	$('#TeaTitle').combobox('select', row[0].teaTitleID) ;
    	$('#BuildTime').datebox('setValue',formattime(row[0].buildTime));
    	$('#BuildAppvlID').val(row[0].buildAppvlID) ;
    	$('#ReceptTime').datebox('setValue',formattime(row[0].receptTime));
    	$('#ReceptAppvlID').val(row[0].receptAppvlID) ;
    	$('#ValidTime').val(row[0].validTime) ;
    	$('#Fund').val(row[0].fund) ;
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
    		url : "pages/T532/deleteByIds?ids=" + ids,
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

	
