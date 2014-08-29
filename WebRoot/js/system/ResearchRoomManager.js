	//只是用来展示的数据
	$(function() {
		$('#roomManager').datagrid( {
			title : '教研室管理',  //可变内容在具体页面定义
			url: 'pages/DiResearchRoom/loadRooms',
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

	});

	var url;
	function newRoom() {

		//update隐藏的量在提交之后要恢复
		$("input#UnitID").attr("readonly",false);
		$("input#UnitID").css({"color":"black"});

		url = 'pages/DiResearchRoom/insert';
		$('#dlg').dialog('open').dialog('setTitle', '添加教研室');
		$('#roomManagerForm').form('reset');
	}

	function singleImport() {
		//录入数据的表单提交
		$('#roomManagerForm').form('submit', {
			url : url,
			data : $('#roomManagerForm').serialize(),
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
					$('#roomManager').datagrid('reload');
				}
			}
		});
	}

	function validate() {
		//获取文本框的值
		var unitID = $('#UnitID').val();
		var parentID = $('#ParentId').combobox('getText');
		var researchName = $('#ResearchName').val();


		//根据数据库定义的字段的长度，对其进行判断
		if (unitID == null || unitID.length == 0 ) {
			alert("教研室号不能为空");
			return false;
		}

		if (parentID == null || parentID.length == 0 ) {
			alert("所属教学单位不能为空");
			return false;
		}

		if (researchName == null || ResearchName.length == 0) {
			alert("一级分类不能为空");
			return false;
		}


		return true;
	}

	function editRoom() {
		var row = $('#roomManager').datagrid('getSelections');

		if (row.length != 1) {
			$.messager.alert('温馨提示', "请选择1条编辑的数据！！！");
			return;
		}

		url = 'pages/DiResearchRoom/edit';

		$('#dlg').dialog('open').dialog('setTitle', '编辑教研室');

    	$('#UnitID').val(row[0].unitId) ;
    	$("input#UnitID").attr("readonly",true);
    	$("input#UnitID").css({"color":"#888"});



		$('#ParentId').combobox('select',row[0].parentId);
		$('#ResearchName').val(row[0].researchName);
	}

	function deleteByIds() {
		//获取选中项
		var row = $('#roomManager').datagrid('getSelections');

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
						ids += ("'"+row[i].unitId+"'" + ",");
					} else {
						ids += ("'"+row[i].unitId +"'"+ ")");
					}
				}
				url = "pages/DiResearchRoom/deleteByIds?ids=" + ids ;
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
					$('#roomManager').datagrid('reload');
				}
			}
		}).submit();
	}

	//查询
	function reloadgrid ()  {
        //查询参数直接添加在queryParams中
         var  queryValue = $('#searchID').val();
         var queryParams = $('#roomManager').datagrid('options').queryParams;
         queryParams.searchID = queryValue;
         $("#roomManager").datagrid('reload');
    }
