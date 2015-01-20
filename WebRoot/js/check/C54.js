$(function() {
	var selectYear = $("#cbYearContrast").combobox('getValue'); 
	var rows = [
	        { "name": "总数", "group": "1.文化、学术讲座数（个）", "value": "",  "field": "lectureSumNum","editor":  false },
	        { "name": "其中：校级", "group": "1.文化、学术讲座数（个）", "value": "", "field": "schLecture", "editor": "numberbox" },
	        { "name": "其中：院（系）级", "value": "", "group": "1.文化、学术讲座数（个）", "field": "collegeLecture", "editor": "numberbox" },
	        { "name": "总数", "value": "", "group": "2.本科生课外科技、文化活动项目（个）", "field": "actItemSumNum", "editor":  false},
	        { "name": "其中：国家大学生创新性实验计划项目", "group": "2.本科生课外科技、文化活动项目（个）", "value": "", "field": "nationActItem", "editor": "numberbox" },
	        { "name": "其中：省部级项目", "group": "2.本科生课外科技、文化活动项目（个）", "value": "", "field": "proviActItem", "editor": "numberbox" },
	        { "name": "其中：学校项目", "group": "2.本科生课外科技、文化活动项目（个）", "value": "", "field": "schActItem", "editor": "numberbox"}
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
		url : 'pages/T54/loadInfo?selectYear=' + selectYear,
		async : false,
		dataType : "json",
		success : function(json) {
			if (typeof (json.data) != "undefined") {
				alert(json.data);
				$("#edit").propertygrid( {
					title : '课外活动、讲座'
				});
			} else {
				//alert(json.checkState);
		if (json.checkState == WAITCHECK) {
			$("#edit").propertygrid( {
				title : '课外活动、讲座（<font color=red>待审核</font>）'
			});
			//document.getElementById("export").style.display ="none";
		$("#pass").show();
		$("#nopass").show();
	} else if (json.checkState == PASSCHECK) {
		$("#edit").propertygrid( {
			title : '课外活动、讲座（<font color=red>已审核通过</font>）'
		});
		$("#pass").hide();
		$("#nopass").hide();
	} else {
		$("#edit").propertygrid( {
			title : '课外活动、讲座（<font color=red>已审核未通过</font>）'
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
			url : 'pages/T54/loadInfo?selectYear=' + year,
			async : false,
			dataType : "json",
			success : function(json) {
				if (typeof (json.data) != "undefined") {
					alert(json.data);
					$("#edit").propertygrid( {
						title : '课外活动、讲座'
					});
				} else {
					//alert(json.checkState);
			if (json.checkState == WAITCHECK) {
				$("#edit").propertygrid( {
					title : '课外活动、讲座（<font color=red>待审核</font>）'
				});
				//document.getElementById("export").style.display ="none";
			$("#pass").show();
			$("#nopass").show();
		} else if (json.checkState == PASSCHECK) {
			$("#edit").propertygrid( {
				title : '课外活动、讲座（<font color=red>已审核通过</font>）'
			});
			$("#pass").hide();
			$("#nopass").hide();
		} else {
			$("#edit").propertygrid( {
				title : '课外活动、讲座（<font color=red>已审核未通过</font>）'
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
				url : "pages/T54/updateCheck?selectYear=" + year + "&checkNum="
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
		url : 'pages/T54/loadInfo?selectYear=' + year,
		async : false,
		dataType : "json",
		success : function(json) {
			if (typeof (json.data) != "undefined") {
				alert(json.data);
				$("#edit").propertygrid( {
					title : '课外活动、讲座'
				});
			} else {
				//alert(json.checkState);
		if (json.checkState == WAITCHECK) {
			$("#edit").propertygrid( {
				title : '课外活动、讲座（<font color=red>待审核</font>）'
			});
			//document.getElementById("export").style.display ="none";
		$("#pass").show();
		$("#nopass").show();
	} else if (json.checkState == PASSCHECK) {
		$("#edit").propertygrid( {
			title : '课外活动、讲座（<font color=red>已审核通过</font>）'
		});
		$("#pass").hide();
		$("#nopass").hide();
	} else {
		$("#edit").propertygrid( {
			title : '课外活动、讲座（<font color=red>已审核未通过</font>）'
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
		url : "pages/T54/updateCheck?selectYear=" + year + "&checkNum="
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