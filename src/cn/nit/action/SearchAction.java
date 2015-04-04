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
import cn.nit.bean.UserinfoBean;
import cn.nit.bean.di.DiTableMessageBean;
import cn.nit.service.QueryService;
import cn.nit.service.di.DiTableMessageService;

public class SearchAction {


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
			ColumnInfoBean columnInfoBean = new ColumnInfoBean();
			columnInfoBean.setTitle(bean.getTname());
			columnInfoBean.setField(bean.getTid());
			columnInfoBean.setAlign("center");
			columnInfoBean.setWidth(120);
			if(bean.getTFieldFlag() == 30){
				columnInfoBean.setFormatter("formattime");
			}
			if(bean.getTFieldFlag() == 31){
				columnInfoBean.setFormatter("formatBoolean");
			}
			if(bean.getTFieldFlag() == 6){
				columnInfoBean.setFormatter("formatIdcode");
			}
			if(bean.getTFieldFlag() == 7){
				columnInfoBean.setFormatter("formatEducation");
			}
			if(bean.getTFieldFlag() == 8){
				columnInfoBean.setFormatter("formatDegree");
			}
			if(bean.getTFieldFlag() == 9){
				columnInfoBean.setFormatter("formatSource");
			}
			if(bean.getTFieldFlag() == 10){
				columnInfoBean.setFormatter("formatMachTitle");
			}
			if(bean.getTFieldFlag() == 11){
				columnInfoBean.setFormatter("formatTeaTitle");
			}
/*			if(bean.getTFieldFlag() == 12){
				columnInfoBean.setFormatter("formatTutor");
			}
			if(bean.getTFieldFlag() == 13){
				columnInfoBean.setFormatter("formatHighTalent");
			}
			if(bean.getTFieldFlag() == 14){
				columnInfoBean.setFormatter("formatHighTeam");
			}
			if(bean.getTFieldFlag() == 15){
				columnInfoBean.setFormatter("formatAwardLevel");
			}
			if(bean.getTFieldFlag() == 16){
				columnInfoBean.setFormatter("formatAwardType");
			}
			if(bean.getTFieldFlag() == 17){
				columnInfoBean.setFormatter("formatPaperType");
			}
			if(bean.getTFieldFlag() == 18){
				columnInfoBean.setFormatter("formatCourseCat");
			}
			if(bean.getTFieldFlag() == 19){
				columnInfoBean.setFormatter("formatCourseChar");
			}
			if(bean.getTFieldFlag() == 20){
				columnInfoBean.setFormatter("formatCourseAttri");
			}
			if(bean.getTFieldFlag() == 21){
				columnInfoBean.setFormatter("formatEvaluType");
			}
			if(bean.getTFieldFlag() == 22){
				columnInfoBean.setFormatter("formatContestType");
			}
			if(bean.getTFieldFlag() == 23){
				columnInfoBean.setFormatter("formatContestScope");
			}*/
			list.add(columnInfoBean);
		}		
		
		List tmList = new ArrayList();//必须再套一层
		tmList.add(list);
		JSONArray jsonArray = JSONArray.fromObject(tmList);
		System.out.println(jsonArray.toString());
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().write(jsonArray.toString());
	}
	
	
	//查询表单内数据
    public void loadQueryResult(){
    	
    	System.out.println(this.getQuerySql());
    	
    	String query;
    	
		//具体教学单位
		UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		String fillUnitID;
		String tempUnitID = bean.getUnitID().substring(0,1);
		if("3".equals(tempUnitID)){
			fillUnitID = bean.getUnitID();
			query = this.getQuerySql() + (" and FillUnitID=" + fillUnitID);
		}else{
			query = this.getQuerySql();
		}

		String pages = queryService.getTableData(query);
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
