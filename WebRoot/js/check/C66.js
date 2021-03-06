$(function() {
	//alert("test");
	var selectYear = $("#cbYearContrast").combobox('getValue'); 
	var rows = [
	        { "name": "总数", "group": "1.学生社团（个）", "value": "", "field": "stuClubSum","editor": false },
	        { "name": "其中：科技类", "group": "1.学生社团（个）", "value": "", "field": "stuClubSciNum", "editor": "numberbox" },
	        { "name": "其中：人文社会类", "group": "1.学生社团（个）", "value": "", "field": "stuClubHumanNum", "editor": "numberbox" },
	        { "name": "其中：体育类", "group": "1.学生社团（个）", "value": "", "field": "stuClubSportNum", "editor": "numberbox" },
	        { "name": "其中：文艺类", "group": "1.学生社团（个）", "value": "", "field": "stuClubArtNum", "editor": "numberbox" },
	        { "name": "其他", "group": "1.学生社团（个）", "value": "", "field": "otherStuClub", "editor": "numberbox" },
	        { "name": "总数", "group": "2.参与人次数（人次）", "value": "", "field": "joinStuSum","editor": false },
	        { "name": "其中：科技类", "group": "2.参与人次数（人次）", "value": "", "field": "joinClubSciNum", "editor": "numberbox" },
	        { "name": "其中：人文社会类", "group": "2.参与人次数（人次）", "value": "", "field": "joinClubHumanNum", "editor": "numberbox" },
	        { "name": "其中：体育类", "group": "2.参与人次数（人次）", "value": "", "field": "joinClubSportNum", "editor": "numberbox" },
	        { "name": "其中：文艺类", "group": "2.参与人次数（人次）", "value": "", "field": "joinClubArtNum", "editor": "numberbox" },
	        { "name": "其他", "group": "2.参与人次数（人次）", "value": "", "field": "joinOtherClub", "editor": "numberbox" }
	    ];

	$('#edit').propertygrid( {
		//title : '学生社团',
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
		url : 'pages/T66/loadInfo?selectYear=' + selectYear,
		async : false,
		dataType : "json",
		success : function(json) {
			if (typeof (json.data) != "undefined") {
				alert(json.data);
				$("#edit").propertygrid( {
					title : '学生社团'
				});
				$("#pass").hide();
				$("#nopass").hide();
				$("#renopass").hide();
			} else {
				//alert(json.checkState);
		if (json.checkState == WAITCHECK) {
			$("#edit").propertygrid( {
				title : '学生社团（<font color=red>待审核</font>）'
			});
			//document.getElementById("export").style.display ="none";
		$("#pass").show();
		$("#nopass").show();
		$("#renopass").hide();
	} else if (json.checkState == PASSCHECK) {
		$("#edit").propertygrid( {
			title : '学生社团（<font color=red>已审核通过</font>）'
		});
		$("#pass").hide();
		$("#nopass").hide();
		$("#renopass").show();
	} else {
		$("#edit").propertygrid( {
			title : '学生社团（<font color=red>已审核未通过</font>）'
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
			url : 'pages/T66/loadInfo?selectYear=' + year,
			async : false,
			dataType : "json",
			success : function(json) {
				if (typeof (json.data) != "undefined") {
					alert(json.data);
					$("#edit").propertygrid( {
						title : '学生社团'
					});
					$("#pass").hide();
					$("#nopass").hide();
					$("#renopass").hide();
				} else {
					//alert(json.checkState);
			if (json.checkState == WAITCHECK) {
				$("#edit").propertygrid( {
					title : '学生社团（<font color=red>待审核</font>）'
				});
				//document.getElementById("export").style.display ="none";
			$("#pass").show();
			$("#nopass").show();
			$("#renopass").hide();
		} else if (json.checkState == PASSCHECK) {
			$("#edit").propertygrid( {
				title : '学生社团（<font color=red>已审核通过</font>）'
			});
			$("#pass").hide();
			$("#nopass").hide();
			$("#renopass").show();
		} else {
			$("#edit").propertygrid( {
				title : '学生社团（<font color=red>已审核未通过</font>）'
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
				url : "pages/T66/updateCheck?selectYear=" + year + "&checkNum="
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
		url : 'pages/T66/loadInfo?selectYear=' + year,
		async : false,
		dataType : "json",
		success : function(json) {
			if (typeof (json.data) != "undefined") {
				alert(json.data);
				$("#edit").propertygrid( {
					title : '学生社团'
				});
				$("#pass").hide();
				$("#nopass").hide();
				$("#renopass").hide();
			} else {
				//alert(json.checkState);
		if (json.checkState == WAITCHECK) {
			$("#edit").propertygrid( {
				title : '学生社团（<font color=red>待审核</font>）'
			});
			//document.getElementById("export").style.display ="none";
		$("#pass").show();
		$("#nopass").show();
		$("#renopass").hide();
	} else if (json.checkState == PASSCHECK) {
		$("#edit").propertygrid( {
			title : '学生社团（<font color=red>已审核通过</font>）'
		});
		$("#pass").hide();
		$("#nopass").hide();
		$("#renopass").show();
	} else {
		$("#edit").propertygrid( {
			title : '学生社团（<font color=red>已审核未通过</font>）'
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
		url : "pages/T66/updateCheck?selectYear=" + year + "&checkNum="
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