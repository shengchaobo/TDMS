	
$(function(){  
				var selectYear = $("#cbYearContrast").combobox('getValue'); 
				var rows = [
				        { "name": "1.学生发表学术论文（篇）", "group": "学生发表论文情况", "value": "",  "field": "schAddress","editor": "text" },
				        { "name": "2.学生发表作品数（篇、册）", "group": "学生发表作品情况", "value": "", "field": "schTel", "editor": "text"},
				        { "name": "3.学生获准专利数（项）", "value": "", "group": "学生获专利情况", "field": "schFax", "editor": "text"},
				        { "name": "4.1 英语四级考试累计通过率（%）", "value": "", "group": "英语等级考试", "field": "schFillerName", "editor": "text"},
				        { "name": "4.2 英语六级考试累计通过率（%）", "group": "英语等级考试", "value": "", "field": "schFillerTel", "editor": "text" },
				        { "name": "5.1 全国计算机等级考试累计通过率（%）", "group": "计算机等级考试", "value": "", "field": "schFillerEmail", "editor": "text" },
				        { "name": "5.2 江西省计算机等级考试累计通过率（%）", "group": "计算机等级考试", "value": "", "field": "schName", "editor": "text" },
				        { "name": "6.体质合格率（%）", "group": "体质合格情况", "value": "", "field": "schID", "editor": "text" },
				        { "name": "7.体质测试达标率（%）", "group": "体质测试达标情况", "value": "", "field": "schEnName", "editor": "text" },
				        { "name": "8.参加国际会议（人次）", "group": "参加国际会议情况", "value": "", "field": "schType", "editor": "text" },
				        { "name": "9.1 总数", "group": "学科竞赛获奖情况", "value": "", "field": "schQuality", "editor": "text" },
				        { "name": "9.2 其中：国际级", "group": "学科竞赛获奖情况", "value": "", "field": "schQuality", "editor": "text" },
				        { "name": "9.3 国家级", "group": "学科竞赛获奖情况", "value": "", "field": "schQuality", "editor": "text" },
				        { "name": "9.4 省部级", "group": "学科竞赛获奖情况", "value": "", "field": "schQuality", "editor": "text" },
				        { "name": "9.5 市级", "group": "学科竞赛获奖情况", "value": "", "field": "schQuality", "editor": "text" },
				        { "name": "9.6 校级", "group": "学科竞赛获奖情况", "value": "", "field": "schQuality", "editor": "text" },
				        
				        { "name": "10.1 总数", "group": "本科生创新活动、技能竞赛获奖情况", "value": "", "field": "schBuilder", "editor": "text" },
				        { "name": "10.2 其中：国际级", "group": "本科生创新活动、技能竞赛获奖情况", "value": "", "field": "schBuilder", "editor": "text" },
				        { "name": "10.3 国家级", "group": "本科生创新活动、技能竞赛获奖情况", "value": "", "field": "schBuilder", "editor": "text" },
				        { "name": "10.4 省部级", "group": "本科生创新活动、技能竞赛获奖情况", "value": "", "field": "schBuilder", "editor": "text" },
				        { "name": "10.5 市级", "group": "本科生创新活动、技能竞赛获奖情况", "value": "", "field": "schBuilder", "editor": "text" },
				        { "name": "10.6 校级", "group": "本科生创新活动、技能竞赛获奖情况", "value": "", "field": "schBuilder", "editor": "text" },
				        
				        { "name": "11.1 总数", "group": "文艺、体育竞赛获奖情况", "value": "", "field": "schBuilder", "editor": "text" },
				        { "name": "11.2 其中：国际级", "group": "文艺、体育竞赛获奖情况", "value": "", "field": "schBuilder", "editor": "text" },
				        { "name": "11.3 国家级", "group": "文艺、体育竞赛获奖情况", "value": "", "field": "schBuilder", "editor": "text" },
				        { "name": "11.4 省部级", "group": "文艺、体育竞赛获奖情况", "value": "", "field": "schBuilder", "editor": "text" },
				        { "name": "11.5 市级", "group": "文艺、体育竞赛获奖情况", "value": "", "field": "schBuilder", "editor": "text" },
				        { "name": "11.6 校级", "group": "文艺、体育竞赛获奖情况", "value": "", "field": "schBuilder", "editor": "text" }
				        
				    ];
				    							
				$('#edit').propertygrid({
						title : '本科生学习成果统计',
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
							url: 'pages/S65/loadInfo?selectYear='+selectYear,
				    		async : false,
				    		dataType : "json",
				    		success : function(json) {
			                    var i = 0;
			                    while(i<rows.length){
			                    	rows[i].value = eval('json.'+rows[i].field);	
			                    	i= i+1;
			                    }														
							},
			                error: function(XMLResponse) {
//								alert(XMLResponse.responseText);
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
							url: 'pages/S65/loadInfo?selectYear='+year,
				    		async : false,
				    		dataType : "json",
				    		success : function(json) {
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
								url: 'pages/S65/save?data='+data+'&selectYear='+year+'&fields='+f,
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
			        var tableName = encodeURI('表6-5本科生学习成果统计情况');
			        var year = $("#cbYearContrast").combobox('getValue'); 
				    $('#exportForm').form('submit', {
				    	data : $('#exportForm').serialize(),
					    url : "pages/S65/dataExport?excelName="+tableName+'&selectYear='+year,
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