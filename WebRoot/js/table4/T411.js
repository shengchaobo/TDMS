	//全局变量，用来暂存当前的url值
   var url;

	//弹出添加的界面
	function newTeacher() {
		url = 'pages/T411/insert' ;
		$('#dlg').dialog('open').dialog('setTitle', '添加新的教职工');
		$('#addForm').form('reset');
	}
	
    //模板导入
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
		
		//update隐藏的量在提交之后要恢复
		$("input#teaId").attr("disabled",false);
    	$('#title1').show();
    	$('#item1').show();
    	$('hr').show();
    	
		// 录入数据的表单提交
		$('#addForm').form('submit', {
			    url : url,
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
					$('#commomData').datagrid('reload');
				}
			}
			});
	}

	//对输入字符串进行验证
	function validate() {
		// 获取文本框的值
		var teaId = $('#teaId').val();
		var teaName = $('#teaName').val();
		var note = $('#note').val();
		var  num = /^\d+$/;  //用于判断字符串是否全是数字
		
		// 根据数据库定义的字段的长度，对其进行判断
		 if (teaName == null || teaName.length == 0 || teaName.length > 100) {
			$('#teaName').focus();
			$('#teaName').select();
			alert("教师名字不能为空或长度不超过100");
			return false;
/*			$('#teaNameSpan').html(
					"<font style=\"color:red\">教师名字不能为空或长度不超过100</font>");
					return false;
			}*/
		 }
		 
		if (teaId == null || teaId.length == 0 ) {
				$('#teaId').focus();
				$('#teaId').select();
				alert("教师ID不能为空");
/*				$('#teaIdSpan').html(
					"<font style=\"color:red\">教师ID不能为空或长度不超过50或不全是数字</font>");*/
				return false;
		}else if( !teaId.match(num)){
			 	alert("教师ID不全是数字");
			 	return false;
		}else if(teaId.length > 50){
			 	alert("教师ID长度不超过50");
			 	return false;
		}
		
		if (note != null && note.length > 1000) {
			alert("备注中文字数不超过500");
/*			$('#noteSpan').html(
				"<font style=\"color:red\">备注中文字数不超过500</font>");*/
				return false;
		}
		return true;
	 }

	function edit() {
		
    	var row = $('#commomData').datagrid('getSelections');
    	
    	if(row.length != 1){
    		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
    		return ;
    	}
    	
    	url = 'pages/T411/edit' ;
    
    	$('#title1').hide();
    	$('#item1').hide();
    	$('hr').hide();
    	$('#dlg').dialog('open').dialog('setTitle','修改该名教职工的信息');
    	$('#teaId').val(row[0].teaId) ;
    	$("input#teaId").attr("disabled",true);
    	$('#teaName').val(row[0].teaName) ;
    	$('#gender').combobox('select', row[0].gender) ;
    	$('#birthday').datebox("setValue", formattime(row[0].birthday)) ;
    	$('#admisTime').datebox("setValue", formattime(row[0].admisTime)) ;
    	$('#teaState').combobox('select', row[0].teaState) ;
    	$('#beginWorkTime').datebox("setValue", formattime(row[0].beginWorkTime)); 
    	$('#idcode').combobox('setText', row[0].idcode) ;
    	$('#officeID').combobox('select', row[0].officeID) ;
    	$('#teaResOfficeID').combobox('select', row[0].teaResOfficeID) ;
    	$('#unitId').combobox('select', row[0].unitId) ;
		$('#education').combobox('setText', row[0].education) ;
		$('#topDegree').combobox('setText', row[0].topDegree) ;
		$('#graSch').val(row[0].graSch) ;
		$('#major').val(row[0].major) ;
		$('#source').combobox('setText', row[0].source) ;
		$('#adminLevel').val(row[0].adminLevel) ;
		$('#majTechTitle').combobox('setText', row[0].majTechTitle) ;
		$('#teaTitle').combobox('setText', row[0].teaTitle) ;
		$('#notTeaTitle').val(row[0].notTeaTitle) ;		
		$('#subjectClass').combobox('select', row[0].subjectClass) ;
		$('#doubleTea').combobox('select', row[0].doubleTea) ;
		$('#industry').combobox('select', row[0].industry) ;
		$('#engineer').combobox('select', row[0].engineer) ;
		$('#teaBase').combobox('select', row[0].teaBase) ;
		$('#note').val(row[0].note) ;
	}
	
	function singleSearch(){
	   	 $('#searchFome').form('submit',{
	   		 url: 'pages/T411/singleSearch',
	   		 type: "post",
		     dataType: "json",
	   		 success: function(result){
	   		 	var result = eval('('+result+')');
	   		 	if (!result.state){
	   		 		$.messager.show({
	   		 			title: 'Error',
	   		 			msg: result.errorMsg
	   			 });
	   		 	} else {
			    	$('#unverfiedData').datagrid('load'); // reload the auditing data
	   		 	}
	   		 }
	   		 });
	   }
	
/*	//删除选中的行
    function deleteByIds() {
	// 获取选中项
    	var row = $('#commomData').datagrid('getSelections');
    	if (row.length == 0) {
    		$.messager.alert('温馨提示', "请选择需要删除的数据！！！");
		return;
    	}
    	$.messager.confirm('数据删除', '您确定删除选中项?', function(sure) {
		if (sure) {
			var ids = "";
			ids += "(";

			for ( var i = 0; i < row.length; i++) {
				if (i < (row.length - 1)) {
					ids += ("'"+row[i].teaId +"'"+ ",");
				} else {
					ids += ("'"+row[i].teaId+"'" + ")");
				}
			}

			deletes(ids);

			}
    	});
    }
    
    function deletes(ids) {
    	$.ajax( {
    		type : "POST",
    		url : "pages/T411/deleteByIds?ids=" + ids,
    		async : "true",
    		dataType : "text",
    		success : function(result) {
			result = eval("(" + result + ")");

			if (result.state) {
				alert(result.data);
				$('#commomData').datagrid('reload');
			}
		}
    	}).submit();
    }*/

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
			
