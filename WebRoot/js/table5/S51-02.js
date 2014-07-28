
	//只是用来展示的数据
	$(function() {
		var year = $("#cbYearContrast").combobox('getValue'); 
		$('#showData').datagrid( {
			title : '本科课程库信息统计表',  //可变内容在具体页面定义
			url: 'pages/S511/loadInfo2',
			iconCls : 'icon-ok',
			width : '100%',
			//height: '100%',
			nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
			striped : true,//设置为true将交替显示行背景。
			collapsible : true,//显示可折叠按钮
			toolbar : "#toolbar",//在添加 增添、删除、修改操作的按钮要用到这个
			singleSelect : false,//为true时只能选择单行
			fitColumns : false,//允许表格自动缩放，以适应父容器
			//sortName : 'xh',//当数据表格初始化时以哪一列来排序
			//sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。
			remoteSort : false,
			rownumbers : true,
			onLoadSuccess: function (rowData) {
					 
			
					var merges2 = [{
		                  field:'teaUnit',
		                  index: 0,
		                  colspan: 1
		              }           
		              ];

		            for (var i = 0; i < merges2.length; i++)
		                $('#showData').datagrid('mergeCells', {
		                    index: merges2[i].index,
		                    field: merges2[i].field,
		                    colspan: merges2[i].colspan,
		                    rowspan: merges2[i].rowspan
		                });	
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
//			alert(222);
	         var queryParams = $('#showData').datagrid('options').queryParams;  
	         queryParams.selectYear = year;   
	         $("#showData").datagrid('reload'); 
	    }	
		
	   //导出
	   $("#export").click(function(){
	        var tableName = encodeURI('S-5-1-1本科课程库信息统计');
	        var year = $("#cbYearContrast").combobox('getValue'); 
		    $('#exportForm').form('submit', {
		    	data : $('#exportForm').serialize(),
			    url : "pages/S511/dataExport2?excelName="+tableName+'&selectYear='+year,
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