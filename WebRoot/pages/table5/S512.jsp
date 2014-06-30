<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'table.jsp' starting page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
	<!--
		<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui/demo/demo.css">
	
	<style type="text/css">
		#fm {
			margin: 0;
			padding: 10px 30px;
		}
		
		.ftitle {
			font-size: 14px;
			font-weight: bold;
			padding: 5px 0;
			margin-bottom: 10px;
			border-bottom: 1px solid #ccc;
		}
		
		.fitem {
			margin-bottom: 5px;
		}
		
		.fitem label {
			display: inline-block;
			width: 120px;
		}
	</style>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="js/commom.js"></script>
	<script type="text/javascript" src="js/table5/T512.js"></script>
	<script type="text/javascript">
		function reloadgrid ()  { 
	        //查询参数直接添加在queryParams中 
	         var queryParams = $('#unverfiedData').datagrid('options').queryParams;  
	         queryParams.seqNum = $('#seqNum').val(); 
	         queryParams.startTime = $('#startTime').datetimebox('getValue');	         		     
	    	 queryParams.endTime  = $('#endTime').datetimebox('getValue');        	 
	         $("#unverfiedData").datagrid('reload'); 
    }
	</script>

	
</head>
<body style="overflow-y:scroll">
	<table id="unverfiedData" title="待审核数据域审核未通过数据" class="easyui-datagrid" style="width:100%px;height:300px" url=""
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="false" >
		
					<thead>
			<tr>
				<th data-options="field:'SeqNumber',align:'center'" rowspan="3">
				编号
				</th>
				<th data-options="field:'Term',align:'center'" rowspan="3">
			          开课单位
				</th>
				<th data-options="field:'CSUnit',align:'center'" rowspan="3">
				单位号
				</th>
				<th colspan="2">
				1.本科课程门次数
				</th>
				<th colspan="6">
				2.主讲本科课程的教师
				</th>
				<th colspan="2">
				3.授课情况
				</th>
			</tr>
			<tr>
				<th rowspan="2">
				总数
				</th>
				<th rowspan="2">
			         其中：小班授课
				</th>
				<th rowspan="2">
				总人数(人)
				</th>
				<th colspan="5">
				其中
				</th>
				<th rowspan="2">
				由教授授课的课程门次（门次）
				</th>
				<th rowspan="2">
				由副教授授课的课程门次（门次）
				</th>
			</tr>
			<tr>
				<th data-options="field:'CSName',align:'center'">
				符合岗位资格（人）
				</th>
				<th data-options="field:'CSID',align:'center'">
				教授（人）
				</th>
				<th data-options="field:'CSType',align:'center'">
				副教授（人）
				</th>
				<th data-options="field:'CSNature',align:'center' ">
				为低年级授课的教授（人）
				</th>
				<th data-options="field:'PubCSType',align:'center'">
				为低年级授课的副教授（人）
				</th>
			</tr>
			
			</thead>
					
		
	  
	</table>
	<div id="toolbar" style="height:auto">
		<div>
			<a href="pages/SchResIns/dataExport?excelName=表1-5-1校级以上科研机构（科研处）.xls" class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newObject()">数据导入</a>
			<a href='pages/SchResIns/downloadModel?saveFile=<%=URLEncoder.encode("表1-5-1校级以上科研机构（科研处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
		 	<form id="auditing" method="post" style="float: right;height: 24px;">
			 	编号: <input id="seqNum" name="seqNum" class="easyui-numberbox" style="width:80px"/>
				日期 起始: <input id="startTime" name="startTime" class="easyui-datebox" style="width:80px"/>
				结束: <input id="endTime" name="endTime" class="easyui-datebox" style="width:80px"/>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="reloadgrid()">查询</a>
			</form>
		</div>
		 
	</div>
	<!-- 
	<div id="toolbar2">
		<a href="pages/UndergraCSBaseTea/dataExport" class="easyui-linkbutton" iconCls="icon-download">数据导出</a>
	
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="loadDic()">高级检索</a>
	</div>
	<table id="verfiedData" title="审核通过数据" class="easyui-datagrid" style="width:100%px;height:250px" url=""
		toolbar="#toolbar2" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="false">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="SeqNumber" width=10>序号</th>
				<th field="ResInsName" width=10>科研机构名称</th>
				<th field="ResInsID" width=10>单位号</th>
				<th field="Type" width=10>类别</th>
				<th field="BuildCondition" width=10>共建情况</th>
				<th field="BiOpen" width=10 >是否对本科生开放</th>
				<th field="OpenCondition" width=10 >对本科生开放情况（500字以内）</th>
				<th field="TeaUnit" width=10>所属教学单位</th>
				<th field="UnitID" width=10>教学单位号</th>
				<th field="BeginYear" width=10 fit="true">开设年份</th>
				<th field="HouseArea" width=10>专业科研用房面积（平方米）</th>
				<th field="Note" width=10>备注</th>
			</tr>
		</thead>
	</table>
	 -->
	<div id="dlg" class="easyui-dialog"
		style="width:500px;height:150px;padding:10px 20px;" closed="true" data-options="modal:true">
		<div class="ftitle">S-5-1-1本科课程、授课情况统计情况导入</div>
		<div class="fitem">
			<form id="batchForm" method="post" enctype="multipart/form-data">
				<label>统计情况导入：</label> 
				<input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox"
					validType="fileType['xls']" required="true" invalidMessage="请选择Excel格式的文件" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">导入</a>
				<!-- 
				<a href='pages/SchResIns/downloadModel?saveFile=<%=URLEncoder.encode("表1-5-1校级以上科研机构（科研处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
				 -->
			</form>
		</div>
	</div>
	
	<div id="dicDlg" class="easyui-dialog" style="width:500px;padding:10px 20px" closed="true">
		<div class="ftitle">高级检索</div>
		<div id="dicTables"  class="fitem">
		</div>
		<div id="dices"  class="fitem"></div>
	</div>
</body>

	<script type="text/javascript"> 

	 function newObject(){
	    	//url = 'pages/T11/uploadFile' ;
		    $('#dlg').dialog('open').dialog('setTitle','导入学校基本信息');
		   // $('#resInsForm').form('reset');
	    }

	 function batchImport(){
    	 $('#batchForm').form('submit',{
    		 url: 'pages/SchResIns/uploadFile',
    		 type: "post",
	         dataType: "json",
    		 onSubmit: function(){
    			 return check() ;
    		 },
    		 success: function(result){
    		 	var result = eval('('+result+')');
    		 	if (!result.success){
    		 		$.messager.show({
    		 			title: 'Error',
    		 			msg: result.errorMsg
    			 });
    		 	} else {
		    		 $('#dlg').dialog('close'); // close the dialog
		    		 $('#dg').datagrid('reload'); // reload the user data
    		 	}
    		 }
    		 });
    }
    
    function check(){
    	var fileName = $('#uploadFile').val() ;
    	
    	if(fileName == null || fileName == ""){
    		 $.messager.alert("操作提示", "请先选择要导入的文件！");
    		return false ;
    	}
    	
    	var pos = fileName.lastIndexOf(".") ;
    	var suffixName = fileName.substring(pos, fileName.length) ;
    	
    	if(suffixName == ".xls"){
    		return true ;
    	}else{
    		 $.messager.alert("操作提示", "请选择正确的Excel文件（后缀为.xls）");
    		return false ;
    	}
    } 
	
	</script>
	<script type="text/javascript"> 
			//日期格式转换 
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
			</script>
			<script type="text/javascript"> 
		    function booleanstr(val) { 	 
		    	if(val == null){
					return null ;
				}
				var bo1=""+val;//吧boolean型转换成str类型再判断
				var boo;
				if( bo1 == "false") {
					boo="否" ;
				}else if (bo1 == "true"){

					boo="是" ;
				}
				return boo;
	        }  
			</script>
			
		
</html>
