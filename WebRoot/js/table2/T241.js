	$(function(){  
				var selectYear = $("#cbYearContrast").combobox('getValue'); 
				var rows = [
				        { "name": "总占地面积", "group": "1.占地面积(平方米)", "value": "",  "field": "sumArea","editor": "numberbox" },
				        { "name": "学校产权", "group": "1.占地面积(平方米)", "value": "", "field": "schProArea", "editor": "numberbox" },
				        { "name": "其中：绿化用地", "value": "", "group": "1.占地面积(平方米)", "field": "greenArea", "editor": "numberbox" },
				        { "name": "非学校产权", "value": "", "group": "1.占地面积(平方米)", "field": "notSchProArea", "editor": "numberbox" },
				        { "name": "其中：绿化用地", "group": "1.占地面积(平方米)", "value": "", "field": "greenAreaNotInSch", "editor": "numberbox" },
				        { "name": "其中：独立使用", "group": "1.占地面积(平方米)", "editor": "numberbox", "value": "", "field": "onlyUseArea" },
				        { "name": "共同使用", "group": "1.占地面积(平方米)", "value": "", "field": "coUseArea", "editor": "numberbox" },
				       	{ "name": "总建筑面积", "group": "2.总建筑面积(平方米)", "editor": "numberbox", "value": "", "field": "sumCoverArea" },
				       	{ "name": "学校产权", "group": "2.总建筑面积(平方米)", "editor": "numberbox", "value": "", "field": "schProCovArea" },
				       	{ "name": "非学校产权", "group": "2.总建筑面积(平方米)", "editor": "numberbox", "value": "", "field": "notSchProCovArea" },
				       	{ "name": "其中：独立使用", "group": "2.总建筑面积(平方米)", "editor": "numberbox", "value": "", "field": "onlyUseCovArea" },
				       	{ "name": "共同使用", "group": "2.总建筑面积(平方米)", "editor": "numberbox", "value": "", "field": "coUseCovArea" }
				    ];
				    							
				$('#edit').propertygrid({
						title : '占地与建筑面积',
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
							url: 'pages/T241/loadInfo?selectYear='+selectYear,
				    		async : false,
				    		dataType : "json",
				    		success : function(json) {
			                    var i = 0;
			                    while(i<12){
			                    	rows[i].value = eval('json.'+rows[i].field);	
			                    	i= i+1;
			                    }														
							},
			                error: function(XMLResponse) {
			                      alert("该年数据为空!!!");
				                    var i = 0;
				                    while(i<12){
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
							url: 'pages/T241/loadInfo?selectYear='+year,
				    		async : false,
				    		dataType : "json",
				    		success : function(json) {
			                    var i = 0;
			                    while(i<12){
			                    	rows[i].value = eval('json.'+rows[i].field);	
			                    	i=i+1;
			                    }								
							},
			                error: function(XMLResponse) {
			                   // alert(XMLResponse.responseText
			                    var i = 0;
			                    while(i<12){
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
								url: 'pages/T241/save?data='+data+'&selectYear='+year+'&fields='+f,
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
			        var tableName = encodeURI('表2-4-1图书数量.xls');
			        var year = $("#cbYearContrast").combobox('getValue'); 
				    $('#exportForm').form('submit', {
				    	data : $('#exportForm').serialize(),
					    url : "pages/T241/dataExport?excelName="+tableName+'&selectYear='+year,
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