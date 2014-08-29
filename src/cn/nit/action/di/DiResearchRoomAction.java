package cn.nit.action.di;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.di.DiResearchRoomBean;
import cn.nit.bean.di.DiResearchRoomBean;
import cn.nit.service.di.DiResearchRoomService;


public class DiResearchRoomAction {

	HttpServletResponse response = ServletActionContext.getResponse();
	private DiResearchRoomService ResearchRoomServices = new DiResearchRoomService() ;
	private DiResearchRoomBean room_bean = new DiResearchRoomBean() ;

	public DiResearchRoomBean getRoom_bean() {
		return room_bean;
	}

	public void setRoom_bean(DiResearchRoomBean roombean) {
		this.room_bean = roombean;
	}

	/**  部门编号 */
	private String ids ;
	
	/**  数据分页的当前页  */
	private String page ;
	
	/**  当前页共显示的总记录数  */
	private String rows ;
	
	private String searchID; 
	//查出所有
	public void loadResearchRoom() throws Exception{
		
		List<DiResearchRoomBean> list = ResearchRoomServices.getList() ;
		//将数据转换为json格式
		JSON json = JSONSerializer.toJSON(list) ;
		PrintWriter out = null ;
		
		try {
			//设置输出内容的格式为json
			response.setContentType("application/json; charset=UTF-8") ;
			out = response.getWriter() ;
			//设置数据的内容的编码格式
			String outPrint = URLDecoder.decode(json.toString(), "UTF-8") ;
			out.print(outPrint) ;
			out.flush() ;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	public void loadRooms(){
		
		if(this.page == null || this.page.equals("") || !page.matches("[\\d]+")){
			return ;
		}
		
		if(this.rows == null || this.rows.equals("") || !rows.matches("[\\d]+")){
			return ;
		}
		
		String cond = null;
		if(this.getSearchID()!= null){
			cond = " and UnitID LIKE '" + this.getSearchID() + "%'";
			System.out.println(cond);
		}
		
		String json = ResearchRoomServices.loadRooms(cond, Integer.parseInt(page), Integer.parseInt(rows));
		PrintWriter out = null ;
		
		try{
			out = getResponse().getWriter();
			out.print(json);
		}catch(Exception e){
			e.printStackTrace();
			return ;
		}finally{
			out.flush();
			
			if(out != null){
				out.close();
			}
		}
	}

	public void edit(){

				
		boolean flag = ResearchRoomServices.update(room_bean) ;
		
		PrintWriter out = null ;
		
		try{
			
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"更新成功!!!\"}") ;
			}else{
				out.print("{\"state\":true,data:\"更新失败!!!\"}") ;
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
	
	
	/**
	 * 添加部门
	 */
	public void insert(){
		
		//首先该用 户是否已存数据库
		Boolean flag0 = ResearchRoomServices.hasRoom(room_bean.getUnitId());
		
		boolean flag = false;
		//如果该用户不存数据库，可以添加
		if(flag0 == false){			
			flag = ResearchRoomServices.insert(room_bean) ;
		}
		
		PrintWriter out = null ;
		
		try{
			
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			
			if(flag0){
				out.print("{\"state\":true,data:\"该教研室已存在!!!\"}") ;
			}else{
				if(flag){
					out.print("{\"state\":true,data:\"添加成功!!!\"}") ;
				}else{
					out.print("{\"state\":false,data:\"添加失败!!!\"}") ;
				}
			}

			
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"添加失败!!!\"}") ;
		}finally{
			out.flush() ;
			
			if(out != null){
				out.close() ;
			}
		}
	}
	
	public void deleteByIds(){
		
		boolean flag = ResearchRoomServices.deleteByIds(ids) ;
		PrintWriter out = null ;
		
		try{
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			
			if(flag){
				out.print("{\"state\":true,data:\"删除成功!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"删除失败!!!\"}") ;
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
		return ServletActionContext.getRequest() ;
	}
	
	/**
	 * 获取session
	 * @return
	 *
	 * @time: 2014-5-14/下午03:07:14
	 */
	public HttpSession getSession(){
		return getRequest().getSession() ;
	}
	
	/**
	 * 获取response
	 * @return
	 *
	 * @time: 2014-5-14/下午03:07:24
	 */
	public HttpServletResponse getResponse(){
		return ServletActionContext.getResponse() ;
	}

	public void setSearchID(String searchID) {
		this.searchID = searchID;
	}

	public String getSearchID() {
		return searchID;
	}

	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

}
