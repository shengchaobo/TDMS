	
$(function(){  
				var selectYear = $("#cbYearContrast").combobox('getValue'); 
				var rows = [
				            { "name": "数量（个））", "group": "总计（个数）", "value": "",  "field": "sumResNum","editor":  false},
					        { "name": "专业科研用房面积（平方米）", "group": "总计（个数）", "value": "",  "field": "sumResArea","editor":  false},
					        { "name": "数量（个）",  "group": "1. 国家级实验室", "value": "", "field": "nationResNum", "editor":  false},
					        { "name": "专业科研用房面积（平方米）", "group": "1. 国家级实验室", "value": "", "field": "nationResArea","editor":  false },
					        { "name": "数量（个）", "group": "2. 国家重点实验室", "value": "", "field": "nationKeyResNum","editor":  false },
					        { "name": "专业科研用房面积（平方米）", "group": "2. 国家重点实验室", "value": "", "field": "nationKeyResArea", "editor":  false },
					        { "name": "数量（个）", "group": "3. 国家工程（技术）研究中心", "value": "","field": "nationEnginResNum", "editor":  false},
					        { "name": "专业科研用房面积（平方米）",  "group": "3. 国家工程（技术）研究中心", "value": "", "field": "nationEnginResArea", "editor":  false},
					        { "name": "数量（个）", "group": "4. 其他国家级科研机构", "value": "", "field": "otherNationResNum","editor":  false },
					        { "name": "专业科研用房面积（平方米）", "group": "4. 其他国家级科研机构", "value": "", "field": "otherNationResArea","editor":  false },
					        { "name": "数量（个）", "group": "5. 省、部级设置的研究所（院、中心）", "value": "",  "field": "proviResNum","editor":  false},
					        { "name": "专业科研用房面积（平方米）",  "group": "5. 省、部级设置的研究所（院、中心）", "value": "", "field": "proviResArea", "editor":  false},
					        { "name": "数量（个）", "group": "6. 省、部级设置的实验室", "value": "", "field": "proviLabNum","editor":  false },
					        { "name": "专业科研用房面积（平方米）", "group": "6. 省、部级设置的实验室", "value": "", "field": "proviLabArea","editor":  false },
					        { "name": "数量（个）", "group": "7. 其他省级科研机构", "value": "",  "field": "otherProviResNum","editor":  false},
					        { "name": "专业科研用房面积（平方米）", "group": "7. 其他省级科研机构", "value": "",  "field": "otherProviResArea","editor":  false},
					        { "name": "数量（个）",  "group": "8.1  人文科学重点研究基地，总数", "value": "", "field": "humanResSumNum", "editor":  false},
					        { "name": "专业科研用房面积（平方米）", "group": "8.1  人文科学重点研究基地，总数", "value": "", "field": "humanResSumArea","editor":  false },
					        { "name": "数量（个）", "group": "8.2  人文科学重点研究基地，其中：国家级", "value": "", "field": "humanNationResNum","editor":  false },
					        { "name": "专业科研用房面积（平方米）", "group": "8.2  人文科学重点研究基地，其中：国家级", "value": "", "field": "humanNationResArea", "editor":  false },
					        { "name": "数量（个）", "group": "8.3  人文科学重点研究基地，其中：省部级", "value": "","field": "humanProviResNum", "editor":  false},
					        { "name": "专业科研用房面积（平方米）",  "group": "8.3  人文科学重点研究基地，其中：省部级", "value": "", "field": "humanProviResArea", "editor":  false},
					        { "name": "数量（个）", "group": "9. 市级科研机构", "value": "", "field": "cityResNum","editor":  false },
					        { "name": "专业科研用房面积（平方米）", "group": "9. 市级科研机构", "value": "", "field": "cityResArea","editor":  false },
					        { "name": "数量（个）", "group": "10. 市级科研机构", "value": "",  "field": "teaUnitResNum","editor":  false},
					        { "name": "专业科研用房面积（平方米）",  "group": "10. 市级科研机构", "value": "", "field": "teaUnitResArea", "editor":  false},
					        { "name": "数量（个）", "group": "11. 其他校级科研机构", "value": "", "field": "otherSchResNum","editor":  false },
					        { "name": "专业科研用房面积（平方米）", "group": "11. 其他校级科研机构", "value": "", "field": "otherSchResArea","editor":  false }
	                   ];

					    		
				    							
				$('#edit').propertygrid({
						title : '科研机构',
						toolbar : "#toolbar",//在添加 增添、删除、修改操作的按钮要用到这个
				        width: '60%',
				        height: 'auto',
				        showGroup: true,
				        scrollbarSize: 0,
				        columns: [[
				                { field: 'name', title: '项目', width: 100, resizable: true },
				                { field: 'value', title: '数量（个）', width: 100, resizable: false }
				        ]]
				 });
				 				 
				  $.ajax( {
				    		type : "POST",
				    		contentType: "application/json;utf-8",
							url: 'pages/S15/loadInfo?selectYear='+selectYear,
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
			                alert("kongkongh");
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
							url: 'pages/S15/loadInfo?selectYear='+year,
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
								url: 'pages/S15/save?data='+data+'&selectYear='+year+'&fields='+f,
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
			        var tableName = encodeURI('表1-1学校基本信息（党院办）');
			        var year = $("#cbYearContrast").combobox('getValue'); 
				    $('#exportForm').form('submit', {
				    	data : $('#exportForm').serialize(),
					    url : "pages/S15/dataExport?excelName="+tableName+'&selectYear='+year,
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