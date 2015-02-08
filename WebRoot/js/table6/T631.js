	//全局变量，用来控制字段合并次数
	var count = 0;	

	//只是用来展示的数据
	$(function() {
		var year = $("#cbYearContrast").combobox('getValue'); 
		$('#newData').datagrid( {
			title : '',  //可变内容在具体页面定义
			url: 'pages/T631/loadInfo?selectYear=' +year,
			iconCls : 'icon-ok',
			width : '100%',
			//height: '100%',
			nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
			striped : true,//设置为true将交替显示行背景。
			collapsible : true,//显示可折叠按钮
			toolbar : "#toolbar",//在添加 增添、删除、修改操作的按钮要用到这个
			singleSelect : false,//为true时只能选择单行
//			fitColumns : true,//允许表格自动缩放，以适应父容器
			//sortName : 'xh',//当数据表格初始化时以哪一列来排序
			//sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。
			remoteSort : false,
			rownumbers : true,
			onLoadSuccess: function (rowData) {	    	  				            
					if(rowData.rows.length == 0){
						alert("该年数据为空");
						if(count == 0){
							count++;
							$("#newData").datagrid({title:'分专业应届本科毕业生毕业情况'});						
						}else{
	    					$("#newData").datagrid("getPanel").panel("setTitle","分专业应届本科毕业生毕业情况");
						}   					
    					$("#newObject").show();
    					$("#edit").show();
    					$("#delete").show();
					}else{
					            //alert(rowData.rows[0].checkState);
								//设置表格状态
								if(rowData.rows[0].checkState!=0){  		
				    				if(rowData.rows[0].checkState==WAITCHECK){
										if(count == 0){
											count++;
					    					$("#newData").datagrid({title:'分专业应届本科毕业生毕业情况（<font color=red>待审核</font>）'});											
										}else{
					    					$("#newData").datagrid("getPanel").panel("setTitle","分专业应届本科毕业生毕业情况（<font color=red>待审核</font>）");
										}
				    					$("#newObject").show();
				    					$("#edit").show();
				    					$("#delete").show();
					    				$("#export").hide();
				    				}
				    				else if(rowData.rows[0].checkState==PASSCHECK){	
										if(count == 0){
											count++;
					    					$("#newData").datagrid({title:'分专业应届本科毕业生毕业情况（<font color=red>审核通过</font>）'});											
										}else{
					    					$("#newData").datagrid("getPanel").panel("setTitle","分专业应届本科毕业生毕业情况（<font color=red>审核通过</font>）");
										}
				    					$("#newObject").hide();
				    					$("#edit").hide();
				    					$("#delete").hide();
					    				$("#export").show();
				    				}				    				
				    				else if(rowData.rows[0].checkState==NOPASSCHECK){
										if(count == 0){
											count++;
					    					$("#newData").datagrid({title:'分专业应届本科毕业生毕业情况（<font color=red>审核未通过</font>）'});											
										}else{
					    					$("#newData").datagrid("getPanel").panel("setTitle","分专业应届本科毕业生毕业情况（<font color=red>审核未通过</font>）");
										}
				    					$("#newObject").hide();
				    					$("#edit").show();
				    					$("#delete").show();
					    				$("#export").hide();
				    				}
								}
				        }
			}
		});
		
		
		//刷新页面
		 $("#cbYearContrast").combobox({  
	        onChange:function(newValue, oldValue){  
				$('#newData').datagrid( {
					//title : '专科招生信息补充表',  //可变内容在具体页面定义
					url: 'pages/T631/loadInfo?selectYear=' + newValue,
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
								alert("该年数据为空");
		    					$("#newData").datagrid("getPanel").panel("setTitle","分专业应届本科毕业生毕业情况");
		    					$("#newObject").show();
		    					$("#edit").show();
		    					$("#delete").show();
		    					$("#export").hide();
							}else{
								//设置表格状态
								if(rowData.rows[0].checkState!=0){  								
				    				if(rowData.rows[0].checkState==WAITCHECK){
				    					$("#newData").datagrid("getPanel").panel("setTitle","分专业应届本科毕业生毕业情况（<font color=red>待审核</font>）");
				    					$("#newObject").show();
				    					$("#edit").show();
				    					$("#delete").show();
					    				$("#export").hide();
				    				}
				    				else if(rowData.rows[0].checkState==PASSCHECK){			    				
				    					$("#newData").datagrid("getPanel").panel("setTitle","分专业应届本科毕业生毕业情况（<font color=red>审核通过</font>）");
				    					$("#newObject").hide();
				    					$("#edit").hide();
				    					$("#delete").hide();
					    				$("#export").show();
				    				}				    				
				    				else if(rowData.rows[0].checkState==NOPASSCHECK){
				    					$("#newData").datagrid("getPanel").panel("setTitle","分专业应届本科毕业生毕业情况（<font color=red>审核未通过</font>）");
				    					$("#newObject").hide();
				    					$("#edit").show();
				    					$("#delete").show();
					    				$("#export").hide();
				    				}
								}
						}
					}
				});
	        }
	    }); 
		
	   //导出
	   $("#export").click(function(){
	        var tableName = encodeURI('表6-3-1分专业应届本科毕业生毕业情况（教务处）');
	        var year = $("#cbYearContrast").combobox('getValue'); 
		    $('#exportForm').form('submit', {
		    	data : $('#exportForm').serialize(),
			    url : "pages/T631/dataExport?excelName="+tableName+'&selectYear='+year,
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

		
			var year = $("#cbYearContrast").combobox('getValue'); 
			url = 'pages/T631/insert?selectYear='+year; 
			$('#dlg').dialog('open').dialog('setTitle', '新增一条分专业应届本科毕业生毕业情况');
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
						if(result.tag==2){
							$('#dlg').dialog('close');
							myMarquee('T631', CTypeThree);
							$('#newData').datagrid('reload'); // reload the user data
						}else{
							$('#dlg').dialog('close');
							$('#newData').datagrid('reload'); // reload the user data
						}
					}
				}
			});
	}

	//对输入字符串进行验证
	function validate() {
			
		// 获取文本框的值

		var  num = /^\d+$/;  //用于判断字符串是否全是数字
		var unitId = $('#unitId').combobox('getText');
		var majorId = $('#majorId').combobox('getText');
		var thisYearGraduNum = $('#thisYearGraduNum').val();
		var thisYearNotGraduNum = $('#thisYearNotGraduNum').val();
		var awardDegreeNum = $('#awardDegreeNum').val();
		var note = $('#note').val();

		// 根据数据库定义的字段的长度，对其进行判断

		if (unitId == null || unitId.length == 0) {
			alert("教学单位不能为空");
			return false;
		}

		if (majorId == null || majorId.length == 0) {
			alert("专业名称不能为空");
			return false;
		}
		
		if (thisYearGraduNum == "" || thisYearNotGraduNum == "" || awardDegreeNum == "") {
			alert("请填写数字，若无请填写0");
			return false;
		}
		
		if (!thisYearGraduNum.match(num) || !thisYearNotGraduNum.match(num) || !awardDegreeNum.match(num)) {
		alert("请填写数字，若无请填写0");
		return false;
	}
		
		if(awardDegreeNum > thisYearGraduNum){
			alert("授予学位数应当小于等于应届毕业生数");
			return false;
		}


		if (note != null && note.length > 1000) {
			alert("备注中文字数不超过500");
			return false;
		}
		return true;
	}

	function edit() {
	
	   	var row = $('#newData').datagrid('getSelections');
	   	
	   	if(row.length != 1){
	   		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！") ;
	   		return ;
	   	}
	
	   	if(row[0].teaUnit=="全校合计"){
	   		alert("haha");
	   		$.messager.alert('温馨提示', "全校合计不可修改！！！") ;
	   		return;
	   	}
	   	
	   	var year = $("#cbYearContrast").combobox('getValue'); 
	   	url = 'pages/T631/edit?selectYear='+year; 
	   	     	
		$('#dlg').dialog('open').dialog('setTitle', '分专业应届本科毕业生毕业情况');
		$('#seqNumber').val(row[0].seqNumber);
		$('#unitId').combobox('select', row[0].unitId);
		$('#majorId').combobox('select', row[0].majorId);

		
		$('#thisYearGraduNum').val(row[0].thisYearGraduNum);
		$('#thisYearNotGraduNum').val(row[0].thisYearNotGraduNum);
		$('#awardDegreeNum').val(row[0].awardDegreeNum);
		$('#note').val(row[0].note);
		}
	
	//删除选中的行
	function deleteByIds() {
		   
		var year = $("#cbYearContrast").combobox('getValue'); 
		// 获取选中项
		var row = $('#newData').datagrid('getSelections');
		if (row.length == 0) {
			$.messager.alert('温馨提示', "请选择需要删除的数据！！！");
			return;
		}
		
		for ( var i = 0; i < row.length; i++) {
		   	if(row[i].teaUnit=="全校合计"){
		   		$.messager.alert('温馨提示', "删除行中不能包括全校合计！！！") ;
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
	   		url : "pages/T631/deleteByIds?ids=" + ids+"&selectYear="+year,
	   		async : "true",
	   		dataType : "text",
	   		success : function(result) {
				result = eval("(" + result + ")");
				if (result.state) {
					alert(result.data);
					myMarquee('T631', CTypeThree);
					$('#newData').datagrid('reload'); // reload the user data
				}
			}
	   	}).submit();
	   }