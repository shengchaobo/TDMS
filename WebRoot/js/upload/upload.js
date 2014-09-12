			var url;
			function upload(fileNum){
			
				$('#udlg').dialog('open').dialog('setTitle','文件上传');
				$('#batchForm').form('reset');	
				url = "fileupload?fileNum=" + fileNum;			
			}
			
			function batchImport(){
			 
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
			  
			  function loadAllURL(start,end){
	  		   	$.ajax({
			   		type : "post",
			   		url : "loadAllURL?start=" + start +"&end=" + end,
			   		async : true,
			   		dataType :"json",
			   		success : function(result) {
			   			for(var i = start; i<=end; i++){
			   				if(result[i]!="none"){
								html = "<a title='下载' href='downloadFile?fileNum="+i+"'>" + result[i] + "</a>&nbsp;&nbsp;&nbsp;&nbsp;<a title='删除' herf='javascript:void(0)' onclick='deleteFile("+i+")'><img src='images/delete.jpg' border='0'/></a>";				
								$('#downFile'+i).html(html);	
			   				}			
			   			}				
					}
			   	});			  	
			  }
			  
			  function deleteFile(fileNum){	
					$.messager.confirm('文件删除', '您确定删除?', function(sure) {
						if (sure) {
			    		   	$.ajax({
						   		type : "post",
						   		url : "deleteFile?fileNum=" +fileNum,
						   		async : true,
						   		dataType :"json",
						   		success : function(result) {
							  		 	if (result.state){				  		 	
											alert(result.data);								
											window.location.reload();											 
							  		 	} else {
											alert(result.data);
							  		 	}		
								}
						   	});
						}
				   	});				  
			  }