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
	//查出所有
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
	 * 校级、系级
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
	

}
