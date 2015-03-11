package cn.nit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;


import cn.nit.bean.ColumnInfoBean;

public class ColumnReAction {
	 
	  ColumnInfoBean columnInfoBean = new ColumnInfoBean();
	  HttpServletRequest request=ServletActionContext.getRequest();
	  HttpServletResponse response=ServletActionContext.getResponse();
	  public void loadColumnResult() throws IOException{
		  List<ColumnInfoBean> list = new ArrayList<ColumnInfoBean>();
		  
		  columnInfoBean.setTitle("区域<div id='drawline'></div>名称");
		  columnInfoBean.setAlign("center");
		  columnInfoBean.setWidth(120);
		  columnInfoBean.setField("appName");
		  list.add(0,columnInfoBean);
	      JSONArray jsonArray = JSONArray.fromObject(list);
	      getResponse().setContentType("text/html; charset=utf-8");
	      getResponse().getWriter().write(jsonArray.toString());
		  
	  }
	  
	  
	  
	  public ColumnInfoBean getColumnInfoBean() {
			return columnInfoBean;
		}

		public void setColumnInfoBean(ColumnInfoBean columnInfoBean) {
			this.columnInfoBean = columnInfoBean;
		}



		public HttpServletRequest getRequest() {
			return request;
		}



		public void setRequest(HttpServletRequest request) {
			this.request = request;
		}



		public HttpServletResponse getResponse() {
			return response;
		}



		public void setResponse(HttpServletResponse response) {
			this.response = response;
		}
	  

}
