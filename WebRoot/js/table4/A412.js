	$(function(){  
				var selectYear = $("#cbYearContrast").combobox('getValue'); 
				var rows = [
				            { "name": "正高级人数", "group": "1.职称结构", "value": "",  "field": "seniorNum","editor":  false},
					        { "name": "正高级比例", "group": "1.职称结构", "value": "",  "field": "seniorRatio","editor":  false,"formatter":"toPercent"},
					        { "name": "副高级人数",  "group": "1.职称结构", "value": "", "field": "subSenior", "editor":  false},
					        { "name": "副高级比例", "group": "1.职称结构", "value": "", "field": "subSeniorRatio","editor":  false },
					        { "name": "中级人数", "group": "1.职称结构", "value": "", "field": "middleNum","editor":  false },
					        { "name": "中级比例", "group": "1.职称结构", "value": "", "field": "middleRatio", "editor":  false },
					        { "name": "初级及以下人数", "group": "1.职称结构", "value": "","field": "primaryNum", "editor":  false},
					        { "name": "初级及以下比例",  "group": "1.职称结构", "value": "", "field": "primaryRatio", "editor":  false},
					        
					        
					        { "name": "博士人数", "group": "2.学位结构", "value": "", "field": "doctorNum","editor":  false },
					        { "name": "博士比例", "group": "2.学位结构", "value": "", "field": "doctorRatio","editor":  false },
					        { "name": "硕士人数", "group": "2.学位结构", "value": "",  "field": "masterNum","editor":  false},
					        { "name": "硕士比例",  "group": "2.学位结构", "value": "", "field": "masterRatio", "editor":  false},
					        { "name": "学士人数", "group": "2.学位结构", "value": "", "field": "bachelorNum","editor":  false },
					        { "name": "学士比例", "group": "2.学位结构", "value": "", "field": "bachelorRatio","editor":  false },
					        { "name": "无学位人数", "group": "2.学位结构", "value": "",  "field": "notDegreeNum","editor":  false},
					        { "name": "无学位比例", "group": "2.学位结构", "value": "",  "field": "notDegreeRatio","editor":  false},
					        
					        
					        { "name": "35岁及以下人数",  "group": "3.年龄结构", "value": "", "field": "below35Num", "editor":  false},
					        { "name": "35岁及以下比例", "group": "3.年龄结构", "value": "", "field": "below35Ratio","editor":  false },
					        { "name": "36~45岁人数", "group": "3.年龄结构", "value": "", "field": "in36To45Num","editor":  false },
					        { "name": "36~45岁比例", "group": "3.年龄结构", "value": "", "field": "in36To45Ratio", "editor":  false },
					        { "name": "46~55岁人数", "group": "3.年龄结构", "value": "","field": "in46To55Num", "editor":  false},
					        { "name": "46~55岁比例",  "group": "3.年龄结构", "value": "", "field": "in46To55Ratio", "editor":  false},
					        { "name": "56岁及以上人数", "group": "3.年龄结构", "value": "", "field": "above56Num","editor":  false },
					        { "name": "56岁及以上比例", "group": "3.年龄结构", "value": "", "field": "above56Ratio","editor":  false },
					        
					        
					        { "name": "本校人数", "group": "4.学缘结构", "value": "",  "field": "thisSchNum","editor":  false},
					        { "name": "本校比例",  "group": "4.学缘结构", "value": "", "field": "thisSchRatio", "editor":  false},
					        { "name": "外校（境内）人数", "group": "4.学缘结构", "value": "", "field": "outSchInNum","editor":  false },
					        { "name": "外校（境内）比例", "group": "4.学缘结构", "value": "", "field": "outSchInRatio","editor":  false },
				            { "name": "外校（境外）人数", "group": "4.学缘结构", "value": "",  "field": "outSchOutNum","editor":  false},
					        { "name": "外校（境外）比例", "group": "4.学缘结构", "value": "",  "field": "outSchOutRatio","editor":  false},
					        
					        
					        { "name": "双师型教师人数",  "group": "5.双师型", "value": "", "field": "duTeaNum", "editor":  false},
					        { "name": "双师型教师比例", "group": "5.双师型", "value": "", "field": "duTeaRatio","editor":  false },
					        { "name": "具有行业背景人数", "group": "5.双师型", "value": "", "field": "industryNum","editor":  false },
					        { "name": "具有行业背景比例", "group": "5.双师型", "value": "", "field": "industryRatio", "editor":  false },
					        { "name": "具有工程背景人数", "group": "5.双师型", "value": "","field": "engineerNum", "editor":  false},
					        { "name": "具有工程背景比例",  "group": "5.双师型", "value": "", "field": "engineerRatio", "editor":  false}

	                   ];
				    							
				$('#edit').propertygrid({
						title : '专任教师队伍结构分析',
						toolbar : "#toolbar",//在添加 增添、删除、修改操作的按钮要用到这个
				        width: '60%',
				        height: 'auto',
				        showGroup: true,
				        scrollbarSize: 0,
				        columns: [[
				                { field: 'name', title: '项目', width: 100, resizable: true },
				                { field: 'value', title: '内容', width: 100, resizable: false ,
				                formatter: function(value,rowData,rowIndex){
				                	if((rowIndex+1)%2==0){
				                		return value + "%";
				                	}else{
				                		return value;
				                	}
				                }
				                }
				        ]]
				 });
				 				 
				  $.ajax( {
				    		type : "POST",
				    		contentType: "application/json;utf-8",
							url: 'pages/A412/loadInfo?selectYear='+selectYear,
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
							url: 'pages/A412/loadInfo?selectYear='+year,
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
		       	
				
				function toPercent(data){
				    var strData = parseFloat(data)*100;
				    strData = Math.round(strData);
				    strData/=100.00;
				    var ret = strData.toString()+"%";
				    return ret;
				}

			
						
				
			   //导出
			   $("#export").click(function(){
			        var tableName = encodeURI('A-4-1-2专任教师队伍结构分析');
			        var year = $("#cbYearContrast").combobox('getValue'); 
				    $('#exportForm').form('submit', {
				    	data : $('#exportForm').serialize(),
					    url : "pages/A412/dataExport?excelName="+tableName+'&selectYear='+year,
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