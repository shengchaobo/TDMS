	//只是用来展示的数据
	$(function() {
		$('#cateManager').datagrid( {
			title : '课程类别管理',  //可变内容在具体页面定义
			url: 'pages/DiCourseCategories/loadDiCourseCategories',
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
			
			onLoadSuccess: function (rowData) {
				
			}
		});							
	});
			
	var url;
	function newCate() {
		
		//update隐藏的量在提交之后要恢复
		$("input#IndexID").attr("readonly",false);
		$("input#IndexID").css({"color":"black"});
		
		url = 'pages/DiCourseCategories/insert';
		$('#dlg').dialog('open').dialog('setTitle', '添加课程类别');
		$('#cateManagerForm').form('reset');
	}

	function singleImport() {
		//录入数据的表单提交
		$('#cateManagerForm').form('submit', {
			url : url,
			data : $('#cateManagerForm').serialize(),
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
					$('#cateManager').datagrid('reload');
				}
			}
		});
	}

	function validate() {
		//获取文本框的值
		var indexID = $('#IndexID').val();
		var courseCategories = $('#CourseCategories').val();
		
		//根据数据库定义的字段的长度，对其进行判断
		if (indexID == null || indexID.length == 0 || indexID == "null") {
			alert("课程类别代码不能为空");
			return false;
		}
		
		if (indexID.length > 5 ) {
			alert("课程类别代码不能超过5位");
			return false;
		}
		
		if (courseCategories == null || courseCategories.length == 0 || courseCategories == "null") {
			alert("课程类别名称不能为空");
			return false;
		}

		return true;
	}

	function editCate() {
		var row = $('#cateManager').datagrid('getSelections');

		if (row.length != 1) {
			$.messager.alert('温馨提示', "请选择1条编辑的数据！！！");
			return;
		}
		
		url = 'pages/DiCourseCategories/edit';

		$('#dlg').dialog('open').dialog('setTitle', '编辑课程类别');
		
    	$('#IndexID').val(row[0].indexId) ;
    	$("input#IndexID").attr("readonly",true);
    	$("input#IndexID").css({"color":"#888"});
    	
    	$('#CourseCategories').val(row[0].courseCategories) ;		
		
	}

	function deleteByIds() {
		//获取选中项
		var row = $('#cateManager').datagrid('getSelections');

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
						ids += ("'"+row[i].indexId+"'" + ",");
					} else {
						ids += ("'"+row[i].indexId +"'"+ ")");
					}
				}				
				url = "pages/DiCourseCategories/deleteByIds?ids=" + ids ;
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
					$('#cateManager').datagrid('reload');
				}
			}
		}).submit();
	}

	