package cn.nit.service;

import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.QueryConditionsBean;
import cn.nit.dao.QueryResultDAO;
import cn.nit.util.Pagition;

public class QueryService {

	QueryResultDAO queryResultDAO=new QueryResultDAO();
	
	public <T> String getTableData(String querySql){
		
		List<T> list=queryResultDAO.getTableData(querySql);
        JSON json=JSONSerializer.toJSON(list);
		System.out.println(json.toString());
		return json.toString();
	}
}
