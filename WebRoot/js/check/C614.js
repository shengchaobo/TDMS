$(function() {
	//alert("test");
	var selectYear = $("#cbYearContrast").combobox('getValue'); 
	var rows = [
	        { "name": "上学年人数（个）", "group": "6.普通预科生数", "value": "", "field": "preppyLastYearNum","editor": "numberbox" },
	        { "name": "本学年人数（个）", "group": "6.普通预科生数", "value": "", "field": "preppyThisYearNum", "editor": "numberbox" },
	        { "name": "上学年人数（个）", "group": "7.进修生数", "value": "", "field": "advStuLastYearNum", "editor": "numberbox" },
	        { "name": "本学年人数（个）", "group": "7.进修生数", "value": "", "field": "advStuThisYearNum", "editor": "numberbox" },
	        { "name": "上学年人数（个）", "group": "8.成人脱产学生数", "value": "", "field": "adultLastYearNum", "editor": "numberbox" },
	        { "name": "本学年人数（个）", "group": "8.成人脱产学生数", "value": "", "field": "adultThisYearNum","editor": "numberbox" },
	        { "name": "上学年人数（个）", "group": "9.夜大（业余）学生数", "value": "", "field": "nightUniLastYearNum", "editor": "numberbox" },
	        { "name": "本学年人数（个）", "group": "9.夜大（业余）学生数", "value": "", "field": "nightUniThisYearNum", "editor": "numberbox" },
	        { "name": "上学年人数（个）", "group": "10.函授学生数", "value": "", "field": "correspdCoLastYearNum", "editor": "numberbox" },
	        { "name": "本学年人数（个）", "group": "10.函授学生数", "value": "", "field": "correspdThisYearNum","editor": "numberbox" },
	        { "name": "上学年人数（个）", "group": "11.网络学生数", "value": "", "field": "netStuLastYearNum", "editor": "numberbox" },
	        { "name": "本学年人数（个）", "group": "11.网络学生数", "value": "", "field": "netStuThisYearNum", "editor": "numberbox" },
	        { "name": "上学年人数（个）", "group": "12.自考学生数", "value": "", "field": "selfStudyLastYearNum", "editor": "numberbox" },
	        { "name": "本学年人数（个）", "group": "12.自考学生数", "value": "", "field": "selfStudyThisYearNum","editor": "numberbox" }
	    ];

	$('#edit').propertygrid( {
		//title : '继续教育学生数量基本情况',
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
		url : 'pages/T614/loadInfo?selectYear=' + selectYear,
		async : false,
		dataType : "json",
		success : function(json) {
			if (typeof (json.data) != "undefined") {
				alert(json.data);
				$("#edit").propertygrid( {
					title : '继续教育学生数量基本情况'
				});
			} else {
				//alert(json.checkState);
		if (json.checkState == WAITCHECK) {
			$("#edit").propertygrid( {
				title : '继续教育学生数量基本情况（<font color=red>待审核</font>）'
			});
			//document.getElementById("export").style.display ="none";
		$("#pass").show();
		$("#nopass").show();
	} else if (json.checkState == PASSCHECK) {
		$("#edit").propertygrid( {
			title : '继续教育学生数量基本情况（<font color=red>已审核通过</font>）'
		});
		$("#pass").hide();
		$("#nopass").hide();
	} else {
		$("#edit").propertygrid( {
			title : '继续教育学生数量基本情况（<font color=red>已审核未通过</font>）'
		});
		$("#pass").hide();
		$("#nopass").hide();
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
			url : 'pages/T614/loadInfo?selectYear=' + year,
			async : false,
			dataType : "json",
			success : function(json) {
				if (typeof (json.data) != "undefined") {
					alert(json.data);
					$("#edit").propertygrid( {
						title : '继续教育学生数量基本情况'
					});
				} else {
					//alert(json.checkState);
			if (json.checkState == WAITCHECK) {
				$("#edit").propertygrid( {
					title : '继续教育学生数量基本情况（<font color=red>待审核</font>）'
				});
				//document.getElementById("export").style.display ="none";
			$("#pass").show();
			$("#nopass").show();
		} else if (json.checkState == PASSCHECK) {
			$("#edit").propertygrid( {
				title : '继续教育学生数量基本情况（<font color=red>已审核通过</font>）'
			});
			$("#pass").hide();
			$("#nopass").hide();
		} else {
			$("#edit").propertygrid( {
				title : '继续教育学生数量基本情况（<font color=red>已审核未通过</font>）'
			});
			$("#pass").hide();
			$("#nopass").hide();
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
				url : "pages/T614/updateCheck?selectYear=" + year + "&checkNum="
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
		url : 'pages/T614/loadInfo?selectYear=' + year,
		async : false,
		dataType : "json",
		success : function(json) {
			if (typeof (json.data) != "undefined") {
				alert(json.data);
				$("#edit").propertygrid( {
					title : '继续教育学生数量基本情况'
				});
			} else {
				//alert(json.checkState);
		if (json.checkState == WAITCHECK) {
			$("#edit").propertygrid( {
				title : '继续教育学生数量基本情况（<font color=red>待审核</font>）'
			});
			//document.getElementById("export").style.display ="none";
		$("#pass").show();
		$("#nopass").show();
	} else if (json.checkState == PASSCHECK) {
		$("#edit").propertygrid( {
			title : '继续教育学生数量基本情况（<font color=red>已审核通过</font>）'
		});
		$("#pass").hide();
		$("#nopass").hide();
	} else {
		$("#edit").propertygrid( {
			title : '继续教育学生数量基本情况（<font color=red>已审核未通过</font>）'
		});
		$("#pass").hide();
		$("#nopass").hide();
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
		url : "pages/T614/updateCheck?selectYear=" + year + "&checkNum="
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