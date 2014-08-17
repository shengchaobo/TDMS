	$(function(){  
				var selectYear = $("#cbYearContrast").combobox('getValue'); 
				var rows = [
				            {"name": "合计", "group": "", "value": "",  "field": "sumTalent","editor": false },
				        	{"name": "中国科学院院士", "group": "", "value": "",  "field": "scienceTalent","editor": false },
				        	{"name": "中国工程院院士", "group": "", "value": "",  "field": "engneerTalent","editor": false },
				        	{"name": "引进海外高层次人才“千人计划”入选者", "group": "", "value": "",  "field": "overseasTalent","editor": false },
				        	{"name": "长江学者特聘教授", "group": "", "value": "",  "field": "yangtzeTalent","editor": false },
				        	{"name": "国家杰出青年科学基金资助者", "group": "", "value": "",  "field": "youthTalent","editor": false },
				        	{"name": "省部级突出贡献专家", "group": "", "value": "",  "field": "expertTalent","editor": false },	
				        	{"name": "新世纪优秀人才", "group": "", "value": "",  "field": "excellentTalent","editor": false },
				        	{"name": "教育部高校青年教师获奖者", "group": "", "value": "",  "field": "youthTeaTalent","editor": false },
				        	{"name": "省级高层次人才", "group": "", "value": "",  "field": "highLevelTalent","editor": false },
				        	{"name": "青年“千人计划”入选者", "group": "", "value": "",  "field": "youthOverseas","editor": false }

	                   ];
				    							
				$('#edit').propertygrid({
						title : '高层次人才统计',
						toolbar : "#toolbar",//在添加 增添、删除、修改操作的按钮要用到这个
				        width: '60%',
				        height: 'auto',
				        showGroup: true,
				        scrollbarSize: 0,
				        columns: [[
				                { field: 'name', title: '项目', width: 100, resizable: true },
				                { field: 'value', title: '内容', width: 100, resizable: false ,
				                }
				        ]]
				 });
				 				 
				  $.ajax( {
				    		type : "POST",
				    		contentType: "application/json;utf-8",
							url: 'pages/S443/loadInfo?selectYear='+selectYear,
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
							url: 'pages/S443/loadInfo?selectYear='+year,
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
			        var tableName = encodeURI('S-4-4-3高层次人才统计');
			        var year = $("#cbYearContrast").combobox('getValue'); 
				    $('#exportForm').form('submit', {
				    	data : $('#exportForm').serialize(),
					    url : "pages/S443/dataExport?excelName="+tableName+'&selectYear='+year,
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