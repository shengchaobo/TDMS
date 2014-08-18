	$(function(){  
				//alert("test");
				var selectYear = $("#cbYearContrast").combobox('getValue'); 
				var rows = [
				        { "name": "上学年人数（个）", "group": "6.普通预科生数", "value": "", "field": "preppyLastYearNum","editor": "numberbox" },
				        { "name": "本学年人数（个）", "group": "6.普通预科生数", "value": "", "field": "preppyThisYearNum", "editor": "numberbox" },
				        { "name": "上学年人数（个）", "group": "7.进修生数", "value": "", "field": "advStuLastYearNum", "editor": "numberbox" },
				        { "name": "本学年人数（个）", "group": "7.进修生数", "value": "", "field": "advStuThisYearNum", "editor": "numberbox" },
				        { "name": "上学年人数（个）", "group": "8.成人脱产学生数", "value": "", "field": "adultLastYearNum", "editor": "numberbox" },
				        { "name": "本学年人数（个）", "group": "8.成人脱产学生数", "value": "", "field": "adultThisYearNum","editor": "numberbox" },
				        { "name": "上学年人数（个）", "group": "9.夜大（业余）学生数", "value": "", "field": "nightUniLastYearNum", "editor": "numberbox" },
				        { "name": "本学年人数（个）", "group": "9.夜大（业余）学生数", "value": "", "field": "nightUniThisYearNum", "editor": "numberbox" },
				        { "name": "上学年人数（个）", "group": "10.函授学生数", "value": "", "field": "correspdCoLastYearNum", "editor": "numberbox" },
				        { "name": "本学年人数（个）", "group": "10.函授学生数", "value": "", "field": "correspdThisYearNum","editor": "numberbox" },
				        { "name": "上学年人数（个）", "group": "11.网络学生数", "value": "", "field": "netStuLastYearNum", "editor": "numberbox" },
				        { "name": "本学年人数（个）", "group": "11.网络学生数", "value": "", "field": "netStuThisYearNum", "editor": "numberbox" },
				        { "name": "上学年人数（个）", "group": "12.自考学生数", "value": "", "field": "selfStudyLastYearNum", "editor": "numberbox" },
				        { "name": "本学年人数（个）", "group": "12.自考学生数", "value": "", "field": "selfStudyThisYearNum","editor": "numberbox" }
				    ];
				    							
				$('#edit').propertygrid({
						title : '继续教育学生数量基本情况',
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
							url: 'pages/T614/loadInfo?selectYear='+selectYear,
				    		async : false,
				    		dataType : "json",
				    		success : function(json) {
				    			if(typeof(json.data)!="undefined"){
				    				alert(json.data);
				    			}
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
							url: 'pages/T614/loadInfo?selectYear='+year,
				    		async : false,
				    		dataType : "json",
				    		success : function(json) {
				    			if(typeof(json.data)!="undefined"){
				    				alert(json.data);
				    			}
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
								url: 'pages/T614/save?data='+data+'&selectYear='+year+'&fields='+f,
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
			        var tableName = encodeURI('表6-1-4继续教育学生数量基本情况（继续教育学院）');
			        var year = $("#cbYearContrast").combobox('getValue'); 
				    $('#exportForm').form('submit', {
				    	data : $('#exportForm').serialize(),
					    url : "pages/T614/dataExport?excelName="+tableName+'&selectYear='+year,
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