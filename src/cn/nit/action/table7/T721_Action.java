package cn.nit.action.table7;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.table7.T721_Bean;
import cn.nit.service.table7.T721_Service;

public class T721_Action {

	
	T721_Service t721_Sr=new T721_Service();
	
	T721_Bean teachResItemTea=new T721_Bean();
	
	/**  待审核数据的查询的序列号  */
	private int seqNum ;
	
	/**  待审核数据查询的起始时间  */
	private Date startTime ;
	
	/**  待审核数据查询的结束时间  */
	private Date endTime ;
	
	/**  数据的SeqNumber编号  */
	private String ids ;
	
	/**  当前查询的是第几页  */
	private String page ;
	
	/**每页显示的条数  */
	private String rows ;
	
	public void insert(){
		
		teachResItemTea.setTime(new Date());
		System.out.println(teachResItemTea.getApplvExp());
		
		boolean flag=t721_Sr.insert(teachResItemTea);
		
		PrintWriter out=null;
		
		
		try {
			getResponse().setContentType("text/html;charset=UTF-8");
			out=getResponse().getWriter();
			if (flag) {
				out.print("{\"state\":true,data:\"录入成功!!!\"}");
				
			} else {
				out.print("{\"state\":false,data:\"录入失败!!!\"}");

			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("{\"state\":false,data:\"录入失败!!!\"}") ;
		}finally{
			if(out!=null){
				out.close();
			}
		}
		out.flush() ;
		
	}
	
/**  为界面加载数据  */
	
	public void auditingData(){
		
		if(this.page == null || this.page.equals("") || !page.matches("[\\d]+")){
			return ;
		}
		
		if(this.rows == null || this.rows.equals("") || !rows.matches("[\\d]+")){
			return ;
		}
		
		String conditions=(String) getSession().getAttribute("auditingConditions");
		String pages=t721_Sr.auditingData(conditions, null,  Integer.parseInt(page), Integer.parseInt(rows));
		
		PrintWriter out=null;
		
		
		try {
			getResponse().setContentType("text/html;charset=UTF-8");
			out=getResponse().getWriter();
			out.print(pages);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}finally{
			if(out!=null){
				out.close();
				
			}
		}
		
	}
/**  生成查询条件   */
	
	public void auditingConditions(){
		
		String sqlconditions=t721_Sr.generateauditingConditions(seqNum, startTime, endTime);
		getSession().setAttribute("auditingConditions", sqlconditions);
        PrintWriter out = null ;
		
		try{
			out = getResponse().getWriter() ;
			out.print("{\"state\":true,data:\"查询失败!!!\"}") ;
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"查询失败!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
		
		
	}
	/**  编辑数据  */
	public void edit(){
		teachResItemTea.setTime(new Date());
		boolean flag=t721_Sr.update(teachResItemTea);
		
		PrintWriter out=null;
		
		try {
			out=getResponse().getWriter();
			if(flag){
				out.print("{\"state\":true,data:\"编辑成功!!!\"}") ;
			}else{
				out.print("{\"state\":true,data:\"编辑失败!!!\"}") ;
			}
			out.flush() ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("{\"state\":false,data:\"系统错误，请联系管理员!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}

	/**  根据数据的id删除数据  */
	public void deleteByIds(){
		System.out.println("ids=" + ids) ;
		boolean flag = t721_Sr.deleteByIds(ids) ;
		PrintWriter out = null ;
		
		try{
			out = getResponse().getWriter() ;
			
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
	
	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	
	public HttpSession getSession(){
		
		return getRequest().getSession();
	}
	
	public HttpServletResponse getResponse(){
		
		return ServletActionContext.getResponse();
	}
	
	public T721_Bean getTeachResItemTea(){
		
		return teachResItemTea;
	}
	
	public void setTeachResItemTea(T721_Bean teachResItemTea){
		
		this.teachResItemTea=teachResItemTea;
	}


	public void setSeqNum(int seqNum) {
		this.seqNum = seqNum;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}


	public void setPage(String page) {
		this.page = page;
	}


	public void setRows(String rows) {
		this.rows = rows;
	}
	
	
	
}
