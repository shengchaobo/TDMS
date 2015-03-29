	$(function(){  
				var selectYear = $("#cbYearContrast").combobox('getValue'); 
				var rows = [
				        { "name": "本科在校生数（人）", "group": "学生人数", "value": "",  "field": "undergraNum","editor": false},
				        { "name": "全日制在校生数（人）", "group": "学生人数", "value": "",  "field": "fulltimeStuNum","editor": false},
				        { "name": "本科生占全日制在校生总数的比例（%）", "group": "学生人数", "value": "",  "field": "undergraRatio","editor": false},
				        { "name": "折合在校生数（人）", "group": "学生人数", "value": "",  "field": "totalStuNum","editor": false},
				        { "name": "转专业:转入人数（人）", "group": "学生人数", "value": "",  "field": "inNum","editor": false},
				        { "name": "转专业:转出人数（人）", "group": "学生人数", "value": "",  "field": "outNum","editor": false},
				        { "name": "转专业学生占本科在校生比例（%）", "group": "学生人数", "value": "",  "field": "inOutRatio","editor": false},
				        { "name": "辅修专业在读学生数（人）", "group": "学生人数", "value": "",  "field": "minorNum","editor": false},
				        { "name": "辅修专业在读学生占本科生在校生比例（%）", "group": "学生人数", "value": "",  "field": "minorNumRatio","editor": false},

				        { "name": "应届本科生毕业率（%）", "group": "学生毕业", "value": "", "field": "graduRatio", "editor":  false},
				        { "name": "应届本科生学位授予率（%）", "group": "学生毕业", "value": "", "field": "degreeRatio", "editor":  false},
				        { "name": "应届本科生初次就业率（%）", "group": "学生毕业", "value": "", "field": "stuEmployRatio", "editor":  false},

				        { "name": "专任教师总数（人）", "group": "教师人数", "value": "", "field": "fullTimeTeachNum", "editor":  false},
				        { "name": "具有研究生学位教师占专任教师的比例（%）", "group": "教师人数", "value": "", "field": "graduDegreeRatio", "editor":  false},
				        { "name": "具有高级职务教师占专任教师的比例（%）", "group": "教师人数", "value": "", "field": "adminLevelRatio", "editor":  false},
				        { "name": "外聘教师数（人）", "group": "教师人数", "value": "", "field": "outHireTeaNum", "editor":  false},
				        { "name": "教师总数（人）", "group": "教师人数", "value": "", "field": "teacherNum", "editor":  false},
				        { "name": "生师比", "group": "教师人数", "value": "", "field": "stuTeaRatio", "editor":  false},

				        { "name": "当年本科招生专业总数（个）", "group": "课程情况", "value": "", "field": "totalFieldNum", "editor":  false},
				        { "name": "全校开设课程总门数（门）", "group": "课程情况", "value": "", "field": "totalScoreNum", "editor":  false},
				        { "name": "实践教学学分占总学分比例（%）", "group": "课程情况", "value": "", "field": "praRatio", "editor":  false},
				        { "name": "选修课学分占总学分比例（%）", "group": "课程情况", "value": "", "field": "optionRatio", "editor":  false},
				        { "name": "主讲本科课程的教授（含副教授）占教授（含副教授）总数的比例（%）", "group": "课程情况", "value": "", "field": "profNumRatio", "editor":  false},
				        { "name": "教授（含副教授）授本科课程占课程总门次数的比例（%）", "group": "课程情况", "value": "", "field": "profCourseRatio", "editor":  false},

				        { "name": "生均占地面积（平方米）", "value": "", "group": "教学条件", "field": "areaPerStu", "editor":  false},
				        { "name": "生均占地面积（平方米）", "value": "", "group": "教学条件", "field": "housePerStu", "editor":  false},
				        { "name": "其中生均实验室面积（平方米）", "value": "", "group": "教学条件", "field": "labPerStu", "editor":  false},
				        { "name": "其中生均宿舍面积（平方米）", "value": "", "group": "教学条件", "field": "dormPerStu", "editor":  false},
				        { "name": "生均教学科研仪器设备值（元/生）", "value": "", "group": "教学条件", "field": "equPerStu", "editor":  false},
				        { "name": "新增教学科研仪器设备所占比例（%）", "value": "", "group": "教学条件", "field": "newEquRatio", "editor":  false},
				        { "name": "生均图书（册/生）", "value": "", "group": "教学条件", "field": "booksPerStu", "editor":  false},
				        { "name": "生均年进书量（册/生）", "value": "", "group": "教学条件", "field": "inbooksPerStu", "editor":  false},
				        { "name": "百名学生配教学计算机台数(台)", "value": "", "group": "教学条件", "field": "comPerStu", "editor":  false},
				        { "name": "百名学生配多媒体教室和语音实验室座位数(个)", "value": "", "group": "教学条件", "field": "mediaPerStu", "editor":  false},

				        { "name": "生均本科教学日常运行支出（元/生）", "value": "", "group": "教学经费", "field": "teachFeePerStu", "editor":  false },
				        { "name": "生均本科实验经费（元/生", "value": "", "group": "教学经费", "field": "labFeePerStu", "editor":  false },
				        { "name": "生均本科实习经费（元/生）", "value": "", "group": "教学经费", "field": "practiceFeePerStu", "editor":  false }
				];
				    							
				$('#edit').propertygrid({
						title : '办学条件监测指标',
						toolbar : "#toolbar",//在添加 增添、删除、修改操作的按钮要用到这个
				        width: '80%',
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
							url: 'loadCondiInfo?selectYear='+selectYear,
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
			                      alert("该表数据不全，请填写相关数据后再进行统计!!!");
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
							url: 'loadCondiInfo?selectYear='+year,
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
			                    	alert("该表数据不全，请填写相关数据后再进行统计!!!");
			                    }			                    
			                }
		    		})
			   }	
				
			   //导出
			   $("#export").click(function(){
			        var tableName = encodeURI('办学条件监测指标');
			        var year = $("#cbYearContrast").combobox('getValue'); 
				    $('#exportForm').form('submit', {
				    	data : $('#exportForm').serialize(),
					    url : "condiDataExport?excelName="+tableName+'&selectYear='+year,
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