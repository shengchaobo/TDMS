

//   var re = /^(100|([1-9]?[0-9]?))%$/;
//    alert(re1.test('50.20%'));
//    alert(re1.test('-25%'));
//    alert(re1.test('3a5%')); 
 //只是用来展示的数据
	$(function() {
		var year = $("#cbYearContrast").combobox('getValue'); 
//		alert(year);
		$('#showData').datagrid( {
			title : '课堂教学质量评估统计表',  //可变内容在具体页面定义
			url: 'pages/T513/auditingData',
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
						var merges2 = [
						  {
			                  field:'item',
			                  index: 0,
			                  rowspan: 2
			              },
						  {
			                  field:'item',
			                  index: 2,
			                  rowspan: 2
			              },
						  {
			                  field:'item',
			                  index: 4,
			                  rowspan: 2
			              },
						  {
			                  field:'item',
			                  index: 6,
			                  rowspan: 2
			              }
			              ];

			            for (var i = 0; i < merges2.length; i++)
			                $('#showData').datagrid('mergeCells', {
			                    index: merges2[i].index,
			                    field: merges2[i].field,
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
		         var queryParams = $('#showData').datagrid('options').queryParams;  
		         queryParams.selectYear = year;   
		         $("#showData").datagrid('reload'); 
		    }	
			
			   //导出
	        $("#export").click(function(){
	        var tableName = encodeURI('表5-1-3课堂教学质量评估统计表（评估中心）');
	        var year = $("#cbYearContrast").combobox('getValue'); 
		    $('#exportForm').form('submit', {
		    	data : $('#exportForm').serialize(),
			    url : "pages/T513/dataExport?excelName="+tableName+'&selectYear='+year,
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

    
	//单条导入
	function singleImport() {
		// 录入数据的表单提交
		$('#addForm').form('submit', {
				url : url,
				data : $('#addForm').serialize(),
				type : "post",
				dataType : "json",
				onSubmit : function() {
					return true;
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
	
	
	 
	//编辑
		function edit() {
			
	    	var row = $('#showData').datagrid('getSelections');
	    	
	    	if(row.length != 1){
	    		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
	    		return ;
	    	}
//	    	alert(26);
	    	
	    	var year= $("#cbYearContrast").combobox('getValue'); 
	    	url = 'pages/T513/edit?selectYear='+year ;

	    	$('#dlg').dialog('open').dialog('setTitle','修改课堂教学质量评估统计的信息');
	    	
	    	$('#seqNumber').val(row[0].seqNumber) ;
	    	$('#Item').combobox('select', row[0].itemID) ;
	    	$('#Category').combobox('select', row[0].category) ;
	    	$('#ShouldASCSNum').numberbox('setValue',row[0].shouldASCSNum) ;
	    	$('#HavedASCSNum').numberbox('setValue',row[0].havedASCSNum);
	    	$('#CoverRatio').numberbox('setValue',row[0].coverRatio);
	    	$('#ExcellentNum').numberbox('setValue',row[0].excellentNum);
	    	$('#ExcellentRatio').numberbox('setValue',row[0].excellentRatio);
	    	$('#GoodNum').numberbox('setValue',row[0].goodNum);
	    	$('#GoodRatio').numberbox('setValue',row[0].excellentRatio);
	    	$('#AvgNum').numberbox('setValue',row[0].avgNum);
	    	$('#AvgRatio').numberbox('setValue',row[0].avgRatio);
	    	$('#PoorNum').numberbox('setValue',row[0].poorNum);
	    	$('#PoorRatio').numberbox('setValue',row[0].poorRatio);
		}

  

	


