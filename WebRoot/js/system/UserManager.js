

//只是用来展示的数据
	$(function() {
		$('#userManager').datagrid( {
			title : '用户管理',  //可变内容在具体页面定义
			url: 'pages/UserManager/loadUsers',
			iconCls : 'icon-ok',
			width : '100%',
			//height: '100%',
			nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
			striped : true,//设置为true将交替显示行背景。
			collapsible : true,//显示可折叠按钮
			toolbar : "#toolbar",//在添加 增添、删除、修改操作的按钮要用到这个
			singleSelect : false,//为true时只能选择单行
			fitColumns : true,//允许表格自动缩放，以适应父容器
			//sortName : 'xh',//当数据表格初始化时以哪一列来排序
			//sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。
			remoteSort : false,
			rownumbers : true,
			pagination: true,
			onLoadSuccess: function (rowData) {
				
			}
		});
					
	   //导出
	        $("#export").click(function(){
	        var tableName = encodeURI('用户列表');
		    $('#exportForm').form('submit', {
		    	data : $('#exportForm').serialize(),
			    url : "pages/UserManager/dataExport?excelName="+tableName,
			    onSubmit : function() {
			    	return $(this).form('validate');//对数据进行格式化
			    },
			    success : function(data) {
			    	    $.messager.show({
					    	title : '提示',
					    	msg : data
					    });
			    }
		    }); 
		});							
	});
			
	var url;
	var req = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/ ;
	function newUser() {
		//update隐藏的量在提交之后要恢复
		$('#TeaName').combobox('readonly',false);
		
		url = 'pages/UserManager/insert';
		$('#dlg').dialog('open').dialog('setTitle', '添加用户');
		$('#userManagerForm').form('reset');
	}

	function singleImport() {
		//录入数据的表单提交
		$('#userManagerForm').form('submit', {
			url : url,
			data : $('#userManagerForm').serialize(),
			type : "post",
			dataType : "json",
			onSubmit : function() {
				return validate();
			},
			//结果返回
			success : function(result) {
				//json格式转化
				var result = eval('(' + result + ')');
				$.messager.alert('温馨提示', result.data);
				if (result.state) {
					$('#dlg').dialog('close');
					$('#userManager').datagrid('reload');
				}
			}
		});
	}

	function validate() {
		//获取文本框的值
		var teaId = $('#TeaName').combobox('getText');
		var teaName = $('#TeaName').combobox('getValue');
		var csUnit = $('#UnitID').combobox('getText');
		var teaEmail = $('#TeaEmail').val();
		var role = $('#RoleID').combobox('getText');
		var note = $('#UserNote').val();
		document.getElementById('TeaID').value = teaId;
		//alert(teaId);
		//alert(teaName);

		//根据数据库定义的字段的长度，对其进行判断
		if (teaId == null ||  teaId == ''  || teaId.length == 0 || teaId == teaName) {
			alert("教工号不能为空或者教师库中无该教工号");
			return false;
		}

		if (csUnit == null || csUnit.length == 0) {
			alert("教职工单位不能为空");
			return false;
		} 
		
/*		if (teaEmail == null || teaEmail.length == 0 || teaEmail.match(req) == null
				|| teaEmail.length > 100) {
			alert("邮箱输入错误或长度不能为空或长度不超过100");
			return false;
		} else {
			$('#TeaEmailSpan').html("");
		}*/
		
		if (role == null || role.length == 0) {
			alert("用户角色不能为空");
			return false;
		} 
		
		if (note != null && note.length > 1000) {
			alert("备注长度不超过1000");
			return false;
		}
		return true;
	}

	function editUser() {
		
		var row = $('#userManager').datagrid('getSelections');

		if (row.length != 1) {
			$.messager.alert('温馨提示', "请选择1条编辑的数据！！！");
			return;
		}
		
		if(row[0].teaID == "000"){
			$.messager.alert('温馨提示', "初始管理员不可被编辑！！！");
			return ;
		}

		url = 'pages/UserManager/edit';

		$('#dlg').dialog('open').dialog('setTitle', '编辑用户');
		$('#TeaName').combobox('select', row[0].teaName);
		$('#TeaName').combobox('readonly',true);
		$('#seqNumber').val(row[0].seqNumber) ;
		$('#UnitID').combobox('select', row[0].unitID);
		$('#RoleID').combobox('select', row[0].roleID);
		$('#TeaEmail').val(row[0].teaEmail) ;
		$('#UserNote').val(row[0].userNote);
	}

	function switchIds(flag) {
		
		
		//获取选中项
		var row = $('#userManager').datagrid('getSelections');

		if(flag=='0'){
			
			if (row.length == 0) {
				$.messager.alert('温馨提示', "请选择要启用的帐户！！！");
				return;
			}

			$.messager.confirm('启用帐户', '您确定启用选中帐户?', function(sure) {
				if (sure) {
					var ids = "";
					ids += "(";
					for ( var i = 0; i < row.length; i++) {
						if (i < (row.length - 1)) {
							ids += ("'"+row[i].teaID+"'" + ",");
						} else {
							ids += ("'"+row[i].teaID +"'"+ ")");
						}
					}				
					url = "pages/UserManager/switchIds?ids=" + ids + "&switchFlag=0" ;
					submitIds();
				}
			});
		}else{
			if (row.length == 0) {
				$.messager.alert('温馨提示', "请选择停用的帐户！！！");
				return;
			}

			$.messager.confirm('停用帐户', '您确定停用选中帐户?', function(sure) {
				if (sure) {
					var ids = "";
					ids += "(";
					for ( var i = 0; i < row.length; i++) {
						if(row[i].teaID == "000"){
							$.messager.alert('温馨提示', "初始管理员不能被停用！！！");
							return ;
						}
						if (i < (row.length - 1)) {
							ids += ("'"+row[i].teaID+"'" + ",");
						} else {
							ids += ("'"+row[i].teaID +"'"+ ")");
						}
					}				
					url = "pages/UserManager/switchIds?ids=" + ids + "&switchFlag=1" ;
					submitIds();
				}
			});
		}

	}
	
	function deleteByIds() {
		//获取选中项
		var row = $('#userManager').datagrid('getSelections');

		if (row.length == 0) {
			$.messager.alert('温馨提示', "请选择需要删除的数据！！！");
			return;
		}

		$.messager.confirm('数据删除', '您确定删除选中项?', function(sure) {
			if (sure) {
				var ids = "";
				ids += "(";
				for ( var i = 0; i < row.length; i++) {
					if(row[i].teaID == "000"){
						$.messager.alert('温馨提示', "初始管理员不可被删除！！！");
						return ;
					}
					if (i < (row.length - 1)) {
						ids += ("'"+row[i].teaID+"'" + ",");
					} else {
						ids += ("'"+row[i].teaID +"'"+ ")");
					}
				}				
				url = "pages/UserManager/deleteByIds?ids=" + ids ;
				submitIds();
			}
		});
	}
	
	function submitIds() {
		//alert(url);
		$.ajax({
			type : "POST",
			url : url,
			async : "true",
			dataType : "text",
			success : function(result) {
				result = eval("(" + result + ")");
				if (result.state) {
					alert(result.data);
					$('#userManager').datagrid('reload');
				}
			}
		}).submit();
	}

	function resetPassword() {
		//获取选中项
		var row = $('#userManager').datagrid('getSelections');

		if (row.length == 0) {
			$.messager.alert('温馨提示', "请选择需要进行密码重置的用户！！！");
			return;
		}

		$.messager.confirm('密码重置', '您确定重置选中用户密码?', function(sure) {
			if (sure) {
				var ids = "";
				ids += "(";

				for ( var i = 0; i < row.length; i++) {
					if (i < (row.length - 1)) {
						ids += ("'"+row[i].teaID+"'" + ",");
					} else {
						ids += ("'"+row[i].teaID +"'"+ ")");
					}
				}	
				url = "pages/UserManager/resetPassword?ids=" + ids ;
				submitIds();
			}
		});
	}
	
	//查询
	function reloadgrid ()  { 
        //查询参数直接添加在queryParams中 
         var  queryValue = $('#searchID').val();
         var queryParams = $('#userManager').datagrid('options').queryParams;  
         queryParams.searchID = queryValue;  
         $("#userManager").datagrid('reload'); 
    }	
	
	function formatBoolean(val) {  
	    if(val == true){
		    return '是' ;
	    }else if(val == false){
	    	return '否' ;
	    }else{
	    	return null;
	    }
	}  