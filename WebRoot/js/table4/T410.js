	//弹出添加的界面
	function newMajorTea() {
		$('#dlg').dialog('open').dialog('setTitle', '添加新的教师科研情况');
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
			url : 'pages/T410/insert',
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
		var hresItemNum = parseInt($('#hresItemNum').val());
		var hitemFund = parseFloat($('#hitemFund').val());
		var hhumanItemNum = parseInt($('#hhumanItemNum').val());
		var hhumanItemFund = parseFloat($('#hhumanItemFund').val());
		var zresItemNum = parseInt($('#zresItemNum').val());
		var zitemFund = parseFloat($('#zitemFund').val());
		var zhumanItemNum = parseInt($('#zhumanItemNum').val());
		var zhumanItemFund= parseFloat($('#zhumanItemFund').val());
		var nationResAward = parseInt($('#nationResAward').val());
		var proviResAward = parseInt($('#proviResAward').val());
		var cityResAward = parseInt($('#cityResAward').val());
		var schResAward = parseInt($('#schResAward').val());
		var sci = parseInt($('#sci ').val());
		var ssci = parseInt($('#ssci').val());
		var ei = parseInt($('#ei').val());
		var istp = parseInt($('#istp').val());
		var inlandCoreJnal = parseInt($('#inlandCoreJnal').val());
		var cssci = parseInt($('#cssci').val());
		var cscd = parseInt($('#cscd').val());
		var otherPaper = parseInt($('#otherPaper').val());
		var treatises = parseInt($('#treatises').val());
		var translation = parseInt($('#translation').val());
		var inventPatent = parseInt($('#inventPatent').val());
		var utilityPatent = parseInt($('#utilityPatent').val());
		var designPatent = parseInt($('#designPatent').val());
		var note = $('#note').val();
		var  num = /^\d+$/;  //用于判断字符串是否全是数字	
		
		if (time == null || time.length == 0) {
			alert("导入时间不能为空");
			return false;
		}	
		
		if($('#hresItemNum').val() == null || $('#hresItemNum').val()==""){
			$('#hresItemNum').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(hresItemNum))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#hhumanItemNum').val() == null || $('#hhumanItemNum').val()==""){
			$('#hhumanItemNum').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(hhumanItemNum))) {
			alert("必须为整数");
			return false;
	    }else{
	    	if(hhumanItemNum>hresItemNum){
	    		alert("横向人文社会科学项目应小于横向总数");
	    		return false;
	    	}
	    }
		
		if($('#zresItemNum').val() == null || $('#zresItemNum').val()==""){
			$('#zresItemNum').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(zresItemNum))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#zhumanItemNum').val() == null || $('#zhumanItemNum').val()==""){
			$('#zhumanItemNum').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(zhumanItemNum))) {
			alert("必须为整数");
			return false;
	    }else{
	    	if(zhumanItemNum>zresItemNum){
	    		alert("纵向人文社会科学项目数应小于纵向总数");
	    		return false;
	    	}
	    }
		
		if(isNaN(hitemFund)){
			if($('#hitemFund').val()==""){
				$('#hitemFund').val(0);
			}else{
				alert("必须为数字");
				return false;
			}
		}
		
		if(isNaN(hhumanItemFund)){
			if($('#hhumanItemFund').val()==""){
				$('#hhumanItemFund').val(0);
			}else{
				alert("必须为数字");
				return false;
			}
		}else{
			if(hhumanItemFund>hitemFund){
				alert("横向人文社会科学经费应小于横向总经费");
				return false;
			}
		}
				
		if(isNaN(zitemFund)){
			if($('#zitemFund').val()==""){
				$('#zitemFund').val(0);
			}else{
				alert("必须为数字");
				return false;
			}
		}
		
		if(isNaN(zhumanItemFund)){
			if($('#zhumanItemFund').val()==""){
				$('#zhumanItemFund').val(0);
			}else{
				alert("必须为数字");
				return false;
			}
		}else{
			if(zhumanItemFund>zitemFund){
				alert("纵向人文社会科学经费应小于纵向总经费");
				return false;
			}
		}
		
		if($('#nationResAward').val() == null || $('#nationResAward').val()==""){
			$('#nationResAward').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(nationResAward))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#proviResAward').val() == null || $('#proviResAward').val()==""){
			$('#proviResAward').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(proviResAward))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#cityResAward').val() == null || $('#cityResAward').val()==""){
			$('#cityResAward').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(cityResAward))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#schResAward').val() == null || $('#schResAward').val()==""){
			$('#schResAward').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(schResAward))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#sci').val() == null || $('#sci').val()==""){
			$('#sci').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(sci))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#ssci').val() == null || $('#ssci').val()==""){
			$('#ssci').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(ssci))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#ei').val() == null || $('#ei').val()==""){
			$('#ei').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(ei))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#istp').val() == null || $('#istp').val()==""){
			$('#istp').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(istp))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#inlandCoreJnal').val() == null || $('#inlandCoreJnal').val()==""){
			$('#inlandCoreJnal').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(inlandCoreJnal))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#cssci').val() == null || $('#cssci').val()==""){
			$('#cssci').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(cssci))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#cscd').val() == null || $('#cscd').val()==""){
			$('#cscd').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(cscd))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#otherPaper').val() == null || $('#otherPaper').val()==""){
			$('#otherPaper').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(otherPaper))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#treatises').val() == null || $('#treatises').val()==""){
			$('#treatises').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(treatises))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#translation').val() == null || $('#translation').val()==""){
			$('#translation').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(translation))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#inventPatent').val() == null || $('#inventPatent').val()==""){
			$('#inventPatent').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(inventPatent))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#utilityPatent').val() == null || $('#utilityPatent').val()==""){
			$('#utilityPatent').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(utilityPatent))) {
			alert("必须为整数");
			return false;
	    }
		
		if($('#designPatent').val() == null || $('#designPatent').val()==""){
			$('#designPatent').val(0) ;
		}else if (!(/(^[0-9]\d*$)/.test(designPatent))) {
			alert("必须为整数");
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

			
