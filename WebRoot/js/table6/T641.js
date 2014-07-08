	$(function(){  
				//alert("test");
				var selectYear = $("#cbYearContrast").combobox('getValue'); 
				var rows = [
				        { "name": "资助金额（万元）", "group": "1.政府奖、助学金", "value": "", "field": "govAidFund","editor": {
				    		"type":"numberbox",
				    		"options":{
				        	 	"min":  0,
				        	 	"precision": 2
				    		}
				        } },
				        { "name": "资助学生数（人次）", "group": "1.政府奖、助学金", "value": "", "field": "govAidNum", "editor": "numberbox" },
				        { "name": "资助金额（万元）", "group": "2.社会奖、助学金", "value": "", "field": "socialAidFund","editor": {
				    		"type":"numberbox",
				    		"options":{
				        	 	"min":  0,
				        	 	"precision": 2
				    		}
				        } },
				        { "name": "资助学生数（人次）", "group": "2.社会奖、助学金", "value": "", "field": "socialAidNum", "editor": "numberbox" },
				        { "name": "资助金额（万元）", "group": "3.学校奖学金", "value": "", "field": "schAidFund","editor": {
				    		"type":"numberbox",
				    		"options":{
				        	 	"min":  0,
				        	 	"precision": 2
				    		}
				        } },
				        { "name": "资助学生数（人次）", "group": "3.学校奖学金", "value": "", "field": "schAidNum", "editor": "numberbox" },
				        { "name": "资助金额（万元）", "group": "4.国家助学贷款", "value": "", "field": "nationAidFund","editor": {
				    		"type":"numberbox",
				    		"options":{
				        	 	"min":  0,
				        	 	"precision": 2
				    		}
				        } },
				        { "name": "资助学生数（人次）", "group": "4.国家助学贷款", "value": "", "field": "nationAidNum", "editor": "numberbox" },
				        { "name": "资助金额（万元）", "group": "5.勤工助学金", "value": "", "field": "workStudyFund","editor": {
				    		"type":"numberbox",
				    		"options":{
				        	 	"min":  0,
				        	 	"precision": 2
				    		}
				        } },
				        { "name": "资助学生数（人次）", "group": "5.勤工助学金", "value": "", "field": "workStudyNum", "editor": "numberbox" },
				        { "name": "资助金额（万元）", "group": "6.减免学费", "value": "", "field": "tuitionWaiberFund","editor": {
				    		"type":"numberbox",
				    		"options":{
				        	 	"min":  0,
				        	 	"precision": 2
				    		}
				        } },
				        { "name": "资助学生数（人次）", "group": "6.减免学费", "value": "", "field": "tuitionWaiberNum", "editor": "numberbox" },
				        { "name": "资助金额（万元）", "group": "7.临时困难补助", "value": "", "field": "tempFund","editor": {
				    		"type":"numberbox",
				    		"options":{
				        	 	"min":  0,
				        	 	"precision": 2
				    		}
				        } },
				        { "name": "资助学生数（人次）", "group": "7.临时困难补助", "value": "", "field": "tempNum", "editor": "numberbox" },
				        { "name": "资助金额（万元）", "group": "总计", "value": "", "field": "sumAidFund","editor": false },
				        { "name": "资助学生数（人次）", "group": "总计", "value": "", "field": "sumAidNum", "editor": false }
				    ];
				    							
				$('#edit').propertygrid({
						title : '本科生奖贷补',
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
						url: 'pages/T641/loadInfo?selectYear='+selectYear,
			    		async : false,
			    		dataType : "json",
			    		success : function(json) {
		                    var i = 0;
		                    while(i < rows.length){
		                    	rows[i].value = eval('json.'+rows[i].field);	
		                    	i= i+ 1;
		                    }														
						},
		                error: function(XMLResponse) {
		                      alert("该年数据为空!!!");
			                    var i = 0;
			                    while(i < rows.length){
			                    	rows[i].value = "";	
			                    	i= i+ 1;
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
						url: 'pages/T641/loadInfo?selectYear='+year,
			    		async : false,
			    		dataType : "json",
			    		success : function(json) {
		                    var i = 0;
		                    while(i < rows.length){
		                    	rows[i].value = eval('json.'+rows[i].field);	
		                    	i=i+1;
		                    }								
						},
		                error: function(XMLResponse) {
		                   // alert(XMLResponse.responseText
		                    var i = 0;
		                    while(i < rows.length){
		                    	rows[i].value = "";	
		                    	i=i+1;
		                    }
		                    if(flag == true){
		                    	alert("该年数据为空!!!");
		                    }			                    
		                }
	    		})
		   }	

		   //保存
		   $("#save").click(function(){
				var  s= '';
				var f='';
				var flag = true;
				var row = $('#edit').propertygrid('getChanges');
				for(var i=0; i<row.length-1; i++){
					s += row[i].field + '%' + row[i].value + ',';
					f += row[i].field+ ',';
				}
				s += row[i].field + '%' + row[i].value;
				f += row[i].field;
				//alert(s);
				//alert(f);
				if(s == '') {
					return false;
				}
				data = encodeURI(s)
			    var year = $("#cbYearContrast").combobox('getValue'); 
				$.ajax( {
				    		type : "POST",
				    		contentType: "application/json;utf-8",
							url: 'pages/T641/save?data='+data+'&selectYear='+year+'&fields='+f,
				    		async : false,
				    		dataType : "json",
				    		success : function(json) {
				    				if(json.mesg == 'success'){
				    					alert("保存成功");
				    				}
				    				if(json.mesg == 'fail'){
				    					alert("保存失败");
				    					flag = false;
				    				}										
							},
			                error: function(XMLResponse) {
									alert("保存失败");
									flag = false;
			                }
		    		})		    		
					reloadgrid (year,flag) 	;
					$('#edit').propertygrid('loadData', rows);						 					 
			});		
						   
		   //取消
		   $("#cancel").click(function(){
			     var year = $("#cbYearContrast").combobox('getValue'); 
			     reloadgrid (year,false) 
			     $('#edit').propertygrid('loadData', rows);
			});	
				
			   //导出
			   $("#export").click(function(){
			        var tableName = encodeURI('表6-4-1本科生奖贷补（学工处）');
			        var year = $("#cbYearContrast").combobox('getValue'); 
				    $('#exportForm').form('submit', {
				    	data : $('#exportForm').serialize(),
					    url : "pages/T641/dataExport?excelName="+tableName+'&selectYear='+year,
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