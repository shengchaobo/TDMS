	//只是用来展示的数据
	$(function() {
		$('#departmentManager').datagrid( {
			title : '部门管理',  //可变内容在具体页面定义
			url: 'pages/DiDepartment/loadDes',
			iconCls : 'icon-ok',
			width : '100%',
			//height: '100%',
			pageSize : 20,//默认选择的分页是每页5行数据
			pageList : [ 5, 10, 20, 50 ],//可以选择的分页集合
			nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
			striped : true,//设置为true将交替显示行背景。
			collapsible : true,//显示可折叠按钮
			toolbar : "#toolbar",//在添加 增添、删除、修改操作的按钮要用到这个
			singleSelect : false,//为true时只能选择单行
			//fitColumns : true,//允许表格自动缩放，以适应父容器
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
	function newDepartment() {
		
		//update隐藏的量在提交之后要恢复
		$("input#UnitID").attr("readonly",false);
		$("input#UnitID").css({"color":"black"});
		
		url = 'pages/DiDepartment/insert';
		$('#dlg').dialog('open').dialog('setTitle', '添加部门');
		$('#departmentManagerForm').form('reset');
	}

	function singleImport() {
		//录入数据的表单提交
		$('#departmentManagerForm').form('submit', {
			url : url,
			data : $('#departmentManagerForm').serialize(),
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
					$('#departmentManager').datagrid('reload');
				}
			}
		});
	}

	function validate() {
		var  num = /^\d+$/;  //用于判断字符串是否全是数字		
		//获取文本框的值
		var unitID = $('#UnitID').val();
		var unitName = $('#UnitName').val();
		var class1 = $('#Class1').val();
		var class2 = $('#Class2').val();
		var functions = $('#Functions').val();
		var leader = $('#Leader').val();
		var teaId = $('#TeaID').val();
		
		var note = $('#Note').val();
		
		//根据数据库定义的字段的长度，对其进行判断
		if (unitID == null || unitID.length == 0 || unitID == "null" ) {
			alert("单位号不能为空");
			return false;
		}
		
		if (unitName == null || unitName.length == 0 || unitName == "null") {
			alert("部门名称不能为空");
			return false;
		}

		if (class1 == null || class1.length == 0 || class1 == "null") {
			alert("一级分类不能为空");
			return false;
		}
		
		if (class2 == null || class2.length == 0 || class2 == "null") {
			alert("二级分类不能为空");
			return false;
		}
		
		if (functions == null || functions.length == 0 || functions == "null") {
			alert("单位职能不能为空");
			return false;
		}
		
		if (leader == null || leader.length == 0 || leader == "null") {
			alert("负责人不能为空");
			return false;
		}

		if (teaId == null ||  teaId == ''  || teaId.length == 0) {
			alert("教工号不能为空");
			return false;
		}
		
				
		if (note != null && note.length > 1000) {
			alert("备注长度不超过1000");
			return false;
		}
		return true;
	}

	function editDepartment() {
		var row = $('#departmentManager').datagrid('getSelections');

		if (row.length != 1) {
			$.messager.alert('温馨提示', "请选择1条编辑的数据！！！");
			return;
		}
		
		url = 'pages/DiDepartment/edit';

		$('#dlg').dialog('open').dialog('setTitle', '编辑部门');
		
    	$('#UnitID').val(row[0].unitId) ;
    	$("input#UnitID").attr("readonly",true);
    	$("input#UnitID").css({"color":"#888"});
    	
    	$('#UnitName').val(row[0].unitName);		
		$('#Class1').val(row[0].class1);
		$('#Class2').val(row[0].class2);
		$('#Functions').val(row[0].functions);
		$('#Leader').val(row[0].leader);
		$('#TeaID').val(row[0].teaId);
		$('#Note').val(row[0].note);
	}

	function deleteByIds() {
		//获取选中项
		var row = $('#departmentManager').datagrid('getSelections');

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
				url = "pages/DiDepartment/deleteByIds?ids=" + ids ;
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
					$('#departmentManager').datagrid('reload');
				}
			}
		}).submit();
	}

	//查询
	function reloadgrid ()  { 
        //查询参数直接添加在queryParams中 
         var  queryValue = $('#searchID').val();
         var queryParams = $('#departmentManager').datagrid('options').queryParams;  
         queryParams.searchID = queryValue;  
         $("#departmentManager").datagrid('reload'); 
    }	