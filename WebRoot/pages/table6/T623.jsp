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

<title>近一届艺术类本科生录取标准及人数（招就处）</title>
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
	<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="js/commom.js"></script>
	<script type="text/javascript" src="js/table6/T623.js"></script>
</head>

<body>
	<table id="commomData" title="近一届艺术类本科生录取标准及人数" class="easyui-datagrid" url="pages/T623/loadData" style="height: auto;">
		<thead data-options="frozen:true">
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber">序号</th>
				<th field="province">省份</th>
				<th field="artType">类型</th>
				<th field="batch">批次</th>
			</tr>
		</thead>	
		<thead>
			<tr>
				<th field="libEnrollNum">文科录取数</th>
				<th field="sciEnrollNum">理科录取数</th>		
				<th field="sumEnrollNum">综合录取数</th>		
				<th field="libLowestScore">文化批次最低控制线（分）</th>		
				<th field="sciLowestScore">专业批次最低控制线（分）</th>	
				<th field="sumLowestScore">综合批次最低控制线（分）</th>			
				<th field="libAvgScore">文化当年录取平均分数（分）</th>
				<th field="sciAvgScore">专业当年录取平均分数（分）</th>
				<th field="sumAvgScore">综合当年录取平均分数（分）</th>		
				<th field="note">说明</th>
				<th field="time" formatter="formattime">填写时间</th>
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
		 	省份: <input id="searchItem" class="easyui-box" style="width:80px"/>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="reloadgrid()">查询</a>
		</div>
	</div>
	
	<!--审核通过数据-->
	<table id="verfiedData"  class="easyui-datagrid"  url=""  style="height: auto;" >
		<thead data-options="frozen:true">
			<tr>
				<th data-options="field:'ck',checkbox:true">选取</th>
				<th field="seqNumber">序号</th>
				<th field="province">省份</th>
				<th field="artType">类型</th>
				<th field="batch">批次</th>
			</tr>
		</thead>	
		<thead>
			<tr>
				<th field="libEnrollNum">文科录取数</th>
				<th field="sciEnrollNum">理科录取数</th>		
				<th field="sumEnrollNum">综合录取数</th>		
				<th field="libLowestScore">文化批次最低控制线（分）</th>		
				<th field="sciLowestScore">专业批次最低控制线（分）</th>	
				<th field="sumLowestScore">综合批次最低控制线（分）</th>			
				<th field="libAvgScore">文化当年录取平均分数（分）</th>
				<th field="sciAvgScore">专业当年录取平均分数（分）</th>
				<th field="sumAvgScore">综合当年录取平均分数（分）</th>		
				<th field="note">说明</th>
				<th field="time" formatter="formattime">填写时间</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar2" style="float: right;">
		<a href='pages/T623/dataExport?excelName=<%=URLEncoder.encode("表6-2-3近一届艺术类本科生录取标准及人数（招就处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download" plain="true" >数据导出</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="">高级检索</a>
	</div>
	
	
	<!--添加弹出框-->
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
		<div class="ftitle" id="title1">近一届艺术类本科生录取标准及人数批量导入</div>
		<div class="fitem" id="item1">
			<form id="batchForm" method="post" enctype="multipart/form-data">
				<label>批量上传：</label> 
				<input type="file" name="uploadFile" id="uploadFile" class="easyui-validatebox" size="48" style="height: 24px;"
					validType="fileType['xls']" required="true" invalidMessage="请选择Excel格式的文件" />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="batchImport()">导入</a>
				<a href='pages/T623/downloadModel?saveFile=<%=URLEncoder.encode("表6-2-3近一届艺术类本科生录取标准及人数（招就处）.xls","UTF-8")%>'  class="easyui-linkbutton" iconCls="icon-download">模板下载</a>
			</form>
		</div>
	
		<div></div>
		<div class="ftitle">近一届艺术类本科生录取标准及人数逐条导入</div>
		<form id="addItemForm" method="post">
		<table>
			<tr>
				<td>
					<div class="fitem">
						<label>省份：</label> 
						<input id="seqNumber" type="hidden" name="T623_bean.seqNumber">		
						<select id="province" type="text" name="T623_bean.province" class='easyui-combobox'  panelHeight="auto" editable="false">
							<option value="北京市">北京市</option>
							<option value="天津市">天津市</option>
							<option value="河北省">河北省</option>
							<option value="山西省">山西省</option>
							<option value="内蒙古自治区">内蒙古自治区</option>
							<option value="辽宁省">辽宁省</option>
							<option value="吉林省">吉林省</option>
							<option value="黑龙江省">黑龙江省</option>
							<option value="上海市">上海市</option>
							<option value="江苏省">江苏省</option>
							<option value="浙江省">浙江省</option>
							<option value="安徽省">安徽省</option>
							<option value="福建省">福建省</option>
							<option value="江西省">江西省</option>
							<option value="山东省">山东省</option>
							<option value="河南省">河南省</option>
							<option value="湖北省">湖北省</option>
							<option value="湖南省">湖南省</option>
							<option value="广东省">广东省</option>
							<option value="广西壮族自治区">广西壮族自治区</option>
							<option value="海南省">海南省</option>
							<option value="重庆市">重庆市</option>
							<option value="四川省">四川省</option>
							<option value="贵州省">贵州省</option>
							<option value="云南省">云南省</option>
							<option value="西藏自治区">西藏自治区</option>
							<option value="陕西省">陕西省</option>
							<option value="甘肃省">甘肃省</option>
							<option value="青海省">青海省</option>
							<option value="宁夏回族自治区">宁夏回族自治区</option>
							<option value="新疆维吾尔自治区">新疆维吾尔自治区</option>
							<option value="台湾">台湾</option>
							<option value="香港">香港</option>
							<option value="澳门">澳门</option>
						</select>
						<span id="provinceSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>类型：</label> 	
						<select id="artType" type="text" name="T623_bean.artType" class='easyui-combobox'  panelHeight="auto" editable="false">
							<option value="美术类">美术类</option>
							<option value="音乐类">音乐类</option>
						</select>
						<span id="artTypeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>批次：</label> 	
						<select id="batch" type="text" name="T623_bean.batch" class='easyui-combobox'  panelHeight="auto" editable="false">
							<option value="提前批招生">提前批招生</option>
							<option value="第一批次招生">第一批次招生</option>
							<option value="第二批次招生A">第二批次招生A</option>
							<option value="第二批次招生B">第二批次招生B</option>
							<option value="第三批次招生A">第三批次招生A</option>
							<option value="第三批次招生B">第三批次招生B</option>
						</select>
						<span id="batchSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>文科录取数：</label> 
						<input id="libEnrollNum" name="T623_bean.libEnrollNum" 
							 class='easyui-validatebox'><span id="libEnrollNumSpan"></span>
					</div>
				</td>
			</tr>
			
			<tr>
				<td>
					<div class="fitem">
						<label>理科录取数：</label> 
						<input id="sciEnrollNum" name="T623_bean.sciEnrollNum" 
							 class='easyui-validatebox'><span id="sciEnrollNumSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>综合录取数：</label> 
						<input id="sumEnrollNum" name="T623_bean.sumEnrollNum" 
							 class='easyui-validatebox'><span id="sumEnrollNumSpan"></span>
					</div>
				</td>
			</tr>
			

			<tr>
				<td>
					<div class="fitem">
						<label>文化批次最低控制线（分）：</label> 
						<input id="libLowestScore" name="T623_bean.libLowestScore" 
							 class='easyui-validatebox'><span id="libLowestScoreSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>专业批次最低控制线（分）：</label> 
						<input id="sciLowestScore" name="T623_bean.sciLowestScore" 
							 class='easyui-validatebox'><span id="sciLowestScoreSpan"></span>
					</div>
				</td>
			</tr>
			
			<tr>
				<td>
					<div class="fitem">
						<label>综合批次最低控制线（分）：</label> 
						<input id="sumLowestScore" name="T623_bean.sumLowestScore" 
							 class='easyui-validatebox'><span id="sumLowestScoreSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>文化当年录取平均分数（分）：</label> 
						<input id="libAvgScore" name="T623_bean.libAvgScore" 
							 class='easyui-validatebox'><span id="libAvgScoreSpan"></span>
					</div>
				</td>
			</tr>
			
			
			
			<tr>
				<td>
					<div class="fitem">
						<label>专业当年录取平均分数（分）：</label> 
						<input id="sciAvgScore" name="T623_bean.sciAvgScore" 
							 class='easyui-validatebox'><span id="sciAvgScoreSpan"></span>
					</div>
				</td>
				<td>
					<div class="fitem">
						<label>综合当年录取平均分数（分）：</label> 
						<input id="sumAvgScore" name="T623_bean.sumAvgScore" 
							 class='easyui-validatebox'><span id="sumAvgScoreSpan"></span>
					</div>
				</td>
				
			</tr>
			<tr>
				<td>
					<div class="fitem">
						<label>填写时间：</label> 
						<input class="easyui-datebox"  id="time" type="text" 
						name="T623_bean.time"  editable="false" />
						<span id="timeSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td style="valign:left" colspan="3"><label>备注：</label>
					<textarea id="note" name="T623_bean.note" style="resize:none" cols="50" rows="10"></textarea>
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

</html>
