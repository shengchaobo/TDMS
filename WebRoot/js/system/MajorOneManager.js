	//只是用来展示的数据
	$(function() {
		$('#majorManager').datagrid( {
			title : '专科专业管理',  //可变内容在具体页面定义
			url: 'pages/DiMajorOne/loadMajors',
			iconCls : 'icon-ok',
			width : '100%',
			//height: '100%',
			pageSize : 20,//默认选择的分页是每页20行数据
			pageList : [ 5, 10, 20, 50 ],//可以选择的分页集合
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
	function newMajor() {
		
		//update隐藏的量在提交之后要恢复
		$("input#MajorNum").attr("readonly",false);
		$("input#MajorNum").css({"color":"black"});
		
		url = 'pages/DiMajorOne/insert';
		$('#dlg').dialog('open').dialog('setTitle', '添加专科专业');
		$('#majorManagerForm').form('reset');
	}

	function singleImport() {
		//录入数据的表单提交
		$('#majorManagerForm').form('submit', {
			url : url,
			data : $('#majorManagerForm').serialize(),
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
					$('#majorManager').datagrid('reload');
				}
			}
		});
	}
	

	function validate() {
		//获取文本框的值

		var majorNum = $('#MajorNum').val();

		var majorName = $('#MajorName').val();
		var duration = $('#Duration').combobox('getText');
		var direction = $('#Direction').val();
		var unitId = $('#UnitId').combobox('getText');

		//根据数据库定义的字段的长度，对其进行判断
		if (majorNum == null || majorNum.length == 0 || majorNum == "null") {
			alert("专业代码不能为空");
			return false;
		}
		
		if (majorName == null || majorName.length == 0 || majorName == "null") {
			alert("专业名称不能为空");
			return false;
		}
		
		if (duration == null || duration.length == 0 || duration == "null") {
			alert("年限不能为空");
			return false;
		}
		
		if (direction == null || direction.length == 0 || direction == "null") {
			alert("方向名称不能为空");
			return false;
		}

		if (unitId == null || unitId.length == 0 || unitId == "null") {
			alert("所属学院不能为空");
			return false;
		}
		
		
		return true;
	}

	function editMajor() {
		var row = $('#majorManager').datagrid('getSelections');

		if (row.length != 1) {
			$.messager.alert('温馨提示', "请选择1条编辑的数据！！！");
			return;
		}
		
		url = 'pages/DiMajorOne/edit';

		
		$('#dlg').dialog('open').dialog('setTitle', '编辑专科专业');
		$('#SeqNumber').val(row[0].seqNumber) ;
    	$('#MajorNum').val(row[0].majorNum) ;
    	$("input#MajorNum").attr("readonly",true);
    	$("input#MajorNum").css({"color":"#888"});

    	$('#MajorName').val(row[0].majorName);
    	$('#Direction').val(row[0].direction);
		$('#Duration').combobox('select',row[0].duration);
		$('#UnitId').combobox('select',row[0].unitId);

	}

	function deleteByIds() {
		//获取选中项
		var row = $('#majorManager').datagrid('getSelections');

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
						ids += ("'"+row[i].majorNum+"'" + ",");
					} else {
						ids += ("'"+row[i].majorNum +"'"+ ")");
					}
				}				
				url = "pages/DiMajorOne/deleteByIds?ids=" + ids ;
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
					$('#majorManager').datagrid('reload');
				}
			}
		}).submit();
	}

	//查询
	function reloadgrid ()  { 
        //查询参数直接添加在queryParams中 
         var  queryValue = $('#searchID').val();
         var queryParams = $('#majorManager').datagrid('options').queryParams;  
         queryParams.searchID = queryValue;  
         $("#majorManager").datagrid('reload'); 
    }	