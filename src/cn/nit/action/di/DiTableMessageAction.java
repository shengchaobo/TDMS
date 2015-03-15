package cn.nit.action.di;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import cn.nit.bean.UserinfoBean;
import cn.nit.bean.di.DiTableMessageBean;
import cn.nit.service.di.DiTableMessageService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class DiTableMessageAction {
	
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	DiTableMessageService diTableMessageService = new DiTableMessageService();
	DiTableMessageBean ditableMessageBean = new DiTableMessageBean();
	
	private String tableName;
	
	public void loadTableMessage() throws Exception{
		
		DiTableMessageService diTableMessageService = new DiTableMessageService();

		UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		
		List<DiTableMessageBean> list = diTableMessageService.getlist(bean.getRoleID());
				
		JSON json = JSONSerializer.toJSON(list) ;
		PrintWriter out = null ;
		try {
			//设置输出内容的格式为json
			response.setContentType("application/json; charset=UTF-8") ;
			out = response.getWriter() ;
			//设置数据的内容的编码格式
			//System.out.println(json.toString());
			out.print(json.toString()) ;
			out.flush() ;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	public void loadTableField() throws Exception{
		
		DiTableMessageService diTableMessageService = new DiTableMessageService();
		
		List<DiTableMessageBean> list = diTableMessageService.getFieldlist(this.getTableName());
				
		JSON json = JSONSerializer.toJSON(list) ;
		PrintWriter out = null ;
		try {
			//设置输出内容的格式为json
			response.setContentType("application/json; charset=UTF-8") ;
			out = response.getWriter() ;
			//设置数据的内容的编码格式
			//System.out.println(json.toString());
			out.print(json.toString()) ;
			out.flush() ;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	

	public DiTableMessageBean getDitableMessageBean() {
		return ditableMessageBean;
	}

	public void setDitableMessageBean(DiTableMessageBean ditableMessageBean) {
		this.ditableMessageBean = ditableMessageBean;
	}

	public static void main(String args[]){
		DiTableMessageAction t=new DiTableMessageAction();
			try {
				t.loadTableMessage();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}
}
