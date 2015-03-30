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

<title>C410</title>
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
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="jquery-easyui/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/dialog_bug.js"></script>
	<script type="text/javascript" src="js/commom.js"></script>
	<script type="text/javascript">
	 //审核通过
  function passCheck(seqNumber){
    		//alert(seqNumber);
    		var checkNum = <%=Constants.PASS_CHECK %>  
    		//alert(checkNum);
		    $.ajax({
				    type:"POST", 
				    url: "pages/T410/updateCheck?seqNum=" + seqNumber +"&checkNum=" + checkNum, 
			   		async : "true",
			   		dataType : "text",
				    success:function(result){  
				         result = eval("(" + result + ")");						    	 
					  		 if (!result.state){
								  		 	$.messager.show({
								  		 			title: 'Error',
								  		 			msg: result.data
								  			 });
						  		} else {
								    		 $('#checkData').datagrid('reload'); // reload the user data
								    		 $('#checkPassData').datagrid('reload'); // reload the user data										    		 
						  		}
				    }
				});
    }
			
    //审核不通过
  function noPassCheck(seqNumber){
   	    
     		var checkNum = <%=Constants.NOPASS_CHECK %>  
    		//alert(checkNum);
		    $.ajax({
				    type:"POST", 
				    url: "pages/T410/updateCheck?seqNum=" + seqNumber +"&checkNum=" + checkNum, 
			   		async : "true",
			   		dataType : "text",
				    success:function(result){  
				         result = eval("(" + result + ")");						    	 
					  		 if (!result.state){
								  		 	$.messager.show({
								  		 			title: 'Error',
								  		 			msg: result.data
								  			 });
						  		} else {
								    		 $('#checkData').datagrid('reload'); // reload the user data		
								    		 $('#checkPassData').datagrid('reload'); // reload the user data										    		 
						  		}
				    }
				});  	    
    }
    
   //全部审核通过
  function checkAll(){
  		  if(confirm("全部数据审核通过，该操作不可恢复，确认吗？")){ 		  	
			    $.ajax({
					    type:"POST", 
					    url: "pages/T410/checkAll", 
				   		async : "true",
				   		dataType : "text",
					    success:function(result){  
					         result = eval("(" + result + ")");						    	 
						  		 if (!result.state){
									  		 	$.messager.show({
									  		 			title: 'Error',
									  		 			msg: result.data
									  			 });
							  		} else {
									    		 $('#checkData').datagrid('reload'); // reload the user data
								    		     $('#checkPassData').datagrid('reload'); // reload the user data											    		 
							  		}
					    }
					});
				}
    }
	</script>
</head>

<% request.setAttribute("CHECKTYPE",Constants.CTypeOne); %>
<% request.setAttribute("WAITCHECK",Constants.WAIT_CHECK); %>
<% request.setAttribute("PASSCHECK",Constants.PASS_CHECK); %>
<body style="height: 100%'">
	<table  id="checkData"  class="easyui-datagrid"  url="pages/T410/loadResInfo?checkNum=<%=request.getAttribute("WAITCHECK") %>"   style="height: auto"  >
		<thead data-options="frozen:true">
			<tr>			
				<th  data-options="field:'check',align:'center'"   formatter="rowformater">审核操作</th>
		  </tr>
		</thead>
		<thead>	
			<tr>	
				  <th  data-options="field:'seqNumber'"  rowspan="2">编号</th>
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
	
	<table  id="checkPassData"  class="easyui-datagrid"  url="pages/T410/loadResInfo?checkNum=<%=request.getAttribute("PASSCHECK")%>&checkFlag=0"   style="height: auto"  >
		<thead data-options="frozen:true">
			<tr>			
				<th  data-options="field:'check',align:'center'"   formatter="rowformater1">审核操作</th>
		  </tr>
		</thead>
		<thead>	
			<tr>	
				  <th  data-options="field:'seqNumber'"  rowspan="2">编号</th>
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
	<!--<div id="toolbar"  style="float: right;">
			<a href='javascript:checkAll()'   class="easyui-linkbutton" iconCls="icon-download" plain="true"  >
					一键审核通过
			</a>
	</div>-->
	<div id="dlg" class="easyui-dialog"
		style="width:400px;height:270px;padding:10px 20px;" closed="true"
		data-options="modal:true" buttons="#dlg-buttons">
		<form id="addReasonForm" method="post">
			<table>			
				<tr>
					<td>
						<div class="fitem">
							<label>审核类型：</label> 
								<input type="text" name="checkInfo.checkType"  id="checkType"   value="<%=request.getAttribute("CHECKTYPE") %>"
								readonly="readonly"  style="width: 150px;color: grey"/>
							</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="fitem">
							<label>被审核表ID：</label> 
								<input type="text" name="checkInfo.tableID" id="tableName"   value="T410"
								readonly="readonly"  style="width: 150px;color: grey"/>
							</div>
					</td>
				</tr>
				<tr>
					<td>
							<div class="fitem">
								<label>该条数据编号：</label> 
								<input type="text" name="checkInfo.checkID" id="dataID" 
								readonly="readonly"  style="width: 150px;color: grey"/>
							</div>
					</td>
				</tr>
				<tr>
					<td>
							<div class="fitem">
								<label>审核不通过理由描述：</label> 
								<textarea id="noPassReason" name="checkInfo.checkInfo" style="resize:none" cols="30" rows="5"></textarea>
							</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="addCheckInfo()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
</body>
</html>
