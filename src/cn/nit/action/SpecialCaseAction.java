package cn.nit.action;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.SpecialCaseBean;
import cn.nit.bean.UserinfoBean;
import cn.nit.service.SpecialCaseService;
import cn.nit.util.ExcelUtil;


public class SpecialCaseAction {
	
	private String rows; //每页显示的记录数
	
	private String page; //当前第几页
	
	private SpecialCaseService case_services = new SpecialCaseService();
	
	private SpecialCaseBean specialCaseBean = new SpecialCaseBean();
	
	
	/**  待审核数据的要删除的序列集  */
	private String ids; //删除的id
	
	
	/**  下载的excelName  */
	private String excelName ;



	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	//查询出所有
	public void loadCaseInfo() throws Exception{
		
		HttpServletResponse response = ServletActionContext.getResponse() ;	
		String cond = null;
		StringBuffer conditions = new StringBuffer();

		
		//具体教学单位
		UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		String fillUnitID;
		String tempUnitID = bean.getUnitID().substring(0,1);
		if("3".equals(tempUnitID)){
			fillUnitID = bean.getUnitID();
			conditions.append(" and RoleID='" + bean.getRoleID() + "' and FillUnitID='" + fillUnitID + "'") ;
		}else{
			conditions.append(" and RoleID='" + bean.getRoleID()+"'") ;
		}
		cond = conditions.toString();
		
		List<SpecialCaseBean> list = case_services.getCaseInfo(cond, this.getRows(), this.getPage()) ;
		String TeaInfoJson = this.toBeJson(list,case_services.getTotal(cond));
		//private JSONObject jsonObj;
		
		PrintWriter out = null ;

		if(TeaInfoJson == null){			
			return ;
		}else{
			try {
				
				System.out.println(TeaInfoJson) ;
				response.setContentType("application/json;charset=UTF-8") ;
				out = response.getWriter() ;
				out.print(TeaInfoJson) ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(out != null){
					out.flush() ;
					out.close() ;
				}
			}
		}
	}

    //将分页系统的总数以及当前页的list转化一个json传页面显示
	private String toBeJson(List<SpecialCaseBean> list, int total) throws Exception{
		// TODO Auto-generated method stub
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		 

		JSONObject testjson = new JSONObject();
		testjson.accumulate("total", total);
		testjson.accumulate("rows", list);
		
        String json = testjson.toString();
		return json;
	}
	
	//插入一个新的教职工
	public void insert(){
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		HttpServletResponse response = ServletActionContext.getResponse();
		
		
		UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		String tempUnitID = bean.getUnitID().substring(0,1);
		if("3".equals(tempUnitID)){
			specialCaseBean.setRoleID(bean.getRoleID());
			specialCaseBean.setFillUnitID(bean.getUnitID());
		}else{
			specialCaseBean.setRoleID(bean.getRoleID());
		}		
		boolean flag = case_services.insert(specialCaseBean);
		PrintWriter out = null ;
		
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"录入成功!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"录入失败!!!\"}") ;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"录入失败!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
		out.flush() ;
	}
	
	
	/**  编辑数据  */
	public void edit(){

		boolean flag = case_services.update(specialCaseBean) ;
		PrintWriter out = null ;
	
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"修改成功!!!\"}") ;
			}else{
				out.print("{\"state\":true,data:\"修改失败!!!\"}") ;
			}
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"系统错误，请联系管理员!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}

	/**  根据数据的id删除数据  */
	public void deleteByIds(){
		System.out.println("ids=" + this.getIds()) ;
		boolean flag = case_services.deleteByIds(ids) ;
		PrintWriter out = null ;
		
		try{
						
			response.setContentType("application/json; charset=UTF-8") ;
			out = response.getWriter() ;			
			if(flag){
				out.print("{\"state\":true,data:\"数据删除成功!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"数据删除失败!!!\"}") ;
			}
			
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"系统错误，请联系管理员!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	public InputStream getInputStream() throws UnsupportedEncodingException{

		InputStream inputStream = null ;
		UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		String tempUnitID = bean.getUnitID().substring(0,1);
		String roleID = null;
		String fillUnitID = null;
		if("3".equals(tempUnitID)){
			roleID = bean.getRoleID();			
			fillUnitID = bean.getUnitID();
		}else{
			roleID = bean.getRoleID();
		}
		
		try {
	
			List<SpecialCaseBean> list = case_services.totalList(roleID, fillUnitID);
						
			String sheetName = this.excelName;
			
			List<String> columns = new ArrayList<String>();
			columns.add("序号");
			columns.add("表名");columns.add("说明");columns.add("备注");
			
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);
			maplist.put("TableName", 1);maplist.put("Instruction", 2);maplist.put("Note", 3);
						
			inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, sheetName, maplist,columns).toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}

		return inputStream ;
	}
	
	public String execute() throws Exception{
		System.out.println("excelName=============" + this.excelName) ;
		return "success" ;
	}
	
	public String getRows() {
		return rows;
	}


	public void setRows(String rows) {
		this.rows = rows;
	}


	public String getPage() {
		return page;
	}


	public void setPage(String page) {
		this.page = page;
	}
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public String getExcelName() {
		try {
			this.excelName = URLEncoder.encode(excelName, "UTF-8");
			//this.saveFile = new String(saveFile.getBytes("ISO-8859-1"),"UTF-8");// 中文乱码解决
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

	public void setSpecialCaseBean(SpecialCaseBean specialCaseBean) {
		this.specialCaseBean = specialCaseBean;
	}

	public SpecialCaseBean getSpecialCaseBean() {
		return specialCaseBean;
	}
}