	
$(function(){  
				var selectYear = $("#cbYearContrast").combobox('getValue');
				var rows = [
					        { "name": "个数（个）", "group": "1. 国家级科研机构", "value": "",  "field": "nationResNum","editor":  false},
					        { "name": "所占比例（%）",  "group": "1. 国家级科研机构", "value": "", "field": "nationResRatio", "editor":  false},
					        { "name": "个数（个）", "group": "2. 省部级科研机构", "value": "", "field": "proviResNum","editor":  false },
					        { "name": "所占比例（%）", "group": "2. 省部级科研机构", "value": "", "field": "proviResRatio","editor":  false },
					        { "name": "个数（个）", "group": "3. 市级科研机构", "value": "", "field": "cityResNum", "editor":  false },
					        { "name": "所占比例（%）", "group": "3. 市级科研机构", "value": "","field": "cityResRatio", "editor":  false},
					        { "name": "个数（个）",  "group": "4. 校级科研机构", "value": "", "field": "schResNum", "editor":  false},
					        { "name": "所占比例（%）", "group": "4. 校级科研机构", "value": "", "field": "schResRatio","editor":  false },
					        { "name": "个数（个）", "group": "5. 总计", "value": "", "field": "sumResNum","editor":  false }
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
					               { field: 'value', title: '内容', width: 100, resizable: false }
				        ]]
				 });
				 				 
				  $.ajax( {
				    		type : "POST",
				    		contentType: "application/json;utf-8",
							url: 'pages/A15/loadInfo?selectYear='+selectYear,
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
							url: 'pages/A15/loadInfo?selectYear='+year,
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
								url: 'pages/A15/save?data='+data+'&selectYear='+year+'&fields='+f,
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
			        var tableName = encodeURI('表A-1-5科研机构');
			        var year = $("#cbYearContrast").combobox('getValue'); 
				    $('#exportForm').form('submit', {
				    	data : $('#exportForm').serialize(),
					    url : "pages/A15/dataExport?excelName="+tableName+'&selectYear='+year,
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