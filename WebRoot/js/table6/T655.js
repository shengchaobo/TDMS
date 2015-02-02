//全局变量，用来控制字段合并次数
	var count = 0;
//全局变量，用来暂存当前的url值
	var url;

//只是用来展示的数据
$(function() {
	year = $("#cbYearContrast").combobox('getValue'); 
	$('#newData').datagrid({
		//title : '',  //可变内容在具体页面定义
		url: 'pages/T655/loadInfo?selectYear=' + year,
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
		       if(count == 0 ) {				    	  				            
			            count++;
						//设置表格状态
						if(rowData.rows[0].checkState!=0){  								
		    				if(rowData.rows[0].checkState==WAITCHECK){
		    					$("#newData").datagrid({title:'学习成果-英语四六级、省计算机等级考试（<font color=red>待审核</font>）'});
		    					$("#edit").show();
			    				$("#export").hide();
		    				}
		    				else if(rowData.rows[0].checkState==PASSCHECK){			    				
		    					$("#newData").datagrid({title:'学习成果-英语四六级、省计算机等级考试（<font color=red>审核通过</font>）'});
		    					$("#edit").hide();
		    					$("#export").show();
		    				}				    				
		    				else if(rowData.rows[0].checkState==NOPASSCHECK){
		    					$("#newData").datagrid({title:'学习成果-英语四六级、省计算机等级考试（<font color=red>审核未通过</font>）'});
		    					$("#edit").show();
			    				$("#export").hide();
		    				}
						}else{
							alert("该年数据为空");
	    					$("#newData").datagrid({title:'学习成果-英语四六级、省计算机等级考试'});
	    					$("#edit").show();
						}
		     }
		}
	});

	
	//刷新页面
	 $("#cbYearContrast").combobox({  
	        onChange:function(newValue, oldValue){  
				$('#newData').datagrid( {
					//title : '',  //可变内容在具体页面定义
					url: 'pages/T655/loadInfo?selectYear=' + newValue,
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
				    					$("#newData").datagrid("getPanel").panel("setTitle","学习成果-英语四六级、省计算机等级考试（<font color=red>待审核</font>）")
				    					//$("#newData").datagrid('setTitle','教学、科研仪器设备（<font color=red>待审核</font>）');
				    					$("#edit").show();
					    				$("#export").hide();
				    				}
				    				else if(rowData.rows[0].checkState==PASSCHECK){			
				    					$("#newData").datagrid("getPanel").panel("setTitle","学习成果-英语四六级、省计算机等级考试（<font color=red>审核通过</font>）")
				    					//$("#newData").datagrid('setTitle','教学、科研仪器设备（<font color=red>审核通过</font>）');
				    					$("#edit").hide();
				    					$("#export").show();
				    				}				    				
				    				else if(rowData.rows[0].checkState==NOPASSCHECK){
				    					$("#newData").datagrid("getPanel").panel("setTitle","学习成果-英语四六级、省计算机等级考试（<font color=red>审核未通过</font>）");
				    					//$("#newData").datagrid('setTitle','教学、科研仪器设备（<font color=red>审核未通过</font>）');
				    					$("#edit").show();
					    				$("#export").hide();
				    				}
								}else{
									alert("该年数据为空");
			    					$("#newData").datagrid("getPanel").panel("setTitle","学习成果-英语四六级、省计算机等级考试");
			    					//$("#export").hide();
			    					//$("#newData").datagrid('setTitle','教学、科研仪器设备');
			    					$("#edit").show();
								}							
					}
				});
	       }
	   }); 
	 
	 //导出
	    $("#export").click(function(){
	        var tableName = encodeURI('表6-5-5学习成果-英语四六级、省计算机等级考试（教务处）');
	        var year = $("#cbYearContrast").combobox('getValue'); 
		    $('#exportForm').form('submit', {
		    	data : $('#exportForm').serialize(),
			    url : "pages/T655/dataExport?excelName="+tableName+'&selectYear='+year,
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
						myMarquee('T655', CTypeThree);
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

	if (row.length != 1) {
		$.messager.alert('温馨提示', "请选择1条编辑的数据！！！");
		return;
	}

 	var year = $("#cbYearContrast").combobox('getValue'); 
   	url = 'pages/T655/edit?selectYear='+year; 
	
	$('.title1').hide();
	$('#item1').hide();
	$('hr').hide();
	
	$('#dlg').dialog('open').dialog('setTitle', '学习成果—英语四六级、计算机等级考试通过率');
	$('#seqNumber').val(row[0].seqNumber);
	$('#teaUnit').val(row[0].teaUnit) ;
  	$('#unitID').val(row[0].unitId) ;
  	
	$("input#teaUnit").attr("readonly",true);
	$("input#teaUnit").css({"color":"#888"});
	$("input#unitID").attr("readonly",true);
	$("input#unitID").css({"color":"#888"});
	
	$('#CET4PassRate').numberbox('setValue',row[0].CET4PassRate);
	$('#CET6PassRate').numberbox('setValue',row[0].CET6PassRate);
	$('#jiangxiNCREPassRate').numberbox('setValue',row[0].jiangxiNCREPassRate);
	$('#note').val(row[0].note);
}

 
	 



//弹出添加的界面
function newItem() {
	url = 'pages/T655/insert' ; 

	$('.title1').show();
	$('#item1').show();
	$('hr').show();
	$('#dlg').dialog('open').dialog('setTitle', '学习成果—英语四六级、计算机等级考试通过率');
	$('#addItemForm').form('reset');
}

//数据验证
function validate() {
	// 获取文本框的值
	
	var  num = /^\d+$/;  //用于判断字符串是否全是数字
	
	
	var unitId = $('#unitId').combobox('getText');
	var CET4PassRate = $('#CET4PassRate').numberbox('getValue');
	var CET6PassRate = $('#CET6PassRate').numberbox('getValue');
	var jiangxiNCREPassRate = $('#jiangxiNCREPassRate').numberbox('getValue');
	var time = $('#time').datetimebox('getValue');
	var note = $('#note').val();



	// 根据数据库定义的字段的长度，对其进行判断

	if (unitId == null || unitId.length == 0) {
		alert("教学单位不能为空");
		return false;
	}
	
	if (CET4PassRate == null || CET4PassRate.length == 0) {
		alert("英语四级考试累计通过率不能为空");
		return false;
	}
	
	if (CET6PassRate == null || CET6PassRate.length == 0) {
		alert("英语六级考试累计通过率不能为空");
		return false;
	}
	
//
//	
//	if (awardStuNum == "" || guideTeaNum == "") {
//		alert("获奖学生数或指导教师部分请填写数字，若无请填写0");
//		return false;
//	}
//	
//	if (!awardStuNum.match(num) || !guideTeaNum.match(num)) {
//	alert("获奖学生数或指导教师部分请填写数字，若无请填写0");
//	return false;
//	}
	
	if (time == null || time.length == 0) {
	
		alert("请选择时间");
		return false;
	}

	if (note != null && note.length > 1000) {
		alert("备注中文字数不超过500");
		return false;
	}
	return true;
}



function reloadgrid ()  { 
    //查询参数直接添加在queryParams中 
	 var  seqNum = $('#seqNum').val();
     var startTime = $('#startTime').datetimebox('getValue');
     var endTime = $('#endTime').datetimebox('getValue');
     var queryParams = $('#commomData').datagrid('options').queryParams;  
     queryParams.seqNum = seqNum;  
     queryParams.startTime = startTime;  
     queryParams.endTime = endTime;  
     $("#commomData").datagrid('reload'); 
}	

//模板导入
function batchImport(){

	  var fileName = $('#uploadFile').val() ; 	
	  if(fileName == null || fileName == ""){
		  $.messager.alert('Excel批量用户导入', '请选择将要上传的文件!');      
	   		return false ;
	  }	
	  var pos = fileName.lastIndexOf(".") ;
	  var suffixName = fileName.substring(pos, fileName.length) ; 	
	  if(suffixName != ".xls"){
		   $.messager.alert('Excel批量用户导入','文件类型不正确，请选择.xls文件!');   
	   		return false ;
	 }
	 $('#batchForm').form('submit',{
		 url: 'pages/T655/uploadFile',
		 type: "post",
	     dataType: "json",
		 onSubmit: function(){
			 return true;
		 },
		 success: function(result){
		 	var result = eval('('+result+')');
		 	if (!result.success){
		 		$.messager.show({
		 			title: 'Error',
		 			msg: result.errorMsg
			 });
		 	} else {
		 		$.messager.show({
		 			title: 'Success',
		 			msg: result.errorMsg
		 		});
		    		 $('#dlg').dialog('close'); // close the dialog
		    		 $('#commomData').datagrid('reload'); // reload the user data
		 	}
		 }
		 });
 }
