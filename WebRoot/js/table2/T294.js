	

	//只是用来展示的数据
	$(function() {
		var year = $("#cbYearContrast").combobox('getValue'); 
		$('#showData').datagrid( {
			title : '社会捐赠情况',  //可变内容在具体页面定义
			url: 'pages/T294/loadInfo',
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
					if(rowData.rows.length == 0){
						alert("无该年数据！！！");
					}else{
						var merges2 = [
						  {
			                  field:'donaName',
			                  index: 0,
			                  colspan: 2
			              },
						  {
			                  field:'donaMoney',
			                  index: 0,
			                  colspan: 2
			              }   
			              ];

			            for (var i = 0; i < merges2.length; i++)
			                $('#showData').datagrid('mergeCells', {
			                    index: merges2[i].index,
			                    field: merges2[i].field,
			                    colspan: merges2[i].colspan,
			                    rowspan: merges2[i].rowspan
			                });						
					}
					},

			queryParams:{
				'selectYear': year
			}
		});
		
		
		//刷新页面
		 $("#cbYearContrast").combobox({  
	        onChange:function(newValue, oldValue){  
				reloadgrid(newValue);
	        }
	    }); 
		 
		//查询
		function reloadgrid (year)  { 
			//alert(year);
	        //查询参数直接添加在queryParams中 
	         var queryParams = $('#showData').datagrid('options').queryParams;  
	         queryParams.selectYear = year;   
	         $("#showData").datagrid('reload'); 
	    }	
		
	   //导出
	        $("#export").click(function(){
	        var tableName = encodeURI('表2-9-4社会捐赠情况（计财处）');
	        var year = $("#cbYearContrast").combobox('getValue'); 
		    $('#exportForm').form('submit', {
		    	data : $('#exportForm').serialize(),
			    url : "pages/T294/dataExport?excelName="+tableName+'&selectYear='+year,
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
	
	
	//全局变量，用来暂存当前的url值
	   var url;

		//弹出添加的界面
		function newObject() {	   
			
			$("input#donaName").attr("readonly",false);
			$("input#donaName").css({"color":"black"});
			
			var year = $("#cbYearContrast").combobox('getValue'); 
			url = 'pages/T294/insert?selectYear='+year; 
			$('#dlg').dialog('open').dialog('setTitle', '新增一条捐赠信息');
			$('#addForm').form('reset');
		}
		
		//单条导入
		function singleImport() {
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
						$('#showData').datagrid('reload');
					}
				}
				});
		}

		//对输入字符串进行验证
		function validate() {
			
			// 获取文本框的值
			var note = $('#note').val();
	
			if (note != null && note.length > 1000) {
				alert("备注中文字数不超过500");
				return false;
			}
			return true;
		 }

	function edit() {
			
	   	var row = $('#showData').datagrid('getSelections');
	   	
	   	if(row.length != 1){
	   		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
	   		return ;
	   	}
	   	//alert(row[0].donaName);
	   	if(row[0].donaName=="捐赠金额总计"){
	   		$.messager.alert('温馨提示', "捐赠金额总计不可修改！！！") ;
	   		return;
	   	}
	   	
	   	var year = $("#cbYearContrast").combobox('getValue'); 
	   	url = 'pages/T294/edit?selectYear='+year; 
	   	     	
	   	$('#dlg').dialog('open').dialog('setTitle','修改所选捐赠信息');
	   	$('#seqNumber').val(row[0].seqNumber) ;
    	$('#donaName').val(row[0].donaName) ;
    	$("input#donaName").attr("readonly",true);
    	$("input#donaName").css({"color":"#888"});
	  	$('#type').combobox('select', row[0].type) ;
	  	$('#donaMoney').numberbox('setValue',row[0].donaMoney); 
		$('#note').val(row[0].note) ;
		}
	
		//删除选中的行
	   function deleteByIds() {
		   
		var year = $("#cbYearContrast").combobox('getValue'); 
		// 获取选中项
	   	var row = $('#showData').datagrid('getSelections');
	   	if (row.length == 0) {
	   		$.messager.alert('温馨提示', "请选择需要删除的数据！！！");
			return;
	   	}
	   	
		for ( var i = 0; i < row.length; i++) {
		   	if(row[i].donaName=="捐赠金额总计"){
		   		$.messager.alert('温馨提示', "删除行中不能包括捐赠金额总计！！！") ;
		   		return;
		   	}
		}
	   
	   	$.messager.confirm('数据删除', '您确定删除选中项?', function(sure) {
			if (sure) {
				var ids = "";
				ids += "(";
				for ( var i = 0; i < row.length; i++) {
					if (i < (row.length - 1)) {
						ids += (row[i].seqNumber + ",");
					} else {
						ids += (row[i].seqNumber + ")");
					}
				}
				deletes(ids, year);
				}
	   	});
	   }
	   
	   function deletes(ids,year) {
	   	$.ajax( {
	   		type : "POST",
	   		url : "pages/T294/deleteByIds?ids=" + ids+"&selectYear="+year,
	   		async : "true",
	   		dataType : "text",
	   		success : function(result) {
				result = eval("(" + result + ")");
				if (result.state) {
					$('#showData').datagrid('reload');
				}
			}
	   	}).submit();
	   }