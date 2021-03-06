	$(function(){  
				var selectYear = $("#cbYearContrast").combobox('getValue'); 
				var rows = [
					        { "name": "收入总额", "group": "1.学校教育经费收入（万元）", "value": "",  "field": "sumIncome","editor": false },
					        { "name": "收入总计", "group": "2.其中本科教育事业收入（万元）", "value": "", "field": "sumUndergraIncome", "editor":  false },
					        { "name": "<font color=white>——</font>本科生生均拨款总额：总数", "value": "", "group": "2.其中本科教育事业收入（万元）", "field": "allocateFund", "editor":  false },
					        { "name": "<font color=white>————————————</font>国家", "value": "", "group": "2.其中本科教育事业收入（万元）", "field": "nationFund", "editor":  {
					    		"type":"numberbox",
					    		"options":{
					        	 	"min":  0,
					        	 	"precision": 2
					    		}
					        } },
					        { "name": "<font color=white>————————————</font>地方", "group": "2.其中本科教育事业收入（万元）", "value": "", "field": "localFund", "editor":  {
					    		"type":"numberbox",
					    		"options":{
					        	 	"min":  0,
					        	 	"precision": 2
					    		}
					        } },
					        { "name": "<font color=white>——</font>本科生学费收入", "value": "", "group": "2.其中本科教育事业收入（万元）", "field": "undergraTuition", "editor":  {
					    		"type":"numberbox",
					    		"options":{
					        	 	"min":  0,
					        	 	"precision": 2
					    		}
					        } },
					        { "name": "<font color=white>——</font>本科教改专项拨款", "group": "2.其中本科教育事业收入（万元）", "value": "", "field": "eduReformFund", "editor":  {
					    		"type":"numberbox",
					    		"options":{
					        	 	"min":  0,
					        	 	"precision": 2
					    		}
					        } },
					        { "name": "收入总计", "group": "3.其他教育事业收入（万元）", "value": "", "field": "sumOtherIncome", "editor": false},
					       	{ "name": "<font color=white>——</font>专科生生均拨款总额", "group": "3.其他教育事业收入（万元）", "value": "", "field": "juniorAllocateFund" ,"editor": {
					    		"type":"numberbox",
					    		"options":{
					        	 	"min":  0,
					        	 	"precision": 2
					    		}
					        } },
					        { "name": "<font color=white>——</font>经常性预算内教育事业费拨款：总数", "group": "3.其他教育事业收入（万元）", "value": "", "field": "otherAllocateFund", "editor": false },
					       	{ "name": "<font color=white>————————————————</font>国家", "group": "3.其他教育事业收入（万元）", "value": "", "field": "otherNationFund" ,"editor": {
					    		"type":"numberbox",
					    		"options":{
					        	 	"min":  0,
					        	 	"precision": 2
					    		}
					        } },
					       	{ "name": "<font color=white>————————————————</font>地方", "group": "3.其他教育事业收入（万元）", "value": "", "field": "otherLocalFund","editor": {
					    		"type":"numberbox",
					    		"options":{
					        	 	"min":  0,
					        	 	"precision": 2
					    		}
					        } },
					       	{ "name": "<font color=white>——</font>学费收入：总数", "group": "3.其他教育事业收入（万元）",  "value": "", "field": "otherTuition","editor": false},
					       	{ "name": "<font color=white>——————</font>　各类研究生", "group": "3.其他教育事业收入（万元）",  "value": "", "field": "graTuition", "editor": {
					    		"type":"numberbox",
					    		"options":{
					        	 	"min":  0,
					        	 	"precision": 2
					    		}
					        } },
					       	{ "name": "<font color=white>——————</font>　高职高专", "group": "3.其他教育事业收入（万元）",  "value": "", "field": "juniorTuition","editor": {
					    		"type":"numberbox",
					    		"options":{
					        	 	"min":  0,
					        	 	"precision": 2
					    		}
					        } },
					       	{ "name": "<font color=white>——————</font>　网络与继续教育", "group": "3.其他教育事业收入（万元）",  "value": "", "field": "netTeaTuition", "editor": {
					    		"type":"numberbox",
					    		"options":{
					        	 	"min":  0,
					        	 	"precision": 2
					    		}
					        } },
					       	{ "name": "<font color=white>——</font>社会捐赠收入（如为0，则表示无该年捐赠收入，请先填写相关表）", "group": "3.其他教育事业收入（万元）",  "value": "", "field": "donation","editor": false},
					       	{ "name": "<font color=white>——</font>其他教育事业收入", "group": "3.其他教育事业收入（万元）",  "value": "", "field": "otherIncome","editor": {
					    		"type":"numberbox",
					    		"options":{
					        	 	"min":  0,
					        	 	"precision": 2
					    		}
					        } }

					    ];
				    							
				$('#edit').propertygrid({
						//title : '本科教育经费收入情况',
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
							url: 'pages/T293/loadInfo?selectYear='+selectYear,
				    		async : false,
				    		dataType : "json",
				    		success : function(json) {
				    			if(typeof(json.data)!="undefined"){
				    				alert(json.data);
				    				$("#edit").propertygrid({title:'本科教育经费收入情况'});		
			    					$("#cancel").show();
			    					$("#save").show();
				    				$("#export").hide();
				    			}else{	
				    				if(json.checkState==WAITCHECK){
				    					$("#edit").propertygrid({title:'本科教育经费收入情况（<font color=red>待审核</font>）'});
				    					//document.getElementById("export").style.display ="none";
				    					$("#cancel").show();
				    					$("#save").show();
					    				$("#export").hide();
				    				}
				    				else if(json.checkState==PASSCHECK){
				    					$("#edit").propertygrid({title:'本科教育经费收入情况（<font color=red>审核通过</font>）'});					    				
				    					$("#cancel").hide();
				    					$("#save").hide();
				    					$("#export").show();
				    				}				    				
				    				else if(json.checkState==NOPASSCHECK){
				    					$("#edit").propertygrid({title:'本科教育经费收入情况（<font color=red>审核未通过</font>）'});	
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
							url: 'pages/T293/loadInfo?selectYear='+year,
				    		async : false,
				    		dataType : "json",
				    		success : function(json) {
				    			if(typeof(json.data)!="undefined"){
				    				alert(json.data);
				    				$("#edit").propertygrid({title:'本科教育经费收入情况'});		
			    					$("#cancel").show();
			    					$("#save").show();
				    				$("#export").hide();
				    			}else{	
				    				if(json.checkState==WAITCHECK){
				    					$("#edit").propertygrid({title:'本科教育经费收入情况（<font color=red>待审核</font>）'});
				    					//document.getElementById("export").style.display ="none";
				    					$("#cancel").show();
				    					$("#save").show();
					    				$("#export").hide();
				    				}
				    				else if(json.checkState==PASSCHECK){
				    					$("#edit").propertygrid({title:'本科教育经费收入情况（<font color=red>审核通过</font>）'});					    				
				    					$("#cancel").hide();
				    					$("#save").hide();
				    					$("#export").show();
				    				}				    				
				    				else if(json.checkState==NOPASSCHECK){
				    					$("#edit").propertygrid({title:'本科教育经费收入情况（<font color=red>审核未通过</font>）'});	
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
					if(s == '') {
						return false;
					}
					data = encodeURI(s)
				    var year = $("#cbYearContrast").combobox('getValue'); 
     				$.ajax( {
					    		type : "POST",
					    		contentType: "application/json;utf-8",
								url: 'pages/T293/save?data='+data+'&selectYear='+year+'&fields='+f,
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
			    		myMarquee('T293', CTypeThree)
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
			        var tableName = encodeURI('表2-9-3本科教育经费收入情况（计财处）');
			        var year = $("#cbYearContrast").combobox('getValue'); 
				    $('#exportForm').form('submit', {
				    	data : $('#exportForm').serialize(),
					    url : "pages/T293/dataExport?excelName="+tableName+'&selectYear='+year,
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