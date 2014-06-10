	//弹出添加的界面
	function newMajorTea() {
		$('#dlg').dialog('open').dialog('setTitle', '添加新的教学单位教材出版信息');
				$('#addForm').form('reset');
	}
	
    //模板导入
    var url;
	function batchImport() {
		$('#fm').form('submit', {
					url : url,
					onSubmit : function() {
						return $(this).form('validate');
					},
					success : function(result) {
						var result = eval('(' + result + ')');
						if (result.errorMsg) {
							$.messager.show( {
								title : 'Error',
								msg : result.errorMsg
							});
						} else {
							$('#dlg').dialog('close'); // close the dialog
					$('#dg').datagrid('reload'); // reload the user data
				}
			}
		});
	}
    
	//单条导入
	function singleImport() {
		// 录入数据的表单提交
		$('#addForm').form('submit', {
			url : 'pages/T49/insert',
				data : $('#addForm').serialize(),
				type : "post",
				dataType : "json",
				onSubmit : function() {
					return validate();
				},
				// 结果返回
				success : function(result) {
				// json格式转化
				var result = eval('(' + result + ')');
				$.messager.alert('温馨提示', result.data);
				if (result.state) {
					$('#dlg').dialog('close');
					$('#unverfiedData').datagrid('reload');
				}
			}
			});
	}

	//对输入字符串进行验证
	function validate() {
		// 获取文本框的值
		var time = $('#time').datetimebox('getValue');
		var complileBookNum =  parseInt($('#complileBookNum').val());
		var writeBookNum =  parseInt($('#writeBookNum').val());
		var interPlanBook=  parseInt($('#interPlanBook').val());
		var nationPlanBook=  parseInt($('#nationPlanBook').val());
		var proviPlanBook=  parseInt($('#proviPlanBook').val());
		var cityPlanBook=  parseInt($('#cityPlanBook').val());
		var schPlanBook=  parseInt($('#schPlanBook').val());
		var interAwardBook=  parseInt($('#interAwardBook').val());
		var nationAwardBook= parseInt( $('#nationAwardBook').val());
		var proviAwardBook=  parseInt($('#proviAwardBook').val());
		var cityAwardBook =  parseInt($('#cityAwardBook').val());
		var schAwardBook=  parseInt($('#schAwardBook').val());
		var note = $('#note').val();
		var  num = /^\d+$/;  //用于判断字符串是否全是数字		
		if (time == null || time.length == 0) {
			alert("导入时间不能为空");
			return false;
		}	

		if($('#complileBookNum').val() == null || $('#complileBookNum').val()==""){
			$('#complileBookNum').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(complileBookNum))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#writeBookNum').val() == null || $('#writeBookNum').val()==""){
			$('#writeBookNum').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(writeBookNum))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#interPlanBook').val() == null || $('#interPlanBook').val()==""){
			$('#interPlanBook').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(interPlanBook))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#nationPlanBook').val() == null || $('#nationPlanBook').val()==""){
			$('#nationPlanBook').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(nationPlanBook))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#proviPlanBook').val() == null || $('#proviPlanBook').val()==""){
			$('#proviPlanBook').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(proviPlanBook))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#cityPlanBook').val() == null || $('#cityPlanBook').val()==""){
			$('#cityPlanBook').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(cityPlanBook))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#schPlanBook').val() == null || $('#schPlanBook').val()==""){
			$('#schPlanBook').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(schPlanBook))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#interAwardBook').val() == null || $('#interAwardBook').val()==""){
			$('#interAwardBook').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(interAwardBook))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#nationAwardBook').val() == null || $('#nationAwardBook').val()==""){
			$('#nationAwardBook').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(nationAwardBook))) {
			alert("必须为整数");
			return false;
	    }
		if($('#proviAwardBook').val() == null || $('#proviAwardBook').val()==""){
			$('#proviAwardBook').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(proviAwardBook))) {
			alert("必须为整数");
			return false;
	    }
		if($('#cityAwardBook').val() == null || $('#cityAwardBook').val()==""){
			$('#cityAwardBook').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(cityAwardBook))) {
			alert("必须为整数");
			return false;
	    }
		if($('#schAwardBook').val() == null || $('#schAwardBook').val()==""){
			$('#schAwardBook').val(0) ;
		}else  if (!(/(^[0-9]\d*$)/.test(schAwardBook))) {
			alert("必须为整数");
			return false;
	    }
		
		if((complileBookNum+writeBookNum) < (	interPlanBook+nationPlanBook+proviPlanBook
		+cityPlanBook+ schPlanBook+ interAwardBook+nationAwardBook+ proviAwardBook
		+ cityAwardBook + schAwardBook)){
			alert("编著教材数与编写教材数之和应大于规划教材和获奖教材之和");
			return false;
		}
		
		if (note != null && note.length > 1000) {
			alert("备注中文字数不超过500");
			return false;
		}
		return true;
	 }

	function editUser() {
		var row = $('#dg').datagrid('getSelections');
		if (row.length != 1) {
		$.messager.alert("信息提示", "没选取或者选取了多行", "info");
			return;
		}
		alert(row[0].birthday);
		var date = formattime(row[0].birthday);
		// 为文本框赋值
		$('#id').val(row[0].id);
		$('#username').val(row[0].username);
		$('#password').val(row[0].password);
		$('#email').val(row[0].email);
		$('#sex').val(row[0].sex);
		$('#birthday').val(date);

		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', '本科课程库');
				$('#fm').form('load', row);
				url = 'updateUser';
			}
		}

	    function loadDic() {
				$('#dicDlg').dialog('open').dialog('setTitle', '高级查询');
				loadDictionary();
		}
		function loadDictionary() {
				$.ajax(
				{
					type : "POST",
					url : "table5/loadDic",
									async : "false",
									dataType : "text",
									success : function(data) {
										data = eval("(" + data + ")");
										alert(data[0].id);
										var str = "<table width=\"100%\" border=\"1\"><tr>";
										$(data)
												.each(
														function(index) {
															var val = data[index];
															if (index % 4 == 0
																	&& index != 0) {
																str += "</tr><tr>";
															}
															str += "<td><input type=\"checkbox\" id=\""
																	+ val.id
																	+ "\"name="
																	+ "\"checkboxex\""
																	+ "value=\""
																	+ val.data
																	+ "\">"
																	+ val.data
																	+ "</input></td>";
														});
										// alert(str);
										str += "</tr><tr><td colSpan=\"4\" style=\"text-align:center\"><a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" iconCls=\"icon-add\" onclick=\"loadData()\">添加</a></td></tr></table>";
										document.getElementById("dicTables").innerHTML = str;
										$.parser.parse('#dicTables');
									}
								}).submit();
			}

			function loadData() {

				// flag判断
				var flag = false;
				var checkboxes = document.getElementsByName("checkboxex");
				var tables = "<div class=\"ftitle\">自定义查询条件</div><form method=\"post\" action=\"table5/dictorySearch\" id=\"dicsDataForm\"><table width=\"100%\" border=\"1\">";
				tables += "<tr><td>查询名称</td><td>运算符</td><td>查询内容</td><td>逻辑关系</td></tr>";
				for (i = 0; i < checkboxes.length; i++) {
					if (checkboxes[i].checked) {
						flag = true;
						tables += ("<tr><td style=\"width:50%px\">"
								+ hideId(checkboxes[i].id, i)
								+ checkboxes[i].value + "</td><td>"
								+ selectOperateData(i) + "</td><td>"
								+ selectDataHtml(checkboxes[i].id, i)
								+ "</td><td>" + selectLogicData(i) + "</td></tr>");
					}
				}
				if (flag) {
					tables += "<tr><td colSpan=\"4\" style=\"text-align:center\"><a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" iconCls=\"icon-search\" onclick=\"submitDicForm()\">查询</a></td></tr>";
				}
				tables += "</table></form>";
				alert(tables);
				document.getElementById("dices").innerHTML = tables;
				$.parser.parse('#dices');

			}

			function hideId(val, index) {
				var hiddenId = "<input type='hidden' name='dictorySearch["
						+ index + "].id' value='" + val + "'/>";

				return hiddenId;
			}

			// 自动加载要查询的数据
			function selectDataHtml(val, index) {

				var selectsHtml = "<select class=\"easyui-combogrid\" style=\"width:50%px\" name=\"dictorySearch["
						+ index
						+ "].dicData\" data-options=\"panelWidth: 500, multiple: true,required:true,"
						+ " idField: 'dicData',textField: 'dicData',"
						+ "url: 'table5/loadDictionary?dicId="
						+ val
						+ "',"
						+ "method: 'post',"
						+ "columns: [[{field:'ck',checkbox:true},{field:'itemid',title:'数据',width:80},{field:'dicData',title:'数据',width:80}]],"
						+ "fitColumns: true \"> </select>";

				return selectsHtml;
			}

			// 生成运算关系combo
			function selectOperateData(index) {

				var operateHtml = "<select style=\"width:15%px\" name=\"dictorySearch["
						+ index
						+ "].operator\"> <option value=\"equals\">等于</option><option value=\"between\">之间</option><option value=\"side\">两边</option></select>";

				return operateHtml;
			}

			// 生成逻辑关系combo
			function selectLogicData(index) {

				var logicHtml = "<select style=\"width:15%px\" name=\"dictorySearch["
						+ index
						+ "].logic\"> <option value=\"and\">并且</option><option value=\"or\">或者</option></select>";

				return logicHtml;
			}

			function submitDicForm() {
				$.ajax( {
					type : "POST",
					url : "table5/dictorySearch",
					data : $('#dicsDataForm').serialize(),
					async : "false",
					dataType : "text",
					success : function(data) {
						alert(123);
					}
				}).submit();
			}
