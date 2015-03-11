package cn.nit.action;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.di.DiFieldNameBean;
import cn.nit.dao.FieldNameDAO;


public class FieldNameAction {
	private FieldNameDAO fieldNameDAO=new FieldNameDAO();
	private String tablename;
	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	public void loadFieldName() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse() ;	
		List<DiFieldNameBean> list=fieldNameDAO.getlist(tablename);
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

	public FieldNameDAO getFieldNameDAO() {
		return fieldNameDAO;
	}

	public void setFieldNameDAO(FieldNameDAO fieldNameDAO) {
		this.fieldNameDAO = fieldNameDAO;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	
	
}
