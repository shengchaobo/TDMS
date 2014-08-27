	//只是用来展示的数据
	$(function() {
		
		var year = $("#cbYearContrast").combobox('getValue'); 
		$('#showData').datagrid( {
			title : '专业认证（评估）情况统计',  //可变内容在具体页面定义
			url: 'pages/S322/loadInfo',
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
			async : false,
			type : "POST",
			onLoadSuccess: function (rowData) {	
				if(typeof(rowData.rows[0].data) != "undefined"){
					alert(rowData.rows[0].data);
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
	        var tableName = encodeURI('S-3-2-2专业认证（评估）情况统计');
	        var year = $("#cbYearContrast").combobox('getValue'); 
		    $('#exportForm').form('submit', {
		    	data : $('#exportForm').serialize(),
			    url : "pages/S322/dataExport?excelName="+tableName+'&selectYear='+year,
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
	
	
	   function formattime(val) {  
		    if(val == null){
			    return null ;
		    }
		    var year=parseInt(val.year)+1900;  
		    var month=(parseInt(val.month)+1);  
		    month=month>9?month:('0'+month);  
		    var date=parseInt(val.date);  
		    date=date>9?date:('0'+date);  
		    var hours=parseInt(val.hours);  
		    hours=hours>9?hours:('0'+hours);  
		    var minutes=parseInt(val.minutes);  
		    minutes=minutes>9?minutes:('0'+minutes);  
		    var seconds=parseInt(val.seconds);  
		    seconds=seconds>9?seconds:('0'+seconds);  
		    var time=year+'-'+month+'-'+date ;  
		    //alert(time) ;
		     return time;  
		} 

            	