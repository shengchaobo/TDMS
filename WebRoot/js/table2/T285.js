	//全局变量，用来控制字段合并次数
	var count = 0;

	//只是用来展示的数据
	$(function() {
		year = $("#cbYearContrast").combobox('getValue'); 
		$('#newData').datagrid({
			//title : '',  //可变内容在具体页面定义
			url: 'pages/T285/loadInfo?selectYear=' + year,
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
					//设置表格状态
					if(rowData.rows[0].checkState!=0){  								
	    				if(rowData.rows[0].checkState==WAITCHECK){
	    					if(count==0){
	    						count++;
		    					$("#newData").datagrid({title:'教学、科研仪器设备（<font color=red>待审核</font>）'});
	    					}else{
		    					$("#newData").datagrid("getPanel").panel("setTitle","教学、科研仪器设备（<font color=red>待审核</font>）");
	    					}
	    					$("#edit").show();
		    				$("#export").hide();
	    				}
	    				else if(rowData.rows[0].checkState==PASSCHECK){	
	    					if(count==0){
	    						count++;
		    					$("#newData").datagrid({title:'教学、科研仪器设备（<font color=red>审核通过</font>）'});
	    					}else{
		    					$("#newData").datagrid("getPanel").panel("setTitle","教学、科研仪器设备（<font color=red>审核通过</font>）")
	    					}
	    					$("#edit").hide();
	    					$("#export").show();
	    				}				    				
	    				else if(rowData.rows[0].checkState==NOPASSCHECK){
	    					if(count==0){
	    						count++;
		    					$("#newData").datagrid({title:'教学、科研仪器设备（<font color=red>审核未通过</font>）'});
	    					}else{
		    					$("#newData").datagrid("getPanel").panel("setTitle","教学、科研仪器设备（<font color=red>审核未通过</font>）");
	    					}
	    					$("#edit").show();
		    				$("#export").hide();
	    				}
					}else{
						alert("该年数据为空");
    					if(count==0){
    						count++;
        					$("#newData").datagrid({title:'教学、科研仪器设备'});
    					}else{
	    					$("#newData").datagrid("getPanel").panel("setTitle","教学、科研仪器设备");
    					}
    					$("#edit").show();
					}
			}
		});
		
		
	//刷新页面
	 $("#cbYearContrast").combobox({  
	        onChange:function(newValue, oldValue){  
				$('#newData').datagrid( {
					//title : '',  //可变内容在具体页面定义
					url: 'pages/T285/loadInfo?selectYear=' + newValue,
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
								//设置表格状态
								if(rowData.rows[0].checkState!=0){  
				    				if(rowData.rows[0].checkState==WAITCHECK){
				    					$("#newData").datagrid("getPanel").panel("setTitle","教学、科研仪器设备（<font color=red>待审核</font>）");
				    					//$("#newData").datagrid('setTitle','教学、科研仪器设备（<font color=red>待审核</font>）');
				    					$("#edit").show();
					    				$("#export").hide();
				    				}
				    				else if(rowData.rows[0].checkState==PASSCHECK){			
				    					$("#newData").datagrid("getPanel").panel("setTitle","教学、科研仪器设备（<font color=red>审核通过</font>）")
				    					//$("#newData").datagrid('setTitle','教学、科研仪器设备（<font color=red>审核通过</font>）');
				    					$("#edit").hide();
				    					$("#export").show();
				    				}				    				
				    				else if(rowData.rows[0].checkState==NOPASSCHECK){
				    					$("#newData").datagrid("getPanel").panel("setTitle","教学、科研仪器设备（<font color=red>审核未通过</font>）");
				    					//$("#newData").datagrid('setTitle','教学、科研仪器设备（<font color=red>审核未通过</font>）');
				    					$("#edit").show();
					    				$("#export").hide();
				    				}
								}else{
									alert("该年数据为空");
			    					$("#newData").datagrid("getPanel").panel("setTitle","教学、科研仪器设备");
			    					//$("#newData").datagrid('setTitle','教学、科研仪器设备');
			    					$("#edit").show();
								}							
					}
				});
	       }
	   }); 
		 
	   //导出
	    $("#export").click(function(){
	        var tableName = encodeURI('表2-8-5教学、科研仪器设备（设备处）');
	        var year = $("#cbYearContrast").combobox('getValue'); 
		    $('#exportForm').form('submit', {
		    	data : $('#exportForm').serialize(),
			    url : "pages/T285/dataExport?excelName="+tableName+'&selectYear='+year,
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
						if(result.tag==2){
							$('#dlg').dialog('close');
							myMarquee('T285', CTypeThree);
							$('#newData').datagrid('reload'); // reload the user data
						}else{
							$('#dlg').dialog('close');
							$('#newData').datagrid('reload'); // reload the user data
						}
					}
				}
				});
		}

	function edit() {
			
	   	var row = $('#newData').datagrid('getSelections');
	   	
	   	if(row.length != 1){
	   		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
	   		return ;
	   	}
	   	//alert(row[0].donaName);
	   	if(row[0].teaUnit=="全校合计："){
	   		$.messager.alert('温馨提示', "合计信息不可编辑！！！") ;
	   		return;
	   	}
	   	
	   	var year = $("#cbYearContrast").combobox('getValue'); 
	   	url = 'pages/T285/edit?selectYear='+year; 
	   	     	
	   	$('#dlg').dialog('open').dialog('setTitle','编辑所选学院教学、科研仪器设备信息');
	   	$('#seqNumber').val(row[0].seqNumber) ;
    	$('#teaUnit').val(row[0].teaUnit) ;
    	$('#unitID').val(row[0].unitID) ;
    	$("input#teaUnit").attr("readonly",true);
    	$("input#teaUnit").css({"color":"#888"});
    	$("input#unitID").attr("readonly",true);
    	$("input#unitID").css({"color":"#888"});
	  	$('#sumEquNum').numberbox('setValue',row[0].sumEquNum); 
	  	$('#aboveTenEquNum').numberbox('setValue',row[0].aboveTenEquNum); 
	  	$('#sumEquAsset').numberbox('setValue',row[0].sumEquAsset); 
	  	$('#newAddAsset').numberbox('setValue',row[0].newAddAsset); 
	  	$('#aboveTenEquAsset').numberbox('setValue',row[0].aboveTenEquAsset); 
		}
