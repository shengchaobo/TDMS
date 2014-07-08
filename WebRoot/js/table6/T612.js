	$(function(){  
				//alert("test");
				var selectYear = $("#cbYearContrast").combobox('getValue');
				var rows = [
				        { "name": "上学年人数（个）", "group": "3.硕士研究生数", "value": "", "field": "masterLastYearNum","editor": false },
				        { "name": "本学年人数（个）", "group": "3.硕士研究生数", "value": "", "field": "masterThisYearNum", "editor": false },
				        { "name": "上学年人数（个）", "group": "其中：全日制研究生", "value": "", "field": "fullTimeMasterLastYearNum", "editor": "numberbox" },
				        { "name": "本学年人数（个）", "group": "其中：全日制研究生", "value": "", "field": "fullTimeMasterThisYearNum", "editor": "numberbox" },
				        { "name": "上学年人数（个）", "group": "其中：非全日制研究生", "value": "", "field": "partTimeMasterLastYearNum", "editor": "numberbox" },
				        { "name": "本学年人数（个）", "group": "其中：非全日制研究生", "value": "", "field": "partTimeMasterThisYearNum","editor": "numberbox" },
				        { "name": "上学年人数（个）", "group": "4.博士研究生数", "value": "", "field": "doctorLastYearNum","editor": false },
				        { "name": "本学年人数（个）", "group": "4.博士研究生数", "value": "", "field": "doctorThisYearNum", "editor": false },
				        { "name": "上学年人数（个）", "group": "其中：全日制博士生", "value": "", "field": "fullTimeDoctorLastYearNum", "editor": "numberbox" },
				        { "name": "本学年人数（个）", "group": "其中：全日制博士生", "value": "", "field": "fullTimeDoctorThisYearNum", "editor": "numberbox" },
				        { "name": "上学年人数（个）", "group": "其中：非全日制博士生", "value": "", "field": "partTimeDoctorLastYearNum", "editor": "numberbox" },
				        { "name": "本学年人数（个）", "group": "其中：非全日制博士生", "value": "", "field": "partTimeDoctorThisYearNum","editor": "numberbox" }
				    ];
				    							
				$('#edit').propertygrid({
						title : '研究生数量基本情况',
						toolbar : "#toolbar",//在添加 增添、删除、修改操作的按钮要用到这个
				        width: '60%',
				        height: 'auto',
				        showGroup: true,
				        scrollbarSize: 0,
				        columns: [[
				                { field: 'name', title: '', width: 100, resizable: true },
				                { field: 'value', title: '', width: 100, resizable: false }
				        ]]
				 });
				
				  $.ajax( {
				    		type : "POST",
				    		contentType: "application/json;utf-8",
							url: 'pages/T612/loadInfo?selectYear='+selectYear,
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
							url: 'pages/T612/loadInfo?selectYear='+year,
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
								url: 'pages/T612/save?data='+data+'&selectYear='+year+'&fields='+f,
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
			        var tableName = encodeURI('表6-1-2研究生数量基本情况（研究生院）');
			        var year = $("#cbYearContrast").combobox('getValue'); 
				    $('#exportForm').form('submit', {
				    	data : $('#exportForm').serialize(),
					    url : "pages/T612/dataExport?excelName="+tableName+'&selectYear='+year,
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