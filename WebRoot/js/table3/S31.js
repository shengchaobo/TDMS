
	$(function(){  
		var selectYear = $("#cbYearContrast").combobox('getValue'); 
		var rows = [
			         
		        	{"name": "1.博士后流动站（个）", "group": "", "value": "",  "field": "postdocStation","editor": false },
		        	{"name": "2.博士点（个）", "group": "", "value": "",  "field": "docStation","editor": false },
		        	{"name": "3.硕士点（个）", "group": "", "value": "",  "field": "masterStation","editor": false },
		        	{"name": "4.本科专业总数（个）", "group": "", "value": "",  "field": "sumMajor","editor": false },
		        	{"name": "4.本科专业其中：新专业（个）", "group": "", "value": "",  "field": "newMajor","editor": false },
		        	{"name": "5.专科专业（各）", "group": "", "value": "",  "field": "juniorMajor","editor": false }				        
		        
		    ];
		    							
		$('#edit').propertygrid({
	
				title : '学科建设',
				toolbar : "#toolbar",//在添加 增添、删除、修改操作的按钮要用到这个
		        width: '60%',
		        height: 'auto',
		        showGroup: true,
		        scrollbarSize: 0,
		        columns: [[
		                   { field: 'name', title: '项目', width: 100, resizable: true },
			               { field: 'value', title: '内容', width: 100, resizable: false }
		        ]]
		 });
		
		  $.ajax( {
	    		type : "POST",
	    		contentType: "application/json;utf-8",
				url: 'pages/S31/loadInfo?selectYear='+selectYear,
	    		async : false,
	    		dataType : "json",
	    		success : function(json) {
		  			if(typeof(json.data)!="undefined"){
		  				alert(json.data);
		  			}
                  var i = 0;
                  while(i<rows.length){
                  	rows[i].value = eval('json.'+rows[i].field);	
                  	i= i+1;
                  }														
				},
              error: function(XMLResponse) {
                    alert("该年数据为空!!!");
	                    var i = 0;
	                    while(i<rows.length){
	                    	rows[i].value = "";	
	                    	i= i+1;
	                    }			                      
              }
		})

		$('#edit').propertygrid('loadData', rows);
	   	
	   	
			//刷新页面
		 $("#cbYearContrast").combobox({  
	         onChange:function(newValue, oldValue){  
				reloadgrid(newValue,true);
				$('#edit').propertygrid('loadData', rows);
             }
         }); 
         
	       	function reloadgrid (year,flag)  {
	       	
 				  $.ajax( {
			    		type : "POST",
			    		contentType: "application/json;utf-8",
						url: 'pages/S31/loadInfo?selectYear='+year,
			    		async : false,
			    		dataType : "json",
			    		success : function(json) {
				  			if(typeof(json.data)!="undefined"){
				  				alert(json.data);
				  			}
		                    var i = 0;
		                    while(i<rows.length){
		                    	rows[i].value = eval('json.'+rows[i].field);	
		                    	i=i+1;
		                    }								
						},
		                error: function(XMLResponse) {
		                   // alert(XMLResponse.responseText
		                    var i = 0;
		                    while(i<rows.length){
		                    	rows[i].value = "";	
		                    	i=i+1;
		                    }
		                    if(flag == true){
		                    	alert("该年数据为空!!!");
		                    }			                    
		                }
	    		})
		   }	

	

	   //导出
	   $("#export").click(function(){
		  
	        var tableName = encodeURI('S-3-1学科建设');
	        var year = $("#cbYearContrast").combobox('getValue'); 
	        //alert(year);
		    $('#exportForm').form('submit', {
		    	data : $('#exportForm').serialize(),
			    url : "pages/S31/dataExport?excelName="+tableName+'&selectYear='+year,
			    onSubmit : function() {
			    	return $(this).form('validate');//对数据进行格式化
			    },
			    success : function(data) {
			    	    $.messager.show({
					    	title : '提示',
					    	msg : data
					    });
			    }
		    }); 
		});							    	
}); 