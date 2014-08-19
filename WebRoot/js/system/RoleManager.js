	//只是用来展示的数据
	$(function() {
		$('#roleManager').datagrid( {
			title : '角色管理',  //可变内容在具体页面定义
			url: 'pages/diRole/loadRoles',
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
	        var tableName = encodeURI('角色列表');
		    $('#exportForm').form('submit', {
		    	data : $('#exportForm').serialize(),
			    url : "pages/diRole/dataExport?excelName="+tableName,
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
	function newRole() {
		
		//update隐藏的量在提交之后要恢复
		$("input#RoleID").attr("readonly",false);
		$("input#RoleID").css({"color":"black"});
		
		url = 'pages/diRole/insert';
		$('#dlg').dialog('open').dialog('setTitle', '添加角色');
		$('#roleManagerForm').form('reset');
	}

	function singleImport() {
		//录入数据的表单提交
		$('#roleManagerForm').form('submit', {
			url : url,
			data : $('#roleManagerForm').serialize(),
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
					$('#roleManager').datagrid('reload');
				}
			}
		});
	}

	function validate() {
		//获取文本框的值
		var roleID = $('#RoleID').val();
		var roleName = $('#RoleName').val();
		var csUnit = $('#UnitName').val();
		var note = $('#RoleDest').val();
		//根据数据库定义的字段的长度，对其进行判断
		if (roleID == null || roleID.length == 0 ) {
			alert("角色编号不能为空");
			return false;
		}
		
		if (roleName == null || roleName.length == 0 ) {
			alert("角色乐称不能为空");
			return false;
		}

		if (csUnit == null || csUnit.length == 0) {
			alert("角色单位不能为空");
			return false;
		} 
		
		if (note != null && note.length > 1000) {
			alert("角色描述长度不超过1000");
			return false;
		}
		return true;
	}

	function editRole() {
		var row = $('#roleManager').datagrid('getSelections');

		if (row.length != 1) {
			$.messager.alert('温馨提示', "请选择1条编辑的数据！！！");
			return;
		}
		
		url = 'pages/diRole/edit';

		$('#dlg').dialog('open').dialog('setTitle', '编辑用户');
		
    	$('#RoleID').val(row[0].roleID) ;
    	$("input#RoleID").attr("readonly",true);
    	$("input#RoleID").css({"color":"#888"});
    	
    	$('#RoleName').val(row[0].roleName) ;		
		$('#UnitName').val(row[0].unitName);
		$('#roleDest').val(row[0].roleDest);
	}

	function deleteByIds() {
		//获取选中项
		var row = $('#roleManager').datagrid('getSelections');

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
						ids += ("'"+row[i].roleID+"'" + ",");
					} else {
						ids += ("'"+row[i].roleID +"'"+ ")");
					}
				}				
				url = "pages/diRole/deleteByIds?ids=" + ids ;
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
					$('#roleManager').datagrid('reload');
				}
			}
		}).submit();
	}

	//查询
	function reloadgrid ()  { 
        //查询参数直接添加在queryParams中 
         var  queryValue = $('#searchID').val();
         var queryParams = $('#roleManager').datagrid('options').queryParams;  
         queryParams.searchID = queryValue;  
         $("#roleManager").datagrid('reload'); 
    }	