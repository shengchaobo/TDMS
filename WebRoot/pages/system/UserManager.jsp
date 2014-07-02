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

<title>用户管理</title>
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
<script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="jquery-easyui/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>

</head>
<body style="overflow-y:scroll">
	<table id="userManager" title="用户管理" class="easyui-datagrid"
		style="width:100%px;height:'auto'" url="pages/UserManager/loadUsers"
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="false">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="teaID" width="10">职工号</th>
				<th field="teaName" width="10">姓名</th>
				<th field="fromOffice" width="10">单位号</th>
				<th field="unitID" width="10">单位名称</th>
				<th field="teaEmail" width="10">电子邮箱</th>
				<th field="userNote" width="10">备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="height:auto">
		<div>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="newUser()">添加用户</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="editUser()">编辑用户</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="deleteByIds()">删除用户</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="resetPassword()">密码重置</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" plain="true" onclick="addRole()">添加角色</a>
		</div>
		<div>
			<form id="auditing" method="post">
				序号: <input id="seqNum" name="seqNum" class="easyui-numberbox"
					style="width:80px" /> <a href="javascript:void(0)"
					class="easyui-linkbutton" iconCls="icon-search"
					onclick="singleSearch()">查询</a>
			</form>
		</div>
	</div>
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true"
		data-options="modal:true" buttons="#dlg-buttons">
		<div class="ftitle">用户管理</div>
		<form id="userManagerForm" method="post">
			<table>
				<tr>
					<td>
						<div class="fitem">
							<label>职工号：</label> 
							<input id="seqNumber" type="hidden" 
								name="userinfo.SeqNumber" value="0"></input> 
							<input id="TeaID" type="text" name="userinfo.TeaID"
								class="easyui-validatebox" required="true">
							<span id="TeaIDSpan"></span>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="fitem">
							<label>姓名：</label> 
							<input id="TeaName" type="text"
								name="userinfo.TeaName" class="easyui-validatebox"
								required="true">
							<span id="TeaNameSpan"></span>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="fitem">
							<label>职工单位：</label>
							<!-- 下边的onselect方法是为了后台既要教学单位名称，有需要教学单位编号，而我们只有一个下拉框包含了这两条信息 -->
							<input type="hidden" name="userinfo.FromOffice" id="FromOffice" />
							<input id="UnitID" name="userinfo.UnitID"
								class='easyui-combobox'
								data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDiDepartment',listHeight:'auto',editable:false,
							 	onSelect:function(){
							 		document.getElementById('FromOffice').value=$(this).combobox('getText') ;
							 	}">
							<span id="UnitIDSpan"></span>
						  </div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="fitem">
								<label>电子邮箱：</label> 
								<input type="text" name="userinfo.TeaEmail" 
									class="easyui-validatebox" id="TeaEmail" required='true'/>
								<span id="TeaEmailSpan"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="fitem">
								<label>备&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
								<textarea id="Note" name="userinfo.UserNote" style="resize:none" cols="50" rows="10"></textarea>
								<span id="NoteSpan"></span>
							</div>
						</td>
					</tr>
			</table>
		</form>
	</div>

	<div id="roleDlg" class="easyui-dialog"
		style="width:500px;padding:10px 20px" closed="true"
		data-options="modal:true" buttons="#roleDlg-buttons">
		<div class="ftitle">添加用户角色</div>
		<div id="dicTables" class="fitem"></div>
		<form id="roleForm">
			<table>
				<tr>
					<td>
						<div class="fitem">
							<label>用户角色：</label>
							<input type="hidden" name="userId" id="userId" />
							<!-- 下边的onselect方法是为了后台既要教学单位名称，有需要教学单位编号，而我们只有一个下拉框包含了这两条信息 -->
							<input type="hidden" name="roleName" id="roleName" />
							<input id="RoleID" name="RoleID"
								class='easyui-combobox'
								data-options="valueField:'roleID',textField:'roleName',url:'pages/diRole/loadDIRoles',listHeight:'auto',editable:false,
							 	onSelect:function(){
							 		document.getElementById('roleName').value=$(this).combobox('getText') ;
							 	}">
							<span id="RoleIDSpan"></span>
						  </div>
						</td>
					</tr>
			</table>
		</form>
	</div>
	
	<div id="roleDlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="addUserRole()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#roleDlg').dialog('close')">取消</a>
	</div>
	
	<div id="dicDlg" class="easyui-dialog"
		style="width:500px;padding:10px 20px" closed="true">
		<div class="ftitle">高级检索</div>
		<div id="dicTables" class="fitem"></div>
		<div id="dices" class="fitem"></div>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="singleImport()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
</body>
<script type="text/javascript">
	var url;
	var req = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/ ;
	
	function singleSearch() {
		$('#auditing').form('submit', {
			url : 'pages/UndergraCSBaseTea/singleSearch',
			type : "post",
			dataType : "json",
			success : function(result) {
				var result = eval('(' + result + ')');
				if (!result.state) {
					$.messager.show({
						title : 'Error',
						msg : result.errorMsg
					});
				} else {
					alert(13113);
					$('#userManger').datagrid('load'); // reload the auditing data
				}
			}
		});
	}

	function newUser() {
		url = 'pages/UserManager/insert';
		$('#dlg').dialog('open').dialog('setTitle', '添加用户');
		$('#userManagerForm').form('reset');
	}

	function singleImport() {
		//录入数据的表单提交
		$('#userManagerForm').form('submit', {
			url : url,
			data : $('#userManagerForm').serialize(),
			type : "post",
			dataType : "json",
			onSubmit : function() {
				return validate();
			},
			//结果返回
			success : function(result) {
				//json格式转化
				var result = eval('(' + result + ')');
				$.messager.alert('温馨提示', result.data);
				if (result.state) {
					$('#dlg').dialog('close');
					$('#userManager').datagrid('reload');
				}
			}
		});
	}

	function validate() {
		//获取文本框的值
		var teaId = $('#TeaID').val();
		var teaName = $('#TeaName').val();
		var csUnit = $('#UnitID').combobox('getText');
		var teaEmail = $('#TeaEmail').val();
		var note = $('#Note').val();
		//根据数据库定义的字段的长度，对其进行判断
		if (teaId == null || teaId.length == 0 || teaId.length > 50) {
			$('#TeaID').focus();
			$('#TeaID').select();
			$('#TeaIDSpan').html(
					"<font style=\"color:red\">教职工编号不能为空或长度不超过50</font>");
			return false;
		} else {
			$('#TeaIDSpan').html("");
		}

		if (teaName == null || teaName.length == 0 || teaName.length > 50) {
			$('#TeaName').focus();
			$('#TeaName').select();
			$('#TeaNameSpan').html(
					"<font style=\"color:red\">教师姓名不能为空或长度不超过50</font>");
			return false;
		} else {
			$('#TeaNameSpan').html("");
		}

		if (csUnit == null || csUnit.length == 0) {
			$('#UnitIDSpan').html("<font style=\"color:red\">教职工单位不能为空</font>");
			return false;
		} else {
			$('#UnitIDSpan').html("");
		}
		
		if (teaEmail == null || teaEmail.length == 0 || teaEmail.match(req) == null
				|| teaEmail.length > 100) {
			$('#TeaEmail').focus();
			$('#TeaEmail').select();
			$('#TeaEmailSpan').html("<font style=\"color:red\">邮箱输入错误或长度不能为空或长度不超过100</font>");
			return false;
		} else {
			$('#TeaEmailSpan').html("");
		}
		
		if (note != null && note.length > 1000) {
			$('#Note').focus();
			$('#Note').select();
			$('#NoteSpan').html("<font style=\"color:red\">备注长度不超过1000</font>");
			return false;
		} else {
			$('#NoteSpan').html("");
		}
		return true;
	}

	function editUser() {
		var row = $('#userManager').datagrid('getSelections');

		if (row.length != 1) {
			$.messager.alert('温馨提示', "请选择1条编辑的数据！！！");
			return;
		}

		url = 'pages/UserManager/edit';

		$('#dlg').dialog('open').dialog('setTitle', '编辑用户');
		$('#seqNumber').val(row[0].seqNumber);
		$('#TeaID').val(row[0].teaID);
		$('#TeaName').val(row[0].teaName);
		$('#UnitID').combobox('select', row[0].unitID);
		$('#TeaEmail').val(row[0].teaEmail) ;
		$('#Note').val(row[0].userNote);
	}

	function deleteByIds() {
		//获取选中项
		var row = $('#userManager').datagrid('getSelections');

		if (row.length == 0) {
			$.messager.alert('温馨提示', "请选择需要删除的数据！！！");
			return;
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
				
				url = "pages/UserManager/deleteByIds?ids=" + ids ;
				deleteCourses();

			}
		});
	}

	function deleteCourses() {
		$.ajax({
			type : "POST",
			url : url,
			async : "true",
			dataType : "text",
			success : function(result) {
				result = eval("(" + result + ")");

				if (result.state) {
					alert(result.data);
					$('#userManager').datagrid('reload');
				}
			}
		}).submit();
	}

	function resetPassword() {
		//获取选中项
		var row = $('#userManager').datagrid('getSelections');

		if (row.length == 0) {
			$.messager.alert('温馨提示', "请选择需要删除的数据！！！");
			return;
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
				url = "pages/UserManager/resetPassword?ids=" + ids ;
				deleteCourses();

			}
		});
	}
	
	function addRole(){
		
		var row = $('#userManager').datagrid('getSelections');

		if (row.length != 0) {
			$.messager.alert('温馨提示', "请选择1个用户！！！");
			return;
		}
		
		url = 'pages/UserManager/insert';
		$('#roleDlg').dialog('open').dialog('setTitle', '添加用户角色');
		$('#roleForm').form('reset');
	}
	
	function addUserRole(){
		//录入数据的表单提交
		$('#userManagerForm').form('submit', {
			url : url,
			data : $('#roleForm').serialize(),
			type : "post",
			dataType : "json",
			onSubmit : function() {
				return roleValidate();
			},
			//结果返回
			success : function(result) {
				//json格式转化
				var result = eval('(' + result + ')');
				$.messager.alert('温馨提示', result.data);
				if (result.state) {
					$('#roleDlg').dialog('close');
				}
			}
		});
	}
	
	function roleValidate(){
		
		var roleName = $('#RoleID').combobox('getText');
		
		if(roleName == null || roleName.length == 0){
			$('#RoleIDSpan').html("<font style=\"color:red\">用户角色不能为空</font>");
			return false ;
		}
		return true ;
	}
	
	//日期格式转换 
	function formattime(val) {

		if (val == null) {
			return null;
		}

		var year = parseInt(val.year) + 1900;
		var month = (parseInt(val.month) + 1);
		month = month > 9 ? month : ('0' + month);
		var date = parseInt(val.date);
		date = date > 9 ? date : ('0' + date);
		var hours = parseInt(val.hours);
		hours = hours > 9 ? hours : ('0' + hours);
		var minutes = parseInt(val.minutes);
		minutes = minutes > 9 ? minutes : ('0' + minutes);
		var seconds = parseInt(val.seconds);
		seconds = seconds > 9 ? seconds : ('0' + seconds);
		var time = year + '-' + month + '-' + date;
		//alert(time) ;
		return time;
	}
</script>

</html>
