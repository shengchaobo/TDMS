	$(function(){  
				var selectYear = new Date().getFullYear();
				var rows = [
					        { "name": "35岁及以下人数",  "group": "1.年龄结构", "value": "", "field": "below35Num", "editor":  false},
					        { "name": "35岁及以下比例", "group": "1.年龄结构", "value": "", "field": "below35Ratio","editor":  false },
					        { "name": "36~45岁人数", "group": "1.年龄结构", "value": "", "field": "in36To45Num","editor":  false },
					        { "name": "36~45岁比例", "group": "1.年龄结构", "value": "", "field": "in36To45Ratio", "editor":  false },
					        { "name": "46~55岁人数", "group": "1.年龄结构", "value": "","field": "in46To55Num", "editor":  false},
					        { "name": "46~55岁比例",  "group": "1.年龄结构", "value": "", "field": "in46To55Ratio", "editor":  false},
					        { "name": "56岁及以上人数", "group": "1.年龄结构", "value": "", "field": "above56Num","editor":  false },
					        { "name": "56岁及以上比例", "group": "1.年龄结构", "value": "", "field": "above56Ratio","editor":  false },
				            
				            { "name": "正高级人数", "group": "2.职称结构", "value": "",  "field": "seniorNum","editor":  false},
					        { "name": "正高级比例", "group": "2.职称结构", "value": "",  "field": "seniorRatio","editor":  false},
					        { "name": "副高级人数",  "group": "2.职称结构", "value": "", "field": "subSenior", "editor":  false},
					        { "name": "副高级比例", "group": "2.职称结构", "value": "", "field": "subSeniorRatio","editor":  false },
					        { "name": "中级人数", "group": "2.职称结构", "value": "", "field": "middleNum","editor":  false },
					        { "name": "中级比例", "group": "2.职称结构", "value": "", "field": "middleRatio", "editor":  false },
					        { "name": "初级及以下人数", "group": "2.职称结构", "value": "","field": "primaryNum", "editor":  false},
					        { "name": "初级及以下比例",  "group": "2.职称结构", "value": "", "field": "primaryRatio", "editor":  false},

					        { "name": "行政单位人数", "group": "3.工作单位类别", "value": "",  "field": "adminNum","editor":  false},
					        { "name": "行政单位比例",  "group": "3.工作单位类别", "value": "", "field": "adminRatio", "editor":  false},
					        { "name": "科研单位人数", "group": "3.工作单位类别", "value": "", "field": "resNum","editor":  false },
					        { "name": "科研单位比例", "group": "3.工作单位类别", "value": "", "field": "resRatio","editor":  false },
				            { "name": "高等学校人数", "group": "3.工作单位类别", "value": "",  "field": "highNum","editor":  false},
					        { "name": "高等学校比例", "group": "3.工作单位类别", "value": "",  "field": "highRatio","editor":  false},				        
					        { "name": "其他事业单位人数",  "group": "3.工作单位类别", "value": "", "field": "businessNum", "editor":  false},
					        { "name": "其他事业单位比例", "group": "3.工作单位类别", "value": "", "field": "businessRatio","editor":  false },
					        { "name": "企业公司人数", "group": "3.工作单位类别", "value": "", "field": "companyNum","editor":  false },
					        { "name": "企业公司比例", "group": "3.工作单位类别", "value": "", "field": "companyRatio", "editor":  false },
					        { "name": "部队人数", "group": "3.工作单位类别", "value": "","field": "armyNum", "editor":  false},
					        { "name": "部队比例",  "group": "3.工作单位类别", "value": "", "field": "armyRatio", "editor":  false},
					        { "name": "其他单位人数", "group": "3.工作单位类别", "value": "","field": "otherNum", "editor":  false},
					        { "name": "其他单位比例",  "group": "3.工作单位类别", "value": "", "field": "otherRatio", "editor":  false},
					        
					        { "name": "博士、硕士导师人数", "group": "4.导师类别", "value": "", "field": "duTutorNum","editor":  false },
					        { "name": "博士、硕士导师比例", "group": "4.导师类别", "value": "", "field": "duTutorRatio","editor":  false },
					        { "name": "博士导师人数", "group": "4.导师类别", "value": "",  "field": "docTutorNum","editor":  false},
					        { "name": "博士导师比例",  "group": "4.导师类别", "value": "", "field": "docTutorRaio", "editor":  false},
					        { "name": "硕士导师人数", "group": "4.导师类别", "value": "", "field": "masterTutorNum","editor":  false },
					        { "name": "硕士导师比例", "group": "4.导师类别", "value": "", "field": "masterTutorRatio","editor":  false },
					        { "name": "其他人数", "group": "4.导师类别", "value": "",  "field": "notTutorNum","editor":  false},
					        { "name": "其他比例", "group": "4.导师类别", "value": "",  "field": "notTutorRatio","editor":  false}
					        


	                   ];
				    							
				$('#edit').propertygrid({
						title : '外聘教师队伍结构分析',
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
							url: 'pages/A413/loadInfo?selectYear='+selectYear,
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
			   	

		         
		       	function reloadgrid (year,flag)  { 
       				  $.ajax( {
				    		type : "POST",
				    		contentType: "application/json;utf-8",
							url: 'pages/A413/loadInfo?selectYear='+year,
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
		       	
				

						
				
			   //导出
			   $("#export").click(function(){
			        var tableName = encodeURI('A-4-1-3外聘教师队伍结构分析');
			        var year = new Date().getFullYear();
				    $('#exportForm').form('submit', {
				    	data : $('#exportForm').serialize(),
					    url : "pages/A413/dataExport?excelName="+tableName+'&selectYear='+year,
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