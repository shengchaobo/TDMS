	$(function(){  
				var selectYear = $("#cbYearContrast").combobox('getValue'); 
				var rows = [
				        { "name": "1.校园网主干带宽（Mbps）", "group": "", "value": "",  "field": "mainBw","editor":  {
				    		"type":"numberbox",
				    		"options":{
				        	 	"min":  0,
				        	 	"precision": 2
				    		}
				        } },
				        { "name": "2.校园网出口带宽（Mbps）", "group": "", "value": "", "field": "exitBw", "editor":  {
				    		"type":"numberbox",
				    		"options":{
				        	 	"min":  0,
				        	 	"precision": 2
				    		}
				        } },
				        { "name": "3.网络接入信息点数量（个)", "value": "", "group": "", "field": "accessPoinum", "editor": "numberbox"},
				        { "name": "教室数（间）", "value": "", "group": "4.通达情况", "field": "classrmNum", "editor": "numberbox"},
				        { "name": "学生宿舍数（间）", "group": "4.通达情况", "value": "", "field": "dormiNum", "editor": "numberbox"},
				        { "name": "教学、科研与管理办公室数（间）", "group": "4.通达情况", "value": "", "field": "officeNum", "editor": "numberbox"},
				       	{ "name": "总量(TB)", "group": "5.网络存储空间", "value": "", "field": "sumMemSpace" ,"editor": {
				    		"type":"numberbox",
				    		"options":{
				        	 	"min":  0,
				        	 	"precision": 2
				    		}
				        } },
				       	{ "name": "教职工平均存储空间（MB）", "group": "5.网络存储空间", "value": "", "field": "avgTeaMemSpace","editor": {
				    		"type":"numberbox",
				    		"options":{
				        	 	"min":  0,
				        	 	"precision": 2
				    		}
				        } },
				       	{ "name": "学生平均存储空间（MB）", "group": "5.网络存储空间",  "value": "", "field": "avgStuMemSpace", "editor": {
				    		"type":"numberbox",
				    		"options":{
				        	 	"min":  0,
				        	 	"precision": 2
				    		}
				        } },
				       	{ "name": "链接地址", "group": "6.网络教学平台",  "value": "", "field": "webTeahingUrl", "editor": "text" },
				        { "name": "链接地址", "group": "7.教学管理信息系统",  "value": "", "field": "teaManageUrl", "editor": "text" }
				    ];
				    							
				$('#edit').propertygrid({
						//title : '校园网',
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
							url: 'pages/T27/loadInfo?selectYear='+selectYear,
				    		async : false,
				    		dataType : "json",
				    		success : function(json) {
						  		  if (typeof (json.data) != "undefined") {
											alert(json.data);
											$("#edit").propertygrid( {
													title : '校园网'
											});
											$("#renopass").hide();
											$("#pass").hide();
											$("#nopass").hide();
								   } 
						  		  else {
												//alert(json.checkState);
										if (json.checkState == WAITCHECK) {
											$("#edit").propertygrid( {
												title : '校园网（<font color=red>待审核</font>）'
											});
											//document.getElementById("export").style.display ="none";
											$("#pass").show();
											$("#nopass").show();
											$("#renopass").hide();
									} else if (json.checkState == PASSCHECK) {
										$("#edit").propertygrid( {
											title : '校园网（<font color=red>已审核通过</font>）'
										});
										$("#pass").hide();
										$("#nopass").hide();
										$("#renopass").show();
									} else {
										$("#edit").propertygrid( {
											title : '校园网（<font color=red>已审核未通过</font>）'
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
							url: 'pages/T27/loadInfo?selectYear='+year,
				    		async : false,
				    		dataType : "json",
				    		success : function(json) {
							  	  if (typeof (json.data) != "undefined") {
											alert(json.data);
											$("#edit").propertygrid( {
													title : '校园网'
											});
											$("#renopass").hide();
											$("#pass").hide();
											$("#nopass").hide();
								   } 
						  		  else {
												//alert(json.checkState);
										if (json.checkState == WAITCHECK) {
											$("#edit").propertygrid( {
												title : '校园网（<font color=red>待审核</font>）'
											});
											//document.getElementById("export").style.display ="none";
											$("#pass").show();
											$("#nopass").show();
											$("#renopass").hide();
									} else if (json.checkState == PASSCHECK) {
										$("#edit").propertygrid( {
											title : '校园网（<font color=red>已审核通过</font>）'
										});
										$("#pass").hide();
										$("#nopass").hide();
										$("#renopass").show();
									} else {
										$("#edit").propertygrid( {
											title : '校园网（<font color=red>已审核未通过</font>）'
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
				url : "pages/T27/updateCheck?selectYear=" + year + "&checkNum="+ PASSCHECK,
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
			url: 'pages/T27/loadInfo?selectYear='+year,
    		async : false,
    		dataType : "json",
    		success : function(json) {
			  	  if (typeof (json.data) != "undefined") {
							alert(json.data);
							$("#edit").propertygrid( {
									title : '校园网'
							});
							$("#renopass").hide();
							$("#pass").hide();
							$("#nopass").hide();
				   } 
				  else {
								//alert(json.checkState);
						if (json.checkState == WAITCHECK) {
							$("#edit").propertygrid( {
								title : '校园网（<font color=red>待审核</font>）'
							});
							//document.getElementById("export").style.display ="none";
							$("#pass").show();
							$("#nopass").show();
							$("#renopass").hide();
					} else if (json.checkState == PASSCHECK) {
						$("#edit").propertygrid( {
							title : '校园网（<font color=red>已审核通过</font>）'
						});
						$("#pass").hide();
						$("#nopass").hide();
						$("#renopass").show();
					} else {
						$("#edit").propertygrid( {
							title : '校园网（<font color=red>已审核未通过</font>）'
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
		url : "pages/T27/updateCheck?selectYear=" + year + "&checkNum="
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