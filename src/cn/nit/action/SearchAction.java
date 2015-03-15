package cn.nit.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;


import cn.nit.action.di.DiTableMessageAction;
import cn.nit.bean.ColumnInfoBean;
import cn.nit.bean.QueryConditionsBean;
import cn.nit.bean.di.DiTableMessageBean;
import cn.nit.service.QueryService;
import cn.nit.service.di.DiTableMessageService;

public class SearchAction {

	ColumnInfoBean columnInfoBean = new ColumnInfoBean();
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	QueryService queryService = new QueryService();
	DiTableMessageService tableService = new DiTableMessageService();
	
	
	private String querySql;
	private String tableName;

	//查询表单表头信息
	public void loadColumnResult() throws IOException {
		List<ColumnInfoBean> list = new ArrayList<ColumnInfoBean>();
		
		List<DiTableMessageBean> fieldList = tableService.getFieldlist(this.getTableName());
		for(DiTableMessageBean bean:fieldList){
			columnInfoBean.setTitle(bean.getTname());
			columnInfoBean.setField(bean.getTid());
			columnInfoBean.setAlign("center");
			columnInfoBean.setWidth(120);
		}		
		list.add(columnInfoBean);
		
		List tmList = new ArrayList();//必须再套一层
		tmList.add(list);
		JSONArray jsonArray = JSONArray.fromObject(tmList);
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().write(jsonArray.toString());
	}
	
	//查询表单内数据
    public void loadQueryResult(){
    	System.out.println(this.getQuerySql());

		String pages = queryService.getTableData(this.getQuerySql());
	    PrintWriter out = null ;
		
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
			out.print(pages) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
    }

	public void setQuerySql(String querySql) {
		this.querySql = querySql;
	}

	public String getQuerySql() {
		return querySql;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}
}
