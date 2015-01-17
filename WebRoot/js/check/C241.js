	$(function(){  
				var selectYear = $("#cbYearContrast").combobox('getValue'); 
				var rows = [
				        { "name": "总量（册）", "group": "1.纸质图书", "value": "",  "field": "paperBookNum","editor": "numberbox" },
				        { "name": "数量（份）", "group": "2.纸质期刊", "value": "", "field": "paperJonalNum", "editor": "numberbox" },
				        { "name": "种类（种）", "value": "", "group": "2.纸质期刊", "field": "paperJonalType", "editor": "numberbox" },
				        { "name": "数量（种）", "value": "", "group": "3.电子图书", "field": "digBookType", "editor": "numberbox" },
				        { "name": "其中：中文数量（种）", "group": "3.电子图书", "value": "", "field": "chiDigBookType", "editor": "numberbox" },
				        { "name": "其中：外文数量（种）", "group": "3.电子图书", "editor": "numberbox", "value": "", "field": "forDigBookType" },
				        { "name": "电子图书总量（GB）", "group": "3.电子图书", "value": "", "field": "digBookSize", "editor": "numberbox" },
				       	{ "name": "期刊种类（种）", "group": "4.电子期刊", "editor": "numberbox", "value": "", "field": "digJonalType" },
				       	{ "name": "数量（个）", "group": "5.数据库", "editor": "numberbox", "value": "", "field": "databaseNum" },
				       	{ "name": "数量（个）", "group": "6.其他数据库", "editor": "numberbox", "value": "", "field": "otherDatabase" }
				    ];
				    							
				$('#edit').propertygrid({
						//title : '图书数量',
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
							url: 'pages/T241/loadInfo?selectYear='+selectYear,
				    		async : false,
				    		dataType : "json",
				    		success : function(json) {
						  		  if (typeof (json.data) != "undefined") {
											alert(json.data);
											$("#edit").propertygrid( {
													title : '图书数量'
											});
								   } 
						  		  else {
												//alert(json.checkState);
										if (json.checkState == WAITCHECK) {
											$("#edit").propertygrid( {
												title : '图书数量（<font color=red>待审核</font>）'
											});
											//document.getElementById("export").style.display ="none";
										$("#pass").show();
										$("#nopass").show();
									} else if (json.checkState == PASSCHECK) {
										$("#edit").propertygrid( {
											title : '图书数量（<font color=red>已审核通过</font>）'
										});
										$("#pass").hide();
										$("#nopass").hide();
									} else {
										$("#edit").propertygrid( {
											title : '图书数量（<font color=red>已审核未通过</font>）'
										});
										$("#pass").hide();
										$("#nopass").hide();
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
							url: 'pages/T241/loadInfo?selectYear='+year,
				    		async : false,
				    		dataType : "json",
				    		success : function(json) {
					  				if (typeof (json.data) != "undefined") {
											alert(json.data);
											$("#edit").propertygrid( {
													title : '图书数量'
											});
								   } else {
												//alert(json.checkState);
										if (json.checkState == WAITCHECK) {
											$("#edit").propertygrid( {
												title : '图书数量（<font color=red>待审核</font>）'
											});
											//document.getElementById("export").style.display ="none";
										$("#pass").show();
										$("#nopass").show();
									} else if (json.checkState == PASSCHECK) {
										$("#edit").propertygrid( {
											title : '图书数量（<font color=red>已审核通过</font>）'
										});
										$("#pass").hide();
										$("#nopass").hide();
									} else {
										$("#edit").propertygrid( {
											title : '图书数量（<font color=red>已审核未通过</font>）'
										});
										$("#pass").hide();
										$("#nopass").hide();
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
				url : "pages/T241/updateCheck?selectYear=" + year + "&checkNum="+ PASSCHECK,
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
			url: 'pages/T241/loadInfo?selectYear='+year,
    		async : false,
    		dataType : "json",
    		success : function(json) {
					if (typeof (json.data) != "undefined") {
							alert(json.data);
							$("#edit").propertygrid( {
									title : '图书数量'
							});
					} else {
								//alert(json.checkState);
						if (json.checkState == WAITCHECK) {
							$("#edit").propertygrid( {
								title : '图书数量（<font color=red>待审核</font>）'
							});
							//document.getElementById("export").style.display ="none";
						$("#pass").show();
						$("#nopass").show();
					} else if (json.checkState == PASSCHECK) {
						$("#edit").propertygrid( {
							title : '图书数量（<font color=red>已审核通过</font>）'
						});
						$("#pass").hide();
						$("#nopass").hide();
					} else {
						$("#edit").propertygrid( {
							title : '图书数量（<font color=red>已审核未通过</font>）'
						});
						$("#pass").hide();
						$("#nopass").hide();
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
		url : "pages/T241/updateCheck?selectYear=" + year + "&checkNum="
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