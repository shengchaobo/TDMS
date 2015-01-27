			var url;
			function recover(fileNum){
			
				$('#udlg').dialog('open').dialog('setTitle','文件上传');
				$('#batchForm').form('reset');	
				url = "fileupload?fileNum=" + fileNum;			
			}