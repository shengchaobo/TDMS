$(function() {
	var selectYear = $("#cbYearContrast").combobox('getValue'); 
	var rows = [
	        { "name": "面积‌‌（平方米）", "group": "1.行政办公用房", "value": "",  "field": "admOfficeArea","editor":  {
	    		"type":"numberbox",
	    		"options":{
	        	 	"min":  0,
	        	 	"precision": 2
	    		}
	        } },
	        { "name": "数量（个）", "group": "1.行政办公用房", "value": "/", "field": "admOfficeNum", "editor": false },
	        { "name": "面积‌‌（平方米）", "value": "", "group": "2.图书馆", "field": "libArea", "editor":  {
	    		"type":"numberbox",
	    		"options":{
	        	 	"min":  0,
	        	 	"precision": 2
	    		}
	        } },
	        { "name": "数量（个）", "value": "", "group": "2.图书馆", "field": "libNum", "editor": "numberbox" },
	        { "name": "面积‌‌（平方米）", "group": "3.图书馆阅览室座位数", "value": "/", "field": "libRoomArea", "editor": false },
	        { "name": "数量（个）", "group": "3.图书馆阅览室座位数", "editor": "numberbox", "value": "", "field": "libRoomSitNum" },
	        { "name": "面积‌‌（平方米）", "group": "4.博物馆", "value": "", "field": "museumArea", "editor":  {
	    		"type":"numberbox",
	    		"options":{
	        	 	"min":  0,
	        	 	"precision": 2
	    		}
	        } },
	       	{ "name": "数量（个）", "group": "4.博物馆", "editor": "numberbox", "value": "", "field": "museumNum" },
	       	{ "name": "面积‌‌（平方米）", "group": "5.校史馆",  "value": "", "field": "schHisHallArea","editor": {
	    		"type":"numberbox",
	    		"options":{
	        	 	"min":  0,
	        	 	"precision": 2
	    		}
	        } },
	       	{ "name": "数量（个）", "group": "5.校史馆", "editor": "numberbox", "value": "", "field": "schHisHallNum" },
	       	{ "name": "面积‌‌（平方米）", "group": "6.体育馆",  "value": "", "field": "gymArea","editor": {
	    		"type":"numberbox",
	    		"options":{
	        	 	"min":  0,
	        	 	"precision": 2
	    		}
	        } },
	       	{ "name": "数量（个）", "group": "6.体育馆", "editor": "numberbox", "value": "", "field": "gymNum" },
	        { "name": "面积‌‌（平方米）", "group": "7.运动场", "value": "",  "field": "sportArea","editor": {
	    		"type":"numberbox",
	    		"options":{
	        	 	"min":  0,
	        	 	"precision": 2
	    		}
	        } },
	        { "name": "数量（个）", "group": "7.运动场", "value": "", "field": "sportNum", "editor": "numberbox" },
	        { "name": "面积‌‌（平方米）", "value": "", "group": "8.学生活动中心", "field": "stuCenterArea","editor": {
	    		"type":"numberbox",
	    		"options":{
	        	 	"min":  0,
	        	 	"precision": 2
	    		}
	        } },
	        { "name": "数量（个）", "value": "", "group": "8.学生活动中心", "field": "stuCenterNum", "editor": "numberbox" },
	        { "name": "面积‌‌（平方米）", "group": "9.会堂", "value": "", "field": "hallArea","editor": {
	    		"type":"numberbox",
	    		"options":{
	        	 	"min":  0,
	        	 	"precision": 2
	    		}
	        } },
	        { "name": "其中：独立使用", "group": "9.会堂", "editor": "numberbox", "value": "", "field": "hallNum" },
	        { "name": "面积‌‌（平方米）", "group": "10.学生食堂", "value": "", "field": "stuCanteenArea","editor": {
	    		"type":"numberbox",
	    		"options":{
	        	 	"min":  0,
	        	 	"precision": 2
	    		}
	        } },
	       	{ "name": "数量（个）", "group": "10.学生食堂", "editor": "numberbox", "value": "", "field": "stuCanteenNum" },
	       	{ "name": "面积‌‌（平方米）", "group": "11.学生宿舍", "value": "", "field": "stuDormiArea","editor": {
	    		"type":"numberbox",
	    		"options":{
	        	 	"min":  0,
	        	 	"precision": 2
	    		}
	        } },
	       	{ "name": "数量（个）", "group": "11.学生宿舍", "editor": "numberbox", "value": "", "field": "stuDormiNum" },
	       	{ "name": "面积‌‌（平方米）", "group": "12.其他", "value": "", "field": "otherArea","editor": {
	    		"type":"numberbox",
	    		"options":{
	        	 	"min":  0,
	        	 	"precision": 2
	    		}
	        } },
	       	{ "name": "数量（个）", "group": "12.其他", "editor": "numberbox", "value": "", "field": "otherNum" }
	    ];

	$('#edit').propertygrid( {
		//title : '占地与建筑面积',
		toolbar : "#toolbar",//在添加 增添、删除、修改操作的按钮要用到这个
		width : '60%',
		height : 'auto',
		showGroup : true,
		scrollbarSize : 0,
		columns : [ [ {
			field : 'name',
			title : '项目',
			width : 100,
			resizable : true
		}, {
			field : 'value',
			title : '内容',
			width : 100,
			resizable : false
		} ] ]
	});

	$.ajax( {
		type : "POST",
		contentType : "application/json;utf-8",
		url : 'pages/T22/loadInfo?selectYear=' + selectYear,
		async : false,
		dataType : "json",
		success : function(json) {
			if (typeof (json.data) != "undefined") {
				alert(json.data);
				$("#edit").propertygrid( {
					title : '用房面积'
				});
			} else {
				//alert(json.checkState);
					if (json.checkState == WAITCHECK) {
							$("#edit").propertygrid( {
								title : '用房面积（<font color=red>待审核</font>）'
							});
							//document.getElementById("export").style.display ="none";
						$("#pass").show();
						$("#nopass").show();
					} else if (json.checkState == PASSCHECK) {
						$("#edit").propertygrid( {
							title : '用房面积（<font color=red>已审核通过</font>）'
						});
						$("#pass").hide();
						$("#nopass").hide();
					} else {
						$("#edit").propertygrid( {
							title : '用房面积（<font color=red>已审核未通过</font>）'
						});
						$("#pass").hide();
						$("#nopass").hide();
					}
				}
	            var i = 0;
	            while(i<rows.length){
	            	if((i==1)|| (i==4)) {
	            		i=i+1;
	            		continue;
	            	}
	            	rows[i].value = eval('json.'+rows[i].field);	
	            	i=i+1;
	            }	
			},
			error : function(XMLResponse) {
			alert("该年数据为空!!!");
	            var i = 0;
	            while(i<rows.length){
	            	if((i==1)|| (i==4)) {
	            		i=i+1;
	            		continue;
	            	}
	            	rows[i].value = "";	
	            	i=i+1;
	            }	
			}
	})

	$('#edit').propertygrid('loadData', rows);

	//刷新页面
	$("#cbYearContrast").combobox( {
		onChange : function(newValue, oldValue) {
			reloadgrid(newValue, true);
			$('#edit').propertygrid('loadData', rows);
		}
	});

	function reloadgrid(year, flag) {
		$.ajax( {
			type : "POST",
			contentType : "application/json;utf-8",
			url : 'pages/T22/loadInfo?selectYear=' + year,
			async : false,
			dataType : "json",
			success : function(json) {
				if (typeof (json.data) != "undefined") {
					alert(json.data);
					$("#edit").propertygrid( {
						title : '用房面积'
					});
				} else {
					//alert(json.checkState);
			if (json.checkState == WAITCHECK) {
				$("#edit").propertygrid( {
					title : '用房面积（<font color=red>待审核</font>）'
				});
				//document.getElementById("export").style.display ="none";
			$("#pass").show();
			$("#nopass").show();
		} else if (json.checkState == PASSCHECK) {
			$("#edit").propertygrid( {
				title : '用房面积（<font color=red>已审核通过</font>）'
			});
			$("#pass").hide();
			$("#nopass").hide();
		} else {
			$("#edit").propertygrid( {
				title : '用房面积（<font color=red>已审核未通过</font>）'
			});
			$("#pass").hide();
			$("#nopass").hide();
		}
	}
    var i = 0;
    while(i<rows.length){
    	if((i==1)|| (i==4)) {
    		i=i+1;
    		continue;
    	}
    	rows[i].value = eval('json.'+rows[i].field);	
    	i=i+1;
    }	
  },
  error : function(XMLResponse) {
	// alert(XMLResponse.responseText
	      var i = 0;
	      while(i<rows.length){
	      	if((i==1)|| (i==4)) {
	      		i=i+1;
	      		continue;
	      	}
	      	rows[i].value = "";	
	      	i=i+1;
	      }	
			if (flag == true) {
				alert("该年数据为空!!!");
			}
		}
	})
}

	//审核通过
	$("#pass").click(function() {
		alert("test");
		var year = $("#cbYearContrast").combobox('getValue');
		var checkNum = PASSCHECK;
		//alert(checkNum);
			$.ajax( {
				type : "POST",
				url : "pages/T22/updateCheck?selectYear=" + year + "&checkNum="+ PASSCHECK,
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

function reloadgrid(year, flag) {
	$.ajax( {
		type : "POST",
		contentType : "application/json;utf-8",
		url : 'pages/T22/loadInfo?selectYear=' + year,
		async : false,
		dataType : "json",
		success : function(json) {
						if (typeof (json.data) != "undefined") {
							alert(json.data);
							$("#edit").propertygrid( {
								title : '占地与建筑面积'
							});
						} else {
							//alert(json.checkState);
					if (json.checkState == WAITCHECK) {
						$("#edit").propertygrid( {
							title : '占地与建筑面积（<font color=red>待审核</font>）'
						});
						//document.getElementById("export").style.display ="none";
					$("#pass").show();
					$("#nopass").show();
				} else if (json.checkState == PASSCHECK) {
					$("#edit").propertygrid( {
						title : '用房面积（<font color=red>已审核通过</font>）'
					});
					$("#pass").hide();
					$("#nopass").hide();
				} else {
					$("#edit").propertygrid( {
						title : '用房面积（<font color=red>已审核未通过</font>）'
					});
					$("#pass").hide();
					$("#nopass").hide();
				}
			}
            var i = 0;
            while(i<rows.length){
            	if((i==1)|| (i==4)) {
            		i=i+1;
            		continue;
            	}
            	rows[i].value = eval('json.'+rows[i].field);	
            	i=i+1;
            }	
		},
		error : function(XMLResponse) {
		// alert(XMLResponse.responseText
		    var i = 0;
		    while(i<rows.length){
		    	if((i==1)|| (i==4)) {
		    		i=i+1;
		    		continue;
		    	}
		    	rows[i].value = "";	
		    	i=i+1;
		  }	
		if (flag == true) {
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
		url : "pages/T22/updateCheck?selectYear=" + year + "&checkNum="
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