<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="cn.nit.constants.Constants"%>
<%@ page import="java.net.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>普通本科分专业（大类）学生数</title>
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
	<link rel="stylesheet" type="text/css" href="jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui/demo/demo.css">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="js/commom.js"></script>
	<script type="text/javascript" src="js/table6/T615.js"></script>
</head>
<% request.setAttribute("CHECKTYPE",Constants.CTypeOne); %>
<% request.setAttribute("NOCHECK",Constants.NO_CHECK); %>
<% request.setAttribute("PASS",Constants.PASS_CHECK); %>
<body style="height: 100%'"  onload="myMarquee('T615','<%=request.getAttribute("CHECKTYPE") %>')">
  <div  id="floatDiv">
        <span style="font:12px; font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;审核未通过提示消息：</span>
        <marquee id="marquee"  scrollAmount="1"  width="900"  height="40" direction="up"  style="color: red;"  onmouseover="stop()" onmouseout="start()">
        </marquee>       
  </div>
  <br/> 
  
	<table id="unverfiedData" title="待审核数据域审核未通过数据" class="easyui-datagrid" 
	url="pages/T615/loadData?checkNum=<%=request.getAttribute("NOCHECK") %>"  style="height: auto;">
		<thead   data-options="frozen:true">
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber">编号</th>
					<th  data-options="field:'checkState'"   formatter="formatCheckState">审核状态</th>
				<th field="majorName">校内专业（大类）名称</th>
				<th field="majorId">校内专业（大类）代码</th>
				<th field="fromUnitId">所属教学单位</th>
				<th field="unitId">单位号</th>
			</tr>
		</thead>
		<thead>
			<tr>
				<th field="schLen">学制</th>
				<th field="schStuSumNum">在校生总人数</th>
				<th field="freshmanNum">一年级生人数</th>
				<th field="sophomoreNum">二年级生人数</th>
				<th field="juniorNum">三年级生人数</th>
				<th field="seniorNum">四年级生人数</th>
				<th field="otherGradeNum">五年级生及以上人数</th>
				<th field="minorNum">辅修学生人数</th>
				<th field="dualDegreeNum">双学位学生人数</th>
				<th field="changeInNum">转入人数</th>
				<th field="changeOutNum">转出人数</th>
				<th field="time" formatter="formattime">时间</th>
				<th field="note">备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar" style="height:auto">
		<div style="float: left;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newItem()">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editItem()">编辑</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteByIds()">删除</a>
		</div>
		 <div style="float: right;">
		 <form method="post" id="searchForm"
				style="float: right; height: 24px;">
				<table id="test" width="520">
					<tr>
						<td>
							编号:
						</td>
						<td>
							<input id="seqNum" name="seqNum" class="easyui-box"
								style="width: 40px" />
						</td>
						<td>
							起始日期:
						</td>
						<td>
							<input id="startTime" name="startTime" class="easyui-datebox"
								style="width: 100px" />
						</td>
						<td>
							结束日期:
						</td>
						<td>
							<input id="endTime" name="endTime" class="easyui-datebox"
								style="width: 100px" />
						</td>
						<td>
							<a href="javascript:void(0)" class="easyui-linkbutton"
								iconCls="icon-search" plain="true" onclick=	reloadgrid();>查询</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	<!--审核通过数据-->
	<table id="verfiedData"  class="easyui-datagrid"  
	url="pages/T615/loadData?checkNum=<%=request.getAttribute("PASS") %>" style="height: auto;" >
		<thead   data-options="frozen:true">
			<tr>
				<th field="seqNumber">编号</th>
				<th field="majorName">校内专业（大类）名称</th>
				<th field="majorId">校内专业（大类）代码</th>
				<th field="fromUnitId">所属教学单位</th>
				<th field="unitId">单位号</th>
			</tr>
		</thead>
		<thead>
			<tr>
				<th field="schLen">学制</th>
				<th field="schStuSumNum">在校生总人数</th>
				<th field="freshmanNum">一年级生人数</th>
				<th field="sophomoreNum">二年级生人数</th>
				<th field="juniorNum">三年级生人数</th>
				<th field="seniorNum">四年级生人数</th>
				<th field="otherGradeNum">五年级生及以上人数</th>
				<th field="minorNum">辅修学生人数</th>
				<th field="dualDegreeNum">双学位学生人数</th>
				<th field="changeInNum">转入人数</th>
				<th field="changeOutNum">转出人数</th>
				<th field="time" formatter="formattime">时间</th>
				<th field="note">备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar2" style="float: right;">
	 <form action='pages/T615/dataExport?excelName=<%=URLEncoder.encode("表6-1-5普通本科分专业（大类）学生数（教务处）","UTF-8")%>'   method="post"  id="exportForm" enctype="multipart/form-data"  style="float: right;">
					  <select class="easyui-combobox"  id="cbYearContrast1" name="selectYear"  editable=false ></select>&nbsp;&nbsp;
						<a href='javascript:submitForm()'   style="font:12px;color: black;text-decoration:none;" >
								数据导出
						</a> &nbsp;&nbsp;&nbsp;&nbsp;		
			</form>
		
	</div>
	
	
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<h3 class="title1">普通本科分专业（大类）学生数批量导入</h3>
		<div class="fitem" id="item1">
			<form id="batchForm" method="post" enctype="multipart/form-data">
				<select class="easyui-combobox"  id="cbYearContrast" name="selectYear" editable="false"></select>
				<input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox" size="48" style="height: 24px;"
					validType="fileType['xls']" required="true" invalidMessage="请选择Excel格式的文件" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">模板导入</a>
				<a href='pages/T615/downloadModel?saveFile=<%=URLEncoder.encode("表6-1-5普通本科分专业（大类）学生数（教务处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
		</div>	
		<hr></hr>	
		<h3 class="title1">普通本科分专业（大类）学生数逐条导入</h3>
		<form id="addItemForm" method="post">
		<table>
			<tr>
				<td>
					<div class="fitem">
						<label>校内专业（大类）名称：</label> 
						<input id="seqNumber" type="hidden" name="T615_bean.seqNumber">	
						<input id="majorName" type="hidden" name="T615_bean.majorName">
						<input id="majorId" type="text" name="T615_bean.majorId" class='easyui-combobox'
							data-options="valueField:'majorNum',textField:'majorName',url:'pages/DiMajorTwo/loadDiMajorTwo',listHeight:'auto',editable:false,
							onSelect:function(){
							 	 document.getElementById('majorName').value=$(this).combobox('getText') ;
							 }"></input>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>所属教学单位：</label> 
						<input id="fromUnitId" type="hidden" name="T615_bean.fromUnitId">										
						<input id="unitId" type="text" name="T615_bean.unitId" class='easyui-combobox'
							data-options="valueField:'unitId',textField:'unitName',url:'pages/DiDepartment/loadDIDepartmentAca',listHeight:'auto',editable:false,
							onSelect:function(){
							 	 document.getElementById('fromUnitId').value=$(this).combobox('getText') ;
							 }">
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>学制：</label> 
						<input id="schLen" name="T615_bean.schLen" 
							 class='easyui-validatebox'><span id="schLenSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>在校生总人数：</label> 
						<input id="schStuSumNum" name="T615_bean.schStuSumNum" 
							 class='easyui-validatebox'><span id="schStuSumNumSpan"></span>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div class="fitem">
						<label>一年级生人数：</label> 
						<input id="freshmanNum" name="T615_bean.freshmanNum" 
							 class='easyui-validatebox'><span id="freshmanNumSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>二年级生人数：</label> 
						<input id="sophomoreNum" name="T615_bean.sophomoreNum" 
							 class='easyui-validatebox'><span id="sophomoreNumSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>三年级生人数：</label> 
						<input id="juniorNum" name="T615_bean.juniorNum" 
							 class='easyui-validatebox'><span id="juniorNumSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>四年级生人数：</label> 
						<input id="seniorNum" name="T615_bean.seniorNum" 
							 class='easyui-validatebox'><span id="seniorNumSpan"></span>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div class="fitem">
						<label>五年级生及以上人数：</label> 
						<input id="otherGradeNum" name="T615_bean.otherGradeNum" 
							 class='easyui-validatebox'><span id="otherGradeNumSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>辅修学生人数：</label> 
						<input id="minorNum" name="T615_bean.minorNum" 
							 class='easyui-validatebox'><span id="minorNumSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>双学位学生人数：</label> 
						<input id="dualDegreeNum" name="T615_bean.dualDegreeNum" 
							 class='easyui-validatebox'><span id="dualDegreeNumSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>转入人数：</label> 
						<input id="changeInNum" name="T615_bean.changeInNum" 
							 class='easyui-validatebox'><span id="changeInNumSpan"></span>
					</div>
				</td>
			</tr>
			
			<tr>
				<td>
					<div class="fitem">
						<label>转入人数：</label> 
						<input id="changeOutNum" name="T615_bean.changeOutNum" 
							 class='easyui-validatebox'><span id="changeOutNumSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
					<div class="fitem">
						<label>时间：</label> 
						<input class="easyui-datebox"  id="time" type="text" 
						name="T615_bean.time"  editable="false" />
						<span id="timeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td style="valign:left"  colspan="3"><label>备注：</label>
					<textarea id="note" name="T615_bean.note" style="resize:none" cols="50" rows="10"></textarea>
					<span id="noteSpan"></span>
				</td>
			</tr>
		</table>
		</form>
	</div>
	

	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="singleImport()">保存</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
</body>

	<script type="text/javascript">
    	var currentYear = new Date().getFullYear();
    	var select = document.getElementById("cbYearContrast");
    	for (var i = 0; i <= 10; i++) {
        var theOption = document.createElement("option");
        	theOption.innerHTML = currentYear-i + "年";
        	theOption.value = currentYear-i;
        	select.appendChild(theOption);
    	}
	</script>
	<script type="text/javascript">
    	var currentYear = new Date().getFullYear();
    	var select = document.getElementById("cbYearContrast1");
    	for (var i = 0; i <= 10; i++) {
        var theOption = document.createElement("option");
        	theOption.innerHTML = currentYear-i + "年";
        	theOption.value = currentYear-i;
        	select.appendChild(theOption);
    	}
	</script>

</html>
