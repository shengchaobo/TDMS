<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix= "s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'T12_SchAdminUnit_PartyOffice.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="pages/css/globale.css">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
         <Table width="100%" border="1" id="table1">
            <tr>
            <td colspan="4" >
    		<div style="height: 25px; float: left;margin-left: 20px;margin-top: 5px;">
            <form action="searchInAdmin" method="post" >
                                 行政单位号：
                <input type="text" name="studentno" size="15" align="middle">
                <input type="submit" value="提交"/>
            </form>
            </div>
            </td>
             <td colspan="4" align="center"><a href="#">下载模板</a></td>
          </tr>
          </Table>
          <Table width="100%" border="1" id="table2">
          <tr  bordercolor="black" >
            <th colspan="8" align="center">
                                                      学校行政单位信息
            </th>
          </tr>         
          <tr bordercolor="black" >
             <th>
                                          序号
             </th>
             <th>
                                       行政单位名称
             </th>
             <th>
                                           单位号
             </th>
             <th>
                                            单位职能
             </th>
             <th>
                                         单位负责人
             </th>
             <th>
                                           教工号
             </th>
              <th>
                                           操作
             </th>
          </tr >
          
          <s:iterator value = "#request.student" id="student">
            <tr>
              <td align="center"> 
                  <s:property value = "SeqNumber"/>
              </td>
              <td align="center"> 
                  <s:property value = "UnitName"/>
              </td>
              <td align="center"> 
                  <s:property value = "identity"/>
              </td>
              <td align="center"> 
                  <s:property value = "Functions"/>
              </td>
              <td align="center"> 
                  <s:property value = "nationality"/>
              </td>
              <td align="center"> 
                  <s:property value = "Leader"/>
              </td>
              <td align="center"> 
                  <s:property value = "TeaID"/>
              </td>
                <td align="center">
						<a href="javascript:void(0)"
							onclick="">
							操作</a>
			    </td>
            </tr>
          </s:iterator>
          <tr >
				<td colspan="8" align="center">
					<input type="submit" value="保存" size="15" >
					<input type="reset" value="重置" />
				</td>
			</tr>
       </Table>
  </body>
</html>
