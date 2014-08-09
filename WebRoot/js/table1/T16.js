$(function(){  
				var selectYear = $("#cbYearContrast").combobox('getValue');
				var rows = [
					        { "name": "1.校训", "group": "办学指导思想", "value": "",  "field": "contents1","editor": "text"},
					        { "name": "2.定位与发展目标",  "group": "办学指导思想", "value": "", "field": "contents2","editor": "textarea",
					        	"rowheight":"300px"
					        }     
	                   ];

				    							
				$('#edit').propertygrid({
						title : '学校办学指导思想',
						toolbar : "#toolbar",//在添加 增添、删除、修改操作的按钮要用到这个
				        width: '60%',
				        height: '400px',
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
							url: 'pages/T16/loadInfo?selectYear='+selectYear,
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
							url: 'pages/T16/loadInfo?selectYear='+year,
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
						s += row[i].field + '%' + row[i].value + 'a';
						f += row[i].field+ 'a';
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
								url: 'pages/T16/save?data='+data+'&selectYear='+year+'&fields='+f,
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
			   
			 $("#delete").click(function(){
				   var year = $("#cbYearContrast").combobox('getValue'); 
				 $.messager.confirm('数据删除', '您确定删除'+year+'年数据？', function(sure){
					 if (sure){
					 	deleteCourses(year) ;
					 }
				});
			 });
			 
			 function deleteCourses(year){
				 var flag = true;
			    	$.ajax({ 
			    		type: "POST", 
			    		url: "pages/T16/deleteByYear?year="+year, 
			    		async:"true",
			    		dataType: "json",
			    		success: function(json){
					    		if(json.mesg == 'success'){
			    					alert("刪除成功");
			    				}
			    				if(json.mesg == 'fail'){
			    					alert("刪除失败");
			    					flag = false;
			    				}
			    				 reloadgrid (year,flag) 
			   					$('#edit').propertygrid('loadData', rows);	
			    		}
			    	}).submit();
			 
			    }
							   
			   //取消
			   $("#cancel").click(function(){
				     var year = $("#cbYearContrast").combobox('getValue'); 
				     reloadgrid (year,false) 
				     $('#edit').propertygrid('loadData', rows);
				});	
				
			   //导出
			   $("#export").click(function(){
			        var tableName = encodeURI('表1-6办学指导思想（党院办');
			        var year = $("#cbYearContrast").combobox('getValue'); 
				    $('#exportForm').form('submit', {
				    	data : $('#exportForm').serialize(),
				    	   url : "pages/T16/dataExport?excelName="+tableName+'&selectYear='+year,
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


			function douToStr(val){
				     var str;
				     if(val ==  null){
					     str=null;
				     }else{
					     val=val*100;
				    	 var bol=""+val;//把double型转换成sre类型
					     str = bol+"%";
				     }
				     return str;
			  }