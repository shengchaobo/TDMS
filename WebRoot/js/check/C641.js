$(function() {
	//alert("test");
	var selectYear = $("#cbYearContrast").combobox('getValue'); 
	var rows = [
	        { "name": "资助金额（万元）", "group": "1.政府奖、助学金", "value": "", "field": "govAidFund","editor": {
	    		"type":"numberbox",
	    		"options":{
	        	 	"min":  0,
	        	 	"precision": 2
	    		}
	        } },
	        { "name": "资助学生数（人次）", "group": "1.政府奖、助学金", "value": "", "field": "govAidNum", "editor": "numberbox" },
	        { "name": "资助金额（万元）", "group": "2.社会奖、助学金", "value": "", "field": "socialAidFund","editor": {
	    		"type":"numberbox",
	    		"options":{
	        	 	"min":  0,
	        	 	"precision": 2
	    		}
	        } },
	        { "name": "资助学生数（人次）", "group": "2.社会奖、助学金", "value": "", "field": "socialAidNum", "editor": "numberbox" },
	        { "name": "资助金额（万元）", "group": "3.学校奖学金", "value": "", "field": "schAidFund","editor": {
	    		"type":"numberbox",
	    		"options":{
	        	 	"min":  0,
	        	 	"precision": 2
	    		}
	        } },
	        { "name": "资助学生数（人次）", "group": "3.学校奖学金", "value": "", "field": "schAidNum", "editor": "numberbox" },
	        { "name": "资助金额（万元）", "group": "4.国家助学贷款", "value": "", "field": "nationAidFund","editor": {
	    		"type":"numberbox",
	    		"options":{
	        	 	"min":  0,
	        	 	"precision": 2
	    		}
	        } },
	        { "name": "资助学生数（人次）", "group": "4.国家助学贷款", "value": "", "field": "nationAidNum", "editor": "numberbox" },
	        { "name": "资助金额（万元）", "group": "5.勤工助学金", "value": "", "field": "workStudyFund","editor": {
	    		"type":"numberbox",
	    		"options":{
	        	 	"min":  0,
	        	 	"precision": 2
	    		}
	        } },
	        { "name": "资助学生数（人次）", "group": "5.勤工助学金", "value": "", "field": "workStudyNum", "editor": "numberbox" },
	        { "name": "资助金额（万元）", "group": "6.减免学费", "value": "", "field": "tuitionWaiberFund","editor": {
	    		"type":"numberbox",
	    		"options":{
	        	 	"min":  0,
	        	 	"precision": 2
	    		}
	        } },
	        { "name": "资助学生数（人次）", "group": "6.减免学费", "value": "", "field": "tuitionWaiberNum", "editor": "numberbox" },
	        { "name": "资助金额（万元）", "group": "7.临时困难补助", "value": "", "field": "tempFund","editor": {
	    		"type":"numberbox",
	    		"options":{
	        	 	"min":  0,
	        	 	"precision": 2
	    		}
	        } },
	        { "name": "资助学生数（人次）", "group": "7.临时困难补助", "value": "", "field": "tempNum", "editor": "numberbox" },
	        { "name": "资助金额（万元）", "group": "总计", "value": "", "field": "sumAidFund","editor": false },
	        { "name": "资助学生数（人次）", "group": "总计", "value": "", "field": "sumAidNum", "editor": false }
	    ];

	$('#edit').propertygrid( {
		//title : '本科生奖贷补',
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
		url : 'pages/T641/loadInfo?selectYear=' + selectYear,
		async : false,
		dataType : "json",
		success : function(json) {
			if (typeof (json.data) != "undefined") {
				alert(json.data);
				$("#edit").propertygrid( {
					title : '本科生奖贷补'
				});
				$("#renopass").hide();
				$("#pass").hide();
				$("#nopass").hide();
			} else {
				//alert(json.checkState);
		if (json.checkState == WAITCHECK) {
			$("#edit").propertygrid( {
				title : '本科生奖贷补（<font color=red>待审核</font>）'
			});
			//document.getElementById("export").style.display ="none";
		$("#pass").show();
		$("#nopass").show();
		$("#renopass").hide();
	} else if (json.checkState == PASSCHECK) {
		$("#edit").propertygrid( {
			title : '本科生奖贷补（<font color=red>已审核通过</font>）'
		});
		$("#pass").hide();
		$("#nopass").hide();
		$("#renopass").show();
	} else {
		$("#edit").propertygrid( {
			title : '本科生奖贷补（<font color=red>已审核未通过</font>）'
		});
		$("#pass").hide();
		$("#nopass").hide();
		$("#renopass").hide();
	}
}
var i = 0;
while (i < rows.length) {
	rows[i].value = eval('json.' + rows[i].field);
	i = i + 1;
}
},
error : function(XMLResponse) {
alert("该年数据为空!!!");
var i = 0;
while (i < rows.length) {
	rows[i].value = "";
	i = i + 1;
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
			url : 'pages/T641/loadInfo?selectYear=' + year,
			async : false,
			dataType : "json",
			success : function(json) {
				if (typeof (json.data) != "undefined") {
					alert(json.data);
					$("#edit").propertygrid( {
						title : '本科生奖贷补'
					});
					$("#pass").hide();
					$("#nopass").hide();
					$("#renopass").hide();
				} else {
					//alert(json.checkState);
			if (json.checkState == WAITCHECK) {
				$("#edit").propertygrid( {
					title : '本科生奖贷补（<font color=red>待审核</font>）'
				});
				//document.getElementById("export").style.display ="none";
			$("#pass").show();
			$("#nopass").show();
			$("#renopass").hide();
		} else if (json.checkState == PASSCHECK) {
			$("#edit").propertygrid( {
				title : '本科生奖贷补（<font color=red>已审核通过</font>）'
			});
			$("#pass").hide();
			$("#nopass").hide();
			$("#renopass").show();
		} else {
			$("#edit").propertygrid( {
				title : '本科生奖贷补（<font color=red>已审核未通过</font>）'
			});
			$("#pass").hide();
			$("#nopass").hide();
			$("#renopass").hide();
		}
	}
	var i = 0;
	while (i < rows.length) {
		rows[i].value = eval('json.' + rows[i].field);
		i = i + 1;
	}
},
error : function(XMLResponse) {
	// alert(XMLResponse.responseText
			var i = 0;
			while (i < rows.length) {
				rows[i].value = "";
				i = i + 1;
			}
			if (flag == true) {
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
				url : "pages/T641/updateCheck?selectYear=" + year + "&checkNum="
						+ PASSCHECK,
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
		url : 'pages/T641/loadInfo?selectYear=' + year,
		async : false,
		dataType : "json",
		success : function(json) {
			if (typeof (json.data) != "undefined") {
				alert(json.data);
				$("#edit").propertygrid( {
					title : '本科生奖贷补'
				});
				$("#pass").hide();
				$("#nopass").hide();
				$("#renopass").hide();
			} else {
				//alert(json.checkState);
		if (json.checkState == WAITCHECK) {
			$("#edit").propertygrid( {
				title : '本科生奖贷补（<font color=red>待审核</font>）'
			});
			//document.getElementById("export").style.display ="none";
		$("#pass").show();
		$("#nopass").show();
		$("#renopass").hide();
	} else if (json.checkState == PASSCHECK) {
		$("#edit").propertygrid( {
			title : '本科生奖贷补（<font color=red>已审核通过</font>）'
		});
		$("#pass").hide();
		$("#nopass").hide();
		$("#renopass").show();
	} else {
		$("#edit").propertygrid( {
			title : '本科生奖贷补（<font color=red>已审核未通过</font>）'
		});
		$("#pass").hide();
		$("#nopass").hide();
		$("#renopass").hide();
	}
}
var i = 0;
while (i < rows.length) {
	rows[i].value = eval('json.' + rows[i].field);
	i = i + 1;
}
},
error : function(XMLResponse) {
// alert(XMLResponse.responseText
		var i = 0;
		while (i < rows.length) {
			rows[i].value = "";
			i = i + 1;
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
		url : "pages/T641/updateCheck?selectYear=" + year + "&checkNum="
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