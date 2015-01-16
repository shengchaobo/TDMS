$(function() {
	$('#commomData').datagrid( {
		//title : '', 可变内容在具体页面定义
		iconCls : 'icon-ok',
		width : '100%',
		//height: '100%',
		pageSize : 10,//默认选择的分页是每页5行数据
		pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合
		nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
		striped : true,//设置为true将交替显示行背景。
		collapsible : true,//显示可折叠按钮
		toolbar : "#toolbar",//在添加 增添、删除、修改操作的按钮要用到这个
		//url : 'pages/T411/loadTeaInfo',//url调用Action方法，可变内容在具体页面定义
		loadMsg : '数据装载中......',
		singleSelect : false,//为true时只能选择单行
		fitColumns : false,//允许表格自动缩放，以适应父容器
		//sortName : 'xh',//当数据表格初始化时以哪一列来排序
		//sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。
		remoteSort : false,
		pagination : true,//分页
		rownumbers : true
	//行数
	});
});

//只是用来展示的数据
$(function() {
	$('#showData').datagrid( {
		//title : '', 可变内容在具体页面定义
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
		rownumbers : true
	//行数
	});
});

$(function() {
	$('#unverfiedData').datagrid( {
		title : '待审核数据域审核未通过数据',
		iconCls : 'icon-ok',
		width : '100%',
		//height: '50%',
		pageSize : 10,//默认选择的分页是每页5行数据
		pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合
		nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
		striped : true,//设置为true将交替显示行背景。
		collapsible : true,//显示可折叠按钮
		toolbar : "#toolbar",//在添加 增添、删除、修改操作的按钮要用到这个
		//url : 'pages/table4/loadTeaInfo',//url调用Action方法
		loadMsg : '数据装载中......',
		singleSelect : false,//为true时只能选择单行
		fitColumns : false,//允许表格自动缩放，以适应父容器
		//sortName : 'xh',//当数据表格初始化时以哪一列来排序
		//sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。
		remoteSort : false,
		pagination : true,//分页
		rownumbers : true
	//行数
	});
});

$(function() {
	$('#verfiedData').datagrid( {
		title : '审核通过数据',
		iconCls : 'icon-ok',
		width : '100%',
		pageSize : 10,//默认选择的分页是每页5行数据
		pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合
		nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
		striped : true,//设置为true将交替显示行背景。
		collapsible : true,//显示可折叠按钮
		toolbar : "#toolbar2",//在添加 增添、删除、修改操作的按钮要用到这个
		//url : 'pages/table4/loadTeaInfo',//url调用Action方法
		loadMsg : '数据装载中......',
		singleSelect : false,//为true时只能选择单行
		fitColumns : false,//允许表格自动缩放，以适应父容器
		//sortName : 'xh',//当数据表格初始化时以哪一列来排序
		//sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。
		remoteSort : false,
		pagination : true,//分页
		rownumbers : true
	//行数
	});
});

$(function() {
		$('#checkData').datagrid({  
			title : '待审核数据', //可变内容在具体页面定义
			iconCls : 'icon-ok',
			width : '100%',
			//height: '50%',
			pageSize : 10,//默认选择的分页是每页5行数据
			pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合
			nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
			striped : true,//设置为true将交替显示行背景。
			collapsible : true,//显示可折叠按钮
			toolbar : "#toolbar",//在添加 增添、删除、修改操作的按钮要用到这个
			loadMsg : '数据装载中......',
			singleSelect : false,//为true时只能选择单行
			fitColumns : false,//允许表格自动缩放，以适应父容器
			remoteSort : false,
			pagination : true,//分页
			rownumbers : true
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

function formatBoolean(val) {  
    if(val == true){
	    return '是' ;
    }else if(val == false){
    	return '否' ;
    }else{
    	return null;
    }
}  


//三种审核状态
var WAITCHECK = 1;     //待审核
var PASSCHECK = 2;     //审核通过  
var NOPASSCHECK = 4;   //审核未通过

//三种审核方式
var CTypeOne = 1;     //普通多条审核
var CTypeTwo = 2;     //教学单位类审核
var CTypeThree = 3;   //单表审核


function formatCheckState(val) {  
    if(val == WAITCHECK){
	    return '待审核状态' ;
    }else if(val == NOPASSCHECK){
    	return '审核未通过' ;
    }else{
    	return null;
    }
}

//审核功能中调用审核信息
function myMarquee(tableID,checkType){
	    $.ajax({
		    type:"POST", 
		    url: "loadCheckInfo?tableName=" + tableID + "&checkType=" + checkType, 
	   		async : "true",
	   		dataType : "text",
		    success:function(result){  						    	 
		       var result = eval('(' + result + ')'); 
		       document.getElementById("marquee").innerHTML	 = result.data ;
		    }
	});
}

function rowformater(value,row,index)
{
		return "<a href='javascript:passCheck(" + row.seqNumber+")'>审核通过</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href ='javascript:openDig("+ row.seqNumber+ "," + row.fillUnitID +")'>审核不通过</a>";
}

//打开填写框
function openDig(seqNumber, fillUnitID) {
	if(typeof(seqNumber)!="undefined"){
		if(typeof(fillUnitID)!="undefined"){
			alert(fillUnitID);
			document.getElementById("dataID").value = seqNumber ; 
			document.getElementById("unitID").value = fillUnitID ; 
		}else{
			document.getElementById("dataID").value = seqNumber ; 
		}
		
	}else{
		var year = $("#cbYearContrast").combobox('getValue'); 
		document.getElementById("dataID").value = year ; 
	}
	$('#dlg').dialog('open').dialog('setTitle', '填写审核不通过理由');
}

//审核不通过
function addCheckInfo(){
            dataID = document.getElementById("dataID").value;
			$('#addReasonForm').form('submit', {
			url : 'addCheckInfo',
			data : $('#addReasonForm').serialize(),
			type : "post",
			dataType : "json",
			onSubmit : function() {
				return true;
			},
			//结果返回
			success : function(result) {
				//json格式转化
				var result = eval('(' + result + ')');
				$.messager.alert('温馨提示', result.data);
				if (result.state) {
					$('#dlg').dialog('close');	
					noPassCheck(dataID)
				}
			}
	});    
}

function validate() {
	//获取文本框的值
	alert("test");
	var reason = $('#noPassReason').val();		
	if( (reason == null) || (reason == '') ||  (reason.length == 0)){
		alert("理由不能为空!!!");
		return false;
	}		
	return true;
}


