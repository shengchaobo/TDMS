	//全局变量，用来控制字段合并次数
	var count = 0;	


	//只是用来展示的数据
	$(function() {
		var year = $("#cbYearContrast").combobox('getValue'); 
		$('#showData').datagrid( {
			title : '国外及港澳台学生情况（国际交流与合作处）',  //可变内容在具体页面定义
			url: 'pages/T616/loadInfo',
			iconCls : 'icon-ok',
			width : '100%',
			//height: '100%',
			nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
			striped : true,//设置为true将交替显示行背景。
			collapsible : true,//显示可折叠按钮
			toolbar : "#toolbar",//在添加 增添、删除、修改操作的按钮要用到这个
			singleSelect : true,//为true时只能选择单行
			fitColumns : true,//允许表格自动缩放，以适应父容器
			//sortName : 'xh',//当数据表格初始化时以哪一列来排序
			//sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。
			remoteSort : false,
			rownumbers : true,
			onLoadSuccess: function (rowData) {
					if(count==0){
						
						var merges2 = [
						  {
			                  field:'stuType',
			                  index: 0,
			                  colspan: 1
			              }
			              ];

			            for (var i = 0; i < merges2.length; i++)
			                $('#showData').datagrid('mergeCells', {
			                    index: merges2[i].index,
			                    field: merges2[i].field,
			                    colspan: merges2[i].colspan
			                });	
			         count++;   
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
	        var tableName = encodeURI('表6-1-6国外及港澳台学生情况（国际交流与合作处）');
	        var year = $("#cbYearContrast").combobox('getValue'); 
		    $('#exportForm').form('submit', {
		    	data : $('#exportForm').serialize(),
			    url : "pages/T616/dataExport?excelName="+tableName+'&selectYear='+year,
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
	        
	        //打开导入窗口
			   $("#add").click(function(){
				   alert(123);
				   $('#dlg-import').dialog('open').dialog('setTitle','课外活动、讲座情况的导入');
			   });
			   
			   //导入
			 $("#import").click(function(){
				 var year =  $("#cbYearContrast1").combobox('getValue'); 
//				 alert(year);
				 $('#batchForm').form('submit',{
		    		 url: 'pages/T616/uploadFile',
		    		 type: "post",
			         dataType: "json",
		    		 onSubmit: function(){
					       return check() ;
						   },
		    		 success: function(result){
		    		 	var result = eval('('+result+')');
//		    		 	alert(result);
		    		 	if (!result.success){
		    		 		$.messager.show({
		    		 			title: 'Error',
		    		 			msg: result.errorMsg
		    			 });
		    		 		  $('#dlg-import').dialog('close');
		    		 			$('#cbYearContrast').combobox('select',year);
		    		 		  	reloadgrid (year,true) 
			   					$('#edit').propertygrid('loadData', rows);
		    		 		  	
		    		 	} else {
		    		 		alert(123);
				    		  $('#dlg-import').dialog('close'); // close the dialog
				    		   reloadgrid(newValue,true)
								$('#edit').propertygrid('loadData', rows);
		    		 	}
		    		 }
		    		 });
			 });
			 
			 function check(){
			    	var fileName = $('#uploadFile').val() ;
			    	
			    	if(fileName == null || fileName == ""){
				    	$.messager.alert("操作提示","请选择一个Excel文件！");
			    		return false ;
			    	}
			    	
			    	var pos = fileName.lastIndexOf(".") ;
			    	var suffixName = fileName.substring(pos, fileName.length) ;
			    	
			    	if(suffixName == ".xls"){
			    		return true ;
			    	}else{
			    		$.messager.alert("操作提示","文件格式错误，请选择后缀为“.xls”的文件！");
			    		return false ;
			    	}
			    } 
			     
	});
	
		//全局变量，用来暂存当前的url值
	   var url;
	   alert(222);
		//单条导入
		function singleImport() {
			//alert(url);
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

	function edit() {
	   	var row = $('#showData').datagrid('getSelections');
	   	
	   	if(row.length != 1){
	   		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
	   		return ;
	   	}
	   	
	   	if(row[0].stuType=="总计"){
	   		$.messager.alert('温馨提示', "合计信息不可编辑！！！") ;
	   		return;
	   	}
	   	
	   	var year = $("#cbYearContrast").combobox('getValue'); 
	   	url = 'pages/T616/edit?selectYear='+year; 
	   	     	
	   	$('#dlg').dialog('open').dialog('setTitle','编辑所选学生人数');
	   	$('#seqNumber').val(row[0].seqNumber) ;
    	$('#stuType').val(row[0].stuType) ;
    	$("input#stuType").attr("readonly",true);
    	$("input#stuType").css({"color":"#888"});
    	
	  	$('#sumGraNum').numberbox('setValue',row[0].sumGraNum); 
	  	$('#graOutNum').numberbox('setValue',row[0].graOutNum); 
	  	$('#graHongNum').numberbox('setValue',row[0].graHongNum); 
	  	$('#graAoNum').numberbox('setValue',row[0].graAoNum); 
	  	$('#graTaiNum').numberbox('setValue',row[0].graTaiNum);
	  	$('#sumDegreeNum').numberbox('setValue',row[0].sumDegreeNum); 
	  	$('#degreeOutNum').numberbox('setValue',row[0].degreeOutNum); 
	  	$('#degreeHongNum').numberbox('setValue',row[0].degreeHongNum); 
	  	$('#degreeAoNum').numberbox('setValue',row[0].degreeAoNum); 
	  	$('#degreeTaiNum').numberbox('setValue',row[0].degreeTaiNum);
	  	$('#sumAdmisNum').numberbox('setValue',row[0].sumAdmisNum); 
	  	$('#admisOutNum').numberbox('setValue',row[0].admisOutNum); 
	  	$('#admisHongNum').numberbox('setValue',row[0].admisHongNum); 
	  	$('#admisAoNum').numberbox('setValue',row[0].admisAoNum); 
	  	$('#admisTaiNum').numberbox('setValue',row[0].admisTaiNum); 
	  	$('#sumInSchNum').numberbox('setValue',row[0].sumInSchNum); 
	  	$('#inSchOutNum').numberbox('setValue',row[0].inSchOutNum); 
	  	$('#inSchHongNum').numberbox('setValue',row[0].inSchHongNum); 
	  	$('#inSchAoNum').numberbox('setValue',row[0].inSchAoNum); 
	  	$('#inSchTaiNum').numberbox('setValue',row[0].inSchTaiNum); 
	  	
	  	
		}
