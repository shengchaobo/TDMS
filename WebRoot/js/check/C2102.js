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
						  		  if (typeof (json.data) != "undefined") {
											alert(json.data);
											$("#edit").propertygrid( {
													title : '职业及创业教育课程'
											});
											$("#renopass").hide();
											$("#pass").hide();
											$("#nopass").hide();
								   } 
						  		  else {
												//alert(json.checkState);
										if (json.checkState == WAITCHECK) {
											$("#edit").propertygrid( {
												title : '职业及创业教育课程（<font color=red>待审核</font>）'
											});
											//document.getElementById("export").style.display ="none";
											$("#pass").show();
											$("#nopass").show();
											$("#renopass").hide();
									} else if (json.checkState == PASSCHECK) {
										$("#edit").propertygrid( {
											title : '职业及创业教育课程（<font color=red>已审核通过</font>）'
										});
										$("#pass").hide();
										$("#nopass").hide();
										$("#renopass").show();
									} else {
										$("#edit").propertygrid( {
											title : '职业及创业教育课程（<font color=red>已审核未通过</font>）'
										});
										$("#pass").hide();
										$("#nopass").hide();
										$("#renopass").hide();
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
							  	if (typeof (json.data) != "undefined") {
											alert(json.data);
											$("#edit").propertygrid( {
													title : '职业及创业教育课程'
											});
											$("#renopass").hide();
											$("#pass").hide();
											$("#nopass").hide();
								   } 
						  		  else {
												//alert(json.checkState);
										if (json.checkState == WAITCHECK) {
											$("#edit").propertygrid( {
												title : '职业及创业教育课程（<font color=red>待审核</font>）'
											});
											//document.getElementById("export").style.display ="none";
											$("#pass").show();
											$("#nopass").show();
											$("#renopass").hide();
									} else if (json.checkState == PASSCHECK) {
										$("#edit").propertygrid( {
											title : '职业及创业教育课程（<font color=red>已审核通过</font>）'
										});
										$("#pass").hide();
										$("#nopass").hide();
										$("#renopass").show();
									} else {
										$("#edit").propertygrid( {
											title : '职业及创业教育课程（<font color=red>已审核未通过</font>）'
										});
										$("#pass").hide();
										$("#nopass").hide();
										$("#renopass").hide();
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

	//审核通过
	$("#pass").click(function() {
		var year = $("#cbYearContrast").combobox('getValue');
		var checkNum = PASSCHECK;
		//alert(checkNum);
			$.ajax( {
				type : "POST",
				url : "pages/T2102/updateCheck?selectYear=" + year + "&checkNum="+ PASSCHECK,
				async : "true",
				dataType : "text",
				success : function(result) {
					result = eval("(" + result + ")");
					if (!result.state) {
						$.messager.show( {
							title : 'Error',
							msg : result.data
						});
					} else {
						reloadgrid(year, true);
						$('#edit').propertygrid('loadData', rows);
					}
				}
			});
		});

})

function reloadgrid (year,flag)  { 
	  $.ajax( {
    		type : "POST",
    		contentType: "application/json;utf-8",
			url: 'pages/T2102/loadInfo?selectYear='+year,
    		async : false,
    		dataType : "json",
    		success : function(json) {
			  	  if (typeof (json.data) != "undefined") {
							alert(json.data);
							$("#edit").propertygrid( {
									title : '职业及创业教育课程'
							});
							$("#renopass").hide();
							$("#pass").hide();
							$("#nopass").hide();
				   } 
				  else {
								//alert(json.checkState);
						if (json.checkState == WAITCHECK) {
							$("#edit").propertygrid( {
								title : '职业及创业教育课程（<font color=red>待审核</font>）'
							});
							//document.getElementById("export").style.display ="none";
							$("#pass").show();
							$("#nopass").show();
							$("#renopass").hide();
					} else if (json.checkState == PASSCHECK) {
						$("#edit").propertygrid( {
							title : '职业及创业教育课程（<font color=red>已审核通过</font>）'
						});
						$("#pass").hide();
						$("#nopass").hide();
						$("#renopass").show();
					} else {
						$("#edit").propertygrid( {
							title : '职业及创业教育课程（<font color=red>已审核未通过</font>）'
						});
						$("#pass").hide();
						$("#nopass").hide();
						$("#renopass").hide();
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

//审核不通过
function noPassCheck(year) {
	var checkNum = NOPASSCHECK;
	//alert(checkNum);
	$.ajax( {
		type : "POST",
		url : "pages/T2102/updateCheck?selectYear=" + year + "&checkNum="
				+ NOPASSCHECK,
		async : "true",
		dataType : "text",
		success : function(result) {
			result = eval("(" + result + ")");
			if (!result.state) {
				$.messager.show( {
					title : 'Error',
					msg : result.data
				});
			} else {
				reloadgrid(year, true);
				$('#edit').propertygrid('loadData', rows);
			}
		}
	});
}