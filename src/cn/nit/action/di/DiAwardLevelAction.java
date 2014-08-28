package cn.nit.action.di;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.di.DiAwardLevelBean;
import cn.nit.service.di.DiAwardLevelService;


public class DiAwardLevelAction {

	HttpServletResponse response = ServletActionContext.getResponse();
	private DiAwardLevelService AwardLevelServices = new DiAwardLevelService() ;
	private DiAwardLevelBean level_bean = new DiAwardLevelBean();
	private String ids ;
	//查出所�?
	public void loadAwardLevel() throws Exception{
		
		List<DiAwardLevelBean> list = AwardLevelServices.getList() ;
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
	
	/**
	 * 校级即校级以上的荣誉
	 * */
	public void loadDIAwardLevelPart(){
			
		    HttpServletResponse response = ServletActionContext.getResponse();
			List<DiAwardLevelBean> list = AwardLevelServices.getListPart() ;
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
	
	/**
	 * 校级、系�?
	 * */
	public void loadDIAwardLevelPartTwo(){
			
		    HttpServletResponse response = ServletActionContext.getResponse();
			List<DiAwardLevelBean> list = AwardLevelServices.getListPartTwo() ;
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
	
	public void edit(){
		
		boolean flag = AwardLevelServices.update(level_bean) ;
		
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
	 * 添加用户
	 */
	public void insert(){
		
		//首先该用 户是否已存数据库
		Boolean flag0 = AwardLevelServices.hasLevel(level_bean.getIndexId());
		
		boolean flag = false;
		//如果该用户不存数据库，可以添加
		if(flag0 == false){			
			flag = AwardLevelServices.insert(level_bean) ;
		}
		
		PrintWriter out = null ;
		
		try{
			
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			
			if(flag0){
				out.print("{\"state\":true,data:\"该荣誉奖项级别已存在!!!\"}") ;
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
	
	/**
	 * 删除用户
	 */
	public void deleteByIds(){
		
		boolean flag = AwardLevelServices.deleteByIds(ids) ;
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
	
	public void setLevel_bean(DiAwardLevelBean level_bean) {
		this.level_bean = level_bean;
	}

	public DiAwardLevelBean getLevel_bean() {
		return level_bean;
	}
	
	public HttpServletResponse getResponse(){
		return ServletActionContext.getResponse() ;
	}
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	

}
