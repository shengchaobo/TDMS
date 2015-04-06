	$(function(){  
				var selectYear = $("#cbYearContrast").combobox('getValue'); 
				var rows = [
				        { "name": "1.教学科研及辅助用房（平方米）", "group": "", "value": "",  "field": "sumTeaArea","editor": false},
				        { "name": "<font color=white>——</font>其中：教室", "group": "", "value": "", "field": "classrmArea", "editor":  false},
				        { "name": "<font color=white>————</font>　图书馆", "value": "", "group": "", "field": "libArea", "editor":  false},
				        { "name": "<font color=white>————</font>　实验室、实习场所", "value": "", "group": "", "field": "labArea", "editor":  false },
				        { "name": "<font color=white>————</font>　专用科研用房", "group": "", "value": "", "field": "resArea", "editor": false },
				        { "name": "<font color=white>————</font>　体育馆", "group": "", "value": "", "field": "phyArea", "editor": false},
				        { "name": "<font color=white>————</font>　会堂", "group": "", "value": "", "field": "hallArea", "editor": false},
				        { "name": "2.行政用房（平方米）", "group": "", "value": "", "field": "sumAdminArea", "editor": false} 
				    ];
				    							
				$('#edit').propertygrid({
						title : '教学行政用房面积',
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
							url: 'pages/S22/loadInfo?selectYear='+selectYear,
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
			                      alert("该统计表数据不全，请填写相关数据后再进行统计!!!");
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
							url: 'pages/S22/loadInfo?selectYear='+year,
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
			                    	alert("该统计表数据不全，请填写相关数据后再进行统计!!!");
			                    }			                    
			                }
		    		})
			   }	
				
			   //导出
			   $("#export").click(function(){
			        var tableName = encodeURI('S-2-2教学行政用房面积');
			        var year = $("#cbYearContrast").combobox('getValue'); 
				    $('#exportForm').form('submit', {
				    	data : $('#exportForm').serialize(),
					    url : "pages/S22/dataExport?excelName="+tableName+'&selectYear='+year,
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