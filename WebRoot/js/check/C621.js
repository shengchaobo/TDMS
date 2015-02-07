	//全局变量，用来控制字段合并次数
	var count = 0;	

	//只是用来展示的数据
	$(function() {
		var year = $("#cbYearContrast").combobox('getValue'); 
		$('#newData').datagrid( {
			//title : '社会捐赠情况',  //可变内容在具体页面定义
			url: 'pages/T621/loadInfo?selectYear=' +year,
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
						if(count == 0){
							count++;
							$("#newData").datagrid({title:'近一届本科生分专业招生录取情况'});						
						}else{
	    					$("#newData").datagrid("getPanel").panel("setTitle","近一届本科生分专业招生录取情况");
						}   
    					$("#pass").hide();
    					$("#nopass").hide();
					}else{
			            //alert(rowData.rows[0].checkState);
						//设置表格状态
						if(rowData.rows[0].checkState!=0){  		
		    				if(rowData.rows[0].checkState==WAITCHECK){
								if(count == 0){
									count++;
			    					$("#newData").datagrid({title:'近一届本科生分专业招生录取情况（<font color=red>待审核</font>）'});											
								}else{
			    					$("#newData").datagrid("getPanel").panel("setTitle","近一届本科生分专业招生录取情况（<font color=red>待审核</font>）");
								}
								$("#pass").show();
		    					$("#nopass").show();
		    				}
		    				else if(rowData.rows[0].checkState==PASSCHECK){			    				
								if(count == 0){
									count++;
			    					$("#newData").datagrid({title:'近一届本科生分专业招生录取情况（<font color=red>审核通过</font>）'});											
								}else{
			    					$("#newData").datagrid("getPanel").panel("setTitle","近一届本科生分专业招生录取情况（<font color=red>审核通过</font>）");
								}
								$("#pass").hide();
		    					$("#nopass").hide();
		    				}				    				
		    				else if(rowData.rows[0].checkState==NOPASSCHECK){
								if(count == 0){
									count++;
			    					$("#newData").datagrid({title:'近一届本科生分专业招生录取情况（<font color=red>审核未通过</font>）'});											
								}else{
			    					$("#newData").datagrid("getPanel").panel("setTitle","近一届本科生分专业招生录取情况（<font color=red>审核未通过</font>）");
								}				    					$("#pass").hide();
		    					$("#nopass").hide();
		    				}
						}
				}
			}
		});
		
		//刷新页面
		 $("#cbYearContrast").combobox({  
	        onChange:function(newValue, oldValue){  
				$('#newData').datagrid( {
					//title : '社会捐赠情况',  //可变内容在具体页面定义
					url: 'pages/T621/loadInfo?selectYear=' + newValue,
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
		    					$("#newData").datagrid("getPanel").panel("setTitle","近一届本科生分专业招生录取情况");
		    					$("#pass").hide();
		    					$("#nopass").hide();
							}else{
								//设置表格状态
								if(rowData.rows[0].checkState!=0){  								
				    				if(rowData.rows[0].checkState==WAITCHECK){
				    					$("#newData").datagrid("getPanel").panel("setTitle","近一届本科生分专业招生录取情况（<font color=red>待审核</font>）");
				    					$("#pass").show();
				    					$("#nopass").show();
				    				}
				    				else if(rowData.rows[0].checkState==PASSCHECK){			    				
				    					$("#newData").datagrid("getPanel").panel("setTitle","近一届本科生分专业招生录取情况（<font color=red>审核通过</font>）");
				    					$("#pass").hide();
				    					$("#nopass").hide();
				    				}				    				
				    				else if(rowData.rows[0].checkState==NOPASSCHECK){
				    					$("#newData").datagrid("getPanel").panel("setTitle","近一届本科生分专业招生录取情况（<font color=red>审核未通过</font>）");
				    					$("#pass").hide();
				    					$("#nopass").hide();
				    				}
								}
						}
					}
				});
	        }
	    }); 
	 	 
	//审核通过
	$("#pass").click(function() {
		var year = $("#cbYearContrast").combobox('getValue');
		var checkNum = PASSCHECK;
		//alert(checkNum);
			$.ajax( {
				type : "POST",
				url : "pages/T621/updateCheck?selectYear=" + year + "&checkNum="
						+ PASSCHECK,
				async : "true",
				dataType : "text",
				success : function(result) {
					result = eval("(" + result + ")");
					if (!result.state) {
						$.messager.show( {
							title : 'Error',
							msg : result.data
						});
					} else {
						$('#newData').datagrid('reload'); // reload the user data
					}
				}
			});
		});		
   })	 

		//审核不通过
	function noPassCheck(year) {
		var checkNum = NOPASSCHECK;
		//alert(checkNum);
		$.ajax({
			type : "POST",
			url : "pages/T621/updateCheck?selectYear=" + year + "&checkNum="
					+ NOPASSCHECK,
			async : "true",
			dataType : "text",
			success : function(result) {
				result = eval("(" + result + ")");
				if (!result.state) {
					$.messager.show( {
						title : 'Error',
						msg : result.data
					});
				} else {
					$('#newData').datagrid('reload'); // reload the user data
				}
			}
		});
	}
