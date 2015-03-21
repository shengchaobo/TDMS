var url;
			function upload(){
			
				$('#udlg').dialog('open').dialog('setTitle','数据恢复');
				$('#batchForm').form('reset');	
				url = "fileupload";		
			}
			
			function batchImport(){
				if(confirm("恢复数据将替换数据库中的数据，确认将继续选择备份文件。")){
				  var fileName = $('#upload').val() ; 	
				  if(fileName == null || fileName == ""){
					  $.messager.alert('文件导入', '请选择将要上传的文件!');      
				   		return false ;
				  }	
				  
			  	 $('#batchForm').form('submit',{
			  		 url: url,
			  		 type: "post",
				     dataType: "json",
			  		 onSubmit: function(){
			  			 return true;
			  		 },
			  		 success: function(result){
				  		 	var result = eval('('+result+')');
				  		 	if (result.state){
				  		 		$('#udlg').dialog('close'); // close the dialog	
								alert(result.data);								
								window.location.reload();											 
				  		 	} else {
								alert(result.data);
				  		 	}
				  	 }
			  	});
			  }
			}