<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
		<link rel="stylesheet" type="text/css"
			href="jquery-easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css"
			href="jquery-easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css"
			href="jquery-easyui/demo/demo.css">

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
	width: 80px;
}
</style>
		<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
		<script type="text/javascript" src="jquery-easyui/jquery-1.7.2.min.js"></script>
		<script type="text/javascript"
			src="jquery-easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript"
			src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript"
			src="jquery-easyui/jquery-migrate-1.2.1.min.js"></script>
		<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>

	</head>
	<body style="overflow-y: scroll">

		<table id="dg" class="easyui-datagrid" title="Row Editing in DataGrid"
			style="width: 700px; height: 500px"
			data-options="
			iconCls: 'icon-edit',
			singleSelect:true,
			toolbar: '#tb',
			url: 'pages/T16/auditingData',
			method: 'post',
			onClickRow: onClickRow
			">
			<thead>
				<tr>
					<th data-options="field:'item',width:80,editor:'text'">
						项目
					</th>
					<th
						data-options="field:'contents',width:80,align:'right',editor:'text'">
						内容
					</th>
					<th data-options="field:'note',width:250,editor:'text'">
						备注
					</th>
					<th data-options="field:'status',width:60,align:'center',editor:{type:'checkbox',options:{on:'P',off:''}}">
						状态
					</th>
				</tr>
			</thead>
		</table>

		<div id="tb" style="height: auto">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save',plain:true" onclick="accept()">保存</a>
		</div>
	
	  <script type="text/javascript">
        var editIndex = undefined;
        function endEditing(){
            if (editIndex == undefined){return true}
            if ($('#dg').datagrid('validateRow', editIndex)){
                editIndex = undefined;
                return true;
            } else {
                return false;
            }
        }
        function onClickRow(index){
            if (editIndex != index){
                if (endEditing()){
                    alert(123) ;
                    $('#dg').datagrid('selectRow', index)
                            .datagrid('beginEdit', index);
                    editIndex = index;
                } else {
                    $('#dg').datagrid('selectRow', editIndex);
                }
            }
        }
        function append(){
        	if (endEditing()){
                $('#dg').datagrid('appendRow',{status:'P'});
                editIndex = $('#dg').datagrid('getRows').length-1;
                $('#dg').datagrid('selectRow', editIndex)
                        .datagrid('beginEdit', editIndex);
            }
        }
        function removeit(){
            if (editIndex == undefined){return}
            $('#dg').datagrid('cancelEdit', editIndex)
                    .datagrid('deleteRow', editIndex);
            editIndex = undefined;
        }
        function accept(){
            if (endEditing()){
            	$('#dg').datagrid('acceptChanges') ;
                var row = $('#dg').datagrid('getSelected');
                var url = "pages/T16/insert?t16Bean.item=" + row.item + "&t16Bean.contents=" + row.contents ;
            }
        }
        function reject(){
            $('#dg').datagrid('rejectChanges');
            editIndex = undefined;
        }
        function getChanges(){
            var rows = $('#dg').datagrid('getChanges');
            alert(rows.length+' rows are changed!');
        }
    </script>
</body>
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

</html>
