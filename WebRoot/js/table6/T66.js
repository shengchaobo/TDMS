	$(function(){  
				//alert("test");
				var selectYear = $("#cbYearContrast").combobox('getValue'); 
				var rows = [
				        { "name": "总数", "group": "1.学生社团（个）", "value": "", "field": "stuClubSum","editor": false },
				        { "name": "其中：科技类", "group": "1.学生社团（个）", "value": "", "field": "stuClubSciNum", "editor": "numberbox" },
				        { "name": "其中：人文社会类", "group": "1.学生社团（个）", "value": "", "field": "stuClubHumanNum", "editor": "numberbox" },
				        { "name": "其中：体育类", "group": "1.学生社团（个）", "value": "", "field": "stuClubSportNum", "editor": "numberbox" },
				        { "name": "其中：文艺类", "group": "1.学生社团（个）", "value": "", "field": "stuClubArtNum", "editor": "numberbox" },
				        { "name": "其他", "group": "1.学生社团（个）", "value": "", "field": "otherStuClub", "editor": "numberbox" },
				        { "name": "总数", "group": "2.参与人次数（人次）", "value": "", "field": "joinStuSum","editor": false },
				        { "name": "其中：科技类", "group": "2.参与人次数（人次）", "value": "", "field": "joinClubSciNum", "editor": "numberbox" },
				        { "name": "其中：人文社会类", "group": "2.参与人次数（人次）", "value": "", "field": "joinClubHumanNum", "editor": "numberbox" },
				        { "name": "其中：体育类", "group": "2.参与人次数（人次）", "value": "", "field": "joinClubSportNum", "editor": "numberbox" },
				        { "name": "其中：文艺类", "group": "2.参与人次数（人次）", "value": "", "field": "joinClubArtNum", "editor": "numberbox" },
				        { "name": "其他", "group": "2.参与人次数（人次）", "value": "", "field": "joinOtherClub", "editor": "numberbox" }
				    ];
				    							
				$('#edit').propertygrid({
						title : '学生社团',
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
						url: 'pages/T66/loadInfo?selectYear='+selectYear,
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
						url: 'pages/T66/loadInfo?selectYear='+year,
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
							url: 'pages/T66/save?data='+data+'&selectYear='+year+'&fields='+f,
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
			        var tableName = encodeURI('表6-6 学生社团（团委）');
			        var year = $("#cbYearContrast").combobox('getValue'); 
				    $('#exportForm').form('submit', {
				    	data : $('#exportForm').serialize(),
					    url : "pages/T66/dataExport?excelName="+tableName+'&selectYear='+year,
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