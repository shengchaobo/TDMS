	$(function(){  
				var selectYear = $("#cbYearContrast").combobox('getValue'); 
				var rows = [
				        { "name": "1.开设的职业生涯规划及创业教育指导课程数（个）", "group": "", "value": "",  "field": "courseNum","editor": "numberbox" },
				        { "name": "2.课程门次数（门次）", "group": "", "value": "",  "field": "courseTimes","editor": "numberbox" },
				        { "name": "3.任课教师数（人）", "group": "", "value": "",  "field": "teaNum","editor": "numberbox" },
				        { "name": "4.面向学生数（人）", "group": "", "value": "",  "field": "stuNum","editor": "numberbox" }
				    ];
				    							
				$('#edit').propertygrid({
						//title : '职业及创业教育课程',
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
							url: 'pages/T2102/loadInfo?selectYear='+selectYear,
				    		async : false,
				    		dataType : "json",
				    		success : function(json) {
				    			if(typeof(json.data)!="undefined"){
				    				alert(json.data);
				    				$("#edit").propertygrid({title:'职业及创业教育课程'});		
			    					$("#cancel").show();
			    					$("#save").show();
				    				$("#export").hide();
				    			}else{	
				    				if(json.checkState==WAITCHECK){
				    					$("#edit").propertygrid({title:'职业及创业教育课程（<font color=red>待审核</font>）'});
				    					//document.getElementById("export").style.display ="none";
				    					$("#cancel").show();
				    					$("#save").show();
					    				$("#export").hide();
				    				}
				    				else if(json.checkState==PASSCHECK){
				    					$("#edit").propertygrid({title:'职业及创业教育课程（<font color=red>审核通过</font>）'});					    				
				    					$("#cancel").hide();
				    					$("#save").hide();
				    					$("#export").show();
				    				}				    				
				    				else if(json.checkState==NOPASSCHECK){
				    					$("#edit").propertygrid({title:'职业及创业教育课程（<font color=red>审核未通过</font>）'});	
				    					$("#cancel").show();
				    					$("#save").show();
					    				$("#export").hide();
				    				}
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
							url: 'pages/T2102/loadInfo?selectYear='+year,
				    		async : false,
				    		dataType : "json",
				    		success : function(json) {
				    			if(typeof(json.data)!="undefined"){
				    				alert(json.data);
				    				$("#edit").propertygrid({title:'职业及创业教育课程'});		
			    					$("#cancel").show();
			    					$("#save").show();
				    				$("#export").hide();
				    			}else{	
				    				if(json.checkState==WAITCHECK){
				    					$("#edit").propertygrid({title:'职业及创业教育课程（<font color=red>待审核</font>）'});
				    					//document.getElementById("export").style.display ="none";
				    					$("#cancel").show();
				    					$("#save").show();
					    				$("#export").hide();
				    				}
				    				else if(json.checkState==PASSCHECK){
				    					$("#edit").propertygrid({title:'职业及创业教育课程（<font color=red>审核通过</font>）'});					    				
				    					$("#cancel").hide();
				    					$("#save").hide();
				    					$("#export").show();
				    				}				    				
				    				else if(json.checkState==NOPASSCHECK){
				    					$("#edit").propertygrid({title:'职业及创业教育课程（<font color=red>审核未通过</font>）'});	
				    					$("#cancel").show();
				    					$("#save").show();
					    				$("#export").hide();
				    				}
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
								url: 'pages/T2102/save?data='+data+'&selectYear='+year+'&fields='+f,
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
			    		myMarquee('T2102', CTypeThree)
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
			        var tableName = encodeURI('表2-10-2职业及创业教育课程（招就处）');
			        var year = $("#cbYearContrast").combobox('getValue'); 
				    $('#exportForm').form('submit', {
				    	data : $('#exportForm').serialize(),
					    url : "pages/T2102/dataExport?excelName="+tableName+'&selectYear='+year,
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