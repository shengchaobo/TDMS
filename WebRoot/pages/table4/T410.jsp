<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>T410</title>
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
	     label {
	    width: 10em;
	    float: left;
	}
	.empty{
		width: 4em;
	}
	</style>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="js/commom.js"></script>
	<script type="text/javascript" src="js/table4/T410.js"></script>
</head>

<body style="height: 100%'" >
	<table  id="unverfiedData"  class="easyui-datagrid"  url="pages/T410/loadResInfo"  style="height: auto"  >
		<thead data-options="frozen:true">
			<tr>			
				  <th data-options="field:'ck',checkbox:true" rowspan="2">选取</th>
		     </tr>
		</thead>
		<thead>
				<tr>
					 <th  colspan="5">
					 	教师科研项目数
					</th>
					<th colspan="5"> 
						教师科研项目经费
					</th>
					<th colspan="5"> 
						近一届科研成果数（项）
					</th>
					 <th colspan="9"> 
						发表论文数（篇）
					</th>
					 <th colspan="3"> 
						出版专译著（册）
					</th>
					 <th colspan="4"> 
						获准专利（项）
					</th>
				</tr>	
				<tr>	
					<th data-options="field:'resItemNum'">
						总计
					</th>				
					<th data-options="field:'hresItemNum'" >
						横向总数
					</th>
					<th data-options="field:'hhumanItemNum'" >
						横向人文社会科学
					</th>	
					<th data-options="field:'zresItemNum'" >
						纵向总数
					</th>
					<th data-options="field:'zhumanItemNum'" >
						纵向人文社会科学
					</th>	
					<th data-options="field:'resItemFund'">
						总计
					</th>				
					<th data-options="field:'hitemFund'" >
						横向总数
					</th>
					<th data-options="field:'hhumanItemFund'" >
						横向人文社会科学
					</th>	
					<th data-options="field:'zitemFund'" >
						纵向向总数
					</th>
					<th data-options="field:'zhumanItemFund'" >
						纵向人文社会科学
					</th>						
					<th data-options="field:'resAwardNum'">
						总数
					</th>				
					<th data-options="field:'nationResAward'" >
						国家级
					</th>
					<th data-options="field:'proviResAward'" >
						省部级
					</th>	
					<th data-options="field:'cityResAward'" >
						市厅级
					</th>
					<th data-options="field:'schResAward'" >
						校级
					</th>
					<th data-options="field:'paperNum'">
						总数
					</th>				
					<th data-options="field:'sci'" >
						SCI
					</th>
					<th data-options="field:'ssci'" >
						SSCI
					</th>	
					<th data-options="field:'ei'" >
						EI
					</th>
					<th data-options="field:'istp'" >
						ISTP
					</th>						
					<th data-options="field:'inlandCoreJnal'">
						国内核心期刊
					</th>				
					<th data-options="field:'cssci'" >
						CSSCI
					</th>
					<th data-options="field:'cscd'" >
						CSCD
					</th>	
					<th data-options="field:'ohterPaper'" >
						其他
					</th>
					<th data-options="field:'publicationNum'" >
						总数
					</th>
					<th data-options="field:'treatises'" >
						专著
					</th>
					<th data-options="field:'translation'" >
						译著
					</th>
					<th data-options="field:'patentNum'" >
						总数
					</th>
					<th data-options="field:'inventPatent'" >
						发明专利
					</th>
					<th data-options="field:'utilityPatent'" >
						实用新型专利
					</th>
					<th data-options="field:'designPatent'" >
						外观设计专利
					</th>	
					<th data-options="field:'note'" rowspan="2">
						备注
					</th>
				</tr>
			</thead>
	</table>
	<div id="toolbar" style="height:auto">
		<div style="float: left;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newMajorTea()">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editCourse()">编辑</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyCourse()">删除</a>
		</div>
		 <div style="float: right;">
		 	序号: <input class="easyui-box" style="width:80px"/>
			日期 起始: <input class="easyui-datebox" style="width:80px"/>
			结束: <input class="easyui-datebox" style="width:80px"/>
			<a href="#" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		</div>
	</div>
	
	<table id="verfiedData"  class="easyui-datagrid"  url=""  style="height: auto;" >
		<thead data-options="frozen:true">
			<tr>			
				  <th data-options="field:'ck',checkbox:true" rowspan="2">选取</th>
		     </tr>
		</thead>
		<thead>
				<tr>
					 <th  colspan="5">
					 	教师科研项目数
					</th>
					<th colspan="5"> 
						教师科研项目经费
					</th>
					<th colspan="5"> 
						近一届科研成果数（项）
					</th>
					 <th colspan="9"> 
						发表论文数（篇）
					</th>
					 <th colspan="3"> 
						出版专译著（册）
					</th>
					 <th colspan="4"> 
						获准专利（项）
					</th>
				</tr>	
				<tr>	
					<th data-options="field:'resItemNum'">
						总计
					</th>				
					<th data-options="field:'hresItemNum'" >
						横向总数
					</th>
					<th data-options="field:'hhumanItemNum'" >
						横向人文社会科学
					</th>	
					<th data-options="field:'zresItemNum'" >
						纵向总数
					</th>
					<th data-options="field:'zhumanItemNum'" >
						纵向人文社会科学
					</th>	
					<th data-options="field:'resItemFund'">
						总计
					</th>				
					<th data-options="field:'hitemFund'" >
						横向总数
					</th>
					<th data-options="field:'hhumanItemFund'" >
						横向人文社会科学
					</th>	
					<th data-options="field:'zitemFund'" >
						纵向向总数
					</th>
					<th data-options="field:'zhumanItemFund'" >
						纵向人文社会科学
					</th>						
					<th data-options="field:'resAwardNum'">
						总数
					</th>				
					<th data-options="field:'nationResAward'" >
						国家级
					</th>
					<th data-options="field:'proviResAward'" >
						省部级
					</th>	
					<th data-options="field:'cityResAward'" >
						市厅级
					</th>
					<th data-options="field:'schResAward'" >
						校级
					</th>
					<th data-options="field:'paperNum'">
						总数
					</th>				
					<th data-options="field:'sci'" >
						SCI
					</th>
					<th data-options="field:'ssci'" >
						SSCI
					</th>	
					<th data-options="field:'ei'" >
						EI
					</th>
					<th data-options="field:'istp'" >
						ISTP
					</th>						
					<th data-options="field:'inlandCoreJnal'">
						国内核心期刊
					</th>				
					<th data-options="field:'cssci'" >
						CSSCI
					</th>
					<th data-options="field:'cscd'" >
						CSCD
					</th>	
					<th data-options="field:'otherPaper'" >
						其他
					</th>
					<th data-options="field:'publicationNum'" >
						总数
					</th>
					<th data-options="field:'treatises'" >
						专著
					</th>
					<th data-options="field:'translation'" >
						译著
					</th>
					<th data-options="field:'patentNum'" >
						总数
					</th>
					<th data-options="field:'inventPatent'" >
						发明专利
					</th>
					<th data-options="field:'utilityPatent'" >
						实用新型专利
					</th>
					<th data-options="field:'designPatent'" >
						外观设计专利
					</th>	
					<th data-options="field:'note'" rowspan="2">
						备注
					</th>
				</tr>
			</thead>
	</table>
	<div id="toolbar2" style="float: right;">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-download" plain="true" onclick="">数据导出</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="">高级检索</a>
	</div>
	
	<!--添加弹出框-->
	<div id="dlg" class="easyui-dialog"
		style="width:800px;height:500px;padding:10px 20px;" closed="true" data-options="modal:true"
		buttons="#dlg-buttons">
	   <h3 class="ftitle">教师科研情况导入</h3>
	   <form id="addForm" method="post">
		<table>
			<tr>
				<td style="valign:left" colspan="3">
					<div class="fitem">
						<label>请选择导入时间：</label> 
						<input class="easyui-datebox"  id="time" type="text" 
						name="T410_bean.time"  required="true"  editable="false" />
						<span id="timeSpan"></span>
					</div>
				</td>
			</tr>			
			<tr>
				<td>
 					<div class="fitem">
					<label>横向项目总数：</label> 
					<input id="hresItemNum" type="text" name="T410_bean.hresItemNum"
							class="easyui-validatebox" ><span id="hresItemNumSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
 					<div class="fitem">
					<label>横向人文社会科学项目：</label> 
					<input id="hhumanItemNum" type="text" name="T410_bean.hhumanItemNum"
							class="easyui-validatebox" ><span id="hhumanItemNumSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
 					<div class="fitem">
					<label>纵向项目总数：</label> 
					<input id="zresItemNum" type="text" name="T410_bean.zresItemNum"
							class="easyui-validatebox" ><span id="zresItemNumSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
 					<div class="fitem">
					<label>纵向人文社会科学项目：</label> 
					<input id="zhumanItemNum" type="text" name="T410_bean.zhumanItemNum"
							class="easyui-validatebox" ><span id="zhumanItemNumSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
 					<div class="fitem">
					<label>横向经费总数：</label> 
					<input id="hitemFund" type="text" name="T410_bean.hitemFund"
							class="easyui-validatebox" ><span id=" hitemFundSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
 					<div class="fitem">
					<label>横向人文社会科学经费：</label> 
					<input id="hhumanItemFund" type="text" name="T410_bean.hhumanItemFund"
							class="easyui-validatebox" ><span id="hhumanItemFundSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
 					<div class="fitem">
					<label>纵向经费总数：</label> 
					<input id="zitemFund" type="text" name="T410_bean.zitemFund"
							class="easyui-validatebox" ><span id=" zitemFundSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
 					<div class="fitem">
					<label>纵向人文社会科学经费：</label> 
					<input id="zhumanItemFund" type="text" name="T410_bean.zhumanItemFund"
							class="easyui-validatebox" ><span id="zhumanItemFundSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
 					<div class="fitem">
					<label>国家级科研成果：</label> 
					<input id="nationResAward" type="text" name="T410_bean.nationResAward"
							class="easyui-validatebox" ><span id="nationResAwardSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
 					<div class="fitem">
					<label>省部级科研成果：</label> 
					<input id="proviResAward" type="text" name="T410_bean.proviResAward"
							class="easyui-validatebox" ><span id="proviResAwardSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
 					<div class="fitem">
					<label>市厅级科研成果：</label> 
					<input id="cityResAward" type="text" name="T410_bean.cityResAward"
							class="easyui-validatebox" ><span id="cityResAwardSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
 					<div class="fitem">
					<label>校级科研成果：</label> 
					<input id="schResAward" type="text" name="T410_bean.schResAward"
							class="easyui-validatebox" ><span id="schResAwardSpan"></span>
					</div>
				</td>
			</tr>			
			<tr>
				<td>
 					<div class="fitem">
					<label>SCI：</label> 
					<input id="sci" type="text" name="T410_bean.sci"
							class="easyui-validatebox" ><span id="sciSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
 					<div class="fitem">
					<label>SSCI：</label> 
					<input id="ssci" type="text" name="T410_bean.ssci"
							class="easyui-validatebox" ><span id="ssciSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
 					<div class="fitem">
					<label>EI：</label> 
					<input id="ei" type="text" name="T410_bean.ei"
							class="easyui-validatebox" ><span id="eiSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
 					<div class="fitem">
					<label>ISTP：</label> 
					<input id="istp" type="text" name="T410_bean.istp"
							class="easyui-validatebox" ><span id="istpSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
 					<div class="fitem">
					<label>国内核心期刊：</label> 
					<input id="inlandCoreJnal" type="text" name="T410_bean.inlandCoreJnal"
							class="easyui-validatebox" ><span id="inlandCoreJnalSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
 					<div class="fitem">
					<label>CSSCI：</label> 
					<input id="cssci" type="text" name="T410_bean.cssci"
							class="easyui-validatebox" ><span id="cssciSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
 					<div class="fitem">
					<label>CSCD：</label> 
					<input id="cscd" type="text" name="T410_bean.cscd"
							class="easyui-validatebox" ><span id="cscdSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
 					<div class="fitem">
					<label>其他论文数：</label> 
					<input id="otherPaper" type="text" name="T410_bean.otherPaper"
							class="easyui-validatebox" ><span id="otherPaperSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
 					<div class="fitem">
					<label>出版专著数：</label> 
					<input id="treatises" type="text" name="T410_bean.treatises"
							class="easyui-validatebox" ><span id="treatisesSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
 					<div class="fitem">
					<label>出版译著数：</label> 
					<input id="translation" type="text" name="T410_bean.translation"
							class="easyui-validatebox" ><span id="translationSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
 					<div class="fitem">
					<label>发明专利：</label> 
					<input id="inventPatent" type="text" name="T410_bean.inventPatent"
							class="easyui-validatebox" ><span id="inventPatentSpan"></span>
					</div>
				</td>
				<td class="empty"></td>
				<td>
 					<div class="fitem">
					<label>实用新型专利：</label> 
					<input id="utilityPatent" type="text" name="T410_bean.utilityPatent"
							class="easyui-validatebox" ><span id="utilityPatentSpan"></span>
					</div>
				</td>
			</tr>			
			<tr>
				<td colspan="3">
 					<div class="fitem">
					<label>外观设计专利：</label> 
					<input id="designPatent" type="text" name="T410_bean.designPatent"
							class="easyui-validatebox" ><span id="designPatentSpan"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td style="valign:left" colspan="3"><label>备注：</label>
					<textarea id="note" name="T410_bean.note" style="resize:none" cols="50" rows="10"></textarea>
					<span id="noteSpan"></span>
				</td>
			</tr>			
		</table>
		</form>
	</div>
	<!-- 跟dlg组合-->
	<div id="dlg-buttons"  >
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="singleImport()">保存</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
</body>
</html>
