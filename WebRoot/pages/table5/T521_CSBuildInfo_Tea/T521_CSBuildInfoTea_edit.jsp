<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>表5-2-1课程建设情况（教务处）——编辑</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <form method="post" action="pages/table5/csBuildTeaEdit">
    	<table>
    		<tr>
    			<td>
    				<label>类型：</label>
    				<select style="width:120px;" onchange="document.getElementById('input').value=this.value">
					    <option value="A类">精品视频公开课</option>
					    <option value="B类">精品资源共享课</option>
					    <option value="C类">微课</option>
					    <option value="D类">双语教学示范课程</option>
					 </select>
    			</td>
    			<td>
    				<label>课程名称：</label>
    				<input type="text" name="csName" />
    			</td>
    			<td>
    				<label>课程编号：</label>
    				<input type="text" name="csNO" />
    			</td>
    		</tr>
    		<tr>
    			<td>
    				<label>级别：</label>
    				<select style="width:120px;" onchange="document.getElementById('input').value=this.value">
					    <option value="A类">国际级</option>
					    <option value="B类">国家级</option>
					    <option value="C类">省部级</option>
					    <option value="D类">市级</option>
					    <option value="E类">校级</option>
					 </select>
    			</td>
    			<td>
    				<label>负责人：</label>
    				<input type="text" name="responsibility"/>
    			</td>
    			<td>
    				<label>教工号：</label>
    				<input type="text" name="teaId"/>
    			</td>
    		</tr>
    		<tr>
    			<td>
	    			<label>参与教师人数：</label>
	    			<input type="text" name="teaNO"/>
    			</td>
    			<td>
    				<label>其他参与教师：</label>
    				<input type="text" name="teaName"/>
    			</td>
    			<td>
    				<label>课程访问链接：</label>
    				<input type="text" name="link"/>
    			</td>
    		</tr>
    		<tr>
    			<td>
    				<label>获准时间：</label>
    				<input type="text" name="appliedTime"/>
    			</td>
    			<td>
    				<label>验收时间：</label>
    				<input type="text" name="acceptTime"/>
    			</td>
    			<td>
    				<label>所属教学单位：</label>
    				<input type="text" name="dept"/>
    			</td>
    		</tr>
    		<tr>
    			<td>
    				<label>单位号：</label>
    				<input type="text" name="deptId"/>
    			</td>
    			<td>
    				<label>批文号：</label>
    				<input type="text" name="batchId"/>
    			</td>
    			<td>
    				<label>备注：</label>
    				<input type="text" name="remark"/>
    			</td>
    		</tr>
    		<tr>
    			<td><input type="submit" value="保存"/></td>
    			<td><button onclick="window.location.href('pages/1_SchBasicInfo/T12_SchAdminUnit_PartyOffice.jsp')">取消</button></td>
    		</tr>
    	</table>
    </form>
  </body>
</html>
