$(function() {
	var selectYear = $("#cbYearContrast").combobox('getValue');
	var rows = [ {
		"name" : "总占地面积",
		"group" : "1.占地面积(平方米)",
		"value" : "",
		"field" : "sumArea",
		"editor" : false
	}, {
		"name" : "学校产权",
		"group" : "1.占地面积(平方米)",
		"value" : "",
		"field" : "schProArea",
		"editor" : {
			"type" : "numberbox",
			"options" : {
				"min" : 0,
				"precision" : 2
			}
		}
	}, {
		"name" : "其中：绿化用地",
		"value" : "",
		"group" : "1.占地面积(平方米)",
		"field" : "greenArea",
		"editor" : {
			"type" : "numberbox",
			"options" : {
				"min" : 0,
				"precision" : 2
			}
		}
	}, {
		"name" : "非学校产权",
		"value" : "",
		"group" : "1.占地面积(平方米)",
		"field" : "notSchProArea",
		"editor" : false
	}, {
		"name" : "其中：绿化用地",
		"group" : "1.占地面积(平方米)",
		"value" : "",
		"field" : "greenAreaNotInSch",
		"editor" : {
			"type" : "numberbox",
			"options" : {
				"min" : 0,
				"precision" : 2
			}
		}
	}, {
		"name" : "其中：独立使用",
		"group" : "1.占地面积(平方米)",
		"value" : "",
		"field" : "onlyUseArea",
		"editor" : {
			"type" : "numberbox",
			"options" : {
				"min" : 0,
				"precision" : 2
			}
		}
	}, {
		"name" : "共同使用",
		"group" : "1.占地面积(平方米)",
		"value" : "",
		"field" : "coUseArea",
		"editor" : {
			"type" : "numberbox",
			"options" : {
				"min" : 0,
				"precision" : 2
			}
		}
	}, {
		"name" : "总建筑面积",
		"group" : "2.总建筑面积(平方米)",
		"value" : "",
		"field" : "sumCoverArea",
		"editor" : false
	}, {
		"name" : "学校产权",
		"group" : "2.总建筑面积(平方米)",
		"value" : "",
		"field" : "schProCovArea",
		"editor" : {
			"type" : "numberbox",
			"options" : {
				"min" : 0,
				"precision" : 2

			}
		}
	}, {
		"name" : "非学校产权",
		"group" : "2.总建筑面积(平方米)",
		"value" : "",
		"field" : "notSchProCovArea",
		"editor" : false
	}, {
		"name" : "其中：独立使用",
		"group" : "2.总建筑面积(平方米)",
		"value" : "",
		"field" : "onlyUseCovArea",
		"editor" : {
			"type" : "numberbox",
			"options" : {
				"min" : 0,
				"precision" : 2
			}
		}
	}, {
		"name" : "共同使用",
		"group" : "2.总建筑面积(平方米)",
		"value" : "",
		"field" : "coUseCovArea",
		"editor" : {
			"type" : "numberbox",
			"options" : {
				"min" : 0,
				"precision" : 2
			}
		}
	} ];

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
		url : 'pages/T21/loadInfo?selectYear=' + selectYear,
		async : false,
		dataType : "json",
		success : function(json) {
			if (typeof (json.data) != "undefined") {
				alert(json.data);
				$("#edit").propertygrid( {
					title : '占地与建筑面积'
				});
				$("#renopass").hide();
				$("#pass").hide();
				$("#nopass").hide();
			} else {
				//alert(json.checkState);
				if (json.checkState == WAITCHECK) {
					$("#edit").propertygrid( {
						title : '占地与建筑面积（<font color=red>待审核</font>）'
					});
						//document.getElementById("export").style.display ="none";
					$("#pass").show();
					$("#nopass").show();
					$("#renopass").hide();
				} else if (json.checkState == PASSCHECK) {
					$("#edit").propertygrid( {
						title : '占地与建筑面积（<font color=red>已审核通过</font>）'
					});
					$("#pass").hide();
					$("#nopass").hide();
					$("#renopass").show();
				} else {
					$("#edit").propertygrid( {
						title : '占地与建筑面积（<font color=red>已审核未通过</font>）'
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
			url : 'pages/T21/loadInfo?selectYear=' + year,
			async : false,
			dataType : "json",
			success : function(json) {
			if (typeof (json.data) != "undefined") {
				alert(json.data);
				$("#edit").propertygrid( {
					title : '占地与建筑面积'
				});
				$("#renopass").hide();
				$("#pass").hide();
				$("#nopass").hide();
			} else {
				//alert(json.checkState);
				if (json.checkState == WAITCHECK) {
					$("#edit").propertygrid( {
						title : '占地与建筑面积（<font color=red>待审核</font>）'
					});
						//document.getElementById("export").style.display ="none";
					$("#pass").show();
					$("#nopass").show();
					$("#renopass").hide();
				} else if (json.checkState == PASSCHECK) {
					$("#edit").propertygrid( {
						title : '占地与建筑面积（<font color=red>已审核通过</font>）'
					});
					$("#pass").hide();
					$("#nopass").hide();
					$("#renopass").show();
				} else {
					$("#edit").propertygrid( {
						title : '占地与建筑面积（<font color=red>已审核未通过</font>）'
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
				url : "pages/T21/updateCheck?selectYear=" + year + "&checkNum="
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
		url : 'pages/T21/loadInfo?selectYear=' + year,
		async : false,
		dataType : "json",
		success : function(json) {
		if (typeof (json.data) != "undefined") {
			alert(json.data);
			$("#edit").propertygrid( {
				title : '占地与建筑面积'
			});
			$("#renopass").hide();
			$("#pass").hide();
			$("#nopass").hide();
		} else {
			//alert(json.checkState);
			if (json.checkState == WAITCHECK) {
				$("#edit").propertygrid( {
					title : '占地与建筑面积（<font color=red>待审核</font>）'
				});
					//document.getElementById("export").style.display ="none";
				$("#pass").show();
				$("#nopass").show();
				$("#renopass").hide();
			} else if (json.checkState == PASSCHECK) {
				$("#edit").propertygrid( {
					title : '占地与建筑面积（<font color=red>已审核通过</font>）'
				});
				$("#pass").hide();
				$("#nopass").hide();
				$("#renopass").show();
			} else {
				$("#edit").propertygrid( {
					title : '占地与建筑面积（<font color=red>已审核未通过</font>）'
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
		url : "pages/T21/updateCheck?selectYear=" + year + "&checkNum="
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